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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A structure describing a trade change.
 * 
 * <p>Classe Java pour TradeChangeBase complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeChangeBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}AbstractEvent">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="tradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="originalTrade" type="{http://www.fpml.org/FpML-5/recordkeeping}Trade"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AgreementAndEffectiveDates.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}TradeAlterationPayment.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeChangeBase", propOrder = {
    "tradeIdentifier",
    "originalTrade",
    "agreementDate",
    "executionDateTime",
    "effectiveDate",
    "payment"
})
@XmlSeeAlso({
    TradeNotionalChange.class
})
public class TradeChangeBase
    extends AbstractEvent
{

    protected List<PartyTradeIdentifier> tradeIdentifier;
    protected Trade originalTrade;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar agreementDate;
    @XmlElement(required = true)
    protected ExecutionDateTime executionDateTime;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    protected Payment payment;

    /**
     * Gets the value of the tradeIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tradeIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTradeIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyTradeIdentifier }
     * 
     * 
     */
    public List<PartyTradeIdentifier> getTradeIdentifier() {
        if (tradeIdentifier == null) {
            tradeIdentifier = new ArrayList<PartyTradeIdentifier>();
        }
        return this.tradeIdentifier;
    }

    /**
     * Obtient la valeur de la propriété originalTrade.
     * 
     * @return
     *     possible object is
     *     {@link Trade }
     *     
     */
    public Trade getOriginalTrade() {
        return originalTrade;
    }

    /**
     * Définit la valeur de la propriété originalTrade.
     * 
     * @param value
     *     allowed object is
     *     {@link Trade }
     *     
     */
    public void setOriginalTrade(Trade value) {
        this.originalTrade = value;
    }

    /**
     * Obtient la valeur de la propriété agreementDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAgreementDate() {
        return agreementDate;
    }

    /**
     * Définit la valeur de la propriété agreementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAgreementDate(XMLGregorianCalendar value) {
        this.agreementDate = value;
    }

    /**
     * Obtient la valeur de la propriété executionDateTime.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionDateTime }
     *     
     */
    public ExecutionDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    /**
     * Définit la valeur de la propriété executionDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionDateTime }
     *     
     */
    public void setExecutionDateTime(ExecutionDateTime value) {
        this.executionDateTime = value;
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
