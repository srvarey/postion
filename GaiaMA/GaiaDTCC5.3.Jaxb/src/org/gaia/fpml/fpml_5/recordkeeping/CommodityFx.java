//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the FX observations to be used to convert the observed Commodity Reference Price to the Settlement Currency. The rate source must be specified. Additionally, a time for the spot price to be observed on that source may be specified, or else an averaging schedule for trades priced using an average FX rate.
 * 
 * <p>Classe Java pour CommodityFx complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityFx">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="secondaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="fxType" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFxType" minOccurs="0"/>
 *         &lt;element name="averagingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingMethodEnum" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="fxObservationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDates" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;sequence>
 *               &lt;sequence minOccurs="0">
 *                 &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PricingDays.model"/>
 *                 &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}LagOrReference.model" minOccurs="0"/>
 *               &lt;/sequence>
 *               &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriodsPointer.model"/>
 *             &lt;/sequence>
 *           &lt;/choice>
 *           &lt;element name="fixingTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityFx", propOrder = {
    "primaryRateSource",
    "secondaryRateSource",
    "fxType",
    "averagingMethod",
    "fxObservationDates",
    "dayType",
    "dayDistribution",
    "dayCount",
    "dayOfWeek",
    "dayNumber",
    "lag",
    "lagReference",
    "calculationPeriodsReference",
    "calculationPeriodsScheduleReference",
    "calculationPeriodsDatesReference",
    "fixingTime"
})
public class CommodityFx {

    protected InformationSource primaryRateSource;
    protected InformationSource secondaryRateSource;
    protected CommodityFxType fxType;
    protected AveragingMethodEnum averagingMethod;
    protected List<AdjustableDates> fxObservationDates;
    protected String dayType;
    protected CommodityFrequencyType dayDistribution;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dayCount;
    protected List<DayOfWeekEnum> dayOfWeek;
    protected BigInteger dayNumber;
    protected Lag lag;
    protected LagReference lagReference;
    protected CalculationPeriodsReference calculationPeriodsReference;
    protected CalculationPeriodsScheduleReference calculationPeriodsScheduleReference;
    protected CalculationPeriodsDatesReference calculationPeriodsDatesReference;
    protected BusinessCenterTime fixingTime;

    /**
     * Obtient la valeur de la propriété primaryRateSource.
     * 
     * @return
     *     possible object is
     *     {@link InformationSource }
     *     
     */
    public InformationSource getPrimaryRateSource() {
        return primaryRateSource;
    }

    /**
     * Définit la valeur de la propriété primaryRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationSource }
     *     
     */
    public void setPrimaryRateSource(InformationSource value) {
        this.primaryRateSource = value;
    }

    /**
     * Obtient la valeur de la propriété secondaryRateSource.
     * 
     * @return
     *     possible object is
     *     {@link InformationSource }
     *     
     */
    public InformationSource getSecondaryRateSource() {
        return secondaryRateSource;
    }

    /**
     * Définit la valeur de la propriété secondaryRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationSource }
     *     
     */
    public void setSecondaryRateSource(InformationSource value) {
        this.secondaryRateSource = value;
    }

    /**
     * Obtient la valeur de la propriété fxType.
     * 
     * @return
     *     possible object is
     *     {@link CommodityFxType }
     *     
     */
    public CommodityFxType getFxType() {
        return fxType;
    }

    /**
     * Définit la valeur de la propriété fxType.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityFxType }
     *     
     */
    public void setFxType(CommodityFxType value) {
        this.fxType = value;
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
     * Gets the value of the fxObservationDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxObservationDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFxObservationDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjustableDates }
     * 
     * 
     */
    public List<AdjustableDates> getFxObservationDates() {
        if (fxObservationDates == null) {
            fxObservationDates = new ArrayList<AdjustableDates>();
        }
        return this.fxObservationDates;
    }

    /**
     * Obtient la valeur de la propriété dayType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayType() {
        return dayType;
    }

    /**
     * Définit la valeur de la propriété dayType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayType(String value) {
        this.dayType = value;
    }

    /**
     * Obtient la valeur de la propriété dayDistribution.
     * 
     * @return
     *     possible object is
     *     {@link CommodityFrequencyType }
     *     
     */
    public CommodityFrequencyType getDayDistribution() {
        return dayDistribution;
    }

    /**
     * Définit la valeur de la propriété dayDistribution.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityFrequencyType }
     *     
     */
    public void setDayDistribution(CommodityFrequencyType value) {
        this.dayDistribution = value;
    }

    /**
     * Obtient la valeur de la propriété dayCount.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDayCount() {
        return dayCount;
    }

    /**
     * Définit la valeur de la propriété dayCount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDayCount(BigInteger value) {
        this.dayCount = value;
    }

    /**
     * Gets the value of the dayOfWeek property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dayOfWeek property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDayOfWeek().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DayOfWeekEnum }
     * 
     * 
     */
    public List<DayOfWeekEnum> getDayOfWeek() {
        if (dayOfWeek == null) {
            dayOfWeek = new ArrayList<DayOfWeekEnum>();
        }
        return this.dayOfWeek;
    }

    /**
     * Obtient la valeur de la propriété dayNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDayNumber() {
        return dayNumber;
    }

    /**
     * Définit la valeur de la propriété dayNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDayNumber(BigInteger value) {
        this.dayNumber = value;
    }

    /**
     * Obtient la valeur de la propriété lag.
     * 
     * @return
     *     possible object is
     *     {@link Lag }
     *     
     */
    public Lag getLag() {
        return lag;
    }

    /**
     * Définit la valeur de la propriété lag.
     * 
     * @param value
     *     allowed object is
     *     {@link Lag }
     *     
     */
    public void setLag(Lag value) {
        this.lag = value;
    }

    /**
     * Obtient la valeur de la propriété lagReference.
     * 
     * @return
     *     possible object is
     *     {@link LagReference }
     *     
     */
    public LagReference getLagReference() {
        return lagReference;
    }

    /**
     * Définit la valeur de la propriété lagReference.
     * 
     * @param value
     *     allowed object is
     *     {@link LagReference }
     *     
     */
    public void setLagReference(LagReference value) {
        this.lagReference = value;
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
     * Obtient la valeur de la propriété fixingTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getFixingTime() {
        return fixingTime;
    }

    /**
     * Définit la valeur de la propriété fixingTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setFixingTime(BusinessCenterTime value) {
        this.fixingTime = value;
    }

}
