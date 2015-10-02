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
package org.gaia.domain.trades;

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
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "product_rate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductRate.findAll", query = "SELECT p FROM ProductRate p"),
    @NamedQuery(name = "ProductRate.findByProductId", query = "SELECT p FROM ProductRate p WHERE p.productRateId = :productRateId"),
    @NamedQuery(name = "ProductRate.findByMinimumQuantity", query = "SELECT p FROM ProductRate p WHERE p.minimumQuantity = :minimumQuantity"),
    @NamedQuery(name = "ProductRate.findByIssuedAmount", query = "SELECT p FROM ProductRate p WHERE p.issuedAmount = :issuedAmount"),
    @NamedQuery(name = "ProductRate.findByIssueDate", query = "SELECT p FROM ProductRate p WHERE p.issueDate = :issueDate"),
    @NamedQuery(name = "ProductRate.findByRate", query = "SELECT p FROM ProductRate p WHERE p.rate = :rate"),
    @NamedQuery(name = "ProductRate.findBySpread", query = "SELECT p FROM ProductRate p WHERE p.spread = :spread"),
    @NamedQuery(name = "ProductRate.findByInflationStartRate", query = "SELECT p FROM ProductRate p WHERE p.inflationStartRate = :inflationStartRate"),
    @NamedQuery(name = "ProductRate.findByRateIndexFactor", query = "SELECT p FROM ProductRate p WHERE p.rateIndexFactor = :rateIndexFactor")})
public class ProductRate implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_rate_id", nullable = false)
    private Integer productRateId;
    @Column(name = "minimum_quantity")
    private BigDecimal minimumQuantity;
    @Column(name = "issued_amount")
    private Long issuedAmount;
    @Column(name = "initial_notional")
    private BigDecimal initialNotional;
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "spread")
    private BigDecimal spread;
    @Column(name = "inflation_start_rate")
    private BigDecimal inflationStartRate;
    @Column(name = "rate_index_factor")
    private BigDecimal rateIndexFactor;
    @Column(name = "issue_price")
    private BigDecimal issuePrice;
    @Column(name = "repayment_price")
    private BigDecimal repaymentPrice;
    @Column(name = "country")
    private String country;
    @Column(name = "issuer_type")
    private String issuerType;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;

    public ProductRate() {
    }

    public ProductRate(Integer productId) {
        this.productRateId = productId;
    }

    public Integer getProductRateId() {
        return productRateId;
    }

    public void setProductRateId(Integer productRateId) {
        this.productRateId = productRateId;
    }

    public BigDecimal getInitialNotional() {
        return initialNotional;
    }

    public void setInitialNotional(Double initialNotional) {
        this.initialNotional = BigDecimal.valueOf(initialNotional);
    }

    public BigDecimal getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(BigDecimal minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Long getIssuedAmount() {
        return issuedAmount;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public BigDecimal getRepaymentPrice() {
        return repaymentPrice;
    }

    public void setRepaymentPrice(BigDecimal repaymentPrice) {
        this.repaymentPrice = repaymentPrice;
    }

    public void setIssuedAmount(Long issuedAmount) {
        this.issuedAmount = issuedAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getInflationStartRate() {
        return inflationStartRate;
    }

    public void setInflationStartRate(BigDecimal inflationStartRate) {
        this.inflationStartRate = inflationStartRate;
    }

    public BigDecimal getRateIndexFactor() {
        return rateIndexFactor;
    }

    public void setRateIndexFactor(BigDecimal rateIndexFactor) {
        this.rateIndexFactor = rateIndexFactor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIssuerType() {
        return issuerType;
    }

    public void setIssuerType(String issuerType) {
        this.issuerType = issuerType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productRateId != null ? productRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductRate)) {
            return false;
        }
        ProductRate other = (ProductRate) object;
        if ((this.productRateId == null && other.productRateId != null) || (this.productRateId != null && !this.productRateId.equals(other.productRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductRate[ productId=" + productRateId + " ]";
    }

    @Override
    public ProductRate clone() {
        ProductRate cloned = null;
        try {
            cloned = (ProductRate)super.clone();
            cloned.setProductRateId(null);

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
