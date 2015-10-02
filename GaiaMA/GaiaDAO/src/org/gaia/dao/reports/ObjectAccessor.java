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

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.audit.AuditAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author Benjamin Frerejean
 */
public class ObjectAccessor {

    private static final Logger logger = Logger.getLogger(ObjectAccessor.class);

    public static String buildReportQuery(
            LinkedHashMap<Integer, ReportLine> reportLines,
            Class objectType,
            Filter filter,
            Collection<TemplateColumnItem> sortingOrder) {


        String table = IntrospectUtil.getDBTableName(objectType);
        table = table.substring(table.lastIndexOf(StringUtils.DOT) + 1);
        String rootAlias = table.substring(0, 1).toLowerCase() + table.substring(1);
        String dbField = StringUtils.EMPTY_STRING;
        int i = 0;
        String query = null;
        HashMap<String, Short> tables = new HashMap();

        /**
         * add fields from columns to the select and order by parts
         */
        try {
            String orderBy = " order by ";
            String select = "select ";
            if (sortingOrder != null && !sortingOrder.isEmpty()) {
                for (TemplateColumnItem templateColumnItem : sortingOrder) {
                    if (templateColumnItem.getColumnType().equals(TemplateColumnItem.COLUMN_STANDARD)) {
                        String tableAlias = getTableAliasForFields(templateColumnItem.getGetter(), objectType, templateColumnItem.getParam());
                        String dbfield = getDBFieldName(templateColumnItem.getGetter(), objectType, templateColumnItem.getParam());
                        String fieldAlias = getAliasForField(templateColumnItem.getGetter(), objectType, templateColumnItem.getParam()) + "_" + i;
                        if (dbfield != null && !dbfield.isEmpty()) {
                            select = select + tableAlias + StringUtils.DOT + dbfield + " as " + fieldAlias + StringUtils.COMMA;
                            orderBy = orderBy + tableAlias + StringUtils.DOT + dbfield + StringUtils.COMMA;
                        } else {
                            select = select + "'' as " + fieldAlias + StringUtils.COMMA;
                            logger.error("Error : field not found : " + objectType + StringUtils.DOT + templateColumnItem.getGetter());
                        }
                        if (!tables.containsKey(tableAlias)) {
                            tables.put(tableAlias, Short.MIN_VALUE);
                            if (dbfield != null && dbfield.equalsIgnoreCase("rating")) {
                                String tableAlias2 = tableAlias.replace("Rating", "ler");
                                tables.put(tableAlias2, Short.MIN_VALUE);
                            }
                        }
                    }
                    i++;
                }
            } else {
                return null;
            }
            /**
             * remove the coma
             */
            select = select.substring(0, select.length() - 1);
            orderBy = orderBy.substring(0, orderBy.length() - 1);

            /**
             * we look in the tree to add correspounding tables .
             */
            String queryConditions;
            String joins = StringUtils.EMPTY_STRING;
            IntrospectTree tree = new IntrospectTree(objectType);
            IntrospectTree.IntrospectNode root = tree.getClassTree(null, false);

            /**
             * then manage conditions.
             */
            if (select.contains("Product_SecondLeg")) {
                queryConditions = " where (Product_SecondLeg.product_id>=Product_FirstLeg.product_id or Product_FirstLeg.product_id is null or (Product_FirstLeg.product_id is not null and Product_SecondLeg.product_id is null)) ";
            } else {
                queryConditions = " where 1=1 ";
            }
            if (filter != null && filter.getFilterCriteria() != null) {
                for (FilterCriteria filterCriteria : filter.getFilterCriteria()) {
                    if (filterCriteria.getColumnName() != null && filterCriteria.getReturnType() != null && !filterCriteria.isPostTreatment()) {

                        String tableAlias = getTableAliasForFields(filterCriteria.getGetter(), objectType, filterCriteria.getParam());
                        if (!tables.containsKey(tableAlias)) {
                            tables.put(tableAlias, Short.MIN_VALUE);
                        }
                        if (tables.containsKey(tableAlias)) {
                            dbField = getDBFieldName(filterCriteria.getGetter(), objectType, filterCriteria.getParam());

                            if ((filterCriteria.getValue() != null && !filterCriteria.getValue().isEmpty())
                                    || (filterCriteria.getValue2() != null && !filterCriteria.getValue2().isEmpty())) {
                                String inNotIn;
                                if (filterCriteria.getNotIn()) {
                                    inNotIn = " not in ";
                                } else {
                                    inNotIn = " in ";
                                }
                                if (filterCriteria.getValue() != null && filterCriteria.getValue().equalsIgnoreCase("null")) {
                                    if (filterCriteria.getNotIn()) {
                                        queryConditions = queryConditions + " and " + tableAlias + StringUtils.DOT + dbField + " is not null";
                                    } else {
                                        queryConditions = queryConditions + " and " + tableAlias + StringUtils.DOT + dbField + " is null";
                                    }
                                } else if (filterCriteria.getReturnType().equals(Date.class.getName())) {
                                    Date date = DateUtils.removeTime(new Date());

                                    if (filterCriteria.getValue() != null && !filterCriteria.getValue().isEmpty()) {

                                        Date dateRef;
                                        try {
                                            /**
                                             * if day +
                                             */
                                            Integer n = Integer.parseInt(filterCriteria.getValue());
                                            dateRef = DateUtils.addOpenDay(date, n);
                                        } catch (Exception e) {
                                            /**
                                             * if date.
                                             */
                                            dateRef = HibernateUtil.dateFormat.parse(filterCriteria.getValue());
                                        }
                                        if (date != null) {
                                            queryConditions = queryConditions + " and (" + tableAlias + StringUtils.DOT + dbField + ">='" + HibernateUtil.dateFormat.format(dateRef) + "')";

                                        }
                                    }
                                    if (!StringUtils.isEmptyString(filterCriteria.getValue2())) {//
                                        Date dateRef;
                                        try {
                                            /**
                                             * if day + .
                                             */
                                            Integer n = Integer.parseInt(filterCriteria.getValue2());
                                            dateRef = DateUtils.addOpenDay(date, n);
                                        } catch (Exception e) {
                                            /**
                                             * if date .
                                             */
                                            dateRef = HibernateUtil.dateFormat.parse(filterCriteria.getValue2());
                                        }
                                        if (date != null) {
                                            queryConditions = queryConditions + " and (" + tableAlias + StringUtils.DOT + dbField + " is null or " + tableAlias + StringUtils.DOT + dbField + "<='" + HibernateUtil.dateFormat.format(dateRef) + "')";
                                        }
                                    }
                                } else if (filterCriteria.getReturnType().equals(String.class.getName())) {
                                    queryConditions = queryConditions + " and " + tableAlias + StringUtils.DOT + dbField + inNotIn + "('" + filterCriteria.getValue().replaceAll(StringUtils.COMMA, "','") + "')";//
                                } else {
                                    queryConditions = queryConditions + " and " + tableAlias + StringUtils.DOT + dbField + inNotIn + "(" + filterCriteria.getValue() + ")";
                                }
                            }
                        }
                    }
                }
            }
            // get joins with tables from fields and filters
            joins = getJoinsFromTree(joins, root, rootAlias, tables);

            // final build of the query
            query = select + " from " + table + StringUtils.SPACE + rootAlias + joins + queryConditions + orderBy;
        } catch (ParseException | HibernateException e) {
            logger.error("error " + StringUtils.formatErrorMessage(e));
        }
        return query;
    }

    public static void fillReportLines(
            LinkedHashMap<Integer, ReportLine> reportLines,
            List list,
            Collection<TemplateColumnItem> sortingOrder,
            boolean isFirstDate) {
        int i;
        try {
            if (list.size() > 0) {
                Object test = list.get(0);
                if (test instanceof Integer) {
                    // deprecated case when the only column coming out of the query is the id
                    // happends when no column are selected
                    for (Object object : list) {
                        fillLine(reportLines,new Object[]{object},sortingOrder,isFirstDate,1);
                    }
                } else {
                    // usual case
                    //===============================
                    for (Object object : list) {
                        Object[] objects = (Object[]) object;
                        fillLine(reportLines,objects,sortingOrder,isFirstDate,1);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("error " + StringUtils.formatErrorMessage(e));
        }
    }

    public static Integer fillLine(
            LinkedHashMap<Integer, ReportLine> reportLines,
            Object[] objects,
            Collection<TemplateColumnItem> sortingOrder,
            boolean isFirstDate,
            int idIndex) {
        Integer id;
        if (objects[objects.length - idIndex] instanceof Integer) {
            id = (Integer) objects[objects.length - idIndex];
        } else if (objects[objects.length - idIndex] instanceof BigInteger) {
            id = ((BigInteger) objects[objects.length - idIndex]).intValue();
        } else {
            logger.error("ERROR in report generation : missing id at the end of lines. ObjectAccessor.getReportLinesWithFilter.251");
            return null;
        }

        ReportLine line = reportLines.get(id);
        if (line == null) {
            line = new ReportLine();
            line.setId(id);
        }

        HashMap objectMap = new HashMap();
        int i = 0;
        for (TemplateColumnItem templateColumnItem : sortingOrder) {
            if (templateColumnItem.getColumnType().equals(TemplateColumnItem.COLUMN_STANDARD)) {
                if (StringUtils.isEmptyString(templateColumnItem.getParam())) {
                    objectMap.put(templateColumnItem.getName(), objects[i]);
                } else {
                    objectMap.put(templateColumnItem.getName() + StringUtils.DOT + templateColumnItem.getParam(), objects[i]);
                }
                i++;
            } else {
                objectMap.put(templateColumnItem.getName(), null);
            }
        }

        if (!isFirstDate) {
            line.setObjectMap(objectMap);
        } else {
            line.setObjectMapFirst(objectMap);
        }
        reportLines.put(id, line);
        return id;
    }


    public static String getDBFieldName(String getter, Class rootClass, String param) {
        String dbField;
        Class clazz = getTableFromGetter(getter, rootClass);
        String field = getTableName(getter);
        dbField = IntrospectUtil.getDBFieldName(clazz.getName(), field);
        if (param != null && !param.isEmpty()) {
            String attr = getter.replace("get", "");
            attr = attr.substring(attr.lastIndexOf("/") + 1);
            switch (attr) {
                case "Rating":
                    dbField = "rating";
                    break;
                case "ProductReference":
                    dbField = "value";
                    break;
            }
        }
        return dbField;
    }

    public static Class getTableFromGetter(String getter, Class rootClass) {

        String tmp = getter;
        int index = getter.indexOf("/");
        try {
            while (index > 0) {
                String tab = tmp.substring(0, index);
                Method method;
                if (tab.equals("getPositionHistory")) {
                    method = rootClass.getMethod(tab, Date.class);
                } else {
                    method = rootClass.getMethod(tab, (Class[]) null);
                }
                rootClass = method.getReturnType();
                tmp = tmp.substring(index + 1);
                index = tmp.indexOf("/");
            }
            return rootClass;
        } catch (NoSuchMethodException | SecurityException e) {
            logger.error("error" + StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static String getAliasForField(String column, Class objectType, String parameter) {

        String alias = getTableAliasForFields(column, objectType, parameter);
        String dbfield = getDBFieldName(column, objectType, parameter);
        alias = alias + "_" + dbfield;
        return alias;
    }

    public static String getTableAliasForFields(String getter, Class objectType, String param) {
        String ret = getter.replaceAll("get", "").replace('/', '_');
        ret = ret.replace("User", "GUser");
        if (ret.lastIndexOf("_") > 0) {
            ret = ret.substring(0, ret.lastIndexOf("_"));
        } else {
            String table = IntrospectUtil.getDBTableName(objectType);
            table = table.substring(table.lastIndexOf(StringUtils.DOT) + 1);
            ret = table.substring(0, 1).toLowerCase() + table.substring(1);
        }
        if (param != null && !param.isEmpty()) {
            String attr = getter.replace("get", "");
            attr = attr.substring(attr.lastIndexOf("/") + 1);
            switch (attr) {
                case "Rating":
                    ret = ret + "_Rating_" + StringUtils.cleanStringForTables(param);
                    break;
                case "ProductReference":
                    ret = ret + "_ProductReference_" + StringUtils.cleanStringForTables(param);
                    break;
            }
        }
        return ret;
    }

    public static String getAlias(IntrospectTree.IntrospectNode node) {
        if (!node.getUserObject().toString().isEmpty()) {
            return getAlias(node.getUserObject().toString());
        } else {
            return getAlias(node.getResultType().substring(node.getResultType().lastIndexOf(StringUtils.DOT) + 1) + StringUtils.DOT);
        }
    }

    public static String getAlias(String column) {
        String ret = column.replaceAll("get", "").replace('/', '_');
        ret = ret.replace("User", "GUser");
        return ret.substring(0, ret.length() - 1);
    }

    public static String getTableName(String column) {
        String ret = column.replaceAll("get", "").replace('/', '.');

        if (ret.lastIndexOf(StringUtils.DOT) > 0) {
            ret = ret.substring(ret.lastIndexOf(StringUtils.DOT) + 1);
        }
        return ret.substring(0, 1).toLowerCase() + ret.substring(1, ret.length());
    }

    public static String getJoinsFromTree(String query, IntrospectTree.IntrospectNode root, String rootAlias, HashMap tables) {

        for (int i = 0; i < root.getChildCount(); i++) {
            IntrospectTree.IntrospectNode node = (IntrospectTree.IntrospectNode) root.getChildAt(i);

            if (!node.isLeaf()) {
                String tableAlias = getAlias(node);
                if (tables.containsKey(tableAlias)) {
                    query = fillClassNodeJoin(query, tableAlias, root, node, rootAlias, tables);
                }
                query = getJoinsFromTree(query, node, tableAlias, tables);
            } else if (!node.getParam().isEmpty()) {

                String field = node.toString().substring(0, node.toString().lastIndexOf(StringUtils.DOT));
                String param = node.toString().substring(node.toString().lastIndexOf(StringUtils.DOT) + 1);
                String tableAlias = StringUtils.cleanStringForTables(param);
                String tableAlias2 = null;
                switch (field) {
                    case "Rating":
                        tableAlias2 = rootAlias + "_ler_" + tableAlias;
                        break;
                    case "ProductReference":
                        tableAlias2 = rootAlias + "_ProductReference_" + tableAlias;
                        break;
                }
                if (tables.containsKey(tableAlias2)) {
                    if (!tables.containsKey(rootAlias)) { // if father table lacks, add it
                        IntrospectTree.IntrospectNode parent = (IntrospectTree.IntrospectNode) node.getParent();
                        String tableAlias_ = getAlias(parent);
                        IntrospectTree.IntrospectNode superparent = (IntrospectTree.IntrospectNode) parent.getParent();
                        String rootAlias_ = getAlias(superparent);
                        query = fillClassNodeJoin(query, tableAlias_, superparent, parent, rootAlias_, tables);
                        tables.put(rootAlias, Short.MAX_VALUE);
                    }
                    switch (field) {
                        case "Rating":
                            query = query + " left join legal_entity_rating " + tableAlias2;
                            query = query + " on " + rootAlias + ".legal_entity_id=" + tableAlias2 + ".legal_entity_id";
                            query = query + " left join rating " + rootAlias + "_Rating_" + tableAlias;
                            query = query + " on " + tableAlias2 + ".rating_id=" + rootAlias + "_Rating_" + tableAlias + ".rating_id";
                            query = query + " and " + rootAlias + "_Rating_" + tableAlias + ".agency='" + StringUtils.cleanString(param) + StringUtils.QUOTE;
                            break;
                        case "ProductReference":
                            query = query + " left join product_reference " + tableAlias2;
                            query = query + " on " + tableAlias2 + ".product_id=" + rootAlias + ".product_id";
                            query = query + " and " + tableAlias2 + ".reference_type='" + StringUtils.cleanString(param) + StringUtils.QUOTE;
                            break;
                    }
                }
            }
        }
        return query;
    }

    public static String fillClassNodeJoin(String query, String tableAlias, IntrospectTree.IntrospectNode root, IntrospectTree.IntrospectNode node, String rootAlias, HashMap tables) {
        String field = node.toString().substring(0, 1).toLowerCase() + node.toString().substring(1, node.toString().length());

        if (!tables.containsKey(rootAlias)) {
            // if father table lacks, add it
            IntrospectTree.IntrospectNode parent = (IntrospectTree.IntrospectNode) node.getParent();
            String tableAlias_ = getAlias(parent);
            IntrospectTree.IntrospectNode superparent = (IntrospectTree.IntrospectNode) parent.getParent();
            if (superparent != null) {
                String rootAlias_ = getAlias(superparent);
                query = fillClassNodeJoin(query, tableAlias_, superparent, parent, rootAlias_, tables);
                tables.put(rootAlias, Short.MAX_VALUE);
            }
        }
        if (field.equalsIgnoreCase("underlying")) {
            query = query + " left join product_underlying " + rootAlias + "_pu";
            query = query + " on " + rootAlias + ".product_id=" + rootAlias + "_pu.product_id";
            query = query + " left join product " + tableAlias;
            query = query + " on " + rootAlias + "_pu.underlying_id=" + tableAlias + ".product_id";
        } else if (field.equalsIgnoreCase("firstLeg")) {
            query = query + " left join product_subproduct " + rootAlias + "_sp1";
            query = query + " on " + rootAlias + ".product_id=" + rootAlias + "_sp1.product_id";
            query = query + " left join product " + tableAlias;
            query = query + " on " + rootAlias + "_sp1.subproduct_id=" + tableAlias + ".product_id";
        } else if (field.equalsIgnoreCase("secondLeg")) {
            query = query + " left join product_subproduct " + rootAlias + "_sp2";
            query = query + " on " + rootAlias + ".product_id=" + rootAlias + "_sp2.product_id";
            query = query + " left join product " + tableAlias;
            query = query + " on " + rootAlias + "_sp2.subproduct_id=" + tableAlias + ".product_id";
        } else {

            String table = IntrospectUtil.getDBTableName(node.getResultType());
            String link = IntrospectUtil.getDBFieldName(root.getResultType(), field);
            String id = IntrospectUtil.getIdDBFieldName(node.getResultType());
            switch (field) {
                case "positionHistory":
                    link = "position_id";
                    id = "position_id";
                    break;
                case "productEquity":
                    link = "product_id";
                    id = "product_id";
                    break;
                case "productRate":
                    link = "product_id";
                    id = "product_id";
                    break;
                case "productCredit":
                    link = "product_id";
                    id = "product_id";
                    break;
                case "productSingleTraded":
                    link = "product_id";
                    id = "product_id";
                    break;
                case "productCurve":
                    link = "product_id";
                    id = "product_id";
                    break;
                case "productForex":
                    link = "product_id";
                    id = "product_id";
                    break;
            }
            query = query + " left join " + table + StringUtils.SPACE + tableAlias;
            query = query + " on " + rootAlias + StringUtils.DOT + link + "=" + tableAlias + StringUtils.DOT + id;
        }
        return query;
    }

    /**
     * Calculates the result of a template column item on an object
     *
     * @param templateColumnItem
     * @param object
     * @return
     */
    public static Object fillColumn(TemplateColumnItem templateColumnItem, Object object) {
        if (object == null) {
            return null;
        }
        Object resultObject = null;
        if (templateColumnItem.getReturnType() != null && templateColumnItem.getReturnType().equals(BigDecimal.class.getName())) {
            resultObject = BigDecimal.ZERO;
        }
        /**
         * we call object methods.
         */
        try {
            Object realObject = null;
            Class clazz = IntrospectTree.getClass(object.getClass(), templateColumnItem.getGetter());
            Method getter = null;
            if (templateColumnItem.getGetter() != null) {

                if (templateColumnItem.getParam() != null && !templateColumnItem.getParam().isEmpty()) {
                    if (templateColumnItem.getName().equalsIgnoreCase("Product.ProductReference")){
                        //ugly : because of lazy hibernate relation
                        realObject = getObject(object, templateColumnItem.getGetter());
                        return ProductAccessor.getProductReference((Product)realObject, templateColumnItem.getParam());
                    } else {
                        getter = clazz.getMethod(templateColumnItem.getGetter().substring(templateColumnItem.getGetter().lastIndexOf("/") + 1), String.class);
                    }
                } else {
                    getter = clazz.getMethod(templateColumnItem.getGetter().substring(templateColumnItem.getGetter().lastIndexOf("/") + 1), (Class[]) null);
                }
                realObject = getObject(object, templateColumnItem.getGetter());
            }
            if (realObject != null && getter != null) {
                if (templateColumnItem.getParam() != null && !templateColumnItem.getParam().isEmpty()) {
                    resultObject = getter.invoke(realObject, templateColumnItem.getParam());
                } else {
                    resultObject = getter.invoke(realObject, (Object[]) null);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
            logger.error("error" + StringUtils.formatErrorMessage(e));
        }
        return resultObject;
    }

    public static Object reloadObject(Object object, Session session) {
        if (object != null) {
            Method method = IntrospectUtil.getIdGetter(object.getClass().getName());
            Serializable id = null;
            try {
                id = (Serializable) method.invoke(object, (Object[]) null);
                if (id != null) {
                    object = session.get(object.getClass().getName(), id);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error(object.getClass().getSimpleName() + StringUtils.SPACE + id);
            }
        }
        return object;
    }

    /**
     * Gets an object given a getter
     *
     * @param object
     * @param getters
     */
    private static Object getObject(Object object, String getters) {
        Object ret = object;
        String get = getters;
        String leftGetter;
        Method mget;
        if (get == null) {
            get = StringUtils.EMPTY_STRING;
        }
        try {
            while (get.indexOf("/") >= 0) {
                object = ret;
                if (object != null) {
                    leftGetter = get.substring(0, get.indexOf("/"));
                    Class clazz = Class.forName(object.getClass().getName());
                    if (clazz != null) {
                        if (!leftGetter.equals("ProductReference")) {
                            mget = clazz.getMethod(leftGetter);
                            ret = mget.invoke(object, (Object[]) null);
                            if (ret instanceof HibernateProxy) {
                                ret = AuditAccessor.loadLazyObject((HibernateProxy) ret);
                            }
                        } else {
                            //String type = get.substring(get.lastIndexOf("/") + 1);
                            // TODO
                            get = StringUtils.EMPTY_STRING;
                        }
                    }
                } else {
                    get = StringUtils.EMPTY_STRING;
                }
                get = get.substring(get.indexOf("/") + 1);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error(object.getClass().getSimpleName() + StringUtils.SPACE + getters);
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return ret;
    }
}
