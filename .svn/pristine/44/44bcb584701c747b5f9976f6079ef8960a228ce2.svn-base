//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

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
 * A type defining the parameters used to generate the calculation period dates schedule, including the specification of any initial or final stub calculation periods. A calculation perod schedule consists of an optional initial stub calculation period, one or more regular calculation periods and an optional final stub calculation period. In the absence of any initial or final stub calculation periods, the regular part of the calculation period schedule is assumed to be between the effective date and the termination date. No implicit stubs are allowed, i.e. stubs must be explicitly specified using an appropriate combination of firstPeriodStateDate, firstRegularPeriodStartDate and lastRegularPeriodEndDate.
 * 
 * <p>Classe Java pour CalculationPeriodDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationPeriodDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="effectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate"/>
 *           &lt;element name="relativeEffectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustedRelativeDateOffset" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="terminationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate"/>
 *           &lt;element name="relativeTerminationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="calculationPeriodDatesAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *         &lt;element name="firstPeriodStartDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate" minOccurs="0"/>
 *         &lt;element name="firstRegularPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="firstCompoundingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="lastRegularPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="stubPeriodType" type="{http://www.fpml.org/FpML-5/recordkeeping}StubPeriodTypeEnum" minOccurs="0"/>
 *         &lt;element name="calculationPeriodFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodFrequency" minOccurs="0"/>
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
@XmlType(name = "CalculationPeriodDates", propOrder = {
    "effectiveDate",
    "relativeEffectiveDate",
    "terminationDate",
    "relativeTerminationDate",
    "calculationPeriodDatesAdjustments",
    "firstPeriodStartDate",
    "firstRegularPeriodStartDate",
    "firstCompoundingPeriodEndDate",
    "lastRegularPeriodEndDate",
    "stubPeriodType",
    "calculationPeriodFrequency"
})
public class CalculationPeriodDates {

    protected AdjustableDate effectiveDate;
    protected AdjustedRelativeDateOffset relativeEffectiveDate;
    protected AdjustableDate terminationDate;
    protected RelativeDateOffset relativeTerminationDate;
    protected BusinessDayAdjustments calculationPeriodDatesAdjustments;
    protected AdjustableDate firstPeriodStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstRegularPeriodStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstCompoundingPeriodEndDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastRegularPeriodEndDate;
    protected StubPeriodTypeEnum stubPeriodType;
    protected CalculationPeriodFrequency calculationPeriodFrequency;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setEffectiveDate(AdjustableDate value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété relativeEffectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustedRelativeDateOffset }
     *     
     */
    public AdjustedRelativeDateOffset getRelativeEffectiveDate() {
        return relativeEffectiveDate;
    }

    /**
     * Définit la valeur de la propriété relativeEffectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustedRelativeDateOffset }
     *     
     */
    public void setRelativeEffectiveDate(AdjustedRelativeDateOffset value) {
        this.relativeEffectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété terminationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getTerminationDate() {
        return terminationDate;
    }

    /**
     * Définit la valeur de la propriété terminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setTerminationDate(AdjustableDate value) {
        this.terminationDate = value;
    }

    /**
     * Obtient la valeur de la propriété relativeTerminationDate.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getRelativeTerminationDate() {
        return relativeTerminationDate;
    }

    /**
     * Définit la valeur de la propriété relativeTerminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setRelativeTerminationDate(RelativeDateOffset value) {
        this.relativeTerminationDate = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public BusinessDayAdjustments getCalculationPeriodDatesAdjustments() {
        return calculationPeriodDatesAdjustments;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public void setCalculationPeriodDatesAdjustments(BusinessDayAdjustments value) {
        this.calculationPeriodDatesAdjustments = value;
    }

    /**
     * Obtient la valeur de la propriété firstPeriodStartDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getFirstPeriodStartDate() {
        return firstPeriodStartDate;
    }

    /**
     * Définit la valeur de la propriété firstPeriodStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setFirstPeriodStartDate(AdjustableDate value) {
        this.firstPeriodStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété firstRegularPeriodStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstRegularPeriodStartDate() {
        return firstRegularPeriodStartDate;
    }

    /**
     * Définit la valeur de la propriété firstRegularPeriodStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstRegularPeriodStartDate(XMLGregorianCalendar value) {
        this.firstRegularPeriodStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété firstCompoundingPeriodEndDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstCompoundingPeriodEndDate() {
        return firstCompoundingPeriodEndDate;
    }

    /**
     * Définit la valeur de la propriété firstCompoundingPeriodEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstCompoundingPeriodEndDate(XMLGregorianCalendar value) {
        this.firstCompoundingPeriodEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastRegularPeriodEndDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastRegularPeriodEndDate() {
        return lastRegularPeriodEndDate;
    }

    /**
     * Définit la valeur de la propriété lastRegularPeriodEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastRegularPeriodEndDate(XMLGregorianCalendar value) {
        this.lastRegularPeriodEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété stubPeriodType.
     * 
     * @return
     *     possible object is
     *     {@link StubPeriodTypeEnum }
     *     
     */
    public StubPeriodTypeEnum getStubPeriodType() {
        return stubPeriodType;
    }

    /**
     * Définit la valeur de la propriété stubPeriodType.
     * 
     * @param value
     *     allowed object is
     *     {@link StubPeriodTypeEnum }
     *     
     */
    public void setStubPeriodType(StubPeriodTypeEnum value) {
        this.stubPeriodType = value;
    }

    /**
     * Obtient la valeur de la propriété calculationPeriodFrequency.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodFrequency }
     *     
     */
    public CalculationPeriodFrequency getCalculationPeriodFrequency() {
        return calculationPeriodFrequency;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodFrequency }
     *     
     */
    public void setCalculationPeriodFrequency(CalculationPeriodFrequency value) {
        this.calculationPeriodFrequency = value;
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
