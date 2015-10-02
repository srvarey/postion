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
package org.gaia.dao.trades.events;

import java.util.Date;
import java.util.List;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductEventDetail;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;

/**
 *
 * @author Benjamin Frerejean
 */


public interface IEventApply {

     public List<ProductEventDetail> applyOnProduct(Product product, ProductEvent productEvent);

     public List<Integer> applyOnTrade(Trade trade, ProductEvent productEvent);

     public void applyOnFlows(Flow flow);

     public void applyOnScheduleLines(List<ScheduleLine> lines);

     public String getEventType();

     public Date getEffectiveDate();

     public String getEventDescription();
}
