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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.pricing.pricers.isda.CurveUtilsIsda;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.pricing.pricers.isda.IsdaCurve;
import org.gaia.dao.pricing.pricers.isda.IsdaInputCurve;
import org.gaia.dao.pricing.pricers.isda.IsdaPricerCaller;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class CurveObservable extends AbstractObservable implements Serializable {

    protected ArrayList<Product> underlyings;
    protected List<MarketQuote> quotes;
    protected IsdaCurve isdaCurve;
    private static final Logger logger = Logger.getLogger(CurveObservable.class);

    public CurveObservable() {
    }

    public CurveObservable(ObservableType observableType, String function) {
        this.observableType = observableType;
        this.function = function;
    }

    /**
     * fill Data .
     *
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

            product = ProductAccessor.getProductById(productId);

            quotes = (ArrayList) MarketQuoteAccessor.getLastCurveByDate(product, quoteSet, date);
            if (quotes != null) {
                for (MarketQuote mq : quotes) {
                    underlyings.add(mq.getProduct());
                }

                Collections.sort(underlyings, this.new TenorComparator());

                for (MarketQuote marketQuote : quotes) {
                    String tenor;
                    if (marketQuote.getProduct().getProductCurve() != null) {
                        tenor = marketQuote.getProduct().getProductCurve().getTenor();
                        Date endDate = DateIntervalUtil.getDateFromStartAndTenor(date, tenor);
                        marketQuote.setDateEnd(endDate);
                    }
                }

                Collections.sort(quotes);
            }
            if (product == null) {
                product = ProductAccessor.getParentProduct(underlyings);
            }
            generateZeroCouponCurve();
            isFilled = true;
        }
    }

    @Override
    public void replaceQuote(Integer productId, Double last) {
        if (quotes!=null){
            for (MarketQuote marketQuote : quotes) {
                if (marketQuote.getProduct().getProductId().intValue() == productId.intValue()) {
                    marketQuote.setClose(BigDecimal.valueOf(last));
                }
            }
            generateZeroCouponCurve();
        }
    }

    @Override
    public BigDecimal getObservableValue(Object[] coord) {
        BigDecimal ret = null;
        if (coord != null && quotes != null) {
            if (coord.length > 0 && quotes.size() > 0) {
                Object object = coord[0];
                if (object instanceof Date) {
                    Date valueDate = (Date) object;
                    int i = 0;
                    while (i < isdaCurve.getDates().size() && isdaCurve.getDates().get(i).before(valueDate)) {
                        i++;
                    }
                    if (i == isdaCurve.getRates().size()) {
                        ret = BigDecimal.valueOf(isdaCurve.getRates().get(isdaCurve.getRates().size() - 1));
                    } else if (i == 0) {
                        ret = BigDecimal.valueOf(isdaCurve.getRates().get(0));
                    } else {
                        /**
                         * lineaire
                         */
                        double tmp = isdaCurve.getRates().get(i).doubleValue() - isdaCurve.getRates().get(i - 1).doubleValue();
                        long numerator = DateUtils.dateDiff(quotes.get(i - 1).getDateEnd(), valueDate);
                        long denominator = DateUtils.dateDiff(quotes.get(i - 1).getDateEnd(), quotes.get(i).getDateEnd());
                        tmp = tmp * numerator;
                        tmp = tmp / denominator;
                        tmp = tmp + isdaCurve.getRates().get(i - 1).doubleValue();
                        ret = BigDecimal.valueOf(tmp);
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public Class[] getCoordinateClass() {
        return new Class[]{Date.class};
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
        CurveObservable newObs = null;
        try {
            int i;
            newObs = (CurveObservable) this.clone();
            List<MarketQuote> newQuotes = newObs.getQuotes();
            if (isAbsolute) {
                for (ObservableShift shift : shifts) {
                    i = 0;
                    for (MarketQuote quote : newQuotes) {
                        if (shift.getCoordinate1() == null || quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(shift.getCoordinate1())) {
                            quote.setClose(quote.getClose().add(shift.getShift()));
                            if (i<isdaCurve.getRates().size()){
                                isdaCurve.getRates().set(i, isdaCurve.getRates().get(i) + shift.getShift().doubleValue());
    //                            double time=DateUtils.dateDiff(isdaCurve.getDates().get(i),isdaCurve.getBaseDate())/365.;
    //                            isdaCurve.getDiscount().set(i,isdaCurve.getDiscount().get(i)*Math.exp(-shift.getShift().doubleValue()*time));
                            }
                        }
                        i++;
                    }
                }
            } else {
                for (ObservableShift shift : shifts) {
                    i = 0;
                    BigDecimal tmp = shift.getShift().add(BigDecimal.ONE);
                    for (MarketQuote quote : newQuotes) {
                        if (shift.getCoordinate1() == null || quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(shift.getCoordinate1())) {
                            quote.setClose(quote.getClose().multiply(tmp));
//                            double rate=isdaCurve.getRates().get(i);
                            if (i<isdaCurve.getRates().size()){
                                isdaCurve.getRates().set(i, isdaCurve.getRates().get(i) * tmp.doubleValue());
    //                            double time=DateUtils.dateDiff(isdaCurve.getDates().get(i),isdaCurve.getBaseDate())/365.;
    //                            isdaCurve.getDiscount().set(i,Math.exp(-rate*tmp.doubleValue()*time));
                            }
                        }
                        i++;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("error" + StringUtils.formatErrorMessage(e));
            logger.error("product : " + product.toString());
        }
        return newObs;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    public class TenorComparator implements Comparator<Product> {

        @Override
        public int compare(Product p1, Product p2) {
            if (p1.getProductCurve() == null || p2.getProductCurve() == null) {
                return 0;
            } else if (p1.getProductCurve().getTenor() == null || p2.getProductCurve().getTenor() == null) {
                return 0;
            } else if (p1.getProductCurve().getTenor().isEmpty() || p2.getProductCurve().getTenor().isEmpty()) {
                return 0;
            } else {
                String letter1 = p1.getProductCurve().getTenor().substring(p1.getProductCurve().getTenor().length() - 1);
                String letter2 = p2.getProductCurve().getTenor().substring(p2.getProductCurve().getTenor().length() - 1);
                if ((letter1.equals("D") && letter2.equals("M"))
                        || (letter1.equals("M") && letter2.equals("Y"))
                        || (letter1.equals("D") && letter2.equals("Y"))
                        || (letter1.equals("D") && letter2.equals("W"))
                        || (letter1.equals("W") && letter2.equals("M"))
                        || (letter1.equals("W") && letter2.equals("Y"))
                        || (letter1.equals("D") && letter2.equals("N"))
                        || (letter1.equals("N") && letter2.equals("M"))// T/N
                        || (letter1.equals("N") && letter2.equals("W"))
                        || (letter1.equals("N") && letter2.equals("Y"))) {
                    return -1;
                } else if ((letter1.equals("Y") && letter2.equals("M"))
                        || (letter1.equals("M") && letter2.equals("D"))
                        || (letter1.equals("Y") && letter2.equals("D"))
                        || (letter1.equals("W") && letter2.equals("D"))
                        || (letter1.equals("Y") && letter2.equals("N"))
                        || (letter1.equals("M") && letter2.equals("N"))
                        || (letter1.equals("W") && letter2.equals("N"))
                        || (letter1.equals("N") && letter2.equals("D"))
                        || (letter1.equals("M") && letter2.equals("W"))
                        || (letter1.equals("Y") && letter2.equals("W"))) {
                    return 1;
                } else {
                    String sId1 = p1.getProductCurve().getTenor().substring(0, p1.getProductCurve().getTenor().length() - 1);
                    Integer i1;
                    if (sId1.equalsIgnoreCase("T")) {
                        i1 = 1;
                    } else {
                        i1 = Integer.parseInt(sId1);
                    }
                    String sId2 = p2.getProductCurve().getTenor().substring(0, p2.getProductCurve().getTenor().length() - 1);
                    Integer i2;
                    if (sId2.equalsIgnoreCase("T")) {
                        i2 = 1;
                    } else {
                        i2 = Integer.parseInt(p2.getProductCurve().getTenor().substring(0, p2.getProductCurve().getTenor().length() - 1));
                    }
                    return (i1 > i2 ? 1 : (i1 == i2 ? 0 : -1));
                }
            }
        }
    }

    @Override
    public Object clone() {
        CurveObservable curveObservable;
        curveObservable = (CurveObservable) super.clone();

        curveObservable.setQuotes(new ArrayList());
        if (quotes != null) {
            for (MarketQuote mq : this.quotes) {
                curveObservable.quotes.add((MarketQuote) mq.clone());
            }
        }
        curveObservable.setUnderlyings(new ArrayList());
        for (Product u : this.underlyings) {
            curveObservable.underlyings.add(u);
        }
        return curveObservable;
    }

    public void generateZeroCouponCurve() {
        generateZeroCouponCurve(null);
    }

    public void generateZeroCouponCurve(String dayCountbasis) {

        if (quotes != null) {
            try {
                /*
                 *  build of discount curve .
                 */
                IsdaInputCurve discCurve = IsdaPricerCaller.getCurveFromCurveObservable(this);

                if (discCurve != null) {
                    if (dayCountbasis == null || dayCountbasis.isEmpty()) {
                        dayCountbasis = DayCountAccessor.DayCount.ACT_365.name;
                    }

                    HolidayCalendar holidayCalendar = new HolidayCalendar();

//                isdaCurve = new IsdaCurve();
//                isdaCurve.setBaseDate(valDate);
//                isdaCurve.setBasis(IsdaPricerCaller.getLongDayCount(dayCountbasis).doubleValue());
                    int i = 0;
                    Date valueDate = valDate;
                    valueDate = DateUtils.adjustDate(valueDate, DateUtils.ADJUSTMENT_FOLLOW, null);

                    Date[] dates = new Date[quotes.size()];
                    double[] rates = new double[quotes.size()];
                    for (MarketQuote quote : quotes) {
                        Date dateEnd = quote.getDateEnd();
                        dateEnd = DateUtils.adjustDate(dateEnd, DateUtils.ADJUSTMENT_FOLLOW, null);
                        dates[i] = dateEnd;
                        rates[i] = quote.getClose().doubleValue();
                        i++;
                    }

                    //java calculation
                    isdaCurve = CurveUtilsIsda.JpmcdsBuildIRZeroCurve(valueDate, discCurve.instrNames, dates, rates, discCurve.nInstr.longValue(),
                            dayCountbasis, discCurve.fixedSwapFreq.longValue(), discCurve.floatSwapFreq.longValue(), IsdaPricerCaller.getStringDayCount(discCurve.fixedSwapDCC),
                            IsdaPricerCaller.getStringDayCount(discCurve.fixedSwapDCC), IsdaPricerCaller.getStringBadDays(discCurve.irbadDayConv.longValue()),
                            holidayCalendar);

                }
            } catch (Exception e) {
                logger.error("error" + StringUtils.formatErrorMessage(e));
            }
        }
    }

    public Date getZCCurveBaseDate() {
        if (isdaCurve != null) {
            return isdaCurve.getBaseDate();
        }
        return null;
    }

    public ArrayList<Date> getZCCurveDates() {
        if (isdaCurve != null) {
            return isdaCurve.getDates();
        }
        return new ArrayList();
    }

    public ArrayList<Double> getZCCurveRates() {
        if (isdaCurve != null) {
            return isdaCurve.getRates();
        }
        return null;
    }

    public ArrayList<Double> getZCCurveDiscountFactors() {
        if (isdaCurve != null) {
            return isdaCurve.getDiscount();
        }
        return null;
    }

    public BigDecimal getZeroPrice(Date valueDate) {
        double zeroPrice = CurveUtilsIsda.JpmcdsZeroPrice(isdaCurve, valueDate);
        return new BigDecimal(zeroPrice);
    }

    public BigDecimal getDiscountFactor(Date valueDate) {
        double zeroPrice = CurveUtilsIsda.JpmcdsZeroPrice(isdaCurve, valueDate);
        return new BigDecimal(1 / zeroPrice);
    }

    public void setIsdaCurve(IsdaCurve isdaCurve) {
        this.isdaCurve = isdaCurve;
    }

    @Override
    public String toString() {
        String str = "CurveObservable";
        if (observableType != null && observableType.name != null) {
            str += StringUtils.SPACE + observableType.name;
        }
        if (product != null && product.getShortName() != null) {
            str += "/" + product.getShortName();
        }
        if (productId != null) {
            str += "/" + productId.toString();
        }
        return str;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CurveObservable)) {
            return false;
        }
        CurveObservable other = (CurveObservable) object;
        if (this.observableType != null && other.observableType != null && this.observableType.name.equalsIgnoreCase(other.observableType.name)
                && this.product != null && other.product != null && this.product.getNotionalCurrency() != null && other.product.getNotionalCurrency() != null
                && this.product.getNotionalCurrency().equalsIgnoreCase(other.product.getNotionalCurrency())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.underlyings);
        hash = 97 * hash + Objects.hashCode(this.quotes);
        hash = 97 * hash + Objects.hashCode(this.isdaCurve);
        return hash;
    }
}
