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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerFromClose implements IPricer ,INPVPricer{

    @Override
    /**
     *  retrieve the quote
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> arr = new ArrayList();
        if (priced != null) {
            IObservable obs = new MarketQuoteObservable(priced.getProduct().getId());
            arr.add(obs);
        }
        return arr;
    }

    @Override
    /**
     *  returns the quote
     */
    public Map<IPricerMeasure,MeasureValue[]> calculate(IPriceable priced, Map<Integer,IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure,MeasureValue[]> listMeasure = new HashMap<>();

        MeasuresAccessor.Measure pv = MeasuresAccessor.Measure.NPV_unit;
        if (priced != null) {
            MarketQuoteObservable obs = (MarketQuoteObservable) observables.get(priced.getProduct().getId());
            if (obs != null) {
                MarketQuote quote = obs.getMarketQuote();
                if (quote != null) {
                    if (quote.getClose()!=null){
                        MeasureValue meas = new MeasureValue(pv, quote.getClose(), quote.getCurrency(), dateValo, priced.getId());
                        listMeasure.put(pv, new MeasureValue[]{meas});
                    } else if (quote.getOpen()!=null){
                        MeasureValue meas = new MeasureValue(pv, quote.getOpen(), quote.getCurrency(), dateValo, priced.getId());
                        listMeasure.put(pv, new MeasureValue[]{meas});
                    }
                } else if (priced instanceof Trade){
                    BigDecimal price=((Trade)priced).getPrice();
                    if (price!=null){
                        MeasureValue meas = new MeasureValue(pv, price, ((Trade)priced).getPriceCurrency(), dateValo, priced.getId());
                        listMeasure.put(pv, new MeasureValue[]{meas});
                    }
                } else if (priced instanceof Position){
                    BigDecimal price=((Position)priced).getPositionHistory(dateValo).getPrice();
                    if (price!=null){
                        MeasureValue meas = new MeasureValue(pv, price, priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
                        listMeasure.put(pv, new MeasureValue[]{meas});
                    }
                }
            }
        }
        return listMeasure;
    }
}
