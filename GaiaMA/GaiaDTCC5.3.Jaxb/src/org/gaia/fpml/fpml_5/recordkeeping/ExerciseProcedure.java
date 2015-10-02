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
 * A type describing how notice of exercise should be given. This can be either manual or automatic.
 * 
 * <p>Classe Java pour ExerciseProcedure complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExerciseProcedure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="manualExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}ManualExercise" minOccurs="0"/>
 *           &lt;element name="automaticExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}AutomaticExercise" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="followUpConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="limitedRightToConfirm" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="splitTicket" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExerciseProcedure", propOrder = {
    "manualExercise",
    "automaticExercise",
    "followUpConfirmation",
    "limitedRightToConfirm",
    "splitTicket"
})
public class ExerciseProcedure {

    protected ManualExercise manualExercise;
    protected AutomaticExercise automaticExercise;
    protected Boolean followUpConfirmation;
    protected Boolean limitedRightToConfirm;
    protected Boolean splitTicket;

    /**
     * Obtient la valeur de la propriété manualExercise.
     * 
     * @return
     *     possible object is
     *     {@link ManualExercise }
     *     
     */
    public ManualExercise getManualExercise() {
        return manualExercise;
    }

    /**
     * Définit la valeur de la propriété manualExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link ManualExercise }
     *     
     */
    public void setManualExercise(ManualExercise value) {
        this.manualExercise = value;
    }

    /**
     * Obtient la valeur de la propriété automaticExercise.
     * 
     * @return
     *     possible object is
     *     {@link AutomaticExercise }
     *     
     */
    public AutomaticExercise getAutomaticExercise() {
        return automaticExercise;
    }

    /**
     * Définit la valeur de la propriété automaticExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link AutomaticExercise }
     *     
     */
    public void setAutomaticExercise(AutomaticExercise value) {
        this.automaticExercise = value;
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
     * Obtient la valeur de la propriété limitedRightToConfirm.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLimitedRightToConfirm() {
        return limitedRightToConfirm;
    }

    /**
     * Définit la valeur de la propriété limitedRightToConfirm.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLimitedRightToConfirm(Boolean value) {
        this.limitedRightToConfirm = value;
    }

    /**
     * Obtient la valeur de la propriété splitTicket.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSplitTicket() {
        return splitTicket;
    }

    /**
     * Définit la valeur de la propriété splitTicket.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSplitTicket(Boolean value) {
        this.splitTicket = value;
    }

}
