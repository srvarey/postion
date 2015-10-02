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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A structure describing an option exercise.
 * 
 * <p>Classe Java pour OptionExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OptionExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}AbstractEvent">
 *       &lt;sequence>
 *         &lt;element name="optionSeller" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="optionBuyer" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="tradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exerciseDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="exerciseTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="expiry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *           &lt;element name="fullExercise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="exerciseInNotionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *             &lt;element name="outstandingNotionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="exerciseInNumberOfOptions" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="outstandingNumberOfOptions" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="exerciseInNumberOfUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="outstandingNumberOfUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="settlementType" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementTypeEnum" minOccurs="0"/>
 *           &lt;element name="cashSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}SimplePayment"/>
 *           &lt;element name="physicalSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalSettlement"/>
 *         &lt;/choice>
 *         &lt;element name="payment" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativePayment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionExercise", propOrder = {
    "optionSeller",
    "optionBuyer",
    "tradeIdentifier",
    "exerciseDate",
    "exerciseTime",
    "expiry",
    "fullExercise",
    "exerciseInNotionalAmount",
    "outstandingNotionalAmount",
    "exerciseInNumberOfOptions",
    "outstandingNumberOfOptions",
    "exerciseInNumberOfUnits",
    "outstandingNumberOfUnits",
    "settlementType",
    "cashSettlement",
    "physicalSettlement",
    "payment"
})
public class OptionExercise
    extends AbstractEvent
{

    protected PartyReference optionSeller;
    protected PartyReference optionBuyer;
    protected List<PartyTradeIdentifier> tradeIdentifier;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar exerciseDate;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar exerciseTime;
    protected Boolean expiry;
    protected Boolean fullExercise;
    protected Money exerciseInNotionalAmount;
    protected Money outstandingNotionalAmount;
    protected BigDecimal exerciseInNumberOfOptions;
    protected BigDecimal outstandingNumberOfOptions;
    protected BigDecimal exerciseInNumberOfUnits;
    protected BigDecimal outstandingNumberOfUnits;
    protected String settlementType;
    protected SimplePayment cashSettlement;
    protected PhysicalSettlement physicalSettlement;
    protected NonNegativePayment payment;

    /**
     * Obtient la valeur de la propriété optionSeller.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getOptionSeller() {
        return optionSeller;
    }

    /**
     * Définit la valeur de la propriété optionSeller.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setOptionSeller(PartyReference value) {
        this.optionSeller = value;
    }

    /**
     * Obtient la valeur de la propriété optionBuyer.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getOptionBuyer() {
        return optionBuyer;
    }

    /**
     * Définit la valeur de la propriété optionBuyer.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setOptionBuyer(PartyReference value) {
        this.optionBuyer = value;
    }

    /**
     * Gets the value of the tradeIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tradeIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTradeIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyTradeIdentifier }
     * 
     * 
     */
    public List<PartyTradeIdentifier> getTradeIdentifier() {
        if (tradeIdentifier == null) {
            tradeIdentifier = new ArrayList<PartyTradeIdentifier>();
        }
        return this.tradeIdentifier;
    }

    /**
     * Obtient la valeur de la propriété exerciseDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExerciseDate() {
        return exerciseDate;
    }

    /**
     * Définit la valeur de la propriété exerciseDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExerciseDate(XMLGregorianCalendar value) {
        this.exerciseDate = value;
    }

    /**
     * Obtient la valeur de la propriété exerciseTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExerciseTime() {
        return exerciseTime;
    }

    /**
     * Définit la valeur de la propriété exerciseTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExerciseTime(XMLGregorianCalendar value) {
        this.exerciseTime = value;
    }

    /**
     * Obtient la valeur de la propriété expiry.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpiry() {
        return expiry;
    }

    /**
     * Définit la valeur de la propriété expiry.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpiry(Boolean value) {
        this.expiry = value;
    }

    /**
     * Obtient la valeur de la propriété fullExercise.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFullExercise() {
        return fullExercise;
    }

    /**
     * Définit la valeur de la propriété fullExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFullExercise(Boolean value) {
        this.fullExercise = value;
    }

    /**
     * Obtient la valeur de la propriété exerciseInNotionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getExerciseInNotionalAmount() {
        return exerciseInNotionalAmount;
    }

    /**
     * Définit la valeur de la propriété exerciseInNotionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setExerciseInNotionalAmount(Money value) {
        this.exerciseInNotionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété outstandingNotionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getOutstandingNotionalAmount() {
        return outstandingNotionalAmount;
    }

    /**
     * Définit la valeur de la propriété outstandingNotionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setOutstandingNotionalAmount(Money value) {
        this.outstandingNotionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété exerciseInNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExerciseInNumberOfOptions() {
        return exerciseInNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété exerciseInNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExerciseInNumberOfOptions(BigDecimal value) {
        this.exerciseInNumberOfOptions = value;
    }

    /**
     * Obtient la valeur de la propriété outstandingNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutstandingNumberOfOptions() {
        return outstandingNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété outstandingNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutstandingNumberOfOptions(BigDecimal value) {
        this.outstandingNumberOfOptions = value;
    }

    /**
     * Obtient la valeur de la propriété exerciseInNumberOfUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExerciseInNumberOfUnits() {
        return exerciseInNumberOfUnits;
    }

    /**
     * Définit la valeur de la propriété exerciseInNumberOfUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExerciseInNumberOfUnits(BigDecimal value) {
        this.exerciseInNumberOfUnits = value;
    }

    /**
     * Obtient la valeur de la propriété outstandingNumberOfUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutstandingNumberOfUnits() {
        return outstandingNumberOfUnits;
    }

    /**
     * Définit la valeur de la propriété outstandingNumberOfUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutstandingNumberOfUnits(BigDecimal value) {
        this.outstandingNumberOfUnits = value;
    }

    /**
     * Obtient la valeur de la propriété settlementType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementType() {
        return settlementType;
    }

    /**
     * Définit la valeur de la propriété settlementType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementType(String value) {
        this.settlementType = value;
    }

    /**
     * Obtient la valeur de la propriété cashSettlement.
     * 
     * @return
     *     possible object is
     *     {@link SimplePayment }
     *     
     */
    public SimplePayment getCashSettlement() {
        return cashSettlement;
    }

    /**
     * Définit la valeur de la propriété cashSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link SimplePayment }
     *     
     */
    public void setCashSettlement(SimplePayment value) {
        this.cashSettlement = value;
    }

    /**
     * Obtient la valeur de la propriété physicalSettlement.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalSettlement }
     *     
     */
    public PhysicalSettlement getPhysicalSettlement() {
        return physicalSettlement;
    }

    /**
     * Définit la valeur de la propriété physicalSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalSettlement }
     *     
     */
    public void setPhysicalSettlement(PhysicalSettlement value) {
        this.physicalSettlement = value;
    }

    /**
     * Obtient la valeur de la propriété payment.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativePayment }
     *     
     */
    public NonNegativePayment getPayment() {
        return payment;
    }

    /**
     * Définit la valeur de la propriété payment.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativePayment }
     *     
     */
    public void setPayment(NonNegativePayment value) {
        this.payment = value;
    }

}
