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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CashSettlementTerms complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CashSettlementTerms">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}SettlementTerms">
 *       &lt;sequence>
 *         &lt;element name="valuationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationDate" minOccurs="0"/>
 *         &lt;element name="valuationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="quotationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotationRateTypeEnum" minOccurs="0"/>
 *         &lt;element name="quotationAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="minimumQuotationAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="dealer" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cashSettlementBusinessDays" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FixedRecovery.model" minOccurs="0"/>
 *         &lt;element name="fixedSettlement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="accruedInterest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="valuationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationMethodEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CashSettlementTerms", propOrder = {
    "valuationDate",
    "valuationTime",
    "quotationMethod",
    "quotationAmount",
    "minimumQuotationAmount",
    "dealer",
    "cashSettlementBusinessDays",
    "cashSettlementAmount",
    "recoveryFactor",
    "fixedSettlement",
    "accruedInterest",
    "valuationMethod"
})
public class CashSettlementTerms
    extends SettlementTerms
{

    protected ValuationDate valuationDate;
    protected BusinessCenterTime valuationTime;
    protected QuotationRateTypeEnum quotationMethod;
    protected Money quotationAmount;
    protected Money minimumQuotationAmount;
    protected List<String> dealer;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cashSettlementBusinessDays;
    protected Money cashSettlementAmount;
    protected BigDecimal recoveryFactor;
    protected Boolean fixedSettlement;
    protected Boolean accruedInterest;
    protected ValuationMethodEnum valuationMethod;

    /**
     * Obtient la valeur de la propriété valuationDate.
     * 
     * @return
     *     possible object is
     *     {@link ValuationDate }
     *     
     */
    public ValuationDate getValuationDate() {
        return valuationDate;
    }

    /**
     * Définit la valeur de la propriété valuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationDate }
     *     
     */
    public void setValuationDate(ValuationDate value) {
        this.valuationDate = value;
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
     * Obtient la valeur de la propriété quotationMethod.
     * 
     * @return
     *     possible object is
     *     {@link QuotationRateTypeEnum }
     *     
     */
    public QuotationRateTypeEnum getQuotationMethod() {
        return quotationMethod;
    }

    /**
     * Définit la valeur de la propriété quotationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotationRateTypeEnum }
     *     
     */
    public void setQuotationMethod(QuotationRateTypeEnum value) {
        this.quotationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété quotationAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getQuotationAmount() {
        return quotationAmount;
    }

    /**
     * Définit la valeur de la propriété quotationAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setQuotationAmount(Money value) {
        this.quotationAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minimumQuotationAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getMinimumQuotationAmount() {
        return minimumQuotationAmount;
    }

    /**
     * Définit la valeur de la propriété minimumQuotationAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setMinimumQuotationAmount(Money value) {
        this.minimumQuotationAmount = value;
    }

    /**
     * Gets the value of the dealer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dealer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDealer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDealer() {
        if (dealer == null) {
            dealer = new ArrayList<String>();
        }
        return this.dealer;
    }

    /**
     * Obtient la valeur de la propriété cashSettlementBusinessDays.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCashSettlementBusinessDays() {
        return cashSettlementBusinessDays;
    }

    /**
     * Définit la valeur de la propriété cashSettlementBusinessDays.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCashSettlementBusinessDays(BigInteger value) {
        this.cashSettlementBusinessDays = value;
    }

    /**
     * Obtient la valeur de la propriété cashSettlementAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getCashSettlementAmount() {
        return cashSettlementAmount;
    }

    /**
     * Définit la valeur de la propriété cashSettlementAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setCashSettlementAmount(Money value) {
        this.cashSettlementAmount = value;
    }

    /**
     * Obtient la valeur de la propriété recoveryFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecoveryFactor() {
        return recoveryFactor;
    }

    /**
     * Définit la valeur de la propriété recoveryFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecoveryFactor(BigDecimal value) {
        this.recoveryFactor = value;
    }

    /**
     * Obtient la valeur de la propriété fixedSettlement.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFixedSettlement() {
        return fixedSettlement;
    }

    /**
     * Définit la valeur de la propriété fixedSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFixedSettlement(Boolean value) {
        this.fixedSettlement = value;
    }

    /**
     * Obtient la valeur de la propriété accruedInterest.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccruedInterest() {
        return accruedInterest;
    }

    /**
     * Définit la valeur de la propriété accruedInterest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccruedInterest(Boolean value) {
        this.accruedInterest = value;
    }

    /**
     * Obtient la valeur de la propriété valuationMethod.
     * 
     * @return
     *     possible object is
     *     {@link ValuationMethodEnum }
     *     
     */
    public ValuationMethodEnum getValuationMethod() {
        return valuationMethod;
    }

    /**
     * Définit la valeur de la propriété valuationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationMethodEnum }
     *     
     */
    public void setValuationMethod(ValuationMethodEnum value) {
        this.valuationMethod = value;
    }

}
