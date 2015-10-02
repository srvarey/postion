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
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "product_historical_measure_value")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductHistoricalMeasureValue.findAll", query = "SELECT p FROM ProductHistoricalMeasureValue p"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByProductHistoricalMeasureValueId", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.productHistoricalMeasureValueId = :productHistoricalMeasureValueId"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByMeasureName", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.measureName = :measureName"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByMeasureValue", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.measureValue = :measureValue"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByCurrency", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.currency = :currency"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByDerivativeLevel", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.derivativeLevel = :derivativeLevel"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByRelatedProductId", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.relatedProductId = :relatedProductId"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByRelatedProductId2", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.relatedProductId2 = :relatedProductId2"),
    @NamedQuery(name = "ProductHistoricalMeasureValue.findByComment", query = "SELECT p FROM ProductHistoricalMeasureValue p WHERE p.comment = :comment")})


public class ProductHistoricalMeasureValue implements Serializable , Cloneable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_historical_measure_value_id")
    private Integer productHistoricalMeasureValueId;
    @Column(name = "measure_name")
    private String measureName;
    @Column(name = "quotation_type")
    private String quotationType;
    /**  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation */
    @Column(name = "measure_value")
    private BigDecimal measureValue;
    @Column(name = "currency")
    private String currency;
    @Column(name = "derivative_level")
    private Short derivativeLevel;
    @Column(name = "related_product_id")
    private Integer relatedProductId;
    @Column(name = "related_product_id_2")
    private Integer relatedProductId2;
    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "product_historical_measure_id", referencedColumnName = "product_historical_measure_id")
    @ManyToOne
    private ProductHistoricalMeasure productHistoricalMeasure;

    public ProductHistoricalMeasureValue() {
    }

    public ProductHistoricalMeasureValue(Integer productHistoricalMeasuresValueId) {
        this.productHistoricalMeasureValueId = productHistoricalMeasuresValueId;
    }

    public Integer getProductHistoricalMeasureValueId() {
        return productHistoricalMeasureValueId;
    }

    public void setProductHistoricalMeasureValueId(Integer productHistoricalMeasureValueId) {
        this.productHistoricalMeasureValueId = productHistoricalMeasureValueId;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public BigDecimal getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(BigDecimal measureValue) {
        this.measureValue = measureValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Short getDerivativeLevel() {
        return derivativeLevel;
    }

    public void setDerivativeLevel(Short derivativeLevel) {
        this.derivativeLevel = derivativeLevel;
    }

    public Integer getRelatedProductId() {
        return relatedProductId;
    }

    public void setRelatedProductId(Integer relatedProductId) {
        this.relatedProductId = relatedProductId;
    }

    public Integer getRelatedProductId2() {
        return relatedProductId2;
    }

    public void setRelatedProductId2(Integer relatedProductId2) {
        this.relatedProductId2 = relatedProductId2;
    }


    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductHistoricalMeasure getProductHistoricalMeasure() {
        return productHistoricalMeasure;
    }

    public void setProductHistoricalMeasure(ProductHistoricalMeasure productHistoricalMeasure) {
        this.productHistoricalMeasure = productHistoricalMeasure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productHistoricalMeasureValueId != null ? productHistoricalMeasureValueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductHistoricalMeasureValue)) {
            return false;
        }
        ProductHistoricalMeasureValue other = (ProductHistoricalMeasureValue) object;
        if ((this.productHistoricalMeasureValueId == null && other.productHistoricalMeasureValueId != null) || (this.productHistoricalMeasureValueId != null && !this.productHistoricalMeasureValueId.equals(other.productHistoricalMeasureValueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.observables.ProductHistoricalMeasuresValue[ productHistoricalMeasuresValueId=" + productHistoricalMeasureValueId + " ]";
    }

    @Override
    public Object clone(){
        ProductHistoricalMeasureValue productHistoricalMeasureValue = null;
        try {
            productHistoricalMeasureValue=(ProductHistoricalMeasureValue)super.clone();
            productHistoricalMeasureValue.setProductHistoricalMeasureValueId(null);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ProductHistoricalMeasureValue.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productHistoricalMeasureValue;
    }

}
