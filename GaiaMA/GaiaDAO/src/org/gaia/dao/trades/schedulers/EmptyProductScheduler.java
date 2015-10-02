
package org.gaia.dao.trades.schedulers;

import java.math.BigDecimal;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;

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

/**
 *
 * @author Benjamin Frerejean
 */


public class EmptyProductScheduler implements IProductScheduler{

    @Override
    public Schedule buildSchedule(Product product) {
        return new Schedule();
    }

    @Override
    public BigDecimal calculateAmount(ScheduleLine line) {
        return BigDecimal.ZERO;
    }

}
