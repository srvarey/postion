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
 * A type defining a compounding rate. The compounding interest can either point back to the floating rate calculation of interest calculation node on the Interest Leg, or be defined specifically.
 * 
 * <p>Classe Java pour CompoundingRate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CompoundingRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="interestLegRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateCalculationReference" minOccurs="0"/>
 *         &lt;element name="specificRate" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestAccrualsMethod" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompoundingRate", propOrder = {
    "interestLegRate",
    "specificRate"
})
public class CompoundingRate {

    protected FloatingRateCalculationReference interestLegRate;
    protected InterestAccrualsMethod specificRate;

    /**
     * Obtient la valeur de la propriété interestLegRate.
     * 
     * @return
     *     possible object is
     *     {@link FloatingRateCalculationReference }
     *     
     */
    public FloatingRateCalculationReference getInterestLegRate() {
        return interestLegRate;
    }

    /**
     * Définit la valeur de la propriété interestLegRate.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingRateCalculationReference }
     *     
     */
    public void setInterestLegRate(FloatingRateCalculationReference value) {
        this.interestLegRate = value;
    }

    /**
     * Obtient la valeur de la propriété specificRate.
     * 
     * @return
     *     possible object is
     *     {@link InterestAccrualsMethod }
     *     
     */
    public InterestAccrualsMethod getSpecificRate() {
        return specificRate;
    }

    /**
     * Définit la valeur de la propriété specificRate.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestAccrualsMethod }
     *     
     */
    public void setSpecificRate(InterestAccrualsMethod value) {
        this.specificRate = value;
    }

}
