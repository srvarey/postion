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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type defining parameters associated with an individual observation or fixing. This type forms part of the cashflow representation of a stream.
 * 
 * <p>Classe Java pour RateObservation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RateObservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resetDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedFixingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="observedRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="treatedRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="observationWeight" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="rateReference" type="{http://www.fpml.org/FpML-5/recordkeeping}RateReference" minOccurs="0"/>
 *         &lt;element name="forecastRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="treatedForecastRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateObservation", propOrder = {
    "resetDate",
    "adjustedFixingDate",
    "observedRate",
    "treatedRate",
    "observationWeight",
    "rateReference",
    "forecastRate",
    "treatedForecastRate"
})
public class RateObservation {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar resetDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedFixingDate;
    protected BigDecimal observedRate;
    protected BigDecimal treatedRate;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger observationWeight;
    protected RateReference rateReference;
    protected BigDecimal forecastRate;
    protected BigDecimal treatedForecastRate;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété resetDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResetDate() {
        return resetDate;
    }

    /**
     * Définit la valeur de la propriété resetDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResetDate(XMLGregorianCalendar value) {
        this.resetDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedFixingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedFixingDate() {
        return adjustedFixingDate;
    }

    /**
     * Définit la valeur de la propriété adjustedFixingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedFixingDate(XMLGregorianCalendar value) {
        this.adjustedFixingDate = value;
    }

    /**
     * Obtient la valeur de la propriété observedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getObservedRate() {
        return observedRate;
    }

    /**
     * Définit la valeur de la propriété observedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setObservedRate(BigDecimal value) {
        this.observedRate = value;
    }

    /**
     * Obtient la valeur de la propriété treatedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTreatedRate() {
        return treatedRate;
    }

    /**
     * Définit la valeur de la propriété treatedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTreatedRate(BigDecimal value) {
        this.treatedRate = value;
    }

    /**
     * Obtient la valeur de la propriété observationWeight.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getObservationWeight() {
        return observationWeight;
    }

    /**
     * Définit la valeur de la propriété observationWeight.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setObservationWeight(BigInteger value) {
        this.observationWeight = value;
    }

    /**
     * Obtient la valeur de la propriété rateReference.
     * 
     * @return
     *     possible object is
     *     {@link RateReference }
     *     
     */
    public RateReference getRateReference() {
        return rateReference;
    }

    /**
     * Définit la valeur de la propriété rateReference.
     * 
     * @param value
     *     allowed object is
     *     {@link RateReference }
     *     
     */
    public void setRateReference(RateReference value) {
        this.rateReference = value;
    }

    /**
     * Obtient la valeur de la propriété forecastRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getForecastRate() {
        return forecastRate;
    }

    /**
     * Définit la valeur de la propriété forecastRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setForecastRate(BigDecimal value) {
        this.forecastRate = value;
    }

    /**
     * Obtient la valeur de la propriété treatedForecastRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTreatedForecastRate() {
        return treatedForecastRate;
    }

    /**
     * Définit la valeur de la propriété treatedForecastRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTreatedForecastRate(BigDecimal value) {
        this.treatedForecastRate = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
