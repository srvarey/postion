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
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining discounting information. The 2000 ISDA definitions, section 8.4. discounting (related to the calculation of a discounted fixed amount or floating amount) apply. This type must only be included if discounting applies.
 * 
 * <p>Classe Java pour Discounting complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Discounting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountingType" type="{http://www.fpml.org/FpML-5/recordkeeping}DiscountingTypeEnum" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}DiscountRate.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Discounting", propOrder = {
    "discountingType",
    "discountRate",
    "discountRateDayCountFraction"
})
public class Discounting {

    protected DiscountingTypeEnum discountingType;
    protected BigDecimal discountRate;
    protected DayCountFraction discountRateDayCountFraction;

    /**
     * Obtient la valeur de la propriété discountingType.
     * 
     * @return
     *     possible object is
     *     {@link DiscountingTypeEnum }
     *     
     */
    public DiscountingTypeEnum getDiscountingType() {
        return discountingType;
    }

    /**
     * Définit la valeur de la propriété discountingType.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountingTypeEnum }
     *     
     */
    public void setDiscountingType(DiscountingTypeEnum value) {
        this.discountingType = value;
    }

    /**
     * Obtient la valeur de la propriété discountRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * Définit la valeur de la propriété discountRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountRate(BigDecimal value) {
        this.discountRate = value;
    }

    /**
     * Obtient la valeur de la propriété discountRateDayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDiscountRateDayCountFraction() {
        return discountRateDayCountFraction;
    }

    /**
     * Définit la valeur de la propriété discountRateDayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDiscountRateDayCountFraction(DayCountFraction value) {
        this.discountRateDayCountFraction = value;
    }

}
