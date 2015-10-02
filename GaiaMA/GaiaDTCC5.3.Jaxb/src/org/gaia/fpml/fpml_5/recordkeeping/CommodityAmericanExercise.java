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
 * A type for defining exercise procedures associated with an American style exercise of a commodity option.
 * 
 * <p>Classe Java pour CommodityAmericanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityAmericanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="exercisePeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityExercisePeriods" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="exerciseFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}Frequency" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="latestExerciseTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *           &lt;element name="latestExerciseTimeDetermination" type="{http://www.fpml.org/FpML-5/recordkeeping}DeterminationMethod" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="expirationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="multipleExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityMultipleExercise" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityAmericanExercise", propOrder = {
    "exercisePeriod",
    "exerciseFrequency",
    "latestExerciseTime",
    "latestExerciseTimeDetermination",
    "expirationTime",
    "multipleExercise"
})
public class CommodityAmericanExercise
    extends Exercise
{

    protected List<CommodityExercisePeriods> exercisePeriod;
    protected Frequency exerciseFrequency;
    protected BusinessCenterTime latestExerciseTime;
    protected DeterminationMethod latestExerciseTimeDetermination;
    protected BusinessCenterTime expirationTime;
    protected CommodityMultipleExercise multipleExercise;

    /**
     * Gets the value of the exercisePeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exercisePeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExercisePeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityExercisePeriods }
     * 
     * 
     */
    public List<CommodityExercisePeriods> getExercisePeriod() {
        if (exercisePeriod == null) {
            exercisePeriod = new ArrayList<CommodityExercisePeriods>();
        }
        return this.exercisePeriod;
    }

    /**
     * Obtient la valeur de la propriété exerciseFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Frequency }
     *     
     */
    public Frequency getExerciseFrequency() {
        return exerciseFrequency;
    }

    /**
     * Définit la valeur de la propriété exerciseFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Frequency }
     *     
     */
    public void setExerciseFrequency(Frequency value) {
        this.exerciseFrequency = value;
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
     * Obtient la valeur de la propriété latestExerciseTimeDetermination.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethod }
     *     
     */
    public DeterminationMethod getLatestExerciseTimeDetermination() {
        return latestExerciseTimeDetermination;
    }

    /**
     * Définit la valeur de la propriété latestExerciseTimeDetermination.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethod }
     *     
     */
    public void setLatestExerciseTimeDetermination(DeterminationMethod value) {
        this.latestExerciseTimeDetermination = value;
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
     *     {@link CommodityMultipleExercise }
     *     
     */
    public CommodityMultipleExercise getMultipleExercise() {
        return multipleExercise;
    }

    /**
     * Définit la valeur de la propriété multipleExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityMultipleExercise }
     *     
     */
    public void setMultipleExercise(CommodityMultipleExercise value) {
        this.multipleExercise = value;
    }

}
