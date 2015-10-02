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
 * A Bond Option
 * 
 * <p>Classe Java pour BondOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BondOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}OptionBaseExtended">
 *       &lt;sequence>
 *         &lt;element name="strike" type="{http://www.fpml.org/FpML-5/recordkeeping}BondOptionStrike"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BondChoice.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BondOption", propOrder = {
    "strike",
    "bond",
    "convertibleBond"
})
public class BondOption
    extends OptionBaseExtended
{

    @XmlElement(required = true)
    protected BondOptionStrike strike;
    protected Bond bond;
    protected ConvertibleBond convertibleBond;

    /**
     * Obtient la valeur de la propriété strike.
     * 
     * @return
     *     possible object is
     *     {@link BondOptionStrike }
     *     
     */
    public BondOptionStrike getStrike() {
        return strike;
    }

    /**
     * Définit la valeur de la propriété strike.
     * 
     * @param value
     *     allowed object is
     *     {@link BondOptionStrike }
     *     
     */
    public void setStrike(BondOptionStrike value) {
        this.strike = value;
    }

    /**
     * A bond instrument referenced by a contract
     * 
     * @return
     *     possible object is
     *     {@link Bond }
     *     
     */
    public Bond getBond() {
        return bond;
    }

    /**
     * Définit la valeur de la propriété bond.
     * 
     * @param value
     *     allowed object is
     *     {@link Bond }
     *     
     */
    public void setBond(Bond value) {
        this.bond = value;
    }

    /**
     * A convertible bond instrument referenced by a contract.
     * 
     * @return
     *     possible object is
     *     {@link ConvertibleBond }
     *     
     */
    public ConvertibleBond getConvertibleBond() {
        return convertibleBond;
    }

    /**
     * Définit la valeur de la propriété convertibleBond.
     * 
     * @param value
     *     allowed object is
     *     {@link ConvertibleBond }
     *     
     */
    public void setConvertibleBond(ConvertibleBond value) {
        this.convertibleBond = value;
    }

}
