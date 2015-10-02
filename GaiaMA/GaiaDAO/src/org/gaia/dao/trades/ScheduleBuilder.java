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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOAgentPool;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.PositionBuilder;
import static org.gaia.dao.trades.FlowAccessor.fillCashFlow;
import static org.gaia.dao.trades.FlowAccessor.storeFlow;
import static org.gaia.dao.trades.ScheduleAccessor.deleteProductScheduleLinesAndFlows;
import org.gaia.dao.trades.events.EventEngine;
import org.gaia.dao.trades.schedulers.EmptyProductScheduler;
import org.gaia.dao.trades.schedulers.FRAScheduler;
import org.gaia.dao.trades.schedulers.FXOptionScheduler;
import org.gaia.dao.trades.schedulers.FXSwapScheduler;
import org.gaia.dao.trades.schedulers.IProductScheduler;
import org.gaia.dao.trades.schedulers.NDForexScheduler;
import org.gaia.dao.trades.schedulers.RateScheduler;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Benjamin Frerejean
 */

public class ScheduleBuilder {

    public static final String ACCRUAL_PAY_ALL = "ACCRUAL_PAY_ALL";
    public static final String ACCRUAL_PAY_NONE = "ACCRUAL_PAY_NONE";
    private static final Logger logger = Logger.getLogger(ScheduleBuilder.class);

    public static final String BUILD_SCHEDULE_FROM_TRADE = "buildScheduleFromTrade";
    public static Schedule buildScheduleFromTrade(Trade trade) {

        /**
         * build product schedule
         */
        Schedule productSchedule;
        Product product = trade.getProduct();
        productSchedule = buildScheduleFromProduct(product);
        /**
         * build trade schedule
         */
        Schedule tradeSchedule = new Schedule();
        List<ScheduleLine> lines = new ArrayList();

        /**
         * start with trade amount add product lines when relevant
         */
        if (productSchedule != null) {
            BigDecimal initialNotional=BigDecimal.ONE;
            if (product.getProductRate() != null && product.getProductRate().getInitialNotional() != null) {
                initialNotional = product.getProductRate().getInitialNotional();
            }
            for (ScheduleLine scheduleLine : productSchedule.getScheduleLines()) {
                if (!scheduleLine.getPaymentDate().before(trade.getValueDate())) {
                    if (!scheduleLine.isCustom()) {
                        scheduleLine.setNotional(scheduleLine.getNotional().multiply(trade.getQuantity(trade.getTradeDate())).divide(initialNotional, 20, RoundingMode.HALF_UP));
                        scheduleLine.setPaymentAmount(scheduleLine.getPaymentAmount().multiply(trade.getQuantity(trade.getTradeDate())).divide(initialNotional, 20, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_DOWN));
                    }
                    lines.add(scheduleLine);
                }
            }
        }

        tradeSchedule.setScheduleLines(lines);
        return tradeSchedule;
    }

    public static Schedule buildScheduleFromProduct(Product product) {
        Schedule productSchedule = null;
        for (Product component : ProductAccessor.getProductAndSubProducts(product)) {
            Schedule subProductSchedule = buildScheduleFromSingleProduct(component);
            if (productSchedule == null) {
                productSchedule = subProductSchedule;
            } else if (subProductSchedule != null) {
                productSchedule.getScheduleLines().addAll(subProductSchedule.getScheduleLines());
            }
        }
        return productSchedule;
    }

    public static IProductScheduler getProductScheduler(Product product) {


        if (product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_NDF.getName())) {
            return new NDForexScheduler();
        } else if (product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_SWAP.getName())) {
            return new FXSwapScheduler();
        } else if (product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_OPTION.getName())
                ||product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_VANILLA_OPTION.getName())
                ||product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_BINARY_OPTION.getName())
                ||product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_BARRIER_OPTION.getName())) {
            return new FXOptionScheduler();
        } else if (product.getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.FRA.getName())) {
            return new FRAScheduler();
        } else if (product.getScheduler() != null && product.getMaturityDate() != null && product.getStartDate() != null) {
            return new RateScheduler();
        }

        return new EmptyProductScheduler();
    }

    /**
     * build Schedule From Single Product
     */
    private static Schedule buildScheduleFromSingleProduct(Product product) {

        try {
            // 1 .build schedule
            IProductScheduler productScheduler = getProductScheduler(product);
            Schedule schedule = productScheduler.buildSchedule(product);
            if (schedule == null) {
                return null;
            }
            // 2. apply events
            schedule.setScheduleLines(EventEngine.applyProductEventsOnScheduleLines(product, schedule.getScheduleLines()));

            // 3. merge with stored data
            // a. load stored lines
            List<ScheduleLine> storedLines = (ArrayList) ScheduleAccessor.getScheduleLinesByProduct(product);
            if (storedLines != null && !storedLines.isEmpty()) {
                // b. merge
                mergeSchedule(schedule.getScheduleLines(), storedLines);
            }
            return schedule;
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
            return new Schedule();
        }

    }
    /*
     * sets the id of already stored lines so that they will be updated and not created
     */

    public static void mergeSchedule(List<ScheduleLine> newScheduleLineList, List<ScheduleLine> storedScheduleLineList) {
        if (storedScheduleLineList == null){
            return ;
        }
        // merge with customized
        List<ScheduleLine> ret = new ArrayList();
        int i;
        for (ScheduleLine stored : storedScheduleLineList) {
            if (stored.isCustom()){
                i = 0;
                for (ScheduleLine newScheduleLine : newScheduleLineList) {
                    if (newScheduleLine.getResetDate().equals(stored.getResetDate())
                     && (   newScheduleLine.getFixingProduct()==null ||
                            newScheduleLine.getFixingProduct().getProductId().intValue()==stored.getFixingProduct().getProductId().intValue())){
                            newScheduleLineList.set(i, stored);
                    }
                    i++;
                }
            }
        }
        if (storedScheduleLineList.size() != newScheduleLineList.size()) {
            return;
        }
        i=0;
        for (ScheduleLine newScheduleLine : newScheduleLineList) {
            ScheduleLine stored = storedScheduleLineList.get(i);
            newScheduleLine.setScheduleLineId(stored.getScheduleLineId());

            /* if the product or date change, the line must be unfixed*/
            if (stored.isFixed()
                    && stored.getFixingProduct() != null
                    && stored.getFixingProduct().getProductId().equals(newScheduleLine.getFixingProduct().getProductId())
                    && stored.getResetDate().equals(newScheduleLine.getResetDate())) {
                newScheduleLine.setFixed(Boolean.TRUE);
                newScheduleLine.setFixing(stored.getFixing());
                newScheduleLine.setPaymentAmount(stored.getPaymentAmount());
            }
            i++;
        }
    }
    public static final String UPDATE_PRODUCT_SCHEDULE = "updateProductSchedule";

    public static void updateProductSchedule(Product product) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        updateProductSchedule(product, session);
        transaction.commit();
        session.close();
    }

    public static void updateProductSchedule(Product product, Session session) {

//        session.getTransaction().commit();
//        session.getTransaction().begin();
        product.getTrades();
        Schedule schedule = ScheduleBuilder.buildScheduleFromProduct(product);

        // get concerned trades by the product
        List<Trade> tradeList = getTradeFromProductOrSubProduct(product);

        // to detect if the table has to be cleant : look for ids
        boolean noIds = true;
        for (ScheduleLine scheduleLine : schedule.getScheduleLines()) {
            if (scheduleLine.getScheduleLineId() != null) {
                noIds = false;
            }
        }
        /*
         * if no id found, that means that all the lines and flows have to be rebuilt
         * so we start by deleteing them
         */
        if (noIds) {
            deleteProductScheduleLinesAndFlows(product.getId());
        }
        // look for stored lines that have to be deleted
        List<ScheduleLine> storedLines = (ArrayList) ScheduleAccessor.getScheduleLinesByProduct(product);
        if (storedLines.size() != schedule.getScheduleLines().size()) {
            for (ScheduleLine scheduleLine : storedLines) {
                HibernateUtil.deleteObject(scheduleLine);
            }
        }
        // store lines
        for (ScheduleLine scheduleLine : schedule.getScheduleLines()) {
            HibernateUtil.storeObject(scheduleLine, session);
        }
        // store trade flows
        for (Trade trade : tradeList) {
            boolean majPosition=false;
            Flow firstFlow = null;
            for (ScheduleLine scheduleLine : schedule.getScheduleLines()) {
                Flow storedFlow = FlowAccessor.getFlowsByTradeAndScheduleLine(trade.getId(), scheduleLine.getScheduleLineId());

                if (trade.getValueDate().before(scheduleLine.getPaymentDate()) && TradeAccessor.isValid(trade)) {
                    if (scheduleLine.isFixed()) {
                        BigDecimal amount=scheduleLine.getPaymentAmount().multiply(trade.getQuantity());
                        if (storedFlow==null
                            || Math.abs(amount.doubleValue()-storedFlow.getQuantity().doubleValue())>0.000001
                            || !scheduleLine.getPaymentDate().equals(storedFlow.getValueDate())
                            || !scheduleLine.getPaymentDate().equals(storedFlow.getTradeDate())){

                            Flow flow = fillCashFlow(storedFlow, amount,
                                    scheduleLine.getCurrency(), trade, scheduleLine.getPaymentDate(), scheduleLine.getPaymentDate(),
                                    scheduleLine.getScheduleLineId(), Flow.FlowSubType.INTEREST);
                            if (firstFlow == null) {
                                firstFlow = flow;
                            }
                            storeFlow(flow);
                            majPosition=true;
                        }
                    }
                }
            }
            if (firstFlow != null && majPosition) {
                // just generate position with first trade flow to avoid updating several times the same position
                PositionBuilder.updatePositionFromFlow(new ArrayList(), firstFlow, trade, Boolean.FALSE);
            }
        }
    }
    public static final String MAKE_SCHEDULE_LINES_FIXINGS = "makeScheduleLinesFixings";

    public static Boolean makeScheduleLinesFixings(List<ScheduleLine> lines, Filter filter) {
        for (ScheduleLine line : lines) {
            if (line.getFixing() != null) {
                IProductScheduler productScheduler = getProductScheduler(line.getProduct());
                if (productScheduler == null) {
                    return false;
                }
                line.setPaymentAmount(productScheduler.calculateAmount(line));
                line.setFixed(Boolean.TRUE);
                // store all lines
                HibernateUtil.storeObject(line);
                List<Trade> tradeList = getTradeFromProductOrSubProduct(line.getProduct());
                for (Trade trade : tradeList) {
                    if (FilterAccessor.isInFilter(trade, filter)) {
                        if (trade.getValueDate().before(line.getPaymentDate()) && TradeAccessor.isValid(trade) && line.getPaymentAmount().doubleValue() != 0.) {
                            BigDecimal amount= line.getPaymentAmount().multiply(trade.getQuantity());
                            Flow flow = fillCashFlow(null, amount,
                                    line.getCurrency(), trade, line.getPaymentDate(), line.getPaymentDate(),
                                    line.getScheduleLineId(), Flow.FlowSubType.INTEREST);
                            storeFlow(flow);
                            DAOAgentPool.callAsynchroneMethod(PositionBuilder.class, PositionBuilder.UPDATE_POSITION_FROM_FLOW, new ArrayList(), flow, trade, Boolean.FALSE);
                            if (line.getProduct().getProductForex()!=null && line.getProduct().getProductForex().getStrike()!=null){
                                trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.EXERCISED.name);
                                TradeAccessor.storeTrade(trade);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static List<Trade> getTradeFromProductOrSubProduct(Product product) {
        List<Trade> tradeList = TradeAccessor.getTradesByProductId(product.getId());
        if (tradeList.isEmpty()) {
            // can be on parent product
            List<Product> subProducts = new ArrayList<>();
            subProducts.add(product);
            Product parentProduct = ProductAccessor.getParentProduct(subProducts);
            if (parentProduct != null) {
                tradeList = TradeAccessor.getTradesByProductId(parentProduct.getId());
            }
        }

        return tradeList;
    }
}
