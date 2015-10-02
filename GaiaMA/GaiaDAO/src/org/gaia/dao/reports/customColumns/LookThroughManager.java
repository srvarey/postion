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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.utils.StringUtils;

/**
 * @author Benjamin Frerejean
 * this class computes the quantities on instrument in look through (sub funds)
 */
public class LookThroughManager extends AbstractPostPricingTreatment {

    private static final Logger logger = Logger.getLogger(LookThroughManager.class);

    public static void calculate(
            LinkedHashMap<Integer, ReportLine> reportLines,
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,
            Date valueDate,
            Map<String, BigDecimal> mapFX,
            Map<String, List<ReportLine>> additionalReportData,
            boolean isFirstDate,
            ReportSettings reportSettings) {

        try {

            if (reportLines.size() > 0 && reportSettings.getTemplate().isFundLookThrough()) {

                final String QUANTITY = "PositionHistory.Quantity";
                final String ORIGINAL_QUANTITY = "PositionHistory.Quantity.original";

                List<Integer> toRemove = new ArrayList();
                LinkedHashMap<Integer, ReportLine> toAdd = new LinkedHashMap();
                Filter filter = new Filter();
                filter.setFilterCriteria(new HashSet());
                for (FilterCriteria criteria : reportSettings.getTemplate().getFilter().getFilterCriteria()) {
                    if (criteria.isPostTreatment()) {
                        filter.getFilterCriteria().add(criteria);
                    }
                }

                // first : look through on portfolios
                for (ReportLine line : reportLines.values()) {
                    if (!filter.getFilterCriteria().isEmpty()) {
                        if (!FilterAccessor.isInFilter(line.getLineData(), filter)) {
                            toRemove.add(line.getId());
                            line.setIsToRemove(true);
                        }
                    }
                    if (line.getParentLine() != null && !line.isToRemove()) {
                        if (!isFirstDate) {
                            String stockType = (String) line.getObjectMap().get("Product.ProductReference.FakeStockType");
                            BigDecimal parentNpv = (BigDecimal) line.getParentLine().getObjectMap().get(MeasuresAccessor.Measure.NPV.name());
                            BigDecimal sumLookthrough = (BigDecimal) line.getParentLine().getObjectMap().get("LookThroughSum." + stockType);
                            BigDecimal quantity = (BigDecimal) line.getObjectMap().get(ORIGINAL_QUANTITY);
                            if (quantity == null) {
                                quantity = (BigDecimal) line.getObjectMap().get(QUANTITY);
                                line.getObjectMap().put(ORIGINAL_QUANTITY, quantity);
                            }
                            if (parentNpv != null) {
                                quantity = quantity.multiply(parentNpv);
                            } else {
                                logger.error("Look Through manager error : no npv on line " + line.getParentLine().getId());
                            }
                            if (sumLookthrough != null && sumLookthrough.doubleValue() != 0) {
                                quantity = quantity.divide(sumLookthrough, 20, RoundingMode.UP);
                            } else {
                                quantity = quantity.divide(NumberUtils.BIGDECIMAL_100, 20, RoundingMode.UP);
                            }
                            line.getObjectMap().put(MeasuresAccessor.Measure.NPV.name(), quantity);
                            BigDecimal divisor = (BigDecimal) line.getObjectMap().get(MeasuresAccessor.Measure.NPV_unit.name());
                            if (divisor != null && divisor.doubleValue() != 0) {
                                quantity = quantity.divide(divisor, 20, RoundingMode.UP);
                            }
                            line.getObjectMap().put(QUANTITY, quantity);
                            toRemove.add(line.getParentLine().getId());
                        } else {
                            BigDecimal multiplierMeasureValue = (BigDecimal) line.getParentLine().getObjectMapFirst().get(MeasuresAccessor.Measure.NPV_unit.name());
                            BigDecimal quantity = (BigDecimal) line.getObjectMapFirst().get(QUANTITY);
                            quantity = quantity.multiply(multiplierMeasureValue);
                            BigDecimal divisor = (BigDecimal) line.getObjectMapFirst().get(MeasuresAccessor.Measure.NPV_unit.name());
                            if (divisor.doubleValue() != 0) {
                                quantity = quantity.divide(divisor, 20, RoundingMode.UP);
                            }
                            line.getObjectMapFirst().put(QUANTITY, quantity);
                            line.getObjectMapFirst().put(MeasuresAccessor.Measure.NPV.name(), quantity.multiply(divisor));
                        }
                    }
                }

                for (Integer lineId : toRemove) {
                    reportLines.remove(lineId);
                }
                toRemove.clear();

                // then manage look through on index
                for (ReportLine line : reportLines.values()) {
                    if (line.getLineData() instanceof Position) {
                        Position position = (Position) line.getLineData();
                        if (position.getProduct() != null && position.getProduct().getUnderlying() != null
                                && position.getProduct().getUnderlying().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.EQUITY_INDEX.getName())
                                && position.getProduct().getUnderlying().getUnderlyingProducts() != null
                                && !position.getProduct().getUnderlying().getUnderlyingProducts().isEmpty()) {
                            BigDecimal multiplierMeasureValue = (BigDecimal) line.getObjectMap().get(MeasuresAccessor.Measure.NPV.name());
                            LegalEntity portfolio = ProductAccessor.getProductPortfolio(position.getProduct().getId());
                            if (portfolio == null) {
                                portfolio = new LegalEntity();
                                portfolio.setBaseCurrency(position.getProduct().getNotionalCurrency());
                                portfolio.setShortName(position.getProduct().getShortName());
                            }
                            for (ProductUnderlying productUnderlying : position.getProduct().getUnderlying().getUnderlyingProducts()) {

                                ReportLine newLine = new ReportLine();
                                newLine.setId(-productUnderlying.getUnderlying().getId() - line.getId());
                                Position myData = position.clone();
                                myData.setProduct(productUnderlying.getUnderlying());
                                myData.setPositionId(newLine.getId());
                                myData.setInternalCounterparty(portfolio);
                                newLine.setLineData(myData);

                                if (!filter.getFilterCriteria().isEmpty()) {
                                    if (!FilterAccessor.isInFilter(newLine.getLineData(), filter)) {
                                        newLine.setIsToRemove(true);
                                    }
                                }
                                if (!newLine.isToRemove()) {

                                    newLine.setObjectMap(new HashMap());
                                    newLine.setObjectMapFirst(new HashMap());
                                    newLine.setParentLine(line);
                                    newLine.getObjectMap().put("PositionId", newLine.getId());
                                    newLine.getObjectMap().put("Position Id", newLine.getId());
                                    newLine.getObjectMap().put("Product.ProductId", myData.getProduct().getId());
                                    newLine.setObjectMapFirst(new HashMap());
                                    newLine.getObjectMapFirst().put("PositionId", newLine.getId());
                                    newLine.getObjectMapFirst().put("Position Id", newLine.getId());
                                    newLine.getObjectMapFirst().put("Product.ProductId", myData.getProduct().getId());
                                    BigDecimal quantity = productUnderlying.getWeight();
                                    if (multiplierMeasureValue != null) {
                                        quantity = quantity.multiply(multiplierMeasureValue);
                                    } else {
                                        logger.error("Look Through manager error : no quote on line " + line.getParentLine().getId());
                                    }
                                    MarketQuote mqDivisor = MarketQuoteAccessor.getLastQuote(productUnderlying.getUnderlying().getId(), valueDate, MarketQuoteAccessor.getDefaultQuoteSet());
                                    if (mqDivisor != null && mqDivisor.getClose() != null && mqDivisor.getClose().doubleValue() != 0) {
                                        quantity = quantity.divide(mqDivisor.getClose(), 20, RoundingMode.UP);
                                        newLine.getObjectMap().put(QUANTITY, quantity);
                                        quantity = quantity.multiply(mqDivisor.getClose());
                                        newLine.getObjectMap().put(MeasuresAccessor.Measure.NPV.name(), quantity);
                                    } else {
                                        newLine.getObjectMap().put(QUANTITY, quantity);
                                        newLine.getObjectMap().put(MeasuresAccessor.Measure.NPV.name(), quantity);
                                    }
                                    toAdd.put(newLine.getId(), newLine);
                                    if (!toRemove.contains(line.getId())){
                                        toRemove.add(line.getId());
                                    }
                                }
                            }
                        }
                    }
                }
                for (Integer lineId : toRemove){
                    reportLines.remove(lineId);
                }
                reportLines.putAll(toAdd);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }
}
