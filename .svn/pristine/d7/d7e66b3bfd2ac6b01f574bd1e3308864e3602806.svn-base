/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.observables;

import java.util.Date;
import java.util.List;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductHistoricalAccessor {



    public static final String GET_HISTORICALS="getHistoricalByDateAndProduct";
    /**
     *  retrieve list of historic from a product.
     * @param productId
     * @return
     */
    public static List<ProductHistoricalMeasure> getHistoricals(Integer productId) {
        String query = "from ProductHistoricalMeasure mq";
        query = query + " join fetch mq.product p";
        query = query + " where p.productId in(" + productId + ")";
        query = query + " order by valuation_date";

        return (List<ProductHistoricalMeasure>) HibernateUtil.getObjectsWithQuery(query);
    }

    public static final String GET_HISTORICAL_BY_DATE_AND_PRODUCT="getHistoricalByDateAndProduct";
     /**
      *  retrieve list of historic from a product with a given date .
      * @param productId
      * @param Date
      * @param quoteSet
      * @return
      */
    public static ProductHistoricalMeasure getHistoricalByDateAndProduct(Integer productId, Date Date, String quoteSet) {
        String query = "from ProductHistoricalMeasure phm";
        query = query + " join fetch phm.productHistoricalMeasureValues";
        query = query + " where product_id in(" + productId + ")";
        query = query + " and valuation_date = '" + HibernateUtil.dateFormat.format(Date) + StringUtils.QUOTE;
        return (ProductHistoricalMeasure)HibernateUtil.getObjectWithQuery(query);
    }

    public static final String GET_LAST_HISTORICAL="getLastHistorical";
     /**
      *  retrieve last list of historic from a product.
      * @param productId
      * @param date
      * @param quoteSet
      * @return
      */
    public static ProductHistoricalMeasure getLastHistorical(Integer productId, Date date, String quoteSet) {
        Date myDate = getLastHistoricalDate(productId, date, quoteSet);
        if (myDate!=null){
            return getHistoricalByDateAndProduct(productId, myDate, quoteSet);
        }
        return null;
    }

    public static final String GET_LAST_HISTORICAL_DATE="getLastHistoricalDate";

     /**
      *  retrieve last list of historic from a product with a given date.
      * @param productId
      * @param date
      * @param quoteSet
      * @return
      */
    public static Date getLastHistoricalDate(Integer productId, Date date, String quoteSet) {
        String query = "select max(phm.valuationDate) as valueDate from ProductHistoricalMeasure phm inner join phm.product p";
        query = query + " where p.productId=" + productId;
        query = query + " and phm.valuationDate<='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;

        return (Date) HibernateUtil.getObjectWithQuery(query);
    }

    public static final String GET_HISTORICAL_BY_DATES_AND_PRODUCT="getHistoricalByDatesAndProduct";
    /**
     *  retrieve last list of historic from a product with an interval date .
     * @param productId
     * @param startDate
     * @param endDate
     * @param quoteSet
     * @return
     */
    public static List<ProductHistoricalMeasure> getHistoricalByDatesAndProduct(Integer productId, Date startDate, Date endDate, String quoteSet) {
        String query = "from ProductHistoricalMeasure mq";
        query = query + " join fetch mq.product p";
        query = query + " where p.productId in(" + productId + ")";
        query = query + " and valuation_date between '" + HibernateUtil.dateFormat.format(startDate) + "' and '" + HibernateUtil.dateFormat.format(endDate) + "' order by valuation_date";

        return (List<ProductHistoricalMeasure>) HibernateUtil.getObjectsWithQuery(query);
    }

    public static final String STORE_HISTORICAL_MEASURE_VALUE="storeProductHistoricalMeasureValue";
    /**
     *  store a product hisorical measure .
     * @param measureValue
     */
    public static void storeProductHistoricalMeasureValue(ProductHistoricalMeasureValue measureValue){
        HibernateUtil.storeObject(measureValue);
    }

    public static final String SAVE_HISTORICAL_MEASURE="saveProductHistoricalMeasure";
    /**
     *  store a product hisorical measure.
     * @param productHistoricalMeasure
     * @return
     */
    public static Integer saveProductHistoricalMeasure(ProductHistoricalMeasure productHistoricalMeasure){
        return (Integer)HibernateUtil.saveObject(productHistoricalMeasure);
    }
}
