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
package org.gaia.dao.jade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
/**
 *
 * @author Benjamin Frerejean
 */
public class PricerResult implements Serializable{

    private int id;
    private Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate = new HashMap();
    private Object lineData;


    /**
     * Get the value of id
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     * @param id new value of id
     */

    public void setId(int id) {
        this.id = id;
    }

    public Object getLineData() {
        return lineData;
    }

    public void setLineData(Object lineData) {
        this.lineData = lineData;
    }

    public Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> getMapWithDate() {
        return mapWithDate;
    }

    public void setMapWithDate(Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate) {
        this.mapWithDate = mapWithDate;
    }

    @Override
    public String toString(){
        return "Line "+id;
    }
}
