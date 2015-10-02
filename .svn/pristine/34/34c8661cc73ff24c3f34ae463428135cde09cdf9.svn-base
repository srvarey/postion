/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.referentials;

import java.util.List;
import org.gaia.domain.referentials.Country;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class CountryAccessor {

    public static final String STORE_COUNTRY = "storeCountry";

    /**
     * store Country
     *
     * @param country
     */
    public static void storeCountry(Country country) {
        HibernateUtil.storeObject(country);
    }

    public static final String GET_COUNTRY_BY_NAME = "getCountryByName";

    /**
     * retrieve Country search by name
     *
     * @param name
     * @return
     */
    public static Country getCountryByName(String name) {
        return (Country) HibernateUtil.getObjectWithQueryWithCache("from Country where name ='" + name + StringUtils.QUOTE);
    }

    public static final String GET_COUNTRY_BY_ID = "getCountryById";

    /**
     * retrieve Country search by id
     *
     * @param id
     * @return
     */
    public static Country getCountryById(int id) {
        return (Country) HibernateUtil.getObjectWithQuery("from Country where country_id =" + id);
    }

    public static final String LOAD_COUNTRY_NAMES = "loadCountryNames";

    /**
     * load list country name
     *
     * @return
     */
    public static List<String> loadCountryNames() {
        return HibernateUtil.getObjectsWithQuery("select name from Country");
    }

    public static final String LOAD_ALL_COUNTRIES = "loadAllCountries";

    /**
     * load list from all country
     *
     * @return
     */
    public static List<Country> loadAllCountries() {
        return HibernateUtil.getObjectsWithQuery("from Country order by name");
    }

    public static final String DELETE_COUNTRY = "deleteCountry";

    /**
     * delate country
     *
     * @param name
     */
    public static void deleteCountry(String name) {
        HibernateUtil.deleteObject(getCountryByName(name));
    }
}
