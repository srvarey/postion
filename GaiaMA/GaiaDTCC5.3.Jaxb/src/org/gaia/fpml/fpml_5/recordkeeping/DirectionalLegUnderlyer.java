//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * An abstract base class for all directional leg types with effective date, termination date, and underlyer where a payer makes a stream of payments of greater than zero value to a receiver.
 * 
 * <p>Classe Java pour DirectionalLegUnderlyer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DirectionalLegUnderlyer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLeg">
 *       &lt;sequence>
 *         &lt;element name="underlyer" type="{http://www.fpml.org/FpML-5/recordkeeping}Underlyer" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionSettlement.model"/>
 *         &lt;element name="fxFeature" type="{http://www.fpml.org/FpML-5/recordkeeping}FxFeature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionalLegUnderlyer", propOrder = {
    "underlyer",
    "settlementType",
    "settlementDate",
    "settlementAmount",
    "settlementCurrency",
    "fxFeature"
})
@XmlSeeAlso({
    DirectionalLegUnderlyerValuation.class,
    DividendLeg.class
})
public abstract class DirectionalLegUnderlyer
    extends DirectionalLeg
{

    protected Underlyer underlyer;
    protected String settlementType;
    protected AdjustableOrRelativeDate settlementDate;
    protected Money settlementAmount;
    protected Currency settlementCurrency;
    protected FxFeature fxFeature;

    /**
     * Obtient la valeur de la propriété underlyer.
     * 
     * @return
     *     possible object is
     *     {@link Underlyer }
     *     
     */
    public Underlyer getUnderlyer() {
        return underlyer;
    }

    /**
     * Définit la valeur de la propriété underlyer.
     * 
     * @param value
     *     allowed object is
     *     {@link Underlyer }
     *     
     */
    public void setUnderlyer(Underlyer value) {
        this.underlyer = value;
    }

    /**
     * Obtient la valeur de la propriété settlementType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementType() {
        return settlementType;
    }

    /**
     * Définit la valeur de la propriété settlementType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementType(String value) {
        this.settlementType = value;
    }

    /**
     * Obtient la valeur de la propriété settlementDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * Définit la valeur de la propriété settlementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setSettlementDate(AdjustableOrRelativeDate value) {
        this.settlementDate = value;
    }

    /**
     * Obtient la valeur de la propriété settlementAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * Définit la valeur de la propriété settlementAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setSettlementAmount(Money value) {
        this.settlementAmount = value;
    }

    /**
     * Obtient la valeur de la propriété settlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Définit la valeur de la propriété settlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setSettlementCurrency(Currency value) {
        this.settlementCurrency = value;
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

}
