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
import org.gaia.domain.referentials.Sector;
import org.gaia.domain.utils.HibernateUtil;


/**
 *
 * @author Benjamin Frerejean
 */
public class SectorAccessor {

    public static final String GICS="GICS";
    public static final String ICB="ICB";

    /**
     * store a product sector
     * @param sector
     */
    public static void storeProductSector(Sector sector){
            HibernateUtil.storeObject(sector);
    }

    public static final String GET_CLASSIFICATIONS="getClassifications";
    /**
     * get classifications list
     * @return
     */
     public static List<String> getClassifications() {
         return HibernateUtil.getObjectsWithQueryWithCache("select distinct s.classificationName from Sector s ");
     }

    public static final String GET_SECTORS_BY_CLASSIFICATION="getSectorsByClassification";
    /**
     * get the list of sectors of a classification
     * @param classificationName
     * @return
     */
     public static List<Sector> getSectorsByClassification(String classificationName) {
         return HibernateUtil.getObjectsWithQuery("from Sector s where s.classificationName='"+classificationName+"' order by code");
     }

    public static final String GET_SECTOR_NAMES_BY_CLASSIFICATION="getNonParentSectorNamesByClassification";
     /**
     * get the list of sectors names of a classification
     * @param classificationName
     * @return
     */
     public static List<String> getSectorNamesByClassification(String classificationName) {
         List<String> ret=new ArrayList();
         for (Sector sector : getSectorsByClassification(classificationName)){
             ret.add(sector.getSectorName());
         }
         return ret;
     }
    public static final String GET_GICS_SECTOR_NAMES="getGICSSectorNames";
     /**
     * get the list of GICS sectors
     * @return
     */
     public static List<String> getGICSSectorNames() {
         return getSectorNamesByClassification(GICS);
     }

     public static final String GET_GICS_SECTOR_BY_NAME="getGICSSectorByName";
     /**
     * get a GICS sector by name
     * @param sector
     * @return
     */
     public static Sector getGICSSectorByName(String sector) {
         return (Sector)HibernateUtil.getObjectWithQuery("from Sector s where s.classificationName='"+GICS+"' and s.sectorName='"+sector+"' order by s.parent desc");
     }

     public static final String GET_PRODUCT_SECTOR="getProductSector";
     /**
     * get a sector by product id
     * @param productId
     * @return
     */
     public static Sector getProductSector(Integer productId) {
         return (Sector)HibernateUtil.getObjectWithQuery("select s.sector from ProductEquity s where s.product.productId="+productId);
     }
}
