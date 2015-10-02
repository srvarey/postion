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
 * As per ISDA 2002 Definitions.
 * 
 * <p>Classe Java pour Asian complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Asian">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="averagingInOut" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingInOutEnum" minOccurs="0"/>
 *         &lt;element name="strikeFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="averagingPeriodIn" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingPeriod" minOccurs="0"/>
 *         &lt;element name="averagingPeriodOut" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingPeriod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Asian", propOrder = {
    "averagingInOut",
    "strikeFactor",
    "averagingPeriodIn",
    "averagingPeriodOut"
})
public class Asian {

    protected AveragingInOutEnum averagingInOut;
    protected BigDecimal strikeFactor;
    protected AveragingPeriod averagingPeriodIn;
    protected AveragingPeriod averagingPeriodOut;

    /**
     * Obtient la valeur de la propriété averagingInOut.
     * 
     * @return
     *     possible object is
     *     {@link AveragingInOutEnum }
     *     
     */
    public AveragingInOutEnum getAveragingInOut() {
        return averagingInOut;
    }

    /**
     * Définit la valeur de la propriété averagingInOut.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingInOutEnum }
     *     
     */
    public void setAveragingInOut(AveragingInOutEnum value) {
        this.averagingInOut = value;
    }

    /**
     * Obtient la valeur de la propriété strikeFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStrikeFactor() {
        return strikeFactor;
    }

    /**
     * Définit la valeur de la propriété strikeFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStrikeFactor(BigDecimal value) {
        this.strikeFactor = value;
    }

    /**
     * Obtient la valeur de la propriété averagingPeriodIn.
     * 
     * @return
     *     possible object is
     *     {@link AveragingPeriod }
     *     
     */
    public AveragingPeriod getAveragingPeriodIn() {
        return averagingPeriodIn;
    }

    /**
     * Définit la valeur de la propriété averagingPeriodIn.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingPeriod }
     *     
     */
    public void setAveragingPeriodIn(AveragingPeriod value) {
        this.averagingPeriodIn = value;
    }

    /**
     * Obtient la valeur de la propriété averagingPeriodOut.
     * 
     * @return
     *     possible object is
     *     {@link AveragingPeriod }
     *     
     */
    public AveragingPeriod getAveragingPeriodOut() {
        return averagingPeriodOut;
    }

    /**
     * Définit la valeur de la propriété averagingPeriodOut.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingPeriod }
     *     
     */
    public void setAveragingPeriodOut(AveragingPeriod value) {
        this.averagingPeriodOut = value;
    }

}
