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
 * A type defining the parameters used in the calculation of fixed or floating rate calculation period amounts or for specifying a known calculation period amount or known amount schedule.
 * 
 * <p>Classe Java pour CalculationPeriodAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationPeriodAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="calculation" type="{http://www.fpml.org/FpML-5/recordkeeping}Calculation"/>
 *         &lt;element name="knownAmountSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}AmountSchedule" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationPeriodAmount", propOrder = {
    "calculation",
    "knownAmountSchedule"
})
public class CalculationPeriodAmount {

    protected Calculation calculation;
    protected AmountSchedule knownAmountSchedule;

    /**
     * Obtient la valeur de la propriété calculation.
     * 
     * @return
     *     possible object is
     *     {@link Calculation }
     *     
     */
    public Calculation getCalculation() {
        return calculation;
    }

    /**
     * Définit la valeur de la propriété calculation.
     * 
     * @param value
     *     allowed object is
     *     {@link Calculation }
     *     
     */
    public void setCalculation(Calculation value) {
        this.calculation = value;
    }

    /**
     * Obtient la valeur de la propriété knownAmountSchedule.
     * 
     * @return
     *     possible object is
     *     {@link AmountSchedule }
     *     
     */
    public AmountSchedule getKnownAmountSchedule() {
        return knownAmountSchedule;
    }

    /**
     * Définit la valeur de la propriété knownAmountSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountSchedule }
     *     
     */
    public void setKnownAmountSchedule(AmountSchedule value) {
        this.knownAmountSchedule = value;
    }

}
