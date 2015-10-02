//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the date on which the dividend will be paid/received. This type is also used to specify the date on which the FX rate will be determined, when applicable.
 * 
 * <p>Classe Java pour DividendPaymentDate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendPaymentDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="dividendDateReference" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendDateReferenceEnum" minOccurs="0"/>
 *             &lt;element name="paymentDateOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}Offset" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="adjustableDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendPaymentDate", propOrder = {
    "dividendDateReference",
    "paymentDateOffset",
    "adjustableDate"
})
public class DividendPaymentDate {

    protected DividendDateReferenceEnum dividendDateReference;
    protected Offset paymentDateOffset;
    protected AdjustableDate adjustableDate;

    /**
     * Obtient la valeur de la propriété dividendDateReference.
     * 
     * @return
     *     possible object is
     *     {@link DividendDateReferenceEnum }
     *     
     */
    public DividendDateReferenceEnum getDividendDateReference() {
        return dividendDateReference;
    }

    /**
     * Définit la valeur de la propriété dividendDateReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendDateReferenceEnum }
     *     
     */
    public void setDividendDateReference(DividendDateReferenceEnum value) {
        this.dividendDateReference = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDateOffset.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getPaymentDateOffset() {
        return paymentDateOffset;
    }

    /**
     * Définit la valeur de la propriété paymentDateOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setPaymentDateOffset(Offset value) {
        this.paymentDateOffset = value;
    }

    /**
     * Obtient la valeur de la propriété adjustableDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getAdjustableDate() {
        return adjustableDate;
    }

    /**
     * Définit la valeur de la propriété adjustableDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setAdjustableDate(AdjustableDate value) {
        this.adjustableDate = value;
    }

}
