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
package org.gaia.dao.trades;


import java.util.ArrayList;
import java.util.List;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.trades.ProductReferenceType;

/**
 * @author Benjamin Frerejean
 */
public class ProductReferenceTypeAccessor {

    public static final String STORE_PRODUCT_REFERENCE_TYPE="storeProductReferenceType";
    public static void storeProductReferenceType(ProductReferenceType referenceType){
        HibernateUtil.storeObject(referenceType);
    }

    public static final String GET_ALL_PRODUCT_REFERENCE_TYPE_STRINGS="getAllProductReferenceTypeStrings";
    public static List<String> getAllProductReferenceTypeStrings() {
        List<String> output=new ArrayList();
        List<ProductReferenceType> referenceTypes= (List)HibernateUtil.getObjects("ProductReferenceType","reference_type");
        if (referenceTypes != null) {
            for (ProductReferenceType type : referenceTypes) {
                output.add(type.getReferenceType());
            }
        }
        return output;
    }

    public static final String GET_ALL_PRODUCT_REFERENCE_TYPES="getAllProductReferenceTypes";
    public static List getAllProductReferenceTypes() {
        return HibernateUtil.getObjects("ProductReferenceType","reference_type");
    }

    public static final String GET_PRODUCT_REFERENCE_TYPE="getProductReferenceType";
    public static ProductReferenceType getProductReferenceType(String name) {
        return (ProductReferenceType) HibernateUtil.getObject(ProductReferenceType.class, name);
    }

    public static final String DELETE_PRODUCT_REFERENCE_TYPE="deleteProductReferenceType";
    public static void deleteProductReferenceType(String name) {
        ProductReferenceType prt=(ProductReferenceType)HibernateUtil.getObject(ProductReferenceType.class, name);
        HibernateUtil.deleteObject(prt);
    }

}
