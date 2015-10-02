package org.gaia.dao.reports.customColumns;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class CustomColumnAccessorTest extends AbstractTest {

    public CustomColumnAccessorTest() {
    }

    /**
     * Test of getCustomColumnsTree method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnsTree() {
        System.out.println("getCustomColumnsTree");
        IntrospectTree.IntrospectNode result = CustomColumnAccessor.getCustomColumnsTree(Position.class, FormulaCustom.class.getName());
        assertNotNull("null return", result);
    }

    /**
     * Test of getStaticCustomColumns method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetStaticCustomColumns() {
        System.out.println("getStaticCustomColumns");
        List<CustomColumn> result = CustomColumnAccessor.getStaticCustomColumns();
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getCustomColumnsByObjectTypeAndClass method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnsByObjectTypeAndClass() {
        System.out.println("getCustomColumnsByObjectTypeAndClass");
        List<CustomColumn> result = CustomColumnAccessor.getCustomColumnsByObjectTypeAndClass(Position.class.getName(), FormulaCustom.class.getName());
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of deleteCustomColumn method, of class CustomColumnAccessor.
     */
    @Test
    public void testDeleteCustomColumn() {
        System.out.println("deleteCustomColumn");
        CustomColumn customColumn = new CustomColumn();
        customColumn.setName("UNIT TEST");
        HibernateUtil.saveObject(customColumn);
        CustomColumnAccessor.deleteCustomColumn(customColumn);
        Object res = HibernateUtil.getObject(CustomColumn.class, customColumn.getName());
        assertNull("failed deleting custom columns", res);
    }

    /**
     * Test of deleteCustomColumnDetail method, of class CustomColumnAccessor.
     */
    @Test
    public void testDeleteCustomColumnDetail() {
        System.out.println("deleteCustomColumnDetail");
        CustomColumn column = (CustomColumn) HibernateUtil.getObjectWithQuery("from CustomColumn col where col.name=(select min(name) from CustomColumn)");
        CustomColumnDetail detail = new CustomColumnDetail();
        detail.setCustomColumn(column);
        detail.setColumnName("UNIT TEST");
        HibernateUtil.saveObject(detail);
        CustomColumnAccessor.deleteCustomColumnDetail(column.getName(), detail.getColumnName(),"");
        Object res = HibernateUtil.getObject(CustomColumnDetail.class, detail.getCustomColumnDetailId());
        assertNull("failed deleting custom columns", res);
    }

    /**
     * Test of getCustomColumnByName method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnByName() {
        System.out.println("getCustomColumnByName");
        CustomColumn column = (CustomColumn) HibernateUtil.getObjectWithQuery("from CustomColumn col where col.name=(select min(name) from CustomColumn)");
        CustomColumn result = CustomColumnAccessor.getCustomColumnByName(column.getName());
        assertEquals("failed getting custom column", result, column);
    }

    /**
     * Test of getCustomColumnDetailByNames method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnDetailByNames() {
        System.out.println("getCustomColumnDetailByNames");
        CustomColumnDetail detail = (CustomColumnDetail) HibernateUtil.getObjectWithQuery("from CustomColumnDetail col where col.customColumnDetailId=(select min(customColumnDetailId) from CustomColumnDetail ccd )");
        CustomColumnDetail result = CustomColumnAccessor.getCustomColumnDetailByNames(detail.getCustomColumn().getName(), detail.getColumnName(),detail.getSuffix());
        assertEquals("failed getting custom column detail ", result, detail);
    }

    /**
     * Test of getCustomColumnDetailByFilterId method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnDetailByFilterId() {
        System.out.println("getCustomColumnDetailByFilterId");
        CustomColumnDetail detail = (CustomColumnDetail) HibernateUtil.getObjectWithQuery("from CustomColumnDetail col where col.customColumnDetailId=(select min(customColumnDetailId) from CustomColumnDetail ccd where ccd.filter is not null)");
        CustomColumnDetail result = CustomColumnAccessor.getCustomColumnDetailByFilterId(detail.getCustomColumn().getName(), detail.getFilter().getFilterId());
        assertEquals("failed getting custom column detail ", result, detail);
    }

    /**
     * Test of getSelectorColumnList method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetSelectorColumnList_0args() {
        System.out.println("getSelectorColumnList");
        List<String> result = CustomColumnAccessor.getSelectorColumnList();
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getCollatSelectorColumnList method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCollatSelectorColumnList() {
        System.out.println("getCollatSelectorColumnList");
        List<String> result = CustomColumnAccessor.getCollatSelectorColumnList();
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getFormulaColumnList method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetFormulaColumnList_String() {
        System.out.println("getFormulaColumnList");
        List<String> result = CustomColumnAccessor.getFormulaColumnList(Trade.class);
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getSelectorColumnList method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetSelectorColumnList_String() {
        System.out.println("getSelectorColumnList");
        List<String> result = CustomColumnAccessor.getSelectorColumnList(Trade.class);
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of storeCustomColumn method, of class CustomColumnAccessor.
     */
    @Test
    public void testStoreCustomColumn() {
        System.out.println("storeCustomColumn");
        CustomColumn customColumn = new CustomColumn();
        customColumn.setName("UNIT TEST");
        CustomColumnAccessor.storeCustomColumn(customColumn);
        Object res = HibernateUtil.getObject(CustomColumn.class, customColumn.getName());
        assertNotNull("failed to store custom column", res);
        HibernateUtil.deleteObject(customColumn);
    }

    /**
     * Test of storeCustomColumnDetail method, of class CustomColumnAccessor.
     */
    @Test
    public void testStoreCustomColumnDetail() {
        System.out.println("storeCustomColumnDetail");
        CustomColumnDetail customColumnDetail = new CustomColumnDetail();
        CustomColumn column = (CustomColumn) HibernateUtil.getObjectWithQuery("from CustomColumn col where col.name=(select min(name) from CustomColumn)");
        customColumnDetail.setCustomColumn(column);
        customColumnDetail.setColumnName("UNIT TEST");
        CustomColumnAccessor.storeCustomColumnDetail(customColumnDetail);
        Object res = HibernateUtil.getObject(CustomColumnDetail.class, customColumnDetail.getCustomColumnDetailId());
        assertNotNull("failed to store custom column detail", res);
        HibernateUtil.deleteObject(customColumnDetail);
    }

    /**
     * Test of getFormulaColumnList method, of class CustomColumnAccessor.
     */
    @Test
    public void testGetFormulaColumnList_0args() {
        System.out.println("getFormulaColumnList");
        List<String> result = CustomColumnAccessor.getFormulaColumnList();
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getCustomColumnListByCategory method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnListByCategory() {
        System.out.println("getCustomColumnListByCategory");
        List<CustomColumn> result = CustomColumnAccessor.getCustomColumnListByCategory("Standard");
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

    /**
     * Test of getCustomColumnNamesbyFilterId method, of class
     * CustomColumnAccessor.
     */
    @Test
    public void testGetCustomColumnNamesbyFilterId() {
        System.out.println("getCustomColumnNamesbyFilterId");
        CustomColumnDetail detail = (CustomColumnDetail) HibernateUtil.getObjectWithQuery("from CustomColumnDetail col where col.customColumnDetailId=(select min(customColumnDetailId) from CustomColumnDetail ccd where ccd.filter is not null)");
        List<String> result = CustomColumnAccessor.getCustomColumnNamesbyFilterId(detail.getFilter().getFilterId());
        assertNotNull("missing custom columns", result);
        assertFalse("missing custom columns", result.isEmpty());
    }

}
