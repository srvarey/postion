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
 * A type that describes the rate of exchange at which the embedded option in a Dual Currency Deposit has been struck.
 * 
 * <p>Classe Java pour DualCurrencyStrikePrice complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DualCurrencyStrikePrice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="strikeQuoteBasis" type="{http://www.fpml.org/FpML-5/recordkeeping}DualCurrencyStrikeQuoteBasisEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DualCurrencyStrikePrice", propOrder = {
    "rate",
    "strikeQuoteBasis"
})
public class DualCurrencyStrikePrice {

    protected BigDecimal rate;
    protected DualCurrencyStrikeQuoteBasisEnum strikeQuoteBasis;

    /**
     * Obtient la valeur de la propriété rate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Définit la valeur de la propriété rate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Obtient la valeur de la propriété strikeQuoteBasis.
     * 
     * @return
     *     possible object is
     *     {@link DualCurrencyStrikeQuoteBasisEnum }
     *     
     */
    public DualCurrencyStrikeQuoteBasisEnum getStrikeQuoteBasis() {
        return strikeQuoteBasis;
    }

    /**
     * Définit la valeur de la propriété strikeQuoteBasis.
     * 
     * @param value
     *     allowed object is
     *     {@link DualCurrencyStrikeQuoteBasisEnum }
     *     
     */
    public void setStrikeQuoteBasis(DualCurrencyStrikeQuoteBasisEnum value) {
        this.strikeQuoteBasis = value;
    }

}
