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
package org.gaia.domain.referentials;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.trades.Product;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "currency")
@XmlRootElement
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByCode", query = "SELECT c FROM Currency c WHERE c.code = :code"),
    @NamedQuery(name = "Currency.findByName", query = "SELECT c FROM Currency c WHERE c.name = :name"),
    @NamedQuery(name = "Currency.findByRounding", query = "SELECT c FROM Currency c WHERE c.rounding = :rounding"),
    @NamedQuery(name = "Currency.findByRoundingType", query = "SELECT c FROM Currency c WHERE c.roundingType = :roundingType")})

public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 3)
    private String code;
    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "rounding", precision = 17, scale = 17)
    private Integer rounding;
    @Column(name = "rounding_type", length = 32)
    private String roundingType;
    @Column(name = "country", length = 128)
    private String country;
    @Column(name = "non_deliverable", nullable = false, columnDefinition ="boolean default false")
    private boolean nonDeliverable;
    @Column(name = "daycount", length = 10)
    private String daycount;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="holiday_code")
    private HolidayCalendar calendar;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @Fetch(FetchMode.JOIN)
    private Product currencyProduct;


    public HolidayCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(HolidayCalendar calendar) {
        this.calendar = calendar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getRounding() {
        return rounding;
    }

    public void setRounding(Integer rounding) {
        this.rounding = rounding;
    }

    public String getRoundingType() {
        return roundingType;
    }

    public void setRoundingType(String roundingType) {
        this.roundingType = roundingType;
    }

    public String getDaycount() {
        return daycount;
    }

    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }

    public Product getCurrencyProduct() {
        return currencyProduct;
    }

    public void setCurrencyProduct(Product currencyProduct) {
        this.currencyProduct = currencyProduct;
    }

    public boolean isNonDeliverable() {
        return nonDeliverable;
    }
    public void setNonDeliverable(boolean nonDeliverable) {
        this.nonDeliverable = nonDeliverable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Currency[ code=" + code + " ]";
    }

}
