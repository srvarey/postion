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
package org.gaia.dao.referentials;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Benjamin Frerejean
 */
public class FrequencyUtil {

    public static final BigDecimal oneDay=new BigDecimal(1/365);
    public static final BigDecimal oneWeek=new BigDecimal(7/365);
    public static final BigDecimal twoWeek=new BigDecimal(14/365);
    public static final BigDecimal threeWeek=new BigDecimal(21/365);
    public static final BigDecimal fourWeek=new BigDecimal(28/365);
    public static final BigDecimal oneMonth=new BigDecimal(1/12);
    public static final BigDecimal twoMonth=new BigDecimal(2/12);
    public static final BigDecimal threeMonth=new BigDecimal(3/12);
    public static final BigDecimal fourMonth=new BigDecimal(4/12);
    public static final BigDecimal fiveMonth=new BigDecimal(5/12);
    public static final BigDecimal sixMonth=new BigDecimal(6/12);
    public static final BigDecimal sevenMonth=new BigDecimal(7/12);
    public static final BigDecimal heightMonth=new BigDecimal(8/12);
    public static final BigDecimal nineMonth=new BigDecimal(9/12);
    public static final BigDecimal tenMonth=new BigDecimal(10/12);
    public static final BigDecimal elevenMonth=new BigDecimal(11/12);

    /**
     * list of frequency of a coupon
     */
    public enum Frequency {

        QUARTERLY("Quarterly", Tenor.TENOR_3M.name),
        MONTHLY("Monthly", Tenor.TENOR_1M.name),
        ANNUAL("Annual", Tenor.TENOR_1Y.name),
        SEMIANNUAL("Semi-Annual", Tenor.TENOR_6M.name),
        DAILY("Daily", Tenor.TENOR_1D.name),
        WEEKLY("Weekly", Tenor.TENOR_1W.name),
        ZEROCOUPON("Zero-Coupon", Tenor.NONE.name),
        MOON("Moon", "28D");
        public final String name;
        public String tenor;

        Frequency(String name, String tenor) {
            this.name = name;
            this.tenor = tenor;
        }
    }

    public enum Tenor {

        TENOR_1D("1D",oneDay),
//        TENOR_1W("1W",oneWeek),
////        TENOR_1M("1M",oneMonth),
//        TENOR_3M("3M",threeMonth),
//        TENOR_6M("6M",sixMonth),
//        TENOR_1Y("1Y",BigDecimal.ONE),
//        NONE("NONE",BigDecimal.ZERO);
        TENOR_TN("TN",oneDay),
        TENOR_1W("1W",oneWeek),
        TENOR_2W("2W",twoWeek),
        TENOR_3W("3W",threeWeek),
        TENOR_1M("1M",oneMonth),
        TENOR_2M("2M",twoMonth),
        TENOR_3M("3M",threeMonth),
        TENOR_4M("4M",fourMonth),
        TENOR_5M("5M",fiveMonth),
        TENOR_6M("6M",sixMonth),
        TENOR_7M("7M",sevenMonth),
        TENOR_8M("8M",heightMonth),
        TENOR_9M("9M",nineMonth),
        TENOR_10M("10M",tenMonth),
        TENOR_11M("11M",elevenMonth),
        TENOR_1Y("1Y",BigDecimal.ONE),
        TENOR_2Y("2Y",new BigDecimal(2)),
        TENOR_3Y("3Y",new BigDecimal(3)),
        TENOR_4Y("4Y",new BigDecimal(4)),
        TENOR_5Y("5Y",new BigDecimal(5)),
        TENOR_6Y("6Y",new BigDecimal(6)),
        TENOR_7Y("7Y",new BigDecimal(7)),
        TENOR_8Y("8Y",new BigDecimal(8)),
        TENOR_9Y("9Y",new BigDecimal(9)),
        TENOR_10Y("10Y",new BigDecimal(10)),
        TENOR_15Y("15Y",new BigDecimal(15)),
        TENOR_20Y("20Y",new BigDecimal(20)),
        NONE("NONE",BigDecimal.ZERO);

        public final String name;
        public final BigDecimal noYears;

        Tenor(String name,BigDecimal noYears) {
            this.name = name;
            this.noYears=noYears;
        }

        public static Tenor getTenor(String name) {
            for (int i = 0; i < Tenor.values().length; i++) {
                if (Tenor.values()[i].name.equalsIgnoreCase(name)) {
                    return Tenor.values()[i];
                }
            }
            return null;
        }
    }

    /**
     * retrieve the tenor from coupon relative to the frequency
     */
    public static String getTenorFromFrequency(String frequency) {
        for (Frequency frequency_ : Frequency.values()) {
            if (frequency_.name.equals(frequency_.name)) {
                return frequency_.tenor;
            }
        }
        return null;
    }

    /**
     * retrieve the frequency from coupon relative to the tenor
     */
    public static String getFrequencyFromTenor(String tenor) {
        for (Frequency frequency : Frequency.values()) {
            if (frequency.tenor.equals(tenor)) {
                return frequency.name;
            }
        }
        return null;
    }

    /**
     * retrieve list of frequencies
     */
    public static List<String> getFrequencies() {
        ArrayList<String> frequencies = new ArrayList();
        for (int i = 0; i < Frequency.values().length; i++) {
            frequencies.add(Frequency.values()[i].name);
        }
        return frequencies;
    }

    /**
     * retrieve list of string tenors
     */
    public static List<String> getTenors() {
        ArrayList<String> tenors = new ArrayList();
        for (int i = 0; i < Tenor.values().length; i++) {
            tenors.add(Tenor.values()[i].name);
        }
        return tenors;
    }


    public class TenorComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof Tenor && o2 instanceof Tenor) {
                Tenor t1 = (Tenor) o1;
                Tenor t2 = (Tenor) o2;
                return t1.noYears.subtract(t2.noYears).multiply(new BigDecimal("365")).intValue();
            }
            return 0;
        }
    }
}
