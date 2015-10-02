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
package org.gaia.domain.legalEntity;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.referentials.PdfDocConfig;
import org.gaia.domain.referentials.Rating;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "legal_entity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LegalEntity.findAll", query = "SELECT t FROM LegalEntity t"),
    @NamedQuery(name = "LegalEntity.findByLegalEntityId", query = "SELECT t FROM LegalEntity t WHERE t.legalEntityId = :legalEntityId"),
    @NamedQuery(name = "LegalEntity.findByShortName", query = "SELECT t FROM LegalEntity t WHERE t.shortName = :shortName"),
    @NamedQuery(name = "LegalEntity.findByLongName", query = "SELECT t FROM LegalEntity t WHERE t.longName = :longName"),
    @NamedQuery(name = "LegalEntity.findByComments", query = "SELECT t FROM LegalEntity t WHERE t.comments = :comments"),
    @NamedQuery(name = "LegalEntity.findByLegalEntityStatus", query = "SELECT t FROM LegalEntity t WHERE t.legalEntityStatus = :legalEntityStatus"),
    @NamedQuery(name = "LegalEntity.findByInactiveDate", query = "SELECT t FROM LegalEntity t WHERE t.inactiveDate = :inactiveDate"),
    @NamedQuery(name = "LegalEntity.findByEnteredDate", query = "SELECT t FROM LegalEntity t WHERE t.enteredDate = :enteredDate"),
    @NamedQuery(name = "LegalEntity.findByEnteredUser", query = "SELECT t FROM LegalEntity t WHERE t.enteredUser = :enteredUser"),
    @NamedQuery(name = "LegalEntity.findByHolidays", query = "SELECT t FROM LegalEntity t WHERE t.holidays = :holidays"),
    @NamedQuery(name = "LegalEntity.findByExternalRef", query = "SELECT t FROM LegalEntity t WHERE t.externalRef = :externalRef")})
public class LegalEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "legal_entity_id")
    private Integer legalEntityId;
    @Basic(optional = false)
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "long_name")
    private String longName;
    @Column(name = "comments")
    private String comments;
    @Column(name = "legal_entity_status")
    private String legalEntityStatus;
    @Column(name = "inactive_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    @Column(name = "entered_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Column(name = "entered_user")
    private String enteredUser;
    @Column(name = "holidays")
    private String holidays;
    @Column(name = "country")
    private String country;
    @Column(name = "external_ref")
    private String externalRef;
    @Column(name = "base_currency", length = 3)
    @AvailableValueList(isCurrency = true)
    private String baseCurrency;
    @OneToMany(mappedBy = "legalEntityId", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<LegalEntityAttribute> legalEntityAttributes;
    @ManyToOne(fetch = FetchType.EAGER)
    @XStreamOmitField
    @JoinColumn(name = "parent_legal_entity_id", referencedColumnName = "legal_entity_id")
    @NotAccessibleField(level = 1)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    private LegalEntity parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @XStreamOmitField
    private Set<LegalEntity> children = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "legalEntity")
    @XStreamOmitField
    private Collection<LegalEntityRole> roles = new HashSet<>();
    @OneToMany(mappedBy = "issuer")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<ProductCredit> productCredits;
    @ManyToMany(cascade = {CascadeType.ALL})
    @XStreamOmitField
    @JoinTable(name = "legal_entity_rating", joinColumns = {
        @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")}, inverseJoinColumns = {
        @JoinColumn(name = "rating_id", referencedColumnName = "rating_id")})
    @LazyCollection(LazyCollectionOption.TRUE)
    @NotAccessibleField(level = 4)
    private Collection<Rating> ratings;
    @OneToMany(mappedBy = "legalEntity1")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<Agreement> agreementCollection1;
    @OneToMany(mappedBy = "legalEntity2")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<Agreement> agreementCollection2;
    @OneToMany(mappedBy = "legalEntity")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<MarginClearerRule> marginClearerRuleCollection;
    @OneToMany(mappedBy = "accountManager")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<BoAccount> accountsAsAccountManager;
    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<BoAccount> accountsAsClient;
    @OneToMany(mappedBy = "legalEntityId")
    @XStreamOmitField
    private Collection<BoAccountIntermediary> boAccountIntermediaryCollection;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_entity_id")
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<CreditEntity> creditEntities;
    @OneToMany(cascade = CascadeType.ALL)
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinColumn(name = "portofolio_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Collection<PdfDocConfig> pdfDocConfigCollection;

    @XmlTransient
    public Collection<BoAccountIntermediary> getBoAccountIntermediaryCollection() {
        return boAccountIntermediaryCollection;
    }

    public void setBoAccountIntermediaryCollection(Collection<BoAccountIntermediary> boAccountIntermediaryCollection) {
        this.boAccountIntermediaryCollection = boAccountIntermediaryCollection;
    }

    public LegalEntity() {
    }

    public LegalEntity(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public LegalEntity(Integer legalEntityId, String shortName) {
        this.legalEntityId = legalEntityId;
        this.shortName = shortName;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Collection<LegalEntityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<LegalEntityRole> roles) {
        this.roles = roles;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Collection<LegalEntityAttribute> getLegalEntityAttributes() {
        return legalEntityAttributes;
    }

    public void setLegalEntityAttributes(Collection<LegalEntityAttribute> legalEntityAttributes) {
        this.legalEntityAttributes = legalEntityAttributes;
    }

    public LegalEntity getParent() {
        return parent;
    }

    public void setParent(LegalEntity parent) {
        this.parent = parent;
    }

    public Set<LegalEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<LegalEntity> children) {
        this.children = children;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLegalEntityStatus() {
        return legalEntityStatus;
    }

    public void setLegalEntityStatus(String legalEntityStatus) {
        this.legalEntityStatus = legalEntityStatus;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getEnteredUser() {
        return enteredUser;
    }

    public void setEnteredUser(String enteredUser) {
        this.enteredUser = enteredUser;
    }

    public String getHolidays() {
        return holidays;
    }

    public void setHolidays(String holidays) {
        this.holidays = holidays;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }

    public Collection<MarginClearerRule> getMarginClearerRuleCollection() {
        return marginClearerRuleCollection;
    }

    public void setMarginClearerRuleCollection(Collection<MarginClearerRule> marginClearerRuleCollection) {
        this.marginClearerRuleCollection = marginClearerRuleCollection;
    }

    public Collection<BoAccount> getAccountsAsAccountManager() {
        return accountsAsAccountManager;
    }

    public void setAccountsAsAccountManager(Collection<BoAccount> accountsAsAccountManager) {
        this.accountsAsAccountManager = accountsAsAccountManager;
    }

    public Collection<BoAccount> getAccountsAsClient() {
        return accountsAsClient;
    }

    public void setAccountsAsClient(Collection<BoAccount> accountsAsClient) {
        this.accountsAsClient = accountsAsClient;
    }

    public String getRating(String agency) {
        if (ratings != null) {
            for (Rating r : ratings) {
                if (r.getAgency().equals(agency)) {
                    return r.getRating();
                }
            }
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (legalEntityId != null ? legalEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof LegalEntity)) {
            return false;
        }
        LegalEntity other = (LegalEntity) object;
        if ((this.legalEntityId == null && other.legalEntityId != null)
                || (this.legalEntityId != null && !this.legalEntityId.equals(other.legalEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return shortName + "/" + legalEntityId;
    }

    @XmlTransient
    public Collection<ProductCredit> getProductCredits() {
        return productCredits;
    }

    public void setProductCredits(Collection<ProductCredit> productCredits) {
        this.productCredits = productCredits;
    }

    @XmlTransient
    public Collection<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<Rating> ratings) {
        this.ratings = ratings;
    }

    @XmlTransient
    public Collection<Agreement> getAgreementCollection2() {
        return agreementCollection2;
    }

    public void setAgreementCollection2(Collection<Agreement> agreementCollection2) {
        this.agreementCollection2 = agreementCollection2;
    }

    @XmlTransient
    public Collection<Agreement> getAgreementCollection1() {
        return agreementCollection1;
    }

    public void setAgreementCollection1(Collection<Agreement> agreementCollection1) {
        this.agreementCollection1 = agreementCollection1;
    }

    public LegalEntityAttribute getLegalEntityAttributeByName(String attributeName) {
        if (legalEntityAttributes != null) {
            for (LegalEntityAttribute legalEntityAttribute : legalEntityAttributes) {
                if (legalEntityAttribute.getAttributeName().equalsIgnoreCase(attributeName)) {
                    return legalEntityAttribute;
                }
            }
        }
        return null;
    }

    public void addLegalEntityAttribute(LegalEntityAttribute attribute) {
        if (legalEntityAttributes == null) {
            legalEntityAttributes = new HashSet<>();
        } else {
            LegalEntityAttribute oldAttribute = getLegalEntityAttributeByName(attribute.getAttributeName());
            if (oldAttribute != null) {
                oldAttribute.setAttributeValue(attribute.getAttributeValue());
            }
        }
        legalEntityAttributes.add(attribute);
    }
}
