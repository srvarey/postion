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
 * A type that shows how multiple trades have been combined into a result.
 * 
 * <p>Classe Java pour CompressionActivity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CompressionActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compressionType" type="{http://www.fpml.org/FpML-5/recordkeeping}CompressionType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="replacementTradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" minOccurs="0"/>
 *             &lt;element name="originatingTradeIdentifier" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="replacementTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeId" minOccurs="0"/>
 *             &lt;element name="originatingTradeId" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeId" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompressionActivity", propOrder = {
    "compressionType",
    "replacementTradeIdentifier",
    "originatingTradeIdentifier",
    "replacementTradeId",
    "originatingTradeId"
})
public class CompressionActivity {

    protected CompressionType compressionType;
    protected TradeIdentifier replacementTradeIdentifier;
    protected List<TradeIdentifier> originatingTradeIdentifier;
    protected TradeId replacementTradeId;
    protected List<TradeId> originatingTradeId;

    /**
     * Obtient la valeur de la propriété compressionType.
     * 
     * @return
     *     possible object is
     *     {@link CompressionType }
     *     
     */
    public CompressionType getCompressionType() {
        return compressionType;
    }

    /**
     * Définit la valeur de la propriété compressionType.
     * 
     * @param value
     *     allowed object is
     *     {@link CompressionType }
     *     
     */
    public void setCompressionType(CompressionType value) {
        this.compressionType = value;
    }

    /**
     * Obtient la valeur de la propriété replacementTradeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link TradeIdentifier }
     *     
     */
    public TradeIdentifier getReplacementTradeIdentifier() {
        return replacementTradeIdentifier;
    }

    /**
     * Définit la valeur de la propriété replacementTradeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeIdentifier }
     *     
     */
    public void setReplacementTradeIdentifier(TradeIdentifier value) {
        this.replacementTradeIdentifier = value;
    }

    /**
     * Gets the value of the originatingTradeIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the originatingTradeIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOriginatingTradeIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeIdentifier }
     * 
     * 
     */
    public List<TradeIdentifier> getOriginatingTradeIdentifier() {
        if (originatingTradeIdentifier == null) {
            originatingTradeIdentifier = new ArrayList<TradeIdentifier>();
        }
        return this.originatingTradeIdentifier;
    }

    /**
     * Obtient la valeur de la propriété replacementTradeId.
     * 
     * @return
     *     possible object is
     *     {@link TradeId }
     *     
     */
    public TradeId getReplacementTradeId() {
        return replacementTradeId;
    }

    /**
     * Définit la valeur de la propriété replacementTradeId.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeId }
     *     
     */
    public void setReplacementTradeId(TradeId value) {
        this.replacementTradeId = value;
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
     * {@link TradeId }
     * 
     * 
     */
    public List<TradeId> getOriginatingTradeId() {
        if (originatingTradeId == null) {
            originatingTradeId = new ArrayList<TradeId>();
        }
        return this.originatingTradeId;
    }

}
