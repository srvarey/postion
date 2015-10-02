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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Allows timing information about when a trade was processed and reported to be recorded.
 * 
 * <p>Classe Java pour TradeProcessingTimestamps complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeProcessingTimestamps">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderEntered" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderSubmitted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publiclyReported" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publicReportUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nonpubliclyReported" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nonpublicReportUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="submittedForConfirmation" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedForConfirmation" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="confirmed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="submittedForClearing" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedForClearing" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cleared" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="allocationsSubmitted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="allocationsUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="allocationsCompleted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeTimestamp" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeProcessingTimestamps", propOrder = {
    "orderEntered",
    "orderSubmitted",
    "publiclyReported",
    "publicReportUpdated",
    "nonpubliclyReported",
    "nonpublicReportUpdated",
    "submittedForConfirmation",
    "updatedForConfirmation",
    "confirmed",
    "submittedForClearing",
    "updatedForClearing",
    "cleared",
    "allocationsSubmitted",
    "allocationsUpdated",
    "allocationsCompleted",
    "timestamp"
})
public class TradeProcessingTimestamps {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderEntered;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderSubmitted;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publiclyReported;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publicReportUpdated;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nonpubliclyReported;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nonpublicReportUpdated;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submittedForConfirmation;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedForConfirmation;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar confirmed;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submittedForClearing;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedForClearing;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cleared;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar allocationsSubmitted;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar allocationsUpdated;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar allocationsCompleted;
    protected List<TradeTimestamp> timestamp;

    /**
     * Obtient la valeur de la propriété orderEntered.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderEntered() {
        return orderEntered;
    }

    /**
     * Définit la valeur de la propriété orderEntered.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderEntered(XMLGregorianCalendar value) {
        this.orderEntered = value;
    }

    /**
     * Obtient la valeur de la propriété orderSubmitted.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderSubmitted() {
        return orderSubmitted;
    }

    /**
     * Définit la valeur de la propriété orderSubmitted.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderSubmitted(XMLGregorianCalendar value) {
        this.orderSubmitted = value;
    }

    /**
     * Obtient la valeur de la propriété publiclyReported.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPubliclyReported() {
        return publiclyReported;
    }

    /**
     * Définit la valeur de la propriété publiclyReported.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPubliclyReported(XMLGregorianCalendar value) {
        this.publiclyReported = value;
    }

    /**
     * Obtient la valeur de la propriété publicReportUpdated.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublicReportUpdated() {
        return publicReportUpdated;
    }

    /**
     * Définit la valeur de la propriété publicReportUpdated.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublicReportUpdated(XMLGregorianCalendar value) {
        this.publicReportUpdated = value;
    }

    /**
     * Obtient la valeur de la propriété nonpubliclyReported.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNonpubliclyReported() {
        return nonpubliclyReported;
    }

    /**
     * Définit la valeur de la propriété nonpubliclyReported.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNonpubliclyReported(XMLGregorianCalendar value) {
        this.nonpubliclyReported = value;
    }

    /**
     * Obtient la valeur de la propriété nonpublicReportUpdated.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNonpublicReportUpdated() {
        return nonpublicReportUpdated;
    }

    /**
     * Définit la valeur de la propriété nonpublicReportUpdated.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNonpublicReportUpdated(XMLGregorianCalendar value) {
        this.nonpublicReportUpdated = value;
    }

    /**
     * Obtient la valeur de la propriété submittedForConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmittedForConfirmation() {
        return submittedForConfirmation;
    }

    /**
     * Définit la valeur de la propriété submittedForConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmittedForConfirmation(XMLGregorianCalendar value) {
        this.submittedForConfirmation = value;
    }

    /**
     * Obtient la valeur de la propriété updatedForConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedForConfirmation() {
        return updatedForConfirmation;
    }

    /**
     * Définit la valeur de la propriété updatedForConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedForConfirmation(XMLGregorianCalendar value) {
        this.updatedForConfirmation = value;
    }

    /**
     * Obtient la valeur de la propriété confirmed.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConfirmed() {
        return confirmed;
    }

    /**
     * Définit la valeur de la propriété confirmed.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConfirmed(XMLGregorianCalendar value) {
        this.confirmed = value;
    }

    /**
     * Obtient la valeur de la propriété submittedForClearing.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmittedForClearing() {
        return submittedForClearing;
    }

    /**
     * Définit la valeur de la propriété submittedForClearing.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmittedForClearing(XMLGregorianCalendar value) {
        this.submittedForClearing = value;
    }

    /**
     * Obtient la valeur de la propriété updatedForClearing.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedForClearing() {
        return updatedForClearing;
    }

    /**
     * Définit la valeur de la propriété updatedForClearing.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedForClearing(XMLGregorianCalendar value) {
        this.updatedForClearing = value;
    }

    /**
     * Obtient la valeur de la propriété cleared.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCleared() {
        return cleared;
    }

    /**
     * Définit la valeur de la propriété cleared.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCleared(XMLGregorianCalendar value) {
        this.cleared = value;
    }

    /**
     * Obtient la valeur de la propriété allocationsSubmitted.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllocationsSubmitted() {
        return allocationsSubmitted;
    }

    /**
     * Définit la valeur de la propriété allocationsSubmitted.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllocationsSubmitted(XMLGregorianCalendar value) {
        this.allocationsSubmitted = value;
    }

    /**
     * Obtient la valeur de la propriété allocationsUpdated.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllocationsUpdated() {
        return allocationsUpdated;
    }

    /**
     * Définit la valeur de la propriété allocationsUpdated.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllocationsUpdated(XMLGregorianCalendar value) {
        this.allocationsUpdated = value;
    }

    /**
     * Obtient la valeur de la propriété allocationsCompleted.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllocationsCompleted() {
        return allocationsCompleted;
    }

    /**
     * Définit la valeur de la propriété allocationsCompleted.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllocationsCompleted(XMLGregorianCalendar value) {
        this.allocationsCompleted = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timestamp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimestamp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeTimestamp }
     * 
     * 
     */
    public List<TradeTimestamp> getTimestamp() {
        if (timestamp == null) {
            timestamp = new ArrayList<TradeTimestamp>();
        }
        return this.timestamp;
    }

}
