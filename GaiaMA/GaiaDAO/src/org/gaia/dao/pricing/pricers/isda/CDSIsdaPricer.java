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
import org.gaia.dao.pricing.pricers.ICDSPricer;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class CDSIsdaPricer implements ICDSPricer, INPVPricer {

    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {
        ArrayList<IObservable> ret = new ArrayList();
        IObservable obs = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
        ret.add(obs);
        IObservable obs2 = new CurveObservable(AbstractObservable.ObservableType.RECOVERY_CURVE, AbstractObservable.DEFAULT_FORCASTING);
        ret.add(obs2);
        IObservable obs3 = new CurveObservable(AbstractObservable.ObservableType.CREDIT_CURVE, AbstractObservable.DEFAULT_FORCASTING);
        ret.add(obs3);

        return ret;
    }

    @Override
    public Map<MeasuresAccessor.Measure, MeasureValue[]> calculate(IPriceable priced, Map observables, Date dateValo, boolean withDetails) {
        Map<MeasuresAccessor.Measure, MeasureValue[]> mapMeasures = new HashMap<>();
        dateValo = DateUtils.adjustDate(dateValo, DateUtils.ADJUSTMENT_PREVIOUS, null);
        double npv = 0;
        double npvFeeLeg = 0;
        double npvProtectionLeg = 0;

        MeasureValue npvMeasureValue = new MeasureValue(MeasuresAccessor.Measure.NPV_unit, BigDecimal.ZERO, priced.getProduct().getNotionalCurrency(), dateValo, priced.getId());

        CurveObservable ircurve = null;
        CurveObservable recoverycurve = null;
        CurveObservable creditcurve = null;
        for (Object obj : observables.values()) {
            IObservable obs = (IObservable) obj;
            if (obs instanceof CurveObservable) {
                CurveObservable curve = (CurveObservable) obs;
                if (curve.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                    ircurve = curve;
                } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.RECOVERY_CURVE)) {
                    recoverycurve = curve;
                } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.CREDIT_CURVE)) {
                    creditcurve = curve;
                }
            }
        }
        MeasureValue[] measurevaluesArray = null;
        if (ircurve != null && recoverycurve != null && creditcurve != null) {
            Object[] arr = new Object[1];
            arr[0] = priced.getProduct().getMaturityDate();

            double recoveryRate = 0.4;
            if (recoverycurve.getObservableValue(arr) != null) {
                recoveryRate = recoverycurve.getObservableValue(arr).doubleValue();
            }
            if (priced.getProduct().getProductCredit() != null && priced.getProduct().getProductCredit().getRecoveryFactor() != null) {
                recoveryRate = priced.getProduct().getProductCredit().getRecoveryFactor().doubleValue();
            }
            if (withDetails) {
                measurevaluesArray = new MeasureValue[3];
                try {
                    npvFeeLeg = IsdaPricerCaller.getCDSFeeLegNPV(priced, ircurve, creditcurve, dateValo);
                } catch (Exception e) {
                    throw e;
                }
                try {
                    npvProtectionLeg = IsdaPricerCaller.getCDSProtectionLegNPV(priced, ircurve, creditcurve, recoveryRate, dateValo);
                } catch (Exception e) {
                    throw e;
                }
                try {
                    npvFeeLeg = -1 * npvFeeLeg;
                    npv = npvProtectionLeg + npvFeeLeg;
                } catch (Exception e) {
                    throw e;
                }

                MeasureValue npvFeeLegMeasureValue = (MeasureValue) npvMeasureValue.clone();
                npvFeeLegMeasureValue.setMeasureValue(BigDecimal.valueOf(npvFeeLeg));
                npvFeeLegMeasureValue.setMeasure(MeasuresAccessor.Measure.NPV_FEE_unit);
                npvFeeLegMeasureValue.setName(MeasuresAccessor.Measure.NPV_FEE_unit.name());
                npvMeasureValue.setMeasureValue(BigDecimal.valueOf(npv));

                MeasureValue npvProtectionLegMeasureValue = (MeasureValue) npvMeasureValue.clone();
                npvProtectionLegMeasureValue.setMeasureValue(BigDecimal.valueOf(npvProtectionLeg));
                npvProtectionLegMeasureValue.setMeasure(MeasuresAccessor.Measure.NPV_CREDIT_unit);
                npvProtectionLegMeasureValue.setName(MeasuresAccessor.Measure.NPV_CREDIT_unit.name());

                measurevaluesArray[0] = npvMeasureValue;
                measurevaluesArray[1] = npvFeeLegMeasureValue;
                measurevaluesArray[2] = npvProtectionLegMeasureValue;
            } else {
                measurevaluesArray = new MeasureValue[1];
                try {
                    npv = IsdaPricerCaller.getCDSNPV(priced, ircurve, creditcurve, recoveryRate, dateValo);
                    npvMeasureValue.setMeasureValue(BigDecimal.valueOf(npv));
                } catch (Exception e) {
                    throw e;
                }
                measurevaluesArray[0] = npvMeasureValue;

            }
            mapMeasures.put(MeasuresAccessor.Measure.NPV_unit, measurevaluesArray);
        }
        return mapMeasures;
    }

    @Override
    public double getUpfront(IPriceable priced, Map observables) {
        double upfront = 0;

        if (priced instanceof Trade) {
            Trade trade = (Trade) priced;

            CurveObservable ircurve = null;
            CurveObservable recoverycurve = null;
            for (Object obj : observables.values()) {
                IObservable obs = (IObservable) obj;
                if (obs instanceof CurveObservable) {
                    CurveObservable curve = (CurveObservable) obs;
                    if (curve.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                        ircurve = curve;
                    } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.RECOVERY_CURVE)) {
                        recoverycurve = curve;
                    }
                }
            }
            if (ircurve != null && recoverycurve != null && priced.getProduct().getMaturityDate() != null) {
                Object[] arr = new Object[1];
                arr[0] = priced.getProduct().getMaturityDate();
                double recoveryRate = 0;
                if (recoverycurve.getQuotes().size() > 0) {
                    recoveryRate = recoverycurve.getObservableValue(arr).doubleValue();
                }
                if (priced.getProduct().getProductCredit() != null && priced.getProduct().getProductCredit().getRecoveryFactor() != null) {
                    recoveryRate = priced.getProduct().getProductCredit().getRecoveryFactor().doubleValue();
                }
                upfront = IsdaPricerCaller.getCDSUpfront(trade, ircurve, recoveryRate);
            }
        }
        return upfront;
    }

    @Override
    public double getSpread(IPriceable priced, Map observables) {
        double spread = 0;

        if (priced instanceof Trade) {
            Trade trade = (Trade) priced;
            CurveObservable ircurve = null;
            CurveObservable recoverycurve = null;
            for (Object obj : observables.values()) {
                IObservable obs = (IObservable) obj;
                if (obs instanceof CurveObservable) {
                    CurveObservable curve = (CurveObservable) obs;
                    if (curve.getObservableType().equals(AbstractObservable.ObservableType.IR_CURVE)) {
                        ircurve = curve;
                    } else if (curve.getObservableType().equals(AbstractObservable.ObservableType.RECOVERY_CURVE)) {
                        recoverycurve = curve;
                    }
                }
            }
            if (ircurve != null && recoverycurve != null) {
                Object[] arr = new Object[1];
                arr[0] = priced.getProduct().getMaturityDate();
                double recoveryRate = 0.4;
                if (recoverycurve.getQuotes().size() > 0) {
                    recoveryRate = recoverycurve.getObservableValue(arr).doubleValue();
                }
                if (priced.getProduct().getProductCredit() != null && priced.getProduct().getProductCredit().getRecoveryFactor() != null) {
                    recoveryRate = priced.getProduct().getProductCredit().getRecoveryFactor().doubleValue();
                }
                spread = IsdaPricerCaller.getCDSSpread(trade, ircurve, recoveryRate);
            }
        }
        return spread;
    }
}
