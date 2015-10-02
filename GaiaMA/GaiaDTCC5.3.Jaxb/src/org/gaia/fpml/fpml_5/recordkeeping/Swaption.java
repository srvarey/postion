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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type to define an option on a swap.
 * 
 * <p>Classe Java pour Swaption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Swaption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="optionType" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionTypeEnum" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}exercise"/>
 *         &lt;element name="exerciseProcedure" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseProcedure" minOccurs="0"/>
 *         &lt;element name="calculationAgent" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationAgent" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="cashSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}CashSettlement" minOccurs="0"/>
 *           &lt;element name="physicalSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}SwaptionPhysicalSettlement" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="swaptionStraddle" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="swaptionAdjustedDates" type="{http://www.fpml.org/FpML-5/recordkeeping}SwaptionAdjustedDates" minOccurs="0"/>
 *         &lt;element name="swap" type="{http://www.fpml.org/FpML-5/recordkeeping}Swap"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Swaption", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "premium",
    "optionType",
    "exercise",
    "exerciseProcedure",
    "calculationAgent",
    "cashSettlement",
    "physicalSettlement",
    "swaptionStraddle",
    "swaptionAdjustedDates",
    "swap"
})
public class Swaption
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    protected List<Payment> premium;
    protected String optionType;
    @XmlElementRef(name = "exercise", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class)
    protected JAXBElement<? extends Exercise> exercise;
    protected ExerciseProcedure exerciseProcedure;
    protected CalculationAgent calculationAgent;
    protected CashSettlement cashSettlement;
    protected SwaptionPhysicalSettlement physicalSettlement;
    protected boolean swaptionStraddle;
    protected SwaptionAdjustedDates swaptionAdjustedDates;
    @XmlElement(required = true)
    protected Swap swap;

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
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getPremium() {
        if (premium == null) {
            premium = new ArrayList<Payment>();
        }
        return this.premium;
    }

    /**
     * Obtient la valeur de la propriété optionType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionType() {
        return optionType;
    }

    /**
     * Définit la valeur de la propriété optionType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionType(String value) {
        this.optionType = value;
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
     * Obtient la valeur de la propriété physicalSettlement.
     * 
     * @return
     *     possible object is
     *     {@link SwaptionPhysicalSettlement }
     *     
     */
    public SwaptionPhysicalSettlement getPhysicalSettlement() {
        return physicalSettlement;
    }

    /**
     * Définit la valeur de la propriété physicalSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link SwaptionPhysicalSettlement }
     *     
     */
    public void setPhysicalSettlement(SwaptionPhysicalSettlement value) {
        this.physicalSettlement = value;
    }

    /**
     * Obtient la valeur de la propriété swaptionStraddle.
     * 
     */
    public boolean isSwaptionStraddle() {
        return swaptionStraddle;
    }

    /**
     * Définit la valeur de la propriété swaptionStraddle.
     * 
     */
    public void setSwaptionStraddle(boolean value) {
        this.swaptionStraddle = value;
    }

    /**
     * Obtient la valeur de la propriété swaptionAdjustedDates.
     * 
     * @return
     *     possible object is
     *     {@link SwaptionAdjustedDates }
     *     
     */
    public SwaptionAdjustedDates getSwaptionAdjustedDates() {
        return swaptionAdjustedDates;
    }

    /**
     * Définit la valeur de la propriété swaptionAdjustedDates.
     * 
     * @param value
     *     allowed object is
     *     {@link SwaptionAdjustedDates }
     *     
     */
    public void setSwaptionAdjustedDates(SwaptionAdjustedDates value) {
        this.swaptionAdjustedDates = value;
    }

    /**
     * Obtient la valeur de la propriété swap.
     * 
     * @return
     *     possible object is
     *     {@link Swap }
     *     
     */
    public Swap getSwap() {
        return swap;
    }

    /**
     * Définit la valeur de la propriété swap.
     * 
     * @param value
     *     allowed object is
     *     {@link Swap }
     *     
     */
    public void setSwap(Swap value) {
        this.swap = value;
    }

}
