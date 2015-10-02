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
public class TInterpData {
                                         /* interpolation data type */
   TDateInterval   interval;            /* linear FWDs: length of forward */
   long            dayCountConv;        /*    ""   ""   day counting conv */
   long            basis;               /*    ""   ""   compounding basis */
   Date[]    badDayList;          /*    ""   ""   bad day list */
   TDateInterval[]   addPointsIntvls;  /* intervals to add points between
                                         * start,1y,2y,5y,10y,end */
   boolean        enableGeneration;    /* enable generation of zc points
                                         * at coupon dates */


   public TInterpData(){
       addPointsIntvls=new  TDateInterval[5] ;
   }

    public TDateInterval getInterval() {
        return interval;
    }

    public void setInterval(TDateInterval interval) {
        this.interval = interval;
    }

    public long getDayCountConv() {
        return dayCountConv;
    }

    public void setDayCountConv(long dayCountConv) {
        this.dayCountConv = dayCountConv;
    }

    public long getBasis() {
        return basis;
    }

    public void setBasis(long basis) {
        this.basis = basis;
    }

    public Date[] getBadDayList() {
        return badDayList;
    }

    public void setBadDayList(Date[] badDayList) {
        this.badDayList = badDayList;
    }

    public TDateInterval[] getAddPointsIntvls() {
        return addPointsIntvls;
    }

    public void setAddPointsIntvls(TDateInterval[] addPointsIntvls) {
        this.addPointsIntvls = addPointsIntvls;
    }

    public boolean isEnableGeneration() {
        return enableGeneration;
    }

    public void setEnableGeneration(boolean enableGeneration) {
        this.enableGeneration = enableGeneration;
    }



}
