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
package org.gaia.dao.trades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.MarketQuote.QuotationType;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductTypeUtil {

    public enum ProductFamily {

        EQUITY("Equity"),
        IR("IR"),
        CREDIT("Credit"),
        COMMO("Commo"),
        FX("FX"),
        FUNDS("Funds"),
        OTHER("Other");

        private final String name;

        ProductFamily(String name) {
            this.name = name;
        }
    }

    public enum ProductTypeUse {

        LEG, OBSERVABLE, LISTED, OTC, CLEARED_OTC, CASH
    }

    public enum ProductType {
        // families

        // families
        // families

        // families
        EQUITY("Equity", null, null, null, null, null),
        IR("InterestRate", null, null, null, null, null),
        CREDIT("Credit", null, null, null, null, null),
        COMMO("Commodities", null, null, null, null, null),
        FX("Forex", null, null, null, null, null),
        OTHER("Other", null, null, null, null, null),
        FUNDS("Funds", null, null, null, null, null),
        // types  first level listed
        BOND("Bond", ProductType.IR, ProductFamily.IR, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.DIRTY),
        LOAN("Loan", ProductType.IR, ProductFamily.IR, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.DIRTY),
        STOCK("Stock", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        ADR("ADR", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        CERTIFICATE("Certificate", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        CFD("CFD", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, null, MarketQuote.QuotationType.PRICE),
        EQUITY_FORWARD("Equity Forward", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, null, MarketQuote.QuotationType.PRICE),
        EQUITY_FUTURE("Equity Future", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        EQUITY_LISTED_OPTION("Equity Listed Option", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        FUND("Investment Fund", ProductType.FUNDS, ProductFamily.FUNDS, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        FUND_Of_FUNDS("Fund of funds", ProductType.FUNDS, ProductFamily.FUNDS, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        ETF("ETF", ProductType.FUNDS, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        WARRANT("Warrant", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LISTED, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        BOND_FUTURE("Bond Future", ProductType.IR, ProductFamily.IR, ProductTypeUse.LISTED, null, MarketQuote.QuotationType.PRICE),
        CASH("Cash", ProductType.OTHER, ProductFamily.OTHER, ProductTypeUse.CASH, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        // types  first level OTC

        MMKT("Money Market", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.RATE),
        IRS("IRS", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.RATE),
        CCS("CCS", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        BASIS_SWAP("Basis Swap", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        CDS_PRODUCT("CDS Product", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.CLEARED_OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        CDS_INDEX("CDS Index", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.CLEARED_OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        CDS_INDEX_TRANCH("CDS Index Tranch", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        CDS_FIXED_RECOVERY("CDS Fixed Recovery", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        CDS_RECOVERY_LOCKS("CDS Recovery Locks", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        CUSTOM_CDS("Custom CDS", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        LOAN_CDS("Loan CDS", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.BASIS_POINT),
        PERFORMANCE_SWAP("Performance Swap", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.OTC, null, MarketQuote.QuotationType.SPREAD),
        EQUITY_SWAP("Equity Swap", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICEpct),
        CDO("CDO", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.SPREAD),
        CDO_TRANCH("CDO Tranch", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OTC, null, MarketQuote.QuotationType.SPREAD),
        EQUITY_OTC_OPTION("Equity OTC Option", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        CURRENCY_PAIR("Currency Pair", ProductType.FX, ProductFamily.FX, ProductTypeUse.CLEARED_OTC, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICE),
        //        FX_SPOT("FX Spot", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        //        FX_FORWARD("FX Forward", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_FORWARD_CURVE("FX Forward Curve", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, AbstractObservable.ObservableType.FX_FORWARD_CURVE.name, MarketQuote.QuotationType.SPREAD),
        FX_FORWARD_CURVE_POINT("FX Forward Curve Point", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, AbstractObservable.ObservableType.FX_FORWARD_CURVE.name, MarketQuote.QuotationType.SPREAD),
        FX_NDF("FX NDF", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_OPTION("FX Option", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_VANILLA_OPTION("FX Vanilla Option", ProductType.FX_OPTION, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_BARRIER_OPTION("FX Barrier Option", ProductType.FX_OPTION, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_BINARY_OPTION("FX Binary Option", ProductType.FX_OPTION, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_DIGITAL_OPTION("FX Digital Option", ProductType.FX_OPTION, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_COMPLEX_OPTION("FX Complex Option", ProductType.FX_OPTION, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_STRATEGY("FX Strategy", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        HEDGE("Hedge", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_STRADDLE("FX Straddle", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_STRANGLE("FX Strangle", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_BUTTERFLY("FX ButterFly", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_RISK_REVERSAL("FX Risk Reversal", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_STRIP("FX Strip", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_CUSTOM_STRATEGY("FX Custom Strategy", ProductType.FX_STRATEGY, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_SWAP("FX Swap", ProductType.FX, ProductFamily.FX, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICE),
        FX_VOLATILITY("FX Volatility", ProductType.FX, ProductFamily.FX, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.VOLATILITY.name, MarketQuote.QuotationType.VOLATILITY),
        FX_VOLATILITY_POINT("FX Volatility Point", ProductType.FX, ProductFamily.FX, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.VOLATILITY.name, MarketQuote.QuotationType.VOLATILITY),
        BOND_OPTION("Bond Option", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICEpct),
        CAP_FLOOR("Cap/Floor", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICEpct),
        FRA("FRA", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICEpct),
        SWAPTION("Swaption", ProductType.IR, ProductFamily.IR, ProductTypeUse.OTC, null, MarketQuote.QuotationType.PRICEpct),
        // swap legs
        FIXED_LEG("Fixed Leg", ProductType.IR, ProductFamily.IR, ProductTypeUse.LEG, null, MarketQuote.QuotationType.PRICEpct),
        FLOATING_LEG("Floating Leg", ProductType.IR, ProductFamily.IR, ProductTypeUse.LEG, null, MarketQuote.QuotationType.PRICEpct),
        CREDIT_PROTECTION_LEG("Credit Protection Leg", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.LEG, null, MarketQuote.QuotationType.PRICEpct),
        CREDIT_FUNDING_LEG("Credit Funding Leg", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.LEG, null, MarketQuote.QuotationType.PRICEpct),
        PERFORMANCE_LEG("Performance Leg", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.LEG, null, MarketQuote.QuotationType.PRICEpct),
        // observables
        INTEREST_RATE_INDEX("Rate Index", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.RATE),
        CURVE("Curve", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICEpct),
        IR_CURVE("IR Curve", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.PRICEpct),
        IR_CURVE_MMKT_UNDERLYING("IR Curve Money Market Underlying", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.RATE),
        IR_CURVE_SWAP_UNDERLYING("IR Curve Swap Underlying", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.RATE),
        IR_BASIS_SWAP_CURVE("IR Basis Swap Curve", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.PRICEpct),
        IR_BASIS_SWAP_CURVE_UNDERLYING("IR Basis Swap Curve Underlying", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.SPREAD),
        IR_SPREAD_CURVE("IR Spread Curve", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.SPREAD),
        //        COMPOSITE_CURVE("Composite Curve", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.SPREAD),
        IR_SPREAD_CURVE_POINT("IR Spread Curve Point", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.SPREAD),
        IR_CURVE_POINT("IR Curve Point", ProductType.IR, ProductFamily.IR, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.IR_CURVE.name, MarketQuote.QuotationType.RATE),
        CREDIT_CURVE("Credit Curve", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.CREDIT_CURVE.name, MarketQuote.QuotationType.RATE),
        CREDIT_CURVE_POINT("Credit Curve Point", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.CREDIT_CURVE.name, MarketQuote.QuotationType.RATE),
        RECOVERY_CURVE("Recovery Curve", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.RECOVERY_CURVE.name, MarketQuote.QuotationType.PRICEpct),
        RECOVERY_CURVE_POINT("Recovery Curve Point", ProductType.CREDIT, ProductFamily.CREDIT, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.RECOVERY_CURVE.name, MarketQuote.QuotationType.PRICEpct),
        EQUITY_INDEX("Equity Index", ProductType.EQUITY, ProductFamily.EQUITY, ProductTypeUse.OBSERVABLE, AbstractObservable.ObservableType.MARKET_QUOTE.name, MarketQuote.QuotationType.PRICEpct);

        public String name;
        public ProductType parent;
        public ProductFamily family;
        public ProductTypeUse use;
        public String observableType;
        public MarketQuote.QuotationType defaultQuotationType;

        ProductType(String name, ProductType parent, ProductFamily family, ProductTypeUse use, String observableType, MarketQuote.QuotationType defaultQuotationType) {
            this.name = name;
            /**
             * name of product
             */
            this.parent = parent;
            /**
             * parent of product
             */
            this.family = family;
            /**
             * family of product
             */
            this.use = use;
            /**
             * type of use
             */
            this.observableType = observableType;
            this.defaultQuotationType = defaultQuotationType;
        }

        public String getName() {
            return name;
        }

        public ProductType getParent() {
            return parent;
        }

        public ProductFamily getFamily() {
            return family;
        }

        public ProductTypeUse getUse() {
            return use;
        }

        public String getObservableType() {
            return observableType;
        }

        public QuotationType getDefaultQuotationType() {
            return defaultQuotationType;
        }
    }

    /**
     * list type of product use
     * @return
     */
    public static List<ProductTypeUse> loadProductTypeUses() {
        ProductTypeUse[] arr = ProductTypeUse.values();
        return new ArrayList(Arrays.asList(arr));
    }

    /**
     * list of product family
     * @return
     */
    public static List<ProductFamily> loadProductFamilies() {
        ProductFamily[] arr = ProductFamily.values();
        return new ArrayList(Arrays.asList(arr));
    }

    /**
     * list of product type and families
     * @return
     */
    public static List<ProductType> loadProductTypesAndFamilies() {
        ProductType[] arr = ProductType.values();
        return new ArrayList(Arrays.asList(arr));
    }

    /**
     * list of product type provided parent not null
     * @return
     */
    public static List<ProductType> loadProductTypes() {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.parent == null) {
                list.remove(productType);
            }
        }
        return list;
    }

    /**
     * list of product type provided parent not null
     * @return
     */
    public static List<String> loadProductTypeName() {
        ArrayList<String> list = new ArrayList<>();
        for (ProductType productType : loadProductTypes()) {
            list.add(productType.getName());
        }
        return list;
    }

    /**
     * list of product type order by family and use
     * @param family
     * @param use
     * @return
     */
    public static List<ProductType> loadTypesByFamilyAndUse(ProductFamily family, ProductTypeUse use) {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.family != null && productType.use != null) {
                if (!productType.family.equals(family) || !productType.use.equals(use)) {
                    list.remove(productType);
                }
            } else {
                list.remove(productType);
            }
        }
        return list;
    }

    /**
     * list of product type order by family
     * @param family
     * @return
     */
    public static List<ProductType> loadTypesByFamily(ProductFamily family) {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.family != null) {
                if (!productType.family.equals(family)) {
                    list.remove(productType);
                }
            } else {
                list.remove(productType);
            }
        }
        return list;
    }

    /**
     * list of product type order by use
     * @param use
     * @return
     */
    public static List<ProductType> loadTypesByUse(ProductTypeUse use) {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.use != null) {
                if (!productType.use.equals(use)) {
                    list.remove(productType);
                }
            } else {
                list.remove(productType);
            }
        }
        return list;
    }


    /**
     * list of product type order by use
     * @param use
     * @return
     */
    public static String loadTypesByUseSQL(ProductTypeUse use) {
        String ret=StringUtils.EMPTY_STRING;
        for (ProductType productType : loadTypesByUse(use)) {
            ret+=StringUtils.QUOTE+productType.name+"',";
        }
        if (ret.length()>0){
            ret=ret.substring(0,ret.length()-1);
        }
        return ret;
    }
    /**
     * list of product type order by type used
     * @return
     */
    public static List<ProductType> loadUsableTypes() {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.use != null) {
                if (productType.use == null) {
                    list.remove(productType);
                }
            } else {
                list.remove(productType);
            }
        }
        return list;
    }

    /**
     * load listed products
     * @return
     */
    public static List<ProductType> loadListed() {
        return loadTypesByUse(ProductTypeUse.LISTED);
    }

    /**
     * load LEGS
     * @return
     */
    public static List<ProductType> loadLegs() {
        return loadTypesByUse(ProductTypeUse.LEG);
    }

    /**
     * load OTCs
     * @return
     */
    public static List<ProductType> loadOTC() {
        return loadTypesByUse(ProductTypeUse.OTC);
    }

    /**
     * load equites
     * @return
     */
    public static List<ProductType> loadEquities() {
        return loadTypesByFamily(ProductFamily.EQUITY);
    }
    /**
     * load funds
     * @return
     */
    public static List<ProductType> loadFunds() {
        return loadTypesByFamily(ProductFamily.FUNDS);
    }
    /*
     * load equites and funds
     * @return
     */
    public static List<ProductType> loadEquitiesAndFunds() {
        List<ProductType> list= loadTypesByFamily(ProductFamily.EQUITY);
        list.addAll(loadTypesByFamily(ProductFamily.FUNDS));
        return list;
    }

    /**
     * load listed equites
     * @return
     */
    public static List<ProductType> loadListedEquities() {
        return loadTypesByFamilyAndUse(ProductFamily.EQUITY, ProductTypeUse.LISTED);
    }

    /**
     * load IR RATES
     * @return
     */
    public static List<ProductType> loadListedIRRates() {
        return loadTypesByFamilyAndUse(ProductFamily.IR, ProductTypeUse.LISTED);
    }

    /**
     * load UNDERLYINGS types
     * @return
     */
    public static List<ProductType> loadUnderlyings() {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.use != null) {
                if (!productType.use.equals(ProductTypeUse.LISTED)
                 && !productType.use.equals(ProductTypeUse.OBSERVABLE)) {
                    list.remove(productType);
                }
            }
        }
        list.add(ProductType.CURRENCY_PAIR);
        return list;
    }

    public static FilterCriteria getFXOptionTypesFilterCriteria() {
        FilterCriteria criteria = new FilterCriteria();
        criteria.setColumnName("Product.ProductType");
        criteria.setGetter("getProduct/getProductType");
        criteria.setReturnType(String.class.getName());
        criteria.setNotIn(false);
        criteria.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
        List<ProductType> types = getChildrenTypes(ProductType.FX_OPTION);
        String sType = StringUtils.EMPTY_STRING;
        for (ProductType type : types) {
            sType = sType + type.getName() + StringUtils.COMMA;
        }
        if (sType.length() > 0) {
            sType = sType.substring(0, sType.length() - 1);
        }
        criteria.setValue(sType);
        return criteria;
    }

    public static List<ProductType> getChildrenTypes(ProductType parentType) {
        List children = new ArrayList();
        children.add(parentType);
        for (ProductType type : ProductType.values()) {
            if (type.getParent() != null && type.getParent().equals(parentType)) {
                children.add(type);
            }
        }
        return children;
    }

    public static List<ProductType> getBondTypes() {
        ArrayList<ProductType> list = new ArrayList();
        list.add(ProductType.BOND);
        return list;
    }

    /**
     * load TRADEABLE TYPES
     * @return
     */
    public static List<ProductType> loadTradeableTypes() {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.use != null) {
                if (!productType.use.equals(ProductTypeUse.LISTED) && !productType.use.equals(ProductTypeUse.OTC)
                        && !productType.use.equals(ProductTypeUse.CLEARED_OTC)) {
                    list.remove(productType);
                }
            }
        }
        return list;
    }

    /**
     * load LOAD_TRADEABLE AND CASH TYPES
     * @return
     */
    public static List<ProductType> loadTradeableAndCashTypes() {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.use != null) {
                if (!productType.use.equals(ProductTypeUse.LISTED) && !productType.use.equals(ProductTypeUse.OTC) && !productType.use.equals(ProductTypeUse.CASH)) {
                    list.remove(productType);
                }
            }
        }
        return list;
    }

    public static List<String> loadTradeableTypeNames() {
        ArrayList<ProductType> list = (ArrayList) loadTradeableTypes();
        ArrayList<String> reslist = new ArrayList();
        for (ProductType productType : list) {
            reslist.add(productType.name);
        }
        return reslist;
    }

    public static List<ProductType> loadFamilyUnderlyings(ProductFamily family) {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        ArrayList<ProductType> tmp = (ArrayList) list.clone();
        for (ProductType productType : tmp) {
            if (productType.family != null && productType.use != null) {
                if (!productType.family.equals(family) || (!productType.use.equals(ProductTypeUse.LISTED) && !productType.use.equals(ProductTypeUse.OBSERVABLE))) {
                    list.remove(productType);
                }
            } else {
                list.remove(productType);
            }
        }
        return list;
    }

    public static List<ProductType> loadEquityAndFundsUnderlyings() {
        List<ProductType> list= loadFamilyUnderlyings(ProductFamily.EQUITY);
        list.addAll(loadFamilyUnderlyings(ProductFamily.FUNDS));
        return list;
    }

    public static List<ProductType> loadEquityUnderlyings() {
        return loadFamilyUnderlyings(ProductFamily.EQUITY);
    }

    public static List<ProductType> loadRateUnderlyings() {
        return loadFamilyUnderlyings(ProductFamily.IR);
    }

    public static ProductType getProductTypeByName(String name) {
        ArrayList<ProductType> list = (ArrayList) loadProductTypesAndFamilies();
        for (ProductType productType : list) {
            if (productType.name != null) {
                if (productType.name.equals(name)) {
                    return productType;
                }
            }
        }
        return null;
    }

    /**
     * check if is a child of a parent
     * @param child
     * @param parent
     * @return
     */
    public static boolean isChildOf(String child, String parent) {
        boolean ret = false;
        ProductType productType = getProductTypeByName(child);
        if (productType != null) {
            while (productType.parent != null) {
                if (productType.parent.name.equalsIgnoreCase(parent)) {
                    ret = true;
                }
                productType = productType.parent;
            }
        }
        return ret;
    }

    public static List<String> getProductStatusList() {
        List<String> ret = new ArrayList();
        ProductConst.ProductStatus[] arr = ProductConst.ProductStatus.values();
        for (ProductConst.ProductStatus arr1 : arr) {
            ret.add(arr1.name());
        }
        return ret;
    }
}
