/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOAgentPool;
import org.gaia.dao.observables.ProductHistoricalAccessor;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.trades.ProductConst.cdsImmDate;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductEquity;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.ProductSingleTraded;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.trades.ProductUnderlyingPK;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;
import sk.nociar.jpacloner.JpaCloner;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductAccessor {

    private static final Logger logger = Logger.getLogger(ProductAccessor.class);
    public static final String GET_CREDIT_EVENT_BY_ID = "getCreditEventById";

    /**
     * gest a contract event by id
     *
     * @param contractEventId
     * @return
     */
    public static ContractEvent getContractEventById(Integer contractEventId) {
        return (ContractEvent) HibernateUtil.getObjectWithQuery("from ContractEvent ce where ce.contractEventId =" + contractEventId);
    }
    public static final String SAVE_CONTRACT_EVENT = "saveContractEvent";

    public static Integer saveContractEvent(ContractEvent contractEvent) {
        return (Integer) HibernateUtil.saveObject(contractEvent);
    }
    public static final String STORE_PRODUCT = "storeProduct";

    public static Product storeProduct(Product product) {

        Session session = HibernateUtil.getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            product = storeProduct(product, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.fatal(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
        // update schedule
        DAOAgentPool.callAsynchroneMethod(ScheduleBuilder.class, ScheduleBuilder.UPDATE_PRODUCT_SCHEDULE, product);
        return product;
    }

    /**
     * Stores a product
     *
     * @param product the product to store
     * @param session
     * @return
     */
    public static Product storeProduct(Product product, Session session) {
        // schedule has to be updated outside
        if (product == null) {
            return null;
        } else if (product.getId() == null) {
            product.setProductVersion(1);
            product.setCreationDateTime((new GregorianCalendar()).getTime());
        } else {
            if (product.getProductVersion() != null) {
                product.setProductVersion(product.getProductVersion() + 1);
            } else {
                product.setProductVersion(1);
            }
            product.setUpdateDateTime((new GregorianCalendar()).getTime());
        }

        /*controls
         * */
        boolean correct = controlProduct(product);
        if (!correct) {
            return null;
        }

        /**
         * manage short names
         */
        manageShortName(product);

        // pre store
        if (product.getProductForex() != null && product.getProductForex().getProductForexId() == null && product.getProductId() != null) {
            ProductForex forex;
            session.save(product.getProductForex());
            forex = product.getProductForex();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductForex(forex);
        }

        if (product.getProductCurve() != null && product.getProductCurve().getProductCurveId() == null && product.getProductId() != null) {
            ProductCurve curve;
            session.save(product.getProductCurve());
            curve = product.getProductCurve();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductCurve(curve);
        }

        if (product.getProductEquity() != null && product.getProductEquity().getProductEquityId() == null && product.getProductId() != null) {
            ProductEquity equity;
            session.save(product.getProductEquity());
            equity = product.getProductEquity();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductEquity(equity);
        }
        if (product.getProductCredit() != null && product.getProductCredit().getProductCreditId() == null && product.getProductId() != null) {
            ProductCredit credit;
            session.save(product.getProductCredit());
            credit = product.getProductCredit();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductCredit(credit);
        }
        if (product.getProductRate() != null && product.getProductRate().getProductRateId() == null && product.getProductId() != null) {
            ProductRate rate;
            session.save(product.getProductRate());
            rate = product.getProductRate();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductRate(rate);
        }
        if (product.getProductSingleTraded() != null && product.getProductSingleTraded().getProductSingleTradedId() == null && product.getProductId() != null) {
            ProductSingleTraded single;
            session.save(product.getProductSingleTraded());
            single = product.getProductSingleTraded();
            session.flush();
            product = (Product) session.get(Product.class, product.getProductId());
            product.setProductSingleTraded(single);
        }
        // SAVE HERE
        //==================
        session.saveOrUpdate(product);
        // underlyings
        saveOrUpdateProductUnderlyings(product, session);
        if (product.getSubProducts() != null) {
            for (Product subProduct : product.getSubProducts()) {
                saveOrUpdateProductUnderlyings(subProduct, session);
            }
        }

        return product;
    }

    public static boolean controlProduct(Product product) {
        boolean controlOk = true;
        List<String> errorList = new ArrayList<>();
        if (StringUtils.isEmptyString(product.getNotionalCurrency())) {
            errorList.add(" NO CURRENCY");
        }
        if (product.getNotionalMultiplier() == null) {
            errorList.add(" NO NOTIONAL ");
        }
        if (StringUtils.isEmptyString(product.getProductType())) {
            errorList.add(" NO PRODUCT TYPE ");
        }
        if (!errorList.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("ERROR in ProductAccessor.storeProduct on ");
            errorMessage.append(product.toString());
            for (String error : errorList) {
                errorMessage.append(error);
            }
            logger.error(errorMessage.toString());
            controlOk = false;
        }
        if (product.getSubProducts() != null) {
            for (Product subProduct : product.getSubProducts()) {
                boolean ret = controlProduct(subProduct);
                if (!ret) {
                    return ret;
                }
            }
        }
        return controlOk;
    }

    /**
     * Manages short name so that it is set and unique
     *
     * @param product the product to store
     */
    public static void manageShortName(Product product) {
        if (StringUtils.isEmptyString(product.getShortName())) {
            product.setShortName(getDefaultProductShortName(product));
        }
        product.setShortName(secureUniqueShortName(product));
    }
    public static final String GET_DEFAULT_PRODUCT_SHORT_NAME = "getDefaultProductShortName";

    /**
     * Get a default short name to any product used if no shortname is set
     *
     * @param product the product to store
     * @return
     */
    public static String getDefaultProductShortName(Product product) {

        // shortens long name
        if (product.getLongName() != null && !product.getLongName().isEmpty()) {
            return product.getLongName().substring(0, 128);
        }
        // OR

        //start with type
        StringBuilder name = new StringBuilder(product.getProductType());
        // add underlying (for derivatives)
        if (product.loadSingleUnderlying() != null) {
            name.append(StringUtils.SPACE).append(product.loadSingleUnderlying().getShortName());
        }
        // add Call/Put (for options)
        if (product.getProductEquity() != null) {
            if (product.getProductEquity().getOptionStyle() != null) {
                name.append(StringUtils.SPACE).append(product.getProductEquity().getOptionStyle());
            }
        }
        if (product.getProductForex() != null) {
            if (product.getProductForex().getOptionStyle() != null) {
                name.append(StringUtils.SPACE).append(product.getProductForex().getOptionStyle());
            }
        }
        // add strike (for options)
        if (product.getProductEquity() != null) {
            if (product.getProductEquity().getStrike() != null) {
                name.append(StringUtils.SPACE).append(product.getProductEquity().getStrike());
            }
        }
        if (product.getProductForex() != null) {
            if (product.getProductForex().getStrike() != null) {
                name.append(StringUtils.SPACE).append(product.getProductForex().getStrike());
            }
        }
        // add issuer (for credit)
        if (product.getProductCredit() != null) {
            if (product.getProductCredit().getIssuer() != null) {
                LegalEntity issuer = getProductIssuer(product.getProductId());
                name.append(StringUtils.SPACE).append(issuer.getLegalEntityId());
            }
        }
        // add tenor (for curves)
        if (product.getProductCurve() != null) {
            if (product.getProductCurve().getTenor() != null) {
                name.append(StringUtils.SPACE).append(product.getProductCurve().getTenor());
            }
            if (product.getProductCurve().getStrike() != null) {
                name.append(StringUtils.SPACE).append(product.getProductCurve().getStrike().toPlainString());
            }
        }

        // on swaps
        if (product.getSubProducts() != null) {
            Iterator<Product> it = product.getSubProducts().iterator();
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));
            // leg 1
            if (product.getSubProducts().size() > 0) {
                Product product1 = it.next();
                if (product1 != null && product1.loadSingleUnderlying() != null) {
                    name.append(StringUtils.SPACE).append(product1.loadSingleUnderlying().getShortName());
                } else if (product1 != null && product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name) && product1.getProductCredit() != null) {
                    if (product1.getProductId() != null) {
                        name.append(StringUtils.SPACE).append(ProductAccessor.getProductIssuer(product1.getProductId()).getShortName());
                    } else if (!product1.getProductCredits().isEmpty()
                            && product1.getProductCredits().iterator().next().getIssuer() != null
                            && !(product1.getProductCredits().iterator().next().getIssuer() instanceof HibernateProxy)) {
                        name.append(StringUtils.SPACE).append(product1.getProductCredits().iterator().next().getIssuer().getShortName());
                    }
                } else if (product1 != null && product1.getProductSingleTraded() != null && product1.getProductSingleTraded().getRate() != null) {
                    name.append(StringUtils.SPACE).append(decimalFormat.format(product1.getProductSingleTraded().getRate().multiply(BigDecimal.valueOf(100)))).append("%");
                }
            }

            // leg 2
            if (product.getSubProducts().size() > 1) {
                name.append("/");
                Product product2 = it.next();

                if (product2 != null && product2.loadSingleUnderlying() != null) {
                    name.append(StringUtils.SPACE).append(product2.loadSingleUnderlying().getShortName());
                } else if (product2 != null && product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name) && product2.getProductCredit() != null) {
                    if (product2.getProductId() != null) {
                        name.append(StringUtils.SPACE).append(ProductAccessor.getProductIssuer(product2.getProductId()).getShortName());
                    } else if (!product2.getProductCredits().isEmpty()
                            && product2.getProductCredits().iterator().next().getIssuer() != null
                            && !(product2.getProductCredits().iterator().next().getIssuer() instanceof HibernateProxy)) {
                        name.append(StringUtils.SPACE).append(product2.getProductCredits().iterator().next().getIssuer().getShortName());

                    }
                } else if (product2 != null && product2.getProductSingleTraded() != null && product2.getProductSingleTraded().getRate() != null) {
                    name.append(StringUtils.SPACE).append(decimalFormat.format(product2.getProductSingleTraded().getRate().multiply(BigDecimal.valueOf(100)))).append("%");
                }
            }
        }

        if (product.getMaturityDate() != null) {
            name.append(StringUtils.SPACE).append(HibernateUtil.dateFormat.format(product.getMaturityDate()));
        }
        return name.toString();
    }

    /**
     * Check that the short name is unique and add an index to it if it is not
     *
     * @param product the product to store
     * @return
     */
    public static String secureUniqueShortName(Product product) {
        String name = product.getShortName();

        /**
         * now we want to be sure that this name is unique so if not, we add an index at the end
         */
        Product tmp = ProductAccessor.getProductByShortName(name);
        if (tmp != null && tmp.getProductId() != null && !tmp.getProductId().equals(product.getProductId())) {
            List<Product> listProds = ProductAccessor.getProductsByShortNameLike(name);
            Integer maxIndex = 0;
            if (listProds != null) {
                for (Product productToCompare : listProds) {
                    if (productToCompare.getShortName().matches(".*\\.\\d+$")) {
                        String sNo = productToCompare.getShortName().substring(productToCompare.getShortName().lastIndexOf(StringUtils.DOT) + 1);
                        try {
                            Integer no = Integer.parseInt(sNo);
                            if (no > maxIndex) {
                                maxIndex = no;
                            }
                        } catch (Exception e) {
                            logger.error(StringUtils.formatErrorMessage(e));
                        }
                    }
                }
            }
            if (name.lastIndexOf(StringUtils.DOT) > 0 && maxIndex > 0) {
                name = name.substring(0, name.lastIndexOf(StringUtils.DOT));
            }
            name += StringUtils.DOT + (maxIndex + 1);
        }
        if (name.length() > 512) {
            name = name.substring(0, 512);
        }
        return name;
    }

    public static List<Product> getProductAndSubProducts(Product product) {
        List<Product> products = new ArrayList();
        if (product != null) {
            products.add(product);
            if (product.getSubProducts() != null && !product.getSubProducts().isEmpty()) {
                for (Product subProduct : product.getSubProducts()) {
                    products.addAll(getProductAndSubProducts(subProduct));
                }
            }
        }
        return products;
    }

    public static List<Product> getProductAndParentProducts(Product product) {
        List<Product> products = new ArrayList();
        products.add(product);
        List<Product> parents = getParentProductList(product.getProductId());
        if (parents != null && !parents.isEmpty()) {
            products.addAll(parents);
        }
        return products;
    }
    public static final String GET_PRODUCT_REFERENCE = "getProductReference";

    /**
     * gets a produt reference
     *
     * @param p
     * @param type the reference type
     * @return
     */
    public static String getProductReference(Product p, String type) {
        String ret = "";
        ProductReference pr = (ProductReference) HibernateUtil.getObjectWithQuery("from ProductReference where product_id =" + p.getId() + " and reference_type='" + type + StringUtils.QUOTE);
        if (pr != null) {
            ret = pr.getValue();
        }
        return ret;
    }
    public static final String GET_PRODUCT_BY_ID = "getProductById";

    /**
     * gets a produt by id
     *
     * @param productId the product id
     * @return
     */
    public static Product getProductById(Integer productId) {
        return (Product) HibernateUtil.getObject(Product.class, productId);
    }
    public static final String GET_PRODUCT_BY_SHORT_NAME = "getProductByShortName";

    /**
     * gets a produt by short name
     *
     * @param shortName the product short name
     * @return
     */
    public static Product getProductByShortName(String shortName) {
        if (StringUtils.isEmptyString(shortName)) {
            return null;
        }
        return (Product) HibernateUtil.getObjectWithQuery("from Product p where p.shortName='" + shortName + StringUtils.QUOTE);
    }
    public static final String GET_PRODUCT_BY_TYPE_AND_ISSUER_AND_MATURITY = "getProductByTypeIssuerAndMaturity";

    /**
     * gets a produt by short name
     *
     * @param type the product type
     * @param issuer the issuer name
     * @param maturity the maturity ate
     * @return
     */
    public static Product getProductByTypeIssuerAndMaturity(String type, String issuer, Date maturity) {
        String query = "from Product p  inner join p.productCredits cc where p.productType='" + type + StringUtils.QUOTE
                + " and cc.issuer.shortName='" + issuer + "' and p.maturityDate='" + HibernateUtil.dateFormat.format(maturity) + StringUtils.QUOTE;
        List<Object[]> products = (List<Object[]>) HibernateUtil.getObjectsWithQuery(query);
        if (products != null && !products.isEmpty()) {
            Object[] objects = products.get(0);
            return (Product) objects[0];
        }
        return null;
    }
    public static final String GET_PRODUCT_BY_SHORT_NAME_LIKE = "getProductsByShortNameLike";

    /**
     * gets a produt by short name
     *
     * @param shortName the product short name
     * @return
     */
    public static List<Product> getProductsByShortNameLike(String shortName) {
        return getProductsWithWhereClause("p.shortName like '" + shortName + "%'");
    }
    public static final String GET_PRODUCT_ISSUER = "getProductIssuer";

    /**
     * gets the product issuer
     *
     * @param productId the product id
     * @return
     */
    public static LegalEntity getProductIssuer(Integer productId) {
        if (productId != null) {
            LegalEntity ret = (LegalEntity) HibernateUtil.getObjectWithQuery("select pc.issuer from ProductCredit pc where pc.product.productId=" + productId);
            if (ret != null) {
                return ret;
            } else {
                return (LegalEntity) HibernateUtil.getObjectWithQuery("select pe.issuer from ProductEquity pe where pe.product.productId=" + productId);
            }
        }
        return null;
    }
    public static final String GET_PRODUCT_PORTFOLIO = "getProductPortfolio";

    /**
     * gets the product portfolio
     *
     * @param productId the product id
     * @return
     */
    public static LegalEntity getProductPortfolio(Integer productId) {
        if (productId != null) {
            return (LegalEntity) HibernateUtil.getObjectWithQuery("select pe.portfolio from ProductEquity pe where pe.product.productId=" + productId);
        }
        return null;
    }

    /**
     * gets a product that has a product as underlying
     *
     * @param subProducts the list of underlying products
     * @return
     */
    public static Product getParentProduct(List<Product> subProducts) {
        if (subProducts.size() > 0) {
            Product subProduct = subProducts.get(0);
            List<Product> resultList = getParentProductList(subProduct.getId());
            for (Product product : resultList) {
                if (!product.getProductId().equals(subProduct.getProductId())) {
                    return product;
                }
            }
        }
        return null;
    }
    public static final String GET_PARENT_PRODUCT = "getParentProduct";

    /**
     * gets a product that has a product as underlying
     *
     * @param underlyings the list of underlying products
     * @return
     */
    public static Product getParentProduct(Product subProduct) {

        List<Product> resultList = getParentProductList(subProduct.getId());
        for (Product product : resultList) {
            if (!product.getProductId().equals(subProduct.getProductId())) {
                return product;
            }
        }
        return null;
    }
    public static final String GET_PARENT_PRODUCT_LIST = "getParentProductList";

    public static List<Product> getParentProductList(Integer subProductId) {
        List<Product> resultList = new ArrayList();
        if (subProductId != null) {
            List<Object[]> result = (List<Object[]>) HibernateUtil.getObjectsWithQuery("from Product as p inner join p.subProducts as sp where sp.productId=" + subProductId);
            for (Object[] objects : result) {
                resultList.add((Product) objects[0]);
            }
        }
        return resultList;
    }

    public static List<Product> getSuperProductList(Integer underlyingId) {
        List<Product> result = new ArrayList();
        if (underlyingId != null) {
            result = getProductsWithWhereClause("p.productId IN (select pu.pk.productId from ProductUnderlying as pu"
                    + " where pu.pk.underlyingId="
                    + underlyingId + ")");
        }
        return result;
    }
    public static final String GET_PRODUCTS_WITH_WHERE_CLAUSE = "getProductsWithWhereClause";

    /**
     * gets a list of products with a given hql where clause
     *
     * @param whereClause the where clause
     * @return
     */
    public static List<Product> getProductsWithWhereClause(String whereClause) {
        List<Product> resultList = new ArrayList();
        String query;
        try {
            query = "from Product p where " + whereClause + " order by p.shortName  ";
            resultList = (List<Product>) HibernateUtil.getObjectsWithQuery(query);
        } catch (HibernateException he) {
            logger.error("error HibernateException in a getProductsWithWhereClause  " + StringUtils.formatErrorMessage(he));
        }
        return resultList;
    }
    public static final String GET_PRODUCT_BY_REFERENCE_AND_VALUE = "getProductByReferenceAndValue";

    /**
     * gets a product with a given reference type (isin...)and its value
     *
     * @param referenceName the reference type
     * @param referenceValue the reference value
     * @return
     */
    public static Product getProductByReferenceAndValue(String referenceName, String referenceValue) {
        Object[] objects = (Object[]) HibernateUtil.getObjectWithQuery("from Product p join p.productReferences pr where pr.referenceType='" + referenceName + "' and pr.value='" + referenceValue + StringUtils.QUOTE);
        if (objects != null) {
            return (Product) objects[0];
        }
        return null;
    }
    public static final String GET_PRODUCT_BY_REFERENCE = "getProductByReference";

    /**
     * gets a product with any reference type (isin...) from its value
     *
     * @param referenceValue the reference value
     * @return
     */
    public static Product getProductByReference(String reference) {
        ProductReference productReference = (ProductReference) HibernateUtil.getObjectWithQuery("from ProductReference pr where pr.value='" + reference + StringUtils.QUOTE);
        if (productReference != null) {
            return getProductById(productReference.getProduct().getId());
        }
        return null;
    }
    public static final String GET_PRODUCTS_BY_TYPE_AND_CURRENCY = "getProductsByTypeAndCurrency";

    /**
     * gets a list of products from its type and currency
     *
     * @param type the product type
     * @param currency the product currency
     * @return
     */
    public static List<Product> getProductsByTypeAndCurrency(String type, String currency) {
        return getProductsWithWhereClause("p.productType ='" + type + "' and p.notionalCurrency='" + currency + StringUtils.QUOTE);
    }
    public static final String GET_PRODUCTS_BY_TYPE = "getProductsByType";

    /**
     * gets a list of products from its type and currency
     *
     * @param type the product type
     * @return
     */
    public static List<Product> getProductsByType(String type) {
        return getProductsWithWhereClause("p.productType ='" + type + StringUtils.QUOTE);
    }
    public static final String GET_PRODUCT_REFERENCES = "getProductReferences";

    /**
     * gets a product reference
     *
     * @param productId the product id
     * @return
     */
    public static List<ProductReference> getProductReferences(Integer productId) {
        return HibernateUtil.getObjectsWithQuery("from ProductReference pr where pr.product.productId=" + productId);
    }

    /**
     * gets the list of existing coupon adjustments
     *
     * @return
     */
    public static List getCouponAdjustments() {
        return DateUtils.DateAdjustment.getDateAdjustments();
    }
    public static final String GET_INTEREST_RATES_SOURCES = "getInterestRatesSources";

    /**
     * gets the list of existing interest rate sources in domain values
     *
     * @return
     */
    public static List<String> getInterestRatesSources() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_INTEREST_RATE_SOURCES);
    }
    public static final String GET_BOND_SENIORITIES = "getBondSeniorities";

    /**
     * gets the list of existing seniorities in domain values
     *
     * @return
     */
    public static List<String> getBondSeniorities() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_SENIORITIES);
    }
    public static final String GET_PAYOFF_FORMULA_FUNCTIONS = "getPayoffFormulaFunctions";

    /**
     * gets the list of existing payoff formula functionsin domain values
     *
     * @return
     */
    public static List<String> getPayoffFormulaFunctions() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_PAYOFF_FORMULA_FUNCTIONS);
    }
    public static final String GET_PAYOFF_CONDITIONS_FUNCTIONS = "getPayoffConditionFunctions";

    /**
     * gets the list of existing payoff condition functions in domain values
     *
     * @return
     */
    public static List<String> getPayoffConditionFunctions() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_PAYOFF_CONDITION_FUNCTIONS);
    }
    public static final String GET_PRODUCTS_WITH_FINDER = "getProductsWithFinder";

    /**
     * load products with some criteria to fill the asset finder
     *
     * @param productIds the product ids separated with comma
     * @param types the product types separated with comma
     * @param productRefType the product reference
     * @param productRefVal the product reference
     * @return
     */
    public static List<Object[]> getProductsWithFinder(String productIds, String types, String name, String productRefType, String productRefVal) {

        try {
            String query = "select p.product_id,p.short_name,pr.value,p.product_type from product p"
                    + " left join product_reference pr on pr.product_id=p.product_id and pr.reference_type='" + productRefType + StringUtils.QUOTE
                    + " where p.short_name is not null";

            if (productIds != null && !productIds.isEmpty()) /**
             * case when the productIds is not empty
             */
            {
                query = query + " and p.product_id in (" + productIds + ")";
            }

            if (types != null && !types.isEmpty()) /**
             * case when the product_type is not empty
             */
            {
                query = query + " and p.product_type in (" + types + ")";
            }
            if (!name.isEmpty()) /**
             * case when the name is not empty
             */
            {
                query = query + " and upper(p.short_name) like '" + name.toUpperCase() + "%'";
            }
            if (productRefType != null && productRefVal != null && !productRefType.isEmpty() && !productRefVal.isEmpty()) /**
             * case when the productRefType and productRefVal is not empty
             */
            {
                query = query + " and pr.value like '" + productRefVal + "%'";
            }
            query = query + " order by p.short_name";
            List<Object[]> list = HibernateUtil.getListWithSQLQuery(query);
            return list;

        } catch (HibernateException he) {
            logger.error("error HibernateException in a loadProductsWithFinder  " + StringUtils.formatErrorMessage(he));
        }

        return new ArrayList();
    }
    public static final String DELETE_PRODUCT = "deleteProduct";

    /**
     * deletes a product with its dependencies
     *
     * @param product the product
     * @return
     */
    public static boolean deleteProduct(Product product) {

        if (product != null) {
            BigInteger lineNumber = (BigInteger) HibernateUtil.getFieldWithSQLQuery("select count(*) from trade where product_id=" + product.getProductId());
            if (lineNumber.intValue() > 0) {
                logger.error("Trying to delete a product with existing trades");
                return false;
            }
            lineNumber = (BigInteger) HibernateUtil.getFieldWithSQLQuery("select count(*) from product_underlying where underlying_id=" + product.getProductId());
            if (lineNumber.intValue() > 0) {
                List<Object[]> list = HibernateUtil.getObjectsWithQuery("select p.shortName,p.productId from Product p inner join p.underlyingProducts up where up.productId=" + product.getProductId());
                String slist = StringUtils.EMPTY_STRING;
                for (Object[] fields : list) {
                    for (Object field : fields) {
                        if (field != null) {
                            slist += field.toString() + "/";
                        }
                    }
                    slist += StringUtils.COMMA;
                }
                logger.error("Trying to delete a product being other products underlying :" + slist);
                return false;
            }

            product = (Product) HibernateUtil.getObject(Product.class, product.getProductId());

            if (product != null) {

                if (product.getScheduler() != null) {
                    Integer schedulerId = product.getScheduler().getSchedulerId();
                    product.setScheduler(null);
                    product = (Product) HibernateUtil.storeAndReturnObject(product);
                    HibernateUtil.executeQuery("delete from Scheduler where scheduler_id=" + schedulerId);
                }

                HibernateUtil.executeQuery("delete from ProductReference where product_id=" + product.getId());

                HibernateUtil.executeQuery("delete from MarketQuote where product_id=" + product.getId());

                HibernateUtil.executeQuery("delete from ScheduleLine where product_id=" + product.getId());

                HibernateUtil.executeQuery("delete from ContractEvent where product_id=" + product.getId());

                List<ProductHistoricalMeasure> historicals = ProductHistoricalAccessor.getHistoricals(product.getId());
                for (ProductHistoricalMeasure historical : historicals) {
                    for (ProductHistoricalMeasureValue historicalValue : historical.getProductHistoricalMeasuresValueCollection()) {
                        HibernateUtil.deleteObject(historicalValue);
                    }
                    HibernateUtil.deleteObject(historical);
                }

                FlowAccessor.deleteFlowsByProduct(product.getProductId());

                List<Position> positions = HibernateUtil.getObjectsWithQuery("from Position p where p.product.productId=" + product.getProductId());
                for (Position position : positions) {
                    PositionBuilder.deletePosition(position.getId());
                }

                for (Product component : product.getSubProducts()) {
                    boolean ret = deleteProduct(component);
                    if (!ret) {
                        return ret;
                    }
                }
                HibernateUtil.executeSQLQuery("delete from product_subproduct where subproduct_id=" + product.getProductId());
                product.setSubProducts(new HashSet());
                HibernateUtil.deleteObject(product);

            }
        }
        return true;
    }
    public static final String DELETE_PRODUCT_BY_ID = "deleteProductById";

    /**
     * deletes a product with its dependencies
     *
     * @param productId id of the the product
     */
    public static void deleteProductById(Integer productId) {
        Product product = (Product) HibernateUtil.getObject(Product.class, productId);
        deleteProduct(product);
    }

    /**
     * gets the following CDS IMM dates during 5Years
     *
     * @return
     */
    public static List<Date> getCdsImmDates() {

        Date firstDate = new Date(109, 1, 1);
        Date current = new Date();
        long dateDiff = DateUtils.dateDiff(firstDate, current);
        int nbYears = Math.round(dateDiff / 365) + 20;
        return getCdsImmDates(firstDate, nbYears);
    }

    /**
     * gets CDS IMM dates
     *
     * @return
     */
    public static List<Date> getCdsImmDates(Date startDate, int nbYears) {
        List<Date> immDateList = new ArrayList();
        cdsImmDate[] immDates = ProductConst.cdsImmDate.values();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int year = calendar.get(Calendar.YEAR) - 1900;
        int count = 0;
        for (int yearPlus = 0; yearPlus <= nbYears; yearPlus++) {
            for (cdsImmDate cdsImmDate_ : immDates) {
                Date date = cdsImmDate_.date;
                date = DateUtils.addYear(date, year + yearPlus);
                if (count < nbYears * 4) {
                    immDateList.add(date);
                    count++;
                }
            }
        }
        return immDateList;
    }

    public static Product cloneProduct(Product toClone) {
        Product cloned = null;
        try {
            cloned = JpaCloner.clone(toClone, "scheduler");
            if (toClone.getProductCredit() != null) {
                cloned.setProductCredit(toClone.getProductCredit().clone());
                cloned.getProductCredit().setProduct(cloned);
            }
            if (toClone.getProductEquity() != null) {
                cloned.setProductEquity(toClone.getProductEquity().clone());
                cloned.getProductEquity().setProduct(cloned);
            }
            if (toClone.getProductRate() != null) {
                cloned.setProductRate(toClone.getProductRate().clone());
                cloned.getProductRate().setProduct(cloned);
            }
            if (toClone.getProductCurve() != null) {
                cloned.setProductCurve(toClone.getProductCurve().clone());
                cloned.getProductCurve().setProduct(cloned);
            }
            if (toClone.getProductSingleTraded() != null) {
                cloned.setProductSingleTraded(toClone.getProductSingleTraded().clone());
                cloned.getProductSingleTraded().setProduct(cloned);
            }
            if (toClone.getScheduler() != null) {
                cloned.setScheduler(toClone.getScheduler().clone());
            }
            for (Product component : toClone.getSubProducts()) {
                cloned.addSubProduct(cloneProduct(component));
            }
            for (Product underlying : toClone.loadUnderlyings()) {
                cloned.addUnderlying(underlying);
            }
            cloned.setProductEvents(new HashSet());
            cloned.setProductId(null);

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return cloned;
    }

    /**
     * gets the multiplier for diplay from the quotation type
     *
     * @param quotationType the quotation type of the product
     * @return
     */
    public static double getMultiplierFromQuotationType(String quotationType) {
        return MarketQuote.QuotationType.getMult(quotationType).doubleValue();
    }

    public static void storeProductEvent(ProductEvent productEvent, Session session) {
        HibernateUtil.storeObject(productEvent, session);
    }
    /**
     * gets Product events list for given product
     *
     */
    public static final String GET_PRODUCT_EVENTS_BY_PRODUCT_ID = "getProductEventsByProductId";

    public static List<ProductEvent> getProductEventsByProductId(Integer productId) {
        List<ProductEvent> result;
        result = (List<ProductEvent>) HibernateUtil.getObjectsWithQueryWithCache("from ProductEvent WHERE product_id=" + productId);
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    public static Product buildIndexUnderlyingCDSProduct(Product _product, LegalEntity issuer) {
        Product underlying = new Product();
        underlying.setNotionalMultiplier(BigDecimal.ONE);
        underlying.setNotionalCurrency(_product.getNotionalCurrency());
        underlying.setProductType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        underlying.setMaturityDate(_product.getMaturityDate());
        underlying.setStartDate(_product.getStartDate());
        underlying.setIsAsset(Boolean.TRUE);
        underlying.setQuantityType(Trade.QuantityType.NOTIONAL.name());
        underlying.setQuotationType(MarketQuote.QuotationType.SPREAD.getName());
        ProductCredit _creditProduct = new ProductCredit();
        _creditProduct.setProduct(_product);
        _creditProduct.setIssuer(issuer);
        underlying.setProductCredit(_creditProduct);
        return underlying;
    }
    public static final String FIND_CDS_PRODUCT_BY_ISSUER_AND_MATURITY_DATE = "findCDSProductByIssuerAndMaturityDate";

    public static Product findCDSProductByIssuerAndMaturityDate(LegalEntity issuer, Date maturity) {

        List<Product> productList = getProductsWithWhereClause("p.productType ='"
                + ProductTypeUtil.ProductType.CDS_PRODUCT.getName() + "' AND p.productId IN (select pc.product.productId "
                + "from ProductCredit pc where pc.issuer.legalEntityId=" + issuer.getLegalEntityId()
                + ") AND p.maturityDate='" + HibernateUtil.dateFormat.format(maturity) + StringUtils.QUOTE);

        if (!productList.isEmpty()) {
            return productList.get(0);
        }
        return null;
    }

    private static void saveOrUpdateProductUnderlyings(Product product, Session session) throws HibernateException {
        List<ProductUnderlying> resultList;

        //only stores underlyings when they are new
        if (product.getUnderlyingProducts() != null && !product.getUnderlyingProducts().isEmpty()) {
            for (ProductUnderlying underlying : product.getUnderlyingProducts()) {
                if (underlying.getUnderlying().getProductId() == null) {
                    storeProduct(underlying.getUnderlying(), session);
                }
            }
        }
        String query = "from ProductUnderlying p where p.pk.productId=" + product.getProductId();
        try {
            resultList = (List<ProductUnderlying>) HibernateUtil.getObjectsWithQuery(query);
            if (!product.getUnderlyingProducts().isEmpty()) {
                for (ProductUnderlying underlying : product.getUnderlyingProducts()) {
                    if (resultList != null && resultList.contains(underlying)) {
                        resultList.remove(underlying);
                    } else {
                        underlying.setPk(new ProductUnderlyingPK(product.getProductId(), underlying.getUnderlying().getProductId()));
                    }
                    session.saveOrUpdate(underlying);

                }
                if (resultList != null && !resultList.isEmpty()) {
                    for (ProductUnderlying productUnderlying : resultList) {
                        HibernateUtil.deleteObject(productUnderlying);
                    }
                }
            } else {
                if (resultList != null && !resultList.isEmpty()) {
                    for (ProductUnderlying productUnderlying : resultList) {
                        HibernateUtil.deleteObject(productUnderlying);
                    }
                }
            }

        } catch (HibernateException he) {
            logger.error("error HibernateException in a saveOrUpdateProductUnderlying  " + StringUtils.formatErrorMessage(he));
        }
    }
    public static final String GET_SETTLEMENT_DATE = "getSettlementDate";

    public static Date getSettlementDate(Product product, Date startDate) {
        Date settleDate = startDate;
        if (settleDate != null && product != null && product.getSettlementDelay() != null) {
            List<HolidayCalendar> cals = new ArrayList();
            if (product.getCalendar() != null) {
                HolidayCalendar intctpCal = CalendarAccessor.getCalendarByCode(product.getCalendar());
                cals.add(intctpCal);
            }
            if (product.getProductForex() != null) {
                if (product.getProductForex().getCurrency1() != null && product.getProductForex().getCurrency1().getCalendar() != null) {
                    HolidayCalendar cur1cal = CalendarAccessor.getCalendarByCode(product.getProductForex().getCurrency1().getCalendar());
                    cals.add(cur1cal);
                }
                if (product.getProductForex().getCurrency2() != null && product.getProductForex().getCurrency2().getCalendar() != null) {
                    HolidayCalendar cur2cal = CalendarAccessor.getCalendarByCode(product.getProductForex().getCurrency2().getCalendar());
                    cals.add(cur2cal);
                }
                if (product.getProductForex().getIsPaymentAtEnd()) {
                    return product.getMaturityDate();
                }
            }
            HolidayCalendar cal = new HolidayCalendar();
            for (HolidayCalendar cal_ : cals) {
                cal = CalendarAccessor.mergeCalendars(cal, cal_);
            }
            Short delay = product.getSettlementDelay();
            settleDate = DateUtils.addOpenDay(settleDate, delay, cal);
        }

        return settleDate;
    }

    public static Product getAnyProductByType(String productType) {
        // used by unit tests
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(p.productId) from Product p where p.productType='" + productType + StringUtils.QUOTE);
        return getProductById(id);
    }
}
