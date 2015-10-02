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
import jade.util.leap.ArrayList;
import jade.util.leap.List;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author user
 */
public class PricingSetting implements Serializable,Concept{
    
    private Date valuationDate;
    private Integer pricingEnvironmentId;
    private String name;
    private List pricingSettingItemCollection;
    private List pricersSettingCollection;
    private List Measures2D;
    private List ListIds2D;
    private Boolean isRealTime;

    public PricingSetting(){
        super();
    }
    
    public PricingSetting(JadePricingEnvironment pricingEnv, Date valuationDate,List Measures2D,List ListIds2D,Boolean isRealTime){
        
        this.valuationDate=valuationDate;
        this.pricingEnvironmentId=pricingEnv.getPricingEnvironmentId();
        this.name=pricingEnv.getName();
        this.isRealTime=isRealTime;
        
        this.pricingSettingItemCollection=new ArrayList();
        for (int i=0;i<pricingEnv.getPricingSettingItemCollection().size();i++){
            JadePricingSettingItem pricingSettingItem = (JadePricingSettingItem)pricingEnv.getPricingSettingItemCollection().get(i);
            this.pricingSettingItemCollection.add(new JadePricingSettingItem( pricingSettingItem.getItemValueId(), pricingSettingItem.getItemType(),  pricingSettingItem.getPricingFunction(), pricingSettingItem.getCurrency() , pricingSettingItem.getProductType(), pricingSettingItem.getTradeFilterName(), pricingSettingItem.getProductUnderlyingId(),pricingSettingItem.getItemValue(),pricingSettingItem.getItemValueId()));
        }
        
        this.pricersSettingCollection=new ArrayList();
        for (int i=0;i<pricingEnv.getPricersSettingCollection().size();i++){
            JadePricersSetting pricersSetting = (JadePricersSetting)pricingEnv.getPricersSettingCollection().get(i);         
            this.pricersSettingCollection.add(new JadePricersSetting(  pricersSetting.getPricersSettingId(), pricersSetting.getProductType(),pricersSetting.getTradeFilterName(),pricersSetting.getMeasureGroup(),pricersSetting.getPricer())); 
         }
        this.Measures2D=Measures2D;
        this.ListIds2D=ListIds2D;
    }    
    
    public JadePricingEnvironment getPricingEnvironment(){
        JadePricingEnvironment pe=new JadePricingEnvironment();
        pe.setName(name);
        pe.setPricingEnvironmentId(pricingEnvironmentId);
        pe.setPricersSettingCollection(this.pricersSettingCollection);
        pe.setPricingSettingItemCollection(pricingSettingItemCollection);
        return pe;
    }    
    
    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public Integer getPricingEnvironmentId() {
        return pricingEnvironmentId;
    }

    public void setPricingEnvironmentId(Integer pricingEnvironmentId) {
        this.pricingEnvironmentId = pricingEnvironmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getPricingSettingItemCollection() {
        return pricingSettingItemCollection;
    }

    public void setPricingSettingItemCollection(List pricingSettingItemCollection) {
        this.pricingSettingItemCollection = pricingSettingItemCollection;
    }

    public List getPricersSettingCollection() {
        return pricersSettingCollection;
    }

    public void setPricersSettingCollection(List pricersSettingCollection) {
        this.pricersSettingCollection = pricersSettingCollection;
    }

    public List getMeasures2D() {
        return Measures2D;
    }

    public void setMeasures2D(List Measures2D) {
        this.Measures2D = Measures2D;
    }

    public List getListIds2D() {
        return ListIds2D;
    }

    public void setListIds2D(List ListIds2D) {
        this.ListIds2D = ListIds2D;
    }

    public Boolean getIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(Boolean isRealTime) {
        this.isRealTime = isRealTime;
    }
    
    
}
