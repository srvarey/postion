/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gaia.domain.trades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.utils.DomainUtils;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "product_event")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Audited
@NamedQueries({
    @NamedQuery(name = "ProductEvent.findAll", query = "SELECT p FROM ProductEvent p"),
    @NamedQuery(name = "ProductEvent.findByProductEventId", query = "SELECT p FROM ProductEvent p WHERE p.productEventId = :productEventId"),
    @NamedQuery(name = "ProductEvent.findByEffectiveDate", query = "SELECT p FROM ProductEvent p WHERE p.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "ProductEvent.findByEventClassName", query = "SELECT p FROM ProductEvent p WHERE p.eventClassName = :eventClassName"),
    @NamedQuery(name = "ProductEvent.findByEventId", query = "SELECT p FROM ProductEvent p WHERE p.eventId = :eventId")})
public class ProductEvent implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_event_id")
    private Integer productEventId;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "event_class_name")
    private String eventClassName;
    @Column(name = "event_id")
    private Integer eventId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_event_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AuditMappedBy(mappedBy = "productEvent")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductEventDetail> productEventDetails;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "trade_id", referencedColumnName = "trade_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Trade trade;

    public ProductEvent() {
    }

    public ProductEvent(Integer productEventId) {
        this.productEventId = productEventId;
    }

    public ProductEvent(Product product, Date effectiveDate, String eventType, String eventClassName, Integer eventId) {
        this.product = product;
        this.effectiveDate = effectiveDate;
        this.eventType = eventType;
        this.eventClassName = eventClassName;
        this.eventId = eventId;
        this.productEventDetails = new ArrayList();
    }

    public Integer getProductEventId() {
        return productEventId;
    }

    public void setProductEventId(Integer productEventId) {
        this.productEventId = productEventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public void setEventClassName(String eventClassName) {
        this.eventClassName = eventClassName;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @XmlTransient
    public List<ProductEventDetail> getProductEventDetails() {
        return productEventDetails;
    }

    public void setProductEventDetails(List<ProductEventDetail> productEventDetails) {
        this.productEventDetails = productEventDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productEventId != null ? productEventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductEvent)) {
            return false;
        }
        ProductEvent other = (ProductEvent) object;
        if ((this.productEventId == null && other.productEventId != null) || (this.productEventId != null && !this.productEventId.equals(other.productEventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductEvent " + productEventId + StringUtils.SPACE + eventType +" on " + product.getProductId()+" on "+DomainUtils.dateFormat.format(effectiveDate);
    }

    @Override
    public ProductEvent clone() {
        ProductEvent myClone = null;
        try {
            myClone = (ProductEvent) super.clone();
            myClone.setProduct(null);
            myClone.setProductEventId(productEventId != null ? null : productEventId);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }

    public Integer getId() {
        return eventId;
    }

    public Date getValueDate() {
        return effectiveDate;
    }

    public BigDecimal getQuantity(Date valDate) {
        if (trade!=null){
            return trade.getQuantity();
        }
        return BigDecimal.ZERO;
    }

}
