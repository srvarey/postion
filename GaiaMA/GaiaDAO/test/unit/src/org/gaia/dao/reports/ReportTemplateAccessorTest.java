package org.gaia.dao.reports;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ReportTemplateAccessorTest extends AbstractTest {

    public ReportTemplateAccessorTest() {
    }

    /**
     * Test of getMeasureTemplateColumnItemsByName method, of class
     * ReportTemplateAccessor.
     */
    @Test
    public void testGetMeasureTemplateColumnItemsByName() {
        System.out.println("getMeasureTemplateColumnItemsByName");
        TemplateColumnItem column = (TemplateColumnItem) HibernateUtil.getObjectWithQuery("from TemplateColumnItem col where col.columnItemId=(select min(columnItemId) from TemplateColumnItem where columnType='" + TemplateColumnItem.COLUMN_MEASURE + "')");
        List<TemplateColumnItem> result = ReportTemplateAccessor.getMeasureTemplateColumnItemsByName(column.getName());
        assertNotNull("column not found", result);
    }

    /**
     * Test of getTemplateColumnItemById method, of class
     * ReportTemplateAccessor.
     */
    @Test
    public void testGetTemplateColumnItemById() {
        System.out.println("getTemplateColumnItemById");
        TemplateColumnItem column = (TemplateColumnItem) HibernateUtil.getObjectWithQuery("from TemplateColumnItem col where col.columnItemId=(select min(columnItemId) from TemplateColumnItem)");
        TemplateColumnItem result = ReportTemplateAccessor.getTemplateColumnItemById(column.getColumnItemId());
        assertNotNull("column not found", result);
    }

    /**
     * Test of getReportTemplateByNameAndType method, of class
     * ReportTemplateAccessor.
     */
    @Test
    public void testGetReportTemplateByNameAndType() {
        System.out.println("getReportTemplateByNameAndType");
        ReportTemplate template = (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate rt where rt.templateId=(select min(templateId) from ReportTemplate)");
        ReportTemplate result = ReportTemplateAccessor.getReportTemplateByNameAndType(template.getTemplateName(), template.getObjectTypeClass());
        assertNotNull("report template not found", result);
    }

    /**
     * Test of getReportTemplateById method, of class ReportTemplateAccessor.
     */
    @Test
    public void testGetReportTemplateById() {
        System.out.println("getReportTemplateById");
        ReportTemplate template = (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate rt where rt.templateId=(select min(templateId) from ReportTemplate)");
        ReportTemplate result = ReportTemplateAccessor.getReportTemplateById(template.getTemplateId());
        assertNotNull("report template not found", result);
    }

    /**
     * Test of getTemplateList method, of class ReportTemplateAccessor.
     */
    @Test
    public void testGetTemplateList() {
        System.out.println("getTemplateList");
        ReportTemplate template = (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate rt where rt.templateId=(select min(templateId) from ReportTemplate)");
        List<String> result;
        result = ReportTemplateAccessor.getTemplateList(template.getObjectTypeClass());
        assertNotNull("report template not found", result);
        assertFalse("report template not found", result.isEmpty());
    }

    /**
     * Test of deleteTemplate method, of class ReportTemplateAccessor.
     */
    @Test
    public void testDeleteTemplate() {
        System.out.println("deleteTemplate");
        ReportTemplate template = new ReportTemplate();
        template.setTemplateName("UNIT TEST");
        template.setObjectTypeClass(Trade.class);
        HibernateUtil.saveObject(template);
        ReportTemplateAccessor.deleteTemplate(template.getTemplateName(), template.getObjectTypeClass());
        Object result = HibernateUtil.getObject(ReportTemplate.class, template.getTemplateId());
        assertNull("report template not deleted", result);
    }

    /**
     * Test of isACustomColumUsedInTemplates method, of class
     * ReportTemplateAccessor.
     */
    @Test
    public void testIsACustomColumUsedInTemplates() {
        System.out.println("getTemplateColumnItemsNoForACustomColumn");
        CustomColumn column = (CustomColumn) HibernateUtil.getObjectWithQuery("from CustomColumn col where col.name=(select min(name) from CustomColumn where name in (select name from TemplateColumnItem))");
        Boolean result = ReportTemplateAccessor.isACustomColumUsedInTemplates(column.getName());
        assertTrue("failed checking column use", result);
    }

    /**
     * Test of storeReportTemplate method, of class ReportTemplateAccessor.
     */
    @Test
    public void testStoreReportTemplate() {
        System.out.println("storeReportTemplate");
        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setTemplateName("UNIT TEST");
        reportTemplate.setObjectTypeClass(Trade.class);
        ReportTemplateAccessor.storeReportTemplate(reportTemplate);
        Object res = HibernateUtil.getObject(ReportTemplate.class, reportTemplate.getTemplateId());
        assertNotNull("failed storing template", res);
        HibernateUtil.deleteObject(reportTemplate);
    }

    /**
     * Test of saveReportTemplate method, of class ReportTemplateAccessor.
     */
    @Test
    public void testSaveReportTemplate() {
        System.out.println("saveReportTemplate");
        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setTemplateName("UNIT TEST");
        reportTemplate.setObjectTypeClass(Trade.class);
        ReportTemplateAccessor.saveReportTemplate(reportTemplate);
        Object res = HibernateUtil.getObject(ReportTemplate.class, reportTemplate.getTemplateId());
        assertNotNull("failed storing template", res);
        HibernateUtil.deleteObject(reportTemplate);
    }

    /**
     * Test of storeTemplateColumnItem method, of class ReportTemplateAccessor.
     */
    @Test
    public void testStoreTemplateColumnItem() {
        System.out.println("storeTemplateColumnItem");
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        ReportTemplateAccessor.storeTemplateColumnItem(templateColumnItem);
        Object res = HibernateUtil.getObject(TemplateColumnItem.class, templateColumnItem.getColumnItemId());
        assertNotNull("failed storing template column", res);
        HibernateUtil.deleteObject(templateColumnItem);
    }

    /**
     * Test of deleteTemplateColumnItem method, of class ReportTemplateAccessor.
     */
    @Test
    public void testDeleteTemplateColumnItem() {
        System.out.println("deleteTemplateColumnItem");
        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
        HibernateUtil.saveObject(templateColumnItem);
        ReportTemplateAccessor.deleteTemplateColumnItem(templateColumnItem);
        Object res = HibernateUtil.getObject(TemplateColumnItem.class, templateColumnItem.getColumnItemId());
        assertNull("failed to delete template column", res);
    }

    /**
     * Test of getTemplateNamesbyFilterid method, of class
     * ReportTemplateAccessor.
     */
    @Test
    public void testGetTemplateNamesbyFilterid() {
        System.out.println("getTemplateNamesbyFilterid");
        ReportTemplate template = (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate rt where rt.templateId=(select min(templateId) from ReportTemplate where filter is not null)");
        List<String> result = ReportTemplateAccessor.getTemplateNamesbyFilterid(template.getFilter().getFilterId());
        assertNotNull("template not found", result);
        assertFalse("template not found", result.isEmpty());
    }

}
