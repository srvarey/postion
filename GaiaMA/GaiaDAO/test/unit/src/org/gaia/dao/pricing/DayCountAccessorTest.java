package org.gaia.dao.pricing;

import java.util.Calendar;
import java.util.Date;
import org.gaia.dao.pricing.DayCountAccessor.DayCount;
import org.gaia.dao.utils.AbstractTest;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jawhar Kamoun
 */
public class DayCountAccessorTest extends AbstractTest {

    public DayCountAccessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of dayCountFraction method, of class DayCountAccessor.
     */
    @Test
    public void testDayCountFraction() {
        System.out.println("dayCountFraction");
        Date startDate = new Date();
        Date endDate = new Date();
        String method = DayCount.ACT_360.getName();
        double expResult = 0.0;
        double result = DayCountAccessor.dayCountFraction(startDate, endDate, method);
        assertEquals("DayCount should be 0", expResult, result, 0.0);
    }

    /**
     * Test of dayCountDaysDiff method, of class DayCountAccessor.
     */
    @Test
    public void testDayCountDaysDiff() {
        System.out.println("dayCountDaysDiff");
        Date startDate = new Date();
        Date endDate = new Date();
        String method = DayCount.ACT_360.getName();
        long expResult = 0L;
        long result = DayCountAccessor.dayCountDaysDiff(startDate, endDate, method);
        assertEquals("DayCount diff should be 0", expResult, result, 0.0);
    }

    /**
     * Test of daysLeftThisYear method, of class DayCountAccessor.
     */
    @Test
    public void testDaysLeftThisYear() {
        System.out.println("daysLeftThisYear");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date date = cal.getTime();
        long expResult = 1;
        long result = DayCountAccessor.daysLeftThisYear(date);
        assertEquals("31 dec is last day of the year should be 1", expResult, result);
    }

}
