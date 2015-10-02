package org.gaia.dao.referentials;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.Rating;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class RatingsAccessorTest extends AbstractTest{

    /**
     * Test of storeRating method, of class RatingsAccessor.
     */
    @Test
    public void testStoreRating() {
        System.out.println("storeRating");
        Rating rating = new Rating();
        rating.setAgency("agency");
        rating.setRating("XXX");
        RatingsAccessor.storeRating(rating);
        Rating res=(Rating)HibernateUtil.getObjectWithQuery("from Rating where rating_id="+rating.getRatingId());
        assertNotNull("failed to store rating",res);
        HibernateUtil.deleteObject(rating);
    }

    /**
     * Test of loadAllRatings method, of class RatingsAccessor.
     */
    @Test
    public void testLoadAllRatings() {
        System.out.println("loadAllRatings");
        List result = RatingsAccessor.loadAllRatings();
        assertNotNull("failed to load ratings",result);
    }

    /**
     * Test of getRatingByAgencyAndRating method, of class RatingsAccessor.
     */
    @Test
    public void testGetRatingByAgencyAndRating() {
        System.out.println("getRatingByAgencyAndRating");
        Rating res=(Rating)HibernateUtil.getObjectWithQuery("from Rating");
        Rating result = RatingsAccessor.getRatingByAgencyAndRating(res.getAgency(), res.getRating());
        assertNotNull("failed to load ratings",result);
    }

    /**
     * Test of getAgencies method, of class RatingsAccessor.
     */
    @Test
    public void testGetAgencies() {
        System.out.println("getAgencies");
        List result = RatingsAccessor.getAgencies();
        assertNotNull("failed to load agencies",result);
    }

    /**
     * Test of deleteRating method, of class RatingsAccessor.
     */
    @Test
    public void testDeleteRating() {
        System.out.println("deleteRating");
        Rating rating = new Rating();
        rating.setAgency("agency");
        rating.setRating("XXX");
        RatingsAccessor.storeRating(rating);
        HibernateUtil.deleteObject(rating);
        Rating res=(Rating)HibernateUtil.getObjectWithQuery("from Rating where rating_id="+rating.getRatingId());
        assertNull("failed to delete rating",res);
    }

    /**
     * Test of getRatingsByAgency method, of class RatingsAccessor.
     */
    @Test
    public void testGetRatingsByAgency() {
        System.out.println("getRatingsByAgency");
        List<String> result = RatingsAccessor.getRatingsByAgency("Fitch");
        assertNotNull("failed to get ratings",result);
    }

}
