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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import static org.gaia.dao.reports.ObjectAccessor.getDBFieldName;
import static org.gaia.dao.reports.ObjectAccessor.getTableAliasForFields;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.FilterGroup;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin
 */
public class FilterAccessor {

    private static final Logger logger = Logger.getLogger(FilterAccessor.class);
    public static final String GET_FILTER_NAMES = "getFilterNames";

    /**
     * return filter names
     * @param objectType
     * @return
     */
    public static List<String> getFilterNames(Class objectType) {
        return HibernateUtil.getObjectsWithQuery("select name from Filter where object_type='" + objectType.getName() + "' order by name");
    }
    public static final String GET_FILTER_BY_NAME_AND_TYPE = "getFilterByName";

    /**
     * return a filter select by name and object type
     * @param name
     * @param objectType
     * @return
     */
    public static Filter getFilterByName(String name, Class objectType) {
        return (Filter) HibernateUtil.getObjectWithQueryWithCache("from Filter where name='" + name + "' and object_type='" + objectType.getName() + StringUtils.QUOTE);
    }
    public static final String GET_FILTER_CRITERIA_BY_NAME_AND_FILTER_ID = "getFilterCriteriaByNameAndFilterId";

    /**
     * return a filterCriteria select by the column name and the id of filter
     * @param name
     * @param filterId
     * @return
     */
    public static FilterCriteria getFilterCriteriaByNameAndFilterId(String name, Integer filterId) {
        return (FilterCriteria) HibernateUtil.getObjectWithQueryWithCache("from FilterCriteria where column_name='" + name + "' and filter_id=" + filterId);
    }
    public static final String GET_FILTER_CRITERIA_BY_ID = "getFilterCriteriaById";

    /**
     * get a filterCriteria by id
     * @param filterCriteriaId
     * @return
     */
    public static FilterCriteria getFilterCriteriaById(Integer filterCriteriaId) {
        return (FilterCriteria) HibernateUtil.getObjectWithQuery("from FilterCriteria where filter_criteria_id=" + filterCriteriaId);
    }
    public static final String DELETE_FILTER_CRITERIA = "deleteFilterCriteria";

    /**
     * delete a filterCriteria
     * @param filterCriteria
     */
    public static void deleteFilterCriteria(FilterCriteria filterCriteria) {
        HibernateUtil.deleteObject(filterCriteria);
    }
    public static final String STORE_FILTER = "storeFilter";

    /**
     * store a filter
     * @param filter
     */
    public static void storeFilter(Filter filter) {
        Collection<FilterCriteria> criterias = null;
        if (filter.getFilterId() == null) {
            criterias = filter.getFilterCriteria();
            filter.setFilterCriteria(null);
        }
        HibernateUtil.storeAndReturnObject(filter);
        if (criterias != null) {
            for (FilterCriteria criteria : criterias) {
                criteria.setFilter(filter);
                HibernateUtil.storeObject(criteria);
            }
        } else {
            for (FilterCriteria criteria : filter.getFilterCriteria()) {
                criteria.setFilter(filter);
                HibernateUtil.storeObject(criteria);
            }
        }
    }

    /**
     * remove a criteria from a filter by its column name
     * @param filter
     * @param column
     */
    public static void removeFilterColumn(Filter filter, String column) {
        FilterCriteria toRemove = null;
        for (FilterCriteria fc : filter.getFilterCriteria()) {
            if (fc.getColumnName().equals(column)) {
                toRemove = fc;
            }
        }
        if (toRemove != null) {
            filter.getFilterCriteria().remove(toRemove);
        }
    }

    /**
     * add a criteria to a filter
     * @param filter
     * @param columnName
     * @param getter
     * @param value1
     * @param returnType
     * @param value2
     * @return
     */
    public static Filter addCriteria(Filter filter, String columnName, String getter, String value1, String value2, String returnType) {
        if (filter == null) {
            filter = new Filter();
            filter.setFilterCriteria(new ArrayList());
        }
        FilterCriteria criteria = new FilterCriteria();
        criteria.setColumnName(columnName);
        criteria.setFilter(filter);
        criteria.setGetter(getter);
        criteria.setValue(value1);
        criteria.setValue2(value2);
        criteria.setReturnType(returnType);
        filter.getFilterCriteria().add(criteria);
        return filter;
    }
    public static final String DELETE_FILTER_BY_NAME_AND_TYPE = "deleteFilterByName";
    /**
     * delete a filter by its name
     * @param name
     * @param objectType
     */
    public static void deleteFilterByName(String name, Class objectType) {

        Filter filter = getFilterByName(name, objectType);
        deleteFilter(filter);
    }
    public static final String DELETE_FILTER = "deleteFilter";

    /**
     * delete a filter
     * @param filter
     */
    public static void deleteFilter(Filter filter) {
        if (filter != null) {
            HibernateUtil.deleteObject(filter);
        }
    }
    public static final String IS_IN_FILTER = "isInFilter";

    /**
     * checks if a given object enters a filter
     * @param objectValue
     * @param filter
     * @return
     */
    public static Boolean isInFilter(Object objectValue, Filter filter) {
        Boolean ret = true;
        if (filter != null) {
            for (FilterCriteria filterCriteria : filter.getFilterCriteria()) {
                if (!filterCriteria.getGetter().isEmpty()) {
                    TemplateColumnItem templateColumnItem = new TemplateColumnItem();
                    templateColumnItem.setGetter(filterCriteria.getGetter());
                    templateColumnItem.setParam(filterCriteria.getParam());
                    templateColumnItem.setReturnType(filterCriteria.getReturnType());
                    templateColumnItem.setColumnType(filterCriteria.getColumnType());
                    templateColumnItem.setName(filterCriteria.getColumnName());
                    Object objectResult = ObjectAccessor.fillColumn(templateColumnItem, objectValue);

                    if (filterCriteria.getReturnType().equals(Date.class.toString())) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        if (!filterCriteria.getValue().isEmpty() && objectResult != null) {
                            boolean validInt = true;
                            boolean validDate = true;
                            Date datetrade;
                            try {
                                datetrade = (Date) objectResult;
                                Date date = DateUtils.removeTime(new Date());
                                Integer n = Integer.parseInt(filterCriteria.getValue());
                                if (datetrade.before(DateUtils.addOpenDay(date, n))) {
                                    return false;
                                }
                            } catch (Exception e) {
                                validInt = false;
                            }

                            try {
                                datetrade = (Date) objectResult;
                                Date d = dateFormat.parse(filterCriteria.getValue());
                                if (datetrade.before(d)) {
                                    return false;
                                }
                            } catch (Exception e) {
                                validDate = false;
                            }

                            if (validDate == false && validInt == false) {
                                logger.error("TradeAccessor date argument not valid" + filterCriteria.getValue());
                            }
                        }

                        if (!filterCriteria.getValue2().isEmpty() && objectResult != null) {
                            boolean validInt = true;
                            boolean validDate = true;
                            Date datetrade;
                            try {
                                datetrade = (Date) objectResult;
                                Date date = DateUtils.removeTime(new Date());
                                Integer n = Integer.parseInt(filterCriteria.getValue2());
                                if (datetrade.after(DateUtils.addOpenDay(date, n))) {
                                    return false;
                                }
                            } catch (Exception e) {
                                validInt = false;
                            }
                            try {
                                datetrade = (Date) objectResult;
                                Date d = dateFormat.parse(filterCriteria.getValue2());
                                if (datetrade.before(d)) {
                                    return false;
                                }
                            } catch (Exception e) {
                                validDate = false;
                            }
                            if (validDate == false && validInt == false) {
                                logger.error("TradeAccessor date argument not valid" + filterCriteria.getValue());
                            }
                        }

                    } else if (!filterCriteria.getValue().isEmpty()) {
                        String vals = filterCriteria.getValue();
                        ArrayList<String> valueList = StringUtils.stringToArrayList(vals, StringUtils.COMMA);
                        if (objectResult != null) {
                            if (filterCriteria.getNotIn() && valueList.contains(objectResult.toString())) {
                                return false;
                            } else if (!filterCriteria.getNotIn() && !valueList.contains(objectResult.toString())) {
                                return false;
                            }
                        } else {
                            if (filterCriteria.getNotIn() && vals.equalsIgnoreCase("null")) {
                                return false;
                            } else if (!filterCriteria.getNotIn() && !valueList.contains("null")) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    public static final String GET_FILTER_GROUP = "getFilterGroup";

    /**
     * get a filter group
     * @param className
     * @param linkedObjectId
     * @return
     */
    public static FilterGroup getFilterGroup(String className, Integer linkedObjectId) {
        String query = "from FilterGroup where linked_object_class='" + className + "' and linked_object_id=" + linkedObjectId;
        return (FilterGroup) HibernateUtil.getObjectWithQueryWithCache(query);
    }
    public static final String STORE_FILTER_GROUP = "storeFilterGroup";

    /**
     * store a filter group
     * @param filterGroup
     */
    public static void storeFilterGroup(FilterGroup filterGroup) {
        if (filterGroup.getFilterGroupId() == null) {
            Serializable sId = HibernateUtil.saveObject(filterGroup);
            filterGroup.setFilterGroupId((Integer) sId);
        } else {
            HibernateUtil.updateObject(filterGroup);
        }

    }
    public static final String GET_FILTER_GROUP_NAMES_BY_FILTER_ID = "getFilterGroupNamesbyFilterId";

    /**
     * return the list of filter group names
     * @param filterId
     * @return
     */
    public static List<String> getFilterGroupNamesbyFilterId(Integer filterId) {
        return HibernateUtil.getObjectsWithQuery("select fg.filterGroupName from FilterGroup fg inner join fg.filterCollection fc where fc.filterId=" + filterId);
    }

    /**
     * build the sql query from a filter
     * @param filter
     * @param className
     * @return
     */
    public static String getQueryFromFilter(Filter filter, Class className) {

        String table = IntrospectUtil.getDBTableName(className.getName());
        table = table.substring(table.lastIndexOf(StringUtils.DOT) + 1);
        String rootAlias = table.substring(0, 1).toLowerCase() + table.substring(1);

        IntrospectTree tree = new IntrospectTree(className);
        IntrospectTree.IntrospectNode root = tree.getClassTree(null, false);
        /**
         * localisation of table.
         */
        StringBuilder query = new StringBuilder("from ");
        query.append(table).append(StringUtils.SPACE).append(rootAlias);

        String dbField;
        /**
         * localisation of table.
         */
        HashMap<String, Short> tables=new HashMap();
        for (FilterCriteria criterion : filter.getFilterCriteria()){
            String tableAlias = getTableAliasForFields(criterion.getGetter(), className, criterion.getParam());
            tables.put(tableAlias, Short.MIN_VALUE);
        }
        String leftJoins = ObjectAccessor.getJoinsFromTree(StringUtils.EMPTY_STRING, root, rootAlias, tables);

        /**
         * then manage conditions.
         */
        query.append(leftJoins);
        query.append(" where 1=1 ");
        /**
         * first : add conditions according to filter criteria
         */
        for (FilterCriteria criteria : filter.getFilterCriteria()) {
            if (criteria.getColumnName() != null && criteria.getReturnType() != null) {

                String tableAlias = getTableAliasForFields(criteria.getGetter(), className, criteria.getParam());
                dbField = getDBFieldName(criteria.getGetter(), className, criteria.getParam());
                if (!criteria.getValue().isEmpty() || !criteria.getValue2().isEmpty()) {
                    String inNotIn;
                    if (criteria.getNotIn()) {
                        inNotIn = " not in ";
                    } else {
                        inNotIn = " in ";
                    }
                    if (criteria.getReturnType().equals(Date.class.getName())) {
                        Date date = DateUtils.removeTime(new Date());
                        if (!criteria.getValue().isEmpty()) {
                            try {
                                Integer n = Integer.parseInt(criteria.getValue());
                                query.append(" and (").append(tableAlias).append(StringUtils.DOT).append(dbField).append(" is null or ")
                                        .append(tableAlias).append(StringUtils.DOT).append(dbField).append(">='")
                                        .append(HibernateUtil.dateFormat.format(DateUtils.addOpenDay(date, n))).append("')");
                            } catch (Exception e) {
                                logger.error("error  " + StringUtils.formatErrorMessage(e));
                            }
                        }
                        if (!criteria.getValue2().isEmpty()) {
                            try {
                                Integer n = Integer.parseInt(criteria.getValue2());
                                query.append(" and (").append(tableAlias).append(StringUtils.DOT).append(dbField).append(" is null or ")
                                        .append(tableAlias).append(StringUtils.DOT).append(dbField).append("<='")
                                        .append(HibernateUtil.dateFormat.format(DateUtils.addOpenDay(date, n))).append("')");
                            } catch (Exception e) {
                                logger.error("error  " + StringUtils.formatErrorMessage(e));
                            }
                        }
                    } else if (criteria.getReturnType().equals(String.class.getName())) {
                        query.append(" and ").append(tableAlias).append(StringUtils.DOT).append(dbField).append(inNotIn).append("('").append(criteria.getValue().replaceAll(StringUtils.COMMA, "','")).append("')");
                    } else {
                        query.append(" and ").append(tableAlias).append(StringUtils.DOT).append(dbField).append(inNotIn).append("(").append(criteria.getValue()).append(")");
                    }
                }

            }
        }

        return query.toString();
    }
}
