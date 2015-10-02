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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.DomainUtils;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "market_quote")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size=10)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarketQuote.findAll", query = "SELECT m FROM MarketQuote m"),
    @NamedQuery(name = "MarketQuote.findByMarketQuoteId", query = "SELECT m FROM MarketQuote m WHERE m.marketQuoteId = :marketQuoteId"),
    @NamedQuery(name = "MarketQuote.findByQuoteSet", query = "SELECT m FROM MarketQuote m WHERE m.quoteSet = :quoteSet"),
    @NamedQuery(name = "MarketQuote.findByValuationDate", query = "SELECT m FROM MarketQuote m WHERE m.valuationDate = :valuationDate"),
    @NamedQuery(name = "MarketQuote.findByQuotationType", query = "SELECT m FROM MarketQuote m WHERE m.quotationType = :quotationType"),
    @NamedQuery(name = "MarketQuote.findByOpen", query = "SELECT m FROM MarketQuote m WHERE m.open = :open"),
    @NamedQuery(name = "MarketQuote.findByClose", query = "SELECT m FROM MarketQuote m WHERE m.close = :close"),
    @NamedQuery(name = "MarketQuote.findByHigh", query = "SELECT m FROM MarketQuote m WHERE m.high = :high"),
    @NamedQuery(name = "MarketQuote.findByLow", query = "SELECT m FROM MarketQuote m WHERE m.low = :low"),
    @NamedQuery(name = "MarketQuote.findByBid", query = "SELECT m FROM MarketQuote m WHERE m.bid = :bid"),
    @NamedQuery(name = "MarketQuote.findByAsk", query = "SELECT m FROM MarketQuote m WHERE m.ask = :ask"),
    @NamedQuery(name = "MarketQuote.findByCurrency", query = "SELECT m FROM MarketQuote m WHERE m.currency = :currency"),
    @NamedQuery(name = "MarketQuote.findBySource", query = "SELECT m FROM MarketQuote m WHERE m.source = :source"),
    @NamedQuery(name = "MarketQuote.findByProvider", query = "SELECT m FROM MarketQuote m WHERE m.provider = :provider"),
    @NamedQuery(name = "MarketQuote.findByStatus", query = "SELECT m FROM MarketQuote m WHERE m.status = :status"),
    @NamedQuery(name = "MarketQuote.findByCreationDate", query = "SELECT m FROM MarketQuote m WHERE m.creationDate = :creationDate"),
    @NamedQuery(name = "MarketQuote.findByUpdateDate", query = "SELECT m FROM MarketQuote m WHERE m.updateDate = :updateDate"),
    @NamedQuery(name = "MarketQuote.findByComment", query = "SELECT m FROM MarketQuote m WHERE m.comment = :comment")})


public class MarketQuote implements Serializable,Comparable,Cloneable {

    public static final String OPEN="Open";
    public static final String BID="Bid";
    public static final String ASK="Ask";
    public static final String CLOSE="Close";


    public enum QuotationType{

        CLEAN("Clean",new BigDecimal(100)),
        DIRTY("Dirty",new BigDecimal(100)),
        PRICE("Price",BigDecimal.ONE),
        PRICEpct("PricePct",new BigDecimal(100)),
        RATE("Rate",new BigDecimal(100)),
        SPREAD("Spread",new BigDecimal(10000)),
        ACCRUAL("Accrual",new BigDecimal(100)),
        BASIS_POINT("bp",new BigDecimal(10000)),
        FWDpts("Point",new BigDecimal(10000)),
        RECOVERY_RATE("Recovery",new BigDecimal(100)),
        VOLATILITY("Volatility",new BigDecimal(100)),
        VOLATILITY_SPREAD("Volatility Spread",new BigDecimal(100));

        private static final long serialVersionUID = 1L;
        private final String name;
        private BigDecimal mult ;

        QuotationType(String value,BigDecimal mult) {
            this.name=value;
            this.mult=mult;
        }

        public BigDecimal getMult() {
            return mult;
        }

        public String getName() {
            return name;
        }

        public static BigDecimal getMult( String quotationType){
            BigDecimal ret=BigDecimal.ONE;
            QuotationType[] arr = QuotationType.values();
            if (quotationType!=null){
            for (int i=0;i<arr.length;i++){
                if (quotationType.equals(arr[i].getName())){
                    ret=arr[i].getMult();
                }
            }}
            return ret;
        }

        public static QuotationType getQuotationTypeByName(String name){
            for (QuotationType qtype : QuotationType.values()){
                if (qtype.name.equalsIgnoreCase(name)){
                    return qtype;
                }
            }
            return null;
        }
    }


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "market_quote_id")
    private Integer marketQuoteId;
    @Column(name = "quote_set")
    private String quoteSet;
    @Column(name = "valuation_date")
    @Temporal(TemporalType.DATE)
    private Date valuationDate;
    @Column(name = "quotation_type")
    private String quotationType;
   /** // @Max(name=?)  @Min(name=?)//if you know range of your decimal fields consider using these annotations to enforce field validation */
    @Column(name = "open_quote")
    private BigDecimal open;
    @Column(name = "close_quote")
    private BigDecimal close;
    @Column(name = "high")
    private BigDecimal high;
    @Column(name = "low")
    private BigDecimal low;
    @Column(name = "bid")
    private BigDecimal bid;
    @Column(name = "ask")
    private BigDecimal ask;
    @Column(name = "currency")
    private String currency;
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
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;

    /** used to order curve quotes */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    public MarketQuote() {
    }

    public MarketQuote(Integer marketQuoteId) {
        this.marketQuoteId = marketQuoteId;
    }

    public Integer getMarketQuoteId() {
        return marketQuoteId;
    }

    public void setMarketQuoteId(Integer marketQuoteId) {
        this.marketQuoteId = marketQuoteId;
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

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marketQuoteId != null ? marketQuoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof MarketQuote)) {
            return false;
        }
        MarketQuote other = (MarketQuote) object;
        if ((this.marketQuoteId == null && other.marketQuoteId != null) || (this.marketQuoteId != null && !this.marketQuoteId.equals(other.marketQuoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str="Id=" + marketQuoteId;
        if (product!=null && product.getShortName()!=null){
            str+=" on "+product.getShortName()+"/"+product.getProductId();
        }
        str+=StringUtils.SPACE+DomainUtils.numberFormat.format(close);
        str+= " on "+valuationDate;
        return str;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public int compareTo(Object o){
        MarketQuote marketQuote=(MarketQuote) o;
        if (dateEnd!=null && marketQuote.getDateEnd()!=null && dateEnd.after(marketQuote.getDateEnd())) {
            return 1;
        }
        else if (dateEnd!=null && marketQuote.getDateEnd()!=null && dateEnd.before(marketQuote.getDateEnd())) {
            return -1;
        }
        return 0;
    }

    @Override
    public MarketQuote clone() {
        MarketQuote object = null;
        try {
            object = (MarketQuote)super.clone();
            object.setMarketQuoteId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return object;
    }
}
