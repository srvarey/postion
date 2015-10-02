//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The Payment Dates of the trade relative to the Calculation Periods.
 * 
 * <p>Classe Java pour CommodityRelativePaymentDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityRelativePaymentDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="payRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}PayRelativeToEnum" minOccurs="0"/>
 *           &lt;element name="payRelativeToEvent" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPayRelativeToEvent" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriodsPointer.model"/>
 *         &lt;element name="paymentDaysOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}DateOffset" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCentersOrReference.model" minOccurs="0"/>
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
@XmlType(name = "CommodityRelativePaymentDates", propOrder = {
    "payRelativeTo",
    "payRelativeToEvent",
    "calculationPeriodsReference",
    "calculationPeriodsScheduleReference",
    "calculationPeriodsDatesReference",
    "paymentDaysOffset",
    "businessCentersReference",
    "businessCenters"
})
public class CommodityRelativePaymentDates {

    protected PayRelativeToEnum payRelativeTo;
    protected CommodityPayRelativeToEvent payRelativeToEvent;
    protected CalculationPeriodsReference calculationPeriodsReference;
    protected CalculationPeriodsScheduleReference calculationPeriodsScheduleReference;
    protected CalculationPeriodsDatesReference calculationPeriodsDatesReference;
    protected DateOffset paymentDaysOffset;
    protected BusinessCentersReference businessCentersReference;
    protected BusinessCenters businessCenters;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété payRelativeTo.
     * 
     * @return
     *     possible object is
     *     {@link PayRelativeToEnum }
     *     
     */
    public PayRelativeToEnum getPayRelativeTo() {
        return payRelativeTo;
    }

    /**
     * Définit la valeur de la propriété payRelativeTo.
     * 
     * @param value
     *     allowed object is
     *     {@link PayRelativeToEnum }
     *     
     */
    public void setPayRelativeTo(PayRelativeToEnum value) {
        this.payRelativeTo = value;
    }

    /**
     * Obtient la valeur de la propriété payRelativeToEvent.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPayRelativeToEvent }
     *     
     */
    public CommodityPayRelativeToEvent getPayRelativeToEvent() {
        return payRelativeToEvent;
    }

    /**
     * Définit la valeur de la propriété payRelativeToEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPayRelativeToEvent }
     *     
     */
    public void setPayRelativeToEvent(CommodityPayRelativeToEvent value) {
        this.payRelativeToEvent = value;
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
     * Obtient la valeur de la propriété paymentDaysOffset.
     * 
     * @return
     *     possible object is
     *     {@link DateOffset }
     *     
     */
    public DateOffset getPaymentDaysOffset() {
        return paymentDaysOffset;
    }

    /**
     * Définit la valeur de la propriété paymentDaysOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link DateOffset }
     *     
     */
    public void setPaymentDaysOffset(DateOffset value) {
        this.paymentDaysOffset = value;
    }

    /**
     * Obtient la valeur de la propriété businessCentersReference.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCentersReference }
     *     
     */
    public BusinessCentersReference getBusinessCentersReference() {
        return businessCentersReference;
    }

    /**
     * Définit la valeur de la propriété businessCentersReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCentersReference }
     *     
     */
    public void setBusinessCentersReference(BusinessCentersReference value) {
        this.businessCentersReference = value;
    }

    /**
     * Obtient la valeur de la propriété businessCenters.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenters }
     *     
     */
    public BusinessCenters getBusinessCenters() {
        return businessCenters;
    }

    /**
     * Définit la valeur de la propriété businessCenters.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenters }
     *     
     */
    public void setBusinessCenters(BusinessCenters value) {
        this.businessCenters = value;
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
