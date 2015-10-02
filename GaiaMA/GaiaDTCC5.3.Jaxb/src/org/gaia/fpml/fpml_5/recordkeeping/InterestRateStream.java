//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the components specifiying an interest rate stream, including both a parametric and cashflow representation for the stream of payments.
 * 
 * <p>Classe Java pour InterestRateStream complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestRateStream">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Leg">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="calculationPeriodDates" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodDates"/>
 *         &lt;element name="paymentDates" type="{http://www.fpml.org/FpML-5/recordkeeping}PaymentDates"/>
 *         &lt;element name="resetDates" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetDates" minOccurs="0"/>
 *         &lt;element name="calculationPeriodAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodAmount"/>
 *         &lt;element name="stubCalculationPeriodAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}StubCalculationPeriodAmount" minOccurs="0"/>
 *         &lt;element name="principalExchanges" type="{http://www.fpml.org/FpML-5/recordkeeping}PrincipalExchanges" minOccurs="0"/>
 *         &lt;element name="cashflows" type="{http://www.fpml.org/FpML-5/recordkeeping}Cashflows" minOccurs="0"/>
 *         &lt;element name="settlementProvision" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementProvision" minOccurs="0"/>
 *         &lt;element name="formula" type="{http://www.fpml.org/FpML-5/recordkeeping}Formula" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestRateStream", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "calculationPeriodDates",
    "paymentDates",
    "resetDates",
    "calculationPeriodAmount",
    "stubCalculationPeriodAmount",
    "principalExchanges",
    "cashflows",
    "settlementProvision",
    "formula"
})
public class InterestRateStream
    extends Leg
{

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    @XmlElement(required = true)
    protected CalculationPeriodDates calculationPeriodDates;
    @XmlElement(required = true)
    protected PaymentDates paymentDates;
    protected ResetDates resetDates;
    @XmlElement(required = true)
    protected CalculationPeriodAmount calculationPeriodAmount;
    protected StubCalculationPeriodAmount stubCalculationPeriodAmount;
    protected PrincipalExchanges principalExchanges;
    protected Cashflows cashflows;
    protected SettlementProvision settlementProvision;
    protected Formula formula;

    /**
     * Obtient la valeur de la propriété payerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getPayerPartyReference() {
        return payerPartyReference;
    }

    /**
     * Définit la valeur de la propriété payerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setPayerPartyReference(PartyReference value) {
        this.payerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété payerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getPayerAccountReference() {
        return payerAccountReference;
    }

    /**
     * Définit la valeur de la propriété payerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setPayerAccountReference(AccountReference value) {
        this.payerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getReceiverPartyReference() {
        return receiverPartyReference;
    }

    /**
     * Définit la valeur de la propriété receiverPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setReceiverPartyReference(PartyReference value) {
        this.receiverPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getReceiverAccountReference() {
        return receiverAccountReference;
    }

    /**
     * Définit la valeur de la propriété receiverAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setReceiverAccountReference(AccountReference value) {
        this.receiverAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodDates.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodDates }
     *     
     */
    public CalculationPeriodDates getCalculationPeriodDates() {
        return calculationPeriodDates;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodDates }
     *     
     */
    public void setCalculationPeriodDates(CalculationPeriodDates value) {
        this.calculationPeriodDates = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDates.
     * 
     * @return
     *     possible object is
     *     {@link PaymentDates }
     *     
     */
    public PaymentDates getPaymentDates() {
        return paymentDates;
    }

    /**
     * Définit la valeur de la propriété paymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentDates }
     *     
     */
    public void setPaymentDates(PaymentDates value) {
        this.paymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété resetDates.
     * 
     * @return
     *     possible object is
     *     {@link ResetDates }
     *     
     */
    public ResetDates getResetDates() {
        return resetDates;
    }

    /**
     * Définit la valeur de la propriété resetDates.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetDates }
     *     
     */
    public void setResetDates(ResetDates value) {
        this.resetDates = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodAmount.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodAmount }
     *     
     */
    public CalculationPeriodAmount getCalculationPeriodAmount() {
        return calculationPeriodAmount;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodAmount }
     *     
     */
    public void setCalculationPeriodAmount(CalculationPeriodAmount value) {
        this.calculationPeriodAmount = value;
    }

    /**
     * Obtient la valeur de la propriété stubCalculationPeriodAmount.
     * 
     * @return
     *     possible object is
     *     {@link StubCalculationPeriodAmount }
     *     
     */
    public StubCalculationPeriodAmount getStubCalculationPeriodAmount() {
        return stubCalculationPeriodAmount;
    }

    /**
     * Définit la valeur de la propriété stubCalculationPeriodAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link StubCalculationPeriodAmount }
     *     
     */
    public void setStubCalculationPeriodAmount(StubCalculationPeriodAmount value) {
        this.stubCalculationPeriodAmount = value;
    }

    /**
     * Obtient la valeur de la propriété principalExchanges.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalExchanges }
     *     
     */
    public PrincipalExchanges getPrincipalExchanges() {
        return principalExchanges;
    }

    /**
     * Définit la valeur de la propriété principalExchanges.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalExchanges }
     *     
     */
    public void setPrincipalExchanges(PrincipalExchanges value) {
        this.principalExchanges = value;
    }

    /**
     * Obtient la valeur de la propriété cashflows.
     * 
     * @return
     *     possible object is
     *     {@link Cashflows }
     *     
     */
    public Cashflows getCashflows() {
        return cashflows;
    }

    /**
     * Définit la valeur de la propriété cashflows.
     * 
     * @param value
     *     allowed object is
     *     {@link Cashflows }
     *     
     */
    public void setCashflows(Cashflows value) {
        this.cashflows = value;
    }

    /**
     * Obtient la valeur de la propriété settlementProvision.
     * 
     * @return
     *     possible object is
     *     {@link SettlementProvision }
     *     
     */
    public SettlementProvision getSettlementProvision() {
        return settlementProvision;
    }

    /**
     * Définit la valeur de la propriété settlementProvision.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementProvision }
     *     
     */
    public void setSettlementProvision(SettlementProvision value) {
        this.settlementProvision = value;
    }

    /**
     * Obtient la valeur de la propriété formula.
     * 
     * @return
     *     possible object is
     *     {@link Formula }
     *     
     */
    public Formula getFormula() {
        return formula;
    }

    /**
     * Définit la valeur de la propriété formula.
     * 
     * @param value
     *     allowed object is
     *     {@link Formula }
     *     
     */
    public void setFormula(Formula value) {
        this.formula = value;
    }

}
