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
import java.util.List;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.observables.Stress;

/**
 *
 * @author Benjamin Frerejean
 */
public class DAOGreekSetting implements Serializable {

    private final GreekSetting myGreekSetting;

    private String[] underlyingNames;
    private Integer[] underlyingIds;
    private List<Integer> levels;
    private NPVArrayDimension npvArrayDimension;
    private final IPricerMeasure pricerMeasure;
    private final IPricerMeasure movingPricerMeasure;
    private GreekSetting movingGreekSetting = null;
    private DAOGreekSetting daoMovingGreekSetting;

    public DAOGreekSetting(GreekSetting greekSetting, IPricerMeasure measure) {
        this.myGreekSetting = greekSetting;
        IPricerMeasure movingMeasure = MeasuresAccessor.getMeasureByName(greekSetting.getMovingPricerMeasure());
        if (movingMeasure instanceof StoredGreek) {
            this.movingGreekSetting = ((StoredGreek) movingMeasure).getMyGreekSetting();
            if (movingGreekSetting != null) {
                this.daoMovingGreekSetting = new DAOGreekSetting(movingGreekSetting,movingMeasure);
            }
        }
        pricerMeasure = measure;
        movingPricerMeasure = MeasuresAccessor.getMeasureByName(myGreekSetting.getMovingPricerMeasure());
    }

    public IPricerMeasure getPricerMeasure() {
        return pricerMeasure;
    }

    public IPricerMeasure getMovingPricerMeasure() {
        return movingPricerMeasure;
    }

    public DAOGreekSetting getDaoMovingGreekSetting() {
        return daoMovingGreekSetting;
    }

    public GreekSetting getMovingGreekSetting() {
        return movingGreekSetting;
    }

    public String getShifted() {
        return myGreekSetting.getShifted();
    }

    public Stress getStress() {
        return myGreekSetting.getStress();
    }

    public Boolean isAbsolute() {
        return myGreekSetting.isAbsolute();
    }

    public Boolean isInAmount() {
        return myGreekSetting.isInAmount();
    }

    public String[] getUnderlyingNames() {
        return underlyingNames;
    }

    public void setUnderlyingNames(String[] underlyingNames) {
        this.underlyingNames = underlyingNames;
    }

    public Integer[] getUnderlyingIds() {
        return underlyingIds;
    }

    public void setUnderlyingIds(Integer[] underlyingIds) {
        this.underlyingIds = underlyingIds;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }

    public NPVArrayDimension getNpvArrayDimension() {
        return npvArrayDimension;
    }

    public void setNpvArrayDimension(NPVArrayDimension npvArrayDimension) {
        this.npvArrayDimension = npvArrayDimension;
    }

    public void setDaoMovingGreekSetting(DAOGreekSetting daoMovingGreekSetting) {
        this.daoMovingGreekSetting = daoMovingGreekSetting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (myGreekSetting != null ? myGreekSetting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DAOGreekSetting)) {
            return false;
        }
        DAOGreekSetting other = (DAOGreekSetting) object;
        return (this.toString() != null || other.toString() == null) && (this.toString() == null || this.toString().equals(other.toString()));
    }

    public String getName(){
        return myGreekSetting.getPricerMeasure();
    }

    @Override
    public String toString() {
        if (underlyingNames != null && underlyingNames.length > 0) {
            String tostr = myGreekSetting.getPricerMeasure();
            for (String underlyingName : underlyingNames) {
                tostr += StringUtils.DOT + underlyingName;
            }
            if (myGreekSetting != null) {
                tostr += StringUtils.DOT + myGreekSetting.getStress().toString();
            }
            return tostr;
        } else {
            return myGreekSetting.getPricerMeasure();
        }
    }

}
