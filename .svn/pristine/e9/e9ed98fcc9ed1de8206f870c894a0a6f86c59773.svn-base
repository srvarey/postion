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
package org.gaia.dao.pricing.pricers;

import java.util.ArrayList;
import java.util.List;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.utils.Mapping;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricersUtils {



    public static final String GET_PRICERS="getPricers";
    public static List<String> getPricers(){
        ArrayList<String> res =new ArrayList();
        List<Mapping> maps =MappingsAccessor.getMappingsByName("pricers");
        for (Mapping m: maps){
            res.add(m.getKey1());
        }
        return res;
    }

    public static List<String> getPricingGroups(){
        ArrayList<String> res =new ArrayList();
        MeasureGroup[] groups = MeasureGroup.values();
        for (MeasureGroup mg: groups){
            res.add(mg.name());
        }
        return res;
    }


}
