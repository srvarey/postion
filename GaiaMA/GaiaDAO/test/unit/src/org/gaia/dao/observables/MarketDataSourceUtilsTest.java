package org.gaia.dao.observables;

import jade.core.AID;
import java.util.List;
import java.util.Map;
import org.gaia.dao.jade.MarketDataAgent;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketDataSource;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class MarketDataSourceUtilsTest extends AbstractTest{

    public MarketDataSourceUtilsTest() {
    }

    /**
     * Test of getMarketDataSources method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketDataSources() {
        System.out.println("getMarketDataSources");
        List<String> result = MarketDataSourceUtils.getMarketDataSources();
        assertNotNull("missing MarketDataSources", result);
        assertFalse("missings MarketDataSources", result.isEmpty());
    }

    /**
     * Test of getMarketDataSourceByName method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketDataSourceByName() {
        System.out.println("getMarketDataSourceByName");
        String name=(String) HibernateUtil.getObjectWithQuery("select min(mds.marketDataSourceName) from MarketDataSource mds");
        MarketDataSource result = MarketDataSourceUtils.getMarketDataSourceByName(name);
        assertNotNull("missing MarketDataSources", result);
    }

    /**
     * Test of storeMarketDataSource method, of class MarketDataSourceUtils.
     */
    @Test
    public void testStoreMarketDataSource() {
        System.out.println("storeMarketDataSource");
        MarketDataSource marketDataSource = new MarketDataSource();
        marketDataSource.setClassName("test");
        marketDataSource.setServerHost("test");
        marketDataSource.setServerPort(0);
        MarketDataSourceUtils.storeMarketDataSource(marketDataSource);
        assertNotNull("missing MarketDataSources", marketDataSource.getMarketDataSourceId());
        HibernateUtil.deleteObject(marketDataSource);
    }

    /**
     * Test of deleteMarketDataSource method, of class MarketDataSourceUtils.
     */
    @Test
    public void testDeleteMarketDataSource() {
        System.out.println("deleteMarketDataSource");
        MarketDataSource marketDataSource = new MarketDataSource();
        marketDataSource.setClassName("test");
        marketDataSource.setServerHost("test");
        marketDataSource.setServerPort(0);
        HibernateUtil.saveObject(marketDataSource);
        MarketDataSourceUtils.deleteMarketDataSource(marketDataSource);
        marketDataSource=(MarketDataSource)HibernateUtil.getObject(MarketDataSource.class, marketDataSource.getMarketDataSourceId());
        assertNull("missing MarketDataSources", marketDataSource);
    }

    /**
     * Test of launchMarketDataConnectors method, of class MarketDataSourceUtils.
     */
    @Test
    public void testLaunchMarketDataConnectors() {
        System.out.println("launchMarketDataConnectors");
        MarketDataAgent myAgent = new MarketDataAgent(new AID(),new jade.util.leap.ArrayList());
        MarketDataSourceUtils.launchMarketDataConnectors(myAgent);
    }

    /**
     * Test of getMarketDataSourceLinkCodes method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketDataSourceLinkCodes() {
        System.out.println("getMarketDataSourceLinkCodes");
        MarketDataSource marketDataSource=(MarketDataSource) HibernateUtil.getObjectWithQuery("from MarketDataSource mds where mds.marketDataSourceId=(select min(marketDataSourceId) from MarketDataSource)");
        List<String> result = MarketDataSourceUtils.getMarketDataSourceLinkCodes(marketDataSource.getMarketDataSourceName());
        assertNotNull("missing MarketDataSources", result);
        assertFalse("missings MarketDataSources", result.isEmpty());
    }

    /**
     * Test of getMarketCodesMap method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketCodesMap_String() {
        System.out.println("getMarketCodesMap");
        MarketDataSource marketDataSource=(MarketDataSource) HibernateUtil.getObjectWithQuery("from MarketDataSource mds where mds.marketDataSourceId=(select min(marketDataSourceId) from MarketDataSource)");
        Map<String, Integer> result = MarketDataSourceUtils.getMarketCodesMap(marketDataSource.getMarketDataSourceName());
        assertNotNull("missing MarketDataSources", result);
        assertFalse("missings MarketDataSources", result.isEmpty());
    }

    /**
     * Test of getMarketCodes method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketCodes() {
        System.out.println("getMarketCodes");
        MarketDataCode marketDataCode=(MarketDataCode) HibernateUtil.getObjectWithQuery("from MarketDataCode mdc where mdc.marketDataCodeId=(select min(marketDataCodeId) from MarketDataCode)");
        List<MarketDataCode> result = MarketDataSourceUtils.getMarketCodes(marketDataCode.getProduct().getProductId());
        assertNotNull("missing MarketData codes", result);
        assertFalse("missings MarketData codes", result.isEmpty());
    }

    /**
     * Test of getMarketCodesMap method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketCodesMap_Integer() {
        System.out.println("getMarketCodesMap");
        MarketDataCode marketDataCode=(MarketDataCode) HibernateUtil.getObjectWithQuery("from MarketDataCode mdc where mdc.marketDataCodeId=(select min(marketDataCodeId) from MarketDataCode)");
        Map<String, MarketDataCode> result = MarketDataSourceUtils.getMarketCodesMap(marketDataCode.getProduct().getProductId());
        assertNotNull("missing MarketData codes", result);
        assertFalse("missings MarketData codes", result.isEmpty());
    }

    /**
     * Test of getMarketCode method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketCode() {
        System.out.println("getMarketCode");
        MarketDataCode marketDataCode=(MarketDataCode) HibernateUtil.getObjectWithQuery("from MarketDataCode mdc where mdc.marketDataCodeId=(select min(marketDataCodeId) from MarketDataCode)");
        MarketDataCode result = MarketDataSourceUtils.getMarketCode(marketDataCode.getProduct().getProductId(), marketDataCode.getMarketDataSource().getMarketDataSourceName());
        assertNotNull("missing MarketData", result);
    }

    /**
     * Test of getMarketDataSourceNamesbyFilterId method, of class MarketDataSourceUtils.
     */
    @Test
    public void testGetMarketDataSourceNamesbyFilterId() {
        System.out.println("getMarketDataSourceNamesbyFilterId");
        MarketDataSource marketDataSource=(MarketDataSource) HibernateUtil.getObjectWithQuery("from MarketDataSource mds where mds.marketDataSourceId=(select min(marketDataSourceId) from MarketDataSource)");
        if (marketDataSource.getFilter()!=null){
            List<String> result = MarketDataSourceUtils.getMarketDataSourceNamesbyFilterId(marketDataSource.getFilter().getFilterId());
            assertNotNull("missing MarketData Source", result);
            assertFalse("missings MarketData Source", result.isEmpty());
        }
    }

}
