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
 * A curve used to model a set of zero-coupon interest rates.
 * 
 * <p>Classe Java pour ZeroRateCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ZeroRateCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compoundingFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}CompoundingFrequency" minOccurs="0"/>
 *         &lt;element name="rateCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}TermCurve" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZeroRateCurve", propOrder = {
    "compoundingFrequency",
    "rateCurve"
})
public class ZeroRateCurve {

    protected CompoundingFrequency compoundingFrequency;
    protected TermCurve rateCurve;

    /**
     * Obtient la valeur de la propriété compoundingFrequency.
     * 
     * @return
     *     possible object is
     *     {@link CompoundingFrequency }
     *     
     */
    public CompoundingFrequency getCompoundingFrequency() {
        return compoundingFrequency;
    }

    /**
     * Définit la valeur de la propriété compoundingFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundingFrequency }
     *     
     */
    public void setCompoundingFrequency(CompoundingFrequency value) {
        this.compoundingFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété rateCurve.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getRateCurve() {
        return rateCurve;
    }

    /**
     * Définit la valeur de la propriété rateCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setRateCurve(TermCurve value) {
        this.rateCurve = value;
    }

}
