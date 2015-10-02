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
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.ICVAPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerZeroCVA implements IPricer, ICVAPricer {

    @Override
    /**
     * get Needed Observables
     */
     public List<IObservable> getNeededObservables(IPriceable priced) {
        return null;
    }

    /**
     * returns 0
     * @param priced
     * @param observables
     * @param dateValo
     * @param withDetails
     * @return
     */
    @Override
     public Map<MeasuresAccessor.Measure,MeasureValue[]> calculate(IPriceable priced, Map observables, Date dateValo, boolean withDetails) {
        Map<MeasuresAccessor.Measure,MeasureValue[]> listMeasure = new HashMap();

        MeasureValue unitCVA = new MeasureValue(MeasuresAccessor.Measure.CVA_unit, BigDecimal.ZERO, priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());
        listMeasure.put(MeasuresAccessor.Measure.CVA_unit_line, new MeasureValue[]{unitCVA});

        return listMeasure;
    }
}

