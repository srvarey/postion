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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Benjamin Frerejean
 */
public class IsdaCurve implements Serializable {

    private ArrayList<Date> dates;
    private Date baseDate;
    private ArrayList<Double> Rates;
    private double basis;
    private String dayCountConv;
    private ArrayList<Double> discount;

    /**
     * array of discounts
     */

    /**
     * array of discounts
     *
     * @return Basis
     */
    public double getBasis() {
        return basis;
    }

    public void setBasis(double fBasis) {
        this.basis = fBasis;
    }

    public String getDayCountConv() {
        return dayCountConv;
    }

    public void setDayCountConv(String fDayCountConv) {
        this.dayCountConv = fDayCountConv;
    }

    public ArrayList<Double> getRates() {
        return Rates;
    }

    public void setRates(ArrayList<Double> Rates) {
        this.Rates = Rates;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date fBaseDate) {
        this.baseDate = fBaseDate;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Date> fDates) {
        this.dates = fDates;
    }

    public int getNumItems() {
        return dates.size();
    }

    public ArrayList<Double> getDiscount() {
        return discount;
    }

    public void setDiscount(ArrayList<Double> discount) {
        this.discount = discount;
    }

}
