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
 * A type for defining Equity Option Termination.
 * 
 * <p>Classe Java pour EquityOptionTermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityOptionTermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settlementAmountPaymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate" minOccurs="0"/>
 *         &lt;element name="settlementAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityOptionTermination", propOrder = {
    "settlementAmountPaymentDate",
    "settlementAmount"
})
public class EquityOptionTermination {

    protected AdjustableDate settlementAmountPaymentDate;
    protected NonNegativeMoney settlementAmount;

    /**
     * Obtient la valeur de la propriété settlementAmountPaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDate }
     *     
     */
    public AdjustableDate getSettlementAmountPaymentDate() {
        return settlementAmountPaymentDate;
    }

    /**
     * Définit la valeur de la propriété settlementAmountPaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDate }
     *     
     */
    public void setSettlementAmountPaymentDate(AdjustableDate value) {
        this.settlementAmountPaymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété settlementAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * Définit la valeur de la propriété settlementAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setSettlementAmount(NonNegativeMoney value) {
        this.settlementAmount = value;
    }

}
