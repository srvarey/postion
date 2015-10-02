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
 * The acceptable tolerance in the delivered quantity of a physical commodity product in terms of a percentage of the agreed delivery quantity.
 * 
 * <p>Classe Java pour PercentageTolerance complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PercentageTolerance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="postitive" type="{http://www.fpml.org/FpML-5/recordkeeping}RestrictedPercentage" minOccurs="0"/>
 *         &lt;element name="negative" type="{http://www.fpml.org/FpML-5/recordkeeping}RestrictedPercentage" minOccurs="0"/>
 *         &lt;element name="option" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PercentageTolerance", propOrder = {
    "postitive",
    "negative",
    "option"
})
public class PercentageTolerance {

    protected BigDecimal postitive;
    protected BigDecimal negative;
    protected PartyReference option;

    /**
     * Obtient la valeur de la propriété postitive.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPostitive() {
        return postitive;
    }

    /**
     * Définit la valeur de la propriété postitive.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPostitive(BigDecimal value) {
        this.postitive = value;
    }

    /**
     * Obtient la valeur de la propriété negative.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNegative() {
        return negative;
    }

    /**
     * Définit la valeur de la propriété negative.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNegative(BigDecimal value) {
        this.negative = value;
    }

    /**
     * Obtient la valeur de la propriété option.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getOption() {
        return option;
    }

    /**
     * Définit la valeur de la propriété option.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setOption(PartyReference value) {
        this.option = value;
    }

}
