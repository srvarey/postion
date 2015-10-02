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
package org.gaia.dao.pricing;

import java.io.Serializable;
import org.gaia.domain.observables.GreekSetting;

/**
 *
 * @author Benjamin Frerejean
 */


public class StoredGreek implements IPricerMeasure, Serializable{

    private final GreekSetting myGreekSetting;
    private final boolean isUnit;

    public StoredGreek(GreekSetting myGreekSetting, boolean isUnit){
        this.myGreekSetting=myGreekSetting;
        this.isUnit=isUnit;
    }

    @Override
    public String getName() {
        if (isUnit){
            return myGreekSetting.getPricerMeasure()+MeasuresAccessor.UNIT_POSTFIX;
        }else {
            return myGreekSetting.getPricerMeasure();
        }
    }

    public GreekSetting getMyGreekSetting() {
        return myGreekSetting;
    }

    public boolean isIsUnit() {
        return isUnit;
    }

    @Override
    public MeasuresAccessor.MeasureGroup getGroup() {
        return MeasuresAccessor.MeasureGroup.PV_GROUP;
    }

    @Override
    public IPricerMeasure getLinkedAmountMeasure() {
        if (isUnit){
            return new StoredGreek(myGreekSetting,false);
        } else {
            return null;
        }
    }

    @Override
    public int getDimension() {
        return 2;
    }

    @Override
    public boolean isUnit() {
        return isUnit;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public String toString(){
        return getName();
    }
}
