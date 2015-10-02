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
 * A type for defining ISDA 2002 Equity Derivative Representations.
 * 
 * <p>Classe Java pour Representations complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Representations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nonReliance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="agreementsRegardingHedging" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="indexDisclaimer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="additionalAcknowledgements" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Representations", propOrder = {
    "nonReliance",
    "agreementsRegardingHedging",
    "indexDisclaimer",
    "additionalAcknowledgements"
})
public class Representations {

    protected Boolean nonReliance;
    protected Boolean agreementsRegardingHedging;
    protected Boolean indexDisclaimer;
    protected Boolean additionalAcknowledgements;

    /**
     * Obtient la valeur de la propriété nonReliance.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonReliance() {
        return nonReliance;
    }

    /**
     * Définit la valeur de la propriété nonReliance.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonReliance(Boolean value) {
        this.nonReliance = value;
    }

    /**
     * Obtient la valeur de la propriété agreementsRegardingHedging.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAgreementsRegardingHedging() {
        return agreementsRegardingHedging;
    }

    /**
     * Définit la valeur de la propriété agreementsRegardingHedging.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAgreementsRegardingHedging(Boolean value) {
        this.agreementsRegardingHedging = value;
    }

    /**
     * Obtient la valeur de la propriété indexDisclaimer.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIndexDisclaimer() {
        return indexDisclaimer;
    }

    /**
     * Définit la valeur de la propriété indexDisclaimer.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndexDisclaimer(Boolean value) {
        this.indexDisclaimer = value;
    }

    /**
     * Obtient la valeur de la propriété additionalAcknowledgements.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdditionalAcknowledgements() {
        return additionalAcknowledgements;
    }

    /**
     * Définit la valeur de la propriété additionalAcknowledgements.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdditionalAcknowledgements(Boolean value) {
        this.additionalAcknowledgements = value;
    }

}
