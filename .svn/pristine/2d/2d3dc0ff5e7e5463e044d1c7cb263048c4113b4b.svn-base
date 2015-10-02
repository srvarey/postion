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

import java.util.Date;

/**
 *
 * @author Benjamin Frerejean
 */
public class TSwapDates {


    private Date[]    adjusted;                 /* Adjusted dates */
    private Date[]    original;                 /* UNadjusted dates */
    private Date[]    previous;                 /* Previous coupon dates */
    private boolean[] onCycle;                  /* T=Count fwd from Val date */
                                       /* F=Count bckwd from Mat date */
    private int       numDates;                 /* Length of all 4 arrays. */

    public TSwapDates(int       numDates){
        this.numDates=numDates;
        adjusted=new Date[numDates];
        original=new Date[numDates];
        previous=new Date[numDates];
        onCycle=new boolean[numDates];
    }

    public Date[] getAdjusted() {
        return adjusted;
    }

    public void setAdjusted(Date[] adjusted) {
        this.adjusted = adjusted;
    }

    public Date[] getOriginal() {
        return original;
    }

    public void setOriginal(Date[] original) {
        this.original = original;
    }

    public Date[] getPrevious() {
        return previous;
    }

    public void setPrevious(Date[] previous) {
        this.previous = previous;
    }

    public boolean[] getOnCycle() {
        return onCycle;
    }

    public void setOnCycle(boolean[] onCycle) {
        this.onCycle = onCycle;
    }

    public int getNumDates() {
        return numDates;
    }

    public void setNumDates(int numDates) {
        this.numDates = numDates;
    }


}
