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
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.ProductHistoricalObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.ICVAPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerCVAFromDB implements IPricer,ICVAPricer {

    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> arr = new ArrayList();
        if (priced != null) {
            IObservable obs = new ProductHistoricalObservable(priced.getProduct().getId());
            arr.add(obs);
        }
        return arr;
    }

    @Override
     public Map<IPricerMeasure,MeasureValue[]> calculate(IPriceable priced, Map observables, Date dateValo, boolean withDetails) {
         List<IPricerMeasure> list = new ArrayList();

         list.add(MeasuresAccessor.Measure.CVA_unit);
         return PricerNPVFromDB.getMeasure(list,priced,observables,dateValo);
     }

}
