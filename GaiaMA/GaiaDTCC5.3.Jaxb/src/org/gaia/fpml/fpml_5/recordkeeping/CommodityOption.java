//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Commodity Option.
 * 
 * <p>Classe Java pour CommodityOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element name="optionType" type="{http://www.fpml.org/FpML-5/recordkeeping}PutCallEnum" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFinancialOption.model"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPhysicalOption.model"/>
 *         &lt;/choice>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPremium" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityContent.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityOption", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "optionType",
    "commodity",
    "effectiveDate",
    "calculationPeriodsSchedule",
    "calculationPeriods",
    "pricingDates",
    "averagingMethod",
    "notionalQuantitySchedule",
    "notionalQuantity",
    "settlementPeriodsNotionalQuantity",
    "totalNotionalQuantity",
    "quantityReference",
    "exercise",
    "strikePricePerUnit",
    "strikePricePerUnitSchedule",
    "commoditySwap",
    "commodityForward",
    "physicalExercise",
    "premium",
    "commonPricing",
    "marketDisruption",
    "settlementDisruption",
    "rounding"
})
public class CommodityOption
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    protected PutCallEnum optionType;
    protected Commodity commodity;
    protected AdjustableOrRelativeDate effectiveDate;
    protected CommodityCalculationPeriodsSchedule calculationPeriodsSchedule;
    protected AdjustableDates calculationPeriods;
    protected CommodityPricingDates pricingDates;
    protected AveragingMethodEnum averagingMethod;
    protected CommodityNotionalQuantitySchedule notionalQuantitySchedule;
    protected CommodityNotionalQuantity notionalQuantity;
    protected List<CommoditySettlementPeriodsNotionalQuantity> settlementPeriodsNotionalQuantity;
    protected BigDecimal totalNotionalQuantity;
    protected QuantityReference quantityReference;
    protected CommodityExercise exercise;
    protected NonNegativeMoney strikePricePerUnit;
    protected CommodityStrikeSchedule strikePricePerUnitSchedule;
    protected CommoditySwap commoditySwap;
    protected CommodityForward commodityForward;
    protected CommodityPhysicalExercise physicalExercise;
    protected List<CommodityPremium> premium;
    protected Boolean commonPricing;
    protected CommodityMarketDisruption marketDisruption;
    protected CommodityBullionSettlementDisruptionEnum settlementDisruption;
    protected Rounding rounding;

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
     *     {@link PutCallEnum }
     *     
     */
    public PutCallEnum getOptionType() {
        return optionType;
    }

    /**
     * Définit la valeur de la propriété optionType.
     * 
     * @param value
     *     allowed object is
     *     {@link PutCallEnum }
     *     
     */
    public void setOptionType(PutCallEnum value) {
        this.optionType = value;
    }

    /**
     * Obtient la valeur de la propriété commodity.
     * 
     * @return
     *     possible object is
     *     {@link Commodity }
     *     
     */
    public Commodity getCommodity() {
        return commodity;
    }

    /**
     * Définit la valeur de la propriété commodity.
     * 
     * @param value
     *     allowed object is
     *     {@link Commodity }
     *     
     */
    public void setCommodity(Commodity value) {
        this.commodity = value;
    }

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
     * Obtient la valeur de la propriété calculationPeriodsSchedule.
     * 
     * @return
     *     possible object is
     *     {@link CommodityCalculationPeriodsSchedule }
     *     
     */
    public CommodityCalculationPeriodsSchedule getCalculationPeriodsSchedule() {
        return calculationPeriodsSchedule;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodsSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityCalculationPeriodsSchedule }
     *     
     */
    public void setCalculationPeriodsSchedule(CommodityCalculationPeriodsSchedule value) {
        this.calculationPeriodsSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriods.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDates }
     *     
     */
    public AdjustableDates getCalculationPeriods() {
        return calculationPeriods;
    }

    /**
     * Définit la valeur de la propriété calculationPeriods.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDates }
     *     
     */
    public void setCalculationPeriods(AdjustableDates value) {
        this.calculationPeriods = value;
    }

    /**
     * Obtient la valeur de la propriété pricingDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPricingDates }
     *     
     */
    public CommodityPricingDates getPricingDates() {
        return pricingDates;
    }

    /**
     * Définit la valeur de la propriété pricingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPricingDates }
     *     
     */
    public void setPricingDates(CommodityPricingDates value) {
        this.pricingDates = value;
    }

    /**
     * Obtient la valeur de la propriété averagingMethod.
     * 
     * @return
     *     possible object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public AveragingMethodEnum getAveragingMethod() {
        return averagingMethod;
    }

    /**
     * Définit la valeur de la propriété averagingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public void setAveragingMethod(AveragingMethodEnum value) {
        this.averagingMethod = value;
    }

    /**
     * Obtient la valeur de la propriété notionalQuantitySchedule.
     * 
     * @return
     *     possible object is
     *     {@link CommodityNotionalQuantitySchedule }
     *     
     */
    public CommodityNotionalQuantitySchedule getNotionalQuantitySchedule() {
        return notionalQuantitySchedule;
    }

    /**
     * Définit la valeur de la propriété notionalQuantitySchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityNotionalQuantitySchedule }
     *     
     */
    public void setNotionalQuantitySchedule(CommodityNotionalQuantitySchedule value) {
        this.notionalQuantitySchedule = value;
    }

    /**
     * Obtient la valeur de la propriété notionalQuantity.
     * 
     * @return
     *     possible object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public CommodityNotionalQuantity getNotionalQuantity() {
        return notionalQuantity;
    }

    /**
     * Définit la valeur de la propriété notionalQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public void setNotionalQuantity(CommodityNotionalQuantity value) {
        this.notionalQuantity = value;
    }

    /**
     * Gets the value of the settlementPeriodsNotionalQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settlementPeriodsNotionalQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettlementPeriodsNotionalQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommoditySettlementPeriodsNotionalQuantity }
     * 
     * 
     */
    public List<CommoditySettlementPeriodsNotionalQuantity> getSettlementPeriodsNotionalQuantity() {
        if (settlementPeriodsNotionalQuantity == null) {
            settlementPeriodsNotionalQuantity = new ArrayList<CommoditySettlementPeriodsNotionalQuantity>();
        }
        return this.settlementPeriodsNotionalQuantity;
    }

    /**
     * Obtient la valeur de la propriété totalNotionalQuantity.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalNotionalQuantity() {
        return totalNotionalQuantity;
    }

    /**
     * Définit la valeur de la propriété totalNotionalQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalNotionalQuantity(BigDecimal value) {
        this.totalNotionalQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété quantityReference.
     * 
     * @return
     *     possible object is
     *     {@link QuantityReference }
     *     
     */
    public QuantityReference getQuantityReference() {
        return quantityReference;
    }

    /**
     * Définit la valeur de la propriété quantityReference.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityReference }
     *     
     */
    public void setQuantityReference(QuantityReference value) {
        this.quantityReference = value;
    }

    /**
     * Obtient la valeur de la propriété exercise.
     * 
     * @return
     *     possible object is
     *     {@link CommodityExercise }
     *     
     */
    public CommodityExercise getExercise() {
        return exercise;
    }

    /**
     * Définit la valeur de la propriété exercise.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityExercise }
     *     
     */
    public void setExercise(CommodityExercise value) {
        this.exercise = value;
    }

    /**
     * Obtient la valeur de la propriété strikePricePerUnit.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getStrikePricePerUnit() {
        return strikePricePerUnit;
    }

    /**
     * Définit la valeur de la propriété strikePricePerUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setStrikePricePerUnit(NonNegativeMoney value) {
        this.strikePricePerUnit = value;
    }

    /**
     * Obtient la valeur de la propriété strikePricePerUnitSchedule.
     * 
     * @return
     *     possible object is
     *     {@link CommodityStrikeSchedule }
     *     
     */
    public CommodityStrikeSchedule getStrikePricePerUnitSchedule() {
        return strikePricePerUnitSchedule;
    }

    /**
     * Définit la valeur de la propriété strikePricePerUnitSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityStrikeSchedule }
     *     
     */
    public void setStrikePricePerUnitSchedule(CommodityStrikeSchedule value) {
        this.strikePricePerUnitSchedule = value;
    }

    /**
     * DEPRICATED. Defines a commodity swap product.
     * 
     * @return
     *     possible object is
     *     {@link CommoditySwap }
     *     
     */
    public CommoditySwap getCommoditySwap() {
        return commoditySwap;
    }

    /**
     * Définit la valeur de la propriété commoditySwap.
     * 
     * @param value
     *     allowed object is
     *     {@link CommoditySwap }
     *     
     */
    public void setCommoditySwap(CommoditySwap value) {
        this.commoditySwap = value;
    }

    /**
     * Obtient la valeur de la propriété commodityForward.
     * 
     * @return
     *     possible object is
     *     {@link CommodityForward }
     *     
     */
    public CommodityForward getCommodityForward() {
        return commodityForward;
    }

    /**
     * Définit la valeur de la propriété commodityForward.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityForward }
     *     
     */
    public void setCommodityForward(CommodityForward value) {
        this.commodityForward = value;
    }

    /**
     * Obtient la valeur de la propriété physicalExercise.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPhysicalExercise }
     *     
     */
    public CommodityPhysicalExercise getPhysicalExercise() {
        return physicalExercise;
    }

    /**
     * Définit la valeur de la propriété physicalExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPhysicalExercise }
     *     
     */
    public void setPhysicalExercise(CommodityPhysicalExercise value) {
        this.physicalExercise = value;
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
     * {@link CommodityPremium }
     * 
     * 
     */
    public List<CommodityPremium> getPremium() {
        if (premium == null) {
            premium = new ArrayList<CommodityPremium>();
        }
        return this.premium;
    }

    /**
     * Obtient la valeur de la propriété commonPricing.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommonPricing() {
        return commonPricing;
    }

    /**
     * Définit la valeur de la propriété commonPricing.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommonPricing(Boolean value) {
        this.commonPricing = value;
    }

    /**
     * Obtient la valeur de la propriété marketDisruption.
     * 
     * @return
     *     possible object is
     *     {@link CommodityMarketDisruption }
     *     
     */
    public CommodityMarketDisruption getMarketDisruption() {
        return marketDisruption;
    }

    /**
     * Définit la valeur de la propriété marketDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityMarketDisruption }
     *     
     */
    public void setMarketDisruption(CommodityMarketDisruption value) {
        this.marketDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété settlementDisruption.
     * 
     * @return
     *     possible object is
     *     {@link CommodityBullionSettlementDisruptionEnum }
     *     
     */
    public CommodityBullionSettlementDisruptionEnum getSettlementDisruption() {
        return settlementDisruption;
    }

    /**
     * Définit la valeur de la propriété settlementDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityBullionSettlementDisruptionEnum }
     *     
     */
    public void setSettlementDisruption(CommodityBullionSettlementDisruptionEnum value) {
        this.settlementDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété rounding.
     * 
     * @return
     *     possible object is
     *     {@link Rounding }
     *     
     */
    public Rounding getRounding() {
        return rounding;
    }

    /**
     * Définit la valeur de la propriété rounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Rounding }
     *     
     */
    public void setRounding(Rounding value) {
        this.rounding = value;
    }

}
