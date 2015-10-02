//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A valuation of an FX curve object., which includes pricing inputs and term structures for fx forwards.
 * 
 * <p>Classe Java pour FxCurveValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxCurveValuation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureValuation">
 *       &lt;sequence>
 *         &lt;element name="settlementCurrencyYieldCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *         &lt;element name="forecastCurrencyYieldCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *         &lt;element name="spotRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FxRateSet" minOccurs="0"/>
 *         &lt;element name="fxForwardCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}TermCurve" minOccurs="0"/>
 *         &lt;element name="fxForwardPointsCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}TermCurve" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxCurveValuation", propOrder = {
    "settlementCurrencyYieldCurve",
    "forecastCurrencyYieldCurve",
    "spotRate",
    "fxForwardCurve",
    "fxForwardPointsCurve"
})
public class FxCurveValuation
    extends PricingStructureValuation
{

    protected PricingStructureReference settlementCurrencyYieldCurve;
    protected PricingStructureReference forecastCurrencyYieldCurve;
    protected FxRateSet spotRate;
    protected TermCurve fxForwardCurve;
    protected TermCurve fxForwardPointsCurve;

    /**
     * Obtient la valeur de la propriété settlementCurrencyYieldCurve.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getSettlementCurrencyYieldCurve() {
        return settlementCurrencyYieldCurve;
    }

    /**
     * Définit la valeur de la propriété settlementCurrencyYieldCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setSettlementCurrencyYieldCurve(PricingStructureReference value) {
        this.settlementCurrencyYieldCurve = value;
    }

    /**
     * Obtient la valeur de la propriété forecastCurrencyYieldCurve.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getForecastCurrencyYieldCurve() {
        return forecastCurrencyYieldCurve;
    }

    /**
     * Définit la valeur de la propriété forecastCurrencyYieldCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setForecastCurrencyYieldCurve(PricingStructureReference value) {
        this.forecastCurrencyYieldCurve = value;
    }

    /**
     * Obtient la valeur de la propriété spotRate.
     * 
     * @return
     *     possible object is
     *     {@link FxRateSet }
     *     
     */
    public FxRateSet getSpotRate() {
        return spotRate;
    }

    /**
     * Définit la valeur de la propriété spotRate.
     * 
     * @param value
     *     allowed object is
     *     {@link FxRateSet }
     *     
     */
    public void setSpotRate(FxRateSet value) {
        this.spotRate = value;
    }

    /**
     * Obtient la valeur de la propriété fxForwardCurve.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getFxForwardCurve() {
        return fxForwardCurve;
    }

    /**
     * Définit la valeur de la propriété fxForwardCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setFxForwardCurve(TermCurve value) {
        this.fxForwardCurve = value;
    }

    /**
     * Obtient la valeur de la propriété fxForwardPointsCurve.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getFxForwardPointsCurve() {
        return fxForwardPointsCurve;
    }

    /**
     * Définit la valeur de la propriété fxForwardPointsCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setFxForwardPointsCurve(TermCurve value) {
        this.fxForwardPointsCurve = value;
    }

}
