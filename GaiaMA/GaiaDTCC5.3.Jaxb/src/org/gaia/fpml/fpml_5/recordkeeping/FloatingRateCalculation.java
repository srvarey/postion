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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the floating rate and definitions relating to the calculation of floating rate amounts.
 * 
 * <p>Classe Java pour FloatingRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FloatingRateCalculation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRate">
 *       &lt;sequence>
 *         &lt;element name="initialRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="finalRateRounding" type="{http://www.fpml.org/FpML-5/recordkeeping}Rounding" minOccurs="0"/>
 *         &lt;element name="averagingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingMethodEnum" minOccurs="0"/>
 *         &lt;element name="negativeInterestRateTreatment" type="{http://www.fpml.org/FpML-5/recordkeeping}NegativeInterestRateTreatmentEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatingRateCalculation", propOrder = {
    "initialRate",
    "finalRateRounding",
    "averagingMethod",
    "negativeInterestRateTreatment"
})
@XmlSeeAlso({
    InflationRateCalculation.class
})
public class FloatingRateCalculation
    extends FloatingRate
{

    protected BigDecimal initialRate;
    protected Rounding finalRateRounding;
    protected AveragingMethodEnum averagingMethod;
    protected NegativeInterestRateTreatmentEnum negativeInterestRateTreatment;

    /**
     * Obtient la valeur de la propriété initialRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialRate() {
        return initialRate;
    }

    /**
     * Définit la valeur de la propriété initialRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialRate(BigDecimal value) {
        this.initialRate = value;
    }

    /**
     * Obtient la valeur de la propriété finalRateRounding.
     * 
     * @return
     *     possible object is
     *     {@link Rounding }
     *     
     */
    public Rounding getFinalRateRounding() {
        return finalRateRounding;
    }

    /**
     * Définit la valeur de la propriété finalRateRounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Rounding }
     *     
     */
    public void setFinalRateRounding(Rounding value) {
        this.finalRateRounding = value;
    }

    /**
     * Obtient la valeur de la propriété averagingMethod.
     * 
     * @return
     *     possible object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public AveragingMethodEnum getAveragingMethod() {
        return averagingMethod;
    }

    /**
     * Définit la valeur de la propriété averagingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public void setAveragingMethod(AveragingMethodEnum value) {
        this.averagingMethod = value;
    }

    /**
     * Obtient la valeur de la propriété negativeInterestRateTreatment.
     * 
     * @return
     *     possible object is
     *     {@link NegativeInterestRateTreatmentEnum }
     *     
     */
    public NegativeInterestRateTreatmentEnum getNegativeInterestRateTreatment() {
        return negativeInterestRateTreatment;
    }

    /**
     * Définit la valeur de la propriété negativeInterestRateTreatment.
     * 
     * @param value
     *     allowed object is
     *     {@link NegativeInterestRateTreatmentEnum }
     *     
     */
    public void setNegativeInterestRateTreatment(NegativeInterestRateTreatmentEnum value) {
        this.negativeInterestRateTreatment = value;
    }

}
