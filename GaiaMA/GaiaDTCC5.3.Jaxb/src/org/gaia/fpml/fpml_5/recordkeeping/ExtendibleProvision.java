//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining an option to extend an existing swap transaction on the specified exercise dates for a term ending on the specified new termination date.
 * 
 * <p>Classe Java pour ExtendibleProvision complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExtendibleProvision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}exercise" minOccurs="0"/>
 *         &lt;element name="exerciseNotice" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseNotice" minOccurs="0"/>
 *         &lt;element name="followUpConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="extendibleProvisionAdjustedDates" type="{http://www.fpml.org/FpML-5/recordkeeping}ExtendibleProvisionAdjustedDates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendibleProvision", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "exercise",
    "exerciseNotice",
    "followUpConfirmation",
    "extendibleProvisionAdjustedDates"
})
public class ExtendibleProvision {

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    @XmlElementRef(name = "exercise", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Exercise> exercise;
    protected ExerciseNotice exerciseNotice;
    protected Boolean followUpConfirmation;
    protected ExtendibleProvisionAdjustedDates extendibleProvisionAdjustedDates;

    /**
     * Obtient la valeur de la propriété buyerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getBuyerPartyReference() {
        return buyerPartyReference;
    }

    /**
     * Définit la valeur de la propriété buyerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setBuyerPartyReference(PartyReference value) {
        this.buyerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété buyerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getBuyerAccountReference() {
        return buyerAccountReference;
    }

    /**
     * Définit la valeur de la propriété buyerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setBuyerAccountReference(AccountReference value) {
        this.buyerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getSellerPartyReference() {
        return sellerPartyReference;
    }

    /**
     * Définit la valeur de la propriété sellerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setSellerPartyReference(PartyReference value) {
        this.sellerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getSellerAccountReference() {
        return sellerAccountReference;
    }

    /**
     * Définit la valeur de la propriété sellerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setSellerAccountReference(AccountReference value) {
        this.sellerAccountReference = value;
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
     * Obtient la valeur de la propriété extendibleProvisionAdjustedDates.
     * 
     * @return
     *     possible object is
     *     {@link ExtendibleProvisionAdjustedDates }
     *     
     */
    public ExtendibleProvisionAdjustedDates getExtendibleProvisionAdjustedDates() {
        return extendibleProvisionAdjustedDates;
    }

    /**
     * Définit la valeur de la propriété extendibleProvisionAdjustedDates.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendibleProvisionAdjustedDates }
     *     
     */
    public void setExtendibleProvisionAdjustedDates(ExtendibleProvisionAdjustedDates value) {
        this.extendibleProvisionAdjustedDates = value;
    }

}
