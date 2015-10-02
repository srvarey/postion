//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CommoditySwaptionUnderlying complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommoditySwaptionUnderlying">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommoditySwapDetails.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommoditySwaptionUnderlying", propOrder = {
    "effectiveDate",
    "terminationDate",
    "settlementCurrency",
    "commoditySwapLeg",
    "commonPricing",
    "marketDisruption",
    "settlementDisruption",
    "rounding"
})
public class CommoditySwaptionUnderlying {

    @XmlElement(required = true)
    protected AdjustableOrRelativeDate effectiveDate;
    @XmlElement(required = true)
    protected AdjustableOrRelativeDate terminationDate;
    protected IdentifiedCurrency settlementCurrency;
    @XmlElementRef(name = "commoditySwapLeg", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends CommoditySwapLeg>> commoditySwapLeg;
    protected Boolean commonPricing;
    protected CommodityMarketDisruption marketDisruption;
    protected CommodityBullionSettlementDisruptionEnum settlementDisruption;
    protected Rounding rounding;

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setEffectiveDate(AdjustableOrRelativeDate value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété terminationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getTerminationDate() {
        return terminationDate;
    }

    /**
     * Définit la valeur de la propriété terminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setTerminationDate(AdjustableOrRelativeDate value) {
        this.terminationDate = value;
    }

    /**
     * Obtient la valeur de la propriété settlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public IdentifiedCurrency getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Définit la valeur de la propriété settlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public void setSettlementCurrency(IdentifiedCurrency value) {
        this.settlementCurrency = value;
    }

    /**
     * Gets the value of the commoditySwapLeg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commoditySwapLeg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommoditySwapLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ElectricityPhysicalLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link FixedPriceLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link FloatingPriceLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link OilPhysicalLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link CommoditySwapLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link CoalPhysicalLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link GasPhysicalLeg }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends CommoditySwapLeg>> getCommoditySwapLeg() {
        if (commoditySwapLeg == null) {
            commoditySwapLeg = new ArrayList<JAXBElement<? extends CommoditySwapLeg>>();
        }
        return this.commoditySwapLeg;
    }

    /**
     * Obtient la valeur de la propriété commonPricing.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommonPricing() {
        return commonPricing;
    }

    /**
     * Définit la valeur de la propriété commonPricing.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommonPricing(Boolean value) {
        this.commonPricing = value;
    }

    /**
     * Obtient la valeur de la propriété marketDisruption.
     * 
     * @return
     *     possible object is
     *     {@link CommodityMarketDisruption }
     *     
     */
    public CommodityMarketDisruption getMarketDisruption() {
        return marketDisruption;
    }

    /**
     * Définit la valeur de la propriété marketDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityMarketDisruption }
     *     
     */
    public void setMarketDisruption(CommodityMarketDisruption value) {
        this.marketDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété settlementDisruption.
     * 
     * @return
     *     possible object is
     *     {@link CommodityBullionSettlementDisruptionEnum }
     *     
     */
    public CommodityBullionSettlementDisruptionEnum getSettlementDisruption() {
        return settlementDisruption;
    }

    /**
     * Définit la valeur de la propriété settlementDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityBullionSettlementDisruptionEnum }
     *     
     */
    public void setSettlementDisruption(CommodityBullionSettlementDisruptionEnum value) {
        this.settlementDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété rounding.
     * 
     * @return
     *     possible object is
     *     {@link Rounding }
     *     
     */
    public Rounding getRounding() {
        return rounding;
    }

    /**
     * Définit la valeur de la propriété rounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Rounding }
     *     
     */
    public void setRounding(Rounding value) {
        this.rounding = value;
    }

}
