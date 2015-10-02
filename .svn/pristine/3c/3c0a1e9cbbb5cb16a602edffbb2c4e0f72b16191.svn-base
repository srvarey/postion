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

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "pricing_setting_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PricingSettingItem.findAll", query = "SELECT p FROM PricingSettingItem p"),
    @NamedQuery(name = "PricingSettingItem.findByPricingSettingItemId", query = "SELECT p FROM PricingSettingItem p WHERE p.pricingSettingItemId = :pricingSettingItemId"),
    @NamedQuery(name = "PricingSettingItem.findByItemType", query = "SELECT p FROM PricingSettingItem p WHERE p.itemType = :itemType"),
    @NamedQuery(name = "PricingSettingItem.findByPricingFunction", query = "SELECT p FROM PricingSettingItem p WHERE p.pricingFunction = :pricingFunction"),
    @NamedQuery(name = "PricingSettingItem.findByCurrency", query = "SELECT p FROM PricingSettingItem p WHERE p.currency = :currency"),
    @NamedQuery(name = "PricingSettingItem.findByProductType", query = "SELECT p FROM PricingSettingItem p WHERE p.productType = :productType"),
    @NamedQuery(name = "PricingSettingItem.findByTtradeFilterName", query = "SELECT p FROM PricingSettingItem p WHERE p.tradeFilterName = :productFilterName"),
    @NamedQuery(name = "PricingSettingItem.findByProductUnderlyingId", query = "SELECT p FROM PricingSettingItem p WHERE p.productUnderlyingId = :productUnderlyingId"),
    @NamedQuery(name = "PricingSettingItem.findByItemValue", query = "SELECT p FROM PricingSettingItem p WHERE p.itemValue = :itemValue")})


public class PricingSettingItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pricing_setting_item_id")
    private Integer pricingSettingItemId;
    @Column(name = "item_type")
    private String itemType;
    @Column(name = "pricing_function")
    private String pricingFunction;
    @Column(name = "currency")
    private String currency;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "trade_filter_name")
    private String tradeFilterName;
    @Column(name = "product_underlying_id")
    private Integer productUnderlyingId;
    @Column(name = "item_value")
    private String itemValue;
    @Column(name = "item_value_id")
    private Integer itemValueId;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "pricing_environment_id", referencedColumnName = "pricing_environment_id", insertable = false, updatable = false, nullable=false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private PricingEnvironment pricingEnvironment;


    public PricingSettingItem() {
    }

    public PricingSettingItem(Integer pricerSettingItemId) {
        this.pricingSettingItemId = pricerSettingItemId;
    }

    public Integer getPricingSettingItemId() {
        return pricingSettingItemId;
    }

    public void setPricingSettingItemId(Integer pricingSettingItemId) {
        this.pricingSettingItemId = pricingSettingItemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPricingFunction() {
        return pricingFunction;
    }

    public void setPricingFunction(String pricingFunction) {
        this.pricingFunction = pricingFunction;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTradeFilterName() {
        return tradeFilterName;
    }

    public void setTradeFilterName(String tradeFilterName) {
        this.tradeFilterName = tradeFilterName;
    }

    public Integer getProductUnderlyingId() {
        return productUnderlyingId;
    }

    public void setProductUnderlyingId(Integer productUnderlyingId) {
        this.productUnderlyingId = productUnderlyingId;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public PricingEnvironment getPricingEnvironment() {
        return pricingEnvironment;
    }

    public void setPricingEnvironment(PricingEnvironment pricingEnvironment) {
        this.pricingEnvironment = pricingEnvironment;
    }

    public Integer getItemValueId() {
        return itemValueId;
    }

    public void setItemValueId(Integer itemValueId) {
        this.itemValueId = itemValueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricingSettingItemId != null ? pricingSettingItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PricingSettingItem)) {
            return false;
        }
        PricingSettingItem other = (PricingSettingItem) object;
        if ((this.pricingSettingItemId == null && other.pricingSettingItemId != null) || (this.pricingSettingItemId != null && !this.pricingSettingItemId.equals(other.pricingSettingItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       String str ="PricingSettingItem " + pricingSettingItemId;
       str+=StringUtils.SPACE+itemType;
       str+=StringUtils.SPACE+itemValue;
       str+=" : "+currency;
       str+="/"+productType;
       str+="/"+tradeFilterName;
       return str;
    }

}
