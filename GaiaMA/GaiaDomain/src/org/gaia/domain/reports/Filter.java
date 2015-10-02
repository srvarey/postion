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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "filter")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "Filter.findAll", query = "SELECT t FROM Filter t"),
    @NamedQuery(name = "Filter.findByTradeFilterId", query = "SELECT t FROM Filter t WHERE t.filterId = :filterId"),
    @NamedQuery(name = "Filter.findByName", query = "SELECT t FROM Filter t WHERE t.name = :name")})

public class Filter implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    public static String FILTER = "filters";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "filter_id")
    private Integer filterId;
    @Column(name = "name")
    private String name;
    @Column(name = "object_type")
    private String objectType;
    @OneToMany(mappedBy = "filter")
    @LazyCollection(LazyCollectionOption.TRUE)
    @NotAudited
    private Collection<ReportTemplate> templates;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "filter_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AuditMappedBy(mappedBy = "filter")
    private Collection<FilterCriteria> filterCriteria;
    @ManyToMany(mappedBy = "filterCollection", cascade = CascadeType.ALL)
    private Collection<FilterGroup> filterGroups;
    @OneToMany(mappedBy = "filter")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<CustomColumnDetail> customColumnDetails;
    private static final Logger logger = Logger.getLogger(Filter.class);


    public Filter() {
        filterCriteria = new HashSet();
    }

    public Filter(Integer filterId) {
        this.filterId = filterId;
    }

    public Integer getFilterId(String filterName) {
        return filterId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getObjectType() {
        return objectType;
    }

    public Class getObjectTypeClass() {
        try {
            return Class.forName(objectType);
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }

    public void setObjectTypeClass(Class objectType) {
        this.objectType = objectType.getName();
    }

    private void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @XmlTransient
    public Collection<CustomColumnDetail> getCustomColumnDetails() {
        return customColumnDetails;
    }

    public void setCustomColumnDetails(Collection<CustomColumnDetail> customColumnDetails) {
        this.customColumnDetails = customColumnDetails;
    }

    @XmlTransient
    public Collection<FilterCriteria> getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(Collection<FilterCriteria> filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public Collection<FilterGroup> getFilterGroups() {
        return filterGroups;
    }

    public void setFilterGroups(Collection<FilterGroup> filterGroups) {
        this.filterGroups = filterGroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filterId != null ? filterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Filter)) {
            return false;
        }
        Filter other = (Filter) object;
        if ((this.filterId == null && other.filterId != null) || (this.filterId != null && !this.filterId.equals(other.filterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filter " + name + "/" + filterId;
    }

    @XmlTransient
    public Collection<ReportTemplate> getTemplates() {
        return templates;
    }

    public void setTemplate(Collection<ReportTemplate> templates) {
        this.templates = templates;
    }

    @Override
    public Object clone() {
        Filter filter = null;
        try {
            filter = (Filter) super.clone();
            filter.setFilterId(null);
        } catch (CloneNotSupportedException cnse) {
            logger.error(cnse.getMessage());
        }
        filter.setFilterCriteria(new ArrayList());
        for (FilterCriteria fc : this.getFilterCriteria()) {
            filter.getFilterCriteria().add((FilterCriteria) fc.clone());
        }
        return filter;
    }

//    public void setObjectType(Class objectType) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
