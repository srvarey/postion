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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Floating Price Leg of a Commodity Swap.
 * 
 * <p>Classe Java pour FloatingPriceLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FloatingPriceLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}FinancialSwapLeg">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriods.model"/>
 *         &lt;element name="commodity" type="{http://www.fpml.org/FpML-5/recordkeeping}Commodity"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityNotionalQuantity.model"/>
 *         &lt;element name="calculation" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingLegCalculation"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPaymentDates.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFreightFlatRate.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatingPriceLeg", propOrder = {
    "calculationDates",
    "calculationPeriods",
    "calculationPeriodsSchedule",
    "calculationPeriodsReference",
    "calculationPeriodsScheduleReference",
    "calculationPeriodsDatesReference",
    "commodity",
    "notionalQuantitySchedule",
    "notionalQuantity",
    "settlementPeriodsNotionalQuantity",
    "totalNotionalQuantity",
    "quantityReference",
    "calculation",
    "relativePaymentDates",
    "paymentDates",
    "masterAgreementPaymentDates",
    "flatRate",
    "flatRateAmount"
})
public class FloatingPriceLeg
    extends FinancialSwapLeg
{

    protected AdjustableDates calculationDates;
    protected AdjustableDates calculationPeriods;
    protected CommodityCalculationPeriodsSchedule calculationPeriodsSchedule;
    protected CalculationPeriodsReference calculationPeriodsReference;
    protected CalculationPeriodsScheduleReference calculationPeriodsScheduleReference;
    protected CalculationPeriodsDatesReference calculationPeriodsDatesReference;
    @XmlElement(required = true)
    protected Commodity commodity;
    protected CommodityNotionalQuantitySchedule notionalQuantitySchedule;
    protected CommodityNotionalQuantity notionalQuantity;
    protected List<CommoditySettlementPeriodsNotionalQuantity> settlementPeriodsNotionalQuantity;
    protected BigDecimal totalNotionalQuantity;
    protected QuantityReference quantityReference;
    @XmlElement(required = true)
    protected FloatingLegCalculation calculation;
    protected CommodityRelativePaymentDates relativePaymentDates;
    protected AdjustableDatesOrRelativeDateOffset paymentDates;
    protected Boolean masterAgreementPaymentDates;
    protected FlatRateEnum flatRate;
    protected NonNegativeMoney flatRateAmount;

    /**
     * Obtient la valeur de la propriété calculationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDates }
     *     
     */
    public AdjustableDates getCalculationDates() {
        return calculationDates;
    }

    /**
     * Définit la valeur de la propriété calculationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDates }
     *     
     */
    public void setCalculationDates(AdjustableDates value) {
        this.calculationDates = value;
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
     * Obtient la valeur de la propriété calculationPeriodsReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodsReference }
     *     
     */
    public CalculationPeriodsReference getCalculationPeriodsReference() {
        return calculationPeriodsReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodsReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodsReference }
     *     
     */
    public void setCalculationPeriodsReference(CalculationPeriodsReference value) {
        this.calculationPeriodsReference = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodsScheduleReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodsScheduleReference }
     *     
     */
    public CalculationPeriodsScheduleReference getCalculationPeriodsScheduleReference() {
        return calculationPeriodsScheduleReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodsScheduleReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodsScheduleReference }
     *     
     */
    public void setCalculationPeriodsScheduleReference(CalculationPeriodsScheduleReference value) {
        this.calculationPeriodsScheduleReference = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodsDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodsDatesReference }
     *     
     */
    public CalculationPeriodsDatesReference getCalculationPeriodsDatesReference() {
        return calculationPeriodsDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodsDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodsDatesReference }
     *     
     */
    public void setCalculationPeriodsDatesReference(CalculationPeriodsDatesReference value) {
        this.calculationPeriodsDatesReference = value;
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
     * Obtient la valeur de la propriété calculation.
     * 
     * @return
     *     possible object is
     *     {@link FloatingLegCalculation }
     *     
     */
    public FloatingLegCalculation getCalculation() {
        return calculation;
    }

    /**
     * Définit la valeur de la propriété calculation.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingLegCalculation }
     *     
     */
    public void setCalculation(FloatingLegCalculation value) {
        this.calculation = value;
    }

    /**
     * Obtient la valeur de la propriété relativePaymentDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityRelativePaymentDates }
     *     
     */
    public CommodityRelativePaymentDates getRelativePaymentDates() {
        return relativePaymentDates;
    }

    /**
     * Définit la valeur de la propriété relativePaymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityRelativePaymentDates }
     *     
     */
    public void setRelativePaymentDates(CommodityRelativePaymentDates value) {
        this.relativePaymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public AdjustableDatesOrRelativeDateOffset getPaymentDates() {
        return paymentDates;
    }

    /**
     * Définit la valeur de la propriété paymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public void setPaymentDates(AdjustableDatesOrRelativeDateOffset value) {
        this.paymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété masterAgreementPaymentDates.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMasterAgreementPaymentDates() {
        return masterAgreementPaymentDates;
    }

    /**
     * Définit la valeur de la propriété masterAgreementPaymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMasterAgreementPaymentDates(Boolean value) {
        this.masterAgreementPaymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété flatRate.
     * 
     * @return
     *     possible object is
     *     {@link FlatRateEnum }
     *     
     */
    public FlatRateEnum getFlatRate() {
        return flatRate;
    }

    /**
     * Définit la valeur de la propriété flatRate.
     * 
     * @param value
     *     allowed object is
     *     {@link FlatRateEnum }
     *     
     */
    public void setFlatRate(FlatRateEnum value) {
        this.flatRate = value;
    }

    /**
     * Obtient la valeur de la propriété flatRateAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getFlatRateAmount() {
        return flatRateAmount;
    }

    /**
     * Définit la valeur de la propriété flatRateAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setFlatRateAmount(NonNegativeMoney value) {
        this.flatRateAmount = value;
    }

}
