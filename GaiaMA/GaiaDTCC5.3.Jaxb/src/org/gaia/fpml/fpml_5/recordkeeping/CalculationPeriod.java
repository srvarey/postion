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
 * A type defining the parameters used in the calculation of a fixed or floating rate calculation period amount. This type forms part of cashflows representation of a swap stream.
 * 
 * <p>Classe Java pour CalculationPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unadjustedStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="unadjustedEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="calculationPeriodNumberOfDays" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="notionalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;element name="fxLinkedNotionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}FxLinkedNotionalAmount" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="floatingRateDefinition" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateDefinition" minOccurs="0"/>
 *           &lt;element name="fixedRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="dayCountYearFraction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="forecastAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="forecastRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlType(name = "CalculationPeriod", propOrder = {
    "unadjustedStartDate",
    "unadjustedEndDate",
    "adjustedStartDate",
    "adjustedEndDate",
    "calculationPeriodNumberOfDays",
    "notionalAmount",
    "fxLinkedNotionalAmount",
    "floatingRateDefinition",
    "fixedRate",
    "dayCountYearFraction",
    "forecastAmount",
    "forecastRate"
})
public class CalculationPeriod {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar unadjustedStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar unadjustedEndDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedEndDate;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger calculationPeriodNumberOfDays;
    protected BigDecimal notionalAmount;
    protected FxLinkedNotionalAmount fxLinkedNotionalAmount;
    protected FloatingRateDefinition floatingRateDefinition;
    protected BigDecimal fixedRate;
    protected BigDecimal dayCountYearFraction;
    protected Money forecastAmount;
    protected BigDecimal forecastRate;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété unadjustedStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUnadjustedStartDate() {
        return unadjustedStartDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUnadjustedStartDate(XMLGregorianCalendar value) {
        this.unadjustedStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété unadjustedEndDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUnadjustedEndDate() {
        return unadjustedEndDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUnadjustedEndDate(XMLGregorianCalendar value) {
        this.unadjustedEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedStartDate() {
        return adjustedStartDate;
    }

    /**
     * Définit la valeur de la propriété adjustedStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedStartDate(XMLGregorianCalendar value) {
        this.adjustedStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedEndDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedEndDate() {
        return adjustedEndDate;
    }

    /**
     * Définit la valeur de la propriété adjustedEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedEndDate(XMLGregorianCalendar value) {
        this.adjustedEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodNumberOfDays.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCalculationPeriodNumberOfDays() {
        return calculationPeriodNumberOfDays;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodNumberOfDays.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCalculationPeriodNumberOfDays(BigInteger value) {
        this.calculationPeriodNumberOfDays = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotionalAmount() {
        return notionalAmount;
    }

    /**
     * Définit la valeur de la propriété notionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotionalAmount(BigDecimal value) {
        this.notionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété fxLinkedNotionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link FxLinkedNotionalAmount }
     *     
     */
    public FxLinkedNotionalAmount getFxLinkedNotionalAmount() {
        return fxLinkedNotionalAmount;
    }

    /**
     * Définit la valeur de la propriété fxLinkedNotionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link FxLinkedNotionalAmount }
     *     
     */
    public void setFxLinkedNotionalAmount(FxLinkedNotionalAmount value) {
        this.fxLinkedNotionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété floatingRateDefinition.
     * 
     * @return
     *     possible object is
     *     {@link FloatingRateDefinition }
     *     
     */
    public FloatingRateDefinition getFloatingRateDefinition() {
        return floatingRateDefinition;
    }

    /**
     * Définit la valeur de la propriété floatingRateDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingRateDefinition }
     *     
     */
    public void setFloatingRateDefinition(FloatingRateDefinition value) {
        this.floatingRateDefinition = value;
    }

    /**
     * Obtient la valeur de la propriété fixedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedRate() {
        return fixedRate;
    }

    /**
     * Définit la valeur de la propriété fixedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedRate(BigDecimal value) {
        this.fixedRate = value;
    }

    /**
     * Obtient la valeur de la propriété dayCountYearFraction.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDayCountYearFraction() {
        return dayCountYearFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountYearFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDayCountYearFraction(BigDecimal value) {
        this.dayCountYearFraction = value;
    }

    /**
     * Obtient la valeur de la propriété forecastAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getForecastAmount() {
        return forecastAmount;
    }

    /**
     * Définit la valeur de la propriété forecastAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setForecastAmount(Money value) {
        this.forecastAmount = value;
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
