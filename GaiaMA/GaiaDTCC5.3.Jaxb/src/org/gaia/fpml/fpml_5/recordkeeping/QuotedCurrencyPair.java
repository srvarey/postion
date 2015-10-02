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
 * A type that describes the composition of a rate that has been quoted or is to be quoted. This includes the two currencies and the quotation relationship between the two currencies and is used as a building block throughout the FX specification.
 * 
 * <p>Classe Java pour QuotedCurrencyPair complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="QuotedCurrencyPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currency1" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="currency2" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="quoteBasis" type="{http://www.fpml.org/FpML-5/recordkeeping}QuoteBasisEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuotedCurrencyPair", propOrder = {
    "currency1",
    "currency2",
    "quoteBasis"
})
@XmlSeeAlso({
    CrossRate.class
})
public class QuotedCurrencyPair {

    protected Currency currency1;
    protected Currency currency2;
    protected QuoteBasisEnum quoteBasis;

    /**
     * Obtient la valeur de la propriété currency1.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency1() {
        return currency1;
    }

    /**
     * Définit la valeur de la propriété currency1.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency1(Currency value) {
        this.currency1 = value;
    }

    /**
     * Obtient la valeur de la propriété currency2.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency2() {
        return currency2;
    }

    /**
     * Définit la valeur de la propriété currency2.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency2(Currency value) {
        this.currency2 = value;
    }

    /**
     * Obtient la valeur de la propriété quoteBasis.
     * 
     * @return
     *     possible object is
     *     {@link QuoteBasisEnum }
     *     
     */
    public QuoteBasisEnum getQuoteBasis() {
        return quoteBasis;
    }

    /**
     * Définit la valeur de la propriété quoteBasis.
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteBasisEnum }
     *     
     */
    public void setQuoteBasis(QuoteBasisEnum value) {
        this.quoteBasis = value;
    }

}
