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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the characteristics of the gas being traded in a physically settled gas transaction.
 * 
 * <p>Classe Java pour GasProduct complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GasProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}GasProductTypeEnum"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="calorificValue" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *           &lt;element name="quality" type="{http://www.fpml.org/FpML-5/recordkeeping}GasQuality" minOccurs="0"/>
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
@XmlType(name = "GasProduct", propOrder = {
    "type",
    "calorificValue",
    "quality"
})
public class GasProduct {

    @XmlElement(required = true)
    protected GasProductTypeEnum type;
    protected BigDecimal calorificValue;
    protected GasQuality quality;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link GasProductTypeEnum }
     *     
     */
    public GasProductTypeEnum getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link GasProductTypeEnum }
     *     
     */
    public void setType(GasProductTypeEnum value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété calorificValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCalorificValue() {
        return calorificValue;
    }

    /**
     * Définit la valeur de la propriété calorificValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCalorificValue(BigDecimal value) {
        this.calorificValue = value;
    }

    /**
     * Obtient la valeur de la propriété quality.
     * 
     * @return
     *     possible object is
     *     {@link GasQuality }
     *     
     */
    public GasQuality getQuality() {
        return quality;
    }

    /**
     * Définit la valeur de la propriété quality.
     * 
     * @param value
     *     allowed object is
     *     {@link GasQuality }
     *     
     */
    public void setQuality(GasQuality value) {
        this.quality = value;
    }

}
