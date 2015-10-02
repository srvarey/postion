//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing a single underlyer
 * 
 * <p>Classe Java pour SingleUnderlyer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SingleUnderlyer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}underlyingAsset" minOccurs="0"/>
 *         &lt;element name="openUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dividendPayout" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPayout" minOccurs="0"/>
 *         &lt;element name="couponPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}PendingPayment" minOccurs="0"/>
 *         &lt;element name="averageDailyTradingVolume" type="{http://www.fpml.org/FpML-5/recordkeeping}AverageDailyTradingVolumeLimit" minOccurs="0"/>
 *         &lt;element name="depositoryReceipt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SingleUnderlyer", propOrder = {
    "underlyingAsset",
    "openUnits",
    "dividendPayout",
    "couponPayment",
    "averageDailyTradingVolume",
    "depositoryReceipt"
})
public class SingleUnderlyer {

    @XmlElementRef(name = "underlyingAsset", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Asset> underlyingAsset;
    protected BigDecimal openUnits;
    protected DividendPayout dividendPayout;
    protected PendingPayment couponPayment;
    protected AverageDailyTradingVolumeLimit averageDailyTradingVolume;
    protected Boolean depositoryReceipt;

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
     * Obtient la valeur de la propriété openUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOpenUnits() {
        return openUnits;
    }

    /**
     * Définit la valeur de la propriété openUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOpenUnits(BigDecimal value) {
        this.openUnits = value;
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
     * Obtient la valeur de la propriété averageDailyTradingVolume.
     * 
     * @return
     *     possible object is
     *     {@link AverageDailyTradingVolumeLimit }
     *     
     */
    public AverageDailyTradingVolumeLimit getAverageDailyTradingVolume() {
        return averageDailyTradingVolume;
    }

    /**
     * Définit la valeur de la propriété averageDailyTradingVolume.
     * 
     * @param value
     *     allowed object is
     *     {@link AverageDailyTradingVolumeLimit }
     *     
     */
    public void setAverageDailyTradingVolume(AverageDailyTradingVolumeLimit value) {
        this.averageDailyTradingVolume = value;
    }

    /**
     * Obtient la valeur de la propriété depositoryReceipt.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDepositoryReceipt() {
        return depositoryReceipt;
    }

    /**
     * Définit la valeur de la propriété depositoryReceipt.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDepositoryReceipt(Boolean value) {
        this.depositoryReceipt = value;
    }

}
