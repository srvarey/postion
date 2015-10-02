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
package org.gaia.dao.observables;

import jade.util.leap.ArrayList;
import java.util.Iterator;
import org.gaia.jade.ontology.JadePricersSetting;
import org.gaia.jade.ontology.JadePricingSettingItem;


/**
 *
 * @author Benjamin
 */

public class PricingEnvironmentMapper {

    public static org.gaia.jade.ontology.JadePricingEnvironment mapToJade(org.gaia.domain.observables.PricingEnvironment pricingEnvironment){
        org.gaia.jade.ontology.JadePricingEnvironment jadePricingEnvironment=new org.gaia.jade.ontology.JadePricingEnvironment();
        jadePricingEnvironment.setPricingSettingItemCollection(new ArrayList());

        jadePricingEnvironment.setName(pricingEnvironment.getName());
        jadePricingEnvironment.setPricingEnvironmentId(pricingEnvironment.getPricingEnvironmentId());

        jade.util.leap.List outPricersSettingList=new ArrayList();
        Iterator<org.gaia.domain.observables.PricersSetting> it=pricingEnvironment.getPricersSettingCollection().iterator();
        while(it.hasNext())
        {
            org.gaia.domain.observables.PricersSetting pricersSetting =(org.gaia.domain.observables.PricersSetting)it.next();
            org.gaia.jade.ontology.JadePricersSetting outPricersSetting=new org.gaia.jade.ontology.JadePricersSetting();
            outPricersSetting.setMeasureGroup(pricersSetting.getMeasureGroup());
            outPricersSetting.setPricer(pricersSetting.getPricer());
            outPricersSetting.setPricersSettingId(pricersSetting.getPricersSettingId());
            outPricersSetting.setProductType(pricersSetting.getProductType());
            outPricersSetting.setTradeFilterName(pricersSetting.getTradeFilterName());
            outPricersSettingList.add(outPricersSetting);
        }
        jadePricingEnvironment.setPricersSettingCollection(outPricersSettingList);

        jade.util.leap.List outPricingSettingItemList=new ArrayList();
        Iterator<org.gaia.domain.observables.PricingSettingItem> it2=pricingEnvironment.getPricingSettingItemCollection().iterator();
        while(it2.hasNext())
        {
            org.gaia.domain.observables.PricingSettingItem pricingSettingItem=(org.gaia.domain.observables.PricingSettingItem) it2.next();
            org.gaia.jade.ontology.JadePricingSettingItem outPricingSettingItem=new org.gaia.jade.ontology.JadePricingSettingItem();
            outPricingSettingItem.setCurrency(pricingSettingItem.getCurrency());
            outPricingSettingItem.setItemType(pricingSettingItem.getItemType());
            outPricingSettingItem.setItemValue(pricingSettingItem.getItemValue());
            outPricingSettingItem.setItemValueId(pricingSettingItem.getItemValueId());
            outPricingSettingItem.setPricingFunction(pricingSettingItem.getPricingFunction());
            outPricingSettingItem.setPricingSettingItemId(pricingSettingItem.getPricingSettingItemId());
            outPricingSettingItem.setProductType(pricingSettingItem.getProductType());
            outPricingSettingItem.setProductUnderlyingId(pricingSettingItem.getProductUnderlyingId());
            outPricingSettingItem.setTradeFilterName(pricingSettingItem.getTradeFilterName());
            outPricingSettingItemList.add(outPricingSettingItem);
        }
        jadePricingEnvironment.setPricingSettingItemCollection(outPricingSettingItemList);



        return jadePricingEnvironment;
    }


    public static org.gaia.domain.observables.PricingEnvironment mapFromJade(org.gaia.jade.ontology.JadePricingEnvironment jadePricingEnvironment){
        org.gaia.domain.observables.PricingEnvironment pricingEnvironment=new org.gaia.domain.observables.PricingEnvironment();

        pricingEnvironment.setPricingSettingItemCollection(new java.util.ArrayList());
        pricingEnvironment.setName(pricingEnvironment.getName());
        pricingEnvironment.setPricingEnvironmentId(pricingEnvironment.getPricingEnvironmentId());

        java.util.List<org.gaia.domain.observables.PricersSetting> outPricersSettingList=new java.util.ArrayList();
        for (int i=0;i<jadePricingEnvironment.getPricersSettingCollection().size();i++)
        {
            JadePricersSetting pricersSetting =(JadePricersSetting) jadePricingEnvironment.getPricersSettingCollection().get(i);
            org.gaia.domain.observables.PricersSetting outPricersSetting=new org.gaia.domain.observables.PricersSetting();
            outPricersSetting.setMeasureGroup(pricersSetting.getMeasureGroup());
            outPricersSetting.setPricer(pricersSetting.getPricer());
            outPricersSetting.setPricersSettingId(pricersSetting.getPricersSettingId());
            outPricersSetting.setProductType(pricersSetting.getProductType());
            outPricersSetting.setTradeFilterName(pricersSetting.getTradeFilterName());
            outPricersSettingList.add(outPricersSetting);
        }
        pricingEnvironment.setPricersSettingCollection(outPricersSettingList);

        java.util.List<org.gaia.domain.observables.PricingSettingItem> outPricingSettingItemList=new java.util.ArrayList();
        for (int i=0;i<jadePricingEnvironment.getPricingSettingItemCollection().size();i++)
        {
            JadePricingSettingItem pricingSettingItem=(JadePricingSettingItem) jadePricingEnvironment.getPricingSettingItemCollection().get(i);
            org.gaia.domain.observables.PricingSettingItem outPricingSettingItem=new org.gaia.domain.observables.PricingSettingItem();
            outPricingSettingItem.setCurrency(pricingSettingItem.getCurrency());
            outPricingSettingItem.setItemType(pricingSettingItem.getItemType());
            outPricingSettingItem.setItemValue(pricingSettingItem.getItemValue());
            outPricingSettingItem.setItemValueId(pricingSettingItem.getItemValueId());
            outPricingSettingItem.setPricingFunction(pricingSettingItem.getPricingFunction());
            outPricingSettingItem.setPricingSettingItemId(pricingSettingItem.getPricingSettingItemId());
            outPricingSettingItem.setProductType(pricingSettingItem.getProductType());
            outPricingSettingItem.setProductUnderlyingId(pricingSettingItem.getProductUnderlyingId());
            outPricingSettingItem.setTradeFilterName(pricingSettingItem.getTradeFilterName());
            outPricingSettingItemList.add(outPricingSettingItem);
        }
        pricingEnvironment.setPricingSettingItemCollection(outPricingSettingItemList);


        return pricingEnvironment;
    }
}
