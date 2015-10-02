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
package org.gaia.domain.observables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.trades.Product;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "product_historical_measure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductHistoricalMeasure.findAll", query = "SELECT p FROM ProductHistoricalMeasure p"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByProductHistoricalMeasureId", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.productHistoricalMeasureId = :productHistoricalMeasureId"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByQuoteSet", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.quoteSet = :quoteSet"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByValuationDate", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.valuationDate = :valuationDate"),
    @NamedQuery(name = "ProductHistoricalMeasure.findBySource", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.source = :source"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByProvider", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.provider = :provider"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByStatus", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.status = :status"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByCreationDate", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.creationDate = :creationDate"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByUpdateDate", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.updateDate = :updateDate"),
    @NamedQuery(name = "ProductHistoricalMeasure.findByComment", query = "SELECT p FROM ProductHistoricalMeasure p WHERE p.comment = :comment")})


public class ProductHistoricalMeasure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_historical_measure_id")
    private Integer productHistoricalMeasureId;
    @Column(name = "quote_set")
    private String quoteSet;
    @Column(name = "valuation_date")
    @Temporal(TemporalType.DATE)
    private Date valuationDate;
    @Column(name = "source")
    private String source;
    @Column(name = "provider")
    private String provider;
    @Column(name = "status")
    private String status;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "productHistoricalMeasure")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProductHistoricalMeasureValue> productHistoricalMeasureValues;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Product product;

    public ProductHistoricalMeasure() {
    }

    public ProductHistoricalMeasure(Integer productHistoricalMeasuresId) {
        this.productHistoricalMeasureId = productHistoricalMeasuresId;
    }

    public Integer getProductHistoricalMeasureId() {
        return productHistoricalMeasureId;
    }

    public void setProductHistoricalMeasureId(Integer productHistoricalMeasureId) {
        this.productHistoricalMeasureId = productHistoricalMeasureId;
    }

    public String getQuoteSet() {
        return quoteSet;
    }

    public void setQuoteSet(String quoteSet) {
        this.quoteSet = quoteSet;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlTransient
    public Collection<ProductHistoricalMeasureValue> getProductHistoricalMeasuresValueCollection() {
        return productHistoricalMeasureValues;
    }

    public void setProductHistoricalMeasuresValueCollection(Collection<ProductHistoricalMeasureValue> productHistoricalMeasuresValueCollection) {
        this.productHistoricalMeasureValues = productHistoricalMeasuresValueCollection;
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
        hash += (productHistoricalMeasureId != null ? productHistoricalMeasureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductHistoricalMeasure)) {
            return false;
        }
        ProductHistoricalMeasure other = (ProductHistoricalMeasure) object;
        if ((this.productHistoricalMeasureId == null && other.productHistoricalMeasureId != null) || (this.productHistoricalMeasureId != null && !this.productHistoricalMeasureId.equals(other.productHistoricalMeasureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.observables.ProductHistoricalMeasures[ productHistoricalMeasuresId=" + productHistoricalMeasureId + " ]";
    }

}
