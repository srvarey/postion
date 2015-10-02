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
import org.hibernate.envers.Audited;

/**
 *
 * @author Jawhar Kamoun
 */
@Audited
@Entity
@Table(name = "bo_account_intermediary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BoAccountIntermediary.findAll", query = "SELECT b FROM BoAccountIntermediary b"),
    @NamedQuery(name = "BoAccountIntermediary.findByBoAccountIntermediaryId", query = "SELECT b FROM BoAccountIntermediary b WHERE b.boAccountIntermediaryId = :boAccountIntermediaryId"),
    @NamedQuery(name = "BoAccountIntermediary.findByIsdefault", query = "SELECT b FROM BoAccountIntermediary b WHERE b.isdefault = :isdefault")})
public class BoAccountIntermediary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bo_account_intermediary_id")
    private Integer boAccountIntermediaryId;
    @Column(name = "isdefault")
    private Boolean isdefault;
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")
    @ManyToOne
    private LegalEntity legalEntityId;
    @ManyToOne
    @JoinColumn(name = "account_id",insertable=false,updatable=false)
    private BoAccount account;

    public BoAccountIntermediary() {
    }

    public BoAccountIntermediary(Integer boAccountIntermediaryId) {
        this.boAccountIntermediaryId = boAccountIntermediaryId;
    }

    public Integer getBoAccountIntermediaryId() {
        return boAccountIntermediaryId;
    }

    public void setBoAccountIntermediaryId(Integer boAccountIntermediaryId) {
        this.boAccountIntermediaryId = boAccountIntermediaryId;
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    public LegalEntity getLegalEntity() {
        return legalEntityId;
    }

    public void setLegalEntity(LegalEntity legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public BoAccount getAccount() {
        return account;
    }

    public void setAccount(BoAccount account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boAccountIntermediaryId != null ? boAccountIntermediaryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof BoAccountIntermediary)) {
            return false;
        }
        BoAccountIntermediary other = (BoAccountIntermediary) object;
        if ((this.boAccountIntermediaryId == null && other.boAccountIntermediaryId != null) || (this.boAccountIntermediaryId != null && !this.boAccountIntermediaryId.equals(other.boAccountIntermediaryId))) {
            return false;
        }
        if ((this.legalEntityId == null && other.legalEntityId != null) || (this.legalEntityId != null && !this.legalEntityId.equals(other.legalEntityId))) {
            return false;
        }
        if ((this.account == null && other.account != null) || (this.account != null && !this.account.equals(other.account))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return legalEntityId.getShortName();
    }

}
