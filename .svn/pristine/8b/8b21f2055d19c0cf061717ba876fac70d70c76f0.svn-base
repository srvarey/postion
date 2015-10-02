package org.gaia.dao.trades.schedulers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
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
public class FRAScheduler implements IProductScheduler {

    @Override
    public Schedule buildSchedule(Product product) {
        return buildScheduleFromScheduler(product);
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
            double notional = 1;
            if (product.getNotionalMultiplier() != null) {
                notional = product.getNotionalMultiplier().doubleValue();
            }
            if (product.getProductRate() != null && product.getProductRate().getInitialNotional() != null) {
                notional = product.getProductRate().getInitialNotional().doubleValue();
            }
            BigDecimal couponRate = BigDecimal.ZERO;
            if (product.getProductRate() != null) {
                if (product.getProductRate().getRate() != null) {
                    couponRate = product.getProductRate().getRate();
                }
            }
            Product fixingProduct = null;
            if (product.loadSingleUnderlying() != null && product.loadSingleUnderlying().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.getName())) {
                fixingProduct = product.loadSingleUnderlying();
            }
            int resetLag = 0;
            if (product.getScheduler().getResetLag() != null) {
                resetLag = product.getScheduler().getResetLag();
            }

            String currency = product.getNotionalCurrency();

            ScheduleLine scheduleLine = new ScheduleLine();
            scheduleLine.setStartDate(startDate);
            scheduleLine.setResetDate(DateUtils.addOpenDay(endDate, resetLag));
            scheduleLine.setProduct(product);
            scheduleLine.setScheduleIndex(0);
            scheduleLine.setPaymentDate(endDate);
            scheduleLine.setNotional(BigDecimal.valueOf(notional));
            if (fixingProduct != null) {
                scheduleLine.setFixingProduct(fixingProduct);
            }
            scheduleLine.setFixing(BigDecimal.ZERO);
            scheduleLine.setSpread(couponRate.negate());
            scheduleLine.setCurrency(currency);
            scheduleLine.setFixed(false);
            scheduleLine.setEndDate(endDate);
            schedule = new Schedule();
            schedule.setDcc(product.getScheduler().getDaycount());
            schedule.setScheduleLines(new ArrayList());


            double period = DayCountAccessor.dayCountFraction(scheduleLine.getStartDate(), endDate, product.getScheduler().getDaycount());
            scheduleLine.setPeriod(period);
            scheduleLine.setPaymentAmount(BigDecimal.ZERO);
            schedule.getScheduleLines().add(scheduleLine);


        }
        return schedule;
    }
}
