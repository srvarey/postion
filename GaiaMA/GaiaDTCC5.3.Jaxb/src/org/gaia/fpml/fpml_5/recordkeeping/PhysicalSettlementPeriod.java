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
 * <p>Classe Java pour PhysicalSettlementPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PhysicalSettlementPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="businessDaysNotSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="businessDays" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="maximumBusinessDays" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalSettlementPeriod", propOrder = {
    "businessDaysNotSpecified",
    "businessDays",
    "maximumBusinessDays"
})
public class PhysicalSettlementPeriod {

    protected Boolean businessDaysNotSpecified;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger businessDays;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maximumBusinessDays;

    /**
     * Obtient la valeur de la propriété businessDaysNotSpecified.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessDaysNotSpecified() {
        return businessDaysNotSpecified;
    }

    /**
     * Définit la valeur de la propriété businessDaysNotSpecified.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessDaysNotSpecified(Boolean value) {
        this.businessDaysNotSpecified = value;
    }

    /**
     * Obtient la valeur de la propriété businessDays.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBusinessDays() {
        return businessDays;
    }

    /**
     * Définit la valeur de la propriété businessDays.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBusinessDays(BigInteger value) {
        this.businessDays = value;
    }

    /**
     * Obtient la valeur de la propriété maximumBusinessDays.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumBusinessDays() {
        return maximumBusinessDays;
    }

    /**
     * Définit la valeur de la propriété maximumBusinessDays.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumBusinessDays(BigInteger value) {
        this.maximumBusinessDays = value;
    }

}
