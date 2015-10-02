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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "domain_values")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DomainValue.findAll", query = "SELECT d FROM DomainValue d"),
    @NamedQuery(name = "DomainValue.findByName", query = "SELECT d FROM DomainValue d WHERE d.domainValuesPK.name = :name"),
    @NamedQuery(name = "DomainValue.findByValue", query = "SELECT d FROM DomainValue d WHERE d.domainValuesPK.value = :value"),
    @NamedQuery(name = "DomainValue.findByDescription", query = "SELECT d FROM DomainValue d WHERE d.description = :description")})
public class DomainValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DomainValuePK domainValuesPK;
    @Column(name = "description")
    private String description;
    @Column(name = "locked")
    private Boolean locked;

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public DomainValue() {
        this.domainValuesPK=new DomainValuePK();
    }

    public DomainValue(DomainValuePK domainValuesPK) {
        this.domainValuesPK = domainValuesPK;
    }

    public DomainValue(String name, String value) {
        this.domainValuesPK = new DomainValuePK(name, value);
    }

    public DomainValuePK getDomainValuesPK() {
        return domainValuesPK;
    }

    public void setDomainValuesPK(DomainValuePK domainValuesPK) {
        this.domainValuesPK = domainValuesPK;
    }

    public String getName() {
        return domainValuesPK.getName();
    }

    public void setName(String name) {
        domainValuesPK.setName(name);
    }

    public String getValue() {
        return domainValuesPK.getValue();
    }

    public void setValue(String value) {
        domainValuesPK.setValue(value);
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domainValuesPK != null ? domainValuesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof DomainValue)) {
            return false;
        }
        DomainValue other = (DomainValue) object;
        if ((this.domainValuesPK == null && other.domainValuesPK != null) || (this.domainValuesPK != null && !this.domainValuesPK.equals(other.domainValuesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.referentials.DomainValues[ domainValuesPK=" + domainValuesPK + " ]";
    }

}
