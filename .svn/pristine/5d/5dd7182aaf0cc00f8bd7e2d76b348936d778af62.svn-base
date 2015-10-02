//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * The items (trades, trade references, holdings, other positions) that comprise this position. Currently a position may consist only of a single trade, a reference to a previously submitted position, or a reference to the trade. The choice structure is optional to allow extensions to be placed within this container.
 * 
 * <p>Classe Java pour PositionConstituent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PositionConstituent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice minOccurs="0">
 *         &lt;element name="trade" type="{http://www.fpml.org/FpML-5/recordkeeping}Trade" minOccurs="0"/>
 *         &lt;element name="positionVersionReference" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="tradeReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyTradeIdentifiers" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionConstituent", propOrder = {
    "trade",
    "positionVersionReference",
    "tradeReference"
})
public class PositionConstituent {

    protected Trade trade;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger positionVersionReference;
    protected PartyTradeIdentifiers tradeReference;

    /**
     * Obtient la valeur de la propriété trade.
     * 
     * @return
     *     possible object is
     *     {@link Trade }
     *     
     */
    public Trade getTrade() {
        return trade;
    }

    /**
     * Définit la valeur de la propriété trade.
     * 
     * @param value
     *     allowed object is
     *     {@link Trade }
     *     
     */
    public void setTrade(Trade value) {
        this.trade = value;
    }

    /**
     * Obtient la valeur de la propriété positionVersionReference.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPositionVersionReference() {
        return positionVersionReference;
    }

    /**
     * Définit la valeur de la propriété positionVersionReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPositionVersionReference(BigInteger value) {
        this.positionVersionReference = value;
    }

    /**
     * Obtient la valeur de la propriété tradeReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyTradeIdentifiers }
     *     
     */
    public PartyTradeIdentifiers getTradeReference() {
        return tradeReference;
    }

    /**
     * Définit la valeur de la propriété tradeReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyTradeIdentifiers }
     *     
     */
    public void setTradeReference(PartyTradeIdentifiers value) {
        this.tradeReference = value;
    }

}
