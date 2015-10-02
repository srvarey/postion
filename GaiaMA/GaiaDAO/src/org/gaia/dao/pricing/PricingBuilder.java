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
package org.gaia.dao.pricing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.PricingSetting;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.Measure;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.pricers.ICDSPricer;
import org.gaia.dao.pricing.pricers.ICompositePricer;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.reports.customColumns.UnitMeasuresMultiplicator;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.referentials.UserPricingPreference;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean

 Pricing builder handles main pricing processings observables / market data
 loading and pricers calls
 *
 */
public class PricingBuilder {

    private static final Logger logger = Logger.getLogger(PricingBuilder.class);
    public static final String LOAD_PRICER_MAP = "loadPricerMap";

    /**
     * Loads a map of nedded pricers
     *
     * @param priceable
     * @param valuationDate valuation date
     * @param measureGroups
     * @param pricingEnvironment
     * @return
     */
    public static Map<MeasuresAccessor.MeasureGroup, IPricer> loadPricerMap(IPriceable priceable, Date valuationDate, PricingEnvironment pricingEnvironment, MeasuresAccessor.MeasureGroup[] measureGroups) {
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = new HashMap();
        Map<MeasuresAccessor.MeasureGroup, String> map = PricingEnvironmentAccessor.getPricersMapByPriceable(priceable, pricingEnvironment, measureGroups);
        for (MeasuresAccessor.MeasureGroup measureGroup : map.keySet()) {
            String pricerName = map.get(measureGroup);
            String pricerPath = MappingsAccessor.getMappingValueByNameAndKey("pricers", pricerName);
            try {
                IPricer pricer = (IPricer) Class.forName(pricerPath).newInstance();
                if (pricer instanceof ICompositePricer) {
                    ICompositePricer comp = (ICompositePricer) pricer;
                    comp.init(priceable, pricingEnvironment);
                }
                pricersMap.put(measureGroup, pricer);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                logger.error("PRICER NOT FOUND : " + pricerPath);
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return pricersMap;
    }

    public static final String GET_OBSERBVABLES = "getObservables";

    /**
     * Loads a map of observables needed for
     *
     * @param pricersMap
     * @param priceable
     * @param pricingEnvironment
     * @return
     */
    public static Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> getObservables(
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap,
            IPriceable priceable,
            PricingEnvironment pricingEnvironment) {

        Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = new HashMap();

        Integer observableProductId;

        for (MeasuresAccessor.MeasureGroup measureGroup : pricersMap.keySet()) {
            IPricer pricer = pricersMap.get(measureGroup);

            try {
                Map<Integer, IObservable> observableMap = new HashMap();
                List<IObservable> observables = pricer.getNeededObservables(priceable);

                if (observables != null) {
                    for (IObservable observable : observables) {
                        observableProductId = observable.getProductId();
                        /*
                         *  use observable lookup product for research
                         *  for composite,index,ndf&fxfwd,vol...
                         */
                        Product originalProduct = null;
                        if (observable.getLookupProduct() != null) {
                            originalProduct = priceable.getProduct();
                            priceable.setProduct(observable.getLookupProduct());
                            if (priceable instanceof Product) { // hint because not possible to setproduct in product
                                priceable = observable.getLookupProduct();
                            }
                        }
                        if (observableProductId == null) {
                            observableProductId = PricingEnvironmentAccessor.lookForObservableIdLinkedWithPricingEnvironment(observable, priceable, pricingEnvironment);
                        }
                        if (observableProductId != null) {
                            observable.setProductId(observableProductId);
                            observable.setProduct(ProductAccessor.getProductById(observableProductId));
                            observableMap.put(observable.getProductId(), observable);
                        }
                        if (observable.getLookupProduct() != null && originalProduct != null) {
                            // put back the original product
                            priceable.setProduct(originalProduct);
                            if (priceable instanceof Product) {
                                priceable = originalProduct;
                            }
                        }
                    }
                    groupObservablesMap.put(measureGroup, observableMap);
                }

            } catch (Exception e) {
                logger.error("Error on " + priceable.getClass().getSimpleName() + StringUtils.SPACE + priceable.getId() + ":");
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return groupObservablesMap;
    }

    public static final String BUILD_PRICING_SETTING = "buildPricingSetting";

    public static PricingSetting buildPricingSetting(Collection<TemplateColumnItem> templateColumnItems, PricingEnvironment pricingEnvironment, Date valueDate, boolean isRealTime) {
        List<DAOGreekSetting> greeksList = new ArrayList();
        List measures2D = new ArrayList();
        List listIds2D = new ArrayList();
        List<MeasuresAccessor.MeasureGroup> listMeasureGroups = new ArrayList();
        List<NPVArrayDimension> npvArrayDimensions = new ArrayList();

        /**
         * list of underlying with delta or quote needs
         */
        for (TemplateColumnItem templateColumnItem : templateColumnItems) {

            if (templateColumnItem.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_MEASURE)) {

                try {
                    // on pricing measures
                    // we have to manage greeks

                    String fieldName = templateColumnItem.getName();
                    if (fieldName.indexOf(StringUtils.DOT) > 0) {
                        fieldName = fieldName.substring(0, fieldName.indexOf(StringUtils.DOT));
                    }

                    IPricerMeasure measure = MeasuresAccessor.getMeasureByName(fieldName);
                    if (measure instanceof StoredGreek){
                        GreekSetting greekSetting =((StoredGreek)measure).getMyGreekSetting();
                        if (greekSetting != null) {
                            DAOGreekSetting daoGreekSetting = new DAOGreekSetting(greekSetting, measure);
                            // if it is a greek measure, the dimension of shifted observable has to be added to the array
                            addGreek(daoGreekSetting, templateColumnItem, greeksList, npvArrayDimensions);
                        }
                    }

                    if (measure != null && measure.getDimension() == 2) {
                        measures2D.add(templateColumnItem.getName());
                        listIds2D.add(templateColumnItem.getParam());
                    }
                    // add the measure group if needed
                    if (measure != null && !listMeasureGroups.contains(measure.getGroup())) {
                        listMeasureGroups.add(measure.getGroup());
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
        }

        MeasuresAccessor.MeasureGroup[] measureGroups = (MeasuresAccessor.MeasureGroup[]) listMeasureGroups.toArray(new MeasuresAccessor.MeasureGroup[listMeasureGroups.size()]);
        /**
         * build object PricingSetting passed to pricer agent
         */
        PricingSetting pricingSettings = new PricingSetting(pricingEnvironment, valueDate, measures2D, listIds2D, isRealTime, greeksList, measureGroups, npvArrayDimensions);
        return pricingSettings;
    }

    private static void addGreek(DAOGreekSetting daoGreekSetting, TemplateColumnItem templateColumnItem, List<DAOGreekSetting> greeksList, List<NPVArrayDimension> npvArrayDimensions) {
        String[] names = null;
        String fieldName = daoGreekSetting.getPricerMeasure().getName();
        if (templateColumnItem.getName().indexOf(StringUtils.DOT) > 0) {
            names = templateColumnItem.getName().substring(fieldName.length() + 1).split(StringUtils.COMMA);
        }
        if (daoGreekSetting.getShifted().equalsIgnoreCase(AbstractObservable.ObservableType.TIME.name)) {
            if (names == null) {
                names = new String[1];
            } else {
                String[] tmp = new String[names.length + 1];
                System.arraycopy(names, 0, tmp, 1, names.length);
                names = tmp;
            }
        }
        Integer[] ids = null;
        if (!templateColumnItem.getParam().isEmpty()) {
            String[] sIds = templateColumnItem.getParam().split(StringUtils.COMMA);
            ids = new Integer[sIds.length];
            for (int i = 0; i < sIds.length; i++) {
                ids[i] = new Integer(sIds[i]);
            }
        }
        if (daoGreekSetting.getShifted().equalsIgnoreCase(AbstractObservable.ObservableType.TIME.name)) {
            if (ids == null) {
                ids = new Integer[1];
            } else {
                Integer[] tmp = new Integer[ids.length + 1];
                System.arraycopy(ids, 0, tmp, 1, ids.length);
                ids = tmp;
            }
        }
        if (daoGreekSetting.getDaoMovingGreekSetting() != null) {
            if (names.length > 1) { // cross
                String[] newnames = Arrays.copyOfRange(names, 1, names.length);
                daoGreekSetting.getDaoMovingGreekSetting().setUnderlyingNames(newnames);
                Integer[] newids = Arrays.copyOfRange(ids, 1, ids.length);
                daoGreekSetting.getDaoMovingGreekSetting().setUnderlyingIds(newids);
            } else { // gamma style
                daoGreekSetting.getDaoMovingGreekSetting().setUnderlyingNames(names);
                daoGreekSetting.getDaoMovingGreekSetting().setUnderlyingIds(ids);
            }// add moving greek if not selected
            Integer foundIndex = greeksList.indexOf(daoGreekSetting.getDaoMovingGreekSetting());
            if (foundIndex == -1) {
                greeksList.add(daoGreekSetting.getDaoMovingGreekSetting());
                Integer[] subids = new Integer[1];
                if (ids.length > 1) {
                    System.arraycopy(ids, 1, subids, 0, ids.length - 1);
                } else {
                    subids = ids;
                }
                fillArrayDimensionWithGreekSetting(daoGreekSetting.getDaoMovingGreekSetting(), npvArrayDimensions, names, subids);
            } else {
                daoGreekSetting.setDaoMovingGreekSetting(greeksList.get(foundIndex));
            }
        }
        daoGreekSetting.setUnderlyingNames(names);
        daoGreekSetting.setUnderlyingIds(ids);
        if (!greeksList.contains(daoGreekSetting)) {
            greeksList.add(daoGreekSetting);
        }
        fillArrayDimensionWithGreekSetting(daoGreekSetting, npvArrayDimensions, names, ids);
    }

    private static void fillArrayDimensionWithGreekSetting(DAOGreekSetting greekSetting, List<NPVArrayDimension> npvArrayDimensions, String[] names, Integer[] ids) {

        // case of a simple shift measure
        NPVArrayDimension foundDimension = findGreekDimension(greekSetting, npvArrayDimensions);
        if (foundDimension != null) {
            if (greekSetting.getMovingGreekSetting() != null && greekSetting.getShifted().equalsIgnoreCase(greekSetting.getMovingGreekSetting().getShifted())) {
                foundDimension.setMin(-1);
            }
            greekSetting.setNpvArrayDimension(foundDimension);
        } else {
            NPVArrayDimension newDimension = new NPVArrayDimension();
            if (ids != null && ids.length > 0) {
                newDimension.setObservableId(ids[0]);
            }
            AbstractObservable.ObservableType type = AbstractObservable.ObservableType.getObservableByName(greekSetting.getShifted());
            newDimension.setObservableType(type);
            newDimension.setStress(greekSetting.getStress());
            if (names != null && names.length > 0) {
                newDimension.setObservableName(names[0]);
            }
            newDimension.setIsAbsolute(greekSetting.isAbsolute());
            greekSetting.setNpvArrayDimension(newDimension);
            npvArrayDimensions.add(newDimension);
        }
    }

    private static NPVArrayDimension findGreekDimension(DAOGreekSetting greekSetting, List<NPVArrayDimension> npvArrayDimensions) {

        for (NPVArrayDimension dimension : npvArrayDimensions) {
            if (dimension.getObservableType().name.equals(greekSetting.getShifted())) {
                if (dimension.getObservableId() == null && greekSetting.getUnderlyingIds() == null) {
                    return dimension;
                } else if (dimension.getObservableId() != null && greekSetting.getUnderlyingIds() != null && greekSetting.getUnderlyingIds().length > 0
                        && dimension.getObservableId().intValue() == greekSetting.getUnderlyingIds()[0].intValue()
                        && dimension.getStress().getStressId().intValue() == greekSetting.getStress().getStressId().intValue()) {
                    return dimension;
                } else if (dimension.getObservableId() == null && greekSetting.getUnderlyingIds() != null && greekSetting.getUnderlyingIds().length > 0
                        && greekSetting.getUnderlyingIds()[0] == null) {
                    return dimension;
                }
            }
        }
        return null;
    }

    /**
     * Call the pricers for priceable object
     *
     * @param priceable priceable object
     * @param groupObservablesMap
     * @param valuationDate valuation date
     * @param pricersMap map of pricers
     * @param measures2DUnderlyingIds underlyings on which greeks have to be
     * calculated
     * @param pricingSettings
     * @return
     */
    public static Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> pricePriceable(
            IPriceable priceable,
            Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap,
            Date valuationDate,
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap,
            Map<String, Map<Integer, String>> measures2DUnderlyingIds,
            PricingSetting pricingSettings) {
        boolean fillAllQuotes = false;
        Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> dateMeasures = new HashMap();
        Map<Integer, IObservable> observables;

        if (priceable == null || pricingSettings == null) {
            return dateMeasures;
        }

        /**
         * PRICING
         */
        try {

            Map<IPricerMeasure, IMeasureValue[]> measureValues = new HashMap();
            if (measures2DUnderlyingIds == null) {
                measures2DUnderlyingIds = new HashMap();
                /**
                 * for pricing in trade windows
                 */
                fillAllQuotes = true;
            }

            for (Entry<MeasureGroup, IPricer> entry : pricersMap.entrySet()) {

                IPricer pricer = entry.getValue();
                MeasureGroup measureGroup = entry.getKey();
                Map<IPricerMeasure, MeasureValue[]> pricerResultMap;

                // filter when object not alive on cva and pv groups
                if ((measureGroup.equals(MeasureGroup.CVA_GROUP) || measureGroup.equals(MeasureGroup.PV_GROUP))
                        && ((priceable.getProduct().getMaturityDate() != null && !priceable.getProduct().getMaturityDate().after(valuationDate))
                        || priceable.getQuantity(valuationDate) == null || priceable.getQuantity(valuationDate).doubleValue() == 0
                        || (priceable instanceof Trade && ((Trade) priceable).getTradeDate().after(valuationDate)))) {
                    IPricerMeasure measure = null;
                    if (measureGroup.equals(MeasureGroup.CVA_GROUP)) {
                        measure = Measure.CVA_unit;
                    } else if (measureGroup.equals(MeasureGroup.PV_GROUP)) {
                        measure = Measure.NPV_unit;
                    }
                    MeasureValue zeroValue = new MeasureValue(measure, BigDecimal.ZERO, priceable.getProduct().getNotionalCurrency(), valuationDate, priceable.getId());
                    measureValues.put(measure, new MeasureValue[]{zeroValue});

                } else {

                    /**
                     * PRICER CALL HERE :
                     */
                    observables = groupObservablesMap.get(measureGroup);
                    pricerResultMap = pricer.calculate(priceable, observables, valuationDate, true);
                    /*==================================================================================*/
                    /**
                     * that's all for pricing single dimension pricings now:
                     * GREEKS & QUOTES
                     */
                    if (pricerResultMap != null) {
                        measureValues.putAll(pricerResultMap);

                        MeasureValue[] npvValues = pricerResultMap.get(Measure.NPV_unit);
                        if (measureGroup.equals(MeasureGroup.PV_GROUP) && npvValues != null) {
                            MeasureValue mvNPV = npvValues[0];
                            Double npv = null;
                            if (mvNPV.getMeasureValue() != null) {
                                npv = mvNPV.getMeasureValue().doubleValue();
                            }

                            /**
                             * GREEKS
                             */
                            if (!pricingSettings.getNpvArrayDimensions().isEmpty() && npv != null && observables != null) {

                                // select the greeks and dimensions concerning our observable
                                List<DAOGreekSetting> greeksList = pricingSettings.getGreeksList();
                                List<NPVArrayDimension> npvArrayDimensions = new ArrayList();
                                cleanGreekList(pricingSettings, observables, npvArrayDimensions, greeksList);

                                if (!npvArrayDimensions.isEmpty()) {
                                    // now build the sub array with them
                                    Serializable npvArray = buildNPVArray(greeksList, npvArrayDimensions, new ArrayList());
                                    // clean array to avoid useless calulations
                                    npvArray = cleanNPVArray(npvArray, npvArrayDimensions, greeksList);
                                    // fill the array with needed npv for greek calculations
                                    fillNPVArray(observables, npvArray, npvArrayDimensions, pricer, priceable, valuationDate, npvArrayDimensions.size(), false);

                                    Map<DAOGreekSetting, MeasureValue> greekValuesList = new HashMap();
                                    for (DAOGreekSetting greekSetting : greeksList) {
                                        try {
                                            int[] coordinates = getGreekSettingCoordinates(greekSetting, npvArrayDimensions);

                                            Double greek = calculateGreek(npv, npvArray, greekSetting, coordinates, npvArrayDimensions, greekValuesList, npv);

                                            if (greek != null && greek != Double.POSITIVE_INFINITY && greek != Double.NaN) {
                                                MeasureValue mvGreek = (MeasureValue) mvNPV.clone();
                                                mvGreek.setMeasure(MeasuresAccessor.getUnitMeasure(greekSetting.getPricerMeasure().getName()));
                                                String name = greekSetting.getPricerMeasure().getName() + MeasuresAccessor.UNIT_POSTFIX;;
                                                if (greekSetting.getUnderlyingNames() != null) {
                                                    for (String underlyingName : greekSetting.getUnderlyingNames()) {
                                                        if (underlyingName != null) {
                                                            name += StringUtils.DOT + underlyingName;
                                                            mvGreek.setQualifier(underlyingName);
                                                        }
                                                    }
                                                }
                                                mvGreek.setName(name);
                                                mvGreek.setMeasureValue(new BigDecimal(greek));
                                                greekValuesList.put(greekSetting, mvGreek);

                                                IMeasureValue[] existing = measureValues.get(mvGreek.getMeasure());
                                                if (existing == null) {
                                                    measureValues.put(mvGreek.getMeasure(), new IMeasureValue[]{mvGreek});
                                                } else {
                                                    IMeasureValue[] newValues = Arrays.copyOf(existing, existing.length + 1);
                                                    newValues[existing.length] = mvGreek;
                                                    measureValues.put(mvGreek.getMeasure(), newValues);
                                                }
                                            }
                                        } catch (Exception e) {
                                            logger.error("Error on " + priceable.getClass().getSimpleName() + " id " + priceable.getId() + " product " + priceable.getProduct().getProductId()+StringUtils.SPACE+ priceable.getProduct().getShortName()+" on "+greekSetting.getName()+" calculation");
                                            logger.error(StringUtils.formatErrorMessage(e));
                                        }

                                    }
                                }
                            }

                            /**
                             * MARKET QUOTES
                             */
                            try {
                                List<MeasureValue> quoteValuesList = new ArrayList();
                                if (groupObservablesMap.get(measureGroup) != null) {
                                    for (Integer index : groupObservablesMap.get(measureGroup).keySet()) {
//                                        String obsType=groupObservablesMap.get(measureGroup).get(index).getObservableType().name;
                                        Object oobsType=measures2DUnderlyingIds.get(Measure.QUOTE.name());
                                        String obsType=null;
                                        if (oobsType!=null){
                                            oobsType=((HashMap)oobsType).keySet().iterator().next();
                                            obsType=oobsType.toString();
                                        }
                                        if ((measures2DUnderlyingIds.get(Measure.QUOTE.name()) != null
                                             && (((HashMap) measures2DUnderlyingIds.get(Measure.QUOTE.name())).containsKey(index.toString())
                                                   || groupObservablesMap.get(measureGroup).get(index).getObservableType().name.equalsIgnoreCase(obsType) ))
                                                   || fillAllQuotes) {

                                            IObservable observable = groupObservablesMap.get(measureGroup).get(index);
                                            BigDecimal quote = observable.getObservableValueFromPriceable(priceable);
                                            if (quote != null) {
                                                /**
                                                 * put it in result
                                                 */
                                                if (observable.getProduct() != null) {
                                                    if (observable.getProduct().getQuotationType() != null) {
                                                        MarketQuote.QuotationType quoteType = MarketQuote.QuotationType.getQuotationTypeByName(observable.getProduct().getQuotationType());
                                                        if (quoteType.getMult().doubleValue()!=1) {
                                                            quote=quote.multiply(quoteType.getMult());
                                                        }
                                                    }
                                                    MeasureValue mvquote = new MeasureValue(Measure.QUOTE, mvNPV.getValDate());
                                                    mvquote.setCurrency(mvNPV.getCurrency());
                                                    mvquote.setPriceableId(mvNPV.getPriceableId());
                                                    if (groupObservablesMap.get(measureGroup).get(index).getObservableType().name.equalsIgnoreCase(obsType)) {// case observable type
                                                        mvquote.setName(Measure.QUOTE.name() + StringUtils.DOT +obsType);
                                                    } else {// case specific product
                                                        mvquote.setName(Measure.QUOTE.name() + StringUtils.DOT + observable.getProduct().getShortName());
                                                    }
                                                    mvquote.setMeasureValue(quote);
                                                    mvquote.setQualifier(observable.getProduct().getShortName());
                                                    quoteValuesList.add(mvquote);
                                                }
                                            }
                                        }
                                    }
                                }
                                MeasureValue[] quoteValues = quoteValuesList.toArray(new MeasureValue[quoteValuesList.size()]);
                                measureValues.put(Measure.QUOTE, quoteValues);
                            } catch (Exception e) {
                                logger.error(StringUtils.formatErrorMessage(e));
                            }
                        }
                    }
                }
            }

            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> priceableMeasures = new HashMap();
            priceableMeasures.put(priceable.getId(), measureValues);
            dateMeasures.put(valuationDate, priceableMeasures);
        } catch (Exception e) {
            logger.error("Error on pricing of id " + priceable.getId() + " product id " + priceable.getProduct().getProductId());
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return dateMeasures;
    }

    public static void cleanGreekList(PricingSetting pricingSettings, Map<Integer, IObservable> observables, List<NPVArrayDimension> npvArrayDimensions, List<DAOGreekSetting> greeksList) {
        for (NPVArrayDimension arrayDim : pricingSettings.getNpvArrayDimensions()) {
            if (observables.containsKey(arrayDim.getObservableId())
                    || arrayDim.getObservableType().equals(AbstractObservable.ObservableType.TIME)) {
                npvArrayDimensions.add(arrayDim);
            }
        }
        List<DAOGreekSetting> toRemove = new ArrayList();
        for (DAOGreekSetting greek : greeksList) {
            if (!greek.getNpvArrayDimension().getObservableType().equals(AbstractObservable.ObservableType.TIME)
                    && !observables.containsKey(greek.getNpvArrayDimension().getObservableId())) {
                toRemove.add(greek);
            }
        }
        greeksList.removeAll(toRemove);
    }

    public static Serializable buildNPVArray(List<DAOGreekSetting> greeksList, List<NPVArrayDimension> npvArrayDimensions, List<ObservableShift> upperShifts) {
        Serializable[] array = null;
        try {
            if (!npvArrayDimensions.isEmpty()) {
                NPVArrayDimension lastDimension = npvArrayDimensions.get(npvArrayDimensions.size() - 1);
//                logger.info("dims "+npvArrayDimensions.size()+" last "+lastDimension.toString());
                int length = lastDimension.getMax() - lastDimension.getMin() + 1;
                List<ObservableShift> shifts = lastDimension.getStress().getObservableShifts();//greeksList.get(greeksList.size() - 1).getStress();

                if (npvArrayDimensions.size() > 1) {
                    List<NPVArrayDimension> subDimensionsList = npvArrayDimensions.subList(0, npvArrayDimensions.size() - 1);
                    List<DAOGreekSetting> subGreekList = new ArrayList(greeksList);
                    for (DAOGreekSetting greekSetting : greeksList) {
                        if (greekSetting.getNpvArrayDimension().equals(lastDimension)) {
                            subGreekList.remove(greekSetting);
                        }
                    }
                    array = new Serializable[length];
                    for (int i = 0; i < length; i++) {
                        if (i == lastDimension.getMiddle()) {
                            array[i] = buildNPVArray(subGreekList, subDimensionsList, upperShifts);
                        } else if (i < lastDimension.getMiddle()) {
                            // case when shift has to be on the other size
                            List<ObservableShift> negatedShifts = new ArrayList();
                            for (ObservableShift shift : shifts) {
                                ObservableShift observableShift = shift.clone();
                                observableShift.setShift(observableShift.getShift().negate());
                                negatedShifts.add(observableShift);
                            }
                            array[i] = buildNPVArray(subGreekList, subDimensionsList, negatedShifts);
                        } else {
                            array[i] = buildNPVArray(subGreekList, subDimensionsList, shifts);
                        }
                    }
                } else {
                    // calculate the shift
                    array = new Serializable[length];
                    for (int i = 0; i < length; i++) {
                        if (i == lastDimension.getMiddle()) {
                            array[i] = (Serializable) cloneShifts(upperShifts);
                        } else {
                            List<ObservableShift> arrayShifts = cloneShifts(shifts);
                            for (ObservableShift shift : arrayShifts) {
                                shift.setShift(shift.getShift().multiply(new BigDecimal(i + lastDimension.getMin())));
                            }
                            array[i] = (Serializable) arrayShifts;
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return array;
    }

    public static List<ObservableShift> cloneShifts(List<ObservableShift> sourceShifts) {
        List<ObservableShift> targetShifts = new ArrayList(sourceShifts.size());
        for (ObservableShift shift : sourceShifts) {
            targetShifts.add(shift.clone());
        }
        return targetShifts;
    }

    public static void fillNPVArray(Map<Integer, IObservable> observablesMap, Serializable npvArray, List<NPVArrayDimension> npvArrayDimensions, IPricer pricer, IPriceable priceable, Date valuationDate, int level, boolean isShifted) {

        try {
            Map<Integer, IObservable> shiftedPricerObservables = observablesMap;
            Date calculationDate = valuationDate;
            NPVArrayDimension npvArrayDimension = npvArrayDimensions.get(level - 1);
            Serializable[] array = (Serializable[]) npvArray;
            for (int i = 0; i < array.length; i++) {
                if (level == 1) {
                    fillFirstLevel(array, i, npvArrayDimension, shiftedPricerObservables, observablesMap, calculationDate, valuationDate, isShifted, pricer, priceable);
                } else {
                    fillSecondaryLevel(array, i, npvArrayDimensions, npvArrayDimension, observablesMap, calculationDate, valuationDate, pricer, priceable, level, isShifted);
                }

            }

        } catch (Exception e) {
            logger.error("Error on " + priceable.getClass().getSimpleName() + " id " + priceable.getId() + " product " + priceable.getProduct().getShortName());
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static void fillSecondaryLevel(Serializable[] array, int i, List<NPVArrayDimension> npvArrayDimensions, NPVArrayDimension npvArrayDimension,
            Map<Integer, IObservable> observablesMap, Date calculationDate, Date valuationDate, IPricer pricer, IPriceable priceable, int level, boolean isShifted) {
        Map<Integer, IObservable> shiftedPricerObservables;

        List<ObservableShift> shifts = getShifts(array[i], npvArrayDimensions.subList(0, npvArrayDimensions.indexOf(npvArrayDimension)));
        if (shifts != null) {
            if (i != npvArrayDimension.getMiddle()) {
                shiftedPricerObservables = shiftObservables(shifts, npvArrayDimension.isAbsolute(), observablesMap, npvArrayDimension);
                if (npvArrayDimension.getObservableType().name.equalsIgnoreCase(AbstractObservable.OBSERVABLE_TIME)) {
                    if (npvArrayDimension.isBusDaysTimeShift()) {
                        calculationDate = DateUtils.addOpenDay(valuationDate, shifts.get(0).getShift().intValue());
                    } else {
                        calculationDate = DateUtils.addCalendarDay(valuationDate, shifts.get(0).getShift().intValue());
                    }
                }
                isShifted = true;
                fillNPVArray(shiftedPricerObservables, array[i], npvArrayDimensions, pricer, priceable, calculationDate, level - 1, isShifted);
            } else {
                fillNPVArray(observablesMap, array[i], npvArrayDimensions, pricer, priceable, calculationDate, level - 1, isShifted);
            }

        }
    }

    public static void fillFirstLevel(Serializable[] array, int i, NPVArrayDimension npvArrayDimension, Map<Integer, IObservable> shiftedPricerObservables, Map<Integer, IObservable> observablesMap, Date calculationDate, Date valuationDate, boolean isShifted, IPricer pricer, IPriceable priceable) {
        if (array[i] != null) {
            if (i != npvArrayDimension.getMiddle()) {
                shiftedPricerObservables = shiftObservables(((List<ObservableShift>) array[i]), npvArrayDimension.isAbsolute(), observablesMap, npvArrayDimension);
                if (npvArrayDimension.getObservableType().name.equalsIgnoreCase(AbstractObservable.OBSERVABLE_TIME)) {
                    if (npvArrayDimension.isBusDaysTimeShift()) {
                        calculationDate = DateUtils.addOpenDay(valuationDate, ((List<ObservableShift>) array[i]).get(0).getShift().intValue());
                    } else {
                        calculationDate = DateUtils.addCalendarDay(valuationDate, ((List<ObservableShift>) array[i]).get(0).getShift().intValue());
                    }
                }
                isShifted = true;
            }
            // CALL OF THE PRICER
            if (isShifted && ((List) array[i]).size() > 0) {
//                            logger.error("CALC "+((List<ObservableShift>)array[i]).get(0).getStress().shiftsDesc());
                Map<IPricerMeasure, MeasureValue[]> resmShifted = ((INPVPricer) pricer).calculate(priceable, shiftedPricerObservables, calculationDate, false);
                MeasureValue[] measureValue = resmShifted.get(Measure.NPV_unit);

                if (measureValue != null && measureValue.length > 0) {
                    MeasureValue shiftedMeasureValue = resmShifted.get(Measure.NPV_unit)[0];
                    ((List<ObservableShift>) array[i]).get(0).setShiftResult(shiftedMeasureValue.getMeasureValue());
                }
            }
        }
    }

    public static Serializable[] buildEmptyArray(List<NPVArrayDimension> npvArrayDimensions) {
        Serializable[] array = null;
        if (!npvArrayDimensions.isEmpty()) {
            NPVArrayDimension lastNPVArrayDimension = npvArrayDimensions.get(npvArrayDimensions.size() - 1);
            int length = lastNPVArrayDimension.getMax() - lastNPVArrayDimension.getMin() + 1;
            if (npvArrayDimensions.size() == 1) {
                array = new Serializable[length];
            } else {
                array = new Serializable[length];
                List<NPVArrayDimension> subList = npvArrayDimensions.subList(0, npvArrayDimensions.size() - 1);
                for (int i = 0; i < length; i++) {
                    array[i] = buildEmptyArray(subList);
                }
            }
        }

        return array;
    }

    public static Serializable cleanNPVArray(Serializable npvArray, List<NPVArrayDimension> npvArrayDimensions, List<DAOGreekSetting> greeksList) {
        Serializable[] newArray = buildEmptyArray(npvArrayDimensions);
        for (DAOGreekSetting greekSetting : greeksList) {
            int[] coordinates = getGreekSettingCoordinates(greekSetting, npvArrayDimensions);
            if (!greekSetting.getMovingPricerMeasure().equals(MeasuresAccessor.Measure.NPV)
                    && !greekSetting.getShifted().equalsIgnoreCase(greekSetting.getMovingGreekSetting().getShifted())) {
                int[] subcoordinates = getGreekSettingCoordinates(greekSetting.getDaoMovingGreekSetting(), npvArrayDimensions);
                for (int i = 0; i < coordinates.length; i++) {
                    coordinates[i] += subcoordinates[i];
                }
            }
            copyArrayValue(coordinates, npvArray, newArray, npvArrayDimensions.size() - 1, npvArrayDimensions);
        }
        return newArray;
    }

    public static void copyArrayValue(int[] coordinates, Serializable fromArray, Serializable toArray, int level, List<NPVArrayDimension> npvArrayDimensions) {

        Serializable[] fromSerializableArray = (Serializable[]) fromArray;
        Serializable[] toSerializableArray = (Serializable[]) toArray;
        if (level == 0) {
            toSerializableArray[npvArrayDimensions.get(level).getMiddle() + coordinates[0]] = (Serializable) fromSerializableArray[npvArrayDimensions.get(level).getMiddle() + coordinates[0]];
            toSerializableArray[npvArrayDimensions.get(level).getMiddle()] = (Serializable) fromSerializableArray[npvArrayDimensions.get(level).getMiddle()];
        } else {
            copyArrayValue(coordinates, fromSerializableArray[npvArrayDimensions.get(level).getMiddle() + coordinates[level]], toSerializableArray[npvArrayDimensions.get(level).getMiddle() + coordinates[level]], level - 1, npvArrayDimensions);
        }
    }

    public static List<ObservableShift> getShifts(Serializable npvArray, List<NPVArrayDimension> npvArrayDimensions) {
        NPVArrayDimension npvArrayDimension = npvArrayDimensions.get(npvArrayDimensions.size() - 1);
        if (npvArrayDimensions.size() == 1) {
            Serializable[] shiftsArray = (Serializable[]) npvArray;
            return (List<ObservableShift>) shiftsArray[npvArrayDimension.getMiddle()];
        } else {
            Serializable[] array = (Serializable[]) npvArray;
            List<NPVArrayDimension> subDimensionsArray = npvArrayDimensions.subList(0, npvArrayDimensions.size() - 1);
            return getShifts(array[npvArrayDimension.getMiddle()], subDimensionsArray);
        }
    }

    public static Map<Integer, IObservable> shiftObservables(List<ObservableShift> shifts, boolean isAbsolute, Map<Integer, IObservable> observablesMap, NPVArrayDimension npvArrayDimension) {
        Map<Integer, IObservable> shiftedPricerObservables = new HashMap();
        if (observablesMap != null) {
            shiftedPricerObservables.putAll(observablesMap);
            if (!shifts.isEmpty()) {
                for (Map.Entry<Integer, IObservable> map : shiftedPricerObservables.entrySet()) {
                    if (map.getKey().equals(npvArrayDimension.getObservableId())) {
                        // here we shift
                        shiftedPricerObservables.put(map.getKey(), map.getValue().shift(shifts, isAbsolute));
                    }
                }
            }
        }
        return shiftedPricerObservables;
    }

    public static Double calculateGreek(Double referenceValue, Serializable npvArray, DAOGreekSetting greekSetting, int[] coordinates, List<NPVArrayDimension> npvArrayDimensions, Map<DAOGreekSetting, MeasureValue> greekValuesList, Double npv) {
        BigDecimal bdShifted = getPointFromNPVArray(npvArray, coordinates, npvArrayDimensions, npvArrayDimensions.size() - 1);
        Double shifted;
        if (bdShifted != null) {
            shifted = bdShifted.doubleValue();
        } else {
            shifted = npv;
        }
        if (!greekSetting.getMovingPricerMeasure().equals(MeasuresAccessor.Measure.NPV)) {
            int[] subcoordinates = getGreekSettingCoordinates(greekSetting.getDaoMovingGreekSetting(), npvArrayDimensions);
            for (int i = 0; i < coordinates.length; i++) {
                subcoordinates[i] += coordinates[i];
            }
            MeasureValue mv = greekValuesList.get(greekSetting.getDaoMovingGreekSetting());
            if (mv != null && mv.getMeasureValue() != null) {
                referenceValue = mv.getMeasureValue().doubleValue();
                shifted = calculateGreek(shifted.doubleValue(), npvArray, greekSetting.getDaoMovingGreekSetting(), subcoordinates, npvArrayDimensions, greekValuesList, npv);
            } else {
                return null;
            }
        }
        if (greekSetting.isInAmount()) {
            return (shifted.doubleValue() - referenceValue);
        } else {
            double averageShift = 0;
            for (ObservableShift shift : greekSetting.getStress().getObservableShifts()) {
                averageShift += shift.getShift().doubleValue();
            }
            averageShift = averageShift / greekSetting.getStress().getObservableShifts().size();
            if (greekSetting.isAbsolute()) {
                return (shifted.doubleValue() - referenceValue) / averageShift;
            } else {
                return (shifted.doubleValue() - referenceValue) / referenceValue;
            }
        }
    }

    public static BigDecimal getPointFromNPVArray(Serializable npvArray, int[] coordinates, List<NPVArrayDimension> npvArrayDimensions, int level) {

        if (level == 0) {
            Serializable[] shiftsArray = (Serializable[]) npvArray;
            List<ObservableShift> shifts = ((List<ObservableShift>) shiftsArray[npvArrayDimensions.get(level).getMiddle() + coordinates[level]]);
            if (shifts!=null && shifts.size() > 0) {
                return shifts.get(0).getShiftResult();
            } else {
                return null;
            }
        } else {
            Serializable[] subArray = (Serializable[]) npvArray;
            return getPointFromNPVArray(subArray[npvArrayDimensions.get(level).getMiddle() + coordinates[level]], coordinates, npvArrayDimensions, level - 1);
        }
    }

    public static int[] getGreekSettingCoordinates(DAOGreekSetting greekSetting, List<NPVArrayDimension> npvArrayDimensions) {
        int[] coordinates = new int[npvArrayDimensions.size()];
        int i = 0;
        for (NPVArrayDimension npvArrayDimension : npvArrayDimensions) {
            if (greekSetting.getNpvArrayDimension().equals(npvArrayDimension)) {
                coordinates[i] = 1;
                if (!greekSetting.getMovingPricerMeasure().equals(Measure.NPV)) {
                    GreekSetting moving = greekSetting.getMovingGreekSetting();
                    if (greekSetting.getShifted().equalsIgnoreCase(moving.getShifted())) {
                        // if both have the same shifted observable the coordinate must be changed
                        coordinates[i] = -1;
                    }
                }
            } else {
                coordinates[i] = 0;
            }
            i++;
        }
        return coordinates;
    }

    public static String GENERATE_DEFAULT_TEMPLATE_COLUMN_ITEMS = "generateDefaultTemplateColumnItemsCollection";

    public static ArrayList<TemplateColumnItem> generateDefaultTemplateColumnItemsCollection(
            Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap
    ) {

        ArrayList<TemplateColumnItem> templateColumnItems = new ArrayList();
        List<GreekSetting> greekSettings = MeasuresAccessor.getGreekSettings();
        for (GreekSetting greekSetting : greekSettings) {
            for (Map<Integer, IObservable> observablesMap : groupObservablesMap.values()) {
                for (Entry<Integer, IObservable> entry : observablesMap.entrySet()) {
                    if (entry.getValue().getObservableType().name.equals(greekSetting.getShifted())
                            && entry.getValue().getProduct() != null) {
                        TemplateColumnItem templateColumnItem = new TemplateColumnItem();
                        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_MEASURE);
                        templateColumnItem.setName(greekSetting.getPricerMeasure() + StringUtils.DOT + entry.getValue().getProduct().getShortName());
                        templateColumnItem.setParam(entry.getKey().toString());
                        templateColumnItems.add(templateColumnItem);
                    }
                }
            }
        }

        return templateColumnItems;
    }

    public static String FILTER_DEFAULT_TEMPLATE_COLUMN_ITEMS_WITH_PREFERENCES = "filterDefaultTemplateColumnItemsWithPreferences";

    public static void filterDefaultTemplateColumnItemsWithPreferences(
            ArrayList<TemplateColumnItem> items,
            List<UserPricingPreference> preferences) {
        ArrayList<TemplateColumnItem> toRemove = new ArrayList();
        for (TemplateColumnItem item : items) {
            boolean isThere = false;
            if (preferences != null) {
                for (UserPricingPreference preference : preferences) {
                    String name = item.getName();
                    if (name.contains(StringUtils.DOT)) {
                        name = name.substring(0, name.indexOf(StringUtils.DOT));
                    }
                    if (name.equalsIgnoreCase(preference.getPricingMeasure())) {
                        isThere = true;
                    }
                }
            }
            if (!isThere) {
                toRemove.add(item);
            }
        }
        items.removeAll(toRemove);

    }


    public static final String GET_TRADE_PRICING = "getTradePricing";

    /**
     * Prices a trade individually Only called by the GUI Not optimized for
     * server
     *
     * @param trade
     * @param valDate value date
     * @param pricingEnvironmentName pricing environment
     * @param overridedPVPricerName
     * @param preferences
     * @return
     */
    public static Map<String, BigDecimal> getTradePricing(Trade trade, Date valDate, String pricingEnvironmentName,
            String overridedPVPricerName, ArrayList<UserPricingPreference> preferences) {
        Map<String, BigDecimal> output = new HashMap();
        if (trade == null) {
            return null;
        }
        try {
            /**
             * load pricing map
             */
            PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnvironmentName);

            MeasureGroup[] measureGroups=new MeasureGroup[]{MeasureGroup.PV_GROUP};
            Map<MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(trade, valDate, pricingEnvironment, measureGroups);
            /*
             * If there is an overrided pricer, get it
             */
            String pricerNpv = StringUtils.EMPTY_STRING;
            IPricer pvPricer = pricersMap.get(MeasureGroup.PV_GROUP);
            if (pvPricer != null) {
                pricerNpv = pvPricer.getClass().getName();
            }
            if (overridedPVPricerName != null) {
                overridedPVPricerName = MappingsAccessor.getMappingValueByNameAndKey("pricers", overridedPVPricerName);
                if (!pricerNpv.equalsIgnoreCase(overridedPVPricerName)) {
                    try {
                        IPricer pricer = (IPricer) Class.forName(overridedPVPricerName).newInstance();
                        if (pricer instanceof ICompositePricer) {
                            ICompositePricer comp = (ICompositePricer) pricer;
                            comp.init(trade, pricingEnvironment);
                        }
                        pricersMap.put(MeasuresAccessor.MeasureGroup.PV_GROUP, pricer);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        logger.error("PRICER NOT FOUND : " + overridedPVPricerName);
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            }

            /**
             * load needed observables list
             */
            Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap =
                    PricingBuilder.getObservables(pricersMap, trade, pricingEnvironment);
            /**
             * fill observables
             */
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
                for (IObservable observable : observables.values()) {
                    observable.fillData(valDate, quoteSet);
                }
            }
            /*
             * build pricing setting
             */
            ArrayList<TemplateColumnItem> items = generateDefaultTemplateColumnItemsCollection(groupObservablesMap);
            filterDefaultTemplateColumnItemsWithPreferences(items, preferences);
            PricingSetting pricingSettings = buildPricingSetting(items, pricingEnvironment, valDate, false);
            // case when no greek set
            if (pricingSettings.getMeasureGroups().length==0){
                pricingSettings.setMeasureGroups(new MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP});
            }
            /**
             * price
             */
            Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> pricingResult
                    = pricePriceable(trade, groupObservablesMap, valDate, pricersMap, null, pricingSettings);
            /**
             * post treatments
             */
            LinkedHashMap<Integer, ReportLine> reportLines = new LinkedHashMap();
            ReportLine reportLine = new ReportLine();
            reportLine.setLineData(trade);
            reportLines.put(trade.getId(), reportLine);
            UnitMeasuresMultiplicator.calculate(reportLines, pricingResult.get(valDate), valDate, null, null);

            for (Entry<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> o : pricingResult.entrySet()) {
                for (Entry<Integer, Map<IPricerMeasure, IMeasureValue[]>> o2 : o.getValue().entrySet()) {
                    for (Entry<IPricerMeasure, IMeasureValue[]> o3 : o2.getValue().entrySet()) {
                        for (IMeasureValue mv : o3.getValue()) {
                            output.put(mv.getName(), mv.getMeasureValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return output;
    }
    public static final String GET_CDS_UPFRONT = "getUpfront";

    public static Double getUpfront(Trade trade, Date valueDate, String pricingEnvironmentName) {
        Double amount = 0.;
        /**
         * appel au pricer cds
         */
        if (trade != null && trade.getProduct() != null && trade.getProduct().getMaturityDate() != null) {
            MeasuresAccessor.MeasureGroup[] measureGroups = null;
            PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnvironmentName);
            /**
             * load pricer.
             */
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(trade, valueDate, pricingEnvironment, measureGroups);
            /**
             * load needed observables list
             */
            Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, trade, pricingEnvironment);
            /**
             * fill observables
             */
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            if (groupObservablesMap != null) {
                for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
                    for (IObservable observable : observables.values()) {
                        observable.fillData(valueDate, quoteSet);
                    }
                }
                IPricer pricer = pricersMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);
                if (pricer instanceof ICDSPricer) {
                    ICDSPricer cdspricer = (ICDSPricer) pricer;
                    double upFront = cdspricer.getUpfront(trade, groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP));
                    amount = upFront * trade.getQuantity().doubleValue();
                }
            }

        }
        return amount;
    }
    public static final String GET_CDS_SPREAD = "getCDSSpread";

    public static Double getCDSSpread(Trade trade, Date valueDate, String pricingEnvironmentName) {
        Double spread = 0.;
        /**
         * Call isda cds pricer
         */

        if (trade != null && trade.getProduct().getMaturityDate() != null) {
            MeasuresAccessor.MeasureGroup[] measureGroups = null;
            PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(pricingEnvironmentName);
            /**
             * load pricer.
             */
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(trade, valueDate, pricingEnvironment, measureGroups);
            /**
             * load needed observables list
             */
            Map<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap = PricingBuilder.getObservables(pricersMap, trade, pricingEnvironment);
            /**
             * fill observables
             */
            String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
            for (Map<Integer, IObservable> observables : groupObservablesMap.values()) {
                for (IObservable observable : observables.values()) {
                    observable.fillData(valueDate, quoteSet);
                }
            }
            ICDSPricer pricer = (ICDSPricer) pricersMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP);

            if (pricer != null) {
                double dspread = pricer.getSpread(trade, groupObservablesMap.get(MeasuresAccessor.MeasureGroup.PV_GROUP));
                spread = new Double(dspread);

            }
        }
        return spread;
    }

    public static void calculateUnitPostPricings(IPriceable priceable, Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate) {
        try {
            if (mapWithDate != null && mapWithDate.entrySet().size() > 0) {
                Entry<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> inEntry = mapWithDate.entrySet().iterator().next();
                Date valueDate = inEntry.getKey();
                Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade = inEntry.getValue();
                Map<IPricerMeasure, IMeasureValue[]> resToadd;
                IMeasureValue[] pricerMeasureValues;
                IPricerMeasure smeasure;
                IMeasureValue newpmv;
                /**
                 * trade pricing output : measure / meausre value
                 */
                Map<IPricerMeasure, IMeasureValue[]> pricerRes;
                pricerRes = (Map) pricerResultsByTrade.get(priceable.getId());
                if (pricerRes != null) {
                    resToadd = new HashMap();
                    for (Entry<IPricerMeasure, IMeasureValue[]> entry : pricerRes.entrySet()) {
                        pricerMeasureValues = entry.getValue();
                        List<IMeasureValue> amountMeasures = new ArrayList();
                        for (IMeasureValue pricerMeasureValue : pricerMeasureValues) {

                            /**
                             * unitary pricer measures => x trade quantity
                             */
                            if (pricerMeasureValue.getMeasure().isUnit() && pricerMeasureValue.getMeasureValue() != null) {
                                smeasure = pricerMeasureValue.getMeasure().getLinkedAmountMeasure();
                                newpmv = (IMeasureValue) pricerMeasureValue.clone();
                                newpmv.setMeasure(smeasure);
                                if (pricerMeasureValue.getQualifier() != null && !pricerMeasureValue.getQualifier().isEmpty()) {
                                    newpmv.setName(smeasure.getName() + StringUtils.DOT + pricerMeasureValue.getQualifier());
                                } else {
                                    newpmv.setName(smeasure.getName());
                                }
                                if (priceable.getQuantity(valueDate) != null) {
                                    newpmv.multiplyValueBy(priceable.getQuantity(valueDate));
                                    /**
                                     * m= pm% x amount
                                     */
                                } else {
                                    newpmv.setMeasureValue(BigDecimal.ZERO);
                                }
                                amountMeasures.add(newpmv);
                            }
                        }
                        if (amountMeasures.size() > 0) {
                            resToadd.put(entry.getKey().getLinkedAmountMeasure(), amountMeasures.toArray(new IMeasureValue[amountMeasures.size()]));
                        }
                    }
                    pricerRes.putAll(resToadd);
                    pricerResultsByTrade.put(priceable.getId(), pricerRes);
                    mapWithDate.put(valueDate, pricerResultsByTrade);
                }
            }
        } catch (Exception e) {
            logger.error("post pricing error on id " + priceable.getId() + StringUtils.SPACE + StringUtils.formatErrorMessage(e));
        }
    }
}
