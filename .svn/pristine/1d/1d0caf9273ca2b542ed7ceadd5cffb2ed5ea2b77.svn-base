package org.gaia.dao.referentials;


import java.util.Date;
import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.CalendarDate;
import org.gaia.domain.referentials.HolidayCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class CalendarAccessorTest extends AbstractTest{


    /**
     * Test of storeCalendar method, of class CalendarAccessor.
     */
    @Test
    public void testStoreCalendar() {
        System.out.println("storeCalendar");
        HolidayCalendar calendar = new HolidayCalendar();
        calendar.setCalendarCode("test");
        calendar.setCurrency("XXX");
        CalendarAccessor.storeCalendar(calendar);
        HolidayCalendar result=CalendarAccessor.getCalendarByCode("test");
        assertEquals("failed to store calendar",result,calendar);
        HibernateUtil.deleteObject(result);
    }

    /**
     * Test of storeCalendarDate method, of class CalendarAccessor.
     */
    @Test
    public void testStoreCalendarDate() {
        System.out.println("storeCalendarDate");
        CalendarDate calendarDate=(CalendarDate)HibernateUtil.getObjectWithQuery("from CalendarDate");
        CalendarDate test=calendarDate.clone();
        CalendarAccessor.storeCalendarDate(test);
        CalendarDate result =CalendarAccessor.getCalendarDateByCodeAndDate(test.getCalendarCode(), test.getCalendarDate());
        assertEquals("failed to store calendar date ",result,calendarDate);
        CalendarAccessor.deleteCalendarDate(test);
    }

    /**
     * Test of getCalendarByCode method, of class CalendarAccessor.
     */
    @Test
    public void testGetCalendarByCode() {
        System.out.println("getCalendarByCode");
        HolidayCalendar test=(HolidayCalendar)HibernateUtil.getObjectWithQuery("from HolidayCalendar");
        HolidayCalendar result = CalendarAccessor.getCalendarByCode(test.getCalendarCode());
        assertNotNull("failed to load calendar", result);
    }

    /**
     * Test of getCalendarDateByCodeAndDate method, of class CalendarAccessor.
     */
    @Test
    public void testGetCalendarDateByCodeAndDate() {
        System.out.println("getCalendarDateByCodeAndDate");
        CalendarDate test=(CalendarDate)HibernateUtil.getObjectWithQuery("from CalendarDate");
        CalendarDate result = CalendarAccessor.getCalendarDateByCodeAndDate(test.getCalendarCode(), test.getCalendarDate());
        assertNotNull("failed to load calendar date", result);
    }

    /**
     * Test of loadAllCalendars method, of class CalendarAccessor.
     */
    @Test
    public void testLoadAllCalendars() {
        System.out.println("loadAllCalendars");
        List result = CalendarAccessor.loadAllCalendars();
        assertNotNull("failed to load calendars", result);
    }

    /**
     * Test of loadCalendarCodes method, of class CalendarAccessor.
     */
    @Test
    public void testLoadCalendarCodes() {
        System.out.println("loadCalendarCodes");
        List result = CalendarAccessor.loadCalendarCodes();
        assertNotNull("failed to load calendars", result);
    }

    /**
     * Test of deleteCalendar method, of class CalendarAccessor.
     */
    @Test
    public void testDeleteCalendar() {
        System.out.println("deleteCalendar");
        CalendarAccessor.deleteCalendar("test");
        HolidayCalendar result = CalendarAccessor.getCalendarByCode("test");
        assertNull("failed to delete calendars", result);
    }

    /**
     * Test of deleteCalendarDateByCode method, of class CalendarAccessor.
     */
    @Test
    public void testDeleteCalendarDateByCode() {
        System.out.println("deleteCalendarDateByCode");
        Date date =DateUtils.getDate();
        CalendarAccessor.deleteCalendarDateByCode("test", date);
        CalendarDate res=CalendarAccessor.getCalendarDateByCodeAndDate("test", date);
        assertNull("failed to delete calendars", res);
    }

    /**
     * Test of deleteCalendarDate method, of class CalendarAccessor.
     */
    @Test
    public void testDeleteCalendarDate() {
        System.out.println("deleteCalendarDate");
        CalendarDate calendarDate=(CalendarDate)HibernateUtil.getObjectWithQuery("from CalendarDate");
        CalendarDate test = calendarDate.clone();
        CalendarAccessor.storeCalendarDate(test);
        CalendarAccessor.deleteCalendarDate(test);
        test=(CalendarDate)HibernateUtil.getObject(CalendarDate.class, test.getCalendarDateId());
        assertNull("failed to delete calendar date", test);
    }

    /**
     * Test of mergeCalendars method, of class CalendarAccessor.
     */
    @Test
    public void testMergeCalendars() {
        System.out.println("mergeCalendars");
        List<HolidayCalendar> calendars=(List)HibernateUtil.getObjectsWithQuery("from HolidayCalendar");
        if (calendars.size()>=2){
            HolidayCalendar cal1 = calendars.get(0);
            HolidayCalendar cal2 = calendars.get(1);
            HolidayCalendar result = CalendarAccessor.mergeCalendars(cal1, cal2);
            assertEquals("failed to merge calendars",result.getCalendarDateList().size(), cal1.getCalendarDateList().size()+cal2.getCalendarDateList().size());

        }
    }

}
