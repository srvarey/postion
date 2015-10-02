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
import org.apache.log4j.Logger;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean

 This class computes the CVA in case of netting aggreement
 In this case the CVA is based on global positive NPV
 *
 */
public class TradeQuantityEventManager extends AbstractPostPricingTreatment {

    private static final Logger logger = Logger.getLogger(TradeQuantityEventManager.class);

    public static void calculate(
            LinkedHashMap<Integer,ReportLine> reportLines,
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,
            Date valueDate,
            Map<String, BigDecimal> mapFX,
            Map<String,List<ReportLine>> additionalReportData,
            boolean isFirstDate,
            ReportSettings reportSettings) {

        try {

            if (reportLines.size() > 0) {
                if (reportLines.get(0) != null && reportLines.get(0).getLineData() instanceof Trade) {

                    for (ReportLine line : reportLines.values()) {
                        // on trades the quantity has to be impacted by contract events
                        if (line.getLineData() instanceof Trade) {
                            String getter = "Quantity";
                            if (!isFirstDate) {
                                BigDecimal quantity = (BigDecimal) line.getObjectMap().get(getter);
                                if (quantity != null) {
                                    IPriceable priceable = (IPriceable) line.getLineData();
                                    line.getObjectMap().put(getter, quantity.multiply(priceable.getProduct().getNotionalMultiplierByDate(valueDate)));
                                }
                            } else {
                                BigDecimal quantity = (BigDecimal) line.getObjectMapFirst().get(getter);
                                if (quantity != null) {
                                    IPriceable priceable = (IPriceable) line.getLineData();
                                    line.getObjectMapFirst().put(getter, quantity.multiply(priceable.getProduct().getNotionalMultiplierByDate(valueDate)));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e))                        ;
        }

    }
}
