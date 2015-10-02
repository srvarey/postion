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
package org.gaia.domain.legalEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "credit_entity")
@XmlRootElement
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "CreditEntity.findAll", query = "SELECT c FROM CreditEntity c"),
    @NamedQuery(name = "CreditEntity.findByCreditEntityId", query = "SELECT c FROM CreditEntity c WHERE c.creditEntityId = :creditEntityId"),
    @NamedQuery(name = "CreditEntity.findByDefaultCoupon", query = "SELECT c FROM CreditEntity c WHERE c.defaultCoupon = :defaultCoupon"),
    @NamedQuery(name = "CreditEntity.findByDefaultContractType", query = "SELECT c FROM CreditEntity c WHERE c.defaultContractType = :defaultContractType")})
public class CreditEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "credit_entity_id")
    private Integer creditEntityId;
    @Column(name = "default_coupon")
    private BigDecimal defaultCoupon;
    @Column(name = "default_contract_type")
    private String defaultContractType;

    @ManyToOne
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity legalEntity;

    public CreditEntity() {
    }

    public CreditEntity(Integer creditEntityId) {
        this.creditEntityId = creditEntityId;
    }

    public Integer getCreditEntityId() {
        return creditEntityId;
    }

    public void setCreditEntityId(Integer creditEntityId) {
        this.creditEntityId = creditEntityId;
    }

    public BigDecimal getDefaultCoupon() {
        return defaultCoupon;
    }

    public void setDefaultCoupon(BigDecimal defaultCoupon) {
        this.defaultCoupon = defaultCoupon;
    }

    public String getDefaultContractType() {
        return defaultContractType;
    }

    public void setDefaultContractType(String defaultContractType) {
        this.defaultContractType = defaultContractType;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditEntityId != null ? creditEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditEntity)) {
            return false;
        }
        CreditEntity other = (CreditEntity) object;
        if ((this.creditEntityId == null && other.creditEntityId != null) || (this.creditEntityId != null && !this.creditEntityId.equals(other.creditEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiajademasterdispatcherserver.CreditEntity[ creditEntityId=" + creditEntityId + " ]";
    }

}
