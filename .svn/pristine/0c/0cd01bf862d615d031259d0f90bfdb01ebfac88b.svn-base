//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Commodity Notional.
 * 
 * <p>Classe Java pour CommodityNotionalQuantity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityNotionalQuantity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quantityUnit" type="{http://www.fpml.org/FpML-5/recordkeeping}QuantityUnit"/>
 *         &lt;element name="quantityFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityQuantityFrequency" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlType(name = "CommodityNotionalQuantity", propOrder = {
    "quantityUnit",
    "quantityFrequency",
    "quantity"
})
@XmlSeeAlso({
    CommoditySettlementPeriodsNotionalQuantity.class,
    ElectricityPhysicalDeliveryQuantity.class
})
public class CommodityNotionalQuantity {

    @XmlElement(required = true)
    protected QuantityUnit quantityUnit;
    protected CommodityQuantityFrequency quantityFrequency;
    protected BigDecimal quantity;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété quantityUnit.
     * 
     * @return
     *     possible object is
     *     {@link QuantityUnit }
     *     
     */
    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    /**
     * Définit la valeur de la propriété quantityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityUnit }
     *     
     */
    public void setQuantityUnit(QuantityUnit value) {
        this.quantityUnit = value;
    }

    /**
     * Obtient la valeur de la propriété quantityFrequency.
     * 
     * @return
     *     possible object is
     *     {@link CommodityQuantityFrequency }
     *     
     */
    public CommodityQuantityFrequency getQuantityFrequency() {
        return quantityFrequency;
    }

    /**
     * Définit la valeur de la propriété quantityFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityQuantityFrequency }
     *     
     */
    public void setQuantityFrequency(CommodityQuantityFrequency value) {
        this.quantityFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété quantity.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantity(BigDecimal value) {
        this.quantity = value;
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
