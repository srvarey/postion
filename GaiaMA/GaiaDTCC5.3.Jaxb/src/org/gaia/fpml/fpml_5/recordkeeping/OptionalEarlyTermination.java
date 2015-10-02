//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining an early termination provision where either or both parties have the right to exercise.
 * 
 * <p>Classe Java pour OptionalEarlyTermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OptionalEarlyTermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singlePartyOption" type="{http://www.fpml.org/FpML-5/recordkeeping}SinglePartyOption" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}exercise" minOccurs="0"/>
 *         &lt;element name="exerciseNotice" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseNotice" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="followUpConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="calculationAgent" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationAgent" minOccurs="0"/>
 *         &lt;element name="cashSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}CashSettlement" minOccurs="0"/>
 *         &lt;element name="optionalEarlyTerminationAdjustedDates" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionalEarlyTerminationAdjustedDates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionalEarlyTermination", propOrder = {
    "singlePartyOption",
    "exercise",
    "exerciseNotice",
    "followUpConfirmation",
    "calculationAgent",
    "cashSettlement",
    "optionalEarlyTerminationAdjustedDates"
})
public class OptionalEarlyTermination {

    protected SinglePartyOption singlePartyOption;
    @XmlElementRef(name = "exercise", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Exercise> exercise;
    protected List<ExerciseNotice> exerciseNotice;
    protected Boolean followUpConfirmation;
    protected CalculationAgent calculationAgent;
    protected CashSettlement cashSettlement;
    protected OptionalEarlyTerminationAdjustedDates optionalEarlyTerminationAdjustedDates;

    /**
     * Obtient la valeur de la propriété singlePartyOption.
     * 
     * @return
     *     possible object is
     *     {@link SinglePartyOption }
     *     
     */
    public SinglePartyOption getSinglePartyOption() {
        return singlePartyOption;
    }

    /**
     * Définit la valeur de la propriété singlePartyOption.
     * 
     * @param value
     *     allowed object is
     *     {@link SinglePartyOption }
     *     
     */
    public void setSinglePartyOption(SinglePartyOption value) {
        this.singlePartyOption = value;
    }

    /**
     * Obtient la valeur de la propriété exercise.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BermudaExercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link AmericanExercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link Exercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link EuropeanExercise }{@code >}
     *     
     */
    public JAXBElement<? extends Exercise> getExercise() {
        return exercise;
    }

    /**
     * Définit la valeur de la propriété exercise.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BermudaExercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link AmericanExercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link Exercise }{@code >}
     *     {@link JAXBElement }{@code <}{@link EuropeanExercise }{@code >}
     *     
     */
    public void setExercise(JAXBElement<? extends Exercise> value) {
        this.exercise = value;
    }

    /**
     * Gets the value of the exerciseNotice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exerciseNotice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExerciseNotice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExerciseNotice }
     * 
     * 
     */
    public List<ExerciseNotice> getExerciseNotice() {
        if (exerciseNotice == null) {
            exerciseNotice = new ArrayList<ExerciseNotice>();
        }
        return this.exerciseNotice;
    }

    /**
     * Obtient la valeur de la propriété followUpConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFollowUpConfirmation() {
        return followUpConfirmation;
    }

    /**
     * Définit la valeur de la propriété followUpConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFollowUpConfirmation(Boolean value) {
        this.followUpConfirmation = value;
    }

    /**
     * Obtient la valeur de la propriété calculationAgent.
     * 
     * @return
     *     possible object is
     *     {@link CalculationAgent }
     *     
     */
    public CalculationAgent getCalculationAgent() {
        return calculationAgent;
    }

    /**
     * Définit la valeur de la propriété calculationAgent.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationAgent }
     *     
     */
    public void setCalculationAgent(CalculationAgent value) {
        this.calculationAgent = value;
    }

    /**
     * Obtient la valeur de la propriété cashSettlement.
     * 
     * @return
     *     possible object is
     *     {@link CashSettlement }
     *     
     */
    public CashSettlement getCashSettlement() {
        return cashSettlement;
    }

    /**
     * Définit la valeur de la propriété cashSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link CashSettlement }
     *     
     */
    public void setCashSettlement(CashSettlement value) {
        this.cashSettlement = value;
    }

    /**
     * Obtient la valeur de la propriété optionalEarlyTerminationAdjustedDates.
     * 
     * @return
     *     possible object is
     *     {@link OptionalEarlyTerminationAdjustedDates }
     *     
     */
    public OptionalEarlyTerminationAdjustedDates getOptionalEarlyTerminationAdjustedDates() {
        return optionalEarlyTerminationAdjustedDates;
    }

    /**
     * Définit la valeur de la propriété optionalEarlyTerminationAdjustedDates.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionalEarlyTerminationAdjustedDates }
     *     
     */
    public void setOptionalEarlyTerminationAdjustedDates(OptionalEarlyTerminationAdjustedDates value) {
        this.optionalEarlyTerminationAdjustedDates = value;
    }

}
