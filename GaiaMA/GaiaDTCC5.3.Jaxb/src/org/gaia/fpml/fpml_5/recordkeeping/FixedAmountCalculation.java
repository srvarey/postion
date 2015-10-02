//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FixedAmountCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FixedAmountCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationAmount" minOccurs="0"/>
 *         &lt;element name="fixedRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FixedRate"/>
 *         &lt;element name="dayCountFraction" type="{http://www.fpml.org/FpML-5/recordkeeping}DayCountFraction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedAmountCalculation", propOrder = {
    "calculationAmount",
    "fixedRate",
    "dayCountFraction"
})
public class FixedAmountCalculation {

    protected CalculationAmount calculationAmount;
    @XmlElement(required = true)
    protected FixedRate fixedRate;
    protected DayCountFraction dayCountFraction;

    /**
     * Obtient la valeur de la propriété calculationAmount.
     * 
     * @return
     *     possible object is
     *     {@link CalculationAmount }
     *     
     */
    public CalculationAmount getCalculationAmount() {
        return calculationAmount;
    }

    /**
     * Définit la valeur de la propriété calculationAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationAmount }
     *     
     */
    public void setCalculationAmount(CalculationAmount value) {
        this.calculationAmount = value;
    }

    /**
     * Obtient la valeur de la propriété fixedRate.
     * 
     * @return
     *     possible object is
     *     {@link FixedRate }
     *     
     */
    public FixedRate getFixedRate() {
        return fixedRate;
    }

    /**
     * Définit la valeur de la propriété fixedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link FixedRate }
     *     
     */
    public void setFixedRate(FixedRate value) {
        this.fixedRate = value;
    }

    /**
     * Obtient la valeur de la propriété dayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDayCountFraction() {
        return dayCountFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDayCountFraction(DayCountFraction value) {
        this.dayCountFraction = value;
    }

}
