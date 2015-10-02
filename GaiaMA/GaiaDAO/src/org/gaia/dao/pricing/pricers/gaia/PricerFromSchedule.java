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
package org.gaia.dao.pricing.pricers.gaia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.schedulers.RateScheduler;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerFromSchedule implements IPricer, INPVPricer {

    @Override
    /**
     *  retrieve the list of market data.
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> array = new ArrayList();
        if (priced != null && priced.getProduct() != null) {
            List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProduct(priced.getProduct());
            List<String> currencies = new ArrayList();
            List<String> indexRates = new ArrayList();
            for (ScheduleLine line : lines) {
                if (!currencies.contains(line.getCurrency())) {
                    // Discount IR curve
                    IObservable obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
                    obs.setLookupProduct(ProductAccessor.cloneProduct(priced.getProduct()));
                    obs.getLookupProduct().setNotionalCurrency(line.getCurrency());

//                    Product cur=CurrencyAccessor.getCurrencyProductByCode(line.getCurrency());
//                    cur.setProductType(priced.getProduct().getProductType());
//                    obs.setLookupProduct(cur);
                    array.add(obs);
                    currencies.add(line.getCurrency());
                }
                if (!line.isFixed() && line.getFixingProduct() != null) {
                    if (!indexRates.contains(line.getFixingProduct().getShortName())) {
                        // Forward IR curve
                        IObservable obsForecast = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.FORCASTING);
                        obsForecast.setLookupProduct(line.getFixingProduct());
                        array.add(obsForecast);
                        indexRates.add(line.getFixingProduct().getShortName());
                    }
                }
                if (priced.getProduct().getProductCredit() != null && priced.getProduct().getProductCredit().getIssuer() != null) {
                    // was made for bond pricing : we add the credit part
                    // credit curve
                    IObservable obs = new CurveObservable(AbstractObservable.ObservableType.CREDIT_CURVE, AbstractObservable.DEFAULT_FORCASTING);
                    array.add(obs);
                    // recovery rate curve
                    IObservable obsRR = new CurveObservable(AbstractObservable.ObservableType.RECOVERY_CURVE, AbstractObservable.DEFAULT_FORCASTING);
                    array.add(obsRR);
                }
            }
        }
        return array;
    }

    @Override
    /**
     *  calculate the unit npv
     */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer, IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure, MeasureValue[]> listMeasure = new HashMap<>();
        Map<String, Integer> discountCurves = new HashMap();
        Map<String, Integer> forecastCurves = new HashMap();
        BigDecimal value = BigDecimal.ZERO;
        MeasuresAccessor.Measure pv = MeasuresAccessor.Measure.NPV_unit;
        CurveObservable creditCurve = null;
        CurveObservable RRCurve = null;
        if (priced != null) {
            for (IObservable obs_ : observables.values()) {
                if (obs_ instanceof CurveObservable) {
                    if (obs_.getFunction().equalsIgnoreCase(AbstractObservable.DISCOUNTING)) {
                        discountCurves.put(obs_.getProduct().getNotionalCurrency(), obs_.getProductId());
                    } else if (obs_.getFunction().equalsIgnoreCase(AbstractObservable.FORCASTING)) {
                        forecastCurves.put(obs_.getProduct().getNotionalCurrency(), obs_.getProductId());
                    } else if (obs_.getFunction().equalsIgnoreCase(AbstractObservable.DEFAULT_FORCASTING)) {
                        if (obs_.getObservableType().name().equalsIgnoreCase(AbstractObservable.ObservableType.CREDIT_CURVE.name())) {
                            creditCurve = (CurveObservable) obs_;
                        } else if (obs_.getObservableType().name().equalsIgnoreCase(AbstractObservable.ObservableType.RECOVERY_CURVE.name())) {
                            RRCurve = (CurveObservable) obs_;
                        }
                    }
                }
            }

            List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProduct(priced.getProduct());

            for (ScheduleLine line : lines) {
                if (line.getPaymentDate().after(dateValo)) {
                    CurveObservable discountCurve = (CurveObservable) observables.get(discountCurves.get(line.getCurrency()));
                    if (discountCurve == null) {
                        discountCurve = (CurveObservable) observables.get(forecastCurves.get(line.getCurrency()));
                    }
                    BigDecimal amount = line.getPaymentAmount();
                        if (!line.isFixed() && line.getFixingProduct() != null) {
                            CurveObservable forecastCurve = (CurveObservable) observables.get(forecastCurves.get(line.getCurrency()));
                            if (forecastCurve == null) {
                                forecastCurve = (CurveObservable) observables.get(discountCurves.get(line.getCurrency()));
                            }
                            if (forecastCurve != null) {
                                BigDecimal df1 = forecastCurve.getDiscountFactor(line.getResetDate());
                                String tenor = line.getFixingProduct().getProductCurve().getTenor();
                                Date endDate = DateIntervalUtil.getDateFromStartAndTenor(line.getResetDate(), tenor);
                                BigDecimal df2 = forecastCurve.getDiscountFactor(endDate);
                                BigDecimal rate = df1;
                                if (df2.doubleValue() != 0) {
                                    rate = rate.divide(df2, 20, RoundingMode.HALF_UP);
                                }
                                rate = rate.subtract(BigDecimal.ONE);
                                double time = DateUtils.dateDiff(line.getResetDate(), endDate);
                                time = time / 365.;
                                if (time != 0) {
                                    rate = rate.divide(new BigDecimal(time), 20, RoundingMode.UP);
                                }
                                line.setFixing(rate);
                                amount = new RateScheduler().calculateAmount(line);
                            }
                        }
                        if (line.getProduct().getProductRate() != null && line.getProduct().getProductRate().getInitialNotional() != null
                                && line.getProduct().getProductRate().getInitialNotional().doubleValue() != 0) {
                            amount = amount.divide(line.getProduct().getProductRate().getInitialNotional(), 20, RoundingMode.UP);//
                        }
                        BigDecimal creditDF = BigDecimal.ONE;
                        if (creditCurve != null) {
                            creditDF = creditCurve.getDiscountFactor(line.getPaymentDate());
                        }
                        if (RRCurve != null && creditCurve != null) {
                            double defaultprobe = 1 - creditDF.doubleValue();
                            defaultprobe = defaultprobe * (1 - RRCurve.getObservableValue(new Object[]{line.getPaymentDate()}).doubleValue());
                            creditDF = BigDecimal.ONE.subtract(new BigDecimal(defaultprobe));
                        }
                        if (creditDF.doubleValue() != 0) {
                            amount = amount.multiply(creditDF);
                        }

                        if (discountCurve != null) {
                            BigDecimal df = discountCurve.getDiscountFactor(line.getPaymentDate());
                            if (df.doubleValue() != 0) {
                                amount = amount.multiply(df);
                            }

                        }
                        value = value.add(amount);
                }
            }
            MeasureValue meas = new MeasureValue(pv, value, priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
            listMeasure.put(pv, new MeasureValue[]{meas});
        }
        return listMeasure;
    }
}
