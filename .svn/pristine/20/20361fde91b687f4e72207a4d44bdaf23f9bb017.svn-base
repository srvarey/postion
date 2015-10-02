package org.gaia.dao.pricing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.jade.PricingSetting;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import static org.gaia.dao.pricing.PricingBuilder.buildEmptyArray;
import static org.gaia.dao.pricing.PricingBuilder.cleanGreekList;
import static org.gaia.dao.pricing.PricingBuilder.cleanNPVArray;
import static org.gaia.dao.pricing.PricingBuilder.getGreekSettingCoordinates;
import static org.gaia.dao.pricing.PricingBuilder.getShifts;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jawhar Kamoun
 */
public class PricingBuilderTest extends AbstractTest {

    public PricingBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadPricerMap method, of class PricingBuilder.
     */
    @Test
    public void testLoadPricerMap() {
        System.out.println("loadPricerMap");
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> result = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        assertNotNull("Pricer could Not be null", result);
    }

    /**
     * Test of getObservables method, of class PricingBuilder.
     */
    @Test
    public void testGetObservables() {
        System.out.println("getObservables");
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> result = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);
        assertNotNull("Observables could Not be null", result);
    }

    /**
     * Test of buildPricingSetting method, of class PricingBuilder.
     */
    @Test
    public void testBuildPricingSetting() {
        System.out.println("buildPricingSetting");
        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        String defaultTemplatName = ReportUtils.getDefaultTemplate(Position.class.getSimpleName(), 0);
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType(defaultTemplatName, objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        Date valueDate = DateUtils.getDate();
        boolean isRealTime = false;
        PricingSetting result = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valueDate, isRealTime);
        assertNotNull("Pricing Setting could Not be null", result);
    }

    /**
     * Test of pricePriceable method, of class PricingBuilder.
     */
    @Test
    public void testPricePriceable() {
        System.out.println("pricePriceable");

        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);
        boolean isRealTime = false;
        Class objectType = ReportUtils.getObjectType(Trade.class.getSimpleName());
        String defaultTemplatName = ReportUtils.getDefaultTemplate(Trade.class.getSimpleName(), 0);
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType(defaultTemplatName, objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, isRealTime);
        Map<String, Map<Integer, String>> measures2DUnderlyingIds = null;

        Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> result = PricingBuilder.pricePriceable(priceable, groupObservablesMap, valuationDate, pricersMap, measures2DUnderlyingIds, pricingSettings);
        assertNotNull("Pricing Result could Not be null", result);
    }

    /**
     * Test of fillNPVArray method, of class PricingBuilder.
     */
    @Test
    public void testFillNPVArray() {
        System.out.println("fillNPVArray");

        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
        IPricer pricer = pricersMap.entrySet().iterator().next().getValue();

        int level = npvArrayDimensions.size();
        boolean isShifted = false;
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }
        PricingBuilder.fillNPVArray(observablesMap, npvArray, npvArrayDimensions, pricer, priceable, valuationDate, level, isShifted);
        Serializable[] array = (Serializable[]) npvArray;
        BigDecimal result = ((List<ObservableShift>) array[1]).get(0).getShiftResult();
        assertNotNull("ShiftResult could Not be null", result);
    }

    /**
     * Test of buildEmptyArray method, of class PricingBuilder.
     */
    @Test
    public void testBuildEmptyArray() {
        System.out.println("buildEmptyArray");
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList<>();
        NPVArrayDimension dimension = new NPVArrayDimension();
        npvArrayDimensions.add(dimension);

        Serializable[] result = PricingBuilder.buildEmptyArray(npvArrayDimensions);
        assertNotNull("ShiftResult could Not be null", result);

    }

    /**
     * Test of cleanNPVArray method, of class PricingBuilder.
     */
    @Test
    public void testCleanNPVArray() {
        System.out.println("cleanNPVArray");
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();

        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);

        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());

        Serializable result = PricingBuilder.cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
        assertNotNull("ShiftResult could Not be null", result);
    }

    /**
     * Test of copyArrayValue method, of class PricingBuilder.
     */
    @Test
    public void testCopyArrayValue() {
        System.out.println("copyArrayValue");
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();

        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);

        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        Serializable[] newArray = buildEmptyArray(npvArrayDimensions);
        if (!greeksList.isEmpty()) {
            DAOGreekSetting greekSetting = greeksList.get(0);
            int[] coordinates = getGreekSettingCoordinates(greekSetting, npvArrayDimensions);
            if (!greekSetting.getMovingPricerMeasure().equals(MeasuresAccessor.Measure.NPV)
                    && !greekSetting.getShifted().equalsIgnoreCase(greekSetting.getMovingGreekSetting().getShifted())) {
                int[] subcoordinates = getGreekSettingCoordinates(greekSetting.getDaoMovingGreekSetting(), npvArrayDimensions);
                for (int i = 0; i < coordinates.length; i++) {
                    coordinates[i] += subcoordinates[i];
                }
            }
            PricingBuilder.copyArrayValue(coordinates, npvArray, newArray, npvArrayDimensions.size() - 1, npvArrayDimensions);
        }

        assertNotNull("ShiftResult could Not be null", newArray);
        assertArrayEquals((Object[]) npvArray, newArray);
    }

    /**
     * Test of getShifts method, of class PricingBuilder.
     */
    @Test
    public void testGetShifts() {
        System.out.println("getShifts");

        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);

        List<ObservableShift> result = PricingBuilder.getShifts(npvArray, npvArrayDimensions);
        assertNotNull("ShiftResult could Not be null", result);
    }

    /**
     * Test of shiftObservables method, of class PricingBuilder.
     */
    @Test
    public void testShiftObservables() {
        System.out.println("shiftObservables");

        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);

        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }
        NPVArrayDimension npvArrayDimension = npvArrayDimensions.get(0);
        Serializable[] array = (Serializable[]) npvArray;
        Map<Integer, IObservable> result = null;
        if (array[1] != null) {
            List<ObservableShift> shifts = getShifts(array, npvArrayDimensions);
            if (1 != npvArrayDimension.getMiddle()) {
                result = PricingBuilder.shiftObservables(shifts, npvArrayDimension.isAbsolute(), observablesMap, npvArrayDimension);
            }
        }
        assertNotNull("ShiftResult could Not be null", result);
    }

    /**
     * Test of calculateGreek method, of class PricingBuilder.
     */
    @Test
    public void testCalculateGreek() {
        System.out.println("calculateGreek");
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
        IPricer pricer = pricersMap.entrySet().iterator().next().getValue();

        int level = npvArrayDimensions.size();
        boolean isShifted = false;
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }
        PricingBuilder.fillNPVArray(observablesMap, npvArray, npvArrayDimensions, pricer, priceable, valuationDate, level, isShifted);
        Map<DAOGreekSetting, MeasureValue> greekValuesList = new HashMap();
        Double result = null;
        if (!greeksList.isEmpty()) {
            DAOGreekSetting greekSetting = greeksList.get(0);
            Map<IPricerMeasure, MeasureValue[]> pricerResultMap = pricer.calculate(priceable, observablesMap, valuationDate, true);
            int[] coordinates = getGreekSettingCoordinates(greekSetting, npvArrayDimensions);
            MeasureValue[] npvValues = pricerResultMap.get(MeasuresAccessor.Measure.NPV_unit);
            if (npvValues != null) {
                MeasureValue mvNPV = npvValues[0];
                Double npv = null;
                if (mvNPV.getMeasureValue() != null) {
                    npv = mvNPV.getMeasureValue().doubleValue();
                }
                if (!pricingSettings.getNpvArrayDimensions().isEmpty() && npv != null && observablesMap != null) {
                    result = PricingBuilder.calculateGreek(npv, npvArray, greekSetting, coordinates, npvArrayDimensions, greekValuesList, npv);
                }
            }
        }
        assertNotNull("Greek Value could Not be null", result);

    }

    /**
     * Test of getPointFromNPVArray method, of class PricingBuilder.
     */
    @Test
    public void testGetPointFromNPVArray() {
        System.out.println("getPointFromNPVArray");
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
        IPricer pricer = pricersMap.entrySet().iterator().next().getValue();

        int level = npvArrayDimensions.size();
        boolean isShifted = false;
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }
        PricingBuilder.fillNPVArray(observablesMap, npvArray, npvArrayDimensions, pricer, priceable, valuationDate, level, isShifted);

        BigDecimal result = null;
        if (!greeksList.isEmpty()) {
            DAOGreekSetting greekSetting = greeksList.get(0);
            int[] coordinates = getGreekSettingCoordinates(greekSetting, npvArrayDimensions);
            if (!pricingSettings.getNpvArrayDimensions().isEmpty()) {
                result = PricingBuilder.getPointFromNPVArray(npvArray, coordinates, npvArrayDimensions, level - 1);
            }

        }
        assertNotNull("PointFromNPVArray could Not be null", result);
    }

    /**
     * Test of getGreekSettingCoordinates method, of class PricingBuilder.
     */
    @Test
    public void testGetGreekSettingCoordinates() {
        System.out.println("getGreekSettingCoordinates");
        int[] result = null;
        IPriceable priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valuationDate = DateUtils.getDate();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        PricingSetting pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
        Map<Integer, IObservable> observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);
        // now build the sub array with them
        Serializable npvArray = PricingBuilder.buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
        // clean array to avoid useless calulations
        npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
        IPricer pricer = pricersMap.entrySet().iterator().next().getValue();

        int level = npvArrayDimensions.size();
        boolean isShifted = false;
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }
        PricingBuilder.fillNPVArray(observablesMap, npvArray, npvArrayDimensions, pricer, priceable, valuationDate, level, isShifted);

        if (!greeksList.isEmpty()) {
            DAOGreekSetting greekSetting = greeksList.get(0);
            result = PricingBuilder.getGreekSettingCoordinates(greekSetting, npvArrayDimensions);
        }
        assertNotNull("GreekSettingCoordinates could Not be null", result);
    }

    /**
     * Test of getTradePricing method, of class PricingBuilder.
     */
    @Test
    public void testGetTradePricing() {
        System.out.println("getTradePricing");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valDate = DateUtils.getDate();
        String pricingEnvironmentName = PricingEnvironmentAccessor.getDefaultPricingEnvironment().getName();
        Map<String, BigDecimal> result = PricingBuilder.getTradePricing(trade, valDate, pricingEnvironmentName, null, null);
        assertNotNull("TradePricing could Not be null", result);
    }

    /**
     * Test of getUpfront method, of class PricingBuilder.
     */
    @Test
    public void testGetUpfront() {
        System.out.println("getUpfront");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.loadEquities().get(0).getName());
        Date valDate = DateUtils.getDate();
        String pricingEnvironmentName = PricingEnvironmentAccessor.getDefaultPricingEnvironment().getName();
        Double result = PricingBuilder.getUpfront(trade, valDate, pricingEnvironmentName);
        assertNotNull("Upfront could Not be null", result);
    }

    /**
     * Test of getCDSSpread method, of class PricingBuilder.
     */
    @Test
    public void testGetCDSSpread() {
        System.out.println("getCDSSpread");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        Date valDate = DateUtils.getDate();
        String pricingEnvironmentName = PricingEnvironmentAccessor.getDefaultPricingEnvironment().getName();
        Double result = PricingBuilder.getCDSSpread(trade, valDate, pricingEnvironmentName);
        assertNotNull("CDSSpread could Not be null", result);
    }

}
