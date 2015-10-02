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
 * A type describing the weight of each of the underlyer constituent within the basket, either in absolute or relative terms.
 * 
 * <p>Classe Java pour ConstituentWeight complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ConstituentWeight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="openUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="basketPercentage" type="{http://www.fpml.org/FpML-5/recordkeeping}RestrictedPercentage" minOccurs="0"/>
 *         &lt;element name="basketAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConstituentWeight", propOrder = {
    "openUnits",
    "basketPercentage",
    "basketAmount"
})
public class ConstituentWeight {

    protected BigDecimal openUnits;
    protected BigDecimal basketPercentage;
    protected Money basketAmount;

    /**
     * Obtient la valeur de la propriété openUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOpenUnits() {
        return openUnits;
    }

    /**
     * Définit la valeur de la propriété openUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOpenUnits(BigDecimal value) {
        this.openUnits = value;
    }

    /**
     * Obtient la valeur de la propriété basketPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBasketPercentage() {
        return basketPercentage;
    }

    /**
     * Définit la valeur de la propriété basketPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBasketPercentage(BigDecimal value) {
        this.basketPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété basketAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getBasketAmount() {
        return basketAmount;
    }

    /**
     * Définit la valeur de la propriété basketAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setBasketAmount(Money value) {
        this.basketAmount = value;
    }

}
