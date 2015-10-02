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
package org.gaia.domain.observables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "pricing_environment")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "PricingEnvironment.findAll", query = "SELECT p FROM PricingEnvironment p"),
    @NamedQuery(name = "PricingEnvironment.findByPricingEnvironmentId", query = "SELECT p FROM PricingEnvironment p WHERE p.pricingEnvironmentId = :pricingEnvironmentId"),
    @NamedQuery(name = "PricingEnvironment.findByName", query = "SELECT p FROM PricingEnvironment p WHERE p.name = :name")})


public class PricingEnvironment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pricing_environment_id")
    private Integer pricingEnvironmentId;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pricing_environment_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<PricingSettingItem> pricingSettingItemCollection;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pricing_environment_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<PricersSetting> pricersSettingCollection;

    public PricingEnvironment() {
    }

    public PricingEnvironment(Integer pricingEnvironmentId) {
        this.pricingEnvironmentId = pricingEnvironmentId;
    }

    public Integer getPricingEnvironmentId() {
        return pricingEnvironmentId;
    }

    public void setPricingEnvironmentId(Integer pricingEnvironmentId) {
        this.pricingEnvironmentId = pricingEnvironmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<PricingSettingItem> getPricingSettingItemCollection() {
        return pricingSettingItemCollection;
    }

    public void setPricingSettingItemCollection(Collection<PricingSettingItem> pricingSettingItemCollection) {
        this.pricingSettingItemCollection = pricingSettingItemCollection;
    }

    @XmlTransient
    public Collection<PricersSetting> getPricersSettingCollection() {
        return pricersSettingCollection;
    }

    public void setPricersSettingCollection(Collection<PricersSetting> pricersSettingCollection) {
        this.pricersSettingCollection = pricersSettingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricingEnvironmentId != null ? pricingEnvironmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PricingEnvironment)) {
            return false;
        }
        PricingEnvironment other = (PricingEnvironment) object;
        if ((this.pricingEnvironmentId == null && other.pricingEnvironmentId != null) || (this.pricingEnvironmentId != null && !this.pricingEnvironmentId.equals(other.pricingEnvironmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id=" + pricingEnvironmentId + "/"+name;
    }

}
