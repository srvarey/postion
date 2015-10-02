package org.gaia.dao.pricing.pricers.isda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.gaia.dao.jade.PricingSetting;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.DAOGreekSetting;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.NPVArrayDimension;
import org.gaia.dao.pricing.PricingBuilder;
import static org.gaia.dao.pricing.PricingBuilder.cleanGreekList;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jawhar Kamoun
 */
public class IsdaPricerCallerTest extends AbstractTest {

    public IsdaPricerCallerTest() {
    }
    Trade priceable;
    Date valuationDate;
    PricingEnvironment pricingEnvironment;
    MeasuresAccessor.MeasureGroup[] groups;
    Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap;
    Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap;
    PricingSetting pricingSettings;
    List<DAOGreekSetting> greeksList;
    List<NPVArrayDimension> npvArrayDimensions;
    Map<Integer, IObservable> observablesMap;
    CurveObservable ircurve = null;
    CurveObservable recoverycurve = null;
    CurveObservable creditcurve = null;
    double recoveryRate = 0.4;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        priceable = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        valuationDate = DateUtils.getDate();

        pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();

        groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, groups);
        groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

        Class objectType = ReportUtils.getObjectType(Position.class.getSimpleName());
        ReportTemplate template = ReportTemplateAccessor.getReportTemplateByNameAndType("default", objectType);
        Collection<TemplateColumnItem> templateColumnItems = template.getTemplateColumnItems();
        pricingSettings = PricingBuilder.buildPricingSetting(templateColumnItems, pricingEnvironment, valuationDate, false);

        greeksList = pricingSettings.getGreeksList();
        npvArrayDimensions = new ArrayList();
        observablesMap = groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
        cleanGreekList(pricingSettings, observablesMap, npvArrayDimensions, greeksList);

        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
            for (IObservable observable : observables.values()) {
                observable.fillData(valuationDate, quoteSet);
            }
        }

        for (IObservable obs : observablesMap.values()) {
            if (obs instanceof CurveObservable) {
                CurveObservable curve = (CurveObservable) obs;
                if (curve.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                    ircurve = curve;
                } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.RECOVERY_CURVE)) {
                    recoverycurve = curve;
                } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.CREDIT_CURVE)) {
                    creditcurve = curve;
                }
            }
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCDSNPV method, of class IsdaPricerCaller.
     */
    @Test
    public void testGetCDSNPV() {
        System.out.println("getCDSNPV");
        double result = IsdaPricerCaller.getCDSNPV(priceable, ircurve, creditcurve, recoveryRate, valuationDate);
        assertNotNull("CDSNPV could Not be null", result);
    }

    /**
     * Test of getCDSProtectionLegNPV method, of class IsdaPricerCaller.
     */
    @Test
    public void testGetCDSProtectionLegNPV() {
        System.out.println("getCDSProtectionLegNPV");
        double result = IsdaPricerCaller.getCDSProtectionLegNPV(priceable, ircurve, creditcurve, recoveryRate, valuationDate);
        assertNotNull("CDSProtectionLegNPV could Not be null", result);
    }

    /**
     * Test of getCDSFeeLegNPV method, of class IsdaPricerCaller.
     */
    @Test
    public void testGetCDSFeeLegNPV() {
        System.out.println("getCDSFeeLegNPV");

        double result = IsdaPricerCaller.getCDSFeeLegNPV(priceable, ircurve, creditcurve, valuationDate);
        assertNotNull("CDSFeeLegNPV could Not be null", result);
    }

    /**
     * Test of getSurvivalProbability method, of class IsdaPricerCaller.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");

        double result = IsdaPricerCaller.getSurvivalProbability(valuationDate, creditcurve);
        assertNotNull("Probability could Not be null", result);
    }

}
