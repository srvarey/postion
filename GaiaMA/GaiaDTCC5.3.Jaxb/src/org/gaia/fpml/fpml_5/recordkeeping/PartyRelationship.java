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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour PartyRelationship complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PartyRelationship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartyAndAccountReferences.model"/>
 *         &lt;element name="role" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyRole" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyRoleType" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="terminationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="documentation" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyRelationshipDocumentation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyRelationship", propOrder = {
    "partyReference",
    "accountReference",
    "role",
    "type",
    "effectiveDate",
    "terminationDate",
    "documentation"
})
public class PartyRelationship {

    @XmlElement(required = true)
    protected PartyReference partyReference;
    protected AccountReference accountReference;
    protected PartyRole role;
    protected PartyRoleType type;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar terminationDate;
    protected PartyRelationshipDocumentation documentation;

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
     * Obtient la valeur de la propriété role.
     * 
     * @return
     *     possible object is
     *     {@link PartyRole }
     *     
     */
    public PartyRole getRole() {
        return role;
    }

    /**
     * Définit la valeur de la propriété role.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRole }
     *     
     */
    public void setRole(PartyRole value) {
        this.role = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link PartyRoleType }
     *     
     */
    public PartyRoleType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRoleType }
     *     
     */
    public void setType(PartyRoleType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété terminationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTerminationDate() {
        return terminationDate;
    }

    /**
     * Définit la valeur de la propriété terminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTerminationDate(XMLGregorianCalendar value) {
        this.terminationDate = value;
    }

    /**
     * Obtient la valeur de la propriété documentation.
     * 
     * @return
     *     possible object is
     *     {@link PartyRelationshipDocumentation }
     *     
     */
    public PartyRelationshipDocumentation getDocumentation() {
        return documentation;
    }

    /**
     * Définit la valeur de la propriété documentation.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRelationshipDocumentation }
     *     
     */
    public void setDocumentation(PartyRelationshipDocumentation value) {
        this.documentation = value;
    }

}
