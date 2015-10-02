package org.gaia.dao.trades;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */
public class ScheduleAccessorTest extends AbstractTest{

    /**
     * Test of getScheduleLinesByProductId method, of class ScheduleAccessor.
     */
    @Test
    public void testGetScheduleLinesByProductId() {
        System.out.println("getScheduleLinesByProductId");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List result = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        assertNotNull("missing schedule line", result);
        assertFalse("missing schedule line", result.isEmpty());
    }

    /**
     * Test of getScheduleLinesByDate method, of class ScheduleAccessor.
     */
    @Test
    public void testGetScheduleLinesByDate() {
        System.out.println("getScheduleLinesByDate");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        if (lines !=null && !lines.isEmpty()){
            List result = ScheduleAccessor.getScheduleLinesByDate(lines.get(0).getResetDate(), null);
            assertNotNull("missing schedule line", result);
            assertFalse("missing schedule line", result.isEmpty());
        }
    }

    /**
     * Test of getFXOptionsExercisesByDate method, of class ScheduleAccessor.
     */
    @Test
    public void testGetFXOptionsExercisesByDate() {
        System.out.println("getFXOptionsExercisesByDate");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.FX_VANILLA_OPTION.getName());
        List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        if (lines !=null && !lines.isEmpty()){
            List result = ScheduleAccessor.getFXOptionsExercisesByDate(lines.get(0).getResetDate(), null);
            assertNotNull("missing fx exercise line", result);
            assertFalse("missing fx exercise line", result.isEmpty());
        }
    }

    /**
     * Test of deleteProductScheduleLinesAndFlows method, of class ScheduleAccessor.
     */
    @Test
    public void testDeleteProductScheduleLinesAndFlows() {
        System.out.println("deleteProductScheduleLinesAndFlows");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        ScheduleAccessor.deleteProductScheduleLinesAndFlows(product.getProductId());
        List<ScheduleLine> result = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        assertTrue("schedule lines not deleted", result.isEmpty());
        // regenerage
        Schedule schedule=ScheduleBuilder.buildScheduleFromProduct(product);
        for (ScheduleLine line : schedule.getScheduleLines()){
            ScheduleAccessor.saveScheduleLine(line);
        }
    }

    /**
     * Test of storeScheduleLine method, of class ScheduleAccessor.
     */
    @Test
    public void testStoreScheduleLine() {
        System.out.println("storeScheduleLine");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        if (lines !=null && !lines.isEmpty()){
             ScheduleLine scheduleLine=lines.get(0).clone();
             Integer result = ScheduleAccessor.storeScheduleLine(scheduleLine);
             assertNotNull("schedule lines not stored", result);
             HibernateUtil.deleteObject(scheduleLine);
        }
    }

    /**
     * Test of saveScheduleLine method, of class ScheduleAccessor.
     */
    @Test
    public void testSaveScheduleLine() {
        System.out.println("saveScheduleLine");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        if (lines !=null && !lines.isEmpty()){
            ScheduleLine scheduleLine=lines.get(0).clone();
            ScheduleAccessor.saveScheduleLine(scheduleLine);
            assertNotNull("schedule lines not stored", scheduleLine.getScheduleLineId());
            ScheduleAccessor.deleteScheduleLine(scheduleLine);
        }
    }

    /**
     * Test of deleteScheduleLine method, of class ScheduleAccessor.
     */
    @Test
    public void testDeleteScheduleLine() {
        System.out.println("deleteScheduleLine");
        Product product =ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IRS.getName());
        List<ScheduleLine> lines = ScheduleAccessor.getScheduleLinesByProductId(product.getProductId());
        if (lines !=null && !lines.isEmpty()){
            ScheduleLine scheduleLine=lines.get(0).clone();
            ScheduleAccessor.saveScheduleLine(scheduleLine);
            ScheduleAccessor.deleteScheduleLine(scheduleLine);
            Object obj=HibernateUtil.getObject(ScheduleLine.class, scheduleLine.getScheduleLineId());
            assertNull("schedule lines not deleted", obj);

        }
    }
}