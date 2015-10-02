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
 * A type defining a content model that includes valuation (pricing and risk) data without expressing any processing intention.
 * 
 * <p>Classe Java pour ValuationDocument complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ValuationDocument">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DataDocument">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}market" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}valuationSet" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValuationDocument", propOrder = {
    "market",
    "valuationSet"
})
public class ValuationDocument
    extends DataDocument
{

    protected List<Market> market;
    protected List<ValuationSet> valuationSet;

    /**
     * Gets the value of the market property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the market property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Market }
     * 
     * 
     */
    public List<Market> getMarket() {
        if (market == null) {
            market = new ArrayList<Market>();
        }
        return this.market;
    }

    /**
     * Gets the value of the valuationSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valuationSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValuationSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuationSet }
     * 
     * 
     */
    public List<ValuationSet> getValuationSet() {
        if (valuationSet == null) {
            valuationSet = new ArrayList<ValuationSet>();
        }
        return this.valuationSet;
    }

}
