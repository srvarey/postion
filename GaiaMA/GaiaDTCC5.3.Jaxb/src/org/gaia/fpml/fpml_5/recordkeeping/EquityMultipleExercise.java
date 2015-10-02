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
 * A type for defining the multiple exercise provisions of an American or Bermuda style equity option.
 * 
 * <p>Classe Java pour EquityMultipleExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityMultipleExercise">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="integralMultipleExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="minimumNumberOfOptions" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *         &lt;element name="maximumNumberOfOptions" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityMultipleExercise", propOrder = {
    "integralMultipleExercise",
    "minimumNumberOfOptions",
    "maximumNumberOfOptions"
})
public class EquityMultipleExercise {

    protected BigDecimal integralMultipleExercise;
    protected BigDecimal minimumNumberOfOptions;
    protected BigDecimal maximumNumberOfOptions;

    /**
     * Obtient la valeur de la propriété integralMultipleExercise.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIntegralMultipleExercise() {
        return integralMultipleExercise;
    }

    /**
     * Définit la valeur de la propriété integralMultipleExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIntegralMultipleExercise(BigDecimal value) {
        this.integralMultipleExercise = value;
    }

    /**
     * Obtient la valeur de la propriété minimumNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinimumNumberOfOptions() {
        return minimumNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété minimumNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinimumNumberOfOptions(BigDecimal value) {
        this.minimumNumberOfOptions = value;
    }

    /**
     * Obtient la valeur de la propriété maximumNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumNumberOfOptions() {
        return maximumNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété maximumNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumNumberOfOptions(BigDecimal value) {
        this.maximumNumberOfOptions = value;
    }

}
