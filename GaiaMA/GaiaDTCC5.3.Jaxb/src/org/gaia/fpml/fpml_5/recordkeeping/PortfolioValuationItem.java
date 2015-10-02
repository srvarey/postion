//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A type used in valuation enquiry messages which relates a portfolio to its trades and current value.
 * 
 * <p>Classe Java pour PortfolioValuationItem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PortfolioValuationItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}portfolio" minOccurs="0"/>
 *         &lt;element name="tradeValuationItem" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeValuationItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}valuationSet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortfolioValuationItem", propOrder = {
    "portfolio",
    "tradeValuationItem",
    "valuationSet"
})
public class PortfolioValuationItem {

    @XmlElementRef(name = "portfolio", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Portfolio> portfolio;
    protected List<TradeValuationItem> tradeValuationItem;
    protected ValuationSet valuationSet;

    /**
     * Portfolio identifier
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QueryPortfolio }{@code >}
     *     {@link JAXBElement }{@code <}{@link Portfolio }{@code >}
     *     
     */
    public JAXBElement<? extends Portfolio> getPortfolio() {
        return portfolio;
    }

    /**
     * Définit la valeur de la propriété portfolio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QueryPortfolio }{@code >}
     *     {@link JAXBElement }{@code <}{@link Portfolio }{@code >}
     *     
     */
    public void setPortfolio(JAXBElement<? extends Portfolio> value) {
        this.portfolio = value;
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

    /**
     * The portfolio valuation.
     * 
     * @return
     *     possible object is
     *     {@link ValuationSet }
     *     
     */
    public ValuationSet getValuationSet() {
        return valuationSet;
    }

    /**
     * Définit la valeur de la propriété valuationSet.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationSet }
     *     
     */
    public void setValuationSet(ValuationSet value) {
        this.valuationSet = value;
    }

}
