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
package org.gaia.jade.ontology;

import jade.content.Concept;

/**
 *
 * @author user
 */
public class JadePricingSettingItem implements Concept {

    
    public JadePricingSettingItem(){
        super();
    }
    
    public JadePricingSettingItem(Integer pricingSettingItemId, String itemType, String pricingFunction, String currency, String productType, String tradeFilterName, Integer productUnderlyingId, String itemValue, Integer itemValueId) {
        this.pricingSettingItemId = pricingSettingItemId;
        this.itemType = itemType;
        this.pricingFunction = pricingFunction;
        this.currency = currency;
        this.productType = productType;
        this.tradeFilterName = tradeFilterName;
        this.productUnderlyingId = productUnderlyingId;
        this.itemValue = itemValue;
        this.itemValueId = itemValueId;
    }
    private Integer pricingSettingItemId;
    private String itemType;
    private String pricingFunction;
    private String currency;
    private String productType;
    private String tradeFilterName;
    private Integer productUnderlyingId;
    private String itemValue;
    private Integer itemValueId;

    
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

    public Integer getItemValueId() {
        return itemValueId;
    }

    public void setItemValueId(Integer itemValueId) {
        this.itemValueId = itemValueId;
    }
}
