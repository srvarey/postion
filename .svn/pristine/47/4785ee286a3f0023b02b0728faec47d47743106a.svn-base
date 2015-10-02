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
 * Physically settled leg of a physically settled electricity transaction.
 * 
 * <p>Classe Java pour ElectricityPhysicalLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ElectricityPhysicalLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalSwapLeg">
 *       &lt;sequence>
 *         &lt;element name="deliveryPeriods" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPeriods" minOccurs="0"/>
 *         &lt;element name="settlementPeriods" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementPeriods" maxOccurs="unbounded"/>
 *         &lt;element name="settlementPeriodsSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementPeriodsSchedule" minOccurs="0"/>
 *         &lt;element name="electricity" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityProduct"/>
 *         &lt;element name="deliveryConditions" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityDelivery"/>
 *         &lt;element name="deliveryQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityPhysicalQuantity"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElectricityPhysicalLeg", propOrder = {
    "deliveryPeriods",
    "settlementPeriods",
    "settlementPeriodsSchedule",
    "electricity",
    "deliveryConditions",
    "deliveryQuantity"
})
public class ElectricityPhysicalLeg
    extends PhysicalSwapLeg
{

    protected CommodityDeliveryPeriods deliveryPeriods;
    @XmlElement(required = true)
    protected List<SettlementPeriods> settlementPeriods;
    protected SettlementPeriodsSchedule settlementPeriodsSchedule;
    @XmlElement(required = true)
    protected ElectricityProduct electricity;
    @XmlElement(required = true)
    protected ElectricityDelivery deliveryConditions;
    @XmlElement(required = true)
    protected ElectricityPhysicalQuantity deliveryQuantity;

    /**
     * Obtient la valeur de la propriété deliveryPeriods.
     * 
     * @return
     *     possible object is
     *     {@link CommodityDeliveryPeriods }
     *     
     */
    public CommodityDeliveryPeriods getDeliveryPeriods() {
        return deliveryPeriods;
    }

    /**
     * Définit la valeur de la propriété deliveryPeriods.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityDeliveryPeriods }
     *     
     */
    public void setDeliveryPeriods(CommodityDeliveryPeriods value) {
        this.deliveryPeriods = value;
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
     * Obtient la valeur de la propriété settlementPeriodsSchedule.
     * 
     * @return
     *     possible object is
     *     {@link SettlementPeriodsSchedule }
     *     
     */
    public SettlementPeriodsSchedule getSettlementPeriodsSchedule() {
        return settlementPeriodsSchedule;
    }

    /**
     * Définit la valeur de la propriété settlementPeriodsSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementPeriodsSchedule }
     *     
     */
    public void setSettlementPeriodsSchedule(SettlementPeriodsSchedule value) {
        this.settlementPeriodsSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété electricity.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityProduct }
     *     
     */
    public ElectricityProduct getElectricity() {
        return electricity;
    }

    /**
     * Définit la valeur de la propriété electricity.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityProduct }
     *     
     */
    public void setElectricity(ElectricityProduct value) {
        this.electricity = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryConditions.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityDelivery }
     *     
     */
    public ElectricityDelivery getDeliveryConditions() {
        return deliveryConditions;
    }

    /**
     * Définit la valeur de la propriété deliveryConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityDelivery }
     *     
     */
    public void setDeliveryConditions(ElectricityDelivery value) {
        this.deliveryConditions = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryQuantity.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityPhysicalQuantity }
     *     
     */
    public ElectricityPhysicalQuantity getDeliveryQuantity() {
        return deliveryQuantity;
    }

    /**
     * Définit la valeur de la propriété deliveryQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityPhysicalQuantity }
     *     
     */
    public void setDeliveryQuantity(ElectricityPhysicalQuantity value) {
        this.deliveryQuantity = value;
    }

}
