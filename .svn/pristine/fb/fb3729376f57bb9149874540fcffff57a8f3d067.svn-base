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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.PricingMaps;
import org.gaia.dao.reports.PositionTree.AggregNode;
import org.gaia.dao.reports.customColumns.CVACalculationManager;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.reports.customColumns.ICustomColumn;
import org.gaia.dao.reports.customColumns.LookThroughManager;
import org.gaia.dao.reports.customColumns.TradeQuantityEventManager;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.SnapshotReport;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Benjamin Frerejean The report builder handles the building of reports
 * @param <T>
 */
public class ReportBuilder<T> {

    public PositionTree.PositionNode root;
    public PricingMaps maps;
    private ReportTemplate template;
    private final Map<String, CustomColumn> customColumns = new HashMap();
    private static final Logger logger = Logger.getLogger(ReportBuilder.class);

    public enum ReportInformation {

        FXRate,
        ValueDate
    }

    public ReportBuilder() {
    }

    public ReportTemplate getReportTemplate() {
        return this.template;
    }

    /**
     * Fills report lines objects with static fields based on the report template
     *
     * @param reportLines to create / update
     * @param _template the report template
     * @param valueDate the date of data
     * @param isFirstDate
     * @param lookthrough
     * @return
     * @throws java.lang.Exception
     */
    public LinkedHashMap<Integer, ReportLine> fillReportLines(
            LinkedHashMap<Integer, ReportLine> reportLines,
            ReportTemplate _template,
            Date valueDate,
            boolean isFirstDate,
            boolean lookthrough) throws Exception {

        PositionTree tree = new PositionTree();
        root = tree.new AggregNode(null);
        template = (ReportTemplate) _template.clone();
        if (template != null) {

            Filter filter = template.getFilter();
            Class objectType = template.getObjectTypeClass();
            template.setTemplateColumnItems(orderColumns(template.getTemplateColumnItems()));

            try {
                // test if product is a sub object
                objectType.getDeclaredMethod("getProduct", (Class[]) null);
                // add the portfolio id to allow look-through
                if (lookthrough && template.getObjectTypeClass().equals(Position.class)) {
                    addProductEquityPortfolioIdColumn(template.getTemplateColumnItems());
                    addProductIdColumn(template.getTemplateColumnItems());
                    addProductFakeStockTypeReference(template.getTemplateColumnItems());
                }
                // add the currency to allow conversions
                addProductCurrencyColumn(template.getTemplateColumnItems());
            } catch (Exception e) {
            }// nothing to do here
            // add the id to be able to bet objects (do not remove, mandatory)
            addIdColumn(template.getTemplateColumnItems(), objectType);

            if (Position.class.equals(objectType)) {
                filter = FilterAccessor.addCriteria(filter, "PositionHistory.PositionDate", "getPositionHistory/getPositionDate", HibernateUtil.dateFormat.format(valueDate), HibernateUtil.dateFormat.format(valueDate), Date.class.getName());
                filter = FilterAccessor.addCriteria(filter, "PositionConfigurationId", "getPositionConfigurationId", template.getPositionConfiguration().getPositionConfigurationId().toString(), null, Integer.class.getName());
            } else if (Trade.class.equals(objectType)) {
                filter = FilterAccessor.addCriteria(filter, "TradeDate", "getTradeDate", null, HibernateUtil.dateFormat.format(valueDate), Date.class.getName());
            }
            if (Position.class.equals(objectType) && !valueDate.after(DateUtils.getDate())) {
                PositionBuilder.generatePositionIfNeeded(valueDate, template.getPositionConfiguration().getPositionConfigurationId());
            }
            // CALL TO Object Accessor
            //=====================================
            reportLines = ObjectAccessorCaller.getReportLinesWithFilter(reportLines, objectType, filter, template.getTemplateColumnItems(), valueDate, isFirstDate, lookthrough);

            // after lines retrieval, remove additional colums
            List<TemplateColumnItem> toRemove = new ArrayList();
            String typeId = objectType.getSimpleName() + " Id";
            TemplateColumnItem prev = null;
            for (TemplateColumnItem column : template.getTemplateColumnItems()) {
                if (column.isToBeRemoved) {
                    toRemove.add(column);
                } else if (column.getName().equalsIgnoreCase(typeId) && prev != null && prev.getName().equalsIgnoreCase(typeId)) {
                    toRemove.add(column);
                }
            }
            template.getTemplateColumnItems().removeAll(toRemove);
        }
        return reportLines;
    }

    /**
     * Merges report lines and pricer results
     *
     * @param lines to fill
     * @param pricingResults pricing results
     * @param isFirstDate is on first report date
     */
    public static void mergeColumns(LinkedHashMap<Integer, ReportLine> lines, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricingResults, Boolean isFirstDate) {

        for (ReportLine line : lines.values()) {
            try {
                Map<IPricerMeasure, IMeasureValue[]> cols = pricingResults.get(line.getId());
                if (cols != null) {
                    for (Entry<IPricerMeasure, IMeasureValue[]> colEntry : cols.entrySet()) {
                        IMeasureValue[] vals = colEntry.getValue();
                        if (isFirstDate && line.getObjectMapFirst() != null) {
                            for (IMeasureValue val : vals) {
                                line.getObjectMapFirst().put(val.getName(), val.getMeasureValue());
                            }
                        } else if (line.getObjectMap() != null) {
                            for (IMeasureValue val : vals) {
                                line.getObjectMap().put(val.getName(), val.getMeasureValue());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e) + " line " + line.getId());
            }
        }
    }

    /**
     * Calculates all post treatments
     *
     * @param pricerResultsByTrade pricer results object
     * @param listPriceable list of priceables
     * @param valuationDate
     * @param mapFX
     * @param isFirstDate
     * @param reportSettings
     * @param additionalreportData map of forex rates
     */
    public static void calculatePostTreatments(
            LinkedHashMap<Integer, ReportLine> listPriceable,
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,
            Date valuationDate,
            Map<String, BigDecimal> mapFX,
            Map<String, List<ReportLine>> additionalreportData,
            boolean isFirstDate,
            ReportSettings reportSettings
    ) {
        LookThroughManager.calculate(listPriceable, pricerResultsByTrade, valuationDate, mapFX, additionalreportData, isFirstDate, reportSettings);

        TradeQuantityEventManager.calculate(listPriceable, pricerResultsByTrade, valuationDate, mapFX, additionalreportData, isFirstDate, reportSettings);

        CVACalculationManager.calculate(listPriceable, pricerResultsByTrade, valuationDate, mapFX, additionalreportData, isFirstDate, reportSettings);
    }

    /**
     * Load priceables
     *
     * @param reportLines to fill
     * @param objectClass the class of objects to load
     * @param valDate value date
     * @return
     */
    public static ArrayList<IPriceable> getPriceables(List<ReportLine> reportLines, Class objectClass, Date valDate) {

        ArrayList<IPriceable> listPriceables = new ArrayList();
        for (ReportLine line : reportLines) {

            Object object = HibernateUtil.getObject(objectClass, line.getId());
            if (object instanceof IPriceable) {
                IPriceable priceable = (IPriceable) object;
                listPriceables.add(priceable);
            }
        }
        return listPriceables;
    }

    /**
     * Load a priceable
     *
     * @param id the id of the object
     * @param className the class name of the object
     * @param valDate value date
     * @param firstDate first date
     * @return
     */
    public static IPriceable getPriceable(Integer id, String className, Date valDate, Date firstDate) {
        IPriceable priceable = null;

        if (className.equalsIgnoreCase(Position.class.getName())) {

            if (firstDate == null || valDate.equals(firstDate)) {
                priceable = PositionBuilder.getPositionAndPositionHistory(id, valDate);
            } else {
                priceable = PositionBuilder.getPositionAndTwoPositionHistories(id, valDate, firstDate);
            }
        } else {
            try {
                Class clazz = Class.forName(className);
                Object obj = HibernateUtil.getObject(clazz, id);
                if (obj instanceof IPriceable) {
                    priceable = (IPriceable) obj;
                }
            } catch (Exception e) {
                logger.error("error  " + StringUtils.formatErrorMessage(e));
            }
        }

        return priceable;
    }
    public static final String GET_SNAPSHOT_REPORT_BY_ID = "getSnapshotReportById";

    /**
     * Load a snaphot report
     *
     * @param id the id of the object
     * @return
     */
    public static SnapshotReport getSnapshotReportById(Integer id) {
        SnapshotReport res;
        Session session = HibernateUtil.getSession();
        Query querySnapShot = session.getNamedQuery("SnapshotReport.findBySnapshotId");
        querySnapShot.setInteger("snapshotReportId", id);
        res = (SnapshotReport) querySnapShot.uniqueResult();
        return res;
    }
    public static final String SAVE_SNAPSHOT_REPORT = "saveSnapshotReport";

    public static void saveSnapshotReport(SnapshotReport snapshot) {
        HibernateUtil.storeObject(snapshot);
    }
    public static final String DELETE_SNAPSHOT_REPORT = "deleteSnapshotReport";

    public static void deleteSnapshotReport(SnapshotReport snapshotReport) {
        HibernateUtil.deleteObject(snapshotReport);
    }

    /**
     * Add additional data if needed
     *
     * @param lines the report lines
     * @param reportSettings
     * @param mapFXEnd map of forex rates at val date
     * @param mapFXStart map of forex rates at start date
     *
     */
    public void addReportData(LinkedHashMap<Integer, ReportLine> lines, ReportSettings reportSettings, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart) {

        for (TemplateColumnItem item : reportSettings.getTemplate().getTemplateColumnItems()) {
            if (item.getName().equalsIgnoreCase(ReportInformation.FXRate.name())) {
                addFXRates(lines, reportSettings.getValDate(), reportSettings.getFirstDate(), mapFXEnd, mapFXStart);
            } else if (item.getName().equalsIgnoreCase(ReportInformation.ValueDate.name())) {
                for (ReportLine line : lines.values()) {
                    line.getObjectMap().put(ReportInformation.ValueDate.name(), reportSettings.getValDate());
                }
            } else if (item.getName().equalsIgnoreCase(ReportInformation.ValueDate.name() + ReportTemplate.FIRSTDATE_SUFFIX)) {
                for (ReportLine line : lines.values()) {
                    line.getObjectMap().put(ReportInformation.ValueDate.name(), reportSettings.getFirstDate());
                }
            }
        }
    }

    /**
     * Add forex rates to report lines
     *
     * @param lines the report lines
     * @param valDate value date
     * @param startDate
     * @param mapFXEnd map of forex rates at val date
     * @param mapFXStart map of forex rates at start date
     *
     */
    public void addFXRates(LinkedHashMap<Integer, ReportLine> lines, Date valDate, Date startDate, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart) {
        String reportCurrency = template.getCurrency();
        for (ReportLine line : lines.values()) {
            String currency = getCurrency(line);
            BigDecimal fxRate = getFxRate(reportCurrency, currency, mapFXEnd);
            line.getObjectMap().put(ReportInformation.FXRate.name(), fxRate);

            if (!startDate.equals(valDate)) {
                fxRate = getFxRate(reportCurrency, currency, mapFXStart);
                line.getObjectMapFirst().put(ReportInformation.FXRate.name() + ReportTemplate.FIRSTDATE_SUFFIX, fxRate);
            }
        }

    }

    /**
     * Gets a forex rate
     *
     * @param reportCurrency the report currency
     * @param productCurrency the product currency
     * @param mapFX the forex source map
     * @return
     *
     */
    public static BigDecimal getFxRate(String reportCurrency, String productCurrency, Map<String, BigDecimal> mapFX) {

        try {
            if (productCurrency == null) {
                return BigDecimal.ONE;
            } else if (reportCurrency.equals(productCurrency)) {
                return BigDecimal.ONE;
            } else {
                String pair = productCurrency + "/" + reportCurrency;
                String pair2 = reportCurrency + "/" + productCurrency;

                BigDecimal fxRate1 = mapFX.get(pair);
                BigDecimal fxRate2 = mapFX.get(pair2);
                if (fxRate1 != null) {
                    return fxRate1;
                } else if (fxRate2 != null && !fxRate2.equals(BigDecimal.ZERO)) {
                    return BigDecimal.ONE.divide(fxRate2, 20, RoundingMode.HALF_UP);
                } else {
                    return BigDecimal.ZERO;
                }
            }
        } catch (Exception e) {
            logger.error("Error on getFxRate " + reportCurrency + "/" + productCurrency);
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return BigDecimal.ZERO;
    }

    /**
     * Converts a field to report currency
     *
     * @param reportCurrency the report currency
     * @param productCurrency the product currency
     * @param decimal the value of the field
     * @param date the date
     * @param mapFX the forex source map
     * @return
     *
     */
    public static BigDecimal convertField(String reportCurrency, String productCurrency, BigDecimal decimal, Date date, Map<String, BigDecimal> mapFX) {
        BigDecimal fxRate = getFxRate(reportCurrency, productCurrency, mapFX);
        if (fxRate != null) {
            decimal = decimal.multiply(fxRate);
        }
        return decimal;
    }

    /**
     * Gets the product currency on a report line
     *
     * @param line the report line
     * @return
     *
     */
    public static String getCurrency(ReportLine line) {
        String cur = StringUtils.EMPTY_STRING;
        if (line != null) {
            if (line.getLineData() != null && line.getLineData() instanceof IPriceable) {
                IPriceable priceable = (IPriceable) line.getLineData();
                if (priceable.getProduct() != null) {
                    cur = priceable.getProduct().getNotionalCurrency();
                }
            } else if (line.getObjectMap().containsKey("Product.NotionalCurrency")) {
                cur = (String) line.getObjectMap().get("Product.NotionalCurrency");
            }
        }
        return cur;
    }

    /**
     * Builds the output tree of a report
     *
     * @param reportLines the report lines
     * @param reportSettings
     * @param mapFXEnd map of forex rates at value date
     * @param mapFXStart map of forex rates at start date
     * @param additionalReportData
     * @return
     */
    public PositionTree.PositionNode buildOutPutTree(LinkedHashMap<Integer, ReportLine> reportLines, ReportSettings reportSettings, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, Map<String, List<ReportLine>> additionalReportData) {
        int i;
        int j;
        if (reportLines != null) {
            try {
                /**
                 * build header
                 */
                ArrayList<String> headings = new ArrayList();
                for (TemplateColumnItem tci : template.getTemplateColumnItems()) {
                    addColumnToHeader(tci, headings);
                }

                /**
                 * build the array data
                 */
                logger.info("FILL DATA : " + reportLines.size() + " LINES AT " + DateUtils.getTime());
                Object[][] result = new Object[reportLines.size() + 1][headings.size()];

                /**
                 * set header as first line
                 */
                j = 0;
                for (String s : headings) {
                    result[0][j] = s;
                    j++;
                }

                /**
                 * if valdate = start date, set fx rates the same
                 */
                if (!reportSettings.hasFirstDate()) {
                    mapFXStart = mapFXEnd;
                }

                /* FILL RESULT ARRAY
                 * =======================
                 */
                i = 1;
                try {
                    String reportCurrency = template.getCurrency();
                    for (ReportLine line : reportLines.values()) {
                        if (line.getParentLine() == null) {
                            Integer id = line.getId();
                        }
                        logger.debug("line : " + i + " id:" + line.getId());
                        result[i] = fillReportRow(line, reportSettings.hasFirstDate(), reportCurrency, reportSettings.getValDate(), reportSettings.getFirstDate(), mapFXEnd, mapFXStart);
                        i++;
                    }
                } catch (Exception e) {
                    logger.error("error  " + StringUtils.formatErrorMessage(e));
                }
                /**
                 * Final sorting :case when there is an aggregation on a custom column or a look through that mixes lines
                 */
                final int MAX_FUNDTHROUGH_SORTING_LEVEL = 3;
                j = 0;
                int agregationColumnNumber = 0;
                for (TemplateColumnItem templatecolumnItem : template.getTemplateColumnItems()) {
                    if (templatecolumnItem.isAggregated() || (reportSettings.getTemplate().isFundLookThrough() && j < MAX_FUNDTHROUGH_SORTING_LEVEL)) {
                        if (templatecolumnItem.isAggregated()) {
                            agregationColumnNumber++;
                        }
                        if ((templatecolumnItem.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_CUSTOM)
                                || reportSettings.getTemplate().isFundLookThrough())) {
                            List<SortableRow> toBeSorted = new ArrayList();
                            // not the header
                            for (i = 1; i < result.length; i++) {
                                toBeSorted.add(new SortableRow(result[i], j));
                            }
                            Collections.sort(toBeSorted);
                            i = 1;
                            for (SortableRow row : toBeSorted) {
                                result[i] = row.getContent();
                                i++;
                            }
                        }
                    }
                    j++;
                }

                /*
                 * Manage so-called additional data (credit lines...)
                 */
                PositionTree tree = new PositionTree();
                if (result != null) {
                    if (result.length > 1) {
                        //  manage additional data
                        //  credit line and exposures, jump to default
                        ArrayList<Map<Object, List<ColumnValue>>> additionalData = new ArrayList();
                        try {
                            if (additionalReportData != null && !additionalReportData.isEmpty()) {
                                for (TemplateColumnItem templateColumnItem : template.getTemplateColumnItems()) {
                                    HashMap map = new HashMap();
                                    //  these data are on agreg nodes only
                                    if (templateColumnItem.isAggregated()) {
                                        String objectGetter = templateColumnItem.getGetter();
                                        if (templateColumnItem.getGetter().indexOf("/") >= 0) {
                                            objectGetter = templateColumnItem.getGetter().substring(0, templateColumnItem.getGetter().indexOf("/"));
                                        }

                                        List<ReportLine> list = additionalReportData.get(objectGetter);
                                        if (list != null) {
                                            TemplateColumnItem tmp = (TemplateColumnItem) templateColumnItem.clone();
                                            if (tmp.getGetter().indexOf("/") >= 0) {
                                                tmp.setGetter(tmp.getGetter().substring(tmp.getGetter().indexOf("/") + 1));
                                            } else {
                                                tmp.setGetter("getShortName");
                                            }
                                            for (ReportLine additionalReportLine : additionalReportData.get(objectGetter)) {
                                                Object key = ObjectAccessor.fillColumn(tmp, additionalReportLine.getLineData());
                                                ArrayList columnValues = (ArrayList) map.get(key);
                                                if (columnValues == null) {
                                                    columnValues = new ArrayList();
                                                }
                                                columnValues.add(new ColumnValue(additionalReportLine.getId(), ((MeasureValue) additionalReportLine.getObjectMap().values().iterator().next()).getMeasureValue()));
                                                map.put(key, columnValues);

                                            }
                                        }
                                        additionalData.add(map);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            logger.error(StringUtils.formatErrorMessage(e));
                        }

                        //    building of the tree
                        // =========================
                        if (agregationColumnNumber > 0) {
                            root = getTree(agregationColumnNumber, result, headings.size(), additionalData);
                        } else {
                            /**
                             * no aggregation *
                             */
                            root = tree.new AggregNode(null);
                            root.add(tree.new LineNode(result[0]));
                            Object[] topObjects = new Object[result[0].length];
                            topObjects[0] = "TOTAL";
                            AggregNode topAggreg = tree.new AggregNode(topObjects);
                            root.add(topAggreg);
                            for (i = 1; i < result.length; i++) {
                                topAggreg.add(tree.new LineNode(result[i]));
                            }
                        }
                    }
                    /**
                     * aggregations calculations
                     */
                    aggregationsCalculations(root, template.getTemplateColumnItems());
                    /**
                     * exposures calculations
                     */
                    exposuresCalculations(root, template.getTemplateColumnItems());
                }
            } catch (Exception e) {

                logger.error("error  " + StringUtils.formatErrorMessage(e));
            }
        }
        return root;
    }

    public int getAgregationColumnNumber() {
        int number = 0;
        for (TemplateColumnItem templateColumnItem : template.getTemplateColumnItems()) {
            if (templateColumnItem.isAggregated()) {
                number++;
            }
        }
        return number;
    }

    public void fillLine(ReportLine line, PositionTree.PositionNode root, boolean hasDifferentFirstDate, String reportCurrency, Date valueDate, Date startDate, Map mapFXEnd, Map mapFXStart) {
        Object[] result = fillReportRow(line, hasDifferentFirstDate, reportCurrency, valueDate, startDate, mapFXEnd, mapFXStart);
        if (result != null) {
            int numberOflevels = getAgregationColumnNumber();
            for (int i = 0; i < numberOflevels; i++) {
                result[i] = StringUtils.EMPTY_STRING;
            }
            setLine(root, result, line.getId());
        }
    }

    public static void setHiddenObject(Object object, PositionTree.PositionNode root) {
        Object[] data = (Object[]) root.getChildAt(0).getUserObject();
        if (data.length > 0) {
            data[data.length - 1] = object;
            root.getChildAt(0).setUserObject(data);
        }
    }

    public void setLine(PositionTree.PositionNode root, Object[] array, Integer lineId) {
        try {
            if (root.isLeaf() && root.getData() != null) {
                if (root.getData()[root.getData().length - 1] instanceof Integer) {
                    Integer id = (Integer) root.getData()[root.getData().length - 1];
                    if (id.intValue() == lineId.intValue()) {
                        root.setData(array);
                    }
                }
            } else {
                PositionTree.PositionNode node;
                for (int i = 0; i < root.getChildCount(); i++) {
                    node = (PositionTree.PositionNode) root.getChildAt(i);
                    setLine(node, array, lineId);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public Object[] fillReportRow(ReportLine line, boolean hasDifferentFirstDate, String reportCurrency, Date valueDate, Date startDate, Map mapFXEnd, Map mapFXStart) {
        Object[] returnArray = new Object[template.getTemplateColumnItems().size()];
        if (!hasDifferentFirstDate) {
            line.setObjectMapFirst(line.getObjectMap());
            mapFXStart = mapFXEnd;
        }

        int j = 0;
        for (TemplateColumnItem templatecolumnItem : template.getTemplateColumnItems()) {

            Object resultField = null;
            try {
                /**
                 * HERE WE FILL THE FIELDS
                 */
                resultField = fillField(line, templatecolumnItem, valueDate, startDate, mapFXEnd, mapFXStart, reportCurrency, customColumns);

                /**
                 * manage post forex conversion
                 */
                if (templatecolumnItem.isConversion() && !templatecolumnItem.isConversionFirst() && resultField instanceof BigDecimal) {
                    BigDecimal decimal = (BigDecimal) resultField;
                    String currency = getCurrency(line);
                    if (templatecolumnItem.isFirstDate()) {
                        resultField = convertField(reportCurrency, currency, decimal, startDate, mapFXStart);
                    } else {
                        resultField = convertField(reportCurrency, currency, decimal, valueDate, mapFXEnd);
                    }
                }
            } catch (Exception e) {
                logger.error("error  " + StringUtils.formatErrorMessage(e));
            }
            if (resultField != null) {
                returnArray[j] = resultField;
            } else {
                if (resultField instanceof BigDecimal) {
                    returnArray[j] = null;
                } else {
                    returnArray[j] = "";
                }
            }
            j++;
        }
        return returnArray;
    }

    /**
     * Fills an ouput field
     *
     * @param reportLine the report line
     * @param templateColumnItem the template column
     * @param valueDate value date
     * @param startDate start date
     * @param mapFXEnd map of forex rates at value date
     * @param mapFXStart map of forex rates at start date
     * @param reportCurrency the currency of the report
     * @param customColumns
     * @return
     */
    public static Object fillField(ReportLine reportLine, TemplateColumnItem templateColumnItem, Date valueDate, Date startDate, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency, Map<String, CustomColumn> customColumns) {
        Object resultField = null;
        if (templateColumnItem.isEvol()) {
            Object startObject = fillFieldGivenDate(reportLine, templateColumnItem, startDate, true, mapFXStart, reportCurrency);
            Object endObject = fillFieldGivenDate(reportLine, templateColumnItem, valueDate, false, mapFXEnd, reportCurrency);
            if (startObject != null && endObject != null) {
                resultField = ((BigDecimal) endObject).subtract((BigDecimal) startObject);
            } else if (startObject != null) {
                resultField = ((BigDecimal) startObject).negate();
            } else if (endObject != null) {
                resultField = ((BigDecimal) endObject);
            }
        } else if (templateColumnItem.getColumnType().equals(TemplateColumnItem.COLUMN_CUSTOM)) {

            CustomColumn customColumn = customColumns.get(templateColumnItem.getName());
            if (customColumn == null) {
                customColumn = CustomColumnAccessor.getCustomColumnByName(templateColumnItem.getName());
                customColumns.put(templateColumnItem.getName(), customColumn);
            }
            try {
                if (customColumn != null) {
                    Class clazz = Class.forName(customColumn.getClassName());
                    ICustomColumn column = (ICustomColumn) clazz.newInstance();
                    resultField = column.calculate(customColumn, templateColumnItem, reportLine, valueDate, startDate, templateColumnItem.getSuffix(), mapFXEnd, mapFXStart, reportCurrency);
                    if (resultField != null) {
                        switch (templateColumnItem.getSuffix()) {
                            case ReportTemplate.VALUEDATE_SUFFIX:
                                reportLine.getObjectMap().put(templateColumnItem.getName(), resultField);
                                break;
                            case ReportTemplate.FIRSTDATE_SUFFIX:
                                reportLine.getObjectMapFirst().put(templateColumnItem.getName(), resultField);
                                break;
                        }
                    }
                } else {
                    logger.warn("ReportBuilder : custom colum not found : " + templateColumnItem.getName());
                }
                // pre forex conversion
                if (templateColumnItem.isConversion() && templateColumnItem.isConversionFirst() && resultField instanceof BigDecimal) {
                    BigDecimal decimal = (BigDecimal) resultField;
                    String currency = getCurrency(reportLine);
                    if (templateColumnItem.isFirstDate()) {
                        resultField = convertField(reportCurrency, currency, decimal, startDate, mapFXStart);
                    } else {
                        resultField = convertField(reportCurrency, currency, decimal, valueDate, mapFXEnd);
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                logger.error("error  " + StringUtils.formatErrorMessage(e));
            }
        } else if (templateColumnItem.isFirstDate()) {
            resultField = fillFieldGivenDate(reportLine, templateColumnItem, startDate, true, mapFXStart, reportCurrency);
        } else {
            resultField = fillFieldGivenDate(reportLine, templateColumnItem, valueDate, false, mapFXEnd, reportCurrency);
        }
        return resultField;
    }

    /**
     * Fills an ouput field given the date
     *
     * @param reportLine the report line
     * @param templateColumnItem the template column
     * @param date the date
     * @param isFirstDate flags if on the start date
     * @param mapFX map of forex rates
     * @param reportCurrency the currency of the report
     * @return
     */
    public static Object fillFieldGivenDate(ReportLine reportLine, TemplateColumnItem templateColumnItem, Date date, Boolean isFirstDate, Map<String, BigDecimal> mapFX, String reportCurrency) {
        Object resultField;
        Map<String, Object> columns;
        String suffix;
        if (isFirstDate) {
            columns = reportLine.getObjectMapFirst();
            suffix = ReportTemplate.FIRSTDATE_SUFFIX;
        } else {
            columns = reportLine.getObjectMap();
            suffix = ReportTemplate.VALUEDATE_SUFFIX;
        }

        /**
         * looks for value
         *
         */
        boolean found;
        if (StringUtils.isEmptyString(templateColumnItem.getParam()) || templateColumnItem.getColumnType().equals(TemplateColumnItem.COLUMN_MEASURE)) {
            found = columns.containsKey(templateColumnItem.getName());
            resultField = columns.get(templateColumnItem.getName());
        } else {
            found = columns.containsKey(templateColumnItem.getName() + StringUtils.DOT + templateColumnItem.getParam());
            resultField = columns.get(templateColumnItem.getName() + StringUtils.DOT + templateColumnItem.getParam());
        }
        /**
         * if not found and custom => calculate
         *
         */
        if (!found && templateColumnItem.getColumnType().equals(TemplateColumnItem.COLUMN_CUSTOM)) {
            CustomColumn customColumn = CustomColumnAccessor.getCustomColumnByName(templateColumnItem.getName());
            if (customColumn != null) {
                try {
                    Class clazz = Class.forName(customColumn.getClassName());
                    ICustomColumn column = (ICustomColumn) clazz.newInstance();
                    resultField = column.calculate(customColumn, templateColumnItem, reportLine, date, date, suffix, mapFX, mapFX, reportCurrency);
                    columns.put(templateColumnItem.getName(), resultField);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    logger.error("error  " + StringUtils.formatErrorMessage(e));
                }
            } else {
                logger.info("WARNING The Custom Column does not exist: " + templateColumnItem.getName());
            }
        } else if (templateColumnItem.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_STANDARD) && !found
                && !columns.containsKey(templateColumnItem.getName()) && reportLine.getLineData() != null) {
            resultField = ObjectAccessor.fillColumn(templateColumnItem, reportLine.getLineData());
            if (resultField != null) {
                columns.put(templateColumnItem.getName(), resultField);
            }
        }

        /**
         * pre forex conversion
         */
        if (templateColumnItem.isConversion() && templateColumnItem.isConversionFirst() && resultField instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) resultField;
            String currency = getCurrency(reportLine);
            resultField = convertField(reportCurrency, currency, decimal, date, mapFX);
        }
        return resultField;
    }

    /**
     * Fills an ouput field with a custom column
     *
     * @param reportLine the report line
     * @param customColumnDetail the custom column definition
     * @param valueDate value date
     * @param startDate start date
     * @param suffix the column suffix
     * @param mapFXEnd map of forex rates at value date
     * @param mapFXStart map of forex rates at start date
     * @param reportCurrency the currency of the report
     * @param customColumn
     * @return
     */
    public static Object fillFieldWithCustom(ReportLine reportLine, CustomColumnDetail customColumnDetail, Date valueDate, Date startDate, String suffix, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency, CustomColumn customColumn) {
        Object resultField;
        TemplateColumnItem tmp = new TemplateColumnItem();
        tmp.setGetter(customColumnDetail.getGetter());
        tmp.setName(customColumnDetail.getColumnName());
        tmp.setReturnType(customColumnDetail.getCustomColumn().getType());
        tmp.setParam(customColumnDetail.getParam());
        tmp.setColumnType(customColumnDetail.getColumnType());
        if (!customColumnDetail.getSuffix().isEmpty()) {
            tmp.setSuffix(customColumnDetail.getSuffix());
        } else {
            tmp.setSuffix(suffix);
        }
        Map customColumns = new HashMap();
        customColumns.put(customColumn.getName(), customColumn);
        resultField = fillField(reportLine, tmp, valueDate, startDate, mapFXEnd, mapFXStart, reportCurrency, customColumns);
        return resultField;
    }

    /**
     * Calculates aggregations on the output tree
     *
     * @param root the output tree
     * @param templateColumnItems the template columns
     */
    public static void aggregationsCalculations(PositionTree.PositionNode root, Collection<TemplateColumnItem> templateColumnItems) {
        if (root != null) {
            /**
             * aggregations calculations
             */
            int i = 0;
            for (TemplateColumnItem templateColumnItem : templateColumnItems) {
                /**
                 * for each column to aggregate
                 */
                if (templateColumnItem.getAggregation() != null && !templateColumnItem.getAggregation().isEmpty()) {
                    IColumnAggregator aggregator = null;
                    switch (templateColumnItem.getAggregation().trim()) {
                        case "Sum":
                            aggregator = new AggregatorSum();
                            break;
                        case "Avg":
                            aggregator = new AggregatorAverage();
                            break;
                    }
                    if (aggregator != null) {
                        aggregator.getAggregationValue(i, root);
                    }
                }
                i++;
            }
        }
    }

    /**
     * Calculates exposures on the output tree
     *
     * @param root the output tree
     * @param templateColumnItems the template columns
     */
    public static void exposuresCalculations(PositionTree.PositionNode root, Collection<TemplateColumnItem> templateColumnItems) {
        if (root != null) {
            /**
             * aggregations calculations
             */
            int i = 0;
            for (TemplateColumnItem templateColumnItem : templateColumnItems) {
                if (templateColumnItem.isExposure()) {
                    int iAggreg = -1;
                    int j = 0;
                    for (TemplateColumnItem item : templateColumnItems) {
                        if (item.getAggregation() != null
                                && item.getName().equalsIgnoreCase(templateColumnItem.getName())
                                && !item.isExposure()) {
                            iAggreg = j;
                            break;
                        }
                        j++;
                    }
                    // header
                    Object[] objs = (Object[]) root.getChildAt(0).getUserObject();
                    //values
                    if (root.getChildCount() > 1) {
                        if (iAggreg >= 0) {
                            calculateExposures(i, iAggreg, (PositionTree.PositionNode) root.getChildAt(1));
                        } else {
                            logger.error("Problem calculating exposures on " + templateColumnItem.getName() + "  : column not found");
                        }
                    }
                }
                i++;
            }
        }
    }

    public static void calculateExposures(int iExposure, int iAggregations, PositionTree.PositionNode node) {
        BigDecimal ret = BigDecimal.ONE;
        Object[] objs = (Object[]) node.getUserObject();
        if (objs != null && objs[iAggregations] instanceof BigDecimal) {
            ret = (BigDecimal) objs[iAggregations];
        }
        BigDecimal aggreg = BigDecimal.ZERO;
        if (node.getParent() != null) {
            if (node.getParent().getUserObject() != null) {
                Object[] objs_ = (Object[]) node.getParent().getUserObject();
                if (objs_[iAggregations] instanceof BigDecimal) {
                    aggreg = (BigDecimal) objs_[iAggregations];
                }
            }
        }
        if (aggreg.doubleValue() != 0) {
            objs[iExposure] = ret.divide(aggreg, 20, RoundingMode.HALF_UP).multiply(NumberUtils.BIGDECIMAL_100);
        } else if (objs != null) {
            objs[iExposure] = NumberUtils.BIGDECIMAL_100;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            calculateExposures(iExposure, iAggregations, (PositionTree.PositionNode) node.getChildAt(i));
        }
    }

    /**
     * Builds output exposures nodes out of the result object array
     *
     * @param agregationColumnNumber
     * @param resultArray the result object array
     * @param additionalData
     * @param columnIndex the index of the column
     * @return
     */
    public static PositionTree.AggregNode getTree(int agregationColumnNumber, Object[][] resultArray, int columnIndex, ArrayList<Map<Object, List<ColumnValue>>> additionalData) {

        PositionTree tree = new PositionTree();
        PositionTree.AggregNode root = tree.new AggregNode(null);

        /**
         * the first node is the header
         */
        PositionTree.AggregNode[] currentAggregNode = new PositionTree.AggregNode[agregationColumnNumber + 1];
        Object[] nodearray = resultArray[0].clone();
        currentAggregNode[0] = tree.new AggregNode(nodearray);
        root.add(currentAggregNode[0]);

        /**
         * build top node
         */
        nodearray = new Object[columnIndex];
        currentAggregNode[0] = tree.new AggregNode(nodearray);
        root.add(currentAggregNode[0]);
        nodearray[0] = "TOTAL";
        Object[] curlinearray;
        /**
         * main loop
         */
        PositionTree.LineNode lnode;
        for (int i = 1; i < resultArray.length; i++) {
            curlinearray = resultArray[i].clone();
            int level = 1;
            while (level <= agregationColumnNumber) {
                if (!resultArray[i][level - 1].equals(resultArray[i - 1][level - 1])) {
                    nodearray = new Object[columnIndex];
                    nodearray[level - 1] = resultArray[i][level - 1];
                    currentAggregNode[level] = tree.new AggregNode(nodearray);
                    currentAggregNode[level - 1].add(currentAggregNode[level]);
                    curlinearray[level - 1] = StringUtils.EMPTY_STRING;

                    if (additionalData.size() >= level) {
                        List<ColumnValue> columnValues = (List<ColumnValue>) additionalData.get(level - 1).get(nodearray[level - 1]);
                        if (columnValues != null) {
                            for (ColumnValue columnValue : columnValues) {
                                nodearray[columnValue.column] = columnValue.value;
                            }
                        }
                    }
                }
                curlinearray[level - 1] = StringUtils.EMPTY_STRING;
                level++;
            }
            lnode = tree.new LineNode(curlinearray);
            currentAggregNode[agregationColumnNumber].add(lnode);
        }

        return root;
    }

    /**
     * Orders existing columns
     *
     * @param templateColumnItems the template columns
     * @return
     */
    public static ArrayList<TemplateColumnItem> orderColumns(Collection<TemplateColumnItem> templateColumnItems) {
        ArrayList<TemplateColumnItem> temp = new ArrayList();
        /**
         * first add aggregated
         *
         */
        for (TemplateColumnItem templateColumnItem : templateColumnItems) {
            if (templateColumnItem.isAggregated()) {
                temp.add(templateColumnItem);
            }
        }
        /**
         * then not aggregated
         *
         */
        for (TemplateColumnItem templateColumnItem : templateColumnItems) {
            if (!templateColumnItem.isAggregated()) {
                temp.add(templateColumnItem);
            }
        }
        return temp;
    }

    /**
     * Adds the id column to the column list to allow object access
     *
     * @param columns
     * @param objectType the object type
     */
    public static void addIdColumn(Collection<TemplateColumnItem> columns, Class objectType) {
        String type = objectType.getSimpleName();
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        templateColumnItem.setGetter("get" + type + "Id");
        templateColumnItem.setIsAggregated(Boolean.FALSE);
        templateColumnItem.setReturnType(Integer.class.toString());
        templateColumnItem.setDisplayName(type + " Id");
        templateColumnItem.setColumnIndex(columns.size());
        templateColumnItem.setName(type + " Id");
        templateColumnItem.setParam(StringUtils.EMPTY_STRING);
        ArrayList columns_ = (ArrayList) columns;
        if (columns_.size() <= 1 || !columns_.get(columns_.size() - 1).equals(templateColumnItem)) {
            columns.add(templateColumnItem);
        }
    }

    /**
     * Adds the currency column to the column list to allow fx conversion
     *
     * @param columns
     */
    public static void addProductCurrencyColumn(Collection<TemplateColumnItem> columns) {
        for (TemplateColumnItem item : columns) {
            if (item.getName().equalsIgnoreCase("Product.NotionalCurrency")) {
                return;
            }
        }
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        templateColumnItem.setGetter("getProduct/getNotionalCurrency");
        templateColumnItem.setIsAggregated(Boolean.FALSE);
        templateColumnItem.setReturnType(String.class.toString());
        templateColumnItem.setDisplayName("Currency");
        templateColumnItem.setColumnIndex(columns.size());
        templateColumnItem.setName("Product.NotionalCurrency");
        templateColumnItem.setParam(StringUtils.EMPTY_STRING);
        templateColumnItem.isToBeRemoved = true;
        columns.add(templateColumnItem);
    }

    /**
     * Adds the fund portfolio id column to the column list to allow look through
     *
     * @param columns
     */
    public static void addProductEquityPortfolioIdColumn(Collection<TemplateColumnItem> columns) {
        for (TemplateColumnItem item : columns) {
            if (item.getName().equalsIgnoreCase("Product.ProductEquity.Portfolio.LegalEntityId")) {
                return;
            }
        }
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        templateColumnItem.setGetter("getProduct/getProductEquity/getPortfolio/getLegalEntityId");
        templateColumnItem.setIsAggregated(Boolean.FALSE);
        templateColumnItem.setReturnType(Integer.class.toString());
        templateColumnItem.setDisplayName("Portfolio Id");
        templateColumnItem.setColumnIndex(columns.size());
        templateColumnItem.setName("Product.ProductEquity.Portfolio.LegalEntityId");
        templateColumnItem.setParam(StringUtils.EMPTY_STRING);
        templateColumnItem.isToBeRemoved = true;
        columns.add(templateColumnItem);
    }

    /**
     * Adds the fund portfolio id column to the column list to allow look through
     *
     * @param columns
     */
    public static void addProductIdColumn(Collection<TemplateColumnItem> columns) {

        for (TemplateColumnItem item : columns) {
            if (item.getName().equalsIgnoreCase("Product.ProductId")) {
                return;
            }
        }
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        templateColumnItem.setGetter("getProduct/getProductId");
        templateColumnItem.setIsAggregated(Boolean.FALSE);
        templateColumnItem.setReturnType(Integer.class.toString());
        templateColumnItem.setDisplayName("Product Id");
        templateColumnItem.setColumnIndex(columns.size());
        templateColumnItem.setName("Product.ProductId");
        templateColumnItem.setParam(StringUtils.EMPTY_STRING);
        templateColumnItem.isToBeRemoved = true;
        columns.add(templateColumnItem);
    }

    /**
     * Adds the fund portfolio id column to the column list to allow look through
     *
     * @param columns
     */
    public static void addProductFakeStockTypeReference(Collection<TemplateColumnItem> columns) {

        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        templateColumnItem.setGetter("getProduct/getProductReference");
        templateColumnItem.setIsAggregated(Boolean.FALSE);
        templateColumnItem.setReturnType(Integer.class.toString());
        templateColumnItem.setDisplayName("Product Reference Stock type");
        templateColumnItem.setColumnIndex(columns.size());
        templateColumnItem.setName("Product.ProductReference");
        templateColumnItem.setParam("FakeStockType");
        templateColumnItem.isToBeRemoved = true;
        columns.add(templateColumnItem);
    }

    /**
     * Adds the column name to the report header
     *
     * @param templateColumnItem the template column
     * @param headings the header
     */
    public static void addColumnToHeader(TemplateColumnItem templateColumnItem, ArrayList<String> headings) {
        String display;
        if (templateColumnItem.getDisplayName() != null && !templateColumnItem.getDisplayName().isEmpty()) {
            display = templateColumnItem.getDisplayName();
        } else {
            display = templateColumnItem.getName();
            String suffix = templateColumnItem.getSuffix();
            if (templateColumnItem.isConversion() != null && templateColumnItem.isConversion()) {
                if (templateColumnItem.isConversionFirst()) {
                    suffix = ReportTemplate.CONVERSION_SUFFIX + suffix;
                } else {
                    suffix += ReportTemplate.CONVERSION_SUFFIX;
                }
            }
            display += suffix;
        }
        headings.add(display);
    }

    public PricingMaps getMaps() {
        return maps;
    }

    public Class getObjectClass() {
        return template.getObjectTypeClass();
    }

    public class SortableRow implements Comparable {

        private final Object[] content;
        private final int colToSortBy;

        public SortableRow(Object[] content, int colToSortBy) {
            this.content = content;
            this.colToSortBy = colToSortBy;
        }

        @Override
        public int compareTo(Object o) {
            Object[] comparedTo = ((SortableRow) o).getContent();
            if (colToSortBy == 1 && !content[colToSortBy - 1].equals(comparedTo[colToSortBy - 1])) {
                return 0;
            } else if (colToSortBy == 2 && (!content[colToSortBy - 1].equals(comparedTo[colToSortBy - 1]) || !content[colToSortBy - 2].equals(comparedTo[colToSortBy - 2]))) {
                return 0;
            } else {
                if (content[colToSortBy] instanceof BigDecimal) {
                    return Double.compare(((BigDecimal) content[colToSortBy]).doubleValue(), ((BigDecimal) comparedTo[colToSortBy]).doubleValue());
                } else if (content[colToSortBy] instanceof String) {
                    return ((String) content[colToSortBy]).compareTo((String) comparedTo[colToSortBy]);
                } else if (content[colToSortBy] instanceof Integer) {
                    return ((Integer) content[colToSortBy]).compareTo((Integer) comparedTo[colToSortBy]);
                } else if (content[colToSortBy] instanceof Date) {
                    return ((Date) content[colToSortBy]).compareTo((Date) comparedTo[colToSortBy]);
                }
            }
            return 0;
        }

        public Object[] getContent() {
            return content;
        }
    }
}
