//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Specifies each of the characteristics of the principal exchange cashflows, in terms of paying/receiving counterparties, amounts and dates.
 * 
 * <p>Classe Java pour PrincipalExchangeDescriptions complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PrincipalExchangeDescriptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="principalExchangeAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}PrincipalExchangeAmount" minOccurs="0"/>
 *         &lt;element name="principalExchangeDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrincipalExchangeDescriptions", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "principalExchangeAmount",
    "principalExchangeDate"
})
public class PrincipalExchangeDescriptions {

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    protected PrincipalExchangeAmount principalExchangeAmount;
    protected AdjustableOrRelativeDate principalExchangeDate;

    /**
     * Obtient la valeur de la propriété payerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getPayerPartyReference() {
        return payerPartyReference;
    }

    /**
     * Définit la valeur de la propriété payerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setPayerPartyReference(PartyReference value) {
        this.payerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété payerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getPayerAccountReference() {
        return payerAccountReference;
    }

    /**
     * Définit la valeur de la propriété payerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setPayerAccountReference(AccountReference value) {
        this.payerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getReceiverPartyReference() {
        return receiverPartyReference;
    }

    /**
     * Définit la valeur de la propriété receiverPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setReceiverPartyReference(PartyReference value) {
        this.receiverPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getReceiverAccountReference() {
        return receiverAccountReference;
    }

    /**
     * Définit la valeur de la propriété receiverAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setReceiverAccountReference(AccountReference value) {
        this.receiverAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété principalExchangeAmount.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalExchangeAmount }
     *     
     */
    public PrincipalExchangeAmount getPrincipalExchangeAmount() {
        return principalExchangeAmount;
    }

    /**
     * Définit la valeur de la propriété principalExchangeAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalExchangeAmount }
     *     
     */
    public void setPrincipalExchangeAmount(PrincipalExchangeAmount value) {
        this.principalExchangeAmount = value;
    }

    /**
     * Obtient la valeur de la propriété principalExchangeDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getPrincipalExchangeDate() {
        return principalExchangeDate;
    }

    /**
     * Définit la valeur de la propriété principalExchangeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setPrincipalExchangeDate(AdjustableOrRelativeDate value) {
        this.principalExchangeDate = value;
    }

}
