//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Simple product representation providing key information about a variety of different products
 * 
 * <p>Classe Java pour GenericProduct complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GenericProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="multiLeg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *           &lt;element name="counterpartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="2" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}SimplePayment" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate2" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate2" minOccurs="0"/>
 *         &lt;element name="terminationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate2" minOccurs="0"/>
 *         &lt;element name="underlyer" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeUnderlyer2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}CashflowNotional" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="optionType" type="{http://www.fpml.org/FpML-5/recordkeeping}OptionType" minOccurs="0"/>
 *         &lt;element name="settlementCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedCurrency" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericProduct", propOrder = {
    "multiLeg",
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "counterpartyReference",
    "premium",
    "effectiveDate",
    "expirationDate",
    "terminationDate",
    "underlyer",
    "notional",
    "optionType",
    "settlementCurrency"
})
public class GenericProduct
    extends Product
{

    protected Boolean multiLeg;
    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    protected List<PartyReference> counterpartyReference;
    protected SimplePayment premium;
    protected AdjustableDate2 effectiveDate;
    protected AdjustableDate2 expirationDate;
    protected AdjustableDate2 terminationDate;
    protected List<TradeUnderlyer2> underlyer;
    protected List<CashflowNotional> notional;
    protected OptionType optionType;
    protected List<IdentifiedCurrency> settlementCurrency;

    /**
     * Obtient la valeur de la propriété multiLeg.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultiLeg() {
        return multiLeg;
    }

    /**
     * Définit la valeur de la propriété multiLeg.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultiLeg(Boolean value) {
        this.multiLeg = value;
    }

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
     * Gets the value of the counterpartyReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the counterpartyReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCounterpartyReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getCounterpartyReference() {
        if (counterpartyReference == null) {
            counterpartyReference = new ArrayList<PartyReference>();
        }
        return this.counterpartyReference;
    }

    /**
     * Obtient la valeur de la propriété premium.
     * 
     * @return
     *     possible object is
     *     {@link SimplePayment }
     *     
     */
    public SimplePayment getPremium() {
        return premium;
    }

    /**
     * Définit la valeur de la propriété premium.
     * 
     * @param value
     *     allowed object is
     *     {@link SimplePayment }
     *     
     */
    public void setPremium(SimplePayment value) {
        this.premium = value;
    }

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate2 }
     *     
     */
    public AdjustableDate2 getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate2 }
     *     
     */
    public void setEffectiveDate(AdjustableDate2 value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété expirationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate2 }
     *     
     */
    public AdjustableDate2 getExpirationDate() {
        return expirationDate;
    }

    /**
     * Définit la valeur de la propriété expirationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate2 }
     *     
     */
    public void setExpirationDate(AdjustableDate2 value) {
        this.expirationDate = value;
    }

    /**
     * Obtient la valeur de la propriété terminationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate2 }
     *     
     */
    public AdjustableDate2 getTerminationDate() {
        return terminationDate;
    }

    /**
     * Définit la valeur de la propriété terminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate2 }
     *     
     */
    public void setTerminationDate(AdjustableDate2 value) {
        this.terminationDate = value;
    }

    /**
     * Gets the value of the underlyer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the underlyer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnderlyer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeUnderlyer2 }
     * 
     * 
     */
    public List<TradeUnderlyer2> getUnderlyer() {
        if (underlyer == null) {
            underlyer = new ArrayList<TradeUnderlyer2>();
        }
        return this.underlyer;
    }

    /**
     * Gets the value of the notional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CashflowNotional }
     * 
     * 
     */
    public List<CashflowNotional> getNotional() {
        if (notional == null) {
            notional = new ArrayList<CashflowNotional>();
        }
        return this.notional;
    }

    /**
     * Obtient la valeur de la propriété optionType.
     * 
     * @return
     *     possible object is
     *     {@link OptionType }
     *     
     */
    public OptionType getOptionType() {
        return optionType;
    }

    /**
     * Définit la valeur de la propriété optionType.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionType }
     *     
     */
    public void setOptionType(OptionType value) {
        this.optionType = value;
    }

    /**
     * Gets the value of the settlementCurrency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settlementCurrency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettlementCurrency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifiedCurrency }
     * 
     * 
     */
    public List<IdentifiedCurrency> getSettlementCurrency() {
        if (settlementCurrency == null) {
            settlementCurrency = new ArrayList<IdentifiedCurrency>();
        }
        return this.settlementCurrency;
    }

}
