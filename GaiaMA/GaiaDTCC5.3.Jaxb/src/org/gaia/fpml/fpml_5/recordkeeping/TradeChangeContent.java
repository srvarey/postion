//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A structure describing a non-negotiated trade resulting from a market event.
 * 
 * <p>Classe Java pour TradeChangeContent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeChangeContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="oldTradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" minOccurs="0"/>
 *           &lt;element name="oldTrade" type="{http://www.fpml.org/FpML-5/recordkeeping}Trade" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="trade" type="{http://www.fpml.org/FpML-5/recordkeeping}Trade" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}changeEvent" minOccurs="0"/>
 *         &lt;element name="payment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeChangeContent", propOrder = {
    "oldTradeIdentifier",
    "oldTrade",
    "trade",
    "effectiveDate",
    "changeEvent",
    "payment"
})
public class TradeChangeContent {

    protected PartyTradeIdentifier oldTradeIdentifier;
    protected Trade oldTrade;
    protected Trade trade;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlElementRef(name = "changeEvent", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends ChangeEvent> changeEvent;
    protected Payment payment;

    /**
     * Obtient la valeur de la propriété oldTradeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link PartyTradeIdentifier }
     *     
     */
    public PartyTradeIdentifier getOldTradeIdentifier() {
        return oldTradeIdentifier;
    }

    /**
     * Définit la valeur de la propriété oldTradeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyTradeIdentifier }
     *     
     */
    public void setOldTradeIdentifier(PartyTradeIdentifier value) {
        this.oldTradeIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété oldTrade.
     * 
     * @return
     *     possible object is
     *     {@link Trade }
     *     
     */
    public Trade getOldTrade() {
        return oldTrade;
    }

    /**
     * Définit la valeur de la propriété oldTrade.
     * 
     * @param value
     *     allowed object is
     *     {@link Trade }
     *     
     */
    public void setOldTrade(Trade value) {
        this.oldTrade = value;
    }

    /**
     * Obtient la valeur de la propriété trade.
     * 
     * @return
     *     possible object is
     *     {@link Trade }
     *     
     */
    public Trade getTrade() {
        return trade;
    }

    /**
     * Définit la valeur de la propriété trade.
     * 
     * @param value
     *     allowed object is
     *     {@link Trade }
     *     
     */
    public void setTrade(Trade value) {
        this.trade = value;
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
     * Substitution point for types of change
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangeEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link IndexChange }{@code >}
     *     
     */
    public JAXBElement<? extends ChangeEvent> getChangeEvent() {
        return changeEvent;
    }

    /**
     * Définit la valeur de la propriété changeEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangeEvent }{@code >}
     *     {@link JAXBElement }{@code <}{@link IndexChange }{@code >}
     *     
     */
    public void setChangeEvent(JAXBElement<? extends ChangeEvent> value) {
        this.changeEvent = value;
    }

    /**
     * Obtient la valeur de la propriété payment.
     * 
     * @return
     *     possible object is
     *     {@link Payment }
     *     
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Définit la valeur de la propriété payment.
     * 
     * @param value
     *     allowed object is
     *     {@link Payment }
     *     
     */
    public void setPayment(Payment value) {
        this.payment = value;
    }

}
