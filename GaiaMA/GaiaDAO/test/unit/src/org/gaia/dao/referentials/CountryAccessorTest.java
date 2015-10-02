package org.gaia.dao.referentials;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.Country;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class CountryAccessorTest extends AbstractTest{

    /**
     * Test of storeCountry method, of class CountryAccessor.
     */
    @Test
    public void testStoreCountry() {
        System.out.println("storeCountry");
        Country country = new Country();
        country.setCodeIso2a("XX");
        country.setCodeIso3a("XXX");
        country.setName("test");
        CountryAccessor.storeCountry(country);
        Country res =(Country) HibernateUtil.getObjectWithQuery("from Country where name='test'");
        assertNotNull("failed to store country",res);
    }

    /**
     * Test of getCountryByName method, of class CountryAccessor.
     */
    @Test
    public void testGetCountryByName() {
        System.out.println("getCountryByName");
        String name =(String) HibernateUtil.getObjectWithQuery("select name from Country");
        Country result = CountryAccessor.getCountryByName(name);
        assertNotNull("failed to get country",result);
    }

    /**
     * Test of getCountryById method, of class CountryAccessor.
     */
    @Test
    public void testGetCountryById() {
        System.out.println("getCountryById");
        Country country =(Country) HibernateUtil.getObjectWithQuery("from Country");
        Country result = CountryAccessor.getCountryById(country.getCountryId());
        assertNotNull("failed to get country",result);
    }

    /**
     * Test of loadAllCountryNames method, of class CountryAccessor.
     */
    @Test
    public void testLoadCountryNames() {
        System.out.println("loadAllCountryNames");
        List<String> result = CountryAccessor.loadCountryNames();
        assertNotNull("failed to load countries",result);
    }

    /**
     * Test of loadAllCountries method, of class CountryAccessor.
     */
    @Test
    public void testLoadAllCountries() {
        System.out.println("loadAllCountries");
        List<Country> result = CountryAccessor.loadAllCountries();
        assertNotNull("failed to load countries",result);
    }

    /**
     * Test of deleteCountry method, of class CountryAccessor.
     */
    @Test
    public void testDeleteCountry() {
        System.out.println("deleteCountry");
        CountryAccessor.deleteCountry("test");
        Country result = CountryAccessor.getCountryByName("test");
        assertNull("failed to delete countries",result);
    }

}
