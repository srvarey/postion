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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.domain.referentials.GaiaUser;
import org.gaia.domain.referentials.UserPreference;
import org.gaia.domain.referentials.UserPricingPreference;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 * @author Benjamin Frerejean
 */
public class UserAccessor {

    public static final String STORE_USER = "storeUser";

    public enum UserSettings {

        Currency(true,CurrencyAccessor.class,CurrencyAccessor.LOAD_CURRENCY_CODES),
        PricingEnvironment(true,PricingEnvironmentAccessor.class,PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_LIST),
        CurrencyPairs(false,CurrencyAccessor.class,CurrencyAccessor.LOAD_CURRENCY_PAIRS),
        MainCurrencyPair(true,CurrencyAccessor.class,CurrencyAccessor.LOAD_CURRENCY_PAIRS),
        PositionDefaultView(true,ReportTemplateAccessor.class,ReportTemplateAccessor.GET_TEMPLATE_LIST,Position.class),
        CollateralDefaultView(true,ReportTemplateAccessor.class,ReportTemplateAccessor.GET_TEMPLATE_LIST,Position.class),
        TradeDefaultView(true,ReportTemplateAccessor.class,ReportTemplateAccessor.GET_TEMPLATE_LIST,Trade.class),
        Currencies(false,CurrencyAccessor.class,CurrencyAccessor.LOAD_CURRENCY_CODES);

        public boolean isUnique;
        public Class clazz;
        public String method;
        public Serializable[] args;

        UserSettings(boolean isUnique,Class clazz,String method,Serializable ... args) {
            this.isUnique = isUnique;
            this.clazz=clazz;
            this.method=method;
            this.args=args;
        }
    }

    /*
     * store a user
     */
    public static void storeUser(GaiaUser user) {
        HibernateUtil.storeObject(user);
    }

    public static final String GET_USER_BY_SHORT_NAME = "getUserByShortName";
    /*
     * get a user by name
     */

    public static GaiaUser getUserByShortName(String shortName) {
        return (GaiaUser) HibernateUtil.getObjectWithQuery("from GaiaUser where short_name ='" + shortName + StringUtils.QUOTE);
    }

    /*
     * get a user by name
     */
    public static void initLogin(GaiaUser user) {
        LoggedUser.initLoggedUser(user);
    }

    public static final String LOAD_ALL_USERS = "loadAllUsers";
    /*
     * load all Users
     */

    public static List<Serializable> loadAllUsers() {
        return HibernateUtil.getObjectsWithQuery("from GaiaUser gu order by gu.shortName");
    }

    public static final String DELETE_USER = "deleteUser";
    /*
     * delete a user
     */

    public static void deleteUser(GaiaUser user) {
        BigInteger count = (BigInteger) HibernateUtil.getFieldWithSQLQuery("select count(*) from gaia_user");
        if (count.intValue() > 1) {
            HibernateUtil.deleteObject(user);
        }
    }

    public static final String IS_LOGIN_ACTIVATED = "isLoginActivated";
    /*
     * check if the login is activated
     */

    public static Boolean isLoginActivated() {
        List<String> values = DomainValuesAccessor.getDomainValuesByName("isLoginActivated");
        if (values != null) {
            for (String value : values) {
                return value.equalsIgnoreCase("true");
            }
        }
        return false;
    }
    public static final String LOAD_USER_PREFERENCES = "loadUserPreferences";
    /*
     * load all user preferences
     */

    public static List<UserPreference> loadUserPreferences(Integer userId) {
        return (List<UserPreference>) HibernateUtil.getObjectsWithQuery("from UserPreference where user_id=" + userId);
    }

    public static final String STORE_USER_PREFERENCE = "storeUserPreference";
    /*
     * store user preference
     */

    public static void storeUserPreference(UserPreference userPreference) {
        HibernateUtil.storeObject(userPreference);
    }

    public static final String DELETE_USER_PREFERENCE = "deleteUserPreference";
    /*
     * delete user preference
     */

    public static void deleteUserPreference(UserPreference userPreference) {
        HibernateUtil.deleteObject(userPreference);
    }

    public static final String GET_USER_PREFERENCE_BY_ID = "getUserPreferenceById";
    /*
     * retrieve user preference by id
     */

    public static UserPreference getUserPreferenceById(Integer preferenceId) {
        return (UserPreference) HibernateUtil.getObjectWithQuery("from UserPreference where user_preference_id =" + preferenceId);
    }

    public static final String GET_DEFAULT_CURRENCY = "getDefaultCurrency";

    /** retrieve user by code
     * @param userId
     * @return  */
    public static String getDefaultCurrency(Integer userId) {
        return getUserPreferenceStringValue(UserSettings.Currency.name(), userId);
    }

    public static final String GET_DEFAULT_CURRENCY_PAIR = "getDefaultCurrencyPair";

    /**
     * get user main default currency pair
     * @param userId
     * @return
     */
    public static String getDefaultCurrencyPair(Integer userId) {
        return getUserPreferenceStringValue(UserSettings.MainCurrencyPair.name(), userId);
    }

    public static final String GET_DEFAULT_CURRENCIES = "getDefaultCurrencies";

    /**
     * get user default currency pair list
     * @param userId
     * @return
     */
    public static List<String> getDefaultCurrencies(Integer userId) {
        List<String> result = getDefaultSettingList(UserSettings.Currencies.name(), userId);
        if (result == null || result.isEmpty()) {
            result = CurrencyAccessor.loadCurrencyCodes();
        }
        return result;
    }
    public static final String GET_DEFAULT_CURRENCY_PAIRS = "getDefaultCurrencyPairs";

    /**
     * get user default currency pair list
     * @param userId
     * @return
     */
    public static List<String> getDefaultCurrencyPairs(Integer userId) {
        return getDefaultSettingList(UserSettings.CurrencyPairs.name(), userId);
    }

    /**
     * get user default currency pair list
     * @param setting
     * @param userId
     * @return
     */
    public static List<String> getDefaultSettingList(String setting, Integer userId) {
        List<UserPreference> prefs = getUserPreferences(setting, userId);
        List<String> ret = new ArrayList();
        if (prefs != null) {
            for (UserPreference pref : prefs) {
                ret.add(pref.getSettingValue());
            }
        }
        return ret;
    }
    public static final String GET_DEFAULT_TRADE_VIEW = "getDefaultTradeView";
    /*
     * get user default trade view
     */

    public static String getDefaultTradeView(Integer userId) {
        return getUserPreferenceStringValue(UserSettings.TradeDefaultView.name(), userId);
    }

    public static final String GET_DEFAULT_POSITION_VIEW = "getDefaultPositionView";
    /*
     * get user default position view
     */

    public static String getDefaultPositionView(Integer userId) {
        return getUserPreferenceStringValue(UserSettings.PositionDefaultView.name(), userId);
    }

    public static final String GET_DEFAULT_COLLATERAL_VIEW = "getDefaultCollateralView";
    /*
     * get user default position view
     */

    public static String getDefaultCollateralView(Integer userId) {
        String view = getUserPreferenceStringValue(UserSettings.CollateralDefaultView.name(), userId);
        if (view == null) {

        }
        return view;
    }

    public static String getUserPreferenceStringValue(String setting, Integer userId) {
        UserPreference pref = getUserPreference(setting, userId);
        if (pref != null) {
            return pref.getSettingValue();
        } else {
            return null;
        }
    }

    /**
     * get a user preference
     * @param setting
     * @param userId
     * @return
     */
    public static UserPreference getUserPreference(String setting, Integer userId) {
        return (UserPreference) HibernateUtil.getObjectWithQuery("from UserPreference where user_id =" + userId + " and setting_name='" + setting + StringUtils.QUOTE);
    }

    /**
     * get a user preference list
     * @param setting
     * @param userId
     * @return
     */
    public static List<UserPreference> getUserPreferences(String setting, Integer userId) {
        return HibernateUtil.getObjectsWithQuery("from UserPreference where user_id =" + userId + " and setting_name='" + setting + StringUtils.QUOTE);
    }

    public static final String GET_USER_PRICING_PREFERENCES = "getUserPricingPreferences";

    /**
     * get a user pricing preference list
     * @param productTypes
     * @param userId
     * @return
     */
    public static List<UserPricingPreference> getUserPricingPreferences(String productTypes, Integer userId) {
        return HibernateUtil.getObjectsWithQuery("from UserPricingPreference where user_id =" + userId + " and product_type='" + productTypes + StringUtils.QUOTE);
    }

    public static final String STORE_USER_PRICING_PREFERENCES = "storeUserPricingPreferences";

    /**
     * get a user pricing preference list
     * @param preferences
     * @param userId
     */
    public static void storeUserPricingPreferences(List<UserPricingPreference> preferences, Integer userId) {
        if (preferences.size() > 0) {
            List<UserPricingPreference> storedPreferences;
            storedPreferences = getUserPricingPreferences(preferences.get(0).getProductType(), userId);
            for (UserPricingPreference storedPref : storedPreferences) {
                boolean isIn = false;
                for (UserPricingPreference pref : preferences) {
                    if (pref.getUserPricingPreferenceId() != null
                            && storedPref.getUserPricingPreferenceId().intValue() == pref.getUserPricingPreferenceId().intValue()) {
                        isIn = true;
                    }
                }
                if (!isIn) {
                    HibernateUtil.deleteObject(storedPref);
                }
            }
            for (UserPricingPreference pref : preferences) {
                HibernateUtil.storeObject(pref);
            }
        }
    }
}
