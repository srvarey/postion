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
package org.gaia.dao.pricing.pricers.gaia;

/**
 *
 * @author Benjamin
 */
public class GaiaPricersUtils {

    /*
     * Normal distribution
     */
    @SuppressWarnings("empty-statement")
    public static double N(double z) {
        if (z > 6.0) {
            return 1.0;
        }; // this guards against overflow
        if (z < -6.0) {
            return 0.0;
        };
        double b1 = 0.31938153;
        double b2 = -0.356563782;
        double b3 = 1.781477937;
        double b4 = -1.821255978;
        double b5 = 1.330274429;
        double p = 0.2316419;
        double c2 = 0.3989423;
        double a = Math.abs(z);
        double t = 1.0 / (1.0 + a * p);
        double b = c2 * Math.exp((-z) * (z / 2.0));
        double n = ((((b5 * t + b4) * t + b3) * t + b2) * t + b1) * t;
        n = 1.0 - b * n;
        if (z < 0.0) {
            n = 1.0 - n;
        }
        return n;
    }

}
