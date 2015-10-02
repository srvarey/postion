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
 * A type describing the amount that will paid or received on each of the payment dates. This type is used to define both the Equity Amount and the Interest Amount.
 * 
 * <p>Classe Java pour LegAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="LegAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CurrencyAndDeterminationMethod.model" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="referenceAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}ReferenceAmount" minOccurs="0"/>
 *           &lt;element name="formula" type="{http://www.fpml.org/FpML-5/recordkeeping}Formula" minOccurs="0"/>
 *           &lt;element name="encodedDescription" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="calculationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegAmount", propOrder = {
    "currency",
    "determinationMethod",
    "currencyReference",
    "referenceAmount",
    "formula",
    "encodedDescription",
    "calculationDates"
})
@XmlSeeAlso({
    ReturnSwapAmount.class
})
public class LegAmount {

    protected IdentifiedCurrency currency;
    protected DeterminationMethod determinationMethod;
    protected IdentifiedCurrencyReference currencyReference;
    protected ReferenceAmount referenceAmount;
    protected Formula formula;
    protected byte[] encodedDescription;
    protected AdjustableRelativeOrPeriodicDates calculationDates;

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
     * Obtient la valeur de la propriété determinationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethod }
     *     
     */
    public DeterminationMethod getDeterminationMethod() {
        return determinationMethod;
    }

    /**
     * Définit la valeur de la propriété determinationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethod }
     *     
     */
    public void setDeterminationMethod(DeterminationMethod value) {
        this.determinationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété currencyReference.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedCurrencyReference }
     *     
     */
    public IdentifiedCurrencyReference getCurrencyReference() {
        return currencyReference;
    }

    /**
     * Définit la valeur de la propriété currencyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedCurrencyReference }
     *     
     */
    public void setCurrencyReference(IdentifiedCurrencyReference value) {
        this.currencyReference = value;
    }

    /**
     * Obtient la valeur de la propriété referenceAmount.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceAmount }
     *     
     */
    public ReferenceAmount getReferenceAmount() {
        return referenceAmount;
    }

    /**
     * Définit la valeur de la propriété referenceAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceAmount }
     *     
     */
    public void setReferenceAmount(ReferenceAmount value) {
        this.referenceAmount = value;
    }

    /**
     * Obtient la valeur de la propriété formula.
     * 
     * @return
     *     possible object is
     *     {@link Formula }
     *     
     */
    public Formula getFormula() {
        return formula;
    }

    /**
     * Définit la valeur de la propriété formula.
     * 
     * @param value
     *     allowed object is
     *     {@link Formula }
     *     
     */
    public void setFormula(Formula value) {
        this.formula = value;
    }

    /**
     * Obtient la valeur de la propriété encodedDescription.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEncodedDescription() {
        return encodedDescription;
    }

    /**
     * Définit la valeur de la propriété encodedDescription.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEncodedDescription(byte[] value) {
        this.encodedDescription = value;
    }

    /**
     * Obtient la valeur de la propriété calculationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public AdjustableRelativeOrPeriodicDates getCalculationDates() {
        return calculationDates;
    }

    /**
     * Définit la valeur de la propriété calculationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates }
     *     
     */
    public void setCalculationDates(AdjustableRelativeOrPeriodicDates value) {
        this.calculationDates = value;
    }

}
