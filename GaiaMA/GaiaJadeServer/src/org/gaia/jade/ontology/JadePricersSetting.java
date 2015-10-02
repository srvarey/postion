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
import java.io.Serializable;

/**
 *
 * @author user
 */
public class JadePricersSetting implements Serializable, Concept {

    private Integer pricersSettingId;
    private String productType;
    private String tradeFilterName;
    private String measureGroup;
    private String pricer;

    public JadePricersSetting() {
        super();
    }

    public JadePricersSetting(Integer pricersSettingId, String productType, String tradeFilterName, String measureGroup, String pricer) {
        this.pricersSettingId = pricersSettingId;
        this.productType = productType;
        this.tradeFilterName = tradeFilterName;
        this.measureGroup = measureGroup;
        this.pricer = pricer;
    }

    public Integer getPricersSettingId() {
        return pricersSettingId;
    }

    public void setPricersSettingId(Integer pricersSettingId) {
        this.pricersSettingId = pricersSettingId;
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

    public String getMeasureGroup() {
        return measureGroup;
    }

    public void setMeasureGroup(String measureGroup) {
        this.measureGroup = measureGroup;
    }

    public String getPricer() {
        return pricer;
    }

    public void setPricer(String pricer) {
        this.pricer = pricer;
    }
}
