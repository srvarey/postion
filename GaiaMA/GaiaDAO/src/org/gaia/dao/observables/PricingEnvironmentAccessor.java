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
package org.gaia.dao.observables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.dao.utils.PricingFilter;
import org.gaia.domain.observables.PricersSetting;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.observables.PricingSettingItem;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricingEnvironmentAccessor {

    private static final Logger logger = Logger.getLogger(PricingEnvironmentAccessor.class);
    public static final String GET_PRICING_ENVIRONMENT_LIST = "getPricingEnvironmentList";

    /**
     * gets the list of pricing environments
     *
     * @return
     */
    public static List<String> getPricingEnvironmentList() {
        return HibernateUtil.getObjectsWithQuery("select pe.name from PricingEnvironment pe order by pe.name");
    }
    public static final String GET_PRICING_ENVIRONMENT_FROM_NAME = "getPricingEnvironmentFromName";

    /**
     * a pricing environment with its name
     *
     * @param name
     * @return
     */
    public static PricingEnvironment getPricingEnvironmentFromName(String name) {
        return (PricingEnvironment) HibernateUtil.getObjectWithQueryWithCache("from PricingEnvironment where name='" + name + StringUtils.QUOTE);
    }
    public static final String LOOK_FOR_OBSERVABLE_ID_LINKED_WITH_PRICING_ENVIRONMENT = "lookForObservableIdLinkedWithPricingEnvironment";

    /**
     * get an observable id for a priceable and environment
     *
     * @param observable
     * @param priceable
     * @param pricingEnvironment
     * @return
     */
    public static Integer lookForObservableIdLinkedWithPricingEnvironment(IObservable observable, IPriceable priceable, PricingEnvironment pricingEnvironment) {
        Integer productId = observable.getProductId();
        if (productId == null && pricingEnvironment != null) {
            if (pricingEnvironment.getPricingSettingItemCollection() != null) {
                ArrayList<PricingSettingItem> pricingSettingItems = new ArrayList();

                for (PricingSettingItem pricingSettingItem : pricingEnvironment.getPricingSettingItemCollection()) {
                    if (observable.getObservableType().name.equalsIgnoreCase(pricingSettingItem.getItemType())) {
                        if (observable.getFunction().equalsIgnoreCase(pricingSettingItem.getPricingFunction())
                                || pricingSettingItem.getPricingFunction() == null
                                || pricingSettingItem.getPricingFunction().isEmpty()) {
                            pricingSettingItems.add(pricingSettingItem);
                        }
                    }
                }

                if (pricingSettingItems.size() == 1) {
                    return pricingSettingItems.get(0).getItemValueId();
                } else {
                    ArrayList<PricingFilter> pricingFilters = new ArrayList();
                    for (PricingSettingItem pricingSettingItem : pricingSettingItems) {
                        PricingFilter pricingFilter = new PricingFilter(pricingSettingItem.getCurrency(), pricingSettingItem.getProductType(), pricingSettingItem.getTradeFilterName(), pricingSettingItem.getItemValueId());
                        pricingFilters.add(pricingFilter);
                    }
                    // a hint to fake the following search
//                    String priceableCurrency=priceable.getProduct().getNotionalCurrency();
//                    if (observable.getProduct() != null) {
//                        if (observable.getProduct().getNotionalCurrency() != null) {
//                            priceable.getProduct().setNotionalCurrency(observable.getProduct().getNotionalCurrency());
//                        }
//                    }
                    PricingFilter myFilter = TradeAccessor.getValueByCurTypeFilter(priceable, pricingFilters);
//                    priceable.getProduct().setNotionalCurrency(priceableCurrency);
                    if (myFilter != null) {
                        return myFilter.referenceId;
                    }
                }
            }
        }
        return productId;
    }

    /**
     * get the fx forward curve linked to the currency pair and pricing env
     *
     * @param product : currency pair
     * @param pricingEnvironment : pricing environment
     */
//    public static Product lookForFxForwardCurveLinkedWithPricingEnvironment(Product product, PricingEnvironment pricingEnvironment) {
//        Product curve = null;
//        for (PricingSettingItem pricingSettingItem : pricingEnvironment.getPricingSettingItemCollection()) {
//            if (pricingSettingItem.getItemType().equalsIgnoreCase(AbstractObservable.ObservableType.FX_FORWARD_CURVE.name)) {
//                curve = findCurve(pricingSettingItem, product);
//                if (curve != null) {
//                    break;
//                }
//            }
//        }
//        return null;
//    }

//    public static final String LOOK_FOR_FX_VOLATILITY_CURVE="lookForFxVolatilityCurveLinkedWithPricingEnvironment";
    /**
     * get the fx volatility linked to the currency pair and pricing env
     *
     * @param product : currency pair
     * @param pricingEnvironment : pricing environment
     */
//    public static Product lookForFxVolatilityCurveLinkedWithPricingEnvironment(Product product, PricingEnvironment pricingEnvironment) {
//        Product curveProduct = null;
//        for (PricingSettingItem pricingSettingItem : pricingEnvironment.getPricingSettingItemCollection()) {
//            if (pricingSettingItem.getItemType().equalsIgnoreCase(AbstractObservable.ObservableType.FX_VOLATILITY.name)) {
//                curveProduct = findCurve(pricingSettingItem, product);
//                if (curveProduct != null) {
//                    break;
//                }
//            }
//        }
//        return curveProduct;
//    }

    /**
     * Loads the observable quoteset depending on the pricing environment
     *
     * @param observable observable object
     * @param pricingEnvironment
     * @return
     */
    public static String getObservableQuoteSet(IPriceable observable, PricingEnvironment pricingEnvironment) {
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        if (pricingEnvironment != null) {
            if (pricingEnvironment.getPricingSettingItemCollection() != null) {
                ArrayList<PricingSettingItem> pricingSettingItems = new ArrayList();

                for (PricingSettingItem pricingSettingItem : pricingEnvironment.getPricingSettingItemCollection()) {
                    if (pricingSettingItem.getItemType().equalsIgnoreCase(AbstractObservable.ObservableType.MARKET_QUOTE.name)) {
                        pricingSettingItems.add(pricingSettingItem);
                    }
                }

                if (pricingSettingItems.size() == 1) {
                    return pricingSettingItems.get(0).getItemValue();
                } else {
                    ArrayList<PricingFilter> pricingFilterList = new ArrayList();
                    for (PricingSettingItem pricingSettingItem : pricingSettingItems) {
                        PricingFilter ctf = new PricingFilter(pricingSettingItem.getCurrency(), pricingSettingItem.getProductType(), pricingSettingItem.getTradeFilterName(), pricingSettingItem.getItemValue());
                        pricingFilterList.add(ctf);
                    }
                    PricingFilter myFilter = TradeAccessor.getValueByCurTypeFilter(observable, pricingFilterList);
                    if (myFilter != null) {
                        return myFilter.quoteSet;
                    }
                }
            }
        }
        return quoteSet;
    }

    /**
     * gets the map of pricers by priceables
     *
     * @param listPriceables priceable objects
     * @param pricingEnvironment pricing environment
     * @param measureGroups
     * @return
     */
    public static Map<Integer, Map<MeasuresAccessor.MeasureGroup, String>> getPricersMap(List<IPriceable> listPriceables, PricingEnvironment pricingEnvironment, MeasuresAccessor.MeasureGroup[] measureGroups) {

        Map<Integer, Map<MeasuresAccessor.MeasureGroup, String>> resultMap = new HashMap();
        if (listPriceables != null && pricingEnvironment != null) {
            for (IPriceable priceable : listPriceables) {
                Map<MeasuresAccessor.MeasureGroup, String> map = getPricersMapByPriceable(priceable, pricingEnvironment, measureGroups);
                resultMap.put(priceable.getId(), map);
            }
        }
        return resultMap;
    }

    /**
     * gets the map of pricers by measure group
     *
     * @param priceable priceable object
     * @param pricingEnvironment pricing environment
     * @param measureGroups
     * @return
     */
    public static Map<MeasuresAccessor.MeasureGroup, String> getPricersMapByPriceable(IPriceable priceable, PricingEnvironment pricingEnvironment, MeasuresAccessor.MeasureGroup[] measureGroups) {

        Map<MeasuresAccessor.MeasureGroup, String> map = new HashMap();
        if (measureGroups == null) {
            measureGroups = MeasuresAccessor.MeasureGroup.values();
        }
        if (priceable != null) {
            for (MeasuresAccessor.MeasureGroup group : measureGroups) {
                ArrayList<PricingFilter> pricingFilterList = new ArrayList();
                Map<Integer,PricersSetting> pricersSettings = new HashMap();
                for (PricersSetting setting : pricingEnvironment.getPricersSettingCollection()) {
                    if (setting.getMeasureGroup().equals(group.name())) {
                        pricersSettings.put(setting.getPricersSettingId(),setting);
                        PricingFilter filter = new PricingFilter(StringUtils.EMPTY_STRING, setting.getProductType(), setting.getTradeFilterName(), setting.getPricersSettingId());
                        pricingFilterList.add(filter);
                    }
                }

                PricingFilter myFilter = TradeAccessor.getValueByCurTypeFilter(priceable, pricingFilterList);
                if (myFilter != null) {
                    PricersSetting setting=pricersSettings.get(myFilter.referenceId);
                    if (setting!=null){
                            map.put(group, setting.getPricer());

                    }
                } else {
                    logger.error("NO PRICER FOUND FOR ID " + priceable.getId() + " on " + group.name() + " type=" + priceable.getProduct().getProductType());
                }
            }
        }
        return map;

    }
    public static final String GET_PRICING_SETTING_ITEM = "getPricingSettingItem";

    /**
     * retrieve price from table PricingSettingItem .
     *
     * @param id
     * @return
     */
    public static PricingSettingItem getPricingSettingItem(Integer id) {
        return (PricingSettingItem) HibernateUtil.getObjectWithQuery("from PricingSettingItem where pricing_setting_item_id=" + id);
    }
    public static final String GET_PRICERS_SETTING = "getPricersSetting";

    /**
     * retrieve price from table PricersSetting.
     *
     * @param id
     * @return
     */
    public static PricersSetting getPricersSetting(Integer id) {
        return (PricersSetting) HibernateUtil.getObjectWithQuery("from PricersSetting where pricers_setting_id=" + id);
    }
    public static final String LOAD_PRICING_ITEM_VALUES_IDS = "loadPricingSettingItemValuesIds";

    public static void loadPricingSettingItemValuesIds(PricingEnvironment pricingEnvironment) {
        if (pricingEnvironment != null) {
            if (pricingEnvironment.getPricingSettingItemCollection() != null) {
                for (PricingSettingItem pricingSettingItem : pricingEnvironment.getPricingSettingItemCollection()) {
                    try {
                        Integer productId = (Integer) HibernateUtil.getObjectWithQueryWithCache("select p.productId from Product p where p.shortName='" + pricingSettingItem.getItemValue() + StringUtils.QUOTE);
                        if (productId != null) {
                            pricingSettingItem.setItemValueId(productId);
                        }
                    } catch (Exception e) {
                        logger.error("error " + StringUtils.formatErrorMessage(e));
                    }
                }
            }
        }
    }
    public static final String STORE_PRICING_ENVIRONMENT = "storePricingEnvironment";

    public static void storePricingEnvironment(PricingEnvironment pricingEnvironment) {
        HibernateUtil.storeObject(pricingEnvironment);
    }
    public static final String STORE_PRICERS_SETTING = "storePricersSetting";

    public static void storePricersSetting(PricersSetting pricersSetting) {
        HibernateUtil.storeObject(pricersSetting);
    }
    public static final String DELETE_PRICERS_SETTING = "deletePricersSetting";

    public static void deletePricersSetting(PricersSetting pricersSetting) {
        HibernateUtil.deleteObject(pricersSetting);
    }
    public static final String STORE_PRICING_SETTING_ITEM = "storePricingSettingItem";

    public static void storePricingSettingItem(PricingSettingItem pricingSettingItem) {
        HibernateUtil.storeObject(pricingSettingItem);
    }
    public static final String DELETE_PRICING_SETTING_ITEM = "deletePricingSettingItem";

    public static void deletePricingSettingItem(PricingSettingItem pricingSettingItem) {
        HibernateUtil.deleteObject(pricingSettingItem);
    }
    public static final String GET_PRICING_ENVIRONMENT_NAMES_BY_FILTER_NAME = "getPricingEnvironmentNamesByFilterName";

    /**
     * return the list of pricing environment .
     *
     * @param filterName
     * @return
     */
    public static List<String> getPricingEnvironmentNamesByFilterName(String filterName) {
        return HibernateUtil.getObjectsWithQueryWithCache("select pe.name from PricingEnvironment pe inner join pe.pricingSettingItemCollection psi inner join pe.pricersSettingCollection pss where psi.tradeFilterName='" + filterName + "' or pss.tradeFilterName='" + filterName + StringUtils.QUOTE);
    }
    public static final String GET_DEFAULT_PRICING_ENVIRONMENT = "getDefaultPricingEnvironment";

    /**
     * return the default pricing environment .
     * @return
     */
    public static PricingEnvironment getDefaultPricingEnvironment() {
        String defaultPricingEnvironmentName = MappingsAccessor.getDefaultPricingEnvironmentName();
        return getPricingEnvironmentFromName(defaultPricingEnvironmentName);
    }

}
