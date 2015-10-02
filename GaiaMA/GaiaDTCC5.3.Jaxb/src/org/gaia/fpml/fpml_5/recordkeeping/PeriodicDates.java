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
 * <p>Classe Java pour PeriodicDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PeriodicDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationStartDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="calculationEndDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="calculationPeriodFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodFrequency" minOccurs="0"/>
 *         &lt;element name="calculationPeriodDatesAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodicDates", propOrder = {
    "calculationStartDate",
    "calculationEndDate",
    "calculationPeriodFrequency",
    "calculationPeriodDatesAdjustments"
})
public class PeriodicDates {

    protected AdjustableOrRelativeDate calculationStartDate;
    protected AdjustableOrRelativeDate calculationEndDate;
    protected CalculationPeriodFrequency calculationPeriodFrequency;
    protected BusinessDayAdjustments calculationPeriodDatesAdjustments;

    /**
     * Obtient la valeur de la propriété calculationStartDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getCalculationStartDate() {
        return calculationStartDate;
    }

    /**
     * Définit la valeur de la propriété calculationStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setCalculationStartDate(AdjustableOrRelativeDate value) {
        this.calculationStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété calculationEndDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getCalculationEndDate() {
        return calculationEndDate;
    }

    /**
     * Définit la valeur de la propriété calculationEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setCalculationEndDate(AdjustableOrRelativeDate value) {
        this.calculationEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodFrequency.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodFrequency }
     *     
     */
    public CalculationPeriodFrequency getCalculationPeriodFrequency() {
        return calculationPeriodFrequency;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodFrequency }
     *     
     */
    public void setCalculationPeriodFrequency(CalculationPeriodFrequency value) {
        this.calculationPeriodFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public BusinessDayAdjustments getCalculationPeriodDatesAdjustments() {
        return calculationPeriodDatesAdjustments;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public void setCalculationPeriodDatesAdjustments(BusinessDayAdjustments value) {
        this.calculationPeriodDatesAdjustments = value;
    }

}
