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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The different options for specifying the Delivery Periods of a physical leg.
 * 
 * <p>Classe Java pour CommodityDeliveryPeriods complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityDeliveryPeriods">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="periods" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDates" minOccurs="0"/>
 *         &lt;element name="periodsSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriodsSchedule" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityCalculationPeriodsPointer.model"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityDeliveryPeriods", propOrder = {
    "periods",
    "periodsSchedule",
    "calculationPeriodsReference",
    "calculationPeriodsScheduleReference",
    "calculationPeriodsDatesReference"
})
@XmlSeeAlso({
    ElectricityDeliveryPeriods.class,
    GasDeliveryPeriods.class
})
public class CommodityDeliveryPeriods {

    protected AdjustableDates periods;
    protected CommodityCalculationPeriodsSchedule periodsSchedule;
    protected CalculationPeriodsReference calculationPeriodsReference;
    protected CalculationPeriodsScheduleReference calculationPeriodsScheduleReference;
    protected CalculationPeriodsDatesReference calculationPeriodsDatesReference;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété periods.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDates }
     *     
     */
    public AdjustableDates getPeriods() {
        return periods;
    }

    /**
     * Définit la valeur de la propriété periods.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDates }
     *     
     */
    public void setPeriods(AdjustableDates value) {
        this.periods = value;
    }

    /**
     * Obtient la valeur de la propriété periodsSchedule.
     * 
     * @return
     *     possible object is
     *     {@link CommodityCalculationPeriodsSchedule }
     *     
     */
    public CommodityCalculationPeriodsSchedule getPeriodsSchedule() {
        return periodsSchedule;
    }

    /**
     * Définit la valeur de la propriété periodsSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityCalculationPeriodsSchedule }
     *     
     */
    public void setPeriodsSchedule(CommodityCalculationPeriodsSchedule value) {
        this.periodsSchedule = value;
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
