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
package org.gaia.domain.reports;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "custom_column")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "CustomColumn.findAll", query = "SELECT c FROM CustomColumn c"),
    @NamedQuery(name = "CustomColumn.findByName", query = "SELECT c FROM CustomColumn c WHERE c.name = :name"),
    @NamedQuery(name = "CustomColumn.findByType", query = "SELECT c FROM CustomColumn c WHERE c.type = :type"),
    @NamedQuery(name = "CustomColumn.findByCategory", query = "SELECT c FROM CustomColumn c WHERE c.category = :category")})

public class CustomColumn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "formula")
    private String formula;
    @Column(name = "class_name")
    private String className;
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "customColumn", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CustomColumnDetail> customColumnDetails;

    public CustomColumn() {
        customColumnDetails = new HashSet();
    }

    public CustomColumn(String name) {
        this.name = name;
        customColumnDetails = new HashSet();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        this.category = _category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<CustomColumnDetail> getCustomColumnDetails() {
        return customColumnDetails;
    }

    public void setCustomColumnDetails(Collection<CustomColumnDetail> customColumnDetails) {
        this.customColumnDetails = customColumnDetails;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String clazz) {
        this.className = clazz;
    }

    private String getObjectType() {
        return objectType;
    }

    public Class getObjectTypeClass() {
        try {
            return Class.forName(objectType);

        } catch (ClassNotFoundException e) {
        }
        return null;
    }

    public void setObjectTypeClass(Class objectType) {
        this.objectType = objectType.getName();
    }

    private void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomColumn)) {
            return false;
        }
        CustomColumn other = (CustomColumn) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "CustomColumn " + name;
        if (formula != null) {
            str = "=" + formula;
        }
        return str;
    }

}
