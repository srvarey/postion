package org.gaia.dao.trades.events;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.legalEntity.CreditEvent;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
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


public class CreditEventApplyTest extends AbstractTest{
    private CreditEventApply instance;
    private Product product;
    private Trade OTCTrade;
    private CreditEvent OTCEvent;
    private ProductEvent productEvent;

    public CreditEventApplyTest() {
        super();

        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select max(ce.contractEventId) from ContractEvent ce");
        instance = new CreditEventApply(id.intValue());
    }


    /**
     * Test of applyOnProduct method, of class ContractEventApply.
     */
    @Test
    public void testIRSNovation() {
        System.out.println("CustomCDSCreditevent");

        Date date=DateUtils.getDate();
        Product initialProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.CUSTOM_CDS.getName());
        product=ProductAccessor.cloneProduct(initialProduct);
        product.setStartDate(DateUtils.addYear(date, -1));
        product.setMaturityDate(DateUtils.addYear(date, 1));

        Trade initialTrade =TradeAccessor.getTradesByProduct(initialProduct).get(0);
        OTCTrade=initialTrade.clone();
        OTCTrade.setProduct(product);
        OTCTrade.setTradeDate(DateUtils.addYear(date, -1));
        OTCTrade.setStatus(TradeAccessor.TradeStatus.NEW.name);
        OTCTrade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
        OTCTrade.setTradeGroups(new HashSet());
        OTCTrade=TradeAccessor.storeTrade(OTCTrade);

        FlowAccessor.generateAndStoreScheduleFlows(OTCTrade);

        OTCEvent=new CreditEvent();
        OTCEvent.setCreditEvent("Default");
        OTCEvent.setDefaultDate(date);
        OTCEvent.setIsHard(Boolean.TRUE);
        OTCEvent.setIssuer(ProductAccessor.getProductIssuer(product.getProductId()));
        OTCEvent.setPaymentDate(date);
        OTCEvent.setReceptionDate(date);
        OTCEvent.setRecoveryRate(new BigDecimal(.4));

        HibernateUtil.storeObject(OTCEvent);

        // launch engine
        EventEngine.applyEventOnProduct(CreditEventApply.class,OTCEvent.getCreditEventId(),OTCTrade.getProduct().getProductId());

        //get results
        product = ProductAccessor.getProductById(OTCTrade.getProduct().getProductId());
        productEvent=(ProductEvent)HibernateUtil.getObjectWithQuery("from ProductEvent pe where pe.product.productId="+OTCTrade.getProduct().getProductId());
        OTCTrade=TradeAccessor.getTradeById(OTCTrade.getTradeId());

        // control
        assertNotNull("problem applying credit event on product" , productEvent.getProductEventDetails());
        assertFalse("problem applying credit event on product" , productEvent.getProductEventDetails().isEmpty());
        assertEquals("problem applying credit event on product : status , " , product.getStatus(), ProductConst.ProductStatus.Defaulted.name());

        assertEquals("problem applying credit event on trade" , OTCTrade.getLifeCycleStatus(), TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name);

        for (Flow flow : FlowAccessor.getFlowsByTrade(OTCTrade.getTradeId())){
            if (flow.getValueDate().after(OTCEvent.getDefaultDate())){
                assertTrue("problem applying credit event on flows" , flow.getQuantity().doubleValue()==0);
            }
        }

        for (ScheduleLine line : ScheduleAccessor.getScheduleLinesByProductId(OTCTrade.getProduct().getProductId())){
            if (line.getStartDate().after(OTCEvent.getDefaultDate())){
                assertTrue("problem applying credit event on flows" , line.getPaymentAmount().doubleValue()==0);
            }
        }
        // cancel event
        EventEngine.cancelProductEvent(productEvent);
        productEvent=(ProductEvent)HibernateUtil.getObjectWithQuery("from ProductEvent pe where pe.product.productId="+OTCTrade.getProduct().getProductId());
        assertNull("problem cancelling credit event" , productEvent);
        //clean
        OTCTrade.setProductEvents(null);
        TradeAccessor.deleteTrade(OTCTrade.getTradeId());
    }

}
