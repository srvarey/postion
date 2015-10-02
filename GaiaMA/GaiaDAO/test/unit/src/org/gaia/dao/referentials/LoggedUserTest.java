package org.gaia.dao.referentials;

import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.GaiaUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class LoggedUserTest extends AbstractTest{


    /**
     * Test of getLoggedUser method, of class LoggedUser.
     */
    @Test
    public void testGetLoggedUser() {
        System.out.println("getLoggedUser");
        LoggedUser result = LoggedUser.getLoggedUser();
        assertNotNull("missing user", result);
    }

    /**
     * Test of initLoggedUser method, of class LoggedUser.
     */
    @Test
    public void testInitLoggedUser() {
        System.out.println("initLoggedUser");
        GaiaUser user = (GaiaUser)HibernateUtil.getObjectWithQuery("from GaiaUser");
        LoggedUser.initLoggedUser(user);
        LoggedUser result = LoggedUser.getLoggedUser();
        assertEquals("failed to init user", result.getGaiaUser(),user);
    }

    /**
     * Test of getGaiaUser method, of class LoggedUser.
     */
    @Test
    public void testGetGaiaUser() {
        System.out.println("getGaiaUser");
        GaiaUser guser=new GaiaUser();
        guser.setShortName("test");
        guser.setUserId(0);
        LoggedUser.initLoggedUser(guser);
        LoggedUser instance = LoggedUser.getLoggedUser();
        GaiaUser result = instance.getGaiaUser();
        assertNotNull("missing user", result);
    }

    /**
     * Test of getLoggedUserName method, of class LoggedUser.
     */
    @Test
    public void testGetLoggedUserName() {
        System.out.println("getLoggedUserName");
        String result = LoggedUser.getLoggedUserName();
        assertNotNull("missing user", result);
    }

    /**
     * Test of getLoggedUserId method, of class LoggedUser.
     */
    @Test
    public void testGetLoggedUserId() {
        System.out.println("getLoggedUserId");
        Integer result = LoggedUser.getLoggedUserId();
        assertNotNull("missing user id", result);
    }

}
