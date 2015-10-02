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
import org.apache.log4j.Logger;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.Trade;

/**
 *
 * @author Benjamin Frerejean
 */

/**
 *
 * @author Benjamin Frerejean
 */
public class BuySellCustomColumn implements IStaticCustomColumn
{
    private static final Logger logger = Logger.getLogger(BuySellCustomColumn.class);

    @Override
    public CustomColumn getCustomColumn(){
        CustomColumn column=new CustomColumn();
        column.setClassName(this.getClass().getName());
        column.setName("BuySell");
        column.setType(String.class.getName());
        return column;
    }

    @Override
    public Object calculate(CustomColumn customColumn, TemplateColumnItem templateColumnItem, ReportLine line, Date valDate, Date startDate, String suffix, Map<String,BigDecimal> mapFXEnd,Map<String,BigDecimal> mapFXStart,String reportCurrency){

        try {
            BigDecimal quantity=null;

            if (line.getLineData() instanceof Trade){
                quantity = ((Trade)line.getLineData()).getQuantity();
            } else if (line.getLineData() instanceof Position){
                quantity = ((Position)line.getLineData()).getQuantity();
            } else if (line.getLineData() instanceof ProductEvent){
                ProductEvent event = (ProductEvent) line.getLineData();
                if (event.getTrade()!=null){
                    quantity=event.getTrade().getQuantity();
                }
            }
            if (quantity!=null){
                if (quantity.doubleValue()<0){
                    return "S";
                } else {
                    return "B";
                }
            }
        } catch (Exception e) {
            logger.error("Error on object id " + line.getId()+StringUtils.SPACE+StringUtils.formatErrorMessage(e));
        }
        return null;
    }
}
