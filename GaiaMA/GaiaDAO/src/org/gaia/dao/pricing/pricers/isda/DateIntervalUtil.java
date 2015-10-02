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
package org.gaia.dao.pricing.pricers.isda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
import org.gaia.dao.utils.DateInterval;
import org.gaia.dao.utils.DateUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class DateIntervalUtil {

    private static final Logger logger = Logger.getLogger(DateIntervalUtil.class);

    public static final String GET_DATE_FROM_START_AND_TENOR = "getDateFromStartAndTenor";

    public static Date getDateFromStartAndTenor(Date startDate, String tenor) {

        DateInterval interval = DateIntervalUtil.JpmcdsStringToDateInterval(tenor);
        Date date = JpmcdsDtFwdAny(startDate, interval);
        return date;
    }


    /*
     ***************************************************************************
     ** Converts a string representation ("2Y", "1S", etc) to a TDateInterval.
     ** Number optional, default is 1, hence, e.g., "M" (= "1M") is valid.
     ***************************************************************************
     */
    public static DateInterval JpmcdsStringToDateInterval(String input) {
        String inp = input;
        String nump = "";            /* Pointer to number */

        char periodType;
        int numPeriods;

        /* Copy sign,if any, to numberBuff */
        if (inp.startsWith("-") || inp.startsWith("+")) {
            nump = inp.substring(0, 1);
            inp = inp.substring(1);
        }

        /* Copy digits, if any, to numberBuff */
        while (isDigit(inp.substring(0, 1))) {
            nump = nump + inp.substring(0, 1);
            inp = inp.substring(1);
        }

        if (inp == null ? input != null : !inp.equalsIgnoreCase(input)) /* Found some digits */ {
            numPeriods = Integer.parseInt(nump);
        } else /* Found on digits */ {
            numPeriods = 1;
        }

        periodType = inp.toUpperCase().charAt(0);   /* To upper case */

        return JpmcdsMakeDateInterval(numPeriods, periodType);

    }

    public static boolean isDigit(String in) {
        try {
            Integer.parseInt(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     ***************************************************************************
     ** Makes a TDateInterval
     ***************************************************************************
     */
    public static DateInterval JpmcdsMakeDateInterval(
            int numPeriods, /* (I) Number of periods */
            char periodType) {
        DateInterval interval = new DateInterval();

        interval.flag = 0;

        switch (periodType) {
            case 'A':                       /* Year */

            case 'Y':                       /* Year */

                interval.prd_typ = 'M';
                interval.prd = numPeriods * 12;
                break;

            case 'S':                       /* Semi annual */

                interval.prd_typ = 'M';
                interval.prd = numPeriods * 6;
                break;

            case 'Q':                       /* Quarter */

                interval.prd_typ = 'M';
                interval.prd = numPeriods * 3;
                break;

            case 'W':                       /* Week */

                interval.prd_typ = 'D';
                interval.prd = numPeriods * 7;
                break;

            case 'U':                       /* Lunar period - 28 Days */

                interval.prd_typ = 'D';
                interval.prd = numPeriods * 28;
                break;

            case 'D':                       /* Day                                 */

            case 'M':                       /* Normal Month                        */

            case 'F':                       /* Flexible End of month               */

            case 'E':                       /* End of month unconditional          */

            case 'B':                       /* Beginning of month unconditional          */

            case 'G':                       /* 29th of month                       */

            case 'H':                       /* 30th of month                       */

            case 'I':                       /* IMM date                            */

            case 'J':                       /* monthly IMM date                    */

            case 'K':                       /* Aussie quarterly IMM date           */

            case 'L':                       /* Kiwi quarterly IMM date             */

            case 'T':                       /* equity derivative expiry 3rd Friday */

            case 'V':                       /* virtual IMM dates (fortnightly)     */

                interval.prd_typ = periodType;
                interval.prd = numPeriods;
                break;

            default:
                logger.error("Interval type is not valid" + periodType);
        }

        return interval;
    }

    /*
     ***************************************************************************
     ** Moves forward by the specified interval.
     ***************************************************************************
     */
    public static Date JpmcdsDtFwdAny(Date startDate, /* (I) date */
            DateInterval interval) {
        //TMonthDayYear        mdy;
        DateInterval intval = null;
        char upperPrdTyp;
        Date sumDate = null;

        intval = new DateInterval(0, '?', 0);

        Calendar cal = new GregorianCalendar();

        upperPrdTyp = interval.prd_typ;

        switch (upperPrdTyp) {
            case 'M':                     /* MONTHly increments */

            case 'A':                     /* ANNUAL increments */

            case 'Y':                     /* YEARly increments */

            case 'S':                     /* SEMIANNUALL increments */

            case 'Q':                     /* QUARTERly increments */

                /* First reduce all of these types to monthly. Note that we shouldn't
                 * really need code to handle A,S & Q, since JpmcdsMakeDateInterval
                 * converts all of them to 'M' anyway. This code is left here for
                 * people who have set up their own DateIntervals without calling
                 * JpmcdsMakeDateInterval or one of the supplied macros. The month
                 * type is checked first for efficiency reasons.
                 */
                intval.flag = 0;
                intval.prd_typ = 'M';           /* months */

                if (upperPrdTyp == 'M') {
                    intval.prd = interval.prd;
                } else if (upperPrdTyp == 'A' || interval.prd_typ == 'Y') {
                    intval.prd = interval.prd * 12;
                } else if (upperPrdTyp == 'S') {
                    intval.prd = interval.prd * 6;
                } else {
                    intval.prd = interval.prd * 3;
                }
                cal.setTime(startDate);
                cal.add(Calendar.MONTH, intval.prd);

                sumDate = cal.getTime();
                break;

            case 'D':                         /* Daily increments */

                sumDate = DateUtils.addOpenDay(startDate, interval.prd);
                break;

            case 'T':                         /* T/N */

                sumDate = DateUtils.addOpenDay(startDate, 2);
                break;

            case 'W':
                sumDate = DateUtils.addOpenDay(startDate, interval.prd * 5);
                break;                          /* WEEKly increments */

            default:
                logger.error("Period type unknown " + interval.prd_typ);
        }
        return sumDate;
    }


    /*
     ***************************************************************************
     ** Converts a frequency (# times/year) to a TDateInterval
     ** Returns SUCCESS/FAILURE.
     ***************************************************************************
     */
    public static TDateInterval JpmcdsFreq2TDateInterval(long freq) /* (O) */ {
        TDateInterval ret = new TDateInterval();

        if (freq > 0 && freq <= 12) {
            ret.prd = 12 / (int) freq;
            ret.prd_typ = 'M';
        }
        return ret;
    }


    /*
     ***************************************************************************
     ** Converts a TDateInterval to # years. Note that if the TDateInterval is a
     ** month type (A,S,Q), the routine uses 30/360 to compute the year fraction.
     ** If it is a day type (D,W), it uses Act/365F.
     ***************************************************************************
     */
    public static double JpmcdsDateIntervalToYears(TDateInterval interval) /* (O) # times per year */ {
        double years = 0;

        switch (interval.prd_typ) {
            case 'A':
            case 'Y':
                years = (double) interval.prd; /* 30/360 */

                break;
            case 'S':
                years = (double) (interval.prd) / 2.; /* 30/360 */

                break;
            case 'Q':
            case 'I':
            case 'K':
            case 'L':
                years = (double) (interval.prd) / 4.; /* 30/360 */

                break;
            case 'M':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'J':
            case 'T':
                years = (double) (interval.prd) / 12; /* 30/360 */

                break;
            case 'W':
                years = (double) interval.prd
                        * (double) 7 / 365; /* Act/365F*/

                break;
            case 'D':
                years = (double) interval.prd / 365; /* Act/365F */

                break;
            case 'U':
                years = (double) interval.prd
                        * (double) 28 / 365; /* Act/365F*/

            default:

        }
        return years;
    }

    /*
     ***************************************************************************
     ** Moves forward by the specified interval.
     ***************************************************************************
     */
    public static Date JpmcdsDtFwdAny(Date startDate, /* (I) date */
            TDateInterval interval /* (I) dateInterval */
    /* (O) date + dateInterval */
    ) {
        Date sumDate = new Date();
        long mdy;
        TDateInterval intval = new TDateInterval();
        char upperPrdTyp;

        upperPrdTyp = (char) interval.prd_typ;
        switch (upperPrdTyp) {
            case 'M':                     /* MONTHly increments */

            case 'A':                     /* ANNUAL increments */

            case 'Y':                     /* YEARly increments */

            case 'S':                     /* SEMIANNUALL increments */

            case 'Q':                     /* QUARTERly increments */

                /* First reduce all of these types to monthly. Note that we shouldn't
                 * really need code to handle A,S & Q, since JpmcdsMakeDateInterval
                 * converts all of them to 'M' anyway. This code is left here for
                 * people who have set up their own TDateIntervals without calling
                 * JpmcdsMakeDateInterval or one of the supplied macros. The month
                 * type is checked first for efficiency reasons.
                 */
                intval.flag = 0;
                intval.prd_typ = 'M';           /* months */

                if (upperPrdTyp == 'M') {
                    intval.prd = interval.prd;
                } else if (upperPrdTyp == 'A' || interval.prd_typ == 'Y') {
                    intval.prd = interval.prd * 12;
                } else if (upperPrdTyp == 'S') {
                    intval.prd = interval.prd * 6;
                } else {
                    intval.prd = interval.prd * 4;
                }

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.MONTH, intval.prd);
                sumDate = cal.getTime();

            case 'D':                         /* DAYly increments */

                sumDate = DateUtils.addCalendarDayLong(startDate, interval.prd);
                break;

            case 'W':
                sumDate = DateUtils.addCalendarDayLong(startDate, interval.prd * 7);
                break;                          /* WEEKly increments */

            default:

        }
        return sumDate;
    }

}
