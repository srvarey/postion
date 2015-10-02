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
package org.gaia.dao.reports.customColumns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.reports.ReportLine;

/**
 *
 * @author Benjamin Frerejean
 */
public abstract class AbstractPostPricingTreatment {

    public static void calculate(
            LinkedHashMap<Integer,ReportLine> reportLines,
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,
            Date valueDate,
            Map<String, BigDecimal> mapFX,
            Map<String,List<ReportLine>> additionalReportData,
            boolean isFirstDate,
            ReportSettings reportSettings)
    {}
}
