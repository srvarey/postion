package org.gaia.dao.audit;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */


public class AuditAccessorTest extends AbstractTest {

    public AuditAccessorTest() {
    }

    /**
     * Test of getPreviousVersion method, of class AuditAccessor.
     */
    @Test
    public void testGetPreviousVersion() {
        System.out.println("getPreviousVersion");
        Trade entity = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId in (select min(tradeId) from Trade)");
        Trade result = AuditAccessor.getPreviousVersion(entity);
        assertNotNull(" trade version not found", result);
    }

    /**
     * Test of getOldVersionList method, of class AuditAccessor.
     */
    @Test
    public void testGetOldVersionList() {
        System.out.println("getOldVersionList");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId in (select min(tradeId) from Trade)");
        List<Trade> result = AuditAccessor.getOldVersionList(trade);
        assertNotNull(" trade versions not found", result);
        assertFalse(" trade versions not found", result.isEmpty());
    }

    /**
     * Test of unProxyObject method, of class AuditAccessor.
     */
    @Test
    public void testUnProxyObject() throws Exception {
        System.out.println("unProxyObject");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId in (select min(tradeId) from Trade)");
        AuditAccessor.unProxyObject(trade);
        assertNotNull(" failed unproxying trade", trade);
    }

    /**
     * Test of unLazyObjectRecursively method, of class AuditAccessor.
     */
    @Test
    public void testUnLazyObjectRecursively_Object() {
        System.out.println("unLazyObjectRecursively");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId in (select min(tradeId) from Trade)");
        AuditAccessor.unLazyObjectRecursively(trade);
        assertNotNull("failed lazy loading trade", trade);
    }

    /**
     * Test of unLazyObjectRecursively method, of class AuditAccessor.
     */
    @Test
    public void testUnLazyObjectRecursively_Object_int() throws Exception {
        System.out.println("unLazyObjectRecursively");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId in (select min(tradeId) from Trade)");
        AuditAccessor.unLazyObjectRecursively(trade, 5);
        assertNotNull("failed lazy loading trade", trade);
    }

}
