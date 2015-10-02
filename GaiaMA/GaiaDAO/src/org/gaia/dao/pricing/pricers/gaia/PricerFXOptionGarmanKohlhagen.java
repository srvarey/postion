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
import org.apache.log4j.Logger;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.observables.VolatilityObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerFXOptionGarmanKohlhagen implements IPricer, INPVPricer {

    private static final Logger logger = Logger.getLogger(PricerFXOptionGarmanKohlhagen.class);

    @Override
    /**
     * get the list of market data.
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> array = new ArrayList();
        if (priced != null && priced.getProduct()!=null  && priced.getProduct().getUnderlyingProducts() != null && !priced.getProduct().getUnderlyingProducts().isEmpty() && priced.getProduct().loadSingleUnderlying().getProductForex()!=null) {
            // volatility
            IObservable obs;
            if (priced.getProduct().loadSingleUnderlying() != null) {
                obs = new VolatilityObservable(AbstractObservable.ObservableType.FX_VOLATILITY, AbstractObservable.OBSERVABLE_FX_VOLATILITY);
                obs.setLookupProduct(priced.getProduct().loadSingleUnderlying());
                array.add(obs);
                // spot
                obs = new MarketQuoteObservable(priced.getProduct().loadSingleUnderlying());
                array.add(obs);
                // rate 1
                ProductForex forex = priced.getProduct().loadSingleUnderlying().getProductForex();
                obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
                Product cur1 = ProductAccessor.cloneProduct(priced.getProduct());
                cur1.setNotionalCurrency(forex.getCurrency1().getShortName());
                obs.setLookupProduct(cur1);
                array.add(obs);
                // rate 2
                obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
                Product cur2= ProductAccessor.cloneProduct(priced.getProduct());
                cur2.setNotionalCurrency(forex.getCurrency2().getShortName());
                obs.setLookupProduct(cur2);
                array.add(obs);
            } else {
                logger.error("PricerFXOptionGarmanKohlhagen : not underlying on option " + priced.getProduct().getShortName() + " id " + priced.getProduct().getProductId());
            }
        }
        return array;
    }

    @Override
    /**
     * calculate the NPV
     */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer, IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure, MeasureValue[]> listMeasure = new HashMap<>();

        MeasuresAccessor.Measure pv = MeasuresAccessor.Measure.NPV_unit;
        if (priced != null && priced.getProduct() !=null && priced.getProduct().loadSingleUnderlying() != null && observables!=null) {
            Double spot = null;
            Double volatility;
            Double rate1 = 0.;
            Double rate2 = 0.;
            Double strike = null;
            Double barrier = null;
            String barrierType = null;
            CurveObservable irCurve=null;
            ProductForex forex = priced.getProduct().loadSingleUnderlying().getProductForex();
            VolatilityObservable volatilityObservable = null;

            if (priced.getProduct().getProductForex() != null) {
                if (priced.getProduct().getProductForex().getStrike() != null) {
                    strike = priced.getProduct().getProductForex().getStrike().doubleValue();
                }
                if (priced.getProduct().getProductForex().getBarrier() != null) {
                    barrier = priced.getProduct().getProductForex().getBarrier().doubleValue();
                }
                if (priced.getProduct().getProductForex().getBarrierType() != null) {
                    barrierType = priced.getProduct().getProductForex().getBarrierType();
                }
            }
            for (IObservable observable : observables.values()) {
                if (observable.getObservableType().equals(AbstractObservable.ObservableType.FX_VOLATILITY)) {
                    volatilityObservable = (VolatilityObservable) observable;
                } else if (observable.getObservableType().equals(AbstractObservable.ObservableType.MARKET_QUOTE)) {
                    spot = observable.getObservableValue(null).doubleValue();
                } else if (observable.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                    Object[] coords = new Object[1];
                    coords[0] = priced.getProduct().getMaturityDate();

                    if (observable.getProduct().getNotionalCurrency().equalsIgnoreCase(forex.getCurrency1().getShortName())) {
                        rate1 = observable.getObservableValue(coords).doubleValue();
                        irCurve=(CurveObservable)observable;
                    } else if (observable.getProduct().getNotionalCurrency().equalsIgnoreCase(forex.getCurrency2().getShortName())) {
                        rate2 = observable.getObservableValue(coords).doubleValue();
                    }
                }
            }
            boolean isBarrier = barrierType != null;
            double result = -1;
            double time = DateUtils.dateDiff(dateValo, priced.getProduct().getMaturityDate());
            if (time <= 0) {
                result = 0;
            } else {
                time = time / 365;

                // first,  take the volatility atm
                Object[] coords = new Object[2];
                coords[0] = new BigDecimal(".5");
                coords[1] = priced.getProduct().getMaturityDate();
                if (volatilityObservable != null) {
                    volatility = volatilityObservable.getObservableValue(coords).doubleValue();
                    // second calculate the delta with it
                    double delta = OSJavaQuant.option_delta_european_call_payout(spot, strike, rate1, rate2, volatility, time);

                    // third look for the volatility with this delta
                    coords[0] = new BigDecimal(delta);
                    volatility = volatilityObservable.getObservableValue(coords).doubleValue();

                    // now we've got the right volatility, we can price
                    if (priced.getProduct().getProductForex().getOptionStyle().equalsIgnoreCase(ProductConst.OptionType.Call.name())) {
                        // call
                        if (!isBarrier) {
                            result = OSJavaQuant.option_price_european_call_payout(spot, strike, rate1, rate2, volatility, time);
                        } else if (barrierType!=null){
                            if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)) {
                                result = OSJavaQuant.option_price_euro_call_down_in(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.display)) {
                                result = OSJavaQuant.option_price_euro_call_down_out(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)) {
                                result = OSJavaQuant.option_price_euro_call_up_in(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.display)) {
                                result = OSJavaQuant.option_price_euro_call_up_out(spot, strike, barrier, rate1, rate2, volatility, time);
                            }
                        }

                    } else {
                        // put
                        if (!isBarrier) {
                            result = OSJavaQuant.option_price_european_put_payout(spot, strike, rate1, rate2, volatility, time);
                        } else if (barrierType!=null) {
                            if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)) {
                                result = OSJavaQuant.option_price_euro_put_down_in(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.display)) {
                                result = OSJavaQuant.option_price_euro_put_down_out(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)) {
                                result = OSJavaQuant.option_price_euro_put_up_in(spot, strike, barrier, rate1, rate2, volatility, time);
                            } else if (barrierType.equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.display)) {
                                result = OSJavaQuant.option_price_euro_put_down_out(spot, strike, barrier, rate1, rate2, volatility, time);
                            }
                        }
                    }
                } else {
                    logger.error("Missing volatility for product " + priced.getProduct().toString());
                }
            }
            if (result != -1) {
                if (irCurve!=null){
                    Date settleDate=ProductAccessor.getSettlementDate(priced.getProduct(),dateValo);
                    Double zp=irCurve.getZeroPrice(settleDate).doubleValue();
                    result=result*zp;
                }
                MeasureValue meas = new MeasureValue(pv, new BigDecimal(result), priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
                listMeasure.put(pv, new MeasureValue[]{meas});
            }
        }
        return listMeasure;
    }



}
