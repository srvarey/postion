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
 * Specifies the compounding method and the compounding rate.
 * 
 * <p>Classe Java pour Compounding complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Compounding">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compoundingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}CompoundingMethodEnum" minOccurs="0"/>
 *         &lt;element name="compoundingRate" type="{http://www.fpml.org/FpML-5/recordkeeping}CompoundingRate" minOccurs="0"/>
 *         &lt;element name="compoundingSpread" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="compoundingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Compounding", propOrder = {
    "compoundingMethod",
    "compoundingRate",
    "compoundingSpread",
    "compoundingDates"
})
public class Compounding {

    protected CompoundingMethodEnum compoundingMethod;
    protected CompoundingRate compoundingRate;
    protected BigDecimal compoundingSpread;
    protected AdjustableRelativeOrPeriodicDates2 compoundingDates;

    /**
     * Obtient la valeur de la propriété compoundingMethod.
     * 
     * @return
     *     possible object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public CompoundingMethodEnum getCompoundingMethod() {
        return compoundingMethod;
    }

    /**
     * Définit la valeur de la propriété compoundingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public void setCompoundingMethod(CompoundingMethodEnum value) {
        this.compoundingMethod = value;
    }

    /**
     * Obtient la valeur de la propriété compoundingRate.
     * 
     * @return
     *     possible object is
     *     {@link CompoundingRate }
     *     
     */
    public CompoundingRate getCompoundingRate() {
        return compoundingRate;
    }

    /**
     * Définit la valeur de la propriété compoundingRate.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundingRate }
     *     
     */
    public void setCompoundingRate(CompoundingRate value) {
        this.compoundingRate = value;
    }

    /**
     * Obtient la valeur de la propriété compoundingSpread.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCompoundingSpread() {
        return compoundingSpread;
    }

    /**
     * Définit la valeur de la propriété compoundingSpread.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCompoundingSpread(BigDecimal value) {
        this.compoundingSpread = value;
    }

    /**
     * Obtient la valeur de la propriété compoundingDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates2 }
     *     
     */
    public AdjustableRelativeOrPeriodicDates2 getCompoundingDates() {
        return compoundingDates;
    }

    /**
     * Définit la valeur de la propriété compoundingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates2 }
     *     
     */
    public void setCompoundingDates(AdjustableRelativeOrPeriodicDates2 value) {
        this.compoundingDates = value;
    }

}
