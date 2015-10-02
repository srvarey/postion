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
package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import static org.gaia.dao.trades.TradeAccessor.generateTradeSpecificFlows;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeEntity;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class FlowAccessor {


    private static final Logger logger = Logger.getLogger(FlowAccessor.class);

    public enum FlowStatus {CREATED, VALIDATED,  CANCELLED};


    public static final String GENERATE_AND_STORE_SCHEDULE_FLOWS = "generateAndStoreScheduleFlows";

    /**
     * generates and stores the schedule flows on a trade
     *
     * @param trade the trade
     */
    public static void generateAndStoreScheduleFlows(Trade trade) {
        HibernateUtil.executeQuery("delete Flow f where f.trade.tradeId=" + trade.getId() + " and f.subType='" + Flow.FlowSubType.INTEREST + StringUtils.QUOTE);
        ArrayList<Flow> flows = generateScheduleFlows(trade);
        for (Flow flow : flows) {
            storeFlow(flow);
        }
    }
    public static final String GENERATE_AND_STORE_SCHEDULE_FLOWS_IF_NEEDED = "generateAndStoreScheduleFlowsIfNeeded";

    public static void generateAndStoreScheduleFlowsIfNeeded(Trade trade) {
        List<Flow> storedFlows = getScheduleFlowsByTrade(trade.getTradeId());
        List<Flow> flows = generateScheduleFlows(trade);
        if (flows.size() != storedFlows.size()) {
            // in this case we regenrates it all
            HibernateUtil.executeQuery("delete Flow f where f.trade.tradeId=" + trade.getId() + " and f.subType='" + Flow.FlowSubType.INTEREST + StringUtils.QUOTE);
            for (Flow flow : flows) {
                storeFlow(flow);
            }
        } else {
            int i = 0;
            for (Flow storedFlow : storedFlows) {
                boolean hasChanged = false;
                Flow calcFlow = flows.get(i);
                if (Math.abs(calcFlow.getQuantity().doubleValue() - storedFlow.getQuantity().doubleValue()) > 0.000001) {
                    storedFlow.setQuantity(calcFlow.getQuantity());
                    hasChanged = true;
                }
                if (calcFlow.getProduct().getProductId() != storedFlow.getProduct().getProductId()) {
                    storedFlow.setProduct(calcFlow.getProduct());
                    hasChanged = true;
                }
                if (!calcFlow.getValueDate().equals(storedFlow.getValueDate())) {
                    storedFlow.setValueDate(calcFlow.getValueDate());
                    hasChanged = true;
                }
                if (!calcFlow.getFlowType().equalsIgnoreCase(storedFlow.getFlowType())) {
                    storedFlow.setFlowType(calcFlow.getFlowType());
                    hasChanged = true;
                }
                if (calcFlow.getCounterparty().getLegalEntityId().intValue() != storedFlow.getCounterparty().getLegalEntityId().intValue()) {
                    storedFlow.setCounterparty(calcFlow.getCounterparty());
                    hasChanged = true;
                }
                if (calcFlow.getInternalCounterparty().getLegalEntityId().intValue() != storedFlow.getInternalCounterparty().getLegalEntityId().intValue()) {
                    storedFlow.setInternalCounterparty(calcFlow.getInternalCounterparty());
                    hasChanged = true;
                }
                if (calcFlow.getCcp() != null && storedFlow.getCcp() != null
                        && calcFlow.getCcp().getLegalEntityId().intValue() != storedFlow.getCcp().getLegalEntityId().intValue()) {
                    storedFlow.setCcp(calcFlow.getCcp());
                    hasChanged = true;
                }
                if (!calcFlow.getSettlementDate().equals(storedFlow.getSettlementDate())) {
                    storedFlow.setSettlementDate(calcFlow.getSettlementDate());
                    hasChanged = true;
                }
                if (!calcFlow.getStatus().equalsIgnoreCase(storedFlow.getStatus())) {
                    storedFlow.setStatus(calcFlow.getStatus());
                    hasChanged = true;
                }
                if (hasChanged) {
                    storeFlow(storedFlow);
                }
                i++;
            }
        }
    }
    public static final String GENERATE_SCHEDULE_FLOWS = "generateScheduleFlows";

    /**
     * generates the schedule flows on a trade
     *
     * @param trade the trade
     * @return
     */
    public static ArrayList<Flow> generateScheduleFlows(Trade trade) {
        ArrayList<Flow> flows = new ArrayList();

        if (trade.getProduct() != null) {
            Schedule schedule = ScheduleBuilder.buildScheduleFromTrade(trade);
            for (ScheduleLine scheduleLine : schedule.getScheduleLines()) {
                double amount = scheduleLine.getPaymentAmount().doubleValue();
                if (amount != 0 && scheduleLine.isFixed()) {
//                    String status = scheduleLine.isFixed() ? "KNOWN" : "PENDING";
                    Flow flow = fillCashFlow(null, scheduleLine.getPaymentAmount(), scheduleLine.getCurrency(), trade, scheduleLine.getPaymentDate(), scheduleLine.getPaymentDate(),
                            scheduleLine.getScheduleLineId(), Flow.FlowSubType.INTEREST);
                    flows.add(flow);
                }
//                HibernateUtil.storeObject(scheduleLine);
            }
        }
        return flows;
    }

    /**
     * Fills a cash flow
     *
     * @param flow
     * @param amount
     * @param currency
     * @param trade the trade
     * @param valueDate
     * @param tradeDate
     * @param subType
     * @param scheduleLineId
     * @return
     */
    public static Flow fillCashFlow(Flow flow, BigDecimal amount, String currency, Trade trade, Date valueDate, Date tradeDate, Integer scheduleLineId, Flow.FlowSubType subType) {
        Product product = ProductAccessor.getProductByShortName(currency);

        return fillFlow(flow, amount, Trade.QuantityType.QUANTITY.name, product, trade, valueDate, tradeDate,
                scheduleLineId, Flow.FlowType.CASH, subType);
    }

    /**
     * fills a flow
     *
     * @param flow the flow to fill (null if new)
     * @param quantity the cash amount
     * @param quantityType
     * @param product
     * @param trade the trade
     * @param valueDate the value date
     * @param tradeDate
     * @param scheduleLineId the schedule line id
     * @param type the type
     * @param subType the sub type
     * @return
     */
    public static Flow fillFlow(Flow flow, BigDecimal quantity, String quantityType, Product product, Trade trade, Date valueDate, Date tradeDate,
            Integer scheduleLineId, Flow.FlowType type, Flow.FlowSubType subType) {

        if (quantity!=null && quantity.doubleValue() != 0 && trade!=null) {
            if (flow == null) {
                flow = new Flow();
            }
            flow.setQuantity(quantity);
            flow.setQuantityType(quantityType);
            flow.setProduct(product);
            if (type!=null){
                flow.setFlowType(type.name());
            } else {
                logger.error("Missing flow type");
                return null;
            }
            flow.setTrade(trade);
            flow.setScheduleLineId(scheduleLineId);
            flow.setTradeDate(tradeDate);
            flow.setValueDate(valueDate);
            flow.setSettlementDate(valueDate);
            flow.setSourceProductId(trade.getProduct().getId());
            flow.setStatus(FlowStatus.CREATED.name());
            if (subType!=null){
                flow.setSubType(subType.name());
            } else {
                logger.error("Missing flow sub type");
                return null;
            }
            flow.setIsCollateral(trade.getIsCollateral());
            flow.setPrice(trade.getPrice());
            flow.setPriceType(trade.getPriceType());
            flow.setPriceCurrency(trade.getPriceCurrency());
            flow.setTrader(trade.getTrader());
            flow.setCounterparty(trade.getCounterparty());
            flow.setInternalCounterparty(trade.getInternalCounterparty());
            flow.setTradeType(trade.getTradeType());
            flow.setAgreement(trade.getAgreement());
            if (trade.getTradeEntityCollection() != null) {
                for (TradeEntity entity : trade.getTradeEntityCollection()) {
                    if (entity.getRole().equalsIgnoreCase(LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name)) {
                        flow.setCcp(entity.getLegalEntity());
                    } else if (entity.getRole().equalsIgnoreCase(LegalEntityRole.LegalEntityRoleEnum.CLEARING_MEMBER_ROLE.name)) {
                        flow.setClearingMember(entity.getLegalEntity());
                    }
                }
            }
        } else {
            logger.error("Missing ");
            return null;
        }
        return flow;
    }
    public static final String REGENERATE_PRINCIPAL_FLOWS = "regeneratePrincipalFlows";

    /**
     * generates and stores the schedule flows on a trade
     *
     * @param trade the trade
     */
    public static void regeneratePrincipalFlows(Trade trade) {
        HibernateUtil.executeQuery("delete Flow f where f.trade.tradeId=" + trade.getId() + " and f.subType='" + Flow.FlowSubType.PRINCIPAL + StringUtils.QUOTE);
        generatePrincipalFlows(trade);
        generateTradeSpecificFlows(trade);
    }
    public static final String GENERATE_PRINCIPAL_FLOWS = "generatePrincipalFlows";

    /**
     * generates the principal flows of a trade ie the start asset and cash flows and maturity asset one
     *
     * @param trade the trade
     */
    public static void generatePrincipalFlows(Trade trade) {
        Flow cashFlow = null;
        Flow assetFlow = null;
        Flow maturityFlow = null;
        // get existing flows
        List<Flow> principalFlows = getFlowsByTradeAndSubType(trade.getId(), Flow.FlowSubType.PRINCIPAL);
        if (principalFlows != null) {
            for (Flow flow : principalFlows) {
                if (flow.getFlowType().equals(Flow.FlowType.CASH.name())) {
                    cashFlow = flow;
                } else if (flow.getFlowType().equals(Flow.FlowType.ASSET.name())) {
                    if (trade.getProduct().getMaturityDate() != null
                            && flow.getTradeDate().equals(trade.getProduct().getMaturityDate())) {
                        maturityFlow = flow;
                    } else {
                        assetFlow = flow;
                    }
                }
            }
        }
        /**
         * cash amount
         */
        double amount = trade.getAmount().doubleValue();

        if (amount != 0 && trade.getSettlementCurrency()!=null) {
            // cash flow
            cashFlow = fillCashFlow(cashFlow, trade.getAmount(), trade.getSettlementCurrency(), trade, trade.getValueDate(), trade.getTradeDate(),
                    null, Flow.FlowSubType.PRINCIPAL);
            FlowAccessor.storeFlow(cashFlow);
        } else if (cashFlow != null) {
            FlowAccessor.deleteFlow(cashFlow);
        }
        /**
         * asset
         */
        amount = trade.getQuantity().doubleValue();
        if (trade.getProduct().getNotionalMultiplier()!=null){
            amount=amount*trade.getProduct().getNotionalMultiplier().doubleValue();
        }

        if (amount != 0) {
            if (trade.getProduct().getProductType().equals(ProductTypeUtil.ProductType.CASH.name)) {
                assetFlow = fillCashFlow(assetFlow, trade.getQuantity(), trade.getProduct().getNotionalCurrency(), trade, trade.getValueDate(), trade.getTradeDate(),
                        null, Flow.FlowSubType.PRINCIPAL);
            } else {
                if (ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType()).getUse().equals(ProductTypeUtil.ProductTypeUse.OTC)){
                    assetFlow = fillFlow(assetFlow, trade.getQuantity(), trade.getQuantityType(), trade.getProduct(), trade, trade.getTradeDate(), trade.getTradeDate(),
                        null, Flow.FlowType.ASSET, Flow.FlowSubType.PRINCIPAL);
                } else {
                    assetFlow = fillFlow(assetFlow, trade.getQuantity(), trade.getQuantityType(), trade.getProduct(), trade, trade.getValueDate(), trade.getTradeDate(),
                        null, Flow.FlowType.ASSET, Flow.FlowSubType.PRINCIPAL);
                }
            }
            FlowAccessor.storeFlow(assetFlow);
            if (!trade.getProduct().getProductType().equals(ProductTypeUtil.ProductType.CASH.name)
                    && trade.getProduct().getMaturityDate() != null) {
                maturityFlow = fillFlow(maturityFlow, trade.getQuantity().negate(), trade.getQuantityType(), trade.getProduct(), trade, trade.getProduct().getMaturityDate(), trade.getProduct().getMaturityDate(),
                        null, Flow.FlowType.ASSET, Flow.FlowSubType.PRINCIPAL);
                FlowAccessor.storeFlow(maturityFlow);
            }

        } else {
            if (assetFlow != null) {
                FlowAccessor.deleteFlow(assetFlow);
            }
            if (maturityFlow != null) {
                FlowAccessor.deleteFlow(maturityFlow);
            }
        }
    }
    public static final String GENERATE_ALL_PRINCIPAL_FLOWS = "generateAllPrincipalFlows";

    /**
     * generates the principal flows on all trades (!) be careful, don't use it
     * ie the start asset and cash flows
     */
    public static void generateAllPrincipalFlows() {
        List<Trade> trades = TradeAccessor.getTradesWithWhereClause(" 1=1");
        for (Trade trade : trades) {
            regeneratePrincipalFlows(trade);
        }
    }

    public static final String DELETE_FLOWS_BY_TRADE_AND_SUBTYPE = "deleteFlowsByTradeAndSubType";

    /**
     *delete the principal flows of a trade
     *
     * @param trade the trade
     * @param subType
     */
    public static void deleteFlowsByTradeAndSubType(Trade trade, String subType) {
        HibernateUtil.executeQuery("delete from Flow where trade_id="+trade.getId()+" and sub_type='"+subType+StringUtils.QUOTE);
    }
    public static final String DELETE_PRINCIPAL_FLOWS = "deletePrincipalFlows";

    /**
     *delete the principal flows of a trade
     *
     * @param trade the trade
     */
    public static void deletePrincipalFlows(Trade trade) {
        deleteFlowsByTradeAndSubType(trade,Flow.FlowSubType.PRINCIPAL.name());
    }
    /**
     * get the flows of a trade between two dates
     *
     * @param tradeId
     * @param startDate the start date of the priod
     * @param endDate the end date of the period
     * @return
     */
    public static List<Flow> getFlowsByTradeAndDates(Integer tradeId, Date startDate, Date endDate) {
        DateFormat df = HibernateUtil.dateFormat;
        return HibernateUtil.getObjectsWithQuery("from Flow where trade_id=" + tradeId + " and trade_date between '" + df.format(startDate) + "' and '" + df.format(endDate) + StringUtils.QUOTE);
    }
    /**
     * gets a flow with its id
     *
     */
    public static final String GET_FLOW_BY_ID = "getFlowById";

    public static Flow getFlowById(Integer flowId) {
        Flow ret;
        ret = (Flow) HibernateUtil.getObject(Flow.class, flowId);
        return ret;
    }

    /**
     * store a flow
     *
     * @param flow the trade
     */
    public static void storeFlow(Flow flow) {
        if (flow != null) {
            if (flow.getFlowId() == null) {
                flow.setFlowVersion(1);
                flow.setCreationDateTime(new Date());
            } else {
                flow.setFlowVersion(flow.getFlowVersion() + 1);
                flow.setUpdateDateTime(new Date());
            }
            HibernateUtil.storeObject(flow);
        }
    }
    public static final String GENERATE_AND_STORE_ALL_SCHEDULE_FLOWS = "generateAndStoreAllScheduleFlows";

    /**
     * generates the schedule flows on all trades (!) be careful!
     */
    public static void generateAndStoreAllScheduleFlows() {
        List<Trade> trades = TradeAccessor.getTradesWithWhereClause("1=1");
        for (Trade trade : trades) {
            generateAndStoreScheduleFlowsIfNeeded(trade);
        }
    }
    public static final String GET_FLOWS_BY_TRADE = "getFlowsByTrade";

    /**
     * gets the flows of a trade
     *
     * @param tradeId the trade id
     * @return
     */
    public static List<Flow> getFlowsByTrade(Integer tradeId) {
        // do not change the order by : same as schedule generation
        return HibernateUtil.getObjectsWithQuery("from Flow where trade_id=" + tradeId + " order by value_date");
    }
    public static final String GET_SCHEDULE_FLOWS_BY_TRADE = "getScheduleFlowsByTrade";

    /**
     * gets the flows of a trade
     *
     * @param tradeId the trade id
     * @return
     */
    public static List<Flow> getScheduleFlowsByTrade(Integer tradeId) {
        // do not change the order by : same as schedule generation
        return HibernateUtil.getObjectsWithQuery("from Flow where trade_id=" + tradeId + " and sub_type='INTEREST' order by schedule_line_id");
    }

    /**
     * gets flow of a trade and schedule line
     *
     * @param tradeId the trade id
     * @param schedulelineId the schedule line id
     * @return
     */
    public static Flow getFlowsByTradeAndScheduleLine(Integer tradeId, Integer schedulelineId) {
        return (Flow) HibernateUtil.getObjectWithQuery("from Flow where trade_id=" + tradeId + " and schedule_line_id=" + schedulelineId);
    }

    /**
     * gets the flows of a trade with a given type
     *
     * @param tradeId the trade id
     * @param subtype the sub type
     * @return
     */
    public static List<Flow> getFlowsByTradeAndSubType(Integer tradeId, Flow.FlowSubType subtype) {
        return HibernateUtil.getObjectsWithQuery("from Flow where trade_id=" + tradeId + " and sub_type='" + subtype + StringUtils.QUOTE);
    }

    public static final String DELETE_FLOW = "deleteFlow";

    /**
     * deletes a flow
     *
     * @param flow the flow
     */
    public static void deleteFlow(Flow flow) {
        HibernateUtil.deleteObject(flow);
    }

    public static void deleteFlowsByProduct(Integer productId) {
        HibernateUtil.executeQuery("delete from Flow f where f.product.productId=" + productId);
    }
}
