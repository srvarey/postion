package org.gaia.dao.trades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.PricingFilter;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeAttribute;
import org.gaia.domain.trades.TradeGroup;
import org.gaia.domain.utils.HibernateUtil;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class TradeAccessorTest extends AbstractTest{


//    /**
//     * Test of storeTrade method, of class TradeAccessor.
//     */
    @Test
    public void testGetBondTrade() {
        System.out.println("getBondTrade");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        assertNotNull("fail to get bond trade "+trade.getTradeId(),trade);
    }
    /**
     * Test of storeTrade method, of class TradeAccessor.
     */
    @Test
    public void testGetIRSTrade() {
        System.out.println("getIRSTrade");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());

        assertNotNull("fail to get IRS trade "+trade.getTradeId(),trade);
    }
    /**
     * Test of storeTrade method, of class TradeAccessor.
     */
    @Test
    public void testStoreAssetTrade() {
        System.out.println("storeAssetTrade");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        Trade result = TradeAccessor.storeTrade(trade);
        assertEquals("fail to store trade "+trade.getTradeId(), trade, result);
    }
 /**
     * Test of storeTrade method, of class TradeAccessor.
     */
    @Test
    public void testStoreIRSTrade() {
        System.out.println("storeIRSTrade");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Trade result = TradeAccessor.storeTrade(trade);
        assertEquals("fail to store trade "+trade.getTradeId(),trade, result);
    }
    /**
     * Test of generateImpacts method, of class TradeAccessor.
     * not for the moment : call to agent pool
     */
//    @Test
//    public void testGenerateImpacts() {
//        System.out.println("generateImpacts");
//        Trade trade = TradeAccessor.getBondTrade();
//        TradeAccessor.generateImpacts(trade, null);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of generateMirrorTrade method, of class TradeAccessor.
     */
    @Test
    public void testGenerateMirrorTrade_Trade() {
        System.out.println("generateMirrorTrade");
        List<String> ctps=LegalEntityAccessor.getInternalCounterparties();
        Trade initialTrade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        if (ctps.size()>0){
            LegalEntity ctp=LegalEntityAccessor.getLegalEntityByShortName(ctps.get(0));
            Trade trade=initialTrade.clone();
            trade.setCounterparty(ctp);
            trade.setTradeGroups(new HashSet());
            trade=(Trade)HibernateUtil.storeAndReturnObject(trade);
            TradeAccessor.generateMirrorTrade(trade);
            assertEquals("mirror trade not found.",trade.getTradeGroups().size(),1);
            // clean
            Object[] trades=trade.getTradeGroups().iterator().next().getTrades().toArray();
            HibernateUtil.deleteObject(trade.getTradeGroups().iterator().next());
            for (Object t : trades){
                 HibernateUtil.deleteObject((Trade)t);
            }

        }
    }

    /**
     * Test of getTradeById method, of class TradeAccessor.
     */
    @Test
    public void testGetTradeById() {
        System.out.println("getTradeById");
        Integer tradeId = (Integer)HibernateUtil.getObjectWithQuery("select min(t.tradeId) from Trade t inner join t.product p where p.productType='Bond'");
        if (tradeId!=null){
            Trade result = TradeAccessor.getTradeById(tradeId);
            assertNotNull("Trade not found with id"+tradeId,result);
        }
    }

    /**
     * Test of getTradesWithWhereClause method, of class TradeAccessor.
     */
    @Test
    public void testGetTradesWithWhereClause() {
        System.out.println("getTradesWithWhereClause");
        Integer id = (Integer)HibernateUtil.getObjectWithQuery("select min(t.tradeId) from Trade t");
        String whereClause = " trade_id="+id;
        List result = TradeAccessor.getTradesWithWhereClause(whereClause);
        assertNotNull("Trade not found"+id,result);
    }

    /**
     * Test of getTradesByProductId method, of class TradeAccessor.
     */
    @Test
    public void testGetTradesByProductId() {
        System.out.println("getTradesByProductId");
        Integer id = (Integer)HibernateUtil.getObjectWithQuery("select min(p.productId) from Trade t inner join t.product p");
        List result = TradeAccessor.getTradesByProductId(id);
        assertNotNull("Trade not found with product id "+id,result);
    }

    /**
     * Test of getDefaultCCP method, of class TradeAccessor.
     */
    @Test
    public void testGetDefaultCCP() {
        System.out.println("getDefaultCCP");
        Integer accountId =(Integer) HibernateUtil.getFieldWithSQLQuery("select a.account_id from bo_account a , filter_group g where a.account_manager_id=g.linked_object_id and linked_object_class='org.gaia.domain.legalEntity.LegalEntity'");
        BoAccount account = (BoAccount)HibernateUtil.getObjectWithQuery("from BoAccount a where a.accountId="+accountId);
        if (account!=null){


            Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
            trade.setInternalCounterparty(account.getClient());
            LegalEntity result = TradeAccessor.getDefaultCCP(trade);
            assertNotNull("The CCP was not found on account "+account.getAccountCode(),result);
        }
    }

    /**
     * Test of getTradesWithFilter method, of class TradeAccessor.
     */
    @Test
    public void testGetTradesWithFilter() {
        System.out.println("getTradesWithFilter");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        FilterCriteria criteria=new FilterCriteria("tradeId", trade.getTradeId().toString(), null, "getTradeId", Integer.class.getName(), null);
        Filter filter = new Filter();
        filter.getFilterCriteria().add(criteria);
        Collection<TemplateColumnItem> sortingOrder = null;
        List result = TradeAccessor.getTradesWithFilter(filter, sortingOrder);
        assertNotNull("Trade not found "+trade.getTradeId(),result);
    }

    /**
     * Test of getStatusList method, of class TradeAccessor.
     */
    @Test
    public void testGetStatusList() {
        System.out.println("getStatusList");
        List result = TradeAccessor.getStatusList();
        assertNotNull(result);
        assertFalse("Empty status list.",result.size()==0);
    }

    /**
     * Test of getLifeCycleStatusList method, of class TradeAccessor.
     */
    @Test
    public void testGetLifeCycleStatusList() {
        System.out.println("getLifeCycleStatusList");
        List result = TradeAccessor.getLifeCycleStatusList();
        assertNotNull(result);
        assertFalse("Empty life cycle status list.",result.size()==0);
    }

    /**
     * Test of getValueByCurTypeFilter method, of class TradeAccessor.
     */
    @Test
    public void testGetValueByCurTypeFilter() {
        System.out.println("getValueByCurTypeFilter");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        PricingFilter pricingFilter =new PricingFilter(trade.getProduct().getNotionalCurrency(),trade.getProduct().getProductType(), null, "default");
        ArrayList<PricingFilter> currencyTypeFilterList=new ArrayList();
        currencyTypeFilterList.add(pricingFilter);
        PricingFilter result = TradeAccessor.getValueByCurTypeFilter(trade, currencyTypeFilterList);
        assertEquals("Wrong filter selection",pricingFilter, result);
    }

    /**
     * Test of deleteTrade method, of class TradeAccessor.
     */
    @Test
    public void testDeleteTrade() {
        System.out.println("deleteTrade");
        Trade intialTrade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        Trade trade=TradeAccessor.storeTrade(intialTrade.clone());
        TradeAccessor.deleteTrade(trade.getTradeId());
        Trade deleted=TradeAccessor.getTradeById(trade.getTradeId());
        assertNull("Trade not deleted "+trade.getTradeId(),deleted);
    }

    /**
     * Test of loadTradeByAttribute method, of class TradeAccessor.
     */
    @Test
    public void testLoadTradeByAttribute() {
        System.out.println("loadTradeByAttribute");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        TradeAttribute attr=new TradeAttribute(trade,"key","value");
        trade.getTradeAttributes().add(attr);
        TradeAccessor.storeTrade(trade);
        Trade result = TradeAccessor.loadTradeByAttribute(attr.getAttributeName(), attr.getAttributeValue());
        assertNotNull("Trade not found with attribute "+attr.getAttributeName()+"="+attr.getAttributeValue(),result);
        assertEquals("Problem with load trade : not equals",trade,result);
    }

    /**
     * Test of deleteTradeAttribute method, of class TradeAccessor.
     */
    @Test
    public void testDeleteTradeAttribute() {
        System.out.println("deleteTradeAttribute");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        TradeAttribute attr=null;
        for (TradeAttribute ta : trade.getTradeAttributes()){
            if (ta.getAttributeName().equalsIgnoreCase("key")){
                attr=ta;
            }
        }
        TradeAccessor.deleteTradeAttribute(attr);
        Object object=HibernateUtil.getObject(TradeAttribute.class, attr.getTradeAttibuteId());
        assertNull("Attribute not deleted : "+attr.getTradeAttibuteId(),object);
    }

    /**
     * Test of storeTradeGroup method, of class TradeAccessor.
     */
    @Test
    public void testStoreTradeGroup() {
        System.out.println("storeTradeGroup");
        Integer id = (Integer)HibernateUtil.getObjectWithQuery("select min(tradeGroupId) from TradeGroup");
        TradeGroup group = (TradeGroup)HibernateUtil.getObjectWithQuery("from TradeGroup where tradeGroupId="+id);
        TradeGroup result = TradeAccessor.storeTradeGroup(group);
        assertEquals("problem storing trade group "+group.getTradeGroupId(),group, result);
    }

    /**
     * Test of deleteTradeGroup method, of class TradeAccessor.
     */
    @Test
    public void testDeleteTradeGroup() {
        System.out.println("deleteTradeGroup");
        Trade initTrade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        TradeGroup tradeGroup = new TradeGroup();
        Trade trade1=initTrade.clone();
        trade1=(Trade)HibernateUtil.storeAndReturnObject(trade1);
        Trade trade2=initTrade.clone();
        trade2=(Trade)HibernateUtil.storeAndReturnObject(trade2);
        tradeGroup.addTrade(trade1);
        tradeGroup.addTrade(trade2);
        tradeGroup=TradeAccessor.storeTradeGroup(tradeGroup);
        Integer id=tradeGroup.getTrades().iterator().next().getTradeId();
        TradeAccessor.deleteTradeGroup(tradeGroup);
        assertTrue("trade group not deleted "+id,trade1.getTradeGroups().isEmpty());
        //clean
        HibernateUtil.deleteObject(trade1);
        HibernateUtil.deleteObject(trade2);
    }

    /**
     * Test of isValid method, of class TradeAccessor.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Integer id = (Integer)HibernateUtil.getObjectWithQuery("select min(t.tradeId) from Trade t where t.status='New'");
        Trade trade = TradeAccessor.getTradeById(id);
        boolean result = TradeAccessor.isValid(trade);
        assertTrue("trade "+ id + " is supposed to be valid. valid="+result,result);
    }

    /**
     * Test of getActiveTradeStatus method, of class TradeAccessor.
     */
    @Test
    public void testGetActiveTradeStatus() {
        System.out.println("getActiveTradeStatus");
        List result = TradeAccessor.getActiveTradeStatus();
        assertNotNull(result);
        assertFalse("Empty valid status list",result.isEmpty());
    }

    /**
     * Test of getActiveTradeStatusSQLClause method, of class TradeAccessor.
     */
    @Test
    public void testGetActiveTradeStatusSQLClause() {
        System.out.println("getActiveTradeStatusSQLClause");
        String result = TradeAccessor.getActiveTradeStatusSQLClause();
        assertNotNull(result);
        assertFalse("not valid sql clause = "+result,result.isEmpty());
    }

    /**
     * Test of getTradesByProduct method, of class TradeAccessor.
     */
    @Test
    public void testGetTradesByProduct() {
        System.out.println("getTradesByProduct");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        List<Trade> result = TradeAccessor.getTradesByProduct(trade.getProduct());
        assertNotNull(result);
        assertFalse("Not trade found on product" + trade.getProduct(),result.isEmpty());
    }

    /**
     * Test of createFXOptionsSpotExpiry method, of class TradeAccessor.
     */
    @Test
    public void testCreateFXOptionsSpotExpiry() {
        System.out.println("createFXOptionsSpotExpiry");
        Trade trade =TradeAccessor.getFXOptionTradeForUnitTests();
        if (trade!=null){
            List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(trade.getProduct().getId());
            // launch
            Boolean result = TradeAccessor.createFXOptionsSpotExpiry(lines);
            // assertions
            assertTrue("Problem while creating FX Option spot expiry on trade "+trade.getTradeId(),result);
        }
    }

    /**
     * Test of getSettlementDate method, of class TradeAccessor.
     */
    @Test
    public void testGetSettlementDate() {
        System.out.println("getSettlementDate");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        Date result = TradeAccessor.getSettlementDate(trade);
        assertNotNull("Wrong settlement date",result);
    }

    /**
     * Test of getFxBarrierOptions method, of class TradeAccessor.
     */
    @Test
    public void testGetFxBarrierOptions() {
        System.out.println("getFxBarrierOptions");
        List result = TradeAccessor.getFxBarrierOptions(DateUtils.getDate(), null);
        assertNotNull("Barrier option list should not be null",result);
    }

    /**
     * Test of applyBarrierCrossing method, of class TradeAccessor.
     */
    @Test
    public void testApplyBarrierCrossing() {
        System.out.println("applyBarrierCrossing");
        List<Trade> options= TradeAccessor.getFxBarrierOptions(DateUtils.getDate(), null);
        if (options!=null && !options.isEmpty()){
            List<ScheduleLine> lines =ScheduleAccessor.getScheduleLinesByProductId(options.get(0).getProduct().getProductId());
            if (lines !=null && !lines.isEmpty()){
                ArrayList<ScheduleLine> selectedRows=new ArrayList();
                selectedRows.add(lines.get(0));
                Boolean result = TradeAccessor.applyBarrierCrossing(selectedRows);
                assertTrue("Fail applying barrier crossing",result);
                options.get(0).setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.ACTIVATED.name);
                TradeAccessor.storeTrade(options.get(0));
            }
        }
    }
}