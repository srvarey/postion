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
 * The parameters for defining how the physically-settled commodity option can be exercised and how it is settled.
 * 
 * <p>Classe Java pour CommodityPhysicalExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityPhysicalExercise">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="americanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPhysicalAmericanExercise" minOccurs="0"/>
 *           &lt;element name="europeanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPhysicalEuropeanExercise" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="automaticExercise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="writtenConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityPhysicalExercise", propOrder = {
    "americanExercise",
    "europeanExercise",
    "automaticExercise",
    "writtenConfirmation"
})
public class CommodityPhysicalExercise {

    protected CommodityPhysicalAmericanExercise americanExercise;
    protected CommodityPhysicalEuropeanExercise europeanExercise;
    protected Boolean automaticExercise;
    protected Boolean writtenConfirmation;

    /**
     * Obtient la valeur de la propriété americanExercise.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPhysicalAmericanExercise }
     *     
     */
    public CommodityPhysicalAmericanExercise getAmericanExercise() {
        return americanExercise;
    }

    /**
     * Définit la valeur de la propriété americanExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPhysicalAmericanExercise }
     *     
     */
    public void setAmericanExercise(CommodityPhysicalAmericanExercise value) {
        this.americanExercise = value;
    }

    /**
     * Obtient la valeur de la propriété europeanExercise.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPhysicalEuropeanExercise }
     *     
     */
    public CommodityPhysicalEuropeanExercise getEuropeanExercise() {
        return europeanExercise;
    }

    /**
     * Définit la valeur de la propriété europeanExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPhysicalEuropeanExercise }
     *     
     */
    public void setEuropeanExercise(CommodityPhysicalEuropeanExercise value) {
        this.europeanExercise = value;
    }

    /**
     * Obtient la valeur de la propriété automaticExercise.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutomaticExercise() {
        return automaticExercise;
    }

    /**
     * Définit la valeur de la propriété automaticExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutomaticExercise(Boolean value) {
        this.automaticExercise = value;
    }

    /**
     * Obtient la valeur de la propriété writtenConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWrittenConfirmation() {
        return writtenConfirmation;
    }

    /**
     * Définit la valeur de la propriété writtenConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWrittenConfirmation(Boolean value) {
        this.writtenConfirmation = value;
    }

}
