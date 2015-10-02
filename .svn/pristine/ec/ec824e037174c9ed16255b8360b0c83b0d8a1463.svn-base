//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type that is used for describing the exchange rate for a particular transaction.
 * 
 * <p>Classe Java pour ExchangeRate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExchangeRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quotedCurrencyPair" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotedCurrencyPair" minOccurs="0"/>
 *         &lt;element name="rate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="spotRate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *           &lt;sequence minOccurs="0">
 *             &lt;element name="forwardPoints" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="pointValue" type="{http://www.fpml.org/FpML-5/recordkeeping}PointValue" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/sequence>
 *         &lt;element name="crossRate" type="{http://www.fpml.org/FpML-5/recordkeeping}CrossRate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeRate", propOrder = {
    "quotedCurrencyPair",
    "rate",
    "spotRate",
    "forwardPoints",
    "pointValue",
    "crossRate"
})
public class ExchangeRate {

    protected QuotedCurrencyPair quotedCurrencyPair;
    protected BigDecimal rate;
    protected BigDecimal spotRate;
    protected BigDecimal forwardPoints;
    protected BigDecimal pointValue;
    protected List<CrossRate> crossRate;

    /**
     * Obtient la valeur de la propriété quotedCurrencyPair.
     * 
     * @return
     *     possible object is
     *     {@link QuotedCurrencyPair }
     *     
     */
    public QuotedCurrencyPair getQuotedCurrencyPair() {
        return quotedCurrencyPair;
    }

    /**
     * Définit la valeur de la propriété quotedCurrencyPair.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotedCurrencyPair }
     *     
     */
    public void setQuotedCurrencyPair(QuotedCurrencyPair value) {
        this.quotedCurrencyPair = value;
    }

    /**
     * Obtient la valeur de la propriété rate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Définit la valeur de la propriété rate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Obtient la valeur de la propriété spotRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * Définit la valeur de la propriété spotRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpotRate(BigDecimal value) {
        this.spotRate = value;
    }

    /**
     * Obtient la valeur de la propriété forwardPoints.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getForwardPoints() {
        return forwardPoints;
    }

    /**
     * Définit la valeur de la propriété forwardPoints.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setForwardPoints(BigDecimal value) {
        this.forwardPoints = value;
    }

    /**
     * Obtient la valeur de la propriété pointValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPointValue() {
        return pointValue;
    }

    /**
     * Définit la valeur de la propriété pointValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointValue(BigDecimal value) {
        this.pointValue = value;
    }

    /**
     * Gets the value of the crossRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crossRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrossRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrossRate }
     * 
     * 
     */
    public List<CrossRate> getCrossRate() {
        if (crossRate == null) {
            crossRate = new ArrayList<CrossRate>();
        }
        return this.crossRate;
    }

}
