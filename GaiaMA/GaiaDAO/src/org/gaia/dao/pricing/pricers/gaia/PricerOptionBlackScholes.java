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
package org.gaia.dao.pricing.pricers.gaia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.VolatilityObservable;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerOptionBlackScholes implements IPricer, INPVPricer {

    @Override
    /**
     * retrieve list market data.
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> array = new ArrayList();
        if (priced != null && priced.getProduct().loadSingleUnderlying() != null) {
            // volatility
            IObservable obs = new VolatilityObservable(AbstractObservable.ObservableType.VOLATILITY, AbstractObservable.VOLATILITY);
            obs.setProduct(priced.getProduct().loadSingleUnderlying());
            array.add(obs);
            // spot
            obs = new VolatilityObservable(AbstractObservable.ObservableType.MARKET_QUOTE, AbstractObservable.OBSERVABLE_MARKET_QUOTE);
            obs.setProduct(priced.getProduct().loadSingleUnderlying());
            array.add(obs);
            // rate
            obs = new VolatilityObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
            array.add(obs);
        }
        return array;
    }

    @Override
    /**
     * calculate function for MarketQuoteObservable.
     */
    public Map<MeasuresAccessor.Measure, MeasureValue[]> calculate(IPriceable priced, Map observables, Date dateValo, boolean withDetails) {
        Map<MeasuresAccessor.Measure, MeasureValue[]> listMeasure = new HashMap<>();

        MeasuresAccessor.Measure pv = MeasuresAccessor.Measure.NPV_unit;
        if (priced != null && priced.getProduct().getUnderlyingProducts() != null) {
            Double spot = null;
            Double volatility = null;
            Double rate = null;
            Double strike = null;
            Double dividendRate = 0.;
            Double barrier = null;
            String barrierType = null;
            for (Object oobservable : observables.values()) {
                IObservable observable = (IObservable) oobservable;
                if (observable.getProductId().equals(priced.getProduct().loadSingleUnderlying().getProductId())) {
                    if (observable.getObservableType().equals(AbstractObservable.ObservableType.MARKET_QUOTE)) {
                        spot = observable.getObservableValue(null).doubleValue();
                    } else if (observable.getObservableType().equals(AbstractObservable.ObservableType.VOLATILITY)) {
                        strike = spot;
                        if (priced.getProduct().getProductEquity() != null) {
                            if (priced.getProduct().getProductEquity().getStrike() != null) {
                                strike = priced.getProduct().getProductEquity().getStrike();
                            }
//                            if (priced.getProduct().getProductEquity().getBarrier() != null) {
//                                barrier = priced.getProduct().getProductEquity().getBarrier().doubleValue();
//                            }
//                            if (priced.getProduct().getProductEquity().getBarrierType() != null) {
//                                barrierType = priced.getProduct().getProductEquity().getBarrierType();
//                            }
                        }
                        Object[] coords = new Object[2];
                        coords[0] = priced.getProduct().getMaturityDate();
                        coords[1] = strike;
                        volatility = observable.getObservableValue(coords).doubleValue();
                    }
                }
                if (observable.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                    Object[] coords = new Object[1];
                    coords[0] = priced.getProduct().getMaturityDate();
                    rate = observable.getObservableValue(coords).doubleValue();
                }
            }
            boolean isBarrier = barrierType != null;
            double result=-1;
            double time = DateUtils.dateDiff(dateValo, priced.getProduct().getMaturityDate()) / 365;

            if (priced.getProduct().getProductEquity().getOptionStyle().equalsIgnoreCase(ProductConst.OptionType.Call.name())) {
                // call
                if (!isBarrier) {
                    result=OSJavaQuant.option_price_european_call_payout(spot, strike, rate, dividendRate, volatility, time);
                } else {
                    if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.name())) {
                        result=OSJavaQuant.option_price_euro_call_down_in(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.name())) {
                        result=OSJavaQuant.option_price_euro_call_down_out(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.name())) {
                        result=OSJavaQuant.option_price_euro_call_up_in(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.name())) {
                        result=OSJavaQuant.option_price_euro_call_up_out(spot, strike, barrier, rate, dividendRate, volatility, time);
                    }
                }

            } else {
                // put
                if (!isBarrier) {
                    result=OSJavaQuant.option_price_european_put_payout(spot, strike, rate, dividendRate, volatility, time);
                } else {
                    if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.name())) {
                        result=OSJavaQuant.option_price_euro_put_down_in(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.name())) {
                        result=OSJavaQuant.option_price_euro_put_down_out(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.name())) {
                        result=OSJavaQuant.option_price_euro_put_up_in(spot, strike, barrier, rate, dividendRate, volatility, time);
                    } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.name())) {
                        result=OSJavaQuant.option_price_euro_put_down_out(spot, strike, barrier, rate, dividendRate, volatility, time);
                    }
                }
            }
            if (result != -1) {
                MeasureValue meas = new MeasureValue(pv, new BigDecimal(result), priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
                listMeasure.put(pv, new MeasureValue[]{meas});
            }
        }
        return listMeasure;
    }
}
