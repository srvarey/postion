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
 * A message to request that a message be retransmitted. The original message will typically be a component of a group of messages, such as a portfolio or a report in multiple parts.
 * 
 * <p>Classe Java pour RequestRetransmission complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RequestRetransmission">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}NonCorrectableRequestMessage">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PortfolioReferenceOrReportIdentification.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartiesAndAccounts.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestRetransmission", propOrder = {
    "portfolioReference",
    "reportIdentification",
    "party",
    "account"
})
public class RequestRetransmission
    extends NonCorrectableRequestMessage
{

    protected PortfolioConstituentReference portfolioReference;
    protected ReportSectionIdentification reportIdentification;
    protected List<Party> party;
    protected List<Account> account;

    /**
     * Obtient la valeur de la propriété portfolioReference.
     * 
     * @return
     *     possible object is
     *     {@link PortfolioConstituentReference }
     *     
     */
    public PortfolioConstituentReference getPortfolioReference() {
        return portfolioReference;
    }

    /**
     * Définit la valeur de la propriété portfolioReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PortfolioConstituentReference }
     *     
     */
    public void setPortfolioReference(PortfolioConstituentReference value) {
        this.portfolioReference = value;
    }

    /**
     * Obtient la valeur de la propriété reportIdentification.
     * 
     * @return
     *     possible object is
     *     {@link ReportSectionIdentification }
     *     
     */
    public ReportSectionIdentification getReportIdentification() {
        return reportIdentification;
    }

    /**
     * Définit la valeur de la propriété reportIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportSectionIdentification }
     *     
     */
    public void setReportIdentification(ReportSectionIdentification value) {
        this.reportIdentification = value;
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
