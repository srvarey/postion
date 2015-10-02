/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
*/
package org.gaia.dao.trades.schedulers;

import java.math.BigDecimal;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;


/**
 *
 * @author Benjamin Frerejean
 */


public class FXSwapScheduler implements IProductScheduler{

    @Override
    public Schedule buildSchedule(Product product) {
        Schedule schedule=new Schedule();
        // cur 1 start
        if (product.getUnderlying()!=null && product.getUnderlying().getProductForex()!=null && product.getUnderlying().getProductForex().getCurrency1()!=null){
            ScheduleLine line1 = new ScheduleLine(product.getStartDate(),product.getUnderlying().getProductForex().getCurrency1().getShortName(),
                    product.getStartDate(), product, null, product.getStartDate(),product.getMaturityDate());
            line1.setFixed(Boolean.TRUE);
            line1.setPaymentAmount(product.getNotionalMultiplier());
            schedule.getScheduleLines().add(line1);

            // cur 2 at start is auto
            // cur 1 end
            ScheduleLine line2 = new ScheduleLine(product.getMaturityDate(),product.getUnderlying().getProductForex().getCurrency1().getShortName(),
                    product.getMaturityDate(), product, null, product.getStartDate(),product.getMaturityDate());
            line2.setFixed(Boolean.TRUE);
            line2.setPaymentAmount(product.getNotionalMultiplier().negate());
            schedule.getScheduleLines().add(line2);
            // cur 2 end
            ScheduleLine line3 = new ScheduleLine(product.getMaturityDate(),product.getNotionalCurrency(),
                    product.getMaturityDate(), product, null, product.getStartDate(),product.getMaturityDate());
            line3.setFixed(Boolean.TRUE);
            line3.setPaymentAmount(product.getNotionalMultiplier().multiply(product.getProductForex().getStrike()));
            schedule.getScheduleLines().add(line3);
        }
        return schedule;
    }

    @Override
    public BigDecimal calculateAmount(ScheduleLine line) {
        //no need
        return BigDecimal.ZERO;
    }

}
