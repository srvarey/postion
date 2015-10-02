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
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the content model for a message allowing one party to query the status of one event (trade or post-trade event) previously sent to another party.
 * 
 * <p>Classe Java pour RequestEventStatus complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RequestEventStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}NonCorrectableRequestMessage">
 *       &lt;sequence>
 *         &lt;element name="businessProcess" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessProcess" minOccurs="0"/>
 *         &lt;element name="eventIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}EventIdentifier" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartiesAndAccounts.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestEventStatus", propOrder = {
    "businessProcess",
    "eventIdentifier",
    "party",
    "account"
})
public class RequestEventStatus
    extends NonCorrectableRequestMessage
{

    protected BusinessProcess businessProcess;
    protected EventIdentifier eventIdentifier;
    protected List<Party> party;
    protected List<Account> account;

    /**
     * Obtient la valeur de la propriété businessProcess.
     * 
     * @return
     *     possible object is
     *     {@link BusinessProcess }
     *     
     */
    public BusinessProcess getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Définit la valeur de la propriété businessProcess.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessProcess }
     *     
     */
    public void setBusinessProcess(BusinessProcess value) {
        this.businessProcess = value;
    }

    /**
     * Obtient la valeur de la propriété eventIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link EventIdentifier }
     *     
     */
    public EventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }

    /**
     * Définit la valeur de la propriété eventIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link EventIdentifier }
     *     
     */
    public void setEventIdentifier(EventIdentifier value) {
        this.eventIdentifier = value;
    }

    /**
     * Gets the value of the party property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the party property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Party }
     * 
     * 
     */
    public List<Party> getParty() {
        if (party == null) {
            party = new ArrayList<Party>();
        }
        return this.party;
    }

    /**
     * Gets the value of the account property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the account property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Account }
     * 
     * 
     */
    public List<Account> getAccount() {
        if (account == null) {
            account = new ArrayList<Account>();
        }
        return this.account;
    }

}
