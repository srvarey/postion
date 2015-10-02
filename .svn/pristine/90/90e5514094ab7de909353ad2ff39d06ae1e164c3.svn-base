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
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.TemplateColumnItem;

/**
 *
 * @author Benjamin Frerejean
 */

/**
 *
 * @author Benjamin Frerejean
 */
public class GreekCustomColumn implements IStaticCustomColumn
{
    private static final Logger logger = Logger.getLogger(GreekCustomColumn.class);

    @Override
    public CustomColumn getCustomColumn(){
        CustomColumn column=new CustomColumn();
        column.setClassName(this.getClass().getName());
        column.setName("Greek");
        column.setType(BigDecimal.class.getName());
        return column;
    }

    @Override
    public Object calculate(CustomColumn customColumn, TemplateColumnItem templateColumnItem, ReportLine line, Date valDate, Date startDate, String suffix, Map<String,BigDecimal> mapFXEnd,Map<String,BigDecimal> mapFXStart,String reportCurrency){

        try {
            String name=templateColumnItem.getDisplayName();
            for (Entry<String,Object> entry :line.getObjectMap().entrySet()){
                if (entry.getKey().contains(StringUtils.DOT)){
                    if (entry.getKey().substring(0, entry.getKey().indexOf(StringUtils.DOT)).equalsIgnoreCase(name)){
                        if (entry.getValue()!=null){
                            return entry.getValue();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error on object id " + line.getId()+StringUtils.SPACE+StringUtils.formatErrorMessage(e));
        }

        return null;
    }
}
