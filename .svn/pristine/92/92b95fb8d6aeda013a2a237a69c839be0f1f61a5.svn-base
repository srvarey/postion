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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.CreditContractType;
import org.gaia.domain.legalEntity.CreditEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductEventDetail;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;

/**
 *
 * @author Benjamin Frerejean
 */
public class CreditEventApply implements IEventApply {

    Integer eventId;
    CreditEvent event;
    CreditContractType creditContractType;
    private static final Logger logger = Logger.getLogger(CreditEventApply.class);

    public CreditEventApply(Integer eventId) {
        this.eventId = eventId;
        event = LegalEntityAccessor.getCreditEventById(eventId);
        if (event == null) {
            logger.error("CreditEventApply ERROR : no Credit Event found for id " + eventId);
        }
    }

    @Override
    public List<ProductEventDetail> applyOnProduct(Product product, ProductEvent productEvent) {
        List<ProductEventDetail> productEventDetailList = new ArrayList();
        if (event != null) {
            try {// add to product event details
                ProductConst.ProductStatus targetStatus = ProductConst.ProductStatus.Defaulted;
                if (ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(product.getProductType())) {
                    for (Product underlying : product.loadUnderlyings()) {
                        LegalEntity issuer = ProductAccessor.getProductIssuer(underlying.getProductId());
                        if (issuer.equals(event.getIssuer())) {
                            ProductEvent subProductEvent = productEvent.clone();
                            subProductEvent.setProduct(underlying);
                            List<ProductEventDetail> details = applyOnProduct(underlying, subProductEvent);
                            for (ProductEventDetail detail : details) {
                                detail.setProductEvent(subProductEvent);
                            }
                            subProductEvent.setProductEventDetails(details);
                            underlying.getProductEvents().add(subProductEvent);
                            // lower index notional
                            ProductUnderlying productUnderlying= getProductUnderlying(product,underlying);
//                            product.setNotionalMultiplier(product.getNotionalMultiplier().subtract(productUnderlying.getWeight()));
                            // set to zero
                            ProductEventDetail productEventDetail = new ProductEventDetail("productUnderlying.weight", BigDecimal.ZERO.toString(),
                            productUnderlying.getWeight().toString(), underlying.getProductId().toString());
                            productEventDetailList.add(productEventDetail);
                            productUnderlying.setWeight(BigDecimal.ZERO);
                            ProductAccessor.storeProduct(underlying);
                            break;
                        }
                    }

                    targetStatus = ProductConst.ProductStatus.PartlyDefaulted;
                }
                ProductEventDetail productEventDetail = new ProductEventDetail("Status", targetStatus.name(),
                        product.getStatus(), null);
                productEventDetailList.add(productEventDetail);
                // updates product
                product.setStatus(targetStatus.name());

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return productEventDetailList;
    }

    @Override
    public List<Integer> applyOnTrade(Trade trade, ProductEvent productEvent) {
        List<Integer> ids = new ArrayList();
        if (event != null) {
            Date startDate = event.getDefaultDate();
            if (trade.getProduct().getProductCredit()!=null && trade.getProduct().getProductCredit().getContractType() != null) {
                CreditContractType contractType = LegalEntityAccessor.getCreditContractTypeByName(trade.getProduct()
                        .getProductCredit().getContractType());
                if (contractType.getCreditBackStopDate() != null) {
                    startDate = DateUtils.addCalendarDay(startDate, contractType.getCreditBackStopDate() * -1);
                }
            }
            if (trade.getTradeDate().before(startDate) && TradeAccessor.isValid(trade)) {

                // updates trade
                if (ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(trade.getProduct().getProductType())) {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.PARTIALLY_DEFAULTED.name);
                } else {
                    trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name);
                }
                TradeAccessor.storeTrade(trade);
                // create and store the cash event flow
                BigDecimal eventAmount = calculateEventAmount(trade);
                Flow flow = FlowAccessor.fillCashFlow(null, eventAmount, trade.getProduct().getNotionalCurrency(), trade,
                        event.getPaymentDate(), event.getDefaultDate(), null,  Flow.FlowSubType.PRINCIPAL);
                FlowAccessor.storeFlow(flow);
                // create and store the asset event flow except on index
                if (!ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(trade.getProduct().getProductType())) {
                    Flow assetflow = FlowAccessor.fillFlow(null, trade.getQuantity().negate(),trade.getQuantityType(), trade.getProduct(), trade,
                            event.getDefaultDate(), event.getDefaultDate(), null, Flow.FlowType.ASSET,  Flow.FlowSubType.PRINCIPAL);
                    assetflow.setTradeDate(event.getDefaultDate());
                    FlowAccessor.storeFlow(assetflow);
                }
                ids.add(trade.getTradeId());
            }
        }
        return ids;
    }

    private BigDecimal calculateEventAmount(Trade trade) {
        BigDecimal eventAmount = null;
        if (ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(trade.getProduct().getProductType())) {
            for (ProductUnderlying productUnderlying : trade.getProduct().getUnderlyingProducts()) {
                LegalEntity issuer = ProductAccessor.getProductIssuer(productUnderlying.getUnderlying().getProductId());
                if (issuer.equals(event.getIssuer())) {
                    eventAmount = trade.getQuantity().multiply(BigDecimal.ONE.subtract(event.getRecoveryRate()))
                            .multiply(productUnderlying.getWeight());
                    break;
                }
            }

        } else {
            eventAmount = trade.getQuantity().multiply(trade.getProduct().getNotionalMultiplier()).multiply(event.getRecoveryRate());
        }
        return eventAmount;
    }

    @Override
    public void applyOnFlows(Flow flow) {
        if (!flow.getSettlementDate().before(event.getDefaultDate())&& !flow.getSubType().equals(Flow.FlowSubType.PRINCIPAL.name()) ) {
            if (ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(flow.getProduct().getProductType())) {
                for (ProductUnderlying productUnderlying : flow.getProduct().getUnderlyingProducts()) {
                    LegalEntity issuer = ProductAccessor.getProductIssuer(productUnderlying.getUnderlying().getProductId());
                    if (issuer.equals(event.getIssuer())) {
                        flow.setQuantity(flow.getQuantity().multiply(BigDecimal.ONE.subtract(productUnderlying.getWeight())));
                        FlowAccessor.storeFlow(flow);
                        break;
                    }
                }
            } else {
                FlowAccessor.deleteFlow(flow);
            }
        }
    }

    @Override
    public void applyOnScheduleLines(List<ScheduleLine> lines) {
        ArrayList<ScheduleLine> toRemove = new ArrayList();

        if (event != null && lines != null) {
            for (ScheduleLine line : lines) {
                if (ProductTypeUtil.ProductType.CDS_INDEX.getName().equalsIgnoreCase(line.getProduct().getProductType())) {
                    BigDecimal weight=BigDecimal.ZERO;
                    for (ProductEvent evt : line.getProduct().getProductEvents()){
                        if (evt.getEventId().intValue()==event.getCreditEventId().intValue()){
                            for (ProductEventDetail detail : evt.getProductEventDetails()){
                                if (detail.getProductColumn().equalsIgnoreCase("productUnderlying.weight")){
                                    weight=new BigDecimal(detail.getOldValue());
                                }
                            }
                        }
                    }
                    if (!line.getPaymentDate().before(event.getDefaultDate())) {
                        line.setPaymentAmount(line.getPaymentAmount().multiply(BigDecimal.ONE.subtract(weight)));
                    }
                } else {
                    if (!line.getPaymentDate().before(event.getDefaultDate())) {
                        toRemove.add(line);
                    }
                }
            }

        } else if (lines != null) {
            lines.removeAll(toRemove);
        }
    }

    @Override
    public String getEventDescription() {
        String ret = StringUtils.EMPTY_STRING;
        if (event != null) {
            return event.toString();
        }
        return ret;
    }

    @Override
    public String getEventType() {
        if (event != null) {
            return event.getCreditEvent();
        }
        return null;
    }

    @Override
    public Date getEffectiveDate() {
        if (event != null) {
            return event.getDefaultDate();
        }
        return null;
    }


    private ProductUnderlying getProductUnderlying(Product product, Product underlying) {
        for (ProductUnderlying productUnderlying : product.getUnderlyingProducts()) {
            if (productUnderlying.getUnderlying().equals(underlying)) {
                return productUnderlying;
            }
        }
        return null;
    }


}
