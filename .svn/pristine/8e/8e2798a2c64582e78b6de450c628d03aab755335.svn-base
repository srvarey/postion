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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "filter_criteria")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilterCriteria.findAll", query = "SELECT t FROM FilterCriteria t"),
    @NamedQuery(name = "FilterCriteria.findByFilterCriteriaId", query = "SELECT t FROM FilterCriteria t WHERE t.filterCriteriaId = :filterCriteriaId")})
public class FilterCriteria implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "filter_criteria_id")
    private Integer filterCriteriaId;
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "value")
    private String value;
    @Column(name = "value2")
    private String value2;
    @Column(name = "getter")
    private String getter;
    @Column(name = "param")
    private String param;
    @Column(name = "return_type")
    private String returnType;
    @Column(name = "column_type")
    private String columnType;
    @Column(name = "not_in")
    private Boolean notIn = false;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "filter_id", referencedColumnName = "filter_id")
    private Filter filter;
    @Column(name = "post_treatment")
    private Boolean postTreatment=false;

    public FilterCriteria() {
    }

    public FilterCriteria(Integer filterCriteria) {
        this.filterCriteriaId = filterCriteria;
    }

    public FilterCriteria(String columnName, String value, String value2, String getter, String returnType, String columnType) {
        this.columnName = columnName;
        this.value = value;
        this.value2 = value2;
        this.getter = getter;
        this.returnType = returnType;
        this.columnType = columnType;
    }

    public Integer getFilterCriteriaId() {
        return filterCriteriaId;
    }

    public void setFilterCriteriaId(Integer filterCriteriaId) {
        this.filterCriteriaId = filterCriteriaId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getNotIn() {
        return notIn;
    }

    public void setNotIn(Boolean notIn) {
        this.notIn = notIn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getColumnType() {
        return columnType;
    }

    public Boolean isPostTreatment() {
        return postTreatment;
    }

    public void setPostTreatment(Boolean postTreatment) {
        this.postTreatment = postTreatment;
    }
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filterCriteriaId != null ? filterCriteriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FilterCriteria)) {
            return false;
        }
        FilterCriteria other = (FilterCriteria) object;
        if ((this.filterCriteriaId == null && other.filterCriteriaId != null) || (this.filterCriteriaId != null && !this.filterCriteriaId.equals(other.filterCriteriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(this.columnName);
        if (value != null) {
            if (notIn){
                res.append(" != ").append(this.value);
            } else {
                res.append(" = ").append(this.value);
            }
        } else if (value2 != null) {
            res.append(" between ").append(this.value).append(" and ").append(this.value2);
        }

        return res.toString();
    }

    @Override
    public FilterCriteria clone() {
        FilterCriteria o = null;
        try {
            o = (FilterCriteria)super.clone();
            o.setFilterCriteriaId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }
}
