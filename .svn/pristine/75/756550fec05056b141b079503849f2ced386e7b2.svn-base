//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type defining an event identifier issued by the indicated party.
 * 
 * <p>Classe Java pour BusinessEventIdentifier complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BusinessEventIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="issuer" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyId"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartyAndAccountReferences.model"/>
 *         &lt;/choice>
 *         &lt;element name="eventId" type="{http://www.fpml.org/FpML-5/recordkeeping}EventId"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessEventIdentifier", propOrder = {
    "issuer",
    "partyReference",
    "accountReference",
    "eventId"
})
public class BusinessEventIdentifier {

    protected PartyId issuer;
    protected PartyReference partyReference;
    protected AccountReference accountReference;
    @XmlElement(required = true)
    protected EventId eventId;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété issuer.
     * 
     * @return
     *     possible object is
     *     {@link PartyId }
     *     
     */
    public PartyId getIssuer() {
        return issuer;
    }

    /**
     * Définit la valeur de la propriété issuer.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyId }
     *     
     */
    public void setIssuer(PartyId value) {
        this.issuer = value;
    }

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
     * Obtient la valeur de la propriété eventId.
     * 
     * @return
     *     possible object is
     *     {@link EventId }
     *     
     */
    public EventId getEventId() {
        return eventId;
    }

    /**
     * Définit la valeur de la propriété eventId.
     * 
     * @param value
     *     allowed object is
     *     {@link EventId }
     *     
     */
    public void setEventId(EventId value) {
        this.eventId = value;
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
