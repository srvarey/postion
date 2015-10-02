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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.DomainUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "credit_event")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Audited
@NamedQueries({
    @NamedQuery(name = "CreditEvent.findAll", query = "SELECT c FROM CreditEvent c"),
    @NamedQuery(name = "CreditEvent.findByCreditEventId", query = "SELECT c FROM CreditEvent c WHERE c.creditEventId = :creditEventId"),
    @NamedQuery(name = "CreditEvent.findByCreditEvent", query = "SELECT c FROM CreditEvent c WHERE c.creditEvent = :creditEvent"),
    @NamedQuery(name = "CreditEvent.findByIsHard", query = "SELECT c FROM CreditEvent c WHERE c.isHard = :isHard"),
    @NamedQuery(name = "CreditEvent.findByReceptionDate", query = "SELECT c FROM CreditEvent c WHERE c.receptionDate = :receptionDate"),
    @NamedQuery(name = "CreditEvent.findByRecoveryRate", query = "SELECT c FROM CreditEvent c WHERE c.recoveryRate = :recoveryRate")})


public class CreditEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "credit_event_id")
    private Integer creditEventId;
    @Column(name = "credit_event")
    private String creditEvent;
    @Column(name = "is_hard")
    private Boolean isHard;
    @Column(name = "reception_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receptionDate;
    @Column(name = "default_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date defaultDate;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "recovery_rate")
    private BigDecimal recoveryRate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issuer_id", referencedColumnName = "legal_entity_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity issuer;


    public CreditEvent() {
    }

    public CreditEvent(Integer creditEventId) {
        this.creditEventId = creditEventId;
    }

    public LegalEntity getIssuer() {
        return issuer;
    }

    public void setIssuer(LegalEntity issuer) {
        this.issuer = issuer;
    }

    public Integer getCreditEventId() {
        return creditEventId;
    }

    public void setCreditEventId(Integer creditEventId) {
        this.creditEventId = creditEventId;
    }

    public String getCreditEvent() {
        return creditEvent;
    }

    public void setCreditEvent(String creditEvent) {
        this.creditEvent = creditEvent;
    }

    public Boolean getIsHard() {
        return isHard;
    }

    public void setIsHard(Boolean isHard) {
        this.isHard = isHard;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public Date getDefaultDate() {
        return defaultDate;
    }

    public void setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
    }

    public BigDecimal getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(BigDecimal recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditEventId != null ? creditEventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditEvent)) {
            return false;
        }
        CreditEvent other = (CreditEvent) object;
        if ((this.creditEventId == null && other.creditEventId != null) || (this.creditEventId != null && !this.creditEventId.equals(other.creditEventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return creditEvent+" on "+issuer+" id " + creditEventId+ " on "+DomainUtils.dateFormat.format(defaultDate);
    }

}
