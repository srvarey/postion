//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2013.01.17 à 01:35:40 PM CET
//


package com.abc.extension_1_3.recordkeeping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.gaia.fpml.fpml_5.recordkeeping.AccountReference;
import org.gaia.fpml.fpml_5.recordkeeping.Asset;
import org.gaia.fpml.fpml_5.recordkeeping.PartyReference;
import org.gaia.fpml.fpml_5.recordkeeping.Payment;
import org.gaia.fpml.fpml_5.recordkeeping.Price;
import org.gaia.fpml.fpml_5.recordkeeping.Product;


/**
 * A simple example product that is a forward purchase or sale of an underlying asset.
 *
 * <p>Classe Java pour ForwardSale complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ForwardSale">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}underlyingAsset" minOccurs="0"/>
 *         &lt;element name="forwardSaleDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="settlement" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.fpml.org/FpML-5/recordkeeping}Price" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ForwardSale", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "underlyingAsset",
    "forwardSaleDate",
    "settlement",
    "price"
})
public class ForwardSale
    extends Product
{

    @XmlElement(namespace = "http://www.fpml.org/FpML-5/recordkeeping")
    protected PartyReference buyerPartyReference;
    @XmlElement(namespace = "http://www.fpml.org/FpML-5/recordkeeping")
    protected AccountReference buyerAccountReference;
    @XmlElement(namespace = "http://www.fpml.org/FpML-5/recordkeeping")
    protected PartyReference sellerPartyReference;
    @XmlElement(namespace = "http://www.fpml.org/FpML-5/recordkeeping")
    protected AccountReference sellerAccountReference;
    @XmlElementRef(name = "underlyingAsset", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Asset> underlyingAsset;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar forwardSaleDate;
    protected Payment settlement;
    protected Price price;

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
     * This is the underlying asset substitution group, so it can contain any underlying asset in that group.
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
     * Obtient la valeur de la propriété forwardSaleDate.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getForwardSaleDate() {
        return forwardSaleDate;
    }

    /**
     * Définit la valeur de la propriété forwardSaleDate.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setForwardSaleDate(XMLGregorianCalendar value) {
        this.forwardSaleDate = value;
    }

    /**
     * Obtient la valeur de la propriété settlement.
     *
     * @return
     *     possible object is
     *     {@link Payment }
     *
     */
    public Payment getSettlement() {
        return settlement;
    }

    /**
     * Définit la valeur de la propriété settlement.
     *
     * @param value
     *     allowed object is
     *     {@link Payment }
     *
     */
    public void setSettlement(Payment value) {
        this.settlement = value;
    }

    /**
     * Obtient la valeur de la propriété price.
     *
     * @return
     *     possible object is
     *     {@link Price }
     *
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Définit la valeur de la propriété price.
     *
     * @param value
     *     allowed object is
     *     {@link Price }
     *
     */
    public void setPrice(Price value) {
        this.price = value;
    }

}
