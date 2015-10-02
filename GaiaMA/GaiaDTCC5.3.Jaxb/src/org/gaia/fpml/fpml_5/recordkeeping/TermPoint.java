//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A value point that can have a time dimension. Allows bid, mid, ask, and spread values to be represented.
 * 
 * <p>Classe Java pour TermPoint complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TermPoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="term" type="{http://www.fpml.org/FpML-5/recordkeeping}TimeDimension" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BidMidAsk.model"/>
 *         &lt;element name="spreadValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="definition" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetReference" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TermPoint", propOrder = {
    "term",
    "bid",
    "mid",
    "ask",
    "spreadValue",
    "definition"
})
public class TermPoint {

    protected TimeDimension term;
    protected BigDecimal bid;
    protected BigDecimal mid;
    protected BigDecimal ask;
    protected BigDecimal spreadValue;
    protected AssetReference definition;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété term.
     * 
     * @return
     *     possible object is
     *     {@link TimeDimension }
     *     
     */
    public TimeDimension getTerm() {
        return term;
    }

    /**
     * Définit la valeur de la propriété term.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeDimension }
     *     
     */
    public void setTerm(TimeDimension value) {
        this.term = value;
    }

    /**
     * Obtient la valeur de la propriété bid.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBid() {
        return bid;
    }

    /**
     * Définit la valeur de la propriété bid.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBid(BigDecimal value) {
        this.bid = value;
    }

    /**
     * Obtient la valeur de la propriété mid.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMid() {
        return mid;
    }

    /**
     * Définit la valeur de la propriété mid.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMid(BigDecimal value) {
        this.mid = value;
    }

    /**
     * Obtient la valeur de la propriété ask.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAsk() {
        return ask;
    }

    /**
     * Définit la valeur de la propriété ask.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAsk(BigDecimal value) {
        this.ask = value;
    }

    /**
     * Obtient la valeur de la propriété spreadValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpreadValue() {
        return spreadValue;
    }

    /**
     * Définit la valeur de la propriété spreadValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpreadValue(BigDecimal value) {
        this.spreadValue = value;
    }

    /**
     * Obtient la valeur de la propriété definition.
     * 
     * @return
     *     possible object is
     *     {@link AssetReference }
     *     
     */
    public AssetReference getDefinition() {
        return definition;
    }

    /**
     * Définit la valeur de la propriété definition.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetReference }
     *     
     */
    public void setDefinition(AssetReference value) {
        this.definition = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
