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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour SinglePayment complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SinglePayment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentBase">
 *       &lt;sequence>
 *         &lt;element name="adjustablePaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fixedAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SinglePayment", propOrder = {
    "adjustablePaymentDate",
    "adjustedPaymentDate",
    "fixedAmount"
})
public class SinglePayment
    extends PaymentBase
{

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustablePaymentDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedPaymentDate;
    @XmlElement(required = true)
    protected Money fixedAmount;

    /**
     * Obtient la valeur de la propriété adjustablePaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustablePaymentDate() {
        return adjustablePaymentDate;
    }

    /**
     * Définit la valeur de la propriété adjustablePaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustablePaymentDate(XMLGregorianCalendar value) {
        this.adjustablePaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedPaymentDate() {
        return adjustedPaymentDate;
    }

    /**
     * Définit la valeur de la propriété adjustedPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedPaymentDate(XMLGregorianCalendar value) {
        this.adjustedPaymentDate = value;
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

}
