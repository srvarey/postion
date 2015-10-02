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
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author User
 */
@Entity
@Table(name = "template")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "ReportTemplate.findAll", query = "SELECT r FROM ReportTemplate r"),
    @NamedQuery(name = "ReportTemplate.findByTemplateId", query = "SELECT r FROM ReportTemplate r WHERE r.templateId = :templateId"),
    @NamedQuery(name = "ReportTemplate.findByTemplateName", query = "SELECT r FROM ReportTemplate r WHERE r.templateName = :templateName"),
    @NamedQuery(name = "ReportTemplate.findByPricingEnvironment", query = "SELECT r FROM ReportTemplate r WHERE r.pricingEnvironment = :pricingEnvironment"),
    @NamedQuery(name = "ReportTemplate.findByCurrency", query = "SELECT r FROM ReportTemplate r WHERE r.currency = :currency"),
    @NamedQuery(name = "ReportTemplate.findByObjectType", query = "SELECT r FROM ReportTemplate r WHERE r.objectType = :objectType")})
public class ReportTemplate implements Serializable, Cloneable {

    @Transient
    public static final String VALUEDATE_SUFFIX = "";
    @Transient
    public static final String FIRSTDATE_SUFFIX = ".start";
    @Transient
    public static final String EVOLUTION_SUFFIX = ".evol";
    @Transient
    public static final String CONVERSION_SUFFIX = ".ref";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "template_id")
    private Integer templateId;
    @Column(name = "template_name")
    private String templateName;
    @Column(name = "pricing_environment")
    private String pricingEnvironment;
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "fund_look_through")
    @Basic(optional = false)
    private Boolean fundLookThrough=false;
    @Column(name = "show_root")
    @Basic(optional = false)
    private Boolean showRoot;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "filter_id", referencedColumnName = "filter_id")
    private Filter filter;

    @OneToMany(mappedBy = "template")
    @OrderBy("columnIndex")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<TemplateColumnItem> templateColumnItems;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "position_configuration_id")
    private PositionConfiguration positionConfiguration;

    @Transient
    private boolean isReportEnabled;
    @Transient
    private boolean isDrillDown;

    public ReportTemplate() {
    }

    public ReportTemplate(Integer templateId) {
        this.templateId = templateId;
    }

    public ReportTemplate(Integer templateId, String objectType) {
        this.templateId = templateId;
        this.objectType = objectType;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPricingEnvironment() {
        return pricingEnvironment;
    }

    public void setPricingEnvironment(String pricingEnvironment) {
        this.pricingEnvironment = pricingEnvironment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Boolean isShowRoot() {
        return showRoot;
    }

    public void setShowRoot(Boolean showRoot) {
        this.showRoot = showRoot;
    }

    public void setObjectTypeClass(Class objectType) {
        this.objectType = objectType.getName();
    }

    private void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter _filter) {
        this.filter = _filter;
    }

    @XmlTransient
    public Collection<TemplateColumnItem> getTemplateColumnItems() {
        return templateColumnItems;
    }

    public void setTemplateColumnItems(Collection<TemplateColumnItem> templateColumnItemCollection) {
        this.templateColumnItems = templateColumnItemCollection;
    }

    public boolean isReportEnabled() {
        return isReportEnabled;
    }

    public void setReportEnabled(boolean isReportEnabled) {
        this.isReportEnabled = isReportEnabled;
    }

    public Boolean isFundLookThrough() {
        return fundLookThrough;
    }

    public void setFundLookThrough(Boolean fundLookThrough) {
        this.fundLookThrough = fundLookThrough;
    }

    public boolean isDrillDown() {
        return isDrillDown;
    }

    public void setIsDrillDown(boolean isDrillDown) {
        this.isDrillDown = isDrillDown;
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
        hash += (templateId != null ? templateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ReportTemplate)) {
            return false;
        }
        ReportTemplate other = (ReportTemplate) object;
        if ((this.templateId == null && other.templateId != null) || (this.templateId != null && !this.templateId.equals(other.templateId))) {
            return false;
        }
        if ((this.filter == null && other.filter != null) || (this.filter != null && !this.filter.equals(other.filter))) {
            return false;
        }
        return (this.templateColumnItems != null || other.templateColumnItems == null) && (this.templateColumnItems == null || this.templateColumnItems.equals(other.templateColumnItems));
    }

    @Override
    public String toString() {
        String str = "Template " + templateName + " id " + templateId;
        if (filter != null) {
            str += " filter " + filter.getName();
        }
        return str;
    }

    @Override
    public Object clone() {
        ReportTemplate template = null;
        try {
            template = (ReportTemplate) super.clone();
            template.setTemplateId(null);
            template.setTemplateColumnItems(new ArrayList());
            for (TemplateColumnItem item : this.getTemplateColumnItems()) {
                template.getTemplateColumnItems().add((TemplateColumnItem) item.clone());
            }
            if (this.getFilter() != null) {
                template.setFilter((Filter) this.getFilter().clone());
            }
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return template;
    }
}
