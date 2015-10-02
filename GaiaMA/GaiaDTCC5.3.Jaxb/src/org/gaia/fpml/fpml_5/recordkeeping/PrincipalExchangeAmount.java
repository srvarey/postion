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
 * Specifies the principal exchange amount, either by explicitly defining it, or by point to an amount defined somewhere else in the swap document.
 * 
 * <p>Classe Java pour PrincipalExchangeAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PrincipalExchangeAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="amountRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}AmountReference" minOccurs="0"/>
 *         &lt;element name="determinationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}DeterminationMethod" minOccurs="0"/>
 *         &lt;element name="principalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeMoney" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrincipalExchangeAmount", propOrder = {
    "amountRelativeTo",
    "determinationMethod",
    "principalAmount"
})
public class PrincipalExchangeAmount {

    protected AmountReference amountRelativeTo;
    protected DeterminationMethod determinationMethod;
    protected NonNegativeMoney principalAmount;

    /**
     * Obtient la valeur de la propriété amountRelativeTo.
     * 
     * @return
     *     possible object is
     *     {@link AmountReference }
     *     
     */
    public AmountReference getAmountRelativeTo() {
        return amountRelativeTo;
    }

    /**
     * Définit la valeur de la propriété amountRelativeTo.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountReference }
     *     
     */
    public void setAmountRelativeTo(AmountReference value) {
        this.amountRelativeTo = value;
    }

    /**
     * Obtient la valeur de la propriété determinationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethod }
     *     
     */
    public DeterminationMethod getDeterminationMethod() {
        return determinationMethod;
    }

    /**
     * Définit la valeur de la propriété determinationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethod }
     *     
     */
    public void setDeterminationMethod(DeterminationMethod value) {
        this.determinationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété principalAmount.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMoney }
     *     
     */
    public NonNegativeMoney getPrincipalAmount() {
        return principalAmount;
    }

    /**
     * Définit la valeur de la propriété principalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMoney }
     *     
     */
    public void setPrincipalAmount(NonNegativeMoney value) {
        this.principalAmount = value;
    }

}
