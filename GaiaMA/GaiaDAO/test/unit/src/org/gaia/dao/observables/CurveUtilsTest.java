package org.gaia.dao.observables;

import java.math.BigDecimal;
import java.util.Date;
import org.gaia.dao.pricing.pricers.isda.IsdaCurve;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class CurveUtilsTest extends AbstractTest {
    Date date = DateUtils.getDate();
    String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();


    public CurveUtilsTest() {
    }

    /**
     * Test of fillCurveObservable method, of class CurveUtils.
     */
    @Test
    public void testFillCurveObservable() {
        System.out.println("fillCurveObservable");
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE,AbstractObservable.DISCOUNTING);
        curve.setProduct(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name));
        CurveUtils.fillCurveObservable(curve, date, quoteSet);
        assertNotNull("curve not filled", curve.getQuotes());
    }

    /**
     * Test of generateCurve method, of class CurveUtils.
     */
    @Test
    public void testGenerateCurve_Product_Date() {
        System.out.println("generateCurve");
        CurveObservable result = CurveUtils.generateCurve(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name), date);
        assertNotNull("curve not generated", result);
    }

    /**
     * Test of generateCurve method, of class CurveUtils.
     */
    @Test
    public void testGenerateCurve_CurveObservable_Date() {
        System.out.println("generateCurve");
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE,AbstractObservable.DISCOUNTING);
        curve.setProduct(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name));
        CurveObservable result = CurveUtils.generateCurve(curve, date);
        assertNotNull("curve not generated", result);
    }

    /**
     * Test of generateIsdaCurve method, of class CurveUtils.
     */
    @Test
    public void testGenerateIsdaCurve() {
        System.out.println("generateIsdaCurve");
        IsdaCurve result = CurveUtils.generateIsdaCurve(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name), date);
        assertNotNull("isda curve not generated", result);
    }

    /**
     * Test of loadCurve method, of class CurveUtils.
     */
    @Test
    public void testLoadCurve() {
        System.out.println("loadCurve");
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE,AbstractObservable.DISCOUNTING);
        curve.setProduct(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name));
        CurveObservable result = CurveUtils.loadCurve(curve.getProductId(), date);
        assertNotNull("curve not loaded", result);
    }

    /**
     * Test of getDefaultRate method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultRate() {
        System.out.println("getDefaultRate");
        BigDecimal result = CurveUtils.getDefaultRate("USD", DateUtils.addYear(date, 1));
        assertNotNull("curve rate not loaded", result);
    }

    /**
     * Test of getDefaultIRCurve method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultIRCurve() {
        System.out.println("getDefaultIRCurve");
        CurveObservable result = CurveUtils.getDefaultIRCurve("USD");
        assertNotNull("usd curve not found", result);
    }

    /**
     * Test of getDefaultDiscountFactor method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultDiscountFactor() {
        System.out.println("getDefaultDiscountFactor");
        BigDecimal result = CurveUtils.getDefaultDiscountFactor("USD", DateUtils.addYear(date, 1));
        assertNotNull("usd discount factor not found", result);
    }

    /**
     * Test of getDefaultZeroPrice method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultZeroPrice() {
        System.out.println("getDefaultZeroPrice");
        BigDecimal result = CurveUtils.getDefaultZeroPrice("USD", DateUtils.addYear(date, 1));
        assertNotNull("usd zero price not found", result);
    }

    /**
     * Test of getDefaultForwardPoints method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultForwardPoints() {
        System.out.println("getDefaultForwardPoints");
        BigDecimal result = CurveUtils.getDefaultForwardPoints("EUR", "USD", DateUtils.addYear(date, 1));
        assertNotNull("fx fwd curve not found", result);
    }

    /**
     * Test of getDefaultFxForwardCurve method, of class CurveUtils.
     */
    @Test
    public void testGetDefaultFxForwardCurve() {
        System.out.println("getDefaultFxForwardCurve");
        CurveObservable result = CurveUtils.getDefaultFxForwardCurve("EUR", "USD");
        assertNotNull("fx fwd curve not found", result);
    }

}
