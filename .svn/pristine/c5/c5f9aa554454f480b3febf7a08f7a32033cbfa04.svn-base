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
@Table(name = "custom_column_detail")
@XmlRootElement
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "CustomColumnDetail.findAll", query = "SELECT c FROM CustomColumnDetail c"),
    @NamedQuery(name = "CustomColumnDetail.findByCustomColumnDetailId", query = "SELECT c FROM CustomColumnDetail c WHERE c.customColumnDetailId = :customColumnDetailId"),
    @NamedQuery(name = "CustomColumnDetail.findByLitteralValue", query = "SELECT c FROM CustomColumnDetail c WHERE c.litteralValue = :litteralValue"),
    @NamedQuery(name = "CustomColumnDetail.findByGetter", query = "SELECT c FROM CustomColumnDetail c WHERE c.getter = :getter")})


public class CustomColumnDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "custom_column_detail_id")
    private Integer customColumnDetailId;
    @JoinColumn(name = "filter_id", referencedColumnName = "filter_id")
    @ManyToOne
    private Filter filter;
    @Column(name = "litteral_value")
    private String litteralValue;
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "getter")
    private String getter;
    @Column(name = "param")
    private String param;
    @Column(name = "column_type")
    private String columnType;
    @Column(name = "is_first_date")
    private Boolean isFirstDate=false;
    @Column(name = "is_evol")
    private Boolean isEvol=false;
    @ManyToOne
    @JoinColumn(name = "custom_column_name", referencedColumnName = "name")
    private CustomColumn customColumn;

    public CustomColumnDetail() {
    }

    public CustomColumnDetail(Integer customColumnDetailId) {
        this.customColumnDetailId = customColumnDetailId;
    }

    public Integer getCustomColumnDetailId() {
        return customColumnDetailId;
    }

    public void setCustomColumnDetailId(Integer customColumnDetailId) {
        this.customColumnDetailId = customColumnDetailId;
    }

     public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter _filter) {
        this.filter = _filter;
    }

    public String getLitteralValue() {
        return litteralValue;
    }

    public void setLitteralValue(String litteralValue) {
        this.litteralValue = litteralValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
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

    public String getSuffix(){
        return isFirstDate ? ReportTemplate.FIRSTDATE_SUFFIX : isEvol ? ReportTemplate.EVOLUTION_SUFFIX : "";
    }

    public void setSuffix(String suffix){
        isFirstDate=false;
        isEvol=false;
        if (suffix!=null && !suffix.isEmpty()){
            switch (suffix) {
                case ReportTemplate.FIRSTDATE_SUFFIX:
                    isFirstDate=true;
                    break;
                case ReportTemplate.EVOLUTION_SUFFIX:
                    isEvol=true;
                    break;
            }
        }
    }

    public CustomColumn getCustomColumn() {
        return customColumn;
    }

    public void setCustomColumn(CustomColumn customColumn) {
        this.customColumn = customColumn;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Boolean getIsFirstDate() {
        return isFirstDate;
    }

    public void setIsFirstDate(Boolean isFirstDate) {
        this.isFirstDate = isFirstDate;
    }

    public Boolean getIsEvol() {
        return isEvol;
    }

    public void setIsEvol(Boolean isEvol) {
        this.isEvol = isEvol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customColumnDetailId != null ? customColumnDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomColumnDetail)) {
            return false;
        }
        CustomColumnDetail other = (CustomColumnDetail) object;
        if ((this.customColumnDetailId == null && other.customColumnDetailId != null) || (this.customColumnDetailId != null && !this.customColumnDetailId.equals(other.customColumnDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "column " + customColumnDetailId + StringUtils.SPACE +columnName ;
    }

}
