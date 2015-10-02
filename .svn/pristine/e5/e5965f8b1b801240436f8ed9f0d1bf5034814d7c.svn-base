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

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductEventDetail;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeGroup;
import org.hibernate.Session;

/**
 *
 * @author Benjamin Frerejean
 */
public class EventEngine {

    private static final Logger logger = Logger.getLogger(EventEngine.class);
    public static final String APPLY_EVENT_ON_PRODUCT = "applyEventOnProduct";

    public static String applyEventOnProduct(Class clazz, Integer eventId, Integer productId) {
        String ids = StringUtils.EMPTY_STRING;
        try {

            Product product = ProductAccessor.getProductById(productId);

            // build event applier instance
            IEventApply eventApply = (IEventApply) clazz.getConstructor(Integer.class).newInstance(eventId);

            if (eventApply != null && product != null) {

                // add to product event history
                if (product.getProductEvents() == null) {
                    product.setProductEvents(new HashSet());
                }
                ProductEvent productEvent = new ProductEvent(product, eventApply.getEffectiveDate(), eventApply.getEventType(), clazz.getName(), eventId);

                // apply the event on the product
                List<ProductEventDetail> details = eventApply.applyOnProduct(product,productEvent);

                if (details != null) { // the product really was impacted
                    for(ProductEventDetail detail : details){
                        detail.setProductEvent(productEvent);
                    }
                    productEvent.setProductEventDetails(details);
                    // if no details, no event
                    // for example unwinds on standard cds must not impact the product
                    if (!details.isEmpty()){
                        product.getProductEvents().add(productEvent);
                    }
                    // if not an otc, the apply on trade wont store the product so we have to do it
                    if (!ProductTypeUtil.getProductTypeByName(product.getProductType()).getUse().equals(ProductTypeUtil.ProductTypeUse.OTC) && !details.isEmpty()){
                        ProductAccessor.storeProduct(product);
                    }

                    // load trades
                    List<Trade> trades = TradeAccessor.getTradesByProductId(product.getProductId());
                    List<Integer> impactedTradeIds=new ArrayList();
                    for (Trade trade : trades) {
                        trade.setProduct(product);// useful on non otcs
                        // apply on trades
                        List<Integer> listIds = eventApply.applyOnTrade(trade,productEvent);
                        if (!listIds.isEmpty()) { // if the trade really was impacted
                            impactedTradeIds.addAll(listIds);
                            // if OTC, add trade id on the event
                            if (ProductTypeUtil.getProductTypeByName(product.getProductType()).getUse().equals(ProductTypeUtil.ProductTypeUse.OTC)){
                                productEvent.setTrade(trade);
                                HibernateUtil.storeObject(productEvent);
                            }

                            // apply on flows
                            List<Flow> flows = FlowAccessor.getFlowsByTrade(trade.getTradeId());
                            if (flows != null) {
                                for (Flow flow : flows) {
                                    eventApply.applyOnFlows(flow);
                                }
                            }
                        }
                    }
                    PositionBuilder.updatePositionFromTrades(trades);
                    for (Integer id : impactedTradeIds){
                        ids += id + StringUtils.COMMA;
                    }
                    if (ids.length() > 0) {
                        ids = ids.substring(0, ids.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return ids;
    }

    public static void applyProductEventsOnTradeFlows(Integer tradeId) {
        Trade trade = TradeAccessor.getTradeById(tradeId);
        List<Flow> flows = FlowAccessor.getFlowsByTrade(trade.getTradeId());
        for (ProductEvent event : trade.getProduct().getProductEvents()) {
            try {
                Class clazz = Class.forName(event.getEventClassName());
                IEventApply eventApply = (IEventApply) clazz.getConstructor(Integer.class).newInstance(event.getEventId());
                Session session = HibernateUtil.getSession();
                for (Flow flow : flows) {
                    eventApply.applyOnFlows(flow);
                }
                session.close();

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
    }

    public static List<ScheduleLine> applyProductEventsOnScheduleLines(Product product, List<ScheduleLine> lines) {
        List<ProductEvent> events = getProductEvents(product);
        if (events == null || events.isEmpty()) {
            return lines;
        }
        for (ProductEvent event : events) {
            try {
                Class clazz = Class.forName(event.getEventClassName());
                IEventApply eventApply = (IEventApply) clazz.getConstructor(Integer.class).newInstance(event.getEventId());
                eventApply.applyOnScheduleLines(lines);

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return lines;
    }

    public static List<ProductEvent> getProductEvents(Product product) {
        List<ProductEvent> events = new ArrayList();
        List<Product> products = ProductAccessor.getProductAndParentProducts(product);
        for (Product product_ : products) {
            if (product_.getProductEvents() != null && !product_.getProductEvents().isEmpty()) {
                events.addAll(product_.getProductEvents());
            }
        }
        return events;
    }
    public static final String CANCEL_PRODUCT_EVENT = "cancelProductEvent";

    public static Product cancelProductEvent(ProductEvent productEvent) {
        Product product = productEvent.getProduct();
        product.getProductEvents().remove(productEvent);
        BigDecimal oldNotional=null;
        for (ProductEventDetail detail : productEvent.getProductEventDetails()) {
            String getterName = "set" + detail.getProductColumn();
            for (Method method : Product.class.getMethods()) {
                if (method.getName().equalsIgnoreCase(getterName)) {
                    try {
                        String val = detail.getOldValue();
                        Class clazz = method.getParameterTypes()[0];
                        if (clazz.equals(String.class)) {
                            method.invoke(product, val);
                        } else if (clazz.equals(BigDecimal.class)) {
                            BigDecimal dVal = new BigDecimal(val);
                            method.invoke(product, dVal);
                            if (detail.getProductColumn().equalsIgnoreCase("NotionalMultiplier")){
                                oldNotional=dVal;
                            }
                        } else if (clazz.equals(Date.class)) {
                            Date dVal = HibernateUtil.dateFormat.parse(val);
                            method.invoke(product, dVal);
                        } else if (clazz.equals(Integer.class)) {
                            Integer iVal = new Integer(val);
                            method.invoke(product, iVal);
                        }

                    } catch (Exception e) {
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            }
        }

        // manage trades
        List<Trade> trades = TradeAccessor.getTradesByProductId(product.getProductId());
        for (Trade trade : trades) {
            // generated trade and trade groups
            for (TradeGroup group : trade.getTradeGroups()){
                for (Trade trade_ : group.getTrades()){
                    if (trade_.getTradeId().intValue()!=trade.getTradeId().intValue()){
                        if (trade_.getTradeDate().equals(productEvent.getEffectiveDate())){
                            TradeAccessor.deleteTrade(trade_.getTradeId());
                            TradeAccessor.deleteTradeGroup(group);
                        }
                    }
                }
            }
            // generated flows : assume they are EVENT and PRINCIPAL flows on event date (not always true...)
            List<Flow> flows = FlowAccessor.getFlowsByTradeAndDates(trade.getId(), productEvent.getEffectiveDate(), productEvent.getEffectiveDate());
            for (Flow flow : flows){
                if (flow.getSubType().equalsIgnoreCase(Flow.FlowSubType.EVENT.name())
                  || flow.getSubType().equalsIgnoreCase(Flow.FlowSubType.PRINCIPAL.name())){
                    FlowAccessor.deleteFlow(flow);
                }
            }
            // recalculate maturity flow : assume they are EVENT and PRINCIPAL flows on event date (not always true...)
            if (oldNotional!=null && trade.getProduct().getMaturityDate()!=null){
                flows = FlowAccessor.getFlowsByTradeAndDates(trade.getId(), trade.getProduct().getMaturityDate(), trade.getProduct().getMaturityDate());
                for (Flow flow : flows){
                    if (flow.getSubType().equalsIgnoreCase(Flow.FlowSubType.EVENT.name())
                      || flow.getSubType().equalsIgnoreCase(Flow.FlowSubType.PRINCIPAL.name())){
                        BigDecimal maturityAmount=trade.getQuantity().multiply(oldNotional).negate();
                        flow.setQuantity(maturityAmount);
                        FlowAccessor.storeFlow(flow);
                    }
                }
            }
            // trade status : at least on event that close the trade
            if (trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name)
              ||trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.TERMINATED.name)
              ||trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.EXERCISED.name)
              ||trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.NOVATED.name)
              ||(trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.UNWINDED.name)
                    && trade.getProduct().getNotionalMultiplier().doubleValue()==1)
             ){
                trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.CREATED.name());
            }
            List<ProductEvent> tradeEvents=getEventsFromTrade(trade.getTradeId());
            tradeEvents.remove(productEvent);
            trade.setProductEvents(tradeEvents);
            TradeAccessor.storeTrade(trade);
        }
        HibernateUtil.deleteObject(productEvent);
        return product;
    }
    public static final String GET_EVENT_DESCRIPTION = "getEventDescription";

    public static String getEventDescription(ProductEvent productEvent) {
        try {
            Class clazz = Class.forName(productEvent.getEventClassName());
            IEventApply eventApply = (IEventApply) clazz.getConstructor(Integer.class).newInstance(productEvent.getEventId());
            return eventApply.getEventDescription();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static List<ProductEvent> getEventsFromTrade(Integer tradeId){
        return (List)HibernateUtil.getObjectsWithQuery("from ProductEvent pe where pe.trade.tradeId="+tradeId);
    }

}
