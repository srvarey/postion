/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.referentials;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.gaia.domain.referentials.CalendarDate;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class CalendarAccessor {

    public static final String STORE_CALENDAR = "storeCalendar";

    public static void storeCalendar(HolidayCalendar calendar) {
        /**
         * retrieve a list of holiday date
         */
        List<CalendarDate> list = calendar.getCalendarDateList();
        HolidayCalendar holidayCalendar = getCalendarByCode(calendar.getCalendarCode());
        if (holidayCalendar == null) {
            /**
             * creation
             */
            calendar.setCalendarDateList(null);
        } else {
            if (list != null) {
                for (CalendarDate calendarDate : list) {
                    if (calendarDate.getCalendarDateId() == null) {
                        calendarDate.setCalendar(calendar);
                        storeCalendarDate(calendarDate);
                    }
                }
            }
        }
        HibernateUtil.storeObject(calendar);
        if (holidayCalendar == null) {//creation
            for (CalendarDate cd : list) {
                cd.setCalendar(calendar);
                storeCalendarDate(cd);
            }
        }
    }

    public static final String STORE_CALENDAR_DATE = "storeCalendarDate";

    /**
     * store calendar of holiday date
     *
     * @param calendarDate
     */
    public static void storeCalendarDate(CalendarDate calendarDate) {
        HibernateUtil.storeObject(calendarDate);
    }

    public static final String GET_CALENDAR_BY_CODE = "getCalendarByCode";

    /**
     * retrieve new calendar of holiday date by Cod
     *
     * @param code
     * @return HolidayCalendar
     *
     */
    public static HolidayCalendar getCalendarByCode(String code) {
        return (HolidayCalendar) HibernateUtil.getObjectWithQuery("from HolidayCalendar where calendar_code ='" + code + StringUtils.QUOTE);
    }

    public static final String GET_CALENDAR_DATE_BY_CODE_AND_DATE = "getCalendarDateByCodeAndDate";

    /**
     * retrieve new calendar of holiday date by Code and dat
     *
     * @param code
     * @param date
     * @return CalendarDate
     */
    public static CalendarDate getCalendarDateByCodeAndDate(String code, Date date) {
        return (CalendarDate) HibernateUtil.getObjectWithQuery("from CalendarDate where calendar_code ='" + code + "' and calendar_date='" + HibernateUtil.dateFormat.format(date) + StringUtils.QUOTE);
    }

    public static final String LOAD_ALL_CALENDARS = "loadAllCalendars";

    /**
     * load calendar
     *
     * @return List of HolidayCalendar
     */
    public static List loadAllCalendars() {
        return HibernateUtil.getObjects("HolidayCalendar", "calendar_code");
    }

    public static final String LOAD_CALENDAR_CODES = "loadCalendarCodes";

    /**
     * load calendars with codes
     *
     * @return List of HolidayCalendar code
     */
    public static List loadCalendarCodes() {
        List resultList = new ArrayList();
        List returnList = HibernateUtil.getObjects("HolidayCalendar", "calendar_code");
        for (Object object : returnList) {
            HolidayCalendar calendar = (HolidayCalendar) object;
            resultList.add(calendar.getCalendarCode());
        }
        return resultList;
    }

    public static final String DELETE_CALENDAR = "deleteCalendar";

    /**
     * delete calendar
     *
     * @param name
     */
    public static void deleteCalendar(String name) {
        HibernateUtil.deleteObject(getCalendarByCode(name));
    }

    public static final String DELETE_CALENDAR_DATE_BY_CODE = "deleteCalendarDateByCode";

    /**
     * delete calendar by code
     *
     * @param code
     * @param date
     */
    public static void deleteCalendarDateByCode(String code, Date date) {
        HibernateUtil.deleteObject(getCalendarDateByCodeAndDate(code, date));
    }

    public static final String DELETE_CALENDAR_DATE = "deleteCalendarDate";

    /**
     * delete date from calende
     *
     * @param date
     */
    public static void deleteCalendarDate(CalendarDate date) {
        HibernateUtil.deleteObject(date);
    }

    public static HolidayCalendar mergeCalendars(HolidayCalendar cal1, HolidayCalendar cal2) {
        HolidayCalendar ret = new HolidayCalendar();
        if (cal1 != null) {
            if (cal2 != null && cal2.getCalendarDateList() != null && !cal2.getCalendarDateList().isEmpty() && !cal1.getCalendarDateList().isEmpty()) {
                List<CalendarDate> dates1 = cal1.getCalendarDateList();
                List<CalendarDate> dates2 = cal1.getCalendarDateList();
                int i1 = 0;
                int i2 = 0;
                while (i1 < dates1.size() && i2 < dates2.size()) {
                    while (dates1.get(i1).getCalendarDate().before(dates2.get(i2).getCalendarDate())) {
                        ret.getCalendarDateList().add(dates1.get(i1));
                        i1++;
                    }
                    while (dates2.get(i2).getCalendarDate().before(dates1.get(i1).getCalendarDate())) {
                        ret.getCalendarDateList().add(dates2.get(i2));
                        i2++;
                    }
                    if (dates1.get(i1).getCalendarDate().equals(dates2.get(i2).getCalendarDate())) {
                        ret.getCalendarDateList().add(dates1.get(i1));
                        i1++;
                        i2++;
                    }
                }
                while (i1 < dates1.size()) {
                    ret.getCalendarDateList().add(dates1.get(i1));
                    i1++;
                }
                while (i2 < dates2.size()) {
                    ret.getCalendarDateList().add(dates2.get(i2));
                    i2++;
                }
            } else if (cal1.getCalendarDateList() != null && (cal2 == null || cal2.getCalendarDateList() == null || cal2.getCalendarDateList().isEmpty())) {
                ret.getCalendarDateList().addAll(cal1.getCalendarDateList());
            } else if (cal2 != null && cal2.getCalendarDateList() != null && (cal1.getCalendarDateList() == null || cal1.getCalendarDateList().isEmpty())) {
                ret.getCalendarDateList().addAll(cal2.getCalendarDateList());
            }
        }
        return ret;
    }
}
