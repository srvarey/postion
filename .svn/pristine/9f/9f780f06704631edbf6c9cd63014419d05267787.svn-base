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
 * ISDA 1993 or 2005 commodity market disruption elements.
 * 
 * <p>Classe Java pour CommodityMarketDisruption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityMarketDisruption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="marketDisruptionEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}MarketDisruptionEventsEnum" minOccurs="0"/>
 *             &lt;element name="additionalMarketDisruptionEvent" type="{http://www.fpml.org/FpML-5/recordkeeping}MarketDisruptionEvent" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="marketDisruptionEvent" type="{http://www.fpml.org/FpML-5/recordkeeping}MarketDisruptionEvent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="disruptionFallbacks" type="{http://www.fpml.org/FpML-5/recordkeeping}DisruptionFallbacksEnum" minOccurs="0"/>
 *           &lt;element name="disruptionFallback" type="{http://www.fpml.org/FpML-5/recordkeeping}SequencedDisruptionFallback" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="fallbackReferencePrice" type="{http://www.fpml.org/FpML-5/recordkeeping}Underlyer" minOccurs="0"/>
 *         &lt;element name="maximumNumberOfDaysOfDisruption" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="priceMaterialityPercentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="minimumFuturesContracts" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityMarketDisruption", propOrder = {
    "marketDisruptionEvents",
    "additionalMarketDisruptionEvent",
    "marketDisruptionEvent",
    "disruptionFallbacks",
    "disruptionFallback",
    "fallbackReferencePrice",
    "maximumNumberOfDaysOfDisruption",
    "priceMaterialityPercentage",
    "minimumFuturesContracts"
})
public class CommodityMarketDisruption {

    protected MarketDisruptionEventsEnum marketDisruptionEvents;
    protected List<MarketDisruptionEvent> additionalMarketDisruptionEvent;
    protected List<MarketDisruptionEvent> marketDisruptionEvent;
    protected DisruptionFallbacksEnum disruptionFallbacks;
    protected List<SequencedDisruptionFallback> disruptionFallback;
    protected Underlyer fallbackReferencePrice;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maximumNumberOfDaysOfDisruption;
    protected BigDecimal priceMaterialityPercentage;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger minimumFuturesContracts;

    /**
     * Obtient la valeur de la propriété marketDisruptionEvents.
     * 
     * @return
     *     possible object is
     *     {@link MarketDisruptionEventsEnum }
     *     
     */
    public MarketDisruptionEventsEnum getMarketDisruptionEvents() {
        return marketDisruptionEvents;
    }

    /**
     * Définit la valeur de la propriété marketDisruptionEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketDisruptionEventsEnum }
     *     
     */
    public void setMarketDisruptionEvents(MarketDisruptionEventsEnum value) {
        this.marketDisruptionEvents = value;
    }

    /**
     * Gets the value of the additionalMarketDisruptionEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalMarketDisruptionEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalMarketDisruptionEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketDisruptionEvent }
     * 
     * 
     */
    public List<MarketDisruptionEvent> getAdditionalMarketDisruptionEvent() {
        if (additionalMarketDisruptionEvent == null) {
            additionalMarketDisruptionEvent = new ArrayList<MarketDisruptionEvent>();
        }
        return this.additionalMarketDisruptionEvent;
    }

    /**
     * Gets the value of the marketDisruptionEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketDisruptionEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketDisruptionEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketDisruptionEvent }
     * 
     * 
     */
    public List<MarketDisruptionEvent> getMarketDisruptionEvent() {
        if (marketDisruptionEvent == null) {
            marketDisruptionEvent = new ArrayList<MarketDisruptionEvent>();
        }
        return this.marketDisruptionEvent;
    }

    /**
     * Obtient la valeur de la propriété disruptionFallbacks.
     * 
     * @return
     *     possible object is
     *     {@link DisruptionFallbacksEnum }
     *     
     */
    public DisruptionFallbacksEnum getDisruptionFallbacks() {
        return disruptionFallbacks;
    }

    /**
     * Définit la valeur de la propriété disruptionFallbacks.
     * 
     * @param value
     *     allowed object is
     *     {@link DisruptionFallbacksEnum }
     *     
     */
    public void setDisruptionFallbacks(DisruptionFallbacksEnum value) {
        this.disruptionFallbacks = value;
    }

    /**
     * Gets the value of the disruptionFallback property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disruptionFallback property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisruptionFallback().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SequencedDisruptionFallback }
     * 
     * 
     */
    public List<SequencedDisruptionFallback> getDisruptionFallback() {
        if (disruptionFallback == null) {
            disruptionFallback = new ArrayList<SequencedDisruptionFallback>();
        }
        return this.disruptionFallback;
    }

    /**
     * Obtient la valeur de la propriété fallbackReferencePrice.
     * 
     * @return
     *     possible object is
     *     {@link Underlyer }
     *     
     */
    public Underlyer getFallbackReferencePrice() {
        return fallbackReferencePrice;
    }

    /**
     * Définit la valeur de la propriété fallbackReferencePrice.
     * 
     * @param value
     *     allowed object is
     *     {@link Underlyer }
     *     
     */
    public void setFallbackReferencePrice(Underlyer value) {
        this.fallbackReferencePrice = value;
    }

    /**
     * Obtient la valeur de la propriété maximumNumberOfDaysOfDisruption.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumNumberOfDaysOfDisruption() {
        return maximumNumberOfDaysOfDisruption;
    }

    /**
     * Définit la valeur de la propriété maximumNumberOfDaysOfDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumNumberOfDaysOfDisruption(BigInteger value) {
        this.maximumNumberOfDaysOfDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété priceMaterialityPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceMaterialityPercentage() {
        return priceMaterialityPercentage;
    }

    /**
     * Définit la valeur de la propriété priceMaterialityPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceMaterialityPercentage(BigDecimal value) {
        this.priceMaterialityPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété minimumFuturesContracts.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinimumFuturesContracts() {
        return minimumFuturesContracts;
    }

    /**
     * Définit la valeur de la propriété minimumFuturesContracts.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinimumFuturesContracts(BigInteger value) {
        this.minimumFuturesContracts = value;
    }

}
