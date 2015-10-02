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
package org.gaia.dao.pricing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.gaia.dao.utils.DateUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class DayCountAccessor {

    public enum DayCount {

        ACT_365("ACT/365"),
        ACT_360("ACT/360"),
        ACT_ACT("ACT/ACT"),
        ACT_365F("ACT/365F"),
        B30_360("30/360"),
        B30E_360("30E/360"),
        EFFECTIVE_RATE("EFFECTIVE");
        public final String name;

        DayCount(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static final double JPMCDS_CONTINUOUS_BASIS = 365.;

    public static double dayCountFraction(Date startDate, Date endDate, String method) /**
     * (I) method: B30/360 B30E/360 ACT/365 (ACT/ACT)ACT/365 FIXED ACT/360
     * NL/365 FIXED (no leap) B30E/360 (Italy) 30/360SIA
     */
    {

        Date cur_date;
        Date temp;
        int start_year, end_year;
        double sign = 1.0;
        int i;
        long act_days, leap_days, non_leap_days, days_left;
        boolean isleap;
        double result = 0;

        /**
         * This duplicates some code below and dayCountDaysDiff() and is done
         * for performance reasons.
         */
        if (method.equals(DayCount.ACT_365F.name)) {
            return DateUtils.dateDiff(startDate, endDate) / 365.;
        } else if (method.equals(DayCount.ACT_360.name)) {
            return DateUtils.dateDiff(startDate, endDate) / 360.;
        }

        /**
         * Check if same date
         */
        if (startDate == endDate) {
            return 0;
        }

        /**
         * Check the date order
         */
        if (startDate.after(endDate)) {
            sign = -1.0;
            /**
             * reverse order
             */
            temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        if (method.equals(DayCount.EFFECTIVE_RATE.name)) {
            /**
             * Effective rates have a year fraction of 1.0 or -1.0, depending on
             * the order of the dates (note: if the dates are the same, the year
             * fraction is 0 (handled above).
             */
            return sign;
        }

        /**
         * Calculate the number of days between two dates
         */
        act_days = dayCountDaysDiff(startDate, endDate, method);

        if (method.equals(DayCount.B30_360.name)
                || method.equals(DayCount.B30E_360.name)
                || method.equals(DayCount.ACT_360.name)) {
            result = act_days / 360.0;
        } else if (method.equals(DayCount.ACT_365F.name)) {
            result = act_days / 365.0;
        } else if (method.equals(DayCount.ACT_365.name) || method.equals(DayCount.ACT_ACT.name)) {

            leap_days = 0;
            non_leap_days = 0;

            /**
             * weighted average of leap days divided by 366 + weighted average
             * of non-leap days divided by 365
             */
            /**
             * handle first year
             */
            days_left = daysLeftThisYear(startDate);
            GregorianCalendar gcal1 = new GregorianCalendar();
            gcal1.setTime(startDate);
            isleap = gcal1.isLeapYear(gcal1.get(Calendar.YEAR));

            if (isleap) {
                leap_days += Math.min(act_days, days_left);
            } else {
                non_leap_days += Math.min(act_days, days_left);
            }

            /**
             * loop through the years
             */
            start_year = gcal1.get(Calendar.YEAR);
            GregorianCalendar gcal2 = new GregorianCalendar();
            gcal2.setTime(endDate);
            end_year = gcal2.get(Calendar.YEAR);
            cur_date = startDate;

            /**
             * loop through full years
             */
            for (i = start_year + 1; i < end_year; i++) {
                /**
                 * check if previous year is a leap
                 */
                if (isleap) {
                    cur_date = DateUtils.addCalendarDay(cur_date, 366);
                } /**
                 * next year in days
                 */
                else {
                    cur_date = DateUtils.addCalendarDay(cur_date, 365);
                }
                /**
                 * next year in days
                 */
                /**
                 * check if new year is a leap
                 */
                GregorianCalendar gcalcur = new GregorianCalendar();
                gcalcur.setTime(cur_date);
                boolean cisleap = gcalcur.isLeapYear(gcalcur.get(Calendar.YEAR));
                if (cisleap) {
                    leap_days += 366;
                } else {
                    non_leap_days += 365;
                }
            }

            /**
             * handle last year
             */
            if (start_year != end_year) {
                isleap = gcal2.isLeapYear(gcal2.get(Calendar.YEAR));
                cur_date = DateUtils.startOfYear(endDate);

                /**
                 * number of days elapsed in the last year
                 */
                days_left = dayCountDaysDiff(cur_date, endDate, method);

                if (isleap) {
                    leap_days += days_left;
                } else {
                    non_leap_days += days_left;
                }
            }

            /**
             * calculate final day count fraction ISDA interpretation
             */
            result = leap_days / 366.0 + non_leap_days / 365.0;
        }

        return result * sign;
    }

    /**
     ***************************************************************************
     ** Calculates difference between two dates. * * Methods currently covered:
     * * - Bond basis: 30, 30E * - Actual * * This function calculates the
     * numerator for day count fraction
     * **************************************************************************
     * @param date1 start date
     * @param date2 end date
     * @param method
     * @return
     */
    public static long dayCountDaysDiff(Date date1, Date date2, String method) {

        Date temp;
        long Y1, Y2, M1, M2, D1, D2;
        int negative;
        long result;

        negative = 0;

        if (date1.after(date2)) {
            negative = 1;

            /**
             * reverse order
             */
            temp = date1;
            date1 = date2;
            date2 = temp;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Y1 = cal1.get(Calendar.YEAR);
        M1 = cal1.get(Calendar.MONTH);
        D1 = cal1.get(Calendar.DATE);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        Y2 = cal2.get(Calendar.YEAR);
        M2 = cal2.get(Calendar.MONTH);
        D2 = cal2.get(Calendar.DATE);

        if (method.equals(DayCount.B30_360.name)) {
            /**
             * D1=31 => change D1 to 30
             */
            if (D1 == 31) {
                D1 = 30;
            }

            /**
             * D2=31 and D1 is 30 or 31 => change D2 to 30
             */
            if (D2 == 31 && D1 == 30) {
                D2 = 30;
            }

            result = (Y2 - Y1) * 360 + (M2 - M1) * 30 + (D2 - D1);
        } else if (method.equals(DayCount.B30E_360.name)) {

            /**
             * D1=31 => change D1 to 30
             */
            if (D1 == 31) {
                D1 = 30;
            }

            /**
             * D2=31 => change D2 to 30
             */
            if (D2 == 31) {
                D2 = 30;
            }

            result = (Y2 - Y1) * 360 + (M2 - M1) * 30 + (D2 - D1);
        } else {

            result = DateUtils.dateDiff(date1, date2);

        }

        /**
         * negative difference ???
         */
        if (negative == 1) {
            result = -1;
        }
        return result;

    }

    /**
     ***************************************************************************
     ** Calculates the number of days left in a year.
     * **************************************************************************
     * @param date
     * @return long the number of days left in a year
     */
    public static long daysLeftThisYear(Date date) {
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.add(Calendar.YEAR, 1);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DATE, 1);

        return DateUtils.dateDiff(date, cal2.getTime());
    }
    public static final String GET_DAYCOUNTS = "getDayCounts";

    public static List<String> getDayCounts() {
        ArrayList<String> daycounts = new ArrayList();
        for (DayCount value : DayCount.values()) {
            daycounts.add(value.name);
        }
        return daycounts;
    }

    public static String[] getStringDayCounts() {
        String[] freqs = new String[DayCount.values().length];
        for (int i = 0; i < DayCount.values().length; i++) {
            freqs[i] = DayCount.values()[i].name;
        }
        return freqs;
    }
}
