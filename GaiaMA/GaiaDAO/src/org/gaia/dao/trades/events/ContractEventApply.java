/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.trades.events;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductEventDetail;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeEntity;
import org.gaia.domain.trades.TradeGroup;
import org.gaia.domain.utils.DomainUtils;

/*
 * @author Benjamin Frerejean
 */
public class ContractEventApply implements IEventApply {

    private static final Logger logger = Logger.getLogger(ContractEventApply.class);
    ContractEvent event;

    public enum ContractEventType {

        Unwind, Termination, NovationTransfer, NovationTermination, NovationUnwind, NovationCreation
    };

    public ContractEventApply(Integer eventId) {
        event = ProductAccessor.getContractEventById(eventId);
        if (event == null) {
            logger.error("CreditEventApply ERROR : no Credit Event found for id " + eventId);
        }
    }

    @Override
    public List<ProductEventDetail> applyOnProduct(Product product, ProductEvent productEvent) {
        List<ProductEventDetail> productEventDetailList = new ArrayList();
        try {// add to product event details
            if (product != null) {
                // CLASSICAL OTC ONLY  on cleared OTC , the impact is on trade
                if (ProductTypeUtil.getProductTypeByName(product.getProductType()).getUse().name().equalsIgnoreCase(ProductTypeUtil.ProductTypeUse.OTC.name())) {
                    // lowers notional : works for all events
                    ProductEventDetail productEventDetail = new ProductEventDetail("NotionalMultiplier",
                            String.valueOf(product.getNotionalMultiplier().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor()))),
                            String.valueOf(product.getNotionalMultiplier()), null);
                    product.setNotionalMultiplier(product.getNotionalMultiplier().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                    productEventDetailList.add(productEventDetail);
                    // if the event closes the trade, we change the maturity
                    if (event.getUnwindFactor().doubleValue() == 1) {
                        ProductEventDetail productEventDetail2 = new ProductEventDetail("MaturityDate",
                                HibernateUtil.dateFormat.format(event.getValueDate()), HibernateUtil.dateFormat.format(product.getMaturityDate()), null);
                        product.setMaturityDate(event.getValueDate());
                        if (product.getSubProducts() != null) {
                            for (Product sub : product.getSubProducts()) {
                                sub.setMaturityDate(event.getValueDate());
                            }
                        }
                        productEventDetailList.add(productEventDetail2);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return productEventDetailList;
    }

    @Override
    public List<Integer> applyOnTrade(Trade trade, ProductEvent productEvent) {
        List<Integer> ids = new ArrayList();
        try {

            //  CLASSICAL OTC ONLY
            if (ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType()).getUse().name().equalsIgnoreCase(ProductTypeUtil.ProductTypeUse.OTC.name())) {
                // add the payment flow
                Flow newFlow = FlowAccessor.fillCashFlow(null, event.getPaymentAmount(), event.getPaymentCurrency(), trade,
                        event.getPaymentDate(), event.getNegociationDate(), null, Flow.FlowSubType.EVENT);
                FlowAccessor.storeFlow(newFlow);

                BigDecimal qtty = trade.getQuantity().multiply(trade.getProduct().getNotionalMultiplier());
                // add a comment on trade
                trade.setComment(StringUtils.cleanString(trade.getComment()) + StringUtils.SPACE + productEvent.getEventType() + StringUtils.SPACE + DomainUtils.decimalFormat.format(qtty) + " on " + DomainUtils.dateFormat.format(event.getValueDate()));
                ids.add(trade.getTradeId());

                // novation transfer : change of counterpart
                if (event.getContractEvent().equalsIgnoreCase(ContractEventType.NovationTransfer.name())) {

                    // create a new product
                    Product secondProduct = ProductAccessor.cloneProduct(trade.getProduct());
                    secondProduct.setNotionalMultiplier(BigDecimal.ONE);
                    if (event.getUnwindFactor().doubleValue() == 1) {
                        // if the event closed the first trade
                        // the maturity of first product has been changed
                        Date oldMaturity = HibernateUtil.dateFormat.parse(productEvent.getProductEventDetails().get(1).getOldValue());
                        secondProduct.setMaturityDate(oldMaturity);
                        if (secondProduct.getSubProducts() != null) {
                            for (Product component : secondProduct.getSubProducts()) {
                                component.setMaturityDate(oldMaturity);
                            }
                        }
                    }
                    secondProduct.setStartDate(event.getValueDate());
                    if (secondProduct.getSubProducts() != null) {
                        for (Product component : secondProduct.getSubProducts()) {
                            component.setStartDate(event.getValueDate());
                        }
                    }
                    // create the new trade
                    Trade newTrade = trade.clone();
                    newTrade.setTradeId(null);
                    newTrade.setQuantity(trade.getQuantity().multiply(event.getUnwindFactor()));
                    newTrade.setAmount(trade.getAmount().multiply(event.getUnwindFactor()));
                    newTrade.setProduct(secondProduct);
                    newTrade.setCounterparty(event.getCounterparty());
                    newTrade.setTradeDate(event.getNegociationDate());
                    newTrade.setValueDate(event.getValueDate());
                    newTrade.getTradeEntityCollection().add(new TradeEntity(newTrade, trade.getCounterparty(),
                            LegalEntityRole.LegalEntityRoleEnum.TRANSFEROR.name));
                    newTrade.setComment(trade.getComment() + " generated by the novation of trade " + trade.getTradeId() + " on " + DomainUtils.dateFormat.format(event.getValueDate()));
                    newTrade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
                    newTrade = TradeAccessor.storeTrade(newTrade);
                    ids.add(newTrade.getTradeId());
                    // create group
                    TradeGroup group = new TradeGroup(TradeGroup.TradeGroupType.Event.name());
                    group.addTrade(trade);
                    group.addTrade(newTrade);
                    trade.getTradeGroups().add(group);
                    newTrade.getTradeGroups().add(group);
                    HibernateUtil.saveObject(group);
                    // update the trade
                    trade.getTradeEntityCollection().add(new TradeEntity(trade, event.getCounterparty(),
                            LegalEntityRole.LegalEntityRoleEnum.TRANSFEREE.name));
                    if (event.getUnwindFactor().doubleValue() == 1) {
                        trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.NOVATED.name);
                    } else {
                        trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.UNWINDED.name);
                    }
                } else if (event.getContractEvent().equalsIgnoreCase(ContractEventType.NovationTermination.name())) {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.TERMINATED.name);
                    TradeEntity entity = new TradeEntity(trade, event.getCounterparty(), "Transferee");
                    trade.getTradeEntityCollection().add(entity);
                } else if (event.getContractEvent().equalsIgnoreCase(ContractEventType.Termination.name())) {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.TERMINATED.name);
                } else if (event.getContractEvent().equalsIgnoreCase(ContractEventType.NovationUnwind.name())) {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.UNWINDED.name);
                    TradeEntity entity = new TradeEntity(trade, event.getCounterparty(), "Transferee");
                    trade.getTradeEntityCollection().add(entity);
                } else if (event.getContractEvent().equalsIgnoreCase(ContractEventType.Unwind.name())) {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.UNWINDED.name);
                }
                TradeAccessor.storeTrade(trade);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
            ids.clear();
        }
        return ids;
    }
    public static final String APPLY_ON_CLEARED_TRADE = "applyOnClearedTrade";

    public static String applyOnClearedTrade(Trade trade, ContractEvent event) {
        String ids = StringUtils.EMPTY_STRING;
        try {

            //  CLEARED OTC : LOOKS LIKE LISTED
            // first : lower current position
            Trade unwindTrade = trade.clone();
            unwindTrade.setQuantity(trade.getQuantity().multiply(event.getUnwindFactor()).negate());
            unwindTrade.setAmount(event.getPaymentAmount());
            unwindTrade.setTradeDate(event.getNegociationDate());
            unwindTrade.setValueDate(event.getPaymentDate());
            if (trade.getConvertedAmount() != null) {
                unwindTrade.setConvertedAmount(trade.getConvertedAmount().multiply(event.getUnwindFactor()).negate());
            }
            BigDecimal price = BigDecimal.ONE;
            if (event.getUnwindAmount() != null && event.getUnwindAmount().doubleValue() != 0) {
                price = event.getPaymentAmount().divide(event.getUnwindAmount(), 10, RoundingMode.UP);
            }
            unwindTrade.setPrice(price);
            unwindTrade.setStatus(TradeAccessor.TradeLifeCycleStatus.NOVATED.name);
            unwindTrade = TradeAccessor.storeTrade(unwindTrade);
            // create group
            TradeGroup group = new TradeGroup(TradeGroup.TradeGroupType.Event.name());
            group.addTrade(trade);
            trade.getTradeGroups().add(group);
            group.addTrade(unwindTrade);
            unwindTrade.getTradeGroups().add(group);
            ids = trade.getTradeId() + StringUtils.COMMA + unwindTrade.getTradeId();
            // the novation transfer : create second one
            if (event.getContractEvent().equalsIgnoreCase(ContractEventType.NovationTransfer.name())) {
                Trade newTrade = trade.clone();
                newTrade.setCounterparty(event.getCounterparty());
                newTrade.setQuantity(trade.getQuantity().multiply(event.getUnwindFactor()));
                newTrade.setTradeDate(event.getNegociationDate());
                newTrade.setCreationDateTime(new Date());
                newTrade.setValueDate(event.getPaymentDate());
                if (trade.getConvertedAmount() != null) {
                    newTrade.setConvertedAmount(trade.getConvertedAmount().multiply(event.getUnwindFactor()));
                }
                newTrade = TradeAccessor.storeTrade(newTrade);
                group.addTrade(newTrade);
                newTrade.getTradeGroups().add(group);
                ids += StringUtils.COMMA + newTrade.getTradeId();
            }
            HibernateUtil.saveObject(group);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return ids;
    }

    @Override
    public void applyOnFlows(Flow flow) {

        //  CLASSICAL OTC ONLY
        if (ProductTypeUtil.getProductTypeByName(flow.getTrade().getProduct().getProductType()).getUse().name().equalsIgnoreCase(ProductTypeUtil.ProductTypeUse.OTC.name())) {
            if (Flow.FlowType.CASH.name().equalsIgnoreCase(flow.getFlowType())
                    && flow.getSettlementDate().after(event.getValueDate())) {
                flow.setQuantity(flow.getQuantity().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                FlowAccessor.storeFlow(flow);
            }
            // impact on position : create a principal flow in the other way
            if (Flow.FlowSubType.PRINCIPAL.name().equalsIgnoreCase(flow.getSubType())
                    && Flow.FlowType.ASSET.name().equalsIgnoreCase(flow.getFlowType())) {
                if (flow.getTrade().getTradeDate().equals(flow.getTradeDate())) {
                    Flow newFlow = (Flow) flow.clone();
                    // set date to event date on trade date
                    newFlow.setValueDate(event.getValueDate());
                    newFlow.setTradeDate(event.getValueDate());
                    newFlow.setSettlementDate(event.getValueDate());
                    newFlow.setSubType(Flow.FlowSubType.EVENT.name());
                    newFlow.setQuantity(flow.getQuantity().multiply(event.getUnwindFactor()).negate());
                    newFlow.setFlowId(null);
                    FlowAccessor.storeFlow(newFlow);
                } else if (flow.getTrade().getProduct().getMaturityDate().equals(flow.getTradeDate())) {
                    flow.setQuantity(flow.getQuantity().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                    FlowAccessor.storeFlow(flow);
                }
            }
        }
    }

    @Override
    public void applyOnScheduleLines(List<ScheduleLine> lines) {
        boolean isOTC = false;
        //  CLASSICAL OTC ONLY
        if (lines.size() > 0) {
            isOTC = ProductTypeUtil.getProductTypeByName(lines.get(0).getProduct().getProductType()).getUse().name().equalsIgnoreCase(ProductTypeUtil.ProductTypeUse.OTC.name());
        }
        if (isOTC) {
            for (ScheduleLine scheduleLine : lines) {

                if (scheduleLine.getEndDate() != null && scheduleLine.getEndDate().after(event.getValueDate())) {
                    // period schedules
                    if (scheduleLine.getStartDate().before(event.getValueDate())) {
                        long period = DateUtils.dateDiff(scheduleLine.getStartDate(), scheduleLine.getEndDate());
                        long period1 = DateUtils.dateDiff(scheduleLine.getStartDate(), event.getValueDate());
                        long period2 = DateUtils.dateDiff(event.getValueDate(), scheduleLine.getEndDate());
                        BigDecimal factor = new BigDecimal(period1).add((BigDecimal.ONE.subtract(event.getUnwindFactor()).multiply(new BigDecimal(period2))));
                        factor = factor.divide(new BigDecimal(period), 20, RoundingMode.UP);
                        scheduleLine.setNotional(scheduleLine.getNotional().multiply(factor));
                        scheduleLine.setPaymentAmount(scheduleLine.getPaymentAmount().multiply(factor));
                    } else {
                        scheduleLine.setNotional(scheduleLine.getNotional().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                        scheduleLine.setPaymentAmount(scheduleLine.getPaymentAmount().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                    }
                } else if (scheduleLine.getPaymentDate().after(event.getValueDate())) {
                    // other schedule line types
                    scheduleLine.setNotional(scheduleLine.getNotional().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                    scheduleLine.setPaymentAmount(scheduleLine.getPaymentAmount().multiply(BigDecimal.ONE.subtract(event.getUnwindFactor())));
                }
            }
        }

    }

    @Override
    public String getEventType() {
        if (event != null) {
            return event.getContractEvent();
        }
        return null;
    }

    @Override
    public Date getEffectiveDate() {
        if (event != null) {
            return event.getValueDate();
        }
        return null;
    }

    @Override
    public String getEventDescription() {
        String ret = StringUtils.EMPTY_STRING;
        if (event != null) {
            return event.toString();
        }
        return ret;
    }

    public static final String APPLY_TERMINATION = "applyTermination";

    public static void applyTermination(List<ScheduleLine> lines) {
        for (ScheduleLine line : lines) {
            ContractEvent event = new ContractEvent();
            event.setContractEvent(ContractEventApply.ContractEventType.Termination.name());
            event.setNegociationDate(DateUtils.getDate());
            event.setPaymentAmount(BigDecimal.ZERO);
            event.setPaymentCurrency(line.getCurrency());
            event.setProduct(line.getProduct());
            event.setUnwindAmount(BigDecimal.ZERO);
            event.setUnwindFactor(BigDecimal.ONE);
            event.setValueDate(DateUtils.getDate());
            Integer eventId = (Integer) HibernateUtil.saveObject(event);
            EventEngine.applyEventOnProduct(ContractEventApply.class, eventId, line.getProduct().getProductId());
        }
    }
}
