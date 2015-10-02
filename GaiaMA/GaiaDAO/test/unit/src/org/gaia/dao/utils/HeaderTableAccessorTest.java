package org.gaia.dao.utils;

import org.gaia.domain.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.gaia.domain.trades.HeaderTable;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class HeaderTableAccessorTest extends AbstractTest{

    public HeaderTableAccessorTest() {
    }

    /**
     * Test of storeTableHeaders method, of class HeaderTableAccessor.
     */
    @Test
    public void testStoreTableHeaders() {
        System.out.println("storeHeaderTable");
        List<HeaderTable> headerList = new ArrayList();
        HeaderTable header=new HeaderTable("UNIT TEST","UNIT TEST");
        headerList.add(header);
        HeaderTableAccessor.storeTableHeaders(headerList);
        assertNotNull("failed to store headers",headerList.get(0).getHeaderId());
    }

    /**
     * Test of loadTableHeaders method, of class HeaderTableAccessor.
     */
    @Test
    public void testLoadTableHeaders() {
        System.out.println("loadAllHeaderTable");
        HeaderTable header=(HeaderTable) HibernateUtil.getObjectWithQuery("from HeaderTable h where h.headerId=(select min(headerId) from HeaderTable)");
        List<HeaderTable> result = HeaderTableAccessor.loadTableHeaders(header.getTableName());
        assertNotNull("failed to load headers", result);
    }

}
