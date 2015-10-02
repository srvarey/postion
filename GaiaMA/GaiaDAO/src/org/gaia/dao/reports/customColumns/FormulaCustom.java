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
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.TemplateColumnItem;

/**
 *
 * @author Benjamin Frerejean
 */
public class FormulaCustom implements ICustomColumn{

    private static final Logger logger = Logger.getLogger(FormulaCustom.class);
    @Override
    public Object calculate(CustomColumn customColumn, TemplateColumnItem tci, ReportLine line, Date valDate, Date startDate, String suffix, Map<String,BigDecimal> mapFXEnd,Map<String,BigDecimal> mapFXStart,String reportCurrency) {
        Object ores = null;

        /**
         * formula custom column
         */
        Evaluator evaluator = new Evaluator();
        try {
            if (customColumn.getCustomColumnDetails() != null) {
                boolean allFieldsFound = true;
                for (CustomColumnDetail customColumnDetail : customColumn.getCustomColumnDetails()) {
                    Object param = ReportBuilder.fillFieldWithCustom(line, customColumnDetail, valDate, startDate, suffix, mapFXEnd, mapFXStart, reportCurrency, customColumn);
                    if (param != null) {
                        if (param instanceof BigDecimal){
                            evaluator.putVariable(customColumnDetail.getColumnName()+customColumnDetail.getSuffix(),Double.toString(((BigDecimal)param).doubleValue()));
                        }else {
                            evaluator.putVariable(customColumnDetail.getColumnName()+customColumnDetail.getSuffix(), StringUtils.QUOTE+param.toString()+StringUtils.QUOTE);
                        }
                    } else {
                        allFieldsFound = false;
                    }
                }
                if (allFieldsFound) {
                    ores = evaluator.evaluate(customColumn.getFormula());
                    if (ores.toString().startsWith(StringUtils.QUOTE)&&ores.toString().endsWith(StringUtils.QUOTE)){
                        ores=ores.toString().subSequence(1, ores.toString().length()-1);
                    }
                }
                if (tci.getReturnType()!=null && tci.getReturnType().equals(BigDecimal.class.getName()) && ores != null) {
                    Double double_=Double.parseDouble(ores.toString());
                    if (!double_.isNaN()&&!double_.isInfinite()){
                        ores = BigDecimal.valueOf(double_);
                    }
                }
            }
        } catch (EvaluationException | NumberFormatException e) {
            logger.error("Error formula "+customColumn.getName()+" on object id " + line.getId()+StringUtils.SPACE+StringUtils.formatErrorMessage(e));
        }
        return ores;
    }
}



