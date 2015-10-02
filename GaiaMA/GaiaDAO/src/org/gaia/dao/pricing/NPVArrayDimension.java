/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.pricing;

import java.io.Serializable;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.AbstractObservable.ObservableType;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.Stress;

/**
 *
 * @author Benjamin Frerejean
 */

/**
 *
 * @author Benjamin Frerejean
 */
public class NPVArrayDimension implements Serializable{

    private AbstractObservable.ObservableType observableType;
    private String observableName;
    private Integer observableId;
    private Integer middle=Integer.valueOf(0);
    private Integer max=Integer.valueOf(1);
    private Boolean isAbsolute=Boolean.TRUE;
    private Stress stress ;

    public ObservableType getObservableType() {
        return observableType;
    }

    public void setObservableType(ObservableType observableType) {
        this.observableType = observableType;
    }

    public String getObservableName() {
        return observableName;
    }

    public void setObservableName(String observableName) {
        this.observableName = observableName;
    }

    public Integer getObservableId() {
        return observableId;
    }

    public void setObservableId(Integer observableId) {
        this.observableId = observableId;
    }

    public Stress getStress() {
        return stress;
    }

    public void setStress(Stress stress) {
        this.stress = stress;
    }


    public Integer getMin() {
        return -1*middle;
    }

    public void setMin(Integer min) {
        this.middle = -1*min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMiddle() {
        return middle;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean isAbsolute() {
        return isAbsolute;
    }

    public Boolean isBusDaysTimeShift() {
        return isAbsolute;
    }

    public void setIsAbsolute(Boolean isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    @Override
    public String toString(){
        String str=observableType.name;
        if (observableName!=null){
            str+=StringUtils.DOT+observableName;
        }
        if (stress!=null){
            str+=StringUtils.DOT+stress.shiftsDesc();
        }
        return str;
    }
}
