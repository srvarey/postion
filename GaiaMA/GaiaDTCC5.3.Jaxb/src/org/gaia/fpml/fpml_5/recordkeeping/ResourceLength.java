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
 * The type that indicates the length of the resource.
 * 
 * <p>Classe Java pour ResourceLength complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ResourceLength">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lengthUnit" type="{http://www.fpml.org/FpML-5/recordkeeping}LengthUnitEnum" minOccurs="0"/>
 *         &lt;element name="lengthValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceLength", propOrder = {
    "lengthUnit",
    "lengthValue"
})
public class ResourceLength {

    protected LengthUnitEnum lengthUnit;
    protected BigDecimal lengthValue;

    /**
     * Obtient la valeur de la propriété lengthUnit.
     * 
     * @return
     *     possible object is
     *     {@link LengthUnitEnum }
     *     
     */
    public LengthUnitEnum getLengthUnit() {
        return lengthUnit;
    }

    /**
     * Définit la valeur de la propriété lengthUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthUnitEnum }
     *     
     */
    public void setLengthUnit(LengthUnitEnum value) {
        this.lengthUnit = value;
    }

    /**
     * Obtient la valeur de la propriété lengthValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLengthValue() {
        return lengthValue;
    }

    /**
     * Définit la valeur de la propriété lengthValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLengthValue(BigDecimal value) {
        this.lengthValue = value;
    }

}
