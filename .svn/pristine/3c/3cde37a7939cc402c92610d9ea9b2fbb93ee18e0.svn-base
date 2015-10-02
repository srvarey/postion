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



import java.util.List;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.domain.trades.ProductExternalClassification;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductExternalClassificationAccessor {

    /**Stored External Products Classified   */
    public static void storeProductExternalClassification(ProductExternalClassification prt){
        HibernateUtil.storeObject(prt);
    }
    /** load All external  products */
    public static List loadAllProductExternalClassifications() {
        return HibernateUtil.getObjects("ProductExternalClassification","type,level,name");
    }
     /** load external  products classified by types  */
    public static List loadProductExternalClassificationsTypes() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_TYPE_CLASSIFICATIONS);
    }

    public static ProductExternalClassification getProductExternalClassificationByTypeAndName(String type,String name) {
        return (ProductExternalClassification) HibernateUtil.getObjectWithQuery("from ProductExternalClassification where type ='"+type+"' and name ='"+name +StringUtils.QUOTE);
    }


    public static ProductExternalClassification getProductClassificationByName(String name) {
        return (ProductExternalClassification) HibernateUtil.getObjectWithQuery("from ProductExternalClassification where name ='"+name +StringUtils.QUOTE);
    }

    public static boolean isChildOf(String child,String parent){
        boolean ret=false;
        ProductExternalClassification classif=getProductClassificationByName(child);
        while (classif!=null && classif.getParent()!=null){
            if (classif.getParent().equals(parent)) {
                ret=true;
            }
            classif=getProductClassificationByName(classif.getParent());
        }
        return ret;
    }

    public static void deleteProductExternalClassification(String type,String name) {
        HibernateUtil.deleteObject(getProductExternalClassificationByTypeAndName(type,name));
    }

}
