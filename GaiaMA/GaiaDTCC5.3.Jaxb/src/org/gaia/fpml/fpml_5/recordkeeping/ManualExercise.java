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
 * A type defining manual exercise, i.e. that the option buyer counterparty must give notice to the option seller of exercise.
 * 
 * <p>Classe Java pour ManualExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ManualExercise">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exerciseNotice" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseNotice" minOccurs="0"/>
 *         &lt;element name="fallbackExercise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManualExercise", propOrder = {
    "exerciseNotice",
    "fallbackExercise"
})
public class ManualExercise {

    protected ExerciseNotice exerciseNotice;
    protected Boolean fallbackExercise;

    /**
     * Obtient la valeur de la propriété exerciseNotice.
     * 
     * @return
     *     possible object is
     *     {@link ExerciseNotice }
     *     
     */
    public ExerciseNotice getExerciseNotice() {
        return exerciseNotice;
    }

    /**
     * Définit la valeur de la propriété exerciseNotice.
     * 
     * @param value
     *     allowed object is
     *     {@link ExerciseNotice }
     *     
     */
    public void setExerciseNotice(ExerciseNotice value) {
        this.exerciseNotice = value;
    }

    /**
     * Obtient la valeur de la propriété fallbackExercise.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFallbackExercise() {
        return fallbackExercise;
    }

    /**
     * Définit la valeur de la propriété fallbackExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFallbackExercise(Boolean value) {
        this.fallbackExercise = value;
    }

}
