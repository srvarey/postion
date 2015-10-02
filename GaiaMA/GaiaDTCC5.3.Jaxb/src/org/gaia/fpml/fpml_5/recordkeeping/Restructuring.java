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
 * <p>Classe Java pour Restructuring complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Restructuring">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="restructuringType" type="{http://www.fpml.org/FpML-5/recordkeeping}RestructuringType" minOccurs="0"/>
 *         &lt;element name="multipleHolderObligation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="multipleCreditEventNotices" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Restructuring", propOrder = {
    "applicable",
    "restructuringType",
    "multipleHolderObligation",
    "multipleCreditEventNotices"
})
public class Restructuring {

    protected Boolean applicable;
    protected RestructuringType restructuringType;
    protected Boolean multipleHolderObligation;
    protected Boolean multipleCreditEventNotices;

    /**
     * Obtient la valeur de la propriété applicable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplicable() {
        return applicable;
    }

    /**
     * Définit la valeur de la propriété applicable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplicable(Boolean value) {
        this.applicable = value;
    }

    /**
     * Obtient la valeur de la propriété restructuringType.
     * 
     * @return
     *     possible object is
     *     {@link RestructuringType }
     *     
     */
    public RestructuringType getRestructuringType() {
        return restructuringType;
    }

    /**
     * Définit la valeur de la propriété restructuringType.
     * 
     * @param value
     *     allowed object is
     *     {@link RestructuringType }
     *     
     */
    public void setRestructuringType(RestructuringType value) {
        this.restructuringType = value;
    }

    /**
     * Obtient la valeur de la propriété multipleHolderObligation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleHolderObligation() {
        return multipleHolderObligation;
    }

    /**
     * Définit la valeur de la propriété multipleHolderObligation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleHolderObligation(Boolean value) {
        this.multipleHolderObligation = value;
    }

    /**
     * Obtient la valeur de la propriété multipleCreditEventNotices.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleCreditEventNotices() {
        return multipleCreditEventNotices;
    }

    /**
     * Définit la valeur de la propriété multipleCreditEventNotices.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleCreditEventNotices(Boolean value) {
        this.multipleCreditEventNotices = value;
    }

}
