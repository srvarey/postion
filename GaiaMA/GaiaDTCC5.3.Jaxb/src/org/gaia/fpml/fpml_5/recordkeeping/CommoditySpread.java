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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CommoditySpread complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommoditySpread">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Money">
 *       &lt;sequence>
 *         &lt;element name="spreadConversionFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="spreadUnit" type="{http://www.fpml.org/FpML-5/recordkeeping}QuantityUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommoditySpread", propOrder = {
    "spreadConversionFactor",
    "spreadUnit"
})
public class CommoditySpread
    extends Money
{

    protected BigDecimal spreadConversionFactor;
    protected QuantityUnit spreadUnit;

    /**
     * Obtient la valeur de la propriété spreadConversionFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpreadConversionFactor() {
        return spreadConversionFactor;
    }

    /**
     * Définit la valeur de la propriété spreadConversionFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpreadConversionFactor(BigDecimal value) {
        this.spreadConversionFactor = value;
    }

    /**
     * Obtient la valeur de la propriété spreadUnit.
     * 
     * @return
     *     possible object is
     *     {@link QuantityUnit }
     *     
     */
    public QuantityUnit getSpreadUnit() {
        return spreadUnit;
    }

    /**
     * Définit la valeur de la propriété spreadUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityUnit }
     *     
     */
    public void setSpreadUnit(QuantityUnit value) {
        this.spreadUnit = value;
    }

}
