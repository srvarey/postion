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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A collection of quoted assets.
 * 
 * <p>Classe Java pour QuotedAssetSet complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="QuotedAssetSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instrumentSet" type="{http://www.fpml.org/FpML-5/recordkeeping}InstrumentSet" minOccurs="0"/>
 *         &lt;element name="assetQuote" type="{http://www.fpml.org/FpML-5/recordkeeping}BasicAssetValuation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuotedAssetSet", propOrder = {
    "instrumentSet",
    "assetQuote"
})
@XmlSeeAlso({
    FxRateSet.class
})
public class QuotedAssetSet {

    protected InstrumentSet instrumentSet;
    protected List<BasicAssetValuation> assetQuote;

    /**
     * Obtient la valeur de la propriété instrumentSet.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentSet }
     *     
     */
    public InstrumentSet getInstrumentSet() {
        return instrumentSet;
    }

    /**
     * Définit la valeur de la propriété instrumentSet.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentSet }
     *     
     */
    public void setInstrumentSet(InstrumentSet value) {
        this.instrumentSet = value;
    }

    /**
     * Gets the value of the assetQuote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetQuote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetQuote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasicAssetValuation }
     * 
     * 
     */
    public List<BasicAssetValuation> getAssetQuote() {
        if (assetQuote == null) {
            assetQuote = new ArrayList<BasicAssetValuation>();
        }
        return this.assetQuote;
    }

}
