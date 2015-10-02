//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour Allocation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Allocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allocationTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartyAndAccountReferences.model"/>
 *         &lt;choice>
 *           &lt;element name="allocatedFraction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;element name="allocatedNotional" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" maxOccurs="2" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AllocationContent.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Allocation", propOrder = {
    "allocationTradeId",
    "partyReference",
    "accountReference",
    "allocatedFraction",
    "allocatedNotional",
    "collateral",
    "creditChargeAmount",
    "approvals",
    "masterConfirmationDate",
    "relatedParty"
})
public class Allocation {

    protected TradeIdentifier allocationTradeId;
    @XmlElement(required = true)
    protected PartyReference partyReference;
    protected AccountReference accountReference;
    protected BigDecimal allocatedFraction;
    protected List<Money> allocatedNotional;
    protected Collateral collateral;
    protected Money creditChargeAmount;
    protected Approvals approvals;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar masterConfirmationDate;
    protected List<RelatedParty> relatedParty;

    /**
     * Obtient la valeur de la propriété allocationTradeId.
     * 
     * @return
     *     possible object is
     *     {@link TradeIdentifier }
     *     
     */
    public TradeIdentifier getAllocationTradeId() {
        return allocationTradeId;
    }

    /**
     * Définit la valeur de la propriété allocationTradeId.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeIdentifier }
     *     
     */
    public void setAllocationTradeId(TradeIdentifier value) {
        this.allocationTradeId = value;
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
     * Obtient la valeur de la propriété allocatedFraction.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAllocatedFraction() {
        return allocatedFraction;
    }

    /**
     * Définit la valeur de la propriété allocatedFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAllocatedFraction(BigDecimal value) {
        this.allocatedFraction = value;
    }

    /**
     * Gets the value of the allocatedNotional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allocatedNotional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllocatedNotional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Money }
     * 
     * 
     */
    public List<Money> getAllocatedNotional() {
        if (allocatedNotional == null) {
            allocatedNotional = new ArrayList<Money>();
        }
        return this.allocatedNotional;
    }

    /**
     * Obtient la valeur de la propriété collateral.
     * 
     * @return
     *     possible object is
     *     {@link Collateral }
     *     
     */
    public Collateral getCollateral() {
        return collateral;
    }

    /**
     * Définit la valeur de la propriété collateral.
     * 
     * @param value
     *     allowed object is
     *     {@link Collateral }
     *     
     */
    public void setCollateral(Collateral value) {
        this.collateral = value;
    }

    /**
     * Obtient la valeur de la propriété creditChargeAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getCreditChargeAmount() {
        return creditChargeAmount;
    }

    /**
     * Définit la valeur de la propriété creditChargeAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setCreditChargeAmount(Money value) {
        this.creditChargeAmount = value;
    }

    /**
     * Obtient la valeur de la propriété approvals.
     * 
     * @return
     *     possible object is
     *     {@link Approvals }
     *     
     */
    public Approvals getApprovals() {
        return approvals;
    }

    /**
     * Définit la valeur de la propriété approvals.
     * 
     * @param value
     *     allowed object is
     *     {@link Approvals }
     *     
     */
    public void setApprovals(Approvals value) {
        this.approvals = value;
    }

    /**
     * Obtient la valeur de la propriété masterConfirmationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMasterConfirmationDate() {
        return masterConfirmationDate;
    }

    /**
     * Définit la valeur de la propriété masterConfirmationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMasterConfirmationDate(XMLGregorianCalendar value) {
        this.masterConfirmationDate = value;
    }

    /**
     * Gets the value of the relatedParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedParty }
     * 
     * 
     */
    public List<RelatedParty> getRelatedParty() {
        if (relatedParty == null) {
            relatedParty = new ArrayList<RelatedParty>();
        }
        return this.relatedParty;
    }

}
