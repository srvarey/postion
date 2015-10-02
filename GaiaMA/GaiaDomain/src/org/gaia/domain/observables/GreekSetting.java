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
package org.gaia.domain.observables;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "greek_setting")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "GreekSetting.findAll", query = "SELECT g FROM GreekSetting g")})

public class GreekSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pricer_measure")
    private String pricerMeasure;
    @Column(name = "moving_pricer_measure")
    private String movingPricerMeasure;
    @Column(name = "shifted")
    private String shifted;
    @Column(name = "shift")
    private BigDecimal shift;
    @Column(name = "shift_dimension")
    private Short shiftDimension;
    @Column(name = "is_absolute")
    private Boolean isAbsolute;
    @Column(name = "is_in_amount")
    private Boolean isInAmount;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "stress_id", referencedColumnName = "stress_id")
    private Stress stress;

    public GreekSetting() {
    }

    public GreekSetting(String pricerMeasure) {
        this.pricerMeasure=pricerMeasure;
    }

    public String getPricerMeasure() {
        return pricerMeasure;
    }

    public void setPricerMeasure(String pricerMeasure) {
        this.pricerMeasure = pricerMeasure;
    }

    public String getMovingPricerMeasure() {
        return movingPricerMeasure;
    }

    public void setMovingPricerMeasure(String movingPricerMeasure) {
        this.movingPricerMeasure = movingPricerMeasure;
    }

    public String getShifted() {
        return shifted;
    }

    public void setShifted(String shifted) {
        this.shifted = shifted;
    }

    public Boolean isAbsolute() {
        return isAbsolute;
    }

    public void setIsAbsolute(Boolean isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    public Boolean isBusDaysTimeShift() {
        return isAbsolute;
    }

    public void setIsBusDaysTimeShift(Boolean isBusDaysTimeShift) {
        this.isAbsolute = isBusDaysTimeShift;
    }

    public Boolean isInAmount() {
        return isInAmount;
    }

    public void setIsInAmount(Boolean isInAmount) {
        this.isInAmount = isInAmount;
    }

    public Short getShiftDimension() {
        return shiftDimension;
    }

    public void setShiftDimension(Short shiftDimension) {
        this.shiftDimension = shiftDimension;
    }

    public Stress getStress() {
        return stress;
    }

    public void setStress(Stress stress) {
        this.stress = stress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricerMeasure != null ? pricerMeasure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GreekSetting)) {
            return false;
        }
        GreekSetting other = (GreekSetting) object;
        if ((this.pricerMeasure == null && other.pricerMeasure != null) || (this.pricerMeasure != null && !this.pricerMeasure.equals(other.pricerMeasure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "greek " + pricerMeasure ;
    }

}
