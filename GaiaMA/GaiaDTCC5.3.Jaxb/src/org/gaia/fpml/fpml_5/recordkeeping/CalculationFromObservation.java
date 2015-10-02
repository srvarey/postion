//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Abstract base class for all calculation from observed values.
 * 
 * <p>Classe Java pour CalculationFromObservation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationFromObservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="initialLevel" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;element name="closingLevel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *           &lt;element name="expiringLevel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="expectedN" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationFromObservation", propOrder = {
    "initialLevel",
    "closingLevel",
    "expiringLevel",
    "expectedN"
})
@XmlSeeAlso({
    Correlation.class,
    Variance.class
})
public abstract class CalculationFromObservation {

    protected BigDecimal initialLevel;
    protected Boolean closingLevel;
    protected Boolean expiringLevel;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger expectedN;

    /**
     * Obtient la valeur de la propriété initialLevel.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialLevel() {
        return initialLevel;
    }

    /**
     * Définit la valeur de la propriété initialLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialLevel(BigDecimal value) {
        this.initialLevel = value;
    }

    /**
     * Obtient la valeur de la propriété closingLevel.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClosingLevel() {
        return closingLevel;
    }

    /**
     * Définit la valeur de la propriété closingLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClosingLevel(Boolean value) {
        this.closingLevel = value;
    }

    /**
     * Obtient la valeur de la propriété expiringLevel.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpiringLevel() {
        return expiringLevel;
    }

    /**
     * Définit la valeur de la propriété expiringLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpiringLevel(Boolean value) {
        this.expiringLevel = value;
    }

    /**
     * Obtient la valeur de la propriété expectedN.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpectedN() {
        return expectedN;
    }

    /**
     * Définit la valeur de la propriété expectedN.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpectedN(BigInteger value) {
        this.expectedN = value;
    }

}
