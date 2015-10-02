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
 * A type defining the cashflow representation of a swap trade.
 * 
 * <p>Classe Java pour Cashflows complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Cashflows">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cashflowsMatchParameters" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="principalExchange" type="{http://www.fpml.org/FpML-5/recordkeeping}PrincipalExchange" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paymentCalculationPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}PaymentCalculationPeriod" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cashflows", propOrder = {
    "cashflowsMatchParameters",
    "principalExchange",
    "paymentCalculationPeriod"
})
public class Cashflows {

    protected Boolean cashflowsMatchParameters;
    protected List<PrincipalExchange> principalExchange;
    protected List<PaymentCalculationPeriod> paymentCalculationPeriod;

    /**
     * Obtient la valeur de la propriété cashflowsMatchParameters.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCashflowsMatchParameters() {
        return cashflowsMatchParameters;
    }

    /**
     * Définit la valeur de la propriété cashflowsMatchParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCashflowsMatchParameters(Boolean value) {
        this.cashflowsMatchParameters = value;
    }

    /**
     * Gets the value of the principalExchange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the principalExchange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrincipalExchange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrincipalExchange }
     * 
     * 
     */
    public List<PrincipalExchange> getPrincipalExchange() {
        if (principalExchange == null) {
            principalExchange = new ArrayList<PrincipalExchange>();
        }
        return this.principalExchange;
    }

    /**
     * Gets the value of the paymentCalculationPeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentCalculationPeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentCalculationPeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentCalculationPeriod }
     * 
     * 
     */
    public List<PaymentCalculationPeriod> getPaymentCalculationPeriod() {
        if (paymentCalculationPeriod == null) {
            paymentCalculationPeriod = new ArrayList<PaymentCalculationPeriod>();
        }
        return this.paymentCalculationPeriod;
    }

}
