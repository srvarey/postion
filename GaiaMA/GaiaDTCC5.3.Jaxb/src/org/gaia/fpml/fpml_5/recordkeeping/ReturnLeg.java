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
 * A type describing the return leg of a return type swap.
 * 
 * <p>Classe Java pour ReturnLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReturnLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapLegUnderlyer">
 *       &lt;sequence>
 *         &lt;element name="rateOfReturn" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnLegValuation"/>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapNotional" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapAmount"/>
 *         &lt;element name="return" type="{http://www.fpml.org/FpML-5/recordkeeping}Return" minOccurs="0"/>
 *         &lt;element name="notionalAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalAdjustmentEnum" minOccurs="0"/>
 *         &lt;element name="fxFeature" type="{http://www.fpml.org/FpML-5/recordkeeping}FxFeature" minOccurs="0"/>
 *         &lt;element name="averagingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingPeriod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnLeg", propOrder = {
    "rateOfReturn",
    "notional",
    "amount",
    "_return",
    "notionalAdjustments",
    "fxFeature",
    "averagingDates"
})
public class ReturnLeg
    extends ReturnSwapLegUnderlyer
{

    @XmlElement(required = true)
    protected ReturnLegValuation rateOfReturn;
    protected ReturnSwapNotional notional;
    @XmlElement(required = true)
    protected ReturnSwapAmount amount;
    @XmlElement(name = "return")
    protected Return _return;
    protected NotionalAdjustmentEnum notionalAdjustments;
    protected FxFeature fxFeature;
    protected AveragingPeriod averagingDates;

    /**
     * Obtient la valeur de la propriété rateOfReturn.
     * 
     * @return
     *     possible object is
     *     {@link ReturnLegValuation }
     *     
     */
    public ReturnLegValuation getRateOfReturn() {
        return rateOfReturn;
    }

    /**
     * Définit la valeur de la propriété rateOfReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnLegValuation }
     *     
     */
    public void setRateOfReturn(ReturnLegValuation value) {
        this.rateOfReturn = value;
    }

    /**
     * Obtient la valeur de la propriété notional.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSwapNotional }
     *     
     */
    public ReturnSwapNotional getNotional() {
        return notional;
    }

    /**
     * Définit la valeur de la propriété notional.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSwapNotional }
     *     
     */
    public void setNotional(ReturnSwapNotional value) {
        this.notional = value;
    }

    /**
     * Obtient la valeur de la propriété amount.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSwapAmount }
     *     
     */
    public ReturnSwapAmount getAmount() {
        return amount;
    }

    /**
     * Définit la valeur de la propriété amount.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSwapAmount }
     *     
     */
    public void setAmount(ReturnSwapAmount value) {
        this.amount = value;
    }

    /**
     * Obtient la valeur de la propriété return.
     * 
     * @return
     *     possible object is
     *     {@link Return }
     *     
     */
    public Return getReturn() {
        return _return;
    }

    /**
     * Définit la valeur de la propriété return.
     * 
     * @param value
     *     allowed object is
     *     {@link Return }
     *     
     */
    public void setReturn(Return value) {
        this._return = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link NotionalAdjustmentEnum }
     *     
     */
    public NotionalAdjustmentEnum getNotionalAdjustments() {
        return notionalAdjustments;
    }

    /**
     * Définit la valeur de la propriété notionalAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalAdjustmentEnum }
     *     
     */
    public void setNotionalAdjustments(NotionalAdjustmentEnum value) {
        this.notionalAdjustments = value;
    }

    /**
     * Obtient la valeur de la propriété fxFeature.
     * 
     * @return
     *     possible object is
     *     {@link FxFeature }
     *     
     */
    public FxFeature getFxFeature() {
        return fxFeature;
    }

    /**
     * Définit la valeur de la propriété fxFeature.
     * 
     * @param value
     *     allowed object is
     *     {@link FxFeature }
     *     
     */
    public void setFxFeature(FxFeature value) {
        this.fxFeature = value;
    }

    /**
     * Obtient la valeur de la propriété averagingDates.
     * 
     * @return
     *     possible object is
     *     {@link AveragingPeriod }
     *     
     */
    public AveragingPeriod getAveragingDates() {
        return averagingDates;
    }

    /**
     * Définit la valeur de la propriété averagingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingPeriod }
     *     
     */
    public void setAveragingDates(AveragingPeriod value) {
        this.averagingDates = value;
    }

}
