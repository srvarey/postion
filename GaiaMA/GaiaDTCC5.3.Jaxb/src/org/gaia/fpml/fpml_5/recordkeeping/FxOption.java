//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes an FX option with optional asian and barrier features.
 * 
 * <p>Classe Java pour FxOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Option">
 *       &lt;sequence>
 *         &lt;element name="effectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="tenorPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="americanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}FxAmericanExercise"/>
 *             &lt;element name="europeanExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}FxEuropeanExercise"/>
 *           &lt;/choice>
 *           &lt;element name="exerciseProcedure" type="{http://www.fpml.org/FpML-5/recordkeeping}ExerciseProcedure" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="putCurrencyAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney"/>
 *           &lt;element name="callCurrencyAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney"/>
 *         &lt;/sequence>
 *         &lt;element name="soldAs" type="{http://www.fpml.org/FpML-5/recordkeeping}PutCallEnum" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="strike" type="{http://www.fpml.org/FpML-5/recordkeeping}FxStrikePrice"/>
 *           &lt;element name="spotRate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="features" type="{http://www.fpml.org/FpML-5/recordkeeping}FxOptionFeatures" minOccurs="0"/>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}FxOptionPremium"/>
 *         &lt;element name="cashSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}FxCashSettlement" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxOption", propOrder = {
    "effectiveDate",
    "tenorPeriod",
    "americanExercise",
    "europeanExercise",
    "exerciseProcedure",
    "putCurrencyAmount",
    "callCurrencyAmount",
    "soldAs",
    "strike",
    "spotRate",
    "features",
    "premium",
    "cashSettlement"
})
public class FxOption
    extends Option
{

    protected AdjustableOrRelativeDate effectiveDate;
    protected Period tenorPeriod;
    protected FxAmericanExercise americanExercise;
    protected FxEuropeanExercise europeanExercise;
    protected ExerciseProcedure exerciseProcedure;
    @XmlElement(required = true)
    protected NonNegativeMoney putCurrencyAmount;
    @XmlElement(required = true)
    protected NonNegativeMoney callCurrencyAmount;
    protected PutCallEnum soldAs;
    @XmlElement(required = true)
    protected FxStrikePrice strike;
    protected BigDecimal spotRate;
    protected FxOptionFeatures features;
    @XmlElement(required = true)
    protected FxOptionPremium premium;
    protected FxCashSettlement cashSettlement;

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
     *     {@link FxAmericanExercise }
     *     
     */
    public FxAmericanExercise getAmericanExercise() {
        return americanExercise;
    }

    /**
     * Définit la valeur de la propriété americanExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link FxAmericanExercise }
     *     
     */
    public void setAmericanExercise(FxAmericanExercise value) {
        this.americanExercise = value;
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
     * Obtient la valeur de la propriété putCurrencyAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getPutCurrencyAmount() {
        return putCurrencyAmount;
    }

    /**
     * Définit la valeur de la propriété putCurrencyAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setPutCurrencyAmount(NonNegativeMoney value) {
        this.putCurrencyAmount = value;
    }

    /**
     * Obtient la valeur de la propriété callCurrencyAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getCallCurrencyAmount() {
        return callCurrencyAmount;
    }

    /**
     * Définit la valeur de la propriété callCurrencyAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setCallCurrencyAmount(NonNegativeMoney value) {
        this.callCurrencyAmount = value;
    }

    /**
     * Obtient la valeur de la propriété soldAs.
     * 
     * @return
     *     possible object is
     *     {@link PutCallEnum }
     *     
     */
    public PutCallEnum getSoldAs() {
        return soldAs;
    }

    /**
     * Définit la valeur de la propriété soldAs.
     * 
     * @param value
     *     allowed object is
     *     {@link PutCallEnum }
     *     
     */
    public void setSoldAs(PutCallEnum value) {
        this.soldAs = value;
    }

    /**
     * Obtient la valeur de la propriété strike.
     * 
     * @return
     *     possible object is
     *     {@link FxStrikePrice }
     *     
     */
    public FxStrikePrice getStrike() {
        return strike;
    }

    /**
     * Définit la valeur de la propriété strike.
     * 
     * @param value
     *     allowed object is
     *     {@link FxStrikePrice }
     *     
     */
    public void setStrike(FxStrikePrice value) {
        this.strike = value;
    }

    /**
     * Obtient la valeur de la propriété spotRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * Définit la valeur de la propriété spotRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpotRate(BigDecimal value) {
        this.spotRate = value;
    }

    /**
     * Obtient la valeur de la propriété features.
     * 
     * @return
     *     possible object is
     *     {@link FxOptionFeatures }
     *     
     */
    public FxOptionFeatures getFeatures() {
        return features;
    }

    /**
     * Définit la valeur de la propriété features.
     * 
     * @param value
     *     allowed object is
     *     {@link FxOptionFeatures }
     *     
     */
    public void setFeatures(FxOptionFeatures value) {
        this.features = value;
    }

    /**
     * Obtient la valeur de la propriété premium.
     * 
     * @return
     *     possible object is
     *     {@link FxOptionPremium }
     *     
     */
    public FxOptionPremium getPremium() {
        return premium;
    }

    /**
     * Définit la valeur de la propriété premium.
     * 
     * @param value
     *     allowed object is
     *     {@link FxOptionPremium }
     *     
     */
    public void setPremium(FxOptionPremium value) {
        this.premium = value;
    }

    /**
     * Obtient la valeur de la propriété cashSettlement.
     * 
     * @return
     *     possible object is
     *     {@link FxCashSettlement }
     *     
     */
    public FxCashSettlement getCashSettlement() {
        return cashSettlement;
    }

    /**
     * Définit la valeur de la propriété cashSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link FxCashSettlement }
     *     
     */
    public void setCashSettlement(FxCashSettlement value) {
        this.cashSettlement = value;
    }

}
