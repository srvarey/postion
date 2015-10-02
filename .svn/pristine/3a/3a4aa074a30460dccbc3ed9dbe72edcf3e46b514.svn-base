package org.gaia.dao.trades;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.ProductExternalClassification;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductExternalClassificationAccessorTest extends AbstractTest{


    /**
     * Test of storeProductExternalClassification method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testStoreProductExternalClassification() {
        System.out.println("storeProductExternalClassification");
        ProductExternalClassification classif = new ProductExternalClassification();
        classif.setName("test");
        classif.setType("test");
        ProductExternalClassificationAccessor.storeProductExternalClassification(classif);
        assertNotNull("Extenal classification not stored",classif);
        //clean
        HibernateUtil.deleteObject(classif);
    }

    /**
     * Test of loadAllProductExternalClassifications method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testLoadAllProductExternalClassifications() {
        System.out.println("loadAllProductExternalClassifications");
        List result = ProductExternalClassificationAccessor.loadAllProductExternalClassifications();
        assertNotNull("missing extenal classification", result);
        assertFalse("missing extenal classification", result.isEmpty());
    }

    /**
     * Test of loadProductExternalClassificationsTypes method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testLoadProductExternalClassificationsTypes() {
        System.out.println("loadProductExternalClassificationsTypes");
        List result = ProductExternalClassificationAccessor.loadProductExternalClassificationsTypes();
        assertNotNull("missing extenal classification types", result);
        assertFalse("missing extenal classification types", result.isEmpty());
    }

    /**
     * Test of getProductExternalClassificationByTypeAndName method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testGetProductExternalClassificationByTypeAndName() {
        System.out.println("getProductExternalClassificationByTypeAndName");
        ProductExternalClassification classif =(ProductExternalClassification) HibernateUtil.getObjectWithQuery("from ProductExternalClassification");
        if (classif!=null){
            ProductExternalClassification result = ProductExternalClassificationAccessor.getProductExternalClassificationByTypeAndName(classif.getType(), classif.getName());
            assertEquals("external classification not found",classif,result);
        }
    }

    /**
     * Test of getProductClassificationByName method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testGetProductClassificationByName() {
        System.out.println("getProductClassificationByName");
        ProductExternalClassification prt =(ProductExternalClassification) HibernateUtil.getObjectWithQuery("from ProductExternalClassification");
        if (prt!=null){
            ProductExternalClassification result = ProductExternalClassificationAccessor.getProductClassificationByName(prt.getName());
            assertNotNull("external classification not found", result);
        }
    }

    /**
     * Test of isChildOf method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testIsChildOf() {
       System.out.println("isChildOf");
       ProductExternalClassification son =(ProductExternalClassification) HibernateUtil.getObjectWithQuery("from ProductExternalClassification where parent is not null");
       if (son!=null){
            boolean result = ProductExternalClassificationAccessor.isChildOf(son.getName(),son.getParent());
            assertTrue("error on parent relation", result);
       }
    }

    /**
     * Test of deleteProductExternalClassification method, of class ProductExternalClassificationAccessor.
     */
    @Test
    public void testDeleteProductExternalClassification() {
        System.out.println("deleteProductExternalClassification");
        ProductExternalClassification classif=new ProductExternalClassification();
        classif.setName("test");
        classif.setType("test");
        HibernateUtil.saveObject(classif);
        ProductExternalClassificationAccessor.deleteProductExternalClassification("test", "test");
        classif=ProductExternalClassificationAccessor.getProductExternalClassificationByTypeAndName("test", "test");
        assertNull("external classification not deleted",classif);

    }
}