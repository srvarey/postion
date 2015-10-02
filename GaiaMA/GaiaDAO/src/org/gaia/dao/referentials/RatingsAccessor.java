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

import java.util.List;
import org.gaia.dao.trades.ProductConst;
import org.gaia.domain.referentials.Rating;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;


/**
 *
 * @author Benjamin Frerejean
 */
public class RatingsAccessor {

    public static final String MOODYS="MOODYS";
    public static final String SP="S&P";
    public static final String FITCH="FITCH";

    public static final String STORE_RATING="storeRating";

    /**
     *  store rating of agency .
     * @param rating
     */
    public static void storeRating(Rating rating){
        if (rating!=null && (rating.getRank()==null || rating.getRank()==0)){
            Short maxRank =(Short)HibernateUtil.getFieldWithSQLQuery("select max(r.rank) from Rating r where r.agency='"+rating.getAgency()+StringUtils.QUOTE);
            if (maxRank==null){
                maxRank=new Short("0");
            }
            maxRank++;
            rating.setRank(maxRank);
        }
        if (rating!=null){
            HibernateUtil.storeObject(rating);
        }
    }

    public static final String LOAD_ALL_RATINGS="loadAllRatings";

    /**
     *  retrieve a list of rating from all agencies .
     * @return
     */
    public static List loadAllRatings() {
        return HibernateUtil.getObjects("Rating","agency,rank");
    }

    public static final String GET_RATING_BY_AGENY_AND_RATING="getRatingByAgencyAndRating";

    /**
     *  retrieve rating from a given agency and rating .
     * @param agency
     * @param rating
     * @return
     */
    public static Rating getRatingByAgencyAndRating(String agency,String rating) {
        return (Rating)HibernateUtil.getObjectWithQuery("from Rating where agency='"+agency+"' and rating ='"+rating+StringUtils.QUOTE);
    }

    public static final String GET_AGENCIES="getAgencies";
    /**
     *  retrieve  agencies list .
     * @return
     */
     public static List getAgencies() {
         return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_RATING_AGENCIES);
     }

    public static final String DELETE_RATING="deleteRating";
    /**
     * delete a rating.
     * @param agency
     * @param rating
     */
    public static void deleteRating(String agency,String rating) {
        Rating rtg=getRatingByAgencyAndRating(agency,rating);
        HibernateUtil.deleteObject(rtg);
    }

    public static final String GET_RATINGS_BY_AGENCY="getRatingsByAgency";
    /**
     * * retrieve a list of rating order  by agency.
     * @param agency
     * @return
     */
    public static List<String> getRatingsByAgency(String agency) {
         return HibernateUtil.getObjectsWithQuery("select rating from Rating where agency='"+agency+"' order by rank");
     }
}
