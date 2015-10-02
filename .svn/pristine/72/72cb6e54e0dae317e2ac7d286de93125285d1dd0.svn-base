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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOAgentPool;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.PositionBuilder;
import static org.gaia.dao.trades.FlowAccessor.getFlowsByTradeAndSubType;
import org.gaia.dao.trades.events.EventEngine;
import org.gaia.dao.trades.schedulers.FXSpotScheduler;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.PricingFilter;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterGroup;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeAttribute;
import org.gaia.domain.trades.TradeGroup;
import org.gaia.domain.utils.HibernateUtil;
import static org.gaia.domain.utils.HibernateUtil.cleanStrings;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.simulationService.ontology.ReportObjectNotification;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Benjamin Frerejean
 */
public class TradeAccessor {

    private static final Logger logger = Logger.getLogger(TradeAccessor.class);

    public enum TradeStatus {

        NEW("New", true),
        VALIDATED("Validated", true),
        PENDING("Pending", true),
        CANCELED("Canceled", false);
        public final String name;
        public final boolean isActive;

        TradeStatus(String name, boolean isActive) {
            this.name = name;
            this.isActive = isActive;
        }
    }

    public enum TradeLifeCycleStatus {

        CREATED("Created"),
        UNWINDED("Unwinded"),
        DEFAULTED("Defaulted"),
        PARTIALLY_DEFAULTED("Partially Defaulted"),
        TERMINATED("Terminated"),
        EXERCISED("Exercised"),
        NOVATED("Novated"),
        ACTIVATED("Activated"),
        DEACTIVATED("Deactivated");
        public final String name;

        TradeLifeCycleStatus(String name) {
            this.name = name;
        }
    }
    public static final String STORE_TRADE = "storeTrade";

    /**
     * Stores a trade
     *
     * @param trade the trade
     * @return
     */
    public static Trade storeTrade(Trade trade) {

        // a few controls
        String errorMessage = StringUtils.EMPTY_STRING;
        if (trade.getProduct() == null) {
            errorMessage = "Missing product";
        } else if (trade.getInternalCounterparty() == null) {
            errorMessage = "Missing internal counterparty";
        } else if (trade.getCounterparty() == null) {
            errorMessage = "Missing counterparty";
        } else if (trade.getQuantity() == null) {
            errorMessage = "Missing quantity";
        } else if (trade.getTradeDate() == null) {
            errorMessage = "Missing trade date";
        } else if (trade.getTradeTime() == null) {
            errorMessage = "Missing trade time";
        } else if (trade.getValueDate() == null) {
            errorMessage = "Missing value date";
        } else if (trade.getQuantityType() == null) {
            errorMessage = "Missing quantity type";
        } else if (trade.getPriceType() == null) {
            errorMessage = "Missing price type";
        } else if (trade.getAmount() == null) {
            errorMessage = "Missing amount";
        } else if (trade.getSettlementCurrency() == null) {
            errorMessage = "Missing settlement currency";
        } else if (trade.getForexRate() == null) {
            errorMessage = "Missing forex rate";
        } else if (trade.getStatus() == null) {
            errorMessage = "Missing status";
        } else if (trade.getTradeType() == null) {
            trade.setTradeType(Trade.TradeType.BUY_SELL.name);
        }
        if (!errorMessage.isEmpty()) {
            logger.error("ERROR STORING TRADE " + errorMessage);
            return null;
        }

        try {
            // now prepare for storing
            if (trade.getTradeVersion() == null) {
                trade.setTradeVersion(1);
            } else {
                trade.setTradeVersion(trade.getTradeVersion() + 1);
            }
            if (trade.getCreationDateTime() == null) {
                trade.setCreationDateTime(new Date());
            } else {
                trade.setUpdateDateTime(new Date());
            }
            Trade tradeBefore = null;
            if (trade.getId() != null) {
                tradeBefore = getTradeById(trade.getId());
            }
            Agreement agreement = LegalEntityAccessor.getAgreementByTrade(trade);
            if (agreement != null) {
                trade.setAgreement(agreement);
            }
            ProductTypeUtil.ProductType productType = ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType());

            if (ProductTypeUtil.ProductTypeUse.OTC.equals(productType.use)) {
                // on OTC trades we store trade and product in the same session so it is synchroneous in the audit
                trade = storeTradeAndProduct(trade);
            } else {
                
                
                generateMirrorTrade(trade);
            }

            /**
             * updates flows and positions asynchroneously.
             */
            if (trade != null) {
                DAOAgentPool.callAsynchroneMethod(TradeAccessor.class, TradeAccessor.GENERATE_IMPACTS, trade, tradeBefore);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return trade;
    }
    public static final String GENERATE_IMPACTS = "generateImpacts";

    public static void generateImpacts(Trade trade, Trade tradeBefore) {

        /**
         * manage flows and positions in case of trade change first regenerate
         * changed flows
         */
        boolean amountChange = false;
        boolean quantityChange = false;
        boolean legalEntityChange = false;
        boolean instrumentChange = false;
        boolean dateChange = false;

        if (tradeBefore == null || trade == null) {
            amountChange = true;
            quantityChange = true;
            legalEntityChange = true;
            instrumentChange = true;
            dateChange = true;
        }

        if (tradeBefore != null && trade != null) {
            /**
             * case of change of a amount
             */
            if (tradeBefore.getAmount().doubleValue() != trade.getAmount().doubleValue()) {
                amountChange = true;
            }
            /**
             * case of change of a quantity
             */
            if (tradeBefore.getQuantity().doubleValue() != trade.getQuantity().doubleValue()) {
                quantityChange = true;
            }
            /**
             * case of change of a trade or value date
             */
            if (!tradeBefore.getValueDate().equals(trade.getValueDate())
                    || !tradeBefore.getTradeDate().equals(trade.getTradeDate())
                    || !tradeBefore.getSettlementCurrency().equals(trade.getSettlementCurrency())
                    || (trade.getProduct().getMaturityDate() != null && !trade.getProduct().getMaturityDate().equals(tradeBefore.getProduct().getMaturityDate()))) {
                dateChange = true;
            }

            if (!tradeBefore.getCounterparty().getLegalEntityId().equals(trade.getCounterparty().getLegalEntityId())
                    || !tradeBefore.getInternalCounterparty().getLegalEntityId().equals(trade.getInternalCounterparty().getLegalEntityId())) {
                legalEntityChange = true;
            }

            if (!tradeBefore.getProduct().getProductId().equals(trade.getProduct().getProductId())) {
                instrumentChange = true;
            }
        }
        /**
         * case of change of quantity and date ,generate and store schedule flows
         */
        if (trade != null) {
            // in any case
            FlowAccessor.generateAndStoreScheduleFlowsIfNeeded(trade);

            /**
             * generate principal flows
             */
            List<Flow> principalFlows = getFlowsByTradeAndSubType(trade.getId(), Flow.FlowSubType.PRINCIPAL);
            if (amountChange || quantityChange || legalEntityChange || instrumentChange || dateChange || principalFlows.isEmpty()) {
                if (dateChange && (tradeBefore != null || !principalFlows.isEmpty())) {
                    // in case of date change we have to regenerate flows
                    FlowAccessor.deletePrincipalFlows(trade);
                } else if ((legalEntityChange|| instrumentChange) && !principalFlows.isEmpty() && tradeBefore!=null){
                    // in case of legal entity or instrument change, the former positions have to be recalculated
                    // and then the flows have to be regenerated
                    List already = new ArrayList();
                    for (Flow flow : principalFlows){
                        flow.setQuantity(BigDecimal.ZERO);
                        PositionBuilder.updatePositionFromFlow(already, flow, tradeBefore, true);
                    }
                    FlowAccessor.deletePrincipalFlows(trade);

                }
                FlowAccessor.generatePrincipalFlows(trade);

                /*
                 ** generate specific flows
                 */
                generateTradeSpecificFlows(trade);
            }

            /**
             * then update positions
             */
            if (amountChange || quantityChange || legalEntityChange || instrumentChange || dateChange) {
                DAOAgentPool.callAsynchroneMethod(PositionBuilder.class, PositionBuilder.UPDATE_POSITION_FROM_TRADE, trade);
            }
            /**
             * notify trade reports
             */
            ReportObjectNotification reportObjectNotification = new ReportObjectNotification(Trade.class, trade.getId(), tradeBefore == null, false, HibernateUtil.dateFormat.format(trade.getTradeDate()), null);
            DAOAgentPool.notifyReports(reportObjectNotification);
        }

        /**
         * case of change of legalEntity or instrument, update position from trade
         */
//        if (tradeBefore != null && (legalEntityChange || instrumentChange)) {
//            DAOAgentPool.callAsynchroneMethod(PositionBuilder.class, PositionBuilder.UPDATE_POSITION_FROM_TRADE, tradeBefore);
//            // notify trade reports
//            ReportObjectNotification reportObjectNotification = new ReportObjectNotification(Trade.class, tradeBefore.getId(), false, true, HibernateUtil.dateFormat.format(tradeBefore.getTradeDate()), null);
//            DAOAgentPool.notifyReports(reportObjectNotification);
//        }
    }

    public static void generateMirrorTrade(Trade trade) {
        Session session = HibernateUtil.getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            generateMirrorTrade(trade, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.fatal(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
    }

    public static void generateMirrorTrade(Trade trade, Session session) {
        // manage internal deals
        if (trade != null && trade.getCounterparty() != null && LegalEntityAccessor.isInternal(trade.getCounterparty())) {

            Integer mirrorId = null;
            // look for mirror
            for (TradeGroup group : trade.getTradeGroups()) {
                if (group.getTradeGroupType().equalsIgnoreCase(TradeGroup.TradeGroupType.Mirrors.name())) {
                    for (Trade trade_ : group.getTrades()) {
                        if (trade_.getTradeId().intValue() != trade.getTradeId().intValue()) {
                            mirrorId = trade_.getTradeId();;
                        }
                    }
                }
            }
            // create mirror trade
            Trade mirror = trade.clone();
            mirror.setCounterparty(trade.getInternalCounterparty());
            mirror.setInternalCounterparty(trade.getCounterparty());
            mirror.setQuantity(trade.getQuantity().negate());
            mirror.setAmount(trade.getAmount().negate());
            if (trade.getConvertedAmount() != null) {
                mirror.setConvertedAmount(trade.getConvertedAmount().negate());
            }
            mirror.setTradeMirrorId(trade.getTradeId());
            mirror.setTradeId(mirrorId);
            session.saveOrUpdate(mirror);
            // if did not exist before, create the trade group
            if (mirrorId == null) {
                TradeGroup group = new TradeGroup(TradeGroup.TradeGroupType.Mirrors.name());
                group.addTrade(trade);
                group.addTrade(mirror);
                session.saveOrUpdate(group);
                mirror.getTradeGroups().add(group);
                trade.getTradeGroups().add(group);
            }
        }
    }
    public static final String GENERATE_TRADE_SPECIFIC_FLOWS = "generateTradeSpecificFlows";

    public static void generateTradeSpecificFlows(Trade trade) {
        if (trade.getProduct().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.CURRENCY_PAIR.name)) {
            FXSpotScheduler.createFlow(trade);
        }
    }

    public static final String GET_TRADE_BY_ID = "getTradeById";

    /**
     * Gets the position group = clearing member for a trade
     *
     * @param tradeId the trade id
     * @return
     */
    public static Trade getTradeById(Integer tradeId) {
        List<Trade> trades = getTradesWithWhereClause("t.tradeId =" + tradeId);
        if (trades != null && trades.size() > 0) {
            return trades.get(0);
        } else {
            return null;
        }
    }
    public static final String GET_TRADES_WITH_WHERE_CLAUSE = "getTradesWithWhereClause";

    /**
     * Gets a trade list given an HQL where clause
     *
     * @param whereClause the trade
     * @return
     */
    public static List<Trade> getTradesWithWhereClause(String whereClause) {
        List<Trade> trades = new ArrayList();
        try {
            String query = "from Trade t join fetch t.product p where " + whereClause;
            trades = HibernateUtil.getObjectsWithQuery(query);
        } catch (HibernateException he) {
            logger.error("error HibernateException in a getTradesWithWhereClause" + StringUtils.formatErrorMessage(he));
        }
        return trades;
    }
    public static final String GET_TRADES_BY_PRODUCT_ID = "getTradesByProductId";

    /**
     * Gets a trade list given the product id
     *
     * @param productId the id of the product
     * @return
     */
    public static List<Trade> getTradesByProductId(Integer productId) {
        List<Trade> trades = new ArrayList();
        try {
            String query = "from Trade t join fetch t.product p where p.productId=" + productId;
            trades = HibernateUtil.getObjectsWithQuery(query);
        } catch (HibernateException he) {
            logger.error(StringUtils.formatErrorMessage(he));
        }
        return trades;
    }
    public static final String GET_DEFAULT_CCP = "getDefaultCCP";

    /**
     * Gets the default CCP from the trade
     *
     * @param trade
     * @return
     */
    public static LegalEntity getDefaultCCP(Trade trade) {

        try {
            if (trade.getInternalCounterparty() != null) {
                List<BoAccount> accountList = LegalEntityAccessor.getBoAccounts(trade.getInternalCounterparty().getLegalEntityId(),
                        null, LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
                if (accountList != null && !accountList.isEmpty()) {
                    LegalEntity entity;
                    for (BoAccount boAccount : accountList) {
                        entity = boAccount.getAccountManager();
                        FilterGroup filterGroup = FilterAccessor.getFilterGroup(LegalEntity.class.getName(), entity.getLegalEntityId());
                        if (filterGroup != null) {
                            for (Filter filter : filterGroup.getFilterCollection()) {
                                if (FilterAccessor.isInFilter(trade, filter)) {
                                    return entity;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }
    public static final String GET_TRADES_WITH_FILTER = "getTradesWithFilter";
    /*
     * Gets the list of trades given a filter
     *
     * @param filter
     * @param sortingOrder
     */

    public static List<Serializable> getTradesWithFilter(Filter filter, Collection<TemplateColumnItem> sortingOrder) {
        List<Serializable> returnList = new ArrayList();

        String table;
        String dbField;
        String queryConditions = FilterAccessor.getQueryFromFilter(filter, Trade.class);

        StringBuilder query = new StringBuilder("select * ");
        query.append(queryConditions);

        /**
         * then build the order by
         */
        String orderBy = " order by ";
        if (sortingOrder != null) {
            for (TemplateColumnItem tci : sortingOrder) {
                /**
                 * normal database column only
                 */
                if (tci.getColumnType().equals(TemplateColumnItem.COLUMN_STANDARD) && tci.getParam().isEmpty()) {
                    /**
                     * trade or second level only
                     */
                    if (tci.getName().indexOf(StringUtils.DOT) == tci.getName().lastIndexOf(StringUtils.DOT)) {
                        if (tci.getName().indexOf(StringUtils.DOT) > 0) {
                            table = tci.getName().substring(0, tci.getName().indexOf(StringUtils.DOT));
                        } else {
                            table = "Trade";
                        }
                        dbField = IntrospectUtil.getFieldName(tci.getGetter());
                        orderBy = orderBy + table + StringUtils.DOT + dbField + StringUtils.COMMA;
                    }
                }
            }
            if (sortingOrder.size() > 0) {
                query.append(orderBy.substring(0, orderBy.length() - 1));
            }
        }
        /**
         * finally launch the query
         */
        try {

            logger.info("TRADE_FILTER: " + query);
            List<Serializable> tmp = (List<Serializable>) HibernateUtil.getEntitiesWithSQLQuery(query.toString(), Trade.class);

            /**
             * avoid duplicates
             */
            if (tmp!=null){
                for (Serializable ser : tmp) {
                    if (!returnList.contains(ser)) {
                        returnList.add(ser);
                    }
                }
            }

        } catch (HibernateException he) {
            logger.error("error  " + StringUtils.formatErrorMessage(he));
        }
        return returnList;
    }

    public static final String GET_TRADES_WITH_FINDER = "getTradeWithFinder";
    /*
     * Gets the list of trades given trade finder inputs
     * @param stypeList
     * @param nameLike
     */

    public static List getTradeWithFinder(String stypeList, String nameLike) {
        final int MAXROWS = 500;
        String query = "select t2.trade_id, p2.short_name from trade t1, trade t2, product p1,product p2"
                + " where t1.trade_id>t2.trade_id and t1.product_id=p1.product_id and t2.product_id=p2.product_id"
                + " and p1.product_type in ('" + stypeList + "')"
                + " and p2.product_type in ('" + stypeList + "')";;
        if (!nameLike.isEmpty()) {
            query += " and upper(p1.shortName) like '%" + nameLike.toUpperCase() + "%'"
                    + " and upper(p2.shortName) like '%" + nameLike.toUpperCase() + "%'";
        }
        query += " group by t2.trade_id, p2.short_name having count(t1.trade_id)<" + MAXROWS + " order by t2.trade_id desc";
        return (List<Serializable>) HibernateUtil.getListWithSQLQuery(query);
    }

    public static final String GET_STATUS_LIST = "getStatusList";

    public static List<String> getStatusList() {
        List<String> status = DomainValuesAccessor.getDomainValuesByName("tradeStatus");
        List<TradeStatus> arr = Arrays.asList(TradeStatus.values());
        for (TradeStatus stat : arr) {
            status.add(stat.name);
        }
        return status;
    }

    public static final String GET_LIFECYCLE_STATUS_LIST = "getLifeCycleStatusList";
    public static List<String> getLifeCycleStatusList() {
        List<String> status = new ArrayList<>();
        List<TradeLifeCycleStatus> arr = Arrays.asList(TradeLifeCycleStatus.values());
        for (TradeLifeCycleStatus stat : arr) {
            status.add(stat.name);
        }
        return status;
    }

    /**
     *
     * Gets the id of a priceable given a list of PricingFilter objects
     * PricingFilter contains a currency , a product type and a filter Used by
     * pricing builder to find the pricer of the priceble, based on pricing
     * settings
     *
     * @param priceable
     * @param currencyTypeFilterList
     * @return
     */
    public static PricingFilter getValueByCurTypeFilter(IPriceable priceable, ArrayList<PricingFilter> currencyTypeFilterList) {

        PricingFilter filterResult = null;
        if (priceable != null) {
            try {
                // control criterias
                ArrayList<PricingFilter> listToRemove = new ArrayList();
                /**
                 * currency
                 */
                for (PricingFilter curTypeFilter : currencyTypeFilterList) {
                    if (!StringUtils.isEmptyString(curTypeFilter.currency)) {
                        if (!curTypeFilter.currency.equals(priceable.getProduct().getNotionalCurrency())) {
                            listToRemove.add(curTypeFilter);
                        } else if (!StringUtils.isEmptyString(curTypeFilter.priceableFilter) && filterResult == null) {
                            //default one
                            filterResult = curTypeFilter;
                        }
                    }
                }
                /**
                 * product type
                 */
                currencyTypeFilterList.removeAll(listToRemove);
                listToRemove.clear();
                for (PricingFilter curTypeFilter : currencyTypeFilterList) {
                    if (!StringUtils.isEmptyString(curTypeFilter.productType)) {
                        if (!curTypeFilter.productType.equals(priceable.getProduct().getProductType())) {
                            listToRemove.add(curTypeFilter);
                        } else if (!StringUtils.isEmptyString(curTypeFilter.currency) && !StringUtils.isEmptyString(curTypeFilter.priceableFilter) && filterResult == null) {
                            //default one
                            filterResult = curTypeFilter;
                        }
                    }
                }
                /**
                 * trade filter
                 */
                currencyTypeFilterList.removeAll(listToRemove);
                listToRemove.clear();
                for (PricingFilter curTypeFilter : currencyTypeFilterList) {
                    if (curTypeFilter.priceableFilter != null && !curTypeFilter.priceableFilter.isEmpty()) {
                        Filter filter = FilterAccessor.getFilterByName(curTypeFilter.priceableFilter, Position.class);
                        if (filter != null && FilterAccessor.isInFilter(priceable, filter)) {
                            filterResult = curTypeFilter;
                            break;
                        } else {
                            listToRemove.add(curTypeFilter);
                        }
                    }
                }
                currencyTypeFilterList.removeAll(listToRemove);

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        if (filterResult == null && !currencyTypeFilterList.isEmpty()) {
            filterResult = currencyTypeFilterList.get(0);
        }

        return filterResult;
    }
    public static final String DELETE_TRADE = "deleteTrade";

    /**
     *
     * Deletes a trade
     *
     * @param tradeId
     */
    public static void deleteTrade(Integer tradeId) {

        Trade trade = TradeAccessor.getTradeById(tradeId);

        if (trade != null) {

            List<Flow> flows = FlowAccessor.getFlowsByTrade(trade.getId());
            if (flows != null) {
                for (Flow flow : flows) {
                    flow.setQuantity(BigDecimal.ZERO);
                    HibernateUtil.updateObject(flow);
                }
                PositionBuilder.updatePositionFromTrade(trade);
                HibernateUtil.executeQuery("delete from Flow f where f.trade.tradeId=" + trade.getTradeId());
            }

            if (trade.getTradeAttributes() != null) {
                HibernateUtil.executeQuery("delete from TradeAttribute ta where ta.trade.tradeId=" + trade.getTradeId());
                trade.setTradeAttributes(null);
            }
            if (trade.getTradeGroups() != null) {
                for (TradeGroup group : trade.getTradeGroups()) {
                    if (group.getTrades().size() <= 2) {
                        for (Trade t : group.getTrades()) {
                            t.getTradeGroups().remove(group);
                            storeTrade(t);
                        }
                        HibernateUtil.deleteObject(group);
                    } else {
                        group.getTrades().remove(trade);
                        HibernateUtil.saveObject(group);
                    }
                }
            }

            List<ProductEvent> events = EventEngine.getEventsFromTrade(trade.getTradeId());
            if (events != null) {
                for (ProductEvent event : events) {
                    event.setTrade(null);
                    HibernateUtil.storeObject(event);
                }
            }

            HibernateUtil.deleteObject(trade);

            generateImpacts(null, trade);

            if (trade.getProduct() != null) {
                if (trade.getProduct().getProductType() != null) {
                    if (ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType()).use.equals(ProductTypeUtil.ProductTypeUse.OTC)
                            || ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType()).use.equals(ProductTypeUtil.ProductTypeUse.LEG)) {
                        ProductAccessor.deleteProduct(trade.getProduct());

                    }
                } else {
                    ProductAccessor.deleteProduct(trade.getProduct());
                }
            }
        }
    }
    public static final String LOAD_TRADE_BY_ATTRIBUTE = "LoadTradeByAttribute";

    public static Trade loadTradeByAttribute(String attributName, String attributeValue) {
        Integer tradeId = (Integer) HibernateUtil.getObjectWithQuery("select ta.trade.tradeId from TradeAttribute ta where ta.attributeName='" + attributName + "' and ta.attributeValue='" + attributeValue + StringUtils.QUOTE);
        if (tradeId != null) {
            return getTradeById(tradeId);
        } else {
            return null;
        }

    }
    public static final String DELETE_TRADE_ATTRIBUTE = "deleteTradeAttribute";

    public static void deleteTradeAttribute(TradeAttribute tradeAttribute) {
        HibernateUtil.deleteObject(tradeAttribute);
    }
    public static final String STORE_TRADE_GROUP = "storeTradeGroup";

    public static TradeGroup storeTradeGroup(TradeGroup tradeGroup) {
        if (tradeGroup.getTradeGroupId() == null) {
            Serializable sId = HibernateUtil.saveObject(tradeGroup);
            tradeGroup.setTradeGroupId((Integer) sId);
        } else {
            HibernateUtil.updateObject(tradeGroup);
        }
        return tradeGroup;
    }


    public static final String GET_TRADE_GROUP = "getTradeGroup";

    public static TradeGroup getTradeGroup(Integer tradeGroupId) {
        return (TradeGroup) HibernateUtil.getObject(TradeGroup.class, tradeGroupId);
    }
    public static final String DELETE_TRADE_GROUP = "deleteTradeGroup";

    public static void deleteTradeGroup(TradeGroup tradeGroup) {
        HibernateUtil.deleteObject(tradeGroup);
    }

    public static boolean isValid(Trade trade) {
        if (trade.getStatus() != null) {
            List<TradeStatus> statuss = getActiveTradeStatus();
            for (TradeStatus status : statuss) {
                if (status.name.equalsIgnoreCase(trade.getStatus())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<TradeStatus> getActiveTradeStatus() {
        List<TradeStatus> statuses = new ArrayList();
        TradeStatus[] array = TradeStatus.values();
        for (TradeStatus status : array) {
            if (status.isActive) {
                statuses.add(status);
            }
        }
        return statuses;
    }

    public static String getActiveTradeStatusSQLClause() {
        String ret = StringUtils.EMPTY_STRING;
        List<TradeStatus> statuses = getActiveTradeStatus();
        for (TradeStatus status : statuses) {
            ret += StringUtils.QUOTE + status.name + "',";
        }
        if (ret.length() > 0) {
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }
    public static final String GET_TRADES_BY_PRODUCT = "getTradesByProduct";

    public static List<Trade> getTradesByProduct(Product product) {
        Product tradeProduct = ProductAccessor.getParentProduct(product);
        if (tradeProduct == null) {
            tradeProduct = product;
        }
        return TradeAccessor.getTradesByProductId(tradeProduct.getProductId());
    }
    public static final String CREATE_FXOPTIONS_SPOT_EXPIRY = "createFXOptionsSpotExpiry";

    public static Boolean createFXOptionsSpotExpiry(List<ScheduleLine> lines) {
        Boolean ret = true;
        for (ScheduleLine line : lines) {
            try {
                if (line.getFixing() != null) {
                    List<Trade> trades = getTradesByProduct(line.getProduct());
                    if (trades != null) {

                        for (Trade trade : trades) {
                            BigDecimal quantity = trade.getQuantity().multiply(trade.getProduct().getNotionalMultiplier());
                            BigDecimal amount = line.getFixing().multiply(quantity).negate();
                            Trade spot = new Trade();
                            spot.setProduct(line.getFixingProduct());
                            spot.setTradeDate(line.getResetDate());
                            spot.setTradeTime(DateUtils.getDate());
                            spot.setQuantity(quantity);
                            spot.setQuantityType(Trade.QuantityType.NOTIONAL.name);
                            spot.setAmount(amount);
                            spot.setTradeType(Trade.TradeType.BUY_SELL.name);
                            spot.setSettlementCurrency(line.getCurrency());
                            spot.setPrice(line.getFixing());
                            spot.setPriceType(MarketQuote.QuotationType.PRICE.getName());
                            spot.setComment("Generated by exercise of FX Option product id " + line.getProduct().getProductId());
                            spot.setIsCollateral(false);
                            spot.setCounterparty(trade.getCounterparty());
                            spot.setInternalCounterparty(trade.getInternalCounterparty());
                            spot.setValueDate(line.getPaymentDate());
                            spot.setTrader(trade.getTrader());
                            spot = storeTrade(spot);
                            // create the trade group
                            TradeGroup group = new TradeGroup();
                            group.addTrade(spot);
                            group.addTrade(trade);
                            group.setTradeGroupType(TradeGroup.TradeGroupType.Exercise.name());
                            storeTradeGroup(group);
                            trade.getTradeGroups().add(group);
                            // update trade lifecycle status
                            trade.setLifeCycleStatus(TradeLifeCycleStatus.EXERCISED.name);
                            storeTrade(trade);
                        }
                        line.setFixed(true);
                        HibernateUtil.storeObject(line);
                    }
                    // update product status
                    line.getProduct().setStatus(ProductConst.ProductStatus.Exercised.name());
                    ProductAccessor.storeProduct(line.getProduct());
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
                ret = false;
            }
        }
        return ret;
    }
    public static final String GET_SETTLEMENT_DATE = "getSettlementDate";

    public static Date getSettlementDate(Trade trade) {
        Date settleDate = null;
        if (trade != null) {
            settleDate = trade.getTradeDate();

            if (settleDate != null && trade.getProduct() != null && trade.getProduct().getSettlementDelay() != null) {
                settleDate = ProductAccessor.getSettlementDate(trade.getProduct(), settleDate);

                if (trade.getCounterparty() != null && trade.getCounterparty().getHolidays() != null) {
                    HolidayCalendar ctpCal = CalendarAccessor.getCalendarByCode(trade.getCounterparty().getHolidays());
                    DateUtils.adjustDate(settleDate, DateUtils.ADJUSTMENT_FOLLOW, ctpCal);
                }
                if (trade.getInternalCounterparty() != null && trade.getInternalCounterparty().getHolidays() != null) {
                    HolidayCalendar intctpCal = CalendarAccessor.getCalendarByCode(trade.getInternalCounterparty().getHolidays());
                    DateUtils.adjustDate(settleDate, DateUtils.ADJUSTMENT_FOLLOW, intctpCal);

                }
            }
        }
        return settleDate;
    }
    public static String GET_FXBARRIER_OPTIONS = "getFxBarrierOptions";

    public static List<Trade> getFxBarrierOptions(Date myDate, Filter filter) {
        List<Trade> resultList = new ArrayList();
        String quoteset = MarketQuoteAccessor.getDefaultQuoteSet();
        Map<Integer, BigDecimal> quotes = new HashMap();
        try {
            String query = "from Trade t inner join t.product p inner join p.productForexs pf inner join p.underlyingProducts pus inner join pus.underlying u "
                    + " where pf.barrierType is not null and t.lifeCycleStatus='Created'"
                    + " and t.tradeDate<='" + HibernateUtil.dateFormat.format(myDate) + StringUtils.QUOTE
                    + " and p.maturityDate>='" + HibernateUtil.dateFormat.format(myDate) + StringUtils.QUOTE
                    + " order by u.shortName";

            List<Object[]> objs = (List<Object[]>) HibernateUtil.getObjectsWithQuery(query);
            for (Object[] obj : objs) {
                Trade trade = (Trade) obj[0];
                if (trade.getProduct().getUnderlying() != null
                        && (filter == null || FilterAccessor.isInFilter(trade, filter))) {
                    BigDecimal quote = quotes.get(trade.getProduct().getUnderlying().getProductId());
                    if (quote == null) {
                        MarketQuote mqQuote = MarketQuoteAccessor.getLastQuote(trade.getProduct().getUnderlying().getProductId(), myDate, quoteset);
                        if (mqQuote != null) {
                            quote = mqQuote.getClose();
                        }
                    }
                    if (quote != null) {
                        quotes.put(trade.getProduct().getUnderlying().getProductId(), quote);
                        if (trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)
                                || trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.display)) {
                            if (trade.getProduct().getProductForex().getBarrier() != null
                                    && quote.doubleValue() >= trade.getProduct().getProductForex().getBarrier().doubleValue()) {
                                resultList.add(trade);
                            }
                        } else if (trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)
                                || trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.display)) {
                            if (trade.getProduct().getProductForex().getBarrier() != null
                                    && quote.doubleValue() <= trade.getProduct().getProductForex().getBarrier().doubleValue()) {
                                resultList.add(trade);
                            }
                        }
                    }
                }
            }
        } catch (HibernateException he) {
            logger.error("error HibernateException in a getProductsWithWhereClause  " + StringUtils.formatErrorMessage(he));
        }
        return resultList;
    }
    public static String APPLY_BARRIER_CROSSING = "applyBarrierCrossing";

    public static Boolean applyBarrierCrossing(ArrayList<ScheduleLine> selectedRows) {
        Boolean ret = Boolean.TRUE;
        for (ScheduleLine line : selectedRows) {
            // it is not a mistake : schedule line are fake lines : here they represent trades
            // see FXOptionExercise TC
            Trade trade = getTradeById(line.getScheduleLineId());
            if (trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)
                    || trade.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)) {
                trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.ACTIVATED.name);
            } else {
                trade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.DEACTIVATED.name);
            }
            storeTrade(trade);
        }
        return ret;
    }


    /**
     * Store a Trade
     * @param trade
     * @return
     */
    public static Trade storeTradeOnly(Trade trade) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            if (!StringUtils.isEmptyString(trade.getComment()) && trade.getComment().length() > 512) {
                trade.setComment(trade.getComment().substring(0, 512));
            }
            cleanStrings(trade);
            if (trade.getTradeId() == null) {
                Integer id = (Integer) session.save(trade);
                trade.setTradeId(id);
            } else {
                session.update(trade);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (IntrospectUtil.getIdFieldValue(trade) != null) {
                String id = IntrospectUtil.getIdFieldValue(trade).toString();
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id));
            } else {
                logger.fatal("Error storing " + trade.toString());
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
            trade = null;
        } finally {
            session.close();
        }
        return trade;
    }

    /**
     * Store a Trade
     * @param trade
     * @return
     */
    public static Trade storeTradeAndProduct(Trade trade) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            cleanStrings(trade.getProduct());
            cleanStrings(trade);
            ProductAccessor.storeProduct(trade.getProduct(), session);
            if (trade.getProduct().getId() != null) {
                if (trade.getComment() != null && trade.getComment().length() > 512) {
                    trade.setComment(trade.getComment().substring(0, 512));
                }
                if (trade.getTradeId() == null) {
                    Serializable ser = session.save(trade);
                    Integer id = (Integer) ser;
                    trade.setTradeId(id);
                } else {
                    session.update(trade);
                }

                // manage internal deals mirrors
                generateMirrorTrade(trade, session);
                // update schedule
                ScheduleBuilder.updateProductSchedule(trade.getProduct(), session);
            } else {
                logger.error("Trade not saved (missing product)");
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Object oId = IntrospectUtil.getIdFieldValue(trade);
            if (oId != null) {
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", oId.toString()));
            } else {
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
            trade = null;
        } finally {
            session.close();
        }
        return trade;
    }


    public static Trade getAnyTradeByType(String productType) {
        // used by unit tests
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(t.tradeId) from Trade t inner join t.product p where p.productType='" + productType + StringUtils.QUOTE);
        return TradeAccessor.getTradeById(id);
    }

    public static Trade getFXOptionTradeForUnitTests() {
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(t.tradeId) from Trade t inner join t.product p inner join p.productForexs pf where p.productType='" + ProductTypeUtil.ProductType.FX_VANILLA_OPTION + "' and t.lifeCycleStatus!='" + TradeAccessor.TradeLifeCycleStatus.EXERCISED.name + "' and pf.barrierType is null");
        return TradeAccessor.getTradeById(id);
    }
}
