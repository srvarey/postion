//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type defining the adjusted payment date and associated calculation period parameters required to calculate the actual or projected payment amount. This type forms part of the cashflow representation of a swap stream.
 * 
 * <p>Classe Java pour PaymentCalculationPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PaymentCalculationPeriod">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentBase">
 *       &lt;sequence>
 *         &lt;element name="unadjustedPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="adjustedPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="calculationPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriod" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="fixedPaymentAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="discountFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="forecastPaymentAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="presentValueAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentCalculationPeriod", propOrder = {
    "unadjustedPaymentDate",
    "adjustedPaymentDate",
    "calculationPeriod",
    "fixedPaymentAmount",
    "discountFactor",
    "forecastPaymentAmount",
    "presentValueAmount"
})
public class PaymentCalculationPeriod
    extends PaymentBase
{

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar unadjustedPaymentDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedPaymentDate;
    protected List<CalculationPeriod> calculationPeriod;
    protected BigDecimal fixedPaymentAmount;
    protected BigDecimal discountFactor;
    protected Money forecastPaymentAmount;
    protected Money presentValueAmount;
    @XmlAttribute(name = "href")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object href;

    /**
     * Obtient la valeur de la propriété unadjustedPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUnadjustedPaymentDate() {
        return unadjustedPaymentDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUnadjustedPaymentDate(XMLGregorianCalendar value) {
        this.unadjustedPaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedPaymentDate() {
        return adjustedPaymentDate;
    }

    /**
     * Définit la valeur de la propriété adjustedPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedPaymentDate(XMLGregorianCalendar value) {
        this.adjustedPaymentDate = value;
    }

    /**
     * Gets the value of the calculationPeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calculationPeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalculationPeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalculationPeriod }
     * 
     * 
     */
    public List<CalculationPeriod> getCalculationPeriod() {
        if (calculationPeriod == null) {
            calculationPeriod = new ArrayList<CalculationPeriod>();
        }
        return this.calculationPeriod;
    }

    /**
     * Obtient la valeur de la propriété fixedPaymentAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedPaymentAmount() {
        return fixedPaymentAmount;
    }

    /**
     * Définit la valeur de la propriété fixedPaymentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedPaymentAmount(BigDecimal value) {
        this.fixedPaymentAmount = value;
    }

    /**
     * Obtient la valeur de la propriété discountFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountFactor() {
        return discountFactor;
    }

    /**
     * Définit la valeur de la propriété discountFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountFactor(BigDecimal value) {
        this.discountFactor = value;
    }

    /**
     * Obtient la valeur de la propriété forecastPaymentAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getForecastPaymentAmount() {
        return forecastPaymentAmount;
    }

    /**
     * Définit la valeur de la propriété forecastPaymentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setForecastPaymentAmount(Money value) {
        this.forecastPaymentAmount = value;
    }

    /**
     * Obtient la valeur de la propriété presentValueAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getPresentValueAmount() {
        return presentValueAmount;
    }

    /**
     * Définit la valeur de la propriété presentValueAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setPresentValueAmount(Money value) {
        this.presentValueAmount = value;
    }

    /**
     * Obtient la valeur de la propriété href.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHref() {
        return href;
    }

    /**
     * Définit la valeur de la propriété href.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHref(Object value) {
        this.href = value;
    }

}
