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
 * A type describing the commission that will be charged for each of the hedge transactions.
 * 
 * <p>Classe Java pour Commission complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Commission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commissionDenomination" type="{http://www.fpml.org/FpML-5/recordkeeping}CommissionDenominationEnum" minOccurs="0"/>
 *         &lt;element name="commissionAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="commissionPerTrade" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="fxRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FxRate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Commission", propOrder = {
    "commissionDenomination",
    "commissionAmount",
    "currency",
    "commissionPerTrade",
    "fxRate"
})
public class Commission {

    protected CommissionDenominationEnum commissionDenomination;
    protected BigDecimal commissionAmount;
    protected Currency currency;
    protected BigDecimal commissionPerTrade;
    protected List<FxRate> fxRate;

    /**
     * Obtient la valeur de la propriété commissionDenomination.
     * 
     * @return
     *     possible object is
     *     {@link CommissionDenominationEnum }
     *     
     */
    public CommissionDenominationEnum getCommissionDenomination() {
        return commissionDenomination;
    }

    /**
     * Définit la valeur de la propriété commissionDenomination.
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionDenominationEnum }
     *     
     */
    public void setCommissionDenomination(CommissionDenominationEnum value) {
        this.commissionDenomination = value;
    }

    /**
     * Obtient la valeur de la propriété commissionAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    /**
     * Définit la valeur de la propriété commissionAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCommissionAmount(BigDecimal value) {
        this.commissionAmount = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété commissionPerTrade.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCommissionPerTrade() {
        return commissionPerTrade;
    }

    /**
     * Définit la valeur de la propriété commissionPerTrade.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCommissionPerTrade(BigDecimal value) {
        this.commissionPerTrade = value;
    }

    /**
     * Gets the value of the fxRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFxRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxRate }
     * 
     * 
     */
    public List<FxRate> getFxRate() {
        if (fxRate == null) {
            fxRate = new ArrayList<FxRate>();
        }
        return this.fxRate;
    }

}
