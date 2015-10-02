//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * The physical delivery conditions for electricity.
 * 
 * <p>Classe Java pour ElectricityDelivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ElectricityDelivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="deliveryPoint" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityDeliveryPoint"/>
 *           &lt;element name="deliveryType" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityDeliveryType"/>
 *           &lt;element name="transmissionContingency" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityTransmissionContingency" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="deliveryZone" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPoint" minOccurs="0"/>
 *           &lt;element name="electingPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElectricityDelivery", propOrder = {
    "deliveryPoint",
    "deliveryType",
    "transmissionContingency",
    "deliveryZone",
    "electingPartyReference"
})
public class ElectricityDelivery {

    protected ElectricityDeliveryPoint deliveryPoint;
    protected ElectricityDeliveryType deliveryType;
    protected ElectricityTransmissionContingency transmissionContingency;
    protected CommodityDeliveryPoint deliveryZone;
    protected PartyReference electingPartyReference;

    /**
     * Obtient la valeur de la propriété deliveryPoint.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityDeliveryPoint }
     *     
     */
    public ElectricityDeliveryPoint getDeliveryPoint() {
        return deliveryPoint;
    }

    /**
     * Définit la valeur de la propriété deliveryPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityDeliveryPoint }
     *     
     */
    public void setDeliveryPoint(ElectricityDeliveryPoint value) {
        this.deliveryPoint = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryType.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityDeliveryType }
     *     
     */
    public ElectricityDeliveryType getDeliveryType() {
        return deliveryType;
    }

    /**
     * Définit la valeur de la propriété deliveryType.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityDeliveryType }
     *     
     */
    public void setDeliveryType(ElectricityDeliveryType value) {
        this.deliveryType = value;
    }

    /**
     * Obtient la valeur de la propriété transmissionContingency.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityTransmissionContingency }
     *     
     */
    public ElectricityTransmissionContingency getTransmissionContingency() {
        return transmissionContingency;
    }

    /**
     * Définit la valeur de la propriété transmissionContingency.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityTransmissionContingency }
     *     
     */
    public void setTransmissionContingency(ElectricityTransmissionContingency value) {
        this.transmissionContingency = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryZone.
     * 
     * @return
     *     possible object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public CommodityDeliveryPoint getDeliveryZone() {
        return deliveryZone;
    }

    /**
     * Définit la valeur de la propriété deliveryZone.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public void setDeliveryZone(CommodityDeliveryPoint value) {
        this.deliveryZone = value;
    }

    /**
     * Obtient la valeur de la propriété electingPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getElectingPartyReference() {
        return electingPartyReference;
    }

    /**
     * Définit la valeur de la propriété electingPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setElectingPartyReference(PartyReference value) {
        this.electingPartyReference = value;
    }

}
