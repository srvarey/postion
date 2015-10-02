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
package org.gaia.dao.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.domain.referentials.CalendarDate;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.utils.HibernateUtil;

/**
 *
 * @author Benjamin Frerejean
 */
public class DateUtils {

    /**
     * Use following business day
     */
    public static final String ADJUSTMENT_FOLLOW = "Following";
    /**
     * Use previous business day
     */
    public static final String ADJUSTMENT_PREVIOUS = "Previous";
    /**
     * Dont check for business days
     */
    public static final String ADJUSTMENT_NONE = "No Adjustment";
    /**
     * Try following business day, except when changing month
     */
    public static final String ADJUSTMENT_MODIFIED_FOLLOWING = "Modified Following";
    public static final SimpleDateFormat dftime = new SimpleDateFormat("HH:mm:ss.SSS");

    public enum DateAdjustment {

        ADJUSTMENT_FOLLOW("Following"),
        ADJUSTMENT_PREVIOUS("Previous"),
        ADJUSTMENT_NONE("No Adjustment"),
        ADJUSTMENT_MODIFIED_FOLLOWING("Modified Following");

        public String name;

        DateAdjustment(String name) {
            this.name = name;
        }

        public static List<String> getDateAdjustments() {
            List<String> ret = new ArrayList();
            for (DateAdjustment adjustment : DateAdjustment.values()) {
                ret.add(adjustment.name);
            }
            return ret;
        }

    };

    /**
     * if a month boundary is crossed.
     *
     * @return
     */
    public static Date getDate() {
        return removeTime(new Date());
    }

    /**
     * remove Time of a date
     *
     * @param date
     * @return
     */
    public static Date removeTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date addCalendarDay(Date date, Short n) {
        if (n == null) {
            return date;
        }
        return addCalendarDay(date, Integer.parseInt(n.toString()));
    }

    public static Date addCalendarDay(Date date, Integer n) {
        if (n == null || date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    public static String GET_DATE_RULES = "getDateRules";

    public static List<String> getDateRules() {
        return DomainValuesAccessor.getDomainValuesByName("DateRules");
    }

    public static String GET_HIBERNATE_DATE_FORMAT = "getHibernateDateFormat";

    public static DateFormat getHibernateDateFormat() {
        return HibernateUtil.dateFormat;
    }
    /**
     * add years to a date
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addYear(Date date, int n) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * add months to a date
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addMonth(Date date, int n) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * add calendar day
     * @param date
     * @param n
     * @return
     */
    public static Date addCalendarDayLong(Date date, long n) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = (int) n;
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    /**
     * add open days (i.e. non week ends) to the date
     *
     * @param date
     * @param nbDays *
     * @return
     */
    public static Date addOpenDay(Date date, long nbDays) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = 0;
        if (nbDays > 0) {
            while (i < nbDays) {
                cal.add(Calendar.DATE, 1);
                if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    i++;
                }
            }
        } else if (nbDays < 0) {
            while (i > nbDays) {
                cal.add(Calendar.DATE, -1);
                if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    i--;
                }
            }
        }
        return cal.getTime();
    }

    public static Date addOpenDay(Date date, long nbDays, HolidayCalendar calendar) {
        Date ret = addOpenDay(date, nbDays);
        adjustDate(ret, DateUtils.ADJUSTMENT_FOLLOW, calendar);
        return ret;
    }

    public static long dateDiff(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            double diff = (date2.getTime() - date1.getTime()) / 86400000.0;
            return Math.round(diff);
        }
        return 0;
    }

    public static String getTime() {
        return dftime.format(new Date());
    }

    /**
     ***************************************************************************
     ** Adds dates to a TDateList. * * If the original date list and date list
     * to be added are sorted, then * the resulting date list will be sorted and
     * duplicate dates will be * removed. Sorting assumes ascending order ([0] <
     * [1] etc). * * If either of the inputs are not sorted, then the resulting
     * date list * will not be sorted, and some duplicates may remain. * * For
     * efficiency, we do not automatically try to sort the resulting * date list
     * for unsorted inputs. Sorting the date list each time appears * to be a
     * huge performance issue in some algorithms (where the input * dates would
     * all be sorted anyway). * * Note that if dl is NULL, then this will create
     * a new date list from * the given dates. * * Note that if numItems=0, this
     * will copy the given date list.
     * **************************************************************************
     * @param dl
     * @param numItems
     * @param array
     * @return
     */
    @SuppressWarnings("empty-statement")
    public static ArrayList<Date> dateListAddDates(ArrayList<Date> dl, /* (I) Initial date list            */
            int numItems, /**
             * (I) Number of dates to add
             */
            ArrayList<Date> array) /**
     * (I) [numItems] Dates to be added
     */
    {

        ArrayList<Date> result;

        if (dl == null) {
            result = array;
        } else if (numItems <= 0) {
            result = dl;
        } else if (dl.isEmpty() && numItems == 0) {
            result = new ArrayList();;
        } else {
            int totalItems = dl.size() + numItems;
            int i = 0;
            int j = 0;
            int k = 0;

            result = new ArrayList(totalItems);

            while (i < dl.size() && j < numItems) {
                if (dl.get(i) == array.get(j)) {
                    /**
                     * exclude duplicates
                     */
                    ++j;
                    --totalItems;
                } else if (dl.get(i).before(array.get(j))) {
                    result.set(k, dl.get(i));
                    ++i;
                    ++k;
                } else {
                    result.set(k, array.get(j));
                    ++j;
                    ++k;
                }
            }

            if (i < dl.size()) {
                int n = dl.size() - j;
                for (int l = i; l < dl.size(); l++) {
                    result.add(dl.get(l));
                }

                k += n;
            }

            if (j < numItems) {
                for (int l = j; l < numItems; l++) {
                    result.add(array.get(l));
                }
                int n = numItems - j;

                k += n;
            }

        }
        return result;
    }

    /**
     ***************************************************************************
     ** Truncates a date list at the specified date. The resulting date list *
     * will contain all dates previous to (or following) the specified date. *
     * Dates in the datelist which match the specified date may be optionally *
     * included. * * The datelist may optionally be modified in place or a new
     * copy is * returned. * * The input date list must be sorted.
     * **************************************************************************
     * @param dateList
     * @param truncationDate
     * @param inclusive
     * @param excludeBefore
     * @param inPlace
     * @return
     */
    public static ArrayList<Date> DateListTruncate(ArrayList<Date> dateList, /**
             * (I/O) Date list to be modified in place
             */
            Date truncationDate, /**
             * (I) Date on which to perform trunctation
             */
            boolean inclusive, /**
             * (I) TRUE=include truncation date if in list
             */
            boolean excludeBefore, /**
             * (I) TRUE=exclude dates before truncation date
             */
            boolean inPlace /**
     * (I) TRUE=modify date list in place
     */
    ) {

        ArrayList<Date> truncated;
        int numItems;
        int size;
        int offset;
        int truncatePt;
        int i;

        /**
         * First we find the truncation point in the date list and the size of
         * the new date list
         */
        /**
         * perhaps we could do this more efficiently with a binary search - but
         * this is the code which was imported and therefore should work
         */
        numItems = dateList.size();
        if (excludeBefore) {
            truncatePt = 0;
            for (i = 0; i < numItems; i++) {
                if (dateList.get(i).after(truncationDate)) {
                    truncatePt = i;
                    break;
                }
                if (inclusive && dateList.get(i) == truncationDate) {
                    truncatePt = i;
                    break;
                }
            }
            size = numItems - truncatePt;
            offset = truncatePt;
        } else {
            truncatePt = numItems - 1;
            for (i = numItems - 1; i > 0; i--) {
                if (dateList.get(i).before(truncationDate)) {
                    truncatePt = i;
                    break;
                }
                if (inclusive && dateList.get(i) == truncationDate) {
                    truncatePt = i;
                    break;
                }
            }
            size = truncatePt + 1;
            offset = 0;
        }

        /**
         * Next we get a pointer to the datelist where we store the result
         */
        if (inPlace) {
            truncated = dateList;
        } else {
            truncated = new ArrayList(size);
        }

        /**
         * finally we populate the result
         */
        if (!inPlace || offset != 0) {
            for (i = 0; i < size; i++) {
                truncated.add(dateList.get(i + offset));
            }
        }

        return truncated;
    }

    /**
     * *************************************************************************
     ** Using business day conventions (Following, Preceding, and Modified *
     * Following) calculates the next bisiness day if the input date is not a *
     * business day. Input and Output dates are represented as TDate types.
     * ***********************************************************************
     * @param date
     * @param holiday
     * @param method
     * @return
     */
    public static Date adjustDate(Date date, String method, HolidayCalendar holiday) {
        if (date == null) {
            return null;
        }
        Date outDate = (Date) date.clone();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int intervalSign = 1;

        /**
         * determine whether we should do anything
         */
        if (method.equals(ADJUSTMENT_NONE)) {
            return outDate;
        }
        List<CalendarDate> dateList = null;
        if (holiday != null) {
            dateList = holiday.getCalendarDateList();
        }
        switch (method) {
            case ADJUSTMENT_FOLLOW:
            case ADJUSTMENT_MODIFIED_FOLLOWING:
                intervalSign = +1;
                break;
            case ADJUSTMENT_PREVIOUS:
                intervalSign = -1;
                break;
        }
        if (dateList == null || dateList.isEmpty()) {
            while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                outDate = addOpenDay(outDate, intervalSign);
                cal.setTime(outDate);
            }
            return outDate;

        } else {
            int i = 0;
            while (i < dateList.size() && dateList.get(i).getCalendarDate().after(date)) {
                i++;
            }
            if (i > 0) {
                i--;
            }
            while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                    || dateList.get(i).getCalendarDate().equals(date)) {
                outDate = addCalendarDay(outDate, intervalSign);
                cal.setTime(outDate);
            }
            if (method.equals(ADJUSTMENT_MODIFIED_FOLLOWING)) {
                Calendar calout = new GregorianCalendar();
                calout.setTime(outDate);
                if (cal.get(Calendar.MONTH) != calout.get(Calendar.MONTH)) {
                    outDate = adjustDate(date, ADJUSTMENT_PREVIOUS, holiday);
                    cal.setTime(outDate);
                }
            }

        }
        return outDate;

    }

    /**
     ********************************************************
     ** Given a date in date, retrieve beginning of the year
     * *******************************************************
     * @param date
     * @return
     */
    public static Date startOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);

        return cal.getTime();
    }

    public static Date startOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     ********************************************************
     ** Given a date in date, retrieve end of the year
     * *******************************************************
     * @param date
     * @return
     */
    public static Date endOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        Date st = startOfYear(cal.getTime());
        return DateUtils.addCalendarDay(st, -1);
    }

    public static Date endOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date st = startOfMonth(cal.getTime());
        return DateUtils.addCalendarDay(st, -1);
    }

    public static boolean isBusinessDay(Calendar calendar, Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
    }

    public static List<Date> getCommonFromDateLists(List<Date> dates1, List<Date> dates2) {
        List<Date> res = new ArrayList(dates1);

        for (Date d : dates2) {
            if (!dates1.contains(d)) {
                res.remove(d);
            }
        }
        List<Date> toRemove = new ArrayList();
        for (Date d : res) {
            if (!dates2.contains(d)) {
                toRemove.add(d);
            }
        }
        res.removeAll(toRemove);
        return res;

    }
}
