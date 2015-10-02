//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A complex type to support the credit default swap option.
 * 
 * <p>Classe Java pour CreditDefaultSwapOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CreditDefaultSwapOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}OptionBaseExtended">
 *       &lt;sequence>
 *         &lt;element name="strike" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditOptionStrike"/>
 *         &lt;element name="creditDefaultSwap" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditDefaultSwap"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditDefaultSwapOption", propOrder = {
    "strike",
    "creditDefaultSwap"
})
public class CreditDefaultSwapOption
    extends OptionBaseExtended
{

    @XmlElement(required = true)
    protected CreditOptionStrike strike;
    @XmlElement(required = true)
    protected CreditDefaultSwap creditDefaultSwap;

    /**
     * Obtient la valeur de la propriété strike.
     * 
     * @return
     *     possible object is
     *     {@link CreditOptionStrike }
     *     
     */
    public CreditOptionStrike getStrike() {
        return strike;
    }

    /**
     * Définit la valeur de la propriété strike.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditOptionStrike }
     *     
     */
    public void setStrike(CreditOptionStrike value) {
        this.strike = value;
    }

    /**
     * Obtient la valeur de la propriété creditDefaultSwap.
     * 
     * @return
     *     possible object is
     *     {@link CreditDefaultSwap }
     *     
     */
    public CreditDefaultSwap getCreditDefaultSwap() {
        return creditDefaultSwap;
    }

    /**
     * Définit la valeur de la propriété creditDefaultSwap.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditDefaultSwap }
     *     
     */
    public void setCreditDefaultSwap(CreditDefaultSwap value) {
        this.creditDefaultSwap = value;
    }

}
