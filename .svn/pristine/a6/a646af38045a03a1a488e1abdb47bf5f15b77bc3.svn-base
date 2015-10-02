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
package org.gaia.simulationService.ontology;

import jade.content.AgentAction;
import java.io.Serializable;

/**
 *
 * @author Beng
 */
public class ReportObjectNotification implements Serializable, AgentAction {

    private Class objectType;
    private Integer id;
    private Boolean isNew;
    private Boolean isCancel;
    private String fromDate;
    private Integer positionConfigurationId;

    public ReportObjectNotification(Class objectType, Integer id, Boolean isNew,  Boolean isCancel, String fromDate, Integer positionConfigurationId) {
        this.objectType = objectType;
        this.id = id;
        this.isNew = isNew;
        this.isCancel = isCancel;
        this.fromDate = fromDate;
        this.positionConfigurationId = positionConfigurationId;
    }

    public Class getObjectType() {
        return objectType;
    }

    public void setObjectType(Class objectType) {
        this.objectType = objectType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean isCancel() {
        return isCancel;
    }

    public void setIsCancel(Boolean isCancel) {
        this.isCancel = isCancel;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public Integer getPositionConfigurationId() {
        return positionConfigurationId;
    }

    public void setPositionConfigurationId(Integer positionConfigurationId) {
        this.positionConfigurationId = positionConfigurationId;
    }

    @Override
    public String toString(){
        String str="ReportObjectNotification ";
        if (objectType!=null){
            str+=objectType.getName();
        }
        if (positionConfigurationId!=null){
            str+=" pos id="+positionConfigurationId;
        }
        if (fromDate!=null){
            str+=" from  "+fromDate;
        }
        return str;

    }
}
