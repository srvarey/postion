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
 * A type for defining the multiple exercise provisions of an American style commodity option.
 * 
 * <p>Classe Java pour CommodityMultipleExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityMultipleExercise">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="integralMultipleQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityNotionalQuantity" minOccurs="0"/>
 *         &lt;element name="minimumNotionalQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityNotionalQuantity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityMultipleExercise", propOrder = {
    "integralMultipleQuantity",
    "minimumNotionalQuantity"
})
public class CommodityMultipleExercise {

    protected CommodityNotionalQuantity integralMultipleQuantity;
    protected CommodityNotionalQuantity minimumNotionalQuantity;

    /**
     * Obtient la valeur de la propriété integralMultipleQuantity.
     * 
     * @return
     *     possible object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public CommodityNotionalQuantity getIntegralMultipleQuantity() {
        return integralMultipleQuantity;
    }

    /**
     * Définit la valeur de la propriété integralMultipleQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public void setIntegralMultipleQuantity(CommodityNotionalQuantity value) {
        this.integralMultipleQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété minimumNotionalQuantity.
     * 
     * @return
     *     possible object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public CommodityNotionalQuantity getMinimumNotionalQuantity() {
        return minimumNotionalQuantity;
    }

    /**
     * Définit la valeur de la propriété minimumNotionalQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public void setMinimumNotionalQuantity(CommodityNotionalQuantity value) {
        this.minimumNotionalQuantity = value;
    }

}
