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
package org.gaia.dao.reports;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import static org.gaia.dao.reports.ObjectAccessor.buildReportQuery;
import static org.gaia.dao.reports.ObjectAccessor.fillReportLines;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.TemplateColumnItem;

/**
 * @author Benjamin Frerejean
 */

public class ObjectAccessorCaller {

    private static final Logger logger = Logger.getLogger(ObjectAccessorCaller.class);

    public static LinkedHashMap<Integer, ReportLine> getReportLinesWithFilter(
            LinkedHashMap<Integer, ReportLine> reportLines,
            Class objectType,
            Filter filter,
            Collection<TemplateColumnItem> sortingOrder,
            Date valDate,
            boolean isFirstDate,
            boolean isLookThrough) {

        /**
         * build the query.
         */
        String query = buildReportQuery(reportLines, objectType, filter, sortingOrder);
        /**
         * launch the query.
         */
        logger.info("FILTER SQL: " + query);
        List list = HibernateUtil.getListWithSQLQuery(query);

        /**
         * build the output
         */
        if (reportLines == null) {
            reportLines = new LinkedHashMap();
        }
        fillReportLines(reportLines, list, sortingOrder, isFirstDate);

        return reportLines;
    }

}
