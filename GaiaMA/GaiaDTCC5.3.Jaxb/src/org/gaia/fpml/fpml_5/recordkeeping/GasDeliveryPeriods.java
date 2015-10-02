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
 * The different options for specifying the Delivery Periods for a physically settled gas trade.
 * 
 * <p>Classe Java pour GasDeliveryPeriods complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GasDeliveryPeriods">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPeriods">
 *       &lt;sequence>
 *         &lt;element name="supplyStartTime" type="{http://www.fpml.org/FpML-5/recordkeeping}PrevailingTime"/>
 *         &lt;element name="supplyEndTime" type="{http://www.fpml.org/FpML-5/recordkeeping}PrevailingTime"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GasDeliveryPeriods", propOrder = {
    "supplyStartTime",
    "supplyEndTime"
})
public class GasDeliveryPeriods
    extends CommodityDeliveryPeriods
{

    @XmlElement(required = true)
    protected PrevailingTime supplyStartTime;
    @XmlElement(required = true)
    protected PrevailingTime supplyEndTime;

    /**
     * Obtient la valeur de la propriété supplyStartTime.
     * 
     * @return
     *     possible object is
     *     {@link PrevailingTime }
     *     
     */
    public PrevailingTime getSupplyStartTime() {
        return supplyStartTime;
    }

    /**
     * Définit la valeur de la propriété supplyStartTime.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevailingTime }
     *     
     */
    public void setSupplyStartTime(PrevailingTime value) {
        this.supplyStartTime = value;
    }

    /**
     * Obtient la valeur de la propriété supplyEndTime.
     * 
     * @return
     *     possible object is
     *     {@link PrevailingTime }
     *     
     */
    public PrevailingTime getSupplyEndTime() {
        return supplyEndTime;
    }

    /**
     * Définit la valeur de la propriété supplyEndTime.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevailingTime }
     *     
     */
    public void setSupplyEndTime(PrevailingTime value) {
        this.supplyEndTime = value;
    }

}
