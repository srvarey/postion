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
 * A type to describe a notional schedule where each notional that applies to a calculation period is calculated with reference to a notional amount or notional amount schedule in a different currency by means of a spot currency exchange rate which is normally observed at the beginning of each period.
 * 
 * <p>Classe Java pour FxLinkedNotionalSchedule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxLinkedNotionalSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="constantNotionalScheduleReference" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalReference" minOccurs="0"/>
 *         &lt;element name="initialValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="varyingNotionalCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="varyingNotionalFixingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;element name="fxSpotRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}FxSpotRateSource" minOccurs="0"/>
 *         &lt;element name="varyingNotionalInterimExchangePaymentDates" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxLinkedNotionalSchedule", propOrder = {
    "constantNotionalScheduleReference",
    "initialValue",
    "varyingNotionalCurrency",
    "varyingNotionalFixingDates",
    "fxSpotRateSource",
    "varyingNotionalInterimExchangePaymentDates"
})
public class FxLinkedNotionalSchedule {

    protected NotionalReference constantNotionalScheduleReference;
    protected BigDecimal initialValue;
    protected Currency varyingNotionalCurrency;
    protected RelativeDateOffset varyingNotionalFixingDates;
    protected FxSpotRateSource fxSpotRateSource;
    protected RelativeDateOffset varyingNotionalInterimExchangePaymentDates;

    /**
     * Obtient la valeur de la propriété constantNotionalScheduleReference.
     * 
     * @return
     *     possible object is
     *     {@link NotionalReference }
     *     
     */
    public NotionalReference getConstantNotionalScheduleReference() {
        return constantNotionalScheduleReference;
    }

    /**
     * Définit la valeur de la propriété constantNotionalScheduleReference.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalReference }
     *     
     */
    public void setConstantNotionalScheduleReference(NotionalReference value) {
        this.constantNotionalScheduleReference = value;
    }

    /**
     * Obtient la valeur de la propriété initialValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialValue() {
        return initialValue;
    }

    /**
     * Définit la valeur de la propriété initialValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialValue(BigDecimal value) {
        this.initialValue = value;
    }

    /**
     * Obtient la valeur de la propriété varyingNotionalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getVaryingNotionalCurrency() {
        return varyingNotionalCurrency;
    }

    /**
     * Définit la valeur de la propriété varyingNotionalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setVaryingNotionalCurrency(Currency value) {
        this.varyingNotionalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété varyingNotionalFixingDates.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getVaryingNotionalFixingDates() {
        return varyingNotionalFixingDates;
    }

    /**
     * Définit la valeur de la propriété varyingNotionalFixingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setVaryingNotionalFixingDates(RelativeDateOffset value) {
        this.varyingNotionalFixingDates = value;
    }

    /**
     * Obtient la valeur de la propriété fxSpotRateSource.
     * 
     * @return
     *     possible object is
     *     {@link FxSpotRateSource }
     *     
     */
    public FxSpotRateSource getFxSpotRateSource() {
        return fxSpotRateSource;
    }

    /**
     * Définit la valeur de la propriété fxSpotRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link FxSpotRateSource }
     *     
     */
    public void setFxSpotRateSource(FxSpotRateSource value) {
        this.fxSpotRateSource = value;
    }

    /**
     * Obtient la valeur de la propriété varyingNotionalInterimExchangePaymentDates.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getVaryingNotionalInterimExchangePaymentDates() {
        return varyingNotionalInterimExchangePaymentDates;
    }

    /**
     * Définit la valeur de la propriété varyingNotionalInterimExchangePaymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setVaryingNotionalInterimExchangePaymentDates(RelativeDateOffset value) {
        this.varyingNotionalInterimExchangePaymentDates = value;
    }

}
