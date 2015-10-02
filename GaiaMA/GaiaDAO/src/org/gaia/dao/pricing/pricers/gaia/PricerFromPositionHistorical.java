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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.domain.reports.PositionHistoricalMeasure;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerFromPositionHistorical implements IPricer, INPVPricer {

    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {
        return null;
    }

    @Override
    /** looks in  PositionHistorical
      */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer,IObservable> observables, Date dateValo, boolean withDetails) {
        Map<IPricerMeasure, MeasureValue[]> listMeasure = new HashMap();
        if (priced != null && priced.getProduct()!=null  && priced.getProduct().getId()!=null && priced.getInternalCounterparty()!=null && priced.getCounterparty()!=null) {

            List<PositionHistoricalMeasure> phms = PositionBuilder.getPositionHistoricalMeasureByPosition(priced.getId(), dateValo);
            if (phms != null) {
                for (PositionHistoricalMeasure phm : phms) {
                    IPricerMeasure measure=MeasuresAccessor.getMeasureByName(phm.getMeasureName());
                    MeasureValue meas = new MeasureValue(measure, phm.getMeasureValue(), phm.getCurrency(), dateValo, priced.getId());
                    listMeasure.put(measure, new MeasureValue[]{meas});
                }
            }
        }
        return listMeasure;
    }
}
