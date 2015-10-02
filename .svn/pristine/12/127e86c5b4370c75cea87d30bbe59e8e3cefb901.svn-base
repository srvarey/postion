package org.gaia.dao.trades;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.ProductReferenceType;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductReferenceTypeAccessorTest  extends AbstractTest{

    /**
     * Test of storeProductReferenceType method, of class ProductReferenceTypeAccessor.
     */
    @Test
    public void testStoreProductReferenceType() {
        System.out.println("storeProductReferenceType");
        ProductReferenceType referenceType = new ProductReferenceType("test");
        ProductReferenceTypeAccessor.storeProductReferenceType(referenceType);
        referenceType=ProductReferenceTypeAccessor.getProductReferenceType(referenceType.getReferenceType());
        assertNotNull("referenceType not stored",referenceType);
        //clean
        HibernateUtil.deleteObject(referenceType);
    }

    /**
     * Test of getAllProductReferenceTypeStrings method, of class ProductReferenceTypeAccessor.
     */
    @Test
    public void testGetAllProductReferenceTypeStrings() {
        System.out.println("loadAllProductReferenceTypeStrings");
        List result = ProductReferenceTypeAccessor.getAllProductReferenceTypeStrings();
        assertNotNull("missing product referenceType", result);
        assertFalse("missing product referenceType", result.isEmpty());
    }

    /**
     * Test of getAllProductReferenceTypes method, of class ProductReferenceTypeAccessor.
     */
    @Test
    public void testGetAllProductReferenceTypes() {
        System.out.println("loadAllProductReferenceTypes");
        List result = ProductReferenceTypeAccessor.getAllProductReferenceTypes();
        assertNotNull("missing product referenceType", result);
        assertFalse("missing product referenceType", result.isEmpty());
    }

    /**
     * Test of getProductReferenceType method, of class ProductReferenceTypeAccessor.
     */
    @Test
    public void testGetProductReferenceType() {
        System.out.println("getProductReferenceType");
        String name = (String)HibernateUtil.getObjectWithQuery("select min(prt.referenceType) from ProductReferenceType prt");
        ProductReferenceType result = ProductReferenceTypeAccessor.getProductReferenceType(name);
        assertNotNull("missing reference type", result);
    }

    /**
     * Test of deleteProductReferenceType method, of class ProductReferenceTypeAccessor.
     */
    @Test
    public void testDeleteProductReferenceType() {
        System.out.println("deleteProductReferenceType");
        ProductReferenceType prt=new ProductReferenceType();
        prt.setReferenceType("test");
        prt.setType("test");
        ProductReferenceTypeAccessor.storeProductReferenceType(prt);
        ProductReferenceTypeAccessor.deleteProductReferenceType(prt.getReferenceType());
        prt=ProductReferenceTypeAccessor.getProductReferenceType("test");
        assertNull("reference typenot deleted", prt);
    }
}