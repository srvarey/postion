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

/**
 *
 * @author Jawhar Kamoun
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.Date;
import java.util.Locale;
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
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "contract_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContractEvent.findAll", query = "SELECT c FROM ContractEvent c"),
    @NamedQuery(name = "ContractEvent.findByContractEventId", query = "SELECT c FROM ContractEvent c WHERE c.contractEventId = :contractEventId"),
    @NamedQuery(name = "ContractEvent.findByContractEvent", query = "SELECT c FROM ContractEvent c WHERE c.contractEvent = :contractEvent"),
    @NamedQuery(name = "ContractEvent.findByNegociationDate", query = "SELECT c FROM ContractEvent c WHERE c.negociationDate = :negociationDate"),
    @NamedQuery(name = "ContractEvent.findByValueDate", query = "SELECT c FROM ContractEvent c WHERE c.valueDate = :valueDate"),
    @NamedQuery(name = "ContractEvent.findByPaymentDate", query = "SELECT c FROM ContractEvent c WHERE c.paymentDate = :paymentDate")})
public class ContractEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contract_event_id")
    private Integer contractEventId;
    @Column(name = "contract_event")
    private String contractEvent;
    @Column(name = "unwind_factor")
    private BigDecimal unwindFactor;
    @Column(name = "unwind_amount")
    private BigDecimal unwindAmount;
    @Column(name = "negociation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date negociationDate;
    @Column(name = "value_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueDate;
    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparty_id", referencedColumnName = "legal_entity_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity counterparty;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;
    @AvailableValueList(isCurrency = true)
    @Column(name = "payment_currency", length = 3)
    private String paymentCurrency;

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public ContractEvent() {
    }

    public LegalEntity getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(LegalEntity counterparty) {
        this.counterparty = counterparty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ContractEvent(Integer contractEventId) {
        this.contractEventId = contractEventId;
    }

    public Integer getContractEventId() {
        return contractEventId;
    }

    public void setContractEventId(Integer contractEventId) {
        this.contractEventId = contractEventId;
    }

    public String getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(String contractEvent) {
        this.contractEvent = contractEvent;
    }

    public BigDecimal getUnwindFactor() {
        return unwindFactor;
    }

    public void setUnwindFactor(BigDecimal unwindFactor) {
        this.unwindFactor = unwindFactor;
    }

    public Date getNegociationDate() {
        return negociationDate;
    }

    public void setNegociationDate(Date negociationDate) {
        this.negociationDate = negociationDate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getUnwindAmount() {
        return unwindAmount;
    }

    public void setUnwindAmount(BigDecimal unwindAmount) {
        this.unwindAmount = unwindAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractEventId != null ? contractEventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContractEvent)) {
            return false;
        }
        ContractEvent other = (ContractEvent) object;
        if ((this.contractEventId == null && other.contractEventId != null) || (this.contractEventId != null
                && !this.contractEventId.equals(other.contractEventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Format mydecimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));
        return contractEvent + StringUtils.SPACE + mydecimalFormat.format(unwindAmount);
    }
}
