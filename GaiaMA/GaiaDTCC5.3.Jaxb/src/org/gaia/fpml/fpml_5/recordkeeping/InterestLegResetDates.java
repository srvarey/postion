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
 * <p>Classe Java pour InterestLegResetDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestLegResetDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationPeriodDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestLegCalculationPeriodDatesReference" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="resetRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetRelativeToEnum" minOccurs="0"/>
 *           &lt;element name="resetFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetFrequency" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="initialFixingDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;element name="fixingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDatesOrRelativeDateOffset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestLegResetDates", propOrder = {
    "calculationPeriodDatesReference",
    "resetRelativeTo",
    "resetFrequency",
    "initialFixingDate",
    "fixingDates"
})
public class InterestLegResetDates {

    protected InterestLegCalculationPeriodDatesReference calculationPeriodDatesReference;
    protected ResetRelativeToEnum resetRelativeTo;
    protected ResetFrequency resetFrequency;
    protected RelativeDateOffset initialFixingDate;
    protected AdjustableDatesOrRelativeDateOffset fixingDates;

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link InterestLegCalculationPeriodDatesReference }
     *     
     */
    public InterestLegCalculationPeriodDatesReference getCalculationPeriodDatesReference() {
        return calculationPeriodDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestLegCalculationPeriodDatesReference }
     *     
     */
    public void setCalculationPeriodDatesReference(InterestLegCalculationPeriodDatesReference value) {
        this.calculationPeriodDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété resetRelativeTo.
     * 
     * @return
     *     possible object is
     *     {@link ResetRelativeToEnum }
     *     
     */
    public ResetRelativeToEnum getResetRelativeTo() {
        return resetRelativeTo;
    }

    /**
     * Définit la valeur de la propriété resetRelativeTo.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetRelativeToEnum }
     *     
     */
    public void setResetRelativeTo(ResetRelativeToEnum value) {
        this.resetRelativeTo = value;
    }

    /**
     * Obtient la valeur de la propriété resetFrequency.
     * 
     * @return
     *     possible object is
     *     {@link ResetFrequency }
     *     
     */
    public ResetFrequency getResetFrequency() {
        return resetFrequency;
    }

    /**
     * Définit la valeur de la propriété resetFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetFrequency }
     *     
     */
    public void setResetFrequency(ResetFrequency value) {
        this.resetFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété initialFixingDate.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getInitialFixingDate() {
        return initialFixingDate;
    }

    /**
     * Définit la valeur de la propriété initialFixingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setInitialFixingDate(RelativeDateOffset value) {
        this.initialFixingDate = value;
    }

    /**
     * Obtient la valeur de la propriété fixingDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public AdjustableDatesOrRelativeDateOffset getFixingDates() {
        return fixingDates;
    }

    /**
     * Définit la valeur de la propriété fixingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public void setFixingDates(AdjustableDatesOrRelativeDateOffset value) {
        this.fixingDates = value;
    }

}
