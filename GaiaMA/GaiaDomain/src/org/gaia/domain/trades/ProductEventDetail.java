/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gaia.domain.trades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "product_event_detail")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Audited
@NamedQueries({
    @NamedQuery(name = "ProductEventDetail.findAll", query = "SELECT p FROM ProductEventDetail p"),
    @NamedQuery(name = "ProductEventDetail.findByProductEventDetailId", query = "SELECT p FROM ProductEventDetail p WHERE p.productEventDetailId = :productEventDetailId"),
    @NamedQuery(name = "ProductEventDetail.findByProductColumn", query = "SELECT p FROM ProductEventDetail p WHERE p.productColumn = :productColumn"),
    @NamedQuery(name = "ProductEventDetail.findByProductValue", query = "SELECT p FROM ProductEventDetail p WHERE p.productValue = :productValue"),
    @NamedQuery(name = "ProductEventDetail.findByParam", query = "SELECT p FROM ProductEventDetail p WHERE p.param = :param")})
public class ProductEventDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_event_detail_id")
    private Integer productEventDetailId;
    @Column(name = "product_column")
    private String productColumn;
    @Column(name = "product_value")
    private String productValue;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "param")
    private String param;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_event_id", referencedColumnName = "product_event_id")
    private ProductEvent productEvent;

    public ProductEventDetail() {
    }

    public ProductEventDetail(Integer productEventDetailId) {
        this.productEventDetailId = productEventDetailId;
    }

    public ProductEventDetail(String productColumn, String productValue,String oldValue,String param ) {
        this.productColumn=productColumn;
        this.productValue=productValue;
        this.oldValue=oldValue;
        this.param=param;
    }

    public Integer getProductEventDetailId() {
        return productEventDetailId;
    }

    public void setProductEventDetailId(Integer productEventDetailId) {
        this.productEventDetailId = productEventDetailId;
    }

    public String getProductColumn() {
        return productColumn;
    }

    public void setProductColumn(String productColumn) {
        this.productColumn = productColumn;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public ProductEvent getProductEvent() {
        return productEvent;
    }

    public void setProductEvent(ProductEvent productEvent) {
        this.productEvent = productEvent;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productEventDetailId != null ? productEventDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductEventDetail)) {
            return false;
        }
        ProductEventDetail other = (ProductEventDetail) object;
        if ((this.productEventDetailId == null && other.productEventDetailId != null) || (this.productEventDetailId != null && !this.productEventDetailId.equals(other.productEventDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str= "ProductEventDetail " + productEventDetailId;
        if (productColumn!=null){
            str+=StringUtils.SPACE+productColumn;
        }if (oldValue!=null){
            str+=" : "+oldValue;
        }if (productValue!=null){
            str+="=>"+productValue;
        }
        return str;
    }

}
