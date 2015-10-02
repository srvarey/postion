/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either productVersion 3.0 of the License, or (at your
 * option) any later productVersion.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.referentials.Exchange;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByShortName", query = "SELECT p FROM Product p WHERE p.shortName = :shortName"),
    @NamedQuery(name = "Product.findByLongName", query = "SELECT p FROM Product p WHERE p.longName = :longName"),
    @NamedQuery(name = "Product.findByProductType", query = "SELECT p FROM Product p WHERE p.productType = :productType"),
    @NamedQuery(name = "Product.findByQuantityType", query = "SELECT p FROM Product p WHERE p.quantityType = :quantityType"),
    @NamedQuery(name = "Product.findByNotionalCurrency", query = "SELECT p FROM Product p WHERE p.notionalCurrency = :notionalCurrency"),
    @NamedQuery(name = "Product.findByStartDate", query = "SELECT p FROM Product p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Product.findByMaturityDate", query = "SELECT p FROM Product p WHERE p.maturityDate = :maturityDate"),
    @NamedQuery(name = "Product.findByIsAsset", query = "SELECT p FROM Product p WHERE p.isAsset = :isAsset"),
    @NamedQuery(name = "Product.findByComment", query = "SELECT p FROM Product p WHERE p.comment = :comment"),
    @NamedQuery(name = "Product.findByCreationDateTime", query = "SELECT p FROM Product p WHERE p.creationDateTime = :creationDateTime"),
    @NamedQuery(name = "Product.findByUpdateDateTime", query = "SELECT p FROM Product p WHERE p.updateDateTime = :updateDateTime"),
    @NamedQuery(name = "Product.findByUserId", query = "SELECT p FROM Product p WHERE p.userId = :userId"),
    @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")})
@XStreamAlias("Product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Audited
public class Product implements Serializable, IPriceable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "short_name", length = 128)
    private String shortName;
    @Column(name = "long_name", length = 256)
    private String longName;
    @Column(name = "product_type", length = 32)
    @AvailableValueList
    private String productType;
    @Column(name = "notional_multiplier", precision = 17, scale = 17)
    private BigDecimal notionalMultiplier = BigDecimal.ONE;
    @Column(name = "quantity_type", length = 32)
    private String quantityType = "Notional";
    @Column(name = "quotation_type", length = 32)
    private String quotationType = "Rate";
    @Column(name = "notional_currency", length = 3)
    @AvailableValueList(isCurrency = true)
    private String notionalCurrency;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "maturity_date")
    @Temporal(TemporalType.DATE)
    private Date maturityDate;
    @Column(name = "is_asset")
    private Boolean isAsset = false;
    @Column(name = "comment", length = 2147483647)
    private String comment;
    @Column(name = "creation_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    @Column(name = "product_version")
    private Integer productVersion;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "status", length = 32)
    private String status = "Active";
    @Column(name = "forex_rate")
    private BigDecimal forexRate;
    @Column(name = "settlement_delay")
    private Short settlementDelay;
    @Column(name = "holiday_code")
    private String calendar;
    @OneToMany(mappedBy = "product")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    @NotAudited
    private Collection<Trade> trades;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "scheduler_id", referencedColumnName = "scheduler_id")
    private Scheduler scheduler;
    @ManyToMany
    @XStreamOmitField
    @JoinTable(name = "product_schedule", joinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "schedule_line_id", referencedColumnName = "schedule_line_id", nullable = false)})
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<ScheduleLine> scheduleLines;
    @ManyToMany
    @XStreamOmitField
    @JoinTable(name = "product_exchange", joinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id", nullable = false)})
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<Exchange> exchanges;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_subproduct", joinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "product_id")}, inverseJoinColumns = {
        @JoinColumn(name = "subproduct_id", referencedColumnName = "product_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<Product> subProducts;
    @ManyToMany(mappedBy = "subProducts")
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Collection<Product> parentProducts;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductEquity> productEquities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductRate> productRates;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductForex> productForexs;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductSingleTraded> productSingleTradeds;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductCurve> productCurves;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductCredit> productCredits;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    @AuditMappedBy(mappedBy = "product")
    @NotAccessibleField(level = 3)
    private Collection<ProductReference> productReferences;
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "product")
    @NotAudited
    private Collection<ProductHistoricalMeasure> productHistoricalMeasures;
    @OneToMany(mappedBy = "product")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    @NotAudited
    private Collection<MarketQuote> marketQuotes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<MarketDataCode> marketDataCodes;
    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProductUnderlying> underlyingProducts = new HashSet<>();
    @OneToMany(mappedBy = "underlying")
    @LazyCollection(LazyCollectionOption.TRUE)
    @XStreamOmitField
    private Set<ProductUnderlying> superProducts = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "product")
    private Collection<ProductEvent> productEvents;
    @OneToMany(mappedBy = "currencyProduct")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Currency> currencies;

    // fields for introspection only
    // they are never filled
    @Transient
    private ProductEquity productEquity;
    @Transient
    private ProductCredit productCredit;
    @Transient
    private ProductCurve productCurve;
    @Transient
    private ProductRate productRate;
    @Transient
    private ProductForex productForex;
    @Transient
    private ProductSingleTraded productSingleTraded;
    @Transient
    @NotAccessibleField(level = 3)
    private Product underlying;
    @Transient
    @NotAccessibleField(level = 2)
    private Product firstLeg;
    @Transient
    @NotAccessibleField(level = 2)
    private Product secondLeg;

    public Product() {
        subProducts = new HashSet();
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public String getShortName() {
        return shortName;
    }

    public String getReference(String type) {
        if (productReferences != null) {
            for (ProductReference pr : productReferences) {
                if (pr.getReferenceType().equals(type)) {
                    return pr.getValue();
                }
            }
        }
        return "";
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public BigDecimal getNotionalMultiplier() {
        return notionalMultiplier;
    }

    public BigDecimal getNotionalMultiplierByDate(Date valueDate) {
        if (productEvents != null) {
            BigDecimal factor = null;
            if (productEvents.size() > 0) {
                boolean isAfterAllEvents = true;
                for (ProductEvent event : productEvents) {
                    if (factor == null) {
                        for (ProductEventDetail detail : event.getProductEventDetails()) {
                            if (detail.getProductColumn().equalsIgnoreCase("NotionalMultiplier")) {
                                factor = BigDecimal.valueOf(Double.parseDouble(detail.getOldValue()));
                            }
                        }
                    }
                    if (!event.getEffectiveDate().after(valueDate)) {
                        for (ProductEventDetail detail : event.getProductEventDetails()) {
                            if (detail.getProductColumn().equalsIgnoreCase("NotionalMultiplier")) {
                                factor = BigDecimal.valueOf(Double.parseDouble(detail.getOldValue()));
                            }
                        }
                    } else {
                        isAfterAllEvents = false;
                    }
                }
                if (!isAfterAllEvents && factor != null) {
                    return factor;
                } else {
                    return notionalMultiplier;
                }
            } else {
                return notionalMultiplier;
            }
        } else {
            return notionalMultiplier;
        }
    }

    public void setNotionalMultiplier(BigDecimal notionalMultiplier) {
        this.notionalMultiplier = notionalMultiplier;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public String getNotionalCurrency() {
        return notionalCurrency;
    }

    public void setNotionalCurrency(String notionalCurrency) {
        this.notionalCurrency = notionalCurrency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Collection<Product> getSubProducts() {
        return subProducts;
    }

    public Boolean getIsAsset() {
        return isAsset;
    }

    public void setIsAsset(Boolean isAsset) {
        this.isAsset = isAsset;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(Integer productVersion) {
        this.productVersion = productVersion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSuperProducts(Set<ProductUnderlying> superProducts) {
        this.superProducts = superProducts;
    }

    @XmlTransient
    public Collection<ScheduleLine> getScheduleLines() {
        return scheduleLines;
    }

    public void setScheduleLines(Collection<ScheduleLine> scheduleLines) {
        this.scheduleLines = scheduleLines;
    }

    public Collection<ProductUnderlying> getUnderlyingProducts() {
        return underlyingProducts;
    }

    public List<Product> loadUnderlyings() {
        List<Product> underlyingList = new ArrayList<>();
        for (ProductUnderlying productUnderlying : getUnderlyingProducts()) {
            underlyingList.add(productUnderlying.getUnderlying());
        }
        return underlyingList;
    }

    public Product loadSingleUnderlying() {
        Product singleUnderlying = null;
        if (underlyingProducts != null && !underlyingProducts.isEmpty()) {
            singleUnderlying = loadUnderlyings().get(0);
        }
        return singleUnderlying;
    }

    public void setUnderlyingProducts(Set<ProductUnderlying> underlyingProduct) {
        this.underlyingProducts = underlyingProduct;
    }

    public void addUnderlying(Product underlying) {
        if (underlying != null) {
            ProductUnderlying underlyings = new ProductUnderlying();
            if (productId != null && underlying.getProductId() != null) {
                underlyings.setPk(new ProductUnderlyingPK(productId, underlying.getProductId()));
            }
            underlyings.setProduct(this);
            underlyings.setUnderlying(underlying);
            underlyingProducts.add(underlyings);
        }
    }

    public void setSingleUnderlying(Product underlying) {
        underlyingProducts = new HashSet();
        addUnderlying(underlying);
    }

    public void addProductReference(ProductReference _productRef) {
        if (productReferences == null) {
            productReferences = new HashSet<>();
        } else {
            ProductReference oldProductRef = loadProductReference(_productRef.getReferenceType());
            if (oldProductRef != null) {
                oldProductRef.setValue(_productRef.getValue());
            }
        }
        productReferences.add(_productRef);
    }

    public void addUnderlyingWithWeight(Product underlying, BigDecimal weight) {
        ProductUnderlying underlyings = new ProductUnderlying();
        underlyings.setProduct(this);
        underlyings.setUnderlying(underlying);
        underlyings.setWeight(weight);
        underlyingProducts.add(underlyings);
    }

    public void addSubProduct(Product product) {
        if (subProducts == null) {
            subProducts = new HashSet();
        }
        subProducts.add(product);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if (this.productId == null && other.productId == null) {
            return false;
        }
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return shortName + " id " + productId + " type " + productType;
    }

    @XmlTransient
    public Collection<Exchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(Collection<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    @XmlTransient
    public Collection<ProductReference> getProductReferences() {
        return productReferences;
    }

    public void setProductReferences(Collection<ProductReference> productReferences) {
        this.productReferences = productReferences;
    }

    /**
     * Used by report colum builder
     *
     * @param refType
     * @return
     */
    public String getProductReference(String refType) {
        ProductReference prd = loadProductReference(refType);
        return prd != null ? prd.getValue() : null;
    }

    public ProductReference loadProductReference(String refType) {

        try{
            for (ProductReference pr : getProductReferences()) {
            if (pr.getReferenceType().equals(refType)) {
                return pr;
            }
        }
        } catch (Exception e){}
        return null;
    }

    public ProductEquity getProductEquity() {
        if (productEquities != null && productEquities.size() > 0) {
            return productEquities.iterator().next();
        }
        return null;
    }

    public void setProductEquity(ProductEquity productEquity) {
        if (productEquity == null) {
            productEquities = null;
        } else {
            productEquities = new HashSet();
            productEquities.add(productEquity);
        }
    }

    public ProductRate getProductRate() {
        if (productRates != null && productRates.size() > 0) {
            return productRates.iterator().next();
        }
        return null;
    }

    public void setProductRate(ProductRate productRate) {
        if (productRate == null) {
            productRates = null;
        } else {
            productRates = new HashSet();
            productRates.add(productRate);
        }
    }

    public ProductForex getProductForex() {
        if (productForexs != null && productForexs.size() > 0) {
            return productForexs.iterator().next();
        }
        return null;
    }

    public void setProductForex(ProductForex productForex) {
        if (productForex == null) {
            productForexs = null;
        } else {
            productForexs = new HashSet();
            productForexs.add(productForex);
        }
    }

    public ProductCurve getProductCurve() {
        if (productCurves != null && productCurves.size() > 0) {
            return productCurves.iterator().next();
        }
        return null;
    }

    public void setProductCurve(ProductCurve productCurve) {
        if (productCurve == null) {
            productCurves = null;
        } else {
            productCurves = new HashSet();
            productCurves.add(productCurve);
        }
    }

    public ProductSingleTraded getProductSingleTraded() {
        if (productSingleTradeds != null && productSingleTradeds.size() > 0) {
            return productSingleTradeds.iterator().next();
        }
        return null;
    }

    public void setProductSingleTraded(ProductSingleTraded productSingleTraded) {
        if (productSingleTraded == null) {
            productSingleTradeds = null;
        } else {
            productSingleTradeds = new HashSet();
            productSingleTradeds.add(productSingleTraded);
        }
    }

    public ProductCredit getProductCredit() {
        if (productCredits != null && productCredits.size() > 0) {
            return productCredits.iterator().next();
        }
        return null;
    }

    public void setProductCredit(ProductCredit productCredit) {
        if (productCredit == null) {
            productCredits = null;
        } else {
            productCredits = new HashSet();
            productCredits.add(productCredit);
        }
    }

    @XmlTransient
    public Collection<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Collection<Trade> trades) {
        this.trades = trades;
    }

    @XmlTransient
    public Collection<ProductHistoricalMeasure> getProductHistoricalMeasures() {
        return productHistoricalMeasures;
    }

    public void setProductHistoricalMeasures(Collection<ProductHistoricalMeasure> productHistoricalMeasures) {
        this.productHistoricalMeasures = productHistoricalMeasures;
    }

    @XmlTransient
    public Collection<MarketQuote> getMarketQuotes() {
        return marketQuotes;
    }

    public void setMarketQuotes(Collection<MarketQuote> marketQuotes) {
        this.marketQuotes = marketQuotes;
    }

    public Collection<Product> getParentProducts() {
        return parentProducts;
    }

    public void setParentProducts(Collection<Product> parentProducts) {
        this.parentProducts = parentProducts;
    }

    @Override
    public Product getProduct() {
        return this;
    }

    @Override
    public void setProduct(Product product) {
    }

    @Override
    public Integer getId() {
        return this.productId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    @Override
    public Date getValueDate() {
        return this.startDate;
    }

    @Override
    public BigDecimal getQuantity(Date valDate) {
        return BigDecimal.ONE;
    }

    public void setId(int idProduct) {
        this.productId = idProduct;
    }

    @Override
    public LegalEntity getInternalCounterparty() {
        return null;
    }

    @Override
    public LegalEntity getCounterparty() {
        return null;
    }

    public Collection<MarketDataCode> getMarketDataCodes() {
        return marketDataCodes;
    }

    public void setMarketDataCodes(Collection<MarketDataCode> marketDataCodes) {
        this.marketDataCodes = marketDataCodes;
    }

    @Override
    public Agreement getAgreement() {
        return null;
    }

    public Product getUnderlying() {
        if (underlyingProducts != null && !underlyingProducts.isEmpty()) {
            return underlyingProducts.iterator().next().getUnderlying();
        }
        return null;
    }

    public Product getFirstLeg() {
        if (subProducts != null && !subProducts.isEmpty()) {
            return subProducts.iterator().next().getProduct();
        }
        return null;
    }

    public Product getSecondLeg() {
        if (subProducts != null && !subProducts.isEmpty() && subProducts.size() > 1) {
            Iterator<Product> it = subProducts.iterator();
            it.next();
            return it.next();
        }
        return null;
    }

    @Override
    public Integer getAgreementId() {
        return null;
    }

    public Collection<ProductEvent> getProductEvents() {
        return productEvents;
    }

    public void setProductEvents(Collection<ProductEvent> productEvents) {
        this.productEvents = productEvents;
    }

    public BigDecimal getForexRate() {
        return forexRate;
    }

    public void setForexRate(BigDecimal forexRate) {
        this.forexRate = forexRate;
    }

    public void setSubProducts(Collection<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public Collection<ProductEquity> getProductEquities() {
        return productEquities;
    }

    public Collection<ProductRate> getProductRates() {
        return productRates;
    }

    public Collection<ProductForex> getProductForexs() {
        return productForexs;
    }

    public Collection<ProductSingleTraded> getProductSingleTradeds() {
        return productSingleTradeds;
    }

    public Collection<ProductCurve> getProductCurves() {
        return productCurves;
    }

    public Collection<ProductCredit> getProductCredits() {
        return productCredits;
    }

    public Set<ProductUnderlying> getSuperProducts() {
        return superProducts;
    }

    public Short getSettlementDelay() {
        return settlementDelay;
    }

    public void setSettlementDelay(Short settlementDelay) {
        this.settlementDelay = settlementDelay;
    }

    public void addSuperProduct(Product product) {
        ProductUnderlying underlyings = new ProductUnderlying();
        underlyings.setProduct(product);
        underlyings.setUnderlying(this);
        superProducts.add(underlyings);
    }

    public Collection<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Collection<Currency> currencies) {
        this.currencies = currencies;
    }

}
