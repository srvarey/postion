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
package org.gaia.dao.referentials;

import java.util.ArrayList;
import java.util.List;
import org.gaia.domain.referentials.DomainValue;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class DomainValuesAccessor {

    public static final String STORE_DOMAIN_VALUE="storeDomainValue";
    public static void storeDomainValue(DomainValue domainValue){
       HibernateUtil.storeObject(domainValue);
    }

    public static final String GET_DOMAIN_VALUES_BY_NAME="getDomainValuesByName";

    /** return a list of product names according to the value of product type
     * @param name
     * @return  */
    public static List<String> getDomainValuesByName(String name) {
        List<String> resultList=new ArrayList();
        List returnList=HibernateUtil.getObjectsWithQuery("from DomainValue where name ='"+name+"' order by value");
        for(Object object : returnList){
           resultList.add(((DomainValue) object).getDomainValuesPK().getValue());
       }return resultList;
    }

    public static final String GET_DOMAIN_VALUE_BY_NAME_AND_VALUE="getDomainValueByNameAndValue";
    public static DomainValue getDomainValueByNameAndValue(String name,String value) {
        return (DomainValue)HibernateUtil.getObjectWithQueryWithCache("from DomainValue where name ='"+name+"' and value ='"+value+StringUtils.QUOTE);
    }

    public static final String LOAD_ALL_DOMAIN_VALUES="loadAllDomainValues";
    public static java.util.List loadAllDomainValues() {
        return HibernateUtil.getObjects("DomainValue","name,value");
    }

    public static final String DELETE_DOMAIN_VALUE="deleteDomainValue";
    public static void deleteDomainValue(String name,String value) {
        HibernateUtil.deleteObject(getDomainValueByNameAndValue(name,value));
    }

}
