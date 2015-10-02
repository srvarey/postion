//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Base type for options starting with the 4-3 release, until we refactor the schema as part of the 5-0 release series.
 * 
 * <p>Classe Java pour OptionBaseExtended complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OptionBaseExtended">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}OptionBase">
 *       &lt;sequence>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}Premium"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}exercise"/>
 *         &lt;element name="exerciseProcedure" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseProcedure" minOccurs="0"/>
 *         &lt;element name="feature" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionFeature" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="notionalReference" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalAmountReference" minOccurs="0"/>
 *           &lt;element name="notionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionDenomination.model" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionSettlement.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionBaseExtended", propOrder = {
    "premium",
    "exercise",
    "exerciseProcedure",
    "feature",
    "notionalReference",
    "notionalAmount",
    "optionEntitlement",
    "entitlementCurrency",
    "numberOfOptions",
    "settlementType",
    "settlementDate",
    "settlementAmount",
    "settlementCurrency"
})
@XmlSeeAlso({
    CreditDefaultSwapOption.class,
    BondOption.class
})
public abstract class OptionBaseExtended
    extends OptionBase
{

    @XmlElement(required = true)
    protected Premium premium;
    @XmlElementRef(name = "exercise", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class)
    protected JAXBElement<? extends Exercise> exercise;
    protected ExerciseProcedure exerciseProcedure;
    protected OptionFeature feature;
    protected NotionalAmountReference notionalReference;
    protected Money notionalAmount;
    protected BigDecimal optionEntitlement;
    protected Currency entitlementCurrency;
    protected BigDecimal numberOfOptions;
    protected String settlementType;
    protected AdjustableOrRelativeDate settlementDate;
    protected Money settlementAmount;
    protected Currency settlementCurrency;

    /**
     * Obtient la valeur de la propriété premium.
     * 
     * @return
     *     possible object is
     *     {@link Premium }
     *     
     */
    public Premium getPremium() {
        return premium;
    }

    /**
     * Définit la valeur de la propriété premium.
     * 
     * @param value
     *     allowed object is
     *     {@link Premium }
     *     
     */
    public void setPremium(Premium value) {
        this.premium = value;
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
     * Obtient la valeur de la propriété feature.
     * 
     * @return
     *     possible object is
     *     {@link OptionFeature }
     *     
     */
    public OptionFeature getFeature() {
        return feature;
    }

    /**
     * Définit la valeur de la propriété feature.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionFeature }
     *     
     */
    public void setFeature(OptionFeature value) {
        this.feature = value;
    }

    /**
     * Obtient la valeur de la propriété notionalReference.
     * 
     * @return
     *     possible object is
     *     {@link NotionalAmountReference }
     *     
     */
    public NotionalAmountReference getNotionalReference() {
        return notionalReference;
    }

    /**
     * Définit la valeur de la propriété notionalReference.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalAmountReference }
     *     
     */
    public void setNotionalReference(NotionalAmountReference value) {
        this.notionalReference = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getNotionalAmount() {
        return notionalAmount;
    }

    /**
     * Définit la valeur de la propriété notionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setNotionalAmount(Money value) {
        this.notionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété optionEntitlement.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOptionEntitlement() {
        return optionEntitlement;
    }

    /**
     * Définit la valeur de la propriété optionEntitlement.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOptionEntitlement(BigDecimal value) {
        this.optionEntitlement = value;
    }

    /**
     * Obtient la valeur de la propriété entitlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getEntitlementCurrency() {
        return entitlementCurrency;
    }

    /**
     * Définit la valeur de la propriété entitlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setEntitlementCurrency(Currency value) {
        this.entitlementCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfOptions() {
        return numberOfOptions;
    }

    /**
     * Définit la valeur de la propriété numberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfOptions(BigDecimal value) {
        this.numberOfOptions = value;
    }

    /**
     * Obtient la valeur de la propriété settlementType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementType() {
        return settlementType;
    }

    /**
     * Définit la valeur de la propriété settlementType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementType(String value) {
        this.settlementType = value;
    }

    /**
     * Obtient la valeur de la propriété settlementDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * Définit la valeur de la propriété settlementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setSettlementDate(AdjustableOrRelativeDate value) {
        this.settlementDate = value;
    }

    /**
     * Obtient la valeur de la propriété settlementAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * Définit la valeur de la propriété settlementAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setSettlementAmount(Money value) {
        this.settlementAmount = value;
    }

    /**
     * Obtient la valeur de la propriété settlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Définit la valeur de la propriété settlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setSettlementCurrency(Currency value) {
        this.settlementCurrency = value;
    }

}
