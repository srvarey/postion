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

import org.gaia.domain.utils.HibernateUtil;
import java.util.List;
import org.gaia.domain.trades.HeaderTable;

/**
 *
 * @author SABER
 */
public class HeaderTableAccessor {

    public static final String STORE_HEADER_TABLE = "storeHeaderTable";

    /** store Table headers
     * @param headerList */
    public static void storeTableHeaders(List<HeaderTable> headerList) {
        for (HeaderTable tableHeader : headerList) {
            HibernateUtil.storeObject(tableHeader);
        }
    }
    public static final String LOAD_TABLE_HEADERS = "loadTableHeaders";

    /** load table headers
     * @param tableName
     * @return  */
    public static List<HeaderTable> loadTableHeaders(String tableName) {
        return (List<HeaderTable>)HibernateUtil.getObjectsWithQuery("from HeaderTable where table_name='"+tableName+"' order by header_id");
    }


}
