/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.reports;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOAgentPool;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionConfiguration;
import org.gaia.domain.reports.PositionConfigurationItem;
import org.gaia.domain.reports.PositionHistoricalMeasure;
import org.gaia.domain.reports.PositionHistory;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.simulationService.ontology.ReportObjectNotification;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Benjamin Frerejean
 *
 * The position builder handles all the operations for the position calculation, storage and access
 */
public abstract class PositionBuilder {

    private static final Logger logger = Logger.getLogger(PositionBuilder.class);
    public static final String ctps = "'Counterparty','Market','CCP'";
    public static final String GET_POSITION_CONFIGURATION_NAME_LIST = "getPositionConfigNameList";

    /**
     * Gets all position configurations names
     *
     * @return
     */
    public static List<String> getPositionConfigNameList() {
        return HibernateUtil.getStringListWithQuery("select name from PositionConfiguration");
    }
    public static final String GET_POSITION_CONFIGURATION = "getPositionConfiguration";

    /**
     * Gets a position configuration
     *
     * @param name
     * @return
     */
    public static PositionConfiguration getPositionConfiguration(String name) {
        return (PositionConfiguration) HibernateUtil.getObjectWithQuery("from PositionConfiguration where name='" + name + StringUtils.QUOTE);
    }

    /* Gets a position configuration
     *
     * @param name
     */
    public static PositionConfiguration getPositionConfiguration(Integer configurationId) {
        return (PositionConfiguration) HibernateUtil.getObjectWithQuery("from PositionConfiguration pc where pc.positionConfigurationId=" + configurationId);
    }
    public static final String GET_POSITION_CONFIGURATIONS = "getPositionConfigurations";

    /**
     * Gets all position configurations
     *
     * @return
     */
    public static List<PositionConfiguration> getPositionConfigurations() {
        return (List<PositionConfiguration>) HibernateUtil.getObjectsWithQuery("from PositionConfiguration");
    }
    public static final String GET_COLLATERAL_POSITION_CONFIGURATIONS = "getCollateralPositionConfigurations";

    /**
     * Gets internal collateral compatible position configurations
     *
     * @param counterpartyRole role of the counterparty
     * @return
     */
    public static List<PositionConfiguration> getCollateralPositionConfigurations(String counterpartyRole) {
        List<PositionConfiguration> positionConfigurations = getPositionConfigurations();
        List<PositionConfiguration> output = new ArrayList();
        String agreagationRole = PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.dbField;
        if (counterpartyRole.equalsIgnoreCase(LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name)) {
            agreagationRole = PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.dbField;
        }
        for (PositionConfiguration positionConfiguration : positionConfigurations) {
            boolean hasCCP = false;
            boolean hasSelectedCptyRole = false;
            for (PositionConfigurationItem positionConfigurationItem : positionConfiguration.getPositionConfigurationItems()) {
                if (PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.dbField.equalsIgnoreCase(positionConfigurationItem.getDbField())) {
                    hasCCP = true;
                }
                if (agreagationRole.equalsIgnoreCase(positionConfigurationItem.getDbField())) {
                    hasSelectedCptyRole = true;
                }
            }
            if (hasCCP && hasSelectedCptyRole) {
                output.add(positionConfiguration);
            }
        }
        return output;
    }
    public static final String STORE_CONGURATION = "storeConfiguration";

    /**
     * Stores a position configuration
     *
     * @param configuration
     */
    public static void storeConfiguration(PositionConfiguration configuration) {
        HibernateUtil.storeObject(configuration);
    }
    public static final String DELETE_CONFIGURATION = "deleteConfiguration";

    /**
     * Deletes a position configuration
     *
     * @param configuration
     */
    public static void deleteConfiguration(PositionConfiguration configuration) {
        for (PositionConfigurationItem positionConfigurationItem : configuration.getPositionConfigurationItems()) {
            HibernateUtil.deleteObject(positionConfigurationItem);
        }
        List<ReportTemplate> templates = HibernateUtil.getObjectsWithQuery("from ReportTemplate t where t.positionConfiguration.positionConfigurationId=" + configuration.getPositionConfigurationId());
        for (ReportTemplate template : templates) {
            HibernateUtil.deleteObject(template);
        }
        HibernateUtil.deleteObject(configuration);
    }

    /**
     * Gets position configuration criteria
     *
     * @return
     */
    public static List<PositionConfiguration.PositionAggregationCriteria> getPositionConfigurationCriteria() {
        PositionConfiguration.PositionAggregationCriteria[] array = PositionConfiguration.PositionAggregationCriteria.values();
        return Arrays.asList(array);
    }

    /**
     * Test if position history exists at this date and launch global position calculation if needed
     *
     * @param valuationDate the date of position history
     * @param configurationId the position configuration
     */
    public static void generatePositionIfNeeded(Date valuationDate, Integer configurationId) {

        String query = "select count(*) from PositionHistory ph,Position p where p.positionId=ph.position.positionId and p.positionConfigurationId=" + configurationId + " and ph.positionDate='" + HibernateUtil.dateFormat.format(valuationDate) + StringUtils.QUOTE;
        Long lineNumber = (Long) HibernateUtil.getObjectWithQuery(query);
        if (lineNumber != null && lineNumber == 0) {
            query = "select max(ph.positionDate) from PositionHistory ph,Position p where p.positionId=ph.position.positionId and p.positionConfigurationId=" + configurationId + " and ph.positionDate<='" + HibernateUtil.dateFormat.format(valuationDate) + StringUtils.QUOTE;
            Date lastPositionDate = (Date) HibernateUtil.getObjectWithQuery(query);
            if (lastPositionDate != null) {
                generateAllPositionsBetweenTwoDates(configurationId, lastPositionDate, valuationDate);
            } else {
                generatePositionFromInception(valuationDate, configurationId);
            }
        }
    }

    public static void generatePositionsIfNeeded() {
        Date date = DateUtils.getDate();
        for (PositionConfiguration config : getPositionConfigurations()) {
            generatePositionIfNeeded(date, config.getPositionConfigurationId());
        }
    }

    /**
     * Genereates the global position history at given date
     *
     * @param valuationDate the date of position history
     * @param configurationId position configuration Id
     */
    public static void generatePositionFromInception(Date valuationDate, Integer configurationId) {
        Date firstDate = getFirstFlowDate();
        firstDate = DateUtils.addCalendarDay(firstDate, -1);
        generatePositionBetweenTwoDates(configurationId, valuationDate, firstDate);
    }

    /**
     * Genereates the position history at given date from another one
     *
     * @param configurationId the startDate
     * @param valoDate
     * @param startDate the date of position history
     */
    public static void generatePositionBetweenTwoDates(Integer configurationId, Date valoDate, Date startDate) {

        try {
            long timerStart = System.currentTimeMillis();
            PositionConfiguration configuration = (PositionConfiguration) HibernateUtil.getObject(PositionConfiguration.class, configurationId);

            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            /**
             * gets quantity to add
             */
            String positionConfigurationDBAggregationFields = getPositionConfigurationDBAggregationFields(configuration);
            String query = " select max(f.flowId) as maxFlowId," + positionConfigurationDBAggregationFields + ",sum(f.quantity),avg(f.price*f.quantity)"
                    + " from Flow f"
                    + " inner join f.product product"
                    + " inner join f.trade trade"
                    + " inner join f.internalCounterparty internalCounterparty"
                    + " left join f.counterparty counterparty"
                    + " left join f.ccp ccp"
                    + " left join f.clearingMember clearingMember"
                    + " left join f.market market"
                    + " left join f.agreement a"
                    + " where f." + configuration.getDateField() + "<='" + HibernateUtil.dateFormat.format(valoDate) + StringUtils.QUOTE
                    + " and f." + configuration.getDateField() + ">'" + HibernateUtil.dateFormat.format(startDate) + StringUtils.QUOTE;
            if (!StringUtils.isEmptyString(positionConfigurationDBAggregationFields)) {
                query += " group by " + positionConfigurationDBAggregationFields;
            }

            HashMap<Integer, Integer> positionMap = new HashMap();
            logger.info("POSITION BUILDING : on date " + HibernateUtil.dateFormat.format(valoDate) + " conf id=" + configurationId + " Start at " + DateUtils.getTime() + " query: " + query);
            Query q = session.createQuery(query);
            List<Object[]> res = q.list();
            for (Object[] objects : res) {

                /**
                 * fill the fields
                 */
                // take flow type from last flow
                // to avoid duplicate positions
                Integer flowId = (Integer) objects[0];
                if (flowId == 28920) {
                    int t = 0;
                }
                Flow flow = FlowAccessor.getFlowById(flowId);
                String flowType = null;
                Integer aggreementId = null;
                if (flow != null) {
                    flowType = flow.getFlowType();
                    if (flow.getAgreement() != null) {
                        aggreementId = flow.getAgreement().getAgreementId();
                    }
                }

                String where = "where p.positionConfigurationId=" + configuration.getPositionConfigurationId();
                int i = 1;// because of the previous column
                for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                    if (objects[i] == null) {
                        where += " and " + item.getDbField() + " is null";
                    } else if (objects[i] instanceof Integer) {
                        where += " and " + item.getDbField() + "=" + objects[i].toString();
                    } else {
                        where += " and " + item.getDbField() + "='" + objects[i].toString() + StringUtils.QUOTE;
                    }
                    i++;
                }
                where = where.replace("f.", "p."); // by default fields are on table prefix f but here we will need p for position

                BigDecimal quantity = BigDecimal.valueOf(Double.valueOf(objects[i].toString()));
                String quantityType = null;
                i++;
                BigDecimal price = BigDecimal.valueOf(Double.valueOf(objects[i].toString()));
                if (quantity.doubleValue() != 0) {
                    price = price.divide(quantity, 20, RoundingMode.HALF_UP);
                }

                //gets the position
                // ==================
                query = " from Position p"
                        + " inner join p.product product"
                        + " inner join p.internalCounterparty internalCounterparty"
                        + " left join p.counterparty counterparty"
                        + " left join p.ccp ccp"
                        + " left join p.clearingMember clearingMember"
                        + " left join p.market market"
                        + StringUtils.SPACE + where;

                Object[] result = (Object[]) HibernateUtil.getObjectWithQuery(query);
                Position position = null;
                if (result != null) {
                    position = (Position) result[0];
                }

                // if the position does not exist, create it
                if (position == null) {
                    position = new Position();
                    position.setFlowType(flowType);
                    position.setPositionConfigurationId(configurationId);
                    position.setAgreement(LegalEntityAccessor.getAgreementById(aggreementId));

                    i = 1;//because of the two previous fields
                    for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                        Method getter = Position.class.getMethod(item.getGetter(), (Class<?>[]) null);
                        Method setter = Position.class.getMethod(item.getGetter().replace("get", "set"), getter.getReturnType());

                        if (objects[i] != null) {
                            if (setter.getParameterTypes()[0].equals(Product.class)) {
                                Integer id = Integer.parseInt(objects[i].toString());
                                Product product = ProductAccessor.getProductById(id);
                                position.setProduct(product);
                            } else if (setter.getParameterTypes()[0].equals(LegalEntity.class)) {
                                Integer id = Integer.parseInt(objects[i].toString());
                                LegalEntity entity = LegalEntityAccessor.getLegalEntityById(id);
                                setter.invoke(position, entity);
                            } else if (setter.getParameterTypes()[0].equals(Boolean.class)) {
                                Boolean bool = Boolean.parseBoolean(objects[i].toString());
                                setter.invoke(position, bool);
                            } else {
                                setter.invoke(position, objects[i].toString());
                            }
                        }
                        i++;
                    }
                    session.save(position);
                }
                positionMap.put(position.getId(), i);

                // now fill the position history
                // ===============================
                PositionHistory positionHistory = new PositionHistory();
                positionHistory.setPosition(position);
                positionHistory.setQuantityType(quantityType);
                positionHistory.setPositionDate(valoDate);
                PositionHistory previousPosHistory = PositionBuilder.getPositionHistoriesByPositionIdAndByDate(position.getId(), startDate);
                if (previousPosHistory != null && previousPosHistory.getQuantity().doubleValue() != 0) {
                    positionHistory.setQuantity(quantity.add(previousPosHistory.getQuantity()));
                    if (positionHistory.getQuantity().doubleValue() != 0) {
                        BigDecimal average = price.max(quantity).add(previousPosHistory.getPrice().multiply(previousPosHistory.getQuantity())).divide(positionHistory.getQuantity(), 20, RoundingMode.UP);
                        positionHistory.setPrice(average);
                    }
                } else {
                    positionHistory.setQuantity(quantity);
                    positionHistory.setPrice(price);
                }
                try {
                    session.save(positionHistory);
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }

            /**
             * some position have not moved
             */
            query = "from Position p inner join fetch p.positionHistoryCollection ph where ph.positionDate='" + HibernateUtil.dateFormat.format(startDate) + StringUtils.QUOTE
                    + " and p.positionConfigurationId=" + configurationId;//+" and p.positionId not in (select ph2.position.positionId from PositionHistory ph2 where ph2.positionDate ='"+HibernateUtil.dateFormat.format(valoDate)+"') ";

            List<Position> positions = HibernateUtil.getObjectsWithQuery(query);
            for (Position position : positions) {
                if (positionMap.get(position.getPositionId()) == null) {
                    try {
                        Collection<PositionHistory> positionHistories = position.getPositionHistoryCollection();
                        positionHistories.size();
                    } catch (LazyInitializationException lazy) {
                        /**
                         * case of lazy init problem
                         *
                         */
                        Collection<PositionHistory> positionHistories = new HashSet();
                        Object object = HibernateUtil.getObjectWithQuery("from PositionHistory ph where ph.position.positionId=" + position.getId() + " and ph.positionDate ='" + HibernateUtil.dateFormat.format(startDate) + StringUtils.QUOTE);
                        PositionHistory positionHistory = (PositionHistory) object;
                        positionHistories.add(positionHistory);
                        position.setPositionHistoryCollection(positionHistories);
                    }

                    PositionHistory previousPosHistory = position.getPositionHistoryCollection().iterator().next();

                    Object object = HibernateUtil.getObjectWithQuery("from PositionHistory ph where ph.position.positionId=" + position.getId() + " and ph.positionDate ='" + HibernateUtil.dateFormat.format(valoDate) + StringUtils.QUOTE);

                    if (object == null) {
                        PositionHistory positionHistory = new PositionHistory();
                        positionHistory.setPosition(position);
                        positionHistory.setQuantityType(previousPosHistory.getQuantityType());
                        positionHistory.setPositionDate(valoDate);
                        positionHistory.setQuantity(previousPosHistory.getQuantity());
                        positionHistory.setPrice(previousPosHistory.getPrice());
                        session.save(positionHistory);
                    }
                }
            }
            session.getTransaction().commit();
            session.close();
            logger.info("END OF POSITION BUILDING. Delay:" + (System.currentTimeMillis() - timerStart) / 1000 + " sec, at " + DateUtils.getTime());

        } catch (HibernateException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException he) {
            logger.error("error " + StringUtils.formatErrorMessage(he));
        }

    }
    public static final String GET_FIRST_FLOW_DATE = "getFirstFlowDate";

    /**
     * Gets the oldest flow date
     *
     * @return
     */
    public static Date getFirstFlowDate() {
        String query = "select min(value_date) as value_date,min(trade_date) as trade_date from flow";
        List dates = HibernateUtil.getListWithSQLQuery(query);
        Object[] objects = (Object[]) dates.get(0);
        Date valueDate = (Date) objects[0];
        Date tradeDate = (Date) objects[1];
        if (valueDate != null && tradeDate != null) {
            Date tmp = valueDate.after(tradeDate) ? tradeDate : valueDate;
            return new Date(tmp.getTime());
        }
        return new Date();
    }
    public static final String GET_FIRST_POSITION_DATE = "getFirstPositionDate";

    /**
     * Gets the oldest position date of a configuration
     *
     *
     * @param configurationId configuration id
     * @return
     */
    public static Date getFirstPositionDate(Integer configurationId) {
        String query = "select min(ph.positionDate) from PositionHistory ph where ph.position.positionConfigurationId=" + configurationId;
        Date tmp = (Date) HibernateUtil.getObjectWithQuery(query);
        if (tmp != null) {
            return new Date(tmp.getTime());
        }
        return null;
    }
    public static final String DELETE_POSITIONS = "deletePositions";

    /**
     * Deletes all positions of a position configuration
     *
     * @param configurationId configuration id
     */
    public static void deletePositions(Integer configurationId) {

        // delete existing positions
        String query = "from Position p where p.positionConfigurationId=" + configurationId;
        List<Position> positions = HibernateUtil.getObjectsWithQuery(query);
        for (Position position : positions) {
            deletePosition(position.getId());
        }
    }
    public static final String DELETE_POSITION = "deletePosition";

    /**
     * Deletes a position
     *
     * @param positionId
     */
    public static void deletePosition(Integer positionId) {

        Position position = getPosition(positionId);
        if (position != null) {
            logger.info("Deleting position " + positionId);
            HibernateUtil.executeQuery("delete from PositionHistory ph where ph.position.positionId=" + position.getPositionId());
            HibernateUtil.executeQuery("delete from PositionHistoricalMeasure ph where ph.position.positionId=" + position.getPositionId());
            position.setPositionHistoryCollection(null);
            HibernateUtil.deleteObject(position);
        }
    }
    public static final String RECALCULATE_POSITION_FROM_START_DATE = "recalculatePositionFromStartDate";

    /**
     * Initialize the position at the start date and recalculates all the positions of a configuration from a start date to an end date
     *
     * @param configurationId configuration id
     * @param startDate start date
     * @param endDate end date
     */
    public static void recalculatePositionFromStartDate(Integer configurationId, Date startDate, Date endDate) {

        // delete existing positions
        deletePositions(configurationId);
        // initialization
        generatePositionFromInception(startDate, configurationId);
        // loop on dates
        generateAllPositionsBetweenTwoDates(configurationId, startDate, endDate);
    }

    /**
     * Recalculates all the positions of a configuration from a start date to an end date
     *
     * @param configurationId configuration id
     * @param startDate start date
     * @param endDate end date
     */
    public static void generateAllPositionsBetweenTwoDates(Integer configurationId, Date startDate, Date endDate) {
        // loop on dates
        Date nextDate = DateUtils.addCalendarDay(startDate, 1);
        while (!nextDate.after(endDate)) {
            //            generatePositionFromInception(rollingDate,configurationId);
            generatePositionBetweenTwoDates(configurationId, nextDate, startDate);
            startDate = nextDate;
            nextDate = DateUtils.addCalendarDay(nextDate, 1);
        }
    }

    /**
     * Builds the from clause of an HQL query from a position configuration used to aggregfate flows and positions
     *
     * @param configuration
     * @return
     */
    public static String getPositionConfigurationDBAggregationFields(PositionConfiguration configuration) {
        String fields = "";
        if (configuration != null && configuration.getPositionConfigurationItems() != null) {
            for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                fields += StringUtils.COMMA + item.getDbField();
            }
        }
        if (fields.length() > 0) {
            fields = fields.substring(1);
        }
        return fields;
    }

    /**
     * Updates / creates / deletes the position history of a position at a given date
     *
     * @param positionDate the date
     * @param position the position (usefull if storePositionHistory do not exist)
     * @param positionHistory the position history to update
     */
    public static void createOrUpdatePositionHistory(Date positionDate, Position position, PositionHistory positionHistory) {

        PositionConfiguration configuration = (PositionConfiguration) HibernateUtil.getObject(PositionConfiguration.class, position.getPositionConfigurationId());

        try {
            /**
             * recalculate the quantity
             *
             */
            String query = "select sum(f.quantity),f.quantityType,avg(f.price*f.quantity) "
                    + " from Flow f"
                    + " inner join f.product product"
                    + " inner join f.internalCounterparty internalCounterparty"
                    + " left join f.counterparty counterparty"
                    + " left join f.ccp ccp"
                    + " left join f.clearingMember clearingMember"
                    + " left join f.market market"
                    + " where  f." + configuration.getDateField() + "<='" + HibernateUtil.dateFormat.format(positionDate) + StringUtils.QUOTE;

            for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                Method flowGetterMethod = Position.class.getMethod(item.getGetter(), (Class<?>[]) null);
                Object object = flowGetterMethod.invoke(position, (Object[]) null);

                if (object == null) {
                    query += " and " + item.getDbField() + " is null";
                } else if (object instanceof LegalEntity) {
                    LegalEntity entity = (LegalEntity) object;
                    query += " and " + item.getDbField() + "=" + entity.getLegalEntityId();
                } else if (object instanceof Product) {
                    Product product = (Product) object;
                    query += " and " + item.getDbField() + "=" + product.getProductId();
                } else if (object instanceof Integer) {
                    query += " and " + item.getDbField() + "=" + object.toString();
                } else {
                    query += " and " + item.getDbField() + "='" + object.toString() + StringUtils.QUOTE;
                }
            }

            query += " group by f.quantityType," + getPositionConfigurationDBAggregationFields(configuration);

            List<Object[]> objectsList = HibernateUtil.getObjectsWithQuery(query);
            if (objectsList != null && objectsList.size() > 0) {
                Object[] objects = (Object[]) objectsList.get(0);
                BigDecimal quantity = BigDecimal.valueOf(Double.parseDouble(objects[0].toString()));
                String type = null;
                if (objects[1] != null) {
                    type = objects[1].toString();
                }
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(objects[2].toString()));
                if (quantity.doubleValue() != 0) {
                    price = price.divide(quantity, 10, RoundingMode.HALF_DOWN);
                }
                if (price.doubleValue() > 1E10) {
                    price = BigDecimal.ZERO;
                }
                if (positionHistory == null) {
                    positionHistory = new PositionHistory(positionDate, quantity, type, price, position);
                } else {
                    positionHistory.setQuantity(quantity);
                    positionHistory.setPrice(price);
                }
                HibernateUtil.storeObject(positionHistory);
            } else {
                if (positionHistory == null) {
                    positionHistory = new PositionHistory(positionDate, BigDecimal.ZERO, Trade.QuantityType.NOTIONAL.name, BigDecimal.ZERO, position);
                } else {
                    positionHistory.setQuantity(BigDecimal.ZERO);
                    positionHistory.setPrice(BigDecimal.ZERO);
                }
                HibernateUtil.storeObject(positionHistory);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }
    public final static String GET_FLOW_FILTER_FROM_POSITION = "getFlowFilterFromPosition";

    /**
     * Create a flow filter based on a position used by drill-down
     *
     * @param position the position
     * @return
     */
    public static Filter getFlowFilterFromPosition(Position position, Date valDate) {
        Filter filter = null;
        if (position != null) {
            try {
                filter = new Filter();
                filter.setFilterCriteria(new ArrayList());
                FilterCriteria filterCriteria = new FilterCriteria("FlowType", position.getFlowType(), null,
                        "getFlowType", String.class.getName(), TemplateColumnItem.COLUMN_STANDARD);
                filter.getFilterCriteria().add(filterCriteria);

                PositionConfiguration positionConfiguration = (PositionConfiguration) HibernateUtil.getObject(PositionConfiguration.class, position.getPositionConfigurationId());
                FilterCriteria filterCriteria2 = new FilterCriteria(positionConfiguration.isInTradeDate() ? "TradeDate" : "ValueDate",
                        null, HibernateUtil.dateFormat.format(position.getPositionHistory(valDate).getPositionDate()),
                        positionConfiguration.isInTradeDate() ? "getTradeDate" : "getValueDate",
                        Date.class.getName(), TemplateColumnItem.COLUMN_STANDARD);
                filter.getFilterCriteria().add(filterCriteria2);
                for (PositionConfigurationItem positionConfigurationItem : positionConfiguration.getPositionConfigurationItems()) {
                    Method getter = Position.class.getMethod(positionConfigurationItem.getGetter(), (Class<?>[]) null);
                    String criteriaValue;
                    String getterSuffix = StringUtils.EMPTY_STRING;
                    if (getter.getReturnType().equals(Product.class)) {
                        Product product = (Product) getter.invoke(position, (Object[]) null);
                        criteriaValue = product.getProductId().toString();
                        getterSuffix = "/getProductId";
                    } else if (getter.getReturnType().equals(LegalEntity.class)) {
                        LegalEntity legalentity = (LegalEntity) getter.invoke(position, (Object[]) null);
                        if (legalentity == null) {
                            criteriaValue = null;
                        } else {
                            criteriaValue = legalentity.getLegalEntityId().toString();
                        }
                        getterSuffix = "/getLegalEntityId";
                    } else {
                        criteriaValue = getter.invoke(position, (Object[]) null).toString();
                    }
                    filterCriteria = new FilterCriteria(positionConfigurationItem.getDbField(), criteriaValue, null, positionConfigurationItem.getGetter() + getterSuffix, getter.getReturnType().getName(), TemplateColumnItem.COLUMN_STANDARD);
                    filter.getFilterCriteria().add(filterCriteria);
                }
                filter.getFilterCriteria().add(filterCriteria);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return filter;
    }

    /**
     * Gets a position from its id
     *
     * @param positionId the id of the position
     * @return
     */
    public static Position getPosition(Integer positionId) {
        return (Position) HibernateUtil.getObject(Position.class, positionId);
    }

    /**
     * Gets a position from its PositionConfiguration and a hql where clause
     *
     * @param configuration id the configuration
     * @param where the hql where clause
     * @return
     */
    public static Position getPosition(PositionConfiguration configuration, String where) {
        String query = " from Position p"
                + " inner join p.product product"
                + " inner join p.internalCounterparty internalCounterparty"
                + " left join p.counterparty counterparty"
                + " left join p.ccp ccp"
                + " left join p.clearingMember clearingMember"
                + " left join p.market market"
                + StringUtils.SPACE + where;

        Object[] result = (Object[]) HibernateUtil.getObjectWithQuery(query);
        if (result != null) {
            return (Position) result[0];
        } else {
            return null;
        }
    }
    public static final String GET_POSITION_AND_POSITION_HISTORY = "getPositionAndPositionHistory";

    /**
     * Gets a position with history with position id and date
     *
     * @param positionId id the the position
     * @param date the date of position history
     * @return
     */
    public static Position getPositionAndPositionHistory(Integer positionId, Date date) {
        Object object = null;
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("from Position p inner join fetch p.positionHistoryCollection ph where p.positionId=");
            builder.append(positionId);
            builder.append(" and ph.positionDate ='");
            builder.append(HibernateUtil.dateFormat.format(date));
            builder.append(StringUtils.QUOTE);
            object = HibernateUtil.getObjectWithQuery(builder.toString());
        } catch (Exception e) {
            logger.error("Error on getPosition : position id= " + positionId + " date= " + HibernateUtil.dateFormat.format(date));
            logger.error("query = " + builder.toString());
            logger.error(StringUtils.formatErrorMessage(e));
        }
        Position position = (Position) object;
        if (position != null) {
            try {
                Collection<PositionHistory> positionHistory = position.getPositionHistoryCollection();
                positionHistory.size();
            } catch (LazyInitializationException lazy) {
                /**
                 * is in case of lazy init problem
                 *
                 */
                Collection<PositionHistory> positionHistories = new HashSet();
                object = HibernateUtil.getObjectWithQuery("from PositionHistory ph where ph.position.positionId=" + positionId + " and ph.positionDate ='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE);
                PositionHistory positionHistory = (PositionHistory) object;
                positionHistories.add(positionHistory);
                position.setPositionHistoryCollection(positionHistories);
            }
        } else {
            logger.error("ERROR POSITION NOT FOUND ID " + positionId + " ON " + HibernateUtil.dateFormat.format(date));
            logger.error(builder.toString());
        }
        return position;
    }

    /**
     * Gets a position with two position histories with position id and dates
     *
     * @param positionId id the the position
     * @param date1 the date of a position history
     * @param date2 the date of another position history
     * @return
     */
    public static Position getPositionAndTwoPositionHistories(Integer positionId, Date date1, Date date2) {

        StringBuilder builder = new StringBuilder();
        Position position = null;
        try {
            builder.append("from Position p inner join fetch p.positionHistoryCollection ph where p.positionId=");
            builder.append(positionId);
            builder.append(" and ph.positionDate in ('");
            builder.append(HibernateUtil.dateFormat.format(date1));
            builder.append("','");
            builder.append(HibernateUtil.dateFormat.format(date2));
            builder.append("')");
            Object object = HibernateUtil.getObjectWithQuery(builder.toString());
            position = (Position) object;
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

        if (position != null) {
            try {
                Collection<PositionHistory> positionHistory = position.getPositionHistoryCollection();
                positionHistory.size();
            } catch (LazyInitializationException lazy) {
                /**
                 * looks stupid, but it is in case of lazy init problemb
                 *
                 */
                Collection<PositionHistory> positionHistories = new HashSet();
                List objectList = HibernateUtil.getObjectsWithQuery("from PositionHistory ph where ph.position.positionId=" + positionId + " and ph.positionDate in ('" + HibernateUtil.dateFormat.format(date1) + "','" + HibernateUtil.dateFormat.format(date2) + "')");
                for (Object object : objectList) {
                    PositionHistory positionHistory = (PositionHistory) object;
                    positionHistories.add(positionHistory);
                }
                position.setPositionHistoryCollection(positionHistories);
            }
        }
        return position;
    }
    public static final String GET_TRADE_ID_FROM_POSITION = "getTradeIdFromPosition";

    /**
     * Gets a trade id from a position used by the report window to open a trade on double-click only works on a single trade position
     *
     * @param positionId id the the position
     * @return
     */
    public static Integer getTradeIdFromPosition(Integer positionId) {
        /**
         * look by product
         *
         */
        Position position = getPosition(positionId);
        Object object = HibernateUtil.getFieldWithSQLQuery("select trade_id from trade where product_id=" + position.getProduct().getId()
                + " and internal_counterparty_id=" + position.getInternalCounterparty().getLegalEntityId()
                + " and counterparty_id=" + position.getCounterparty().getLegalEntityId());
        if (object != null) {
            return Integer.parseInt(object.toString());
        } else {
            return null;
        }
    }
    public static final String GET_POSITION_HISTORICAL_MEASURE_BY_POSITION_AND_MEASURE = "getPositionHistoricalMeasureByPositionAndMeasure";

    /**
     * Gets a position historical measure
     *
     * @param positionId id of the pmosition
     * @param valDate the date
     * @param measure the measure
     *
     * @return
     */
    public static PositionHistoricalMeasure getPositionHistoricalMeasureByPositionAndMeasure(Integer positionId, Date valDate, String measure) {

        String query = "from PositionHistoricalMeasure phm join phm.position p"
                + " where p.positionId=" + positionId + " and phm.positionDate='" + HibernateUtil.dateFormat.format(valDate) + "' and phm.measureName='" + measure + StringUtils.QUOTE;
        List<Object[]> objectList = HibernateUtil.getObjectsWithQuery(query);
        if (objectList != null) {
            for (Object[] objects : objectList) {
                return (PositionHistoricalMeasure) objects[0];
            }
        }
        return null;
    }
    public static final String GET_POSITION_HISTORICAL_MEASURE_BY_POSITION = "getPositionHistoricalMeasureByPosition";

    /**
     * Gets position historical measures
     *
     * @param positionId id of the position
     * @param valDate the date
     *
     * @return
     */
    public static List<PositionHistoricalMeasure> getPositionHistoricalMeasureByPosition(Integer positionId, Date valDate) {
        List<PositionHistoricalMeasure> positionHistoricalMeasures = new ArrayList();
        String query = "from PositionHistoricalMeasure phm join phm.position p"
                + " where p.positionId=" + positionId + " and phm.positionDate='" + HibernateUtil.dateFormat.format(valDate) + StringUtils.QUOTE;
        List<Object[]> objectList = HibernateUtil.getObjectsWithQuery(query);
        if (objectList != null) {
            for (Object[] objects : objectList) {
                positionHistoricalMeasures.add((PositionHistoricalMeasure) objects[0]);
            }
        }
        return positionHistoricalMeasures;
    }
    public static final String GET_POSITION_HISTORICAL_MEASURES_BY_MEASURE = "getPositionHistoricalMeasuresByMeasure";

    /**
     * Gets position historical measures
     *
     * @param productId id of the product
     * @param internalCounterpartyId id of the internal counterparty
     * @param counterpartyId id of the counterparty
     * @param isCollateral id of the counterparty
     * @param ccpId id of the counterparty
     * @param valDate the date
     * @param measure measure
     *
     * @return
     */
    public static List<PositionHistoricalMeasure> getPositionHistoricalMeasuresByMeasure(Integer productId, Integer internalCounterpartyId, Integer counterpartyId, Boolean isCollateral, Integer ccpId, Date valDate, String measure) {
        List<PositionHistoricalMeasure> positionHistoricalMeasures = new ArrayList();
        List<Position> positions = getPositions(productId, internalCounterpartyId, counterpartyId, isCollateral, ccpId);
        for (Position position : positions) {
            PositionHistoricalMeasure positionHistoricalMeasure = getPositionHistoricalMeasureByPositionAndMeasure(position.getPositionId(), valDate, measure);
            if (positionHistoricalMeasure != null) {
                positionHistoricalMeasures.add(positionHistoricalMeasure);
            }
        }
        return positionHistoricalMeasures;
    }
    public static final String GET_POSITION_HISTORICAL_MEASURES_ON_PERIOD = "getPositionHistoricalMeasuresOnPeriod";

    /**
     * Gets position historical measures
     *
     * @param productId id of the product
     * @param internalCounterpartyId id of the internal counterparty
     * @param counterpartyId id of the counterparty
     * @param isCollateral id of the counterparty
     * @param ccpId id of the counterparty
     * @param startDate start date
     * @param endDate end date
     *
     * @return
     */
    public static List<PositionHistoricalMeasure> getPositionHistoricalMeasuresOnPeriod(Integer productId, Integer internalCounterpartyId, Integer counterpartyId, Boolean isCollateral, Integer ccpId, Date startDate, Date endDate) {
        List<Position> positions = getPositions(productId, internalCounterpartyId, counterpartyId, isCollateral, ccpId);
        String ids = StringUtils.EMPTY_STRING;
        for (Position position : positions) {
            ids += position.getPositionId().toString() + StringUtils.COMMA;
        }
        ids = ids.substring(0, ids.length() - 1);
        List<PositionHistoricalMeasure> measureList = new ArrayList();
        String query = "from PositionHistoricalMeasure phm join phm.position p "
                + " where p.positionId in (" + ids + ")"
                + " and phm.positionDate between '" + HibernateUtil.dateFormat.format(startDate) + "' and '" + HibernateUtil.dateFormat.format(endDate) + StringUtils.QUOTE
                + " order by phm.positionDate,phm.measureValue";
        List<Object[]> objectList = HibernateUtil.getObjectsWithQuery(query);
        if (objectList != null) {
            for (Object[] objects : objectList) {
                measureList.add((PositionHistoricalMeasure) objects[0]);
            }
        }
        return measureList;
    }
    public static final String STORE_POSITION_HISTORICAL_MEASURE = "storePositionHistoricalMeasure";

    /**
     * Stores a position historical measure
     *
     * @param positionHistoricalMeasure the position historical measure *
     */
    public static void storePositionHistoricalMeasure(PositionHistoricalMeasure positionHistoricalMeasure) {
        HibernateUtil.storeObject(positionHistoricalMeasure);
    }

    /**
     * Gets a position history from its id
     *
     * @param id id of the position history
     *
     * @return
     */
    public static PositionHistory getPositionHistoryById(Integer id) {
        return (PositionHistory) HibernateUtil.getObject(PositionHistory.class, id);
    }
    public static final String GET_POSITION = "getPosition";

    /**
     * Gets a position from its position configuration / product / internalCounterparty / ctp / collateral flag
     *
     * @param positionConfiguration position configuration
     * @param productId id the product
     * @param internalCounterpartyId the internalCounterparty id
     * @param counterpartyId the counterparty id
     * @param isCollateral the collateral flag
     * @param ccpId
     * @return
     */
    public static Position getPosition(PositionConfiguration positionConfiguration, Integer productId, Integer internalCounterpartyId, Integer counterpartyId, Boolean isCollateral, Integer ccpId) {

        String query = "from Position p where p.product.productId=" + productId + " and p.positionConfigurationId=" + positionConfiguration.getPositionConfigurationId();

        Map<String, PositionConfigurationItem> map = positionConfiguration.getPositionConfigurationItemMap();
        if (map.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_IS_COLLATERAL.name()) != null) {
            query += " and p.isCollateral=" + isCollateral;
        }
        if (map.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.name()) != null) {
            query += " and p.internalCounterparty.legalEntityId=" + internalCounterpartyId;
        }
        if (map.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.name()) != null) {
            query += " and p.counterparty.legalEntityId=" + counterpartyId;
        }
        if (map.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.name()) != null) {
            if (ccpId != null) {
                query += " and p.ccp.legalEntityId=" + ccpId;
            } else {
                query += " and p.ccp.legalEntityId is null";
            }
        }
        return (Position) HibernateUtil.getObjectWithQuery(query);
    }
    public static final String GET_POSITIONS = "getPositions";

    /**
     * Gets positions from its product / internalCounterparty / ctp / collateral flag /ccp
     *
     * @param productId id the product
     * @param internalCounterpartyId the internalCounterparty id
     * @param counterpartyId the counterparty id
     * @param isCollateral the collateral flag
     * @param ccpId the ccp id
     * @return
     */
    public static List<Position> getPositions(Integer productId, Integer internalCounterpartyId, Integer counterpartyId, Boolean isCollateral, Integer ccpId) {
        List<Position> positions = new ArrayList();
        List<PositionConfiguration> positionConfigurations = PositionBuilder.getPositionConfigurations();
        for (PositionConfiguration positionConfiguration : positionConfigurations) {
            Position position = getPosition(positionConfiguration, productId, internalCounterpartyId, counterpartyId, isCollateral, ccpId);
            if (position != null) {
                positions.add(position);
            }
        }
        return positions;
    }
    public static final String RECALCULATE_POSITION = "recalculatePosition";

    /**
     * Recalculates a position from start
     *
     * @param positionId id the the product
     */
    public static void recalculatePosition(Integer positionId) {
        String query = "select min(ph.positionDate) from PositionHistory ph where ph.position.positionId=" + positionId;
        Date firstDate = (Date) HibernateUtil.getObjectWithQuery(query);
        Position position = getPosition(positionId);
        if (position != null) {
            Date configurationStartDate = getPositionConfiguration(position.getPositionConfigurationId()).getStartDate();
            if (configurationStartDate.after(firstDate)) {
                firstDate = configurationStartDate;
            }
            if (firstDate != null) {
                ReportObjectNotification reportObjectNotification = new ReportObjectNotification(Position.class, position.getId(), false, false, HibernateUtil.dateFormat.format(firstDate), position.getPositionConfigurationId());
                DAOAgentPool.updatePosition(reportObjectNotification);
            }
        }
    }
    public static final String UPDATE_POSITION_FROM_TRADE = "updatePositionFromTrade";

    /**
     * Updates the positions linked to a given trade
     *
     * @param trade the trade
     */
    public static void updatePositionFromTrade(Trade trade) {
        updatePositionFromTrades(Arrays.asList(new Trade[]{trade}));
    }
    public static final String UPDATE_POSITION_FROM_TRADES = "updatePositionFromTrades";

    /**
     * Updates the positions linked to a given trade
     *
     * @param trades
     */
    public static void updatePositionFromTrades(List<Trade> trades) {
        List<Integer> alreadyManagedProductIds = new ArrayList();
        for (Trade trade : trades) {
            Product currency = ProductAccessor.getProductByShortName(trade.getSettlementCurrency());

            if (currency == null || trade.getInternalCounterparty() == null || trade.getCounterparty() == null) {
                logger.error("Trade corrupted " + trade.getTradeId() + " => position not updated.");
            } else {
                // look for each flows of the trade
                String query = "from Flow f where f.trade.tradeId=" + trade.getTradeId() + " order by f.tradeDate";
                List<Flow> flows = HibernateUtil.getObjectsWithQuery(query);

                for (Flow flow : flows) {
                    updatePositionFromFlow(alreadyManagedProductIds, flow, trade, true);
                }
            }
        }
    }
    public static final String UPDATE_POSITION_FROM_FLOW = "updatePositionFromFlow";
    private static final String QUERY_UPDATE_POSITION_FROM_FLOW = " from Position p"
            + " inner join p.product product"
            + " inner join p.internalCounterparty internalCounterparty"
            + " left join p.counterparty counterparty"
            + " left join p.ccp ccp"
            + " left join p.clearingMember clearingMember"
            + " left join p.market market"
            + " where p.positionConfigurationId=";

    public static void updatePositionFromFlow(List<Integer> alreadyManagedProductId, Flow flow, Trade trade, boolean useDateFromTrade) {
        // just one time by product
        StringBuilder query;
        Position position = null;
        try {
            if (!alreadyManagedProductId.contains(flow.getProduct().getProductId())) {
                alreadyManagedProductId.add(flow.getProduct().getProductId());
                // loop on each position configuration
                boolean isNewPosition = false;
                List<PositionConfiguration> positionConfigurations = getPositionConfigurations();
                for (PositionConfiguration configuration : positionConfigurations) {

                    position = null;
                    // look for concerned position
                    query = new StringBuilder(QUERY_UPDATE_POSITION_FROM_FLOW);
                    query.append(configuration.getPositionConfigurationId());
                    for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                        Method flowGetterMethod = Flow.class.getMethod(item.getGetter(), (Class<?>[]) null);
                        Object object = flowGetterMethod.invoke(flow, (Object[]) null);
                        if (object == null) {
                            query.append(" and ").append(item.getDbField()).append(" is null");
                        } else if (object instanceof LegalEntity) {
                            LegalEntity entity = (LegalEntity) object;
                            query.append(" and ").append(item.getDbField()).append("=").append(entity.getLegalEntityId());
                        } else if (object instanceof Product) {
                            Product product = (Product) object;
                            query.append(" and ").append(item.getDbField()).append("=").append(product.getProductId());
                        } else if (object instanceof Integer) {
                            query.append(" and ").append(item.getDbField()).append("=").append(object.toString());
                        } else {
                            query.append(" and ").append(item.getDbField()).append("='").append(object.toString()).append(StringUtils.QUOTE);
                        }
                    }
                    String queryString = query.toString().replace("f.", "p.");

                    Object[] result = (Object[]) HibernateUtil.getObjectWithQuery(queryString);
                    if (result != null) {
                        position = (Position) result[0];
                    }

                    // create the position if it does not exist
                    if (position == null) {
                        isNewPosition = true;
                        position = new Position();
                        for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                            Method flowGetterMethod = Flow.class.getMethod(item.getGetter(), (Class<?>[]) null);
                            Object object = flowGetterMethod.invoke(flow, (Object[]) null);

                            Method positionSetterMethod = Position.class.getMethod(item.getGetter().replace("get", "set"), flowGetterMethod.getReturnType());
                            positionSetterMethod.invoke(position, object);
                        }
                        position.setPositionConfigurationId(configuration.getPositionConfigurationId());
                        position.setFlowType(flow.getFlowType());
                        position.setAgreement(flow.getAgreement());

                        HibernateUtil.saveObject(position);
                    }

                    // recalculate position
                    Date fromDate;
                    if (configuration.isInTradeDate()) {
                        if (useDateFromTrade) {
                            fromDate = trade.getTradeDate();
                        } else {
                            fromDate = flow.getSettlementDate();
                        }
                    } else {
                        if (useDateFromTrade) {
                            fromDate = trade.getValueDate();
                        } else {
                            fromDate = flow.getValueDate();
                        }
                    }
                    boolean isCancel = flow.getQuantity().doubleValue() == 0;
                    ReportObjectNotification reportObjectNotification = new ReportObjectNotification(Position.class, position.getId(), isNewPosition, isCancel, HibernateUtil.dateFormat.format(fromDate), position.getPositionConfigurationId());
                    DAOAgentPool.updatePosition(reportObjectNotification);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
            logger.error(StringUtils.formatErrorMessage(e));
            if (position == null) {
                logger.error("error : positio is null");
            } else if (position.getId() == null) {
                logger.error("error : position.getId() is null");
            } else if (position.getPositionConfigurationId() == null) {
                logger.error("error : position.getPositionConfigurationId() is null");
            }
        }
    }

    /**
     * Updates a position starting at given date
     *
     * @param position
     * @param fromDate the start date
     */
    public static void updatePosition(Position position, Date fromDate) {

        PositionConfiguration configuration = (PositionConfiguration) HibernateUtil.getObject(PositionConfiguration.class, position.getPositionConfigurationId());
        if (configuration.getStartDate().after(fromDate)) {
            fromDate = configuration.getStartDate();
        }

        /**
         * look for position history on this position
         */
        String query = "select distinct ph.positionDate,ph.positionHistoryId"
                + " from Position p inner join p.positionHistoryCollection ph"
                + " where p.positionId=" + position.getPositionId()
                + " and ph.positionDate>='" + HibernateUtil.dateFormat.format(fromDate) + StringUtils.QUOTE
                + " order by ph.positionDate";

        List<Object[]> positionHistoryList = HibernateUtil.getObjectsWithQuery(query);

        if (positionHistoryList.size() > 0) {
            /*
             * a little optimization in order to calculate the quantity only when we've got a flow
             * first we look for concerned flows
             */
            query = "select distinct f." + configuration.getDateField()
                    + " from Flow f"
                    + " inner join f.product product"
                    + " inner join f.internalCounterparty internalCounterparty"
                    + " left join f.counterparty counterparty"
                    + " left join f.ccp ccp"
                    + " left join f.clearingMember clearingMember"
                    + " left join f.market market"
                    + " where  f." + configuration.getDateField() + ">='" + HibernateUtil.dateFormat.format(fromDate) + StringUtils.QUOTE
                    + "  and   f." + configuration.getDateField() + "<='" + HibernateUtil.dateFormat.format(DateUtils.getDate()) + StringUtils.QUOTE;

            for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                try {
                    Method flowGetterMethod = Position.class.getMethod(item.getGetter(), (Class<?>[]) null);
                    Object object = flowGetterMethod.invoke(position, (Object[]) null);

                    if (object == null) {
                        query += " and " + item.getDbField() + " is null";
                    } else if (object instanceof LegalEntity) {
                        LegalEntity entity = (LegalEntity) object;
                        query += " and " + item.getDbField() + "=" + entity.getLegalEntityId();
                    } else if (object instanceof Product) {
                        Product product = (Product) object;
                        query += " and " + item.getDbField() + "=" + product.getProductId();
                    } else if (object instanceof Integer) {
                        query += " and " + item.getDbField() + "=" + object.toString();
                    } else {
                        query += " and " + item.getDbField() + "='" + object.toString() + StringUtils.QUOTE;
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
            query += " order by f." + configuration.getDateField();
            List flowList = HibernateUtil.getObjectsWithQuery(query);

            Date histoDate;
            Integer posHistoryId = null;
            BigDecimal quantity = null;
            BigDecimal price = null;
            PositionHistory positionHistory = null;
            int iHisto = 0;
            if (flowList.isEmpty()) {
                /* if no flow found :
                 * - calculate the position on first date
                 * - and copy it on folloing dates
                 */
                try {
                    histoDate = (Date) ((Object[]) positionHistoryList.get(0))[0];
                    posHistoryId = (Integer) ((Object[]) positionHistoryList.get(0))[1];
                    positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                    createOrUpdatePositionHistory(histoDate, position, positionHistory);
                    quantity = positionHistory.getQuantity();
                    price = positionHistory.getPrice();
                    iHisto++;
                    while (iHisto < positionHistoryList.size()) {
                        try {
                            posHistoryId = (Integer) ((Object[]) positionHistoryList.get(iHisto))[1];
                            positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                            if (positionHistory != null) {
                                positionHistory.setQuantity(quantity);
                                positionHistory.setPrice(price);
                                HibernateUtil.storeObject(positionHistory);
                            }
                            iHisto++;
                        } catch (Exception e) {
                            logger.error("POSITION ERROR " + position.toString() + " posHistoryId " + posHistoryId);
                            logger.error(StringUtils.formatErrorMessage(e));
                        }
                    }
                    // after last one, create missing one if necessary
                    histoDate = (Date) ((Object[]) positionHistoryList.get(positionHistoryList.size() - 1))[0];
                    histoDate = DateUtils.addCalendarDay(histoDate, 1);
                    while (!histoDate.after(DateUtils.getDate())) {
                        PositionHistory newone = positionHistory.clone();
                        newone.setPositionDate(histoDate);
                        HibernateUtil.storeObject(newone);
                        histoDate = DateUtils.addCalendarDay(histoDate, 1);
                    }
                } catch (Exception e) {
                    logger.error("POSITION ERROR " + position.toString() + " posHistoryId " + posHistoryId);
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            } else {
                /* if we have some flows:
                 *  - loop on flows
                 */
                histoDate = (Date) ((Object[]) positionHistoryList.get(0))[0];
                Date loopDate = (Date) flowList.get(0);
                if (histoDate.after(loopDate)) {
                    while (loopDate.before(histoDate)) {
                        createOrUpdatePositionHistory(loopDate, position, null);
                        loopDate = DateUtils.addCalendarDay(loopDate, 1);
                    }
                }
                posHistoryId = (Integer) ((Object[]) positionHistoryList.get(0))[1];
                positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                createOrUpdatePositionHistory(histoDate, position, positionHistory);
                quantity = positionHistory.getQuantity();
                price = positionHistory.getPrice();
                iHisto++;
                for (Object objects : flowList) {
                    try {
                        Date flowDate = (Date) objects;
                        // loop while no new flow and update positions histories
                        while (iHisto < positionHistoryList.size() && ((Date) ((Object[]) positionHistoryList.get(iHisto))[0]).before(flowDate)) {
                            posHistoryId = (Integer) ((Object[]) positionHistoryList.get(iHisto))[1];
                            positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                            positionHistory.setQuantity(quantity);
                            positionHistory.setPrice(price);
                            HibernateUtil.storeObject(positionHistory);
                            iHisto++;
                        }
                        // when a flow is found : recalculate quantity and price
                        if (iHisto < positionHistoryList.size()) {
                            histoDate = (Date) ((Object[]) positionHistoryList.get(iHisto))[0];
                            posHistoryId = (Integer) ((Object[]) positionHistoryList.get(iHisto))[1];
                            if (positionHistory != null) {
                                positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                                createOrUpdatePositionHistory(histoDate, position, positionHistory);
                                quantity = positionHistory.getQuantity();
                                price = positionHistory.getPrice();
                            }
                            iHisto++;
                        }
                    } catch (Exception e) {
                        logger.error("POSITION ERROR " + position.toString());
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
                // after last flow date, just update quantities
                while (iHisto < positionHistoryList.size()) {
                    try {
                        posHistoryId = (Integer) ((Object[]) positionHistoryList.get(iHisto))[1];
                        positionHistory = PositionBuilder.getPositionHistoryById(posHistoryId);
                        if (positionHistory != null) {
                            positionHistory.setQuantity(quantity);
                            positionHistory.setPrice(price);
                            HibernateUtil.storeObject(positionHistory);
                        }
                        iHisto++;
                    } catch (Exception e) {
                        logger.error("POSITION ERROR posHistoryId " + posHistoryId + " position " + position);
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            }
        } else {
            /**
             * if no position exist with this trade we have to create them
             */
            Date today = DateUtils.getDate();
            Date configurationDate = getPositionConfiguration(position.getPositionConfigurationId()).getStartDate();
            if (configurationDate.after(fromDate)) {
                fromDate = configurationDate;
            }
            while (!fromDate.after(today)) {
                createOrUpdatePositionHistory(fromDate, position, null);
                fromDate = DateUtils.addCalendarDay(fromDate, 1);
            }
        }
    }

    public void storePositionHistory(PositionHistory positionHistory) {
        if (positionHistory.getQuantity().doubleValue() >= 10000000000000.) {
            positionHistory.setQuantity(BigDecimal.valueOf(10000000000000.));
        }
        HibernateUtil.storeObject(positionHistory);

    }

    public static final String DELETE_PORTFOLIOS_POSITIONS = "deletePortfoliosPositions";

    public static void deletePortfoliosPositions(Integer ptfId) {
        List<Integer> positions = (List) HibernateUtil.getObjectsWithQuery("select p.positionId from Position p where p.internalCounterparty.legalEntityId=" + ptfId);
        for (Integer id : positions) {
            deletePosition(id);
        }

    }

    public static final String GET_POSITIONS_BY_PRODUCTID_AND_BY_INTERNALCOUNTERPARTY = "getPositionsByProductIdAndByInternalCounterparty";

    public static List<Position> getPositionsByProductIdAndByInternalCounterparty(Integer productId, Integer portfolioId) {
        return (List) HibernateUtil.getObjectsWithQuery("from Position p where p.internalCounterparty.legalEntityId=" + portfolioId + " and p.product.productId=" + productId);
    }

    public static List<Position> getPositionsByInternalCounterparty(Integer portfolioId) {
        return (List) HibernateUtil.getObjectsWithQuery("from Position p where p.internalCounterparty.legalEntityId=" + portfolioId);
    }

    public static final String GET_POSITIONHISTORIES_BY_POSITION_ID_AND_BY_DATE = "getPositionHistoriesByPositionIdAndByDate";

    public static PositionHistory getPositionHistoriesByPositionIdAndByDate(Integer positionId, Date date) {
        return (PositionHistory) HibernateUtil.getObjectWithQuery("from PositionHistory ph where ph.positionDate='" + HibernateUtil.dateFormat.format(date) + "' and ph.position.positionId=" + positionId);
    }

    public static final String SET_POSITION = "setPosition";

    public static Trade setPosition(Date positionDate, Product product, LegalEntity internalCounterparty, BigDecimal targetQuantity) {

        BigDecimal quantityPositionHistory = BigDecimal.ZERO;
        List<Position> positionList = PositionBuilder.getPositionsByProductIdAndByInternalCounterparty(product.getProductId(), internalCounterparty.getLegalEntityId());
        if (!(positionList.isEmpty())) {
            Position position = positionList.get(0);
            PositionHistory positionHistory = getPositionHistoriesByPositionIdAndByDate(position.getPositionId(), positionDate);
            if (positionHistory != null) {
                quantityPositionHistory = positionHistory.getQuantity();
            }
        }
        BigDecimal tradeQuantity = targetQuantity.subtract(quantityPositionHistory);
        if (tradeQuantity.abs().doubleValue() > 0.001) {
            Trade lookThroughTrade = new Trade();
            lookThroughTrade.setQuantity(tradeQuantity);
            lookThroughTrade.setInternalCounterparty(internalCounterparty);
            lookThroughTrade.setProduct(product);
            lookThroughTrade.setTradeType(Trade.TradeType.BUY_SELL.name);
            lookThroughTrade.setStatus(TradeAccessor.TradeStatus.NEW.name);
            lookThroughTrade.setAmount(BigDecimal.ZERO);
            lookThroughTrade.setPrice(BigDecimal.ZERO);
            lookThroughTrade.setCounterparty(LegalEntityAccessor.getLegalEntityByShortName("UNKNOWN"));
            lookThroughTrade.setValueDate(positionDate);
            lookThroughTrade.setTradeDate(positionDate);
            lookThroughTrade.setTradeTime(new Date());
            lookThroughTrade.setQuantityType(Trade.QuantityType.QUANTITY.name);
            lookThroughTrade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
            lookThroughTrade.setPriceCurrency(product.getNotionalCurrency());
            lookThroughTrade.setSettlementCurrency(product.getNotionalCurrency());
            lookThroughTrade.setForexRate(BigDecimal.ONE);
            lookThroughTrade.setComment("generated by transparency trade generator");
            TradeAccessor.storeTrade(lookThroughTrade);
            return lookThroughTrade;
        }
        return null;
    }

}
