//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining a rounding direction and precision to be used in the rounding of a rate.
 * 
 * <p>Classe Java pour Rounding complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Rounding">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roundingDirection" type="{http://www.fpml.org/FpML-5/recordkeeping}RoundingDirectionEnum" minOccurs="0"/>
 *         &lt;element name="precision" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rounding", propOrder = {
    "roundingDirection",
    "precision"
})
public class Rounding {

    protected RoundingDirectionEnum roundingDirection;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger precision;

    /**
     * Obtient la valeur de la propriété roundingDirection.
     * 
     * @return
     *     possible object is
     *     {@link RoundingDirectionEnum }
     *     
     */
    public RoundingDirectionEnum getRoundingDirection() {
        return roundingDirection;
    }

    /**
     * Définit la valeur de la propriété roundingDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link RoundingDirectionEnum }
     *     
     */
    public void setRoundingDirection(RoundingDirectionEnum value) {
        this.roundingDirection = value;
    }

    /**
     * Obtient la valeur de la propriété precision.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrecision() {
        return precision;
    }

    /**
     * Définit la valeur de la propriété precision.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrecision(BigInteger value) {
        this.precision = value;
    }

}
