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
 * Physically settled leg of a physically settled coal transaction.
 * 
 * <p>Classe Java pour CoalPhysicalLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CoalPhysicalLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalSwapLeg">
 *       &lt;sequence>
 *         &lt;element name="deliveryPeriods" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPeriods" minOccurs="0"/>
 *         &lt;element name="coal" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalProduct"/>
 *         &lt;element name="deliveryConditions" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalDelivery"/>
 *         &lt;element name="deliveryQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPhysicalQuantity"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoalPhysicalLeg", propOrder = {
    "deliveryPeriods",
    "coal",
    "deliveryConditions",
    "deliveryQuantity"
})
public class CoalPhysicalLeg
    extends PhysicalSwapLeg
{

    protected CommodityDeliveryPeriods deliveryPeriods;
    @XmlElement(required = true)
    protected CoalProduct coal;
    @XmlElement(required = true)
    protected CoalDelivery deliveryConditions;
    @XmlElement(required = true)
    protected CommodityPhysicalQuantity deliveryQuantity;

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
     * Obtient la valeur de la propriété coal.
     * 
     * @return
     *     possible object is
     *     {@link CoalProduct }
     *     
     */
    public CoalProduct getCoal() {
        return coal;
    }

    /**
     * Définit la valeur de la propriété coal.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalProduct }
     *     
     */
    public void setCoal(CoalProduct value) {
        this.coal = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryConditions.
     * 
     * @return
     *     possible object is
     *     {@link CoalDelivery }
     *     
     */
    public CoalDelivery getDeliveryConditions() {
        return deliveryConditions;
    }

    /**
     * Définit la valeur de la propriété deliveryConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalDelivery }
     *     
     */
    public void setDeliveryConditions(CoalDelivery value) {
        this.deliveryConditions = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryQuantity.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPhysicalQuantity }
     *     
     */
    public CommodityPhysicalQuantity getDeliveryQuantity() {
        return deliveryQuantity;
    }

    /**
     * Définit la valeur de la propriété deliveryQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPhysicalQuantity }
     *     
     */
    public void setDeliveryQuantity(CommodityPhysicalQuantity value) {
        this.deliveryQuantity = value;
    }

}
