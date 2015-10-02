package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.gaia.dao.trades.ScheduleBuilder.getProductScheduler;
import org.gaia.dao.trades.schedulers.IProductScheduler;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class ScheduleBuilderTest extends AbstractTest{

    /**
     * Test of buildScheduleFromTrade method, of class ScheduleBuilder.
     */
    @Test
    public void testBuildScheduleFromIRSTrade() {
        System.out.println("buildScheduleFromTrade");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.IRS.getName());
        Schedule result = ScheduleBuilder.buildScheduleFromTrade(trade);
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.getScheduleLines().isEmpty());
    }


    @Test
    public void testBuildScheduleFromCDSTrade() {
        System.out.println("buildScheduleFromTrade");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.getName());
        Schedule result = ScheduleBuilder.buildScheduleFromTrade(trade);
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.getScheduleLines().isEmpty());
    }

    @Test
    public void testBuildScheduleFromBondTrade() {
        System.out.println("buildScheduleFromTrade");
        Trade trade=TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.BOND.getName());
        Schedule result = ScheduleBuilder.buildScheduleFromTrade(trade);
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.getScheduleLines().isEmpty());
    }
    /**
     * Test of buildScheduleFromProduct method, of class ScheduleBuilder.
     */
    @Test
    public void testBuildScheduleFromProduct() {
        System.out.println("buildScheduleFromProduct");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        Schedule result = ScheduleBuilder.buildScheduleFromProduct(product);
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.getScheduleLines().isEmpty());
    }

    /**
     * Test of getProductScheduler method, of class ScheduleBuilder.
     */
    @Test
    public void testGetProductScheduler() {
        System.out.println("getProductScheduler");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        IProductScheduler result = ScheduleBuilder.getProductScheduler(product);
        assertNotNull("missing schedule", result);
    }

    /**
     * Test of mergeSchedule method, of class ScheduleBuilder.
     */
    @Test
    public void testMergeSchedule() {
        System.out.println("mergeSchedule");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        product=product.getFirstLeg();
        IProductScheduler productScheduler = getProductScheduler(product);
        Schedule schedule = productScheduler.buildSchedule(product);
        List<ScheduleLine> storedLines = (ArrayList) ScheduleAccessor.getScheduleLinesByProductId(product.getId());
        ScheduleBuilder.mergeSchedule(schedule.getScheduleLines(), storedLines);
        assertNotNull("missing schedule", schedule);
        assertFalse("missing schedule", schedule.getScheduleLines().isEmpty());
    }

    /**
     * Test of updateProductSchedule method, of class ScheduleBuilder.
     */
    @Test
    public void testUpdateProductSchedule_Product() {
        System.out.println("updateProductSchedule");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        ScheduleAccessor.deleteProductScheduleLinesAndFlows(product.getProductId());
        ScheduleBuilder.updateProductSchedule(product);
        List result=ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.isEmpty());
    }

    /**
     * Test of updateProductSchedule method, of class ScheduleBuilder.
     */
    @Test
    public void testUpdateProductSchedule_Product_Session() {
        System.out.println("updateProductSchedule");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        ScheduleAccessor.deleteProductScheduleLinesAndFlows(product.getProductId());
        Session session = HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        ScheduleBuilder.updateProductSchedule(product, session);
        t.commit();
        session.close();
        List result=ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        assertNotNull("missing schedule", result);
        assertFalse("missing schedule", result.isEmpty());
    }

    /**
     * Test of makeScheduleLinesFixings method, of class ScheduleBuilder.
     */
    @Test
    public void testMakeScheduleLinesFixings() {
        System.out.println("makeScheduleLinesFixings");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(sl.scheduleLineId) from ScheduleLine sl where sl.isFixed=false and sl.fixingProduct is not null");
        ScheduleLine line=(ScheduleLine)HibernateUtil.getObject(ScheduleLine.class , id);
        line.setFixing(new BigDecimal(0.01));
        List<ScheduleLine> lines = new ArrayList();
        lines.add(line);
        ScheduleBuilder.makeScheduleLinesFixings(lines, null);
        line=(ScheduleLine)HibernateUtil.getObject(ScheduleLine.class , id);
        assertTrue("schedule line not fixed", line.isFixed());
        //clean
        line.setFixed(false);
        line.setPaymentAmount(BigDecimal.ZERO);
        line.setFixing(BigDecimal.ZERO);
        ScheduleAccessor.storeScheduleLine(line);
    }

    /**
     * Test of getTradeFromProductOrSubProduct method, of class ScheduleBuilder.
     */
    @Test
    public void testGetTradeFromProductOrSubProduct() {
        System.out.println("getTradeFromProductOrSubProduct");
        Product product = ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        product=product.getFirstLeg();
        List result = ScheduleBuilder.getTradeFromProductOrSubProduct(product);
        assertNotNull("trade not found for product "+product.getProductId(), result);
    }
}