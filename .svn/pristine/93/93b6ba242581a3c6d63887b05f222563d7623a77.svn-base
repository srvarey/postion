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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "product_reference")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductReference.findAll", query = "SELECT p FROM ProductReference p"),
    @NamedQuery(name = "ProductReference.findByValue", query = "SELECT p FROM ProductReference p WHERE p.value = :value"),
    @NamedQuery(name = "ProductReference.findByProductReferenceId", query = "SELECT p FROM ProductReference p WHERE p.productReferenceId = :productReferenceId")})
@XStreamAlias("Reference")
public class ProductReference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_reference_id")
    private Integer productReferenceId;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "reference_type")
    private String referenceType;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @XStreamOmitField
    @NotAccessibleField(level = 1)
    private Product product;

    public ProductReference() {
    }

    public ProductReference(Integer productReferenceId) {
        this.productReferenceId = productReferenceId;
    }

    public ProductReference(Integer productReferenceId, String value) {
        this.productReferenceId = productReferenceId;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getProductReferenceId() {
        return productReferenceId;
    }

    public void setProductReferenceId(Integer productReferenceId) {
        this.productReferenceId = productReferenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
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
        hash += (productReferenceId != null ? productReferenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductReference)) {
            return false;
        }
        ProductReference other = (ProductReference) object;
        if ((this.productReferenceId == null && other.productReferenceId != null) || (this.productReferenceId != null && !this.productReferenceId.equals(other.productReferenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductReference " + productReferenceId +StringUtils.SPACE+referenceType+"="+value;
    }
}
