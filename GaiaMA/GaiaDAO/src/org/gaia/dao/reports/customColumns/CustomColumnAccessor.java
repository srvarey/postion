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
package org.gaia.dao.reports.customColumns;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.Mapping;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Benjamin Frerejean
 */
public class CustomColumnAccessor {

    private static final Logger logger = Logger.getLogger(CustomColumnAccessor.class);
    private static final String CUSTOM_COLUMNS_MAPPING = "CustomColumns";

    public static final String GET_CUSTOM_COLUMNS_TREE = "getCustomColumnsTree";

    /**
     * get the tree of custom columns
     *
     * @param objectType
     * @param columnClass
    .
     * @return
     */
    public static IntrospectTree.IntrospectNode getCustomColumnsTree(Class objectType, String columnClass) {
        try {
            List<CustomColumn> list = CustomColumnAccessor.getCustomColumnsByObjectTypeAndClass(objectType.getName(), columnClass);
            list.addAll(getStaticCustomColumns());
            IntrospectTree tree = new IntrospectTree();
            IntrospectTree.ClassNode ccRoot = tree.new ClassNode("getCustomColumns", 1, "");
            for (CustomColumn customColumn : list) {
                IntrospectTree.IntrospectNode node = tree.new FieldNode("get" + customColumn.getName(), 1, "");
                node.setResultType(customColumn.getType());
                node.setColumnType(TemplateColumnItem.COLUMN_CUSTOM);
                node.setIsConversion(Boolean.FALSE);
                ccRoot.add(node);
            }
            return ccRoot;
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
            return null;
        }
    }

    /**
     * get the list of static custom columns
     *
     * @return
     */
    public static List<CustomColumn> getStaticCustomColumns() {
        List<CustomColumn> list = new ArrayList();
        try {
            List<Mapping> mappings = MappingsAccessor.getMappingsByName(CUSTOM_COLUMNS_MAPPING);
            for (Mapping mapping : mappings) {
                Class clazz = Class.forName(mapping.getValue());
                if (clazz != null && IStaticCustomColumn.class.isAssignableFrom(clazz)) {
                    Object obj = clazz.newInstance();
                    list.add(((IStaticCustomColumn) obj).getCustomColumn());
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
            return null;
        }
        return list;
    }
    public static final String GET_CUSTOM_COLUMN_BY_OBJECT_TYPE_AND_CLASS = "getCustomColumnsByObjectTypeAndClass";

    /**
     * get the list of custom columns by object type and class
     *
     * @param objectType
     * @param columnClass
     * @return
     */
    public static List<CustomColumn> getCustomColumnsByObjectTypeAndClass(String objectType, String columnClass) {
        ArrayList<CustomColumn> list;
        if (columnClass == null) {
            list = (ArrayList) HibernateUtil.getObjectsWithQueryWithCache("from CustomColumn where object_type='" + objectType + "' order by name");
        } else {
            list = (ArrayList) HibernateUtil.getObjectsWithQueryWithCache("from CustomColumn where object_type='" + objectType + "' and class_name='" + columnClass + "' order by name");
        }
        return list;
    }
    public static final String DELETE_CUSTOM_COLUMN = "deleteCustomColumn";

    public static void deleteCustomColumn(CustomColumn customColumn) {
        HibernateUtil.deleteObject(customColumn);
    }

    public static final String DELETE_CUSTOM_COLUMN_DETAIL = "deleteCustomColumnDetail";

    public static void deleteCustomColumnDetail(String customColumnName, String columnDetailName, String suffix) {
        CustomColumnDetail customColumnDetail = CustomColumnAccessor.getCustomColumnDetailByNames(customColumnName, columnDetailName, suffix);
        if (customColumnDetail != null) {
            HibernateUtil.deleteObject(customColumnDetail);
        }
    }
    public static final String GET_CUSTOM_COLUMN_BY_NAME = "getCustomColumnByName";

    /**
     * get a custom column
     *
     * @param name
     * @return
     */
    public static CustomColumn getCustomColumnByName(String name) {
        // no query cache : name is the key
        CustomColumn ret = (CustomColumn) HibernateUtil.getObjectWithQuery("from CustomColumn where name='" + name + StringUtils.QUOTE);
        if (ret == null) {
            // look for static custom columns
            try {
                Mapping mapping = MappingsAccessor.getMappingByNameAndKey(CUSTOM_COLUMNS_MAPPING, name);
                if (mapping != null) {
                    Class clazz = Class.forName(mapping.getValue());
                    if (clazz != null) {
                        Object obj = clazz.newInstance();
                        if (obj instanceof IStaticCustomColumn) {
                            CustomColumn tmp = ((IStaticCustomColumn) obj).getCustomColumn();
                            if (tmp.getName().equalsIgnoreCase(name)) {
                                return tmp;
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
                return null;
            }
        }
        return ret;
    }

    public static final String GET_CUSTOM_COLUMN_DETAIL_BY_NAMES = "getCustomColumnDetailByNames";

    /**
     * get custom column details
     *
     * @param customColumnName
     * @param columnDetailName
     * @param suffix
     * @return
     */
    public static CustomColumnDetail getCustomColumnDetailByNames(String customColumnName, String columnDetailName, String suffix) {
        boolean isFirstDate=false;
        boolean isEvol=false;
        if (suffix!=null && !suffix.isEmpty()){
            switch (suffix) {
                case ReportTemplate.FIRSTDATE_SUFFIX:
                    isFirstDate=true;
                    break;
                case ReportTemplate.EVOLUTION_SUFFIX:
                    isEvol=true;
                    break;
            }
        }
        return (CustomColumnDetail) HibernateUtil.getObjectWithQueryWithCache("from CustomColumnDetail where custom_column_name='" + customColumnName + "' and column_name='" + columnDetailName + "' and is_evol='"+isEvol+"' and is_first_date='"+isFirstDate+StringUtils.QUOTE);
    }
    public static final String GET_CUSTOM_COLUMN_DETAIL_BY_FILTER_ID = "getCustomColumnDetailByFilterId";

    /**
     * get a custom column detail
     *
     * @param customColumnName
     * @param filterId
     * @return
     */
    public static CustomColumnDetail getCustomColumnDetailByFilterId(String customColumnName, Integer filterId) {
        return (CustomColumnDetail) HibernateUtil.getObjectWithQueryWithCache("from CustomColumnDetail where custom_column_name='" + customColumnName + "' and filter_id=" + filterId);
    }
    public static final String GET_CUSTOM_COLUMN_DETAIL_BY_ID = "getCustomColumnDetailById";

    /**
     * get a custom column detail
     * @param customDetailId
     * @param filterId
     * @return
     */
    public static CustomColumnDetail getCustomColumnDetailById(Integer customDetailId) {
        return (CustomColumnDetail) HibernateUtil.getObjectWithQueryWithCache("from CustomColumnDetail where custom_column_detail_id=" + customDetailId);
    }


    public static final String GET_SELECTOR_COLUMN_LIST = "getSelectorColumnList";

    /**
     * get the list of selector columns
     *
     * @return
     */
    public static List<String> getSelectorColumnList() {
        return HibernateUtil.getObjectsWithQuery("select name from CustomColumn where class_name='" + SelectorCustom.class.getName() + StringUtils.QUOTE);
    }
    public static final String GET_COLLAT_SELECTOR_COLUMN_LIST = "getSelectorColumnList";

    /**
     * get the list of selector columns
     *
     * @return
     */
    public static List<String> getCollatSelectorColumnList() {
        return HibernateUtil.getObjectsWithQueryWithCache("select name from CustomColumn where class_name='" + SelectorCustom.class.getName() + "' and category='Collateral'");
    }
    public static final String GET_FORMULA_COLUMN_LIST_BY_OBJECT_TYPE = "getFormulaColumnList";

    /**
     * get the list of formula columns
     *
     * @param objectType
     * @return
     */
    public static List<String> getFormulaColumnList(Class objectType) {
        return HibernateUtil.getObjectsWithQueryWithCache("select cc.name from CustomColumn cc where cc.className='" + FormulaCustom.class.getName() + "' and cc.objectType='" + objectType.getName() + StringUtils.QUOTE);
    }
    public static final String GET_SELECTOR_COLUMN_LIST_BY_OBJECT_TYPE = "getSelectorColumnList";

    /**
     * get the list of selector columns
     *
     * @param objectType
     * @return
     */
    public static List<String> getSelectorColumnList(Class objectType) {
        return HibernateUtil.getObjectsWithQueryWithCache("select cc.name from CustomColumn cc where cc.className='" + SelectorCustom.class.getName() + "' and cc.objectType='" + objectType.getName() + StringUtils.QUOTE);
    }
    public static final String STORE_CUSTOM_COLUMN = "storeCustomColumn";

    /**
     * store a column columns
     *
     * @param customColumn
     */
    public static void storeCustomColumn(CustomColumn customColumn) {
        HibernateUtil.storeObject(customColumn);
    }
    public static final String STORE_CUSTOM_COLUMN_DETAIL = "storeCustomColumnDetail";

    /**
     * store a column column detail
     *
     * @param customColumnDetail
     */
    public static void storeCustomColumnDetail(CustomColumnDetail customColumnDetail) {
        HibernateUtil.storeObject(customColumnDetail);
    }
    public static final String GET_FORMULA_COLUMN_LIST = "getFormulaColumnList";

    /**
     * get the formula columns list
     *
     * @return
     */
    public static List<String> getFormulaColumnList() {
        return HibernateUtil.getObjectsWithQueryWithCache("select name from CustomColumn where className='" + FormulaCustom.class.getName() + StringUtils.QUOTE);
    }

    /**
     * gets column columns by category
     *
     * @param categoty
     * @return
     */
    public static List<CustomColumn> getCustomColumnListByCategory(String categoty) {
        List<CustomColumn> res;
        Session session = HibernateUtil.getSession();
        Query queryColumns = session.getNamedQuery("CustomColumn.findByCategory");
        queryColumns.setString("category", categoty);
        res = queryColumns.list();
        return res;
    }
    public static final String GET_CUSTOM_COLUMN_NAMES_BY_FILTER_ID = "getCustomColumnNamesbyFilterId";

    /**
     * return filter name list
     * @param filterid
     * @return
     */
    public static List<String> getCustomColumnNamesbyFilterId(Integer filterid) {
        return HibernateUtil.getObjectsWithQuery("select cc.name from CustomColumn cc inner join cc.customColumnDetails ccd inner join ccd.filter f where f.filterId=" + filterid);
    }
}
