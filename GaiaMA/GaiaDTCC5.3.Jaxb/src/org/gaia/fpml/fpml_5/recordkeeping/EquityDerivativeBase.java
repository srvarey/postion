//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type for defining the common features of equity derivatives.
 * 
 * <p>Classe Java pour EquityDerivativeBase complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityDerivativeBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element name="optionType" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityOptionTypeEnum" minOccurs="0"/>
 *         &lt;element name="equityEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="underlyer" type="{http://www.fpml.org/FpML-5/recordkeeping}Underlyer" minOccurs="0"/>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *         &lt;element name="equityExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityExerciseValuationSettlement" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Feature.model" minOccurs="0"/>
 *         &lt;element name="strategyFeature" type="{http://www.fpml.org/FpML-5/recordkeeping}StrategyFeature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityDerivativeBase", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "optionType",
    "equityEffectiveDate",
    "underlyer",
    "notional",
    "equityExercise",
    "feature",
    "fxFeature",
    "strategyFeature"
})
@XmlSeeAlso({
    EquityDerivativeLongFormBase.class,
    EquityDerivativeShortFormBase.class
})
public abstract class EquityDerivativeBase
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    protected String optionType;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar equityEffectiveDate;
    protected Underlyer underlyer;
    protected NonNegativeMoney notional;
    protected EquityExerciseValuationSettlement equityExercise;
    protected OptionFeatures feature;
    protected FxFeature fxFeature;
    protected StrategyFeature strategyFeature;

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
     * Obtient la valeur de la propriété equityEffectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEquityEffectiveDate() {
        return equityEffectiveDate;
    }

    /**
     * Définit la valeur de la propriété equityEffectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEquityEffectiveDate(XMLGregorianCalendar value) {
        this.equityEffectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété underlyer.
     * 
     * @return
     *     possible object is
     *     {@link Underlyer }
     *     
     */
    public Underlyer getUnderlyer() {
        return underlyer;
    }

    /**
     * Définit la valeur de la propriété underlyer.
     * 
     * @param value
     *     allowed object is
     *     {@link Underlyer }
     *     
     */
    public void setUnderlyer(Underlyer value) {
        this.underlyer = value;
    }

    /**
     * Obtient la valeur de la propriété notional.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getNotional() {
        return notional;
    }

    /**
     * Définit la valeur de la propriété notional.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setNotional(NonNegativeMoney value) {
        this.notional = value;
    }

    /**
     * Obtient la valeur de la propriété equityExercise.
     * 
     * @return
     *     possible object is
     *     {@link EquityExerciseValuationSettlement }
     *     
     */
    public EquityExerciseValuationSettlement getEquityExercise() {
        return equityExercise;
    }

    /**
     * Définit la valeur de la propriété equityExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityExerciseValuationSettlement }
     *     
     */
    public void setEquityExercise(EquityExerciseValuationSettlement value) {
        this.equityExercise = value;
    }

    /**
     * Obtient la valeur de la propriété feature.
     * 
     * @return
     *     possible object is
     *     {@link OptionFeatures }
     *     
     */
    public OptionFeatures getFeature() {
        return feature;
    }

    /**
     * Définit la valeur de la propriété feature.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionFeatures }
     *     
     */
    public void setFeature(OptionFeatures value) {
        this.feature = value;
    }

    /**
     * Obtient la valeur de la propriété fxFeature.
     * 
     * @return
     *     possible object is
     *     {@link FxFeature }
     *     
     */
    public FxFeature getFxFeature() {
        return fxFeature;
    }

    /**
     * Définit la valeur de la propriété fxFeature.
     * 
     * @param value
     *     allowed object is
     *     {@link FxFeature }
     *     
     */
    public void setFxFeature(FxFeature value) {
        this.fxFeature = value;
    }

    /**
     * Obtient la valeur de la propriété strategyFeature.
     * 
     * @return
     *     possible object is
     *     {@link StrategyFeature }
     *     
     */
    public StrategyFeature getStrategyFeature() {
        return strategyFeature;
    }

    /**
     * Définit la valeur de la propriété strategyFeature.
     * 
     * @param value
     *     allowed object is
     *     {@link StrategyFeature }
     *     
     */
    public void setStrategyFeature(StrategyFeature value) {
        this.strategyFeature = value;
    }

}
