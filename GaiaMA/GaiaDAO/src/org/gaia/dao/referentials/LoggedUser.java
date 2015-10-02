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

import org.gaia.domain.referentials.GaiaUser;

/**
 * @author Benjamin Frerejean
 */


public class LoggedUser {
    private static LoggedUser loggedUser;
    private GaiaUser user;

    public static LoggedUser getLoggedUser(){
        if (loggedUser==null){
            loggedUser=new LoggedUser();
        }
        return loggedUser;
    }

    private LoggedUser(){
    }

    private LoggedUser(GaiaUser user){
        this.user=user;
    }

    public static void initLoggedUser(GaiaUser user){
        loggedUser=new LoggedUser(user);
    }

    public static GaiaUser getGaiaUser(){
        return loggedUser.user;
    }

    public static String getLoggedUserName(){
        LoggedUser loggedUser_=getLoggedUser();
        if (loggedUser_.getGaiaUser()!=null){
            return loggedUser_.getGaiaUser().getShortName();
        } else {
            return "default";
        }
    }

    public static Integer getLoggedUserId(){
        LoggedUser loggedUser_=getLoggedUser();
        if (loggedUser_.getGaiaUser()!=null){
            return loggedUser_.getGaiaUser().getUserId();
        } else {
            return 0;
        }
    }



}
