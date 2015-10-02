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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type for defining how and when an equity option is to be valued.
 * 
 * <p>Classe Java pour EquityValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityValuation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="valuationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDateOrRelativeDateSequence" minOccurs="0"/>
 *           &lt;element name="valuationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="valuationTimeType" type="{http://www.fpml.org/FpML-5/recordkeeping}TimeTypeEnum" minOccurs="0"/>
 *         &lt;element name="valuationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="futuresPriceValuation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="optionsPriceValuation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="numberOfValuationDates" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="dividendValuationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates" minOccurs="0"/>
 *         &lt;element name="fPVFinalPriceElectionFallback" type="{http://www.fpml.org/FpML-5/recordkeeping}FPVFinalPriceElectionFallbackEnum" minOccurs="0"/>
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
@XmlType(name = "EquityValuation", propOrder = {
    "valuationDate",
    "valuationDates",
    "valuationTimeType",
    "valuationTime",
    "futuresPriceValuation",
    "optionsPriceValuation",
    "numberOfValuationDates",
    "dividendValuationDates",
    "fpvFinalPriceElectionFallback"
})
public class EquityValuation {

    protected AdjustableDateOrRelativeDateSequence valuationDate;
    protected AdjustableRelativeOrPeriodicDates valuationDates;
    protected TimeTypeEnum valuationTimeType;
    protected BusinessCenterTime valuationTime;
    protected Boolean futuresPriceValuation;
    protected Boolean optionsPriceValuation;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numberOfValuationDates;
    protected AdjustableRelativeOrPeriodicDates dividendValuationDates;
    @XmlElement(name = "fPVFinalPriceElectionFallback")
    protected FPVFinalPriceElectionFallbackEnum fpvFinalPriceElectionFallback;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété valuationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDateOrRelativeDateSequence }
     *     
     */
    public AdjustableDateOrRelativeDateSequence getValuationDate() {
        return valuationDate;
    }

    /**
     * Définit la valeur de la propriété valuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDateOrRelativeDateSequence }
     *     
     */
    public void setValuationDate(AdjustableDateOrRelativeDateSequence value) {
        this.valuationDate = value;
    }

    /**
     * Obtient la valeur de la propriété valuationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public AdjustableRelativeOrPeriodicDates getValuationDates() {
        return valuationDates;
    }

    /**
     * Définit la valeur de la propriété valuationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public void setValuationDates(AdjustableRelativeOrPeriodicDates value) {
        this.valuationDates = value;
    }

    /**
     * Obtient la valeur de la propriété valuationTimeType.
     * 
     * @return
     *     possible object is
     *     {@link TimeTypeEnum }
     *     
     */
    public TimeTypeEnum getValuationTimeType() {
        return valuationTimeType;
    }

    /**
     * Définit la valeur de la propriété valuationTimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTypeEnum }
     *     
     */
    public void setValuationTimeType(TimeTypeEnum value) {
        this.valuationTimeType = value;
    }

    /**
     * Obtient la valeur de la propriété valuationTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getValuationTime() {
        return valuationTime;
    }

    /**
     * Définit la valeur de la propriété valuationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setValuationTime(BusinessCenterTime value) {
        this.valuationTime = value;
    }

    /**
     * Obtient la valeur de la propriété futuresPriceValuation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFuturesPriceValuation() {
        return futuresPriceValuation;
    }

    /**
     * Définit la valeur de la propriété futuresPriceValuation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFuturesPriceValuation(Boolean value) {
        this.futuresPriceValuation = value;
    }

    /**
     * Obtient la valeur de la propriété optionsPriceValuation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptionsPriceValuation() {
        return optionsPriceValuation;
    }

    /**
     * Définit la valeur de la propriété optionsPriceValuation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptionsPriceValuation(Boolean value) {
        this.optionsPriceValuation = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfValuationDates.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfValuationDates() {
        return numberOfValuationDates;
    }

    /**
     * Définit la valeur de la propriété numberOfValuationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfValuationDates(BigInteger value) {
        this.numberOfValuationDates = value;
    }

    /**
     * Obtient la valeur de la propriété dividendValuationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public AdjustableRelativeOrPeriodicDates getDividendValuationDates() {
        return dividendValuationDates;
    }

    /**
     * Définit la valeur de la propriété dividendValuationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public void setDividendValuationDates(AdjustableRelativeOrPeriodicDates value) {
        this.dividendValuationDates = value;
    }

    /**
     * Obtient la valeur de la propriété fpvFinalPriceElectionFallback.
     * 
     * @return
     *     possible object is
     *     {@link FPVFinalPriceElectionFallbackEnum }
     *     
     */
    public FPVFinalPriceElectionFallbackEnum getFPVFinalPriceElectionFallback() {
        return fpvFinalPriceElectionFallback;
    }

    /**
     * Définit la valeur de la propriété fpvFinalPriceElectionFallback.
     * 
     * @param value
     *     allowed object is
     *     {@link FPVFinalPriceElectionFallbackEnum }
     *     
     */
    public void setFPVFinalPriceElectionFallback(FPVFinalPriceElectionFallbackEnum value) {
        this.fpvFinalPriceElectionFallback = value;
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
