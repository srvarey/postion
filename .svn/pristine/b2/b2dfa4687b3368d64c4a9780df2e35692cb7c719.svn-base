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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A generic account that represents any party's account at another party. Parties may be identified by the account at another party.
 * 
 * <p>Classe Java pour Account complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Account">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence maxOccurs="unbounded">
 *           &lt;element name="accountId" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountId" minOccurs="0"/>
 *           &lt;element name="accountName" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountName" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="accountBeneficiary" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *           &lt;element name="servicingParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {
    "accountIdAndAccountName",
    "accountBeneficiary",
    "servicingParty"
})
public class Account {

    @XmlElements({
        @XmlElement(name = "accountId", type = AccountId.class),
        @XmlElement(name = "accountName", type = AccountName.class)
    })
    protected List<Object> accountIdAndAccountName;
    protected PartyReference accountBeneficiary;
    protected PartyReference servicingParty;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the accountIdAndAccountName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountIdAndAccountName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountIdAndAccountName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountId }
     * {@link AccountName }
     * 
     * 
     */
    public List<Object> getAccountIdAndAccountName() {
        if (accountIdAndAccountName == null) {
            accountIdAndAccountName = new ArrayList<Object>();
        }
        return this.accountIdAndAccountName;
    }

    /**
     * Obtient la valeur de la propriété accountBeneficiary.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getAccountBeneficiary() {
        return accountBeneficiary;
    }

    /**
     * Définit la valeur de la propriété accountBeneficiary.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setAccountBeneficiary(PartyReference value) {
        this.accountBeneficiary = value;
    }

    /**
     * Obtient la valeur de la propriété servicingParty.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getServicingParty() {
        return servicingParty;
    }

    /**
     * Définit la valeur de la propriété servicingParty.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setServicingParty(PartyReference value) {
        this.servicingParty = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
