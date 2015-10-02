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
public class MeasureValue  implements IMeasureValue,Serializable {

    private Integer priceableId;
    private IPricerMeasure measure;
    private BigDecimal measureValue;
    private String currency;
    private String qualifier;
    private Date valDate;
    private String name;

    public MeasureValue(IPricerMeasure measure, BigDecimal measureValue, String currency, Date dateValo, Integer priceableId) {

        this.measure = measure;
        this.measureValue = measureValue;
        this.currency = currency;
        this.priceableId = priceableId;
        this.valDate=dateValo;
        this.name=measure.getName();
    }

    public MeasureValue(IPricerMeasure measure, Date dateValo) {

        this.measure = measure;
        this.valDate=dateValo;
        this.name=measure.getName();
    }

    public MeasureValue() {
        super();
    }

    @Override
    public Integer getPriceableId() {
        return priceableId;
    }

    @Override
    public void setPriceableId(Integer priceableId) {
        this.priceableId = priceableId;
    }

    @Override
    public IPricerMeasure getMeasure() {
        return measure;
    }

    @Override
    public void setMeasure(IPricerMeasure measure) {
        this.measure = measure;
    }

    @Override
    public BigDecimal getMeasureValue() {
        return measureValue;
    }

    @Override
    public void setMeasureValue(BigDecimal measureValue) {
        this.measureValue = measureValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    @Override
    public String toString(){
        return name+"="+measureValue.toString();
    }


    @Override
    public void multiplyValueBy(BigDecimal val){
        measureValue=measureValue.multiply(val);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Date getValDate() {
        return valDate;
    }

    @Override
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public MeasureValue clone() {
        MeasureValue clone = null;
        try {
            clone = (MeasureValue)super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return clone;
    }
}
