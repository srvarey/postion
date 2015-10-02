//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * The economics of a trade of a multiply traded instrument.
 * 
 * <p>Classe Java pour InstrumentTradeDetails complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InstrumentTradeDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}underlyingAsset" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.fpml.org/FpML-5/recordkeeping}InstrumentTradeQuantity" minOccurs="0"/>
 *         &lt;element name="pricing" type="{http://www.fpml.org/FpML-5/recordkeeping}InstrumentTradePricing" minOccurs="0"/>
 *         &lt;element name="principal" type="{http://www.fpml.org/FpML-5/recordkeeping}InstrumentTradePrincipal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstrumentTradeDetails", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "underlyingAsset",
    "quantity",
    "pricing",
    "principal"
})
public class InstrumentTradeDetails
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    @XmlElementRef(name = "underlyingAsset", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Asset> underlyingAsset;
    protected InstrumentTradeQuantity quantity;
    protected InstrumentTradePricing pricing;
    protected InstrumentTradePrincipal principal;

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
     * The FpML asset description for the asset.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Loan }{@code >}
     *     {@link JAXBElement }{@code <}{@link Basket }{@code >}
     *     {@link JAXBElement }{@code <}{@link Bond }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConvertibleBond }{@code >}
     *     {@link JAXBElement }{@code <}{@link Index }{@code >}
     *     {@link JAXBElement }{@code <}{@link Mortgage }{@code >}
     *     {@link JAXBElement }{@code <}{@link Commodity }{@code >}
     *     {@link JAXBElement }{@code <}{@link Asset }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityAsset }{@code >}
     *     {@link JAXBElement }{@code <}{@link Future }{@code >}
     *     {@link JAXBElement }{@code <}{@link MutualFund }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExchangeTradedFund }{@code >}
     *     {@link JAXBElement }{@code <}{@link Cash }{@code >}
     *     
     */
    public JAXBElement<? extends Asset> getUnderlyingAsset() {
        return underlyingAsset;
    }

    /**
     * Définit la valeur de la propriété underlyingAsset.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Loan }{@code >}
     *     {@link JAXBElement }{@code <}{@link Basket }{@code >}
     *     {@link JAXBElement }{@code <}{@link Bond }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConvertibleBond }{@code >}
     *     {@link JAXBElement }{@code <}{@link Index }{@code >}
     *     {@link JAXBElement }{@code <}{@link Mortgage }{@code >}
     *     {@link JAXBElement }{@code <}{@link Commodity }{@code >}
     *     {@link JAXBElement }{@code <}{@link Asset }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityAsset }{@code >}
     *     {@link JAXBElement }{@code <}{@link Future }{@code >}
     *     {@link JAXBElement }{@code <}{@link MutualFund }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExchangeTradedFund }{@code >}
     *     {@link JAXBElement }{@code <}{@link Cash }{@code >}
     *     
     */
    public void setUnderlyingAsset(JAXBElement<? extends Asset> value) {
        this.underlyingAsset = value;
    }

    /**
     * Obtient la valeur de la propriété quantity.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentTradeQuantity }
     *     
     */
    public InstrumentTradeQuantity getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentTradeQuantity }
     *     
     */
    public void setQuantity(InstrumentTradeQuantity value) {
        this.quantity = value;
    }

    /**
     * Obtient la valeur de la propriété pricing.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentTradePricing }
     *     
     */
    public InstrumentTradePricing getPricing() {
        return pricing;
    }

    /**
     * Définit la valeur de la propriété pricing.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentTradePricing }
     *     
     */
    public void setPricing(InstrumentTradePricing value) {
        this.pricing = value;
    }

    /**
     * Obtient la valeur de la propriété principal.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentTradePrincipal }
     *     
     */
    public InstrumentTradePrincipal getPrincipal() {
        return principal;
    }

    /**
     * Définit la valeur de la propriété principal.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentTradePrincipal }
     *     
     */
    public void setPrincipal(InstrumentTradePrincipal value) {
        this.principal = value;
    }

}
