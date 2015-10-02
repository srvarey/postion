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
package org.gaia.domain.legalEntity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Jawhar Kamoun
 */
@Audited
@Entity
@Table(name = "bo_account")
@XmlRootElement
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "BoAccount.findAll", query = "SELECT b FROM BoAccount b"),
    @NamedQuery(name = "BoAccount.findByAccountId", query = "SELECT b FROM BoAccount b WHERE b.accountId = :accountId"),
    @NamedQuery(name = "BoAccount.findByType", query = "SELECT b FROM BoAccount b WHERE b.type = :type"),
    @NamedQuery(name = "BoAccount.findByCurrency", query = "SELECT b FROM BoAccount b WHERE b.currency = :currency"),
    @NamedQuery(name = "BoAccount.findByAccountCode", query = "SELECT b FROM BoAccount b WHERE b.accountCode = :accountCode"),
    @NamedQuery(name = "BoAccount.findByIban", query = "SELECT b FROM BoAccount b WHERE b.iban = :iban")})


public class BoAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "type")
    private String type;
    @Column(name = "currency")
    private String currency;
    @Column(name = "code")
    private String accountCode;
    @Column(name = "iban")
    private String iban;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "legal_entity_id")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity client;
    @ManyToOne
    @JoinColumn(name = "account_manager_id", referencedColumnName = "legal_entity_id")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity accountManager;
    @OneToMany(cascade= CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "account_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AuditMappedBy(mappedBy = "account")
    private Collection<BoAccountIntermediary> boAccountIntermediaryCollection;

    @XmlTransient
    public Collection<BoAccountIntermediary> getBoAccountIntermediaryCollection() {
        return boAccountIntermediaryCollection;
    }

    public void setBoAccountIntermediaryCollection(Collection<BoAccountIntermediary> boAccountIntermediaryCollection) {
        this.boAccountIntermediaryCollection = boAccountIntermediaryCollection;
    }

    public BoAccount() {
    }

    public BoAccount(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public LegalEntity getClient() {
        return client;
    }

    public void setClient(LegalEntity client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    public LegalEntity getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(LegalEntity accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof BoAccount)) {
            return false;
        }
        BoAccount other = (BoAccount) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BoAccount id=" + accountId ;
    }

}
