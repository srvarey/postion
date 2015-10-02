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
 * <p>Classe Java pour PaymentDetail complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PaymentDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentBase">
 *       &lt;sequence>
 *         &lt;element name="paymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="paymentRule" type="{http://www.fpml.org/FpML-5/recordkeeping}PaymentRule" minOccurs="0"/>
 *           &lt;element name="paymentAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDetail", propOrder = {
    "paymentDate",
    "paymentRule",
    "paymentAmount"
})
public class PaymentDetail
    extends PaymentBase
{

    protected AdjustableOrRelativeDate paymentDate;
    protected PaymentRule paymentRule;
    protected Money paymentAmount;

    /**
     * Obtient la valeur de la propriété paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Définit la valeur de la propriété paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setPaymentDate(AdjustableOrRelativeDate value) {
        this.paymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété paymentRule.
     * 
     * @return
     *     possible object is
     *     {@link PaymentRule }
     *     
     */
    public PaymentRule getPaymentRule() {
        return paymentRule;
    }

    /**
     * Définit la valeur de la propriété paymentRule.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentRule }
     *     
     */
    public void setPaymentRule(PaymentRule value) {
        this.paymentRule = value;
    }

    /**
     * Obtient la valeur de la propriété paymentAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Définit la valeur de la propriété paymentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setPaymentAmount(Money value) {
        this.paymentAmount = value;
    }

}
