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
 */
package org.gaia.dao.pricing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Benjamin Frerejean
 */
public interface IMeasureValue extends Cloneable,Serializable{

    public Integer getPriceableId();

    public void setPriceableId(Integer tradeId);

    public Date getValDate();

    public void setValDate(Date dateValo);

    public IPricerMeasure getMeasure();

    public void setMeasure(IPricerMeasure measure);

    public void multiplyValueBy(BigDecimal val);

    public Object clone();

    public String getName();

    public void setName(String name);

    public BigDecimal getMeasureValue();

    public void setMeasureValue(BigDecimal val);

    public String getQualifier();
}
