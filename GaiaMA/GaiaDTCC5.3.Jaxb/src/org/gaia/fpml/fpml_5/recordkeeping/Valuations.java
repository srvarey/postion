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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * A set of valuation.
 * 
 * <p>Classe Java pour Valuations complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Valuations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AssetValuationOrReference.model" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Valuations", propOrder = {
    "assetValuationOrReferenceModel"
})
public class Valuations {

    @XmlElements({
        @XmlElement(name = "valuation", type = AssetValuation.class),
        @XmlElement(name = "valuationReference", type = ValuationReference.class)
    })
    protected List<Object> assetValuationOrReferenceModel;

    /**
     * Gets the value of the assetValuationOrReferenceModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetValuationOrReferenceModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetValuationOrReferenceModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetValuation }
     * {@link ValuationReference }
     * 
     * 
     */
    public List<Object> getAssetValuationOrReferenceModel() {
        if (assetValuationOrReferenceModel == null) {
            assetValuationOrReferenceModel = new ArrayList<Object>();
        }
        return this.assetValuationOrReferenceModel;
    }

}
