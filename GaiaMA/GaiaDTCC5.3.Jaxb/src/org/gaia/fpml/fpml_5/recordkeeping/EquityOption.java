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
 * A type for defining equity options.
 * 
 * <p>Classe Java pour EquityOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}EquityDerivativeLongFormBase">
 *       &lt;sequence>
 *         &lt;element name="strike" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityStrike" minOccurs="0"/>
 *         &lt;element name="spotPrice" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *         &lt;element name="numberOfOptions" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeDecimal" minOccurs="0"/>
 *         &lt;element name="optionEntitlement" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="equityPremium" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityPremium" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityOption", propOrder = {
    "strike",
    "spotPrice",
    "numberOfOptions",
    "optionEntitlement",
    "equityPremium"
})
public class EquityOption
    extends EquityDerivativeLongFormBase
{

    protected EquityStrike strike;
    protected BigDecimal spotPrice;
    protected BigDecimal numberOfOptions;
    protected BigDecimal optionEntitlement;
    protected EquityPremium equityPremium;

    /**
     * Obtient la valeur de la propriété strike.
     * 
     * @return
     *     possible object is
     *     {@link EquityStrike }
     *     
     */
    public EquityStrike getStrike() {
        return strike;
    }

    /**
     * Définit la valeur de la propriété strike.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityStrike }
     *     
     */
    public void setStrike(EquityStrike value) {
        this.strike = value;
    }

    /**
     * Obtient la valeur de la propriété spotPrice.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpotPrice() {
        return spotPrice;
    }

    /**
     * Définit la valeur de la propriété spotPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpotPrice(BigDecimal value) {
        this.spotPrice = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfOptions() {
        return numberOfOptions;
    }

    /**
     * Définit la valeur de la propriété numberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfOptions(BigDecimal value) {
        this.numberOfOptions = value;
    }

    /**
     * Obtient la valeur de la propriété optionEntitlement.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOptionEntitlement() {
        return optionEntitlement;
    }

    /**
     * Définit la valeur de la propriété optionEntitlement.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOptionEntitlement(BigDecimal value) {
        this.optionEntitlement = value;
    }

    /**
     * Obtient la valeur de la propriété equityPremium.
     * 
     * @return
     *     possible object is
     *     {@link EquityPremium }
     *     
     */
    public EquityPremium getEquityPremium() {
        return equityPremium;
    }

    /**
     * Définit la valeur de la propriété equityPremium.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityPremium }
     *     
     */
    public void setEquityPremium(EquityPremium value) {
        this.equityPremium = value;
    }

}
