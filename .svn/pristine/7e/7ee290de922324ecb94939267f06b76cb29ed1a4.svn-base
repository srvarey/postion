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
package org.gaia.dao.pricing.pricers.isda;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Benjamin Frerejean
 */
public class TDateInterval extends Structure  implements Cloneable {
//    public static class ByValue extends TDateInterval implements Structure.ByValue { }
    public List getFieldOrder(){
        return Arrays.asList(new String[] { "prd","prd_typ","flag" });
    }
    public TDateInterval(){
    }

    public TDateInterval(int prd,char prd_typ){
        this.prd_typ=prd_typ;
        this.prd_typ=prd_typ;
    }
    public int prd;
    public char prd_typ;
    public int flag;

    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
