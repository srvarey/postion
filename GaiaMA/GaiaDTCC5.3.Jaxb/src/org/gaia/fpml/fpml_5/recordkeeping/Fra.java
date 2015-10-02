//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type defining a Forward Rate Agreement (FRA) product.
 * 
 * <p>Classe Java pour Fra complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Fra">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element name="adjustedEffectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RequiredIdentifierDate"/>
 *         &lt;element name="adjustedTerminationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="paymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate" minOccurs="0"/>
 *         &lt;element name="fixingDateOffset" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *         &lt;element name="dayCountFraction" type="{http://www.fpml.org/FpML-5/recordkeeping}DayCountFraction"/>
 *         &lt;element name="calculationPeriodNumberOfDays" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}Money"/>
 *         &lt;element name="fixedRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="floatingRateIndex" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateIndex"/>
 *         &lt;element name="indexTenor" type="{http://www.fpml.org/FpML-5/recordkeeping}Period" maxOccurs="unbounded"/>
 *         &lt;element name="fraDiscounting" type="{http://www.fpml.org/FpML-5/recordkeeping}FraDiscountingEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fra", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "adjustedEffectiveDate",
    "adjustedTerminationDate",
    "paymentDate",
    "fixingDateOffset",
    "dayCountFraction",
    "calculationPeriodNumberOfDays",
    "notional",
    "fixedRate",
    "floatingRateIndex",
    "indexTenor",
    "fraDiscounting"
})
public class Fra
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    @XmlElement(required = true)
    protected RequiredIdentifierDate adjustedEffectiveDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedTerminationDate;
    protected AdjustableDate paymentDate;
    protected RelativeDateOffset fixingDateOffset;
    @XmlElement(required = true)
    protected DayCountFraction dayCountFraction;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger calculationPeriodNumberOfDays;
    @XmlElement(required = true)
    protected Money notional;
    @XmlElement(required = true)
    protected BigDecimal fixedRate;
    @XmlElement(required = true)
    protected FloatingRateIndex floatingRateIndex;
    @XmlElement(required = true)
    protected List<Period> indexTenor;
    protected FraDiscountingEnum fraDiscounting;

    /**
     * Obtient la valeur de la propriété buyerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getBuyerPartyReference() {
        return buyerPartyReference;
    }

    /**
     * Définit la valeur de la propriété buyerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setBuyerPartyReference(PartyReference value) {
        this.buyerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété buyerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getBuyerAccountReference() {
        return buyerAccountReference;
    }

    /**
     * Définit la valeur de la propriété buyerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setBuyerAccountReference(AccountReference value) {
        this.buyerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getSellerPartyReference() {
        return sellerPartyReference;
    }

    /**
     * Définit la valeur de la propriété sellerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setSellerPartyReference(PartyReference value) {
        this.sellerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getSellerAccountReference() {
        return sellerAccountReference;
    }

    /**
     * Définit la valeur de la propriété sellerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setSellerAccountReference(AccountReference value) {
        this.sellerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedEffectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link RequiredIdentifierDate }
     *     
     */
    public RequiredIdentifierDate getAdjustedEffectiveDate() {
        return adjustedEffectiveDate;
    }

    /**
     * Définit la valeur de la propriété adjustedEffectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiredIdentifierDate }
     *     
     */
    public void setAdjustedEffectiveDate(RequiredIdentifierDate value) {
        this.adjustedEffectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedTerminationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedTerminationDate() {
        return adjustedTerminationDate;
    }

    /**
     * Définit la valeur de la propriété adjustedTerminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedTerminationDate(XMLGregorianCalendar value) {
        this.adjustedTerminationDate = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Définit la valeur de la propriété paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setPaymentDate(AdjustableDate value) {
        this.paymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété fixingDateOffset.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getFixingDateOffset() {
        return fixingDateOffset;
    }

    /**
     * Définit la valeur de la propriété fixingDateOffset.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setFixingDateOffset(RelativeDateOffset value) {
        this.fixingDateOffset = value;
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
     * Obtient la valeur de la propriété notional.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getNotional() {
        return notional;
    }

    /**
     * Définit la valeur de la propriété notional.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setNotional(Money value) {
        this.notional = value;
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
     * Obtient la valeur de la propriété floatingRateIndex.
     * 
     * @return
     *     possible object is
     *     {@link FloatingRateIndex }
     *     
     */
    public FloatingRateIndex getFloatingRateIndex() {
        return floatingRateIndex;
    }

    /**
     * Définit la valeur de la propriété floatingRateIndex.
     * 
     * @param value
     *     allowed object is
     *     {@link FloatingRateIndex }
     *     
     */
    public void setFloatingRateIndex(FloatingRateIndex value) {
        this.floatingRateIndex = value;
    }

    /**
     * Gets the value of the indexTenor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indexTenor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndexTenor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Period }
     * 
     * 
     */
    public List<Period> getIndexTenor() {
        if (indexTenor == null) {
            indexTenor = new ArrayList<Period>();
        }
        return this.indexTenor;
    }

    /**
     * Obtient la valeur de la propriété fraDiscounting.
     * 
     * @return
     *     possible object is
     *     {@link FraDiscountingEnum }
     *     
     */
    public FraDiscountingEnum getFraDiscounting() {
        return fraDiscounting;
    }

    /**
     * Définit la valeur de la propriété fraDiscounting.
     * 
     * @param value
     *     allowed object is
     *     {@link FraDiscountingEnum }
     *     
     */
    public void setFraDiscounting(FraDiscountingEnum value) {
        this.fraDiscounting = value;
    }

}
