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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.referentials.HolidayCalendar;

/**
 *
 * @author Benjamin Frerejean
 */
public class CurveUtilsIsda {

    public static final String INTERP_LINEAR = "Linear";
    /* Interpolation methods which can be applied to a zero curve only. */
    public static final String INTERP_LINEAR_FORWARDS = "LinearForwards";     /* Linear forward interpolation  */

    public static final String INTERP_FLAT_FORWARDS = "FlatForwards";    /* Flat forward interpolation  */

    private static final Logger logger = Logger.getLogger(CurveUtilsIsda.class);

    /*
     ***************************************************************************
     ** Build zero curve from money market, and swap instruments.
     ***************************************************************************
     */
    public static IsdaCurve JpmcdsBuildIRZeroCurve(
            Date valueDate, /* (I) Value date                       */
            String instrNames, /* (I) Array of 'M' or 'S'              */
            Date[] dates, /* (I) Array of swaps dates             */
            double[] rates, /* (I) Array of swap rates              */
            long nInstr, /* (I) Number of benchmark instruments  */
            String mmDCC, /* (I) DCC of MM instruments            */
            long fixedSwapFreq, /* (I) Fixed leg freqency               */
            long floatSwapFreq, /* (I) Floating leg freqency            */
            String fixedSwapDCC, /* (I) DCC of fixed leg                 */
            String floatSwapDCC, /* (I) DCC of floating leg              */
            String badDayConv, /* (I) Bad day convention               */
            HolidayCalendar holidayFile) /* (I) Holiday file                     */ {

        int i;
        int nCash = 0;
        int nSwap = 0;
        char fwdLength = '3';   /* not used */

        char instr;
        int n = (int) nInstr;

        Date[] cashDates = new Date[n];
        Date[] swapDates = new Date[n];
        double[] cashRates = new double[n];
        double[] swapRates = new double[n];
        IsdaCurve zcurveIni;
        IsdaCurve zcurveCash;
        IsdaCurve zcurveSwap;

        if (cashDates == null || swapDates == null || cashRates == null || swapRates == null) {
            return null;
        }

        /* Sort out cash and swap separately */
        for (i = 0; i < nInstr; i++) {
            instr = instrNames.charAt(i);
            if (instr != 'M' && instr != 'S') {
                return null;
            }

            if (instr == 'M') {    /* MM Rate */

                cashDates[nCash] = dates[i];
                cashRates[nCash] = rates[i];
                nCash++;
            } else /* Swap Rate */ {
                swapDates[nSwap] = dates[i];
                swapRates[nSwap] = rates[i];
                nSwap++;
            }
        }

        /* Initialize the zero curve */
        zcurveIni = new IsdaCurve();
        zcurveIni.setDayCountConv(DayCountAccessor.DayCount.ACT_365F.name);
        zcurveIni.setBasis(1);
        zcurveIni.setBaseDate(valueDate);
        zcurveIni.setDates(new ArrayList());
        zcurveIni.setRates(new ArrayList());
        zcurveIni.setDiscount(new ArrayList());

        /* Cash instruments */
        zcurveCash = JpmcdsZCCash(zcurveIni, cashDates, cashRates, nCash, mmDCC);
        if (zcurveCash == null) {
            return null;
        }

        /* Swap instruments */
        zcurveSwap = JpmcdsZCSwaps(zcurveCash,
                null, /* discZC */
                swapDates,
                swapRates,
                nSwap,
                (int) fixedSwapFreq,
                (int) floatSwapFreq,
                fixedSwapDCC,
                floatSwapDCC,
                fwdLength,
                badDayConv,
                "",
                holidayFile);

        return zcurveSwap;
    }
    /*
     ***************************************************************************
     ** Adds cash points to a given zero curve.
     ***************************************************************************
     */

    public static IsdaCurve JpmcdsZCCash(
            IsdaCurve zeroCurve, /* (I) Zero curve to add to             */
            Date[] dates, /* (I) Array of cash dates              */
            double[] rates, /* (I) Array of cash rates              */
            int numRates, /* (I) Number of cash rates to add      */
            String dayCountConv) /* (I) See JpmcdsDayCountConvention        */ {

        IsdaCurve tcCash;
        IsdaCurve zcCash = null;
        IsdaCurve zcStub=null;

        if (numRates == 0) /* nothing to add */ {
            return zeroCurve;
        }

//   if (CheckZCCashInputs(zeroCurve, dates, rates, numRates, dayCountConv) == FAILURE)
//      goto done;

        /* convert the input TCurve into a ZCurve so we can call all the usual
         zero curve routines */
        if (zeroCurve.getNumItems() > 0) /* only want it if we need it */ {
//       zcStub = JpmcdsZCFromTCurve(zeroCurve);
            zcStub = zeroCurve;
        }

        zcCash = new IsdaCurve();
        zcCash.setBaseDate(zeroCurve.getBaseDate());
        zcCash.setBasis(1);
        zcCash.setDayCountConv(DayCountAccessor.DayCount.ACT_365F.name);
        zcCash.setDates(new ArrayList());
        zcCash.setRates(new ArrayList());
        zcCash.setDiscount(new ArrayList());
//   zcCash = JpmcdsZCMake(zeroCurve->fBaseDate, numRates + 2, ZC_DEFAULT_BASIS, ZC_DEFAULT_DAYCNT);

        try {
            JpmcdsZCAddMoneyMarket(zcCash, dates, rates, numRates, dayCountConv);
            /* combine the stub curve with the cash part */
            if (zcStub != null) {
                JpmcdsZCAddPrefixCurve(zcCash, zcStub);
                JpmcdsZCAddSuffixCurve(zcCash, zcStub);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//   tcCash = JpmcdsZCToTCurve(zcCash);
        tcCash = zcCash;
        return tcCash;
    }


    /*
     ***************************************************************************
     ** Adds swap points to a given zero curve.
     ***************************************************************************
     */
    public static IsdaCurve JpmcdsZCSwaps(
            IsdaCurve zeroCurve, /* (I) Zero curve to add swap points to*/
            IsdaCurve discZC, /* (I) Zero curve used for discounting */
            Date[] dates, /* (I) Unadjusted swap maturity dates  */
            double[] rates, /* (I) Swap par fixed rates (0.06=6%)  */
            int numSwaps, /* (I) Len of dates,rates,prices       */
            int fixedSwapFreq, /* (I) Fixed leg coupon frequency      */
            int floatSwapFreq, /* (I) Floating leg coupon frequency   */
            String fixDayCountConv, /* (I) See JpmcdsDayCountConvention       */
            String floatDayCountConv, /* (I) See JpmcdsDayCountConvention       */
            char fwdLength, /* (I) For fwd smoothing, length of fwds*/
            String badDayConv, /* (I) Bad day conv + stub pos         */
            String stubPos,
            HolidayCalendar holidayFile) /* (I) See JpmcdsBusinessDay              */ {

        int offset;              /* offset in array of swap rates        */

        TInterpData interpData = null;    /* interpolation data for zero rates    */

        Date lastStubDate;
        IsdaCurve tcSwaps;
        IsdaCurve zcSwaps;
        TBadDayList badDayList = null;

        if (numSwaps == 0) /* nothing to add */ {
            return zeroCurve;
        }

        /* convert the input TCurve into a ZCurve so we can call all the usual zero curve routines */
//   zcSwaps = JpmcdsZCFromTCurve(zeroCurve);
        zcSwaps = zeroCurve;

        /* only want to add swap points not already covered by stub curve,
         don't want to replace anything in the existing curve */
        if (zeroCurve.getNumItems() < 1) {
            lastStubDate = zeroCurve.getBaseDate();
        } else {
            lastStubDate = zeroCurve.getDates().get(zeroCurve.getNumItems() - 1);
        }

        offset = 0;
        while (numSwaps > 0 && dates[offset].before(lastStubDate)) {
            offset++;
            numSwaps--;
        }

        if (numSwaps > 0) {
            Date[] datesOffsettees = new Date[dates.length - offset];
            double[] ratesOffsettes = new double[rates.length - offset];
            for (int i = 0; i < dates.length - offset; i++) {
                datesOffsettees[i] = dates[i - offset];
                ratesOffsettes[i] = rates[i - offset];
            }

            JpmcdsZCAddSwaps(zcSwaps,
                    discZC,
                    datesOffsettees,
                    ratesOffsettes,
                    numSwaps,
                    fixedSwapFreq,
                    floatSwapFreq,
                    fixDayCountConv,
                    floatDayCountConv,
                    INTERP_FLAT_FORWARDS,
                    interpData,
                    badDayList,
                    badDayConv,
                    stubPos,
                    holidayFile);
        }

        tcSwaps = zcSwaps;
        return tcSwaps;
    }


    /*
     ***************************************************************************
     ** Adds a strip of swap instruments to a ZCurve.
     **
     ** The zero interpolation kicks in whenever a point is calculated to
     ** determine the discount factor for a cash flow, deep inside the objective
     ** function called by the root finder, which calls JpmcdsZCInterpolate() to
     ** determine the appropriate discount factor.
     **
     ** In this routine, there are two mutually exclusive ways to specify
     ** bad days: badDayList, and badDayConv/holidayFile. If badDayList is used,
     ** bad business day lists can be used to make sure swap dates near to
     ** canonical dates (i.e. integral number of years from start date) are
     ** properly used for coupon dates of later instruments.  Say the 2-year
     ** point has been adjusted because of holidays to be 3 days later.  The
     ** coupon at 2-years for the 3 (and subsequent) swap instruments must be
     ** adjusted to that date.  To do this, the badDayList contains a list of
     ** <good,bad> day pairs, which are used to adjust dates to/from bad
     ** business days.
     **
     ** INPUTS: ZCurve to add swaps to, maturity dates and rates for them, how many
     ** there are, coupon frequency: annual or semi-annual, dayCountConvention,
     ** interpolation method and data, bad business day list.
     ** WARNING: In this routine, there are two mutually exclusive ways to specify
     ** bad days: badDayList, and badDayConv/holidayFile.
     ** If badDayList is non-NULL, the maturity dates passed in must be
     ** adjusted. If the badDayConv is not JPMCDS_BAD_DAY_NONE, the maturity dates
     ** passed in must be UN-adjusted.
     **
     ** OUTPUTS: Extended zero curve.
     ***************************************************************************
     */
    public static void JpmcdsZCAddSwaps( /* adds a strip of swaps to ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add to        */
            IsdaCurve discZC, /* (I) Discount zero curve     */
            Date[] inDates, /* (I) MatDates; adj. if badDayList!=0*/
            double[] inRates, /* (I) Fixed rates (0.06 for 6%) */
            int numSwaps, /* (I) # instruments to add    */
            int fixedSwapFreq, /* (I) Fixed leg freq          */
            int floatSwapFreq, /* (I) Floating leg freq       */
            String fixDayCountConv, /* (I) Convention for fixed leg*/
            String floatDayCountConv,/* (I) Convention for float leg*/
            String interpType, /* (I) Zero interpolation method */
            TInterpData interpData, /* (I) Zero interpolation data */
            TBadDayList badDayList, /* (I) Bad-good day pairs; if non-NULL */
            /*     maturity dates must be adjusted */
            String badDayConv, /* (I) See JpmcdsBusinessDay      */
            String stubPos, /* (I) See JpmcdsBusinessDay      */
            HolidayCalendar holidayFile) /* (I) See JpmcdsBusinessDay      */ {
        boolean oneAlreadyAdded;        /* If a swap was already added */

        int i;                      /* loops over all instruments */

        TSwapDates swapDates;         /* Dates from caller */

        double[] swapRates;
//        Date[] newDates = null;        /* Swap dates w/ synthetics */
//        double[] newRates = null;        /* Swap rates w/ synthetics */
//        boolean useFastZC = false;      /* whether to use swap zc */

        if (zc == null) {
            return;
        }

        if (badDayList != null && !badDayConv.equals("NONE")) {
            return;
        }

        /* Set up TSwapDates with input swap maturity dates. */
        if (badDayList != null) /* This means dates are adjusted already */ {
            swapDates = JpmcdsSwapDatesNewFromAdjusted(zc.getBaseDate(), fixedSwapFreq, inDates, numSwaps, badDayList);
            if (swapDates == null) {
                return;
            }
        } else /* Dates NOT adjusted */ {
            swapDates = JpmcdsSwapDatesNewFromOriginal(zc.getBaseDate(), fixedSwapFreq, inDates, numSwaps, badDayList, badDayConv, holidayFile);

            if (swapDates == null) {
                return;
            }
        }

        /* Just to keep date/rate names the same. */
        swapRates = inRates;

        oneAlreadyAdded = false;

        /* Add individual swap instruments  */
        for (i = 0; i < swapDates.getNumDates(); i++) {
            /* Add those beyond stub zero curve
             */
            if (zc.getDates().isEmpty() || swapDates.getAdjusted()[i].after(zc.getDates().get(zc.getNumItems() - 1))) {
                /* Check if optimization okay. Note linear forwards
                 * not OK because they must include intermed. fwds.
                 */
                if (oneAlreadyAdded
                        && discZC == null
                        && swapRates[i - 1] != 0.0
                        && swapDates.getAdjusted()[i - 1].equals(zc.getDates().get(zc.getNumItems() - 1))
                        && swapDates.getPrevious()[i].equals(swapDates.getOriginal()[i - 1])
                        && swapDates.getOnCycle()[i]
                        && !interpType.equalsIgnoreCase(INTERP_LINEAR_FORWARDS)) {
                    /* Optimization: compute from last
                     */
                    AddSwapFromPrevious(zc, swapDates.getAdjusted()[i], swapRates[i], 1.0, swapDates.getAdjusted()[i - 1], swapRates[i - 1], 1.0, fixDayCountConv);
                } else /* No efficiency */ {
                    JpmcdsZCAddSwap(zc, discZC,
                            1.0,
                            swapDates.getOriginal()[i],
                            swapDates.getOnCycle()[i],
                            swapRates[i],
                            fixedSwapFreq, floatSwapFreq,
                            fixDayCountConv, floatDayCountConv,
                            interpType, interpData,
                            badDayList, badDayConv,
                            stubPos, holidayFile);
                    oneAlreadyAdded = true;
                } /* else  */

            } /* if (swapDates->adjusted[i] > zc->date[zc
             * -->numItems-1]) */

        } /* for (i=0;  i < swapDates->numDates; i++)  */


    }

    /*
     ***************************************************************************
     ** Adds a single swap instrument to a ZCurve.
     **
     ** ZCurve is updated to contain swap information (with points at each of
     ** the coupon dates, as well as the maturity date).
     ***************************************************************************
     */
    public static void JpmcdsZCAddSwap( /* adds a swap to ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add to          */
            IsdaCurve discZC, /* (I) ZC used for discounting */
            double price, /* (I) Par price: usually 1.0    */
            Date matDate, /* (I) Unadjusted Maturity date  */
            boolean onCycle, /* (I) If matDate on cycle       */
            double rate, /* (I) Coupon rate (0.06 for 6%) */
            int fixedSwapFreq, /* (I) Fixed coupon frequency    */
            int floatSwapFreq, /* (I) Floating coupon frequency */
            String fixDayCountConv, /* (I) Fixed leg convention      */
            String floatDayCountConv, /* (I) Floating leg convention   */
            String interpType, /* (I) Interpolation method type */
            TInterpData interpData, /* (I) Interpolation data        */
            TBadDayList badDayList, /* (I) List of bad-business days */
            String badDayConv, /* (I) Bad day conv + stub pos   */
            String stubPos, /* (I) Bad day conv + stub pos   */
            HolidayCalendar holidayFile) /* (I) See JpmcdsBusinessDay        */ {
        Date adjMatDate;          /* Mat date adjusted to be good date */

        TCashFlowList cfl = null;          /* Cash flow list representing swap*/

        TDateInterval ivl;

        /* If floating side at par. */
        if (discZC == null) {
            boolean isEndStub;
            /* Here we must pass in the unadjusted matDate, so that the
             * cashflow dates are correctly generated.
             */
            ivl = DateIntervalUtil.JpmcdsFreq2TDateInterval(fixedSwapFreq);

            if (onCycle) {
                isEndStub = true;
            } else {
                isEndStub = JpmcdsIsEndStub(zc.getBaseDate(), matDate, ivl, stubPos);
            }
            cfl = JpmcdsZCGetSwapCFL(zc.getBaseDate(),
                    matDate, isEndStub,
                    rate,
                    ivl,
                    fixDayCountConv,
                    badDayList, badDayConv, holidayFile);
            if (cfl == null) {
                return;
            }

            /* Adjust matDate to be a good business day, store it in adjMatDate.
             */
            adjMatDate = JpmcdsZCAdjustDate(matDate, badDayList, badDayConv, holidayFile);


            /* Add rate implied by cashflow list to the zeroCurve.
             */
            JpmcdsZCAddCashFlowList(zc, cfl, price, adjMatDate, interpType, interpData);


            /* Add zero rates at all dates in cfl.
             * This guarantees that regardless of the interpolation used when
             * pricing one of the original benchmark swaps with the zero curve
             * created by this routine, the swap will be priced to par.
             * Since JpmcdsZCInterpolate does not support linear forwards,
             * we switch to linear interp here if linear fwds was specified.
             */
            JpmcdsZCAddCFLPoints(zc, cfl, new Date(), interpType.equalsIgnoreCase(INTERP_LINEAR) ? INTERP_LINEAR : interpType, interpData);
        } else /* Need to value floating side */ {
//       JpmcdsZCValueFixFltSwap
//           (zc, discZC,
//            price, matDate, rate,
//            fixedSwapFreq, floatSwapFreq,
//            fixDayCountConv, floatDayCountConv,
//            interpType, interpData,
//            badDayConv,stubPos, holidayFile);
        } /* ValueFloating */

    }
    /*
     ***************************************************************************
     ** Models the swap points which are to be added to the zero curve using the
     ** more detailed swap structures.
     ***************************************************************************
     */
//public static void JpmcdsZCValueFixFltSwap
//(IsdaCurve         zc,                 /* (M) ZCurve to add to           */
// IsdaCurve           discZC,             /* (I) Discount zero curve        */
// double            price,              /* (I) Par price: usually 1.0     */
// Date             matDate,            /* (I) Maturity date              */
// double            rate,               /* (I) Coupon rate (0.06 for 6%)  */
// int               fixedSwapFreq,      /* (I) Fixed coupon frequency     */
// int               floatSwapFreq,      /* (I) Floating coupon frequency  */
// String              fixDayCountConv,    /* (I) Fixed leg convention       */
// String              floatDayCountConv,  /* (I) Floating leg convention    */
// String              interpType,         /* (I) Interpolation method       */
// TInterpData      interpData,         /* (I) Interpolation data         */
// String          badDay,   /* (I) See JpmcdsBusinessDay         */
// String          stubPos,   /* (I) See JpmcdsBusinessDay         */
// HolidayCalendar    holidayFile)       /* (I) See JpmcdsBusinessDay         */
//
//{
//   Date[]    floatDL = null;
//   Date[]    fixedDL = null;
//   float[]       floatStream = null;
//   float[]       fixedStream = null;
//   TDateInterval      floatInterval;
//   TDateInterval      fixedInterval;
//   TDateInterval      payInterval;
//   double         firstCoupon;
//   TDateInterval         firstCouponInterval;
//   double         finalCoupon;
//   TDateInterval         finalCouponInterval;
//   float         floatingRate;
//   TCashFlowList      fixedCFL = null;
//   TCashFlowList      floatCFL = null;
//   TCashFlowList      swapCFL = null;
////   TPfunctionParams   funcParams;
//   IsdaCurve             tc = null;
//   Date              indexMatDate;
//   Date              minDate;          /* Min of pay and index mat date  */
//   Date              maxDate;          /* Max of pay and index mat date */
//   long               badDayConv;
////   TStubPos           stubPos;
//   boolean           isEndStub;
//
//
//
//   /* Convert the frequency to date intervals */
//   fixedInterval=DateIntervalUtil.JpmcdsFreq2TDateInterval((long) fixedSwapFreq);
//   floatInterval=DateIntervalUtil.JpmcdsFreq2TDateInterval((long) floatSwapFreq);
//
//   /* make sure that the pay interval that is contained in the floating rate
//      is bigger than the maturity interval or otherwise JpmcdsZerosToCouponsPoint
//      will not calculate a basic *IBOR rate */
//   payInterval=new TDateInterval(2,'A');
//
//   /* set up the stub and floating rate structures */
//   firstCoupon = 0.0;
//   firstCouponInterval = floatInterval;
//
//   finalCoupon = 0.0;
//   finalCouponInterval = floatInterval;
//
//   floatingRate.matInterval = floatInterval;
//   floatingRate.payInterval = payInterval;
//   floatingRate.dayCountConv = floatDayCountConv;
//   JPMCDS_SET_ADJ_INTERVAL_DAYS(floatingRate.spotOffset, RESET_OFFSET)
//   floatingRate.weight = 1.0;
//   floatingRate.spread = 0.0;
//
//
//   isEndStub=JpmcdsIsEndStub(zc.getBaseDate(), matDate, fixedInterval, stubPos);
//
//   fixedDL = JpmcdsNewCouponDatesSwap(
//                zc.getBaseDate(),
//                matDate,
//                fixedInterval,
//                ADJUST_LAST_ACC_DATE,
//                IN_ARREARS,
//                PAY_OFFSET,
//                RESET_OFFSET,
//                isEndStub,
//                LONG_STUB,
//                FIRST_ROLL_DATE,
//                LAST_ROLL_DATE,
//                FULL_FIRST_COUPON_DATE,
//                badDayConv,
//                badDayConv,
//                badDayConv,
//                holidayFile);
//
//
//
//   /* Make notional of fixed side = +1.
//    */
//   fixedStream = JpmcdsNewStreamFixed(
//                   fixedDL,
//                   JPMCDS_SINGLE_REFIX,
//                   1.0,
//                   fixDayCountConv,
//                   0,
//                   rate,
//                   rate,
//                   rate);
//   if (fixedStream == NULL)
//       goto done;
//
//   fixedCFL = JpmcdsNewStreamFixedCFL(zc->valueDate, fixedStream);
//   if (fixedCFL == NULL)
//       goto done;
//
//   if (JpmcdsIsEndStub(zc->valueDate,
//                    matDate,
//                    &floatInterval,
//                    stubPos,
//                    &isEndStub) != SUCCESS)
//   {
//       goto done;
//   }
//   floatDL = JpmcdsNewCouponDatesSwap(
//                zc->valueDate,
//                matDate,
//                &floatInterval,
//                ADJUST_LAST_ACC_DATE,
//                IN_ARREARS,
//                PAY_OFFSET,
//                RESET_OFFSET,
//                isEndStub,
//                LONG_STUB,
//                FIRST_ROLL_DATE,
//                LAST_ROLL_DATE,
//                FULL_FIRST_COUPON_DATE,
//                badDayConv,
//                badDayConv,
//                badDayConv,
//                holidayFile);
//   if (floatDL == NULL)
//       goto done;
//
//
//   /* Make notional of floating side -1.
//    */
//   floatStream = JpmcdsNewStreamFloat(
//                   floatDL,
//                   JPMCDS_SINGLE_REFIX,
//                   -1.0,
//                   floatDayCountConv,
//                   0,
//                   &firstCoupon,
//                   &finalCoupon,
//                   0.0,         /* Average so far */
//                   1.0,         /* Compounded so far */
//                   &floatingRate);
//
//   if (floatStream == NULL)
//       goto done;
//
//   /* find the furthest reaching date */
//
//   if (JpmcdsDtFwdAny
//       (floatDL->fArray[floatDL->fNumItems-1].resetDate,
//        &floatInterval,
//        &indexMatDate) == FAILURE)
//       goto done;
//
//   if (JpmcdsBusinessDay(indexMatDate,
//                      badDayConv,
//                      holidayFile,
//                      &indexMatDate) == FAILURE)
//       goto done;
//
//
//   /* Pricing the floating side depends on both the payDate and the
//    * the index maturity date. We want the max of these two, so
//    * that when we interpolate the other, the answer is not changed
//    * by adding the zero for the *next* swap. We put *rate* in for
//    * now, and then replace this with the correct value below.
//    */
//   maxDate = MAX(indexMatDate, floatDL->fArray[floatDL->fNumItems-1].payDate);
//   minDate = MIN(indexMatDate, floatDL->fArray[floatDL->fNumItems-1].payDate);
//   if (JpmcdsZCAddRate(zc,maxDate,rate) == FAILURE)
//       goto done;
//
//
//   /* Need to make a temporary TCurve copy of the ZCurve, since all the
//    * swap routines only take TCurve structures
//    */
//
//   tc = JpmcdsZCToTCurve(zc);
//   if (tc == NULL)
//       goto done;
//
//   funcParams.fixedCfls = fixedCFL;
//   funcParams.zeroCurve = tc;
//   funcParams.floatStream = floatStream;
//   funcParams.interpType = interpType;
//   funcParams.price = price;
//   funcParams.rateBadDayConv = badDayConv;
//   funcParams.holidayFile = holidayFile;
//   funcParams.discZC = discZC;
//
//   /* Use the par swap rate as the first zero rate guess. Use that
//    * to set the bounds. Use the fact that JpmcdsRootFind starts iterating
//    * at average between rlow and rhigh. Set up rlow and rhigh so that
//    * that average is "rate".
//    */
//   if (JpmcdsRootFindBrent(JpmcdsObjFuncPVtheSwap,
//                        &funcParams,
//                        LOWER_BOUND, UPPER_BOUND,
//                        MAX_ITERATIONS, rate,
//                        INITIAL_X_STEP, INITIAL_F_DERIV,
//                        X_TOLERANCE, F_TOLERANCE,
//                        &zc->rate[zc->numItems-1]) == FAILURE)
//   {
//       JpmcdsErrMsg("%s: Root finder failed.\n", routine);
//       goto done;
//   }
//
//   if (JpmcdsZCComputeDiscount(zc,
//                         zc->date[zc->numItems-1],
//                         zc->rate[zc->numItems-1],
//                         &zc->discount[zc->numItems-1])==FAILURE)
//   {
//       goto done;
//   }
//
//   /* We are only generating the cashflows for the dates-we don't
//    * care about the values of the cashflows. We use the dates only
//    * to insert points in the zero curve so that the benchmarks will
//    * price out to par. Note that there could be small errors if
//    * we are adjusting for bad days, and if the interpolation method
//    * used by the pricing software does not match the zero curve
//    * interpolation method, because the index maturity dates do not
//    * necessarily match the pay dates.
//    */
//   floatCFL = JpmcdsNewStreamFloatCFLGen(tc, interpType, floatStream, badDayConv, holidayFile);
//   if (floatCFL == NULL)
//       goto done;
//
//   swapCFL = JpmcdsMergeCFL(fixedCFL, floatCFL);
//   if (swapCFL == NULL)
//       goto done;
//
//
//   /* Add zero rates at all dates in swapCFL.
//    * This guarantees that regardless of the interpolation used when
//    * pricing one of the original benchmark swaps with the zero curve
//    * created by this routine, the swap will be priced to par.
//    */
//   if (JpmcdsZCAddCFLPoints(zc,
//                      swapCFL,
//                      0,     /* Add point after this one*/
//                      interpType,
//                      interpData) == FAILURE)
//   {
//       goto done;
//   }
//
//   /* If we are adjusting for bad days, the index maturity date
//    * is not necessarily the same as the pay date. Add another
//    * zero point to help guarantee that the benchmarks price out to par.
//    */
//   if (minDate != maxDate)
//   {
//       double zeroRate;
//
//       if (JpmcdsZCInterpolate(zc, minDate, interpType, interpData, &zeroRate) == FAILURE)
//           goto  done;
//
//       if (JpmcdsZCAddRate(zc, minDate, zeroRate) == FAILURE)
//           goto done;
//   }
//
//   status = SUCCESS;
//
// done:
//   if (status == FAILURE)
//   {
//       JpmcdsErrMsg("%s: Failed.\n", routine);
//   }
//
//   JpmcdsFreeCouponDateList(floatDL);
//   JpmcdsFreeCouponDateList(fixedDL);
//   JpmcdsFreeTStreamFloat(floatStream);
//   JpmcdsFreeTStreamFixed(fixedStream);
//   JpmcdsFreeCFL(fixedCFL);
//   JpmcdsFreeCFL(floatCFL);
//   JpmcdsFreeCFL(swapCFL);
//   JpmcdsFreeTCurve(tc);
//   return status;
//}

    /*
     ***************************************************************************
     ** Adds information represented by a list-of-cash-flows to a zero curve.
     ** Any cash flows which are already covered by the zero curve are
     ** discounted at rates derived from the zero curve.  Cash flows beyond the
     ** zero curve imply discount factors, which are added to the zero curve.  If
     ** there is more than one such cash flow, several points are added to the curve,
     ** which are calculated by using an interative root-finding secant method,
     ** where the discount factor for the last cash flow is guessed (and the other
     ** discount factors are implied by interpolation) where the current price =
     ** net present value of all the cash flows.
     **
     ** The zero curve is updated to reflect the cash flows.  A point is added for
     ** every cash flow, if not already in ZCurve list.  For linear forwards, all
     ** interpolated points are returned, e.g. for 1 month forwards in an annual
     ** market, monthly points will be returned, not just yearly.
     **
     ** Notes: date may be set for non-linear-forward interpolation methods to a
     ** date to be added to the zero curve.  This allows production of a curve
     ** with "nice" dates.
     ***************************************************************************
     */
    public static void JpmcdsZCAddCashFlowList( /* adds cashFlowList info to a ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add info to */
            TCashFlowList cfl, /* (I) Cash flows to add to ZCurve */
            double price, /* (I) Current price of cash-flows */
            Date date, /* (I) Date to add last point at */
            String interpType, /* (I) Interpolation method's type */
            TInterpData interpData) /* (I) Interpolation method's data */ {

        double sumNPV = 0.0;           /* sum of n.p.vs of uncovered c.f.s */

        int firstUncovered;         /* index in cfl of 1st uncovered c.f.*/

        if (date == null) /* add at last c.f. if not set up */ {
            date = cfl.getfDates()[ cfl.getfDates().length - 1];
        }

        if (zc.getNumItems() <= 0) /* if no points in ZCurve ... */ {
            firstUncovered = 0;
            /* if no data, default to lin interp*/
            interpType = INTERP_LINEAR;
        } else {
            Date lastZCDate = zc.getDates().get(zc.getNumItems() - 1); /* last date in ZCurve*/

            if (date.before(lastZCDate) || date.equals(lastZCDate)) {
                return;
            }

            firstUncovered = cfl.getfDates().length - 1;
            if (firstUncovered < 0 || cfl.getfDates()[firstUncovered].before(lastZCDate)) {
                return;
            }

            while (firstUncovered >= 0 && cfl.getfDates()[firstUncovered].after(lastZCDate)) {
                firstUncovered--;           /* decrement until covered */

            }
            firstUncovered++;               /* move up to first not covered */

        }

        if (firstUncovered > 0) /* calc n.p.v. of covered cash flows */ {
            sumNPV = JpmcdsJpmcdsZCPresentValueCFL(zc, cfl, 0, firstUncovered - 1, interpType, interpData);
        }

        if (firstUncovered == cfl.getfDates().length - 1 /* if only one c.f. not covered */
                && cfl.getfDates()[firstUncovered].equals(date)) /* and c.f. is date to add */ {
            double discFactor;              /* discount implied by last c.f. */

            double pvOfLast = price - sumNPV; /* present value of last c.f. */

            double futOfLast;               /* future value of last c.f. */

            futOfLast = cfl.getfAmounts()[firstUncovered];

            discFactor = pvOfLast / futOfLast;
            JpmcdsZCAddDiscountFactor(zc, date, discFactor);
        } else if (firstUncovered < cfl.getfDates().length) {
            double rate;       /* rate to use for guess (and result)*/
//        TObjectiveData  objData;    /* data for objective function */

            if (zc.getNumItems() <= 0) /* make a guess based on zc */ {
                rate = 0.06;
            } else {                           /* linearly extrapolate guess */

                rate = JpmcdsZCInterpolate(zc, date, INTERP_LINEAR, null);
                rate = Math.max(0.01, Math.min(1.00, rate));
            }
            JpmcdsZCAddRate(zc, date, rate);

//        objData.zc               = zc; /* zero curve */
//        objData.cfl              = cfl; /* cash flow list */
//        objData.firstUncovered   = firstUncovered; /* 1st uncovered c.f.*/
//        objData.pvUnCovered      = price - sumNPV; /* p.v.of uncoverd cfs*/
//        objData.interpType       = interpType; /* interpolation type */
//        objData.interpData       = interpData; /* interpolation data */
//        objData.zcIndex          = zc->numItems-1; /*index into ZC ofrate*/
//        objData.discount         = NULL; /* not used for interp*/
//        objData.offset           = 0.0; /* ditto */
//        objData.startingDiscount = 0.0; /* ditto */
//        objData.fwdDL            = NULL; /* ditto */
//
//        rate=JpmcdsRootFindBrent((TObjectFunc) JpmcdsObjFunctionRate,
//                             &objData,
//                             MIN_ZERO_RATE, MAX_ZERO_RATE,
//                             MAX_ITERATIONS, rate,
//                             INITIAL_X_STEP, INITIAL_F_DERIV,
//                             X_TOLERANCE, F_TOLERANCE);
//        zc->rate[ objData.zcIndex ] = rate;
        }

        /* Ensure all dates used to discount cash flows are placed in zero curve.
         * This will make sure they can be priced appropriately by the curve.  Note
         * that non-linear interpolation might actually change when points are
         * added, so they are calculated before any are added, so they
         * won't change.
         */
        if (firstUncovered < cfl.getfDates().length) {
            JpmcdsZCAddCFLPoints(zc, cfl, DateUtils.addCalendarDay(cfl.getfDates()[firstUncovered], -1), interpType, interpData);
        }

    }

    /*
     ***************************************************************************
     ** Adds points into a zero curve to match all points in a cashflow list
     ** after a certain date. Note that only the *dates* in the cashflow list
     ** are used.
     **
     ** Zero curve is modified.
     ***************************************************************************
     */
    public static void JpmcdsZCAddCFLPoints( /* adds points in a CFL to zcurve */
            IsdaCurve zc, /* (M) ZCurve to add info to */
            TCashFlowList cfl, /* (I) Use dates only for pts to add */
            Date lastDate, /* (I) Only add pts after this date*/
            String interpType, /* (I) Interpolation method's type */
            TInterpData interpData) /* (I) Interpolation method's data */ {

        int n = 0;                      /* counts added points */

        int i;                          /* iterates over cashflows */

        Date[] dates = new Date[cfl.getfDates().length];
        double[] rates = new double[cfl.getfDates().length];
        if (dates == null || rates == null) {
            return;
        }

        /* Ensure all uncovered cash flow dates in curve. Note that we perform
         * all interpolations *first*, before inserting any points back in
         * curve, because some interp methods are such that new points will
         * affect the values of remaining points to be interpolated.
         */
        for (i = 0; i < cfl.getfDates().length; i++) {
            Date d = cfl.getfDates()[i];
            if (lastDate.before(d) && JpmcdsZCFindDateExact(zc, d) < 0) /* only add if not already in */ {
                double rate;
                rate = JpmcdsZCInterpolate(zc, d, interpType, interpData);
                if (rate >= 0.0) {
                    dates[n] = d;
                    rates[n++] = rate;
                }
            }
        }

        while (--n >= 0) {
            JpmcdsZCAddRate(zc, dates[n], rates[n]);
        }
    }
    /*
     ***************************************************************************
     ** Adds a discount factor (at a specified date) to a ZCurve.
     **
     ** Modified ZCurve, and error indicator.
     ***************************************************************************
     */

    public static void JpmcdsZCAddDiscountFactor( /* add discount factor to ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add discount to */
            Date date, /* (I) date assoc'd w/ disc factor */
            double disc) /* (I) discount factor to add */ {
        double rate;
        /* Convert discount factor to rate      */
        rate = JpmcdsDiscountToRate(disc, zc.getBaseDate(), date, zc.getDayCountConv(), (long) zc.getBasis());
        JpmcdsZCAddRateAndDiscount(zc, date, rate, disc);
    }

//
///*
//***************************************************************************
//** Adds a zero rate to a ZCurve.
//** The input rate must be in the proper ZCurve terms (zc->basis and zc->dayCountConv).
//**
//** ZCurve is updated to include the rate.
//***************************************************************************
//*/
    public static void JpmcdsZCAddRate( /* adds rate to ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add rate to */
            Date date, /* (I) date associated with rate */
            double rate) /* (I) rate, in ZCurve terms */ {
        double discount;               /* discount associated with rate */

        discount = JpmcdsZCComputeDiscount(zc, date, rate);
        JpmcdsZCAddRateAndDiscount(zc, date, rate, discount);
    }

///*
//***************************************************************************
//** Calculates net-present-value of a cash-flow list.
//**
//** Note: This embeds some logic in JpmcdsZCDiscountFactor() to optimize speed.
//** Basically it avoids searching for matching dates in the zero curve.  This can
//** usually works well, since a swap's coupon dates have been added by prior
//** instruments.
//***************************************************************************
//*/
    public static double JpmcdsJpmcdsZCPresentValueCFL( /* present value of a Cash-Flow-List */
            IsdaCurve zc, /* (I) ZCurve for discounting */
            TCashFlowList cfl, /* (I) cash flow list to value */
            int iLo, /* (I) index of 1st c.f. to value*/
            int iHi, /* (I) index of last c.f. to value*/
            String interpType, /* (I) type of interpolation */
            TInterpData interpData /* (I) data for interpolation */) /* (O) present value of cash flows*/ {

        double sumPV = 0.0;            /* sum of p.v. of cash flows */

        int i;                      /* loops over cash flows[iLo..iHi] */

        int j = 0;                  /* loops over ZC entries */

        for (i = iLo; i <= iHi; i++) {
            double amt = cfl.getfAmounts()[i]; /* short hand for c.f. amount */

            Date date = cfl.getfDates()[i]; /* short hand for c.f. date */

            double pv;                      /* present value of c.f. */

            while (j < zc.getNumItems() && zc.getDates().get(j).before(date)) /* push j up to c.f. date */ {
                j++;
            }

            if (j < zc.getNumItems() && zc.getDates().get(j).equals(date)) /* found exact date in zc */ {
                pv = zc.getDiscount().get(j) * amt;
            } else {
                pv = JpmcdsZCPresentValue(zc, amt, date, interpType, interpData);
            }
            sumPV += pv;
        }
        return sumPV;
    }
///*
//***************************************************************************
//** Calculates net-present-value of a cash-flow (a payment at a given date
//** in the future).
//***************************************************************************
//*/

    public static double JpmcdsZCPresentValue( /* present-value of future payment */
            IsdaCurve zc, /* (I) ZCurve used for discounting*/
            double price, /* (I) amount of payment */
            Date date, /* (I) date of payment */
            String interpType, /* (I) type of interpolation */
            TInterpData interpData /* (I) data for interpolation */) /* (I) present value of price */ {

        double discFactor = JpmcdsZCDiscountFactor(zc, date, interpType, interpData);
        double pv = price * discFactor;
        return pv;
    }

///*
//***************************************************************************
//** Calculates discount factor for a date.
//***************************************************************************
//*/
    public static double JpmcdsZCDiscountFactor /* discount factor for given date */(IsdaCurve zc, /* (I) ZCurve */
                    Date date, /* (I) date to calculate d.f. for */
                    String interpType, /* (I) interpolation type to use */
                    TInterpData interpData /* (I) interpolation method to use */) /* (O) discount factor */ {
        double rate;         /* Zero rate to date */
        double discount;

        rate = JpmcdsZCInterpolate(zc, date, interpType, interpData);

        /* Calculate discount factor from rate
         */
        discount = JpmcdsZCComputeDiscount(zc, date, rate);
        return discount;
    }
///*
//***************************************************************************
//** Calculates an interpolated rate for a date.
//**
//** Note: Piece wise interpolation allows different areas of the zero curve
//** to be interpolated using different methods.  Basically an array of
//** <date,interpolationStuff> is given, where the interpolationStuff is used
//** for any date before the given date.  The code allows the interpolationStuff
//** to be another pieceWise interpolation type, although the utility of this
//** is unknown.
//***************************************************************************
//*/

    public static double JpmcdsZCInterpolate( /* interpolation on a ZCurve */
            IsdaCurve zc, /* (I) ZCurve to interpolate on */
            Date date, /* (I) date to interpolate to */
            String interpTypeIn, /* (I) type of interpolation's use*/
            TInterpData interpDataIn /* (I) data for interp method */) /* (O) rate to return */ {
        int lo;                     /* low index in ZCurve for linear */

        int hi;                     /* high index ""     "" */

        String interpType = interpTypeIn;  /* interpolation type */

        TInterpData interpData = interpDataIn;  /* interpolation data */
//   TMetricDoubleFunc metricFP;             /* day counting metric function */

        double rate = 0;
        double yearsFromBaseDate = DateUtils.dateDiff(date, zc.getBaseDate()) / 365.25;

        /* Do *flat* extrapolation only when going backwards. This
         * is done so that swaps which have payments before the beginning
         * of the stub zero curve will still value to par. This can happen
         * very easily if there are swaps with front stubs.
         * We still permit forward non-flat extrapolation.
         */
        if (date.before(zc.getDates().get(0)) || date.equals(zc.getDates().get(0))) {
            rate = zc.getRates().get(0);
        }
        if (zc.getDates().size() == 1) {
            return rate;
        }

        if (date.equals(zc.getBaseDate())) {
            date = DateUtils.addCalendarDay(date, 1);
            /* cannot calculate rate for value date, so get the value at the
             ** next date which is the next best thing       */
        }

        /* Find indices in zero curve which bracket date.
         */
        lo = 0;
        while (zc.getDates().get(lo).before(date)) {
            lo++;
        }
        hi = lo;
        if (lo > 0) {
            lo--;
        }
//   if (JpmcdsBSearchLongFast((double)date,
//                          zc->date,
//                          sizeof(TDate),
//                          zc->numItems,
//                          &lo,
//                          &hi) == FAILURE)

        if (zc.getDates().get(lo).equals(date)) /* if exact match, don't interpolate */ {
            rate = zc.getRates().get(lo);
        }

        if (hi < zc.getDates().size() && zc.getDates().get(hi).equals(date)) /* if exact match, don't interpolate */ {
            rate = zc.getRates().get(hi);
        }

//   metricFP = JpmcdsDayCountToMetricFunc(zc->dayCountConv);
//   switch (interpType)
//   {
//   case INTERP_LINEAR:
        if (interpType.equals(INTERP_LINEAR)) {
            long hi_lo = DateUtils.dateDiff(zc.getDates().get(hi), zc.getDates().get(lo));
            long dt_lo = DateUtils.dateDiff(date, zc.getDates().get(lo));
            rate = zc.getRates().get(0);
            if (hi_lo != 0) {
                rate += ((zc.getRates().get(hi) - zc.getRates().get(lo)) / hi_lo) * dt_lo;
            }
        } //       break;
        //
        //   case INTERP_FLAT_FORWARDS:
        else if (interpType.equals(INTERP_FLAT_FORWARDS)) {
            double discLo;                  /* discount factor for lo */

            double discHi;                  /* discount factor for hi */

            double discDate;                /* discount factor for date */

            long hi_lo = DateUtils.dateDiff(zc.getDates().get(hi), zc.getDates().get(lo));
            long dt_lo = DateUtils.dateDiff(date, zc.getDates().get(lo));

            if (hi_lo == 0) {
                rate = zc.getRates().get(0);
            } else {
                discLo = JpmcdsZCComputeDiscount(zc, zc.getDates().get(lo), zc.getRates().get(lo));
                discHi = JpmcdsZCComputeDiscount(zc, zc.getDates().get(hi), zc.getRates().get(hi));

                discDate = discLo * Math.pow(discHi / discLo, dt_lo / (double) hi_lo);

                rate = JpmcdsDiscountToRate(discDate, zc.getBaseDate(), date, zc.getDayCountConv(), (long) zc.getBasis());
            }
        }
//       break;
//   }

        return rate;
    }

///*
//***************************************************************************
//** Makes a cash flow list for a swap instrument.
//**
//** return s cash flow list associated with the swap, NULL if an error found.
//***************************************************************************
//*/
    public static TCashFlowList JpmcdsZCGetSwapCFL(Date valueDate, /* (I) Value date                  */
            Date matDate, /* (I) Unadjusted maturity date    */
            boolean stubAtEnd, /* (I) If matDate on-cycle         */
            double rate, /* (I) Coupon rate                 */
            TDateInterval interval, /* (I) Coupon payment interval     */
            String dayCountConv, /* (I) Day count convention        */
            TBadDayList badDayList, /* (I) Bad day adjustment list     */
            String badDayConv, /* (I) Bad day convention for rate */
            HolidayCalendar holidayFile) /* (I) Name of holiday file        */ {

        TCashFlowList cfl;         /* cash-flow-list to return */

        Date[] dl;          /* list of coupon dates */

        Date prevDate;           /* prev date added to cash-flow list */

        int i;                  /* loops over coupon dates */

        /* Bizarre rate==0.0 case
         */
        if (rate == 0.0) {
            cfl = new TCashFlowList(1);
            cfl.getfAmounts()[0] = 1;
            cfl.getfDates()[0] = JpmcdsZCAdjustDate(matDate, badDayList, badDayConv, holidayFile);
            return cfl;
        }

        /* Get dateList with adjusted coupon dates & mat date
         */
        dl = JpmcdsZCGetSwapCouponDL(valueDate,
                matDate, stubAtEnd, interval,
                badDayList, badDayConv, holidayFile);

        cfl = new TCashFlowList(dl.length);    /* make cash flow list w/ coupons */

        prevDate = valueDate;
        for (i = 0; i < dl.length; i++) {
            Date cDate = dl[i];       /* coupon date */

            double yearFraction;                /* fraction of year for coupon #i */

            yearFraction = DayCountAccessor.dayCountFraction(prevDate, cDate, dayCountConv);

            cfl.getfAmounts()[i] = rate * yearFraction;
            cfl.getfDates()[i] = cDate;      /* store date */

            prevDate = cDate;
        }

        cfl.getfAmounts()[cfl.getfAmounts().length - 1] += 1.0;   /* add principal */

        return cfl;
    }

///*
//***************************************************************************
//** Makes a date list for all coupons associated w/ a swap instrument.
//**
//** Only glitch is possible inclusion of a stub date, which is necessary if the
//** maturity date isn't an integral number of frequency intervals away, e.g.
//** a swap date 5 years and 1 month from the value date, which would have a
//** stub date 1 month from now, followed by coupons every year from then.
//**
//** Returns date list associated with the swap's coupons, NULL if error found.
//***************************************************************************
//*/
    public static Date[] JpmcdsZCGetSwapCouponDL(Date valueDate, /* (I) Value date */
            Date matDate, /* (I) Unadjusted maturity date */
            boolean stubAtEnd, /* (I) If matDate onCycle from vdate */
            TDateInterval interval, /* (I) Coupon payment interval */
            TBadDayList badDayList, /* (I) Bad day adjustment list */
            String badDayConv, /* (I) */
            HolidayCalendar holidayFile) /* (I) */ {
        Date[] dl;          /* List of coupon dates */

        /* If the matDate is on-cycle, then stub is at end, because we
         * are counting forward from the value date.
         */
        dl = JpmcdsNewPayDates(valueDate, matDate, interval, stubAtEnd);

        /* Now adjust for bad days.
         */
        if (!badDayConv.equals("NONE")) {
            JpmcdsDateListBusDayAdj(dl, badDayConv, holidayFile);
        }

        JpmcdsFixBadDLBadDayList(badDayList, dl);

        return dl;
    }

///*
//***************************************************************************
//** Adjusts a datelist (in-place) according to the supplied business day
//** adjustment convention.
//***************************************************************************
//*/
    public static void JpmcdsDateListBusDayAdj(
            Date[] dateList, /* (I/O) Unadjusted date list  */
            String badDayConv, /* (I)   Business day adjustment convention */
            HolidayCalendar holidayFile /* (I)   Holiday date file */) {

        int idx;

        for (idx = 0; idx < dateList.length; idx++) {
            dateList[idx] = DateUtils.adjustDate(dateList[idx], badDayConv, holidayFile);
        }
    }
///*
//***************************************************************************
//** This function converts bad dates in a date list to good ones based on
//** a BadDayList.  DateList passed in is MODIFIED to contain "good" dates.
//***************************************************************************
//*/

    public static void JpmcdsFixBadDLBadDayList(
            TBadDayList bdl, /* (I) bad day list */
            Date[] dl) /* (M) date list to convert */ {
        int i;

        if (dl == null) {
            return;
        }

        for (i = 0; i < dl.length; i++) {
            dl[i] = JpmcdsBad2GoodBadDayList(bdl, dl[i]);
        }
    }

///*
//***************************************************************************
//** Allocates a new TDateList by calling JpmcdsNewDateList, and then removing
//** the startDate.
//***************************************************************************
//*/
    public static Date[] JpmcdsNewPayDates(Date startDate, /* (I) This date is not included */
            Date matDate, /* (I) */
            TDateInterval payInterval, /* (I) */
            boolean stubAtEnd) /* (I) */ {

        int idx;
        Date[] payDates;

        payDates = JpmcdsNewDateList(startDate, matDate, payInterval, stubAtEnd);

        Date[] ret = new Date[payDates.length - 1];


        /* Now remove startDate, and move all dates back by one.
         */
        for (idx = 0; idx < payDates.length - 1; idx++) {
            ret[idx] = payDates[idx + 1];
        }

        return ret;
    }
///*
//***************************************************************************
//** Makes an array of dates from startDate, MaturityDate, & interval.
//** If (maturityDate-startDate) divided by interval is not an integer,
//** there is a stub. If stubAtEnd is set, the stub is placed at the end;
//** otherwise, it is placed at the beginning.
//** Unlike JpmcdsNewDateListExtended, the startDate and maturityDate are always
//** included, and are the first and last dates respectively.
//** Assuming there is no stub,  dates created are of the form:
//** baseDate + idx*Interval, where startIdx <= idx <= Time2Maturity/Interval
//** Returns a new DateList on success, and NULL on failure.
//***************************************************************************
//*/

    public static Date[] JpmcdsNewDateList(
            Date startDate, /* (I) Start Date */
            Date maturityDate, /* (I) Maturity Date */
            TDateInterval interval, /* (I) Increment */
            boolean stubAtEnd) /* (I) T=Stub at end; F=Stub at beg. */ {
        Date[] dateList;
        TDateInterval intval = (TDateInterval) interval.clone();   /* Make local copy */

        int numIntervals = 0;
        int extraDays;
        int numDates;

        if (stubAtEnd) /* Count forwards from startDate */ {
            extraDays = JpmcdsCountDates(startDate, maturityDate, intval, numIntervals);
        } else /* Count backwards from matDate */ {
            intval.prd = -intval.prd;
            extraDays = JpmcdsCountDates(maturityDate, startDate, intval, numIntervals);
        }

        if (extraDays > 0) {
            numDates = numIntervals + 2;
        } else {
            numDates = numIntervals + 1;
        }

        /* Fill in the dates
         */
        if (stubAtEnd) /* Stub at end */ {
            dateList = JpmcdsMakeTDateArray(startDate, intval, 0, 1, numDates);
            dateList[numDates - 1] = maturityDate;
        } else /* Stub at beginning */ {
            dateList = JpmcdsMakeTDateArray(maturityDate, intval, 0, -1, numDates);
            dateList[0] = startDate;
        }

        /* Succeeded. */
        return dateList;

    }

///*
//***************************************************************************
//** Returns an array of TDates.
//** Returns SUCCESS/FAILURE.
//***************************************************************************
//*/
    public static Date[] JpmcdsMakeTDateArray(
            Date baseDate, /* (I) Base date */
            TDateInterval interval, /* (I) Date increment */
            int startIdx, /* (I) # intervals to start at */
            /* 0=start @ basedate */
            /* 1=start @ baseDate + interval, etc*/
            int arrayIncrement, /* (I) For array, not dates */
            /* Usually +1 or -1 */
            int ndates) /* (O) array of dates */ {
        Date[] dateArray = new Date[ndates];
        int idx;
        TDateInterval offsetInterval = interval;

        if (arrayIncrement >= 0) {
            for (idx = 0; idx < ndates; idx++) {
                offsetInterval.prd = interval.prd * (startIdx + idx);
                dateArray[idx * arrayIncrement] = DateIntervalUtil.JpmcdsDtFwdAny(baseDate, offsetInterval);
            }
        } else {
            for (idx = 0; idx < ndates; idx++) {
                offsetInterval.prd = interval.prd * (startIdx + idx);
                dateArray[ndates - 1 + idx * arrayIncrement] = DateIntervalUtil.JpmcdsDtFwdAny(baseDate, offsetInterval);
            }

        }
        return dateArray;
    }

///*
//*********************************************************************
//** Returns TRUE if a stub should be at the end.
//**
//** The decision is based on the following.
//** 1. First the default stub position is checked. If this is a back
//** stub then return TRUE - if a front stub return FALSE.
//** 2. If the default stub position is auto then the function will
//** return TRUE, unless there is a stub in whach case FALSE is
//** returned.
//*********************************************************************
//*/
    public static boolean JpmcdsIsEndStub(Date startDate, /* (I) Start date */
            Date maturityDate, /* (I) Maturity date */
            TDateInterval ivl, /* (I) Interval */
            String stubPos /* (I) Stub position */) /* (O) TRUE if end stub */ {
        int numIvls = 0;
        int extraDays;
        boolean isEndStub;
//    switch(stubPos)
//    {
//    case "BACK": isEndStub = true;  break;
//    case "FRONT": isEndStub = false;  break;
//    case "AUTO":
        switch (stubPos) {
            case "BACK":
                isEndStub = true;
                break;
            case "FRONT":
                isEndStub = true;
                break;
            default:
                extraDays = JpmcdsCountDates(startDate, maturityDate, ivl, numIvls);
                isEndStub = extraDays <= 0;
                break;
        }
        return isEndStub;
    }

//
///*
//***************************************************************************
//** Optimization, adds a swap to a zero curve assuming the one added just
//** before has the same coupon dates (w/ one additional payment).  This is
//** much quicker than generating a cash-flow-list for the swap and adding those.
//** And it often happens that this routine can be used.
//**
//** ZCurve is updated to contain swap information.
//**
//** How it works:
//**    Assuming present value of principal + coupon payments == current price,
//**        price = discount[n] + sum of coupon[i]*discount[i]
//**    replacing for coupon:
//**        coupon[i] = yearFraction(date[i] - date[i-1]) * couponRate
//**        price = discount[n] + sum of discount[i]*yearFraction(i)*couponRate
//**        price = discount[n] + couponRate * (sum of discount[i]*yearFraction[i])
//**
//**  We assume that the new swap shares the same yearFraction[], with previous.
//**  Also, all discounts except the last (which we're trying to find) are known.
//**
//**  So, from the previous swap we back out sum of discount[i]*yearFraction[i],
//**  which is done via:  sumDY = (priceOld - discount[n]) / couponRateOld
//**
//**  So,
//**        priceNew = discount[n] + couponRate(discount[n]*yearFraction[n]+sumDY)
//**        priceNew = discount[n] * (1 + couponRate*yearFraction[n]) + cRate*sumDY
//**  Solving for discount[n]:
//**        discount[n] = (priceNew - couponRate*sumDY) / (1+couponRate*yearF[n])
//**
//** Assumptions:
//**   Naturally it assumed that the new swap is equal to the old one with
//**   an additional coupon.  Also assume that the previous couponRate!=0.0,
//**   and that the last discount factor in the zero curve corresponds to the date
//**   of the previous swap and was placed there based on the previous swap.
//***************************************************************************
//*/
    static void AddSwapFromPrevious( /* adds swap based on one just added */
            IsdaCurve zc, /* (M) ZCurve to add to */
            Date dateNew, /* (I) date of new swap */
            double rateNew, /* (I) rate of new swap */
            double priceNew, /* (I) price of new swap */
            Date dateOld, /* (I) date of previous swap */
            double rateOld, /* (I) rate of previous swap */
            double priceOld, /* (I) price of previous swap */
            String dayCount) /* (I) day counting convention */ {

        double sumDY;                  /* sum of discount[i]*yearFrac[i] */

        double yf;                     /* year fraction dateNew-dateOld */

        double discount;               /* discount calculated from swapNew */

        double divisor;                /* a factor used to divide */

        /* Compute year fraction  */
        yf = DayCountAccessor.dayCountFraction(dateOld, dateNew, dayCount);
        /* Compute divisor, and make sure it isn't = 0. */
        divisor = 1.0 + rateNew * yf;
        /* Here we assume that calling routine has already checked that
         * rateOld != 0.
         */
        sumDY = (priceOld - zc.getDiscount().get(zc.getNumItems() - 1)) / rateOld;

        discount = (priceNew - rateNew * sumDY) / divisor;

        if (discount <= 0.0) {
            /*
             * The discount may be <= 0. This is the classical case where a
             * consistent zero curve cannot be built.
             */
            logger.info("swap rates may be inconsistent with one another.\n");
        }

        /* Add the discount factor to the zero curve. */
        JpmcdsZCAddDiscountFactor(zc, dateNew, discount);
    }

// /*
//***************************************************************************
//** Uses swap maturity dates adjusted for bad days and a TBadDayList to set
//** up a TSwapDates.
//***************************************************************************
//*/
    public static TSwapDates JpmcdsSwapDatesNewFromAdjusted(Date valueDate, /* (I) Must be a good day. */
            int freq, /* (I) Used to set previous */
            Date[] adjustedDates, /* (I) Adjusted swap mat dates */
            int numDates, /* (I) Length of adjustedDates */
            TBadDayList badDayList) /* (I) Maps good to bad & vice-vsa*/ {

        int idx;

        TSwapDates sd = new TSwapDates(numDates);

        /* For each swap maturity date... */
        for (idx = 0; idx < numDates; idx++) {
            sd.getAdjusted()[idx] = adjustedDates[idx];
            sd.getOriginal()[idx] = JpmcdsGood2BadBadDayList(badDayList, adjustedDates[idx]);

            /* Now set sd->onCycle[idx] and sd->previous[idx]. */
            sd.getPrevious()[idx] = SetPrevDateAndOnCycle(valueDate, sd.getOriginal()[idx], freq, sd.getOnCycle()[idx]);
        }
        return sd;
    }

///*
//***************************************************************************
//** Uses un-adjusted swap maturity dates, a holiday file, and a bad day
//** convention to set up a TSwapDates.
//***************************************************************************
//*/
    public static TSwapDates JpmcdsSwapDatesNewFromOriginal(Date valueDate, /* (I) Must be a good day. */
            int freq, /* (I) Used to set previous */
            Date[] originalDates, /* (I) UN-Adjusted swap mat dates */
            int numDates, /* (I) Length of originalDates */
            TBadDayList badDayList, /* (I) Maps good to bad & vice-vsa*/
            String badDayConv, /* (I) See JpmcdsIsBusinessDay */
            HolidayCalendar holidayFile) /* (I) List of holidays */ {
        int idx;
        TSwapDates sd = new TSwapDates(numDates);


        /* For each swap maturity date... */
        for (idx = 0; idx < numDates; idx++) {
            /* Set original (UNadjusted) and adjusted dates.
             */
            sd.getOriginal()[idx] = originalDates[idx];
            sd.getAdjusted()[idx] = JpmcdsZCAdjustDate(originalDates[idx], badDayList, badDayConv, holidayFile);


            /* Now set sd->onCycle[idx] and sd->previous[idx]. */
            sd.getPrevious()[idx] = SetPrevDateAndOnCycle(valueDate, originalDates[idx], freq, sd.getOnCycle()[idx]);
        }
        return sd;
    }

///*
//***************************************************************************
//** Adjusts date to fall on a business day, if necesssary.
//***************************************************************************
//*/
    public static Date JpmcdsZCAdjustDate(Date date, /* (I) Date to be adjusted */
            TBadDayList badDayList, /* (I) */
            String badDayConv, /* (I) */
            HolidayCalendar holidayFile /* (I) */) /* (O) */ {

        Date adjDate = new Date();
        if (badDayList != null) {
            adjDate = JpmcdsBad2GoodBadDayList(badDayList, date);
        } else {
            adjDate = DateUtils.adjustDate(date, badDayConv, holidayFile);
        }
        return adjDate;
    }

///*
//***************************************************************************
//** This function converts a good day to a bad one (if it's in list).
//***************************************************************************
//*/
    public static Date JpmcdsGood2BadBadDayList(
            TBadDayList bdl, /* (I) bad day list */
            Date d) /* (I) bad date to convert */ {
        int i;

        if (bdl == null) /* allow NULL to ignore list */ {
            return d;
        }

        for (i = 0; i < bdl.getCount(); i++) {
            if (bdl.getGoodDay()[i] == d) {
                return bdl.getBadDay()[i];
            }
        }
        return d;
    }

///*
//***************************************************************************
//** This function converts a bad day to a good one (if it's in list).
//***************************************************************************
//*/
    public static Date JpmcdsBad2GoodBadDayList(
            TBadDayList bdl, /* (I) bad day list */
            Date d) /* (I) bad date to convert */ {
        int i;

        if (bdl == null) /* allow NULL to ignore list */ {
            return d;
        }

        for (i = 0; i < bdl.getCount(); i++) {
            if (bdl.getBadDay()[i] == d) {
                return bdl.getGoodDay()[i];
            }
        }
        return d;
    }

///*
//***************************************************************************
//** First finds out if a date is on cycle, counting from the value date.
//** Then computes the previous coupon date.
//***************************************************************************
//*/
    static Date SetPrevDateAndOnCycle(Date valueDate, /* (I) Value date */
            Date origDate, /* (I) UNadjusted date  */
            int freq, /* (I) Used to set previous */
            boolean onCycle) /* (O) Previous coupon date */ {

        TDateInterval interval;             /* Translated from freq */

        int numIntervals = 0;         /* # coupon intervals to swap mat */

        int extraDays;            /* number of extra days (stub) */

        Date prevDate ;

        interval = DateIntervalUtil.JpmcdsFreq2TDateInterval(freq);

//    valueMDY=IsdaPricerCaller.dateToLong(valueDate);
//    origMDY=IsdaPricerCaller.dateToLong(origDate);
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(valueDate);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(origDate);

        if ((cal1.get(Calendar.DATE) <= 28)
                && (cal2.get(Calendar.DATE) <= 28)) {
            /*
             * We assume we can only be on cycle if date is not on or after
             * the 29th of the month.
             */

            /* Find out if unadjusted date is on cycle.
             */
            extraDays = JpmcdsCountDates(valueDate, origDate, interval, numIntervals);

            onCycle = (extraDays == 0);
        } else {
            onCycle = false;
        }

        /* Now compute prevDate. If on cycle, count forwards
         * from value date. If off cycle, count backwards from matDate.
         */
        if (onCycle) /* On cycle */ {
            prevDate = JpmcdsDateFromDateAndOffset(valueDate, interval, numIntervals - 1);
        } else /* Off cycle */ {
            prevDate = JpmcdsDateFromDateAndOffset(origDate, interval, -1);

        }
        return prevDate;
    }
    /*
     ***************************************************************************
     ** Counts # TDateIntervals in a range of dates. Note that fromDate
     ** can be either later than or earlier than toDate.
     **
     ** In order to make this routine speedier we start from a low estimate
     ** of result value that is calculated using division (speedy approach).
     ** Based on the low estimate we calculate the results.
     **
     ** Note that we allways calculate currDate based on fromDate rather than
     ** lastDate.  We MUST do it because
     ** date + (n*Interval) = ((date + Interval)+Interval) ... )+Interval
     ** is not always true
     ***************************************************************************
     */

    public static int JpmcdsCountDates(
            Date fromDate, /* (I) Date to count from */
            Date toDate, /* (I) Date to count to */
            TDateInterval interval, /* (I) Interval to count */
            int numIntervals /* (O) Answer (Quotient) */) /* (O) Days left over(remainder) */ {
        int extraDays;
        double intervalYears;     /* TDateInterval expressed in years */

        double fromToYears;       /* interval between to & from dates in years */

        int lowNumIntervals;      /* low estimation of *numIntervals */

        int index;                /* running approximation of *numIntervals */

        Date currDate;           /* date index times interval away from fromDate*/

        Date lastDate;           /* last date fitting guaranteed to be between */

        /* Make sure interval has the right sign.
         */

        /* Convert TDateInterval to years
         */
        intervalYears = DateIntervalUtil.JpmcdsDateIntervalToYears(interval);

        /* Convert toDate-fromDate to years
         */
        fromToYears = (double) (DateUtils.dateDiff(toDate, fromDate)) / 365;

        /* Compute & round the quotient to the lower integer and then subtract TWO
         * to make sure that corresponding lowToDate is INSIDE
         */
        lowNumIntervals = Math.max(0, (int) Math.floor(Math.abs(fromToYears / intervalYears)) - 2);

        /* Initialize the LOOP by index of intervals
         */
        index = lowNumIntervals;
        currDate = JpmcdsDateFromDateAndOffset(fromDate, interval, index);
        lastDate = currDate;

        /* Keep advancing currDate while currDate is between fromDate and toDate.
         */
        while (currDate.after(fromDate) && currDate.before(toDate)) //       IS_BETWEEN (currDate,fromDate,toDate) )
        {
            ++index;
            lastDate = currDate;
            currDate = JpmcdsDateFromDateAndOffset(fromDate, interval, index);
        }

        numIntervals = index - 1; /* step back inside (fromDate,toDate) interval */

        extraDays = (int) Math.abs(DateUtils.dateDiff(toDate, lastDate));

        return extraDays;
    }

    /*
     ***************************************************************************
     ** Calculates a date from another date and offset represented by interval
     ** and index: newDate = oldDate + index*interval
     ***************************************************************************
     */
    public static Date JpmcdsDateFromDateAndOffset(Date oldDate,
            TDateInterval pInterval,
            int index) {
        TDateInterval compoundInterval = (TDateInterval) pInterval.clone();

        compoundInterval.prd = compoundInterval.prd * index;

        return DateIntervalUtil.JpmcdsDtFwdAny(oldDate, compoundInterval);
    }

///*
//***************************************************************************
//** Adds simple-interest money market bond to ZCurve
//**
//** ZCurve is updated to include information from money market instruments
//***************************************************************************
//*/
    public static void JpmcdsZCAddMoneyMarket( /* adds simple-interest MMKT to ZC */
            IsdaCurve zc, /* (M) ZCurve to add to */
            Date[] dates, /* (I) maturity dates of instrs */
            double[] rates, /* (I) rates (e.g. 0.06 for 6%) */
            int n, /* (I) number of instruments */
            String dayCountConv) /* (I) day-count-convention */ {
        int i;                          /* loops over instruments */

        for (i = 0; i < n; i++) {
            JpmcdsZCAddGenRate(zc, dates[i], rates[i], 0, dayCountConv);
        }

    }

///*
//***************************************************************************
//** Adds points to a zero curve from another zero curve, but only those
//** dates before first date of the other curve.
//**
//** ZCurve is updated to include the new points.
//**
//** Note: curve value dates MUST be the same, basis and dayCounts may differ.
//***************************************************************************
//*/
    public static void JpmcdsZCAddPrefixCurve( /* adds prefix from other ZCurve */
            IsdaCurve zc1, /* (M) zCurve to added to */
            IsdaCurve zc2) /* (I) zCurve to add from */ {
        Date firstDate;                  /* first date of zc */

        int i;                          /* loops over zc2 elements */

        if (zc1.getDates() == null) /* if nothing in zCurve, add everything*/ {
            if (zc2.getDates() == null) /* easy to add nothing to nothing */ {
                return;
            }
            firstDate = zc2.getDates().get(zc2.getNumItems() - 1);
        } else {
            firstDate = zc1.getDates().get(0);
        }

        for (i = 0; i < zc2.getNumItems() && zc2.getDates().get(i).before(firstDate); i++) {
            JpmcdsZCAddGenRate(zc1, zc2.getDates().get(i), zc2.getRates().get(i), (long) zc2.getBasis(), zc2.getDayCountConv());
        }
    }


    /*
     ***************************************************************************
     ** Adds points to a zero curve from another zero curve, but only those
     ** dates after last date of the other curve.
     **
     ** ZCurve is updated to include the new points.
     **
     ** Note: curve value dates MUST be the same, basis and dayCounts may differ.
     ***************************************************************************
     */
    public static void JpmcdsZCAddSuffixCurve( /* adds suffix from other ZCurve */
            IsdaCurve zc1, /* (M) zCurve to added to */
            IsdaCurve zc2) /* (I) zCurve to add from */ {
        Date lastDate;                   /* last date of zc */

        int i;                          /* loops over zc2 elements */

        if (zc1.getDates() == null) /* if nothing in zCurve, add everything*/ {
            if (zc2.getDates() == null) /* easy to add nothing to nothing */ {
                return;
            }
            lastDate = DateUtils.addCalendarDay(zc2.getDates().get(0), - 1);
        } else {
            lastDate = zc1.getDates().get(zc1.getNumItems() - 1);
        }

        for (i = zc2.getNumItems() - 1; i >= 0 && zc2.getDates().get(i).after(lastDate); i--) {
            JpmcdsZCAddGenRate(zc1, zc2.getDates().get(i), zc2.getRates().get(i), (long) zc2.getBasis(), zc2.getDayCountConv());
        }
    }

//
///***************************************************************************
//** Adds a general zero rate to a ZCurve.
//** ZCurve is updated to include the rate.
//***************************************************************************
//*/
    public static void JpmcdsZCAddGenRate( /* adds rate to ZCurve */
            IsdaCurve zc, /* (M) ZCurve to add rate to */
            Date date, /* (I) date associated with rate */
            double rate, /* (I) rate, in ZCurve terms */
            long basis, /* (I) compounding basis of rate*/
            String dayCountConv) /* (I) day counting convention */ {
        double df;                         /* discount factor assoc w/ rate */

        if (basis == zc.getBasis() && dayCountConv.equals(zc.getDayCountConv())) {
            JpmcdsZCAddRate(zc, date, rate);
        }
        /* convert to discount factor */
        df = JpmcdsRateToDiscount(rate, zc.getBaseDate(), date, dayCountConv, basis);
        JpmcdsZCAddDiscountFactor(zc, date, df);
    }
///*
//***************************************************************************
//** Convert from a given discount factor to a rate.
//***************************************************************************
//*/

    public static double JpmcdsDiscountToRate(double discount, /* (I) Discount factor */
            Date startDate, /* (I) Start date */
            Date endDate, /* (I) End date */
            String rateDayCountConv, /* (I) Day count convention for rate */
            long rateBasis /* (I) Basis for the rate */) /* (O) output rate */ {
        double rateYF;

        if (discount <= 0.0) {
            return 0;
        }

        if (rateBasis == -2) {
            if (startDate.equals(endDate)) {
                return 1.0;
            } else {
                return discount;
            }
        }

        /* get year fractions for rate */
        rateYF = DayCountAccessor.dayCountFraction(startDate, endDate, rateDayCountConv);
        double rate = JpmcdsDiscountToRateYearFrac(discount, rateYF, (double) rateBasis);
        return rate;
    }

///*
//***************************************************************************
//** Convert from a discount factor to a rate using a year fraction.
//**
//** Calculate an interest rate from a discount factor, given the daycount
//** and basis.
//***************************************************************************
//*/
    public static double JpmcdsDiscountToRateYearFrac(
            double discount, /* (I) Discount factor */
            double yearFraction, /* (I) Year fraaction */
            double basis) /* (I) Basis for the rate */ /* (O) Output rate */ {
        double rate;
        switch ((int) basis) {
            case 0:
                rate = (1.0 / discount - 1.0) / yearFraction;
                break;
            case 512:
                rate = (1.0 - discount) / yearFraction;
                break;
            case 5000:
                rate = (-Math.log(discount) / yearFraction);
                break;
            case -2:
                rate = discount;
                break;
            default:
                /* We prefer to be stodgy and not do the log/exp thing here for performance. */
                rate = basis * (Math.pow(discount, -1.0 / (basis * yearFraction)) - 1.0);
                break;
        }
        return rate;
    }

///*
///*
//***************************************************************************
//** Adds a zero rate and discount to a ZCurve.
//** The input rate must be in the proper ZCurve terms (zc->basis and zc->dayCountConv).
//** ZCurve is updated to include the rate.
//***************************************************************************
//*/
    public static int JpmcdsZCAddRateAndDiscount( /* adds rate and discount factor to ZC*/
            IsdaCurve zc, /* (M) ZCurve to add rate to */
            Date date, /* (I) date associated with rate */
            double rate, /* (I) rate, in ZCurve terms */
            double disc) /* (I) discount factor to add */ {

        if (zc.getDates().size() > 0 && (date.before(zc.getDates().get(zc.getDates().size() - 1)) || date.equals(zc.getDates().get(zc.getDates().size() - 1)))) {   /* make sure date not already in list */

            int i = JpmcdsZCFindDateExact(zc, date);
            if (i >= 0) {
                if (Math.abs(rate - zc.getRates().get(i)) < 0.0000001) /* if same, no error */ {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

//    if (zc->numAlloc <= zc->numItems)
        if (zc.getDates().size() >= 0) { /* re-allocate arrays */

            int n = 32;//zc.getDates() + 32;      /* number of items to allocate */
            Date[] dateNew = new Date[n];
            double[] rateNew = new double[n];
            double[] discNew = new double[n];
            if (dateNew == null || rateNew == null || discNew == null) {
                return 0;
            }

        } /* insert new data point...*/

        if (zc.getNumItems() == 0 || zc.getDates().get(zc.getNumItems() - 1).before(date)) {
            zc.getDates().add(date); /* insert at end */

            zc.getRates().add(rate);
            zc.getDiscount().add(disc);
        } else {
            int i = zc.getNumItems() - 1;       /* loops backwards, until before date */

            zc.getDates().add(zc.getDates().get(i));
            zc.getRates().add(zc.getRates().get(i));
            zc.getDiscount().add(zc.getDiscount().get(i));

            i--;
            while (i >= 0) {
                if (zc.getDates().get(i).before(date)) /* stop when before date */ {
                    break;
                } /* shift down */

                zc.getDates().set(i + 1, zc.getDates().get(i));
                zc.getRates().set(i + 1, zc.getRates().get(i));
                zc.getDiscount().set(i + 1, zc.getDiscount().get(i));
                i--;
            }
            zc.getDates().set(i + 1, date);
            zc.getRates().set(i + 1, rate);
            zc.getDiscount().set(i + 1, disc);
        }
        return 1;
    }
///*
//***************************************************************************
//** Finds an exact date in a ZC.
//** Returns index of matching date if found, -1 if not.
//***************************************************************************
//*/

    public static int JpmcdsZCFindDateExact( /* finds an exact date in a ZCurve */
            IsdaCurve zc, /* (I) ZCurve to search */
            Date date) /* (I) date to search for */ {
        int i = JpmcdsZCFindDateClosest(zc, date);  /* search for closest date */

        if (i >= 0 && zc.getDates().get(i) != date) {
            i = -1;
        }
        return i;
    }

///*
//***************************************************************************
//** Finds index of entry closest to an input date.
//** Returns index closest to date.
//***************************************************************************
//*/
    public static int JpmcdsZCFindDateClosest( /* finds a closest date in a ZCurve */
            IsdaCurve zc, /* (I) ZCurve to search */
            Date date) /* (I) date to search for */ {
        int lo = 0;                            /* index near date, but below */
        int hi;                            /* index near date, but above */
        int diffLo;                        /* # days away from lo date */
        int diffHi;                        /* # days away from hi date */

        if (zc == null) {
            return -1;
        } else if (zc.getDates().size() == 1) {
            return 0;
        }
        while (date.before(zc.getDates().get(lo)) && lo < zc.getDates().size()) {
            lo++;
        }
        hi = lo + 1;
        diffLo = Math.abs((int) DateUtils.dateDiff(date, zc.getDates().get(lo)));
        diffHi = Math.abs((int) DateUtils.dateDiff(date, zc.getDates().get(hi)));
        return (diffLo <= diffHi) ? lo : hi;
    }

//
///*
//***************************************************************************
//** Converts zc-style rate into a discount factor.
//**
//** Note: As a special optimization, the normal ZCurve case is hard coded for
//** speed (since this routine is critical to performance of building zero-curves).
//** That is, annually-compounded rates w/ 360 or 365-fixed year are calculated
//** directly.
//***************************************************************************
//*/
    public static double JpmcdsZCComputeDiscount( /* calculate discount from ZC rate */
            IsdaCurve zc, /* (I) ZCurve assoc'd with rate */
            Date date, /* (I) date assoc'd with rate */
            double rate) /* (O) discount factor */ {
        double discount;
        if (zc.getBasis() == 1
                && rate >= -1.0
                && (date.after(zc.getBaseDate()) || date.equals(zc.getBaseDate()))
                && (zc.getDayCountConv().equals(DayCountAccessor.DayCount.ACT_365F.name) || zc.getDayCountConv().equals(DayCountAccessor.DayCount.ACT_360.name))) {
            long datediff = DateUtils.dateDiff(zc.getBaseDate(), date);
            double denom = zc.getDayCountConv().equals(DayCountAccessor.DayCount.ACT_360.name) ? 360.0 : 365.0;
            discount = Math.pow(1 + rate, datediff / denom);
            return discount;
        } else {

            discount = JpmcdsRateToDiscount(rate, zc.getBaseDate(), date, zc.getDayCountConv(), (long) zc.getBasis());
        }

        return discount;
    }

//
///*
//***************************************************************************
//** Convert from a given rate to a discount factor.
//***************************************************************************
//*/
    public static double JpmcdsRateToDiscount(double rate, /* (I) Rate */
            Date startDate, /* (I) Start date */
            Date endDate, /* (I) End date */
            String rateDayCountConv, /* (I) Day count convention for rate */
            long rateBasis /* (I) Basis for the rate */) /* (O) Discount factor */ {
        double rateYF;
        double discount;

        if (rateBasis == -2) {
            return rate;
        }

        /* get year fractions for rate */
        rateYF = DayCountAccessor.dayCountFraction(startDate, endDate, rateDayCountConv);
        discount = JpmcdsRateToDiscountYearFrac(rate, rateYF, (double) rateBasis);

        return discount;
    }

    /*
     ***************************************************************************
     ** Convert from a rate to a discount factor using a year fraction.
     **
     ** Calculate discount factor from an interest rate, given the daycount
     ** and basis.
     ***************************************************************************
     */
    public static double JpmcdsRateToDiscountYearFrac(
            double rate, /* (I) The rate */
            double yearFraction, /* (I) Year fraction */
            double basis /* (I) Basis for the rate */) /* (O) Output discount rate */ {

        double discount;
        switch ((int) basis) {
            case 0: // simple
            {
                /* Simple basis is 0 so don't worry bout dividing by it
                 */
                double denom = 1.0 + rate * yearFraction;
                discount = 1.0 / denom;
            }
            break;

            case 512: // discount rate

                discount = 1.0 - rate * yearFraction;
                break;

            case 5000: // JPMCDS_CONTINUOUS_BASIS:
                discount = Math.exp(-rate * yearFraction);
                break;

            case -2: //JPMCDS_DISCOUNT_FACTOR:
                discount = rate;
                break;

            default: {
                double tmp = 1.0 + rate / basis;
                /* Since pow(x,y) is not defined when x < 0 and y is not an integer,
                 * check before calling it.        */
                discount = Math.pow(tmp, -basis * yearFraction);
            }
            break;
        }
        return discount;
    }

//
//    /*
//     ***************************************************************************
//     ** Calculates the zero price for a given start date and maturity date.
//     ** Returns NaN for errors.
//     ***************************************************************************
//     */
    public static double JpmcdsForwardZeroPrice(IsdaCurve zeroCurve, Date startDate, Date maturityDate) {
        double startPrice = JpmcdsZeroPrice(zeroCurve, startDate);
        double maturityPrice = JpmcdsZeroPrice(zeroCurve, maturityDate);
        return maturityPrice / startPrice;
    }

//    /*
//     ***************************************************************************
//     ** Calculates the zero price for a given date. Returns NaN for errors.
//     ***************************************************************************
//     */
    public static double JpmcdsZeroPrice(IsdaCurve zeroCurve, Date date) {
        double zeroPrice = 1.0;
        double rate;
        double time;

        if (zeroCurve != null) {
            rate = JpmcdsZeroRate(zeroCurve, date);
            /*
             ** rate is continuously compounded calculated between valueDate of
             ** the zeroCurve and the required date
             */
            time = DateUtils.dateDiff(date, zeroCurve.getBaseDate()) / 365.0;
            zeroPrice = Math.exp(-rate * time);
        } else {
            logger.warn("warning no ZC Curve defined");
        }
        return zeroPrice;
    }

//    /*
//     ***************************************************************************
//     ** Calculates the zero rate for a given date using ACT/365F and continously
//     ** compounded rates.
//     ***************************************************************************
//     */
    public static double JpmcdsZeroRate(IsdaCurve zeroCurve, Date date) {
        int exact;
        int loBound;
        int hiBound;
        double rate;
        int mid = 0;
        ArrayList<Date> xArray = zeroCurve.getDates();
        int arraySize = xArray.size();

        if (date==null){
            if (zeroCurve.getRates()!=null && !zeroCurve.getRates().isEmpty()){
                return zeroCurve.getRates().get(0);
            }else {
                return 0;
            }
        } else if (date.before(xArray.get(0))) {
            exact = -1;
            loBound = -1;
            hiBound = 0;
        } else if (date.after(xArray.get(arraySize - 1))) {
            exact = -1;
            loBound = arraySize - 1;
            hiBound = arraySize;
        } else if (arraySize == 1) {
            exact = 0;
            loBound = -1;
            hiBound = arraySize;
        } else {

            int lo = 0;
            int hi = arraySize - 2;

            for (int count = arraySize + 1; count > 0; --count) {
                mid = (hi + lo) / 2;

                if (date.before(xArray.get(mid))) {
                    hi = mid - 1;
                } else if (date.after(xArray.get(mid + 1))) {
                    lo = mid + 1;
                } else {
                    break;                  /* Done */

                }
            }

            lo = mid;
            hi = mid + 1;

            if (xArray.get(lo).equals(date)) {
                exact = lo;
            } else if (xArray.get(hi).equals(date)) {
                exact = hi;
            } else {
                exact = -1;
            }

            while (lo >= 0 && (xArray.get(lo).after(date) || xArray.get(lo).equals(date))) {
                --lo;
            }

            if (lo >= 0) {
                loBound = lo;
            } else {
                loBound = -1;
            }

            while (hi < arraySize && (xArray.get(hi).before(date) || xArray.get(hi).equals(date))) {
                ++hi;
            }

            if (hi < arraySize) {
                hiBound = hi;
            } else {
                hiBound = arraySize;
            }
        }
        int lo = loBound;
        int hi = hiBound;

        if (exact >= 0) {
            /* date found in zeroDates */
            rate = zcRateCC(zeroCurve, exact);
        } else if (lo < 0) {
            /* date before start of zeroDates */
            rate = zcRateCC(zeroCurve, 0);
        } else if (hi >= zeroCurve.getNumItems()) {
            /* date after end of zeroDates */
            if (zeroCurve.getNumItems() == 1) {
                rate = zcRateCC(zeroCurve, 0);
            } else {
                /* extrapolate using last flat segment of the curve */
                lo = zeroCurve.getNumItems() - 2;
                hi = zeroCurve.getNumItems() - 1;
                rate = zcInterpRate(zeroCurve, date, lo, hi);
            }
        } else {
            /* date between start and end of zeroDates */
            rate = zcInterpRate(zeroCurve, date, lo, hi);
        }

        return rate;
    }
//    /*
//     ***************************************************************************
//     ** Interpolates a rate segment of a zero curve expressed with continuously
//     ** compounded rates using flat forwards.
//     **
//     ** Always returns a continously compounded ACT/365F rate.
//     ***************************************************************************
//     */
//

    public static double zcInterpRate(IsdaCurve zc, Date date, int lo, int hi) {
        double rate;
        long t1;
        long t2;
        long t;
        double zt;
        double z1t1;
        double z2t2;
        double z1;
        double z2;

        t1 = DateUtils.dateDiff(zc.getDates().get(lo), zc.getBaseDate());
        t2 = DateUtils.dateDiff(zc.getDates().get(hi), zc.getBaseDate());
        t = DateUtils.dateDiff(date, zc.getBaseDate());

//    assert (t > t1);
//    assert (t2 > t1);
        z1 = zcRateCC(zc, lo);
        z2 = zcRateCC(zc, hi);

        /* rates are continuously compounded, i.e. exp(-rt) */
        /* flat forwards => (zt) should be linear in t */
        z1t1 = z1 * t1;
        z2t2 = z2 * t2;
        if (t == 0) {
            /* If the date equals the base date, then the zero rate is essentially undefined and irrelevant - so let us
             get the rate for the following day which is in the right ballpark at any rate.
            An exception to this approach is when t2 = 0 as well. In this case, rate = z2 */
            if (t2 == 0) {
                return z2;
            }
            t = 1;
        }
        zt = z1t1 + (z2t2 - z1t1) * (double) (t - t1) / (double) (t2 - t1);
        rate = zt / t;

        return rate;
    }

//    /*
//     ***************************************************************************
//     ** Truncate timeline.
//     **
//     ** Truncates a timeline so that it will contain the following:
//     ** - startDate
//     ** - endDate
//     ** - criticalDates (can be null)
//     ** - nothing before startDate
//     ** - nothing after endDate
//     ***************************************************************************
//     */
    public static ArrayList<Date> JpmcdsTruncateTimeLine(ArrayList<Date> criticalDates,
            Date startDate,
            Date endDate) {

        ArrayList<Date> tl;
        ArrayList<Date> startEndDate = new ArrayList();

//    REQUIRE (endDate > startDate);
        startEndDate.add(startDate);
        startEndDate.add(endDate);

        tl = DateUtils.dateListAddDates(criticalDates, 2, startEndDate);


        /* remove dates strictly before startDate and strictly after endDate */
        tl = DateUtils.DateListTruncate(tl, startDate, true, true, true);
        tl = DateUtils.DateListTruncate(tl, endDate, true, false, true);

        return tl;
    }

//    /*
//     ***************************************************************************
//     ** Gets a continuously compounded rate from a TCurve for element idx.
//     ***************************************************************************
//     */
    public static double zcRateCC(IsdaCurve tc, int idx) {
        return JpmcdsConvertCompoundRate(tc.getRates().get(idx),
                tc.getBasis(),
                tc.getDayCountConv(),
                DayCountAccessor.JPMCDS_CONTINUOUS_BASIS,
                DayCountAccessor.DayCount.ACT_365F.name);
    }

//    /*
//     ***************************************************************************
//     ** Converts a compound rate from one frequency to another.
//     ** Can also convert between ACT-style day count conventions.
//     ***************************************************************************
//     */
//
    public static double JpmcdsConvertCompoundRate(Double inRate,
            double inBasis,
            String inDayCountConv,
            double outBasis,
            String outDayCountConv) {
        Double outRate = new Double(0.);
        double ccRate = 1.;

        /* this routine is a hotspot and was taking too long for the case where we
         do nothing */
        if (inBasis == outBasis) {
            if (inDayCountConv.equals(outDayCountConv)) {
                outRate = inRate;
            } else if (inDayCountConv.equalsIgnoreCase(DayCountAccessor.DayCount.ACT_365F.name) && outDayCountConv.equalsIgnoreCase(DayCountAccessor.DayCount.ACT_360.name)) {
                outRate = inRate * 360.0 / 365.0;
            } else if (inDayCountConv.equalsIgnoreCase(DayCountAccessor.DayCount.ACT_360.name) && outDayCountConv.equalsIgnoreCase(DayCountAccessor.DayCount.ACT_365F.name)) {
                outRate = inRate * 365.0 / 360.0;
            } else {
            }
        } else {
            double dayFactor = 1.;

            if (inDayCountConv.equalsIgnoreCase(outDayCountConv)) {
                dayFactor = 1.0;
            } else if (inDayCountConv.equals(DayCountAccessor.DayCount.ACT_365F.name) && outDayCountConv.equals(DayCountAccessor.DayCount.ACT_360.name)) {
                dayFactor = 360.0 / 365.0;
            } else if (inDayCountConv.equals(DayCountAccessor.DayCount.ACT_360.name) && outDayCountConv.equals(DayCountAccessor.DayCount.ACT_365F.name)) {
                dayFactor = 365.0 / 360.0;
            } else {
            }

            /* convert inRate to ccRate, then convert to outRate */
            if (inBasis == DayCountAccessor.JPMCDS_CONTINUOUS_BASIS) {
                ccRate = inRate * dayFactor;
            } else if (inBasis >= 1.0 && inBasis <= 365.0) {
                ccRate = dayFactor * inBasis * Math.log(1.0 + inRate / inBasis);
            } else {
            }

            if (outBasis == DayCountAccessor.JPMCDS_CONTINUOUS_BASIS) {
                outRate = ccRate;
            } else if (outBasis >= 1.0 && outBasis <= 365.0) {
                outRate = outBasis * (Math.exp(ccRate / outBasis) - 1.0);
            } else {
            }
        }
        return outRate;
    }
}
