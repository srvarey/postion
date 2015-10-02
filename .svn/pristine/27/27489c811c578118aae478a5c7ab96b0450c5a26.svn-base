/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either tradeVersion 3.0 of the License, or (at your
 * option) any later tradeVersion.
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.referentials.GaiaUser;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.DomainUtils;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "trade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trade.findAll", query = "SELECT t FROM Trade t"),
    @NamedQuery(name = "Trade.findByTradeId", query = "SELECT t FROM Trade t WHERE t.tradeId = :tradeId"),
    @NamedQuery(name = "Trade.findByTradeDate", query = "SELECT t FROM Trade t WHERE t.tradeDate = :tradeDate"),
    @NamedQuery(name = "Trade.findByTradeTime", query = "SELECT t FROM Trade t WHERE t.tradeTime = :tradeTime"),
    @NamedQuery(name = "Trade.findByValueDate", query = "SELECT t FROM Trade t WHERE t.valueDate = :valueDate"),
    @NamedQuery(name = "Trade.findByPrice", query = "SELECT t FROM Trade t WHERE t.price = :price"),
    @NamedQuery(name = "Trade.findByPriceType", query = "SELECT t FROM Trade t WHERE t.priceType = :priceType"),
    @NamedQuery(name = "Trade.findByPriceCurrency", query = "SELECT t FROM Trade t WHERE t.priceCurrency = :priceCurrency"),
    @NamedQuery(name = "Trade.findByForexRate", query = "SELECT t FROM Trade t WHERE t.forexRate = :forexRate"),
    @NamedQuery(name = "Trade.findByStatus", query = "SELECT t FROM Trade t WHERE t.status = :status"),
    @NamedQuery(name = "Trade.findByCreationDateTime", query = "SELECT t FROM Trade t WHERE t.creationDateTime = :creationDateTime"),
    @NamedQuery(name = "Trade.findByUpdateDateTime", query = "SELECT t FROM Trade t WHERE t.updateDateTime = :updateDateTime"),
    @NamedQuery(name = "Trade.findByComment", query = "SELECT t FROM Trade t WHERE t.comment = :comment")})
@XStreamAlias("Trade")
@Audited
public class Trade implements Serializable, IPriceable, Cloneable {

    public enum QuantityType {

        NOTIONAL("Notional"),
        QUANTITY("Quantity");
        private static final long serialVersionUID = 1L;
        public String name;

        QuantityType(String name) {
            this.name = name;
        }
    };

    public enum TradeType {

        /**
         * An enum type.
         */
        COLLATERAL("Collateral"),
        COLLATERAL_SPAN("Collateral Span"),
        COLLATERAL_VARIATION("Collateral Variation"),
        SPOT("Spot"),
        BUY_SELL("Buy/Sell"),
        FORWARD("Forward");
        private static final long serialVersionUID = 1L;
        public String name;

        TradeType(String name) {
            this.name = name;
        }
    };
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trade_id", nullable = false)
    private Integer tradeId;
    @Column(name = "trade_date")
    @Temporal(TemporalType.DATE)
    private Date tradeDate;
    @Column(name = "trade_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tradeTime;
    @Column(name = "quantity")
    private BigDecimal quantity = BigDecimal.ZERO;
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;
    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    private Date valueDate;
    @Column(name = "quantity_type")
    private String quantityType;
    @Column(name = "price", precision = 20, scale = 8)
    private BigDecimal price = BigDecimal.ZERO;
    @Column(name = "price_type", length = 32)
    private String priceType;
    @Column(name = "price_currency", length = 3)
    @AvailableValueList(isCurrency = true)
    private String priceCurrency;
    @AvailableValueList(isCurrency = true)
    @Column(name = "settlement_currency", length = 3)
    private String settlementCurrency;
    @Column(name = "forex_rate", precision = 20, scale = 8)
    private BigDecimal forexRate = BigDecimal.ONE;
    @Column(name = "status", length = 32)
    @AvailableValueList
    private String status = "New";
    @AvailableValueList
    @Column(name = "lifecycle_status", length = 32)
    private String lifeCycleStatus = "Created";
    @Column(name = "creation_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    @Column(name = "trade_version")
    private Integer tradeVersion;
    @Column(name = "comment", length = 2147483647)
    private String comment;
    @Column(name = "is_collateral")
    private Boolean isCollateral = false;
    @Column(name = "trade_type")
    private String tradeType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trader_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    private GaiaUser trader;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", insertable = false, updatable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Agreement agreement;
    @Column(name = "converted_amount")
    private BigDecimal convertedAmount = BigDecimal.ZERO;
    @Column(name = "agreement_id")
    private Integer agreementId;
    @Column(name = "negociated_price")
    private BigDecimal negociatedPrice = BigDecimal.ZERO;
    @Column(name = "market_price")
    private BigDecimal marketPrice = BigDecimal.ZERO;
    @Column(name = "sales_margin")
    private BigDecimal salesMargin = BigDecimal.ZERO;
    @AvailableValueList(isCurrency = true)
    @Column(name = "sales_margin_currency", length = 3)
    private String salesMarginCurrency;
    @Column(name = "negociated_price_type", length = 32)
    private String negociatedPriceType;
    @Column(name = "trade_mirror_id")
    private Integer tradeMirrorId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "internal_counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    @AvailableValueList(LegalEntityRoleList = "InternalCounterparty")
    private LegalEntity internalCounterparty;
    @ManyToMany(mappedBy = "trades")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TradeGroup> tradeGroups;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    @AvailableValueList(LegalEntityRoleList = "Counterparty,Issuer,Legal Entity,CCP")
    private LegalEntity counterparty;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "trade_id")
    @AuditMappedBy(mappedBy = "trade")
    private Collection<TradeEntity> tradeEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trade_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "trade")
    private Set<TradeAttribute> tradeAttributes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Fetch(FetchMode.JOIN)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;
    @OneToMany(mappedBy = "trade")
    @LazyCollection(LazyCollectionOption.TRUE)
    @AuditMappedBy(mappedBy = "trade")
    @XStreamOmitField
    private Collection<ProductEvent> productEvents;

    public Trade() {
        tradeAttributes = new HashSet();
        tradeEntityCollection = new HashSet();
        tradeGroups = new HashSet();
    }

    public String getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(String lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public BigDecimal getNegociatedPrice() {
        return negociatedPrice;
    }

    public void setNegociatedPrice(BigDecimal negociatedPrice) {
        this.negociatedPrice = negociatedPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalesMargin() {
        return salesMargin;
    }

    public void setSalesMargin(BigDecimal salesMargin) {
        this.salesMargin = salesMargin;
    }

    public String getSalesMarginCurrency() {
        return salesMarginCurrency;
    }

    public void setSalesMarginCurrency(String salesMarginCurrency) {
        this.salesMarginCurrency = salesMarginCurrency;
    }

    public String getNegociatedPriceType() {
        return negociatedPriceType;
    }

    public void setNegociatedPriceType(String negociatedPriceType) {
        this.negociatedPriceType = negociatedPriceType;
    }

    @Override
    public LegalEntity getInternalCounterparty() {
        return internalCounterparty;
    }

    public void setInternalCounterparty(LegalEntity internalCounterparty) {
        this.internalCounterparty = internalCounterparty;
    }

    @Override
    public LegalEntity getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(LegalEntity counterparty) {
        this.counterparty = counterparty;
    }

    public Collection<TradeEntity> getTradeEntityCollection() {
        return tradeEntityCollection;
    }

    public void setTradeEntityCollection(Collection<TradeEntity> tradeEntityCollection) {
        this.tradeEntityCollection = tradeEntityCollection;
    }

    public Set<TradeAttribute> getTradeAttributes() {
        return tradeAttributes;
    }

    public void setTradeAttributes(Set<TradeAttribute> tradeAttributes) {
        this.tradeAttributes = tradeAttributes;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    @Override
    public Integer getId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /**
     *
     * @param _tradeId
     */
    public void setId(int _tradeId) {
        this.tradeId = _tradeId;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public BigDecimal getForexRate() {
        return forexRate;
    }

    public void setForexRate(BigDecimal forexRate) {
        this.forexRate = forexRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public Integer getTradeVersion() {
        return tradeVersion;
    }

    public void setTradeVersion(Integer tradeVersion) {
        this.tradeVersion = tradeVersion;
    }

    public GaiaUser getTrader() {
        return trader;
    }

    public void setTrader(GaiaUser trader) {
        this.trader = trader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsCollateral() {
        return isCollateral;
    }

    public void setIsCollateral(Boolean isCollateral) {
        this.isCollateral = isCollateral;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    @Override
    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Set<TradeGroup> getTradeGroups() {
        return tradeGroups;
    }

    public TradeGroup getTradeGroup(String groupType) {
        if (tradeGroups != null) {
            for (TradeGroup group : tradeGroups) {
                if (group.getTradeGroupType().equalsIgnoreCase(groupType)) {
                    return group;
                }
            }
        }
        return null;
    }

    public void setTradeGroups(Set<TradeGroup> tradeGroups) {
        this.tradeGroups = tradeGroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeId != null ? tradeId.hashCode() : 0);
        return hash;
    }

    /**
     * check if equals on tradeId base
     *
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Trade)) {
            return false;
        }
        Trade other = (Trade) object;
        if ((this.tradeId == null && other.tradeId != null) || (this.tradeId != null && !this.tradeId.equals(other.tradeId))) {
            return false;
        }
        return true;
    }

    /**
     * retrieve trade description
     */
    @Override
    public String toString() {
        String ret = "Trade " + tradeId;
        if (internalCounterparty != null && internalCounterparty.getShortName() != null) {
            ret += StringUtils.SPACE + internalCounterparty.getShortName();
        }
        if (quantity != null) {
            ret += " trades " + DomainUtils.decimalFormat.format(quantity);
        }
        if (product != null && product.getShortName() != null) {
            ret += StringUtils.SPACE + product.getShortName();
        }
        if (counterparty != null && counterparty.getShortName() != null) {
            ret += " with " + counterparty.getShortName();
        }
        if (tradeDate != null) {
            ret += " on " + DomainUtils.dateFormat.format(tradeDate);
        }
        return ret;
    }

    /**
     * .
     * @return quantity between two dates.
     */
    @Override
    public BigDecimal getQuantity(Date valDate) {
        if (valDate == null) {
            return quantity;
        } else if (this.tradeDate != null && !valDate.before(this.tradeDate)) {
            return quantity;
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public Integer getTradeMirrorId() {
        return tradeMirrorId;
    }

    public void setTradeMirrorId(Integer tradeMirrorId) {
        this.tradeMirrorId = tradeMirrorId;
    }

    public Collection<ProductEvent> getProductEvents() {
        return productEvents;
    }

    public void setProductEvents(Collection<ProductEvent> productEvents) {
        this.productEvents = productEvents;
    }

    @Override
    public Trade clone() {
        Trade myClone = null;
        try {
            myClone = (Trade) super.clone();
            myClone.setTradeId(null);
            myClone.setTradeEntityCollection(new HashSet());
            if (this.tradeEntityCollection != null) {
                for (TradeEntity entity : this.tradeEntityCollection) {
                    TradeEntity newTradeEntity = entity.clone();
                    newTradeEntity.setTrade(myClone);
                    myClone.getTradeEntityCollection().add(newTradeEntity);
                }
            }
            myClone.setTradeAttributes(new HashSet());
            myClone.setTradeGroups(new HashSet());
            myClone.setProductEvents(new HashSet());

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }
}
