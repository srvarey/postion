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
package org.gaia.dao.trades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class ScheduleAccessor {

    /**
     * retrieve the Schedule of a product
     * @param productId
     * @return
     */
    public static List<ScheduleLine> getScheduleLinesByProductId(Integer productId) {
        List<ScheduleLine> scheduleLine = HibernateUtil.getObjectsWithQuery("from ScheduleLine where product_id=" + productId + " order by schedule_index");
        scheduleLine.addAll(HibernateUtil.getObjectsWithQuery("from ScheduleLine where product_id in (select p.productId from Product p inner join p.parentProducts pp where pp.productId=" + productId + ")"));
        return scheduleLine;
    }
    /**
     * retrieve the Schedule of a product
     * @param product
     * @return
     */
    public static List<ScheduleLine> getScheduleLinesByProduct(Product product) {
        List<Product> productList=ProductAccessor.getProductAndSubProducts(product);
        String ids=StringUtils.EMPTY_STRING;
        for (Product product_ : productList){
            ids+=product_.getProductId()+StringUtils.COMMA;
        }
        if (ids.length()>0){
           return (List) HibernateUtil.getObjectsWithQuery("from ScheduleLine where product_id in (" + ids.substring(0, ids.length()-1) + ") order by schedule_index");
        }
        return null;
    }
    /**
     * retrieve the Schedule by fixing Date
     */
    public static final String GET_SCHEDULE_LINES_BY_DATE = "getScheduleLinesByDate";

    public static List<Serializable> getScheduleLinesByDate(Date resetDate, Filter filter) {
        StringBuilder query = new StringBuilder("select * from schedule_line where is_fixed = false and reset_date='");
        query.append(HibernateUtil.dateFormat.format(resetDate)).append(StringUtils.QUOTE);
        // query from filter
        if (filter == null){
            filter=new Filter();
        }
        // FILTER FOR NO FX OPTIONS
        FilterCriteria fxOptionFilterCriteria=ProductTypeUtil.getFXOptionTypesFilterCriteria();
        fxOptionFilterCriteria.setNotIn(Boolean.TRUE);
        filter.getFilterCriteria().add(fxOptionFilterCriteria);
        String filterConditions=FilterAccessor.getQueryFromFilter(filter, Trade.class);

        query.append(" and product_id in (select product_id from product ");
        query.append(" where product_id in (select distinct trade.product_id ");
        query.append(filterConditions);
        query.append(")");
        query.append(" union select subproduct_id from product_subproduct where product_id in ");
        query.append("(select distinct trade.product_id ");
        query.append(filterConditions);
        query.append("))");
        return HibernateUtil.getEntitiesWithSQLQuery(query.toString(), ScheduleLine.class);
    }
    /**
     * retrieve the Schedule by fixing Date
     */
    public static final String GET_FX_OPTIONS_EXERCISES_BY_DATE = "getFXOptionsExercisesByDate";

    public static List<Serializable> getFXOptionsExercisesByDate(Date resetDate, Filter filter) {
        List<Serializable> ret;
        StringBuilder query = new StringBuilder("select * from schedule_line where is_fixed = false and reset_date='");
        query.append(HibernateUtil.dateFormat.format(resetDate)).append(StringUtils.QUOTE);
        if (filter == null){
            filter=new Filter();
        }
        // FILTER FOR FX OPTIONS ONLY
        FilterCriteria fxOptionFilterCriteria=ProductTypeUtil.getFXOptionTypesFilterCriteria();
        filter.getFilterCriteria().add(fxOptionFilterCriteria);
        String filterConditions=FilterAccessor.getQueryFromFilter(filter, Trade.class);
        /// build the query
        query.append(" and product_id in (select product_id from product ");
        query.append(" where product_id in (select distinct trade.product_id ");
        query.append(filterConditions);
        query.append(")");
        query.append(" union select subproduct_id from product_subproduct where product_id in ");
        query.append("(select distinct trade.product_id ");
        query.append(filterConditions);
        query.append(")) order by product_id");
        ret=(List) HibernateUtil.getEntitiesWithSQLQuery(query.toString(), ScheduleLine.class);
        // then we add american options
        query = new StringBuilder("from Product p inner join p.productForexs pf where p.maturityDate>='"+HibernateUtil.dateFormat.format(resetDate)+"' and p.startDate<='"+HibernateUtil.dateFormat.format(resetDate)+
                "' and pf.exerciceType='"+ProductConst.ExerciseType.American.name()+"' and p.status='"+ProductConst.ProductStatus.Active.name()+StringUtils.QUOTE);
        List<Object[]> americans =(List)HibernateUtil.getObjectsWithQuery(query.toString());
        for (Object[] objs : americans){
            Product amer=(Product)objs[0];
            ScheduleLine line =new ScheduleLine(DateUtils.getDate(),amer.getProductForex().getCurrency2().getShortName(),DateUtils.getDate(),amer,amer.getUnderlying(),amer.getStartDate(),amer.getMaturityDate());
            ret.add(line);
        }
        return ret;
    }

    public static void deleteProductScheduleLinesAndFlows(Integer productId) {
        HibernateUtil.executeQuery("delete from Flow f where source_product_id=" + productId +" and schedule_line_id is not null");
        HibernateUtil.executeQuery("delete from ScheduleLine sl where sl.product.productId=" + productId);
        HibernateUtil.executeQuery("delete from Flow f where source_product_id in (select p.productId from Product p inner join p.parentProducts pp where pp.productId=" + productId + ") and schedule_line_id is not null");
        HibernateUtil.executeQuery("delete from ScheduleLine sl where sl.product.productId  in (select p.productId from Product p inner join p.parentProducts pp where pp.productId=" + productId + ")");
    }
    public static final String STORE_SCHEDULE_LINE = "storeScheduleLine";

    public static Integer storeScheduleLine(ScheduleLine scheduleLine) {
        ScheduleLine line=(ScheduleLine) HibernateUtil.storeAndReturnObject(scheduleLine);
        if (line!=null){
            return line.getScheduleLineId();
        }
        return null;
    }
    public static final String SAVE_SCHEDULE_LINE = "saveScheduleLine";

    public static Integer saveScheduleLine(ScheduleLine scheduleLine) {
        return (Integer) HibernateUtil.saveObject(scheduleLine);
    }
    public static final String DELETE_SCHEDULE_LINE = "deleteScheduleLine";

    public static void deleteScheduleLine(ScheduleLine scheduleLine) {
        HibernateUtil.deleteObject(scheduleLine);
    }
}
