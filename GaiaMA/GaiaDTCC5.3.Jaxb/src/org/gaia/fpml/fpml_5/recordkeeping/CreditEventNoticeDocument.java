//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * An event type that records the occurrence of a credit event notice.
 * 
 * <p>Classe Java pour CreditEventNoticeDocument complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CreditEventNoticeDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="affectedTransactions" type="{http://www.fpml.org/FpML-5/recordkeeping}AffectedTransactions" minOccurs="0"/>
 *         &lt;element name="referenceEntity" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntity" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}creditEvent" minOccurs="0"/>
 *         &lt;element name="publiclyAvailableInformation" type="{http://www.fpml.org/FpML-5/recordkeeping}Resource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notifyingPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="notifiedPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="creditEventNoticeDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="creditEventDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditEventNoticeDocument", propOrder = {
    "affectedTransactions",
    "referenceEntity",
    "creditEvent",
    "publiclyAvailableInformation",
    "notifyingPartyReference",
    "notifiedPartyReference",
    "creditEventNoticeDate",
    "creditEventDate"
})
public class CreditEventNoticeDocument {

    protected AffectedTransactions affectedTransactions;
    protected LegalEntity referenceEntity;
    @XmlElementRef(name = "creditEvent", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends CreditEvent> creditEvent;
    protected List<Resource> publiclyAvailableInformation;
    protected PartyReference notifyingPartyReference;
    protected PartyReference notifiedPartyReference;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creditEventNoticeDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creditEventDate;

    /**
     * Obtient la valeur de la propriété affectedTransactions.
     * 
     * @return
     *     possible object is
     *     {@link AffectedTransactions }
     *     
     */
    public AffectedTransactions getAffectedTransactions() {
        return affectedTransactions;
    }

    /**
     * Définit la valeur de la propriété affectedTransactions.
     * 
     * @param value
     *     allowed object is
     *     {@link AffectedTransactions }
     *     
     */
    public void setAffectedTransactions(AffectedTransactions value) {
        this.affectedTransactions = value;
    }

    /**
     * Obtient la valeur de la propriété referenceEntity.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntity }
     *     
     */
    public LegalEntity getReferenceEntity() {
        return referenceEntity;
    }

    /**
     * Définit la valeur de la propriété referenceEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntity }
     *     
     */
    public void setReferenceEntity(LegalEntity value) {
        this.referenceEntity = value;
    }

    /**
     * Obtient la valeur de la propriété creditEvent.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link FailureToPayEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link BankruptcyEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link ObligationAccelerationEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link RestructuringEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link RepudiationMoratoriumEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link ObligationDefaultEvent }{@code >}
     *     
     */
    public JAXBElement<? extends CreditEvent> getCreditEvent() {
        return creditEvent;
    }

    /**
     * Définit la valeur de la propriété creditEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link FailureToPayEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link BankruptcyEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link ObligationAccelerationEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link RestructuringEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link RepudiationMoratoriumEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link ObligationDefaultEvent }{@code >}
     *     
     */
    public void setCreditEvent(JAXBElement<? extends CreditEvent> value) {
        this.creditEvent = value;
    }

    /**
     * Gets the value of the publiclyAvailableInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publiclyAvailableInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPubliclyAvailableInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Resource }
     * 
     * 
     */
    public List<Resource> getPubliclyAvailableInformation() {
        if (publiclyAvailableInformation == null) {
            publiclyAvailableInformation = new ArrayList<Resource>();
        }
        return this.publiclyAvailableInformation;
    }

    /**
     * Obtient la valeur de la propriété notifyingPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getNotifyingPartyReference() {
        return notifyingPartyReference;
    }

    /**
     * Définit la valeur de la propriété notifyingPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setNotifyingPartyReference(PartyReference value) {
        this.notifyingPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété notifiedPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getNotifiedPartyReference() {
        return notifiedPartyReference;
    }

    /**
     * Définit la valeur de la propriété notifiedPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setNotifiedPartyReference(PartyReference value) {
        this.notifiedPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété creditEventNoticeDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreditEventNoticeDate() {
        return creditEventNoticeDate;
    }

    /**
     * Définit la valeur de la propriété creditEventNoticeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreditEventNoticeDate(XMLGregorianCalendar value) {
        this.creditEventNoticeDate = value;
    }

    /**
     * Obtient la valeur de la propriété creditEventDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreditEventDate() {
        return creditEventDate;
    }

    /**
     * Définit la valeur de la propriété creditEventDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreditEventDate(XMLGregorianCalendar value) {
        this.creditEventDate = value;
    }

}
