package org.gaia.dao.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.observables.PricersSetting;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.observables.PricingSettingItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class PricingEnvironmentAccessorTest extends AbstractTest{

    public PricingEnvironmentAccessorTest() {
    }

    /**
     * Test of getPricingEnvironmentList method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricingEnvironmentList() {
        System.out.println("getPricingEnvironmentList");
        List<String> result = PricingEnvironmentAccessor.getPricingEnvironmentList();
        assertNotNull("missing PricingEnvironments", result);
        assertFalse("missing PricingEnvironments", result.isEmpty());
    }

    /**
     * Test of getPricingEnvironmentFromName method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricingEnvironmentFromName() {
        System.out.println("getPricingEnvironmentFromName");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricingEnvironment result = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnvironment.getName());
        assertNotNull("missing PricingEnvironment", result);
    }

    /**
     * Test of lookForObservableIdLinkedWithPricingEnvironment method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testLookForObservableIdLinkedWithPricingEnvironment() {
        System.out.println("lookForObservableIdLinkedWithPricingEnvironment");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        IObservable observable = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE,AbstractObservable.DISCOUNTING);
        Product usd=ProductAccessor.getProductByShortName("USD");
        observable.setLookupProduct(usd);
        Integer result = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(observable, usd, pricingEnvironment);
        assertNotNull("no result found", result);
    }

    /**
     * Test of getObservableQuoteSet method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetObservableQuoteSet() {
        System.out.println("getObservableQuoteSet");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        IPriceable observable = ProductAccessor.getProductByShortName("USD");
        String result = PricingEnvironmentAccessor.getObservableQuoteSet(observable, pricingEnvironment);
        assertNotNull("no result found", result);
    }

    /**
     * Test of getPricersMap method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricersMap() {
        System.out.println("getPricersMap");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        List<IPriceable> listPriceables = new ArrayList();
        listPriceables.add(ProductAccessor.getProductByShortName("USD"));
        MeasuresAccessor.MeasureGroup[] measureGroups = MeasuresAccessor.MeasureGroup.values();
        Map<Integer, Map<MeasuresAccessor.MeasureGroup, String>> result = PricingEnvironmentAccessor.getPricersMap(listPriceables, pricingEnvironment, measureGroups);
        assertNotNull("no result found", result);
    }

    /**
     * Test of getPricersMapByPriceable method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricersMapByPriceable() {
        System.out.println("getPricersMapByPriceable");
        IPriceable priceable = ProductAccessor.getProductByShortName("USD");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        MeasuresAccessor.MeasureGroup[] measureGroups = MeasuresAccessor.MeasureGroup.values();
        Map<MeasuresAccessor.MeasureGroup, String> result = PricingEnvironmentAccessor.getPricersMapByPriceable(priceable, pricingEnvironment, measureGroups);
        assertNotNull("no result found", result);
    }

    /**
     * Test of getPricingSettingItem method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricingSettingItem() {
        System.out.println("getPricingSettingItem");
        PricingSettingItem item=(PricingSettingItem) HibernateUtil.getObjectWithQuery("from PricingSettingItem psi where psi.pricingSettingItemId=(select min(pricingSettingItemId) from PricingSettingItem)");
        PricingSettingItem result = PricingEnvironmentAccessor.getPricingSettingItem(item.getPricingSettingItemId());
        assertNotNull("no result found", result);
    }

    /**
     * Test of getPricersSetting method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricersSetting() {
        System.out.println("getPricersSetting");
        PricersSetting setting=(PricersSetting) HibernateUtil.getObjectWithQuery("from PricersSetting ps where ps.pricersSettingId=(select min(pricersSettingId) from PricersSetting)");
        PricersSetting result = PricingEnvironmentAccessor.getPricersSetting(setting.getPricersSettingId());
        assertNotNull("no result found", result);
    }

    /**
     * Test of loadPricingSettingItemValuesIds method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testLoadPricingSettingItemValuesIds() {
        System.out.println("loadPricingSettingItemValuesIds");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricingEnvironmentAccessor.loadPricingSettingItemValuesIds(pricingEnvironment);
    }

    /**
     * Test of storePricingEnvironment method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testStorePricingEnvironment() {
        System.out.println("storePricingEnvironment");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricingEnvironmentAccessor.storePricingEnvironment(pricingEnvironment);
        assertNotNull("problem while saving pricing env", pricingEnvironment);
    }

    /**
     * Test of storePricersSetting method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testStorePricersSetting() {
        System.out.println("storePricersSetting");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricersSetting pricersSetting = new PricersSetting();
        pricersSetting.setMeasureGroup(MeasuresAccessor.MeasureGroup.PV_GROUP.name());
        pricersSetting.setPricer("test");
        pricersSetting.setPricingEnvironment(null);
        pricersSetting.setPricingEnvironment(pricingEnvironment);
        PricingEnvironmentAccessor.storePricersSetting(pricersSetting);
        assertNotNull("problem while saving pricer settings", pricersSetting.getPricersSettingId());
        HibernateUtil.deleteObject(pricersSetting);
    }

    /**
     * Test of deletePricersSetting method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testDeletePricersSetting() {
        System.out.println("deletePricersSetting");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricersSetting pricersSetting = new PricersSetting();
        pricersSetting.setMeasureGroup(MeasuresAccessor.MeasureGroup.PV_GROUP.name());
        pricersSetting.setPricer("test");
        pricersSetting.setPricingEnvironment(null);
        pricersSetting.setPricingEnvironment(pricingEnvironment);
        HibernateUtil.saveObject(pricersSetting);
        PricingEnvironmentAccessor.deletePricersSetting(pricersSetting);
        pricersSetting=(PricersSetting)HibernateUtil.getObject(PricersSetting.class, pricersSetting.getPricersSettingId());
        assertNull("problem while deleting pricer settings", pricersSetting);
    }

    /**
     * Test of storePricingSettingItem method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testStorePricingSettingItem() {
        System.out.println("storePricingSettingItem");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricingSettingItem pricingSettingItem = new PricingSettingItem();
        pricingSettingItem.setPricingEnvironment(pricingEnvironment);
        PricingEnvironmentAccessor.storePricingSettingItem(pricingSettingItem);
        assertNotNull("problem while saving pricing setting item", pricingSettingItem.getPricingSettingItemId());
        HibernateUtil.deleteObject(pricingSettingItem);
    }

    /**
     * Test of deletePricingSettingItem method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testDeletePricingSettingItem() {
        System.out.println("deletePricingSettingItem");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        PricingSettingItem pricingSettingItem = new PricingSettingItem();
        pricingSettingItem.setPricingEnvironment(pricingEnvironment);
        HibernateUtil.saveObject(pricingSettingItem);
        PricingEnvironmentAccessor.deletePricingSettingItem(pricingSettingItem);
        pricingSettingItem=(PricingSettingItem)HibernateUtil.getObject(PricingSettingItem.class, pricingSettingItem.getPricingSettingItemId());
        assertNull("problem while deleting pricing setting item", pricingSettingItem);
    }

    /**
     * Test of getPricingEnvironmentNamesByFilterName method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetPricingEnvironmentNamesByFilterName() {
        System.out.println("getPricingEnvironmentNamesByFilterName");
        PricingEnvironment pricingEnvironment=(PricingEnvironment) HibernateUtil.getObjectWithQuery("from PricingEnvironment pe where pe.pricingEnvironmentId=(select min(pricingEnvironmentId) from PricingEnvironment)");
        List<String> result = PricingEnvironmentAccessor.getPricingEnvironmentNamesByFilterName(pricingEnvironment.getPricersSettingCollection().iterator().next().getTradeFilterName());
        assertNotNull("problem while saving pricing setting item", result);
    }

    /**
     * Test of getDefaultPricingEnvironment method, of class PricingEnvironmentAccessor.
     */
    @Test
    public void testGetDefaultPricingEnvironment() {
        System.out.println("getDefaultPricingEnvironment");
        PricingEnvironment result = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        assertNotNull("missing default pricing env", result);
    }

}
