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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class VolatilityObservable extends AbstractObservable implements Serializable {

    protected ArrayList<Product> underlyings;
    protected List<MarketQuote> quotes;
    private static final Logger logger = Logger.getLogger(VolatilityObservable.class);

    public VolatilityObservable() {
    }

    public VolatilityObservable(ObservableType observableType, String function) {
        this.observableType = observableType;
        this.function = function;
    }

    public VolatilityObservable(ObservableType observableType, String function, ArrayList<Product> underlyings, ArrayList<MarketQuote> quotes) {
        this.observableType = observableType;
        this.function = function;
        this.underlyings = underlyings;
        this.quotes = quotes;
        this.product = ProductAccessor.getParentProduct(underlyings);

    }

    /**
     * fill Data .
     * @param date
     * @param quoteSet
     */
    @Override
    public void fillData(Date date, String quoteSet) {
        if (productId != null) {
            valDate = date;
            underlyings = new ArrayList();

            date = DateUtils.adjustDate(date, DateUtils.ADJUSTMENT_PREVIOUS, null);
            valDate = date;

            quotes = (ArrayList) MarketQuoteAccessor.getLastCurveByDate(product, quoteSet, date);
            if (quotes != null) {
                for (MarketQuote mq : quotes) {
                    underlyings.add(mq.getProduct());
                }
                Collections.sort(underlyings, this.new TenorComparator());

                if (product == null) {
                    product = ProductAccessor.getParentProduct(underlyings);
                }
                for (MarketQuote marketQuotes : quotes) {
                    String tenor;
                    if (marketQuotes.getProduct().getProductCurve() != null) {
                        tenor = marketQuotes.getProduct().getProductCurve().getTenor();
                        Date endDate = DateIntervalUtil.getDateFromStartAndTenor(date, tenor);
                        marketQuotes.setDateEnd(endDate);
                    }
                }
                Collections.sort(quotes);

                isFilled = true;
            } else {
                logger.error("VolatilityObservable.fillData : no quotes found for " + productId);
            }
        }
    }

    @Override
    public void replaceQuote(Integer productId, Double last) {
        for (MarketQuote marketQuote : quotes) {
            if (marketQuote.getProduct().getProductId().intValue() == productId.intValue()) {
                marketQuote.setClose(BigDecimal.valueOf(last));
            }
        }
    }

    @Override
    public BigDecimal getObservableValue(Object[] coord) {
        BigDecimal ret = null;
        if (coord != null && quotes != null) {
            if (coord.length == 1 && coord[0] instanceof Date) {
                Object[] newCoord = new Object[2];
                newCoord[0] = new BigDecimal(".5"); // delta
                newCoord[1] = coord[0];             // maturity
                coord = newCoord;
            }

            if (coord.length > 1 && quotes.size() > 0) {
                Object object1 = coord[0];
                Object object2 = coord[1];
                if (object1 instanceof BigDecimal && object2 instanceof Date) {
                    Date maturity = (Date) object2;
                    BigDecimal strike = (BigDecimal) object1;
                    Date dateBeforeInit = DateUtils.addYear(valDate, -100);
                    Date dateBefore = dateBeforeInit;
                    Date dateAfterInit = DateUtils.addYear(valDate, 100);
                    Date dateAfter = dateAfterInit;
                    BigDecimal strikeLess = BigDecimal.ZERO;
                    BigDecimal strikeMore = new BigDecimal("1000000");

                    for (MarketQuote quote : quotes) {
                        if (quote.getDateEnd().after(dateBefore) && !maturity.before(quote.getDateEnd())) {
                            dateBefore = quote.getDateEnd();
                        }
                        if (quote.getDateEnd().before(dateAfter) && !maturity.after(quote.getDateEnd())) {
                            dateAfter = quote.getDateEnd();
                        }
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() > strikeLess.doubleValue() && quote.getProduct().getProductCurve().getStrike().doubleValue() <= strike.doubleValue()) {
                            strikeLess = quote.getProduct().getProductCurve().getStrike();
                        }
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() < strikeMore.doubleValue() && quote.getProduct().getProductCurve().getStrike().doubleValue() >= strike.doubleValue()) {
                            strikeMore = quote.getProduct().getProductCurve().getStrike();
                        }
                    }
                    if (strikeLess.doubleValue() == 0) {
                        strikeLess = strikeMore;
                    }
                    if (strikeMore.doubleValue() == 1000000) {
                        strikeMore = strikeLess;
                    }
                    if (dateAfter.equals(dateAfterInit)) {
                        dateAfter = dateBefore;
                    }
                    if (dateBefore.equals(dateBeforeInit)) {
                        dateBefore = dateAfter;
                    }
                    BigDecimal wholeStrikeInterval = strikeMore.subtract(strikeLess);
                    BigDecimal strikeInterval = strike.subtract(strikeLess);
                    BigDecimal datePeriodToValue = new BigDecimal(DateUtils.dateDiff(dateBefore, maturity));
                    BigDecimal wholeDatePeriod = new BigDecimal(DateUtils.dateDiff(dateBefore, dateAfter));
                    BigDecimal basePoint = null;
                    BigDecimal timeMaxPoint = null;
                    BigDecimal strikeMaxPoint = null;
                    BigDecimal maxPoint = null;
                    for (MarketQuote quote : quotes) {
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() == strikeLess.doubleValue() && quote.getDateEnd().equals(dateBefore)) {
                            basePoint = quote.getClose();
                        }
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() == strikeLess.doubleValue() && quote.getDateEnd().equals(dateAfter)) {
                            timeMaxPoint = quote.getClose();
                        }
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() == strikeMore.doubleValue() && quote.getDateEnd().equals(dateBefore)) {
                            strikeMaxPoint = quote.getClose();
                        }
                        if (quote.getProduct().getProductCurve().getStrike().doubleValue() == strikeMore.doubleValue() && quote.getDateEnd().equals(dateAfter)) {
                            maxPoint = quote.getClose();
                        }
                    }
                    ret = BigDecimal.ZERO;
                    if (wholeStrikeInterval.doubleValue() != 0 && strikeMore.doubleValue() < 1000000) {
                        if (strikeMaxPoint == null) {
                            strikeMaxPoint = maxPoint;
                        }
                        BigDecimal wholeStrikeIntervalVol = strikeMaxPoint.subtract(basePoint);
                        ret = wholeStrikeIntervalVol.multiply(strikeInterval).divide(wholeStrikeInterval, 20, RoundingMode.HALF_DOWN);
                    }
                    if (wholeDatePeriod.doubleValue() != 0) {
                        if (timeMaxPoint == null) {
                            timeMaxPoint = maxPoint;
                        }
                        BigDecimal wholeDateIntervalVol = timeMaxPoint.subtract(basePoint);
                        if (ret.doubleValue()==0){
                            ret = wholeDateIntervalVol.multiply(datePeriodToValue).divide(wholeDatePeriod, 20, RoundingMode.UP);
                        } else {
                            ret = ret.multiply(wholeDateIntervalVol).multiply(datePeriodToValue).divide(wholeDatePeriod, 20, RoundingMode.UP);
                        }
                    }
                    ret = ret.add(basePoint);

                }
            } else {
                logger.error("Volatility Observable needs strike and date as coordinates");
            }
        }
        return ret;
    }

    public Double strikeNewtonRaphson(Date maturity,Double targetDelta) {

        Double y2;
        Double y1;
        Double x0=1.;
        Double x1=targetDelta;
        Double epsilon=0.00001;
        double dx=1.;
        Object[] coord1 = new Object[2];
        coord1[1]=maturity;
        Object[] coord2 = new Object[2];
        coord2[1]=maturity;
        while (Math.abs(dx) > 0.00000001) {

            coord1[0]=BigDecimal.valueOf(x1);
            coord2[0]=BigDecimal.valueOf(x1+epsilon);

            y1 = getObservableValue(coord1).doubleValue();
            y2 = getObservableValue(coord2).doubleValue();

            dx = (y1 - y2) / epsilon;
            if (dx!=0){
               x1 = x0-(targetDelta-y1)/dx;
            } else {
                dx=0;
            }
        }
        return x1;
    }

    @Override
    public BigDecimal getObservableValueFromPriceable(IPriceable priceable) {
        if (priceable != null && priceable.getProduct() != null && priceable.getProduct().getMaturityDate() != null) {
            return getObservableValue(new Object[]{priceable.getProduct().getMaturityDate()});
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getUnderlyings() {
        return underlyings;
    }


    public void setUnderlyings(ArrayList<Product> underlyings) {
        this.underlyings = underlyings;
    }

    public List<MarketQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<MarketQuote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public IObservable shift(List<ObservableShift> shifts, boolean isAbsolute) {
        VolatilityObservable newObs = null;
        try {
            newObs = (VolatilityObservable) this.clone();
            List<MarketQuote> newQuotes = newObs.getQuotes();
            if (isAbsolute) {
                for (ObservableShift shift : shifts) {
                    for (MarketQuote quote : newQuotes) {
                        if ((shift.getCoordinate1() == null || quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(shift.getCoordinate1()))
                                && (shift.getCoordinate2() == null || quote.getProduct().getProductForex().getStrike().doubleValue() == Double.parseDouble(shift.getCoordinate2()))) {
                            quote.setClose(quote.getClose().add(shift.getShift()));
                        }
                    }
                }
            } else {
                for (ObservableShift shift : shifts) {
                    BigDecimal tmp = shift.getShift().add(BigDecimal.ONE);
                    for (MarketQuote quote : newQuotes) {
                        if ((shift.getCoordinate1() == null || quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(shift.getCoordinate1()))
                                && (shift.getCoordinate2() == null || quote.getProduct().getProductForex().getStrike().doubleValue() == Double.parseDouble(shift.getCoordinate2()))) {
                            quote.setClose(quote.getClose().multiply(tmp).subtract(BigDecimal.ONE));
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("error" + StringUtils.formatErrorMessage(e));
        }
        return newObs;
    }

    @Override
    public Class[] getCoordinateClass() {
        return new Class[]{Date.class, BigDecimal.class};
    }

    @Override
    public Product getProduct() {
        return product;
    }

    public class TenorComparator implements Comparator<Product> {

        private final CurveObservable curve = new CurveObservable(ObservableType.IR_CURVE, DISCOUNTING);
        private final CurveObservable.TenorComparator comp = curve.new TenorComparator();

        @Override
        public int compare(Product p1, Product p2) {
            if (p1.getProductCurve() == null || p2.getProductCurve() == null) {
                return 0;
            } else if (p1.getProductCurve().getStrike() == null || p2.getProductCurve().getStrike() == null) {
                return comp.compare(p1, p2);
            } else {
                BigDecimal strike1 = p1.getProductCurve().getStrike();
                BigDecimal strike2 = p2.getProductCurve().getStrike();
                if (strike1.equals(strike2)) {
                    return comp.compare(p1, p2);
                } else {
                    return (strike1.doubleValue() < strike2.doubleValue()) ? -1 : 1;
                }
            }
        }
    }

    @Override
    public Object clone() {
        VolatilityObservable curveObservable;
        curveObservable = (VolatilityObservable) super.clone();

        curveObservable.setQuotes(new ArrayList());
        for (MarketQuote mq : this.quotes) {
            curveObservable.quotes.add(mq.clone());
        }
        curveObservable.setUnderlyings(new ArrayList());
        for (Product u : this.underlyings) {
            curveObservable.underlyings.add(u);
        }
        return curveObservable;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VolatilityObservable)) {
            return false;
        }
        VolatilityObservable other = (VolatilityObservable) object;
        if (this.productId != null && other.productId != null && this.productId == other.productId) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "VolatilityObservable";
        if (product != null && product.getShortName() != null) {
            str += "/" + product.getShortName();
        }
        if (productId != null) {
            str += "/" + productId.toString();
        }
        return str;
    }
}
