//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type definining the parameters used in the calculation of fixed or floating calculation period amounts.
 * 
 * <p>Classe Java pour Calculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Calculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="notionalSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}Notional"/>
 *           &lt;element name="fxLinkedNotionalSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}FxLinkedNotionalSchedule"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="fixedRateSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}Schedule"/>
 *             &lt;element name="futureValueNotional" type="{http://www.fpml.org/FpML-5/recordkeeping}FutureValueAmount" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}rateCalculation"/>
 *         &lt;/choice>
 *         &lt;element name="dayCountFraction" type="{http://www.fpml.org/FpML-5/recordkeeping}DayCountFraction"/>
 *         &lt;element name="discounting" type="{http://www.fpml.org/FpML-5/recordkeeping}Discounting" minOccurs="0"/>
 *         &lt;element name="compoundingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}CompoundingMethodEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Calculation", propOrder = {
    "notionalSchedule",
    "fxLinkedNotionalSchedule",
    "fixedRateSchedule",
    "futureValueNotional",
    "rateCalculation",
    "dayCountFraction",
    "discounting",
    "compoundingMethod"
})
public class Calculation {

    protected Notional notionalSchedule;
    protected FxLinkedNotionalSchedule fxLinkedNotionalSchedule;
    protected Schedule fixedRateSchedule;
    protected FutureValueAmount futureValueNotional;
    @XmlElementRef(name = "rateCalculation", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Rate> rateCalculation;
    @XmlElement(required = true)
    protected DayCountFraction dayCountFraction;
    protected Discounting discounting;
    protected CompoundingMethodEnum compoundingMethod;

    /**
     * Obtient la valeur de la propriété notionalSchedule.
     * 
     * @return
     *     possible object is
     *     {@link Notional }
     *     
     */
    public Notional getNotionalSchedule() {
        return notionalSchedule;
    }

    /**
     * Définit la valeur de la propriété notionalSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link Notional }
     *     
     */
    public void setNotionalSchedule(Notional value) {
        this.notionalSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété fxLinkedNotionalSchedule.
     * 
     * @return
     *     possible object is
     *     {@link FxLinkedNotionalSchedule }
     *     
     */
    public FxLinkedNotionalSchedule getFxLinkedNotionalSchedule() {
        return fxLinkedNotionalSchedule;
    }

    /**
     * Définit la valeur de la propriété fxLinkedNotionalSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link FxLinkedNotionalSchedule }
     *     
     */
    public void setFxLinkedNotionalSchedule(FxLinkedNotionalSchedule value) {
        this.fxLinkedNotionalSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété fixedRateSchedule.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getFixedRateSchedule() {
        return fixedRateSchedule;
    }

    /**
     * Définit la valeur de la propriété fixedRateSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setFixedRateSchedule(Schedule value) {
        this.fixedRateSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété futureValueNotional.
     * 
     * @return
     *     possible object is
     *     {@link FutureValueAmount }
     *     
     */
    public FutureValueAmount getFutureValueNotional() {
        return futureValueNotional;
    }

    /**
     * Définit la valeur de la propriété futureValueNotional.
     * 
     * @param value
     *     allowed object is
     *     {@link FutureValueAmount }
     *     
     */
    public void setFutureValueNotional(FutureValueAmount value) {
        this.futureValueNotional = value;
    }

    /**
     * This element is the head of a substitution group. It is substituted by the floatingRateCalculation element for standard Floating Rate legs, or the inflationRateCalculation element for inflation swaps.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Rate }{@code >}
     *     {@link JAXBElement }{@code <}{@link FloatingRateCalculation }{@code >}
     *     {@link JAXBElement }{@code <}{@link InflationRateCalculation }{@code >}
     *     
     */
    public JAXBElement<? extends Rate> getRateCalculation() {
        return rateCalculation;
    }

    /**
     * Définit la valeur de la propriété rateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Rate }{@code >}
     *     {@link JAXBElement }{@code <}{@link FloatingRateCalculation }{@code >}
     *     {@link JAXBElement }{@code <}{@link InflationRateCalculation }{@code >}
     *     
     */
    public void setRateCalculation(JAXBElement<? extends Rate> value) {
        this.rateCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété dayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDayCountFraction() {
        return dayCountFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDayCountFraction(DayCountFraction value) {
        this.dayCountFraction = value;
    }

    /**
     * Obtient la valeur de la propriété discounting.
     * 
     * @return
     *     possible object is
     *     {@link Discounting }
     *     
     */
    public Discounting getDiscounting() {
        return discounting;
    }

    /**
     * Définit la valeur de la propriété discounting.
     * 
     * @param value
     *     allowed object is
     *     {@link Discounting }
     *     
     */
    public void setDiscounting(Discounting value) {
        this.discounting = value;
    }

    /**
     * Obtient la valeur de la propriété compoundingMethod.
     * 
     * @return
     *     possible object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public CompoundingMethodEnum getCompoundingMethod() {
        return compoundingMethod;
    }

    /**
     * Définit la valeur de la propriété compoundingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public void setCompoundingMethod(CompoundingMethodEnum value) {
        this.compoundingMethod = value;
    }

}
