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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.Trade;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductReferenceCustomColumn implements IStaticCustomColumn {

    private static final Logger logger = Logger.getLogger(ProductReferenceCustomColumn.class);
    public static final DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

    @Override
    public CustomColumn getCustomColumn() {
        CustomColumn column = new CustomColumn();
        column.setClassName(this.getClass().getName());
        column.setName("ProductReference");
        column.setType(String.class.getName());
        return column;
    }

    @Override
    public Object calculate(CustomColumn customColumn, TemplateColumnItem templateColumnItem, ReportLine line, Date valDate, Date startDate, String suffix, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency) {
        try {
            Product product = null;
            if (line.getLineData() instanceof Product) {
                product = (Product) line.getLineData();
            } else if (line.getLineData() instanceof Trade) {
                product = ((Trade) line.getLineData()).getProduct();
            } else if (line.getLineData() instanceof Position) {
                product = ((Position) line.getLineData()).getProduct();
            } else if (line.getLineData() instanceof ProductEvent){
                ProductEvent event = (ProductEvent) line.getLineData();
                product=event.getProduct();
            }
            if (product != null) {
                String ref = ProductAccessor.getProductReference(product, templateColumnItem.getDisplayName());
                if (!StringUtils.isEmptyString(ref)) {
                    return ref;
                }
                if (product.getUnderlying() != null) {
                    ref = ProductAccessor.getProductReference(product.getUnderlying(), templateColumnItem.getDisplayName());
                    if (!StringUtils.isEmptyString(ref)) {
                        return ref;
                    }
                }
                if (product.getSubProducts() != null) {
                    for (Product component : product.getSubProducts()) {
                        if (component.getUnderlying()!=null){
                            ref = ProductAccessor.getProductReference(component.getUnderlying(), templateColumnItem.getDisplayName());
                            if (!StringUtils.isEmptyString(ref)) {
                                return ref;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error on object id " + line.getId() + StringUtils.SPACE + StringUtils.formatErrorMessage(e));
        }
        return null;
    }
}
