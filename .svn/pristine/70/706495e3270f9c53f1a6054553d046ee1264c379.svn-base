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

import com.sun.jna.Library;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.DoubleByReference;

/**
 *
 * @author Benjamin Frerejean
 */
public interface IsdaPricerLinks extends Library {

    double GaiaCdsoneUpfrontCharge(NativeLong today,
            NativeLong valueDate,
            NativeLong benchmarkStartDate, /**
             * start date of benchmark CDS for internal clean spread
             * bootstrapping
             */
            NativeLong stepinDate,
            NativeLong startDate,
            NativeLong endDate,
            double couponRate,
            int payAccruedOnDefault,
            TDateInterval dateInterval,
            TStubMethod stubType,
            NativeLong accrueDCC,
            NativeLong badDayConv,
            String calendar,
            double oneSpread,
            double recoveryRate,
            int payAccruedAtStart,
            // disc Curve
            NativeLong curveDate,
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong irbadDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile);

    /**
     * (I) Holiday file
     */
    /**
     * (I) Holiday file
     *
     * @param today
     * @param valueDate
     * @param benchmarkStartDate
     * @param stepinDate
     * @param startDate
     * @param endDate
     * @param couponRate
     * @param payAccruedOnDefault
     * @param dateInterval
     * @param stubType
     * @param accrueDCC
     * @param badDayConv
     * @param holidayFile
     * @param calendar
     * @param recoveryRate
     * @param payAccruedAtStart
     * @param curveDate
     * @param instrNames
     * @param dates
     * @param rates
     * @param nInstr
     * @param mmDCC
     * @param fixedSwapFreq
     * @param floatSwapFreq
     * @param fixedSwapDCC
     * @param floatSwapDCC
     * @param irbadDayConv
     * @param upfrontCharge
     * @return
     */
    double GaiacdsCdsoneSpread(NativeLong today,
            NativeLong valueDate,
            NativeLong benchmarkStartDate, /**
             * start date of benchmark CDS for internal clean spread
             * bootstrapping
             */
            NativeLong stepinDate,
            NativeLong startDate,
            NativeLong endDate,
            double couponRate,
            int payAccruedOnDefault,
            TDateInterval dateInterval,
            TStubMethod stubType,
            NativeLong accrueDCC,
            NativeLong badDayConv,
            String calendar,
            double upfrontCharge,
            double recoveryRate,
            int payAccruedAtStart,
            /**
             * disc Curve
             */
            NativeLong curveDate,
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong irbadDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile);

    /**
     * (I) Holiday file
     */
    /**
     * (I) Holiday file
     *
     * @param today
     * @param settleDate
     * @param stepinDate
     * @param spholidayFile
     * @param startDate
     * @param endDate
     * @param payAccOnDefault
     * @param dateInterval
     * @param isPriceClean
     * @param paymentDcc
     * @param badDayConv
     * @param calendar
     * @param recoveryRate
     * @param valueDate
     * @param couponRate
     * @param instrNames
     * @param dates
     * @param rates
     * @param nInstr
     * @param mmDCC
     * @param fixedSwapFreq
     * @param stubType
     * @param fixedSwapDCC
     * @param floatSwapDCC
     * @param irbadDayConv
     * @param holidayFile
     * @param spinstrNames
     * @param spdates
     * @param sprates
     * @param floatSwapFreq
     * @param spnInstr
     * @param spbadDayConv
     * @param spmmDCC
     * @param spfloatSwapDCC
     * @param spfixedSwapFreq
     * @param spfixedSwapDCC
     * @param spfloatSwapFreq
     * @return
     */
    double GaiacdsCdsPrice(
            /**
             * cds trade
             */
            NativeLong today,
            NativeLong settleDate,
            NativeLong stepinDate,
            NativeLong startDate,
            NativeLong endDate,
            double couponRate,
            int payAccOnDefault,
            TDateInterval dateInterval,
            TStubMethod stubType,
            NativeLong paymentDcc,
            NativeLong badDayConv,
            String calendar,
            double recoveryRate,
            int isPriceClean,
            /**
             * disc Curve
             */
            NativeLong valueDate, /**
             * (I) Value date
             */
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong irbadDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile, /**
             * (I) Holiday file
             */
            /**
             * spread Curve
             */
            String spinstrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] spdates, /**
             * (I) Array of swaps dates
             */
            double[] sprates, /**
             * (I) Array of swap rates
             */
            NativeLong spnInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong spmmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong spfixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong spfloatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong spfixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong spfloatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong spbadDayConv, /**
             * (I) Bad day convention
             */
            String spholidayFile /**
     * (I) Holiday file
     */
    );

    double GaiacdsCdsFeeLegPV(
            /**
             * cds trade
             */
            NativeLong today,
            NativeLong valueDate,
            NativeLong stepinDate,
            NativeLong startDate,
            NativeLong endDate,
            int payAccOnDefault,
            TDateInterval dateInterval,
            TStubMethod stubType,
            double couponRate,
            NativeLong paymentDcc,
            NativeLong badDayConv,
            String calendar, /**
             * (I) Holiday file
             */
            int isPriceClean,
            /**
             * disc Curve
             */
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong irbadDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile, /**
             * (I) Holiday file
             */
            /**
             * spread Curve
             */
            String spinstrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] spdates, /**
             * (I) Array of swaps dates
             */
            double[] sprates, /**
             * (I) Array of swap rates
             */
            NativeLong spnInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong spmmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong spfixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong spfloatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong spfixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong spfloatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong spbadDayConv, /**
             * (I) Bad day convention
             */
            String spholidayFile /**
     * (I) Holiday file
     */
    );

    double GaiacdsCdsContingentLegPV(
            /**
             * cds trade
             */
            NativeLong today,
            NativeLong valueDate,
            NativeLong startDate,
            NativeLong endDate,
            double recoveryRate,
            /**
             * disc Curve
             */
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong irbadDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile, /**
             * (I) Holiday file
             */
            /**
             * spread Curve
             */
            String spinstrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] spdates, /**
             * (I) Array of swaps dates
             */
            double[] sprates, /**
             * (I) Array of swap rates
             */
            NativeLong spnInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong spmmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong spfixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong spfloatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong spfixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong spfloatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong spbadDayConv, /**
             * (I) Bad day convention
             */
            String spholidayFile /**
     * (I) Holiday file
     */
    );

    int JpmcdsDateFromBusDaysOffset(
            NativeLong date, /**
             * Input Date
             */
            NativeLong offset, /**
             * (I) number of business days
             */
            Pointer holidayFile, /**
             * Filename w/ Holidays
             */
            ByReference outDate);

    /**
     * output date
     */
    /**
     * output date
     *
     * @param date
     * @param baseDate
     * @param holidayFile
     * @param instrNames
     * @param dates
     * @param rates
     * @param nInstr
     * @param fixedSwapFreq
     * @param floatSwapFreq
     * @param fixedSwapDCC
     * @param mmDCC
     * @param badDayConv
     * @param floatSwapDCC
     * @return
     */
    double GaiacdsZeroPrice(
            NativeLong date,
            /**
             * spread Curve
             */
            NativeLong baseDate,
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong badDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile /**
     * (I) Holiday file
     */
    );

    DoubleByReference GaiacdsZeroCurve(
            /**
             * rate Curve
             */
            NativeLong baseDate,
            String instrNames, /**
             * (I) Array of 'M' or 'S'
             */
            NativeLong[] dates, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            NativeLong nInstr, /**
             * (I) Number of benchmark instruments
             */
            NativeLong mmDCC, /**
             * (I) DCC of MM instruments
             */
            NativeLong fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            NativeLong floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            NativeLong fixedSwapDCC, /**
             * (I) DCC of fixed leg
             */
            NativeLong floatSwapDCC, /**
             * (I) DCC of floating leg
             */
            NativeLong badDayConv, /**
             * (I) Bad day convention
             */
            String holidayFile /**
     * (I) Holiday file
     */
    );

    int JpmcdsStringToDateInterval(
            Pointer input, /**
             * (I) String w/ 1Y, 3M, 4D, etc
             */
            Pointer label, /**
             * (I) Label-for JpmcdsErrMsg only
             */
            TDateInterval interval);
}
