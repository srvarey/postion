package org.gaia.dao.trades.schedulers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.pricing.pricers.isda.IsdaPricerCaller;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import static org.gaia.dao.trades.ScheduleBuilder.ACCRUAL_PAY_ALL;
import static org.gaia.dao.trades.ScheduleBuilder.ACCRUAL_PAY_NONE;
import org.gaia.dao.utils.DateInterval;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;

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
 *
 *
 * /**
 *
 * @author Benjamin Frerejean
 */
public class RateScheduler implements IProductScheduler {

    @Override
    public Schedule buildSchedule(Product product) {
        Schedule schedule = buildScheduleFromScheduler(product);
        if (product.getProductRate() != null && product.getProductRate().getRepaymentPrice() != null
                && product.getProductRate().getRepaymentPrice().doubleValue() != 0) {
            // bond repayment
            ScheduleLine scheduleLine = new ScheduleLine();
            scheduleLine.setStartDate(product.getMaturityDate());
            scheduleLine.setResetDate(product.getMaturityDate());
            scheduleLine.setEndDate(product.getMaturityDate());
            scheduleLine.setProduct(product);
            scheduleLine.setScheduleIndex(schedule.getScheduleLines().size() + 1);
            int paymentLag = 0;
            if (product.getScheduler().getPaymentLag() != null) {
                paymentLag = product.getScheduler().getPaymentLag();
            }
            scheduleLine.setPaymentDate(DateUtils.addOpenDay(product.getMaturityDate(), paymentLag));
            double notional = 1;
            if (product.getNotionalMultiplier() != null) {
                notional = product.getNotionalMultiplier().doubleValue();
            }
            if (product.getProductRate() != null && product.getProductRate().getInitialNotional() != null) {
                notional = product.getProductRate().getInitialNotional().doubleValue();
            }
            scheduleLine.setNotional(BigDecimal.valueOf(notional));
            BigDecimal price=product.getProductRate().getRepaymentPrice().divide(NumberUtils.BIGDECIMAL_100);
            scheduleLine.setFixing(price);
            scheduleLine.setSpread(BigDecimal.ZERO);
            scheduleLine.setCurrency(product.getNotionalCurrency());
            scheduleLine.setFixed(true);
            scheduleLine.setPeriod(0.);
            scheduleLine.setPaymentAmount(scheduleLine.getNotional().multiply(price));
            schedule.getScheduleLines().add(scheduleLine);

        }
        return schedule;
    }

    @Override
    public BigDecimal calculateAmount(ScheduleLine line) {
        return line.getFixing().add(line.getSpread()).multiply(line.getNotional()).multiply(BigDecimal.valueOf(line.getPeriod()));
    }

    private static Schedule buildScheduleFromScheduler(Product product) {
        Schedule schedule = null;
        if (product != null && product.getScheduler() != null) {
            Date startDate = product.getStartDate();
            Date endDate = product.getMaturityDate();
            String frequency = product.getScheduler().getFrequency();
            boolean isStubAtEnd = false;
            boolean isLongStub = false;
            double notional = 1;
            if (product.getNotionalMultiplier() != null) {
                notional = product.getNotionalMultiplier().doubleValue();
            }
            if (product.getProductRate() != null && product.getProductRate().getInitialNotional() != null) {
                notional = product.getProductRate().getInitialNotional().doubleValue();
            }
            double couponRate = 0;
            Product fixingProduct = null;
            if (product.getProductRate() != null) {
                if (product.getProductRate().getRate() != null) {
                    couponRate = product.getProductRate().getRate().doubleValue();
                }
            }
            if (product.loadSingleUnderlying() != null) {
                if (product.loadSingleUnderlying().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName())) {
                    fixingProduct = product.loadSingleUnderlying();
                }
            }
            double spread = 0;
            if (product.getProductRate() != null) {
                if (product.getProductRate().getSpread() != null) {
                    spread = product.getProductRate().getSpread().doubleValue();
                }
            }

            int resetLag = 0;
            if (product.getScheduler().getResetLag() != null) {
                resetLag = product.getScheduler().getResetLag();
            }
            int paymentLag = 0;
            if (product.getScheduler().getPaymentLag() != null) {
                paymentLag = product.getScheduler().getPaymentLag();
            }
            String paymentDayCount;
            if (product.getScheduler().getDaycount() != null) {
                paymentDayCount = product.getScheduler().getDaycount();
            } else {
                paymentDayCount = "ACT/360";
            }
            String adjustmentConv = "None";
            if (product.getScheduler().getAdjustment() != null) {
                adjustmentConv = product.getScheduler().getAdjustment();
            }
            if (product.getScheduler().getFirstDate() != null) {
                startDate = product.getScheduler().getFirstDate();
                isStubAtEnd = true;
            }
            if (product.getProductSingleTraded() != null) {
                if (product.getProductSingleTraded().getRate() != null) {
                    couponRate = product.getProductSingleTraded().getRate().doubleValue();
                }
                if (product.getProductSingleTraded().getSpread() != null) {
                    spread = product.getProductSingleTraded().getSpread().doubleValue();
                }
            }
            String currency = product.getNotionalCurrency();
            HolidayCalendar calendar = CalendarAccessor.getCalendarByCode(product.getCalendar());
            schedule = buildSchedule(startDate, endDate, frequency, isStubAtEnd, isLongStub, notional, couponRate, spread, resetLag,
                    paymentLag, fixingProduct, paymentDayCount, adjustmentConv, currency, calendar, product);
        }
        return schedule;
    }

    /**
     * build Schedule
     */
    private static Schedule buildSchedule(
            Date startDate,
            Date endDate,
            String frequency,
            boolean isStubAtEnd,
            boolean isLongStub,
            double notional,
            double couponRate,
            double spread,
            int resetLag,
            int paymentLag,
            Product fixingProduct,
            String paymentDayCount,
            String adjustmentConv,
            String currency,
            HolidayCalendar calendar,
            Product product) {

        boolean protectStart = false;
        boolean payAccOnDefault = false;

        boolean isFixed = true;
        if (fixingProduct != null) {
            isFixed = false;
        }

        String sdateInterval = IsdaPricerCaller.getIntervalFromFrequency(frequency);
        DateInterval dateInterval = DateIntervalUtil.JpmcdsStringToDateInterval(sdateInterval);
        ArrayList<Date> dates = new ArrayList();
        Schedule schedule = null;
        Date prevDate;
        Date prevDateAdj;
        int i;

        if (endDate != null && startDate != null) {
            if (endDate.equals(startDate)) {
                dates.add(startDate);
                dates.add(endDate);
            } else {
                dates = JpmcdsDateListMakeRegular(startDate, endDate, dateInterval, isStubAtEnd, isLongStub);
            }

            /**
             * the datelist includes both start date and end date
             */
            /**
             * therefore it has one more element than the fee leg requires
             */
            schedule = new Schedule();
            schedule.setScheduleLines(new ArrayList(dates.size() - 1));


            if (payAccOnDefault) {
                schedule.setAccrualPayConv(ACCRUAL_PAY_ALL);
            } else {
                schedule.setAccrualPayConv(ACCRUAL_PAY_NONE);
                /**
                 * and we will assume that it observes at end of the period
                 */
            }
            schedule.setDcc(paymentDayCount);
            prevDate = dates.get(0);
            /**
             * first date is not bad day adjusted
             */
            prevDateAdj = prevDate;

            for (i = 0; i < dates.size() - 1; ++i) {
                double rate = couponRate;
                Date nextDate = dates.get(i + 1);
                Date nextDateAdj;

                nextDateAdj = DateUtils.adjustDate(nextDate, adjustmentConv, calendar);
                Date resetDate = DateUtils.addOpenDay(nextDateAdj, resetLag * -1);

                ScheduleLine scheduleLine = new ScheduleLine();
                scheduleLine.setStartDate(prevDateAdj);
                scheduleLine.setResetDate(resetDate);
                scheduleLine.setProduct(product);
                scheduleLine.setScheduleIndex(i);
                scheduleLine.setPaymentDate(DateUtils.addOpenDay(nextDateAdj, paymentLag));
                scheduleLine.setNotional(BigDecimal.valueOf(notional));
                if (fixingProduct != null) {
                    scheduleLine.setFixingProduct(fixingProduct);
//                    String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
//                    if (product.getScheduler().getIsCompound()) {
//                        rate = calculateCompounedRate(fixingProduct, prevDateAdj, nextDateAdj, quoteSet, paymentDayCount);
//                    } else {
//                        rate = MarketQuoteAccessor.getProductQuoteValue(fixingProduct.getId(), resetDate, quoteSet);
//                    }
                }
                scheduleLine.setFixing(BigDecimal.valueOf(rate));
                scheduleLine.setSpread(BigDecimal.valueOf(spread));
                scheduleLine.setCurrency(currency);
                scheduleLine.setFixed(isFixed);
                Date realEndDate;
                if (i < dates.size() - 2) {
                    scheduleLine.setEndDate(nextDateAdj);
                    realEndDate = nextDateAdj;
                } else {
                    // the last date ids not adjusted
                    scheduleLine.setEndDate(nextDate);
                    realEndDate=nextDate;
                    // and the last day is accrued
//                    realEndDate = DateUtils.addCalendarDay(nextDate, 1); // i retrieved this: when predent a zero day makes interest
                }
                double period = DayCountAccessor.dayCountFraction(scheduleLine.getStartDate(), realEndDate, schedule.getDcc());
                scheduleLine.setPeriod(period);

                /**
                 * calculation of the amount
                 */
                double payAmount = period * notional * (couponRate + spread);
                scheduleLine.setPaymentAmount(BigDecimal.valueOf(payAmount));

                schedule.getScheduleLines().add(scheduleLine);

                prevDate = nextDate;
                prevDateAdj = nextDateAdj;
            }
            adjustAcrualDate(schedule, protectStart, prevDate);
        }
        return schedule;

    }

    private static void adjustAcrualDate(Schedule schedule, boolean protectStart, Date prevDate) {
        /**
         * the last accrual date is not adjusted also we may have one extra day
         * of accrued interest
         */
        if (schedule.getScheduleLines().size() > 0) {
            if (protectStart) {
                ScheduleLine line = schedule.getScheduleLines().get(schedule.getScheduleLines().size() - 1);
                line.setEndDate(DateUtils.addCalendarDay(prevDate, 1));
                schedule.setObsStartOfDay(true);
            } else {
                ScheduleLine line = schedule.getScheduleLines().get(schedule.getScheduleLines().size() - 1);
                line.setEndDate(prevDate);
                schedule.setObsStartOfDay(false);
            }
        }
    }

    /**
     ***************************************************************************
     ** Makes a date list from a given start date to a given end date with
     * dates * seperated by a given interval. * * Use the stub parameter to
     * determine whether the stub appears at the * start or the end of the date
     * list, and whether the stub is long or * short. * * The start date and end
     * date are always both in the date list. * The end date must be strictly
     * after the start date. * The date interval must be positive.
     * **************************************************************************
     * @param startDate
     * @param endDate
     * @param interval
     * @param isStubAtEnd
     * @param isLongStub
     * @return
     */
    public static ArrayList<Date> JpmcdsDateListMakeRegular(
            Date startDate, /**
             * (I) Start date
             */
            Date endDate, /**
             * (I) End date
             */
            DateInterval interval, /**
             * (I) Date interval
             */
            boolean isStubAtEnd,
            boolean isLongStub) /**
     * (I) Stub type
     */
    {

        ArrayList<Date> dates = new ArrayList();
        Date[] tmpDates = new Date[100];
        int i;
        int numIntervals;
        int numTmpDates = 100;
        int totalDates = 0;
        Date date;
        DateInterval multiInterval;

        /**
         * we calculate tmpDates in blocks of 100 and add to the datelist
         */
        if (interval.prd==0){
            dates.add(startDate);
            dates.add(endDate);
            return dates;
        } else if (!isStubAtEnd) {
            /**
             * front stub - so we start at endDate and work backwards
             */
            numIntervals = 0;
            i = numTmpDates;
            date = endDate;
            while (date.after(startDate)) {
                --i;
                --numIntervals;
                ++totalDates;
                tmpDates[i] = date;

                multiInterval = new DateInterval(interval.prd * numIntervals, interval.prd_typ, 0);
                date = DateIntervalUtil.JpmcdsDtFwdAny(endDate, multiInterval);
            }
            if (date == startDate || totalDates == 1 || !isLongStub) {
                if (i == 0) {
                }
                --i;
                ++totalDates;
                tmpDates[i] = startDate;
            } else {
                tmpDates[i] = startDate;
            }
            /**
             * now add from tmpDates[i] to tmpDates[numTmpDates-1] to date list
             */
            for (int j = i; j < numTmpDates; j++) {
                dates.add(tmpDates[j]);
            }
        } else {
            /**
             * back stub - so we start at startDate and work forwards
             */
            numIntervals = 0;
            i = -1;
            date = startDate;
            while (date.before(endDate)) {
                ++i;
                ++totalDates;
                if (i == numTmpDates) {
                    i = 0;
                }
                ++numIntervals;
                assert (i < numTmpDates);
                tmpDates[i] = date;

                multiInterval = new DateInterval(interval.prd * numIntervals, interval.prd_typ, 0);
                date = DateIntervalUtil.JpmcdsDtFwdAny(startDate, multiInterval);

            }
            if (date == endDate || totalDates == 1 || isStubAtEnd && !isLongStub) {
                /**
                 * don't change existing tmpDates[] but need to add endDate
                 */
                ++i;
                ++totalDates;
                tmpDates[i] = endDate;
            } else {
                tmpDates[i] = endDate;
            }
            /**
             * now add from tmpDates[0] to tmpDates[i] to the date list
             */
            for (int j = 0; j <= i; j++) {
                dates.add(tmpDates[j]);
            }
        }

        return dates;
    }
}
