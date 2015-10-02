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

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.gaia.domain.legalEntity.LegalEntity;
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
@Table(name = "product_credit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCredit.findAll", query = "SELECT p FROM ProductCredit p"),
    @NamedQuery(name = "ProductCredit.findByProductId", query = "SELECT p FROM ProductCredit p WHERE p.productCreditId = :productCreditId"),
    @NamedQuery(name = "ProductCredit.findBySeniority", query = "SELECT p FROM ProductCredit p WHERE p.seniority = :seniority"),
    @NamedQuery(name = "ProductCredit.findByCreditEvents", query = "SELECT p FROM ProductCredit p WHERE p.creditEvents = :creditEvents")})
public class ProductCredit implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_credit_id", nullable = false)
    private Integer productCreditId;
    @Column(name = "seniority")
    private String seniority;
    @Column(name = "credit_events")
    private String creditEvents;
    @Column(name = "attachment_point")
    private BigDecimal attachmentPoint;
    @Column(name = "exhaustion_point")
    private BigDecimal exhaustionPoint;
    @Column(name = "recovery_factor")
    private BigDecimal recoveryFactor;
    @Column(name = "contract_type")
    private String contractType;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=4)
    @XStreamOmitField
    private LegalEntity issuer;
    @Column(name = "serie")
    private Short serie;
    @Column(name = "version")
    private Short version;

    public ProductCredit() {
    }

    public ProductCredit(Integer productId) {
        this.productCreditId = productId;
    }

    public Integer getProductCreditId() {
        return productCreditId;
    }

    public void setProductCreditId(Integer productId) {
        this.productCreditId = productId;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getCreditEvents() {
        return creditEvents;
    }

    public void setCreditEvents(String creditEvents) {
        this.creditEvents = creditEvents;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LegalEntity getIssuer() {
        return issuer;
    }

    public void setIssuer(LegalEntity issuer) {
        this.issuer = issuer;
    }

    public BigDecimal getAttachmentPoint() {
        return attachmentPoint;
    }

    public void setAttachmentPoint(BigDecimal attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
    }

    public BigDecimal getExhaustionPoint() {
        return exhaustionPoint;
    }

    public void setExhaustionPoint(BigDecimal exhaustionPoint) {
        this.exhaustionPoint = exhaustionPoint;
    }

    public BigDecimal getRecoveryFactor() {
        return recoveryFactor;
    }

    public void setRecoveryFactor(BigDecimal recoveryFactor) {
        this.recoveryFactor = recoveryFactor;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Short getSerie() {
        return serie;
    }

    public void setSerie(Short serie) {
        this.serie = serie;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productCreditId != null ? productCreditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductCredit)) {
            return false;
        }
        ProductCredit other = (ProductCredit) object;
        if ((this.productCreditId == null && other.productCreditId != null) || (this.productCreditId != null && !this.productCreditId.equals(other.productCreditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductCredit[ productId=" + productCreditId + " ]";
    }

    @Override
    public ProductCredit clone() {
        ProductCredit cloned = null;
        try {
            cloned = (ProductCredit) super.clone();
            cloned.setProductCreditId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
