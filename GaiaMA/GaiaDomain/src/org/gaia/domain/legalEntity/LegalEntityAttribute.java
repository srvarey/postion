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
import javax.persistence.FetchType;
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
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "legal_entity_attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LegalEntityAttribute.findAll", query = "SELECT t FROM LegalEntityAttribute t"),
    @NamedQuery(name = "LegalEntityAttribute.findByLegalEntityId", query = "SELECT t FROM LegalEntityAttribute t WHERE t.legalEntityId = :legalEntityId"),
    @NamedQuery(name = "LegalEntityAttribute.findByAttributeName", query = "SELECT t FROM LegalEntityAttribute t WHERE t.attributeName = :attributeName"),
    @NamedQuery(name = "LegalEntityAttribute.findByAttributeValue", query = "SELECT t FROM LegalEntityAttribute t WHERE t.attributeValue = :attributeValue")})

public class LegalEntityAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "legal_entity_id")
    private Integer legalEntityId;
    @Column(name = "attribute_name")
    private String attributeName;
    @Column(name = "attribute_value")
    private String attributeValue;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id", insertable=false, updatable=false)
    private LegalEntity legalEntity;

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
        if (legalEntity!=null){
            this.legalEntityId=legalEntity.getLegalEntityId();
        }
    }

    public LegalEntityAttribute() {
    }

    public LegalEntityAttribute(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (legalEntityId != null ? legalEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof LegalEntityAttribute)) {
            return false;
        }
        LegalEntityAttribute other = (LegalEntityAttribute) object;
        if ((this.legalEntityId == null && other.legalEntityId != null) || (this.legalEntityId != null && !this.legalEntityId.equals(other.legalEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.legalEntitys.LegalEntityAttribute[ legalEntityId=" + legalEntityId + " ]";
    }

}
