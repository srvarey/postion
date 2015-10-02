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
 * A type defining the parameters necessary for each of the ISDA cash price methods for cash settlement.
 * 
 * <p>Classe Java pour CashPriceMethod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CashPriceMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cashSettlementReferenceBanks" type="{http://www.fpml.org/FpML-5/recordkeeping}CashSettlementReferenceBanks" minOccurs="0"/>
 *         &lt;element name="cashSettlementCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
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
@XmlType(name = "CashPriceMethod", propOrder = {
    "cashSettlementReferenceBanks",
    "cashSettlementCurrency",
    "quotationRateType"
})
public class CashPriceMethod {

    protected CashSettlementReferenceBanks cashSettlementReferenceBanks;
    protected Currency cashSettlementCurrency;
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
     * Obtient la valeur de la propriété cashSettlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCashSettlementCurrency() {
        return cashSettlementCurrency;
    }

    /**
     * Définit la valeur de la propriété cashSettlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCashSettlementCurrency(Currency value) {
        this.cashSettlementCurrency = value;
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
