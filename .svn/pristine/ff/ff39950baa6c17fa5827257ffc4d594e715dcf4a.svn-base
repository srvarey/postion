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
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.envers.Audited;

/**
 *
 * @author user
 */
@Audited
@Entity
@Table(name = "filter_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilterGroup.findAll", query = "SELECT f FROM FilterGroup f"),
    @NamedQuery(name = "FilterGroup.findByFilterGroupId", query = "SELECT f FROM FilterGroup f WHERE f.filterGroupId = :filterGroupId"),
    @NamedQuery(name = "FilterGroup.findByFilterGroupName", query = "SELECT f FROM FilterGroup f WHERE f.filterGroupName = :filterGroupName"),
    @NamedQuery(name = "FilterGroup.findByLinkedObjectClass", query = "SELECT f FROM FilterGroup f WHERE f.linkedObjectClass = :linkedObjectClass"),
    @NamedQuery(name = "FilterGroup.findByLinkedObjectId", query = "SELECT f FROM FilterGroup f WHERE f.linkedObjectId = :linkedObjectId")})
public class FilterGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "filter_group_id")
    private Integer filterGroupId;
    @Column(name = "filter_group_name")
    private String filterGroupName;
    @Column(name = "linked_object_class")
    private String linkedObjectClass;
    @Column(name = "linked_object_id")
    private Integer linkedObjectId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "filter_group_filter", joinColumns = {
        @JoinColumn(name = "filter_group_id", referencedColumnName = "filter_group_id")}, inverseJoinColumns = {
        @JoinColumn(name = "filter_id", referencedColumnName = "filter_id")})
    private Collection<Filter> filterCollection=new HashSet();

    public FilterGroup() {
    }

    public FilterGroup(Integer filterGroupId) {
        this.filterGroupId = filterGroupId;
    }

    public Integer getFilterGroupId() {
        return filterGroupId;
    }

    public void setFilterGroupId(Integer filterGroupId) {
        this.filterGroupId = filterGroupId;
    }

    public String getFilterGroupName() {
        return filterGroupName;
    }

    public void setFilterGroupName(String filterGroupName) {
        this.filterGroupName = filterGroupName;
    }

    public String getLinkedObjectClass() {
        return linkedObjectClass;
    }

    public void setLinkedObjectClass(String linkedObjectClass) {
        this.linkedObjectClass = linkedObjectClass;
    }

    public Integer getLinkedObjectId() {
        return linkedObjectId;
    }

    public void setLinkedObjectId(Integer linkedObjectId) {
        this.linkedObjectId = linkedObjectId;
    }

    @XmlTransient
    public Collection<Filter> getFilterCollection() {
        return filterCollection;
    }

    public void setFilterCollection(Collection<Filter> filterCollection) {
        this.filterCollection = filterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filterGroupId != null ? filterGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FilterGroup)) {
            return false;
        }
        FilterGroup other = (FilterGroup) object;
        if ((this.filterGroupId == null && other.filterGroupId != null) || (this.filterGroupId != null && !this.filterGroupId.equals(other.filterGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiaslaveserver.FilterGroup[ filterGroupId=" + filterGroupId + " ]";
    }

}
