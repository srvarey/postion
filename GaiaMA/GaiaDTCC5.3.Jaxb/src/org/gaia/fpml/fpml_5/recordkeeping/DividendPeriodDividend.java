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
 * A time bounded dividend period, with an expected dividend for each period.
 * 
 * <p>Classe Java pour DividendPeriodDividend complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendPeriodDividend">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DividendPeriod">
 *       &lt;sequence>
 *         &lt;element name="dividend" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *         &lt;element name="multiplier" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendPeriodDividend", propOrder = {
    "dividend",
    "multiplier"
})
public class DividendPeriodDividend
    extends DividendPeriod
{

    protected NonNegativeMoney dividend;
    protected BigDecimal multiplier;

    /**
     * Obtient la valeur de la propriété dividend.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getDividend() {
        return dividend;
    }

    /**
     * Définit la valeur de la propriété dividend.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setDividend(NonNegativeMoney value) {
        this.dividend = value;
    }

    /**
     * Obtient la valeur de la propriété multiplier.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMultiplier() {
        return multiplier;
    }

    /**
     * Définit la valeur de la propriété multiplier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMultiplier(BigDecimal value) {
        this.multiplier = value;
    }

}
