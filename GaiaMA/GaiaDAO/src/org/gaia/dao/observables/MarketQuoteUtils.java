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
package org.gaia.dao.observables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gaia.domain.observables.MarketQuote;

/**
 *
 * @author Benjamin Frerejean
 */


public class MarketQuoteUtils {


    /**
     *  load quotation types
     * @return
     */
    public static List<MarketQuote.QuotationType> loadQuotationTypes() {
        MarketQuote.QuotationType[] arr = MarketQuote.QuotationType.values();
        return new ArrayList(Arrays.asList(arr));
    }


    /**
     *  quotation type by name
     * @param name
     * @return
     */
    public static MarketQuote.QuotationType getQuotationTypeByName(String name) {
        ArrayList<MarketQuote.QuotationType> list = (ArrayList) loadQuotationTypes();
        for (MarketQuote.QuotationType quotationType : list) {
            if (quotationType.getName() != null) {
                if (quotationType.getName().equals(name)) {
                    return quotationType;
                }
            }
        }
        return null;
    }
}
