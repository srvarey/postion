/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "template_column_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemplateColumnItem.findAll", query = "SELECT t FROM TemplateColumnItem t"),
    @NamedQuery(name = "TemplateColumnItem.findByColumnItemId", query = "SELECT t FROM TemplateColumnItem t WHERE t.columnItemId = :columnItemId"),
    @NamedQuery(name = "TemplateColumnItem.findByName", query = "SELECT t FROM TemplateColumnItem t WHERE t.name = :name"),
    @NamedQuery(name = "TemplateColumnItem.findByGetter", query = "SELECT t FROM TemplateColumnItem t WHERE t.getter = :getter"),
    @NamedQuery(name = "TemplateColumnItem.findByParam", query = "SELECT t FROM TemplateColumnItem t WHERE t.param = :param"),
    @NamedQuery(name = "TemplateColumnItem.findByColumnIndex", query = "SELECT t FROM TemplateColumnItem t WHERE t.columnIndex = :columnIndex"),
    @NamedQuery(name = "TemplateColumnItem.findByDisplayName", query = "SELECT t FROM TemplateColumnItem t WHERE t.displayName = :displayName"),
    @NamedQuery(name = "TemplateColumnItem.findByAggregation", query = "SELECT t FROM TemplateColumnItem t WHERE t.aggregation = :aggregation"),
    @NamedQuery(name = "TemplateColumnItem.findByReturnType", query = "SELECT t FROM TemplateColumnItem t WHERE t.returnType = :returnType"),
    @NamedQuery(name = "TemplateColumnItem.findByColumnType", query = "SELECT t FROM TemplateColumnItem t WHERE t.columnType = :columnType"),
    @NamedQuery(name = "TemplateColumnItem.findByIsConversion", query = "SELECT t FROM TemplateColumnItem t WHERE t.isConversion = :isConversion"),
    @NamedQuery(name = "TemplateColumnItem.findByIsAggregated", query = "SELECT t FROM TemplateColumnItem t WHERE t.isAggregated = :isAggregated")})

public class TemplateColumnItem implements Serializable, Cloneable {

    public static final String COLUMN_STANDARD = "Standard";
    public static final String COLUMN_MEASURE = "Measure";
    public static final String COLUMN_CUSTOM = "Custom";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "template_column_item_id")
    private Integer columnItemId;
    @Column(name = "name")
    private String name;
    @Column(name = "getter")
    private String getter;
    @Column(name = "param")
    private String param;
    @Column(name = "column_index")
    private Integer columnIndex;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "aggregation")
    private String aggregation;
    @Column(name = "return_type")
    private String returnType;
    @Column(name = "column_type")
    private String columnType;
    @Column(name = "is_conversion")
    private Boolean isConversion = false;
    @Column(name = "is_aggregated")
    private Boolean isAggregated = false;
    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "template_id")
    private ReportTemplate template;
    @Column(name = "is_first_date")
    private Boolean isFirstDate = false;
    @Column(name = "is_evol")
    private Boolean isEvol = false;
    @Column(name = "is_conversion_first")
    private Boolean isConversionFirst = true;
    @Column(name = "is_exposure")
    private Boolean isExposure = false;
    @Column(name = "is_in_chart")
    private Boolean isInChart = false;

    public Boolean isInChart() {
        return isInChart;
    }

    public void setIsInChart(Boolean isInChart) {
        this.isInChart = isInChart;
    }

    @Transient
    public boolean isToBeRemoved = false;

    public TemplateColumnItem() {
    }

    public TemplateColumnItem(Integer columnItemId) {
        this.columnItemId = columnItemId;
    }

    public Integer getColumnItemId() {
        return columnItemId;
    }

    public void setColumnItemId(Integer columnItemId) {
        this.columnItemId = columnItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
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

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Boolean isConversion() {
        return isConversion;
    }

    public void setIsConversion(Boolean isConversion) {
        this.isConversion = isConversion;
    }

    public Boolean isExposure() {
        return isExposure;
    }

    public void setIsExposure(Boolean isExposure) {
        this.isExposure = isExposure;
    }

    public String getSuffix() {
        return isFirstDate ? ReportTemplate.FIRSTDATE_SUFFIX : isEvol ? ReportTemplate.EVOLUTION_SUFFIX : "";
    }

    public void setSuffix(String suffix) {
        isFirstDate = false;
        isEvol = false;
        if (suffix != null && !suffix.isEmpty()) {
            switch (suffix) {
                case ReportTemplate.FIRSTDATE_SUFFIX:
                    isFirstDate = true;
                    break;
                case ReportTemplate.EVOLUTION_SUFFIX:
                    isEvol = true;
                    break;
            }
        }
    }

    public Boolean isAggregated() {
        return isAggregated;
    }

    public void setIsAggregated(Boolean isAggregated) {
        this.isAggregated = isAggregated;
    }

    public ReportTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ReportTemplate template) {
        this.template = template;
    }

    public boolean isFirstDate() {
        return isFirstDate;
    }

    public void setIsFirstDate(boolean isFirstDate) {
        this.isFirstDate = isFirstDate;
    }

    public boolean isEvol() {
        return isEvol;
    }

    public void setIsEvol(boolean isEvol) {
        this.isEvol = isEvol;
    }

    public Boolean isConversionFirst() {
        return isConversionFirst;
    }

    public void setIsConversionFirst(Boolean isConversionFirst) {
        this.isConversionFirst = isConversionFirst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (columnItemId != null ? columnItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TemplateColumnItem)) {
            return false;
        }
        TemplateColumnItem other = (TemplateColumnItem) object;
        if ((this.columnItemId == null && other.columnItemId != null) || (this.columnItemId != null && !this.columnItemId.equals(other.columnItemId))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equalsIgnoreCase(other.name))) {
            return false;
        }
        if ((this.param == null && other.param != null) || (this.param != null && !this.param.equalsIgnoreCase(other.param))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public TemplateColumnItem clone() {
        TemplateColumnItem o = null;
        try {
            o = (TemplateColumnItem) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }
}
