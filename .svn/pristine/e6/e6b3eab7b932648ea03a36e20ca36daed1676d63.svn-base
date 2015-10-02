package org.gaia.dao.observables;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */

public class ProductHistoricalAccessorTest  extends AbstractTest{

    public ProductHistoricalAccessorTest() {
    }

    /**
     * Test of getHistoricals method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testGetHistoricals() {
        System.out.println("getHistoricals");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        List<ProductHistoricalMeasure> result = ProductHistoricalAccessor.getHistoricals(productHistoricalMeasure.getProduct().getProductId());
        assertNotNull("missing historical measure", result);
    }

    /**
     * Test of getHistoricalByDateAndProduct method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testGetHistoricalByDateAndProduct() {
        System.out.println("getHistoricalByDateAndProduct");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        ProductHistoricalMeasure result = ProductHistoricalAccessor.getHistoricalByDateAndProduct(productHistoricalMeasure.getProduct().getProductId(), productHistoricalMeasure.getValuationDate(), productHistoricalMeasure.getQuoteSet());
        assertNotNull("missing historical measure", result);
    }

    /**
     * Test of getLastHistorical method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testGetLastHistorical() {
        System.out.println("getLastHistorical");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        ProductHistoricalMeasure result = ProductHistoricalAccessor.getLastHistorical(productHistoricalMeasure.getProduct().getProductId(), DateUtils.getDate(), productHistoricalMeasure.getQuoteSet());
        assertNotNull("missing historical measure", result);
    }

    /**
     * Test of getLastHistoricalDate method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testGetLastHistoricalDate() {
        System.out.println("getLastHistoricalDate");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        Date result = ProductHistoricalAccessor.getLastHistoricalDate(productHistoricalMeasure.getProduct().getProductId(), DateUtils.getDate(), productHistoricalMeasure.getQuoteSet());
        assertNotNull("missing historical measure", result);
    }

    /**
     * Test of getHistoricalByDatesAndProduct method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testGetHistoricalByDatesAndProduct() {
        System.out.println("getHistoricalByDatesAndProduct");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        List<ProductHistoricalMeasure> result = ProductHistoricalAccessor.getHistoricalByDatesAndProduct(productHistoricalMeasure.getProduct().getProductId(), productHistoricalMeasure.getValuationDate(), productHistoricalMeasure.getValuationDate(), productHistoricalMeasure.getQuoteSet());
        assertNotNull("missing historical measure", result);
    }

    /**
     * Test of storeProductHistoricalMeasureValue method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testStoreProductHistoricalMeasureValue() {
        System.out.println("storeProductHistoricalMeasureValue");
        ProductHistoricalMeasureValue existing=(ProductHistoricalMeasureValue) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasureValue phmv where phmv.productHistoricalMeasureValueId=(select min(productHistoricalMeasureValueId) from ProductHistoricalMeasureValue)");
        ProductHistoricalMeasureValue newOne=new ProductHistoricalMeasureValue();
        newOne.setProductHistoricalMeasure(existing.getProductHistoricalMeasure());
        newOne.setMeasureName("test");
        newOne.setMeasureValue(BigDecimal.ZERO);
        ProductHistoricalAccessor.storeProductHistoricalMeasureValue(newOne);
        assertNotNull("missing historical measure value", newOne.getProductHistoricalMeasureValueId());
        HibernateUtil.deleteObject(newOne);
    }

    /**
     * Test of saveProductHistoricalMeasure method, of class ProductHistoricalAccessor.
     */
    @Test
    public void testSaveProductHistoricalMeasure() {
        System.out.println("saveProductHistoricalMeasure");
        ProductHistoricalMeasure productHistoricalMeasure=(ProductHistoricalMeasure) HibernateUtil.getObjectWithQuery("from ProductHistoricalMeasure phm where phm.productHistoricalMeasureId=(select min(productHistoricalMeasureId) from ProductHistoricalMeasure)");
        ProductHistoricalMeasure newOne = new ProductHistoricalMeasure();
        newOne.setProduct(productHistoricalMeasure.getProduct());
        newOne.setValuationDate(DateUtils.getDate());
        ProductHistoricalAccessor.saveProductHistoricalMeasure(newOne);
        assertNotNull("missing historical measure", newOne.getProductHistoricalMeasureId());
        HibernateUtil.deleteObject(newOne);
    }

}
