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
 * A type for defining a strike spread feature.
 * 
 * <p>Classe Java pour StrikeSpread complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StrikeSpread">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upperStrike" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionStrike" minOccurs="0"/>
 *         &lt;element name="upperStrikeNumberOfOptions" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrikeSpread", propOrder = {
    "upperStrike",
    "upperStrikeNumberOfOptions"
})
public class StrikeSpread {

    protected OptionStrike upperStrike;
    protected BigDecimal upperStrikeNumberOfOptions;

    /**
     * Obtient la valeur de la propriété upperStrike.
     * 
     * @return
     *     possible object is
     *     {@link OptionStrike }
     *     
     */
    public OptionStrike getUpperStrike() {
        return upperStrike;
    }

    /**
     * Définit la valeur de la propriété upperStrike.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionStrike }
     *     
     */
    public void setUpperStrike(OptionStrike value) {
        this.upperStrike = value;
    }

    /**
     * Obtient la valeur de la propriété upperStrikeNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUpperStrikeNumberOfOptions() {
        return upperStrikeNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété upperStrikeNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUpperStrikeNumberOfOptions(BigDecimal value) {
        this.upperStrikeNumberOfOptions = value;
    }

}
