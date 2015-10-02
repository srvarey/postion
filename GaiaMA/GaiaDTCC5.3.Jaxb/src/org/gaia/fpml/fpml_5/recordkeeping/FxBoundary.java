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
 * Describes a precise boundary value.
 * 
 * <p>Classe Java pour FxBoundary complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxBoundary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="inclusive" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="exclusive" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxBoundary", propOrder = {
    "inclusive",
    "exclusive"
})
public class FxBoundary {

    protected Object inclusive;
    protected Object exclusive;

    /**
     * Obtient la valeur de la propriété inclusive.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getInclusive() {
        return inclusive;
    }

    /**
     * Définit la valeur de la propriété inclusive.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setInclusive(Object value) {
        this.inclusive = value;
    }

    /**
     * Obtient la valeur de la propriété exclusive.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getExclusive() {
        return exclusive;
    }

    /**
     * Définit la valeur de la propriété exclusive.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setExclusive(Object value) {
        this.exclusive = value;
    }

}
