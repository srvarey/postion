//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gaia.fpml.fpml_5.recordkeeping package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FxCurveValuation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxCurveValuation");
    private final static QName _CoalPhysicalLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "coalPhysicalLeg");
    private final static QName _Fra_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fra");
    private final static QName _ConvertibleBond_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "convertibleBond");
    private final static QName _VarianceOptionTransactionSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "varianceOptionTransactionSupplement");
    private final static QName _Exercise_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "exercise");
    private final static QName _MessageRejected_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "messageRejected");
    private final static QName _BrokerEquityOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "brokerEquityOption");
    private final static QName _EquityOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "equityOption");
    private final static QName _Bond_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bond");
    private final static QName _CommodityForward_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commodityForward");
    private final static QName _ReturnLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "returnLeg");
    private final static QName _RateIndex_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "rateIndex");
    private final static QName _ReturnSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "returnSwap");
    private final static QName _VerificationStatusAcknowledgement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "verificationStatusAcknowledgement");
    private final static QName _ValuationReportAcknowledgement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationReportAcknowledgement");
    private final static QName _FloatingLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "floatingLeg");
    private final static QName _GenericProduct_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "genericProduct");
    private final static QName _EquitySwapTransactionSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "equitySwapTransactionSupplement");
    private final static QName _FxDigitalOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxDigitalOption");
    private final static QName _ValuationSet_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationSet");
    private final static QName _MutualFund_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "mutualFund");
    private final static QName _CommodityOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commodityOption");
    private final static QName _ValuationReportRetracted_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationReportRetracted");
    private final static QName _Strategy_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "strategy");
    private final static QName _DataDocument_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "dataDocument");
    private final static QName _FailureToPay_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "failureToPay");
    private final static QName _RepudiationMoratorium_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "repudiationMoratorium");
    private final static QName _Commodity_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commodity");
    private final static QName _Swap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "swap");
    private final static QName _FxCurve_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxCurve");
    private final static QName _ReturnSwapLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "returnSwapLeg");
    private final static QName _NonSchemaProduct_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonSchemaProduct");
    private final static QName _Equity_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "equity");
    private final static QName _UnderlyingAsset_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "underlyingAsset");
    private final static QName _CommoditySwaption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commoditySwaption");
    private final static QName _EquityOptionTransactionSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "equityOptionTransactionSupplement");
    private final static QName _AmericanExercise_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "americanExercise");
    private final static QName _ChangeEvent_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "changeEvent");
    private final static QName _Future_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "future");
    private final static QName _NonpublicExecutionReportException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonpublicExecutionReportException");
    private final static QName _VerificationStatusException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "verificationStatusException");
    private final static QName _VarianceSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "varianceSwap");
    private final static QName _ObligationDefault_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "obligationDefault");
    private final static QName _Index_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "index");
    private final static QName _FxSingleLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxSingleLeg");
    private final static QName _CommoditySwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commoditySwap");
    private final static QName _InterestLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "interestLeg");
    private final static QName _BondOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bondOption");
    private final static QName _CreditCurve_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditCurve");
    private final static QName _CurveInstrument_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "curveInstrument");
    private final static QName _YieldCurveValuation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "yieldCurveValuation");
    private final static QName _Market_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "market");
    private final static QName _RateCalculation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "rateCalculation");
    private final static QName _CreditEventNotice_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditEventNotice");
    private final static QName _VarianceSwapTransactionSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "varianceSwapTransactionSupplement");
    private final static QName _Cash_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "cash");
    private final static QName _GasPhysicalLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "gasPhysicalLeg");
    private final static QName _InstrumentTradeDetails_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "instrumentTradeDetails");
    private final static QName _ExchangeTradedFund_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "exchangeTradedFund");
    private final static QName _CreditDefaultSwapOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditDefaultSwapOption");
    private final static QName _CreditEventNotification_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditEventNotification");
    private final static QName _FixedLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fixedLeg");
    private final static QName _SimpleCreditDefaultSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "simpleCreditDefaultSwap");
    private final static QName _CreditCurveValuation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditCurveValuation");
    private final static QName _VerificationStatusNotification_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "verificationStatusNotification");
    private final static QName _AdditionalEvent_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "additionalEvent");
    private final static QName _ServiceNotification_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "serviceNotification");
    private final static QName _VolatilityRepresentation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "volatilityRepresentation");
    private final static QName _EventStatusException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "eventStatusException");
    private final static QName _RequestRetransmission_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "requestRetransmission");
    private final static QName _Restructuring_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "restructuring");
    private final static QName _RequestValuationReport_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "requestValuationReport");
    private final static QName _DividendSwapTransactionSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "dividendSwapTransactionSupplement");
    private final static QName _CreditEventException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditEventException");
    private final static QName _BulletPayment_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bulletPayment");
    private final static QName _ObligationAcceleration_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "obligationAcceleration");
    private final static QName _NonpublicExecutionReportRetracted_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonpublicExecutionReportRetracted");
    private final static QName _YieldCurve_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "yieldCurve");
    private final static QName _Product_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "product");
    private final static QName _FxOption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxOption");
    private final static QName _SimpleFra_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "simpleFra");
    private final static QName _PricingStructure_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "pricingStructure");
    private final static QName _RequestEventStatus_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "requestEventStatus");
    private final static QName _Swaption_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "swaption");
    private final static QName _CommodityForwardLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commodityForwardLeg");
    private final static QName _Basket_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "basket");
    private final static QName _ValuationDocument_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationDocument");
    private final static QName _EventStatusResponse_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "eventStatusResponse");
    private final static QName _Fx_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fx");
    private final static QName _FxSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxSwap");
    private final static QName _QueryPortfolio_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "queryPortfolio");
    private final static QName _BullionPhysicalLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bullionPhysicalLeg");
    private final static QName _CommoditySwapLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commoditySwapLeg");
    private final static QName _Deposit_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "deposit");
    private final static QName _EquityForward_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "equityForward");
    private final static QName _NonpublicExecutionReport_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonpublicExecutionReport");
    private final static QName _ValuationReport_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationReport");
    private final static QName _InflationRateCalculation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "inflationRateCalculation");
    private final static QName _BermudaExercise_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bermudaExercise");
    private final static QName _ElectricityPhysicalLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "electricityPhysicalLeg");
    private final static QName _PricingStructureValuation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "pricingStructureValuation");
    private final static QName _NonpublicExecutionReportAcknowledgement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonpublicExecutionReportAcknowledgement");
    private final static QName _CapFloor_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "capFloor");
    private final static QName _TermDeposit_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "termDeposit");
    private final static QName _Portfolio_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "portfolio");
    private final static QName _CreditEventAcknowledgement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditEventAcknowledgement");
    private final static QName _CorrelationSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "correlationSwap");
    private final static QName _Mortgage_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "mortgage");
    private final static QName _EuropeanExercise_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "europeanExercise");
    private final static QName _IndexChange_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexChange");
    private final static QName _CreditEvent_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditEvent");
    private final static QName _ServiceNotificationException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "serviceNotificationException");
    private final static QName _FloatingRateCalculation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "floatingRateCalculation");
    private final static QName _Loan_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "loan");
    private final static QName _VolatilityMatrixValuation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "volatilityMatrixValuation");
    private final static QName _CreditDefaultSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditDefaultSwap");
    private final static QName _SimpleIrSwap_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "simpleIrSwap");
    private final static QName _StandardProduct_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "standardProduct");
    private final static QName _ValuationReportException_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "valuationReportException");
    private final static QName _Bankruptcy_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "bankruptcy");
    private final static QName _OilPhysicalLeg_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "oilPhysicalLeg");
    private final static QName _IndexReferenceInformationTranche_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "tranche");
    private final static QName _IndexReferenceInformationExcludedReferenceEntity_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "excludedReferenceEntity");
    private final static QName _IndexReferenceInformationSettledEntityMatrix_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "settledEntityMatrix");
    private final static QName _IndexReferenceInformationIndexId_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexId");
    private final static QName _IndexReferenceInformationIndexName_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexName");
    private final static QName _IndexReferenceInformationIndexAnnexDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexAnnexDate");
    private final static QName _IndexReferenceInformationIndexAnnexSource_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexAnnexSource");
    private final static QName _IndexReferenceInformationIndexSeries_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexSeries");
    private final static QName _IndexReferenceInformationIndexAnnexVersion_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "indexAnnexVersion");
    private final static QName _TradeNovationContentTransfereeAccount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "transfereeAccount");
    private final static QName _TradeNovationContentTransferor_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "transferor");
    private final static QName _TradeNovationContentNovationTradeDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novationTradeDate");
    private final static QName _TradeNovationContentFeeTradeIdentifier_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "feeTradeIdentifier");
    private final static QName _TradeNovationContentFullFirstCalculationPeriod_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fullFirstCalculationPeriod");
    private final static QName _TradeNovationContentContractualDefinitions_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "contractualDefinitions");
    private final static QName _TradeNovationContentContractualTermsSupplement_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "contractualTermsSupplement");
    private final static QName _TradeNovationContentRemainingNumberOfUnits_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "remainingNumberOfUnits");
    private final static QName _TradeNovationContentNewTradeIdentifier_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "newTradeIdentifier");
    private final static QName _TradeNovationContentOldTrade_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "oldTrade");
    private final static QName _TradeNovationContentOtherTransfereeAccount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "otherTransfereeAccount");
    private final static QName _TradeNovationContentRemainingPartyAccount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "remainingPartyAccount");
    private final static QName _TradeNovationContentNovationDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novationDate");
    private final static QName _TradeNovationContentRemainingAmount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "remainingAmount");
    private final static QName _TradeNovationContentNovatedNumberOfOptions_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novatedNumberOfOptions");
    private final static QName _TradeNovationContentPayment_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "payment");
    private final static QName _TradeNovationContentNonReliance_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "nonReliance");
    private final static QName _TradeNovationContentOldTradeIdentifier_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "oldTradeIdentifier");
    private final static QName _TradeNovationContentTransferee_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "transferee");
    private final static QName _TradeNovationContentOtherRemainingParty_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "otherRemainingParty");
    private final static QName _TradeNovationContentRemainingParty_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "remainingParty");
    private final static QName _TradeNovationContentFirstPeriodStartDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "firstPeriodStartDate");
    private final static QName _TradeNovationContentOtherTransferee_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "otherTransferee");
    private final static QName _TradeNovationContentOtherRemainingPartyAccount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "otherRemainingPartyAccount");
    private final static QName _TradeNovationContentExecutionDateTime_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "executionDateTime");
    private final static QName _TradeNovationContentNewTrade_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "newTrade");
    private final static QName _TradeNovationContentNovatedNumberOfUnits_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novatedNumberOfUnits");
    private final static QName _TradeNovationContentNovatedAmount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novatedAmount");
    private final static QName _TradeNovationContentRemainingNumberOfOptions_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "remainingNumberOfOptions");
    private final static QName _TradeNovationContentCreditDerivativesNotices_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "creditDerivativesNotices");
    private final static QName _TradeNovationContentFeeTrade_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "feeTrade");
    private final static QName _TradeNovationContentTransferorAccount_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "transferorAccount");
    private final static QName _PositionHistoryTermination_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "termination");
    private final static QName _PositionHistoryAmendment_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "amendment");
    private final static QName _PositionHistoryOriginatingEvent_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "originatingEvent");
    private final static QName _PositionHistoryOptionExercise_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "optionExercise");
    private final static QName _PositionHistoryDeClear_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "deClear");
    private final static QName _PositionHistoryOptionExpiry_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "optionExpiry");
    private final static QName _PositionHistoryWithdrawal_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "withdrawal");
    private final static QName _PositionHistoryNovation_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "novation");
    private final static QName _PositionHistoryTrade_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "trade");
    private final static QName _PositionHistoryTerminatingEvent_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "terminatingEvent");
    private final static QName _PositionHistoryIncrease_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "increase");
    private final static QName _FxOptionFeaturesBarrier_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "barrier");
    private final static QName _FxOptionFeaturesAsian_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "asian");
    private final static QName _PriceQuotationCharacteristics_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "quotationCharacteristics");
    private final static QName _PriceCleanNetPrice_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "cleanNetPrice");
    private final static QName _PriceDeterminationMethod_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "determinationMethod");
    private final static QName _PriceNetPrice_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "netPrice");
    private final static QName _PriceFxConversion_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "fxConversion");
    private final static QName _PriceAccruedInterestPrice_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "accruedInterestPrice");
    private final static QName _PriceGrossPrice_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "grossPrice");
    private final static QName _PriceAmountRelativeTo_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "amountRelativeTo");
    private final static QName _PriceCommission_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "commission");
    private final static QName _NetAndGrossGross_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "gross");
    private final static QName _NetAndGrossNet_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "net");
    private final static QName _AdjustableOrAdjustedDateUnadjustedDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "unadjustedDate");
    private final static QName _AdjustableOrAdjustedDateDateAdjustments_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "dateAdjustments");
    private final static QName _AdjustableOrAdjustedDateAdjustedDate_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "adjustedDate");
    private final static QName _PricingDataPointCoordinateStrike_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "strike");
    private final static QName _PricingDataPointCoordinateGeneric_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "generic");
    private final static QName _PricingDataPointCoordinateExpiration_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "expiration");
    private final static QName _PricingDataPointCoordinateTerm_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "term");
    private final static QName _ReportingRegimeSupervisorRegistration_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "supervisorRegistration");
    private final static QName _ReportingRegimeMandatorilyClearable_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "mandatorilyClearable");
    private final static QName _ReportingRegimeName_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "name");
    private final static QName _ReportingRegimeReportingRole_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "reportingRole");
    private final static QName _ReportingRegimeReportingPurpose_QNAME = new QName("http://www.fpml.org/FpML-5/recordkeeping", "reportingPurpose");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gaia.fpml.fpml_5.recordkeeping
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AdditionalData }
     * 
     */
    public AdditionalData createAdditionalData() {
        return new AdditionalData();
    }

    /**
     * Create an instance of {@link EventStatusResponse }
     * 
     */
    public EventStatusResponse createEventStatusResponse() {
        return new EventStatusResponse();
    }

    /**
     * Create an instance of {@link BullionPhysicalLeg }
     * 
     */
    public BullionPhysicalLeg createBullionPhysicalLeg() {
        return new BullionPhysicalLeg();
    }

    /**
     * Create an instance of {@link Deposit }
     * 
     */
    public Deposit createDeposit() {
        return new Deposit();
    }

    /**
     * Create an instance of {@link QueryPortfolio }
     * 
     */
    public QueryPortfolio createQueryPortfolio() {
        return new QueryPortfolio();
    }

    /**
     * Create an instance of {@link Portfolio }
     * 
     */
    public Portfolio createPortfolio() {
        return new Portfolio();
    }

    /**
     * Create an instance of {@link FxSwap }
     * 
     */
    public FxSwap createFxSwap() {
        return new FxSwap();
    }

    /**
     * Create an instance of {@link FxRateAsset }
     * 
     */
    public FxRateAsset createFxRateAsset() {
        return new FxRateAsset();
    }

    /**
     * Create an instance of {@link EquityForward }
     * 
     */
    public EquityForward createEquityForward() {
        return new EquityForward();
    }

    /**
     * Create an instance of {@link InflationRateCalculation }
     * 
     */
    public InflationRateCalculation createInflationRateCalculation() {
        return new InflationRateCalculation();
    }

    /**
     * Create an instance of {@link NonpublicExecutionReport }
     * 
     */
    public NonpublicExecutionReport createNonpublicExecutionReport() {
        return new NonpublicExecutionReport();
    }

    /**
     * Create an instance of {@link ValuationReport }
     * 
     */
    public ValuationReport createValuationReport() {
        return new ValuationReport();
    }

    /**
     * Create an instance of {@link BermudaExercise }
     * 
     */
    public BermudaExercise createBermudaExercise() {
        return new BermudaExercise();
    }

    /**
     * Create an instance of {@link Exercise }
     * 
     */
    public Exercise createExercise() {
        return new Exercise();
    }

    /**
     * Create an instance of {@link ElectricityPhysicalLeg }
     * 
     */
    public ElectricityPhysicalLeg createElectricityPhysicalLeg() {
        return new ElectricityPhysicalLeg();
    }

    /**
     * Create an instance of {@link PricingStructureValuation }
     * 
     */
    public PricingStructureValuation createPricingStructureValuation() {
        return new PricingStructureValuation();
    }

    /**
     * Create an instance of {@link CapFloor }
     * 
     */
    public CapFloor createCapFloor() {
        return new CapFloor();
    }

    /**
     * Create an instance of {@link Acknowledgement }
     * 
     */
    public Acknowledgement createAcknowledgement() {
        return new Acknowledgement();
    }

    /**
     * Create an instance of {@link TermDeposit }
     * 
     */
    public TermDeposit createTermDeposit() {
        return new TermDeposit();
    }

    /**
     * Create an instance of {@link EuropeanExercise }
     * 
     */
    public EuropeanExercise createEuropeanExercise() {
        return new EuropeanExercise();
    }

    /**
     * Create an instance of {@link Mortgage }
     * 
     */
    public Mortgage createMortgage() {
        return new Mortgage();
    }

    /**
     * Create an instance of {@link CorrelationSwap }
     * 
     */
    public CorrelationSwap createCorrelationSwap() {
        return new CorrelationSwap();
    }

    /**
     * Create an instance of {@link FloatingRateCalculation }
     * 
     */
    public FloatingRateCalculation createFloatingRateCalculation() {
        return new FloatingRateCalculation();
    }

    /**
     * Create an instance of {@link Loan }
     * 
     */
    public Loan createLoan() {
        return new Loan();
    }

    /**
     * Create an instance of {@link CreditEvent }
     * 
     */
    public CreditEvent createCreditEvent() {
        return new CreditEvent();
    }

    /**
     * Create an instance of {@link IndexChange }
     * 
     */
    public IndexChange createIndexChange() {
        return new IndexChange();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CreditDefaultSwap }
     * 
     */
    public CreditDefaultSwap createCreditDefaultSwap() {
        return new CreditDefaultSwap();
    }

    /**
     * Create an instance of {@link VolatilityMatrix }
     * 
     */
    public VolatilityMatrix createVolatilityMatrix() {
        return new VolatilityMatrix();
    }

    /**
     * Create an instance of {@link OilPhysicalLeg }
     * 
     */
    public OilPhysicalLeg createOilPhysicalLeg() {
        return new OilPhysicalLeg();
    }

    /**
     * Create an instance of {@link BankruptcyEvent }
     * 
     */
    public BankruptcyEvent createBankruptcyEvent() {
        return new BankruptcyEvent();
    }

    /**
     * Create an instance of {@link StandardProduct }
     * 
     */
    public StandardProduct createStandardProduct() {
        return new StandardProduct();
    }

    /**
     * Create an instance of {@link SimpleIRSwap }
     * 
     */
    public SimpleIRSwap createSimpleIRSwap() {
        return new SimpleIRSwap();
    }

    /**
     * Create an instance of {@link CreditDefaultSwapOption }
     * 
     */
    public CreditDefaultSwapOption createCreditDefaultSwapOption() {
        return new CreditDefaultSwapOption();
    }

    /**
     * Create an instance of {@link CreditEventNotification }
     * 
     */
    public CreditEventNotification createCreditEventNotification() {
        return new CreditEventNotification();
    }

    /**
     * Create an instance of {@link ExchangeTradedFund }
     * 
     */
    public ExchangeTradedFund createExchangeTradedFund() {
        return new ExchangeTradedFund();
    }

    /**
     * Create an instance of {@link SimpleCreditDefaultSwap }
     * 
     */
    public SimpleCreditDefaultSwap createSimpleCreditDefaultSwap() {
        return new SimpleCreditDefaultSwap();
    }

    /**
     * Create an instance of {@link FixedPriceLeg }
     * 
     */
    public FixedPriceLeg createFixedPriceLeg() {
        return new FixedPriceLeg();
    }

    /**
     * Create an instance of {@link CreditCurveValuation }
     * 
     */
    public CreditCurveValuation createCreditCurveValuation() {
        return new CreditCurveValuation();
    }

    /**
     * Create an instance of {@link VerificationStatusNotification }
     * 
     */
    public VerificationStatusNotification createVerificationStatusNotification() {
        return new VerificationStatusNotification();
    }

    /**
     * Create an instance of {@link ServiceNotification }
     * 
     */
    public ServiceNotification createServiceNotification() {
        return new ServiceNotification();
    }

    /**
     * Create an instance of {@link VolatilityRepresentation }
     * 
     */
    public VolatilityRepresentation createVolatilityRepresentation() {
        return new VolatilityRepresentation();
    }

    /**
     * Create an instance of {@link RequestRetransmission }
     * 
     */
    public RequestRetransmission createRequestRetransmission() {
        return new RequestRetransmission();
    }

    /**
     * Create an instance of {@link RequestValuationReport }
     * 
     */
    public RequestValuationReport createRequestValuationReport() {
        return new RequestValuationReport();
    }

    /**
     * Create an instance of {@link RestructuringEvent }
     * 
     */
    public RestructuringEvent createRestructuringEvent() {
        return new RestructuringEvent();
    }

    /**
     * Create an instance of {@link DividendSwapTransactionSupplement }
     * 
     */
    public DividendSwapTransactionSupplement createDividendSwapTransactionSupplement() {
        return new DividendSwapTransactionSupplement();
    }

    /**
     * Create an instance of {@link BulletPayment }
     * 
     */
    public BulletPayment createBulletPayment() {
        return new BulletPayment();
    }

    /**
     * Create an instance of {@link ObligationAccelerationEvent }
     * 
     */
    public ObligationAccelerationEvent createObligationAccelerationEvent() {
        return new ObligationAccelerationEvent();
    }

    /**
     * Create an instance of {@link YieldCurve }
     * 
     */
    public YieldCurve createYieldCurve() {
        return new YieldCurve();
    }

    /**
     * Create an instance of {@link NonpublicExecutionReportRetracted }
     * 
     */
    public NonpublicExecutionReportRetracted createNonpublicExecutionReportRetracted() {
        return new NonpublicExecutionReportRetracted();
    }

    /**
     * Create an instance of {@link FxOption }
     * 
     */
    public FxOption createFxOption() {
        return new FxOption();
    }

    /**
     * Create an instance of {@link RequestEventStatus }
     * 
     */
    public RequestEventStatus createRequestEventStatus() {
        return new RequestEventStatus();
    }

    /**
     * Create an instance of {@link Swaption }
     * 
     */
    public Swaption createSwaption() {
        return new Swaption();
    }

    /**
     * Create an instance of {@link SimpleFra }
     * 
     */
    public SimpleFra createSimpleFra() {
        return new SimpleFra();
    }

    /**
     * Create an instance of {@link Basket }
     * 
     */
    public Basket createBasket() {
        return new Basket();
    }

    /**
     * Create an instance of {@link ValuationDocument }
     * 
     */
    public ValuationDocument createValuationDocument() {
        return new ValuationDocument();
    }

    /**
     * Create an instance of {@link GenericProduct }
     * 
     */
    public GenericProduct createGenericProduct() {
        return new GenericProduct();
    }

    /**
     * Create an instance of {@link EquityAsset }
     * 
     */
    public EquityAsset createEquityAsset() {
        return new EquityAsset();
    }

    /**
     * Create an instance of {@link CommoditySwaption }
     * 
     */
    public CommoditySwaption createCommoditySwaption() {
        return new CommoditySwaption();
    }

    /**
     * Create an instance of {@link EquityOptionTransactionSupplement }
     * 
     */
    public EquityOptionTransactionSupplement createEquityOptionTransactionSupplement() {
        return new EquityOptionTransactionSupplement();
    }

    /**
     * Create an instance of {@link AmericanExercise }
     * 
     */
    public AmericanExercise createAmericanExercise() {
        return new AmericanExercise();
    }

    /**
     * Create an instance of {@link Future }
     * 
     */
    public Future createFuture() {
        return new Future();
    }

    /**
     * Create an instance of {@link VarianceSwap }
     * 
     */
    public VarianceSwap createVarianceSwap() {
        return new VarianceSwap();
    }

    /**
     * Create an instance of {@link ObligationDefaultEvent }
     * 
     */
    public ObligationDefaultEvent createObligationDefaultEvent() {
        return new ObligationDefaultEvent();
    }

    /**
     * Create an instance of {@link FxSingleLeg }
     * 
     */
    public FxSingleLeg createFxSingleLeg() {
        return new FxSingleLeg();
    }

    /**
     * Create an instance of {@link Index }
     * 
     */
    public Index createIndex() {
        return new Index();
    }

    /**
     * Create an instance of {@link InterestLeg }
     * 
     */
    public InterestLeg createInterestLeg() {
        return new InterestLeg();
    }

    /**
     * Create an instance of {@link CommoditySwap }
     * 
     */
    public CommoditySwap createCommoditySwap() {
        return new CommoditySwap();
    }

    /**
     * Create an instance of {@link BondOption }
     * 
     */
    public BondOption createBondOption() {
        return new BondOption();
    }

    /**
     * Create an instance of {@link YieldCurveValuation }
     * 
     */
    public YieldCurveValuation createYieldCurveValuation() {
        return new YieldCurveValuation();
    }

    /**
     * Create an instance of {@link CreditCurve }
     * 
     */
    public CreditCurve createCreditCurve() {
        return new CreditCurve();
    }

    /**
     * Create an instance of {@link Market }
     * 
     */
    public Market createMarket() {
        return new Market();
    }

    /**
     * Create an instance of {@link CreditEventNoticeDocument }
     * 
     */
    public CreditEventNoticeDocument createCreditEventNoticeDocument() {
        return new CreditEventNoticeDocument();
    }

    /**
     * Create an instance of {@link Cash }
     * 
     */
    public Cash createCash() {
        return new Cash();
    }

    /**
     * Create an instance of {@link VarianceSwapTransactionSupplement }
     * 
     */
    public VarianceSwapTransactionSupplement createVarianceSwapTransactionSupplement() {
        return new VarianceSwapTransactionSupplement();
    }

    /**
     * Create an instance of {@link InstrumentTradeDetails }
     * 
     */
    public InstrumentTradeDetails createInstrumentTradeDetails() {
        return new InstrumentTradeDetails();
    }

    /**
     * Create an instance of {@link GasPhysicalLeg }
     * 
     */
    public GasPhysicalLeg createGasPhysicalLeg() {
        return new GasPhysicalLeg();
    }

    /**
     * Create an instance of {@link CoalPhysicalLeg }
     * 
     */
    public CoalPhysicalLeg createCoalPhysicalLeg() {
        return new CoalPhysicalLeg();
    }

    /**
     * Create an instance of {@link FxCurveValuation }
     * 
     */
    public FxCurveValuation createFxCurveValuation() {
        return new FxCurveValuation();
    }

    /**
     * Create an instance of {@link ConvertibleBond }
     * 
     */
    public ConvertibleBond createConvertibleBond() {
        return new ConvertibleBond();
    }

    /**
     * Create an instance of {@link VarianceOptionTransactionSupplement }
     * 
     */
    public VarianceOptionTransactionSupplement createVarianceOptionTransactionSupplement() {
        return new VarianceOptionTransactionSupplement();
    }

    /**
     * Create an instance of {@link Fra }
     * 
     */
    public Fra createFra() {
        return new Fra();
    }

    /**
     * Create an instance of {@link BrokerEquityOption }
     * 
     */
    public BrokerEquityOption createBrokerEquityOption() {
        return new BrokerEquityOption();
    }

    /**
     * Create an instance of {@link Bond }
     * 
     */
    public Bond createBond() {
        return new Bond();
    }

    /**
     * Create an instance of {@link CommodityForward }
     * 
     */
    public CommodityForward createCommodityForward() {
        return new CommodityForward();
    }

    /**
     * Create an instance of {@link EquityOption }
     * 
     */
    public EquityOption createEquityOption() {
        return new EquityOption();
    }

    /**
     * Create an instance of {@link RateIndex }
     * 
     */
    public RateIndex createRateIndex() {
        return new RateIndex();
    }

    /**
     * Create an instance of {@link ReturnLeg }
     * 
     */
    public ReturnLeg createReturnLeg() {
        return new ReturnLeg();
    }

    /**
     * Create an instance of {@link ReturnSwap }
     * 
     */
    public ReturnSwap createReturnSwap() {
        return new ReturnSwap();
    }

    /**
     * Create an instance of {@link FloatingPriceLeg }
     * 
     */
    public FloatingPriceLeg createFloatingPriceLeg() {
        return new FloatingPriceLeg();
    }

    /**
     * Create an instance of {@link EquitySwapTransactionSupplement }
     * 
     */
    public EquitySwapTransactionSupplement createEquitySwapTransactionSupplement() {
        return new EquitySwapTransactionSupplement();
    }

    /**
     * Create an instance of {@link ValuationSet }
     * 
     */
    public ValuationSet createValuationSet() {
        return new ValuationSet();
    }

    /**
     * Create an instance of {@link FxDigitalOption }
     * 
     */
    public FxDigitalOption createFxDigitalOption() {
        return new FxDigitalOption();
    }

    /**
     * Create an instance of {@link Strategy }
     * 
     */
    public Strategy createStrategy() {
        return new Strategy();
    }

    /**
     * Create an instance of {@link MutualFund }
     * 
     */
    public MutualFund createMutualFund() {
        return new MutualFund();
    }

    /**
     * Create an instance of {@link ValuationReportRetracted }
     * 
     */
    public ValuationReportRetracted createValuationReportRetracted() {
        return new ValuationReportRetracted();
    }

    /**
     * Create an instance of {@link CommodityOption }
     * 
     */
    public CommodityOption createCommodityOption() {
        return new CommodityOption();
    }

    /**
     * Create an instance of {@link DataDocument }
     * 
     */
    public DataDocument createDataDocument() {
        return new DataDocument();
    }

    /**
     * Create an instance of {@link Commodity }
     * 
     */
    public Commodity createCommodity() {
        return new Commodity();
    }

    /**
     * Create an instance of {@link FailureToPayEvent }
     * 
     */
    public FailureToPayEvent createFailureToPayEvent() {
        return new FailureToPayEvent();
    }

    /**
     * Create an instance of {@link RepudiationMoratoriumEvent }
     * 
     */
    public RepudiationMoratoriumEvent createRepudiationMoratoriumEvent() {
        return new RepudiationMoratoriumEvent();
    }

    /**
     * Create an instance of {@link FxCurve }
     * 
     */
    public FxCurve createFxCurve() {
        return new FxCurve();
    }

    /**
     * Create an instance of {@link Swap }
     * 
     */
    public Swap createSwap() {
        return new Swap();
    }

    /**
     * Create an instance of {@link SpreadScheduleType }
     * 
     */
    public SpreadScheduleType createSpreadScheduleType() {
        return new SpreadScheduleType();
    }

    /**
     * Create an instance of {@link CommoditySwaptionUnderlying }
     * 
     */
    public CommoditySwaptionUnderlying createCommoditySwaptionUnderlying() {
        return new CommoditySwaptionUnderlying();
    }

    /**
     * Create an instance of {@link ExerciseFee }
     * 
     */
    public ExerciseFee createExerciseFee() {
        return new ExerciseFee();
    }

    /**
     * Create an instance of {@link FxLinkedNotionalSchedule }
     * 
     */
    public FxLinkedNotionalSchedule createFxLinkedNotionalSchedule() {
        return new FxLinkedNotionalSchedule();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link OilPipelineDelivery }
     * 
     */
    public OilPipelineDelivery createOilPipelineDelivery() {
        return new OilPipelineDelivery();
    }

    /**
     * Create an instance of {@link AmountReference }
     * 
     */
    public AmountReference createAmountReference() {
        return new AmountReference();
    }

    /**
     * Create an instance of {@link PricingDataPointCoordinateReference }
     * 
     */
    public PricingDataPointCoordinateReference createPricingDataPointCoordinateReference() {
        return new PricingDataPointCoordinateReference();
    }

    /**
     * Create an instance of {@link ExerciseEvent }
     * 
     */
    public ExerciseEvent createExerciseEvent() {
        return new ExerciseEvent();
    }

    /**
     * Create an instance of {@link DerivedValuationScenario }
     * 
     */
    public DerivedValuationScenario createDerivedValuationScenario() {
        return new DerivedValuationScenario();
    }

    /**
     * Create an instance of {@link CommodityExercisePeriods }
     * 
     */
    public CommodityExercisePeriods createCommodityExercisePeriods() {
        return new CommodityExercisePeriods();
    }

    /**
     * Create an instance of {@link ReferenceAmount }
     * 
     */
    public ReferenceAmount createReferenceAmount() {
        return new ReferenceAmount();
    }

    /**
     * Create an instance of {@link CreditEvents }
     * 
     */
    public CreditEvents createCreditEvents() {
        return new CreditEvents();
    }

    /**
     * Create an instance of {@link UpperBound }
     * 
     */
    public UpperBound createUpperBound() {
        return new UpperBound();
    }

    /**
     * Create an instance of {@link PricingParameterDerivativeReference }
     * 
     */
    public PricingParameterDerivativeReference createPricingParameterDerivativeReference() {
        return new PricingParameterDerivativeReference();
    }

    /**
     * Create an instance of {@link QueryParameterOperator }
     * 
     */
    public QueryParameterOperator createQueryParameterOperator() {
        return new QueryParameterOperator();
    }

    /**
     * Create an instance of {@link BrokerConfirmation }
     * 
     */
    public BrokerConfirmation createBrokerConfirmation() {
        return new BrokerConfirmation();
    }

    /**
     * Create an instance of {@link PaymentDates }
     * 
     */
    public PaymentDates createPaymentDates() {
        return new PaymentDates();
    }

    /**
     * Create an instance of {@link ElectricityPhysicalQuantity }
     * 
     */
    public ElectricityPhysicalQuantity createElectricityPhysicalQuantity() {
        return new ElectricityPhysicalQuantity();
    }

    /**
     * Create an instance of {@link PrincipalExchanges }
     * 
     */
    public PrincipalExchanges createPrincipalExchanges() {
        return new PrincipalExchanges();
    }

    /**
     * Create an instance of {@link PaymentDetails }
     * 
     */
    public PaymentDetails createPaymentDetails() {
        return new PaymentDetails();
    }

    /**
     * Create an instance of {@link ResourceId }
     * 
     */
    public ResourceId createResourceId() {
        return new ResourceId();
    }

    /**
     * Create an instance of {@link BusinessCenter }
     * 
     */
    public BusinessCenter createBusinessCenter() {
        return new BusinessCenter();
    }

    /**
     * Create an instance of {@link CommoditySettlementPeriodsNotionalQuantitySchedule }
     * 
     */
    public CommoditySettlementPeriodsNotionalQuantitySchedule createCommoditySettlementPeriodsNotionalQuantitySchedule() {
        return new CommoditySettlementPeriodsNotionalQuantitySchedule();
    }

    /**
     * Create an instance of {@link QuantityScheduleReference }
     * 
     */
    public QuantityScheduleReference createQuantityScheduleReference() {
        return new QuantityScheduleReference();
    }

    /**
     * Create an instance of {@link CreditSeniority }
     * 
     */
    public CreditSeniority createCreditSeniority() {
        return new CreditSeniority();
    }

    /**
     * Create an instance of {@link Schedule }
     * 
     */
    public Schedule createSchedule() {
        return new Schedule();
    }

    /**
     * Create an instance of {@link CalculationAmount }
     * 
     */
    public CalculationAmount createCalculationAmount() {
        return new CalculationAmount();
    }

    /**
     * Create an instance of {@link CalculationPeriodsScheduleReference }
     * 
     */
    public CalculationPeriodsScheduleReference createCalculationPeriodsScheduleReference() {
        return new CalculationPeriodsScheduleReference();
    }

    /**
     * Create an instance of {@link TradeTimestamp }
     * 
     */
    public TradeTimestamp createTradeTimestamp() {
        return new TradeTimestamp();
    }

    /**
     * Create an instance of {@link ContractualMatrix }
     * 
     */
    public ContractualMatrix createContractualMatrix() {
        return new ContractualMatrix();
    }

    /**
     * Create an instance of {@link OptionStrike }
     * 
     */
    public OptionStrike createOptionStrike() {
        return new OptionStrike();
    }

    /**
     * Create an instance of {@link CancelableProvisionAdjustedDates }
     * 
     */
    public CancelableProvisionAdjustedDates createCancelableProvisionAdjustedDates() {
        return new CancelableProvisionAdjustedDates();
    }

    /**
     * Create an instance of {@link TermDepositFeatures }
     * 
     */
    public TermDepositFeatures createTermDepositFeatures() {
        return new TermDepositFeatures();
    }

    /**
     * Create an instance of {@link PartyRole }
     * 
     */
    public PartyRole createPartyRole() {
        return new PartyRole();
    }

    /**
     * Create an instance of {@link ExtraordinaryEvents }
     * 
     */
    public ExtraordinaryEvents createExtraordinaryEvents() {
        return new ExtraordinaryEvents();
    }

    /**
     * Create an instance of {@link FxFeature }
     * 
     */
    public FxFeature createFxFeature() {
        return new FxFeature();
    }

    /**
     * Create an instance of {@link FeeLeg }
     * 
     */
    public FeeLeg createFeeLeg() {
        return new FeeLeg();
    }

    /**
     * Create an instance of {@link TradeChangeContent }
     * 
     */
    public TradeChangeContent createTradeChangeContent() {
        return new TradeChangeContent();
    }

    /**
     * Create an instance of {@link Correlation }
     * 
     */
    public Correlation createCorrelation() {
        return new Correlation();
    }

    /**
     * Create an instance of {@link ExtendibleProvisionAdjustedDates }
     * 
     */
    public ExtendibleProvisionAdjustedDates createExtendibleProvisionAdjustedDates() {
        return new ExtendibleProvisionAdjustedDates();
    }

    /**
     * Create an instance of {@link TransactionCharacteristic }
     * 
     */
    public TransactionCharacteristic createTransactionCharacteristic() {
        return new TransactionCharacteristic();
    }

    /**
     * Create an instance of {@link ExecutionDateTime }
     * 
     */
    public ExecutionDateTime createExecutionDateTime() {
        return new ExecutionDateTime();
    }

    /**
     * Create an instance of {@link AdjustedPaymentDates }
     * 
     */
    public AdjustedPaymentDates createAdjustedPaymentDates() {
        return new AdjustedPaymentDates();
    }

    /**
     * Create an instance of {@link MasterAgreementVersion }
     * 
     */
    public MasterAgreementVersion createMasterAgreementVersion() {
        return new MasterAgreementVersion();
    }

    /**
     * Create an instance of {@link CommodityStrikeSchedule }
     * 
     */
    public CommodityStrikeSchedule createCommodityStrikeSchedule() {
        return new CommodityStrikeSchedule();
    }

    /**
     * Create an instance of {@link OptionFeatures }
     * 
     */
    public OptionFeatures createOptionFeatures() {
        return new OptionFeatures();
    }

    /**
     * Create an instance of {@link CalculationAgent }
     * 
     */
    public CalculationAgent createCalculationAgent() {
        return new CalculationAgent();
    }

    /**
     * Create an instance of {@link FixedPrice }
     * 
     */
    public FixedPrice createFixedPrice() {
        return new FixedPrice();
    }

    /**
     * Create an instance of {@link Withdrawal }
     * 
     */
    public Withdrawal createWithdrawal() {
        return new Withdrawal();
    }

    /**
     * Create an instance of {@link CommodityPipeline }
     * 
     */
    public CommodityPipeline createCommodityPipeline() {
        return new CommodityPipeline();
    }

    /**
     * Create an instance of {@link PortfolioName }
     * 
     */
    public PortfolioName createPortfolioName() {
        return new PortfolioName();
    }

    /**
     * Create an instance of {@link PaymentType }
     * 
     */
    public PaymentType createPaymentType() {
        return new PaymentType();
    }

    /**
     * Create an instance of {@link PricingParameterShift }
     * 
     */
    public PricingParameterShift createPricingParameterShift() {
        return new PricingParameterShift();
    }

    /**
     * Create an instance of {@link ParametricAdjustmentPoint }
     * 
     */
    public ParametricAdjustmentPoint createParametricAdjustmentPoint() {
        return new ParametricAdjustmentPoint();
    }

    /**
     * Create an instance of {@link UnprocessedElementWrapper }
     * 
     */
    public UnprocessedElementWrapper createUnprocessedElementWrapper() {
        return new UnprocessedElementWrapper();
    }

    /**
     * Create an instance of {@link BestFitTrade }
     * 
     */
    public BestFitTrade createBestFitTrade() {
        return new BestFitTrade();
    }

    /**
     * Create an instance of {@link AmountSchedule }
     * 
     */
    public AmountSchedule createAmountSchedule() {
        return new AmountSchedule();
    }

    /**
     * Create an instance of {@link OnBehalfOf }
     * 
     */
    public OnBehalfOf createOnBehalfOf() {
        return new OnBehalfOf();
    }

    /**
     * Create an instance of {@link CommodityPhysicalExercise }
     * 
     */
    public CommodityPhysicalExercise createCommodityPhysicalExercise() {
        return new CommodityPhysicalExercise();
    }

    /**
     * Create an instance of {@link ForecastRateIndex }
     * 
     */
    public ForecastRateIndex createForecastRateIndex() {
        return new ForecastRateIndex();
    }

    /**
     * Create an instance of {@link DerivativeCalculationProcedure }
     * 
     */
    public DerivativeCalculationProcedure createDerivativeCalculationProcedure() {
        return new DerivativeCalculationProcedure();
    }

    /**
     * Create an instance of {@link NotionalStepRule }
     * 
     */
    public NotionalStepRule createNotionalStepRule() {
        return new NotionalStepRule();
    }

    /**
     * Create an instance of {@link MoneyReference }
     * 
     */
    public MoneyReference createMoneyReference() {
        return new MoneyReference();
    }

    /**
     * Create an instance of {@link DualCurrencyFeature }
     * 
     */
    public DualCurrencyFeature createDualCurrencyFeature() {
        return new DualCurrencyFeature();
    }

    /**
     * Create an instance of {@link AllocationReportingStatus }
     * 
     */
    public AllocationReportingStatus createAllocationReportingStatus() {
        return new AllocationReportingStatus();
    }

    /**
     * Create an instance of {@link CashPriceMethod }
     * 
     */
    public CashPriceMethod createCashPriceMethod() {
        return new CashPriceMethod();
    }

    /**
     * Create an instance of {@link CoalProductType }
     * 
     */
    public CoalProductType createCoalProductType() {
        return new CoalProductType();
    }

    /**
     * Create an instance of {@link ExerciseFeeSchedule }
     * 
     */
    public ExerciseFeeSchedule createExerciseFeeSchedule() {
        return new ExerciseFeeSchedule();
    }

    /**
     * Create an instance of {@link FxLinkedNotionalAmount }
     * 
     */
    public FxLinkedNotionalAmount createFxLinkedNotionalAmount() {
        return new FxLinkedNotionalAmount();
    }

    /**
     * Create an instance of {@link PaymentDetail }
     * 
     */
    public PaymentDetail createPaymentDetail() {
        return new PaymentDetail();
    }

    /**
     * Create an instance of {@link QuotedCurrencyPair }
     * 
     */
    public QuotedCurrencyPair createQuotedCurrencyPair() {
        return new QuotedCurrencyPair();
    }

    /**
     * Create an instance of {@link CoalQualityAdjustments }
     * 
     */
    public CoalQualityAdjustments createCoalQualityAdjustments() {
        return new CoalQualityAdjustments();
    }

    /**
     * Create an instance of {@link CreditSupportAgreementIdentifier }
     * 
     */
    public CreditSupportAgreementIdentifier createCreditSupportAgreementIdentifier() {
        return new CreditSupportAgreementIdentifier();
    }

    /**
     * Create an instance of {@link EquityAmericanExercise }
     * 
     */
    public EquityAmericanExercise createEquityAmericanExercise() {
        return new EquityAmericanExercise();
    }

    /**
     * Create an instance of {@link ReferenceBank }
     * 
     */
    public ReferenceBank createReferenceBank() {
        return new ReferenceBank();
    }

    /**
     * Create an instance of {@link Beneficiary }
     * 
     */
    public Beneficiary createBeneficiary() {
        return new Beneficiary();
    }

    /**
     * Create an instance of {@link CalculationPeriodDates }
     * 
     */
    public CalculationPeriodDates createCalculationPeriodDates() {
        return new CalculationPeriodDates();
    }

    /**
     * Create an instance of {@link GasDeliveryPoint }
     * 
     */
    public GasDeliveryPoint createGasDeliveryPoint() {
        return new GasDeliveryPoint();
    }

    /**
     * Create an instance of {@link OilDelivery }
     * 
     */
    public OilDelivery createOilDelivery() {
        return new OilDelivery();
    }

    /**
     * Create an instance of {@link OptionExpiryBase }
     * 
     */
    public OptionExpiryBase createOptionExpiryBase() {
        return new OptionExpiryBase();
    }

    /**
     * Create an instance of {@link ReturnLegValuationPrice }
     * 
     */
    public ReturnLegValuationPrice createReturnLegValuationPrice() {
        return new ReturnLegValuationPrice();
    }

    /**
     * Create an instance of {@link PrincipalExchangeDescriptions }
     * 
     */
    public PrincipalExchangeDescriptions createPrincipalExchangeDescriptions() {
        return new PrincipalExchangeDescriptions();
    }

    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link EntityType }
     * 
     */
    public EntityType createEntityType() {
        return new EntityType();
    }

    /**
     * Create an instance of {@link VersionedContractId }
     * 
     */
    public VersionedContractId createVersionedContractId() {
        return new VersionedContractId();
    }

    /**
     * Create an instance of {@link BrokerConfirmationType }
     * 
     */
    public BrokerConfirmationType createBrokerConfirmationType() {
        return new BrokerConfirmationType();
    }

    /**
     * Create an instance of {@link NonNegativePayment }
     * 
     */
    public NonNegativePayment createNonNegativePayment() {
        return new NonNegativePayment();
    }

    /**
     * Create an instance of {@link MortgageSector }
     * 
     */
    public MortgageSector createMortgageSector() {
        return new MortgageSector();
    }

    /**
     * Create an instance of {@link EventId }
     * 
     */
    public EventId createEventId() {
        return new EventId();
    }

    /**
     * Create an instance of {@link DateTimeList }
     * 
     */
    public DateTimeList createDateTimeList() {
        return new DateTimeList();
    }

    /**
     * Create an instance of {@link FxAsianFeature }
     * 
     */
    public FxAsianFeature createFxAsianFeature() {
        return new FxAsianFeature();
    }

    /**
     * Create an instance of {@link ClearingStatusValue }
     * 
     */
    public ClearingStatusValue createClearingStatusValue() {
        return new ClearingStatusValue();
    }

    /**
     * Create an instance of {@link CreditEventsReference }
     * 
     */
    public CreditEventsReference createCreditEventsReference() {
        return new CreditEventsReference();
    }

    /**
     * Create an instance of {@link ReturnSwapNotionalAmountReference }
     * 
     */
    public ReturnSwapNotionalAmountReference createReturnSwapNotionalAmountReference() {
        return new ReturnSwapNotionalAmountReference();
    }

    /**
     * Create an instance of {@link FxAverageRateObservation }
     * 
     */
    public FxAverageRateObservation createFxAverageRateObservation() {
        return new FxAverageRateObservation();
    }

    /**
     * Create an instance of {@link ReturnLegValuation }
     * 
     */
    public ReturnLegValuation createReturnLegValuation() {
        return new ReturnLegValuation();
    }

    /**
     * Create an instance of {@link SettlementInstruction }
     * 
     */
    public SettlementInstruction createSettlementInstruction() {
        return new SettlementInstruction();
    }

    /**
     * Create an instance of {@link MatchId }
     * 
     */
    public MatchId createMatchId() {
        return new MatchId();
    }

    /**
     * Create an instance of {@link PortfolioReference }
     * 
     */
    public PortfolioReference createPortfolioReference() {
        return new PortfolioReference();
    }

    /**
     * Create an instance of {@link StubCalculationPeriodAmount }
     * 
     */
    public StubCalculationPeriodAmount createStubCalculationPeriodAmount() {
        return new StubCalculationPeriodAmount();
    }

    /**
     * Create an instance of {@link Price }
     * 
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link RateObservation }
     * 
     */
    public RateObservation createRateObservation() {
        return new RateObservation();
    }

    /**
     * Create an instance of {@link IdentifiedPayerReceiver }
     * 
     */
    public IdentifiedPayerReceiver createIdentifiedPayerReceiver() {
        return new IdentifiedPayerReceiver();
    }

    /**
     * Create an instance of {@link CoalAttributeDecimal }
     * 
     */
    public CoalAttributeDecimal createCoalAttributeDecimal() {
        return new CoalAttributeDecimal();
    }

    /**
     * Create an instance of {@link PersonReference }
     * 
     */
    public PersonReference createPersonReference() {
        return new PersonReference();
    }

    /**
     * Create an instance of {@link PartyMessageInformation }
     * 
     */
    public PartyMessageInformation createPartyMessageInformation() {
        return new PartyMessageInformation();
    }

    /**
     * Create an instance of {@link IndustryClassification }
     * 
     */
    public IndustryClassification createIndustryClassification() {
        return new IndustryClassification();
    }

    /**
     * Create an instance of {@link ServiceProcessingEvent }
     * 
     */
    public ServiceProcessingEvent createServiceProcessingEvent() {
        return new ServiceProcessingEvent();
    }

    /**
     * Create an instance of {@link SimplePayment }
     * 
     */
    public SimplePayment createSimplePayment() {
        return new SimplePayment();
    }

    /**
     * Create an instance of {@link AccountReference }
     * 
     */
    public AccountReference createAccountReference() {
        return new AccountReference();
    }

    /**
     * Create an instance of {@link VerificationStatus }
     * 
     */
    public VerificationStatus createVerificationStatus() {
        return new VerificationStatus();
    }

    /**
     * Create an instance of {@link Knock }
     * 
     */
    public Knock createKnock() {
        return new Knock();
    }

    /**
     * Create an instance of {@link ElectricityDeliveryPeriods }
     * 
     */
    public ElectricityDeliveryPeriods createElectricityDeliveryPeriods() {
        return new ElectricityDeliveryPeriods();
    }

    /**
     * Create an instance of {@link FixedAmountCalculation }
     * 
     */
    public FixedAmountCalculation createFixedAmountCalculation() {
        return new FixedAmountCalculation();
    }

    /**
     * Create an instance of {@link FxOptionFeatures }
     * 
     */
    public FxOptionFeatures createFxOptionFeatures() {
        return new FxOptionFeatures();
    }

    /**
     * Create an instance of {@link AdjustableOrRelativeDates }
     * 
     */
    public AdjustableOrRelativeDates createAdjustableOrRelativeDates() {
        return new AdjustableOrRelativeDates();
    }

    /**
     * Create an instance of {@link CommodityCalculationPeriodsSchedule }
     * 
     */
    public CommodityCalculationPeriodsSchedule createCommodityCalculationPeriodsSchedule() {
        return new CommodityCalculationPeriodsSchedule();
    }

    /**
     * Create an instance of {@link ImplementationSpecificationVersion }
     * 
     */
    public ImplementationSpecificationVersion createImplementationSpecificationVersion() {
        return new ImplementationSpecificationVersion();
    }

    /**
     * Create an instance of {@link LagReference }
     * 
     */
    public LagReference createLagReference() {
        return new LagReference();
    }

    /**
     * Create an instance of {@link CalculationPeriodAmount }
     * 
     */
    public CalculationPeriodAmount createCalculationPeriodAmount() {
        return new CalculationPeriodAmount();
    }

    /**
     * Create an instance of {@link EquityCorporateEvents }
     * 
     */
    public EquityCorporateEvents createEquityCorporateEvents() {
        return new EquityCorporateEvents();
    }

    /**
     * Create an instance of {@link CountryCode }
     * 
     */
    public CountryCode createCountryCode() {
        return new CountryCode();
    }

    /**
     * Create an instance of {@link ConfirmationMethod }
     * 
     */
    public ConfirmationMethod createConfirmationMethod() {
        return new ConfirmationMethod();
    }

    /**
     * Create an instance of {@link MasterAgreementType }
     * 
     */
    public MasterAgreementType createMasterAgreementType() {
        return new MasterAgreementType();
    }

    /**
     * Create an instance of {@link BoundedVariance }
     * 
     */
    public BoundedVariance createBoundedVariance() {
        return new BoundedVariance();
    }

    /**
     * Create an instance of {@link SinglePartyOption }
     * 
     */
    public SinglePartyOption createSinglePartyOption() {
        return new SinglePartyOption();
    }

    /**
     * Create an instance of {@link OffsetPrevailingTime }
     * 
     */
    public OffsetPrevailingTime createOffsetPrevailingTime() {
        return new OffsetPrevailingTime();
    }

    /**
     * Create an instance of {@link OptionExercise }
     * 
     */
    public OptionExercise createOptionExercise() {
        return new OptionExercise();
    }

    /**
     * Create an instance of {@link RelativeDateSequence }
     * 
     */
    public RelativeDateSequence createRelativeDateSequence() {
        return new RelativeDateSequence();
    }

    /**
     * Create an instance of {@link FxTouch }
     * 
     */
    public FxTouch createFxTouch() {
        return new FxTouch();
    }

    /**
     * Create an instance of {@link PCDeliverableObligationCharac }
     * 
     */
    public PCDeliverableObligationCharac createPCDeliverableObligationCharac() {
        return new PCDeliverableObligationCharac();
    }

    /**
     * Create an instance of {@link DualCurrencyStrikePrice }
     * 
     */
    public DualCurrencyStrikePrice createDualCurrencyStrikePrice() {
        return new DualCurrencyStrikePrice();
    }

    /**
     * Create an instance of {@link ValuationScenario }
     * 
     */
    public ValuationScenario createValuationScenario() {
        return new ValuationScenario();
    }

    /**
     * Create an instance of {@link StubCalculationPeriod }
     * 
     */
    public StubCalculationPeriod createStubCalculationPeriod() {
        return new StubCalculationPeriod();
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link CommodityMarketDisruption }
     * 
     */
    public CommodityMarketDisruption createCommodityMarketDisruption() {
        return new CommodityMarketDisruption();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Approval }
     * 
     */
    public Approval createApproval() {
        return new Approval();
    }

    /**
     * Create an instance of {@link SensitivityDefinition }
     * 
     */
    public SensitivityDefinition createSensitivityDefinition() {
        return new SensitivityDefinition();
    }

    /**
     * Create an instance of {@link GracePeriodExtension }
     * 
     */
    public GracePeriodExtension createGracePeriodExtension() {
        return new GracePeriodExtension();
    }

    /**
     * Create an instance of {@link Trader }
     * 
     */
    public Trader createTrader() {
        return new Trader();
    }

    /**
     * Create an instance of {@link SettlementRateSource }
     * 
     */
    public SettlementRateSource createSettlementRateSource() {
        return new SettlementRateSource();
    }

    /**
     * Create an instance of {@link CreditSupportAgreementType }
     * 
     */
    public CreditSupportAgreementType createCreditSupportAgreementType() {
        return new CreditSupportAgreementType();
    }

    /**
     * Create an instance of {@link OriginatingEvent }
     * 
     */
    public OriginatingEvent createOriginatingEvent() {
        return new OriginatingEvent();
    }

    /**
     * Create an instance of {@link Offset }
     * 
     */
    public Offset createOffset() {
        return new Offset();
    }

    /**
     * Create an instance of {@link ReasonCode }
     * 
     */
    public ReasonCode createReasonCode() {
        return new ReasonCode();
    }

    /**
     * Create an instance of {@link ConstituentWeight }
     * 
     */
    public ConstituentWeight createConstituentWeight() {
        return new ConstituentWeight();
    }

    /**
     * Create an instance of {@link ExecutionType }
     * 
     */
    public ExecutionType createExecutionType() {
        return new ExecutionType();
    }

    /**
     * Create an instance of {@link NotionalAmountReference }
     * 
     */
    public NotionalAmountReference createNotionalAmountReference() {
        return new NotionalAmountReference();
    }

    /**
     * Create an instance of {@link RequiredIdentifierDate }
     * 
     */
    public RequiredIdentifierDate createRequiredIdentifierDate() {
        return new RequiredIdentifierDate();
    }

    /**
     * Create an instance of {@link IndexId }
     * 
     */
    public IndexId createIndexId() {
        return new IndexId();
    }

    /**
     * Create an instance of {@link ResetDatesReference }
     * 
     */
    public ResetDatesReference createResetDatesReference() {
        return new ResetDatesReference();
    }

    /**
     * Create an instance of {@link CorrectableRequestMessage }
     * 
     */
    public CorrectableRequestMessage createCorrectableRequestMessage() {
        return new CorrectableRequestMessage();
    }

    /**
     * Create an instance of {@link CancellationEvent }
     * 
     */
    public CancellationEvent createCancellationEvent() {
        return new CancellationEvent();
    }

    /**
     * Create an instance of {@link NotionalAmount }
     * 
     */
    public NotionalAmount createNotionalAmount() {
        return new NotionalAmount();
    }

    /**
     * Create an instance of {@link Lag }
     * 
     */
    public Lag createLag() {
        return new Lag();
    }

    /**
     * Create an instance of {@link NonNegativeSchedule }
     * 
     */
    public NonNegativeSchedule createNonNegativeSchedule() {
        return new NonNegativeSchedule();
    }

    /**
     * Create an instance of {@link Restructuring }
     * 
     */
    public Restructuring createRestructuring() {
        return new Restructuring();
    }

    /**
     * Create an instance of {@link ProductId }
     * 
     */
    public ProductId createProductId() {
        return new ProductId();
    }

    /**
     * Create an instance of {@link SingleValuationDate }
     * 
     */
    public SingleValuationDate createSingleValuationDate() {
        return new SingleValuationDate();
    }

    /**
     * Create an instance of {@link LegalEntity }
     * 
     */
    public LegalEntity createLegalEntity() {
        return new LegalEntity();
    }

    /**
     * Create an instance of {@link SensitivitySetDefinitionReference }
     * 
     */
    public SensitivitySetDefinitionReference createSensitivitySetDefinitionReference() {
        return new SensitivitySetDefinitionReference();
    }

    /**
     * Create an instance of {@link CommodityEuropeanExercise }
     * 
     */
    public CommodityEuropeanExercise createCommodityEuropeanExercise() {
        return new CommodityEuropeanExercise();
    }

    /**
     * Create an instance of {@link MessageAddress }
     * 
     */
    public MessageAddress createMessageAddress() {
        return new MessageAddress();
    }

    /**
     * Create an instance of {@link EntityId }
     * 
     */
    public EntityId createEntityId() {
        return new EntityId();
    }

    /**
     * Create an instance of {@link PricingStructureReference }
     * 
     */
    public PricingStructureReference createPricingStructureReference() {
        return new PricingStructureReference();
    }

    /**
     * Create an instance of {@link ResourceLength }
     * 
     */
    public ResourceLength createResourceLength() {
        return new ResourceLength();
    }

    /**
     * Create an instance of {@link UnderlyingAssetTranche }
     * 
     */
    public UnderlyingAssetTranche createUnderlyingAssetTranche() {
        return new UnderlyingAssetTranche();
    }

    /**
     * Create an instance of {@link PartyId }
     * 
     */
    public PartyId createPartyId() {
        return new PartyId();
    }

    /**
     * Create an instance of {@link ExceptionMessageHeader }
     * 
     */
    public ExceptionMessageHeader createExceptionMessageHeader() {
        return new ExceptionMessageHeader();
    }

    /**
     * Create an instance of {@link BullionDeliveryLocation }
     * 
     */
    public BullionDeliveryLocation createBullionDeliveryLocation() {
        return new BullionDeliveryLocation();
    }

    /**
     * Create an instance of {@link MatrixType }
     * 
     */
    public MatrixType createMatrixType() {
        return new MatrixType();
    }

    /**
     * Create an instance of {@link PricingInputType }
     * 
     */
    public PricingInputType createPricingInputType() {
        return new PricingInputType();
    }

    /**
     * Create an instance of {@link EquityPremium }
     * 
     */
    public EquityPremium createEquityPremium() {
        return new EquityPremium();
    }

    /**
     * Create an instance of {@link EventIdentifier }
     * 
     */
    public EventIdentifier createEventIdentifier() {
        return new EventIdentifier();
    }

    /**
     * Create an instance of {@link MatrixTerm }
     * 
     */
    public MatrixTerm createMatrixTerm() {
        return new MatrixTerm();
    }

    /**
     * Create an instance of {@link RelativeDates }
     * 
     */
    public RelativeDates createRelativeDates() {
        return new RelativeDates();
    }

    /**
     * Create an instance of {@link Step }
     * 
     */
    public Step createStep() {
        return new Step();
    }

    /**
     * Create an instance of {@link WeightedPartialDerivative }
     * 
     */
    public WeightedPartialDerivative createWeightedPartialDerivative() {
        return new WeightedPartialDerivative();
    }

    /**
     * Create an instance of {@link HTTPAttachmentReference }
     * 
     */
    public HTTPAttachmentReference createHTTPAttachmentReference() {
        return new HTTPAttachmentReference();
    }

    /**
     * Create an instance of {@link SettlementRateOption }
     * 
     */
    public SettlementRateOption createSettlementRateOption() {
        return new SettlementRateOption();
    }

    /**
     * Create an instance of {@link ScheduledDate }
     * 
     */
    public ScheduledDate createScheduledDate() {
        return new ScheduledDate();
    }

    /**
     * Create an instance of {@link NotificationMessageHeader }
     * 
     */
    public NotificationMessageHeader createNotificationMessageHeader() {
        return new NotificationMessageHeader();
    }

    /**
     * Create an instance of {@link QuotationCharacteristics }
     * 
     */
    public QuotationCharacteristics createQuotationCharacteristics() {
        return new QuotationCharacteristics();
    }

    /**
     * Create an instance of {@link CutName }
     * 
     */
    public CutName createCutName() {
        return new CutName();
    }

    /**
     * Create an instance of {@link ProductReference }
     * 
     */
    public ProductReference createProductReference() {
        return new ProductReference();
    }

    /**
     * Create an instance of {@link Compounding }
     * 
     */
    public Compounding createCompounding() {
        return new Compounding();
    }

    /**
     * Create an instance of {@link FailureToPay }
     * 
     */
    public FailureToPay createFailureToPay() {
        return new FailureToPay();
    }

    /**
     * Create an instance of {@link ExtensionEvent }
     * 
     */
    public ExtensionEvent createExtensionEvent() {
        return new ExtensionEvent();
    }

    /**
     * Create an instance of {@link ReturnSwapEarlyTermination }
     * 
     */
    public ReturnSwapEarlyTermination createReturnSwapEarlyTermination() {
        return new ReturnSwapEarlyTermination();
    }

    /**
     * Create an instance of {@link CommodityNotionalQuantity }
     * 
     */
    public CommodityNotionalQuantity createCommodityNotionalQuantity() {
        return new CommodityNotionalQuantity();
    }

    /**
     * Create an instance of {@link Trade }
     * 
     */
    public Trade createTrade() {
        return new Trade();
    }

    /**
     * Create an instance of {@link BasicAssetValuation }
     * 
     */
    public BasicAssetValuation createBasicAssetValuation() {
        return new BasicAssetValuation();
    }

    /**
     * Create an instance of {@link ReportingPurpose }
     * 
     */
    public ReportingPurpose createReportingPurpose() {
        return new ReportingPurpose();
    }

    /**
     * Create an instance of {@link TradeId }
     * 
     */
    public TradeId createTradeId() {
        return new TradeId();
    }

    /**
     * Create an instance of {@link SwaptionAdjustedDates }
     * 
     */
    public SwaptionAdjustedDates createSwaptionAdjustedDates() {
        return new SwaptionAdjustedDates();
    }

    /**
     * Create an instance of {@link IdentifiedCurrency }
     * 
     */
    public IdentifiedCurrency createIdentifiedCurrency() {
        return new IdentifiedCurrency();
    }

    /**
     * Create an instance of {@link PortfolioValuationItem }
     * 
     */
    public PortfolioValuationItem createPortfolioValuationItem() {
        return new PortfolioValuationItem();
    }

    /**
     * Create an instance of {@link TradeMaturity }
     * 
     */
    public TradeMaturity createTradeMaturity() {
        return new TradeMaturity();
    }

    /**
     * Create an instance of {@link Frequency }
     * 
     */
    public Frequency createFrequency() {
        return new Frequency();
    }

    /**
     * Create an instance of {@link PeriodicDates }
     * 
     */
    public PeriodicDates createPeriodicDates() {
        return new PeriodicDates();
    }

    /**
     * Create an instance of {@link CancelableProvision }
     * 
     */
    public CancelableProvision createCancelableProvision() {
        return new CancelableProvision();
    }

    /**
     * Create an instance of {@link ServiceProcessingStatus }
     * 
     */
    public ServiceProcessingStatus createServiceProcessingStatus() {
        return new ServiceProcessingStatus();
    }

    /**
     * Create an instance of {@link SensitivitySetDefinition }
     * 
     */
    public SensitivitySetDefinition createSensitivitySetDefinition() {
        return new SensitivitySetDefinition();
    }

    /**
     * Create an instance of {@link FxMultipleExercise }
     * 
     */
    public FxMultipleExercise createFxMultipleExercise() {
        return new FxMultipleExercise();
    }

    /**
     * Create an instance of {@link CorrelationLeg }
     * 
     */
    public CorrelationLeg createCorrelationLeg() {
        return new CorrelationLeg();
    }

    /**
     * Create an instance of {@link BusinessCenterTime }
     * 
     */
    public BusinessCenterTime createBusinessCenterTime() {
        return new BusinessCenterTime();
    }

    /**
     * Create an instance of {@link TradeReferenceInformation }
     * 
     */
    public TradeReferenceInformation createTradeReferenceInformation() {
        return new TradeReferenceInformation();
    }

    /**
     * Create an instance of {@link BasketId }
     * 
     */
    public BasketId createBasketId() {
        return new BasketId();
    }

    /**
     * Create an instance of {@link PassThroughItem }
     * 
     */
    public PassThroughItem createPassThroughItem() {
        return new PassThroughItem();
    }

    /**
     * Create an instance of {@link EquityBermudaExercise }
     * 
     */
    public EquityBermudaExercise createEquityBermudaExercise() {
        return new EquityBermudaExercise();
    }

    /**
     * Create an instance of {@link InstrumentTradeQuantity }
     * 
     */
    public InstrumentTradeQuantity createInstrumentTradeQuantity() {
        return new InstrumentTradeQuantity();
    }

    /**
     * Create an instance of {@link AdjustedRelativeDateOffset }
     * 
     */
    public AdjustedRelativeDateOffset createAdjustedRelativeDateOffset() {
        return new AdjustedRelativeDateOffset();
    }

    /**
     * Create an instance of {@link CashSettlementReferenceBanks }
     * 
     */
    public CashSettlementReferenceBanks createCashSettlementReferenceBanks() {
        return new CashSettlementReferenceBanks();
    }

    /**
     * Create an instance of {@link OilTransferDelivery }
     * 
     */
    public OilTransferDelivery createOilTransferDelivery() {
        return new OilTransferDelivery();
    }

    /**
     * Create an instance of {@link ElectricityTransmissionContingency }
     * 
     */
    public ElectricityTransmissionContingency createElectricityTransmissionContingency() {
        return new ElectricityTransmissionContingency();
    }

    /**
     * Create an instance of {@link ReportId }
     * 
     */
    public ReportId createReportId() {
        return new ReportId();
    }

    /**
     * Create an instance of {@link PositivePayment }
     * 
     */
    public PositivePayment createPositivePayment() {
        return new PositivePayment();
    }

    /**
     * Create an instance of {@link PeriodicPayment }
     * 
     */
    public PeriodicPayment createPeriodicPayment() {
        return new PeriodicPayment();
    }

    /**
     * Create an instance of {@link AdjustableOrAdjustedDate }
     * 
     */
    public AdjustableOrAdjustedDate createAdjustableOrAdjustedDate() {
        return new AdjustableOrAdjustedDate();
    }

    /**
     * Create an instance of {@link DerivativeCalculationMethod }
     * 
     */
    public DerivativeCalculationMethod createDerivativeCalculationMethod() {
        return new DerivativeCalculationMethod();
    }

    /**
     * Create an instance of {@link StrikeSpread }
     * 
     */
    public StrikeSpread createStrikeSpread() {
        return new StrikeSpread();
    }

    /**
     * Create an instance of {@link DeliverableObligations }
     * 
     */
    public DeliverableObligations createDeliverableObligations() {
        return new DeliverableObligations();
    }

    /**
     * Create an instance of {@link EndUserExceptionDeclaration }
     * 
     */
    public EndUserExceptionDeclaration createEndUserExceptionDeclaration() {
        return new EndUserExceptionDeclaration();
    }

    /**
     * Create an instance of {@link CashflowNotional }
     * 
     */
    public CashflowNotional createCashflowNotional() {
        return new CashflowNotional();
    }

    /**
     * Create an instance of {@link OptionalEarlyTermination }
     * 
     */
    public OptionalEarlyTermination createOptionalEarlyTermination() {
        return new OptionalEarlyTermination();
    }

    /**
     * Create an instance of {@link PrincipalExchangeFeatures }
     * 
     */
    public PrincipalExchangeFeatures createPrincipalExchangeFeatures() {
        return new PrincipalExchangeFeatures();
    }

    /**
     * Create an instance of {@link InterestShortFall }
     * 
     */
    public InterestShortFall createInterestShortFall() {
        return new InterestShortFall();
    }

    /**
     * Create an instance of {@link InstrumentTradePricing }
     * 
     */
    public InstrumentTradePricing createInstrumentTradePricing() {
        return new InstrumentTradePricing();
    }

    /**
     * Create an instance of {@link Money }
     * 
     */
    public Money createMoney() {
        return new Money();
    }

    /**
     * Create an instance of {@link EarlyTerminationEvent }
     * 
     */
    public EarlyTerminationEvent createEarlyTerminationEvent() {
        return new EarlyTerminationEvent();
    }

    /**
     * Create an instance of {@link ReferenceObligation }
     * 
     */
    public ReferenceObligation createReferenceObligation() {
        return new ReferenceObligation();
    }

    /**
     * Create an instance of {@link DerivativeFormula }
     * 
     */
    public DerivativeFormula createDerivativeFormula() {
        return new DerivativeFormula();
    }

    /**
     * Create an instance of {@link ExercisePeriod }
     * 
     */
    public ExercisePeriod createExercisePeriod() {
        return new ExercisePeriod();
    }

    /**
     * Create an instance of {@link ReturnSwapNotional }
     * 
     */
    public ReturnSwapNotional createReturnSwapNotional() {
        return new ReturnSwapNotional();
    }

    /**
     * Create an instance of {@link SettlementPeriodsReference }
     * 
     */
    public SettlementPeriodsReference createSettlementPeriodsReference() {
        return new SettlementPeriodsReference();
    }

    /**
     * Create an instance of {@link TradeCategory }
     * 
     */
    public TradeCategory createTradeCategory() {
        return new TradeCategory();
    }

    /**
     * Create an instance of {@link ValuationSetDetail }
     * 
     */
    public ValuationSetDetail createValuationSetDetail() {
        return new ValuationSetDetail();
    }

    /**
     * Create an instance of {@link FinalCalculationPeriodDateAdjustment }
     * 
     */
    public FinalCalculationPeriodDateAdjustment createFinalCalculationPeriodDateAdjustment() {
        return new FinalCalculationPeriodDateAdjustment();
    }

    /**
     * Create an instance of {@link SettlementProvision }
     * 
     */
    public SettlementProvision createSettlementProvision() {
        return new SettlementProvision();
    }

    /**
     * Create an instance of {@link PremiumQuote }
     * 
     */
    public PremiumQuote createPremiumQuote() {
        return new PremiumQuote();
    }

    /**
     * Create an instance of {@link NetAndGross }
     * 
     */
    public NetAndGross createNetAndGross() {
        return new NetAndGross();
    }

    /**
     * Create an instance of {@link InstrumentId }
     * 
     */
    public InstrumentId createInstrumentId() {
        return new InstrumentId();
    }

    /**
     * Create an instance of {@link CoalAttributePercentage }
     * 
     */
    public CoalAttributePercentage createCoalAttributePercentage() {
        return new CoalAttributePercentage();
    }

    /**
     * Create an instance of {@link PercentageRule }
     * 
     */
    public PercentageRule createPercentageRule() {
        return new PercentageRule();
    }

    /**
     * Create an instance of {@link CommodityPipelineCycle }
     * 
     */
    public CommodityPipelineCycle createCommodityPipelineCycle() {
        return new CommodityPipelineCycle();
    }

    /**
     * Create an instance of {@link Validation }
     * 
     */
    public Validation createValidation() {
        return new Validation();
    }

    /**
     * Create an instance of {@link Valuations }
     * 
     */
    public Valuations createValuations() {
        return new Valuations();
    }

    /**
     * Create an instance of {@link PaymentId }
     * 
     */
    public PaymentId createPaymentId() {
        return new PaymentId();
    }

    /**
     * Create an instance of {@link ContractualDefinitions }
     * 
     */
    public ContractualDefinitions createContractualDefinitions() {
        return new ContractualDefinitions();
    }

    /**
     * Create an instance of {@link Representations }
     * 
     */
    public Representations createRepresentations() {
        return new Representations();
    }

    /**
     * Create an instance of {@link ValuationReference }
     * 
     */
    public ValuationReference createValuationReference() {
        return new ValuationReference();
    }

    /**
     * Create an instance of {@link EquityOptionTermination }
     * 
     */
    public EquityOptionTermination createEquityOptionTermination() {
        return new EquityOptionTermination();
    }

    /**
     * Create an instance of {@link PartyTradeInformation }
     * 
     */
    public PartyTradeInformation createPartyTradeInformation() {
        return new PartyTradeInformation();
    }

    /**
     * Create an instance of {@link DividendPaymentDate }
     * 
     */
    public DividendPaymentDate createDividendPaymentDate() {
        return new DividendPaymentDate();
    }

    /**
     * Create an instance of {@link RateReference }
     * 
     */
    public RateReference createRateReference() {
        return new RateReference();
    }

    /**
     * Create an instance of {@link AdditionalTerm }
     * 
     */
    public AdditionalTerm createAdditionalTerm() {
        return new AdditionalTerm();
    }

    /**
     * Create an instance of {@link RelevantUnderlyingDateReference }
     * 
     */
    public RelevantUnderlyingDateReference createRelevantUnderlyingDateReference() {
        return new RelevantUnderlyingDateReference();
    }

    /**
     * Create an instance of {@link Valuation }
     * 
     */
    public Valuation createValuation() {
        return new Valuation();
    }

    /**
     * Create an instance of {@link PartyTradeIdentifier }
     * 
     */
    public PartyTradeIdentifier createPartyTradeIdentifier() {
        return new PartyTradeIdentifier();
    }

    /**
     * Create an instance of {@link Rounding }
     * 
     */
    public Rounding createRounding() {
        return new Rounding();
    }

    /**
     * Create an instance of {@link SingleUnderlyer }
     * 
     */
    public SingleUnderlyer createSingleUnderlyer() {
        return new SingleUnderlyer();
    }

    /**
     * Create an instance of {@link BasketConstituent }
     * 
     */
    public BasketConstituent createBasketConstituent() {
        return new BasketConstituent();
    }

    /**
     * Create an instance of {@link PartyTradeIdentifiers }
     * 
     */
    public PartyTradeIdentifiers createPartyTradeIdentifiers() {
        return new PartyTradeIdentifiers();
    }

    /**
     * Create an instance of {@link AverageDailyTradingVolumeLimit }
     * 
     */
    public AverageDailyTradingVolumeLimit createAverageDailyTradingVolumeLimit() {
        return new AverageDailyTradingVolumeLimit();
    }

    /**
     * Create an instance of {@link Variance }
     * 
     */
    public Variance createVariance() {
        return new Variance();
    }

    /**
     * Create an instance of {@link CommodityRelativeExpirationDates }
     * 
     */
    public CommodityRelativeExpirationDates createCommodityRelativeExpirationDates() {
        return new CommodityRelativeExpirationDates();
    }

    /**
     * Create an instance of {@link CommodityFx }
     * 
     */
    public CommodityFx createCommodityFx() {
        return new CommodityFx();
    }

    /**
     * Create an instance of {@link SequencedDisruptionFallback }
     * 
     */
    public SequencedDisruptionFallback createSequencedDisruptionFallback() {
        return new SequencedDisruptionFallback();
    }

    /**
     * Create an instance of {@link QuotedAssetSet }
     * 
     */
    public QuotedAssetSet createQuotedAssetSet() {
        return new QuotedAssetSet();
    }

    /**
     * Create an instance of {@link PrincipalExchangeAmount }
     * 
     */
    public PrincipalExchangeAmount createPrincipalExchangeAmount() {
        return new PrincipalExchangeAmount();
    }

    /**
     * Create an instance of {@link FloatingRateIndex }
     * 
     */
    public FloatingRateIndex createFloatingRateIndex() {
        return new FloatingRateIndex();
    }

    /**
     * Create an instance of {@link MatrixSource }
     * 
     */
    public MatrixSource createMatrixSource() {
        return new MatrixSource();
    }

    /**
     * Create an instance of {@link SpreadSchedule }
     * 
     */
    public SpreadSchedule createSpreadSchedule() {
        return new SpreadSchedule();
    }

    /**
     * Create an instance of {@link SettlementTermsReference }
     * 
     */
    public SettlementTermsReference createSettlementTermsReference() {
        return new SettlementTermsReference();
    }

    /**
     * Create an instance of {@link ReferenceBankId }
     * 
     */
    public ReferenceBankId createReferenceBankId() {
        return new ReferenceBankId();
    }

    /**
     * Create an instance of {@link FixedPaymentAmount }
     * 
     */
    public FixedPaymentAmount createFixedPaymentAmount() {
        return new FixedPaymentAmount();
    }

    /**
     * Create an instance of {@link ExtendibleProvision }
     * 
     */
    public ExtendibleProvision createExtendibleProvision() {
        return new ExtendibleProvision();
    }

    /**
     * Create an instance of {@link CompoundingRate }
     * 
     */
    public CompoundingRate createCompoundingRate() {
        return new CompoundingRate();
    }

    /**
     * Create an instance of {@link ValuationScenarioReference }
     * 
     */
    public ValuationScenarioReference createValuationScenarioReference() {
        return new ValuationScenarioReference();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
    }

    /**
     * Create an instance of {@link FloatingAmountEvents }
     * 
     */
    public FloatingAmountEvents createFloatingAmountEvents() {
        return new FloatingAmountEvents();
    }

    /**
     * Create an instance of {@link AutomaticExercise }
     * 
     */
    public AutomaticExercise createAutomaticExercise() {
        return new AutomaticExercise();
    }

    /**
     * Create an instance of {@link ValuationDatesReference }
     * 
     */
    public ValuationDatesReference createValuationDatesReference() {
        return new ValuationDatesReference();
    }

    /**
     * Create an instance of {@link CoalStandardQualitySchedule }
     * 
     */
    public CoalStandardQualitySchedule createCoalStandardQualitySchedule() {
        return new CoalStandardQualitySchedule();
    }

    /**
     * Create an instance of {@link FxConversion }
     * 
     */
    public FxConversion createFxConversion() {
        return new FxConversion();
    }

    /**
     * Create an instance of {@link LegIdentifier }
     * 
     */
    public LegIdentifier createLegIdentifier() {
        return new LegIdentifier();
    }

    /**
     * Create an instance of {@link PartyRoleType }
     * 
     */
    public PartyRoleType createPartyRoleType() {
        return new PartyRoleType();
    }

    /**
     * Create an instance of {@link SettlementPeriodsSchedule }
     * 
     */
    public SettlementPeriodsSchedule createSettlementPeriodsSchedule() {
        return new SettlementPeriodsSchedule();
    }

    /**
     * Create an instance of {@link DividendPayout }
     * 
     */
    public DividendPayout createDividendPayout() {
        return new DividendPayout();
    }

    /**
     * Create an instance of {@link SettlementMethod }
     * 
     */
    public SettlementMethod createSettlementMethod() {
        return new SettlementMethod();
    }

    /**
     * Create an instance of {@link ResetFrequency }
     * 
     */
    public ResetFrequency createResetFrequency() {
        return new ResetFrequency();
    }

    /**
     * Create an instance of {@link RoutingIds }
     * 
     */
    public RoutingIds createRoutingIds() {
        return new RoutingIds();
    }

    /**
     * Create an instance of {@link PersonRole }
     * 
     */
    public PersonRole createPersonRole() {
        return new PersonRole();
    }

    /**
     * Create an instance of {@link EventStatus }
     * 
     */
    public EventStatus createEventStatus() {
        return new EventStatus();
    }

    /**
     * Create an instance of {@link DeterminationMethod }
     * 
     */
    public DeterminationMethod createDeterminationMethod() {
        return new DeterminationMethod();
    }

    /**
     * Create an instance of {@link InterestRateStreamReference }
     * 
     */
    public InterestRateStreamReference createInterestRateStreamReference() {
        return new InterestRateStreamReference();
    }

    /**
     * Create an instance of {@link PartyTradeIdentifierReference }
     * 
     */
    public PartyTradeIdentifierReference createPartyTradeIdentifierReference() {
        return new PartyTradeIdentifierReference();
    }

    /**
     * Create an instance of {@link CashflowId }
     * 
     */
    public CashflowId createCashflowId() {
        return new CashflowId();
    }

    /**
     * Create an instance of {@link Lien }
     * 
     */
    public Lien createLien() {
        return new Lien();
    }

    /**
     * Create an instance of {@link PercentageTolerance }
     * 
     */
    public PercentageTolerance createPercentageTolerance() {
        return new PercentageTolerance();
    }

    /**
     * Create an instance of {@link Obligations }
     * 
     */
    public Obligations createObligations() {
        return new Obligations();
    }

    /**
     * Create an instance of {@link CommodityPricingDates }
     * 
     */
    public CommodityPricingDates createCommodityPricingDates() {
        return new CommodityPricingDates();
    }

    /**
     * Create an instance of {@link AnyAssetReference }
     * 
     */
    public AnyAssetReference createAnyAssetReference() {
        return new AnyAssetReference();
    }

    /**
     * Create an instance of {@link CommodityPremium }
     * 
     */
    public CommodityPremium createCommodityPremium() {
        return new CommodityPremium();
    }

    /**
     * Create an instance of {@link InstrumentTradePrincipal }
     * 
     */
    public InstrumentTradePrincipal createInstrumentTradePrincipal() {
        return new InstrumentTradePrincipal();
    }

    /**
     * Create an instance of {@link EventProposedMatch }
     * 
     */
    public EventProposedMatch createEventProposedMatch() {
        return new EventProposedMatch();
    }

    /**
     * Create an instance of {@link Quanto }
     * 
     */
    public Quanto createQuanto() {
        return new Quanto();
    }

    /**
     * Create an instance of {@link AssetValuation }
     * 
     */
    public AssetValuation createAssetValuation() {
        return new AssetValuation();
    }

    /**
     * Create an instance of {@link MandatoryEarlyTerminationAdjustedDates }
     * 
     */
    public MandatoryEarlyTerminationAdjustedDates createMandatoryEarlyTerminationAdjustedDates() {
        return new MandatoryEarlyTerminationAdjustedDates();
    }

    /**
     * Create an instance of {@link PhysicalSettlementPeriod }
     * 
     */
    public PhysicalSettlementPeriod createPhysicalSettlementPeriod() {
        return new PhysicalSettlementPeriod();
    }

    /**
     * Create an instance of {@link ReferencePoolItem }
     * 
     */
    public ReferencePoolItem createReferencePoolItem() {
        return new ReferencePoolItem();
    }

    /**
     * Create an instance of {@link FeaturePayment }
     * 
     */
    public FeaturePayment createFeaturePayment() {
        return new FeaturePayment();
    }

    /**
     * Create an instance of {@link LimitedCreditDefaultSwap }
     * 
     */
    public LimitedCreditDefaultSwap createLimitedCreditDefaultSwap() {
        return new LimitedCreditDefaultSwap();
    }

    /**
     * Create an instance of {@link FloatingRate }
     * 
     */
    public FloatingRate createFloatingRate() {
        return new FloatingRate();
    }

    /**
     * Create an instance of {@link Unit }
     * 
     */
    public Unit createUnit() {
        return new Unit();
    }

    /**
     * Create an instance of {@link Approvals }
     * 
     */
    public Approvals createApprovals() {
        return new Approvals();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link InformationProvider }
     * 
     */
    public InformationProvider createInformationProvider() {
        return new InformationProvider();
    }

    /**
     * Create an instance of {@link PriceQuoteUnits }
     * 
     */
    public PriceQuoteUnits createPriceQuoteUnits() {
        return new PriceQuoteUnits();
    }

    /**
     * Create an instance of {@link CoalTransportationEquipment }
     * 
     */
    public CoalTransportationEquipment createCoalTransportationEquipment() {
        return new CoalTransportationEquipment();
    }

    /**
     * Create an instance of {@link FxRateSet }
     * 
     */
    public FxRateSet createFxRateSet() {
        return new FxRateSet();
    }

    /**
     * Create an instance of {@link CommodityBusinessCalendar }
     * 
     */
    public CommodityBusinessCalendar createCommodityBusinessCalendar() {
        return new CommodityBusinessCalendar();
    }

    /**
     * Create an instance of {@link PricingStructurePoint }
     * 
     */
    public PricingStructurePoint createPricingStructurePoint() {
        return new PricingStructurePoint();
    }

    /**
     * Create an instance of {@link Formula }
     * 
     */
    public Formula createFormula() {
        return new Formula();
    }

    /**
     * Create an instance of {@link FloatingRateDefinition }
     * 
     */
    public FloatingRateDefinition createFloatingRateDefinition() {
        return new FloatingRateDefinition();
    }

    /**
     * Create an instance of {@link CommodityFrequencyType }
     * 
     */
    public CommodityFrequencyType createCommodityFrequencyType() {
        return new CommodityFrequencyType();
    }

    /**
     * Create an instance of {@link EarlyTerminationProvision }
     * 
     */
    public EarlyTerminationProvision createEarlyTerminationProvision() {
        return new EarlyTerminationProvision();
    }

    /**
     * Create an instance of {@link CommodityQuantityFrequency }
     * 
     */
    public CommodityQuantityFrequency createCommodityQuantityFrequency() {
        return new CommodityQuantityFrequency();
    }

    /**
     * Create an instance of {@link DeterminationMethodReference }
     * 
     */
    public DeterminationMethodReference createDeterminationMethodReference() {
        return new DeterminationMethodReference();
    }

    /**
     * Create an instance of {@link BusinessCenters }
     * 
     */
    public BusinessCenters createBusinessCenters() {
        return new BusinessCenters();
    }

    /**
     * Create an instance of {@link FxBoundary }
     * 
     */
    public FxBoundary createFxBoundary() {
        return new FxBoundary();
    }

    /**
     * Create an instance of {@link InterestAccrualsMethod }
     * 
     */
    public InterestAccrualsMethod createInterestAccrualsMethod() {
        return new InterestAccrualsMethod();
    }

    /**
     * Create an instance of {@link Allocations }
     * 
     */
    public Allocations createAllocations() {
        return new Allocations();
    }

    /**
     * Create an instance of {@link RegulatorId }
     * 
     */
    public RegulatorId createRegulatorId() {
        return new RegulatorId();
    }

    /**
     * Create an instance of {@link InterestAccrualsCompoundingMethod }
     * 
     */
    public InterestAccrualsCompoundingMethod createInterestAccrualsCompoundingMethod() {
        return new InterestAccrualsCompoundingMethod();
    }

    /**
     * Create an instance of {@link ActualPrice }
     * 
     */
    public ActualPrice createActualPrice() {
        return new ActualPrice();
    }

    /**
     * Create an instance of {@link AdjustableDateOrRelativeDateSequence }
     * 
     */
    public AdjustableDateOrRelativeDateSequence createAdjustableDateOrRelativeDateSequence() {
        return new AdjustableDateOrRelativeDateSequence();
    }

    /**
     * Create an instance of {@link TradeHeader }
     * 
     */
    public TradeHeader createTradeHeader() {
        return new TradeHeader();
    }

    /**
     * Create an instance of {@link QueryParameter }
     * 
     */
    public QueryParameter createQueryParameter() {
        return new QueryParameter();
    }

    /**
     * Create an instance of {@link DateList }
     * 
     */
    public DateList createDateList() {
        return new DateList();
    }

    /**
     * Create an instance of {@link EntityName }
     * 
     */
    public EntityName createEntityName() {
        return new EntityName();
    }

    /**
     * Create an instance of {@link CommoditySettlementPeriodsNotionalQuantity }
     * 
     */
    public CommoditySettlementPeriodsNotionalQuantity createCommoditySettlementPeriodsNotionalQuantity() {
        return new CommoditySettlementPeriodsNotionalQuantity();
    }

    /**
     * Create an instance of {@link TradeDifference }
     * 
     */
    public TradeDifference createTradeDifference() {
        return new TradeDifference();
    }

    /**
     * Create an instance of {@link MessageId }
     * 
     */
    public MessageId createMessageId() {
        return new MessageId();
    }

    /**
     * Create an instance of {@link FxOptionPayout }
     * 
     */
    public FxOptionPayout createFxOptionPayout() {
        return new FxOptionPayout();
    }

    /**
     * Create an instance of {@link OptionType }
     * 
     */
    public OptionType createOptionType() {
        return new OptionType();
    }

    /**
     * Create an instance of {@link AbsoluteTolerance }
     * 
     */
    public AbsoluteTolerance createAbsoluteTolerance() {
        return new AbsoluteTolerance();
    }

    /**
     * Create an instance of {@link TerminatingEvent }
     * 
     */
    public TerminatingEvent createTerminatingEvent() {
        return new TerminatingEvent();
    }

    /**
     * Create an instance of {@link PhysicalExercise }
     * 
     */
    public PhysicalExercise createPhysicalExercise() {
        return new PhysicalExercise();
    }

    /**
     * Create an instance of {@link PhysicalSettlement }
     * 
     */
    public PhysicalSettlement createPhysicalSettlement() {
        return new PhysicalSettlement();
    }

    /**
     * Create an instance of {@link Quotation }
     * 
     */
    public Quotation createQuotation() {
        return new Quotation();
    }

    /**
     * Create an instance of {@link IndependentAmount }
     * 
     */
    public IndependentAmount createIndependentAmount() {
        return new IndependentAmount();
    }

    /**
     * Create an instance of {@link SwapCurveValuation }
     * 
     */
    public SwapCurveValuation createSwapCurveValuation() {
        return new SwapCurveValuation();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link AdditionalDisruptionEvents }
     * 
     */
    public AdditionalDisruptionEvents createAdditionalDisruptionEvents() {
        return new AdditionalDisruptionEvents();
    }

    /**
     * Create an instance of {@link PartyRelationshipDocumentation }
     * 
     */
    public PartyRelationshipDocumentation createPartyRelationshipDocumentation() {
        return new PartyRelationshipDocumentation();
    }

    /**
     * Create an instance of {@link PricingParameterDerivative }
     * 
     */
    public PricingParameterDerivative createPricingParameterDerivative() {
        return new PricingParameterDerivative();
    }

    /**
     * Create an instance of {@link FixedPaymentLeg }
     * 
     */
    public FixedPaymentLeg createFixedPaymentLeg() {
        return new FixedPaymentLeg();
    }

    /**
     * Create an instance of {@link EmbeddedOptionType }
     * 
     */
    public EmbeddedOptionType createEmbeddedOptionType() {
        return new EmbeddedOptionType();
    }

    /**
     * Create an instance of {@link TradeInformation }
     * 
     */
    public TradeInformation createTradeInformation() {
        return new TradeInformation();
    }

    /**
     * Create an instance of {@link TimeDimension }
     * 
     */
    public TimeDimension createTimeDimension() {
        return new TimeDimension();
    }

    /**
     * Create an instance of {@link WithdrawalReason }
     * 
     */
    public WithdrawalReason createWithdrawalReason() {
        return new WithdrawalReason();
    }

    /**
     * Create an instance of {@link InterestLegResetDates }
     * 
     */
    public InterestLegResetDates createInterestLegResetDates() {
        return new InterestLegResetDates();
    }

    /**
     * Create an instance of {@link ElectricityDelivery }
     * 
     */
    public ElectricityDelivery createElectricityDelivery() {
        return new ElectricityDelivery();
    }

    /**
     * Create an instance of {@link CorrespondentInformation }
     * 
     */
    public CorrespondentInformation createCorrespondentInformation() {
        return new CorrespondentInformation();
    }

    /**
     * Create an instance of {@link NotionalReference }
     * 
     */
    public NotionalReference createNotionalReference() {
        return new NotionalReference();
    }

    /**
     * Create an instance of {@link FloatingAmountProvisions }
     * 
     */
    public FloatingAmountProvisions createFloatingAmountProvisions() {
        return new FloatingAmountProvisions();
    }

    /**
     * Create an instance of {@link QuantityUnit }
     * 
     */
    public QuantityUnit createQuantityUnit() {
        return new QuantityUnit();
    }

    /**
     * Create an instance of {@link PositiveStep }
     * 
     */
    public PositiveStep createPositiveStep() {
        return new PositiveStep();
    }

    /**
     * Create an instance of {@link CommodityDeliveryPoint }
     * 
     */
    public CommodityDeliveryPoint createCommodityDeliveryPoint() {
        return new CommodityDeliveryPoint();
    }

    /**
     * Create an instance of {@link PhysicalSettlementTerms }
     * 
     */
    public PhysicalSettlementTerms createPhysicalSettlementTerms() {
        return new PhysicalSettlementTerms();
    }

    /**
     * Create an instance of {@link ContractualTermsSupplement }
     * 
     */
    public ContractualTermsSupplement createContractualTermsSupplement() {
        return new ContractualTermsSupplement();
    }

    /**
     * Create an instance of {@link CalendarSpread }
     * 
     */
    public CalendarSpread createCalendarSpread() {
        return new CalendarSpread();
    }

    /**
     * Create an instance of {@link PartyRelationship }
     * 
     */
    public PartyRelationship createPartyRelationship() {
        return new PartyRelationship();
    }

    /**
     * Create an instance of {@link ReferenceInformation }
     * 
     */
    public ReferenceInformation createReferenceInformation() {
        return new ReferenceInformation();
    }

    /**
     * Create an instance of {@link FacilityType }
     * 
     */
    public FacilityType createFacilityType() {
        return new FacilityType();
    }

    /**
     * Create an instance of {@link AgreementType }
     * 
     */
    public AgreementType createAgreementType() {
        return new AgreementType();
    }

    /**
     * Create an instance of {@link CommodityMultipleExercise }
     * 
     */
    public CommodityMultipleExercise createCommodityMultipleExercise() {
        return new CommodityMultipleExercise();
    }

    /**
     * Create an instance of {@link MandatoryEarlyTermination }
     * 
     */
    public MandatoryEarlyTermination createMandatoryEarlyTermination() {
        return new MandatoryEarlyTermination();
    }

    /**
     * Create an instance of {@link VarianceLeg }
     * 
     */
    public VarianceLeg createVarianceLeg() {
        return new VarianceLeg();
    }

    /**
     * Create an instance of {@link FxAmericanExercise }
     * 
     */
    public FxAmericanExercise createFxAmericanExercise() {
        return new FxAmericanExercise();
    }

    /**
     * Create an instance of {@link FxDigitalAmericanExercise }
     * 
     */
    public FxDigitalAmericanExercise createFxDigitalAmericanExercise() {
        return new FxDigitalAmericanExercise();
    }

    /**
     * Create an instance of {@link TradeValuationItem }
     * 
     */
    public TradeValuationItem createTradeValuationItem() {
        return new TradeValuationItem();
    }

    /**
     * Create an instance of {@link IndexName }
     * 
     */
    public IndexName createIndexName() {
        return new IndexName();
    }

    /**
     * Create an instance of {@link PartyPortfolioName }
     * 
     */
    public PartyPortfolioName createPartyPortfolioName() {
        return new PartyPortfolioName();
    }

    /**
     * Create an instance of {@link ValuationDate }
     * 
     */
    public ValuationDate createValuationDate() {
        return new ValuationDate();
    }

    /**
     * Create an instance of {@link MasterConfirmationAnnexType }
     * 
     */
    public MasterConfirmationAnnexType createMasterConfirmationAnnexType() {
        return new MasterConfirmationAnnexType();
    }

    /**
     * Create an instance of {@link InterestLegCalculationPeriodDatesReference }
     * 
     */
    public InterestLegCalculationPeriodDatesReference createInterestLegCalculationPeriodDatesReference() {
        return new InterestLegCalculationPeriodDatesReference();
    }

    /**
     * Create an instance of {@link LowerBound }
     * 
     */
    public LowerBound createLowerBound() {
        return new LowerBound();
    }

    /**
     * Create an instance of {@link ReportingRegimeName }
     * 
     */
    public ReportingRegimeName createReportingRegimeName() {
        return new ReportingRegimeName();
    }

    /**
     * Create an instance of {@link Notional }
     * 
     */
    public Notional createNotional() {
        return new Notional();
    }

    /**
     * Create an instance of {@link FutureValueAmount }
     * 
     */
    public FutureValueAmount createFutureValueAmount() {
        return new FutureValueAmount();
    }

    /**
     * Create an instance of {@link OptionalEarlyTerminationAdjustedDates }
     * 
     */
    public OptionalEarlyTerminationAdjustedDates createOptionalEarlyTerminationAdjustedDates() {
        return new OptionalEarlyTerminationAdjustedDates();
    }

    /**
     * Create an instance of {@link CommodityNotionalQuantitySchedule }
     * 
     */
    public CommodityNotionalQuantitySchedule createCommodityNotionalQuantitySchedule() {
        return new CommodityNotionalQuantitySchedule();
    }

    /**
     * Create an instance of {@link BasketName }
     * 
     */
    public BasketName createBasketName() {
        return new BasketName();
    }

    /**
     * Create an instance of {@link GrossCashflow }
     * 
     */
    public GrossCashflow createGrossCashflow() {
        return new GrossCashflow();
    }

    /**
     * Create an instance of {@link MultiDimensionalPricingData }
     * 
     */
    public MultiDimensionalPricingData createMultiDimensionalPricingData() {
        return new MultiDimensionalPricingData();
    }

    /**
     * Create an instance of {@link SinglePayment }
     * 
     */
    public SinglePayment createSinglePayment() {
        return new SinglePayment();
    }

    /**
     * Create an instance of {@link Sensitivity }
     * 
     */
    public Sensitivity createSensitivity() {
        return new Sensitivity();
    }

    /**
     * Create an instance of {@link ContractualSupplement }
     * 
     */
    public ContractualSupplement createContractualSupplement() {
        return new ContractualSupplement();
    }

    /**
     * Create an instance of {@link PricingInputReplacement }
     * 
     */
    public PricingInputReplacement createPricingInputReplacement() {
        return new PricingInputReplacement();
    }

    /**
     * Create an instance of {@link LinkId }
     * 
     */
    public LinkId createLinkId() {
        return new LinkId();
    }

    /**
     * Create an instance of {@link PositiveAmountSchedule }
     * 
     */
    public PositiveAmountSchedule createPositiveAmountSchedule() {
        return new PositiveAmountSchedule();
    }

    /**
     * Create an instance of {@link AdjustableOrRelativeDate }
     * 
     */
    public AdjustableOrRelativeDate createAdjustableOrRelativeDate() {
        return new AdjustableOrRelativeDate();
    }

    /**
     * Create an instance of {@link NonNegativeAmountSchedule }
     * 
     */
    public NonNegativeAmountSchedule createNonNegativeAmountSchedule() {
        return new NonNegativeAmountSchedule();
    }

    /**
     * Create an instance of {@link CommoditySpreadSchedule }
     * 
     */
    public CommoditySpreadSchedule createCommoditySpreadSchedule() {
        return new CommoditySpreadSchedule();
    }

    /**
     * Create an instance of {@link OptionNumericStrike }
     * 
     */
    public OptionNumericStrike createOptionNumericStrike() {
        return new OptionNumericStrike();
    }

    /**
     * Create an instance of {@link Period }
     * 
     */
    public Period createPeriod() {
        return new Period();
    }

    /**
     * Create an instance of {@link MainPublication }
     * 
     */
    public MainPublication createMainPublication() {
        return new MainPublication();
    }

    /**
     * Create an instance of {@link StrikeSchedule }
     * 
     */
    public StrikeSchedule createStrikeSchedule() {
        return new StrikeSchedule();
    }

    /**
     * Create an instance of {@link CommodityBase }
     * 
     */
    public CommodityBase createCommodityBase() {
        return new CommodityBase();
    }

    /**
     * Create an instance of {@link InstrumentSet }
     * 
     */
    public InstrumentSet createInstrumentSet() {
        return new InstrumentSet();
    }

    /**
     * Create an instance of {@link ExecutionVenueType }
     * 
     */
    public ExecutionVenueType createExecutionVenueType() {
        return new ExecutionVenueType();
    }

    /**
     * Create an instance of {@link DividendPeriodPayment }
     * 
     */
    public DividendPeriodPayment createDividendPeriodPayment() {
        return new DividendPeriodPayment();
    }

    /**
     * Create an instance of {@link SensitivitySet }
     * 
     */
    public SensitivitySet createSensitivitySet() {
        return new SensitivitySet();
    }

    /**
     * Create an instance of {@link FxStrikePrice }
     * 
     */
    public FxStrikePrice createFxStrikePrice() {
        return new FxStrikePrice();
    }

    /**
     * Create an instance of {@link NonPeriodicFixedPriceLeg }
     * 
     */
    public NonPeriodicFixedPriceLeg createNonPeriodicFixedPriceLeg() {
        return new NonPeriodicFixedPriceLeg();
    }

    /**
     * Create an instance of {@link FrequencyType }
     * 
     */
    public FrequencyType createFrequencyType() {
        return new FrequencyType();
    }

    /**
     * Create an instance of {@link TermCurve }
     * 
     */
    public TermCurve createTermCurve() {
        return new TermCurve();
    }

    /**
     * Create an instance of {@link RelativeDateOffset }
     * 
     */
    public RelativeDateOffset createRelativeDateOffset() {
        return new RelativeDateOffset();
    }

    /**
     * Create an instance of {@link ElectricityDeliveryPoint }
     * 
     */
    public ElectricityDeliveryPoint createElectricityDeliveryPoint() {
        return new ElectricityDeliveryPoint();
    }

    /**
     * Create an instance of {@link CommodityAmericanExercise }
     * 
     */
    public CommodityAmericanExercise createCommodityAmericanExercise() {
        return new CommodityAmericanExercise();
    }

    /**
     * Create an instance of {@link CommodityFxType }
     * 
     */
    public CommodityFxType createCommodityFxType() {
        return new CommodityFxType();
    }

    /**
     * Create an instance of {@link CoalDelivery }
     * 
     */
    public CoalDelivery createCoalDelivery() {
        return new CoalDelivery();
    }

    /**
     * Create an instance of {@link Commission }
     * 
     */
    public Commission createCommission() {
        return new Commission();
    }

    /**
     * Create an instance of {@link BusinessCentersReference }
     * 
     */
    public BusinessCentersReference createBusinessCentersReference() {
        return new BusinessCentersReference();
    }

    /**
     * Create an instance of {@link CouponType }
     * 
     */
    public CouponType createCouponType() {
        return new CouponType();
    }

    /**
     * Create an instance of {@link CashSettlement }
     * 
     */
    public CashSettlement createCashSettlement() {
        return new CashSettlement();
    }

    /**
     * Create an instance of {@link FxSwapLeg }
     * 
     */
    public FxSwapLeg createFxSwapLeg() {
        return new FxSwapLeg();
    }

    /**
     * Create an instance of {@link ReferenceSwapCurve }
     * 
     */
    public ReferenceSwapCurve createReferenceSwapCurve() {
        return new ReferenceSwapCurve();
    }

    /**
     * Create an instance of {@link ObservationSchedule }
     * 
     */
    public ObservationSchedule createObservationSchedule() {
        return new ObservationSchedule();
    }

    /**
     * Create an instance of {@link SwaptionPhysicalSettlement }
     * 
     */
    public SwaptionPhysicalSettlement createSwaptionPhysicalSettlement() {
        return new SwaptionPhysicalSettlement();
    }

    /**
     * Create an instance of {@link AdditionalPaymentAmount }
     * 
     */
    public AdditionalPaymentAmount createAdditionalPaymentAmount() {
        return new AdditionalPaymentAmount();
    }

    /**
     * Create an instance of {@link InitialPayment }
     * 
     */
    public InitialPayment createInitialPayment() {
        return new InitialPayment();
    }

    /**
     * Create an instance of {@link PositiveMoney }
     * 
     */
    public PositiveMoney createPositiveMoney() {
        return new PositiveMoney();
    }

    /**
     * Create an instance of {@link MarketDisruption }
     * 
     */
    public MarketDisruption createMarketDisruption() {
        return new MarketDisruption();
    }

    /**
     * Create an instance of {@link PrevailingTime }
     * 
     */
    public PrevailingTime createPrevailingTime() {
        return new PrevailingTime();
    }

    /**
     * Create an instance of {@link ExerciseProcedure }
     * 
     */
    public ExerciseProcedure createExerciseProcedure() {
        return new ExerciseProcedure();
    }

    /**
     * Create an instance of {@link FxFixingDate }
     * 
     */
    public FxFixingDate createFxFixingDate() {
        return new FxFixingDate();
    }

    /**
     * Create an instance of {@link CommodityPhysicalQuantitySchedule }
     * 
     */
    public CommodityPhysicalQuantitySchedule createCommodityPhysicalQuantitySchedule() {
        return new CommodityPhysicalQuantitySchedule();
    }

    /**
     * Create an instance of {@link CreditSupportAgreement }
     * 
     */
    public CreditSupportAgreement createCreditSupportAgreement() {
        return new CreditSupportAgreement();
    }

    /**
     * Create an instance of {@link SwapAdditionalTerms }
     * 
     */
    public SwapAdditionalTerms createSwapAdditionalTerms() {
        return new SwapAdditionalTerms();
    }

    /**
     * Create an instance of {@link BusinessUnitReference }
     * 
     */
    public BusinessUnitReference createBusinessUnitReference() {
        return new BusinessUnitReference();
    }

    /**
     * Create an instance of {@link CommodityHubCode }
     * 
     */
    public CommodityHubCode createCommodityHubCode() {
        return new CommodityHubCode();
    }

    /**
     * Create an instance of {@link DateRelativeToPaymentDates }
     * 
     */
    public DateRelativeToPaymentDates createDateRelativeToPaymentDates() {
        return new DateRelativeToPaymentDates();
    }

    /**
     * Create an instance of {@link PrincipalExchange }
     * 
     */
    public PrincipalExchange createPrincipalExchange() {
        return new PrincipalExchange();
    }

    /**
     * Create an instance of {@link SettlementInformation }
     * 
     */
    public SettlementInformation createSettlementInformation() {
        return new SettlementInformation();
    }

    /**
     * Create an instance of {@link SettlementPriceDefaultElection }
     * 
     */
    public SettlementPriceDefaultElection createSettlementPriceDefaultElection() {
        return new SettlementPriceDefaultElection();
    }

    /**
     * Create an instance of {@link FallbackReferencePrice }
     * 
     */
    public FallbackReferencePrice createFallbackReferencePrice() {
        return new FallbackReferencePrice();
    }

    /**
     * Create an instance of {@link Routing }
     * 
     */
    public Routing createRouting() {
        return new Routing();
    }

    /**
     * Create an instance of {@link CreditDocument }
     * 
     */
    public CreditDocument createCreditDocument() {
        return new CreditDocument();
    }

    /**
     * Create an instance of {@link PassThrough }
     * 
     */
    public PassThrough createPassThrough() {
        return new PassThrough();
    }

    /**
     * Create an instance of {@link VarianceAmount }
     * 
     */
    public VarianceAmount createVarianceAmount() {
        return new VarianceAmount();
    }

    /**
     * Create an instance of {@link ValuationPostponement }
     * 
     */
    public ValuationPostponement createValuationPostponement() {
        return new ValuationPostponement();
    }

    /**
     * Create an instance of {@link Return }
     * 
     */
    public Return createReturn() {
        return new Return();
    }

    /**
     * Create an instance of {@link BusinessProcess }
     * 
     */
    public BusinessProcess createBusinessProcess() {
        return new BusinessProcess();
    }

    /**
     * Create an instance of {@link CommodityPayRelativeToEvent }
     * 
     */
    public CommodityPayRelativeToEvent createCommodityPayRelativeToEvent() {
        return new CommodityPayRelativeToEvent();
    }

    /**
     * Create an instance of {@link MasterAgreement }
     * 
     */
    public MasterAgreement createMasterAgreement() {
        return new MasterAgreement();
    }

    /**
     * Create an instance of {@link IdentifiedDate }
     * 
     */
    public IdentifiedDate createIdentifiedDate() {
        return new IdentifiedDate();
    }

    /**
     * Create an instance of {@link AssetClass }
     * 
     */
    public AssetClass createAssetClass() {
        return new AssetClass();
    }

    /**
     * Create an instance of {@link RequestedAction }
     * 
     */
    public RequestedAction createRequestedAction() {
        return new RequestedAction();
    }

    /**
     * Create an instance of {@link OptionExpiry }
     * 
     */
    public OptionExpiry createOptionExpiry() {
        return new OptionExpiry();
    }

    /**
     * Create an instance of {@link BusinessUnit }
     * 
     */
    public BusinessUnit createBusinessUnit() {
        return new BusinessUnit();
    }

    /**
     * Create an instance of {@link Tranche }
     * 
     */
    public Tranche createTranche() {
        return new Tranche();
    }

    /**
     * Create an instance of {@link GenericAgreement }
     * 
     */
    public GenericAgreement createGenericAgreement() {
        return new GenericAgreement();
    }

    /**
     * Create an instance of {@link CommodityPhysicalEuropeanExercise }
     * 
     */
    public CommodityPhysicalEuropeanExercise createCommodityPhysicalEuropeanExercise() {
        return new CommodityPhysicalEuropeanExercise();
    }

    /**
     * Create an instance of {@link LegalEntityReference }
     * 
     */
    public LegalEntityReference createLegalEntityReference() {
        return new LegalEntityReference();
    }

    /**
     * Create an instance of {@link NonNegativeMoney }
     * 
     */
    public NonNegativeMoney createNonNegativeMoney() {
        return new NonNegativeMoney();
    }

    /**
     * Create an instance of {@link ReportingRegime }
     * 
     */
    public ReportingRegime createReportingRegime() {
        return new ReportingRegime();
    }

    /**
     * Create an instance of {@link CommodityPhysicalAmericanExercise }
     * 
     */
    public CommodityPhysicalAmericanExercise createCommodityPhysicalAmericanExercise() {
        return new CommodityPhysicalAmericanExercise();
    }

    /**
     * Create an instance of {@link CorrelationAmount }
     * 
     */
    public CorrelationAmount createCorrelationAmount() {
        return new CorrelationAmount();
    }

    /**
     * Create an instance of {@link FxEuropeanExercise }
     * 
     */
    public FxEuropeanExercise createFxEuropeanExercise() {
        return new FxEuropeanExercise();
    }

    /**
     * Create an instance of {@link AssetMeasureType }
     * 
     */
    public AssetMeasureType createAssetMeasureType() {
        return new AssetMeasureType();
    }

    /**
     * Create an instance of {@link AccountName }
     * 
     */
    public AccountName createAccountName() {
        return new AccountName();
    }

    /**
     * Create an instance of {@link FxCashSettlement }
     * 
     */
    public FxCashSettlement createFxCashSettlement() {
        return new FxCashSettlement();
    }

    /**
     * Create an instance of {@link ProtectionTerms }
     * 
     */
    public ProtectionTerms createProtectionTerms() {
        return new ProtectionTerms();
    }

    /**
     * Create an instance of {@link PositionHistory }
     * 
     */
    public PositionHistory createPositionHistory() {
        return new PositionHistory();
    }

    /**
     * Create an instance of {@link LegId }
     * 
     */
    public LegId createLegId() {
        return new LegId();
    }

    /**
     * Create an instance of {@link NonCorrectableRequestMessage }
     * 
     */
    public NonCorrectableRequestMessage createNonCorrectableRequestMessage() {
        return new NonCorrectableRequestMessage();
    }

    /**
     * Create an instance of {@link ReportSectionIdentification }
     * 
     */
    public ReportSectionIdentification createReportSectionIdentification() {
        return new ReportSectionIdentification();
    }

    /**
     * Create an instance of {@link PositionConstituent }
     * 
     */
    public PositionConstituent createPositionConstituent() {
        return new PositionConstituent();
    }

    /**
     * Create an instance of {@link CompressionActivity }
     * 
     */
    public CompressionActivity createCompressionActivity() {
        return new CompressionActivity();
    }

    /**
     * Create an instance of {@link EventsChoice }
     * 
     */
    public EventsChoice createEventsChoice() {
        return new EventsChoice();
    }

    /**
     * Create an instance of {@link InterestRateStream }
     * 
     */
    public InterestRateStream createInterestRateStream() {
        return new InterestRateStream();
    }

    /**
     * Create an instance of {@link ExerciseNotice }
     * 
     */
    public ExerciseNotice createExerciseNotice() {
        return new ExerciseNotice();
    }

    /**
     * Create an instance of {@link PortfolioReferenceBase }
     * 
     */
    public PortfolioReferenceBase createPortfolioReferenceBase() {
        return new PortfolioReferenceBase();
    }

    /**
     * Create an instance of {@link CalculationPeriodFrequency }
     * 
     */
    public CalculationPeriodFrequency createCalculationPeriodFrequency() {
        return new CalculationPeriodFrequency();
    }

    /**
     * Create an instance of {@link ElectricityTransmissionContingencyType }
     * 
     */
    public ElectricityTransmissionContingencyType createElectricityTransmissionContingencyType() {
        return new ElectricityTransmissionContingencyType();
    }

    /**
     * Create an instance of {@link ForwardRateCurve }
     * 
     */
    public ForwardRateCurve createForwardRateCurve() {
        return new ForwardRateCurve();
    }

    /**
     * Create an instance of {@link FutureId }
     * 
     */
    public FutureId createFutureId() {
        return new FutureId();
    }

    /**
     * Create an instance of {@link FxTrigger }
     * 
     */
    public FxTrigger createFxTrigger() {
        return new FxTrigger();
    }

    /**
     * Create an instance of {@link GasQuality }
     * 
     */
    public GasQuality createGasQuality() {
        return new GasQuality();
    }

    /**
     * Create an instance of {@link CompressionType }
     * 
     */
    public CompressionType createCompressionType() {
        return new CompressionType();
    }

    /**
     * Create an instance of {@link BasketReferenceInformation }
     * 
     */
    public BasketReferenceInformation createBasketReferenceInformation() {
        return new BasketReferenceInformation();
    }

    /**
     * Create an instance of {@link ProtectionTermsReference }
     * 
     */
    public ProtectionTermsReference createProtectionTermsReference() {
        return new ProtectionTermsReference();
    }

    /**
     * Create an instance of {@link ContractId }
     * 
     */
    public ContractId createContractId() {
        return new ContractId();
    }

    /**
     * Create an instance of {@link Asian }
     * 
     */
    public Asian createAsian() {
        return new Asian();
    }

    /**
     * Create an instance of {@link CollateralizationType }
     * 
     */
    public CollateralizationType createCollateralizationType() {
        return new CollateralizationType();
    }

    /**
     * Create an instance of {@link VersionedTradeId }
     * 
     */
    public VersionedTradeId createVersionedTradeId() {
        return new VersionedTradeId();
    }

    /**
     * Create an instance of {@link CommodityExercise }
     * 
     */
    public CommodityExercise createCommodityExercise() {
        return new CommodityExercise();
    }

    /**
     * Create an instance of {@link CalculationPeriodsReference }
     * 
     */
    public CalculationPeriodsReference createCalculationPeriodsReference() {
        return new CalculationPeriodsReference();
    }

    /**
     * Create an instance of {@link DividendLeg }
     * 
     */
    public DividendLeg createDividendLeg() {
        return new DividendLeg();
    }

    /**
     * Create an instance of {@link MarketReference }
     * 
     */
    public MarketReference createMarketReference() {
        return new MarketReference();
    }

    /**
     * Create an instance of {@link Collateral }
     * 
     */
    public Collateral createCollateral() {
        return new Collateral();
    }

    /**
     * Create an instance of {@link BoundedCorrelation }
     * 
     */
    public BoundedCorrelation createBoundedCorrelation() {
        return new BoundedCorrelation();
    }

    /**
     * Create an instance of {@link CommodityExpireRelativeToEvent }
     * 
     */
    public CommodityExpireRelativeToEvent createCommodityExpireRelativeToEvent() {
        return new CommodityExpireRelativeToEvent();
    }

    /**
     * Create an instance of {@link EquityExerciseValuationSettlement }
     * 
     */
    public EquityExerciseValuationSettlement createEquityExerciseValuationSettlement() {
        return new EquityExerciseValuationSettlement();
    }

    /**
     * Create an instance of {@link CommodityDeliveryRisk }
     * 
     */
    public CommodityDeliveryRisk createCommodityDeliveryRisk() {
        return new CommodityDeliveryRisk();
    }

    /**
     * Create an instance of {@link CalculationPeriodDatesReference }
     * 
     */
    public CalculationPeriodDatesReference createCalculationPeriodDatesReference() {
        return new CalculationPeriodDatesReference();
    }

    /**
     * Create an instance of {@link FxOptionPremium }
     * 
     */
    public FxOptionPremium createFxOptionPremium() {
        return new FxOptionPremium();
    }

    /**
     * Create an instance of {@link ReportIdentification }
     * 
     */
    public ReportIdentification createReportIdentification() {
        return new ReportIdentification();
    }

    /**
     * Create an instance of {@link ReportingRoles }
     * 
     */
    public ReportingRoles createReportingRoles() {
        return new ReportingRoles();
    }

    /**
     * Create an instance of {@link DayCountFraction }
     * 
     */
    public DayCountFraction createDayCountFraction() {
        return new DayCountFraction();
    }

    /**
     * Create an instance of {@link FloatingRateCalculationReference }
     * 
     */
    public FloatingRateCalculationReference createFloatingRateCalculationReference() {
        return new FloatingRateCalculationReference();
    }

    /**
     * Create an instance of {@link BusinessDateRange }
     * 
     */
    public BusinessDateRange createBusinessDateRange() {
        return new BusinessDateRange();
    }

    /**
     * Create an instance of {@link ReportingCurrencyType }
     * 
     */
    public ReportingCurrencyType createReportingCurrencyType() {
        return new ReportingCurrencyType();
    }

    /**
     * Create an instance of {@link NotifyingParty }
     * 
     */
    public NotifyingParty createNotifyingParty() {
        return new NotifyingParty();
    }

    /**
     * Create an instance of {@link CommodityPhysicalQuantity }
     * 
     */
    public CommodityPhysicalQuantity createCommodityPhysicalQuantity() {
        return new CommodityPhysicalQuantity();
    }

    /**
     * Create an instance of {@link MakeWholeProvisions }
     * 
     */
    public MakeWholeProvisions createMakeWholeProvisions() {
        return new MakeWholeProvisions();
    }

    /**
     * Create an instance of {@link ElectricityDeliveryType }
     * 
     */
    public ElectricityDeliveryType createElectricityDeliveryType() {
        return new ElectricityDeliveryType();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link ReturnSwapPaymentDates }
     * 
     */
    public ReturnSwapPaymentDates createReturnSwapPaymentDates() {
        return new ReturnSwapPaymentDates();
    }

    /**
     * Create an instance of {@link DateOffset }
     * 
     */
    public DateOffset createDateOffset() {
        return new DateOffset();
    }

    /**
     * Create an instance of {@link Discounting }
     * 
     */
    public Discounting createDiscounting() {
        return new Discounting();
    }

    /**
     * Create an instance of {@link PerturbationType }
     * 
     */
    public PerturbationType createPerturbationType() {
        return new PerturbationType();
    }

    /**
     * Create an instance of {@link FxAverageRateObservationSchedule }
     * 
     */
    public FxAverageRateObservationSchedule createFxAverageRateObservationSchedule() {
        return new FxAverageRateObservationSchedule();
    }

    /**
     * Create an instance of {@link Barrier }
     * 
     */
    public Barrier createBarrier() {
        return new Barrier();
    }

    /**
     * Create an instance of {@link QuoteTiming }
     * 
     */
    public QuoteTiming createQuoteTiming() {
        return new QuoteTiming();
    }

    /**
     * Create an instance of {@link WeightedAveragingObservation }
     * 
     */
    public WeightedAveragingObservation createWeightedAveragingObservation() {
        return new WeightedAveragingObservation();
    }

    /**
     * Create an instance of {@link AffectedTransactions }
     * 
     */
    public AffectedTransactions createAffectedTransactions() {
        return new AffectedTransactions();
    }

    /**
     * Create an instance of {@link DividendAdjustment }
     * 
     */
    public DividendAdjustment createDividendAdjustment() {
        return new DividendAdjustment();
    }

    /**
     * Create an instance of {@link TradeNotionalChange }
     * 
     */
    public TradeNotionalChange createTradeNotionalChange() {
        return new TradeNotionalChange();
    }

    /**
     * Create an instance of {@link PartyReference }
     * 
     */
    public PartyReference createPartyReference() {
        return new PartyReference();
    }

    /**
     * Create an instance of {@link ServiceProcessingCycle }
     * 
     */
    public ServiceProcessingCycle createServiceProcessingCycle() {
        return new ServiceProcessingCycle();
    }

    /**
     * Create an instance of {@link TelephoneNumber }
     * 
     */
    public TelephoneNumber createTelephoneNumber() {
        return new TelephoneNumber();
    }

    /**
     * Create an instance of {@link ReferencePool }
     * 
     */
    public ReferencePool createReferencePool() {
        return new ReferencePool();
    }

    /**
     * Create an instance of {@link EquityMultipleExercise }
     * 
     */
    public EquityMultipleExercise createEquityMultipleExercise() {
        return new EquityMultipleExercise();
    }

    /**
     * Create an instance of {@link TradeChangeBase }
     * 
     */
    public TradeChangeBase createTradeChangeBase() {
        return new TradeChangeBase();
    }

    /**
     * Create an instance of {@link OrganizationCharacteristic }
     * 
     */
    public OrganizationCharacteristic createOrganizationCharacteristic() {
        return new OrganizationCharacteristic();
    }

    /**
     * Create an instance of {@link DateRange }
     * 
     */
    public DateRange createDateRange() {
        return new DateRange();
    }

    /**
     * Create an instance of {@link ReportingRole }
     * 
     */
    public ReportingRole createReportingRole() {
        return new ReportingRole();
    }

    /**
     * Create an instance of {@link PartialExercise }
     * 
     */
    public PartialExercise createPartialExercise() {
        return new PartialExercise();
    }

    /**
     * Create an instance of {@link FormulaTerm }
     * 
     */
    public FormulaTerm createFormulaTerm() {
        return new FormulaTerm();
    }

    /**
     * Create an instance of {@link PriceSourceDisruption }
     * 
     */
    public PriceSourceDisruption createPriceSourceDisruption() {
        return new PriceSourceDisruption();
    }

    /**
     * Create an instance of {@link CashflowType }
     * 
     */
    public CashflowType createCashflowType() {
        return new CashflowType();
    }

    /**
     * Create an instance of {@link AccountId }
     * 
     */
    public AccountId createAccountId() {
        return new AccountId();
    }

    /**
     * Create an instance of {@link StartingDate }
     * 
     */
    public StartingDate createStartingDate() {
        return new StartingDate();
    }

    /**
     * Create an instance of {@link StubValue }
     * 
     */
    public StubValue createStubValue() {
        return new StubValue();
    }

    /**
     * Create an instance of {@link MultipleExercise }
     * 
     */
    public MultipleExercise createMultipleExercise() {
        return new MultipleExercise();
    }

    /**
     * Create an instance of {@link Documentation }
     * 
     */
    public Documentation createDocumentation() {
        return new Documentation();
    }

    /**
     * Create an instance of {@link InterestCalculation }
     * 
     */
    public InterestCalculation createInterestCalculation() {
        return new InterestCalculation();
    }

    /**
     * Create an instance of {@link RoutingId }
     * 
     */
    public RoutingId createRoutingId() {
        return new RoutingId();
    }

    /**
     * Create an instance of {@link CommodityRelativePaymentDates }
     * 
     */
    public CommodityRelativePaymentDates createCommodityRelativePaymentDates() {
        return new CommodityRelativePaymentDates();
    }

    /**
     * Create an instance of {@link SupervisoryBody }
     * 
     */
    public SupervisoryBody createSupervisoryBody() {
        return new SupervisoryBody();
    }

    /**
     * Create an instance of {@link RateSourcePage }
     * 
     */
    public RateSourcePage createRateSourcePage() {
        return new RateSourcePage();
    }

    /**
     * Create an instance of {@link TimeZone }
     * 
     */
    public TimeZone createTimeZone() {
        return new TimeZone();
    }

    /**
     * Create an instance of {@link Reason }
     * 
     */
    public Reason createReason() {
        return new Reason();
    }

    /**
     * Create an instance of {@link SettlementPeriodsFixedPrice }
     * 
     */
    public SettlementPeriodsFixedPrice createSettlementPeriodsFixedPrice() {
        return new SettlementPeriodsFixedPrice();
    }

    /**
     * Create an instance of {@link PricingDataPointCoordinate }
     * 
     */
    public PricingDataPointCoordinate createPricingDataPointCoordinate() {
        return new PricingDataPointCoordinate();
    }

    /**
     * Create an instance of {@link YieldCurveMethod }
     * 
     */
    public YieldCurveMethod createYieldCurveMethod() {
        return new YieldCurveMethod();
    }

    /**
     * Create an instance of {@link AssetOrTermPointOrPricingStructureReference }
     * 
     */
    public AssetOrTermPointOrPricingStructureReference createAssetOrTermPointOrPricingStructureReference() {
        return new AssetOrTermPointOrPricingStructureReference();
    }

    /**
     * Create an instance of {@link QuantityReference }
     * 
     */
    public QuantityReference createQuantityReference() {
        return new QuantityReference();
    }

    /**
     * Create an instance of {@link PubliclyAvailableInformation }
     * 
     */
    public PubliclyAvailableInformation createPubliclyAvailableInformation() {
        return new PubliclyAvailableInformation();
    }

    /**
     * Create an instance of {@link IntermediaryInformation }
     * 
     */
    public IntermediaryInformation createIntermediaryInformation() {
        return new IntermediaryInformation();
    }

    /**
     * Create an instance of {@link ReturnSwapAmount }
     * 
     */
    public ReturnSwapAmount createReturnSwapAmount() {
        return new ReturnSwapAmount();
    }

    /**
     * Create an instance of {@link MakeWholeAmount }
     * 
     */
    public MakeWholeAmount createMakeWholeAmount() {
        return new MakeWholeAmount();
    }

    /**
     * Create an instance of {@link ContractIdentifier }
     * 
     */
    public ContractIdentifier createContractIdentifier() {
        return new ContractIdentifier();
    }

    /**
     * Create an instance of {@link DeClear }
     * 
     */
    public DeClear createDeClear() {
        return new DeClear();
    }

    /**
     * Create an instance of {@link AgreementVersion }
     * 
     */
    public AgreementVersion createAgreementVersion() {
        return new AgreementVersion();
    }

    /**
     * Create an instance of {@link CashSettlementPaymentDate }
     * 
     */
    public CashSettlementPaymentDate createCashSettlementPaymentDate() {
        return new CashSettlementPaymentDate();
    }

    /**
     * Create an instance of {@link SpreadScheduleReference }
     * 
     */
    public SpreadScheduleReference createSpreadScheduleReference() {
        return new SpreadScheduleReference();
    }

    /**
     * Create an instance of {@link OilProductType }
     * 
     */
    public OilProductType createOilProductType() {
        return new OilProductType();
    }

    /**
     * Create an instance of {@link NotDomesticCurrency }
     * 
     */
    public NotDomesticCurrency createNotDomesticCurrency() {
        return new NotDomesticCurrency();
    }

    /**
     * Create an instance of {@link Math }
     * 
     */
    public Math createMath() {
        return new Math();
    }

    /**
     * Create an instance of {@link CalculationPeriod }
     * 
     */
    public CalculationPeriod createCalculationPeriod() {
        return new CalculationPeriod();
    }

    /**
     * Create an instance of {@link CommoditySpread }
     * 
     */
    public CommoditySpread createCommoditySpread() {
        return new CommoditySpread();
    }

    /**
     * Create an instance of {@link DividendPeriodDividend }
     * 
     */
    public DividendPeriodDividend createDividendPeriodDividend() {
        return new DividendPeriodDividend();
    }

    /**
     * Create an instance of {@link PaymentDatesReference }
     * 
     */
    public PaymentDatesReference createPaymentDatesReference() {
        return new PaymentDatesReference();
    }

    /**
     * Create an instance of {@link OptionFeature }
     * 
     */
    public OptionFeature createOptionFeature() {
        return new OptionFeature();
    }

    /**
     * Create an instance of {@link AveragingObservationList }
     * 
     */
    public AveragingObservationList createAveragingObservationList() {
        return new AveragingObservationList();
    }

    /**
     * Create an instance of {@link PositionId }
     * 
     */
    public PositionId createPositionId() {
        return new PositionId();
    }

    /**
     * Create an instance of {@link RoutingIdsAndExplicitDetails }
     * 
     */
    public RoutingIdsAndExplicitDetails createRoutingIdsAndExplicitDetails() {
        return new RoutingIdsAndExplicitDetails();
    }

    /**
     * Create an instance of {@link OilProduct }
     * 
     */
    public OilProduct createOilProduct() {
        return new OilProduct();
    }

    /**
     * Create an instance of {@link SettlementPriceSource }
     * 
     */
    public SettlementPriceSource createSettlementPriceSource() {
        return new SettlementPriceSource();
    }

    /**
     * Create an instance of {@link CrossRate }
     * 
     */
    public CrossRate createCrossRate() {
        return new CrossRate();
    }

    /**
     * Create an instance of {@link NonDeliverableSettlement }
     * 
     */
    public NonDeliverableSettlement createNonDeliverableSettlement() {
        return new NonDeliverableSettlement();
    }

    /**
     * Create an instance of {@link Premium }
     * 
     */
    public Premium createPremium() {
        return new Premium();
    }

    /**
     * Create an instance of {@link StrategyFeature }
     * 
     */
    public StrategyFeature createStrategyFeature() {
        return new StrategyFeature();
    }

    /**
     * Create an instance of {@link ParametricAdjustment }
     * 
     */
    public ParametricAdjustment createParametricAdjustment() {
        return new ParametricAdjustment();
    }

    /**
     * Create an instance of {@link PersonId }
     * 
     */
    public PersonId createPersonId() {
        return new PersonId();
    }

    /**
     * Create an instance of {@link IndexAnnexSource }
     * 
     */
    public IndexAnnexSource createIndexAnnexSource() {
        return new IndexAnnexSource();
    }

    /**
     * Create an instance of {@link ElectricityPhysicalDeliveryQuantitySchedule }
     * 
     */
    public ElectricityPhysicalDeliveryQuantitySchedule createElectricityPhysicalDeliveryQuantitySchedule() {
        return new ElectricityPhysicalDeliveryQuantitySchedule();
    }

    /**
     * Create an instance of {@link SettlementTerms }
     * 
     */
    public SettlementTerms createSettlementTerms() {
        return new SettlementTerms();
    }

    /**
     * Create an instance of {@link CommoditySettlementPeriodsPriceSchedule }
     * 
     */
    public CommoditySettlementPeriodsPriceSchedule createCommoditySettlementPeriodsPriceSchedule() {
        return new CommoditySettlementPeriodsPriceSchedule();
    }

    /**
     * Create an instance of {@link DenominatorTerm }
     * 
     */
    public DenominatorTerm createDenominatorTerm() {
        return new DenominatorTerm();
    }

    /**
     * Create an instance of {@link InterestLegCalculationPeriodDates }
     * 
     */
    public InterestLegCalculationPeriodDates createInterestLegCalculationPeriodDates() {
        return new InterestLegCalculationPeriodDates();
    }

    /**
     * Create an instance of {@link TermPoint }
     * 
     */
    public TermPoint createTermPoint() {
        return new TermPoint();
    }

    /**
     * Create an instance of {@link PaymentCalculationPeriod }
     * 
     */
    public PaymentCalculationPeriod createPaymentCalculationPeriod() {
        return new PaymentCalculationPeriod();
    }

    /**
     * Create an instance of {@link ClearanceSystem }
     * 
     */
    public ClearanceSystem createClearanceSystem() {
        return new ClearanceSystem();
    }

    /**
     * Create an instance of {@link ImpliedTrade }
     * 
     */
    public ImpliedTrade createImpliedTrade() {
        return new ImpliedTrade();
    }

    /**
     * Create an instance of {@link CashSettlementTerms }
     * 
     */
    public CashSettlementTerms createCashSettlementTerms() {
        return new CashSettlementTerms();
    }

    /**
     * Create an instance of {@link CoalProductSpecifications }
     * 
     */
    public CoalProductSpecifications createCoalProductSpecifications() {
        return new CoalProductSpecifications();
    }

    /**
     * Create an instance of {@link PositiveSchedule }
     * 
     */
    public PositiveSchedule createPositiveSchedule() {
        return new PositiveSchedule();
    }

    /**
     * Create an instance of {@link ExerciseProcedureOption }
     * 
     */
    public ExerciseProcedureOption createExerciseProcedureOption() {
        return new ExerciseProcedureOption();
    }

    /**
     * Create an instance of {@link RestructuringType }
     * 
     */
    public RestructuringType createRestructuringType() {
        return new RestructuringType();
    }

    /**
     * Create an instance of {@link TradeIdentifier }
     * 
     */
    public TradeIdentifier createTradeIdentifier() {
        return new TradeIdentifier();
    }

    /**
     * Create an instance of {@link LoanParticipation }
     * 
     */
    public LoanParticipation createLoanParticipation() {
        return new LoanParticipation();
    }

    /**
     * Create an instance of {@link AssetPool }
     * 
     */
    public AssetPool createAssetPool() {
        return new AssetPool();
    }

    /**
     * Create an instance of {@link FixedRateReference }
     * 
     */
    public FixedRateReference createFixedRateReference() {
        return new FixedRateReference();
    }

    /**
     * Create an instance of {@link GasProduct }
     * 
     */
    public GasProduct createGasProduct() {
        return new GasProduct();
    }

    /**
     * Create an instance of {@link DisruptionFallback }
     * 
     */
    public DisruptionFallback createDisruptionFallback() {
        return new DisruptionFallback();
    }

    /**
     * Create an instance of {@link IssuerId }
     * 
     */
    public IssuerId createIssuerId() {
        return new IssuerId();
    }

    /**
     * Create an instance of {@link ElectricityPhysicalDeliveryQuantity }
     * 
     */
    public ElectricityPhysicalDeliveryQuantity createElectricityPhysicalDeliveryQuantity() {
        return new ElectricityPhysicalDeliveryQuantity();
    }

    /**
     * Create an instance of {@link EquityStrike }
     * 
     */
    public EquityStrike createEquityStrike() {
        return new EquityStrike();
    }

    /**
     * Create an instance of {@link GeneralTerms }
     * 
     */
    public GeneralTerms createGeneralTerms() {
        return new GeneralTerms();
    }

    /**
     * Create an instance of {@link RelatedPerson }
     * 
     */
    public RelatedPerson createRelatedPerson() {
        return new RelatedPerson();
    }

    /**
     * Create an instance of {@link GenericDimension }
     * 
     */
    public GenericDimension createGenericDimension() {
        return new GenericDimension();
    }

    /**
     * Create an instance of {@link ElectricityDeliverySystemFirm }
     * 
     */
    public ElectricityDeliverySystemFirm createElectricityDeliverySystemFirm() {
        return new ElectricityDeliverySystemFirm();
    }

    /**
     * Create an instance of {@link TradeProcessingTimestamps }
     * 
     */
    public TradeProcessingTimestamps createTradeProcessingTimestamps() {
        return new TradeProcessingTimestamps();
    }

    /**
     * Create an instance of {@link FxBarrierFeature }
     * 
     */
    public FxBarrierFeature createFxBarrierFeature() {
        return new FxBarrierFeature();
    }

    /**
     * Create an instance of {@link TimestampTypeScheme }
     * 
     */
    public TimestampTypeScheme createTimestampTypeScheme() {
        return new TimestampTypeScheme();
    }

    /**
     * Create an instance of {@link Language }
     * 
     */
    public Language createLanguage() {
        return new Language();
    }

    /**
     * Create an instance of {@link StreetAddress }
     * 
     */
    public StreetAddress createStreetAddress() {
        return new StreetAddress();
    }

    /**
     * Create an instance of {@link LegAmount }
     * 
     */
    public LegAmount createLegAmount() {
        return new LegAmount();
    }

    /**
     * Create an instance of {@link PartyName }
     * 
     */
    public PartyName createPartyName() {
        return new PartyName();
    }

    /**
     * Create an instance of {@link CrossCurrencyMethod }
     * 
     */
    public CrossCurrencyMethod createCrossCurrencyMethod() {
        return new CrossCurrencyMethod();
    }

    /**
     * Create an instance of {@link ResponseMessageHeader }
     * 
     */
    public ResponseMessageHeader createResponseMessageHeader() {
        return new ResponseMessageHeader();
    }

    /**
     * Create an instance of {@link ZeroRateCurve }
     * 
     */
    public ZeroRateCurve createZeroRateCurve() {
        return new ZeroRateCurve();
    }

    /**
     * Create an instance of {@link ElectricityProduct }
     * 
     */
    public ElectricityProduct createElectricityProduct() {
        return new ElectricityProduct();
    }

    /**
     * Create an instance of {@link QueryParameterId }
     * 
     */
    public QueryParameterId createQueryParameterId() {
        return new QueryParameterId();
    }

    /**
     * Create an instance of {@link PricingMethod }
     * 
     */
    public PricingMethod createPricingMethod() {
        return new PricingMethod();
    }

    /**
     * Create an instance of {@link ServiceProcessingStep }
     * 
     */
    public ServiceProcessingStep createServiceProcessingStep() {
        return new ServiceProcessingStep();
    }

    /**
     * Create an instance of {@link NonNegativeStep }
     * 
     */
    public NonNegativeStep createNonNegativeStep() {
        return new NonNegativeStep();
    }

    /**
     * Create an instance of {@link Calculation }
     * 
     */
    public Calculation createCalculation() {
        return new Calculation();
    }

    /**
     * Create an instance of {@link ReportContents }
     * 
     */
    public ReportContents createReportContents() {
        return new ReportContents();
    }

    /**
     * Create an instance of {@link GasPhysicalQuantity }
     * 
     */
    public GasPhysicalQuantity createGasPhysicalQuantity() {
        return new GasPhysicalQuantity();
    }

    /**
     * Create an instance of {@link AdjustableRelativeOrPeriodicDates }
     * 
     */
    public AdjustableRelativeOrPeriodicDates createAdjustableRelativeOrPeriodicDates() {
        return new AdjustableRelativeOrPeriodicDates();
    }

    /**
     * Create an instance of {@link IndexReferenceInformation }
     * 
     */
    public IndexReferenceInformation createIndexReferenceInformation() {
        return new IndexReferenceInformation();
    }

    /**
     * Create an instance of {@link CommodityDeliveryPeriods }
     * 
     */
    public CommodityDeliveryPeriods createCommodityDeliveryPeriods() {
        return new CommodityDeliveryPeriods();
    }

    /**
     * Create an instance of {@link ExchangeRate }
     * 
     */
    public ExchangeRate createExchangeRate() {
        return new ExchangeRate();
    }

    /**
     * Create an instance of {@link MasterConfirmation }
     * 
     */
    public MasterConfirmation createMasterConfirmation() {
        return new MasterConfirmation();
    }

    /**
     * Create an instance of {@link TradeNovationContent }
     * 
     */
    public TradeNovationContent createTradeNovationContent() {
        return new TradeNovationContent();
    }

    /**
     * Create an instance of {@link Composite }
     * 
     */
    public Composite createComposite() {
        return new Composite();
    }

    /**
     * Create an instance of {@link Underlyer }
     * 
     */
    public Underlyer createUnderlyer() {
        return new Underlyer();
    }

    /**
     * Create an instance of {@link Trigger }
     * 
     */
    public Trigger createTrigger() {
        return new Trigger();
    }

    /**
     * Create an instance of {@link Allocation }
     * 
     */
    public Allocation createAllocation() {
        return new Allocation();
    }

    /**
     * Create an instance of {@link TriggerEvent }
     * 
     */
    public TriggerEvent createTriggerEvent() {
        return new TriggerEvent();
    }

    /**
     * Create an instance of {@link CommodityFixedPriceSchedule }
     * 
     */
    public CommodityFixedPriceSchedule createCommodityFixedPriceSchedule() {
        return new CommodityFixedPriceSchedule();
    }

    /**
     * Create an instance of {@link ClassifiedPayment }
     * 
     */
    public ClassifiedPayment createClassifiedPayment() {
        return new ClassifiedPayment();
    }

    /**
     * Create an instance of {@link RequestedWithdrawalAction }
     * 
     */
    public RequestedWithdrawalAction createRequestedWithdrawalAction() {
        return new RequestedWithdrawalAction();
    }

    /**
     * Create an instance of {@link AdjustableDate }
     * 
     */
    public AdjustableDate createAdjustableDate() {
        return new AdjustableDate();
    }

    /**
     * Create an instance of {@link MultipleValuationDates }
     * 
     */
    public MultipleValuationDates createMultipleValuationDates() {
        return new MultipleValuationDates();
    }

    /**
     * Create an instance of {@link CoalProduct }
     * 
     */
    public CoalProduct createCoalProduct() {
        return new CoalProduct();
    }

    /**
     * Create an instance of {@link PaymentReference }
     * 
     */
    public PaymentReference createPaymentReference() {
        return new PaymentReference();
    }

    /**
     * Create an instance of {@link BusinessEventIdentifier }
     * 
     */
    public BusinessEventIdentifier createBusinessEventIdentifier() {
        return new BusinessEventIdentifier();
    }

    /**
     * Create an instance of {@link CoalProductSource }
     * 
     */
    public CoalProductSource createCoalProductSource() {
        return new CoalProductSource();
    }

    /**
     * Create an instance of {@link PendingPayment }
     * 
     */
    public PendingPayment createPendingPayment() {
        return new PendingPayment();
    }

    /**
     * Create an instance of {@link RoutingExplicitDetails }
     * 
     */
    public RoutingExplicitDetails createRoutingExplicitDetails() {
        return new RoutingExplicitDetails();
    }

    /**
     * Create an instance of {@link SettledEntityMatrix }
     * 
     */
    public SettledEntityMatrix createSettledEntityMatrix() {
        return new SettledEntityMatrix();
    }

    /**
     * Create an instance of {@link PrePayment }
     * 
     */
    public PrePayment createPrePayment() {
        return new PrePayment();
    }

    /**
     * Create an instance of {@link BondOptionStrike }
     * 
     */
    public BondOptionStrike createBondOptionStrike() {
        return new BondOptionStrike();
    }

    /**
     * Create an instance of {@link SupervisorRegistration }
     * 
     */
    public SupervisorRegistration createSupervisorRegistration() {
        return new SupervisorRegistration();
    }

    /**
     * Create an instance of {@link BasicQuotation }
     * 
     */
    public BasicQuotation createBasicQuotation() {
        return new BasicQuotation();
    }

    /**
     * Create an instance of {@link Empty }
     * 
     */
    public Empty createEmpty() {
        return new Empty();
    }

    /**
     * Create an instance of {@link AdjustableDates }
     * 
     */
    public AdjustableDates createAdjustableDates() {
        return new AdjustableDates();
    }

    /**
     * Create an instance of {@link FxSpotRateSource }
     * 
     */
    public FxSpotRateSource createFxSpotRateSource() {
        return new FxSpotRateSource();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link EquityValuation }
     * 
     */
    public EquityValuation createEquityValuation() {
        return new EquityValuation();
    }

    /**
     * Create an instance of {@link DefaultProbabilityCurve }
     * 
     */
    public DefaultProbabilityCurve createDefaultProbabilityCurve() {
        return new DefaultProbabilityCurve();
    }

    /**
     * Create an instance of {@link AssetReference }
     * 
     */
    public AssetReference createAssetReference() {
        return new AssetReference();
    }

    /**
     * Create an instance of {@link FormulaComponent }
     * 
     */
    public FormulaComponent createFormulaComponent() {
        return new FormulaComponent();
    }

    /**
     * Create an instance of {@link Strike }
     * 
     */
    public Strike createStrike() {
        return new Strike();
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link SettlementPeriods }
     * 
     */
    public SettlementPeriods createSettlementPeriods() {
        return new SettlementPeriods();
    }

    /**
     * Create an instance of {@link GasDeliveryPeriods }
     * 
     */
    public GasDeliveryPeriods createGasDeliveryPeriods() {
        return new GasDeliveryPeriods();
    }

    /**
     * Create an instance of {@link RelatedParty }
     * 
     */
    public RelatedParty createRelatedParty() {
        return new RelatedParty();
    }

    /**
     * Create an instance of {@link SettlementPeriodsStep }
     * 
     */
    public SettlementPeriodsStep createSettlementPeriodsStep() {
        return new SettlementPeriodsStep();
    }

    /**
     * Create an instance of {@link CreditRating }
     * 
     */
    public CreditRating createCreditRating() {
        return new CreditRating();
    }

    /**
     * Create an instance of {@link CommodityBusinessCalendarTime }
     * 
     */
    public CommodityBusinessCalendarTime createCommodityBusinessCalendarTime() {
        return new CommodityBusinessCalendarTime();
    }

    /**
     * Create an instance of {@link InterpolationMethod }
     * 
     */
    public InterpolationMethod createInterpolationMethod() {
        return new InterpolationMethod();
    }

    /**
     * Create an instance of {@link AveragingPeriod }
     * 
     */
    public AveragingPeriod createAveragingPeriod() {
        return new AveragingPeriod();
    }

    /**
     * Create an instance of {@link MimeType }
     * 
     */
    public MimeType createMimeType() {
        return new MimeType();
    }

    /**
     * Create an instance of {@link InformationSource }
     * 
     */
    public InformationSource createInformationSource() {
        return new InformationSource();
    }

    /**
     * Create an instance of {@link SharedAmericanExercise }
     * 
     */
    public SharedAmericanExercise createSharedAmericanExercise() {
        return new SharedAmericanExercise();
    }

    /**
     * Create an instance of {@link TradeUnderlyer2 }
     * 
     */
    public TradeUnderlyer2 createTradeUnderlyer2() {
        return new TradeUnderlyer2();
    }

    /**
     * Create an instance of {@link CommodityDetails }
     * 
     */
    public CommodityDetails createCommodityDetails() {
        return new CommodityDetails();
    }

    /**
     * Create an instance of {@link AdjustableDate2 }
     * 
     */
    public AdjustableDate2 createAdjustableDate2() {
        return new AdjustableDate2();
    }

    /**
     * Create an instance of {@link EventStatusItem }
     * 
     */
    public EventStatusItem createEventStatusItem() {
        return new EventStatusItem();
    }

    /**
     * Create an instance of {@link UnitQuantity }
     * 
     */
    public UnitQuantity createUnitQuantity() {
        return new UnitQuantity();
    }

    /**
     * Create an instance of {@link AveragingSchedule }
     * 
     */
    public AveragingSchedule createAveragingSchedule() {
        return new AveragingSchedule();
    }

    /**
     * Create an instance of {@link ImplementationSpecification }
     * 
     */
    public ImplementationSpecification createImplementationSpecification() {
        return new ImplementationSpecification();
    }

    /**
     * Create an instance of {@link IndexAdjustmentEvents }
     * 
     */
    public IndexAdjustmentEvents createIndexAdjustmentEvents() {
        return new IndexAdjustmentEvents();
    }

    /**
     * Create an instance of {@link Cashflows }
     * 
     */
    public Cashflows createCashflows() {
        return new Cashflows();
    }

    /**
     * Create an instance of {@link CreditEventNotice }
     * 
     */
    public CreditEventNotice createCreditEventNotice() {
        return new CreditEventNotice();
    }

    /**
     * Create an instance of {@link FloatingLegCalculation }
     * 
     */
    public FloatingLegCalculation createFloatingLegCalculation() {
        return new FloatingLegCalculation();
    }

    /**
     * Create an instance of {@link CoalDeliveryPoint }
     * 
     */
    public CoalDeliveryPoint createCoalDeliveryPoint() {
        return new CoalDeliveryPoint();
    }

    /**
     * Create an instance of {@link ReferencePair }
     * 
     */
    public ReferencePair createReferencePair() {
        return new ReferencePair();
    }

    /**
     * Create an instance of {@link CreditDerivativesNotices }
     * 
     */
    public CreditDerivativesNotices createCreditDerivativesNotices() {
        return new CreditDerivativesNotices();
    }

    /**
     * Create an instance of {@link PortfolioConstituentReference }
     * 
     */
    public PortfolioConstituentReference createPortfolioConstituentReference() {
        return new PortfolioConstituentReference();
    }

    /**
     * Create an instance of {@link FirstPeriodStartDate }
     * 
     */
    public FirstPeriodStartDate createFirstPeriodStartDate() {
        return new FirstPeriodStartDate();
    }

    /**
     * Create an instance of {@link AdjustableRelativeOrPeriodicDates2 }
     * 
     */
    public AdjustableRelativeOrPeriodicDates2 createAdjustableRelativeOrPeriodicDates2() {
        return new AdjustableRelativeOrPeriodicDates2();
    }

    /**
     * Create an instance of {@link CompoundingFrequency }
     * 
     */
    public CompoundingFrequency createCompoundingFrequency() {
        return new CompoundingFrequency();
    }

    /**
     * Create an instance of {@link SplitSettlement }
     * 
     */
    public SplitSettlement createSplitSettlement() {
        return new SplitSettlement();
    }

    /**
     * Create an instance of {@link ElectricityDeliveryUnitFirm }
     * 
     */
    public ElectricityDeliveryUnitFirm createElectricityDeliveryUnitFirm() {
        return new ElectricityDeliveryUnitFirm();
    }

    /**
     * Create an instance of {@link DividendConditions }
     * 
     */
    public DividendConditions createDividendConditions() {
        return new DividendConditions();
    }

    /**
     * Create an instance of {@link ScheduleReference }
     * 
     */
    public ScheduleReference createScheduleReference() {
        return new ScheduleReference();
    }

    /**
     * Create an instance of {@link EquityEuropeanExercise }
     * 
     */
    public EquityEuropeanExercise createEquityEuropeanExercise() {
        return new EquityEuropeanExercise();
    }

    /**
     * Create an instance of {@link FxFixing }
     * 
     */
    public FxFixing createFxFixing() {
        return new FxFixing();
    }

    /**
     * Create an instance of {@link PricingModel }
     * 
     */
    public PricingModel createPricingModel() {
        return new PricingModel();
    }

    /**
     * Create an instance of {@link FixedRate }
     * 
     */
    public FixedRate createFixedRate() {
        return new FixedRate();
    }

    /**
     * Create an instance of {@link TimezoneLocation }
     * 
     */
    public TimezoneLocation createTimezoneLocation() {
        return new TimezoneLocation();
    }

    /**
     * Create an instance of {@link ContactInformation }
     * 
     */
    public ContactInformation createContactInformation() {
        return new ContactInformation();
    }

    /**
     * Create an instance of {@link BondReference }
     * 
     */
    public BondReference createBondReference() {
        return new BondReference();
    }

    /**
     * Create an instance of {@link CorrelationId }
     * 
     */
    public CorrelationId createCorrelationId() {
        return new CorrelationId();
    }

    /**
     * Create an instance of {@link ProductType }
     * 
     */
    public ProductType createProductType() {
        return new ProductType();
    }

    /**
     * Create an instance of {@link CreditOptionStrike }
     * 
     */
    public CreditOptionStrike createCreditOptionStrike() {
        return new CreditOptionStrike();
    }

    /**
     * Create an instance of {@link MasterConfirmationType }
     * 
     */
    public MasterConfirmationType createMasterConfirmationType() {
        return new MasterConfirmationType();
    }

    /**
     * Create an instance of {@link RequestMessageHeader }
     * 
     */
    public RequestMessageHeader createRequestMessageHeader() {
        return new RequestMessageHeader();
    }

    /**
     * Create an instance of {@link BusinessDayAdjustments }
     * 
     */
    public BusinessDayAdjustments createBusinessDayAdjustments() {
        return new BusinessDayAdjustments();
    }

    /**
     * Create an instance of {@link SpecifiedCurrency }
     * 
     */
    public SpecifiedCurrency createSpecifiedCurrency() {
        return new SpecifiedCurrency();
    }

    /**
     * Create an instance of {@link ProblemLocation }
     * 
     */
    public ProblemLocation createProblemLocation() {
        return new ProblemLocation();
    }

    /**
     * Create an instance of {@link ResetDates }
     * 
     */
    public ResetDates createResetDates() {
        return new ResetDates();
    }

    /**
     * Create an instance of {@link ServiceAdvisory }
     * 
     */
    public ServiceAdvisory createServiceAdvisory() {
        return new ServiceAdvisory();
    }

    /**
     * Create an instance of {@link Stub }
     * 
     */
    public Stub createStub() {
        return new Stub();
    }

    /**
     * Create an instance of {@link CoalStandardQuality }
     * 
     */
    public CoalStandardQuality createCoalStandardQuality() {
        return new CoalStandardQuality();
    }

    /**
     * Create an instance of {@link BusinessDayAdjustmentsReference }
     * 
     */
    public BusinessDayAdjustmentsReference createBusinessDayAdjustmentsReference() {
        return new BusinessDayAdjustmentsReference();
    }

    /**
     * Create an instance of {@link MarketDisruptionEvent }
     * 
     */
    public MarketDisruptionEvent createMarketDisruptionEvent() {
        return new MarketDisruptionEvent();
    }

    /**
     * Create an instance of {@link ServiceAdvisoryCategory }
     * 
     */
    public ServiceAdvisoryCategory createServiceAdvisoryCategory() {
        return new ServiceAdvisoryCategory();
    }

    /**
     * Create an instance of {@link BusinessUnitRole }
     * 
     */
    public BusinessUnitRole createBusinessUnitRole() {
        return new BusinessUnitRole();
    }

    /**
     * Create an instance of {@link AdditionalFixedPayments }
     * 
     */
    public AdditionalFixedPayments createAdditionalFixedPayments() {
        return new AdditionalFixedPayments();
    }

    /**
     * Create an instance of {@link AdjustableDatesOrRelativeDateOffset }
     * 
     */
    public AdjustableDatesOrRelativeDateOffset createAdjustableDatesOrRelativeDateOffset() {
        return new AdjustableDatesOrRelativeDateOffset();
    }

    /**
     * Create an instance of {@link CommodityHub }
     * 
     */
    public CommodityHub createCommodityHub() {
        return new CommodityHub();
    }

    /**
     * Create an instance of {@link ExchangeId }
     * 
     */
    public ExchangeId createExchangeId() {
        return new ExchangeId();
    }

    /**
     * Create an instance of {@link ExternalDocument }
     * 
     */
    public ExternalDocument createExternalDocument() {
        return new ExternalDocument();
    }

    /**
     * Create an instance of {@link DateRelativeToCalculationPeriodDates }
     * 
     */
    public DateRelativeToCalculationPeriodDates createDateRelativeToCalculationPeriodDates() {
        return new DateRelativeToCalculationPeriodDates();
    }

    /**
     * Create an instance of {@link GasDelivery }
     * 
     */
    public GasDelivery createGasDelivery() {
        return new GasDelivery();
    }

    /**
     * Create an instance of {@link RelatedBusinessUnit }
     * 
     */
    public RelatedBusinessUnit createRelatedBusinessUnit() {
        return new RelatedBusinessUnit();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link DateReference }
     * 
     */
    public DateReference createDateReference() {
        return new DateReference();
    }

    /**
     * Create an instance of {@link FxRate }
     * 
     */
    public FxRate createFxRate() {
        return new FxRate();
    }

    /**
     * Create an instance of {@link ElectricityDeliveryFirm }
     * 
     */
    public ElectricityDeliveryFirm createElectricityDeliveryFirm() {
        return new ElectricityDeliveryFirm();
    }

    /**
     * Create an instance of {@link TradeAmendmentContent }
     * 
     */
    public TradeAmendmentContent createTradeAmendmentContent() {
        return new TradeAmendmentContent();
    }

    /**
     * Create an instance of {@link ScheduledDateType }
     * 
     */
    public ScheduledDateType createScheduledDateType() {
        return new ScheduledDateType();
    }

    /**
     * Create an instance of {@link IdentifiedCurrencyReference }
     * 
     */
    public IdentifiedCurrencyReference createIdentifiedCurrencyReference() {
        return new IdentifiedCurrencyReference();
    }

    /**
     * Create an instance of {@link ManualExercise }
     * 
     */
    public ManualExercise createManualExercise() {
        return new ManualExercise();
    }

    /**
     * Create an instance of {@link ReturnSwapAdditionalPayment }
     * 
     */
    public ReturnSwapAdditionalPayment createReturnSwapAdditionalPayment() {
        return new ReturnSwapAdditionalPayment();
    }

    /**
     * Create an instance of {@link GoverningLaw }
     * 
     */
    public GoverningLaw createGoverningLaw() {
        return new GoverningLaw();
    }

    /**
     * Create an instance of {@link CommodityProductGrade }
     * 
     */
    public CommodityProductGrade createCommodityProductGrade() {
        return new CommodityProductGrade();
    }

    /**
     * Create an instance of {@link ExchangeTradedContract }
     * 
     */
    public ExchangeTradedContract createExchangeTradedContract() {
        return new ExchangeTradedContract();
    }

    /**
     * Create an instance of {@link ScheduledDates }
     * 
     */
    public ScheduledDates createScheduledDates() {
        return new ScheduledDates();
    }

    /**
     * Create an instance of {@link CalculationPeriodsDatesReference }
     * 
     */
    public CalculationPeriodsDatesReference createCalculationPeriodsDatesReference() {
        return new CalculationPeriodsDatesReference();
    }

    /**
     * Create an instance of {@link AdditionalData.OriginalMessage }
     * 
     */
    public AdditionalData.OriginalMessage createAdditionalDataOriginalMessage() {
        return new AdditionalData.OriginalMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxCurveValuation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxCurveValuation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructureValuation")
    public JAXBElement<FxCurveValuation> createFxCurveValuation(FxCurveValuation value) {
        return new JAXBElement<FxCurveValuation>(_FxCurveValuation_QNAME, FxCurveValuation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoalPhysicalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "coalPhysicalLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<CoalPhysicalLeg> createCoalPhysicalLeg(CoalPhysicalLeg value) {
        return new JAXBElement<CoalPhysicalLeg>(_CoalPhysicalLeg_QNAME, CoalPhysicalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fra }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fra", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<Fra> createFra(Fra value) {
        return new JAXBElement<Fra>(_Fra_QNAME, Fra.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertibleBond }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "convertibleBond", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<ConvertibleBond> createConvertibleBond(ConvertibleBond value) {
        return new JAXBElement<ConvertibleBond>(_ConvertibleBond_QNAME, ConvertibleBond.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VarianceOptionTransactionSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "varianceOptionTransactionSupplement", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<VarianceOptionTransactionSupplement> createVarianceOptionTransactionSupplement(VarianceOptionTransactionSupplement value) {
        return new JAXBElement<VarianceOptionTransactionSupplement>(_VarianceOptionTransactionSupplement_QNAME, VarianceOptionTransactionSupplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exercise }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "exercise")
    public JAXBElement<Exercise> createExercise(Exercise value) {
        return new JAXBElement<Exercise>(_Exercise_QNAME, Exercise.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "messageRejected")
    public JAXBElement<Exception> createMessageRejected(Exception value) {
        return new JAXBElement<Exception>(_MessageRejected_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerEquityOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "brokerEquityOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<BrokerEquityOption> createBrokerEquityOption(BrokerEquityOption value) {
        return new JAXBElement<BrokerEquityOption>(_BrokerEquityOption_QNAME, BrokerEquityOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EquityOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "equityOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<EquityOption> createEquityOption(EquityOption value) {
        return new JAXBElement<EquityOption>(_EquityOption_QNAME, EquityOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bond }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bond", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Bond> createBond(Bond value) {
        return new JAXBElement<Bond>(_Bond_QNAME, Bond.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommodityForward }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commodityForward", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CommodityForward> createCommodityForward(CommodityForward value) {
        return new JAXBElement<CommodityForward>(_CommodityForward_QNAME, CommodityForward.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "returnLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "returnSwapLeg")
    public JAXBElement<ReturnLeg> createReturnLeg(ReturnLeg value) {
        return new JAXBElement<ReturnLeg>(_ReturnLeg_QNAME, ReturnLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RateIndex }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "rateIndex", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<RateIndex> createRateIndex(RateIndex value) {
        return new JAXBElement<RateIndex>(_RateIndex_QNAME, RateIndex.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "returnSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<ReturnSwap> createReturnSwap(ReturnSwap value) {
        return new JAXBElement<ReturnSwap>(_ReturnSwap_QNAME, ReturnSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Acknowledgement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "verificationStatusAcknowledgement")
    public JAXBElement<Acknowledgement> createVerificationStatusAcknowledgement(Acknowledgement value) {
        return new JAXBElement<Acknowledgement>(_VerificationStatusAcknowledgement_QNAME, Acknowledgement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Acknowledgement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationReportAcknowledgement")
    public JAXBElement<Acknowledgement> createValuationReportAcknowledgement(Acknowledgement value) {
        return new JAXBElement<Acknowledgement>(_ValuationReportAcknowledgement_QNAME, Acknowledgement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FloatingPriceLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "floatingLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<FloatingPriceLeg> createFloatingLeg(FloatingPriceLeg value) {
        return new JAXBElement<FloatingPriceLeg>(_FloatingLeg_QNAME, FloatingPriceLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "genericProduct", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<GenericProduct> createGenericProduct(GenericProduct value) {
        return new JAXBElement<GenericProduct>(_GenericProduct_QNAME, GenericProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EquitySwapTransactionSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "equitySwapTransactionSupplement", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<EquitySwapTransactionSupplement> createEquitySwapTransactionSupplement(EquitySwapTransactionSupplement value) {
        return new JAXBElement<EquitySwapTransactionSupplement>(_EquitySwapTransactionSupplement_QNAME, EquitySwapTransactionSupplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxDigitalOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxDigitalOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<FxDigitalOption> createFxDigitalOption(FxDigitalOption value) {
        return new JAXBElement<FxDigitalOption>(_FxDigitalOption_QNAME, FxDigitalOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuationSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationSet")
    public JAXBElement<ValuationSet> createValuationSet(ValuationSet value) {
        return new JAXBElement<ValuationSet>(_ValuationSet_QNAME, ValuationSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MutualFund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "mutualFund", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<MutualFund> createMutualFund(MutualFund value) {
        return new JAXBElement<MutualFund>(_MutualFund_QNAME, MutualFund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommodityOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commodityOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CommodityOption> createCommodityOption(CommodityOption value) {
        return new JAXBElement<CommodityOption>(_CommodityOption_QNAME, CommodityOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuationReportRetracted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationReportRetracted")
    public JAXBElement<ValuationReportRetracted> createValuationReportRetracted(ValuationReportRetracted value) {
        return new JAXBElement<ValuationReportRetracted>(_ValuationReportRetracted_QNAME, ValuationReportRetracted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Strategy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "strategy", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<Strategy> createStrategy(Strategy value) {
        return new JAXBElement<Strategy>(_Strategy_QNAME, Strategy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "dataDocument")
    public JAXBElement<DataDocument> createDataDocument(DataDocument value) {
        return new JAXBElement<DataDocument>(_DataDocument_QNAME, DataDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FailureToPayEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "failureToPay", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<FailureToPayEvent> createFailureToPay(FailureToPayEvent value) {
        return new JAXBElement<FailureToPayEvent>(_FailureToPay_QNAME, FailureToPayEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RepudiationMoratoriumEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "repudiationMoratorium", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<RepudiationMoratoriumEvent> createRepudiationMoratorium(RepudiationMoratoriumEvent value) {
        return new JAXBElement<RepudiationMoratoriumEvent>(_RepudiationMoratorium_QNAME, RepudiationMoratoriumEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Commodity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commodity", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Commodity> createCommodity(Commodity value) {
        return new JAXBElement<Commodity>(_Commodity_QNAME, Commodity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Swap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "swap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<Swap> createSwap(Swap value) {
        return new JAXBElement<Swap>(_Swap_QNAME, Swap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxCurve }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxCurve", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructure")
    public JAXBElement<FxCurve> createFxCurve(FxCurve value) {
        return new JAXBElement<FxCurve>(_FxCurve_QNAME, FxCurve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectionalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "returnSwapLeg")
    public JAXBElement<DirectionalLeg> createReturnSwapLeg(DirectionalLeg value) {
        return new JAXBElement<DirectionalLeg>(_ReturnSwapLeg_QNAME, DirectionalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonSchemaProduct", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<GenericProduct> createNonSchemaProduct(GenericProduct value) {
        return new JAXBElement<GenericProduct>(_NonSchemaProduct_QNAME, GenericProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EquityAsset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "equity", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<EquityAsset> createEquity(EquityAsset value) {
        return new JAXBElement<EquityAsset>(_Equity_QNAME, EquityAsset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Asset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "underlyingAsset")
    public JAXBElement<Asset> createUnderlyingAsset(Asset value) {
        return new JAXBElement<Asset>(_UnderlyingAsset_QNAME, Asset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommoditySwaption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commoditySwaption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CommoditySwaption> createCommoditySwaption(CommoditySwaption value) {
        return new JAXBElement<CommoditySwaption>(_CommoditySwaption_QNAME, CommoditySwaption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EquityOptionTransactionSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "equityOptionTransactionSupplement", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<EquityOptionTransactionSupplement> createEquityOptionTransactionSupplement(EquityOptionTransactionSupplement value) {
        return new JAXBElement<EquityOptionTransactionSupplement>(_EquityOptionTransactionSupplement_QNAME, EquityOptionTransactionSupplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmericanExercise }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "americanExercise", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "exercise")
    public JAXBElement<AmericanExercise> createAmericanExercise(AmericanExercise value) {
        return new JAXBElement<AmericanExercise>(_AmericanExercise_QNAME, AmericanExercise.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "changeEvent")
    public JAXBElement<ChangeEvent> createChangeEvent(ChangeEvent value) {
        return new JAXBElement<ChangeEvent>(_ChangeEvent_QNAME, ChangeEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Future }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "future", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Future> createFuture(Future value) {
        return new JAXBElement<Future>(_Future_QNAME, Future.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonpublicExecutionReportException")
    public JAXBElement<Exception> createNonpublicExecutionReportException(Exception value) {
        return new JAXBElement<Exception>(_NonpublicExecutionReportException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "verificationStatusException")
    public JAXBElement<Exception> createVerificationStatusException(Exception value) {
        return new JAXBElement<Exception>(_VerificationStatusException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VarianceSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "varianceSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<VarianceSwap> createVarianceSwap(VarianceSwap value) {
        return new JAXBElement<VarianceSwap>(_VarianceSwap_QNAME, VarianceSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationDefaultEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "obligationDefault", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<ObligationDefaultEvent> createObligationDefault(ObligationDefaultEvent value) {
        return new JAXBElement<ObligationDefaultEvent>(_ObligationDefault_QNAME, ObligationDefaultEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Index }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "index", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Index> createIndex(Index value) {
        return new JAXBElement<Index>(_Index_QNAME, Index.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxSingleLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxSingleLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<FxSingleLeg> createFxSingleLeg(FxSingleLeg value) {
        return new JAXBElement<FxSingleLeg>(_FxSingleLeg_QNAME, FxSingleLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommoditySwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commoditySwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CommoditySwap> createCommoditySwap(CommoditySwap value) {
        return new JAXBElement<CommoditySwap>(_CommoditySwap_QNAME, CommoditySwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterestLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "interestLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "returnSwapLeg")
    public JAXBElement<InterestLeg> createInterestLeg(InterestLeg value) {
        return new JAXBElement<InterestLeg>(_InterestLeg_QNAME, InterestLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BondOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bondOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<BondOption> createBondOption(BondOption value) {
        return new JAXBElement<BondOption>(_BondOption_QNAME, BondOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCurve }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditCurve", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructure")
    public JAXBElement<CreditCurve> createCreditCurve(CreditCurve value) {
        return new JAXBElement<CreditCurve>(_CreditCurve_QNAME, CreditCurve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Asset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "curveInstrument")
    public JAXBElement<Asset> createCurveInstrument(Asset value) {
        return new JAXBElement<Asset>(_CurveInstrument_QNAME, Asset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YieldCurveValuation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "yieldCurveValuation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructureValuation")
    public JAXBElement<YieldCurveValuation> createYieldCurveValuation(YieldCurveValuation value) {
        return new JAXBElement<YieldCurveValuation>(_YieldCurveValuation_QNAME, YieldCurveValuation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Market }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "market")
    public JAXBElement<Market> createMarket(Market value) {
        return new JAXBElement<Market>(_Market_QNAME, Market.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "rateCalculation")
    public JAXBElement<Rate> createRateCalculation(Rate value) {
        return new JAXBElement<Rate>(_RateCalculation_QNAME, Rate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditEventNoticeDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditEventNotice")
    public JAXBElement<CreditEventNoticeDocument> createCreditEventNotice(CreditEventNoticeDocument value) {
        return new JAXBElement<CreditEventNoticeDocument>(_CreditEventNotice_QNAME, CreditEventNoticeDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VarianceSwapTransactionSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "varianceSwapTransactionSupplement", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<VarianceSwapTransactionSupplement> createVarianceSwapTransactionSupplement(VarianceSwapTransactionSupplement value) {
        return new JAXBElement<VarianceSwapTransactionSupplement>(_VarianceSwapTransactionSupplement_QNAME, VarianceSwapTransactionSupplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cash }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "cash", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Cash> createCash(Cash value) {
        return new JAXBElement<Cash>(_Cash_QNAME, Cash.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GasPhysicalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "gasPhysicalLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<GasPhysicalLeg> createGasPhysicalLeg(GasPhysicalLeg value) {
        return new JAXBElement<GasPhysicalLeg>(_GasPhysicalLeg_QNAME, GasPhysicalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstrumentTradeDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "instrumentTradeDetails", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<InstrumentTradeDetails> createInstrumentTradeDetails(InstrumentTradeDetails value) {
        return new JAXBElement<InstrumentTradeDetails>(_InstrumentTradeDetails_QNAME, InstrumentTradeDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeTradedFund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "exchangeTradedFund", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<ExchangeTradedFund> createExchangeTradedFund(ExchangeTradedFund value) {
        return new JAXBElement<ExchangeTradedFund>(_ExchangeTradedFund_QNAME, ExchangeTradedFund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditDefaultSwapOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditDefaultSwapOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CreditDefaultSwapOption> createCreditDefaultSwapOption(CreditDefaultSwapOption value) {
        return new JAXBElement<CreditDefaultSwapOption>(_CreditDefaultSwapOption_QNAME, CreditDefaultSwapOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditEventNotification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditEventNotification")
    public JAXBElement<CreditEventNotification> createCreditEventNotification(CreditEventNotification value) {
        return new JAXBElement<CreditEventNotification>(_CreditEventNotification_QNAME, CreditEventNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FixedPriceLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fixedLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<FixedPriceLeg> createFixedLeg(FixedPriceLeg value) {
        return new JAXBElement<FixedPriceLeg>(_FixedLeg_QNAME, FixedPriceLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleCreditDefaultSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "simpleCreditDefaultSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<SimpleCreditDefaultSwap> createSimpleCreditDefaultSwap(SimpleCreditDefaultSwap value) {
        return new JAXBElement<SimpleCreditDefaultSwap>(_SimpleCreditDefaultSwap_QNAME, SimpleCreditDefaultSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCurveValuation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditCurveValuation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructureValuation")
    public JAXBElement<CreditCurveValuation> createCreditCurveValuation(CreditCurveValuation value) {
        return new JAXBElement<CreditCurveValuation>(_CreditCurveValuation_QNAME, CreditCurveValuation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificationStatusNotification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "verificationStatusNotification")
    public JAXBElement<VerificationStatusNotification> createVerificationStatusNotification(VerificationStatusNotification value) {
        return new JAXBElement<VerificationStatusNotification>(_VerificationStatusNotification_QNAME, VerificationStatusNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "additionalEvent")
    public JAXBElement<AdditionalEvent> createAdditionalEvent(AdditionalEvent value) {
        return new JAXBElement<AdditionalEvent>(_AdditionalEvent_QNAME, AdditionalEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceNotification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "serviceNotification")
    public JAXBElement<ServiceNotification> createServiceNotification(ServiceNotification value) {
        return new JAXBElement<ServiceNotification>(_ServiceNotification_QNAME, ServiceNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VolatilityRepresentation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "volatilityRepresentation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructure")
    public JAXBElement<VolatilityRepresentation> createVolatilityRepresentation(VolatilityRepresentation value) {
        return new JAXBElement<VolatilityRepresentation>(_VolatilityRepresentation_QNAME, VolatilityRepresentation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "eventStatusException")
    public JAXBElement<Exception> createEventStatusException(Exception value) {
        return new JAXBElement<Exception>(_EventStatusException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestRetransmission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "requestRetransmission")
    public JAXBElement<RequestRetransmission> createRequestRetransmission(RequestRetransmission value) {
        return new JAXBElement<RequestRetransmission>(_RequestRetransmission_QNAME, RequestRetransmission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestructuringEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "restructuring", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<RestructuringEvent> createRestructuring(RestructuringEvent value) {
        return new JAXBElement<RestructuringEvent>(_Restructuring_QNAME, RestructuringEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestValuationReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "requestValuationReport")
    public JAXBElement<RequestValuationReport> createRequestValuationReport(RequestValuationReport value) {
        return new JAXBElement<RequestValuationReport>(_RequestValuationReport_QNAME, RequestValuationReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DividendSwapTransactionSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "dividendSwapTransactionSupplement", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<DividendSwapTransactionSupplement> createDividendSwapTransactionSupplement(DividendSwapTransactionSupplement value) {
        return new JAXBElement<DividendSwapTransactionSupplement>(_DividendSwapTransactionSupplement_QNAME, DividendSwapTransactionSupplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditEventException")
    public JAXBElement<Exception> createCreditEventException(Exception value) {
        return new JAXBElement<Exception>(_CreditEventException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BulletPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bulletPayment", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<BulletPayment> createBulletPayment(BulletPayment value) {
        return new JAXBElement<BulletPayment>(_BulletPayment_QNAME, BulletPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationAccelerationEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "obligationAcceleration", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<ObligationAccelerationEvent> createObligationAcceleration(ObligationAccelerationEvent value) {
        return new JAXBElement<ObligationAccelerationEvent>(_ObligationAcceleration_QNAME, ObligationAccelerationEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NonpublicExecutionReportRetracted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonpublicExecutionReportRetracted")
    public JAXBElement<NonpublicExecutionReportRetracted> createNonpublicExecutionReportRetracted(NonpublicExecutionReportRetracted value) {
        return new JAXBElement<NonpublicExecutionReportRetracted>(_NonpublicExecutionReportRetracted_QNAME, NonpublicExecutionReportRetracted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YieldCurve }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "yieldCurve", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructure")
    public JAXBElement<YieldCurve> createYieldCurve(YieldCurve value) {
        return new JAXBElement<YieldCurve>(_YieldCurve_QNAME, YieldCurve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Product }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "product")
    public JAXBElement<Product> createProduct(Product value) {
        return new JAXBElement<Product>(_Product_QNAME, Product.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxOption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxOption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<FxOption> createFxOption(FxOption value) {
        return new JAXBElement<FxOption>(_FxOption_QNAME, FxOption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleFra }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "simpleFra", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<SimpleFra> createSimpleFra(SimpleFra value) {
        return new JAXBElement<SimpleFra>(_SimpleFra_QNAME, SimpleFra.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingStructure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "pricingStructure")
    public JAXBElement<PricingStructure> createPricingStructure(PricingStructure value) {
        return new JAXBElement<PricingStructure>(_PricingStructure_QNAME, PricingStructure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestEventStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "requestEventStatus")
    public JAXBElement<RequestEventStatus> createRequestEventStatus(RequestEventStatus value) {
        return new JAXBElement<RequestEventStatus>(_RequestEventStatus_QNAME, RequestEventStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Swaption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "swaption", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<Swaption> createSwaption(Swaption value) {
        return new JAXBElement<Swaption>(_Swaption_QNAME, Swaption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommodityForwardLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commodityForwardLeg")
    public JAXBElement<CommodityForwardLeg> createCommodityForwardLeg(CommodityForwardLeg value) {
        return new JAXBElement<CommodityForwardLeg>(_CommodityForwardLeg_QNAME, CommodityForwardLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Basket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "basket", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Basket> createBasket(Basket value) {
        return new JAXBElement<Basket>(_Basket_QNAME, Basket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuationDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationDocument")
    public JAXBElement<ValuationDocument> createValuationDocument(ValuationDocument value) {
        return new JAXBElement<ValuationDocument>(_ValuationDocument_QNAME, ValuationDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "eventStatusResponse")
    public JAXBElement<EventStatusResponse> createEventStatusResponse(EventStatusResponse value) {
        return new JAXBElement<EventStatusResponse>(_EventStatusResponse_QNAME, EventStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxRateAsset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fx", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<FxRateAsset> createFx(FxRateAsset value) {
        return new JAXBElement<FxRateAsset>(_Fx_QNAME, FxRateAsset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<FxSwap> createFxSwap(FxSwap value) {
        return new JAXBElement<FxSwap>(_FxSwap_QNAME, FxSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPortfolio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "queryPortfolio", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "portfolio")
    public JAXBElement<QueryPortfolio> createQueryPortfolio(QueryPortfolio value) {
        return new JAXBElement<QueryPortfolio>(_QueryPortfolio_QNAME, QueryPortfolio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BullionPhysicalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bullionPhysicalLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commodityForwardLeg")
    public JAXBElement<BullionPhysicalLeg> createBullionPhysicalLeg(BullionPhysicalLeg value) {
        return new JAXBElement<BullionPhysicalLeg>(_BullionPhysicalLeg_QNAME, BullionPhysicalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommoditySwapLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commoditySwapLeg")
    public JAXBElement<CommoditySwapLeg> createCommoditySwapLeg(CommoditySwapLeg value) {
        return new JAXBElement<CommoditySwapLeg>(_CommoditySwapLeg_QNAME, CommoditySwapLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deposit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "deposit", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<Deposit> createDeposit(Deposit value) {
        return new JAXBElement<Deposit>(_Deposit_QNAME, Deposit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EquityForward }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "equityForward", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<EquityForward> createEquityForward(EquityForward value) {
        return new JAXBElement<EquityForward>(_EquityForward_QNAME, EquityForward.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NonpublicExecutionReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonpublicExecutionReport")
    public JAXBElement<NonpublicExecutionReport> createNonpublicExecutionReport(NonpublicExecutionReport value) {
        return new JAXBElement<NonpublicExecutionReport>(_NonpublicExecutionReport_QNAME, NonpublicExecutionReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuationReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationReport")
    public JAXBElement<ValuationReport> createValuationReport(ValuationReport value) {
        return new JAXBElement<ValuationReport>(_ValuationReport_QNAME, ValuationReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InflationRateCalculation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "inflationRateCalculation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "rateCalculation")
    public JAXBElement<InflationRateCalculation> createInflationRateCalculation(InflationRateCalculation value) {
        return new JAXBElement<InflationRateCalculation>(_InflationRateCalculation_QNAME, InflationRateCalculation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BermudaExercise }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bermudaExercise", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "exercise")
    public JAXBElement<BermudaExercise> createBermudaExercise(BermudaExercise value) {
        return new JAXBElement<BermudaExercise>(_BermudaExercise_QNAME, BermudaExercise.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectricityPhysicalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "electricityPhysicalLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<ElectricityPhysicalLeg> createElectricityPhysicalLeg(ElectricityPhysicalLeg value) {
        return new JAXBElement<ElectricityPhysicalLeg>(_ElectricityPhysicalLeg_QNAME, ElectricityPhysicalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingStructureValuation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "pricingStructureValuation")
    public JAXBElement<PricingStructureValuation> createPricingStructureValuation(PricingStructureValuation value) {
        return new JAXBElement<PricingStructureValuation>(_PricingStructureValuation_QNAME, PricingStructureValuation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Acknowledgement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonpublicExecutionReportAcknowledgement")
    public JAXBElement<Acknowledgement> createNonpublicExecutionReportAcknowledgement(Acknowledgement value) {
        return new JAXBElement<Acknowledgement>(_NonpublicExecutionReportAcknowledgement_QNAME, Acknowledgement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CapFloor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "capFloor", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CapFloor> createCapFloor(CapFloor value) {
        return new JAXBElement<CapFloor>(_CapFloor_QNAME, CapFloor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TermDeposit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "termDeposit", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<TermDeposit> createTermDeposit(TermDeposit value) {
        return new JAXBElement<TermDeposit>(_TermDeposit_QNAME, TermDeposit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Portfolio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "portfolio")
    public JAXBElement<Portfolio> createPortfolio(Portfolio value) {
        return new JAXBElement<Portfolio>(_Portfolio_QNAME, Portfolio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Acknowledgement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditEventAcknowledgement")
    public JAXBElement<Acknowledgement> createCreditEventAcknowledgement(Acknowledgement value) {
        return new JAXBElement<Acknowledgement>(_CreditEventAcknowledgement_QNAME, Acknowledgement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorrelationSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "correlationSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CorrelationSwap> createCorrelationSwap(CorrelationSwap value) {
        return new JAXBElement<CorrelationSwap>(_CorrelationSwap_QNAME, CorrelationSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mortgage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "mortgage", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Mortgage> createMortgage(Mortgage value) {
        return new JAXBElement<Mortgage>(_Mortgage_QNAME, Mortgage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EuropeanExercise }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "europeanExercise", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "exercise")
    public JAXBElement<EuropeanExercise> createEuropeanExercise(EuropeanExercise value) {
        return new JAXBElement<EuropeanExercise>(_EuropeanExercise_QNAME, EuropeanExercise.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexChange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexChange", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "changeEvent")
    public JAXBElement<IndexChange> createIndexChange(IndexChange value) {
        return new JAXBElement<IndexChange>(_IndexChange_QNAME, IndexChange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditEvent")
    public JAXBElement<CreditEvent> createCreditEvent(CreditEvent value) {
        return new JAXBElement<CreditEvent>(_CreditEvent_QNAME, CreditEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "serviceNotificationException")
    public JAXBElement<Exception> createServiceNotificationException(Exception value) {
        return new JAXBElement<Exception>(_ServiceNotificationException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FloatingRateCalculation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "floatingRateCalculation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "rateCalculation")
    public JAXBElement<FloatingRateCalculation> createFloatingRateCalculation(FloatingRateCalculation value) {
        return new JAXBElement<FloatingRateCalculation>(_FloatingRateCalculation_QNAME, FloatingRateCalculation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Loan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "loan", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "underlyingAsset")
    public JAXBElement<Loan> createLoan(Loan value) {
        return new JAXBElement<Loan>(_Loan_QNAME, Loan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VolatilityMatrix }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "volatilityMatrixValuation", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "pricingStructureValuation")
    public JAXBElement<VolatilityMatrix> createVolatilityMatrixValuation(VolatilityMatrix value) {
        return new JAXBElement<VolatilityMatrix>(_VolatilityMatrixValuation_QNAME, VolatilityMatrix.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditDefaultSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditDefaultSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<CreditDefaultSwap> createCreditDefaultSwap(CreditDefaultSwap value) {
        return new JAXBElement<CreditDefaultSwap>(_CreditDefaultSwap_QNAME, CreditDefaultSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleIRSwap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "simpleIrSwap", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "curveInstrument")
    public JAXBElement<SimpleIRSwap> createSimpleIrSwap(SimpleIRSwap value) {
        return new JAXBElement<SimpleIRSwap>(_SimpleIrSwap_QNAME, SimpleIRSwap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StandardProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "standardProduct", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "product")
    public JAXBElement<StandardProduct> createStandardProduct(StandardProduct value) {
        return new JAXBElement<StandardProduct>(_StandardProduct_QNAME, StandardProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "valuationReportException")
    public JAXBElement<Exception> createValuationReportException(Exception value) {
        return new JAXBElement<Exception>(_ValuationReportException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankruptcyEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "bankruptcy", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "creditEvent")
    public JAXBElement<BankruptcyEvent> createBankruptcy(BankruptcyEvent value) {
        return new JAXBElement<BankruptcyEvent>(_Bankruptcy_QNAME, BankruptcyEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OilPhysicalLeg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "oilPhysicalLeg", substitutionHeadNamespace = "http://www.fpml.org/FpML-5/recordkeeping", substitutionHeadName = "commoditySwapLeg")
    public JAXBElement<OilPhysicalLeg> createOilPhysicalLeg(OilPhysicalLeg value) {
        return new JAXBElement<OilPhysicalLeg>(_OilPhysicalLeg_QNAME, OilPhysicalLeg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tranche }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "tranche", scope = IndexReferenceInformation.class)
    public JAXBElement<Tranche> createIndexReferenceInformationTranche(Tranche value) {
        return new JAXBElement<Tranche>(_IndexReferenceInformationTranche_QNAME, Tranche.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LegalEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "excludedReferenceEntity", scope = IndexReferenceInformation.class)
    public JAXBElement<LegalEntity> createIndexReferenceInformationExcludedReferenceEntity(LegalEntity value) {
        return new JAXBElement<LegalEntity>(_IndexReferenceInformationExcludedReferenceEntity_QNAME, LegalEntity.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SettledEntityMatrix }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "settledEntityMatrix", scope = IndexReferenceInformation.class)
    public JAXBElement<SettledEntityMatrix> createIndexReferenceInformationSettledEntityMatrix(SettledEntityMatrix value) {
        return new JAXBElement<SettledEntityMatrix>(_IndexReferenceInformationSettledEntityMatrix_QNAME, SettledEntityMatrix.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexId", scope = IndexReferenceInformation.class)
    public JAXBElement<IndexId> createIndexReferenceInformationIndexId(IndexId value) {
        return new JAXBElement<IndexId>(_IndexReferenceInformationIndexId_QNAME, IndexId.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexName", scope = IndexReferenceInformation.class)
    public JAXBElement<IndexName> createIndexReferenceInformationIndexName(IndexName value) {
        return new JAXBElement<IndexName>(_IndexReferenceInformationIndexName_QNAME, IndexName.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexAnnexDate", scope = IndexReferenceInformation.class)
    public JAXBElement<XMLGregorianCalendar> createIndexReferenceInformationIndexAnnexDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_IndexReferenceInformationIndexAnnexDate_QNAME, XMLGregorianCalendar.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexAnnexSource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexAnnexSource", scope = IndexReferenceInformation.class)
    public JAXBElement<IndexAnnexSource> createIndexReferenceInformationIndexAnnexSource(IndexAnnexSource value) {
        return new JAXBElement<IndexAnnexSource>(_IndexReferenceInformationIndexAnnexSource_QNAME, IndexAnnexSource.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexSeries", scope = IndexReferenceInformation.class)
    public JAXBElement<BigInteger> createIndexReferenceInformationIndexSeries(BigInteger value) {
        return new JAXBElement<BigInteger>(_IndexReferenceInformationIndexSeries_QNAME, BigInteger.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "indexAnnexVersion", scope = IndexReferenceInformation.class)
    public JAXBElement<BigInteger> createIndexReferenceInformationIndexAnnexVersion(BigInteger value) {
        return new JAXBElement<BigInteger>(_IndexReferenceInformationIndexAnnexVersion_QNAME, BigInteger.class, IndexReferenceInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "transfereeAccount", scope = TradeNovationContent.class)
    public JAXBElement<AccountReference> createTradeNovationContentTransfereeAccount(AccountReference value) {
        return new JAXBElement<AccountReference>(_TradeNovationContentTransfereeAccount_QNAME, AccountReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "transferor", scope = TradeNovationContent.class)
    public JAXBElement<PartyReference> createTradeNovationContentTransferor(PartyReference value) {
        return new JAXBElement<PartyReference>(_TradeNovationContentTransferor_QNAME, PartyReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novationTradeDate", scope = TradeNovationContent.class)
    public JAXBElement<XMLGregorianCalendar> createTradeNovationContentNovationTradeDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TradeNovationContentNovationTradeDate_QNAME, XMLGregorianCalendar.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "feeTradeIdentifier", scope = TradeNovationContent.class)
    public JAXBElement<PartyTradeIdentifier> createTradeNovationContentFeeTradeIdentifier(PartyTradeIdentifier value) {
        return new JAXBElement<PartyTradeIdentifier>(_TradeNovationContentFeeTradeIdentifier_QNAME, PartyTradeIdentifier.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fullFirstCalculationPeriod", scope = TradeNovationContent.class)
    public JAXBElement<Boolean> createTradeNovationContentFullFirstCalculationPeriod(Boolean value) {
        return new JAXBElement<Boolean>(_TradeNovationContentFullFirstCalculationPeriod_QNAME, Boolean.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractualDefinitions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "contractualDefinitions", scope = TradeNovationContent.class)
    public JAXBElement<ContractualDefinitions> createTradeNovationContentContractualDefinitions(ContractualDefinitions value) {
        return new JAXBElement<ContractualDefinitions>(_TradeNovationContentContractualDefinitions_QNAME, ContractualDefinitions.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractualTermsSupplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "contractualTermsSupplement", scope = TradeNovationContent.class)
    public JAXBElement<ContractualTermsSupplement> createTradeNovationContentContractualTermsSupplement(ContractualTermsSupplement value) {
        return new JAXBElement<ContractualTermsSupplement>(_TradeNovationContentContractualTermsSupplement_QNAME, ContractualTermsSupplement.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "remainingNumberOfUnits", scope = TradeNovationContent.class)
    public JAXBElement<BigDecimal> createTradeNovationContentRemainingNumberOfUnits(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TradeNovationContentRemainingNumberOfUnits_QNAME, BigDecimal.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "newTradeIdentifier", scope = TradeNovationContent.class)
    public JAXBElement<PartyTradeIdentifier> createTradeNovationContentNewTradeIdentifier(PartyTradeIdentifier value) {
        return new JAXBElement<PartyTradeIdentifier>(_TradeNovationContentNewTradeIdentifier_QNAME, PartyTradeIdentifier.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Trade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "oldTrade", scope = TradeNovationContent.class)
    public JAXBElement<Trade> createTradeNovationContentOldTrade(Trade value) {
        return new JAXBElement<Trade>(_TradeNovationContentOldTrade_QNAME, Trade.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "otherTransfereeAccount", scope = TradeNovationContent.class)
    public JAXBElement<AccountReference> createTradeNovationContentOtherTransfereeAccount(AccountReference value) {
        return new JAXBElement<AccountReference>(_TradeNovationContentOtherTransfereeAccount_QNAME, AccountReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "remainingPartyAccount", scope = TradeNovationContent.class)
    public JAXBElement<AccountReference> createTradeNovationContentRemainingPartyAccount(AccountReference value) {
        return new JAXBElement<AccountReference>(_TradeNovationContentRemainingPartyAccount_QNAME, AccountReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novationDate", scope = TradeNovationContent.class)
    public JAXBElement<XMLGregorianCalendar> createTradeNovationContentNovationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TradeNovationContentNovationDate_QNAME, XMLGregorianCalendar.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Money }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "remainingAmount", scope = TradeNovationContent.class)
    public JAXBElement<Money> createTradeNovationContentRemainingAmount(Money value) {
        return new JAXBElement<Money>(_TradeNovationContentRemainingAmount_QNAME, Money.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novatedNumberOfOptions", scope = TradeNovationContent.class)
    public JAXBElement<BigDecimal> createTradeNovationContentNovatedNumberOfOptions(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TradeNovationContentNovatedNumberOfOptions_QNAME, BigDecimal.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Payment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "payment", scope = TradeNovationContent.class)
    public JAXBElement<Payment> createTradeNovationContentPayment(Payment value) {
        return new JAXBElement<Payment>(_TradeNovationContentPayment_QNAME, Payment.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Empty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "nonReliance", scope = TradeNovationContent.class)
    public JAXBElement<Empty> createTradeNovationContentNonReliance(Empty value) {
        return new JAXBElement<Empty>(_TradeNovationContentNonReliance_QNAME, Empty.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "oldTradeIdentifier", scope = TradeNovationContent.class)
    public JAXBElement<PartyTradeIdentifier> createTradeNovationContentOldTradeIdentifier(PartyTradeIdentifier value) {
        return new JAXBElement<PartyTradeIdentifier>(_TradeNovationContentOldTradeIdentifier_QNAME, PartyTradeIdentifier.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "transferee", scope = TradeNovationContent.class)
    public JAXBElement<PartyReference> createTradeNovationContentTransferee(PartyReference value) {
        return new JAXBElement<PartyReference>(_TradeNovationContentTransferee_QNAME, PartyReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "otherRemainingParty", scope = TradeNovationContent.class)
    public JAXBElement<PartyReference> createTradeNovationContentOtherRemainingParty(PartyReference value) {
        return new JAXBElement<PartyReference>(_TradeNovationContentOtherRemainingParty_QNAME, PartyReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "remainingParty", scope = TradeNovationContent.class)
    public JAXBElement<PartyReference> createTradeNovationContentRemainingParty(PartyReference value) {
        return new JAXBElement<PartyReference>(_TradeNovationContentRemainingParty_QNAME, PartyReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FirstPeriodStartDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "firstPeriodStartDate", scope = TradeNovationContent.class)
    public JAXBElement<FirstPeriodStartDate> createTradeNovationContentFirstPeriodStartDate(FirstPeriodStartDate value) {
        return new JAXBElement<FirstPeriodStartDate>(_TradeNovationContentFirstPeriodStartDate_QNAME, FirstPeriodStartDate.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartyReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "otherTransferee", scope = TradeNovationContent.class)
    public JAXBElement<PartyReference> createTradeNovationContentOtherTransferee(PartyReference value) {
        return new JAXBElement<PartyReference>(_TradeNovationContentOtherTransferee_QNAME, PartyReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "otherRemainingPartyAccount", scope = TradeNovationContent.class)
    public JAXBElement<AccountReference> createTradeNovationContentOtherRemainingPartyAccount(AccountReference value) {
        return new JAXBElement<AccountReference>(_TradeNovationContentOtherRemainingPartyAccount_QNAME, AccountReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecutionDateTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "executionDateTime", scope = TradeNovationContent.class)
    public JAXBElement<ExecutionDateTime> createTradeNovationContentExecutionDateTime(ExecutionDateTime value) {
        return new JAXBElement<ExecutionDateTime>(_TradeNovationContentExecutionDateTime_QNAME, ExecutionDateTime.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Trade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "newTrade", scope = TradeNovationContent.class)
    public JAXBElement<Trade> createTradeNovationContentNewTrade(Trade value) {
        return new JAXBElement<Trade>(_TradeNovationContentNewTrade_QNAME, Trade.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novatedNumberOfUnits", scope = TradeNovationContent.class)
    public JAXBElement<BigDecimal> createTradeNovationContentNovatedNumberOfUnits(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TradeNovationContentNovatedNumberOfUnits_QNAME, BigDecimal.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Money }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novatedAmount", scope = TradeNovationContent.class)
    public JAXBElement<Money> createTradeNovationContentNovatedAmount(Money value) {
        return new JAXBElement<Money>(_TradeNovationContentNovatedAmount_QNAME, Money.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "remainingNumberOfOptions", scope = TradeNovationContent.class)
    public JAXBElement<BigDecimal> createTradeNovationContentRemainingNumberOfOptions(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TradeNovationContentRemainingNumberOfOptions_QNAME, BigDecimal.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditDerivativesNotices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "creditDerivativesNotices", scope = TradeNovationContent.class)
    public JAXBElement<CreditDerivativesNotices> createTradeNovationContentCreditDerivativesNotices(CreditDerivativesNotices value) {
        return new JAXBElement<CreditDerivativesNotices>(_TradeNovationContentCreditDerivativesNotices_QNAME, CreditDerivativesNotices.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Trade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "feeTrade", scope = TradeNovationContent.class)
    public JAXBElement<Trade> createTradeNovationContentFeeTrade(Trade value) {
        return new JAXBElement<Trade>(_TradeNovationContentFeeTrade_QNAME, Trade.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "transferorAccount", scope = TradeNovationContent.class)
    public JAXBElement<AccountReference> createTradeNovationContentTransferorAccount(AccountReference value) {
        return new JAXBElement<AccountReference>(_TradeNovationContentTransferorAccount_QNAME, AccountReference.class, TradeNovationContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradeNotionalChange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "termination", scope = PositionHistory.class)
    public JAXBElement<TradeNotionalChange> createPositionHistoryTermination(TradeNotionalChange value) {
        return new JAXBElement<TradeNotionalChange>(_PositionHistoryTermination_QNAME, TradeNotionalChange.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradeAmendmentContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "amendment", scope = PositionHistory.class)
    public JAXBElement<TradeAmendmentContent> createPositionHistoryAmendment(TradeAmendmentContent value) {
        return new JAXBElement<TradeAmendmentContent>(_PositionHistoryAmendment_QNAME, TradeAmendmentContent.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginatingEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "originatingEvent", scope = PositionHistory.class)
    public JAXBElement<OriginatingEvent> createPositionHistoryOriginatingEvent(OriginatingEvent value) {
        return new JAXBElement<OriginatingEvent>(_PositionHistoryOriginatingEvent_QNAME, OriginatingEvent.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OptionExercise }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "optionExercise", scope = PositionHistory.class)
    public JAXBElement<OptionExercise> createPositionHistoryOptionExercise(OptionExercise value) {
        return new JAXBElement<OptionExercise>(_PositionHistoryOptionExercise_QNAME, OptionExercise.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeClear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "deClear", scope = PositionHistory.class)
    public JAXBElement<DeClear> createPositionHistoryDeClear(DeClear value) {
        return new JAXBElement<DeClear>(_PositionHistoryDeClear_QNAME, DeClear.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OptionExpiry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "optionExpiry", scope = PositionHistory.class)
    public JAXBElement<OptionExpiry> createPositionHistoryOptionExpiry(OptionExpiry value) {
        return new JAXBElement<OptionExpiry>(_PositionHistoryOptionExpiry_QNAME, OptionExpiry.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Withdrawal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "withdrawal", scope = PositionHistory.class)
    public JAXBElement<Withdrawal> createPositionHistoryWithdrawal(Withdrawal value) {
        return new JAXBElement<Withdrawal>(_PositionHistoryWithdrawal_QNAME, Withdrawal.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradeNovationContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "novation", scope = PositionHistory.class)
    public JAXBElement<TradeNovationContent> createPositionHistoryNovation(TradeNovationContent value) {
        return new JAXBElement<TradeNovationContent>(_PositionHistoryNovation_QNAME, TradeNovationContent.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Trade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "trade", scope = PositionHistory.class)
    public JAXBElement<Trade> createPositionHistoryTrade(Trade value) {
        return new JAXBElement<Trade>(_PositionHistoryTrade_QNAME, Trade.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TerminatingEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "terminatingEvent", scope = PositionHistory.class)
    public JAXBElement<TerminatingEvent> createPositionHistoryTerminatingEvent(TerminatingEvent value) {
        return new JAXBElement<TerminatingEvent>(_PositionHistoryTerminatingEvent_QNAME, TerminatingEvent.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradeNotionalChange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "increase", scope = PositionHistory.class)
    public JAXBElement<TradeNotionalChange> createPositionHistoryIncrease(TradeNotionalChange value) {
        return new JAXBElement<TradeNotionalChange>(_PositionHistoryIncrease_QNAME, TradeNotionalChange.class, PositionHistory.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxBarrierFeature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "barrier", scope = FxOptionFeatures.class)
    public JAXBElement<FxBarrierFeature> createFxOptionFeaturesBarrier(FxBarrierFeature value) {
        return new JAXBElement<FxBarrierFeature>(_FxOptionFeaturesBarrier_QNAME, FxBarrierFeature.class, FxOptionFeatures.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxAsianFeature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "asian", scope = FxOptionFeatures.class)
    public JAXBElement<FxAsianFeature> createFxOptionFeaturesAsian(FxAsianFeature value) {
        return new JAXBElement<FxAsianFeature>(_FxOptionFeaturesAsian_QNAME, FxAsianFeature.class, FxOptionFeatures.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuotationCharacteristics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "quotationCharacteristics", scope = Price.class)
    public JAXBElement<QuotationCharacteristics> createPriceQuotationCharacteristics(QuotationCharacteristics value) {
        return new JAXBElement<QuotationCharacteristics>(_PriceQuotationCharacteristics_QNAME, QuotationCharacteristics.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "cleanNetPrice", scope = Price.class)
    public JAXBElement<BigDecimal> createPriceCleanNetPrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PriceCleanNetPrice_QNAME, BigDecimal.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeterminationMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "determinationMethod", scope = Price.class)
    public JAXBElement<DeterminationMethod> createPriceDeterminationMethod(DeterminationMethod value) {
        return new JAXBElement<DeterminationMethod>(_PriceDeterminationMethod_QNAME, DeterminationMethod.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "netPrice", scope = Price.class)
    public JAXBElement<ActualPrice> createPriceNetPrice(ActualPrice value) {
        return new JAXBElement<ActualPrice>(_PriceNetPrice_QNAME, ActualPrice.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FxConversion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "fxConversion", scope = Price.class)
    public JAXBElement<FxConversion> createPriceFxConversion(FxConversion value) {
        return new JAXBElement<FxConversion>(_PriceFxConversion_QNAME, FxConversion.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "accruedInterestPrice", scope = Price.class)
    public JAXBElement<BigDecimal> createPriceAccruedInterestPrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PriceAccruedInterestPrice_QNAME, BigDecimal.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "grossPrice", scope = Price.class)
    public JAXBElement<ActualPrice> createPriceGrossPrice(ActualPrice value) {
        return new JAXBElement<ActualPrice>(_PriceGrossPrice_QNAME, ActualPrice.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmountReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "amountRelativeTo", scope = Price.class)
    public JAXBElement<AmountReference> createPriceAmountRelativeTo(AmountReference value) {
        return new JAXBElement<AmountReference>(_PriceAmountRelativeTo_QNAME, AmountReference.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Commission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "commission", scope = Price.class)
    public JAXBElement<Commission> createPriceCommission(Commission value) {
        return new JAXBElement<Commission>(_PriceCommission_QNAME, Commission.class, Price.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "gross", scope = NetAndGross.class)
    public JAXBElement<BigDecimal> createNetAndGrossGross(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_NetAndGrossGross_QNAME, BigDecimal.class, NetAndGross.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "net", scope = NetAndGross.class)
    public JAXBElement<BigDecimal> createNetAndGrossNet(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_NetAndGrossNet_QNAME, BigDecimal.class, NetAndGross.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentifiedDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "unadjustedDate", scope = AdjustableOrAdjustedDate.class)
    public JAXBElement<IdentifiedDate> createAdjustableOrAdjustedDateUnadjustedDate(IdentifiedDate value) {
        return new JAXBElement<IdentifiedDate>(_AdjustableOrAdjustedDateUnadjustedDate_QNAME, IdentifiedDate.class, AdjustableOrAdjustedDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessDayAdjustments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "dateAdjustments", scope = AdjustableOrAdjustedDate.class)
    public JAXBElement<BusinessDayAdjustments> createAdjustableOrAdjustedDateDateAdjustments(BusinessDayAdjustments value) {
        return new JAXBElement<BusinessDayAdjustments>(_AdjustableOrAdjustedDateDateAdjustments_QNAME, BusinessDayAdjustments.class, AdjustableOrAdjustedDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentifiedDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "adjustedDate", scope = AdjustableOrAdjustedDate.class)
    public JAXBElement<IdentifiedDate> createAdjustableOrAdjustedDateAdjustedDate(IdentifiedDate value) {
        return new JAXBElement<IdentifiedDate>(_AdjustableOrAdjustedDateAdjustedDate_QNAME, IdentifiedDate.class, AdjustableOrAdjustedDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "strike", scope = PricingDataPointCoordinate.class)
    public JAXBElement<BigDecimal> createPricingDataPointCoordinateStrike(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PricingDataPointCoordinateStrike_QNAME, BigDecimal.class, PricingDataPointCoordinate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericDimension }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "generic", scope = PricingDataPointCoordinate.class)
    public JAXBElement<GenericDimension> createPricingDataPointCoordinateGeneric(GenericDimension value) {
        return new JAXBElement<GenericDimension>(_PricingDataPointCoordinateGeneric_QNAME, GenericDimension.class, PricingDataPointCoordinate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeDimension }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "expiration", scope = PricingDataPointCoordinate.class)
    public JAXBElement<TimeDimension> createPricingDataPointCoordinateExpiration(TimeDimension value) {
        return new JAXBElement<TimeDimension>(_PricingDataPointCoordinateExpiration_QNAME, TimeDimension.class, PricingDataPointCoordinate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeDimension }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "term", scope = PricingDataPointCoordinate.class)
    public JAXBElement<TimeDimension> createPricingDataPointCoordinateTerm(TimeDimension value) {
        return new JAXBElement<TimeDimension>(_PricingDataPointCoordinateTerm_QNAME, TimeDimension.class, PricingDataPointCoordinate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupervisorRegistration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "supervisorRegistration", scope = ReportingRegime.class)
    public JAXBElement<SupervisorRegistration> createReportingRegimeSupervisorRegistration(SupervisorRegistration value) {
        return new JAXBElement<SupervisorRegistration>(_ReportingRegimeSupervisorRegistration_QNAME, SupervisorRegistration.class, ReportingRegime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "mandatorilyClearable", scope = ReportingRegime.class)
    public JAXBElement<Boolean> createReportingRegimeMandatorilyClearable(Boolean value) {
        return new JAXBElement<Boolean>(_ReportingRegimeMandatorilyClearable_QNAME, Boolean.class, ReportingRegime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportingRegimeName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "name", scope = ReportingRegime.class)
    public JAXBElement<ReportingRegimeName> createReportingRegimeName(ReportingRegimeName value) {
        return new JAXBElement<ReportingRegimeName>(_ReportingRegimeName_QNAME, ReportingRegimeName.class, ReportingRegime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportingRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "reportingRole", scope = ReportingRegime.class)
    public JAXBElement<ReportingRole> createReportingRegimeReportingRole(ReportingRole value) {
        return new JAXBElement<ReportingRole>(_ReportingRegimeReportingRole_QNAME, ReportingRole.class, ReportingRegime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportingPurpose }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fpml.org/FpML-5/recordkeeping", name = "reportingPurpose", scope = ReportingRegime.class)
    public JAXBElement<ReportingPurpose> createReportingRegimeReportingPurpose(ReportingPurpose value) {
        return new JAXBElement<ReportingPurpose>(_ReportingRegimeReportingPurpose_QNAME, ReportingPurpose.class, ReportingRegime.class, value);
    }

}
