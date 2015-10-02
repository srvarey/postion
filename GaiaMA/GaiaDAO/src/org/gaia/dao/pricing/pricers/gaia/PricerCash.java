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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.ICashPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerCash implements IPricer, ICashPricer {

    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {

        return null;
    }

    /**
     *
     * @param priced
     * @param observables
     * @param dateValo
     * @param withDetails
     * @return
     */
    @Override
    /**
     *  sums cash flows
     */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer,IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure, MeasureValue[]> listMeasure = new HashMap<> ();

        double cash = 0;

        if (priced instanceof Trade) {
            Trade trade=(Trade) priced;
             // use generated cash flows on value date

            List<Object[]> bdCashSums = (List) HibernateUtil.getObjectsWithQuery("select sum(f.quantity),f.product.shortName from Flow f inner join f.trade t where t.tradeId="+trade.getId()+" and f.valueDate<='"+HibernateUtil.dateFormat.format(dateValo)+"' and f.flowType='"+Flow.FlowType.CASH+"' group by f.product.shortName");
            if (bdCashSums!=null && !bdCashSums.isEmpty()){
                for (Object[] obs : bdCashSums){
                    if (obs[1]!=null){
                        if (obs[1].toString().equalsIgnoreCase(priced.getProduct().getNotionalCurrency())){
                            cash+=((BigDecimal)obs[0]).doubleValue();
                        } else {
                            BigDecimal converted=MarketQuoteAccessor.convertAmount((BigDecimal)obs[0],priced.getProduct().getNotionalCurrency(), obs[1].toString(), dateValo);
                            cash+=converted.doubleValue();
                        }
                    }
                }
            }
        }
        MeasureValue mv = new MeasureValue(MeasuresAccessor.Measure.CASH, BigDecimal.valueOf(cash), priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
        listMeasure.put(MeasuresAccessor.Measure.CASH, new MeasureValue[]{mv});
        return listMeasure;
    }
}
