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
package org.gaia.dao.trades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductConst {

    public static final String ASSET_TYPE = "assetType";
    public static final String PRODUCT_TYPE_CLASSIFICATIONS = "productClassifications";
    public static final String PRODUCT_QUOTATION_TYPE = "quotationType";
    public static final String PRODUCT_RATING_AGENCIES = "ratingAgencies";
    public static final String PRODUCT_TENORS = "tenors";
    public static final String PRODUCT_INTEREST_RATE_SOURCES = "interestRateSources";
    public static final String PRODUCT_FREQUENCIES = "frequencies";
    public static final String PRODUCT_DAYCOUNTS = "dayCount";
    public static final String PRODUCT_COUPON_ADJUSTMENTS = "couponAdjustment";
    public static final String PRODUCT_SENIORITIES = "bondSeniority";
    public static final String PRODUCT_PAYOFF_FORMULA_FUNCTIONS = "payoffFormulaFunctions";
    public static final String PRODUCT_PAYOFF_CONDITION_FUNCTIONS = "payoffConditionFunctions";
    public static final String PRODUCT_CREDIT_EVENTS = "creditEvents";

    public enum ProductStatus {

        Active, Inactive, Terminated, Defaulted, PartlyDefaulted, Exercised
    }

    public enum cdsImmDate {

        MAR(new Date(0, 2, 20)),
        JUN(new Date(0, 5, 20)),
        SEP(new Date(0, 8, 20)),
        DEC(new Date(0, 11, 20));
        public final Date date;

        /**
         * date of cds
         */
        cdsImmDate(Date date) {
            this.date = date;
        }
    }

    public enum ExerciseType {

        European("European"),
        American("American"),
        Bermuda("Bermuda");
        private final String display;

        private ExerciseType(String name) {
            display = name;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    public enum BarrierType {

        UpAndIn("Up And In"),
        UpAndOut("Up And Out"),
        DownAndIn("Down And In"),
        DownAndOut("Down And Out");
        public final String display;

        private BarrierType(String name) {
            display = name;
        }

        @Override
        public String toString() {
            return display;
        }

        public static List<String> getBarrierTypes(){
            List ret=new ArrayList();
            for (BarrierType type : BarrierType.values()){
                ret.add(type.display);
            }
            return ret;
        }
    }

    public enum OptionType {

        Call("Call"),
        Put("Put");
        private final String display;

        private OptionType(String name) {
            display = name;
        }

        @Override
        public String toString() {
            return display;
        }

    }

    public enum ExerciseSettlementType{Physical, Cash};

}