//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining a content model for a calculation rule defined as percentage of the notional amount.
 * 
 * <p>Classe Java pour PercentageRule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PercentageRule">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PaymentRule">
 *       &lt;sequence>
 *         &lt;element name="paymentPercent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="notionalAmountReference" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalAmountReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PercentageRule", propOrder = {
    "paymentPercent",
    "notionalAmountReference"
})
public class PercentageRule
    extends PaymentRule
{

    protected BigDecimal paymentPercent;
    protected NotionalAmountReference notionalAmountReference;

    /**
     * Obtient la valeur de la propriété paymentPercent.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentPercent() {
        return paymentPercent;
    }

    /**
     * Définit la valeur de la propriété paymentPercent.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentPercent(BigDecimal value) {
        this.paymentPercent = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAmountReference.
     * 
     * @return
     *     possible object is
     *     {@link NotionalAmountReference }
     *     
     */
    public NotionalAmountReference getNotionalAmountReference() {
        return notionalAmountReference;
    }

    /**
     * Définit la valeur de la propriété notionalAmountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalAmountReference }
     *     
     */
    public void setNotionalAmountReference(NotionalAmountReference value) {
        this.notionalAmountReference = value;
    }

}
