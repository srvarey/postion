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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A definition of a shift with respect to a specific pricing parameter.
 * 
 * <p>Classe Java pour PricingParameterShift complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PricingParameterShift">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameterReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetOrTermPointOrPricingStructureReference" minOccurs="0"/>
 *         &lt;element name="shift" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="shiftUnits" type="{http://www.fpml.org/FpML-5/recordkeeping}PriceQuoteUnits" minOccurs="0"/>
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
@XmlType(name = "PricingParameterShift", propOrder = {
    "parameterReference",
    "shift",
    "shiftUnits"
})
public class PricingParameterShift {

    protected AssetOrTermPointOrPricingStructureReference parameterReference;
    protected BigDecimal shift;
    protected PriceQuoteUnits shiftUnits;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété parameterReference.
     * 
     * @return
     *     possible object is
     *     {@link AssetOrTermPointOrPricingStructureReference }
     *     
     */
    public AssetOrTermPointOrPricingStructureReference getParameterReference() {
        return parameterReference;
    }

    /**
     * Définit la valeur de la propriété parameterReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetOrTermPointOrPricingStructureReference }
     *     
     */
    public void setParameterReference(AssetOrTermPointOrPricingStructureReference value) {
        this.parameterReference = value;
    }

    /**
     * Obtient la valeur de la propriété shift.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShift() {
        return shift;
    }

    /**
     * Définit la valeur de la propriété shift.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShift(BigDecimal value) {
        this.shift = value;
    }

    /**
     * Obtient la valeur de la propriété shiftUnits.
     * 
     * @return
     *     possible object is
     *     {@link PriceQuoteUnits }
     *     
     */
    public PriceQuoteUnits getShiftUnits() {
        return shiftUnits;
    }

    /**
     * Définit la valeur de la propriété shiftUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceQuoteUnits }
     *     
     */
    public void setShiftUnits(PriceQuoteUnits value) {
        this.shiftUnits = value;
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
