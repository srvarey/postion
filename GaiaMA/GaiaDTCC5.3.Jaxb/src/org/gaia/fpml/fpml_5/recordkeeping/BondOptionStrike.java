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
 * A complex type to specify the strike of a bond or convertible bond option.
 * 
 * <p>Classe Java pour BondOptionStrike complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BondOptionStrike">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="referenceSwapCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}ReferenceSwapCurve" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionStrike" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BondOptionStrike", propOrder = {
    "referenceSwapCurve",
    "price"
})
public class BondOptionStrike {

    protected ReferenceSwapCurve referenceSwapCurve;
    protected OptionStrike price;

    /**
     * Obtient la valeur de la propriété referenceSwapCurve.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceSwapCurve }
     *     
     */
    public ReferenceSwapCurve getReferenceSwapCurve() {
        return referenceSwapCurve;
    }

    /**
     * Définit la valeur de la propriété referenceSwapCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceSwapCurve }
     *     
     */
    public void setReferenceSwapCurve(ReferenceSwapCurve value) {
        this.referenceSwapCurve = value;
    }

    /**
     * Obtient la valeur de la propriété price.
     * 
     * @return
     *     possible object is
     *     {@link OptionStrike }
     *     
     */
    public OptionStrike getPrice() {
        return price;
    }

    /**
     * Définit la valeur de la propriété price.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionStrike }
     *     
     */
    public void setPrice(OptionStrike value) {
        this.price = value;
    }

}
