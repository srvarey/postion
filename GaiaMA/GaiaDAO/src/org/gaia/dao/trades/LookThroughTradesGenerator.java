/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, jComboBoxIssuer but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.trades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.utils.BatchLogAccessor;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.referentials.PdfDocConfig;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEquity;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeAttribute;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.Mapping;
import org.gaia.domain.utils.StringUtils;

/**
 * @author Zakaria Laguel
 */
public final class LookThroughTradesGenerator {

    private static final Logger logger = Logger.getLogger(LookThroughTradesGenerator.class);
    public List<Position> positionList;
    public Calendar _calendar;

    public static final String GENERATE_LOOKTHROUGH_TRADES = "generateLookThroughTrades";

    public static void generateLookThroughTrades(PdfDocConfig pdfDocConfig, Map<String, BigDecimal> quantityMap, Date positionDateEOM) {
        Integer logId = BatchLogAccessor.createBatchLog(null, PdfDocConfig.class, pdfDocConfig.getPdfDocConfigId(), positionDateEOM,
                        pdfDocConfig.getPortofolio().getShortName()+"/"+HibernateUtil.dateFormat.format(positionDateEOM));

        // look if all the positions in my portfolio have quantities
        // missing ones have to be set to zero
        Map<String, String> mapping = MappingsAccessor.getMappingMapByName(pdfDocConfig.getMappingName());
        List<Position> posList =PositionBuilder.getPositionsByInternalCounterparty(pdfDocConfig.getPortofolio().getLegalEntityId());
        for (Position pos : posList){
            if (quantityMap.get(pos.getProduct().getShortName())==null&&mapping.containsValue(pos.getProduct().getShortName())){
                quantityMap.put(pos.getProduct().getShortName(), BigDecimal.ZERO);
            }
        }
        for (Entry<String, BigDecimal> entry : quantityMap.entrySet()) {
            String productName = entry.getKey();
            BigDecimal targetQuantity = entry.getValue();

            try {
                Product product = ProductAccessor.getProductByShortName(productName);
                if (product == null) {
                    logger.error("LookThroughTradesGenerator.generateLookThroughTrades Error : Product does not exist for country " + productName);
                } else {
                    // trade creation
                    Trade lookThroughTrade = PositionBuilder.setPosition(positionDateEOM, product, pdfDocConfig.getPortofolio(), targetQuantity);
                    // set the attribute
                    if (lookThroughTrade != null) {
                        TradeAttribute attribute = new TradeAttribute();
                        attribute.setAttributeName("Transparency Config");
                        attribute.setTrade(lookThroughTrade);
                        attribute.setAttributeValue(pdfDocConfig.getConfigName());
                        lookThroughTrade.getTradeAttributes().add(attribute);
                        TradeAccessor.storeTrade(lookThroughTrade);
                    }
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
        BatchLogAccessor.endBatchLog(logId);
    }

    public static final String UPDATE_COUNTRY_INDEX_COMPOSITION = "updateCountryIndexComposition";

    public static void updateCountryIndexComposition(PdfDocConfig pdfDocConfig, Map<String, BigDecimal> quantityMap) {

        // set the position date to the last EOM
        List<String> domainValue = DomainValuesAccessor.getDomainValuesByName("LookThroughMappingName");
        if (StringUtils.isEmpty(domainValue)) {
            logger.error("Look Through Mapping Name Domain value not defined");
            return;
        } else if (domainValue.size() > 1) {
            logger.warn("Many defined values in LookThroughMappingName Domain value ... take the first One");
        }
        // looks for the index with the fund portfolio
        ProductEquity productEquity = (ProductEquity) HibernateUtil.getObjectWithQuery("from ProductEquity pe where pe.portfolio.legalEntityId=" + pdfDocConfig.getPortofolio().getLegalEntityId());
        Product index = productEquity.getProduct().getUnderlying();

        List<Product> productList = new ArrayList();
        for (Entry<String, BigDecimal> entry : quantityMap.entrySet()) {
            String country = entry.getKey();
            BigDecimal targetQuantity = entry.getValue();
            Mapping mapping;
            try {
                mapping = MappingsAccessor.getMappingByNameAndKey(domainValue.get(0), country);
                if (mapping == null) {
                    logger.error("Mapping country does not exist");
                    return;
                }
                Product product = ProductAccessor.getProductByShortName(mapping.getValue());
                if (product == null) {
                    logger.error("LookThroughTradesGenerator.generateLookThroughTrades Error : Product does not exist for country " + country);
                } else {
                    productList.add(product);
                    boolean found = false;
                    for (ProductUnderlying component : index.getUnderlyingProducts()) {
                        if (component.getUnderlying().getProductId() == product.getProductId()) {
                            component.setWeight(targetQuantity);
                            found = true;
                        }
                    }
                    if (!found) {
                        index.addUnderlyingWithWeight(product, targetQuantity);
                    }

                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }

        // loop to retrieve components that are no more in the index composition
        for (ProductUnderlying component : index.getUnderlyingProducts()) {
            boolean found = false;
            for (Product product : productList) {
                if (component.getUnderlying().getProductId() == product.getProductId()) {
                    found = true;
                }
            }
            if (!found) {
                component.setWeight(BigDecimal.ZERO);
            }
        }
    }
}
