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
package org.gaia.domain.reports;

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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author user
 */
@Entity
@Table(name = "position_configuration_item")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionConfigurationItem.findAll", query = "SELECT p FROM PositionConfigurationItem p"),
    @NamedQuery(name = "PositionConfigurationItem.findByPositionConfigurationItemId", query = "SELECT p FROM PositionConfigurationItem p WHERE p.positionConfigurationItemId = :positionConfigurationItemId"),
    @NamedQuery(name = "PositionConfigurationItem.findByDbField", query = "SELECT p FROM PositionConfigurationItem p WHERE p.dbField = :dbField"),
    @NamedQuery(name = "PositionConfigurationItem.findByGetter", query = "SELECT p FROM PositionConfigurationItem p WHERE p.getter = :getter")})


public class PositionConfigurationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_configuration_item_id")
    private Integer positionConfigurationItemId;
    @Column(name = "db_field")
    private String dbField;
    @Column(name = "getter")
    private String getter;
    
    @JoinColumn(name = "position_configuration_id", referencedColumnName = "position_configuration_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PositionConfiguration positionConfiguration;

    public PositionConfigurationItem() {
    }

    public PositionConfigurationItem(String dbField, String getter) {
        this.dbField = dbField;
        this.getter = getter;
    }


    public int getPositionConfigurationItemId() {
        return positionConfigurationItemId;
    }

    public void setPositionConfigurationItemId(int positionConfigurationItemId) {
        this.positionConfigurationItemId = positionConfigurationItemId;
    }


    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public PositionConfiguration getPositionConfiguration() {
        return positionConfiguration;
    }

    public void setPositionConfiguration(PositionConfiguration positionConfiguration) {
        this.positionConfiguration = positionConfiguration;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionConfigurationItemId != null ? positionConfigurationItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof PositionConfigurationItem)) {
            return false;
        }
        PositionConfigurationItem other = (PositionConfigurationItem) object;
        if ((this.positionConfigurationItemId == null && other.positionConfigurationItemId != null) || (this.positionConfigurationItemId != null && !this.positionConfigurationItemId.equals(other.positionConfigurationItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiajademasterdispatcherserver.PositionConfigurationItem[ positionConfigurationItemId=" + positionConfigurationItemId + " ]";
    }

}
