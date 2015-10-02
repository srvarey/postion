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
 * A type defining the Bermuda option exercise dates and the expiration date together with any rules govenerning the notional amount of the underlying which can be exercised on any given exercise date and any associated exercise fee.
 * 
 * <p>Classe Java pour BermudaExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BermudaExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;element name="bermudaExerciseDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDates" minOccurs="0"/>
 *         &lt;element name="relevantUnderlyingDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDates" minOccurs="0"/>
 *         &lt;element name="earliestExerciseTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="latestExerciseTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="expirationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="multipleExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}MultipleExercise" minOccurs="0"/>
 *         &lt;element name="exerciseFeeSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseFeeSchedule" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BermudaExercise", propOrder = {
    "bermudaExerciseDates",
    "relevantUnderlyingDate",
    "earliestExerciseTime",
    "latestExerciseTime",
    "expirationTime",
    "multipleExercise",
    "exerciseFeeSchedule"
})
public class BermudaExercise
    extends Exercise
{

    protected AdjustableOrRelativeDates bermudaExerciseDates;
    protected AdjustableOrRelativeDates relevantUnderlyingDate;
    protected BusinessCenterTime earliestExerciseTime;
    protected BusinessCenterTime latestExerciseTime;
    protected BusinessCenterTime expirationTime;
    protected MultipleExercise multipleExercise;
    protected ExerciseFeeSchedule exerciseFeeSchedule;

    /**
     * Obtient la valeur de la propriété bermudaExerciseDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public AdjustableOrRelativeDates getBermudaExerciseDates() {
        return bermudaExerciseDates;
    }

    /**
     * Définit la valeur de la propriété bermudaExerciseDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public void setBermudaExerciseDates(AdjustableOrRelativeDates value) {
        this.bermudaExerciseDates = value;
    }

    /**
     * Obtient la valeur de la propriété relevantUnderlyingDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public AdjustableOrRelativeDates getRelevantUnderlyingDate() {
        return relevantUnderlyingDate;
    }

    /**
     * Définit la valeur de la propriété relevantUnderlyingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public void setRelevantUnderlyingDate(AdjustableOrRelativeDates value) {
        this.relevantUnderlyingDate = value;
    }

    /**
     * Obtient la valeur de la propriété earliestExerciseTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getEarliestExerciseTime() {
        return earliestExerciseTime;
    }

    /**
     * Définit la valeur de la propriété earliestExerciseTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setEarliestExerciseTime(BusinessCenterTime value) {
        this.earliestExerciseTime = value;
    }

    /**
     * Obtient la valeur de la propriété latestExerciseTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getLatestExerciseTime() {
        return latestExerciseTime;
    }

    /**
     * Définit la valeur de la propriété latestExerciseTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setLatestExerciseTime(BusinessCenterTime value) {
        this.latestExerciseTime = value;
    }

    /**
     * Obtient la valeur de la propriété expirationTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getExpirationTime() {
        return expirationTime;
    }

    /**
     * Définit la valeur de la propriété expirationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setExpirationTime(BusinessCenterTime value) {
        this.expirationTime = value;
    }

    /**
     * Obtient la valeur de la propriété multipleExercise.
     * 
     * @return
     *     possible object is
     *     {@link MultipleExercise }
     *     
     */
    public MultipleExercise getMultipleExercise() {
        return multipleExercise;
    }

    /**
     * Définit la valeur de la propriété multipleExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleExercise }
     *     
     */
    public void setMultipleExercise(MultipleExercise value) {
        this.multipleExercise = value;
    }

    /**
     * Obtient la valeur de la propriété exerciseFeeSchedule.
     * 
     * @return
     *     possible object is
     *     {@link ExerciseFeeSchedule }
     *     
     */
    public ExerciseFeeSchedule getExerciseFeeSchedule() {
        return exerciseFeeSchedule;
    }

    /**
     * Définit la valeur de la propriété exerciseFeeSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link ExerciseFeeSchedule }
     *     
     */
    public void setExerciseFeeSchedule(ExerciseFeeSchedule value) {
        this.exerciseFeeSchedule = value;
    }

}
