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

import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class IsdaPricerCaller {

    private static final Logger logger = Logger.getLogger(PricingBuilder.class);

    public static double getCDSNPV(IPriceable priced, CurveObservable obsIRCurve, CurveObservable obsSpreadCurve, double recoveryRate, Date valoDate) {

        /**
         * build of discount curve
         */
        IsdaInputCurve discCurve = getCurveFromCurveObservable(obsIRCurve);

        /**
         * build of spread curve
         */
        IsdaInputCurve spreadCurve = getCurveFromCurveObservable(obsSpreadCurve);

        /**
         * call of pricer
         */
        double couponRate = 0;
        String dateInterval = "Q";
        boolean stubAtEnd = true;
        boolean longStub = false;
        String dayCount = "ACT/360";
        String cdsAdjustment = "";
        if (priced.getProduct().getSubProducts() != null) {
            if (priced.getProduct().getSubProducts().isEmpty()) {
                if (priced.getProduct().getProductRate() != null && priced.getProduct().getProductRate().getRate() != null) {
                    couponRate = priced.getProduct().getProductRate().getRate().doubleValue();
                }
                if (priced.getProduct().getScheduler() != null) {
                    dayCount = priced.getProduct().getScheduler().getDaycount();
                    if (priced.getProduct().getScheduler().getIsStubAtEnd() != null) {
                        stubAtEnd = priced.getProduct().getScheduler().getIsStubAtEnd();
                    }
                    if (priced.getProduct().getScheduler().getIsLongStub() != null) {
                        longStub = priced.getProduct().getScheduler().getIsLongStub();
                    }
                    dateInterval = getIntervalFromFrequency(priced.getProduct().getScheduler().getFrequency());
                    cdsAdjustment = priced.getProduct().getScheduler().getAdjustment();
                }
            } else {
                for (Product comp : priced.getProduct().getSubProducts()) {
                    if (comp.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                        if (comp.getProductRate() != null && comp.getProductRate().getRate() != null) {
                            couponRate = comp.getProductRate().getRate().doubleValue();
                        } else if (comp.getProductSingleTraded() != null && comp.getProductSingleTraded().getRate() != null) {
                            couponRate = comp.getProductSingleTraded().getRate().doubleValue();
                        }
                        if (comp.getScheduler() != null) {
                            if (comp.getScheduler().getDaycount() != null) {
                                dayCount = comp.getScheduler().getDaycount();
                            }
                            if (comp.getScheduler().getIsStubAtEnd() != null) {
                                stubAtEnd = comp.getScheduler().getIsStubAtEnd();
                            }
                            if (comp.getScheduler().getIsLongStub() != null) {
                                longStub = comp.getScheduler().getIsLongStub();
                            }
                            if (comp.getScheduler().getFrequency() != null) {
                                dateInterval = getIntervalFromFrequency(comp.getScheduler().getFrequency());
                            }
                            if (comp.getScheduler().getAdjustment() != null) {
                                cdsAdjustment = comp.getScheduler().getAdjustment();
                            }
                        }
                    }
                }
            }
        }
        Date today = valoDate;
        Date valueDate = valoDate;
        Date startDate = priced.getProduct().getStartDate();
        Date endDate = priced.getProduct().getMaturityDate();
        Date stepinDate = DateUtils.addCalendarDay(valoDate, 1);
        boolean payAccruedOnDefault = true;
        boolean isPriceClean = false;

        double npv = 0;
        if (startDate != null && endDate != null && discCurve != null && spreadCurve != null) {
            try {
                npv = JpmcdsCdsPrice(today, valueDate, stepinDate, startDate, endDate,
                        couponRate, payAccruedOnDefault, dateInterval,
                        stubAtEnd, longStub, dayCount, cdsAdjustment, discCurve, spreadCurve, recoveryRate, isPriceClean);
            } catch (Exception e) {
                throw e;
            }
        }
        return npv;
    }

    /**
     * build CDS
     *
     * @param today
     * @param settleDate
     * @param stepinDate
     * @param startDate
     * @param endDate
     * @param couponRate
     * @param payAccruedOnDefault
     * @param dateInterval
     * @param stubAtEnd
     * @param longStub
     * @param dayCount
     * @param adjustment
     * @param discCurve
     * @param spreadCurve
     * @param recoveryRate
     * @param isPriceClean
     * @return double
     */
    public static double JpmcdsCdsPrice(
            Date today,
            Date settleDate,
            Date stepinDate,
            Date startDate,
            Date endDate,
            double couponRate,
            boolean payAccruedOnDefault,
            String dateInterval,
            boolean stubAtEnd,
            boolean longStub,
            String dayCount,
            String adjustment,
            IsdaInputCurve discCurve,
            IsdaInputCurve spreadCurve,
            double recoveryRate,
            boolean isPriceClean) {

        NativeLong longToday = dateToLong(today);
        NativeLong longSettleDate = dateToLong(settleDate);
        NativeLong longStepinDate = dateToLong(stepinDate);
        NativeLong longStartDate = dateToLong(startDate);
        NativeLong longEndDate = dateToLong(endDate);

        int intPayAccruedOnDefault = (payAccruedOnDefault) ? 1 : 0;
        TDateInterval tdateInterval = cdsStringToDateInterval(dateInterval);

        int istubAtEnd = (stubAtEnd) ? 1 : 0;
        int ilongStub = (longStub) ? 1 : 0;
        TStubMethod tStubMethod = new TStubMethod(istubAtEnd, ilongStub);

        NativeLong accrueDCC = getLongDayCount(dayCount);
        NativeLong badDayConv = getBadDays(adjustment);
        /**
         * holiday calendar
         */
        String calendar = "NONE";

        int intIsPriceClean = (isPriceClean) ? 1 : 0;

        IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();

        /**
         * result of CDS
         */
        double result = link.GaiacdsCdsPrice(longToday, longSettleDate, longStepinDate, longStartDate, longEndDate, couponRate,
                intPayAccruedOnDefault, tdateInterval, tStubMethod, accrueDCC, badDayConv, calendar, recoveryRate, intIsPriceClean,
                /**
                 * disc Curve
                 */
                discCurve.valueDate, discCurve.instrNames, discCurve.dates, discCurve.rates, discCurve.nInstr,
                discCurve.mmDCC, discCurve.fixedSwapFreq, discCurve.floatSwapFreq, discCurve.fixedSwapDCC, discCurve.fixedSwapDCC, discCurve.irbadDayConv, discCurve.holidayFile,
                /**
                 * spread Cruve
                 */
                spreadCurve.instrNames, spreadCurve.dates, spreadCurve.rates, spreadCurve.nInstr,
                spreadCurve.mmDCC, spreadCurve.fixedSwapFreq, spreadCurve.floatSwapFreq, spreadCurve.fixedSwapDCC, spreadCurve.fixedSwapDCC, spreadCurve.irbadDayConv, spreadCurve.holidayFile);

        return result;
    }

    public static double getCDSProtectionLegNPV(IPriceable priced, CurveObservable obsIRCurve, CurveObservable obsSpreadCurve, double recoveryRate, Date valoDate) {

        /**
         * build of discount curve
         */
        IsdaInputCurve discCurve = getCurveFromCurveObservable(obsIRCurve);

        /**
         * build of spread curve
         */
        IsdaInputCurve spreadCurve = getCurveFromCurveObservable(obsSpreadCurve);

        /**
         * call of pricer
         */
        Date today = valoDate;
        Date valueDate = valoDate;
        Date startDate = priced.getProduct().getStartDate();
        if (startDate == null) {
            startDate = valoDate;
        }
        Date stepinDate = DateUtils.addCalendarDay(valoDate, 1);
        if (stepinDate.after(startDate)) {
            startDate = stepinDate;
        }
        Date endDate = priced.getProduct().getMaturityDate();
        boolean isPriceClean = false;

        double npv = 0;
        if (startDate != null && endDate != null && discCurve != null && spreadCurve != null) {
            try {
                npv = GaiacdsCdsContingentLegPV(today, valueDate, startDate, endDate,
                        discCurve, spreadCurve, recoveryRate, isPriceClean);
            } catch (Exception e) {
                throw e;
            }
        }
        return npv;
    }

    public static double GaiacdsCdsContingentLegPV(
            Date today,
            Date settleDate,
            Date startDate,
            Date endDate,
            IsdaInputCurve discCurve,
            IsdaInputCurve spreadCurve,
            double recoveryRate,
            boolean isPriceClean) {

        NativeLong longToday = dateToLong(today);
        NativeLong longSettleDate = dateToLong(settleDate);
        NativeLong longStartDate = dateToLong(startDate);
        NativeLong longEndDate = dateToLong(endDate);

        IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
        double result = link.GaiacdsCdsContingentLegPV(longToday, longSettleDate, longStartDate, longEndDate, recoveryRate,
                /**
                 * discCurve
                 */
                discCurve.instrNames, discCurve.dates, discCurve.rates, discCurve.nInstr,
                discCurve.mmDCC, discCurve.fixedSwapFreq, discCurve.floatSwapFreq, discCurve.fixedSwapDCC, discCurve.fixedSwapDCC, discCurve.irbadDayConv, discCurve.holidayFile,
                /**
                 * spread Cruve
                 */
                spreadCurve.instrNames, spreadCurve.dates, spreadCurve.rates, spreadCurve.nInstr,
                spreadCurve.mmDCC, spreadCurve.fixedSwapFreq, spreadCurve.floatSwapFreq, spreadCurve.fixedSwapDCC, spreadCurve.fixedSwapDCC, spreadCurve.irbadDayConv, spreadCurve.holidayFile);

        return result;
    }

    public static double getCDSFeeLegNPV(IPriceable priced, CurveObservable obsIRCurve, CurveObservable obsSpreadCurve, Date valoDate) {

        /**
         * build of discount curve
         */
        IsdaInputCurve discCurve = getCurveFromCurveObservable(obsIRCurve);

        /**
         * build of spread curve
         */
        IsdaInputCurve spreadCurve = getCurveFromCurveObservable(obsSpreadCurve);

        /**
         * call of pricer
         */
        double couponRate = 0;
        String dateInterval = "Q";
        boolean stubAtEnd = true;
        boolean longStub = false;
        String dayCount = "ACT/360";
        String cdsAdjustment = "";
        if (priced.getProduct().getSubProducts() != null) {
            if (priced.getProduct().getSubProducts().isEmpty()) {
                if (priced.getProduct().getProductRate() != null && priced.getProduct().getProductRate().getRate() != null) {
                    couponRate = priced.getProduct().getProductRate().getRate().doubleValue();
                }
                if (priced.getProduct().getScheduler() != null) {
                    dayCount = priced.getProduct().getScheduler().getDaycount();
                    if (priced.getProduct().getScheduler().getIsStubAtEnd() != null) {
                        stubAtEnd = priced.getProduct().getScheduler().getIsStubAtEnd();
                    }
                    if (priced.getProduct().getScheduler().getIsLongStub() != null) {
                        longStub = priced.getProduct().getScheduler().getIsLongStub();
                    }
                    dateInterval = getIntervalFromFrequency(priced.getProduct().getScheduler().getFrequency());
                    cdsAdjustment = priced.getProduct().getScheduler().getAdjustment();
                }
            } else {
                for (Product comp : priced.getProduct().getSubProducts()) {
                    if (comp.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                        if (comp.getProductRate() != null && comp.getProductRate().getRate() != null) {
                            couponRate = comp.getProductRate().getRate().doubleValue();
                        } else if (comp.getProductSingleTraded() != null && comp.getProductSingleTraded().getRate() != null) {
                            couponRate = comp.getProductSingleTraded().getRate().doubleValue();
                        }
                        if (comp.getScheduler() != null) {
                            if (comp.getScheduler().getDaycount() != null) {
                                dayCount = comp.getScheduler().getDaycount();
                            }
                            if (comp.getScheduler().getIsStubAtEnd() != null) {
                                stubAtEnd = comp.getScheduler().getIsStubAtEnd();
                            }
                            if (comp.getScheduler().getIsLongStub() != null) {
                                longStub = comp.getScheduler().getIsLongStub();
                            }
                            if (comp.getScheduler().getFrequency() != null) {
                                dateInterval = getIntervalFromFrequency(comp.getScheduler().getFrequency());
                            }
                            if (comp.getScheduler().getAdjustment() != null) {
                                cdsAdjustment = comp.getScheduler().getAdjustment();
                            }
                        }
                    }
                }
            }
        }
        Date today = valoDate;
        Date valueDate = valoDate;
        Date startDate = priced.getProduct().getStartDate();
        Date endDate = priced.getProduct().getMaturityDate();
        Date stepinDate = DateUtils.addCalendarDay(valoDate, 1);
        boolean payAccruedOnDefault = true;
        boolean isPriceClean = false;

        double npv = 0;
        if (startDate != null && endDate != null && discCurve != null && spreadCurve != null) {
            try {
                npv = GaiacdsCdsFeeLegPV(today, valueDate, stepinDate, startDate, endDate,
                        couponRate, payAccruedOnDefault, dateInterval,
                        stubAtEnd, longStub, dayCount, cdsAdjustment, discCurve, spreadCurve, isPriceClean);
            } catch (Exception e) {
                throw e;
            }
        }
        return npv;
    }

    public static double GaiacdsCdsFeeLegPV(
            Date today,
            Date settleDate,
            Date stepinDate,
            Date startDate,
            Date endDate,
            double couponRate,
            boolean payAccruedOnDefault,
            String dateInterval,
            boolean stubAtEnd,
            boolean longStub,
            String dayCount,
            String adjustment,
            IsdaInputCurve discCurve,
            IsdaInputCurve spreadCurve,
            boolean isPriceClean) {

        NativeLong longToday = dateToLong(today);
        NativeLong longSettleDate = dateToLong(settleDate);
        NativeLong longStepinDate = dateToLong(stepinDate);
        NativeLong longStartDate = dateToLong(startDate);
        NativeLong longEndDate = dateToLong(endDate);

        int intPayAccruedOnDefault = (payAccruedOnDefault) ? 1 : 0;
        TDateInterval tdateInterval = cdsStringToDateInterval(dateInterval);

        int istubAtEnd = (stubAtEnd) ? 1 : 0;
        int ilongStub = (longStub) ? 1 : 0;
        TStubMethod tStubMethod = new TStubMethod(istubAtEnd, ilongStub);

        NativeLong accrueDCC = getLongDayCount(dayCount);
        NativeLong badDayConv = getBadDays(adjustment);

        String calendar = "NONE";
        /**
         * holiday calendar
         */
        int intIsPriceClean = (isPriceClean) ? 1 : 0;

        IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
        double result = link.GaiacdsCdsFeeLegPV(longToday, longSettleDate, longStepinDate, longStartDate, longEndDate,
                intPayAccruedOnDefault, tdateInterval, tStubMethod, couponRate, accrueDCC, badDayConv, calendar, intIsPriceClean,
                /**
                 * discCurve
                 */
                discCurve.instrNames, discCurve.dates, discCurve.rates, discCurve.nInstr,
                discCurve.mmDCC, discCurve.fixedSwapFreq, discCurve.floatSwapFreq, discCurve.fixedSwapDCC, discCurve.fixedSwapDCC, discCurve.irbadDayConv, discCurve.holidayFile,
                /**
                 * spread Cruve
                 */
                spreadCurve.instrNames, spreadCurve.dates, spreadCurve.rates, spreadCurve.nInstr,
                spreadCurve.mmDCC, spreadCurve.fixedSwapFreq, spreadCurve.floatSwapFreq, spreadCurve.fixedSwapDCC, spreadCurve.fixedSwapDCC, spreadCurve.irbadDayConv, spreadCurve.holidayFile);

        return result;
    }

    public static double getCDSUpfront(Trade trade, CurveObservable obsIRCurve, double recoveryRate) {
        double upfront = 0;

        /**
         * build of TCurve
         */
        IsdaInputCurve discCurve = getCurveFromCurveObservable(obsIRCurve);

        /**
         * call of pricer
         */
        double couponRate = 0;
        String dateInterval = null;
        boolean stubAtEnd = true;
        boolean longStub = false;
        String dayCount = "ACT/360";
        String cdsAdjustment = "";
        if (trade.getProduct().getSubProducts() != null) {

            if (trade.getProduct().getSubProducts().isEmpty()) {
                if (trade.getProduct().getProductRate() != null && trade.getProduct().getProductRate().getRate() != null) {
                    couponRate = trade.getProduct().getProductRate().getRate().doubleValue();
                }
                if (trade.getProduct().getScheduler() != null) {
                    dayCount = trade.getProduct().getScheduler().getDaycount();
                    if (trade.getProduct().getScheduler().getIsStubAtEnd() != null) {
                        stubAtEnd = trade.getProduct().getScheduler().getIsStubAtEnd();
                    }
                    if (trade.getProduct().getScheduler().getIsLongStub() != null) {
                        longStub = trade.getProduct().getScheduler().getIsLongStub();
                    }
                    dateInterval = getIntervalFromFrequency(trade.getProduct().getScheduler().getFrequency());
                    cdsAdjustment = trade.getProduct().getScheduler().getAdjustment();
                }
            } else {
                for (Product comp : trade.getProduct().getSubProducts()) {
                    if (comp.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                        if (comp.getProductRate() != null && comp.getProductRate().getRate() != null) {
                            couponRate = comp.getProductRate().getRate().doubleValue();
                        }
                        if (comp.getScheduler() != null) {
                            if (!comp.getScheduler().getDaycount().isEmpty()) {
                                dayCount = comp.getScheduler().getDaycount();
                            }
                            stubAtEnd = comp.getScheduler().getIsStubAtEnd();
                            longStub = comp.getScheduler().getIsLongStub();
                            if (!comp.getScheduler().getFrequency().isEmpty()) {
                                dateInterval = getIntervalFromFrequency(comp.getScheduler().getFrequency());
                            }
                            if (!comp.getScheduler().getAdjustment().isEmpty()) {
                                cdsAdjustment = comp.getScheduler().getAdjustment();
                            }
                        }
                    }
                }
            }
        }

        Date today = trade.getValueDate();
        Date valueDate = trade.getValueDate();
        Date benchmarkStart = DateUtils.addCalendarDay(today, 1);
        /**
         * > today
         */
        Date startDate = trade.getValueDate();
        /**
         * >= today
         */
        Date endDate = trade.getProduct().getMaturityDate();
        Date stepinDate = DateUtils.addCalendarDay(today, 1);
        boolean payAccruedOnDefault = true;
        double oneSpread = trade.getMarketPrice().doubleValue();
        boolean payAccruedAtStart = true;

        if (startDate != null && endDate != null && discCurve != null) {
            try {
                upfront = JpmcdsCdsoneUpfrontCharge(today, valueDate, benchmarkStart, stepinDate, startDate, endDate,
                        couponRate, payAccruedOnDefault, dateInterval,
                        stubAtEnd, longStub, dayCount, cdsAdjustment, discCurve, oneSpread, recoveryRate, payAccruedAtStart);
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return upfront;
    }

    public static double getCDSSpread(Trade trade, CurveObservable obsIRCurve, double recoveryRate) {
        double spread = 0;

        /**
         * build of TCurve
         */
        IsdaInputCurve discCurve = getCurveFromCurveObservable(obsIRCurve);
        /**
         * call of pricer
         */
        double couponRate = 0;
        String dateInterval = null;
        boolean stubAtEnd = true;
        boolean longStub = false;
        String dayCount = "ACT/360";
        String cdsAdjustment = "";
        if (trade.getProduct().getSubProducts() != null) {
            if (trade.getProduct().getSubProducts().isEmpty()) {
                if (trade.getProduct().getProductRate() != null && trade.getProduct().getProductRate().getRate() != null) {
                    couponRate = trade.getProduct().getProductRate().getRate().doubleValue();
                }
                if (trade.getProduct().getScheduler() != null) {
                    dayCount = trade.getProduct().getScheduler().getDaycount();
                    if (trade.getProduct().getScheduler().getIsStubAtEnd() != null) {
                        stubAtEnd = trade.getProduct().getScheduler().getIsStubAtEnd();
                    }
                    if (trade.getProduct().getScheduler().getIsLongStub() != null) {
                        longStub = trade.getProduct().getScheduler().getIsLongStub();
                    }
                    dateInterval = getIntervalFromFrequency(trade.getProduct().getScheduler().getFrequency());
                    cdsAdjustment = trade.getProduct().getScheduler().getAdjustment();
                }
            } else {
                for (Product comp : trade.getProduct().getSubProducts()) {
                    if (comp.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                        if (comp.getProductRate() != null && comp.getProductRate().getRate() != null) {
                            couponRate = comp.getProductRate().getRate().doubleValue();
                        }
                        if (comp.getScheduler() != null) {
                            if (!comp.getScheduler().getDaycount().isEmpty()) {
                                dayCount = comp.getScheduler().getDaycount();
                            }
                            stubAtEnd = comp.getScheduler().getIsStubAtEnd();
                            longStub = comp.getScheduler().getIsLongStub();
                            if (!comp.getScheduler().getFrequency().isEmpty()) {
                                dateInterval = getIntervalFromFrequency(comp.getScheduler().getFrequency());
                            }
                            if (!comp.getScheduler().getAdjustment().isEmpty()) {
                                cdsAdjustment = comp.getScheduler().getAdjustment();
                            }
                        }
                    }
                }
            }
        }

        Date today = trade.getValueDate();
        Date valueDate = trade.getValueDate();
        Date benchmarkStart = DateUtils.addCalendarDay(today, 1);
        Date startDate = trade.getValueDate();
        Date endDate = trade.getProduct().getMaturityDate();
        Date stepinDate = DateUtils.addCalendarDay(today, 1);
        boolean payAccruedOnDefault = true;
        double upfront = trade.getAmount().doubleValue() / trade.getQuantity(obsIRCurve.getValDate()).doubleValue();
        boolean payAccruedAtStart = true;

        if (startDate != null && endDate != null && discCurve != null) {
            try {
                spread = JpmcdsCdsoneSpread(today, valueDate, benchmarkStart, stepinDate, startDate, endDate,
                        couponRate, payAccruedOnDefault, dateInterval,
                        stubAtEnd, longStub, dayCount, cdsAdjustment, discCurve, upfront, recoveryRate, payAccruedAtStart);
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return spread;
    }

    public static double getSurvivalProbability(Date date, CurveObservable obsSpreadCurve) {
        double result = 0;

        try {
            BigDecimal df = obsSpreadCurve.getDiscountFactor(date);
            result = df.doubleValue();

//            NativeLong longDate = dateToLong(date);
//
//            /**
//             * build of spread TCurve
//             */
//            IsdaInputCurve spreadCurve = getCurveFromCurveObservable(obsSpreadCurve);
//            if (spreadCurve != null) {
//                IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
//                result = link.GaiacdsZeroPrice(longDate,
//                        /**
//                         * spread Cruve
//                         */
//                        spreadCurve.valueDate, spreadCurve.instrNames, spreadCurve.dates, spreadCurve.rates, spreadCurve.nInstr,
//                        spreadCurve.mmDCC, spreadCurve.fixedSwapFreq, spreadCurve.floatSwapFreq, spreadCurve.fixedSwapDCC, spreadCurve.fixedSwapDCC, spreadCurve.irbadDayConv, spreadCurve.holidayFile);
//            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static double[] getZeroCoupon(CurveObservable curve) {

        /**
         * build of spread TCurve
         */
        IsdaInputCurve spreadCurve = getCurveFromCurveObservable(curve);
        double[] result = null;
        try {
            if (spreadCurve != null) {
                IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
                DoubleByReference res = link.GaiacdsZeroCurve( //rate Curve
                        spreadCurve.valueDate, spreadCurve.instrNames, spreadCurve.dates, spreadCurve.rates, spreadCurve.nInstr,
                        spreadCurve.mmDCC, spreadCurve.fixedSwapFreq, spreadCurve.floatSwapFreq, spreadCurve.fixedSwapDCC, spreadCurve.fixedSwapDCC, spreadCurve.irbadDayConv, spreadCurve.holidayFile);
                if (res != null) {
                    Pointer pointer = res.getPointer();
                    result = pointer.getDoubleArray(0, curve.getQuotes().size());
                } else {
                    logger.error("ISDA DLL ERROR on cdsZeroCurve");
                    IsdaLibraryLoader.deleteLink();
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return result;
    }

    public static double JpmcdsCdsoneUpfrontCharge(
            Date today,
            Date valueDate,
            Date benchmarkStartDate, /**
             * start date of benchmark CDS for internal clean spread
             * bootstrapping
             */
            Date stepinDate,
            Date startDate, /**
             * CDS start date, can be in the past
             */
            Date endDate,
            double couponRate,
            boolean payAccruedOnDefault,
            String dateInterval,
            boolean stubAtEnd,
            boolean longStub,
            String dayCount,
            String adjustment,
            IsdaInputCurve discCurve,
            double oneSpread,
            double recoveryRate,
            boolean payAccruedAtStart) {

        NativeLong longToday = dateToLong(today);
        NativeLong longValueDate = dateToLong(valueDate);
        NativeLong longBenchmarkStartDate = dateToLong(benchmarkStartDate);
        NativeLong longStepinDate = dateToLong(stepinDate);
        NativeLong longStartDate = dateToLong(startDate);
        NativeLong longEndDate = dateToLong(endDate);
        int intPayAccruedOnDefault = (payAccruedOnDefault) ? 1 : 0;

        TDateInterval tdateInterval = cdsStringToDateInterval(dateInterval);

        int istubAtEnd = (stubAtEnd) ? 1 : 0;
        int ilongStub = (longStub) ? 1 : 0;
        TStubMethod tStubMethod = new TStubMethod(istubAtEnd, ilongStub);

        NativeLong accrueDCC = getLongDayCount(dayCount);
        NativeLong badDayConv = getBadDays(adjustment);
        int intPayAccruedAtStart = (payAccruedAtStart) ? 1 : 0;

        String calendar = "NONE";
        /**
         * holiday calendar
         */

        IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();

        double result = 0;
        if (discCurve != null) {
            result = link.GaiaCdsoneUpfrontCharge(longToday, longValueDate, longBenchmarkStartDate, longStepinDate, longStartDate, longEndDate, couponRate, intPayAccruedOnDefault,
                    tdateInterval, tStubMethod, accrueDCC, badDayConv, calendar, oneSpread, recoveryRate, intPayAccruedAtStart,
                    /**
                     * discCurve
                     */
                    discCurve.valueDate, discCurve.instrNames, discCurve.dates, discCurve.rates, discCurve.nInstr,
                    discCurve.mmDCC, discCurve.fixedSwapFreq, discCurve.floatSwapFreq, discCurve.fixedSwapDCC, discCurve.fixedSwapDCC, discCurve.irbadDayConv, discCurve.holidayFile);
        }
        return result;

    }

    public static double JpmcdsCdsoneSpread(
            Date today,
            Date valueDate,
            Date benchmarkStartDate, /**
             * start date of benchmark CDS for internal clean spread
             * bootstrapping
             */
            Date stepinDate,
            Date startDate, /**
             * CDS start date, can be in the past
             */
            Date endDate,
            double couponRate,
            boolean payAccruedOnDefault,
            String dateInterval,
            boolean stubAtEnd,
            boolean longStub,
            String dayCount,
            String adjustment,
            IsdaInputCurve discCurve,
            double oneSpread,
            double recoveryRate,
            boolean payAccruedAtStart) {

        NativeLong longToday = dateToLong(today);
        NativeLong longValueDate = dateToLong(valueDate);
        NativeLong longBenchmarkStartDate = dateToLong(benchmarkStartDate);
        NativeLong longStepinDate = dateToLong(stepinDate);
        NativeLong longStartDate = dateToLong(startDate);
        NativeLong longEndDate = dateToLong(endDate);
        int intPayAccruedOnDefault = (payAccruedOnDefault) ? 1 : 0;

        TDateInterval tdateInterval = cdsStringToDateInterval(dateInterval);

        int istubAtEnd = (stubAtEnd) ? 1 : 0;
        int ilongStub = (longStub) ? 1 : 0;
        TStubMethod tStubMethod = new TStubMethod(istubAtEnd, ilongStub);

        NativeLong accrueDCC = getLongDayCount(dayCount);
        NativeLong badDayConv = getBadDays(adjustment);
        int intPayAccruedAtStart = (payAccruedAtStart) ? 1 : 0;

        String calendar = "NONE";
        /**
         * holiday calendar
         */

        IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
        double result = 0;
        if (discCurve != null) {
            result = link.GaiacdsCdsoneSpread(longToday, longValueDate, longBenchmarkStartDate, longStepinDate, longStartDate, longEndDate, couponRate, intPayAccruedOnDefault,
                    tdateInterval, tStubMethod, accrueDCC, badDayConv, calendar, oneSpread, recoveryRate, intPayAccruedAtStart,
                    /**
                     * discCurve
                     */
                    discCurve.valueDate, discCurve.instrNames, discCurve.dates, discCurve.rates, discCurve.nInstr,
                    discCurve.mmDCC, discCurve.fixedSwapFreq, discCurve.floatSwapFreq, discCurve.fixedSwapDCC, discCurve.fixedSwapDCC, discCurve.irbadDayConv, discCurve.holidayFile);
        }
        return result;

    }

    public static IsdaInputCurve getCurveFromCurveObservable(CurveObservable observable) {

        /**
         * build of the TCurve
         */
        IsdaInputCurve curve = null;

        if (observable != null) {
            Date dateEnd = DateUtils.getDate();
            List<MarketQuote> quotes = observable.getQuotes();
            if (quotes != null) {
                String[] instrTypes = new String[quotes.size()];
                Date[] datesArray = new Date[quotes.size()];
                double[] rates = new double[quotes.size()];
                String MMDayCount = "ACT/360";
                String fixedSwapDayCount = "30/360";
                String floatSwapDayCount = "ACT/360";
                String fixedSwapFreq = "Semi-Annual";
                String floatSwapFreq = FrequencyUtil.Frequency.QUARTERLY.name;
                String adjustment = DateUtils.ADJUSTMENT_MODIFIED_FOLLOWING;
                for (int i = 0; i < quotes.size(); i++) {
                    MarketQuote marketQuote = quotes.get(i);
                    if (marketQuote.getProduct().getProductType().equals(ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING.name)) {
                        instrTypes[i] = "S";
                        if (marketQuote.getProduct().getSubProducts() != null) {
                            for (Product component : marketQuote.getProduct().getSubProducts()) {
                                if (component.getProductType().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)) {
                                    if (component.getScheduler() != null) {
                                        if (!component.getScheduler().getDaycount().isEmpty()) {
                                            fixedSwapDayCount = component.getScheduler().getDaycount();
                                        }
                                        if (!component.getScheduler().getFrequency().isEmpty()) {
                                            fixedSwapFreq = component.getScheduler().getFrequency();
                                        }
                                    }

                                } else if (component.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                                    if (component.getScheduler() != null) {
                                        if (!component.getScheduler().getDaycount().isEmpty()) {
                                            floatSwapDayCount = component.getScheduler().getDaycount();
                                        }
                                        if (!component.getScheduler().getFrequency().isEmpty()) {
                                            floatSwapFreq = component.getScheduler().getFrequency();
                                        }
                                    }
                                }
                            }
                        }
                    } else if (marketQuote.getProduct().getProductType().equals(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.name)) {
                        instrTypes[i] = "M";
                        if (marketQuote.getProduct().getScheduler() != null) {
                            if (marketQuote.getProduct().getScheduler().getDaycount() != null && !marketQuote.getProduct().getScheduler().getDaycount().isEmpty()) {
                                MMDayCount = marketQuote.getProduct().getScheduler().getDaycount();
                            }
                        }
                    } else {
                        instrTypes[i] = "M";
                    }
                    if (marketQuote.getDateEnd() != null) {
                        dateEnd = marketQuote.getDateEnd();
//                        dateEnd=DateUtils.adjustDate(dateEnd,DateUtils.ADJUSTMENT_FOLLOW,null);
                    }
                    datesArray[i] = dateEnd;
                    rates[i] = marketQuote.getClose().doubleValue();
                    /**
                     * % to real
                     */
                }
                Date valueDate = observable.getValDate();
//               valueDate=DateUtils.adjustDate(valueDate,DateUtils.ADJUSTMENT_FOLLOW,null);
                curve = JpmcdsBuildIsdaInputCurve(valueDate, instrTypes, datesArray, rates,
                        MMDayCount, fixedSwapFreq, floatSwapFreq, fixedSwapDayCount, floatSwapDayCount, adjustment);
            }
        }
        return curve;
    }

    public static NativeLong getLongDayCount(String dayCount) {
        long accrueDCC;
        switch (dayCount) {
            case "ACT/365":
                accrueDCC = 1;
                break;
            case "ACT/365F":
                accrueDCC = 2;
                break;
            case "ACT/360":
                accrueDCC = 3;
                break;
            case "30/360":
                accrueDCC = 4;
                break;
            case "30E/360":
                accrueDCC = 5;
                break;
            case "ACT/ACT":
                accrueDCC = 1;
                break;
            default:
                accrueDCC = 3;
                break;
        }

        return new NativeLong(accrueDCC);
    }

    public static String getStringDayCount(NativeLong dayCount) {
        String accrueDCC = "";
        switch (dayCount.intValue()) {
            case 1:
                accrueDCC = DayCountAccessor.DayCount.ACT_365.getName();
                break;
            case 2:
                accrueDCC = DayCountAccessor.DayCount.ACT_365F.getName();
                break;
            case 3:
                accrueDCC = DayCountAccessor.DayCount.ACT_360.getName();
                break;
            case 4:
                accrueDCC = DayCountAccessor.DayCount.B30_360.getName();
                break;
            case 5:
                accrueDCC = DayCountAccessor.DayCount.B30E_360.getName();
                break;
        }
        return accrueDCC;
    }

    public static String getIntervalFromFrequency(String freq) {
        String interval = "Q";
        if (freq.equals(FrequencyUtil.Frequency.QUARTERLY.name)) {
            interval = "Q";
        } else if (freq.equals(FrequencyUtil.Frequency.ANNUAL.name)) {
            interval = "A";
        } else if (freq.equals(FrequencyUtil.Frequency.SEMIANNUAL.name)) {
            interval = "S";
        } else if (freq.equals(FrequencyUtil.Frequency.DAILY.name)) {
            interval = "D";
        } else if (freq.equals(FrequencyUtil.Frequency.MONTHLY.name)) {
            interval = "M";
        } else if (freq.equals(FrequencyUtil.Frequency.ZEROCOUPON.name)) {
            interval = "Z";
        }
        return interval;

    }

    public static NativeLong getLongFrequency(String freq) {
        long fr = 0;

        if (freq.equals(FrequencyUtil.Frequency.QUARTERLY.name)) {
            fr = 4;
        } else if (freq.equals(FrequencyUtil.Frequency.ANNUAL.name)) {
            fr = 1;
        } else if (freq.equals(FrequencyUtil.Frequency.SEMIANNUAL.name)) {
            fr = 2;
        } else if (freq.equals(FrequencyUtil.Frequency.MONTHLY.name)) {
            fr = 12;
        }
        return new NativeLong(fr);
    }

    public static NativeLong getBadDays(String adjustment) {
        long badDayConv;
        switch (adjustment) {
            case DateUtils.ADJUSTMENT_NONE:
                badDayConv = (long) 'N';
                break;
            case DateUtils.ADJUSTMENT_MODIFIED_FOLLOWING:
                badDayConv = (long) 'M';
                break;
            case DateUtils.ADJUSTMENT_FOLLOW:
                badDayConv = (long) 'F';
                break;
            case DateUtils.ADJUSTMENT_PREVIOUS:
                badDayConv = (long) 'P';
                break;
            default:
                badDayConv = 78;
                break;
        }

        return new NativeLong(badDayConv);
    }

    public static String getStringBadDays(long adjustment) {
        String badDayConv;
        switch ((int) adjustment) {
            case (int) 'N':
                badDayConv = DateUtils.ADJUSTMENT_NONE;
                break;
            case (int) 'M':
                badDayConv = DateUtils.ADJUSTMENT_MODIFIED_FOLLOWING;
                break;
            case (int) 'F':
                badDayConv = DateUtils.ADJUSTMENT_FOLLOW;
                break;
            case (int) 'P':
                badDayConv = DateUtils.ADJUSTMENT_PREVIOUS;
                break;
            default:
                badDayConv = DateUtils.ADJUSTMENT_FOLLOW;
        }
        return badDayConv;
    }

    public static IsdaInputCurve JpmcdsBuildIsdaInputCurve(
            Date valueDate, /**
             * (I) Value date
             */
            String[] instrTypes, /**
             * (I) Array of 'M' or 'S'
             */
            Date[] datesarr, /**
             * (I) Array of swaps dates
             */
            double[] rates, /**
             * (I) Array of swap rates
             */
            String dayCount, /**
             * (I) DCC of MM instruments
             */
            String fixedSwapFreq, /**
             * (I) Fixed leg freqency
             */
            String floatSwapFreq, /**
             * (I) Floating leg freqency
             */
            String fixedSwapDayCount, /**
             * (I) DCC of fixed leg
             */
            String floatSwapDayCount, /**
             * (I) DCC of floating leg
             */
            String adjustment) /**
     * (I) Bad day convention
     */
    {

        NativeLong TValueDate = dateToLong(valueDate);

        String concat = "";
        for (String instrType : instrTypes) {
            concat = concat + instrType;
        }
        if (concat.length() > 0) {
            Memory instrNames = new Memory(concat.length());
            instrNames.write(0, concat.getBytes(), 0, concat.length());

            NativeLong[] longdates = new NativeLong[datesarr.length];
            for (int i = 0; i < datesarr.length; i++) {
                longdates[i] = dateToLong(datesarr[i]);
            }

            NativeLong nInstr = new NativeLong(instrTypes.length);
            NativeLong accrueDCC = getLongDayCount(dayCount);
            NativeLong freqfixed = getLongFrequency(fixedSwapFreq);

            NativeLong freqfloat = getLongFrequency(floatSwapFreq);
            NativeLong fixedSwapDCC = getLongDayCount(fixedSwapDayCount);
            NativeLong floatSwapDCC = getLongDayCount(floatSwapDayCount);
            NativeLong badDayConv = getBadDays(adjustment);

            String holidayFile = "NONE";

            IsdaInputCurve curve = new IsdaInputCurve(TValueDate, concat, longdates, rates, nInstr, accrueDCC, freqfixed, freqfloat, fixedSwapDCC, floatSwapDCC, badDayConv, holidayFile);
            return curve;
        }
        return null;
    }

    public static TDateInterval cdsStringToDateInterval(String input) {
        TDateInterval interval = null;
        if (input != null) {
            Memory mem = new Memory(input.length());
            mem.write(0, input.getBytes(), 0, input.length());
            Memory mem2 = new Memory(1);

            interval = new TDateInterval();
            IsdaPricerLinks link = IsdaLibraryLoader.getIdsdaLink();
            int res = link.JpmcdsStringToDateInterval(mem, mem2, interval);

            if (res == -1) {
                logger.error("Error in ISDA pricer : cdsStringToDateInterval");
            }
        }
        return interval;
    }

    public static NativeLong dateToLong(Date date) {
        if (date != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.set(Calendar.AM_PM, Calendar.AM);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date = cal.getTime();
            Date startDate = new Date();
            startDate.setTime(0);
            startDate = DateUtils.addCalendarDay(startDate, 4);
            return new NativeLong(DateUtils.dateDiff(startDate, date));
        }
        return new NativeLong();
    }
}
