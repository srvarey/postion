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
 * <p>Classe Java pour NonpublicExecutionReportRetracted complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NonpublicExecutionReportRetracted">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}NonCorrectableRequestMessage">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Events.model" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="primaryAssetClass" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetClass" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;element name="tradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" minOccurs="0"/>
 *               &lt;element name="eventIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessEventIdentifier" minOccurs="0"/>
 *             &lt;/choice>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartiesAndAccounts.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonpublicExecutionReportRetracted", propOrder = {
    "originatingEvent",
    "trade",
    "amendment",
    "increase",
    "terminatingEvent",
    "termination",
    "novation",
    "optionExercise",
    "optionExpiry",
    "deClear",
    "withdrawal",
    "additionalEvent",
    "primaryAssetClass",
    "tradeIdentifier",
    "eventIdentifier",
    "party",
    "account"
})
public class NonpublicExecutionReportRetracted
    extends NonCorrectableRequestMessage
{

    protected OriginatingEvent originatingEvent;
    protected Trade trade;
    protected TradeAmendmentContent amendment;
    protected TradeNotionalChange increase;
    protected TerminatingEvent terminatingEvent;
    protected TradeNotionalChange termination;
    protected TradeNovationContent novation;
    protected OptionExercise optionExercise;
    protected List<OptionExpiry> optionExpiry;
    protected DeClear deClear;
    protected Withdrawal withdrawal;
    protected AdditionalEvent additionalEvent;
    protected AssetClass primaryAssetClass;
    protected PartyTradeIdentifier tradeIdentifier;
    protected BusinessEventIdentifier eventIdentifier;
    protected List<Party> party;
    protected List<Account> account;

    /**
     * Obtient la valeur de la propriété originatingEvent.
     * 
     * @return
     *     possible object is
     *     {@link OriginatingEvent }
     *     
     */
    public OriginatingEvent getOriginatingEvent() {
        return originatingEvent;
    }

    /**
     * Définit la valeur de la propriété originatingEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginatingEvent }
     *     
     */
    public void setOriginatingEvent(OriginatingEvent value) {
        this.originatingEvent = value;
    }

    /**
     * Obtient la valeur de la propriété trade.
     * 
     * @return
     *     possible object is
     *     {@link Trade }
     *     
     */
    public Trade getTrade() {
        return trade;
    }

    /**
     * Définit la valeur de la propriété trade.
     * 
     * @param value
     *     allowed object is
     *     {@link Trade }
     *     
     */
    public void setTrade(Trade value) {
        this.trade = value;
    }

    /**
     * Obtient la valeur de la propriété amendment.
     * 
     * @return
     *     possible object is
     *     {@link TradeAmendmentContent }
     *     
     */
    public TradeAmendmentContent getAmendment() {
        return amendment;
    }

    /**
     * Définit la valeur de la propriété amendment.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeAmendmentContent }
     *     
     */
    public void setAmendment(TradeAmendmentContent value) {
        this.amendment = value;
    }

    /**
     * Obtient la valeur de la propriété increase.
     * 
     * @return
     *     possible object is
     *     {@link TradeNotionalChange }
     *     
     */
    public TradeNotionalChange getIncrease() {
        return increase;
    }

    /**
     * Définit la valeur de la propriété increase.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeNotionalChange }
     *     
     */
    public void setIncrease(TradeNotionalChange value) {
        this.increase = value;
    }

    /**
     * Obtient la valeur de la propriété terminatingEvent.
     * 
     * @return
     *     possible object is
     *     {@link TerminatingEvent }
     *     
     */
    public TerminatingEvent getTerminatingEvent() {
        return terminatingEvent;
    }

    /**
     * Définit la valeur de la propriété terminatingEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link TerminatingEvent }
     *     
     */
    public void setTerminatingEvent(TerminatingEvent value) {
        this.terminatingEvent = value;
    }

    /**
     * Obtient la valeur de la propriété termination.
     * 
     * @return
     *     possible object is
     *     {@link TradeNotionalChange }
     *     
     */
    public TradeNotionalChange getTermination() {
        return termination;
    }

    /**
     * Définit la valeur de la propriété termination.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeNotionalChange }
     *     
     */
    public void setTermination(TradeNotionalChange value) {
        this.termination = value;
    }

    /**
     * Obtient la valeur de la propriété novation.
     * 
     * @return
     *     possible object is
     *     {@link TradeNovationContent }
     *     
     */
    public TradeNovationContent getNovation() {
        return novation;
    }

    /**
     * Définit la valeur de la propriété novation.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeNovationContent }
     *     
     */
    public void setNovation(TradeNovationContent value) {
        this.novation = value;
    }

    /**
     * Obtient la valeur de la propriété optionExercise.
     * 
     * @return
     *     possible object is
     *     {@link OptionExercise }
     *     
     */
    public OptionExercise getOptionExercise() {
        return optionExercise;
    }

    /**
     * Définit la valeur de la propriété optionExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionExercise }
     *     
     */
    public void setOptionExercise(OptionExercise value) {
        this.optionExercise = value;
    }

    /**
     * Gets the value of the optionExpiry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionExpiry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionExpiry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionExpiry }
     * 
     * 
     */
    public List<OptionExpiry> getOptionExpiry() {
        if (optionExpiry == null) {
            optionExpiry = new ArrayList<OptionExpiry>();
        }
        return this.optionExpiry;
    }

    /**
     * Obtient la valeur de la propriété deClear.
     * 
     * @return
     *     possible object is
     *     {@link DeClear }
     *     
     */
    public DeClear getDeClear() {
        return deClear;
    }

    /**
     * Définit la valeur de la propriété deClear.
     * 
     * @param value
     *     allowed object is
     *     {@link DeClear }
     *     
     */
    public void setDeClear(DeClear value) {
        this.deClear = value;
    }

    /**
     * Obtient la valeur de la propriété withdrawal.
     * 
     * @return
     *     possible object is
     *     {@link Withdrawal }
     *     
     */
    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    /**
     * Définit la valeur de la propriété withdrawal.
     * 
     * @param value
     *     allowed object is
     *     {@link Withdrawal }
     *     
     */
    public void setWithdrawal(Withdrawal value) {
        this.withdrawal = value;
    }

    /**
     * The additionalEvent element is an extension/substitution point to customize FpML and add additional events.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalEvent }
     *     
     */
    public AdditionalEvent getAdditionalEvent() {
        return additionalEvent;
    }

    /**
     * Définit la valeur de la propriété additionalEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalEvent }
     *     
     */
    public void setAdditionalEvent(AdditionalEvent value) {
        this.additionalEvent = value;
    }

    /**
     * Obtient la valeur de la propriété primaryAssetClass.
     * 
     * @return
     *     possible object is
     *     {@link AssetClass }
     *     
     */
    public AssetClass getPrimaryAssetClass() {
        return primaryAssetClass;
    }

    /**
     * Définit la valeur de la propriété primaryAssetClass.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetClass }
     *     
     */
    public void setPrimaryAssetClass(AssetClass value) {
        this.primaryAssetClass = value;
    }

    /**
     * Obtient la valeur de la propriété tradeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link PartyTradeIdentifier }
     *     
     */
    public PartyTradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    /**
     * Définit la valeur de la propriété tradeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyTradeIdentifier }
     *     
     */
    public void setTradeIdentifier(PartyTradeIdentifier value) {
        this.tradeIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété eventIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link BusinessEventIdentifier }
     *     
     */
    public BusinessEventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }

    /**
     * Définit la valeur de la propriété eventIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessEventIdentifier }
     *     
     */
    public void setEventIdentifier(BusinessEventIdentifier value) {
        this.eventIdentifier = value;
    }

    /**
     * Gets the value of the party property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the party property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Party }
     * 
     * 
     */
    public List<Party> getParty() {
        if (party == null) {
            party = new ArrayList<Party>();
        }
        return this.party;
    }

    /**
     * Gets the value of the account property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the account property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Account }
     * 
     * 
     */
    public List<Account> getAccount() {
        if (account == null) {
            account = new ArrayList<Account>();
        }
        return this.account;
    }

}
