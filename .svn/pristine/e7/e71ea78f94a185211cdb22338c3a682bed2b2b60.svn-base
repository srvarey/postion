/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.reports;

import java.util.Collection;
import java.util.List;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class ReportTemplateAccessor {

    public static final String GET_MEASURE_TEMPLATE_COLUMN_ITEMS_BY_NAME = "getMeasureTemplateColumnItemsByName";

    /**
     * get templateColumnItems list by name
     *
     * @param name
     * @return
     */
    public static List<TemplateColumnItem> getMeasureTemplateColumnItemsByName(String name) {
        return (List) HibernateUtil.getObjectsWithQuery("from TemplateColumnItem where name like '" + name + "%' and column_type='" + TemplateColumnItem.COLUMN_MEASURE + StringUtils.QUOTE);
    }
    /**
     * getTemplateColumnItemById
     */
    public static final String GET_TEMPLATE_COLUMN_ITEM_BY_ID = "getTemplateColumnItemById";

    public static TemplateColumnItem getTemplateColumnItemById(Integer id) {
        if (id != null) {
            return (TemplateColumnItem) HibernateUtil.getObject(TemplateColumnItem.class, id);
        }
        return null;
    }
    /**
     * getReportTemplateByNameAndType function.
     */
    public static final String GET_REPORT_TEMPLATE_BY_NAME_AND_TYPE = "getReportTemplateByNameAndType";

    public static ReportTemplate getReportTemplateByNameAndType(String name, Class objectType) {
        return (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate where template_name='" + name + "' and object_type='" + objectType.getName() + StringUtils.QUOTE);
    }
    /**
     * getReportTemplateByNameAndType function.
     */
    public static final String GET_REPORT_TEMPLATE_BY_ID = "getReportTemplateById";

    public static ReportTemplate getReportTemplateById(Integer templateId) {
        return (ReportTemplate) HibernateUtil.getObjectWithQuery("from ReportTemplate where template_id=" + templateId);
    }
    /**
     * getTemplateList function.
     *
     */
    public static final String GET_TEMPLATE_LIST = "getTemplateList";

    public static List<String> getTemplateList(Class objectType) {
        return HibernateUtil.getObjectsWithQuery("select rt.templateName from ReportTemplate rt where rt.objectType='" + objectType.getName() + "' order by rt.templateName asc");
    }
    /**
     * deleteTemplate function.
     */
    public static final String DELETE_TEMPLATE = "deleteTemplate";

    public static void deleteTemplate(String name, Class objectType) {

        ReportTemplate template = getReportTemplateByNameAndType(name, objectType);
        Collection<TemplateColumnItem> templateColumn = template.getTemplateColumnItems();
        if (templateColumn != null) {
            for (TemplateColumnItem templ : templateColumn) {
                HibernateUtil.deleteObject(templ);
            }
        }
        HibernateUtil.deleteObject(template);

    }
    /**
     * isACustomColumUsedInTemplates .
     */
    public static final String IS_A_CUSTOM_COLUMN_USED_IN_TEMPLATES = "isACustomColumUsedInTemplates";

    public static Boolean isACustomColumUsedInTemplates(String colName) {
        List<TemplateColumnItem> list = (List<TemplateColumnItem>) HibernateUtil.getObjectsWithQuery("from TemplateColumnItem where name='" + colName + "' and column_type='" + TemplateColumnItem.COLUMN_CUSTOM + StringUtils.QUOTE);
        return Boolean.valueOf(list.size() > 0);
    }
    /**
     * storeReportTemplate .
     *
     */
    public static final String STORE_REPORT_TEMPLATE = "storeReportTemplate";

    public static void storeReportTemplate(ReportTemplate reportTemplate) {
        HibernateUtil.storeObject(reportTemplate);
    }
    /**
     * storeTemplateColumnItem
     */
    public static final String SAVE_REPORT_TEMPLATE = "saveReportTemplate";

    public static ReportTemplate saveReportTemplate(ReportTemplate reportTemplate) {
        Integer id = (Integer) HibernateUtil.saveObject(reportTemplate);
        reportTemplate.setTemplateId(id);
        return reportTemplate;
    }
    /**
     * storeTemplateColumnItem
     */
    public static final String STORE_TEMPLATE_COLUMN_ITEM = "storeTemplateColumnItem";

    public static void storeTemplateColumnItem(TemplateColumnItem templateColumnItem) {
        HibernateUtil.storeObject(templateColumnItem);
    }
    /**
     * deleteTemplateColumnItem
     */
    public static final String DELETE_TEMPLATE_COLUMN_ITEM = "deleteTemplateColumnItem";

    public static void deleteTemplateColumnItem(TemplateColumnItem templateColumnItem) {
        HibernateUtil.deleteObject(templateColumnItem);
    }
    public static final String GET_TEMPLATE_NAMES_BY_FILTER_ID = "getTemplateNamesbyFilterid";

    /**
     * return names of filter .
     *
     * @param objectType
     * @return
     */
    public static List<String> getTemplateNamesbyFilterid(int filterId) {
        return HibernateUtil.getObjectsWithQuery("select rt.templateName from ReportTemplate rt inner join rt.filter f where f.filterId=" + filterId);
    }

}
