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

import com.sun.jna.NativeLong;

/**
 *
 * @author Benjamin Frerejean
 */
public class IsdaInputCurve {


           public NativeLong valueDate; /* (I) Value date                       */
           public String instrNames; /* (I) Array of 'M' or 'S'              */
           public NativeLong[] dates; /* (I) Array of swaps dates             */
           public double[] rates; /* (I) Array of swap rates              */
           public NativeLong nInstr; /* (I) Number of benchmark instruments  */
           public NativeLong mmDCC; /* (I) DCC of MM instruments            */
           public NativeLong fixedSwapFreq; /* (I) Fixed leg freqency               */
           public NativeLong floatSwapFreq; /* (I) Floating leg freqency            */
           public NativeLong fixedSwapDCC; /* (I) DCC of fixed leg                 */
           public NativeLong floatSwapDCC; /* (I) DCC of floating leg              */
           public NativeLong irbadDayConv; /* (I) Bad day convention               */
           public String holidayFile; /* (I) Holiday file                     */

    public IsdaInputCurve(NativeLong valueDate, String instrNames, NativeLong[] dates, double[] rates, NativeLong nInstr, NativeLong mmDCC, NativeLong fixedSwapFreq, NativeLong floatSwapFreq, NativeLong fixedSwapDCC, NativeLong floatSwapDCC, NativeLong irbadDayConv, String holidayFile) {
        this.valueDate = valueDate;
        this.instrNames = instrNames;
        this.dates = dates;
        this.rates = rates;
        this.nInstr = nInstr;
        this.mmDCC = mmDCC;
        this.fixedSwapFreq = fixedSwapFreq;
        this.floatSwapFreq = floatSwapFreq;
        this.fixedSwapDCC = fixedSwapDCC;
        this.floatSwapDCC = floatSwapDCC;
        this.irbadDayConv = irbadDayConv;
        this.holidayFile = holidayFile;
    }



}
