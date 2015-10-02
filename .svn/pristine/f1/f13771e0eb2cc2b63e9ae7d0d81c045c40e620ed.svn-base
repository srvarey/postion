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

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOAgentPool;
import org.gaia.dao.jade.MarketDataAgent;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.HibernateException;

/**
 *
 * @author Benjamin Frerejean
 */
public class MarketQuoteAccessor {

    private static final Logger logger = Logger.getLogger(MarketQuoteAccessor.class);
    private static final String QUOTESET = "quoteSet";

    public static BigDecimal getLastForexRate(String forexPair, Date date) {
        MarketQuoteObservable obs = getForexObservable(forexPair);
        if (obs != null) {
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            MarketQuote quote = getLastQuote(obs.productId, date, quoteSet);
            if (quote != null) {
                return quote.getClose();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public static final String GET_QUOTE_SPREAD = "getQuoteSpread";

    public static BigDecimal getQuoteSpread(Trade trade, Date valueDate) {
        BigDecimal spread;
        IObservable observable = new CurveObservable(AbstractObservable.ObservableType.CREDIT_CURVE, AbstractObservable.DEFAULT_FORCASTING);

        String pricingEnv = MappingsAccessor.getDefaultPricingEnvironmentName();
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnv);
        Integer obsProductId = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(observable, trade, pricingEnvironment);

        observable.setProductId(obsProductId);
        observable.fillData(valueDate, MarketQuoteAccessor.getDefaultQuoteSet());
        spread = observable.getObservableValue(new Object[]{valueDate});
        return spread;
    }

    public static List<String> getQuoteTypes() {
        List<String> ret = new ArrayList();
        MarketQuote.QuotationType[] quotationTypes = MarketQuote.QuotationType.values();
        if (quotationTypes != null) {
            for (MarketQuote.QuotationType quotationType : quotationTypes) {
                ret.add(quotationType.getName());
            }
        }
        return ret;
    }
    public static final String CONVERT_AMOUNT = "convertAmount";

    /* convert an amount in another currency
     *@param amount
     *@param currencyDest
     *@param date
     *@param currencySource  */
    public static BigDecimal convertAmount(BigDecimal amount, String currencyDest, String currencySource, Date date) {
        BigDecimal quote = null;
        StringBuilder currencyPair;
        if (!currencyDest.equalsIgnoreCase(currencySource)) {
            currencyPair = new StringBuilder();
            currencyPair.append(currencyDest);
            currencyPair.append("/");
            currencyPair.append(currencySource);
        } else {
            return amount;
        }
        if (amount != null) {
            quote = getLastForexRate(currencyPair.toString(), date);
            if (quote != null) {
                quote = amount.multiply(quote);
            }
        }
        return quote;
    }

    public static String getDefaultQuoteSet() {
        return "default";
    }
    public static final String GET_QUOTE_SETS = "getQuoteSets";

    /** get the list of quote sets
     * @return  */
    public static List<String> getQuoteSets() {
        return DomainValuesAccessor.getDomainValuesByName(QUOTESET);
    }
    public static final String FILL_OBSERVABLE = "fillObservable";

    public static IObservable fillObservable(IObservable observable, Date date, String quoteSet) {
        observable.fillData(date, quoteSet);
        return observable;
    }
    public static final String STORE_QUOTE = "storeQuote";

    /**
     * store a market quote
     * @param marketQuote
     */
    public static void storeQuote(MarketQuote marketQuote) {
        HibernateUtil.storeObject(marketQuote);
        DAOAgentPool.callAsynchroneMethod(MarketQuoteAccessor.class,MarketQuoteAccessor.IMPACT_REPORTS,marketQuote);
    }

    public static final String IMPACT_REPORTS = "impactReports";


    public static void impactReports(MarketQuote marketQuote){
        Agent agent=DAOAgentPool.getAvailableDAOAgent();
        DFAgentDescription[] descs=MarketDataAgent.findMarketDataAgents(QUOTESET, marketQuote.getValuationDate(), false, agent);
        if (descs!=null){
            for (DFAgentDescription desc : descs){
                MarketDataAgent.impactMarketDataAgent(marketQuote, desc, agent);
            }
        }

    }
    public static final String GET_PRODUCT_QUOTES = "getProductQuotes";

    /**
     * get all market quotes on a product
     * @param productId
     * @return
     */
    public static List<MarketQuote> getProductQuotes(Integer productId) {
        String query = "from MarketQuote mq join fetch mq.product p";
        query = query + " where p.productId in(" + productId + ")";

        return (List<MarketQuote>) HibernateUtil.getObjectsWithQuery(query);

    }
    public static final String GET_PRODUCTS_QUOTE = "getProductsQuote";

    /**
     * get market quotes of a series of product
     * @param sProductIdList list of product ids
     * @param date
     * @param quoteSet
     * @return
     */
    public static List<MarketQuote> getProductsQuote(String sProductIdList, Date date, String quoteSet) {
        List<MarketQuote> ret = new ArrayList();
        String query = "from MarketQuote mq join fetch mq.product p";
        query = query + " where mq.valuationDate='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
        query = query + " and p.productId in(" + sProductIdList + ")";

        return (List<MarketQuote>) HibernateUtil.getObjectsWithQuery(query);
    }
    public static final String GET_LAST_QUOTE = "getLastQuote";

    /**
     * get the last quote of an instrument
     * @param productId
     * @param date
     * @param quoteSet
     * @return
     */
    public static MarketQuote getLastQuote(Integer productId, Date date, String quoteSet) {

        Date valDate = getLastQuoteDate(productId, date, quoteSet);
        if (valDate != null) {
            return getProductQuote(productId, valDate, quoteSet);
        } else {
            return null;
        }
    }
    public static final String GET_LAST_QUOTE_DATE = "getLastQuoteDate";

    /**
     * get the last quote date of an instrument
     * @param productId
     * @param date
     * @param quoteSet
     * @return
     */
    public static Date getLastQuoteDate(Integer productId, Date date, String quoteSet) {

        String query = "select max(mq.valuationDate) from MarketQuote mq inner join mq.product p";
        query = query + " where p.productId=" + productId;
        query = query + " and mq.quoteSet='" + quoteSet + StringUtils.QUOTE;
        query = query + " and mq.valuationDate<='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
        return (Date) HibernateUtil.getObjectWithQuery(query);
    }


    public static final String GET_QUOTES_BY_DATES_AND_PRODUCT = "getQuotesByDatesAndProduct";

    /**
     * get market quotes on a period
     * @param quoteSet
     * @param startDate
     * @param endDate
     * @param productId
     * @return
     */
    public static ArrayList getQuotesByDatesAndProduct(String quoteSet, Date startDate, Date endDate, Integer productId) {
        String query = "from MarketQuote where quote_set='" + quoteSet + "' and product_id=" + productId + " and valuation_date between '"
                + HibernateUtil.dateFormat.format(startDate) + "' and '" + HibernateUtil.dateFormat.format(endDate) + "' order by valuation_date";
        /**
         *  get object with execution of a query .
         */
        return (ArrayList) HibernateUtil.getObjectsWithQuery(query);
    }
    public static final String GET_CURVES_DATES = "getCurveDates";

    /**
     *  gate the list of rate curve dates
     * @param curveId
     * @param quoteSet
     * @return
     */
    public static List<Date> getCurveDates(Integer curveId, String quoteSet) {
        String query = "select distinct mq.valuationDate from MarketQuote mq inner join mq.product p inner join p.superProducts sp";
        query = query + " where sp.product.productId=" + curveId + " order by mq.valuationDate desc";
        return HibernateUtil.getObjectsWithQuery(query);

    }
    public static final String GET_CURVE_BY_DATES = "getCurveByDate";

    /**
     * get a rate curve by date .
     * @param product
     * @param quoteSet
     * @param date
     * @return
     */
    public static List<MarketQuote> getCurveByDate(Product product, String quoteSet, Date date) {
        List<MarketQuote> ret = new ArrayList();
        String query;
        if (product.getProductCurve()!=null && product.getProductCurve().isComposite()) {
            query = "from MarketQuote mq";
            query = query + " join fetch mq.product p";
            query = query + " join fetch p.superProducts sp";
            query = query + " join fetch sp.product spp";
            query = query + " join fetch spp.superProducts ssp";
            query = query + " where mq.valuationDate='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
            query = query + " and ssp.product.productId=" + product.getProductId();
        } else {
            query = "from MarketQuote mq";
            query = query + " join fetch mq.product p";
            query = query + " join fetch p.superProducts sp";
            query = query + " where mq.valuationDate='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
            query = query + " and sp.product.productId=" + product.getProductId();
        }
        try {
            ret = (List<MarketQuote>) HibernateUtil.getObjectsWithQuery(query);
        } catch (HibernateException he) {
            logger.error("error HibernateException in a retrieve of a curve organized by date" + StringUtils.formatErrorMessage(he));
        }
        return ret;
    }
    public static final String GET_LAST_CURVE_DATE = "getLastCurveDate";

    /**
     * get the last rate curve
     * @param product
     * @param quoteSet
     * @param date
     * @return
     */
    public static Date getLastCurveDate(Product product, String quoteSet, Date date) {
        String query=null;
        if (product!=null && product.getProductCurve()!=null && product.getProductCurve().isComposite()) {
            query = "select max(mq1.valuationDate) as maxDate from Product p"
                    + " inner join p.underlyingProducts up1"
                    + " inner join up1.underlying upp1"
                    + " inner join upp1.underlyingProducts uppu1"
                    + " inner join uppu1.underlying uppup1"
                    + " inner join uppup1.marketQuotes mq1"
                    + " inner join p.underlyingProducts up2"
                    + " inner join up2.underlying upp2"
                    + " inner join upp2.underlyingProducts uppu2"
                    + " inner join uppu2.underlying uppup2"
                    + " inner join uppup2.marketQuotes mq2"
                    + " where p.productId=" + product.getProductId()
                    + " and upp1.productId>upp2.productId"
                    + " and mq1.valuationDate=mq2.valuationDate"
                    + " and mq1.valuationDate<='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;

        } else if (product!=null){
            query = "select max(mq.valuationDate) as maxDate from MarketQuote mq inner join mq.product p inner join p.superProducts sp";
            query = query + " where sp.product.productId=" + product.getProductId() + " and mq.valuationDate<='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
        }
        if (query!=null){
            return (Date) HibernateUtil.getObjectWithQuery(query);
        }
        return null;
    }

    public static final String GET_LAST_CURVE_BY_DATE = "getLastCurveByDate";

    public static List<MarketQuote> getLastCurveByDate(Product product, String quoteSet, Date date) {
        Date lastDate = getLastCurveDate(product, quoteSet, date);
        if (lastDate != null) {
            return getCurveByDate(product, quoteSet, lastDate);
        } else {
            return null;
        }
    }
    public static final String GET_PRODUCT_QUOTE = "getProductQuote";

    /**
     * get a product market quote
     * @param productId
     * @param date
     * @param quoteSet
     * @return
     */
    public static MarketQuote getProductQuote(Integer productId, Date date, String quoteSet) {
        MarketQuote ret = null;
        String query;
        query = "from MarketQuote mq";
        query = query + " join fetch mq.product p";
        query = query + " where mq.valuationDate='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
        query = query + " and mq.quoteSet='" + quoteSet + StringUtils.QUOTE;
        query = query + " and p.productId in( " + productId + ")";
        return (MarketQuote) HibernateUtil.getObjectWithQuery(query);

    }
    public static final String GET_PRODUCT_QUOTE_VALUE = "getProductQuoteValue";

    /**
     * get a product market quote
     * @param productId
     * @param date
     * @param quoteSet
     * @return
     */
    public static Double getProductQuoteValue(Integer productId, Date date, String quoteSet) {
        Double res = 0d;
        String query;
        query = "select close_quote from market_quote mq";
        query = query + " where valuation_date='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE;
        query = query + " and product_id =" + productId;
        BigDecimal ret = (BigDecimal) HibernateUtil.getFieldWithSQLQuery(query);
        if (ret != null) {
            res = ret.doubleValue();
        }
        return res;
    }
    public static final String GET_DETAIL_QUOTE_VALUE = "getDetailQuoteValue";

    public static LinkedHashMap<Date, Double> getDetailQuoteValue(Integer productId, Date startDate, Date endDate, String quoteSet) {
        double previousRate = 0;
        Map<Date, Double> mapDetail = new HashMap<>();
        LinkedHashMap<Date, Double> mapDetailResult = new LinkedHashMap<>();
        StringBuilder query = new StringBuilder("select valuation_date, close_quote from market_quote mq");
        query.append(" where (mq.valuation_date BETWEEN '").append(HibernateUtil.dateFormat.format(startDate));
        query.append("' AND '").append(HibernateUtil.dateFormat.format(endDate)).append("' ");
        query.append(") AND mq.product_id =").append(productId).append(" ORDER BY valuation_date");
        try {
            List ret = HibernateUtil.getListWithSQLQuery(query.toString());
            Calendar cal = Calendar.getInstance();

            for (Object objects : ret) {
                Object[] objectTable = (Object[]) objects;
                Date valueDate = (Date) objectTable[0];
                BigDecimal closeQuote = (BigDecimal) objectTable[1];
                if (valueDate != null) {
                    mapDetail.put(valueDate, closeQuote.doubleValue());
                }
            }

            while (startDate.before(endDate)) {
                cal.setTime(startDate);
                if (DateUtils.isBusinessDay(cal, startDate) && mapDetail.containsKey(startDate)) {
                    previousRate = mapDetail.get(startDate);
                    mapDetailResult.put(startDate, previousRate);
                } else {
                    mapDetailResult.put(startDate, previousRate);
                }
                startDate = DateUtils.addCalendarDay(startDate, 1);
            }

        } catch (HibernateException he) {
            logger.error("error HibernateException in a retrieve of a product quote" + StringUtils.formatErrorMessage(he));
        }
        return mapDetailResult;
    }

    public static final String GET_FOREX_OBSERVABLE = "getForexObservable";

    /**
     * get a forex rate obeservable
     * @param forexPair
     * @return
     */
    public static MarketQuoteObservable getForexObservable(String forexPair) {
        Product product = ProductAccessor.getProductByShortName(forexPair);
        if (product != null) {
            return new MarketQuoteObservable(product);
        } else if (forexPair.contains("/") && forexPair.length() == 7) {
            String reverse = forexPair.substring(forexPair.indexOf("/") + 1) + "/" + forexPair.substring(0, 3);
            product = ProductAccessor.getProductByShortName(reverse);
            if (product != null) {
                return new MarketQuoteObservable(product);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void addToFXRatesList(List<IObservable> forexRates, String currency1, String currency2) {
        if (forexRates == null) {
            forexRates = new ArrayList();
        }
        if (currency1 == null || currency2 == null) {
            return;
        }
        if (!currency1.equalsIgnoreCase(currency2)) {
            /*
             * fill if not already in .
             */
            String forexPair = currency1 + "/" + currency2;
            String forexPair2 = currency2 + "/" + currency1;
            boolean alreadyInList = false;
            for (IObservable obs : forexRates) {
                /*
                 * look with pairs in both sides.
                 */
                if (obs.getProduct().getShortName().equals(forexPair) || obs.getProduct().getShortName().equals(forexPair2)) {
                    alreadyInList = true;
                }
            }
            if (!alreadyInList) {
                MarketQuoteObservable fxObservable = MarketQuoteAccessor.getForexObservable(forexPair);
                if (fxObservable != null) {
                    forexRates.add(fxObservable);
                }
            }
        }
    }


}
