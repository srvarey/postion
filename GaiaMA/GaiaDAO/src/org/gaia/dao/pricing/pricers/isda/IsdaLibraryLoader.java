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
package org.gaia.dao.pricing.pricers.isda;

import com.sun.jna.Native;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin
 */
public class IsdaLibraryLoader {

    private static IsdaPricerLinks link;
    private static IsdaLibraryLoader instance;
    private static final Logger logger = Logger.getLogger(IsdaLibraryLoader.class);

    private IsdaLibraryLoader(){
        try {
            if (System.getProperty("os.arch").contains("64")){
                link = (IsdaPricerLinks) Native.loadLibrary("IdsaCDS64", IsdaPricerLinks.class);
            } else {
                link = (IsdaPricerLinks) Native.loadLibrary("IdsaCDS32", IsdaPricerLinks.class);
            }
        } catch (Exception e){
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static IsdaLibraryLoader getInstance(){
        return instance;
    }

    public static IsdaPricerLinks getIdsdaLink(){
        if (instance==null){
            logger.info("Load isda dll library");
            instance=new IsdaLibraryLoader();
        }
        return instance.getLink();
    }

    public static IsdaPricerLinks getLink() {
        return link;
    }

    public static void deleteLink(){
        link=null;
        instance=null;
    }


}
