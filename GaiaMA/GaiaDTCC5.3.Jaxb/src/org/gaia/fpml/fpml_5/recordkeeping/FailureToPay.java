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
 * <p>Classe Java pour FailureToPay complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FailureToPay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="gracePeriodExtension" type="{http://www.fpml.org/FpML-5/recordkeeping}GracePeriodExtension" minOccurs="0"/>
 *         &lt;element name="paymentRequirement" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FailureToPay", propOrder = {
    "applicable",
    "gracePeriodExtension",
    "paymentRequirement"
})
public class FailureToPay {

    protected Boolean applicable;
    protected GracePeriodExtension gracePeriodExtension;
    protected Money paymentRequirement;

    /**
     * Obtient la valeur de la propriété applicable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplicable() {
        return applicable;
    }

    /**
     * Définit la valeur de la propriété applicable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplicable(Boolean value) {
        this.applicable = value;
    }

    /**
     * Obtient la valeur de la propriété gracePeriodExtension.
     * 
     * @return
     *     possible object is
     *     {@link GracePeriodExtension }
     *     
     */
    public GracePeriodExtension getGracePeriodExtension() {
        return gracePeriodExtension;
    }

    /**
     * Définit la valeur de la propriété gracePeriodExtension.
     * 
     * @param value
     *     allowed object is
     *     {@link GracePeriodExtension }
     *     
     */
    public void setGracePeriodExtension(GracePeriodExtension value) {
        this.gracePeriodExtension = value;
    }

    /**
     * Obtient la valeur de la propriété paymentRequirement.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getPaymentRequirement() {
        return paymentRequirement;
    }

    /**
     * Définit la valeur de la propriété paymentRequirement.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setPaymentRequirement(Money value) {
        this.paymentRequirement = value;
    }

}