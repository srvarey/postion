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
 * Fixed payment amount within a Dividend Swap.
 * 
 * <p>Classe Java pour FixedPaymentAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FixedPaymentAmount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentBase">
 *       &lt;sequence>
 *         &lt;element name="paymentAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *         &lt;element name="paymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedPaymentAmount", propOrder = {
    "paymentAmount",
    "paymentDate"
})
public class FixedPaymentAmount
    extends PaymentBase
{

    protected NonNegativeMoney paymentAmount;
    protected RelativeDateOffset paymentDate;

    /**
     * Obtient la valeur de la propriété paymentAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Définit la valeur de la propriété paymentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setPaymentAmount(NonNegativeMoney value) {
        this.paymentAmount = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getPaymentDate() {
        return paymentDate;
    }

    /**
     * Définit la valeur de la propriété paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setPaymentDate(RelativeDateOffset value) {
        this.paymentDate = value;
    }

}
