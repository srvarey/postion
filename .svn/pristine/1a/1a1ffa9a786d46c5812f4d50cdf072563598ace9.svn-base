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
 * A structure describing the removal of a trade from a service, such as a reporting service.
 * 
 * <p>Classe Java pour Withdrawal complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Withdrawal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyTradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="requestedAction" type="{http://www.fpml.org/FpML-5/recordkeeping}RequestedWithdrawalAction" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.fpml.org/FpML-5/recordkeeping}WithdrawalReason" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Withdrawal", propOrder = {
    "partyTradeIdentifier",
    "effectiveDate",
    "requestedAction",
    "reason"
})
public class Withdrawal {

    protected List<PartyTradeIdentifier> partyTradeIdentifier;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    protected RequestedWithdrawalAction requestedAction;
    protected WithdrawalReason reason;

    /**
     * Gets the value of the partyTradeIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyTradeIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyTradeIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyTradeIdentifier }
     * 
     * 
     */
    public List<PartyTradeIdentifier> getPartyTradeIdentifier() {
        if (partyTradeIdentifier == null) {
            partyTradeIdentifier = new ArrayList<PartyTradeIdentifier>();
        }
        return this.partyTradeIdentifier;
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
     * Obtient la valeur de la propriété requestedAction.
     * 
     * @return
     *     possible object is
     *     {@link RequestedWithdrawalAction }
     *     
     */
    public RequestedWithdrawalAction getRequestedAction() {
        return requestedAction;
    }

    /**
     * Définit la valeur de la propriété requestedAction.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestedWithdrawalAction }
     *     
     */
    public void setRequestedAction(RequestedWithdrawalAction value) {
        this.requestedAction = value;
    }

    /**
     * Obtient la valeur de la propriété reason.
     * 
     * @return
     *     possible object is
     *     {@link WithdrawalReason }
     *     
     */
    public WithdrawalReason getReason() {
        return reason;
    }

    /**
     * Définit la valeur de la propriété reason.
     * 
     * @param value
     *     allowed object is
     *     {@link WithdrawalReason }
     *     
     */
    public void setReason(WithdrawalReason value) {
        this.reason = value;
    }

}
