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

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Benjamin Frerejean
 */
public class AggregatorAverage implements IColumnAggregator{

    @Override
    /**
     *  returns a node value.
     */
    public BigDecimal getAggregationValue(int ind,PositionTree.PositionNode node) {
            BigDecimal ret = null;
            if (node.isLeaf()) {
                if (node.getValueAt(ind) instanceof BigDecimal) {
                    ret = (BigDecimal) node.getValueAt(ind);
                }
            } else {
                int count=0;
                /**
                 *  Browse node if is note a leaf .
                 */
                for (int i = 0; i < node.getChildCount(); i++) {
                    BigDecimal val =getAggregationValue(ind,(PositionTree.PositionNode)node.getChildAt(i));
                    if (val!=null){
                        if (ret==null) {
                            ret=val;
                        } else {
                            ret = ret.add(val);
                        }
                        count++;
                    }
                }
                if (node.getData() != null&&count>0) {
                    node.getData()[ind] = ret.divide(BigDecimal.valueOf(count), 20, RoundingMode.HALF_UP);
                }
            }
            return ret;
        }
}
