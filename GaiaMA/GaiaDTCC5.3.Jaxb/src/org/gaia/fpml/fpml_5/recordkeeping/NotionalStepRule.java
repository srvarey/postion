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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type defining a parametric representation of the notional step schedule, i.e. parameters used to generate the notional balance on each step date. The step change in notional can be expressed in terms of either a fixed amount or as a percentage of either the initial notional or previous notional amount. This parametric representation is intended to cover the more common amortizing/accreting.
 * 
 * <p>Classe Java pour NotionalStepRule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NotionalStepRule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationPeriodDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodDatesReference" minOccurs="0"/>
 *         &lt;element name="stepFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" minOccurs="0"/>
 *         &lt;element name="firstNotionalStepDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="lastNotionalStepDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="notionalStepAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="notionalStepRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="stepRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}StepRelativeToEnum" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotionalStepRule", propOrder = {
    "calculationPeriodDatesReference",
    "stepFrequency",
    "firstNotionalStepDate",
    "lastNotionalStepDate",
    "notionalStepAmount",
    "notionalStepRate",
    "stepRelativeTo"
})
public class NotionalStepRule {

    protected CalculationPeriodDatesReference calculationPeriodDatesReference;
    protected Period stepFrequency;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstNotionalStepDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastNotionalStepDate;
    protected BigDecimal notionalStepAmount;
    protected BigDecimal notionalStepRate;
    protected StepRelativeToEnum stepRelativeTo;

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public CalculationPeriodDatesReference getCalculationPeriodDatesReference() {
        return calculationPeriodDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public void setCalculationPeriodDatesReference(CalculationPeriodDatesReference value) {
        this.calculationPeriodDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété stepFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getStepFrequency() {
        return stepFrequency;
    }

    /**
     * Définit la valeur de la propriété stepFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setStepFrequency(Period value) {
        this.stepFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété firstNotionalStepDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstNotionalStepDate() {
        return firstNotionalStepDate;
    }

    /**
     * Définit la valeur de la propriété firstNotionalStepDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstNotionalStepDate(XMLGregorianCalendar value) {
        this.firstNotionalStepDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastNotionalStepDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastNotionalStepDate() {
        return lastNotionalStepDate;
    }

    /**
     * Définit la valeur de la propriété lastNotionalStepDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastNotionalStepDate(XMLGregorianCalendar value) {
        this.lastNotionalStepDate = value;
    }

    /**
     * Obtient la valeur de la propriété notionalStepAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotionalStepAmount() {
        return notionalStepAmount;
    }

    /**
     * Définit la valeur de la propriété notionalStepAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotionalStepAmount(BigDecimal value) {
        this.notionalStepAmount = value;
    }

    /**
     * Obtient la valeur de la propriété notionalStepRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotionalStepRate() {
        return notionalStepRate;
    }

    /**
     * Définit la valeur de la propriété notionalStepRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotionalStepRate(BigDecimal value) {
        this.notionalStepRate = value;
    }

    /**
     * Obtient la valeur de la propriété stepRelativeTo.
     * 
     * @return
     *     possible object is
     *     {@link StepRelativeToEnum }
     *     
     */
    public StepRelativeToEnum getStepRelativeTo() {
        return stepRelativeTo;
    }

    /**
     * Définit la valeur de la propriété stepRelativeTo.
     * 
     * @param value
     *     allowed object is
     *     {@link StepRelativeToEnum }
     *     
     */
    public void setStepRelativeTo(StepRelativeToEnum value) {
        this.stepRelativeTo = value;
    }

}
