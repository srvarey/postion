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
import javax.xml.bind.annotation.XmlType;


/**
 * Type to represent a single pass through payment.
 * 
 * <p>Classe Java pour PassThroughItem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PassThroughItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="underlyerReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetReference" minOccurs="0"/>
 *         &lt;element name="passThroughPercentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PassThroughItem", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "underlyerReference",
    "passThroughPercentage"
})
public class PassThroughItem {

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    protected AssetReference underlyerReference;
    protected BigDecimal passThroughPercentage;

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
     * Obtient la valeur de la propriété underlyerReference.
     * 
     * @return
     *     possible object is
     *     {@link AssetReference }
     *     
     */
    public AssetReference getUnderlyerReference() {
        return underlyerReference;
    }

    /**
     * Définit la valeur de la propriété underlyerReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetReference }
     *     
     */
    public void setUnderlyerReference(AssetReference value) {
        this.underlyerReference = value;
    }

    /**
     * Obtient la valeur de la propriété passThroughPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPassThroughPercentage() {
        return passThroughPercentage;
    }

    /**
     * Définit la valeur de la propriété passThroughPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPassThroughPercentage(BigDecimal value) {
        this.passThroughPercentage = value;
    }

}
