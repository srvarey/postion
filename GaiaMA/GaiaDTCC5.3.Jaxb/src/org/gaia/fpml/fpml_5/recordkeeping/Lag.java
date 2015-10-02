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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * An observation period that is offset from a Calculation Period.
 * 
 * <p>Classe Java pour Lag complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Lag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lagDuration" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
 *         &lt;element name="firstObservationDateOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
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
@XmlType(name = "Lag", propOrder = {
    "lagDuration",
    "firstObservationDateOffset"
})
public class Lag {

    protected Period lagDuration;
    protected Period firstObservationDateOffset;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété lagDuration.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getLagDuration() {
        return lagDuration;
    }

    /**
     * Définit la valeur de la propriété lagDuration.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setLagDuration(Period value) {
        this.lagDuration = value;
    }

    /**
     * Obtient la valeur de la propriété firstObservationDateOffset.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getFirstObservationDateOffset() {
        return firstObservationDateOffset;
    }

    /**
     * Définit la valeur de la propriété firstObservationDateOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setFirstObservationDateOffset(Period value) {
        this.firstObservationDateOffset = value;
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