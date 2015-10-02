//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type defining parameters used to generate the payment dates schedule, including the specification of early or delayed payments. Payment dates are determined relative to the calculation period dates or the reset dates.
 * 
 * <p>Classe Java pour PaymentDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PaymentDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="calculationPeriodDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodDatesReference" minOccurs="0"/>
 *           &lt;element name="resetDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetDatesReference" minOccurs="0"/>
 *           &lt;element name="valuationDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationDatesReference" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="paymentFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}Frequency"/>
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="lastRegularPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="payRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}PayRelativeToEnum" minOccurs="0"/>
 *         &lt;element name="paymentDaysOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}Offset" minOccurs="0"/>
 *         &lt;element name="paymentDatesAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDates", propOrder = {
    "calculationPeriodDatesReference",
    "resetDatesReference",
    "valuationDatesReference",
    "paymentFrequency",
    "firstPaymentDate",
    "lastRegularPaymentDate",
    "payRelativeTo",
    "paymentDaysOffset",
    "paymentDatesAdjustments"
})
public class PaymentDates {

    protected CalculationPeriodDatesReference calculationPeriodDatesReference;
    protected ResetDatesReference resetDatesReference;
    protected ValuationDatesReference valuationDatesReference;
    @XmlElement(required = true)
    protected Frequency paymentFrequency;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstPaymentDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastRegularPaymentDate;
    protected PayRelativeToEnum payRelativeTo;
    protected Offset paymentDaysOffset;
    protected BusinessDayAdjustments paymentDatesAdjustments;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public CalculationPeriodDatesReference getCalculationPeriodDatesReference() {
        return calculationPeriodDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public void setCalculationPeriodDatesReference(CalculationPeriodDatesReference value) {
        this.calculationPeriodDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété resetDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link ResetDatesReference }
     *     
     */
    public ResetDatesReference getResetDatesReference() {
        return resetDatesReference;
    }

    /**
     * Définit la valeur de la propriété resetDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetDatesReference }
     *     
     */
    public void setResetDatesReference(ResetDatesReference value) {
        this.resetDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété valuationDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link ValuationDatesReference }
     *     
     */
    public ValuationDatesReference getValuationDatesReference() {
        return valuationDatesReference;
    }

    /**
     * Définit la valeur de la propriété valuationDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationDatesReference }
     *     
     */
    public void setValuationDatesReference(ValuationDatesReference value) {
        this.valuationDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété paymentFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Frequency }
     *     
     */
    public Frequency getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Définit la valeur de la propriété paymentFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Frequency }
     *     
     */
    public void setPaymentFrequency(Frequency value) {
        this.paymentFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété firstPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstPaymentDate() {
        return firstPaymentDate;
    }

    /**
     * Définit la valeur de la propriété firstPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstPaymentDate(XMLGregorianCalendar value) {
        this.firstPaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastRegularPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastRegularPaymentDate() {
        return lastRegularPaymentDate;
    }

    /**
     * Définit la valeur de la propriété lastRegularPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastRegularPaymentDate(XMLGregorianCalendar value) {
        this.lastRegularPaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété payRelativeTo.
     * 
     * @return
     *     possible object is
     *     {@link PayRelativeToEnum }
     *     
     */
    public PayRelativeToEnum getPayRelativeTo() {
        return payRelativeTo;
    }

    /**
     * Définit la valeur de la propriété payRelativeTo.
     * 
     * @param value
     *     allowed object is
     *     {@link PayRelativeToEnum }
     *     
     */
    public void setPayRelativeTo(PayRelativeToEnum value) {
        this.payRelativeTo = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDaysOffset.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getPaymentDaysOffset() {
        return paymentDaysOffset;
    }

    /**
     * Définit la valeur de la propriété paymentDaysOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setPaymentDaysOffset(Offset value) {
        this.paymentDaysOffset = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDatesAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public BusinessDayAdjustments getPaymentDatesAdjustments() {
        return paymentDatesAdjustments;
    }

    /**
     * Définit la valeur de la propriété paymentDatesAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public void setPaymentDatesAdjustments(BusinessDayAdjustments value) {
        this.paymentDatesAdjustments = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
