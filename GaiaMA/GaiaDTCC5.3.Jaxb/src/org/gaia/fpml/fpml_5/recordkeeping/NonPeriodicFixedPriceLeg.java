//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The details of a fixed payment. Can be used for a forward transaction or as the base for a more complex fixed leg component such as the fixed leg of a swap.
 * 
 * <p>Classe Java pour NonPeriodicFixedPriceLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NonPeriodicFixedPriceLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}CommoditySwapLeg">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="fixedPrice" type="{http://www.fpml.org/FpML-5/recordkeeping}FixedPrice"/>
 *         &lt;element name="totalPrice" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *         &lt;element name="quantityReference" type="{http://www.fpml.org/FpML-5/recordkeeping}QuantityReference" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPaymentDates.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonPeriodicFixedPriceLeg", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "fixedPrice",
    "totalPrice",
    "quantityReference",
    "relativePaymentDates",
    "paymentDates",
    "masterAgreementPaymentDates"
})
public class NonPeriodicFixedPriceLeg
    extends CommoditySwapLeg
{

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    @XmlElement(required = true)
    protected FixedPrice fixedPrice;
    protected NonNegativeMoney totalPrice;
    protected QuantityReference quantityReference;
    protected CommodityRelativePaymentDates relativePaymentDates;
    protected AdjustableDatesOrRelativeDateOffset paymentDates;
    protected Boolean masterAgreementPaymentDates;

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
     * Obtient la valeur de la propriété fixedPrice.
     * 
     * @return
     *     possible object is
     *     {@link FixedPrice }
     *     
     */
    public FixedPrice getFixedPrice() {
        return fixedPrice;
    }

    /**
     * Définit la valeur de la propriété fixedPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link FixedPrice }
     *     
     */
    public void setFixedPrice(FixedPrice value) {
        this.fixedPrice = value;
    }

    /**
     * Obtient la valeur de la propriété totalPrice.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getTotalPrice() {
        return totalPrice;
    }

    /**
     * Définit la valeur de la propriété totalPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setTotalPrice(NonNegativeMoney value) {
        this.totalPrice = value;
    }

    /**
     * Obtient la valeur de la propriété quantityReference.
     * 
     * @return
     *     possible object is
     *     {@link QuantityReference }
     *     
     */
    public QuantityReference getQuantityReference() {
        return quantityReference;
    }

    /**
     * Définit la valeur de la propriété quantityReference.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityReference }
     *     
     */
    public void setQuantityReference(QuantityReference value) {
        this.quantityReference = value;
    }

    /**
     * Obtient la valeur de la propriété relativePaymentDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityRelativePaymentDates }
     *     
     */
    public CommodityRelativePaymentDates getRelativePaymentDates() {
        return relativePaymentDates;
    }

    /**
     * Définit la valeur de la propriété relativePaymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityRelativePaymentDates }
     *     
     */
    public void setRelativePaymentDates(CommodityRelativePaymentDates value) {
        this.relativePaymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public AdjustableDatesOrRelativeDateOffset getPaymentDates() {
        return paymentDates;
    }

    /**
     * Définit la valeur de la propriété paymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDatesOrRelativeDateOffset }
     *     
     */
    public void setPaymentDates(AdjustableDatesOrRelativeDateOffset value) {
        this.paymentDates = value;
    }

    /**
     * Obtient la valeur de la propriété masterAgreementPaymentDates.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMasterAgreementPaymentDates() {
        return masterAgreementPaymentDates;
    }

    /**
     * Définit la valeur de la propriété masterAgreementPaymentDates.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMasterAgreementPaymentDates(Boolean value) {
        this.masterAgreementPaymentDates = value;
    }

}
