package org.gaia.dao.referentials;


import java.io.Serializable;
import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.GaiaUser;
import org.gaia.domain.referentials.UserPreference;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class UserAccessorTest extends AbstractTest{

    /**
     * Test of storeUser method, of class UserAccessor.
     */
    @Test
    public void testStoreUser() {
        System.out.println("storeUser");
        GaiaUser user = new GaiaUser();
        user.setFirstName("Gerard");
        user.setLastName("Cumin");
        user.setShortName("GC");
        UserAccessor.storeUser(user);
        GaiaUser res = (GaiaUser)HibernateUtil.getObject(GaiaUser.class,user.getUserId());
        assertNotNull("failed to store user",res);
        HibernateUtil.deleteObject(res);
    }

    /**
     * Test of getUserByShortName method, of class UserAccessor.
     */
    @Test
    public void testGetUserByShortName() {
        System.out.println("getUserByShortName");
        String name=(String)HibernateUtil.getObjectWithQuery("select u.shortName from GaiaUser u");
        GaiaUser result = UserAccessor.getUserByShortName(name);
        assertNotNull("failed to get user",result);
    }

    /**
     * Test of loadAllUsers method, of class UserAccessor.
     */
    @Test
    public void testLoadAllUsers() {
        System.out.println("loadAllUsers");
        List<Serializable> result = UserAccessor.loadAllUsers();
        assertNotNull("failed to get users",result);
    }

    /**
     * Test of deleteUser method, of class UserAccessor.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        GaiaUser user = new GaiaUser();
        user.setFirstName("Gerard");
        user.setLastName("Cumin");
        user.setShortName("GerardCumin");
        UserAccessor.storeUser(user);
        UserAccessor.deleteUser(user);
        GaiaUser res =UserAccessor.getUserByShortName("GerardCumin");
        assertNull("failed to delete user",res);
    }

    /**
     * Test of isLoginActivated method, of class UserAccessor.
     */
    @Test
    public void testIsLoginActivated() {
        System.out.println("isLoginActivated");
        Boolean result = UserAccessor.isLoginActivated();
        assertNotNull("failed to get user activation status",result);
    }

    /**
     * Test of loadUserPreferences method, of class UserAccessor.
     */
    @Test
    public void testLoadUserPreferences() {
        System.out.println("loadUserPreferences");
        Integer userId = (Integer)HibernateUtil.getObjectWithQuery("select min(u.userId) from GaiaUser u");
        List<UserPreference> result = UserAccessor.loadUserPreferences(userId);
        assertNotNull("failed to get user",result);
    }

    /**
     * Test of storeUserPreference method, of class UserAccessor.
     */
    @Test
    public void testStoreUserPreference() {
        System.out.println("storeUserPreference");
        UserPreference userPreference = new UserPreference();
        userPreference.setSettingName("name");
        userPreference.setSettingName("value");
        userPreference.setUserId((Integer)HibernateUtil.getObjectWithQuery("select min(u.userId) from GaiaUser u"));
        UserAccessor.storeUserPreference(userPreference);
        Serializable res=HibernateUtil.getObject(UserPreference.class,userPreference.getUserPreferenceId());
        assertNotNull("failed to store user pref",res);
        HibernateUtil.deleteObject(res);
    }

    /**
     * Test of deleteUserPreference method, of class UserAccessor.
     */
    @Test
    public void testDeleteUserPreference() {
        System.out.println("deleteUserPreference");UserPreference userPreference = new UserPreference();
        userPreference.setSettingName("name");
        userPreference.setSettingName("value");
        userPreference.setUserId((Integer)HibernateUtil.getObjectWithQuery("select min(u.userId) from GaiaUser u"));
        UserAccessor.storeUserPreference(userPreference);
        UserAccessor.deleteUserPreference(userPreference);
        Object res=HibernateUtil.getObject(UserPreference.class,userPreference.getUserPreferenceId());
        assertNull("failed to delete user pref",res);
    }

    /**
     * Test of getUserPreferenceById method, of class UserAccessor.
     */
    @Test
    public void testGetUserPreferenceById() {
        System.out.println("getUserPreferenceById");
        Integer preferenceId = (Integer)HibernateUtil.getObjectWithQuery("select min(u.userPreferenceId) from UserPreference u");
        UserPreference result = UserAccessor.getUserPreferenceById(preferenceId);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultCurrency method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultCurrency() {
        System.out.println("getDefaultCurrency");
        String result = UserAccessor.getDefaultCurrency(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultCurrencyPair method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultCurrencyPair() {
        System.out.println("getDefaultCurrencyPair");
        String result = UserAccessor.getDefaultCurrencyPair(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultCurrencies method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultCurrencies() {
        System.out.println("getDefaultCurrencies");
        List<String> result = UserAccessor.getDefaultCurrencies(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultCurrencyPairs method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultCurrencyPairs() {
        System.out.println("getDefaultCurrencyPairs");
        List<String> result = UserAccessor.getDefaultCurrencyPairs(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultSettingList method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultSettingList() {
        System.out.println("getDefaultSettingList");
        List<String> result = UserAccessor.getDefaultSettingList(UserAccessor.UserSettings.Currency.name(),0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultTradeView method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultTradeView() {
        System.out.println("getDefaultTradeView");
        String result = UserAccessor.getDefaultTradeView(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultPositionView method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultPositionView() {
        System.out.println("getDefaultPositionView");
        String result = UserAccessor.getDefaultPositionView(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getDefaultCollateralView method, of class UserAccessor.
     */
    @Test
    public void testGetDefaultCollateralView() {
        System.out.println("getDefaultCollateralView");
        String result = UserAccessor.getDefaultCollateralView(0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getUserPreferenceStringValue method, of class UserAccessor.
     */
    @Test
    public void testGetUserPreferenceStringValue() {
        System.out.println("getUserPreferenceStringValue");
        String result = UserAccessor.getUserPreferenceStringValue(UserAccessor.UserSettings.Currency.name(),0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getUserPreference method, of class UserAccessor.
     */
    @Test
    public void testGetUserPreference() {
        System.out.println("getUserPreference");
        UserPreference result = UserAccessor.getUserPreference(UserAccessor.UserSettings.Currency.name(),0);
        assertNotNull("failed to get user pref",result);
    }

    /**
     * Test of getUserPreferences method, of class UserAccessor.
     */
    @Test
    public void testGetUserPreferences() {
        System.out.println("getUserPreferences");
        List<UserPreference> result = UserAccessor.getUserPreferences(UserAccessor.UserSettings.Currency.name(),0);
        assertNotNull("failed to get user pref",result);
    }

}
