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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class ProductUnderlyingPK implements Serializable {

//    @AttributeOverrides({
//        @AttributeOverride(name = "productId", column =
//                @Column(name = "product_id", nullable = false)),
//        @AttributeOverride(name = "underlyingId", column =
//                @Column(name = "underlying_id", nullable = false))})
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private Integer underlyingId;

    public ProductUnderlyingPK() {
    }

    public ProductUnderlyingPK(Integer _productId, Integer _underlyingId) {
        this.productId = _productId;
        this.underlyingId = _underlyingId;
    }

    @Column(name = "product_id", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer _productId) {
        this.productId = _productId;
    }

    @Column(name = "underlying_id", nullable = false)
    public Integer getUnderlyingId() {
        return underlyingId;
    }

    public void setUnderlyingId(Integer _underlyingId) {
        this.underlyingId = _underlyingId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (this.getProductId() != null) {
            result = 37 * result + this.getProductId();
        } else if (this.getUnderlyingId() != null) {
            result = 37 * result + this.getUnderlyingId();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductUnderlyingPK other = (ProductUnderlyingPK) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.underlyingId, other.underlyingId)) {
            return false;
        }
        return true;
    }
}
