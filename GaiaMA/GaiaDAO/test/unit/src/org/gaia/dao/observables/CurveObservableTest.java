package org.gaia.dao.observables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.trades.Product;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */

/**
 *
 * @author Benjamin Frerejean
 */
public class CurveObservableTest extends AbstractTest{

    CurveObservable instance = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE,AbstractObservable.DISCOUNTING);
    Date maturity=DateUtils.addYear(DateUtils.getDate(), 1);

    public CurveObservableTest() {
        instance.setProduct(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name));
        instance.fillData(DateUtils.getDate(),  MarketQuoteAccessor.getDefaultQuoteSet());
    }

    /**
     * Test of fillData method, of class CurveObservable.
     */
    @Test
    public void testFillData() {
        System.out.println("fillData");
        instance.fillData(DateUtils.getDate(),  MarketQuoteAccessor.getDefaultQuoteSet());
        assertTrue("failed to fill market quote", instance.getQuotes().size()>0);
    }

    /**
     * Test of replaceQuote method, of class CurveObservable.
     */
    @Test
    public void testReplaceQuote() {
        Double last = Double.MIN_NORMAL;
        instance.replaceQuote(instance.getProduct().getUnderlying().getProductId(), last);
        assertTrue("failed to replace market quote", instance.getQuotes().iterator().next().getClose().doubleValue()==last.doubleValue());
    }

    /**
     * Test of getObservableValue method, of class CurveObservable.
     */
    @Test
    public void testGetObservableValue() {
        System.out.println("getObservableValue");
        BigDecimal result = instance.getObservableValue(new Object[]{maturity});
        assertNotNull("null rate", result);
    }

    /**
     * Test of getCoordinateClass method, of class CurveObservable.
     */
    @Test
    public void testGetCoordinateClass() {
        System.out.println("getCoordinateClass");
        Class[] result = instance.getCoordinateClass();
        assertEquals("should be a date", result[0],Date.class);
    }

    /**
     * Test of getObservableValueFromPriceable method, of class CurveObservable.
     */
    @Test
    public void testGetObservableValueFromPriceable() {
        System.out.println("getObservableValueFromPriceable");
        Product priceable = new Product();
        priceable.setMaturityDate(maturity);
        BigDecimal result = instance.getObservableValueFromPriceable(priceable);
        assertNotNull("null rate", result);
    }

    /**
     * Test of getUnderlyings method, of class CurveObservable.
     */
    @Test
    public void testGetUnderlyings() {
        System.out.println("getUnderlyings");
        List<Product> result = instance.getUnderlyings();
        assertFalse("empty underlyings", result.isEmpty());
    }

    /**
     * Test of setUnderlyings method, of class CurveObservable.
     */
    @Test
    public void testSetUnderlyings() {
        System.out.println("setUnderlyings");
        ArrayList<Product> underlyings = new ArrayList(instance.getUnderlyings());
        instance.setUnderlyings(underlyings);
        assertFalse("empty underlyings", instance.getUnderlyings().isEmpty());
    }

    /**
     * Test of getQuotes method, of class CurveObservable.
     */
    @Test
    public void testGetQuotes() {
        System.out.println("getQuotes");
        assertFalse("empty underlyings", instance.getQuotes().isEmpty());
    }

    /**
     * Test of setQuotes method, of class CurveObservable.
     */
    @Test
    public void testSetQuotes() {
        System.out.println("setQuotes");
        List<MarketQuote> quotes = instance.getQuotes();
        instance.setQuotes(quotes);
        assertFalse("empty underlyings", instance.getQuotes().isEmpty());
    }

    /**
     * Test of shift method, of class CurveObservable.
     */
    @Test
    public void testShift() {
        System.out.println("shift");
        List<ObservableShift> shifts = new ArrayList();
        ObservableShift shift=new ObservableShift();
        shift.setShift(BigDecimal.ONE);
        shifts.add(shift);
        IObservable result = instance.shift(shifts, true);
        assertTrue("bad shift", ((CurveObservable)result).getQuotes().iterator().next().getClose().doubleValue()==instance.getQuotes().iterator().next().getClose().doubleValue()+1);
    }

    /**
     * Test of getProduct method, of class CurveObservable.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        Product result = instance.getProduct();
        assertEquals(instance.getProduct(), result);
    }

    /**
     * Test of clone method, of class CurveObservable.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Object result = instance.clone();
        assertEquals(instance, result);
    }

    /**
     * Test of generateZeroCouponCurve method, of class CurveObservable.
     */
    @Test
    public void testGenerateZeroCouponCurve_0args() {
        System.out.println("generateZeroCouponCurve");
        instance.generateZeroCouponCurve();
        assertNotNull("problem generating ZC curve", instance.getZCCurveDates());
        assertFalse("problem generating ZC curve", instance.getZCCurveDates().isEmpty());
    }

    /**
     * Test of generateZeroCouponCurve method, of class CurveObservable.
     */
    @Test
    public void testGenerateZeroCouponCurve_String() {
        System.out.println("generateZeroCouponCurve");
        instance.generateZeroCouponCurve("ACT/365");
        assertNotNull("problem generating ZC curve", instance.getZCCurveDates());
        assertFalse("problem generating ZC curve", instance.getZCCurveDates().isEmpty());
    }

    /**
     * Test of getZCCurveBaseDate method, of class CurveObservable.
     */
    @Test
    public void testGetZCCurveBaseDate() {
        System.out.println("getZCCurveBaseDate");
        Date result = instance.getZCCurveBaseDate();
        assertNotNull("missing date", result);
    }

    /**
     * Test of getZCCurveDates method, of class CurveObservable.
     */
    @Test
    public void testGetZCCurveDates() {
        System.out.println("getZCCurveDates");
        instance.generateZeroCouponCurve();
        ArrayList<Date> result = instance.getZCCurveDates();
        assertNotNull("missing zc dates", result);
        assertFalse("missing zc dates", result.isEmpty());
    }

    /**
     * Test of getZCCurveRates method, of class CurveObservable.
     */
    @Test
    public void testGetZCCurveRates() {
        System.out.println("getZCCurveRates");
        instance.generateZeroCouponCurve();
        ArrayList<Double> result = instance.getZCCurveRates();
        assertTrue("missing zc rates", result.size()>0);
    }

    /**
     * Test of getZCCurveDiscountFactors method, of class CurveObservable.
     */
    @Test
    public void testGetZCCurveDiscountFactors() {
        System.out.println("getZCCurveDiscountFactors");
        instance.generateZeroCouponCurve();
        ArrayList<Double> result = instance.getZCCurveDiscountFactors();
        assertTrue("missing zc rates", result.size()>0);
    }

    /**
     * Test of getZeroPrice method, of class CurveObservable.
     */
    @Test
    public void testGetZeroPrice() {
        System.out.println("getZeroPrice");
        BigDecimal result = instance.getZeroPrice(maturity);
        assertTrue("missing zc rates", result.doubleValue()>0);
    }

    /**
     * Test of getDiscountFactor method, of class CurveObservable.
     */
    @Test
    public void testGetDiscountFactor() {
        System.out.println("getDiscountFactor");
        BigDecimal result = instance.getDiscountFactor(maturity);
        assertTrue("missing DF", result.doubleValue()>0);
    }

}
