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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.Mapping;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Benjamin Frerejean
 *
 *
 */
public class MappingsAccessor {

    public static final String MAPPINGS = "mappings";

    public static final String GET_MAPPING_LIST="getMappingList";
    public static List<Mapping> getMappingList() {
        List<Mapping> res;
        Session session = HibernateUtil.getSession();
        Query querySnapShot = session.getNamedQuery("Mapping.findAll");
        res = (List<Mapping>) querySnapShot.list();
        return res;
    }

    public static final String GET_MAPPING_NAME_LIST="getMappingNameList";
    public static List<String> getMappingNameList() {
        return DomainValuesAccessor.getDomainValuesByName("mappings");
    }

    public static final String GET_MAPPINGS_BY_NAME="getMappingsByName";
    public static List<Mapping> getMappingsByName(String mappingName) {
        return (List<Mapping>) HibernateUtil.getObjectsWithQueryWithCache("from Mapping where mapping_name='" + mappingName + "' order by key1");
    }

    public static final String GET_MAPPING_BY_NAME_AND_KEY="getMappingByNameAndKey";
    public static Mapping getMappingByNameAndKey(String mappingName, String key) {
        return (Mapping) HibernateUtil.getObjectWithQueryWithCache("from Mapping where mapping_name='" + mappingName + "' and key1='" + key.replace(StringUtils.QUOTE, "''") + StringUtils.QUOTE);
    }

    public static final String GET_MAPPING_BY_NAME_AND_VALUE="getMappingByNameAndValue";
    public static Mapping getMappingByNameAndValue(String mappingName, String value) {
        return (Mapping) HibernateUtil.getObjectWithQueryWithCache("from Mapping where mapping_name='" + mappingName + "' and value='" + value + StringUtils.QUOTE);
    }

    public static final String GET_MAPPING_KEY_BY_NAME_AND_VALUE="getMappingKeyByNameAndValue";
    public static String getMappingKeyByNameAndValue(String mappingName, String value) {
        Mapping map = getMappingByNameAndValue(mappingName, value);
        if (map != null) {
            return map.getKey1();
        }
        return null;
    }

    public static final String GET_MAPPING_VALUE_BY_NAME_AND_KEY="getMappingValueByNameAndKey";
    public static String getMappingValueByNameAndKey(String mappingName, String key) {
        Mapping map = getMappingByNameAndKey(mappingName, key);
        if (map != null) {
            return map.getValue();
        }
        return null;
    }

    public static final String GET_MAPPING_MAP_BY_NAME="getMappingMapByName";
    public static Map<String, String> getMappingMapByName(String mappingName) {
        Map<String, String> resultMap = new HashMap();
        List<Mapping> mappings = (ArrayList<Mapping>) HibernateUtil.getObjectsWithQueryWithCache("from Mapping where mapping_name='" + mappingName + StringUtils.QUOTE);
        for (Mapping map : mappings) {
            resultMap.put(map.getKey1(), map.getValue());
        }
        return resultMap;
    }

    public static final String DELETE_MAPPING="deleteMapping";
    public static void deleteMapping(Mapping mapping){
        HibernateUtil.deleteObject(mapping);
    }

    public static final String STORE_MAPPING="storeMapping";
    public static void storeMapping(Mapping mapping){
        HibernateUtil.storeObject(mapping);
    }

    public static final String GET_DEFAULT_PRICING_ENVIRONMENT_NAME="getDefaultPricingEnvironmentName";
    public static String getDefaultPricingEnvironmentName() {
        return getMappingByNameAndKey("defaults", "default pricing environment").getValue();
    }


}
