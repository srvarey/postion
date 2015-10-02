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
 * <p>Classe Java pour InterestShortFall complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestShortFall">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interestShortfallCap" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestShortfallCapEnum" minOccurs="0"/>
 *         &lt;element name="compounding" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="rateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateIndex" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestShortFall", propOrder = {
    "interestShortfallCap",
    "compounding",
    "rateSource"
})
public class InterestShortFall {

    protected InterestShortfallCapEnum interestShortfallCap;
    protected Boolean compounding;
    protected FloatingRateIndex rateSource;

    /**
     * Obtient la valeur de la propriété interestShortfallCap.
     * 
     * @return
     *     possible object is
     *     {@link InterestShortfallCapEnum }
     *     
     */
    public InterestShortfallCapEnum getInterestShortfallCap() {
        return interestShortfallCap;
    }

    /**
     * Définit la valeur de la propriété interestShortfallCap.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestShortfallCapEnum }
     *     
     */
    public void setInterestShortfallCap(InterestShortfallCapEnum value) {
        this.interestShortfallCap = value;
    }

    /**
     * Obtient la valeur de la propriété compounding.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompounding() {
        return compounding;
    }

    /**
     * Définit la valeur de la propriété compounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompounding(Boolean value) {
        this.compounding = value;
    }

    /**
     * Obtient la valeur de la propriété rateSource.
     * 
     * @return
     *     possible object is
     *     {@link FloatingRateIndex }
     *     
     */
    public FloatingRateIndex getRateSource() {
        return rateSource;
    }

    /**
     * Définit la valeur de la propriété rateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingRateIndex }
     *     
     */
    public void setRateSource(FloatingRateIndex value) {
        this.rateSource = value;
    }

}
