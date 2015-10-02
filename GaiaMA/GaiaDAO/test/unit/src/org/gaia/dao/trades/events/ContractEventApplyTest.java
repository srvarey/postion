package org.gaia.dao.trades.events;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeGroup;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ContractEventApplyTest extends AbstractTest {

    private ProductEvent productEvent;
    private Product product;
    private Trade OTCTrade;
    private ContractEventApply instance;
    private ContractEvent OTCEvent;
    private Trade listedTrade;
    private ContractEvent listedEvent;

    public ContractEventApplyTest() {
        super();

        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select max(ce.contractEventId) from ContractEvent ce");
        instance = new ContractEventApply(id.intValue());
//
//        Trade initialTrade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
//        listedTrade=initialTrade.clone();
//        listedTrade.setStatus(TradeAccessor.TradeStatus.NEW.name);
//        listedTrade.setLifeCycleStatus(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
//        listedTrade.setTradeGroups(new HashSet());
//        TradeAccessor.storeTrade(listedTrade);
//
//        listedEvent=new ContractEvent();
//        listedEvent.setContractEvent(ContractEventApply.ContractEventType.NovationTransfer.name());
//        listedEvent.setNegociationDate(date);
//        listedEvent.setPaymentAmount(BigDecimal.ZERO);
//        listedEvent.setPaymentCurrency("XXX");
//        listedEvent.setPaymentDate(date);
//        listedEvent.setValueDate(date);
//        listedEvent.setProduct(product);
//        listedEvent.setUnwindFactor(BigDecimal.ONE);
//        listedEvent.setUnwindAmount(BigDecimal.ONE);
//        HibernateUtil.storeObject(listedEvent);
    }

    /**
     * Test of applyOnProduct method, of class ContractEventApply.
     */
    @Test
    public void testIRSNovation() {
        System.out.println("IRSNovation");

        Date date=DateUtils.getDate();
        Product initialProduct = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
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

        OTCEvent=new ContractEvent();
        OTCEvent.setContractEvent(ContractEventApply.ContractEventType.NovationTransfer.name());
        OTCEvent.setNegociationDate(date);
        OTCEvent.setPaymentAmount(BigDecimal.ZERO);
        OTCEvent.setPaymentCurrency("XXX");
        OTCEvent.setPaymentDate(date);
        OTCEvent.setValueDate(date);
        OTCEvent.setProduct(product);
        OTCEvent.setUnwindFactor(BigDecimal.ONE);
        OTCEvent.setUnwindAmount(BigDecimal.ONE);
        LegalEntity counterparty=LegalEntityAccessor.getAnyLegalEntityByRole(LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name);
        OTCEvent.setCounterparty(counterparty);
        HibernateUtil.storeObject(OTCEvent);

        // launch engine
        EventEngine.applyEventOnProduct(ContractEventApply.class,OTCEvent.getContractEventId(),OTCTrade.getProduct().getProductId());

        //get results
        product = ProductAccessor.getProductById(OTCTrade.getProduct().getProductId());
        productEvent=(ProductEvent)HibernateUtil.getObjectWithQuery("from ProductEvent pe where pe.product.productId="+OTCTrade.getProduct().getProductId());
        OTCTrade=TradeAccessor.getTradeById(OTCTrade.getTradeId());

        // control
        assertNotNull("problem novating product" , productEvent.getProductEventDetails());
        assertFalse("problem novating product" , productEvent.getProductEventDetails().isEmpty());
        assertEquals("problem novating product" , HibernateUtil.dateFormat.format(productEvent.getEffectiveDate()), HibernateUtil.dateFormat.format(product.getMaturityDate()));

        assertEquals("problem novating trade" , OTCTrade.getLifeCycleStatus(), TradeAccessor.TradeLifeCycleStatus.NOVATED.name);
        assertNotNull("problem novating trade" , OTCTrade.getTradeGroup(TradeGroup.TradeGroupType.Event.name()));

        for (Flow flow : FlowAccessor.getFlowsByTrade(OTCTrade.getTradeId())){
            if (flow.getValueDate().after(OTCEvent.getValueDate())){
                assertTrue("problem novating flows" , flow.getQuantity().doubleValue()==0);
            }
        }

        for (ScheduleLine line : ScheduleAccessor.getScheduleLinesByProductId(OTCTrade.getProduct().getProductId())){
            if (line.getStartDate().after(OTCEvent.getValueDate())){
                assertTrue("problem novating flows" , line.getPaymentAmount().doubleValue()==0);
            }
        }
        //clean
        TradeAccessor.deleteTrade(OTCTrade.getTradeId());
    }

}