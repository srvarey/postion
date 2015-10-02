//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type defining the parameters used to generate the reset dates schedule and associated fixing dates. The reset dates are determined relative to the calculation periods schedules dates.
 * 
 * <p>Classe Java pour ResetDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ResetDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationPeriodDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodDatesReference" minOccurs="0"/>
 *         &lt;element name="resetRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetRelativeToEnum" minOccurs="0"/>
 *         &lt;element name="initialFixingDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;element name="fixingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;element name="rateCutOffDaysOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}Offset" minOccurs="0"/>
 *         &lt;element name="resetFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}ResetFrequency"/>
 *         &lt;element name="resetDatesAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResetDates", propOrder = {
    "calculationPeriodDatesReference",
    "resetRelativeTo",
    "initialFixingDate",
    "fixingDates",
    "rateCutOffDaysOffset",
    "resetFrequency",
    "resetDatesAdjustments"
})
public class ResetDates {

    protected CalculationPeriodDatesReference calculationPeriodDatesReference;
    protected ResetRelativeToEnum resetRelativeTo;
    protected RelativeDateOffset initialFixingDate;
    protected RelativeDateOffset fixingDates;
    protected Offset rateCutOffDaysOffset;
    @XmlElement(required = true)
    protected ResetFrequency resetFrequency;
    protected BusinessDayAdjustments resetDatesAdjustments;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public CalculationPeriodDatesReference getCalculationPeriodDatesReference() {
        return calculationPeriodDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public void setCalculationPeriodDatesReference(CalculationPeriodDatesReference value) {
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
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getFixingDates() {
        return fixingDates;
    }

    /**
     * Définit la valeur de la propriété fixingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setFixingDates(RelativeDateOffset value) {
        this.fixingDates = value;
    }

    /**
     * Obtient la valeur de la propriété rateCutOffDaysOffset.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getRateCutOffDaysOffset() {
        return rateCutOffDaysOffset;
    }

    /**
     * Définit la valeur de la propriété rateCutOffDaysOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setRateCutOffDaysOffset(Offset value) {
        this.rateCutOffDaysOffset = value;
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
     * Obtient la valeur de la propriété resetDatesAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public BusinessDayAdjustments getResetDatesAdjustments() {
        return resetDatesAdjustments;
    }

    /**
     * Définit la valeur de la propriété resetDatesAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public void setResetDatesAdjustments(BusinessDayAdjustments value) {
        this.resetDatesAdjustments = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
