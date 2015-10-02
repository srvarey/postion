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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type to describe the cashflow representation for fx linked notionals.
 * 
 * <p>Classe Java pour FxLinkedNotionalAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxLinkedNotionalAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resetDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedFxSpotFixingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="observedFxSpotRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="notionalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxLinkedNotionalAmount", propOrder = {
    "resetDate",
    "adjustedFxSpotFixingDate",
    "observedFxSpotRate",
    "notionalAmount"
})
public class FxLinkedNotionalAmount {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar resetDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedFxSpotFixingDate;
    protected BigDecimal observedFxSpotRate;
    protected BigDecimal notionalAmount;

    /**
     * Obtient la valeur de la propriété resetDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResetDate() {
        return resetDate;
    }

    /**
     * Définit la valeur de la propriété resetDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResetDate(XMLGregorianCalendar value) {
        this.resetDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedFxSpotFixingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedFxSpotFixingDate() {
        return adjustedFxSpotFixingDate;
    }

    /**
     * Définit la valeur de la propriété adjustedFxSpotFixingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedFxSpotFixingDate(XMLGregorianCalendar value) {
        this.adjustedFxSpotFixingDate = value;
    }

    /**
     * Obtient la valeur de la propriété observedFxSpotRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getObservedFxSpotRate() {
        return observedFxSpotRate;
    }

    /**
     * Définit la valeur de la propriété observedFxSpotRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setObservedFxSpotRate(BigDecimal value) {
        this.observedFxSpotRate = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotionalAmount() {
        return notionalAmount;
    }

    /**
     * Définit la valeur de la propriété notionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotionalAmount(BigDecimal value) {
        this.notionalAmount = value;
    }

}
