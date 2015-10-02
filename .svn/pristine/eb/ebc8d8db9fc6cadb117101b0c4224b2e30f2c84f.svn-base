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
 * A type defining the content model for a message normally generated in response to a RequestValuationReport request.
 * 
 * <p>Classe Java pour ValuationReport complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ValuationReport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}NotificationMessage">
 *       &lt;sequence>
 *         &lt;element name="reportIdentification" type="{http://www.fpml.org/FpML-5/recordkeeping}ReportIdentification" minOccurs="0"/>
 *         &lt;element name="reportContents" type="{http://www.fpml.org/FpML-5/recordkeeping}ReportContents" minOccurs="0"/>
 *         &lt;element name="asOfDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartiesAndAccounts.model"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}market" minOccurs="0"/>
 *         &lt;element name="portfolioValuationItem" type="{http://www.fpml.org/FpML-5/recordkeeping}PortfolioValuationItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tradeValuationItem" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeValuationItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValuationReport", propOrder = {
    "reportIdentification",
    "reportContents",
    "asOfDate",
    "party",
    "account",
    "market",
    "portfolioValuationItem",
    "tradeValuationItem"
})
public class ValuationReport
    extends NotificationMessage
{

    protected ReportIdentification reportIdentification;
    protected ReportContents reportContents;
    protected IdentifiedDate asOfDate;
    protected List<Party> party;
    protected List<Account> account;
    protected Market market;
    protected List<PortfolioValuationItem> portfolioValuationItem;
    protected List<TradeValuationItem> tradeValuationItem;

    /**
     * Obtient la valeur de la propriété reportIdentification.
     * 
     * @return
     *     possible object is
     *     {@link ReportIdentification }
     *     
     */
    public ReportIdentification getReportIdentification() {
        return reportIdentification;
    }

    /**
     * Définit la valeur de la propriété reportIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportIdentification }
     *     
     */
    public void setReportIdentification(ReportIdentification value) {
        this.reportIdentification = value;
    }

    /**
     * Obtient la valeur de la propriété reportContents.
     * 
     * @return
     *     possible object is
     *     {@link ReportContents }
     *     
     */
    public ReportContents getReportContents() {
        return reportContents;
    }

    /**
     * Définit la valeur de la propriété reportContents.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportContents }
     *     
     */
    public void setReportContents(ReportContents value) {
        this.reportContents = value;
    }

    /**
     * Obtient la valeur de la propriété asOfDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getAsOfDate() {
        return asOfDate;
    }

    /**
     * Définit la valeur de la propriété asOfDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setAsOfDate(IdentifiedDate value) {
        this.asOfDate = value;
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

    /**
     * Obtient la valeur de la propriété market.
     * 
     * @return
     *     possible object is
     *     {@link Market }
     *     
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Définit la valeur de la propriété market.
     * 
     * @param value
     *     allowed object is
     *     {@link Market }
     *     
     */
    public void setMarket(Market value) {
        this.market = value;
    }

    /**
     * Gets the value of the portfolioValuationItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portfolioValuationItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortfolioValuationItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortfolioValuationItem }
     * 
     * 
     */
    public List<PortfolioValuationItem> getPortfolioValuationItem() {
        if (portfolioValuationItem == null) {
            portfolioValuationItem = new ArrayList<PortfolioValuationItem>();
        }
        return this.portfolioValuationItem;
    }

    /**
     * Gets the value of the tradeValuationItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tradeValuationItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTradeValuationItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeValuationItem }
     * 
     * 
     */
    public List<TradeValuationItem> getTradeValuationItem() {
        if (tradeValuationItem == null) {
            tradeValuationItem = new ArrayList<TradeValuationItem>();
        }
        return this.tradeValuationItem;
    }

}
