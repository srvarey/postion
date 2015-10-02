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
package org.gaia.dao.reports.customColumns;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.referentials.PdfDocConfigAccessor;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.BatchLogAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.referentials.PdfDocConfig;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;

/**
 * @author Benjamin Frerejean
 * looks for the last import date for
 */
public class FundCompositionImportDateCustomColumn implements IStaticCustomColumn,IColorableColumn {

    private static final Logger logger = Logger.getLogger(FundCompositionImportDateCustomColumn.class);

    @Override
    public CustomColumn getCustomColumn() {
        CustomColumn column = new CustomColumn();
        column.setClassName(this.getClass().getName());
        column.setName("Import Date");
        column.setType(Date.class.getName());
        column.setObjectTypeClass(Position.class);
        return column;
    }

    @Override
    public Object calculate(CustomColumn customColumn, TemplateColumnItem templateColumnItem, ReportLine line, Date valDate, Date startDate, String suffix, Map<String, BigDecimal> mapFXEnd, Map<String, BigDecimal> mapFXStart, String reportCurrency) {
        try {
            if (IPriceable.class.isAssignableFrom(line.getLineData().getClass())) {
                IPriceable priceable = (IPriceable)line.getLineData();
                Product product = priceable.getProduct();
                Integer portfolioId=null;
                if (product.getProductEquity()!=null && product.getProductEquity().getPortfolio()!=null){
                    LegalEntity portfolio=ProductAccessor.getProductPortfolio(product.getProductId());
                    if (portfolio!=null){
                        portfolioId=portfolio.getLegalEntityId();
                    }
                }
                if (portfolioId==null && line.getLineData() instanceof Position){
                    portfolioId=((Position)line.getLineData()).getInternalCounterparty().getLegalEntityId();
                }
                if (portfolioId!=null){
                    PdfDocConfig config=PdfDocConfigAccessor.getPdfDocConfigByPortfolioId(portfolioId);
                    if (config!=null){
                        return BatchLogAccessor.getLastBatchLogDate(PdfDocConfig.class, config.getPdfDocConfigId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error on object id " + line.getId() + StringUtils.SPACE + StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    @Override
    public Color getColor(Object value,boolean hasFocus){
        if (value instanceof Date){
            Date lastEOM=DateUtils.endOfLastMonth(DateUtils.getDate());
            if (lastEOM.equals((Date)value)){
                if (hasFocus){
                    return Color.green.darker();
                }else{
                    return Color.green;
                }
            } else if (lastEOM.after((Date)value)){
                if (hasFocus){
                    return Color.red.darker();
                } else {
                    return Color.red;
                }
            }
        }
        return null;
    }
}
