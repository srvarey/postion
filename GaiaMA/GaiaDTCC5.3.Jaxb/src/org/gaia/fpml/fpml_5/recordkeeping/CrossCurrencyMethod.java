//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CrossCurrencyMethod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CrossCurrencyMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cashSettlementReferenceBanks" type="{http://www.fpml.org/FpML-5/recordkeeping}CashSettlementReferenceBanks" minOccurs="0"/>
 *         &lt;element name="cashSettlementCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="quotationRateType" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotationRateTypeEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrossCurrencyMethod", propOrder = {
    "cashSettlementReferenceBanks",
    "cashSettlementCurrency",
    "quotationRateType"
})
public class CrossCurrencyMethod {

    protected CashSettlementReferenceBanks cashSettlementReferenceBanks;
    protected List<Currency> cashSettlementCurrency;
    protected QuotationRateTypeEnum quotationRateType;

    /**
     * Obtient la valeur de la propriété cashSettlementReferenceBanks.
     * 
     * @return
     *     possible object is
     *     {@link CashSettlementReferenceBanks }
     *     
     */
    public CashSettlementReferenceBanks getCashSettlementReferenceBanks() {
        return cashSettlementReferenceBanks;
    }

    /**
     * Définit la valeur de la propriété cashSettlementReferenceBanks.
     * 
     * @param value
     *     allowed object is
     *     {@link CashSettlementReferenceBanks }
     *     
     */
    public void setCashSettlementReferenceBanks(CashSettlementReferenceBanks value) {
        this.cashSettlementReferenceBanks = value;
    }

    /**
     * Gets the value of the cashSettlementCurrency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cashSettlementCurrency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCashSettlementCurrency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Currency }
     * 
     * 
     */
    public List<Currency> getCashSettlementCurrency() {
        if (cashSettlementCurrency == null) {
            cashSettlementCurrency = new ArrayList<Currency>();
        }
        return this.cashSettlementCurrency;
    }

    /**
     * Obtient la valeur de la propriété quotationRateType.
     * 
     * @return
     *     possible object is
     *     {@link QuotationRateTypeEnum }
     *     
     */
    public QuotationRateTypeEnum getQuotationRateType() {
        return quotationRateType;
    }

    /**
     * Définit la valeur de la propriété quotationRateType.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotationRateTypeEnum }
     *     
     */
    public void setQuotationRateType(QuotationRateTypeEnum value) {
        this.quotationRateType = value;
    }

}
