package org.gaia.dao.reports;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.FilterGroup;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */
public class FilterAccessorTest extends AbstractTest {

    public FilterAccessorTest() {
    }

    /**
     * Test of getFilterNames method, of class FilterAccessor.
     */
    @Test
    public void testGetFilterNames() {
        System.out.println("getFilterNames");
        List<String> result = FilterAccessor.getFilterNames(Trade.class);
        assertNotNull("missing filter", result);
        assertFalse("missing filter", result.isEmpty());
    }

    /**
     * Test of getFilterByName method, of class FilterAccessor.
     */
    @Test
    public void testGetFilterByName() {
        System.out.println("getFilterByName");
        Filter filter = (Filter) HibernateUtil.getObjectWithQuery("from Filter f where f.filterId=(select min(filterId) from Filter where objectType='" + Trade.class.getName() + "')");
        Filter result = FilterAccessor.getFilterByName(filter.getName(), filter.getObjectTypeClass());
        assertEquals("filter not found", filter, result);
    }

    /**
     * Test of getFilterCriteriaByNameAndFilterId method, of class
     * FilterAccessor.
     */
    @Test
    public void testGetFilterCriteriaByNameAndFilterId() {
        System.out.println("getFilterCriteriaByNameAndFilterId");
        Filter filter = (Filter) HibernateUtil.getObjectWithQuery("from Filter f where f.filterId=(select min(filterId) from Filter)");
        if (filter.getFilterCriteria() != null && filter.getFilterCriteria().size() > 0) {
            FilterCriteria result = FilterAccessor.getFilterCriteriaByNameAndFilterId(filter.getFilterCriteria().iterator().next().getColumnName(), filter.getFilterId());
            assertEquals("filter not found", filter, result);
        }
    }

    /**
     * Test of getFilterCriteriaById method, of class FilterAccessor.
     */
    @Test
    public void testGetFilterCriteriaById() {
        System.out.println("getFilterCriteriaById");
        FilterCriteria filterCriteria = (FilterCriteria) HibernateUtil.getObjectWithQuery("from FilterCriteria f where f.filterCriteriaId=(select min(filterCriteriaId) from FilterCriteria)");
        FilterCriteria result = FilterAccessor.getFilterCriteriaById(filterCriteria.getFilterCriteriaId());
        assertEquals("filter criteria not found", filterCriteria, result);
    }

    /**
     * Test of deleteFilterCriteria method, of class FilterAccessor.
     */
    @Test
    public void testDeleteFilterCriteria() {
        System.out.println("deleteFilterCriteria");
        FilterCriteria filterCriteria = new FilterCriteria();
        HibernateUtil.saveObject(filterCriteria);
        FilterAccessor.deleteFilterCriteria(filterCriteria);
        Object res = HibernateUtil.getObject(FilterCriteria.class, filterCriteria.getFilterCriteriaId());
        assertNull("filter criteria not deleted", res);
    }

    /**
     * Test of storeFilter method, of class FilterAccessor.
     */
    @Test
    public void testStoreFilter() {
        System.out.println("storeFilter");
        Filter filter = new Filter();
        FilterAccessor.storeFilter(filter);
        Object res = HibernateUtil.getObject(Filter.class, filter.getFilterId());
        assertNotNull("filter not stored", res);
        HibernateUtil.deleteObject(filter);
    }

    /**
     * Test of removeFilterColumn method, of class FilterAccessor.
     */
    @Test
    public void testRemoveFilterColumn() {
        System.out.println("removeFilterColumn");
        Filter filter = (Filter) HibernateUtil.getObjectWithQuery("from Filter f where f.filterId=(select min(filterId) from Filter)");
        if (filter.getFilterCriteria() != null && filter.getFilterCriteria().size() > 0) {
            int before = filter.getFilterCriteria().size();
            FilterCriteria filterCriteria = filter.getFilterCriteria().iterator().next();
            FilterAccessor.removeFilterColumn(filter, filterCriteria.getColumnName());
            assertEquals("filter criteria not removed", before, filter.getFilterCriteria().size() + 1);
        }
    }

    /**
     * Test of addCriteria method, of class FilterAccessor.
     */
    @Test
    public void testAddCriteria() {
        System.out.println("addCriteria");
        Filter filter = (Filter) HibernateUtil.getObjectWithQuery("from Filter f where f.filterId=(select min(filterId) from Filter)");
        int before = filter.getFilterCriteria().size();
        FilterAccessor.addCriteria(filter, "", "", "", "", "");
        assertEquals("filter criteria not added", before, filter.getFilterCriteria().size() - 1);
    }

    /**
     * Test of deleteFilterByName method, of class FilterAccessor.
     */
    @Test
    public void testDeleteFilterByName() {
        System.out.println("deleteFilterByName");
        Filter filter = new Filter();
        filter.setName("UNIT TEST");
        filter.setObjectTypeClass(Trade.class);
        HibernateUtil.saveObject(filter);
        FilterAccessor.deleteFilterByName(filter.getName(), filter.getObjectTypeClass());
        Object res = HibernateUtil.getObject(Filter.class, filter.getFilterId());
        assertNull("filter not deleted", res);
    }

    /**
     * Test of deleteFilter method, of class FilterAccessor.
     */
    @Test
    public void testDeleteFilter() {
        System.out.println("deleteFilter");
        Filter filter = new Filter();
        filter.setName("UNIT TEST");
        HibernateUtil.saveObject(filter);
        FilterAccessor.deleteFilter(filter);
        Object res = HibernateUtil.getObject(Filter.class, filter.getFilterId());
        assertNull("filter not deleted", res);
    }

    /**
     * Test of isInFilter method, of class FilterAccessor.
     */
    @Test
    public void testIsInFilter() {
        System.out.println("isInFilter");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId=(select min(tradeId) from Trade)");
        Filter filter = new Filter();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setColumnName("tradeId");
        filterCriteria.setColumnType(Integer.class.getName());
        filterCriteria.setFilter(filter);
        filterCriteria.setGetter("getTradeId");
        filterCriteria.setNotIn(false);
        filterCriteria.setParam("");
        filterCriteria.setReturnType(Integer.class.getName());
        filterCriteria.setValue(trade.getTradeId().toString());
        filter.getFilterCriteria().add(filterCriteria);
        Boolean result = FilterAccessor.isInFilter(trade, filter);
        assertTrue("trade should be accepted in filter", result);
        filterCriteria.setValue("0");
        result = FilterAccessor.isInFilter(trade, filter);
        assertFalse("trade should be refused in filter", result);
    }

    /**
     * Test of getFilterGroup method, of class FilterAccessor.
     */
    @Test
    public void testGetFilterGroup() {
        System.out.println("getFilterGroup");
        FilterGroup filterGroup = (FilterGroup) HibernateUtil.getObjectWithQuery("from FilterGroup fg where fg.filterGroupId=(select min(filterGroupId) from FilterGroup)");
        FilterGroup result = FilterAccessor.getFilterGroup(filterGroup.getLinkedObjectClass(), filterGroup.getLinkedObjectId());
        assertNotNull("filter group not found", result);
    }

    /**
     * Test of storeFilterGroup method, of class FilterAccessor.
     */
    @Test
    public void testStoreFilterGroup() {
        System.out.println("storeFilterGroup");
        FilterGroup filterGroup = new FilterGroup();
        FilterAccessor.storeFilterGroup(filterGroup);
        Object res = HibernateUtil.getObject(FilterGroup.class, filterGroup.getFilterGroupId());
        assertNotNull("filter group not stored", res);
        HibernateUtil.deleteObject(filterGroup);
    }

    /**
     * Test of getFilterGroupNamesbyFilterId method, of class FilterAccessor.
     */
    @Test
    public void testGetFilterGroupNamesbyFilterId() {
        System.out.println("getFilterGroupNamesbyFilterId");
        FilterGroup filterGroup = (FilterGroup) HibernateUtil.getObjectWithQuery("from FilterGroup fg where fg.filterGroupId=(select min(filterGroupId) from FilterGroup)");
        if (filterGroup.getFilterCollection() != null && filterGroup.getFilterCollection().size() > 0) {
            List<String> result = FilterAccessor.getFilterGroupNamesbyFilterId(filterGroup.getFilterCollection().iterator().next().getFilterId());
            assertNotNull("filter group list not found", result);
            assertFalse("filter group list not found", result.isEmpty());
        }
    }

    /**
     * Test of getQueryFromFilter method, of class FilterAccessor.
     */
    @Test
    public void testGetQueryFromFilter() {
        System.out.println("getQueryFromFilter");
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId=(select min(tradeId) from Trade)");
        Filter filter = new Filter();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setColumnName("tradeId");
        filterCriteria.setColumnType(Integer.class.getName());
        filterCriteria.setFilter(filter);
        filterCriteria.setGetter("getTradeId");
        filterCriteria.setNotIn(false);
        filterCriteria.setParam("");
        filterCriteria.setReturnType(Integer.class.getName());
        filterCriteria.setValue(trade.getTradeId().toString());
        filter.getFilterCriteria().add(filterCriteria);
        String result = FilterAccessor.getQueryFromFilter(filter, Trade.class);
        assertFalse("error in filter query generation", result.isEmpty());
    }

}
