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
package org.gaia.domain.trades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "product_reference_type")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductReferenceType.findAll", query = "SELECT p FROM ProductReferenceType p"),
    @NamedQuery(name = "ProductReferenceType.findByReferenceType", query = "SELECT p FROM ProductReferenceType p WHERE p.referenceType = :referenceType"),
    @NamedQuery(name = "ProductReferenceType.findByType", query = "SELECT p FROM ProductReferenceType p WHERE p.type = :type")})


public class ProductReferenceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reference_type")
    private String referenceType;
    @Column(name = "type")
    private String type;
    @Column(name = "is_otc")
    private Boolean isOtc=false;
    
    public Boolean getIsOtc() {
        return isOtc;
    }

    public void setIsOtc(Boolean isOtc) {
        this.isOtc = isOtc;
    }

    public ProductReferenceType() {
    }

    public ProductReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referenceType != null ? referenceType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof ProductReferenceType)) {
            return false;
        }
        ProductReferenceType other = (ProductReferenceType) object;
        if ((this.referenceType == null && other.referenceType != null) || (this.referenceType != null && !this.referenceType.equals(other.referenceType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductReferenceType[ referenceType=" + referenceType + " ]";
    }

    
}
