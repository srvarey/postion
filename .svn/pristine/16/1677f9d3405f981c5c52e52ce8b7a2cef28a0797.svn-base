//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The quality attributes of the coal to be delivered, specified on a periodic basis.
 * 
 * <p>Classe Java pour CoalStandardQualitySchedule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CoalStandardQualitySchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StandardQualityStep" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalStandardQuality" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPeriodsPointer.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoalStandardQualitySchedule", propOrder = {
    "standardQualityStep",
    "deliveryPeriodsReference",
    "deliveryPeriodsScheduleReference"
})
public class CoalStandardQualitySchedule {

    @XmlElement(name = "StandardQualityStep")
    protected List<CoalStandardQuality> standardQualityStep;
    protected CalculationPeriodsReference deliveryPeriodsReference;
    protected CalculationPeriodsScheduleReference deliveryPeriodsScheduleReference;

    /**
     * Gets the value of the standardQualityStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardQualityStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardQualityStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoalStandardQuality }
     * 
     * 
     */
    public List<CoalStandardQuality> getStandardQualityStep() {
        if (standardQualityStep == null) {
            standardQualityStep = new ArrayList<CoalStandardQuality>();
        }
        return this.standardQualityStep;
    }

    /**
     * Obtient la valeur de la propriété deliveryPeriodsReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodsReference }
     *     
     */
    public CalculationPeriodsReference getDeliveryPeriodsReference() {
        return deliveryPeriodsReference;
    }

    /**
     * Définit la valeur de la propriété deliveryPeriodsReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodsReference }
     *     
     */
    public void setDeliveryPeriodsReference(CalculationPeriodsReference value) {
        this.deliveryPeriodsReference = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryPeriodsScheduleReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodsScheduleReference }
     *     
     */
    public CalculationPeriodsScheduleReference getDeliveryPeriodsScheduleReference() {
        return deliveryPeriodsScheduleReference;
    }

    /**
     * Définit la valeur de la propriété deliveryPeriodsScheduleReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodsScheduleReference }
     *     
     */
    public void setDeliveryPeriodsScheduleReference(CalculationPeriodsScheduleReference value) {
        this.deliveryPeriodsScheduleReference = value;
    }

}
