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
package org.gaia.io.dtcc5_3.products;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.JAXBElement;
import org.apache.log4j.Logger;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductSingleTraded;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.fpml.fpml_5.recordkeeping.CashSettlementTerms;
import org.gaia.fpml.fpml_5.recordkeeping.CreditDefaultSwap;
import org.gaia.fpml.fpml_5.recordkeeping.IndexId;
import org.gaia.fpml.fpml_5.recordkeeping.IndexName;
import org.gaia.fpml.fpml_5.recordkeeping.SettlementTerms;
import org.gaia.fpml.fpml_5.recordkeeping.Tranche;
import org.gaia.fpml.fpml_5.recordkeeping.UnderlyingAsset;
import org.gaia.io.dtcc5_3.XMLDTCC;

/**
 *
 * @author Benjamin Frerejean
 */
public class CreditDefaultSwapLoader {

    public static final DateFormat dateFormat = XMLDTCC.dateFormat;
    private static final Logger logger = Logger.getLogger(CreditDefaultSwapLoader.class);

    public static Product read(CreditDefaultSwap CDS) {
        Product product = new Product();

        IndexName indexName = null;
        IndexId indexId=null;
        Tranche tranche=null;
        if (CDS.getGeneralTerms().getIndexReferenceInformation()!=null
                && CDS.getGeneralTerms().getIndexReferenceInformation().getContent()!=null){
            for (JAXBElement elt : CDS.getGeneralTerms().getIndexReferenceInformation().getContent()){
                if (elt.getValue() instanceof IndexName){
                    indexName =(IndexName)elt.getValue();
                } else if (elt.getValue() instanceof IndexId){
                    indexId=(IndexId) elt.getValue();
                } else if (elt.getValue() instanceof Tranche){
                    tranche=(Tranche) elt.getValue();
                }
            }
        }

        if (CDS.getProductId().get(0).getValue().contains("Credit:Index:")) {
            String ref = CDS.getGeneralTerms().getIndexReferenceInformation().getId();
            product = ProductAccessor.getProductByReference(ref);
            if (product==null){
                product = new Product();
                ProductCredit productCredit=new ProductCredit();
                product.getProductCredits().add(productCredit);
                Calendar cal=CDS.getGeneralTerms().getScheduledTerminationDate().getUnadjustedDate().getValue().toGregorianCalendar();
                if (indexName!=null){
                    product.setShortName(indexName.getValue()+StringUtils.SPACE+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
                    LegalEntity issuer=LegalEntityAccessor.getLegalEntityByShortName(indexName.getValue());
                    productCredit.setIssuer(issuer);
                }

                product.setIsAsset(Boolean.TRUE);
                product.setNotionalMultiplier(BigDecimal.ONE);
                product.setMaturityDate(cal.getTime());
                product.setNotionalCurrency(CDS.getProtectionTerms().get(0).getCalculationAmount().getCurrency().getValue());
                product.setProductType(ProductTypeUtil.ProductType.CDS_INDEX.name);
                product.setQuantityType("Notional");
                product.setSettlementDelay(new Short("2"));
                productCredit.setSerie(new Short("1"));
                productCredit.setVersion(new Short("1"));;
                product.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());

            }


        } else if (CDS.getProductId().get(0).getValue().contains("Credit:SingleName:")
                || CDS.getProductId().get(0).getValue().contains("Credit:IndexTranche:")) {

            if (CDS.getProductId().get(0).getValue().contains("Credit:SingleName:Corporate")) {
                product.setProductType(ProductTypeUtil.ProductType.CUSTOM_CDS.name);
            } else if (CDS.getProductId().get(0).getValue().contains("Credit:SingleName:Loans")) {
                product.setProductType(ProductTypeUtil.ProductType.LOAN_CDS.name);
            } else if (CDS.getProductId().get(0).getValue().contains("Credit:SingleName:RecoveryCDS:FixedRecoverySwaps")) {
                product.setProductType(ProductTypeUtil.ProductType.CDS_FIXED_RECOVERY.name);
            } else if (CDS.getProductId().get(0).getValue().contains("Credit:SingleName:RecoveryCDS:RecoveryLocks")) {
                product.setProductType(ProductTypeUtil.ProductType.CDS_RECOVERY_LOCKS.name);
            } else if (CDS.getProductId().get(0).getValue().contains("Credit:IndexTranche")) {
                product.setProductType(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH.name);
            }

            product.setIsAsset(Boolean.FALSE);

            Product leg1 = new Product();
            leg1.setProductType(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name);
            leg1.setNotionalMultiplier(BigDecimal.ONE);

            Product leg2 = new org.gaia.domain.trades.Product();
            leg2.setProductType(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name);
            leg2.setNotionalMultiplier(BigDecimal.ONE.negate());

            product.setNotionalMultiplier(BigDecimal.ONE);
            leg1.setNotionalMultiplier(BigDecimal.ONE);
            leg2.setNotionalMultiplier(BigDecimal.ONE.negate());

            product.setNotionalCurrency(CDS.getProtectionTerms().get(0).getCalculationAmount().getCurrency().getValue());
            leg1.setNotionalCurrency(product.getNotionalCurrency());
            leg2.setNotionalCurrency(product.getNotionalCurrency());

            product.setQuantityType(Trade.QuantityType.NOTIONAL.name);
            leg1.setQuantityType(product.getQuantityType());
            leg2.setQuantityType(product.getQuantityType());

            product.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());
            leg1.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());
            leg2.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());

            product.setSubProducts(new ArrayList());
            product.getSubProducts().add(leg1);
            product.getSubProducts().add(leg2);

            try {

                product.setMaturityDate(CDS.getGeneralTerms().getScheduledTerminationDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
                leg1.setMaturityDate(product.getMaturityDate());
                leg2.setMaturityDate(product.getMaturityDate());

                product.setStartDate(CDS.getGeneralTerms().getEffectiveDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
                leg1.setStartDate(product.getStartDate());
                leg2.setStartDate(product.getStartDate());

                /** leg 1 : funding */
                Scheduler schedule = new Scheduler();
                if (CDS.getGeneralTerms().getDateAdjustments() != null) {
                    schedule.setAdjustment(DTCCMappings.getAdjustmentFromDTCC(CDS.getGeneralTerms().getDateAdjustments().getBusinessDayConvention().name()));
                }
                schedule.setDaycount("30/360");
                String tenor = CDS.getFeeLeg().getPeriodicPayment().getPaymentFrequency().getPeriodMultiplier() + CDS.getFeeLeg().getPeriodicPayment().getPaymentFrequency().getPeriod().name();
                schedule.setFrequency(FrequencyUtil.getFrequencyFromTenor(tenor));
                schedule.setFirstDate(CDS.getFeeLeg().getPeriodicPayment().getFirstPaymentDate().toGregorianCalendar().getTime());
                leg1.setScheduler(schedule);

                ProductSingleTraded pst = new ProductSingleTraded();
                pst.setRate(CDS.getFeeLeg().getPeriodicPayment().getFixedAmountCalculation().getFixedRate().getValue());
                pst.setPayoffDateClause("OnScheduleDates");
                pst.setPayoffFormula("Notional*Rate*schedulePeriod");
                leg1.setProductSingleTraded(pst);
                pst.setProduct(leg1);

                /** leg 2 : protection */

                ProductCredit cc = new ProductCredit();
                String issuerName;
                /** singles */
                if (CDS.getGeneralTerms().getReferenceInformation() != null) {
                    issuerName = CDS.getGeneralTerms().getReferenceInformation().getReferenceEntity().getEntityName().getValue();
                    LegalEntity issuer = LegalEntityAccessor.getLegalEntityByShortName(issuerName);
                    if (issuer != null) {
                        cc.setIssuer(issuer);
                    } else {
                        String issuerRef = CDS.getGeneralTerms().getReferenceInformation().getReferenceEntity().getEntityId().get(0).getValue();
                        issuer = LegalEntityAccessor.getLegalEntityByAttribute(issuerRef);
                        if (issuer != null) {
                            cc.setIssuer(issuer);
                        }
                    }
                /** index tranches */
                }else if (CDS.getGeneralTerms().getIndexReferenceInformation() != null) {
                    Product indexSubProduct = null;
                    if (indexId!=null){
                        indexSubProduct = ProductAccessor.getProductByReference(indexId.getValue());
                    }
                    if (indexSubProduct==null && indexName!=null){
                        indexSubProduct = ProductAccessor.getProductByShortName(indexName.getValue());
                    }
                    if (indexSubProduct == null) {
                        System.err.println("Index id " + indexId + " not found");
                        return null;
                    }
                    if (leg2.getSubProducts() == null) {
                        leg2.setSubProducts(new ArrayList<Product>());
                    }
                    leg2.getSubProducts().add(indexSubProduct);

                    /** case of tranches */
                    if (tranche != null) {
                        cc.setAttachmentPoint(tranche.getAttachmentPoint());
                        cc.setExhaustionPoint(tranche.getExhaustionPoint());
                    }
                }
                /** case of recovery swaps */

                if (CDS.getCashSettlementTermsOrPhysicalSettlementTerms() != null
                        && CDS.getCashSettlementTermsOrPhysicalSettlementTerms().size()>0) {
                    SettlementTerms settlementTerms = CDS.getCashSettlementTermsOrPhysicalSettlementTerms().get(0);
                    if (settlementTerms instanceof CashSettlementTerms){
                        cc.setRecoveryFactor(((CashSettlementTerms)settlementTerms).getRecoveryFactor());
                    }
                }
                leg2.setProductCredit(cc);
                cc.setProduct(leg2);

                /** add underlying reference bond or loans */
                if (CDS.getGeneralTerms().getReferenceInformation() != null) {
                    leg2.setSubProducts(new ArrayList());
                    if (CDS.getGeneralTerms().getReferenceInformation().getReferenceObligation() != null) {
                        String ref = null;
                        UnderlyingAsset underlyingAsset = null;
                        if (CDS.getGeneralTerms().getReferenceInformation().getReferenceObligation().get(0).getBond() != null) {
                            underlyingAsset = CDS.getGeneralTerms().getReferenceInformation().getReferenceObligation().get(0).getBond();
                        } else if (CDS.getGeneralTerms().getReferenceInformation().getReferenceObligation().get(0).getLoan() != null) {
                            underlyingAsset = CDS.getGeneralTerms().getReferenceInformation().getReferenceObligation().get(0).getLoan();
                        }
                        if (underlyingAsset != null) {
                            if (underlyingAsset.getInstrumentId() != null) {
                                Product und = null;
                                for (int i = 0; i < underlyingAsset.getInstrumentId().size(); i++) {
                                    ref = underlyingAsset.getInstrumentId().get(i).getValue();
                                    Product tmp = ProductAccessor.getProductByReference(ref);
                                    if (tmp != null) {
                                        und = tmp;
                                    }
                                }
                                if (und != null) {
                                    leg2.getSubProducts().add(und);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Error "+StringUtils.formatErrorMessage(e));
            }
        } else {
            return null;
        }
        return product;
    }
}
