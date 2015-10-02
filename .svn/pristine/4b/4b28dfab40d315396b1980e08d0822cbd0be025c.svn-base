//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * An abstract base class for all calculated money amounts, which are in the currency of the cash multiplier of the calculation.
 * 
 * <p>Classe Java pour CalculatedAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculatedAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates" minOccurs="0"/>
 *         &lt;element name="observationStartDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Dividends.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculatedAmount", propOrder = {
    "calculationDates",
    "observationStartDate",
    "optionsExchangeDividends",
    "additionalDividends",
    "allDividends"
})
@XmlSeeAlso({
    VarianceAmount.class,
    CorrelationAmount.class
})
public abstract class CalculatedAmount {

    protected AdjustableRelativeOrPeriodicDates calculationDates;
    protected AdjustableOrRelativeDate observationStartDate;
    protected Boolean optionsExchangeDividends;
    protected Boolean additionalDividends;
    protected Boolean allDividends;

    /**
     * Obtient la valeur de la propriété calculationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public AdjustableRelativeOrPeriodicDates getCalculationDates() {
        return calculationDates;
    }

    /**
     * Définit la valeur de la propriété calculationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public void setCalculationDates(AdjustableRelativeOrPeriodicDates value) {
        this.calculationDates = value;
    }

    /**
     * Obtient la valeur de la propriété observationStartDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getObservationStartDate() {
        return observationStartDate;
    }

    /**
     * Définit la valeur de la propriété observationStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setObservationStartDate(AdjustableOrRelativeDate value) {
        this.observationStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété optionsExchangeDividends.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptionsExchangeDividends() {
        return optionsExchangeDividends;
    }

    /**
     * Définit la valeur de la propriété optionsExchangeDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptionsExchangeDividends(Boolean value) {
        this.optionsExchangeDividends = value;
    }

    /**
     * Obtient la valeur de la propriété additionalDividends.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdditionalDividends() {
        return additionalDividends;
    }

    /**
     * Définit la valeur de la propriété additionalDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdditionalDividends(Boolean value) {
        this.additionalDividends = value;
    }

    /**
     * Obtient la valeur de la propriété allDividends.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllDividends() {
        return allDividends;
    }

    /**
     * Définit la valeur de la propriété allDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllDividends(Boolean value) {
        this.allDividends = value;
    }

}
