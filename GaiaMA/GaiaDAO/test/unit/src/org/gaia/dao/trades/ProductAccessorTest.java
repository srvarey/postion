package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductAccessorTest extends AbstractTest{


    /**
     * Test of saveContractEvent method, of class ProductAccessor.
     */
    @Test
    public void testSaveContractEvent() {
        System.out.println("saveContractEvent");
        ContractEvent event = new ContractEvent();
        event.setUnwindAmount(BigDecimal.ZERO);
        event.setContractEvent("Test");
        event.setNegociationDate(DateUtils.getDate());
        event.setPaymentAmount(BigDecimal.ZERO);
        event.setPaymentCurrency("XXX");
        event.setPaymentDate(DateUtils.getDate());
        event.setUnwindFactor(BigDecimal.ZERO);
        event.setValueDate(DateUtils.getDate());
        Integer result = ProductAccessor.saveContractEvent(event);
        assertNotNull("event not saved", result);
    }

    /**
     * Test of getContractEventById method, of class ProductAccessor.
     */
    @Test
    public void testGetContractEventById() {
        System.out.println("getContractEventById");
        Integer contractEventId =(Integer)HibernateUtil.getObjectWithQuery("select max(ce.contractEventId) from ContractEvent ce");
        ContractEvent result = ProductAccessor.getContractEventById(contractEventId);
        assertNotNull("contract event not found",result);
    }

    /**
     * Test of storeProduct method, of class ProductAccessor.
     */
    @Test
    public void testStoreProduct_Product() {
        System.out.println("storeProduct");
        Product initalProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Product newProduct=ProductAccessor.cloneProduct(initalProduct);
        Product result = ProductAccessor.storeProduct(newProduct);
        assertNotNull("product not stored", result);
        //clean
        HibernateUtil.deleteObject(result);
    }

    /**
     * Test of storeProduct method, of class ProductAccessor.
     */
    @Test
    public void testStoreProduct_Product_Session() {
        System.out.println("storeProduct");
        Product initalProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Product newProduct=ProductAccessor.cloneProduct(initalProduct);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Product result = ProductAccessor.storeProduct(newProduct, session);
        session.close();
        assertNotNull("product not stored", result);
        //clean
        HibernateUtil.deleteObject(result);
    }

    /**
     * Test of controlProduct method, of class ProductAccessor.
     */
    @Test
    public void testControlProduct() {
        System.out.println("controlProduct");
        Product product = new Product();
        boolean result = ProductAccessor.controlProduct(product);
        assertFalse("Control should not be ok", result);
    }

    /**
     * Test of manageShortName method, of class ProductAccessor.
     */
    @Test
    public void testManageShortName() {
        System.out.println("manageShortName");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        product.setShortName(null);
        ProductAccessor.manageShortName(product);
        assertNotNull("product short name not managed", product.getShortName());
    }

    /**
     * Test of getDefaultProductShortName method, of class ProductAccessor.
     */
    @Test
    public void testGetDefaultProductShortName() {
        System.out.println("getDefaultProductShortName");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        String result = ProductAccessor.getDefaultProductShortName(product);
        assertNotNull("product default short name not generated", result);
    }

    /**
     * Test of secureUniqueShortName method, of class ProductAccessor.
     */
    @Test
    public void testSecureUniqueShortName() {
        System.out.println("secureUniqueShortName");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        String result = ProductAccessor.secureUniqueShortName(product);
        assertNotNull("product default short name not secured", result);
    }

    /**
     * Test of getProductAndSubProducts method, of class ProductAccessor.
     */
    @Test
    public void testGetProductAndSubProducts() {
        System.out.println("getProductAndSubProducts");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List result = ProductAccessor.getProductAndSubProducts(product);
        assertEquals("IRS should have 3 products", 3, result.size());
    }

    /**
     * Test of getProductAndParentProducts method, of class ProductAccessor.
     */
    @Test
    public void testGetProductAndParentProducts() {
        System.out.println("getProductAndParentProducts");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Product subProduct = product.getSubProducts().iterator().next();
        List result = ProductAccessor.getProductAndParentProducts(subProduct);
        assertEquals("leg should have 1 parent", 2, result.size());
    }

    /**
     * Test of getProductReference method, of class ProductAccessor.
     */
    @Test
    public void testGetProductReference() {
        System.out.println("getProductReference");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pr.productReferenceId) from ProductReference pr");
        ProductReference reference=(ProductReference) HibernateUtil.getObject(ProductReference.class, id);
        String result = ProductAccessor.getProductReference(reference.getProduct(), reference.getReferenceType());
        assertEquals("product reference not found", reference.getValue(), result);
    }

    /**
     * Test of getProductById method, of class ProductAccessor.
     */
    @Test
    public void testGetProductById() {
        System.out.println("getProductById");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        Product result = ProductAccessor.getProductById(product.getProductId());
        assertEquals("product "+product.getProductId()+" not found",product, result);
    }

    /**
     * Test of getProductByShortName method, of class ProductAccessor.
     */
    @Test
    public void testGetProductByShortName() {
        System.out.println("getProductByShortName");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        Product result = ProductAccessor.getProductByShortName(product.getShortName());
        assertEquals("product "+product.getShortName()+" not found",product, result);
    }

    /**
     * Test of getProductIssuer method, of class ProductAccessor.
     */
    @Test
    public void testGetProductIssuer() {
        System.out.println("getProductIssuer");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        if (product!=null){
            LegalEntity result = ProductAccessor.getProductIssuer(product.getProductId());
            assertNotNull("could not found the issuer", result);
        }
    }

    /**
     * Test of getProductByTypeIssuerAndMaturity method, of class ProductAccessor.
     */
    @Test
    public void testGetProductByTypeIssuerAndMaturity() {
        System.out.println("getProductByTypeIssuerAndMaturity");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        if (product!=null){
            LegalEntity issuer=ProductAccessor.getProductIssuer(product.getProductId());
            Product result = ProductAccessor.getProductByTypeIssuerAndMaturity(product.getProductType(), issuer.getShortName(), product.getMaturityDate());
            assertEquals("product "+product.getShortName()+" not found",product, result);
        }
    }

    /**
     * Test of getProductsByShortNameLike method, of class ProductAccessor.
     */
    @Test
    public void testGetProductsByShortNameLike() {
        System.out.println("getProductsByShortNameLike");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        if (product!=null){
            List result = ProductAccessor.getProductsByShortNameLike(product.getShortName().substring(0,product.getShortName().length()-1));
            assertFalse("product not found",result.isEmpty());
        }
    }

    /**
     * Test of getParentProduct method, of class ProductAccessor.
     */
    @Test
    public void testGetParentProduct_List() {
        System.out.println("getParentProduct");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List<Product> subProducts = new ArrayList();
        subProducts.add(product.getFirstLeg());
        Product result = ProductAccessor.getParentProduct(subProducts);
        assertEquals("could not find leg parent product",product, result);
    }

    /**
     * Test of getParentProduct method, of class ProductAccessor.
     */
    @Test
    public void testGetParentProduct_Product() {
        System.out.println("getParentProduct");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Product result = ProductAccessor.getParentProduct(product.getSubProducts().iterator().next());
        assertEquals("could not find leg parent product",product, result);
    }

    /**
     * Test of getParentProductList method, of class ProductAccessor.
     */
    @Test
    public void testGetParentProductList() {
        System.out.println("getParentProductList");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List result = ProductAccessor.getParentProductList(product.getSubProducts().iterator().next().getProductId());
        assertNotNull("could not find leg parent product",result);
        assertFalse("could not find leg parent product", result.isEmpty());
    }

    /**
     * Test of getSuperProductList method, of class ProductAccessor.
     */
    @Test
    public void testGetSuperProductList() {
        System.out.println("getSuperProductList");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_INDEX.getName());
        Product underlying=product.getUnderlying();
        List result = ProductAccessor.getSuperProductList(underlying.getProductId());
        assertNotNull("could not find leg super product",result);
        assertFalse("could not find leg super product", result.isEmpty());
    }

    /**
     * Test of getProductsWithWhereClause method, of class ProductAccessor.
     */
    @Test
    public void testGetProductsWithWhereClause() {
        System.out.println("getProductsWithWhereClause");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        String whereClause = "product_id="+product.getProductId();
        List result = ProductAccessor.getProductsWithWhereClause(whereClause);
        assertNotNull("product not found "+product.getProductId(), result);
    }

    /**
     * Test of getProductByReferenceAndValue method, of class ProductAccessor.
     */
    @Test
    public void testGetProductByReferenceAndValue() {
        System.out.println("getProductByReferenceAndValue");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pr.productReferenceId) from ProductReference pr");
        ProductReference reference=(ProductReference) HibernateUtil.getObject(ProductReference.class, id);
        Product result = ProductAccessor.getProductByReferenceAndValue(reference.getReferenceType(), reference.getValue());
        assertEquals("product reference not found",reference.getProduct(), result);
    }

    /**
     * Test of getProductByReference method, of class ProductAccessor.
     */
    @Test
    public void testGetProductByReference() {
        System.out.println("getProductByReference");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pr.productReferenceId) from ProductReference pr where pr.product.productId is not null");
        ProductReference reference=(ProductReference) HibernateUtil.getObject(ProductReference.class, id);
        Product result = ProductAccessor.getProductByReference(reference.getValue());
        assertNotNull("product not found with reference "+reference.getValue(), result);
    }

    /**
     * Test of getProductsByTypeAndCurrency method, of class ProductAccessor.
     */
    @Test
    public void testGetProductsByTypeAndCurrency() {
        System.out.println("getProductsByTypeAndCurrency");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        List result = ProductAccessor.getProductsByTypeAndCurrency(product.getProductType(), product.getNotionalCurrency());
        assertNotNull("product not found",result);
        assertFalse("product not found",result.isEmpty());
    }

    /**
     * Test of getProductsByType method, of class ProductAccessor.
     */
    @Test
    public void testGetProductsByType() {
        System.out.println("getProductsByType");
        String type=(String)HibernateUtil.getObjectWithQuery("select min(p.productType) from Product p");
        List result = ProductAccessor.getProductsByType(type);
        assertNotNull("product not found",result);
        assertFalse("product not found",result.isEmpty());
    }

    /**
     * Test of getProductReferences method, of class ProductAccessor.
     */
    @Test
    public void testGetProductReferences() {
        System.out.println("getProductReferences");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pr.product.productId) from ProductReference pr");
        List result = ProductAccessor.getProductReferences(id);
        assertNotNull("product references not found",result);
        assertFalse("product references not found",result.isEmpty());
    }

    /**
     * Test of getCouponAdjustments method, of class ProductAccessor.
     */
    @Test
    public void testGetCouponAdjustments() {
        System.out.println("getCouponAdjustments");
        List result = ProductAccessor.getCouponAdjustments();
        assertNotNull("missing adjustments", result);
    }

    /**
     * Test of getInterestRatesSources method, of class ProductAccessor.
     */
    @Test
    public void testGetInterestRatesSources() {
        System.out.println("getInterestRatesSources");
        List result = ProductAccessor.getInterestRatesSources();
        assertNotNull("missing rate sources", result);
    }

    /**
     * Test of getBondSeniorities method, of class ProductAccessor.
     */
    @Test
    public void testGetBondSeniorities() {
        System.out.println("getBondSeniorities");
        List result = ProductAccessor.getBondSeniorities();
        assertNotNull("missing bond seniorities", result);
    }

    /**
     * Test of getPayoffFormulaFunctions method, of class ProductAccessor.
     */
    @Test
    public void testGetPayoffFormulaFunctions() {
        System.out.println("getPayoffFormulaFunctions");
        List result = ProductAccessor.getPayoffFormulaFunctions();
        assertNotNull("missing formula functions", result);
    }

    /**
     * Test of getPayoffConditionFunctions method, of class ProductAccessor.
     */
    @Test
    public void testGetPayoffConditionFunctions() {
        System.out.println("getPayoffConditionFunctions");
        List result = ProductAccessor.getPayoffConditionFunctions();
        assertNotNull("missing payoff conditions", result);
    }

    /**
     * Test of getProductsWithFinder method, of class ProductAccessor.
     */
    @Test
    public void testLoadProductsWithFinder() {
        System.out.println("loadProductsWithFinder");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        List result = ProductAccessor.getProductsWithFinder(product.getProductId().toString(), StringUtils.QUOTE+product.getProductType()+StringUtils.QUOTE, product.getShortName(), null, null);
        assertNotNull("missing result", result);
        assertFalse("missing result", result.isEmpty());
    }

    /**
     * Test of deleteProduct method, of class ProductAccessor.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        Product initialProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        Product product = ProductAccessor.cloneProduct(initialProduct);
        ProductAccessor.storeProduct(product);
        ProductAccessor.deleteProduct(product);
        product=ProductAccessor.getProductById(product.getProductId());
        assertNull("product not deleted", product);
    }

    /**
     * Test of deleteProductById method, of class ProductAccessor.
     */
    @Test
    public void testDeleteProductById() {
        System.out.println("deleteProductById");
        Product initialProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        Product product = ProductAccessor.cloneProduct(initialProduct);
        ProductAccessor.storeProduct(product);
        ProductAccessor.deleteProductById(product.getProductId());
        product=ProductAccessor.getProductById(product.getProductId());
        assertNull("product not deleted", product);
    }

    /**
     * Test of getCdsImmDates method, of class ProductAccessor.
     */
    @Test
    public void testGetCdsImmDates_0args() {
        System.out.println("getCdsImmDates");
        List result = ProductAccessor.getCdsImmDates();
        assertNotNull("missing imm dates", result);
        assertFalse("missing imm dates", result.isEmpty());
    }

    /**
     * Test of getCdsImmDates method, of class ProductAccessor.
     */
    @Test
    public void testGetCdsImmDates_Date_int() {
        System.out.println("getCdsImmDates");
        List result = ProductAccessor.getCdsImmDates(DateUtils.getDate(), 5);
        assertNotNull("missing imm dates", result);
        assertFalse("missing imm dates", result.isEmpty());
    }

    /**
     * Test of cloneProduct method, of class ProductAccessor.
     */
    @Test
    public void testCloneProduct() {
        System.out.println("cloneProduct");
        Product toClone = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Product result = ProductAccessor.cloneProduct(toClone);
        ProductAccessor.storeProduct(result);
        assertNotNull("missing cloned products", result);
        result.setShortName(toClone.getShortName());
        result.setProductId(toClone.getProductId());
        assertEquals(toClone, result);
        // clean
        ProductAccessor.deleteProduct(result);
    }

    /**
     * Test of getMultiplierFromQuotationType method, of class ProductAccessor.
     */
    @Test
    public void testGetMultiplierFromQuotationType() {
        System.out.println("getMultiplierFromQuotationType");
        double result = ProductAccessor.getMultiplierFromQuotationType(MarketQuote.QuotationType.RATE.getName());
        assertTrue("Wrong multiplier", result== MarketQuote.QuotationType.RATE.getMult().doubleValue());
    }

    /**
     * Test of storeProductEvent method, of class ProductAccessor.
     */
    @Test
    public void testStoreProductEvent() {
        System.out.println("storeProductEvent");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName());
        String type="test"+Calendar.getInstance().getTimeInMillis();
        ProductEvent productEvent = new ProductEvent(product, DateUtils.getDate(), type, null, 0);
        Session session = HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        ProductAccessor.storeProductEvent(productEvent, session);
        transaction.commit();
        session.close();
        productEvent=(ProductEvent)HibernateUtil.getObjectWithQuery("from ProductEvent pe where pe.eventType='"+type+StringUtils.QUOTE);
        assertNotNull("Event not stored",productEvent);
        //clean
        HibernateUtil.deleteObject(productEvent);
    }

    /**
     * Test of getProductEventsByProductId method, of class ProductAccessor.
     */
    @Test
    public void testLoadProductEventsByProductId() {
        System.out.println("loadProductEventsByProductId");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(p.productId) from Product p inner join p.productEvents pe");
        List result = ProductAccessor.getProductEventsByProductId(id);
        assertNotNull("missing events", result);
        assertFalse("missing events", result.isEmpty());
    }

    /**
     * Test of buildIndexUnderlyingCDSProduct method, of class ProductAccessor.
     */
    @Test
    public void testBuildCDSProduct() {
        System.out.println("buildCDSProduct");
        Product index = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_INDEX.getName());
        if(index!=null){
            Product underlying =index.getUnderlying();
            if (underlying!=null){
                LegalEntity issuer=ProductAccessor.getProductIssuer(underlying.getProductId());
                Product result = ProductAccessor.buildIndexUnderlyingCDSProduct(index, issuer);
                assertNotNull("CDS not created",result);
                assertEquals("CDS uncompletly created",underlying.getProductType(), result.getProductType());
                assertEquals("CDS uncompletly created",underlying.getMaturityDate(), result.getMaturityDate());
            }
        }
    }

    /**
     * Test of findCDSProductByIssuerAndMaturityDate method, of class ProductAccessor.
     */
    @Test
    public void testFindProductCreditByIssuerAndMaturityDate() {
        System.out.println("findProductCreditByIssuerAndMaturityDate");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pc.productCreditId) from ProductCredit pc inner join pc.product p where p.productType='"+ProductTypeUtil.ProductType.CDS_PRODUCT.name+StringUtils.QUOTE);
        ProductCredit credit=(ProductCredit) HibernateUtil.getObject(ProductCredit.class, id);
        LegalEntity issuer=ProductAccessor.getProductIssuer(credit.getProduct().getProductId());
        Product result = ProductAccessor.findCDSProductByIssuerAndMaturityDate(issuer, credit.getProduct().getMaturityDate());
        assertEquals("cds product not found",credit.getProduct(), result);
    }

    /**
     * Test of getSettlementDate method, of class ProductAccessor.
     */
    @Test
    public void testGetSettlementDate() {
        System.out.println("getSettlementDate");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CDS_INDEX.getName());
        Date result = ProductAccessor.getSettlementDate(product, DateUtils.getDate());
        assertNotNull("Wrong settlement date",result);
    }
}