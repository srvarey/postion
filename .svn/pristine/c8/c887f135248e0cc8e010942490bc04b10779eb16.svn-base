//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Physically settled leg of a physically settled Bullion Transaction.
 * 
 * <p>Classe Java pour BullionPhysicalLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BullionPhysicalLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalForwardLeg">
 *       &lt;sequence>
 *         &lt;element name="bullionType" type="{http://www.fpml.org/FpML-5/recordkeeping}BullionTypeEnum" minOccurs="0"/>
 *         &lt;element name="deliveryLocation" type="{http://www.fpml.org/FpML-5/recordkeeping}BullionDeliveryLocation" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFixedPhysicalQuantity.model"/>
 *         &lt;element name="settlementDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BullionPhysicalLeg", propOrder = {
    "bullionType",
    "deliveryLocation",
    "physicalQuantity",
    "physicalQuantitySchedule",
    "totalPhysicalQuantity",
    "settlementDate"
})
public class BullionPhysicalLeg
    extends PhysicalForwardLeg
{

    protected BullionTypeEnum bullionType;
    protected BullionDeliveryLocation deliveryLocation;
    protected CommodityNotionalQuantity physicalQuantity;
    protected CommodityPhysicalQuantitySchedule physicalQuantitySchedule;
    @XmlElement(required = true)
    protected UnitQuantity totalPhysicalQuantity;
    protected AdjustableOrRelativeDate settlementDate;

    /**
     * Obtient la valeur de la propriété bullionType.
     * 
     * @return
     *     possible object is
     *     {@link BullionTypeEnum }
     *     
     */
    public BullionTypeEnum getBullionType() {
        return bullionType;
    }

    /**
     * Définit la valeur de la propriété bullionType.
     * 
     * @param value
     *     allowed object is
     *     {@link BullionTypeEnum }
     *     
     */
    public void setBullionType(BullionTypeEnum value) {
        this.bullionType = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryLocation.
     * 
     * @return
     *     possible object is
     *     {@link BullionDeliveryLocation }
     *     
     */
    public BullionDeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Définit la valeur de la propriété deliveryLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link BullionDeliveryLocation }
     *     
     */
    public void setDeliveryLocation(BullionDeliveryLocation value) {
        this.deliveryLocation = value;
    }

    /**
     * Obtient la valeur de la propriété physicalQuantity.
     * 
     * @return
     *     possible object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public CommodityNotionalQuantity getPhysicalQuantity() {
        return physicalQuantity;
    }

    /**
     * Définit la valeur de la propriété physicalQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityNotionalQuantity }
     *     
     */
    public void setPhysicalQuantity(CommodityNotionalQuantity value) {
        this.physicalQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété physicalQuantitySchedule.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPhysicalQuantitySchedule }
     *     
     */
    public CommodityPhysicalQuantitySchedule getPhysicalQuantitySchedule() {
        return physicalQuantitySchedule;
    }

    /**
     * Définit la valeur de la propriété physicalQuantitySchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPhysicalQuantitySchedule }
     *     
     */
    public void setPhysicalQuantitySchedule(CommodityPhysicalQuantitySchedule value) {
        this.physicalQuantitySchedule = value;
    }

    /**
     * Obtient la valeur de la propriété totalPhysicalQuantity.
     * 
     * @return
     *     possible object is
     *     {@link UnitQuantity }
     *     
     */
    public UnitQuantity getTotalPhysicalQuantity() {
        return totalPhysicalQuantity;
    }

    /**
     * Définit la valeur de la propriété totalPhysicalQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitQuantity }
     *     
     */
    public void setTotalPhysicalQuantity(UnitQuantity value) {
        this.totalPhysicalQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété settlementDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * Définit la valeur de la propriété settlementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setSettlementDate(AdjustableOrRelativeDate value) {
        this.settlementDate = value;
    }

}
