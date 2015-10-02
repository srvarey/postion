//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the conditions governing the payment of dividends to the receiver of the equity return. With the exception of the dividend payout ratio, which is defined for each of the underlying components.
 * 
 * <p>Classe Java pour DividendConditions complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendConditions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dividendReinvestment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dividendEntitlement" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendEntitlementEnum" minOccurs="0"/>
 *         &lt;element name="dividendAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendAmountTypeEnum" minOccurs="0"/>
 *         &lt;element name="dividendPaymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPaymentDate" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="dividendPeriodEffectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}DateReference" minOccurs="0"/>
 *             &lt;element name="dividendPeriodEndDate" type="{http://www.fpml.org/FpML-5/recordkeeping}DateReference" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="dividendPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPeriodEnum" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="extraOrdinaryDividends" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="excessDividendAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendAmountTypeEnum" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CurrencyAndDeterminationMethod.model" minOccurs="0"/>
 *         &lt;element name="dividendFxTriggerDate" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPaymentDate" minOccurs="0"/>
 *         &lt;element name="interestAccrualsMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestAccrualsCompoundingMethod" minOccurs="0"/>
 *         &lt;element name="numberOfIndexUnits" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}DeclaredCashAndCashEquivalentDividendPercentage.model"/>
 *         &lt;element name="nonCashDividendTreatment" type="{http://www.fpml.org/FpML-5/recordkeeping}NonCashDividendTreatmentEnum" minOccurs="0"/>
 *         &lt;element name="dividendComposition" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendCompositionEnum" minOccurs="0"/>
 *         &lt;element name="specialDividends" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendConditions", propOrder = {
    "dividendReinvestment",
    "dividendEntitlement",
    "dividendAmount",
    "dividendPaymentDate",
    "dividendPeriodEffectiveDate",
    "dividendPeriodEndDate",
    "dividendPeriod",
    "extraOrdinaryDividends",
    "excessDividendAmount",
    "currency",
    "determinationMethod",
    "currencyReference",
    "dividendFxTriggerDate",
    "interestAccrualsMethod",
    "numberOfIndexUnits",
    "declaredCashDividendPercentage",
    "declaredCashEquivalentDividendPercentage",
    "nonCashDividendTreatment",
    "dividendComposition",
    "specialDividends"
})
public class DividendConditions {

    protected Boolean dividendReinvestment;
    protected DividendEntitlementEnum dividendEntitlement;
    protected DividendAmountTypeEnum dividendAmount;
    protected DividendPaymentDate dividendPaymentDate;
    protected DateReference dividendPeriodEffectiveDate;
    protected DateReference dividendPeriodEndDate;
    protected DividendPeriodEnum dividendPeriod;
    protected PartyReference extraOrdinaryDividends;
    protected DividendAmountTypeEnum excessDividendAmount;
    protected IdentifiedCurrency currency;
    protected DeterminationMethod determinationMethod;
    protected IdentifiedCurrencyReference currencyReference;
    protected DividendPaymentDate dividendFxTriggerDate;
    protected InterestAccrualsCompoundingMethod interestAccrualsMethod;
    protected BigDecimal numberOfIndexUnits;
    protected BigDecimal declaredCashDividendPercentage;
    protected BigDecimal declaredCashEquivalentDividendPercentage;
    protected NonCashDividendTreatmentEnum nonCashDividendTreatment;
    protected DividendCompositionEnum dividendComposition;
    protected Boolean specialDividends;

    /**
     * Obtient la valeur de la propriété dividendReinvestment.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDividendReinvestment() {
        return dividendReinvestment;
    }

    /**
     * Définit la valeur de la propriété dividendReinvestment.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDividendReinvestment(Boolean value) {
        this.dividendReinvestment = value;
    }

    /**
     * Obtient la valeur de la propriété dividendEntitlement.
     * 
     * @return
     *     possible object is
     *     {@link DividendEntitlementEnum }
     *     
     */
    public DividendEntitlementEnum getDividendEntitlement() {
        return dividendEntitlement;
    }

    /**
     * Définit la valeur de la propriété dividendEntitlement.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendEntitlementEnum }
     *     
     */
    public void setDividendEntitlement(DividendEntitlementEnum value) {
        this.dividendEntitlement = value;
    }

    /**
     * Obtient la valeur de la propriété dividendAmount.
     * 
     * @return
     *     possible object is
     *     {@link DividendAmountTypeEnum }
     *     
     */
    public DividendAmountTypeEnum getDividendAmount() {
        return dividendAmount;
    }

    /**
     * Définit la valeur de la propriété dividendAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendAmountTypeEnum }
     *     
     */
    public void setDividendAmount(DividendAmountTypeEnum value) {
        this.dividendAmount = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link DividendPaymentDate }
     *     
     */
    public DividendPaymentDate getDividendPaymentDate() {
        return dividendPaymentDate;
    }

    /**
     * Définit la valeur de la propriété dividendPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendPaymentDate }
     *     
     */
    public void setDividendPaymentDate(DividendPaymentDate value) {
        this.dividendPaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPeriodEffectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link DateReference }
     *     
     */
    public DateReference getDividendPeriodEffectiveDate() {
        return dividendPeriodEffectiveDate;
    }

    /**
     * Définit la valeur de la propriété dividendPeriodEffectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DateReference }
     *     
     */
    public void setDividendPeriodEffectiveDate(DateReference value) {
        this.dividendPeriodEffectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPeriodEndDate.
     * 
     * @return
     *     possible object is
     *     {@link DateReference }
     *     
     */
    public DateReference getDividendPeriodEndDate() {
        return dividendPeriodEndDate;
    }

    /**
     * Définit la valeur de la propriété dividendPeriodEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DateReference }
     *     
     */
    public void setDividendPeriodEndDate(DateReference value) {
        this.dividendPeriodEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPeriod.
     * 
     * @return
     *     possible object is
     *     {@link DividendPeriodEnum }
     *     
     */
    public DividendPeriodEnum getDividendPeriod() {
        return dividendPeriod;
    }

    /**
     * Définit la valeur de la propriété dividendPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendPeriodEnum }
     *     
     */
    public void setDividendPeriod(DividendPeriodEnum value) {
        this.dividendPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété extraOrdinaryDividends.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getExtraOrdinaryDividends() {
        return extraOrdinaryDividends;
    }

    /**
     * Définit la valeur de la propriété extraOrdinaryDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setExtraOrdinaryDividends(PartyReference value) {
        this.extraOrdinaryDividends = value;
    }

    /**
     * Obtient la valeur de la propriété excessDividendAmount.
     * 
     * @return
     *     possible object is
     *     {@link DividendAmountTypeEnum }
     *     
     */
    public DividendAmountTypeEnum getExcessDividendAmount() {
        return excessDividendAmount;
    }

    /**
     * Définit la valeur de la propriété excessDividendAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendAmountTypeEnum }
     *     
     */
    public void setExcessDividendAmount(DividendAmountTypeEnum value) {
        this.excessDividendAmount = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public IdentifiedCurrency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public void setCurrency(IdentifiedCurrency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété determinationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethod }
     *     
     */
    public DeterminationMethod getDeterminationMethod() {
        return determinationMethod;
    }

    /**
     * Définit la valeur de la propriété determinationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethod }
     *     
     */
    public void setDeterminationMethod(DeterminationMethod value) {
        this.determinationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété currencyReference.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedCurrencyReference }
     *     
     */
    public IdentifiedCurrencyReference getCurrencyReference() {
        return currencyReference;
    }

    /**
     * Définit la valeur de la propriété currencyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedCurrencyReference }
     *     
     */
    public void setCurrencyReference(IdentifiedCurrencyReference value) {
        this.currencyReference = value;
    }

    /**
     * Obtient la valeur de la propriété dividendFxTriggerDate.
     * 
     * @return
     *     possible object is
     *     {@link DividendPaymentDate }
     *     
     */
    public DividendPaymentDate getDividendFxTriggerDate() {
        return dividendFxTriggerDate;
    }

    /**
     * Définit la valeur de la propriété dividendFxTriggerDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendPaymentDate }
     *     
     */
    public void setDividendFxTriggerDate(DividendPaymentDate value) {
        this.dividendFxTriggerDate = value;
    }

    /**
     * Obtient la valeur de la propriété interestAccrualsMethod.
     * 
     * @return
     *     possible object is
     *     {@link InterestAccrualsCompoundingMethod }
     *     
     */
    public InterestAccrualsCompoundingMethod getInterestAccrualsMethod() {
        return interestAccrualsMethod;
    }

    /**
     * Définit la valeur de la propriété interestAccrualsMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestAccrualsCompoundingMethod }
     *     
     */
    public void setInterestAccrualsMethod(InterestAccrualsCompoundingMethod value) {
        this.interestAccrualsMethod = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfIndexUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfIndexUnits() {
        return numberOfIndexUnits;
    }

    /**
     * Définit la valeur de la propriété numberOfIndexUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfIndexUnits(BigDecimal value) {
        this.numberOfIndexUnits = value;
    }

    /**
     * Obtient la valeur de la propriété declaredCashDividendPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclaredCashDividendPercentage() {
        return declaredCashDividendPercentage;
    }

    /**
     * Définit la valeur de la propriété declaredCashDividendPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclaredCashDividendPercentage(BigDecimal value) {
        this.declaredCashDividendPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété declaredCashEquivalentDividendPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclaredCashEquivalentDividendPercentage() {
        return declaredCashEquivalentDividendPercentage;
    }

    /**
     * Définit la valeur de la propriété declaredCashEquivalentDividendPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclaredCashEquivalentDividendPercentage(BigDecimal value) {
        this.declaredCashEquivalentDividendPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété nonCashDividendTreatment.
     * 
     * @return
     *     possible object is
     *     {@link NonCashDividendTreatmentEnum }
     *     
     */
    public NonCashDividendTreatmentEnum getNonCashDividendTreatment() {
        return nonCashDividendTreatment;
    }

    /**
     * Définit la valeur de la propriété nonCashDividendTreatment.
     * 
     * @param value
     *     allowed object is
     *     {@link NonCashDividendTreatmentEnum }
     *     
     */
    public void setNonCashDividendTreatment(NonCashDividendTreatmentEnum value) {
        this.nonCashDividendTreatment = value;
    }

    /**
     * Obtient la valeur de la propriété dividendComposition.
     * 
     * @return
     *     possible object is
     *     {@link DividendCompositionEnum }
     *     
     */
    public DividendCompositionEnum getDividendComposition() {
        return dividendComposition;
    }

    /**
     * Définit la valeur de la propriété dividendComposition.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendCompositionEnum }
     *     
     */
    public void setDividendComposition(DividendCompositionEnum value) {
        this.dividendComposition = value;
    }

    /**
     * Obtient la valeur de la propriété specialDividends.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSpecialDividends() {
        return specialDividends;
    }

    /**
     * Définit la valeur de la propriété specialDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSpecialDividends(Boolean value) {
        this.specialDividends = value;
    }

}
