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
 * A type used to record the differences between the current trade and another indicated trade.
 * 
 * <p>Classe Java pour BestFitTrade complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BestFitTrade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" minOccurs="0"/>
 *         &lt;element name="differences" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeDifference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BestFitTrade", propOrder = {
    "tradeIdentifier",
    "differences"
})
public class BestFitTrade {

    protected TradeIdentifier tradeIdentifier;
    protected List<TradeDifference> differences;

    /**
     * Obtient la valeur de la propriété tradeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link TradeIdentifier }
     *     
     */
    public TradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    /**
     * Définit la valeur de la propriété tradeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeIdentifier }
     *     
     */
    public void setTradeIdentifier(TradeIdentifier value) {
        this.tradeIdentifier = value;
    }

    /**
     * Gets the value of the differences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the differences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDifferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeDifference }
     * 
     * 
     */
    public List<TradeDifference> getDifferences() {
        if (differences == null) {
            differences = new ArrayList<TradeDifference>();
        }
        return this.differences;
    }

}
