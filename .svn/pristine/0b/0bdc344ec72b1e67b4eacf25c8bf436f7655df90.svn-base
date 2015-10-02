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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.ProductHistoricalObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerNPVFromDB implements IPricer,INPVPricer {

    @Override
    /** get Needed Observables
     *  @param priced
     */
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> arr = new ArrayList();
        IObservable obs = new ProductHistoricalObservable(priced.getProduct().getId());
        if (priced != null) {
            arr.add(obs);
        }
        return arr;
    }

    @Override
    /** looks in db
     *@param priced
     *@param observables
     *@param dateValo
     */
     public Map<IPricerMeasure,MeasureValue[]> calculate(IPriceable priced, Map<Integer,IObservable> observables, Date dateValo, boolean withDetails) {
         List<IPricerMeasure> list = new ArrayList();
         list.add(MeasuresAccessor.Measure.NPV);
         list.add(MeasuresAccessor.Measure.NPV_unit);
         return getMeasure(list,priced,observables,dateValo);
     }
   /**  NPV   history
     *@param list
     *@param priced
     *@param observables
     *@param dateValo
     */
     public static Map<IPricerMeasure,MeasureValue[]> getMeasure(List<IPricerMeasure> list,IPriceable priced, Map observables, Date dateValo) {
        Map<IPricerMeasure,MeasureValue[]> listMeasure = new HashMap<>();
        if (priced != null) {
            ProductHistoricalObservable obs = (ProductHistoricalObservable) observables.get(priced.getProduct().getId());
            if (obs != null) {
                if (obs.getHistoricalMeasures() != null) {
                    for (ProductHistoricalMeasureValue phmv : obs.getHistoricalMeasures()) {
                        for (IPricerMeasure m : list){
                            if (m.getName().equals(phmv.getMeasureName())){
                                MeasureValue meas = new MeasureValue(m, phmv.getMeasureValue(), phmv.getCurrency(), dateValo, priced.getId());
                                listMeasure.put(m, new MeasureValue[]{meas});
                            }
                        }
                    }
                }
            }
        }

        return listMeasure;
    }
}
