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
 * Details on the referenced payment. e.g. Its cashflow components, settlement details.
 * 
 * <p>Classe Java pour PaymentDetails complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PaymentDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PaymentReference" minOccurs="0"/>
 *         &lt;element name="grossCashflow" type="{http://www.fpml.org/FpML-5/recordkeeping}GrossCashflow" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="settlementInformation" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDetails", propOrder = {
    "paymentReference",
    "grossCashflow",
    "settlementInformation"
})
public class PaymentDetails {

    protected PaymentReference paymentReference;
    protected List<GrossCashflow> grossCashflow;
    protected SettlementInformation settlementInformation;

    /**
     * Obtient la valeur de la propriété paymentReference.
     * 
     * @return
     *     possible object is
     *     {@link PaymentReference }
     *     
     */
    public PaymentReference getPaymentReference() {
        return paymentReference;
    }

    /**
     * Définit la valeur de la propriété paymentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentReference }
     *     
     */
    public void setPaymentReference(PaymentReference value) {
        this.paymentReference = value;
    }

    /**
     * Gets the value of the grossCashflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grossCashflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrossCashflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GrossCashflow }
     * 
     * 
     */
    public List<GrossCashflow> getGrossCashflow() {
        if (grossCashflow == null) {
            grossCashflow = new ArrayList<GrossCashflow>();
        }
        return this.grossCashflow;
    }

    /**
     * Obtient la valeur de la propriété settlementInformation.
     * 
     * @return
     *     possible object is
     *     {@link SettlementInformation }
     *     
     */
    public SettlementInformation getSettlementInformation() {
        return settlementInformation;
    }

    /**
     * Définit la valeur de la propriété settlementInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementInformation }
     *     
     */
    public void setSettlementInformation(SettlementInformation value) {
        this.settlementInformation = value;
    }

}
