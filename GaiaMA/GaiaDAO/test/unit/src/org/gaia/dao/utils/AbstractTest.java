/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.utils;

import org.gaia.domain.utils.GlobalInfo;
import org.gaia.domain.utils.HibernateUtil;
import org.junit.Test;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 * @author Benjamin Frerejean
 */


public class AbstractTest {
    static boolean isInitialized=false;

    public AbstractTest(){
        if (!isInitialized){
            isInitialized=true;
            System.out.println("INITIALIZING");
            LocalFileSystem fs = new LocalFileSystem();
            System.out.println("path:"+fs.getRootDirectory().getAbsolutePath());
            GlobalInfo.getInstance().setRunningAsServer(true);
            HibernateUtil.initSession();
            System.out.println("END INITIALIZATION");
        }
    }


    /**
     * Test of nothing
     */
    @Test
    public void noTest() {
        // do not delete please
        // generates an error if deleted
    }
}
