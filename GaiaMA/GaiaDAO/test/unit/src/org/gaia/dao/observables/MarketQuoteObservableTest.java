
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class MarketQuoteObservableTest extends AbstractTest{

    MarketQuoteObservable instance = new MarketQuoteObservable(ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name));


    public MarketQuoteObservableTest() {
        instance.fillData(DateUtils.getDate(), MarketQuoteAccessor.getDefaultQuoteSet());
    }

    /**
     * Test of fillData method, of class MarketQuoteObservable.
     */
    @Test
    public void testFillData() {
        System.out.println("fillData");
        Date date = DateUtils.getDate();
        String quoteSet =  MarketQuoteAccessor.getDefaultQuoteSet();
        instance.fillData(date, quoteSet);
        assertNotNull("failed to fill market quote", instance.getObservableValue(null));
    }

    /**
     * Test of replaceQuote method, of class MarketQuoteObservable.
     */
    @Test
    public void testReplaceQuote() {
        System.out.println("replaceQuote");
        Double last = Double.MIN_NORMAL;
        instance.replaceQuote(instance.getProduct().getId(), last);
        assertTrue("failed to replace market quote", instance.getObservableValue(null).doubleValue()==last.doubleValue());
    }

    /**
     * Test of setMarketQuote method, of class MarketQuoteObservable.
     */
    @Test
    public void testSetMarketQuote() {
        System.out.println("setMarketQuote");
        instance.setMarketQuote(new MarketQuote(instance.getProductId()));
    }

    /**
     * Test of getObservableValue method, of class MarketQuoteObservable.
     */
    @Test
    public void testGetObservableValue() {
        System.out.println("getObservableValue");
        BigDecimal result = instance.getObservableValue(null);
        assertNotNull("should not be null", result);
    }

    /**
     * Test of getCoordinateClass method, of class MarketQuoteObservable.
     */
    @Test
    public void testGetCoordinateClass() {
        System.out.println("getCoordinateClass");
        Class[] result = instance.getCoordinateClass();
        assertNull("should be null", result);
    }

    /**
     * Test of getObservableValueFromPriceable method, of class MarketQuoteObservable.
     */
    @Test
    public void testGetObservableValueFromPriceable() {
        System.out.println("getObservableValueFromPriceable");
        BigDecimal result = instance.getObservableValueFromPriceable(instance.getProduct());
        assertNotNull("should not be null", result);
    }

    /**
     * Test of shift method, of class MarketQuoteObservable.
     */
    @Test
    public void testShift() {
        System.out.println("shift");
        List<ObservableShift> shifts =new ArrayList();
        ObservableShift shift=new ObservableShift();
        shift.setShift(BigDecimal.ONE);
        shifts.add(shift);
        IObservable result = instance.shift(shifts, true);
        assertTrue("bad shift", result.getObservableValue(null).doubleValue()==instance.getObservableValue(null).doubleValue()+1);
    }

    /**
     * Test of getProduct method, of class MarketQuoteObservable.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        Product result = instance.getProduct();
        assertNotNull("should not be null", result);
    }

    /**
     * Test of clone method, of class MarketQuoteObservable.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Object result = instance.clone();
        assertEquals("should be the same",instance, result);
    }

    /**
     * Test of toString method, of class MarketQuoteObservable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = instance.toString();
        assertNotNull("should not be null", result);
    }

    /**
     * Test of getUnderlyings method, of class MarketQuoteObservable.
     */
    @Test
    public void testGetUnderlyings() {
        System.out.println("getUnderlyings");
        List<Product> result = instance.getUnderlyings();
        assertTrue("should be null", result.size()==1);
    }



}
