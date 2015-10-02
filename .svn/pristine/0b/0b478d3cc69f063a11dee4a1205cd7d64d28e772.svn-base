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
package org.gaia.domain.legalEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "agreement")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Agreement.findAll", query = "SELECT a FROM Agreement a"),
@NamedQuery(name = "Agreement.findByAgreementId", query = "SELECT a FROM Agreement a WHERE a.agreementId = :agreementId")})


public class Agreement implements Serializable {

    public enum AgreementType {NETTING , COLLATERAL}

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "agreement_id")
    private Integer agreementId;
    @Column(name = "agreement_type")
    private String agreementType;
    @Column(name = "product_filter")
    private String productFilter;
    @Column(name = "status")
    private String status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agreement")
    private CollateralAgreement collateralAgreement;

    @ManyToOne
    @JoinColumn(name = "legal_entity_2", referencedColumnName = "legal_entity_id")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private LegalEntity legalEntity2;

    @ManyToOne
    @JoinColumn(name = "legal_entity_1", referencedColumnName = "legal_entity_id")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private LegalEntity legalEntity1;

    public Agreement() {
    }

    public Agreement(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getProductFilter() {
        return productFilter;
    }

    public void setProductFilter(String productFilter) {
        this.productFilter = productFilter;
    }

    public CollateralAgreement getCollateralAgreement() {
        return collateralAgreement;
    }

    public void setCollateralAgreement(CollateralAgreement collateralAgreement) {
        this.collateralAgreement = collateralAgreement;
    }

    public LegalEntity getLegalEntity2() {
        return legalEntity2;
    }

    public void setLegalEntity2(LegalEntity legalEntity2) {
        this.legalEntity2 = legalEntity2;
    }

    public LegalEntity getLegalEntity1() {
        return legalEntity1;
    }

    public void setLegalEntity1(LegalEntity legalEntity1) {
        this.legalEntity1 = legalEntity1;
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
        hash += (agreementId != null ? agreementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Agreement)) {
            return false;
        }
        Agreement other = (Agreement) object;
        if ((this.agreementId == null && other.agreementId != null) || (this.agreementId != null && !this.agreementId.equals(other.agreementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agreement Id=" + agreementId + " ]";
    }

}
