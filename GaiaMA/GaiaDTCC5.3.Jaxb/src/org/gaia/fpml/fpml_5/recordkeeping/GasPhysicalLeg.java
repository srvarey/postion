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
 * Physically settled leg of a physically settled gas transaction.
 * 
 * <p>Classe Java pour GasPhysicalLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GasPhysicalLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalSwapLeg">
 *       &lt;sequence>
 *         &lt;element name="deliveryPeriods" type="{http://www.fpml.org/FpML-5/recordkeeping}GasDeliveryPeriods"/>
 *         &lt;element name="gas" type="{http://www.fpml.org/FpML-5/recordkeeping}GasProduct"/>
 *         &lt;element name="deliveryConditions" type="{http://www.fpml.org/FpML-5/recordkeeping}GasDelivery" minOccurs="0"/>
 *         &lt;element name="deliveryQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}GasPhysicalQuantity"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GasPhysicalLeg", propOrder = {
    "deliveryPeriods",
    "gas",
    "deliveryConditions",
    "deliveryQuantity"
})
public class GasPhysicalLeg
    extends PhysicalSwapLeg
{

    @XmlElement(required = true)
    protected GasDeliveryPeriods deliveryPeriods;
    @XmlElement(required = true)
    protected GasProduct gas;
    protected GasDelivery deliveryConditions;
    @XmlElement(required = true)
    protected GasPhysicalQuantity deliveryQuantity;

    /**
     * Obtient la valeur de la propriété deliveryPeriods.
     * 
     * @return
     *     possible object is
     *     {@link GasDeliveryPeriods }
     *     
     */
    public GasDeliveryPeriods getDeliveryPeriods() {
        return deliveryPeriods;
    }

    /**
     * Définit la valeur de la propriété deliveryPeriods.
     * 
     * @param value
     *     allowed object is
     *     {@link GasDeliveryPeriods }
     *     
     */
    public void setDeliveryPeriods(GasDeliveryPeriods value) {
        this.deliveryPeriods = value;
    }

    /**
     * Obtient la valeur de la propriété gas.
     * 
     * @return
     *     possible object is
     *     {@link GasProduct }
     *     
     */
    public GasProduct getGas() {
        return gas;
    }

    /**
     * Définit la valeur de la propriété gas.
     * 
     * @param value
     *     allowed object is
     *     {@link GasProduct }
     *     
     */
    public void setGas(GasProduct value) {
        this.gas = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryConditions.
     * 
     * @return
     *     possible object is
     *     {@link GasDelivery }
     *     
     */
    public GasDelivery getDeliveryConditions() {
        return deliveryConditions;
    }

    /**
     * Définit la valeur de la propriété deliveryConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link GasDelivery }
     *     
     */
    public void setDeliveryConditions(GasDelivery value) {
        this.deliveryConditions = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryQuantity.
     * 
     * @return
     *     possible object is
     *     {@link GasPhysicalQuantity }
     *     
     */
    public GasPhysicalQuantity getDeliveryQuantity() {
        return deliveryQuantity;
    }

    /**
     * Définit la valeur de la propriété deliveryQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link GasPhysicalQuantity }
     *     
     */
    public void setDeliveryQuantity(GasPhysicalQuantity value) {
        this.deliveryQuantity = value;
    }

}
