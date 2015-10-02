//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A class defining the content model for a term deposit product.
 * 
 * <p>Classe Java pour TermDeposit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TermDeposit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FxTenor.model" minOccurs="0"/>
 *         &lt;element name="principal" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveMoney" minOccurs="0"/>
 *         &lt;element name="fixedRate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="dayCountFraction" type="{http://www.fpml.org/FpML-5/recordkeeping}DayCountFraction" minOccurs="0"/>
 *         &lt;element name="features" type="{http://www.fpml.org/FpML-5/recordkeeping}TermDepositFeatures" minOccurs="0"/>
 *         &lt;element name="interest" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="payment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TermDeposit", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "startDate",
    "maturityDate",
    "tenorName",
    "tenorPeriod",
    "principal",
    "fixedRate",
    "dayCountFraction",
    "features",
    "interest",
    "payment"
})
public class TermDeposit
    extends Product
{

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar maturityDate;
    protected FxTenorPeriodEnum tenorName;
    protected Period tenorPeriod;
    protected PositiveMoney principal;
    protected BigDecimal fixedRate;
    protected DayCountFraction dayCountFraction;
    protected TermDepositFeatures features;
    protected Money interest;
    protected List<Payment> payment;

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
     * Obtient la valeur de la propriété startDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Définit la valeur de la propriété startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Obtient la valeur de la propriété maturityDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaturityDate() {
        return maturityDate;
    }

    /**
     * Définit la valeur de la propriété maturityDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaturityDate(XMLGregorianCalendar value) {
        this.maturityDate = value;
    }

    /**
     * Obtient la valeur de la propriété tenorName.
     * 
     * @return
     *     possible object is
     *     {@link FxTenorPeriodEnum }
     *     
     */
    public FxTenorPeriodEnum getTenorName() {
        return tenorName;
    }

    /**
     * Définit la valeur de la propriété tenorName.
     * 
     * @param value
     *     allowed object is
     *     {@link FxTenorPeriodEnum }
     *     
     */
    public void setTenorName(FxTenorPeriodEnum value) {
        this.tenorName = value;
    }

    /**
     * Obtient la valeur de la propriété tenorPeriod.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getTenorPeriod() {
        return tenorPeriod;
    }

    /**
     * Définit la valeur de la propriété tenorPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setTenorPeriod(Period value) {
        this.tenorPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété principal.
     * 
     * @return
     *     possible object is
     *     {@link PositiveMoney }
     *     
     */
    public PositiveMoney getPrincipal() {
        return principal;
    }

    /**
     * Définit la valeur de la propriété principal.
     * 
     * @param value
     *     allowed object is
     *     {@link PositiveMoney }
     *     
     */
    public void setPrincipal(PositiveMoney value) {
        this.principal = value;
    }

    /**
     * Obtient la valeur de la propriété fixedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedRate() {
        return fixedRate;
    }

    /**
     * Définit la valeur de la propriété fixedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedRate(BigDecimal value) {
        this.fixedRate = value;
    }

    /**
     * Obtient la valeur de la propriété dayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDayCountFraction() {
        return dayCountFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDayCountFraction(DayCountFraction value) {
        this.dayCountFraction = value;
    }

    /**
     * Obtient la valeur de la propriété features.
     * 
     * @return
     *     possible object is
     *     {@link TermDepositFeatures }
     *     
     */
    public TermDepositFeatures getFeatures() {
        return features;
    }

    /**
     * Définit la valeur de la propriété features.
     * 
     * @param value
     *     allowed object is
     *     {@link TermDepositFeatures }
     *     
     */
    public void setFeatures(TermDepositFeatures value) {
        this.features = value;
    }

    /**
     * Obtient la valeur de la propriété interest.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getInterest() {
        return interest;
    }

    /**
     * Définit la valeur de la propriété interest.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setInterest(Money value) {
        this.interest = value;
    }

    /**
     * Gets the value of the payment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getPayment() {
        if (payment == null) {
            payment = new ArrayList<Payment>();
        }
        return this.payment;
    }

}
