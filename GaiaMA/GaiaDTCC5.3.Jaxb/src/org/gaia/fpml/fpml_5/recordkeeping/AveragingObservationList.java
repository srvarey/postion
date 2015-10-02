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
 * An un ordered list of weighted averaging observations.
 * 
 * <p>Classe Java pour AveragingObservationList complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AveragingObservationList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="averagingObservation" type="{http://www.fpml.org/FpML-5/recordkeeping}WeightedAveragingObservation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AveragingObservationList", propOrder = {
    "averagingObservation"
})
public class AveragingObservationList {

    protected List<WeightedAveragingObservation> averagingObservation;

    /**
     * Gets the value of the averagingObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the averagingObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAveragingObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeightedAveragingObservation }
     * 
     * 
     */
    public List<WeightedAveragingObservation> getAveragingObservation() {
        if (averagingObservation == null) {
            averagingObservation = new ArrayList<WeightedAveragingObservation>();
        }
        return this.averagingObservation;
    }

}
