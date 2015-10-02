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
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Bouazzi Saber
 */
@Audited
@Entity
@Table(name = "product_forex")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "ProductForex.findAll", query = "SELECT p FROM ProductForex p"),
    @NamedQuery(name = "ProductForex.findByProductForexId", query = "SELECT p FROM ProductForex p WHERE p.productForexId = :productForexId")})

public class ProductForex implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_forex_id", nullable = false)
    private Integer productForexId;
    @Column(name = "strike")
    private BigDecimal strike;
    @Column(name = "barrier")
    private BigDecimal barrier;
    @Column(name = "option_style")
    private String optionStyle;
    @Column(name = "exercice_type")
    private String exerciceType;
    @Column(name = "is_digital")
    private Boolean isDigital;
    @Column(name = "barrier_type")
    private String barrierType;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "currency_id_1", referencedColumnName = "product_id")
    @NotAccessibleField(level=1)
    private Product currency1;
    @ManyToOne
    @JoinColumn(name = "currency_id_2", referencedColumnName = "product_id")
    @NotAccessibleField(level=1)
    private Product currency2;
    @Column(name = "is_payment_at_end")
    private Boolean isPaymentAtEnd = true;
    @Column(name = "tick_size")
    private BigDecimal tickSize;
    @Column(name = "fixing_date")
    private Date fixingDate;

    public ProductForex() {
    }

    public BigDecimal getBarrier() {
        return barrier;
    }

    public void setBarrier(BigDecimal barrier) {
        this.barrier = barrier;
    }

    public ProductForex(Integer productId) {
        this.productForexId = productId;
    }

    public Product getCurrency1() {
        return currency1;
    }

    public void setCurrency1(Product currency1) {
        this.currency1 = currency1;
    }

    public Product getCurrency2() {
        return currency2;
    }

    public void setCurrency2(Product currency2) {
        this.currency2 = currency2;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductForexId() {
        return productForexId;
    }

    public void setProductForexId(Integer productId) {
        this.productForexId = productId;
    }

    public BigDecimal getStrike() {
        return strike;
    }

    public void setStrike(BigDecimal strike) {
        this.strike = strike;
    }

    public String getOptionStyle() {
        return optionStyle;
    }

    public void setOptionStyle(String optionStyle) {
        this.optionStyle = optionStyle;
    }

    public String getExerciceType() {
        return exerciceType;
    }

    public void setExerciceType(String exerciceType) {
        this.exerciceType = exerciceType;
    }

    public Boolean isDigital() {
        return isDigital;
    }

    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType;
    }

    public Boolean getIsPaymentAtEnd() {
        return isPaymentAtEnd;
    }

    public void setIsPaymentAtEnd(Boolean isPaymentAtEnd) {
        this.isPaymentAtEnd = isPaymentAtEnd;
    }

    public Boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigitald(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    public BigDecimal getTickSize() {
        return tickSize;
    }

    public void setTickSize(BigDecimal tickSize) {
        this.tickSize = tickSize;
    }

    public Date getFixingDate() {
        return fixingDate;
    }

    public void setFixingDate(Date fixingDate) {
        this.fixingDate = fixingDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productForexId != null ? productForexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductForex)) {
            return false;
        }
        ProductForex other = (ProductForex) object;
        if ((this.productForexId == null && other.productForexId != null) || (this.productForexId != null && !this.productForexId.equals(other.productForexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductForex[ productId=" + productForexId + " ]";
    }

    @Override
    public ProductForex clone() {
        ProductForex cloned = null;
        try {
            cloned = (ProductForex)super.clone();
            cloned.setProductForexId(null);

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
