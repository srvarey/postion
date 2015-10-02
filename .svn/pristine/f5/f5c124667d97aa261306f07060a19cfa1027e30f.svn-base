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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.referentials.Country;
import org.gaia.domain.referentials.Sector;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "product_equity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductEquity.findAll", query = "SELECT p FROM ProductEquity p"),
    @NamedQuery(name = "ProductEquity.findByProductId", query = "SELECT p FROM ProductEquity p WHERE p.productEquityId = :productEquityId"),
    @NamedQuery(name = "ProductEquity.findByMinimumQuantity", query = "SELECT p FROM ProductEquity p WHERE p.minimumQuantity = :minimumQuantity")
})
public class ProductEquity implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_equity_id", nullable = false)
    private Integer productEquityId;
    @Column(name = "minimum_quantity")
    private Long minimumQuantity;
    @Column(name = "issued_shares")
    private Long issuedShares;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_legal_entity_id")
    @XStreamOmitField
    private LegalEntity issuer;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "parity")
    private Short parity;
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date")
    private Date issueDate;
    @Column(name = "strike")
    private Double strike;
    @Column(name = "option_style")
    private String optionStyle;
    @Column(name = "exercise_type")
    private String exerciseType;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level=1)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    @XStreamOmitField
    private LegalEntity portfolio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    @XStreamOmitField
    @Audited(targetAuditMode = NOT_AUDITED)
    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getOptionStyle() {
        return optionStyle;
    }

    public void setOptionStyle(String optionStyle) {
        this.optionStyle = optionStyle;
    }

    public Double getStrike() {
        return strike;
    }

    public void setStrike(Double strike) {
        this.strike = strike;
    }

    public Long getIssuedShares() {
        return issuedShares;
    }

    public void setIssuedShares(Long issuedShares) {
        this.issuedShares = issuedShares;
    }

    public LegalEntity getIssuer() {
        return issuer;
    }

    public void setIssuer(LegalEntity issuer) {
        this.issuer = issuer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Short getParity() {
        return parity;
    }

    public void setParity(Short parity) {
        this.parity = parity;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public ProductEquity() {
    }

    public ProductEquity(Integer productId) {
        this.productEquityId = productId;
    }

    public Integer getProductEquityId() {
        return productEquityId;
    }

    public void setProductEquityId(Integer productEquityId) {
        this.productEquityId = productEquityId;
    }

    public LegalEntity getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(LegalEntity portfolio) {
        this.portfolio = portfolio;
    }

    public Long getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Long minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productEquityId != null ? productEquityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProductEquity)) {
            return false;
        }
        ProductEquity other = (ProductEquity) object;
        if ((this.productEquityId == null && other.productEquityId != null) || (this.productEquityId != null && !this.productEquityId.equals(other.productEquityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.trades.ProductEquity[ productId=" + productEquityId + " ]";
    }

    @Override
    public ProductEquity clone() {
        ProductEquity cloned = null;
        try {
            cloned = (ProductEquity)super.clone();
            cloned.setProductEquityId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
