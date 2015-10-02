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
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerFXForward2Curves implements IPricer, INPVPricer {

    @Override
    /**
     *  retrieve list market data.
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> array = new ArrayList();
        if (priced != null && priced.getProduct().getProductForex() != null) {
            //curve 1
            IObservable obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
            Product obsProduct = ProductAccessor.cloneProduct(priced.getProduct());
            obsProduct.setNotionalCurrency(priced.getProduct().getProductForex().getCurrency1().getShortName());
            obs.setLookupProduct(obsProduct);
            array.add(obs);
            //curve 2  : currency 2
            obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
            obsProduct  = ProductAccessor.cloneProduct(obsProduct);
            obsProduct.setNotionalCurrency(priced.getProduct().getProductForex().getCurrency2().getShortName());
            obs.setProduct(obsProduct);
            array.add(obs);
            // fx rate
            obs= new MarketQuoteObservable(priced.getProduct());
            array.add(obs);
        }
        return array;
    }

    @Override
    /**
     *  calculate function for MarketQuoteObservable.
     */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer, IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure, MeasureValue[]> listMeasure = new HashMap<>();

        MeasuresAccessor.Measure pv = MeasuresAccessor.Measure.NPV_unit;
        if (priced != null) {
            Date maturity = null;
            BigDecimal initialRate=BigDecimal.ONE;
            if (priced instanceof Trade) {
                maturity = ((Trade) priced).getValueDate();
                initialRate=((Trade) priced).getPrice();
            }
            BigDecimal discount1=BigDecimal.ONE;
            BigDecimal discount2=BigDecimal.ONE;
            BigDecimal fxRate=BigDecimal.ONE;
            ProductForex forex = priced.getProduct().getProductForex();
            if (maturity != null) {
                for (IObservable observable : observables.values()) {
                    if (observable.getObservableType() == AbstractObservable.ObservableType.IR_CURVE
                            && observable.getUnderlyings().size() > 0) {
                        Product observableUnderlying = observable.getUnderlyings().get(0);
                        if (observableUnderlying.loadSingleUnderlying() != null) {
                            observableUnderlying = observableUnderlying.loadSingleUnderlying();
                        }

                        if (forex.getCurrency1().getProductId().equals(observableUnderlying.getProductId())) {
                            CurveObservable curve1 = (CurveObservable) observable;
                            curve1.generateZeroCouponCurve(null);
                            discount1=curve1.getDiscountFactor(maturity);
                        } else if (forex.getCurrency2().getProductId().equals(observableUnderlying.getProductId())) {
                            CurveObservable curve2 = (CurveObservable) observable;
                            curve2.generateZeroCouponCurve(null);
                            discount2=curve2.getDiscountFactor(maturity);
                        }
                    } else if (observable.getProductId().equals(priced.getProduct().getProductId())){
                        fxRate=((MarketQuoteObservable)observable).getMarketQuote().getClose();
                    }
                }
            }
            BigDecimal npv=BigDecimal.ZERO;
            if (initialRate.doubleValue()!=0){
                npv=(discount1.subtract(initialRate.divide(fxRate, 20, RoundingMode.UP).multiply(discount2)));
            }
            MeasureValue meas = new MeasureValue(pv, npv,forex.getCurrency1().getShortName() , dateValo, priced.getId());
            listMeasure.put(pv, new MeasureValue[]{meas});
        }
        return listMeasure;
    }
}
