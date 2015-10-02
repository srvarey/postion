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


/**
 * <p>Classe Java pour ProtectionTerms complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ProtectionTerms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money"/>
 *         &lt;element name="creditEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditEvents" minOccurs="0"/>
 *         &lt;element name="obligations" type="{http://www.fpml.org/FpML-5/recordkeeping}Obligations" minOccurs="0"/>
 *         &lt;element name="floatingAmountEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingAmountEvents" minOccurs="0"/>
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
@XmlType(name = "ProtectionTerms", propOrder = {
    "calculationAmount",
    "creditEvents",
    "obligations",
    "floatingAmountEvents"
})
public class ProtectionTerms {

    @XmlElement(required = true)
    protected Money calculationAmount;
    protected CreditEvents creditEvents;
    protected Obligations obligations;
    protected FloatingAmountEvents floatingAmountEvents;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété calculationAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getCalculationAmount() {
        return calculationAmount;
    }

    /**
     * Définit la valeur de la propriété calculationAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setCalculationAmount(Money value) {
        this.calculationAmount = value;
    }

    /**
     * Obtient la valeur de la propriété creditEvents.
     * 
     * @return
     *     possible object is
     *     {@link CreditEvents }
     *     
     */
    public CreditEvents getCreditEvents() {
        return creditEvents;
    }

    /**
     * Définit la valeur de la propriété creditEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditEvents }
     *     
     */
    public void setCreditEvents(CreditEvents value) {
        this.creditEvents = value;
    }

    /**
     * Obtient la valeur de la propriété obligations.
     * 
     * @return
     *     possible object is
     *     {@link Obligations }
     *     
     */
    public Obligations getObligations() {
        return obligations;
    }

    /**
     * Définit la valeur de la propriété obligations.
     * 
     * @param value
     *     allowed object is
     *     {@link Obligations }
     *     
     */
    public void setObligations(Obligations value) {
        this.obligations = value;
    }

    /**
     * Obtient la valeur de la propriété floatingAmountEvents.
     * 
     * @return
     *     possible object is
     *     {@link FloatingAmountEvents }
     *     
     */
    public FloatingAmountEvents getFloatingAmountEvents() {
        return floatingAmountEvents;
    }

    /**
     * Définit la valeur de la propriété floatingAmountEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingAmountEvents }
     *     
     */
    public void setFloatingAmountEvents(FloatingAmountEvents value) {
        this.floatingAmountEvents = value;
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
