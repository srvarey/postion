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

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "country")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryId", query = "SELECT c FROM Country c WHERE c.countryId = :countryId"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name"),
    @NamedQuery(name = "Country.findByCodeIso3a", query = "SELECT c FROM Country c WHERE c.codeIso3a = :codeIso3a"),
    @NamedQuery(name = "Country.findByCodeIso2a", query = "SELECT c FROM Country c WHERE c.codeIso2a = :codeIso2a"),
    @NamedQuery(name = "Country.findByStatus", query = "SELECT c FROM Country c WHERE c.status = :status")})



public class Country implements Serializable {
  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "country_id")
    private Integer countryId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "code_iso3a")
    private String codeIso3a;
    @Column(name = "code_iso2a")
    private String codeIso2a;
    @Column(name = "status")
    private String status;
    
    @OneToMany(mappedBy = "country")
    @XStreamOmitField 
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Currency> currencyCollection;

    
    public Country() {
    }

    public Country(Integer countryId) {
        this.countryId = countryId;
    }

    public Country(Integer countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    public Collection<Currency> getCurrencyCollection() {
        return currencyCollection;
    }

    public void setCurrencyCollection(Collection<Currency> currencyCollection) {
        this.currencyCollection = currencyCollection;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeIso3a() {
        return codeIso3a;
    }

    public void setCodeIso3a(String codeIso3a) {
        this.codeIso3a = codeIso3a;
    }

    public String getCodeIso2a() {
        return codeIso2a;
    }

    public void setCodeIso2a(String codeIso2a) {
        this.codeIso2a = codeIso2a;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.referentials.Country[ countryId=" + countryId + " ]";
    }

   

    
}
