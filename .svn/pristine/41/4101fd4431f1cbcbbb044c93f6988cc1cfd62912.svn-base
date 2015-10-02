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
package org.gaia.domain.trades;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "product_classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductExternalClassification.findAll", query = "SELECT p FROM ProductExternalClassification p"),
    @NamedQuery(name = "ProductExternalClassification.findByType", query = "SELECT p FROM ProductExternalClassification p WHERE p.productClassificationPK.type = :type"),
    @NamedQuery(name = "ProductExternalClassification.findByName", query = "SELECT p FROM ProductExternalClassification p WHERE p.productClassificationPK.name = :name"),
    @NamedQuery(name = "ProductExternalClassification.findByParent", query = "SELECT p FROM ProductExternalClassification p WHERE p.parent = :parent"),
    @NamedQuery(name = "ProductExternalClassification.findByLevel", query = "SELECT p FROM ProductExternalClassification p WHERE p.level = :level")})


public class ProductExternalClassification implements Serializable,Comparable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductExternalClassificationPK productClassificationPK;
    @Column(name = "parent")
    private String parent;
    @Column(name = "classification_level")
    private Short level;
    @Column(name = "use")
    private String use;

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public ProductExternalClassification() {
        this.productClassificationPK =new ProductExternalClassificationPK();
    }

    public ProductExternalClassification(ProductExternalClassificationPK productClassificationPK) {
        this.productClassificationPK = productClassificationPK;
    }

    public ProductExternalClassification(String type, String name) {
        this.productClassificationPK = new ProductExternalClassificationPK(type, name);
    }

    public ProductExternalClassificationPK getProductExternalClassificationPK() {
        return productClassificationPK;
    }

    public void setProductExternalClassificationPK(ProductExternalClassificationPK productClassificationPK) {
        this.productClassificationPK = productClassificationPK;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }
     public String getType() {
        return productClassificationPK.getType();
    }

    public void setType(String type) {
        this.productClassificationPK.setType(type);
    }

    public String getName() {
        return productClassificationPK.getName();
    }

    public void setName(String name) {
        productClassificationPK.setName(name);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productClassificationPK != null ? productClassificationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductExternalClassification)) {
            return false;
        }
        ProductExternalClassification other = (ProductExternalClassification) object;
        if ((this.productClassificationPK == null && other.productClassificationPK != null) || (this.productClassificationPK != null && !this.productClassificationPK.equals(other.productClassificationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductExternalClassification[ productClassificationPK=" + productClassificationPK + " ]";
    }

    @Override
    public int compareTo(Object o){
        ProductExternalClassification pc=(ProductExternalClassification) o;
        return -1*pc.getName().compareTo(this.productClassificationPK.getName());
    }

}
