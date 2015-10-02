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
import org.gaia.domain.trades.Product;
import org.hibernate.envers.Audited;

/**
 *
 * @author user
 */
@Audited
@Entity
@Table(name = "market_data_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarketDataCode.findAll", query = "SELECT m FROM MarketDataCode m"),
    @NamedQuery(name = "MarketDataCode.findByMarketDataCodeId", query = "SELECT m FROM MarketDataCode m WHERE m.marketDataCodeId = :marketDataCodeId"),
    @NamedQuery(name = "MarketDataCode.findByProductCode", query = "SELECT m FROM MarketDataCode m WHERE m.productCode = :productCode"),
    @NamedQuery(name = "MarketDataCode.findByQuoteSet", query = "SELECT m FROM MarketDataCode m WHERE m.quoteSet = :quoteSet")})


public class MarketDataCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "market_data_code_id")
    private Integer marketDataCodeId;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "quote_set")
    private String quoteSet;    
    @Column(name = "currency")
    private String currency;
    
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    
    @JoinColumn(name = "market_data_source_id", referencedColumnName = "market_data_source_id")
    @ManyToOne
    private MarketDataSource marketDataSource;

    public MarketDataCode() {
    }

    public MarketDataCode(Integer marketDataLinkId) {
        this.marketDataCodeId = marketDataLinkId;
    }

    public Integer getMarketDataCodeId() {
        return marketDataCodeId;
    }

    public void setMarketDataCodeId(Integer marketDataLinkId) {
        this.marketDataCodeId = marketDataLinkId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getQuoteSet() {
        return quoteSet;
    }

    public void setQuoteSet(String quoteSet) {
        this.quoteSet = quoteSet;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MarketDataSource getMarketDataSource() {
        return marketDataSource;
    }

    public void setMarketDataSource(MarketDataSource marketDataSource) {
        this.marketDataSource = marketDataSource;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marketDataCodeId != null ? marketDataCodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarketDataCode)) {
            return false;
        }
        MarketDataCode other = (MarketDataCode) object;
        if ((this.marketDataCodeId == null && other.marketDataCodeId != null) || (this.marketDataCodeId != null && !this.marketDataCodeId.equals(other.marketDataCodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiajademasterdispatcherserver.MarketDataCode[ marketDataLinkId=" + marketDataCodeId + " ]";
    }
    
}
