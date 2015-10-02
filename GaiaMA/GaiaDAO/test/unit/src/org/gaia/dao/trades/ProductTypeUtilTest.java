package org.gaia.dao.trades;

import java.util.List;
import org.gaia.domain.reports.FilterCriteria;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductTypeUtilTest {

    public ProductTypeUtilTest() {
    }

    /**
     * Test of loadProductTypeUses method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadProductTypeUses() {
        System.out.println("loadProductTypeUses");
        List result = ProductTypeUtil.loadProductTypeUses();
        assertNotNull("missing product type use", result);
        assertFalse("missing product type use", result.isEmpty());
    }

    /**
     * Test of loadProductFamilies method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadProductFamilies() {
        System.out.println("loadProductFamilies");
        List result = ProductTypeUtil.loadProductFamilies();
        assertNotNull("missing product families", result);
        assertFalse("missing product families", result.isEmpty());
    }

    /**
     * Test of loadProductTypesAndFamilies method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadProductTypesAndFamilies() {
        System.out.println("loadProductTypesAndFamilies");
        List result = ProductTypeUtil.loadProductTypesAndFamilies();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadProductTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadProductTypes() {
        System.out.println("loadProductTypes");
        List result = ProductTypeUtil.loadProductTypes();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadProductTypeName method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadProductTypeName() {
        System.out.println("loadProductTypeName");
        List result = ProductTypeUtil.loadProductTypeName();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTypesByFamilyAndUse method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTypesByFamilyAndUse() {
        System.out.println("loadTypesByFamilyAndUse");
        List result = ProductTypeUtil.loadTypesByFamilyAndUse(ProductTypeUtil.ProductFamily.FX, ProductTypeUtil.ProductTypeUse.OTC);
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTypesByFamily method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTypesByFamily() {
        System.out.println("loadTypesByFamily");
        List result = ProductTypeUtil.loadTypesByFamily(ProductTypeUtil.ProductFamily.FX);
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTypesByUse method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTypesByUse() {
        System.out.println("loadTypesByUse");
        List result = ProductTypeUtil.loadTypesByUse(ProductTypeUtil.ProductTypeUse.OTC);
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadUsableTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadUsableTypes() {
        System.out.println("loadUsableTypes");
        List result = ProductTypeUtil.loadUsableTypes();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadListed method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadListed() {
        System.out.println("loadListed");
        List result = ProductTypeUtil.loadListed();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadLegs method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadLegs() {
        System.out.println("loadLegs");
        List result = ProductTypeUtil.loadLegs();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadOTC method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadOTC() {
        System.out.println("loadOTC");
        List result = ProductTypeUtil.loadOTC();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadEquities method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadEquities() {
        System.out.println("loadEquities");
        List result = ProductTypeUtil.loadEquities();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadListedEquities method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadListedEquities() {
        System.out.println("loadListedEquities");
        List result = ProductTypeUtil.loadListedEquities();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadListedIRRates method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadListedIRRates() {
        System.out.println("loadListedIRRates");
        List result = ProductTypeUtil.loadListedIRRates();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadUnderlyings method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadUnderlyings() {
        System.out.println("loadUnderlyings");
        List result = ProductTypeUtil.loadUnderlyings();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of getFXOptionTypesFilterCriteria method, of class ProductTypeUtil.
     */
    @Test
    public void testGetFXOptionTypesFilterCriteria() {
        System.out.println("getFXOptionTypesFilterCriteria");
        FilterCriteria result = ProductTypeUtil.getFXOptionTypesFilterCriteria();
        assertNotNull("missing fx option type filter citeria", result);
    }

    /**
     * Test of getFXOptionTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testGetChildrenTypes() {
        System.out.println("getFXOptionTypes");
        List result = ProductTypeUtil.getChildrenTypes(ProductTypeUtil.ProductType.FX_OPTION);
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of getBondTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testGetBondTypes() {
        System.out.println("getBondTypes");
        List result = ProductTypeUtil.getBondTypes();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTradeableTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTradeableTypes() {
        System.out.println("loadTradeableTypes");
        List result = ProductTypeUtil.loadTradeableTypes();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTradeableAndCashTypes method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTradeableAndCashTypes() {
        System.out.println("loadTradeableAndCashTypes");
        List result = ProductTypeUtil.loadTradeableAndCashTypes();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadTradeableTypeNames method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadTradeableTypeNames() {
        System.out.println("loadTradeableTypeNames");
        List result = ProductTypeUtil.loadTradeableTypeNames();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadFamilyUnderlyings method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadFamilyUnderlyings() {
        System.out.println("loadFamilyUnderlyings");
        List result = ProductTypeUtil.loadFamilyUnderlyings(ProductTypeUtil.ProductFamily.IR);
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadEquityUnderlyings method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadEquityUnderlyings() {
        System.out.println("loadEquityUnderlyings");
        List result = ProductTypeUtil.loadEquityUnderlyings();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of loadRateUnderlyings method, of class ProductTypeUtil.
     */
    @Test
    public void testLoadRateUnderlyings() {
        System.out.println("loadRateUnderlyings");
        List result = ProductTypeUtil.loadRateUnderlyings();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }

    /**
     * Test of getProductTypeByName method, of class ProductTypeUtil.
     */
    @Test
    public void testGetProductTypeByName() {
        System.out.println("getProductTypeByName");
        ProductTypeUtil.ProductType result = ProductTypeUtil.getProductTypeByName(ProductTypeUtil.ProductType.CASH.getName());
        assertNotNull("missing product types", result);
    }

    /**
     * Test of isChildOf method, of class ProductTypeUtil.
     */
    @Test
    public void testIsChildOf() {
        System.out.println("isChildOf");
        boolean result = ProductTypeUtil.isChildOf(ProductTypeUtil.ProductType.FX_VANILLA_OPTION.getName(), ProductTypeUtil.ProductType.FX_OPTION.getName());
        assertTrue("missing product types", result);
    }

    /**
     * Test of getProductStatusList method, of class ProductTypeUtil.
     */
    @Test
    public void testGetProductStatusList() {
        System.out.println("getProductStatusList");
        List result = ProductTypeUtil.getProductStatusList();
        assertNotNull("missing product types", result);
        assertFalse("missing product types", result.isEmpty());
    }
}