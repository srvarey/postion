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
package org.gaia.domain.utils;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mapping")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapping.findAll", query = "SELECT m FROM Mapping m"),
    @NamedQuery(name = "Mapping.findByMappingId", query = "SELECT m FROM Mapping m WHERE m.mappingId = :mappingId"),
    @NamedQuery(name = "Mapping.findByMappingName", query = "SELECT m FROM Mapping m WHERE m.mappingName = :mappingName"),
    @NamedQuery(name = "Mapping.findByKey1", query = "SELECT m FROM Mapping m WHERE m.key1 = :key1"),
    @NamedQuery(name = "Mapping.findByKey2", query = "SELECT m FROM Mapping m WHERE m.key2 = :key2"),
    @NamedQuery(name = "Mapping.findByValue", query = "SELECT m FROM Mapping m WHERE m.value = :value")})


public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mapping_id")
    private Integer mappingId;
    @Column(name = "mapping_name")
    private String mappingName;
    @Column(name = "key1")
    private String key1;
    @Column(name = "key2")
    private String key2;
    @Column(name = "value")
    private String value;

    public Mapping() {
    }

    public Mapping(String key, String value) {
        this.key1 = key;
        this.value = value;
    }

    public Mapping(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mappingId != null ? mappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Mapping)) {
            return false;
        }
        Mapping other = (Mapping) object;
        if ((this.mappingId == null && other.mappingId != null) || (this.mappingId != null && !this.mappingId.equals(other.mappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mapping " + mappingName + StringUtils.SPACE + key1 + "=>" + value;
    }
}
