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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
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
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "legal_entity_role")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "LegalEntityRole.findAll", query = "SELECT t FROM LegalEntityRole t")})
@XStreamAlias("Role")
public class LegalEntityRole implements Serializable {

    private static final long serialVersionUID = 1L;
//    public static final String COUNTERPARTY_ROLE = "Counterparty";
//    public static final String CCP_ROLE = "CCP";
//    public static final String INTERNAL_COUNTERPARTY_ROLE = "InternalCounterparty";
//    public static final String ISSUER_ROLE = "Issuer";
//    public static final String MARKET_ROLE = "Market";
//    public static final String LEGAL_ENTITY_ROLE = "Legal Entity";
//    public static final String CLEARING_MEMBER_ROLE = "Clearing Member";
//    public static final String BROKER_ROLE = "Broker";
//    public static final String TRANSFEROR = "Transferor";
//    public static final String TRANSFEREE = "Transferee";

    public enum LegalEntityRoleEnum {

        COUNTERPARTY_ROLE("Counterparty"),
        CCP_ROLE("CCP"),
        INTERNAL_COUNTERPARTY_ROLE("InternalCounterparty"),
        ISSUER_ROLE("Issuer"),
        MARKET_ROLE("Market"),
        LEGAL_ENTITY_ROLE("Legal Entity"),
        CLEARING_MEMBER_ROLE("Clearing Member"),
        BROKER_ROLE("Broker"),
        FUND_ROLE("Fund"),
        TRANSFEROR("Transferor"),
        TRANSFEREE("Transferee");
        private static final long serialVersionUID = 1L;
        public String name;

        LegalEntityRoleEnum(String name) {
            this.name = name;
        }
    };
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "legal_entity_role_id", nullable = false)
    private Integer legalEntityRoleId;
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @XStreamOmitField
    private LegalEntity legalEntity;
    @Column(name = "role")
    private String role;

    public LegalEntityRole() {
    }

    public LegalEntityRole(LegalEntity legalEntity, String role) {
        this.legalEntity = legalEntity;
        this.role = role;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public Integer getLegalEntityRoleId() {
        return legalEntityRoleId;
    }

    public void setLegalEntityRoleId(Integer legalEntityRoleId) {
        this.legalEntityRoleId = legalEntityRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (legalEntityRoleId != null ? legalEntityRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof LegalEntityRole)) {
            return false;
        }
        LegalEntityRole other = (LegalEntityRole) object;
        if ((this.legalEntityRoleId == null && other.legalEntityRoleId != null) || (this.legalEntityRoleId != null && !this.legalEntityRoleId.equals(other.legalEntityRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.legalEntity.LegalEntityRole[ legalEntityRolePK=" + legalEntityRoleId + " ]" + role + StringUtils.SPACE + legalEntity == null ? "" : legalEntity.getShortName();
    }
}
