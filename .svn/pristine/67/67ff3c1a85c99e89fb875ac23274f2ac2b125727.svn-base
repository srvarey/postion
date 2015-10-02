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
package org.gaia.dao.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 *
 * @author Benjamin Frerejean
 */
public class NumberUtils {

    private static final Logger logger = Logger.getLogger(NumberUtils.class);

    public static final BigDecimal BIGDECIMAL_100=new BigDecimal("100");

    /**
     * check if the string is an integer
     *
     * @param input
     * @return
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * check if the string is a number
     *
     * @param input
     * @return
     */
    public static boolean isNumber(String input) {
        try {
            if (input.contains(":")) {
                return false;
            }
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            numberFormat.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    static public BigDecimal stringToRate(String value, DecimalFormat formatter) {
        BigDecimal doubleValue = BigDecimal.ZERO;
        if (value.length() == 0) {
            return BigDecimal.ZERO;
        }
        int idx = value.toLowerCase().indexOf("b");
        if (idx > 0) {
            try {
                doubleValue = stringToNumber(value, formatter).divide(new BigDecimal(10000));
                return doubleValue;
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        try {
            doubleValue = stringToNumber(value, formatter).divide(NumberUtils.BIGDECIMAL_100);
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        return doubleValue;
    }

    static public BigDecimal stringToNumber(String string, DecimalFormat formatter) {

        BigDecimal value = BigDecimal.ZERO;
        try {
            value = BigDecimal.valueOf(formatter.parse(string).doubleValue());
        } catch (ParseException e) {
            logger.error(e);
        }
        return value;
    }
}
