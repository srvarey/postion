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
package org.gaia.dao.utils;

/**
 *
 * @author Benjamin Frerejean
 */
public class DateInterval {

       public int prd;        /* number of periods from offset date       */

       public char prd_typ;   /* type of periods                          */

        /** D - day; M - month; W - week                        */
        /** Q - 3 months; S - 6 months                          */
        /** A - 12 months; Y - 12 months                        */
        /** I - quarterly IMM period                            */
        /** F - flexible end of month                           */
        /** E - end of month                                    */
        /** J - monthly IMM period                              */
        /** K - quarterly Australian futures period             */
        /** L - quarterly New Zealand (kiwi) futures period     */
        /** T - equity derivatives expiry - 3rd Friday monthly  */
        /** U - Lunar (i.e. 28 Day) period                      */

        public int flag;       /** 0 - offset is value date
         -1 - offset is the previous date in the date array
         x - any other number is index into array of intervals.
         the date at that location is an offset */


        public DateInterval(){

        }

        public DateInterval(int prd, char prd_typ, int flag) {
            this.prd = prd;
            this.prd_typ = prd_typ;
            this.flag = flag;
        }



}
