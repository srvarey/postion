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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.reports.CustomColumn;
import org.hibernate.envers.Audited;

/**
 *
 * @author user
 */
@Audited
@Entity
@Table(name = "margin_clearer_rule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarginClearerRule.findAll", query = "SELECT m FROM MarginClearerRule m"),
    @NamedQuery(name = "MarginClearerRule.findByMarginClearerRuleId", query = "SELECT m FROM MarginClearerRule m WHERE m.marginClearerRuleId = :marginClearerRuleId")})

public class MarginClearerRule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "margin_clearer_rule_id")
    private Integer marginClearerRuleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")
    private LegalEntity legalEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "custom_column_name", referencedColumnName = "name")
    private CustomColumn customColumn;
    @Basic(optional = false)
    @Column(name = "margin_type")
    private String marginType;

    public MarginClearerRule() {
    }

    public MarginClearerRule(Integer marginClearerRuleId) {
        this.marginClearerRuleId = marginClearerRuleId;
    }

    public Integer getMarginClearerRuleId() {
        return marginClearerRuleId;
    }

    public void setMarginClearerRuleId(Integer marginClearerRuleId) {
        this.marginClearerRuleId = marginClearerRuleId;
    }

    public String getMarginType() {
        return marginType;
    }

    public void setMarginType(String _marginType) {
        this.marginType = _marginType;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public CustomColumn getCustomColumn() {
        return customColumn;
    }

    public void setCustomColumn(CustomColumn _customColumn) {
        this.customColumn = _customColumn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marginClearerRuleId != null ? marginClearerRuleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof MarginClearerRule)) {
            return false;
        }
        MarginClearerRule other = (MarginClearerRule) object;
        if ((this.marginClearerRuleId == null && other.marginClearerRuleId != null) || (this.marginClearerRuleId != null && !this.marginClearerRuleId.equals(other.marginClearerRuleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiaslaveserver.MarginClearerRule[ marginClearerRuleId=" + marginClearerRuleId + " ]";
    }

}
