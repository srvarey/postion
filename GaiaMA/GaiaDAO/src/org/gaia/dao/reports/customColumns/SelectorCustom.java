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
import java.util.Map;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.TemplateColumnItem;

/**
 *
 * @author user
 */
public class SelectorCustom implements ICustomColumn {

    @Override
    /** calculate position with all information
     *
     */
    public Object calculate(CustomColumn customColumn, TemplateColumnItem tci, ReportLine line, Date valDate, Date startDate, String suffix, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency) {

        Object objectResult = null;
        Filter filter ;
        Object serializable = null;
        if (line.getLineData() != null) {
            serializable = line.getLineData();
        }
        if (customColumn.getCustomColumnDetails() != null) {
            for (CustomColumnDetail customColumnDetail : customColumn.getCustomColumnDetails()) {
                filter = customColumnDetail.getFilter();
                Object defaultObjectResult = null;
                if (filter!=null) {
                    if (!"ALL".equalsIgnoreCase(filter.getName())) {
                        if (FilterAccessor.isInFilter(serializable, filter)) {
                            objectResult = makeValue(customColumnDetail, customColumn, line, valDate, startDate, tci, mapFXEnd, mapFXStart, reportCurrency);
                            return objectResult;
                        }
                    } else {
                        defaultObjectResult = makeValue(customColumnDetail, customColumn, line, valDate, startDate, tci, mapFXEnd, mapFXStart, reportCurrency);
                    }
                }
                if (objectResult == null && defaultObjectResult != null) {
                    objectResult = defaultObjectResult;
                }
            }
        }
        return objectResult;
    }

    private Object makeValue(CustomColumnDetail customColumnDetail, CustomColumn customColumn, ReportLine line, Date valDate, Date startDate, TemplateColumnItem tci, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency) throws NumberFormatException {
        Object objectResult;
        if (!StringUtils.isEmptyString(customColumnDetail.getLitteralValue())) {
            if (customColumn.getType().equalsIgnoreCase(BigDecimal.class.getName())) {
                objectResult = BigDecimal.valueOf(Double.parseDouble(customColumnDetail.getLitteralValue()));
            } else {
                objectResult = customColumnDetail.getLitteralValue();
            }
        } else {
            objectResult = ReportBuilder.fillFieldWithCustom(line, customColumnDetail, valDate, startDate,
                    tci.getSuffix(), mapFXEnd, mapFXStart, reportCurrency, customColumn);
        }
        return objectResult;
    }
}
