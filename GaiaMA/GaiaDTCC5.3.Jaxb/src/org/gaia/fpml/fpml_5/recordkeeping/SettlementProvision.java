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
 * A type defining the specification of settlement terms, occuring when the settlement currency is different to the notional currency of the trade.
 * 
 * <p>Classe Java pour SettlementProvision complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SettlementProvision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settlementCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="nonDeliverableSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}NonDeliverableSettlement" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettlementProvision", propOrder = {
    "settlementCurrency",
    "nonDeliverableSettlement"
})
public class SettlementProvision {

    protected Currency settlementCurrency;
    protected NonDeliverableSettlement nonDeliverableSettlement;

    /**
     * Obtient la valeur de la propriété settlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Définit la valeur de la propriété settlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setSettlementCurrency(Currency value) {
        this.settlementCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété nonDeliverableSettlement.
     * 
     * @return
     *     possible object is
     *     {@link NonDeliverableSettlement }
     *     
     */
    public NonDeliverableSettlement getNonDeliverableSettlement() {
        return nonDeliverableSettlement;
    }

    /**
     * Définit la valeur de la propriété nonDeliverableSettlement.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDeliverableSettlement }
     *     
     */
    public void setNonDeliverableSettlement(NonDeliverableSettlement value) {
        this.nonDeliverableSettlement = value;
    }

}
