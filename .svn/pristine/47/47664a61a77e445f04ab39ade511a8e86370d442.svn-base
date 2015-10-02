package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
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
public class FlowAccessorTest extends AbstractTest{

    /**
     * Test of generateAndStoreScheduleFlows method, of class FlowAccessor.
     */
    @Test
    public void testGenerateAndStoreScheduleFlows() {
        System.out.println("generateAndStoreScheduleFlows");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        FlowAccessor.generateAndStoreScheduleFlows(trade);
        List list=FlowAccessor.getFlowsByTrade(trade.getTradeId());
        assertNotNull("Flows not generated on "+trade.getTradeId(),list);
        assertFalse("No flows generated on "+trade.getTradeId(),list.isEmpty());
    }

    /**
     * Test of generateAndStoreScheduleFlowsIfNeeded method, of class FlowAccessor.
     */
    @Test
    public void testGenerateAndStoreScheduleFlowsIfNeeded() {
        System.out.println("generateAndStoreScheduleFlowsIfNeeded");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        FlowAccessor.deleteFlowsByTradeAndSubType(trade, Flow.FlowSubType.INTEREST.name());
        FlowAccessor.generateAndStoreScheduleFlowsIfNeeded(trade);
        List list=FlowAccessor.getFlowsByTrade(trade.getTradeId());
        assertNotNull("Flows not generated on "+trade.getTradeId(),list);
        assertFalse("No flows generated on "+trade.getTradeId(),list.isEmpty());
    }

    /**
     * Test of generateScheduleFlows method, of class FlowAccessor.
     */
    @Test
    public void testGenerateScheduleFlows() {
        System.out.println("generateScheduleFlows");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        ArrayList result = FlowAccessor.generateScheduleFlows(trade);
        assertNotNull("Flows not generated on "+trade.getTradeId(), result);
        assertFalse("No flows generated on "+trade.getTradeId(),result.isEmpty());
    }

    /**
     * Test of fillCashFlow method, of class FlowAccessor.
     */
    @Test
    public void testFillCashFlow() {
        System.out.println("fillCashFlow");
        Flow flow = null;
        BigDecimal amount = BigDecimal.ONE;
        String currency = "XXX";
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Date valueDate = trade.getValueDate();
        Date tradeDate = trade.getTradeDate();
        Integer scheduleLineId = null;
        Flow.FlowSubType subType = Flow.FlowSubType.INTEREST;
        Flow result = FlowAccessor.fillCashFlow(flow, amount, currency, trade, valueDate, tradeDate, scheduleLineId, subType);
        assertNotNull("Flow not generated", result);
    }

    /**
     * Test of fillFlow method, of class FlowAccessor.
     */
    @Test
    public void testFillFlow() {
        System.out.println("fillFlow");
        Flow flow = null;
        BigDecimal quantity = BigDecimal.ONE;
        String quantityType = "XXX";
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Product product = trade.getProduct();
        Date valueDate = trade.getValueDate();
        Date tradeDate = trade.getTradeDate();
        Integer scheduleLineId = null;
        Flow.FlowType type = Flow.FlowType.CASH;
        Flow.FlowSubType subType = Flow.FlowSubType.INTEREST;
        Flow result = FlowAccessor.fillFlow(flow, quantity, quantityType, product, trade, valueDate, tradeDate, scheduleLineId, type, subType);
        assertNotNull("Flow not generated", result);
    }

    /**
     * Test of regeneratePrincipalFlows method, of class FlowAccessor.
     */
    @Test
    public void testRegeneratePrincipalFlows() {
        System.out.println("regeneratePrincipalFlows");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        FlowAccessor.regeneratePrincipalFlows(trade);
        List list=FlowAccessor.getFlowsByTradeAndSubType(trade.getTradeId(), Flow.FlowSubType.PRINCIPAL);
        assertNotNull("Flow not generated", list);
    }

    /**
     * Test of generatePrincipalFlows method, of class FlowAccessor.
     */
    @Test
    public void testGeneratePrincipalFlows() {
        System.out.println("generatePrincipalFlows");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        FlowAccessor.deletePrincipalFlows(trade);
        FlowAccessor.generatePrincipalFlows(trade);
        List list=FlowAccessor.getFlowsByTradeAndSubType(trade.getTradeId(), Flow.FlowSubType.PRINCIPAL);
        assertNotNull("Flow not generated", list);
    }

    /**
     * Test of generateAllPrincipalFlows method, of class FlowAccessor.
     */
    @Test
    public void testGenerateAllPrincipalFlows() {
        System.out.println("generateAllPrincipalFlows");
        // don't do that
        // superadmin task only
    }

    /**
     * Test of deletePrincipalFlows method, of class FlowAccessor.
     */
    @Test
    public void testDeletePrincipalFlows() {
        System.out.println("deletePrincipalFlows");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        FlowAccessor.deletePrincipalFlows(trade);
        List list =FlowAccessor.getFlowsByTradeAndSubType(trade.getTradeId(),Flow.FlowSubType.PRINCIPAL);
        assertTrue("Some flows were not deleted",list.isEmpty());
        FlowAccessor.generatePrincipalFlows(trade);
    }

    /**
     * Test of getFlowsByTradeAndDates method, of class FlowAccessor.
     */
    @Test
    public void testGetFlowsByTradeAndDates() {
        System.out.println("getFlowsByTradeAndDates");
        Trade trade= TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        List result = FlowAccessor.getFlowsByTradeAndDates(trade.getTradeId(), trade.getTradeDate(), trade.getTradeDate());
        assertFalse("Some flows are not found",result.isEmpty());
    }

    /**
     * Test of getFlowById method, of class FlowAccessor.
     */
    @Test
    public void testGetFlowById() {
        System.out.println("getFlowById");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(f.flowId) from Flow f");
        Flow result = FlowAccessor.getFlowById(id);
        assertNotNull("Flow "+id+" not found", result);
    }

    /**
     * Test of storeFlow method, of class FlowAccessor.
     */
    @Test
    public void testStoreFlow() {
        System.out.println("storeFlow");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(f.flowId) from Flow f");
        Flow intialFlow = FlowAccessor.getFlowById(id);
        Flow flow =intialFlow.clone();
        FlowAccessor.storeFlow(flow);
        Integer id2=(Integer)HibernateUtil.getObjectWithQuery("select max(f.flowId) from Flow f");
        assertEquals("stored flow not found",flow.getFlowId(),id2);
        HibernateUtil.deleteObject(flow);
    }

    /**
     * Test of generateAndStoreAllScheduleFlows method, of class FlowAccessor.
     */
    @Test
    public void testGenerateAndStoreAllScheduleFlows() {
        System.out.println("generateAndStoreAllScheduleFlows");
        // don't do that
        // superadmin task only
    }

    /**
     * Test of getFlowsByTrade method, of class FlowAccessor.
     */
    @Test
    public void testGetFlowsByTrade() {
        System.out.println("getFlowsByTrade");
        Trade trade =TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Long nb=(Long)HibernateUtil.getObjectWithQuery("select count(*) from Flow f where f.trade.tradeId="+trade.getTradeId());
        List result = FlowAccessor.getFlowsByTrade(trade.getTradeId());
        assertEquals("Not the right number of flows",nb.intValue(), result.size());
    }

    /**
     * Test of getScheduleFlowsByTrade method, of class FlowAccessor.
     */
    @Test
    public void testGetScheduleFlowsByTrade() {
        System.out.println("getScheduleFlowsByTrade");
        Trade trade =TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Long nb=(Long)HibernateUtil.getObjectWithQuery("select count(*) from Flow f where f.trade.tradeId="+trade.getTradeId()+" and f.scheduleLineId is not null");
        List result = FlowAccessor.getScheduleFlowsByTrade(trade.getTradeId());
        assertEquals("Not the right number of flows",nb.intValue(), result.size());
    }

    /**
     * Test of getFlowsByTradeAndScheduleLine method, of class FlowAccessor.
     */
    @Test
    public void testGetFlowsByTradeAndScheduleLine() {
        System.out.println("getFlowsByTradeAndScheduleLine");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(f.flowId) from Flow f where f.scheduleLineId is not null");
        Flow intialFlow = FlowAccessor.getFlowById(id);
        Flow result = FlowAccessor.getFlowsByTradeAndScheduleLine(intialFlow.getTrade().getTradeId(), intialFlow.getScheduleLineId());
        assertEquals("Not the right flow",id.intValue(), result.getFlowId().intValue());
    }

    /**
     * Test of getFlowsByTradeAndSubType method, of class FlowAccessor.
     */
    @Test
    public void testGetFlowsByTradeAndSubType() {
        System.out.println("getFlowsByTradeAndSubType");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(f.flowId) from Flow f where f.scheduleLineId is not null");
        Flow intialFlow = FlowAccessor.getFlowById(id);
        List<Flow> result = FlowAccessor.getFlowsByTradeAndSubType(intialFlow.getTrade().getTradeId(), Flow.FlowSubType.valueOf(intialFlow.getSubType()));
        assertFalse(result.isEmpty());
        Integer min=Integer.MAX_VALUE;
        for (Flow flow : result){
            if (min>flow.getFlowId()){
                min=flow.getFlowId();
            }
        }
        assertEquals("Not the right flow",id.intValue(), min.intValue());
    }

    /**
     * Test of deleteFlow method, of class FlowAccessor.
     */
    @Test
    public void testDeleteFlow() {
        System.out.println("deleteFlow");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select max(f.flowId) from Flow f");
        Flow intialFlow = FlowAccessor.getFlowById(id);
        Flow flow = intialFlow.clone();
        FlowAccessor.storeFlow(flow);
        id=(Integer)HibernateUtil.getObjectWithQuery("select max(f.flowId) from Flow f");
        FlowAccessor.deleteFlow(flow);
        flow=FlowAccessor.getFlowById(id);
        assertNull("flow not deleted "+id,flow);
    }

    /**
     * Test of deleteFlowsByProduct method, of class FlowAccessor.
     */
    @Test
    public void testDeleteFlowsByProduct() {
        // don't do that
        // superadmin task only
    }
}