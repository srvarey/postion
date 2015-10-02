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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasureValue;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean

 This class computes the CVA in case of netting aggreement
 In this case the CVA is based on global positive NPV
 *
 */
public class CVACalculationManager extends AbstractPostPricingTreatment {

    private static final Logger logger = Logger.getLogger(CVACalculationManager.class);

    public static void calculate(
            LinkedHashMap<Integer,ReportLine> reportLines,
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,
            Date valueDate,
            Map<String, BigDecimal> mapFX,
            Map<String,List<ReportLine>> additionalReportData,
            boolean isFirstDate,
            ReportSettings reportSettings) {

        Map<IPricerMeasure, IMeasureValue[]> pricerRes;

        boolean cvaToCalculate = false;
        int creditExposureCol = 0;
        int creditLineCol = 0;
        int creditJumpToDefaultCol = 0;
        Map<Integer, TemplateColumnItem> creditLinesUnderlyings = new HashMap();
        int i = 0;
        BigDecimal defaultOneMinusRecoveryRate = new BigDecimal(.6);
        for (TemplateColumnItem item : reportSettings.getTemplate().getTemplateColumnItems()) {
            if (item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CVA.name())
                    || item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CVA_unit.name())
                    || item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.MARGINAL_CVA.name())
                    || item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.MARGINAL_CVA_unit.name())
                    || item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_EXPOSURE.name())
                    || item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_LINE.name())
                    || item.getName().startsWith(MeasuresAccessor.Measure.SINGLE_CREDIT_LINE.name())
                    || item.getName().startsWith(MeasuresAccessor.Measure.JUMP_TO_DEFAULT.name())) {
                cvaToCalculate = true;
                if (item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_EXPOSURE.name())) {
                    creditExposureCol = i;
                }
                if (item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.JUMP_TO_DEFAULT.name())) {
                    creditJumpToDefaultCol = i;
                }
                if (item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_LINE.name())) {
                    creditLineCol = i;
                }
                if (item.getName().startsWith(MeasuresAccessor.Measure.SINGLE_CREDIT_LINE.name())) {
                    Integer ctpId = Integer.parseInt(item.getParam());
                    creditLinesUnderlyings.put(ctpId, item);
                }
            }
            i++;
        }

        if (creditExposureCol + creditJumpToDefaultCol + creditLineCol > 0 || cvaToCalculate) {
            logger.info("CVA STARTED AT " + DateUtils.getTime());
            /** up & down for CVA
             * lets look for a netting agreement
             * if exists a netting agreement then it applies on the sum of npv if positive
             * here we make the sum of npv and positive npv
             * only on positions at the moment
             */
            //cva
            Map<Integer, BigDecimal> totalNPVMap = new HashMap();
            Map<Integer, BigDecimal> positiveNPVMap = new HashMap();
            Map<Integer, BigDecimal> sumProbByNPVMap = new HashMap();
            Map<Integer, BigDecimal> nettedCVAMap = new HashMap();

            // credit exposure
            Map<Integer, BigDecimal> sumCreditExposureMap = new HashMap();
            Map<Integer, BigDecimal> sumCounterpartyNPVMap = new HashMap();
            Map<Integer, BigDecimal> sumJumpToDefaultMap = new HashMap();

            // credit line
            Map<Integer, BigDecimal> sumCreditLineMap = new HashMap();
            Map<Integer, BigDecimal> totalCVAByCounterpartyMap = new HashMap();

            if (reportLines != null && reportLines.size() > 0 && cvaToCalculate) {
                if (reportLines.values().iterator().next().getLineData() instanceof Position) {

                    for (ReportLine line : reportLines.values()) {
                        IPriceable priceable = (IPriceable) line.getLineData();
                        try {
                            if (priceable != null) {
                                pricerRes = (Map) pricerResultsByTrade.get(priceable.getId());
                                if (pricerRes != null) {
                                    BigDecimal npv = BigDecimal.ZERO;
                                    if (pricerRes.get(MeasuresAccessor.Measure.NPV) != null && pricerRes.get(MeasuresAccessor.Measure.NPV).length > 0) {
                                        MeasureValue npvMeasureValue = (MeasureValue) pricerRes.get(MeasuresAccessor.Measure.NPV)[0];
                                        npv = npvMeasureValue.getMeasureValue();
                                        npv = ReportBuilder.convertField(reportSettings.getCurrency(), priceable.getProduct().getNotionalCurrency(), npv, valueDate, mapFX);
                                    }

                                    ProductTypeUtil.ProductType type = ProductTypeUtil.getProductTypeByName(priceable.getProduct().getProductType());

                                    if (type.use.equals(ProductTypeUtil.ProductTypeUse.OTC) || type.use.equals(ProductTypeUtil.ProductTypeUse.CLEARED_OTC)) {
                                        MeasureValue lineCvaMeasureValue = null;
                                        if (pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line) != null && pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line).length > 0) {
                                            lineCvaMeasureValue = (MeasureValue) pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line)[0];
                                            if (npv.doubleValue() < 0) {
                                                lineCvaMeasureValue.setMeasureValue(BigDecimal.ZERO);
                                            }
                                        }
                                        Integer agreementId = priceable.getAgreementId();
                                        if (agreementId != null) {

                                            BigDecimal cvaUnitaire = BigDecimal.ZERO;
                                            if (lineCvaMeasureValue != null) {
                                                cvaUnitaire = lineCvaMeasureValue.getMeasureValue();
                                            }
                                            BigDecimal totalNPV = (BigDecimal) totalNPVMap.get(agreementId);
                                            BigDecimal positiveNPV = null;
                                            BigDecimal sumProbByNPV = null;
                                            if (totalNPV == null) {
                                                totalNPV = BigDecimal.ZERO;
                                            } else {
                                                positiveNPV = (BigDecimal) positiveNPVMap.get(agreementId);
                                                sumProbByNPV = (BigDecimal) sumProbByNPVMap.get(agreementId);
                                            }
                                            if (positiveNPV == null) {
                                                positiveNPV = BigDecimal.ZERO;
                                                sumProbByNPV = BigDecimal.ZERO;
                                                positiveNPVMap = new HashMap();
                                                sumProbByNPVMap = new HashMap();
                                            }
                                            totalNPVMap.put(agreementId, totalNPV.add(npv));
                                            /** only when npv > 0 */
                                            if (npv.doubleValue() > 0) {
                                                positiveNPVMap.put(agreementId, positiveNPV.add(npv));
                                                sumProbByNPVMap.put(agreementId, sumProbByNPV.add(npv.multiply(cvaUnitaire)));
                                            }
                                            if (reportSettings.includeCounterpartyInCreditIndicators()) {
                                                BigDecimal sumNPV = (BigDecimal) sumCounterpartyNPVMap.get(priceable.getCounterparty().getLegalEntityId());
                                                if (sumNPV == null) {
                                                    sumCounterpartyNPVMap.put(priceable.getCounterparty().getLegalEntityId(), npv);
                                                } else {
                                                    sumCounterpartyNPVMap.put(priceable.getCounterparty().getLegalEntityId(), sumNPV.add(npv));
                                                }
                                            }
                                        } else if (reportSettings.includeCounterpartyInCreditIndicators()) {
                                            BigDecimal sumNPV = (BigDecimal) sumCounterpartyNPVMap.get(priceable.getCounterparty().getLegalEntityId());
                                            if (npv.doubleValue() > 0) { // if no netting, credit line on positive
                                                if (sumNPV == null) {
                                                    sumCounterpartyNPVMap.put(priceable.getCounterparty().getLegalEntityId(), npv);
                                                } else {
                                                    sumCounterpartyNPVMap.put(priceable.getCounterparty().getLegalEntityId(), sumNPV.add(npv));
                                                }
                                            }
                                        }
                                    }

                                    // credit exposure : sums the credit exposures
                                    if (creditExposureCol > 0 || creditLineCol > 0 || creditJumpToDefaultCol > 0) {

                                        Integer issuerId = LegalEntityAccessor.getCreditIssuerId(priceable.getProduct());
                                        if (issuerId != null) {
                                            if (creditExposureCol > 0 || creditJumpToDefaultCol > 0) {
                                                BigDecimal expo = priceable.getQuantity(valueDate);
                                                // convert to report currency
                                                ReportBuilder.convertField(reportSettings.getCurrency(), priceable.getProduct().getNotionalCurrency(), expo, valueDate, mapFX);
                                                BigDecimal sumCreditExposure = sumCreditExposureMap.get(issuerId);
                                                BigDecimal sumJumpToDefault = sumJumpToDefaultMap.get(issuerId);
                                                BigDecimal jumpToDefault;
                                                if (priceable.getProduct().getProductCredit() != null && priceable.getProduct().getProductCredit().getRecoveryFactor() != null) {
                                                    jumpToDefault = expo.multiply(BigDecimal.ONE.subtract(priceable.getProduct().getProductCredit().getRecoveryFactor()));
                                                } else {
                                                    jumpToDefault = expo.multiply(defaultOneMinusRecoveryRate);
                                                }
                                                if (sumCreditExposure == null) {
                                                    sumCreditExposureMap.put(issuerId, expo);
                                                    sumJumpToDefaultMap.put(issuerId, jumpToDefault);
                                                } else {
                                                    sumCreditExposureMap.put(issuerId, sumCreditExposure.add(expo));
                                                    sumJumpToDefaultMap.put(issuerId, sumJumpToDefault.add(jumpToDefault));
                                                }
                                            }
                                            if (creditLineCol > 0 || !creditLinesUnderlyings.isEmpty()) {
                                                BigDecimal npvCredit = BigDecimal.ZERO;
                                                IMeasureValue measureValueCredit = null;
                                                if (pricerRes.get(MeasuresAccessor.Measure.NPV) != null && pricerRes.get(MeasuresAccessor.Measure.NPV).length > 0) {
                                                    IMeasureValue[] array = (IMeasureValue[]) pricerRes.get(MeasuresAccessor.Measure.NPV);
                                                    for (IMeasureValue measureValue : array) {
                                                        if (measureValue.getMeasure().getName().equalsIgnoreCase(MeasuresAccessor.Measure.NPV_CREDIT.name())) {
                                                            measureValueCredit = measureValue;
                                                            npvCredit = measureValue.getMeasureValue();
                                                        }
                                                    }
                                                    npvCredit = ReportBuilder.convertField(reportSettings.getCurrency(), priceable.getProduct().getNotionalCurrency(), npvCredit, valueDate, mapFX);
                                                }
                                                BigDecimal sumCreditLine = sumCreditLineMap.get(issuerId);
                                                if (sumCreditLine == null) {
                                                    sumCreditLineMap.put(issuerId, npvCredit);
                                                } else {
                                                    sumCreditLineMap.put(issuerId, sumCreditLine.add(npvCredit));
                                                }
                                                TemplateColumnItem item = creditLinesUnderlyings.get(issuerId);
                                                if (item != null) {
                                                    if (measureValueCredit != null) {
                                                        MeasureValue singleCreditLine = (MeasureValue) measureValueCredit.clone();
                                                        singleCreditLine.setMeasure(MeasuresAccessor.getMeasureByName(item.getName()));
                                                        singleCreditLine.setName(item.getName());
                                                        pricerRes.put(MeasuresAccessor.Measure.SINGLE_CREDIT_LINE, new MeasureValue[]{singleCreditLine});
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            logger.error("Error calculating cva on id " + priceable.getId());
                            logger.error(StringUtils.formatErrorMessage(e));
                        }
                    }

                    /**
                     * calculate global cvas
                     */
                    BigDecimal totalCVA = BigDecimal.ZERO;
                    for (Integer agreementId : totalNPVMap.keySet()) {
                        BigDecimal totalNPV = (BigDecimal) totalNPVMap.get(agreementId);
                        BigDecimal positiveNPV = (BigDecimal) positiveNPVMap.get(agreementId);
                        BigDecimal sumProbByNPV = (BigDecimal) sumProbByNPVMap.get(agreementId);
                        if (positiveNPV != null && sumProbByNPV != null && totalNPV != null && totalNPV.doubleValue() > 0) {
                            totalCVA = totalNPV.multiply(sumProbByNPV).divide(positiveNPV, 20, RoundingMode.HALF_UP);
                            nettedCVAMap.put(agreementId, totalCVA);
                        } else if (totalNPV.doubleValue() <= 0) {
                            nettedCVAMap.put(agreementId, BigDecimal.ZERO);
                        }

                    }

                    /** we have legal entities cva now
                     * now another round and when exists a netting agreement
                     * pos cva = global cva * npv / sum npv
                     */
                    for (ReportLine line : reportLines.values()) {
                        IPriceable priceable = (IPriceable) line.getLineData();
                        boolean isSet = false;
                        try {
                            if (priceable != null) {
                                pricerRes = (Map) pricerResultsByTrade.get(priceable.getId());
                                if (pricerRes != null) {
                                    MeasureValue lineCvaMeasureValue = null;
                                    MeasureValue unitCVAMeasureValue = null;

                                    // transform unit line in unit : avoid explosion when in real time
                                    if (pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line) != null && pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line).length > 0) {
                                        lineCvaMeasureValue = (MeasureValue) pricerRes.get(MeasuresAccessor.Measure.CVA_unit_line)[0];
                                        if (lineCvaMeasureValue != null) {
                                            unitCVAMeasureValue = (MeasureValue) lineCvaMeasureValue.clone();
                                            unitCVAMeasureValue.setMeasure(MeasuresAccessor.Measure.CVA_unit);
                                            unitCVAMeasureValue.setName(MeasuresAccessor.Measure.CVA_unit.name());
                                            pricerRes.put(MeasuresAccessor.Measure.CVA_unit, new MeasureValue[]{unitCVAMeasureValue});
                                        }
                                    }

                                    BigDecimal npv = BigDecimal.ZERO;
                                    if (pricerRes.get(MeasuresAccessor.Measure.NPV) != null && pricerRes.get(MeasuresAccessor.Measure.NPV).length > 0) {
                                        MeasureValue npvMeasureValue = (MeasureValue) pricerRes.get(MeasuresAccessor.Measure.NPV)[0];
                                        npv = npvMeasureValue.getMeasureValue();
                                        npv = ReportBuilder.convertField(reportSettings.getCurrency(), priceable.getProduct().getNotionalCurrency(), npv, valueDate, mapFX);
                                    }

                                    BigDecimal cva = BigDecimal.ZERO;
                                    Integer agreementId = priceable.getAgreementId();
                                    MeasureValue cvaMeasureValue = null;
                                    if (agreementId != null) {

                                        totalCVA = nettedCVAMap.get(agreementId);
                                        if (totalCVA != null) {

                                            ProductTypeUtil.ProductType type = ProductTypeUtil.getProductTypeByName(priceable.getProduct().getProductType());

                                            if (type.use.equals(ProductTypeUtil.ProductTypeUse.OTC) || type.use.equals(ProductTypeUtil.ProductTypeUse.CLEARED_OTC)) {

                                                BigDecimal totalNPV = (BigDecimal) totalNPVMap.get(agreementId);
                                                BigDecimal positiveNPV = (BigDecimal) positiveNPVMap.get(agreementId);
                                                BigDecimal sumProbByNPV = (BigDecimal) sumProbByNPVMap.get(agreementId);

                                                if (lineCvaMeasureValue != null) {
                                                    //dispatching
                                                    BigDecimal unitCva = BigDecimal.ZERO;
                                                    if (npv.doubleValue() > 0) {
                                                        if (positiveNPV.doubleValue() > 0) {
                                                            /** one position cva is calculated at prorata on positive npv positions */
                                                            cva = totalCVA.multiply(npv).divide(positiveNPV, 20, RoundingMode.HALF_UP);  // will be multiplicated by npv after
                                                            /** cva is in report currency ( like the total) so we have to convert it back to product currency*/
                                                            cva = ReportBuilder.convertField(priceable.getProduct().getNotionalCurrency(), reportSettings.getCurrency(), cva, valueDate, mapFX);
                                                        }
                                                    }
                                                    if (npv.doubleValue() != 0) {
                                                        unitCva = cva.divide(npv, 20, RoundingMode.HALF_UP);
                                                    }
                                                    unitCVAMeasureValue.setMeasureValue(unitCva);
                                                    pricerRes.put(MeasuresAccessor.Measure.CVA_unit, new MeasureValue[]{unitCVAMeasureValue});

                                                    cvaMeasureValue = (MeasureValue) lineCvaMeasureValue.clone();
                                                    cvaMeasureValue.setMeasure(MeasuresAccessor.Measure.CVA);
                                                    cvaMeasureValue.setName(MeasuresAccessor.Measure.CVA.name());
                                                    cvaMeasureValue.setMeasureValue(cva);
                                                    pricerRes.put(MeasuresAccessor.Measure.CVA, new MeasureValue[]{cvaMeasureValue});

                                                    /**
                                                     * marginal cva
                                                     */
                                                    totalNPV = totalNPV.subtract(npv);
                                                    if (npv.doubleValue() > 0) {
                                                        positiveNPV = positiveNPV.subtract(npv);
                                                        sumProbByNPV = sumProbByNPV.subtract(npv.multiply(unitCva));
                                                    }

                                                    BigDecimal marginalcva = BigDecimal.ZERO;
                                                    BigDecimal unitMarginalcva = BigDecimal.ZERO;
                                                    if (positiveNPV.doubleValue() == 0) {
                                                        marginalcva = cva;
                                                    } else if (totalNPV.doubleValue() < 0) {
                                                        // if without the trade the total npv is <0, then :
                                                        marginalcva = totalCVA;
                                                    } else if (npv.doubleValue() != 0 && totalNPV.doubleValue() > 0) {
                                                        /**  calculate global cva without the trade */
                                                        BigDecimal totalCVAWithoutThisTrade = totalNPV.multiply(sumProbByNPV).divide(positiveNPV, 20, RoundingMode.HALF_UP);
                                                        /** global position cva is calculated on the sum of npvs */
                                                        marginalcva = totalCVA.subtract(totalCVAWithoutThisTrade);
                                                        /** conversion in product currency                                                  */
                                                        marginalcva = ReportBuilder.convertField(priceable.getProduct().getNotionalCurrency(), reportSettings.getCurrency(), marginalcva, valueDate, mapFX);
                                                        /** will be multiplicated by npv after **/
                                                    }
                                                    if (npv.doubleValue() != 0) {
                                                        /** divide by npv for unit measure  */
                                                        unitMarginalcva = marginalcva.divide(npv, 20, RoundingMode.HALF_UP);
                                                        /** cva is in report currency ( like the total) so we have to convert it back to product currency*/
                                                        unitMarginalcva = ReportBuilder.convertField(priceable.getProduct().getNotionalCurrency(), reportSettings.getCurrency(), unitMarginalcva, valueDate, mapFX);
                                                    }
                                                    /**
                                                     * fill measures
                                                     */
                                                    MeasureValue unitMarginalCVA = (MeasureValue) lineCvaMeasureValue.clone();
                                                    unitMarginalCVA.setMeasure(MeasuresAccessor.Measure.MARGINAL_CVA_unit);
                                                    unitMarginalCVA.setMeasureValue(unitMarginalcva);
                                                    unitMarginalCVA.setName(MeasuresAccessor.Measure.MARGINAL_CVA_unit.name());
                                                    pricerRes.put(MeasuresAccessor.Measure.MARGINAL_CVA_unit, new MeasureValue[]{unitMarginalCVA});

                                                    MeasureValue marginalCvaMeasureValue = (MeasureValue) unitMarginalCVA.clone();
                                                    marginalCvaMeasureValue.setMeasure(MeasuresAccessor.Measure.MARGINAL_CVA);
                                                    marginalCvaMeasureValue.setName(MeasuresAccessor.Measure.MARGINAL_CVA.name());
                                                    marginalCvaMeasureValue.setMeasureValue(marginalcva);
                                                    pricerRes.put(MeasuresAccessor.Measure.MARGINAL_CVA, new MeasureValue[]{marginalCvaMeasureValue});
                                                    isSet = true;
                                                }
                                            }
                                        }
                                    }
                                    if (!isSet && lineCvaMeasureValue != null) {
                                        MeasureValue marginalCVA = (MeasureValue) lineCvaMeasureValue.clone();
                                        marginalCVA.setMeasure(MeasuresAccessor.Measure.MARGINAL_CVA_unit);
                                        marginalCVA.setName(MeasuresAccessor.Measure.MARGINAL_CVA_unit.name());
                                        pricerRes.put(MeasuresAccessor.Measure.MARGINAL_CVA_unit, new MeasureValue[]{marginalCVA});
                                        cva = lineCvaMeasureValue.getMeasureValue().multiply(npv);

                                        cvaMeasureValue = (MeasureValue) lineCvaMeasureValue.clone();
                                        cvaMeasureValue.setMeasure(MeasuresAccessor.Measure.CVA);
                                        cvaMeasureValue.setName(MeasuresAccessor.Measure.CVA.name());
                                        cvaMeasureValue.setMeasureValue(cva);
                                        pricerRes.put(MeasuresAccessor.Measure.CVA, new MeasureValue[]{cvaMeasureValue});

                                        MeasureValue marginalCvaMeasureValue = (MeasureValue) cvaMeasureValue.clone();
                                        marginalCvaMeasureValue.setMeasure(MeasuresAccessor.Measure.MARGINAL_CVA);
                                        marginalCvaMeasureValue.setName(MeasuresAccessor.Measure.MARGINAL_CVA.name());
                                        pricerRes.put(MeasuresAccessor.Measure.MARGINAL_CVA, new MeasureValue[]{marginalCvaMeasureValue});
                                    }
                                    // credit line : sum npv by exposures
                                    if (cva != null && (creditLineCol > 0 || !creditLinesUnderlyings.isEmpty())) {

                                        if (reportSettings.includeCounterpartyInCreditIndicators()) {
                                            BigDecimal sumCounterpartyCVA = totalCVAByCounterpartyMap.get(priceable.getCounterparty().getLegalEntityId());
                                            if (sumCounterpartyCVA == null) {
                                                if (isSet) {
                                                    totalCVAByCounterpartyMap.put(priceable.getCounterparty().getLegalEntityId(), totalCVA);
                                                } else {
                                                    totalCVAByCounterpartyMap.put(priceable.getCounterparty().getLegalEntityId(), cva);
                                                }
                                            } else if (!isSet) {
                                                totalCVAByCounterpartyMap.put(priceable.getCounterparty().getLegalEntityId(), sumCounterpartyCVA.add(cva));
                                            }
                                            TemplateColumnItem item = creditLinesUnderlyings.get(priceable.getCounterparty().getLegalEntityId());
                                            if (item != null) {
                                                if (cvaMeasureValue != null) {
                                                    MeasureValue singleCreditLine = (MeasureValue) cvaMeasureValue.clone();
                                                    BigDecimal creditLine = ReportBuilder.convertField(reportSettings.getCurrency(), priceable.getProduct().getNotionalCurrency(), singleCreditLine.getMeasureValue(), valueDate, mapFX);
                                                    singleCreditLine.setMeasureValue(creditLine);
                                                    singleCreditLine.setMeasure(MeasuresAccessor.getMeasureByName(item.getName()));
                                                    singleCreditLine.setName(item.getName());
                                                    pricerRes.put(MeasuresAccessor.Measure.SINGLE_CREDIT_LINE, new MeasureValue[]{singleCreditLine});
                                                }
                                            }
                                        }
                                    }
                                    pricerResultsByTrade.put(priceable.getId(), pricerRes);
                                } else {
                                    logger.error("Problem calculating cva on id " + priceable.getId() + " no pricing result");
                                }
                            }
                        } catch (Exception e) {
                            logger.error("Error calculating cva on id " + priceable.getId());
                            logger.error(StringUtils.formatErrorMessage(e));
                        }
                    }

                // credit lines : loop on counterparties / issuers
                    // fill the fields map
                    if (creditLineCol > 0) {
                        List<ReportLine> list = additionalReportData.get("getCounterparty");
                        if (list == null) {
                            list = new ArrayList();
                            additionalReportData.put("getCounterparty", list);
                            additionalReportData.put("getIssuer", list);
                        }
                        // when in counterparties and issuers
                        for (Entry<Integer, BigDecimal> entry : totalCVAByCounterpartyMap.entrySet()) {
                            BigDecimal creditLine = entry.getValue();
                            BigDecimal expoCDS = sumCreditLineMap.get(entry.getKey());
                            if (expoCDS != null) {
                                creditLine = creditLine.add(expoCDS);
                                // remove from map
                                sumCreditLineMap.remove(entry.getKey());
                            }
                            ReportLine line = new ReportLine();
                            LegalEntity counterparty = LegalEntityAccessor.getLegalEntityById(entry.getKey());
                            line.setId(creditLineCol);
                            line.setLineData(counterparty);
                            MeasureValue creditLineMV = new MeasureValue();
                            creditLineMV.setCurrency(reportSettings.getCurrency());
                            creditLineMV.setMeasure(MeasuresAccessor.Measure.CREDIT_LINE);
                            creditLineMV.setName(MeasuresAccessor.Measure.CREDIT_LINE.name());
                            creditLineMV.setValDate(valueDate);
                            creditLineMV.setMeasureValue(creditLine);
                            Map<String, Object> map = new HashMap();
                            map.put(MeasuresAccessor.Measure.CREDIT_LINE.name(), creditLineMV);
                            line.setObjectMap(map);
                            list.add(line);
                        }
                        // case when in issuers and not in counterparties
                        for (Entry<Integer, BigDecimal> entry : sumCreditLineMap.entrySet()) {
                            ReportLine line = new ReportLine();
                            LegalEntity counterparty = LegalEntityAccessor.getLegalEntityById(entry.getKey());
                            line.setId(creditLineCol);
                            line.setLineData(counterparty);
                            MeasureValue creditLineMV = new MeasureValue();
                            creditLineMV.setCurrency(reportSettings.getCurrency());
                            creditLineMV.setMeasure(MeasuresAccessor.Measure.CREDIT_LINE);
                            creditLineMV.setName(MeasuresAccessor.Measure.CREDIT_LINE.name());
                            creditLineMV.setValDate(valueDate);
                            creditLineMV.setMeasureValue(entry.getValue());
                            Map<String, Object> map = new HashMap();
                            map.put(MeasuresAccessor.Measure.CREDIT_LINE.name(), creditLineMV);
                            line.setObjectMap(map);
                            list.add(line);
                        }
                    }
                // if credit exposures
                    //credit exposure / jump to default : sum credit and counterparty

                    if (creditExposureCol > 0 || creditJumpToDefaultCol > 0) {

                        for (Entry<Integer, BigDecimal> entry : sumCounterpartyNPVMap.entrySet()) {
                            // jump to default
                            BigDecimal jumpToDefault = sumJumpToDefaultMap.get(entry.getKey());
                            if (jumpToDefault != null) {
                                sumJumpToDefaultMap.put(entry.getKey(), jumpToDefault.add(entry.getValue().negate().multiply(defaultOneMinusRecoveryRate)));
                            } else {
                                sumJumpToDefaultMap.put(entry.getKey(), entry.getValue().negate().multiply(defaultOneMinusRecoveryRate));
                            }
                            //expo
                            BigDecimal exposure = sumCreditExposureMap.get(entry.getKey());
                            if (exposure != null) {
                                sumCreditExposureMap.put(entry.getKey(), exposure.add(entry.getValue().negate()));
                            } else {
                                sumCreditExposureMap.put(entry.getKey(), entry.getValue().negate());
                            }
                        }
                        List<ReportLine> list = additionalReportData.get("getCounterparty");
                        if (list == null) {
                            list = new ArrayList();
                            additionalReportData.put("getCounterparty", list);
                            additionalReportData.put("getIssuer", list);
                        }
                        if (creditExposureCol > 0) {
                            for (Entry<Integer, BigDecimal> entry : sumCreditExposureMap.entrySet()) {
                                ReportLine line = new ReportLine();
                                LegalEntity counterparty = LegalEntityAccessor.getLegalEntityById(entry.getKey());
                                line.setId(creditExposureCol);
                                line.setLineData(counterparty);
                                MeasureValue creditExpoMV = new MeasureValue();
                                creditExpoMV.setCurrency(reportSettings.getCurrency());
                                creditExpoMV.setMeasure(MeasuresAccessor.Measure.CREDIT_EXPOSURE);
                                creditExpoMV.setName(MeasuresAccessor.Measure.CREDIT_EXPOSURE.name());
                                creditExpoMV.setValDate(valueDate);
                                creditExpoMV.setMeasureValue(entry.getValue().negate());
                                Map<String, Object> map = new HashMap();
                                map.put(MeasuresAccessor.Measure.CREDIT_EXPOSURE.name(), creditExpoMV);
                                line.setObjectMap(map);
                                list.add(line);
                            }
                        }
                        if (creditJumpToDefaultCol > 0) {
                            for (Entry<Integer, BigDecimal> entry : sumJumpToDefaultMap.entrySet()) {
                                LegalEntity counterparty = LegalEntityAccessor.getLegalEntityById(entry.getKey());

                                // jump to default
                                MeasureValue jumpToDefaultMV = new MeasureValue();
                                jumpToDefaultMV.setMeasure(MeasuresAccessor.Measure.JUMP_TO_DEFAULT);
                                jumpToDefaultMV.setName(MeasuresAccessor.Measure.JUMP_TO_DEFAULT.name());
                                jumpToDefaultMV.setValDate(valueDate);
                                jumpToDefaultMV.setCurrency(reportSettings.getCurrency());
                                jumpToDefaultMV.setMeasureValue(entry.getValue());
                                Map<String, Object> map2 = new HashMap();
                                map2.put(MeasuresAccessor.Measure.JUMP_TO_DEFAULT.name(), jumpToDefaultMV);
                                ReportLine line2 = new ReportLine();
                                line2.setId(creditJumpToDefaultCol);
                                line2.setLineData(counterparty);
                                line2.setObjectMap(map2);
                                list.add(line2);
                            }
                        }
                    }
                }
            }
            logger.info("CVA ENDED AT " + DateUtils.getTime());
        }
    }
}
