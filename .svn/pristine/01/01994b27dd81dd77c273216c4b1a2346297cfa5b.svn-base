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
 * A type defining one or more trade identifiers allocated to the trade by a party. A link identifier allows the trade to be associated with other related trades, e.g. trades forming part of a larger structured transaction. It is expected that for external communication of trade there will be only one tradeId sent in the document per party.
 * 
 * <p>Classe Java pour PartyTradeIdentifier complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PartyTradeIdentifier">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier">
 *       &lt;sequence>
 *         &lt;element name="linkId" type="{http://www.fpml.org/FpML-5/recordkeeping}LinkId" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="allocationTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="blockTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" minOccurs="0"/>
 *         &lt;element name="originatingTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyTradeIdentifier", propOrder = {
    "linkId",
    "allocationTradeId",
    "blockTradeId",
    "originatingTradeId"
})
public class PartyTradeIdentifier
    extends TradeIdentifier
{

    protected List<LinkId> linkId;
    protected List<TradeIdentifier> allocationTradeId;
    protected TradeIdentifier blockTradeId;
    protected List<TradeIdentifier> originatingTradeId;

    /**
     * Gets the value of the linkId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkId }
     * 
     * 
     */
    public List<LinkId> getLinkId() {
        if (linkId == null) {
            linkId = new ArrayList<LinkId>();
        }
        return this.linkId;
    }

    /**
     * Gets the value of the allocationTradeId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allocationTradeId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllocationTradeId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeIdentifier }
     * 
     * 
     */
    public List<TradeIdentifier> getAllocationTradeId() {
        if (allocationTradeId == null) {
            allocationTradeId = new ArrayList<TradeIdentifier>();
        }
        return this.allocationTradeId;
    }

    /**
     * Obtient la valeur de la propriété blockTradeId.
     * 
     * @return
     *     possible object is
     *     {@link TradeIdentifier }
     *     
     */
    public TradeIdentifier getBlockTradeId() {
        return blockTradeId;
    }

    /**
     * Définit la valeur de la propriété blockTradeId.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeIdentifier }
     *     
     */
    public void setBlockTradeId(TradeIdentifier value) {
        this.blockTradeId = value;
    }

    /**
     * Gets the value of the originatingTradeId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the originatingTradeId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOriginatingTradeId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeIdentifier }
     * 
     * 
     */
    public List<TradeIdentifier> getOriginatingTradeId() {
        if (originatingTradeId == null) {
            originatingTradeId = new ArrayList<TradeIdentifier>();
        }
        return this.originatingTradeId;
    }

}
