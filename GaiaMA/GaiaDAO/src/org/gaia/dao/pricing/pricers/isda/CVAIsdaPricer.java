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
package org.gaia.dao.pricing.pricers.isda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.ICVAPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class CVAIsdaPricer implements IPricer, ICVAPricer {

    /**
     * get new observable curve form Credit Valuation Adjustment CVA
     *
     * @param priced
     * @return List<IObservable>
     */
    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> ret = new ArrayList();
        IObservable obs = new CurveObservable(AbstractObservable.ObservableType.RECOVERY_CURVE, AbstractObservable.DEFAULT_FORCASTING);
        ret.add(obs);
        IObservable obs2 = new CurveObservable(AbstractObservable.ObservableType.CREDIT_CURVE, AbstractObservable.DEFAULT_FORCASTING);
        ret.add(obs2);

        return ret;
    }

    @Override
    public Map<MeasuresAccessor.Measure, MeasureValue[]> calculate(IPriceable priced, Map observables, Date dateValo, boolean withDetails) {
        Map<MeasuresAccessor.Measure, MeasureValue[]> listMeasure = new HashMap();
        double cva;
        MeasureValue unitCVA = new MeasureValue(MeasuresAccessor.Measure.CVA_unit, BigDecimal.ZERO, priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());

        ProductTypeUtil.ProductType pt = ProductTypeUtil.getProductTypeByName(priced.getProduct().getProductType());
        if (pt != null && (pt.use.equals(ProductTypeUtil.ProductTypeUse.OTC) || pt.use.equals(ProductTypeUtil.ProductTypeUse.CLEARED_OTC))) {
            CurveObservable recoverycurve = null;
            CurveObservable creditcurve = null;
            for (Object obj : observables.values()) {
                IObservable obs = (IObservable) obj;
                if (obs instanceof CurveObservable) {
                    CurveObservable curve = (CurveObservable) obs;
                    if (curve.getObservableType().equals(AbstractObservable.ObservableType.RECOVERY_CURVE)) {
                        recoverycurve = curve;
                    } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.CREDIT_CURVE)) {
                        creditcurve = curve;
                    }
                }
            }
            if (recoverycurve != null && creditcurve != null) {
                Object[] arr = new Object[1];
                arr[0] = priced.getProduct().getMaturityDate();
                double recoveryRate = 0.4;
                BigDecimal bdRecoveryRate = recoverycurve.getObservableValue(arr);
                if (bdRecoveryRate != null) {
                    recoveryRate = recoverycurve.getObservableValue(arr).doubleValue();
                }
                double survivalProba = 1;
                try {
                    survivalProba = IsdaPricerCaller.getSurvivalProbability(priced.getProduct().getMaturityDate(), creditcurve);
                } catch (Exception e) {
                    throw e;
                }
                cva = -1 * (1 - recoveryRate) * (1 - survivalProba);
                unitCVA.setMeasureValue(BigDecimal.valueOf(cva));
            }
        }
        listMeasure.put(MeasuresAccessor.Measure.CVA_unit_line, new MeasureValue[]{unitCVA});
        return listMeasure;
    }

}
