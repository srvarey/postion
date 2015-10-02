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
package org.gaia.dao.reports;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
/**
 *
 * @author Benjamin Frerejean
 */
public class ReportLine implements Serializable{

    private int id;
    private Map<String, Object> objectMap=new HashMap();
    private Map<String, Object> objectMapFirst=new HashMap();
    private Object lineData;
    private boolean isToRemove=false;
    private ReportLine parentLine;

    /**
     * Get the value of objectMap
     *
     * @return the value of objectMap
     */
    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    /**
     * Set the value of objectMap
     *
     * @param objectMap new value of objectMap
     */
    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }


    public Map<String, Object> getObjectMapFirst() {
        return objectMapFirst;
    }

    public void setObjectMapFirst(Map<String, Object> objectMapFirst) {
        this.objectMapFirst = objectMapFirst;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */

    /**
     * get the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Object getLineData() {
        return lineData;
    }

    public void setLineData(Object lineData) {
        this.lineData = lineData;
    }

    public boolean isToRemove() {
        return isToRemove;
    }

    public void setIsToRemove(boolean isToRemove) {
        this.isToRemove = isToRemove;
    }

    public ReportLine getParentLine() {
        return parentLine;
    }

    public void setParentLine(ReportLine parentLine) {
        this.parentLine = parentLine;
    }


    @Override
    public String toString(){
        String ret= "Line "+id;
        if (lineData instanceof Position){
            ret+=StringUtils.SPACE+((Position)lineData).toString();
        } else if (lineData instanceof Trade){
            ret+=StringUtils.SPACE+((Trade)lineData).toString();
        } else if (lineData instanceof LegalEntity){
            ret+=StringUtils.SPACE+((LegalEntity)lineData).toString();
        }
        return ret;
    }
}