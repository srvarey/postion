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
package org.gaia.dao.jade;

import jade.content.Concept;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.gaia.dao.pricing.DAOGreekSetting;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.NPVArrayDimension;
import org.gaia.domain.observables.PricingEnvironment;

/**
 * @author Benjamin Frerejean
 */
public class PricingSetting implements Serializable,Concept,Cloneable{

    private Date valuationDate;
    private PricingEnvironment pricingEnvironment;
    private List Measures2D;
    private List ListIds2D;
    private Boolean isRealTime;
    private List<DAOGreekSetting> greeksList;
    private MeasuresAccessor.MeasureGroup[] measureGroups;
    private List<NPVArrayDimension> npvArrayDimensions;

    public PricingSetting(){
        super();
    }

    public PricingSetting(PricingEnvironment pricingEnvironment, Date valuationDate,List Measures2D,List ListIds2D,Boolean isRealTime,List<DAOGreekSetting> greeksList, MeasuresAccessor.MeasureGroup[] measureGroups, List<NPVArrayDimension> npvArrayDimensions){

        this.valuationDate=valuationDate;
        this.pricingEnvironment=pricingEnvironment;
        this.isRealTime=isRealTime;
        this.greeksList=greeksList;
        this.Measures2D=Measures2D;
        this.ListIds2D=ListIds2D;
        this.measureGroups=measureGroups;
        this.npvArrayDimensions=npvArrayDimensions;
    }

    public PricingEnvironment getPricingEnvironment(){
        return pricingEnvironment;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
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

    public List<DAOGreekSetting> getGreeksList() {
        return greeksList;
    }

    public MeasureGroup[] getMeasureGroups() {
        return measureGroups;
    }

    public void setMeasureGroups(MeasureGroup[] measureGroups) {
        this.measureGroups = measureGroups;
    }

    public List<NPVArrayDimension> getNpvArrayDimensions() {
        return npvArrayDimensions;
    }

    public void setNpvArrayDimensions(List<NPVArrayDimension> npvArrayDimensions) {
        this.npvArrayDimensions = npvArrayDimensions;
    }

    @Override
    public PricingSetting clone(){
         PricingSetting object = null;
        try {
            object = (PricingSetting)super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return object;
    }

}
