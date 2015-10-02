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
package org.gaia.markit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityAttribute;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.Message;
import org.gaia.markit.generated.ComponentType;
import org.gaia.markit.generated.Data;
import org.gaia.markit.generated.IndexType;

/**
 *
 * @author Jawhar Kamoun
 */
public class MarkitIndexDefinitionImporter {

    private static final String RED_CODE = "RED";
    public static final String IMPORT_INDEX_DEF = "importIndexDef";
    private static final Logger logger = Logger.getLogger(MarkitIndexDefinitionImporter.class);

    public static List<Message> importIndexDef(String filePath) {
        StringBuilder errorText = new StringBuilder();
        Object indexObj;
        Data indexDefinition = new Data();
        try {
            JAXBContext jcIndex = JAXBContext.newInstance("org.gaia.markit.generated", indexDefinition.getClass().getClassLoader());
            Unmarshaller uIndex = jcIndex.createUnmarshaller();
            indexObj = uIndex.unmarshal(new FileReader(filePath));
            if (indexObj == null || !(indexObj instanceof Data)) {
                errorText.append("Invalid data Type ");
            } else {
                indexDefinition = (Data) indexObj;
                List<IndexType> indexList = indexDefinition.getIndex();
                for (IndexType indexType : indexList) {
                    createOrUpdateIndex(indexType, errorText);
                }

            }
        } catch (JAXBException | FileNotFoundException e) {
            logger.error(StringUtils.formatErrorMessage(e));
            return ReportUtils.makeErrorMessageList(e);
        }
        return ReportUtils.makeMessageList(errorText.toString());
    }

    private static void createOrUpdateIndex(IndexType objIndex, StringBuilder errorText) {
        Product indexProduct = ProductAccessor.getProductByShortName(objIndex.getIndexname());
        ProductCredit creditProduct = null;
        ProductReference referenceProduct = null;
        ProductRate rateProduct = null;
        ProductCurve curveProduct = null;
        Scheduler scheduler = null;
        LegalEntity issuer = LegalEntityAccessor.getLegalEntityByShortName(objIndex.getFamily());
        if (issuer == null) {
            issuer = createIssuerFromIndexType(objIndex);
        }
        if (indexProduct == null) {
            indexProduct = new Product();
            indexProduct.setShortName(objIndex.getIndexname());
            indexProduct.setNotionalMultiplier(BigDecimal.ONE);
            indexProduct.setNotionalCurrency(objIndex.getCcy());
            indexProduct.setProductType(ProductTypeUtil.ProductType.CDS_INDEX.getName());
            indexProduct.setIsAsset(Boolean.TRUE);
            indexProduct.setQuantityType(Trade.QuantityType.NOTIONAL.name());
            indexProduct.setQuotationType(MarketQuote.QuotationType.SPREAD.getName());
            indexProduct.setSettlementDelay((short) 2);

        } else {
            creditProduct = indexProduct.getProductCredit();
            List<ProductReference> references = ProductAccessor.getProductReferences(indexProduct.getProductId());
            indexProduct.setProductReferences(references);
            referenceProduct = indexProduct.loadProductReference(RED_CODE);
            rateProduct = indexProduct.getProductRate();
            curveProduct = indexProduct.getProductCurve();
            scheduler = indexProduct.getScheduler();
            creditProduct.setIssuer(issuer);

        }
        if (creditProduct == null) {
            creditProduct = new ProductCredit();
            creditProduct.setProduct(indexProduct);
            creditProduct.setIssuer(issuer);
        }
        if (referenceProduct == null) {
            referenceProduct = new ProductReference();
            referenceProduct.setProduct(indexProduct);
            referenceProduct.setReferenceType(RED_CODE);
        }
        if (rateProduct == null) {
            rateProduct = new ProductRate();
            rateProduct.setProduct(indexProduct);
        }
        if (scheduler == null) {
            scheduler = new Scheduler();
        }
        if (curveProduct == null) {
            curveProduct = new ProductCurve();
            curveProduct.setProduct(indexProduct);
        }
        creditProduct.setSerie((short) objIndex.getSeries());
        creditProduct.setVersion((short) objIndex.getVersion());

        indexProduct.setNotionalCurrency(objIndex.getCcy());
        indexProduct.setNotionalMultiplier(objIndex.getIndexfactor());
        indexProduct.setStartDate(objIndex.getTerms().getEffective().toGregorianCalendar().getTime());
        indexProduct.setMaturityDate(objIndex.getTerms().getMaturity().toGregorianCalendar().getTime());

        referenceProduct.setValue(objIndex.getRed());
        rateProduct.setRate(objIndex.getTerms().getFixedrate());
        curveProduct.setTenor(objIndex.getTerms().getPeriod());
        scheduler.setFrequency(objIndex.getTerms().getFrequency());
        createOrUpdateUnderlying(objIndex, indexProduct);

        indexProduct.setProductCredit(creditProduct);
        indexProduct.setScheduler(scheduler);
        indexProduct.setProductCurve(curveProduct);
        indexProduct.setProductRate(rateProduct);
        indexProduct.addProductReference(referenceProduct);

        ProductAccessor.storeProduct(indexProduct);
        errorText.append("index Definition ").append(indexProduct.getShortName()).append(" saved");

    }

    private static void createOrUpdateUnderlying(IndexType objIndex, Product indexProduct) {
        List<ComponentType> componentList = objIndex.getComponent();
        indexProduct.getUnderlyingProducts().clear();
        for (ComponentType componentType : componentList) {
            LegalEntity issuer = LegalEntityAccessor.getLegalEntityByShortName(componentType.getRefentity().getName());
            Product underlying = null;
            if (issuer == null) {
                issuer = createIssuerFromComponent(componentType);
            } else {
                underlying = ProductAccessor.findCDSProductByIssuerAndMaturityDate(issuer, indexProduct.getMaturityDate());

            }
            if (underlying == null) {
                underlying = ProductAccessor.buildIndexUnderlyingCDSProduct(indexProduct, issuer);
            }

            indexProduct.addUnderlyingWithWeight(underlying, componentType.getRefentity().getWeight());

        }
    }

    private static LegalEntity createIssuerFromComponent(ComponentType component) {
        LegalEntity issuer = new LegalEntity();
        issuer.setShortName(component.getRefentity().getName());
        issuer.setEnteredDate(new Date());
        issuer.setCountry(component.getRefentity().getCountry());
        issuer.setLegalEntityStatus(ProductConst.ProductStatus.Active.name());
        LegalEntityRole role = new LegalEntityRole(issuer, LegalEntityRole.LegalEntityRoleEnum.ISSUER_ROLE.name);
        issuer.getRoles().add(role);

        LegalEntityAttribute redTicker = new LegalEntityAttribute();
        redTicker.setAttributeName("RED Ticker");
        redTicker.setAttributeValue(component.getRefentity().getTicker());

        LegalEntityAttribute red = new LegalEntityAttribute();
        red.setAttributeName(RED_CODE);
        red.setAttributeValue(component.getRefentity().getRed());

        LegalEntityAttribute redPair = new LegalEntityAttribute();
        redPair.setAttributeName("RED Pair");
        redPair.setAttributeValue(component.getBond().getIssuer().getPairred());
        LegalEntityAccessor.storeLegalEntity(issuer);

        issuer.addLegalEntityAttribute(red);
        issuer.addLegalEntityAttribute(redPair);
        issuer.addLegalEntityAttribute(redTicker);
        LegalEntityAccessor.storeLegalEntity(issuer);
        return LegalEntityAccessor.getLegalEntityByShortName(issuer.getShortName());
    }

    private static LegalEntity createIssuerFromIndexType(IndexType indexType) {
        LegalEntity issuer = new LegalEntity();
        issuer.setShortName(indexType.getFamily());
        issuer.setEnteredDate(new Date());
        issuer.setLegalEntityStatus(ProductConst.ProductStatus.Active.name());
        LegalEntityRole role = new LegalEntityRole(issuer, LegalEntityRole.LegalEntityRoleEnum.ISSUER_ROLE.name);
        issuer.getRoles().add(role);
        LegalEntityAccessor.storeLegalEntity(issuer);
        return LegalEntityAccessor.getLegalEntityByShortName(issuer.getShortName());
    }
}
