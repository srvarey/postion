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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining a content model that is backwards compatible with older FpML releases and which can be used to contain sets of data without expressing any processing intention.
 * 
 * <p>Classe Java pour DataDocument complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DataDocument">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Document">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Validation.model"/>
 *         &lt;sequence>
 *           &lt;element name="onBehalfOf" type="{http://www.fpml.org/FpML-5/recordkeeping}OnBehalfOf" minOccurs="0"/>
 *           &lt;element name="originatingEvent" type="{http://www.fpml.org/FpML-5/recordkeeping}OriginatingEvent" minOccurs="0"/>
 *           &lt;element name="trade" type="{http://www.fpml.org/FpML-5/recordkeeping}Trade" maxOccurs="unbounded"/>
 *         &lt;/sequence>
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
@XmlType(name = "DataDocument", propOrder = {
    "validation",
    "onBehalfOf",
    "originatingEvent",
    "trade",
    "party",
    "account"
})
@XmlSeeAlso({
    ValuationDocument.class
})
public class DataDocument
    extends Document
{

    protected List<Validation> validation;
    protected OnBehalfOf onBehalfOf;
    protected OriginatingEvent originatingEvent;
    @XmlElement(required = true)
    protected List<Trade> trade;
    protected List<Party> party;
    protected List<Account> account;

    /**
     * Gets the value of the validation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Validation }
     * 
     * 
     */
    public List<Validation> getValidation() {
        if (validation == null) {
            validation = new ArrayList<Validation>();
        }
        return this.validation;
    }

    /**
     * Obtient la valeur de la propriété onBehalfOf.
     * 
     * @return
     *     possible object is
     *     {@link OnBehalfOf }
     *     
     */
    public OnBehalfOf getOnBehalfOf() {
        return onBehalfOf;
    }

    /**
     * Définit la valeur de la propriété onBehalfOf.
     * 
     * @param value
     *     allowed object is
     *     {@link OnBehalfOf }
     *     
     */
    public void setOnBehalfOf(OnBehalfOf value) {
        this.onBehalfOf = value;
    }

    /**
     * Obtient la valeur de la propriété originatingEvent.
     * 
     * @return
     *     possible object is
     *     {@link OriginatingEvent }
     *     
     */
    public OriginatingEvent getOriginatingEvent() {
        return originatingEvent;
    }

    /**
     * Définit la valeur de la propriété originatingEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginatingEvent }
     *     
     */
    public void setOriginatingEvent(OriginatingEvent value) {
        this.originatingEvent = value;
    }

    /**
     * Gets the value of the trade property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trade property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrade().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trade }
     * 
     * 
     */
    public List<Trade> getTrade() {
        if (trade == null) {
            trade = new ArrayList<Trade>();
        }
        return this.trade;
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
