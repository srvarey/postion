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
 * A type for defining option features.
 * 
 * <p>Classe Java pour OptionFeatures complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OptionFeatures">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asian" type="{http://www.fpml.org/FpML-5/recordkeeping}Asian" minOccurs="0"/>
 *         &lt;element name="barrier" type="{http://www.fpml.org/FpML-5/recordkeeping}Barrier" minOccurs="0"/>
 *         &lt;element name="knock" type="{http://www.fpml.org/FpML-5/recordkeeping}Knock" minOccurs="0"/>
 *         &lt;element name="passThrough" type="{http://www.fpml.org/FpML-5/recordkeeping}PassThrough" minOccurs="0"/>
 *         &lt;element name="dividendAdjustment" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendAdjustment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionFeatures", propOrder = {
    "asian",
    "barrier",
    "knock",
    "passThrough",
    "dividendAdjustment"
})
public class OptionFeatures {

    protected Asian asian;
    protected Barrier barrier;
    protected Knock knock;
    protected PassThrough passThrough;
    protected DividendAdjustment dividendAdjustment;

    /**
     * Obtient la valeur de la propriété asian.
     * 
     * @return
     *     possible object is
     *     {@link Asian }
     *     
     */
    public Asian getAsian() {
        return asian;
    }

    /**
     * Définit la valeur de la propriété asian.
     * 
     * @param value
     *     allowed object is
     *     {@link Asian }
     *     
     */
    public void setAsian(Asian value) {
        this.asian = value;
    }

    /**
     * Obtient la valeur de la propriété barrier.
     * 
     * @return
     *     possible object is
     *     {@link Barrier }
     *     
     */
    public Barrier getBarrier() {
        return barrier;
    }

    /**
     * Définit la valeur de la propriété barrier.
     * 
     * @param value
     *     allowed object is
     *     {@link Barrier }
     *     
     */
    public void setBarrier(Barrier value) {
        this.barrier = value;
    }

    /**
     * Obtient la valeur de la propriété knock.
     * 
     * @return
     *     possible object is
     *     {@link Knock }
     *     
     */
    public Knock getKnock() {
        return knock;
    }

    /**
     * Définit la valeur de la propriété knock.
     * 
     * @param value
     *     allowed object is
     *     {@link Knock }
     *     
     */
    public void setKnock(Knock value) {
        this.knock = value;
    }

    /**
     * Obtient la valeur de la propriété passThrough.
     * 
     * @return
     *     possible object is
     *     {@link PassThrough }
     *     
     */
    public PassThrough getPassThrough() {
        return passThrough;
    }

    /**
     * Définit la valeur de la propriété passThrough.
     * 
     * @param value
     *     allowed object is
     *     {@link PassThrough }
     *     
     */
    public void setPassThrough(PassThrough value) {
        this.passThrough = value;
    }

    /**
     * Obtient la valeur de la propriété dividendAdjustment.
     * 
     * @return
     *     possible object is
     *     {@link DividendAdjustment }
     *     
     */
    public DividendAdjustment getDividendAdjustment() {
        return dividendAdjustment;
    }

    /**
     * Définit la valeur de la propriété dividendAdjustment.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendAdjustment }
     *     
     */
    public void setDividendAdjustment(DividendAdjustment value) {
        this.dividendAdjustment = value;
    }

}
