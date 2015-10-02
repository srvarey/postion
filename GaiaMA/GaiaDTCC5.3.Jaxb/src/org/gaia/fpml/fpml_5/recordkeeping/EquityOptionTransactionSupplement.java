//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type for defining equity option transaction supplements.
 * 
 * <p>Classe Java pour EquityOptionTransactionSupplement complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityOptionTransactionSupplement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}EquityDerivativeShortFormBase">
 *       &lt;sequence>
 *         &lt;element name="exchangeLookAlike" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="exchangeTradedContractNearest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}IndexAnnexFallback.model" minOccurs="0"/>
 *         &lt;element name="methodOfAdjustment" type="{http://www.fpml.org/FpML-5/recordkeeping}MethodOfAdjustmentEnum" minOccurs="0"/>
 *         &lt;element name="localJurisdiction" type="{http://www.fpml.org/FpML-5/recordkeeping}CountryCode" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="optionEntitlement" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *           &lt;element name="multiplier" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="extraordinaryEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}ExtraordinaryEvents" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityOptionTransactionSupplement", propOrder = {
    "exchangeLookAlike",
    "exchangeTradedContractNearest",
    "multipleExchangeIndexAnnexFallback",
    "componentSecurityIndexAnnexFallback",
    "methodOfAdjustment",
    "localJurisdiction",
    "optionEntitlement",
    "multiplier",
    "extraordinaryEvents"
})
public class EquityOptionTransactionSupplement
    extends EquityDerivativeShortFormBase
{

    protected Boolean exchangeLookAlike;
    protected Boolean exchangeTradedContractNearest;
    protected Boolean multipleExchangeIndexAnnexFallback;
    protected Boolean componentSecurityIndexAnnexFallback;
    protected MethodOfAdjustmentEnum methodOfAdjustment;
    protected CountryCode localJurisdiction;
    protected BigDecimal optionEntitlement;
    protected BigDecimal multiplier;
    protected ExtraordinaryEvents extraordinaryEvents;

    /**
     * Obtient la valeur de la propriété exchangeLookAlike.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExchangeLookAlike() {
        return exchangeLookAlike;
    }

    /**
     * Définit la valeur de la propriété exchangeLookAlike.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExchangeLookAlike(Boolean value) {
        this.exchangeLookAlike = value;
    }

    /**
     * Obtient la valeur de la propriété exchangeTradedContractNearest.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExchangeTradedContractNearest() {
        return exchangeTradedContractNearest;
    }

    /**
     * Définit la valeur de la propriété exchangeTradedContractNearest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExchangeTradedContractNearest(Boolean value) {
        this.exchangeTradedContractNearest = value;
    }

    /**
     * Obtient la valeur de la propriété multipleExchangeIndexAnnexFallback.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleExchangeIndexAnnexFallback() {
        return multipleExchangeIndexAnnexFallback;
    }

    /**
     * Définit la valeur de la propriété multipleExchangeIndexAnnexFallback.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleExchangeIndexAnnexFallback(Boolean value) {
        this.multipleExchangeIndexAnnexFallback = value;
    }

    /**
     * Obtient la valeur de la propriété componentSecurityIndexAnnexFallback.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComponentSecurityIndexAnnexFallback() {
        return componentSecurityIndexAnnexFallback;
    }

    /**
     * Définit la valeur de la propriété componentSecurityIndexAnnexFallback.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComponentSecurityIndexAnnexFallback(Boolean value) {
        this.componentSecurityIndexAnnexFallback = value;
    }

    /**
     * Obtient la valeur de la propriété methodOfAdjustment.
     * 
     * @return
     *     possible object is
     *     {@link MethodOfAdjustmentEnum }
     *     
     */
    public MethodOfAdjustmentEnum getMethodOfAdjustment() {
        return methodOfAdjustment;
    }

    /**
     * Définit la valeur de la propriété methodOfAdjustment.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodOfAdjustmentEnum }
     *     
     */
    public void setMethodOfAdjustment(MethodOfAdjustmentEnum value) {
        this.methodOfAdjustment = value;
    }

    /**
     * Obtient la valeur de la propriété localJurisdiction.
     * 
     * @return
     *     possible object is
     *     {@link CountryCode }
     *     
     */
    public CountryCode getLocalJurisdiction() {
        return localJurisdiction;
    }

    /**
     * Définit la valeur de la propriété localJurisdiction.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCode }
     *     
     */
    public void setLocalJurisdiction(CountryCode value) {
        this.localJurisdiction = value;
    }

    /**
     * Obtient la valeur de la propriété optionEntitlement.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOptionEntitlement() {
        return optionEntitlement;
    }

    /**
     * Définit la valeur de la propriété optionEntitlement.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOptionEntitlement(BigDecimal value) {
        this.optionEntitlement = value;
    }

    /**
     * Obtient la valeur de la propriété multiplier.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMultiplier() {
        return multiplier;
    }

    /**
     * Définit la valeur de la propriété multiplier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMultiplier(BigDecimal value) {
        this.multiplier = value;
    }

    /**
     * Obtient la valeur de la propriété extraordinaryEvents.
     * 
     * @return
     *     possible object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public ExtraordinaryEvents getExtraordinaryEvents() {
        return extraordinaryEvents;
    }

    /**
     * Définit la valeur de la propriété extraordinaryEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public void setExtraordinaryEvents(ExtraordinaryEvents value) {
        this.extraordinaryEvents = value;
    }

}
