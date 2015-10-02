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
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "product_curve")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement

public class ProductCurve implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator="foreign")
    @GenericGenerator(name="foreign", strategy="foreign", parameters={ @Parameter(name="property", value="product") })
    @Column(name = "product_curve_id")
    private Integer productCurveId;
    @Column(name = "tenor")
    private String tenor;
    @Column(name = "source")
    private String source;
    @Column(name = "interpolation_method")
    private String interpolationMethod;
    @Column(name = "bootstrapping_method")
    private String bootstrappingMethod;
    @Column(name = "strike")
    private BigDecimal strike;
    @Column(name = "strike_type")
    private String strikeType;
    @Column(name = "calendar_code")
    private String calendarCode;
    @Column(name = "start_lag")
    private Integer startLag;
    @Column(name = "is_already_generated")
    private Boolean isAlreadyGenerated=false;
    @Column(name = "is_composite")
    private Boolean isComposite=false;


    public Integer getStartLag() {
        return startLag;
    }

    public void setStartLag(Integer startLag) {
        this.startLag = startLag;
    }

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;

    public ProductCurve() {
    }

    public ProductCurve(Integer productId) {
        this.productCurveId = productId;
    }

    public Integer getProductCurveId() {
        return productCurveId;
    }

    public void setProductCurveId(Integer productCurveId) {
        this.productCurveId = productCurveId;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getInterpolationMethod() {
        return interpolationMethod;
    }

    public void setInterpolationMethod(String interpolationMethod) {
        this.interpolationMethod = interpolationMethod;
    }

    public String getBootstrappingMethod() {
        return bootstrappingMethod;
    }

    public void setBootstrappingMethod(String bootstrappingMethod) {
        this.bootstrappingMethod = bootstrappingMethod;
    }

    public BigDecimal getStrike() {
        return strike;
    }

    public void setStrike(BigDecimal strike) {
        this.strike = strike;
    }

    public String getStrikeType() {
        return strikeType;
    }

    public void setStrikeType(String strikeType) {
        this.strikeType = strikeType;
    }

    public String getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    public Boolean isAlreadyGenerated() {
        return isAlreadyGenerated;
    }

    public void setIsAlreadyGenerated(Boolean isAlreadyGenerated) {
        this.isAlreadyGenerated = isAlreadyGenerated;
    }

    public Boolean isComposite() {
        return isComposite;
    }

    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productCurveId != null ? productCurveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductCurve)) {
            return false;
        }
        ProductCurve other = (ProductCurve) object;
        if ((this.productCurveId == null && other.productCurveId != null) || (this.productCurveId != null && !this.productCurveId.equals(other.productCurveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductCurve[ productId=" + productCurveId + " ]";
    }

    @Override
    public ProductCurve clone() {
        ProductCurve cloned = null;
        try {
            cloned = (ProductCurve)super.clone();
            cloned.setProductCurveId(null);

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
