//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour PeriodicPayment complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PeriodicPayment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentBase">
 *       &lt;sequence>
 *         &lt;element name="paymentFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
 *         &lt;element name="firstPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="lastRegularPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="rollConvention" type="{http://www.fpml.org/FpML-5/recordkeeping}RollConventionEnum" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="fixedAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *           &lt;element name="fixedAmountCalculation" type="{http://www.fpml.org/FpML-5/recordkeeping}FixedAmountCalculation"/>
 *         &lt;/choice>
 *         &lt;element name="adjustedPaymentDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustedPaymentDates" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodicPayment", propOrder = {
    "paymentFrequency",
    "firstPeriodStartDate",
    "firstPaymentDate",
    "lastRegularPaymentDate",
    "rollConvention",
    "fixedAmount",
    "fixedAmountCalculation",
    "adjustedPaymentDates"
})
public class PeriodicPayment
    extends PaymentBase
{

    protected Period paymentFrequency;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstPeriodStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstPaymentDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastRegularPaymentDate;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String rollConvention;
    protected Money fixedAmount;
    protected FixedAmountCalculation fixedAmountCalculation;
    protected List<AdjustedPaymentDates> adjustedPaymentDates;

    /**
     * Obtient la valeur de la propriété paymentFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Définit la valeur de la propriété paymentFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setPaymentFrequency(Period value) {
        this.paymentFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété firstPeriodStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstPeriodStartDate() {
        return firstPeriodStartDate;
    }

    /**
     * Définit la valeur de la propriété firstPeriodStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstPeriodStartDate(XMLGregorianCalendar value) {
        this.firstPeriodStartDate = value;
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
     * Obtient la valeur de la propriété rollConvention.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRollConvention() {
        return rollConvention;
    }

    /**
     * Définit la valeur de la propriété rollConvention.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRollConvention(String value) {
        this.rollConvention = value;
    }

    /**
     * Obtient la valeur de la propriété fixedAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getFixedAmount() {
        return fixedAmount;
    }

    /**
     * Définit la valeur de la propriété fixedAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setFixedAmount(Money value) {
        this.fixedAmount = value;
    }

    /**
     * Obtient la valeur de la propriété fixedAmountCalculation.
     * 
     * @return
     *     possible object is
     *     {@link FixedAmountCalculation }
     *     
     */
    public FixedAmountCalculation getFixedAmountCalculation() {
        return fixedAmountCalculation;
    }

    /**
     * Définit la valeur de la propriété fixedAmountCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link FixedAmountCalculation }
     *     
     */
    public void setFixedAmountCalculation(FixedAmountCalculation value) {
        this.fixedAmountCalculation = value;
    }

    /**
     * Gets the value of the adjustedPaymentDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adjustedPaymentDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdjustedPaymentDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjustedPaymentDates }
     * 
     * 
     */
    public List<AdjustedPaymentDates> getAdjustedPaymentDates() {
        if (adjustedPaymentDates == null) {
            adjustedPaymentDates = new ArrayList<AdjustedPaymentDates>();
        }
        return this.adjustedPaymentDates;
    }

}
