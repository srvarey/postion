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


package org.gaia.dao.referentials;

import java.util.Date;
import java.util.List;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class CurrencyAccessor {

    public static final String STORE_CURRENCY="storeCurrency";
    /** store currency
     * @param currency */
    public static void storeCurrency(Currency currency){
        HibernateUtil.storeObject(currency);
    }

    public static final String GET_CURRENCY_BY_CODE="getCurrencyByCode";
   /** retrieve currency by code
     * @param curCode
     * @return  */
    public static Currency getCurrencyByCode(String curCode) {
        return (Currency)HibernateUtil.getObjectWithQuery("from Currency where code like'"+curCode+StringUtils.QUOTE);
    }

    public static final String GET_CURRENCY_PRODUCT_BY_CODE="getCurrencyProductByCode";
   /** retrieve currency by code
     * @param curCode
     * @return  */
    public static Product getCurrencyProductByCode(String curCode) {
        Currency cur=getCurrencyByCode(curCode);
        return cur.getCurrencyProduct();
    }

    public static final String LOAD_ALL_CURRENCIES="loadAllCurrencies";
    /** load all currencies
     * @return  */
    public static List<Currency> loadAllCurrencies() {
       return (List)HibernateUtil.getObjects("Currency","code");
    }

    public static final String LOAD_CURRENCY_CODES="loadCurrencyCodes";
    /** load currency codes
     * @return  */
    public static List<String> loadCurrencyCodes() {
      return  HibernateUtil.getObjectsWithQuery("SELECT code FROM Currency order by code");
    }

    public static final String LOAD_CURRENCY_PAIRS="loadCurrencyPairs";
    /** load currency codes
     * @return  */
    public static List<String> loadCurrencyPairs() {
      return  HibernateUtil.getObjectsWithQuery("select p.shortName from Product p where p.productType='"+ProductTypeUtil.ProductType.CURRENCY_PAIR.getName()+ "' order by p.shortName");
    }

    public static final String DELETE_CURRENCY="deleteCurrency";
     /** delete currency
     * @param currencyCode */
    public static void deleteCurrency(String currencyCode) {
        HibernateUtil.deleteObject(getCurrencyByCode(currencyCode));
    }

    public static final String GET_SPOT_DATE="getSpotDate";
    public static Date getSpotDate(Date currentDate, String sPair){
        Date spotDate=currentDate;
        HolidayCalendar cal1=null;
        HolidayCalendar cal2=null;
        Product pair=ProductAccessor.getProductByShortName(sPair);
        long lag=pair.getSettlementDelay();
        try {
            Product productCur1=pair.getProductForex().getCurrency1();
            Currency cur1=CurrencyAccessor.getCurrencyByCode(productCur1.getShortName());
            cal1=cur1.getCalendar();
        } catch (Exception e){}
        try {
            Product productCur2=pair.getProductForex().getCurrency2();
            Currency cur2=CurrencyAccessor.getCurrencyByCode(productCur2.getShortName());
            cal2=cur2.getCalendar();
        } catch (Exception e){}
        HolidayCalendar merged=CalendarAccessor.mergeCalendars(cal1, cal2);
        return DateUtils.addOpenDay(spotDate, lag, merged);

    }

}
