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
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "product_single_traded")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductSingleTraded.findAll", query = "SELECT p FROM ProductSingleTraded p"),
    @NamedQuery(name = "ProductSingleTraded.findByProductId", query = "SELECT p FROM ProductSingleTraded p WHERE p.productSingleTradedId = :productSingleTradedId"),
    @NamedQuery(name = "ProductSingleTraded.findByRate", query = "SELECT p FROM ProductSingleTraded p WHERE p.rate = :rate"),
    @NamedQuery(name = "ProductSingleTraded.findBySpread", query = "SELECT p FROM ProductSingleTraded p WHERE p.spread = :spread"),
    @NamedQuery(name = "ProductSingleTraded.findByIndexFactor", query = "SELECT p FROM ProductSingleTraded p WHERE p.indexFactor = :indexFactor"),
    @NamedQuery(name = "ProductSingleTraded.findByParity", query = "SELECT p FROM ProductSingleTraded p WHERE p.parity = :parity"),
    @NamedQuery(name = "ProductSingleTraded.findByStrike", query = "SELECT p FROM ProductSingleTraded p WHERE p.strike = :strike"),
    @NamedQuery(name = "ProductSingleTraded.findByOptionStyle", query = "SELECT p FROM ProductSingleTraded p WHERE p.optionStyle = :optionStyle"),
    @NamedQuery(name = "ProductSingleTraded.findByDeliveryType", query = "SELECT p FROM ProductSingleTraded p WHERE p.deliveryType = :deliveryType"),
    @NamedQuery(name = "ProductSingleTraded.findByPayoffDateClause", query = "SELECT p FROM ProductSingleTraded p WHERE p.payoffDateClause = :payoffDateClause"),
    @NamedQuery(name = "ProductSingleTraded.findByPayoffConditionClause", query = "SELECT p FROM ProductSingleTraded p WHERE p.payoffConditionClause = :payoffConditionClause"),
    @NamedQuery(name = "ProductSingleTraded.findByPayoffFormula", query = "SELECT p FROM ProductSingleTraded p WHERE p.payoffFormula = :payoffFormula")})
public class ProductSingleTraded implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_single_traded_id", nullable = false)
    private Integer productSingleTradedId;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "spread")
    private BigDecimal spread;
    @Column(name = "index_factor")
    private BigDecimal indexFactor;
    @Column(name = "parity")
    private Integer parity;
    @Column(name = "strike")
    private BigDecimal strike;
    @Column(name = "option_style")
    private String optionStyle;
    @Column(name = "delivery_type")
    private String deliveryType;
    @Column(name = "payoff_date_clause")
    private String payoffDateClause;
    @Column(name = "payoff_condition_clause")
    private String payoffConditionClause;
    @Column(name = "payoff_formula")
    private String payoffFormula;
    @Column(name = "start_price")
    private BigDecimal startPrice;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;

    public ProductSingleTraded() {
    }

    public ProductSingleTraded(Integer productId) {
        this.productSingleTradedId = productId;
    }

    public Integer getProductSingleTradedId() {
        return productSingleTradedId;
    }

    public void setProductSingleTradedId(Integer productSingleTradedId) {
        this.productSingleTradedId = productSingleTradedId;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
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

    public BigDecimal getIndexFactor() {
        return indexFactor;
    }

    public void setIndexFactor(BigDecimal indexFactor) {
        this.indexFactor = indexFactor;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
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

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPayoffDateClause() {
        return payoffDateClause;
    }

    public void setPayoffDateClause(String payoffDateClause) {
        this.payoffDateClause = payoffDateClause;
    }

    public String getPayoffConditionClause() {
        return payoffConditionClause;
    }

    public void setPayoffConditionClause(String payoffConditionClause) {
        this.payoffConditionClause = payoffConditionClause;
    }

    public String getPayoffFormula() {
        return payoffFormula;
    }

    public void setPayoffFormula(String payoffFormula) {
        this.payoffFormula = payoffFormula;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productSingleTradedId != null ? productSingleTradedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductSingleTraded)) {
            return false;
        }
        ProductSingleTraded other = (ProductSingleTraded) object;
        if ((this.productSingleTradedId == null && other.productSingleTradedId != null) || (this.productSingleTradedId != null && !this.productSingleTradedId.equals(other.productSingleTradedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductSingletraded[ productId=" + productSingleTradedId + " ]";
    }

    @Override
    public ProductSingleTraded clone() {
        ProductSingleTraded cloned = null;
        try {
            cloned = (ProductSingleTraded)super.clone();
            cloned.setProductSingleTradedId(null);

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
