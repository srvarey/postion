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
 * A type describing the method for accruing interests on dividends. Can be either a fixed rate reference or a floating rate reference.
 * 
 * <p>Classe Java pour InterestAccrualsMethod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestAccrualsMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="floatingRateCalculation" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateCalculation"/>
 *         &lt;element name="fixedRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestAccrualsMethod", propOrder = {
    "floatingRateCalculation",
    "fixedRate"
})
@XmlSeeAlso({
    InterestAccrualsCompoundingMethod.class,
    InterestCalculation.class
})
public class InterestAccrualsMethod {

    protected FloatingRateCalculation floatingRateCalculation;
    protected BigDecimal fixedRate;

    /**
     * Obtient la valeur de la propriété floatingRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link FloatingRateCalculation }
     *     
     */
    public FloatingRateCalculation getFloatingRateCalculation() {
        return floatingRateCalculation;
    }

    /**
     * Définit la valeur de la propriété floatingRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingRateCalculation }
     *     
     */
    public void setFloatingRateCalculation(FloatingRateCalculation value) {
        this.floatingRateCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété fixedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedRate() {
        return fixedRate;
    }

    /**
     * Définit la valeur de la propriété fixedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedRate(BigDecimal value) {
        this.fixedRate = value;
    }

}
