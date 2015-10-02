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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.reports.ReportLine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class UnitMeasuresMultiplicator extends AbstractPostPricingTreatment{

    private static final Logger logger = Logger.getLogger(UnitMeasuresMultiplicator.class);
    public static void calculate(LinkedHashMap<Integer,ReportLine> reportLines,Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade,Date valueDate, Map<String, BigDecimal> mapFX, String reportCurrency){

        Map<IPricerMeasure,IMeasureValue[]> resToadd = null;
        IMeasureValue[] pricerMeasureValues = null;
        IPricerMeasure smeasure = null;
        IMeasureValue newpmv = null;
        /**
         *  trade pricing output : measure / meausre value
         */
        Map<IPricerMeasure, IMeasureValue[]> pricerRes = null;

        for (ReportLine line : reportLines.values()) {
            IPriceable priceable=(IPriceable)line.getLineData();
            try {
                pricerRes = (Map) pricerResultsByTrade.get(priceable.getId());
                if (pricerRes != null) {
                    resToadd = new HashMap();
                    for (Entry<IPricerMeasure, IMeasureValue[]> entry : pricerRes.entrySet()){
                        pricerMeasureValues =  entry.getValue();
                        List<IMeasureValue> amountMeasures =new ArrayList();
                        for (IMeasureValue pricerMeasureValue : pricerMeasureValues){

                            /**
                             * unitary pricer measures => x trade quantity
                             */
                            if (pricerMeasureValue.getMeasure().isUnit() && pricerMeasureValue.getMeasureValue()!=null) {
                                smeasure = pricerMeasureValue.getMeasure().getLinkedAmountMeasure();
                                newpmv = (IMeasureValue) pricerMeasureValue.clone();
                                newpmv.setMeasure(smeasure);
                                if (pricerMeasureValue.getQualifier()!=null&&!pricerMeasureValue.getQualifier().isEmpty()){
                                    newpmv.setName(smeasure.getName()+StringUtils.DOT+pricerMeasureValue.getQualifier());
                                }else{
                                    newpmv.setName(smeasure.getName());
                                }
                                if (priceable.getQuantity(valueDate)!=null){
                                    newpmv.multiplyValueBy(priceable.getQuantity(valueDate)); /** m= pm% x amount */
                                }else {
                                    newpmv.setMeasureValue(BigDecimal.ZERO);
                                }
                                amountMeasures.add(newpmv);
                            }
                        }
                        if (amountMeasures.size()>0){
                            resToadd.put(entry.getKey().getLinkedAmountMeasure(),amountMeasures.toArray(new IMeasureValue[amountMeasures.size()]));
                        }
                    }
                    pricerRes.putAll(resToadd);
                    pricerResultsByTrade.put(priceable.getId(), pricerRes);
                }
            } catch (Exception e) {
                logger.error("post pricing error on id " + priceable.getId()+StringUtils.SPACE+StringUtils.formatErrorMessage(e));
            }
        }
    }
}