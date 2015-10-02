//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes an option having a triggerable fixed payout.
 * 
 * <p>Classe Java pour FxDigitalOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxDigitalOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Option">
 *       &lt;sequence>
 *         &lt;element name="effectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="tenorPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;sequence>
 *               &lt;element name="americanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}FxDigitalAmericanExercise" minOccurs="0"/>
 *               &lt;element name="touch" type="{http://www.fpml.org/FpML-5/recordkeeping}FxTouch" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;/sequence>
 *             &lt;sequence>
 *               &lt;element name="europeanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}FxEuropeanExercise" minOccurs="0"/>
 *               &lt;element name="trigger" type="{http://www.fpml.org/FpML-5/recordkeeping}FxTrigger" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;/sequence>
 *           &lt;/choice>
 *           &lt;element name="exerciseProcedure" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseProcedure" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="payout" type="{http://www.fpml.org/FpML-5/recordkeeping}FxOptionPayout" minOccurs="0"/>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}FxOptionPremium" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxDigitalOption", propOrder = {
    "effectiveDate",
    "tenorPeriod",
    "americanExercise",
    "touch",
    "europeanExercise",
    "trigger",
    "exerciseProcedure",
    "payout",
    "premium"
})
public class FxDigitalOption
    extends Option
{

    protected AdjustableOrRelativeDate effectiveDate;
    protected Period tenorPeriod;
    protected FxDigitalAmericanExercise americanExercise;
    protected List<FxTouch> touch;
    protected FxEuropeanExercise europeanExercise;
    protected List<FxTrigger> trigger;
    protected ExerciseProcedure exerciseProcedure;
    protected FxOptionPayout payout;
    protected List<FxOptionPremium> premium;

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setEffectiveDate(AdjustableOrRelativeDate value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété tenorPeriod.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getTenorPeriod() {
        return tenorPeriod;
    }

    /**
     * Définit la valeur de la propriété tenorPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setTenorPeriod(Period value) {
        this.tenorPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété americanExercise.
     * 
     * @return
     *     possible object is
     *     {@link FxDigitalAmericanExercise }
     *     
     */
    public FxDigitalAmericanExercise getAmericanExercise() {
        return americanExercise;
    }

    /**
     * Définit la valeur de la propriété americanExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link FxDigitalAmericanExercise }
     *     
     */
    public void setAmericanExercise(FxDigitalAmericanExercise value) {
        this.americanExercise = value;
    }

    /**
     * Gets the value of the touch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the touch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTouch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxTouch }
     * 
     * 
     */
    public List<FxTouch> getTouch() {
        if (touch == null) {
            touch = new ArrayList<FxTouch>();
        }
        return this.touch;
    }

    /**
     * Obtient la valeur de la propriété europeanExercise.
     * 
     * @return
     *     possible object is
     *     {@link FxEuropeanExercise }
     *     
     */
    public FxEuropeanExercise getEuropeanExercise() {
        return europeanExercise;
    }

    /**
     * Définit la valeur de la propriété europeanExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link FxEuropeanExercise }
     *     
     */
    public void setEuropeanExercise(FxEuropeanExercise value) {
        this.europeanExercise = value;
    }

    /**
     * Gets the value of the trigger property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trigger property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrigger().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxTrigger }
     * 
     * 
     */
    public List<FxTrigger> getTrigger() {
        if (trigger == null) {
            trigger = new ArrayList<FxTrigger>();
        }
        return this.trigger;
    }

    /**
     * Obtient la valeur de la propriété exerciseProcedure.
     * 
     * @return
     *     possible object is
     *     {@link ExerciseProcedure }
     *     
     */
    public ExerciseProcedure getExerciseProcedure() {
        return exerciseProcedure;
    }

    /**
     * Définit la valeur de la propriété exerciseProcedure.
     * 
     * @param value
     *     allowed object is
     *     {@link ExerciseProcedure }
     *     
     */
    public void setExerciseProcedure(ExerciseProcedure value) {
        this.exerciseProcedure = value;
    }

    /**
     * Obtient la valeur de la propriété payout.
     * 
     * @return
     *     possible object is
     *     {@link FxOptionPayout }
     *     
     */
    public FxOptionPayout getPayout() {
        return payout;
    }

    /**
     * Définit la valeur de la propriété payout.
     * 
     * @param value
     *     allowed object is
     *     {@link FxOptionPayout }
     *     
     */
    public void setPayout(FxOptionPayout value) {
        this.payout = value;
    }

    /**
     * Gets the value of the premium property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premium property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremium().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxOptionPremium }
     * 
     * 
     */
    public List<FxOptionPremium> getPremium() {
        if (premium == null) {
            premium = new ArrayList<FxOptionPremium>();
        }
        return this.premium;
    }

}
