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
import javax.xml.bind.annotation.XmlType;


/**
 * The quantity of gas to be delivered.
 * 
 * <p>Classe Java pour GasPhysicalQuantity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GasPhysicalQuantity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPhysicalQuantityBase">
 *       &lt;choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFixedPhysicalQuantity.model"/>
 *         &lt;sequence>
 *           &lt;element name="minPhysicalQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityNotionalQuantity" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="maxPhysicalQuantity" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityNotionalQuantity" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="electingParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GasPhysicalQuantity", propOrder = {
    "physicalQuantity",
    "physicalQuantitySchedule",
    "totalPhysicalQuantity",
    "minPhysicalQuantity",
    "maxPhysicalQuantity",
    "electingParty"
})
public class GasPhysicalQuantity
    extends CommodityPhysicalQuantityBase
{

    protected CommodityNotionalQuantity physicalQuantity;
    protected CommodityPhysicalQuantitySchedule physicalQuantitySchedule;
    protected UnitQuantity totalPhysicalQuantity;
    protected List<CommodityNotionalQuantity> minPhysicalQuantity;
    protected List<CommodityNotionalQuantity> maxPhysicalQuantity;
    protected PartyReference electingParty;

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
     * Gets the value of the minPhysicalQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the minPhysicalQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinPhysicalQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityNotionalQuantity }
     * 
     * 
     */
    public List<CommodityNotionalQuantity> getMinPhysicalQuantity() {
        if (minPhysicalQuantity == null) {
            minPhysicalQuantity = new ArrayList<CommodityNotionalQuantity>();
        }
        return this.minPhysicalQuantity;
    }

    /**
     * Gets the value of the maxPhysicalQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maxPhysicalQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaxPhysicalQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityNotionalQuantity }
     * 
     * 
     */
    public List<CommodityNotionalQuantity> getMaxPhysicalQuantity() {
        if (maxPhysicalQuantity == null) {
            maxPhysicalQuantity = new ArrayList<CommodityNotionalQuantity>();
        }
        return this.maxPhysicalQuantity;
    }

    /**
     * Obtient la valeur de la propriété electingParty.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getElectingParty() {
        return electingParty;
    }

    /**
     * Définit la valeur de la propriété electingParty.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setElectingParty(PartyReference value) {
        this.electingParty = value;
    }

}
