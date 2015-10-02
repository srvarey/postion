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

public class PricingFilter {

    public String currency;
    public String productType;
    public String priceableFilter;
    public Integer referenceId;
    public String quoteSet;

    public PricingFilter(String currency, String productType, String tradeFilter, Integer referenceId) {
        this.currency = currency;
        this.productType = productType;
        this.priceableFilter = tradeFilter;
        this.referenceId = referenceId;
    }

    public PricingFilter(String currency, String productType, String tradeFilter, String quoteSet) {
        this.currency = currency;
        this.productType = productType;
        this.priceableFilter = tradeFilter;
        this.quoteSet = quoteSet;
    }

    public String toString(){
        return currency+"/"+productType+"/"+priceableFilter;
    }
}