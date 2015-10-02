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
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining trade related information which is not product specific.
 * 
 * <p>Classe Java pour TradeHeader complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyTradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" maxOccurs="unbounded"/>
 *         &lt;element name="partyTradeInformation" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeInformation" maxOccurs="unbounded"/>
 *         &lt;element name="tradeDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *         &lt;element name="clearedDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeHeader", propOrder = {
    "partyTradeIdentifier",
    "partyTradeInformation",
    "tradeDate",
    "clearedDate"
})
public class TradeHeader {

    @XmlElement(required = true)
    protected List<PartyTradeIdentifier> partyTradeIdentifier;
    @XmlElement(required = true)
    protected List<PartyTradeInformation> partyTradeInformation;
    protected IdentifiedDate tradeDate;
    protected IdentifiedDate clearedDate;

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
     * Gets the value of the partyTradeInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyTradeInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyTradeInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyTradeInformation }
     * 
     * 
     */
    public List<PartyTradeInformation> getPartyTradeInformation() {
        if (partyTradeInformation == null) {
            partyTradeInformation = new ArrayList<PartyTradeInformation>();
        }
        return this.partyTradeInformation;
    }

    /**
     * Obtient la valeur de la propriété tradeDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getTradeDate() {
        return tradeDate;
    }

    /**
     * Définit la valeur de la propriété tradeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setTradeDate(IdentifiedDate value) {
        this.tradeDate = value;
    }

    /**
     * Obtient la valeur de la propriété clearedDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getClearedDate() {
        return clearedDate;
    }

    /**
     * Définit la valeur de la propriété clearedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setClearedDate(IdentifiedDate value) {
        this.clearedDate = value;
    }

}
