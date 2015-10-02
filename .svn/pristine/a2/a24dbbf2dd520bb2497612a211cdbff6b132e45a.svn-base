
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
package org.gaia.domain.referentials;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "sector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT p FROM Sector p"),
    @NamedQuery(name = "Sector.findByProductSectorId", query = "SELECT p FROM Sector p WHERE p.sectorId = :sectorId"),
    @NamedQuery(name = "Sector.findByClassificationName", query = "SELECT p FROM Sector p WHERE p.classificationName = :classificationName"),
    @NamedQuery(name = "Sector.findByCode", query = "SELECT p FROM Sector p WHERE p.code = :code"),
    @NamedQuery(name = "Sector.findBySectorName", query = "SELECT p FROM Sector p WHERE p.sectorName = :sectorName")})
public class Sector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sector_id")
    private Integer sectorId;
    @Column(name = "classification_name")
    private String classificationName;
    @Column(name = "code")
    private String code;
    @Column(name = "sector_name")
    private String sectorName;

    @ManyToOne(fetch = FetchType.EAGER)
    @XStreamOmitField
    @JoinColumn(name = "parent_id", referencedColumnName = "sector_id")
    @NotAccessibleField(level = 1)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    private Sector parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @XStreamOmitField

    private Set<Sector> children = new HashSet<>();

    public Sector() {
    }

    public Sector(Integer productSectorId) {
        this.sectorId = productSectorId;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public Sector getParent() {
        return parent;
    }

    public void setParent(Sector parent) {
        this.parent = parent;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sectorId != null ? sectorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.sectorId == null && other.sectorId != null) || (this.sectorId != null && !this.sectorId.equals(other.sectorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id=" + sectorId + " code "+code+StringUtils.SPACE+sectorName+StringUtils.SPACE+classificationName;
    }

}
