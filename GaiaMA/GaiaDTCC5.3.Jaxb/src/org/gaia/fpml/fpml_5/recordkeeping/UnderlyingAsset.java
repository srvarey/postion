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
 * Abstract base class for all underlying assets.
 * 
 * <p>Classe Java pour UnderlyingAsset complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="UnderlyingAsset">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedAsset">
 *       &lt;sequence>
 *         &lt;element name="currency" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedCurrency" minOccurs="0"/>
 *         &lt;element name="exchangeId" type="{http://www.fpml.org/FpML-5/recordkeeping}ExchangeId" minOccurs="0"/>
 *         &lt;element name="clearanceSystem" type="{http://www.fpml.org/FpML-5/recordkeeping}ClearanceSystem" minOccurs="0"/>
 *         &lt;element name="definition" type="{http://www.fpml.org/FpML-5/recordkeeping}ProductReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnderlyingAsset", propOrder = {
    "currency",
    "exchangeId",
    "clearanceSystem",
    "definition"
})
@XmlSeeAlso({
    Deposit.class,
    FxRateAsset.class,
    Mortgage.class,
    Loan.class,
    SimpleIRSwap.class,
    SimpleCreditDefaultSwap.class,
    SimpleFra.class,
    Bond.class,
    RateIndex.class,
    MutualFund.class,
    ExchangeTraded.class
})
public abstract class UnderlyingAsset
    extends IdentifiedAsset
{

    protected IdentifiedCurrency currency;
    protected ExchangeId exchangeId;
    protected ClearanceSystem clearanceSystem;
    protected ProductReference definition;

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public IdentifiedCurrency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedCurrency }
     *     
     */
    public void setCurrency(IdentifiedCurrency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété exchangeId.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeId }
     *     
     */
    public ExchangeId getExchangeId() {
        return exchangeId;
    }

    /**
     * Définit la valeur de la propriété exchangeId.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeId }
     *     
     */
    public void setExchangeId(ExchangeId value) {
        this.exchangeId = value;
    }

    /**
     * Obtient la valeur de la propriété clearanceSystem.
     * 
     * @return
     *     possible object is
     *     {@link ClearanceSystem }
     *     
     */
    public ClearanceSystem getClearanceSystem() {
        return clearanceSystem;
    }

    /**
     * Définit la valeur de la propriété clearanceSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link ClearanceSystem }
     *     
     */
    public void setClearanceSystem(ClearanceSystem value) {
        this.clearanceSystem = value;
    }

    /**
     * Obtient la valeur de la propriété definition.
     * 
     * @return
     *     possible object is
     *     {@link ProductReference }
     *     
     */
    public ProductReference getDefinition() {
        return definition;
    }

    /**
     * Définit la valeur de la propriété definition.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductReference }
     *     
     */
    public void setDefinition(ProductReference value) {
        this.definition = value;
    }

}
