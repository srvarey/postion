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
 * Commodity Forward
 * 
 * <p>Classe Java pour CommodityForward complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityForward">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="valueDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="fixedLeg" type="{http://www.fpml.org/FpML-5/recordkeeping}NonPeriodicFixedPriceLeg" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}commodityForwardLeg" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityContent.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityForward", propOrder = {
    "valueDate",
    "fixedLeg",
    "commodityForwardLeg",
    "commonPricing",
    "marketDisruption",
    "settlementDisruption",
    "rounding"
})
public class CommodityForward
    extends Product
{

    protected AdjustableOrRelativeDate valueDate;
    protected NonPeriodicFixedPriceLeg fixedLeg;
    @XmlElementRef(name = "commodityForwardLeg", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends CommodityForwardLeg> commodityForwardLeg;
    protected Boolean commonPricing;
    protected CommodityMarketDisruption marketDisruption;
    protected CommodityBullionSettlementDisruptionEnum settlementDisruption;
    protected Rounding rounding;

    /**
     * Obtient la valeur de la propriété valueDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getValueDate() {
        return valueDate;
    }

    /**
     * Définit la valeur de la propriété valueDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setValueDate(AdjustableOrRelativeDate value) {
        this.valueDate = value;
    }

    /**
     * Obtient la valeur de la propriété fixedLeg.
     * 
     * @return
     *     possible object is
     *     {@link NonPeriodicFixedPriceLeg }
     *     
     */
    public NonPeriodicFixedPriceLeg getFixedLeg() {
        return fixedLeg;
    }

    /**
     * Définit la valeur de la propriété fixedLeg.
     * 
     * @param value
     *     allowed object is
     *     {@link NonPeriodicFixedPriceLeg }
     *     
     */
    public void setFixedLeg(NonPeriodicFixedPriceLeg value) {
        this.fixedLeg = value;
    }

    /**
     * Obtient la valeur de la propriété commodityForwardLeg.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BullionPhysicalLeg }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityForwardLeg }{@code >}
     *     
     */
    public JAXBElement<? extends CommodityForwardLeg> getCommodityForwardLeg() {
        return commodityForwardLeg;
    }

    /**
     * Définit la valeur de la propriété commodityForwardLeg.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BullionPhysicalLeg }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityForwardLeg }{@code >}
     *     
     */
    public void setCommodityForwardLeg(JAXBElement<? extends CommodityForwardLeg> value) {
        this.commodityForwardLeg = value;
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
