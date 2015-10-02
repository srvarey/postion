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
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Jawhar Kamoun
 */
@Entity
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "product_underlying")
public class ProductUnderlying implements Serializable, Cloneable {

    private ProductUnderlyingPK pk;
    @XStreamOmitField
    private Product product;
    private Product underlying;
    private BigDecimal weight;

    public ProductUnderlying() {
    }

    public ProductUnderlying(ProductUnderlyingPK id, Product _product, Product _underlying) {
        this.pk = id;
        this.product = _product;
        this.underlying = _underlying;
    }

    public ProductUnderlying(ProductUnderlyingPK id, Product _product, Product _underlying, BigDecimal _weight) {
        this.pk = id;
        this.product = _product;
        this.underlying = _underlying;
        this.weight = _weight;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "productId", column
                = @Column(name = "product_id", nullable = false)),
        @AttributeOverride(name = "underlyingId", column
                = @Column(name = "underlying_id", nullable = false))})
    public ProductUnderlyingPK getPk() {
        return pk;
    }

    public void setPk(ProductUnderlyingPK _pk) {
        this.pk = _pk;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "underlying_id", nullable = false, insertable = false, updatable = false)
    public Product getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(Product underlying) {
        this.underlying = underlying;
    }

    @Column(name = "weight")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductUnderlying other = (ProductUnderlying) obj;
        if (this.pk == null && other.pk == null) {
            return false;
        }
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }

        return true;
    }
}
