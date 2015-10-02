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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.pricers.ICompositePricer;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerComposite implements ICompositePricer, INPVPricer {

    private static final Logger logger = Logger.getLogger(PricerComposite.class);

    Map<Integer, IPricer> pricers;
    Map<Integer, List<IObservable>> observables;

    @Override
    public void init(IPriceable priced, PricingEnvironment pricingEnvironment) {
        if (priced.getProduct().getSubProducts() != null) {
            MeasureGroup[] measureGroups = new MeasureGroup[]{MeasureGroup.PV_GROUP};
            Map<MeasuresAccessor.MeasureGroup, String> map;
            pricers = new HashMap();
            for (Product subProduct : priced.getProduct().getSubProducts()) {
                map = PricingEnvironmentAccessor.getPricersMapByPriceable(subProduct, pricingEnvironment, measureGroups);
                if (!map.values().isEmpty()) {
                    String pricerName = map.values().iterator().next();
                    String pricerPath = MappingsAccessor.getMappingValueByNameAndKey("pricers", pricerName);
                    try {
                        IPricer pricer = (IPricer) Class.forName(pricerPath).newInstance();
                        if (pricer instanceof ICompositePricer) {
                            ICompositePricer comp = (ICompositePricer) pricer;
                            comp.init(priced, pricingEnvironment);
                        }

                        pricers.put(subProduct.getId(), pricer);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        logger.error("PRICER NOT FOUND : " + pricerPath);
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            }
        }
    }

    @Override
    public List<IObservable> getNeededObservables(IPriceable priced) {
        List<IObservable> globalObservable = new ArrayList();
        if (priced.getProduct().getSubProducts() != null && pricers != null) {
            observables = new HashMap();
            for (Product subProduct : priced.getProduct().getSubProducts()) {
                IPricer pricer = pricers.get(subProduct.getId());
                List<IObservable> pricerObservables = pricer.getNeededObservables(subProduct);
                observables.put(subProduct.getId(), pricerObservables);
                for (IObservable obs : pricerObservables) {
                    if (!globalObservable.contains(obs)) {
                        if (obs.getLookupProduct() == null) {
                            obs.setLookupProduct(subProduct);
                        }
                        globalObservable.add(obs);
                    }
                }
            }
        }
        return globalObservable;
    }

    /**
     *
     * @param priced
     * @param observables
     * @param dateValo     *
     * @return Map<IPricerMeasure, MeasureValue[]>
     */
    @Override
    /**
     * call pricers
     */
    public Map<IPricerMeasure, MeasureValue[]> calculate(IPriceable priced, Map<Integer, IObservable> observables, Date dateValo, boolean withDetails) {

        Map<IPricerMeasure, MeasureValue[]> pricerResultMap = new HashMap();
        if (priced.getProduct().getSubProducts() != null && pricers != null) {
            for (Product subProduct : priced.getProduct().getSubProducts()) {
                IPricer pricer = pricers.get(subProduct.getId());
                Map<IPricerMeasure, MeasureValue[]> subProductPricerResultMap = pricer.calculate(subProduct, observables, dateValo, withDetails);
                for (Entry<IPricerMeasure, MeasureValue[]> entry : subProductPricerResultMap.entrySet()) {
                    MeasureValue[] existingMeasures = pricerResultMap.get(entry.getKey());
                    if (existingMeasures != null) {
                        for (MeasureValue newMeasureValue : entry.getValue()) {
                            if (subProduct.getNotionalMultiplier() != null) {
                                newMeasureValue.setMeasureValue(newMeasureValue.getMeasureValue().multiply(subProduct.getNotionalMultiplier()));
                            }
                            boolean isExisting = false;
                            for (MeasureValue existingMeasureValue : existingMeasures) {
                                if (newMeasureValue.getName().equalsIgnoreCase(existingMeasureValue.getName())) {
                                    existingMeasureValue.setMeasureValue(existingMeasureValue.getMeasureValue().add(newMeasureValue.getMeasureValue()));
                                    isExisting = true;
                                }
                            }
                            if (!isExisting) {
                                MeasureValue[] newMeasureArray = Arrays.copyOf(existingMeasures, existingMeasures.length + 1);
                                newMeasureArray[newMeasureArray.length - 1] = newMeasureValue;
                            }
                        }
                    } else {
                        if (subProduct.getNotionalMultiplier() != null) {
                            for (MeasureValue newMeasureValue : entry.getValue()) {
                                newMeasureValue.setMeasureValue(newMeasureValue.getMeasureValue().multiply(subProduct.getNotionalMultiplier()));
                            }
                        }
                        pricerResultMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return pricerResultMap;
    }
}
