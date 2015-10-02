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
 * <p>Classe Java pour AdditionalFixedPayments complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdditionalFixedPayments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interestShortfallReimbursement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="principalShortfallReimbursement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="writedownReimbursement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalFixedPayments", propOrder = {
    "interestShortfallReimbursement",
    "principalShortfallReimbursement",
    "writedownReimbursement"
})
public class AdditionalFixedPayments {

    protected Boolean interestShortfallReimbursement;
    protected Boolean principalShortfallReimbursement;
    protected Boolean writedownReimbursement;

    /**
     * Obtient la valeur de la propriété interestShortfallReimbursement.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInterestShortfallReimbursement() {
        return interestShortfallReimbursement;
    }

    /**
     * Définit la valeur de la propriété interestShortfallReimbursement.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInterestShortfallReimbursement(Boolean value) {
        this.interestShortfallReimbursement = value;
    }

    /**
     * Obtient la valeur de la propriété principalShortfallReimbursement.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrincipalShortfallReimbursement() {
        return principalShortfallReimbursement;
    }

    /**
     * Définit la valeur de la propriété principalShortfallReimbursement.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrincipalShortfallReimbursement(Boolean value) {
        this.principalShortfallReimbursement = value;
    }

    /**
     * Obtient la valeur de la propriété writedownReimbursement.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWritedownReimbursement() {
        return writedownReimbursement;
    }

    /**
     * Définit la valeur de la propriété writedownReimbursement.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWritedownReimbursement(Boolean value) {
        this.writedownReimbursement = value;
    }

}
