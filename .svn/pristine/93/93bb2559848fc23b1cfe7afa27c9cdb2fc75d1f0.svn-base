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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type describing each of the constituents of a basket.
 * 
 * <p>Classe Java pour BasketConstituent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BasketConstituent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}underlyingAsset" minOccurs="0"/>
 *         &lt;element name="constituentWeight" type="{http://www.fpml.org/FpML-5/recordkeeping}ConstituentWeight" minOccurs="0"/>
 *         &lt;element name="dividendPayout" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPayout" minOccurs="0"/>
 *         &lt;element name="underlyerPrice" type="{http://www.fpml.org/FpML-5/recordkeeping}Price" minOccurs="0"/>
 *         &lt;element name="underlyerNotional" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="underlyerSpread" type="{http://www.fpml.org/FpML-5/recordkeeping}SpreadScheduleReference" minOccurs="0"/>
 *         &lt;element name="couponPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}PendingPayment" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasketConstituent", propOrder = {
    "underlyingAsset",
    "constituentWeight",
    "dividendPayout",
    "underlyerPrice",
    "underlyerNotional",
    "underlyerSpread",
    "couponPayment"
})
public class BasketConstituent {

    @XmlElementRef(name = "underlyingAsset", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Asset> underlyingAsset;
    protected ConstituentWeight constituentWeight;
    protected DividendPayout dividendPayout;
    protected Price underlyerPrice;
    protected Money underlyerNotional;
    protected SpreadScheduleReference underlyerSpread;
    protected PendingPayment couponPayment;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété underlyingAsset.
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
     * Obtient la valeur de la propriété constituentWeight.
     * 
     * @return
     *     possible object is
     *     {@link ConstituentWeight }
     *     
     */
    public ConstituentWeight getConstituentWeight() {
        return constituentWeight;
    }

    /**
     * Définit la valeur de la propriété constituentWeight.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstituentWeight }
     *     
     */
    public void setConstituentWeight(ConstituentWeight value) {
        this.constituentWeight = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPayout.
     * 
     * @return
     *     possible object is
     *     {@link DividendPayout }
     *     
     */
    public DividendPayout getDividendPayout() {
        return dividendPayout;
    }

    /**
     * Définit la valeur de la propriété dividendPayout.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendPayout }
     *     
     */
    public void setDividendPayout(DividendPayout value) {
        this.dividendPayout = value;
    }

    /**
     * Obtient la valeur de la propriété underlyerPrice.
     * 
     * @return
     *     possible object is
     *     {@link Price }
     *     
     */
    public Price getUnderlyerPrice() {
        return underlyerPrice;
    }

    /**
     * Définit la valeur de la propriété underlyerPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link Price }
     *     
     */
    public void setUnderlyerPrice(Price value) {
        this.underlyerPrice = value;
    }

    /**
     * Obtient la valeur de la propriété underlyerNotional.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getUnderlyerNotional() {
        return underlyerNotional;
    }

    /**
     * Définit la valeur de la propriété underlyerNotional.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setUnderlyerNotional(Money value) {
        this.underlyerNotional = value;
    }

    /**
     * Obtient la valeur de la propriété underlyerSpread.
     * 
     * @return
     *     possible object is
     *     {@link SpreadScheduleReference }
     *     
     */
    public SpreadScheduleReference getUnderlyerSpread() {
        return underlyerSpread;
    }

    /**
     * Définit la valeur de la propriété underlyerSpread.
     * 
     * @param value
     *     allowed object is
     *     {@link SpreadScheduleReference }
     *     
     */
    public void setUnderlyerSpread(SpreadScheduleReference value) {
        this.underlyerSpread = value;
    }

    /**
     * Obtient la valeur de la propriété couponPayment.
     * 
     * @return
     *     possible object is
     *     {@link PendingPayment }
     *     
     */
    public PendingPayment getCouponPayment() {
        return couponPayment;
    }

    /**
     * Définit la valeur de la propriété couponPayment.
     * 
     * @param value
     *     allowed object is
     *     {@link PendingPayment }
     *     
     */
    public void setCouponPayment(PendingPayment value) {
        this.couponPayment = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
