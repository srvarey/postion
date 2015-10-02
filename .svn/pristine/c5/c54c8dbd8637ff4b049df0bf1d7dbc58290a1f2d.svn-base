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
package org.gaia.io.dtcc5_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeAttribute;
import org.gaia.domain.utils.Message;
import org.gaia.fpml.fpml_5.recordkeeping.AdjustableDate;
import org.gaia.fpml.fpml_5.recordkeeping.CorrectableRequestMessage;
import org.gaia.fpml.fpml_5.recordkeeping.CreditDefaultSwap;
import org.gaia.fpml.fpml_5.recordkeeping.FxOption;
import org.gaia.fpml.fpml_5.recordkeeping.FxSingleLeg;
import org.gaia.fpml.fpml_5.recordkeeping.GenericProduct;
import org.gaia.fpml.fpml_5.recordkeeping.IdentifiedDate;
import org.gaia.fpml.fpml_5.recordkeeping.NonpublicExecutionReport;
import org.gaia.fpml.fpml_5.recordkeeping.Party;
import org.gaia.fpml.fpml_5.recordkeeping.PartyTradeIdentifier;
import org.gaia.fpml.fpml_5.recordkeeping.RequestMessage;
import org.gaia.fpml.fpml_5.recordkeeping.Swap;
import org.gaia.io.dtcc5_3.products.CreditDefaultSwapLoader;
import org.gaia.io.dtcc5_3.products.FxOptionLoader;
import org.gaia.io.dtcc5_3.products.GenericProductLoader;
import org.gaia.io.dtcc5_3.products.SwapLoader;

/**
 *
 * @author Benjamin Frerejean
 */
public class XMLDTCC {

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XMLDTCC.class);
    public static final String IMPORT_DTCC = "importDTCC";

    /**
     * loaded the file
     * @param filePath
     * @return
     * @throws java.lang.Exception
     */
    public static List<Message> importDTCC(String filePath) throws Exception {
        String errorText = StringUtils.EMPTY_STRING;
        String tradeType = null;

        try {
            // read the message
            NonpublicExecutionReport nonpublicExecutionReport = (NonpublicExecutionReport) JAXBUnmarshallFile(filePath.replace('\\', '/'));

            Trade trade = new Trade();
            Product product = null;

            //internal counterparty
            LegalEntity internalCounterparty = LegalEntityAccessor.getLegalEntityByShortName(nonpublicExecutionReport.getParty().get(0).getId());
            trade.setInternalCounterparty(internalCounterparty);
            if (internalCounterparty == null) {
                errorText += "internal party not found :" + nonpublicExecutionReport.getParty().get(0).getId() + StringUtils.getNewLine();
            }

            //counterparty
            LegalEntity counterparty = LegalEntityAccessor.getLegalEntityByShortName(nonpublicExecutionReport.getParty().get(1).getId());
            trade.setCounterparty(counterparty);
            if (counterparty == null) {
                errorText += "counterparty not found :" + nonpublicExecutionReport.getParty().get(1).getId() + StringUtils.getNewLine();
            }

            // other common fields
            trade.setQuantityType(Trade.QuantityType.NOTIONAL.name);
            IdentifiedDate tradeDate = nonpublicExecutionReport.getTrade().getTradeHeader().getTradeDate();
            Date executionDateTime = nonpublicExecutionReport.getTrade().getTradeHeader().getPartyTradeInformation().iterator().next().getExecutionDateTime().getValue().toGregorianCalendar().getTime();

            if (tradeDate != null) {
                trade.setTradeDate(tradeDate.getValue().toGregorianCalendar().getTime());
                trade.setValueDate(tradeDate.getValue().toGregorianCalendar().getTime());
                trade.setTradeTime(executionDateTime);

            } else {
                trade.setTradeDate(DateUtils.getDate());
                trade.setValueDate(DateUtils.getDate());
                trade.setTradeTime(DateUtils.getDate());
            }
            trade.setIsCollateral(Boolean.FALSE);
            trade.setStatus("Validated");
            trade.setTradeType(Trade.TradeType.BUY_SELL.name);

            // attributes
            Set<TradeAttribute> tradeAttributes = new HashSet();
            trade.setTradeAttributes(tradeAttributes);

            for (PartyTradeIdentifier identifier : nonpublicExecutionReport.getTrade().getTradeHeader().getPartyTradeIdentifier()) {
                if (identifier.getPartyReference() != null) {
                    Party party = (Party) identifier.getPartyReference().getHref();
                    if (party != null && party.getId() != null && counterparty != null && internalCounterparty != null
                            && (party.getId().equalsIgnoreCase(counterparty.getShortName())
                            || party.getId().equalsIgnoreCase(internalCounterparty.getShortName()))) {
                        TradeAttribute tradeAttribute = new TradeAttribute();
                        tradeAttribute.setTrade(trade);
                        tradeAttribute.setAttributeName(party.getId());
                        tradeAttribute.setAttributeValue(identifier.getTradeId().getValue());
                        tradeAttributes.add(tradeAttribute);
                    }
                }
            }
            /**
             * PRODUCTS
             * ==============
             */
            if (nonpublicExecutionReport.getTrade().getProduct().getValue() instanceof CreditDefaultSwap) {
                /**
                 * CREDIT DEFAULT SWAP
                 */
                CreditDefaultSwap CDS = (CreditDefaultSwap) nonpublicExecutionReport.getTrade().getProduct().getValue();
                product = CreditDefaultSwapLoader.read(CDS);

                trade.setQuantity(CDS.getProtectionTerms().get(0).getCalculationAmount().getAmount());
                trade.setNegociatedPriceType(MarketQuote.QuotationType.SPREAD.getName());

                if (CDS.getFeeLeg().getSinglePayment() != null && CDS.getFeeLeg().getSinglePayment().size() > 0) {
                    trade.setAmount(CDS.getFeeLeg().getSinglePayment().get(0).getFixedAmount().getAmount());
                    trade.setSettlementCurrency(CDS.getFeeLeg().getSinglePayment().get(0).getFixedAmount().getCurrency().getValue());
                } else if (CDS.getFeeLeg().getInitialPayment() != null) {
                    trade.setAmount(CDS.getFeeLeg().getInitialPayment().getPaymentAmount().getAmount());
                    trade.setSettlementCurrency(CDS.getFeeLeg().getInitialPayment().getPaymentAmount().getCurrency().getValue());
                } else {
                    trade.setAmount(BigDecimal.ZERO);
                    trade.setSettlementCurrency(CDS.getProtectionTerms().get(0).getCalculationAmount().getCurrency().getValue());
                }

            } else if (nonpublicExecutionReport.getTrade().getProduct().getValue() instanceof Swap) {
                /**
                 * IR SWAP
                 */
                Swap irSwap = (Swap) nonpublicExecutionReport.getTrade().getProduct().getValue();
                product = SwapLoader.read(irSwap);
                trade.setNegociatedPriceType(MarketQuote.QuotationType.SPREAD.getName());

                trade.setQuantity(irSwap.getSwapStream().get(0).getCalculationPeriodAmount().getCalculation().getNotionalSchedule().getNotionalStepSchedule().getInitialValue());
                trade.setAmount(irSwap.getAdditionalPayment().get(0).getPaymentAmount().getAmount());
                trade.setSettlementCurrency(irSwap.getAdditionalPayment().get(0).getPaymentAmount().getCurrency().getValue());
                JAXBElement elt = irSwap.getAdditionalPayment().get(0).getPaymentDate().getContent().get(0);
                if (elt.getValue() instanceof AdjustableDate) {
                    trade.setValueDate(((AdjustableDate) elt.getValue()).getAdjustedDate().getValue().toGregorianCalendar().getTime());
                } else if (elt.getValue() instanceof IdentifiedDate) {
                    trade.setValueDate(((IdentifiedDate) elt.getValue()).getValue().toGregorianCalendar().getTime());
                }
                /**
                 * set the way of legs
                 */
                int i = 0;
                if (product != null && product.getSubProducts() != null) {
                    for (Product component : product.getSubProducts()) {
                        if (((Party) irSwap.getSwapStream().get(i).getPayerPartyReference().getHref()).getId().equalsIgnoreCase(internalCounterparty.getShortName())) {
                            component.setNotionalMultiplier(BigDecimal.ONE.negate());
                        }
                        i++;
                    }
                }

            } else if (nonpublicExecutionReport.getTrade().getProduct().getValue() instanceof FxSingleLeg) {
                /**
                 * FX forwards
                 */
                FxSingleLeg fxSingleLeg = (FxSingleLeg) nonpublicExecutionReport.getTrade().getProduct().getValue();

                String currencyPair = fxSingleLeg.getExchangedCurrency1().getPaymentAmount().getCurrency().getValue() + "/" + fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getCurrency().getValue();
                Product pair = ProductAccessor.getProductByShortName(currencyPair);
                BigDecimal points = fxSingleLeg.getExchangeRate().getForwardPoints();
                if (pair.getProductForex().getTickSize() != null) {
                    points = points.divide(pair.getProductForex().getTickSize(), 20, RoundingMode.UP);
                }
                trade.setNegociatedPrice(points);

                if (!fxSingleLeg.getProductId().get(0).getValue().equalsIgnoreCase("NDF")) {
                    tradeType = "Fx Forward";
                    /**
                     * classic fx fwd
                     */
                    product = pair;
                    if (nonpublicExecutionReport.getParty().get(0).getId().equalsIgnoreCase(((Party) fxSingleLeg.getExchangedCurrency1().getPayerPartyReference().getHref()).getId())) {
                        trade.setQuantity(fxSingleLeg.getExchangedCurrency1().getPaymentAmount().getAmount().negate());
                        trade.setAmount(fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getAmount());
                    } else {
                        trade.setQuantity(fxSingleLeg.getExchangedCurrency1().getPaymentAmount().getAmount());
                        trade.setAmount(fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getAmount().negate());
                    }
                    trade.setSettlementCurrency(fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getCurrency().getValue());
                    if (fxSingleLeg.getValueDate() != null) {
                        trade.setValueDate(fxSingleLeg.getValueDate().toGregorianCalendar().getTime());
                    }
                    if (fxSingleLeg.getExchangeRate().getRate() != null) {
                        trade.setPrice(fxSingleLeg.getExchangeRate().getRate());
                    }
                    if (trade.getQuantity() != null && fxSingleLeg.getExchangeRate().getRate() != null) {
                        trade.setConvertedAmount((trade.getQuantity().multiply(fxSingleLeg.getExchangeRate().getRate())).negate());
                    }
                    trade.setTradeType(Trade.TradeType.FORWARD.name);
                } else {
                    /**
                     * case of non-deliverable forward
                     */
                    if (nonpublicExecutionReport.getParty().get(0).getId().equalsIgnoreCase(((Party) fxSingleLeg.getExchangedCurrency1().getPayerPartyReference().getHref()).getId())) {
                        trade.setQuantity(fxSingleLeg.getExchangedCurrency1().getPaymentAmount().getAmount().negate());
                        trade.setConvertedAmount(fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getAmount());

                    } else {
                        trade.setQuantity(fxSingleLeg.getExchangedCurrency1().getPaymentAmount().getAmount());
                        trade.setConvertedAmount(fxSingleLeg.getExchangedCurrency2().getPaymentAmount().getAmount().negate());
                    }
                    trade.setForexRate(fxSingleLeg.getExchangeRate().getRate());
                    trade.setAmount(BigDecimal.ZERO);
                    trade.setPrice(BigDecimal.ZERO);
                    trade.setSettlementCurrency(fxSingleLeg.getNonDeliverableSettlement().getSettlementCurrency().getValue());
                    product = new Product();
                    product.setProductForex(new ProductForex());
                    product.getProductForex().setProduct(product);
                    product.setProductType(ProductTypeUtil.ProductType.FX_NDF.name);
                    product.setStartDate(trade.getTradeDate());
                    product.addUnderlying(pair);
                    product.getProductForex().setFixingDate(fxSingleLeg.getNonDeliverableSettlement().getFixing().get(0).getFixingDate().toGregorianCalendar().getTime());
                    product.setMaturityDate(fxSingleLeg.getValueDate().toGregorianCalendar().getTime());
                    product.setNotionalCurrency(fxSingleLeg.getNonDeliverableSettlement().getSettlementCurrency().getValue());
                    product.setNotionalMultiplier(BigDecimal.ONE);
                }
                trade.setMarketPrice(fxSingleLeg.getExchangeRate().getSpotRate());
                trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
                trade.setNegociatedPriceType(MarketQuote.QuotationType.FWDpts.getName());

            } else if (nonpublicExecutionReport.getTrade().getProduct().getValue() instanceof FxOption) {
                /**
                 * FX Vanille option trade
                 */
                FxOption fxOption = (FxOption) nonpublicExecutionReport.getTrade().getProduct().getValue();
                product = FxOptionLoader.read(fxOption);

                if ("call".equals(fxOption.getSoldAs().value().toLowerCase())) {
                    trade.setQuantity(fxOption.getCallCurrencyAmount().getAmount());
                } else {
                    trade.setQuantity(fxOption.getPutCurrencyAmount().getAmount());
                }
                trade.setAmount(fxOption.getPremium().getPaymentAmount().getAmount());
                trade.setValueDate(fxOption.getPremium().getPaymentDate().getAdjustableDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());

                BigDecimal price = (trade.getAmount().divide(trade.getQuantity(), 20, RoundingMode.UP)).negate();
                trade.setPrice(price);

                if (fxOption.getSpotRate() != null) {
                    trade.setMarketPrice(fxOption.getSpotRate());
                }
                if (trade.getQuantity() != null && fxOption.getStrike().getRate() != null) {
                    trade.setConvertedAmount((trade.getQuantity().multiply(fxOption.getStrike().getRate())).negate());
                }

                trade.setSettlementCurrency(fxOption.getPremium().getPaymentAmount().getCurrency().getValue());
                trade.setNegociatedPriceType(MarketQuote.QuotationType.PRICE.getName());

            } else if (nonpublicExecutionReport.getTrade().getProduct().getValue() instanceof GenericProduct) {
                /**
                 * Generic Products now : FX Barrier, Binary and Digital option
                 * trade
                 */
                tradeType = "Fx Option";
                GenericProduct genericProduct = (GenericProduct) nonpublicExecutionReport.getTrade().getProduct().getValue();
                product = GenericProductLoader.read(genericProduct);

                trade.setQuantity(genericProduct.getNotional().iterator().next().getAmount());
                trade.setAmount(genericProduct.getPremium().getPaymentAmount().getAmount());
                trade.setValueDate(genericProduct.getPremium().getPaymentDate().getAdjustableDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
                BigDecimal price = (trade.getAmount().divide(trade.getQuantity(), 20, RoundingMode.UP));
                trade.setPrice(price);
                if (trade.getQuantity() != null && price != null) {
                    trade.setConvertedAmount((trade.getQuantity().multiply(price)).negate());
                }

                trade.setSettlementCurrency(genericProduct.getSettlementCurrency().iterator().next().getValue());
                trade.setTradeType(Trade.TradeType.BUY_SELL.name);
                trade.setNegociatedPriceType(MarketQuote.QuotationType.PRICE.getName());
            }

            trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
            String type;
            if (product == null) {
                type = nonpublicExecutionReport.getTrade().getProduct().getValue().getProductId().get(0).getValue();
                errorText += "Product type not managed: " + type;
            }

            if (errorText.isEmpty()) {
                if (product.getProductId() == null) {
                    ProductAccessor.storeProduct(product);
                }
                trade.setProduct(product);
                TradeAccessor.storeTrade(trade);
                if (tradeType == null) {
                    tradeType = product.getProductType();
                }
                return ReportUtils.makeMessageList(tradeType + " trade created : id=" + trade.getId());
            }

        } catch (FileNotFoundException e) {
            logger.error(StringUtils.formatErrorMessage(e));
            return ReportUtils.makeErrorMessageList(e);
        } catch (JAXBException e) {
            logger.error(StringUtils.formatErrorMessage(e));
            return ReportUtils.makeErrorMessageList(e);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
            return ReportUtils.makeErrorMessageList(e);
        }
        return ReportUtils.makeMessageList(errorText);
    }

    public static RequestMessage JAXBUnmarshallFile(String filePath) throws Exception {
        RequestMessage res = null;

        try {
            /**
             * create JAXB context and instantiate marshaller
             */
            JAXBContext context = JAXBContext.newInstance(CorrectableRequestMessage.class);

            Unmarshaller um = context.createUnmarshaller();
            JAXBElement eleme = (JAXBElement) um.unmarshal(new FileReader(filePath));

            res = (CorrectableRequestMessage) eleme.getValue();
        } catch (JAXBException | FileNotFoundException ex) {
            throw ex;
        }
        return res;
    }
}
