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
package org.gaia.dao.observables;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.pricing.pricers.isda.IsdaCurve;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.observables.PricingSettingItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class CurveUtils {

    private static final Logger logger = Logger.getLogger(CurveUtils.class);

    public static void fillCurveObservable(CurveObservable curve, Date date, String quoteSet) {
        if (curve.getProductId() != null) {
            date = DateUtils.adjustDate(date, DateUtils.ADJUSTMENT_PREVIOUS, null);
            curve.setValDate(date);

            ArrayList<Product> underlyings = new ArrayList();

            try {
                ArrayList<MarketQuote> quotes = (ArrayList<MarketQuote>) MarketQuoteAccessor.getLastCurveByDate(curve.getProduct(), quoteSet, date);
                if (quotes != null) {
                    for (MarketQuote marketQuote : quotes) {
                        Product underlying =marketQuote.getProduct();
                        underlying.setSuperProducts(null);
                        underlyings.add(underlying);
                    }
                    Collections.sort(underlyings, curve.new TenorComparator());
                    if (curve.getProduct() == null) {
                        Product product = (Product) ProductAccessor.getParentProduct(underlyings);
                        curve.setProduct(product);
                    }

                    for (MarketQuote marketQuote : quotes) {
                        String tenor;
                        if (marketQuote.getProduct().getProductCurve() != null) {
                            tenor = marketQuote.getProduct().getProductCurve().getTenor();
                            Date endDate = DateIntervalUtil.getDateFromStartAndTenor(date, tenor);
                            marketQuote.setDateEnd(endDate);
                        }
                    }
                    Collections.sort(quotes);

                    ArrayList<MarketQuote> toRemove=new ArrayList();
                    MarketQuote lastMarketQuote=null;
                    for (MarketQuote marketQuote : quotes) {
                        if (lastMarketQuote!=null &&
                                marketQuote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(lastMarketQuote.getProduct().getProductCurve().getTenor())){
                            toRemove.add(marketQuote);
                            lastMarketQuote.setClose(lastMarketQuote.getClose().add(marketQuote.getClose()));
                        }
                        lastMarketQuote=marketQuote;
                    }
                    quotes.removeAll(toRemove);
                    curve.setUnderlyings(underlyings);
                    curve.setQuotes(quotes);
                    curve.setIsFilled(true);
                }

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
    }

//    public static void fillCurveObservable(CurveObservable curve, Date date, String quoteSet) {
//        if (curve.getProductId() != null) {
//            Integer productId = curve.getProductId();
//            date = DateUtils.adjustDate(date, DateUtils.ADJUSTMENT_PREVIOUS, null);
//            curve.setValDate(date);
//
//            ArrayList<Product> underlyings = new ArrayList();
//
//            try {
//                List<MarketQuote> quotes = MarketQuoteAccessor.getLastCurveByDate(productId, quoteSet, date);
//                for (MarketQuote mq : quotes) {
//                    underlyings.add(mq.getProduct());
//                }
//                Collections.sort(underlyings, curve.new TenorComparator());
//                if (curve.getProduct() == null) {
//                    Product product = ProductAccessor.getParentProduct(underlyings);
//                    curve.setProduct(product);
//                }
//                for (MarketQuote quote : quotes) {
//                    String tenor;
//                    if (quote.getProduct().getProductCurve() != null) {
//                        tenor = quote.getProduct().getProductCurve().getTenor();
//                        Date endDate = DateIntervalUtil.getDateFromStartAndTenor(date, tenor);
//                        quote.setDateEnd(endDate);
//                    }
//                }
//                Collections.sort(quotes);
//                curve.setUnderlyings(underlyings);
//                curve.setQuotes(quotes);
//                curve.setIsFilled(true);
//            } catch (Exception e) {
//                logger.error(StringUtils.formatErrorMessage(e));
//            }
//        }
//    }
    public static final String GENERATE_CURVE = "generateCurve";
    public static CurveObservable generateCurve(Product product, Date date) {
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
        curve.setProduct(product);
        return generateCurve(curve, date);
    }

    public static CurveObservable generateCurve(CurveObservable curve, Date date) {
         try {
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            CurveUtils.fillCurveObservable(curve, date, quoteSet);
            String basis = "ACT/360";
            curve.generateZeroCouponCurve(basis);

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
         return curve;
    }

    public static final String GENERATE_ISDA_CURVE = "generateIsdaCurve";
    public static IsdaCurve generateIsdaCurve(Product product, Date date) {
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
        try {
            curve.setProduct(product);
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            CurveUtils.fillCurveObservable(curve, date, quoteSet);
            curve.generateZeroCouponCurve(null);

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return curve.isdaCurve;
    }

    public static CurveObservable loadCurve(Integer curveId, Date startDate) {
        CurveObservable curve = null;
        try {
            Product product = ProductAccessor.getProductById(curveId);
            curve = generateCurve(product, startDate);

        } catch (NumberFormatException e) {
            logger.error("Error " + StringUtils.formatErrorMessage(e));
        }
        return curve;
    }
    public static final String GET_DEFAULT_RATE = "getDefaultRate";

    public static BigDecimal getDefaultRate(String currency, Date maturity) {
        BigDecimal rate = null;
        try {
            String pricingEnvName = MappingsAccessor.getDefaultPricingEnvironmentName();
            PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnvName);
            for (PricingSettingItem item : pricingEnvironment.getPricingSettingItemCollection()) {
                if (item.getItemType().equalsIgnoreCase(ProductTypeUtil.ProductType.IR_CURVE.getName())
                        && item.getPricingFunction().equals(AbstractObservable.DISCOUNTING)
                        && item.getCurrency().equalsIgnoreCase(currency)) {
                    Integer id = item.getItemValueId();
                    CurveObservable curve = CurveUtils.loadCurve(id, new Date());
                    Object[] args = new Object[1];
                    args[0] = maturity;
                    rate = curve.getObservableValue(args);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return rate;
    }

    public static final String GET_DEFAULT_IR_CURVE = "getDefaultIRCurve";

    public static CurveObservable getDefaultIRCurve(String currencyCode) {
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
        Product currencyProduct = CurrencyAccessor.getCurrencyProductByCode(currencyCode);
        Integer id = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(curve, currencyProduct, pricingEnvironment);
        Product product = ProductAccessor.getProductById(id);
        curve.setProductId(id);
        curve.setProduct(product);
        return curve;
    }


    public static final String GET_DEFAULT_DISCOUNT_FACTOR = "getDefaultDiscountFactor";

    public static BigDecimal getDefaultDiscountFactor(String currencyCode, Date maturityDate) {
        CurveObservable curve = getDefaultIRCurve(currencyCode);
        BigDecimal discountFactor = curve.getDiscountFactor(maturityDate);
        return discountFactor;
    }
    public static final String GET_DEFAULT_ZERO_PRICE = "getDefaultZeroPrice";

    public static BigDecimal getDefaultZeroPrice(String currencyCode, Date maturityDate) {

        CurveObservable curve = getDefaultIRCurve(currencyCode);
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        curve.fillData(DateUtils.getDate(), quoteSet);
        BigDecimal zeroPrice = curve.getZeroPrice(maturityDate);
        return zeroPrice;
    }
    public static final String GET_DEFAULT_FORWARD_POINTS = "getDefaultForwardPoints";

    public static BigDecimal getDefaultForwardPoints(String currencyCode1, String currencyCode2, Date maturityDate) {
        try {
            BigDecimal zp1 = getDefaultZeroPrice(currencyCode1, maturityDate);
            BigDecimal zp2 = getDefaultZeroPrice(currencyCode2, maturityDate);
            if (zp1 != null && zp2 != null && zp2.doubleValue() != 0) {
                return zp1.divide(zp2, 20, RoundingMode.HALF_UP).subtract(BigDecimal.ONE);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return BigDecimal.ZERO;
    }


    public static final String GET_DEFAULT_FX_FORWARD_CURVE = "getDefaultFxForwardCurve";
    public static CurveObservable getDefaultFxForwardCurve(String currencyCode1,String currencyCode2) {
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.FX_FORWARD_CURVE, AbstractObservable.DISCOUNTING);
        Product currencyPair = ProductAccessor.getProductByShortName(currencyCode1+"/"+currencyCode2);
        Integer observableProductId = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(curve, currencyPair, pricingEnvironment);
        curve.setProduct(ProductAccessor.getProductById(observableProductId));
//        curve.setProduct(PricingEnvironmentAccessor.lookForFxForwardCurveLinkedWithPricingEnvironment(currencyPair, pricingEnvironment));
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        curve.fillData(new Date(), quoteSet);
        return curve;
    }

    public static final String GET_DEFAULT_SPREAD = "getDefaultSpread";
    public static BigDecimal getDefaultSpread(Product product) {
        PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getDefaultPricingEnvironment();
        CurveObservable curve = new CurveObservable(AbstractObservable.ObservableType.CREDIT_CURVE, AbstractObservable.DEFAULT_FORCASTING);
        Integer observableProductId = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(curve, product, pricingEnvironment);
        curve.setProduct(ProductAccessor.getProductById(observableProductId));
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        curve.fillData(new Date(), quoteSet);
        Object[] args = new Object[1];
        args[0] = product.getMaturityDate();
        BigDecimal spread = curve.getObservableValue(args);
        return spread;
    }

}
