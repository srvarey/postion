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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The dates on which prices are observed for the underlyer.
 * 
 * <p>Classe Java pour CommodityPricingDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityPricingDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriodsPointer.model"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="lag" type="{http://www.fpml.org/FpML-5/recordkeeping}Lag" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;sequence>
 *                 &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PricingDays.model"/>
 *                 &lt;element name="businessCalendar" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityBusinessCalendar" minOccurs="0"/>
 *               &lt;/sequence>
 *               &lt;element name="settlementPeriods" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementPeriods" maxOccurs="unbounded" minOccurs="0"/>
 *               &lt;element name="settlementPeriodsReference" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementPeriodsReference" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;/choice>
 *           &lt;/sequence>
 *           &lt;element name="pricingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDates" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityPricingDates", propOrder = {
    "calculationPeriodsReference",
    "calculationPeriodsScheduleReference",
    "calculationPeriodsDatesReference",
    "lag",
    "dayType",
    "dayDistribution",
    "dayCount",
    "dayOfWeek",
    "dayNumber",
    "businessCalendar",
    "settlementPeriods",
    "settlementPeriodsReference",
    "pricingDates"
})
public class CommodityPricingDates {

    protected CalculationPeriodsReference calculationPeriodsReference;
    protected CalculationPeriodsScheduleReference calculationPeriodsScheduleReference;
    protected CalculationPeriodsDatesReference calculationPeriodsDatesReference;
    protected Lag lag;
    protected String dayType;
    protected CommodityFrequencyType dayDistribution;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dayCount;
    protected List<DayOfWeekEnum> dayOfWeek;
    protected BigInteger dayNumber;
    protected CommodityBusinessCalendar businessCalendar;
    protected List<SettlementPeriods> settlementPeriods;
    protected List<SettlementPeriodsReference> settlementPeriodsReference;
    protected List<AdjustableDates> pricingDates;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

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
     * Obtient la valeur de la propriété businessCalendar.
     * 
     * @return
     *     possible object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public CommodityBusinessCalendar getBusinessCalendar() {
        return businessCalendar;
    }

    /**
     * Définit la valeur de la propriété businessCalendar.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public void setBusinessCalendar(CommodityBusinessCalendar value) {
        this.businessCalendar = value;
    }

    /**
     * Gets the value of the settlementPeriods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settlementPeriods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettlementPeriods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SettlementPeriods }
     * 
     * 
     */
    public List<SettlementPeriods> getSettlementPeriods() {
        if (settlementPeriods == null) {
            settlementPeriods = new ArrayList<SettlementPeriods>();
        }
        return this.settlementPeriods;
    }

    /**
     * Gets the value of the settlementPeriodsReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settlementPeriodsReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettlementPeriodsReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SettlementPeriodsReference }
     * 
     * 
     */
    public List<SettlementPeriodsReference> getSettlementPeriodsReference() {
        if (settlementPeriodsReference == null) {
            settlementPeriodsReference = new ArrayList<SettlementPeriodsReference>();
        }
        return this.settlementPeriodsReference;
    }

    /**
     * Gets the value of the pricingDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricingDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricingDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjustableDates }
     * 
     * 
     */
    public List<AdjustableDates> getPricingDates() {
        if (pricingDates == null) {
            pricingDates = new ArrayList<AdjustableDates>();
        }
        return this.pricingDates;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
