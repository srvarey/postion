//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining a hub or other reference for a physically settled commodity trade.
 * 
 * <p>Classe Java pour CommodityHub complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityHub">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartyAndAccountReferences.model"/>
 *         &lt;element name="hubCode" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityHubCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityHub", propOrder = {
    "partyReference",
    "accountReference",
    "hubCode"
})
public class CommodityHub {

    @XmlElement(required = true)
    protected PartyReference partyReference;
    protected AccountReference accountReference;
    protected CommodityHubCode hubCode;

    /**
     * Obtient la valeur de la propriété partyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getPartyReference() {
        return partyReference;
    }

    /**
     * Définit la valeur de la propriété partyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setPartyReference(PartyReference value) {
        this.partyReference = value;
    }

    /**
     * Obtient la valeur de la propriété accountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getAccountReference() {
        return accountReference;
    }

    /**
     * Définit la valeur de la propriété accountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setAccountReference(AccountReference value) {
        this.accountReference = value;
    }

    /**
     * Obtient la valeur de la propriété hubCode.
     * 
     * @return
     *     possible object is
     *     {@link CommodityHubCode }
     *     
     */
    public CommodityHubCode getHubCode() {
        return hubCode;
    }

    /**
     * Définit la valeur de la propriété hubCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityHubCode }
     *     
     */
    public void setHubCode(CommodityHubCode value) {
        this.hubCode = value;
    }

}
