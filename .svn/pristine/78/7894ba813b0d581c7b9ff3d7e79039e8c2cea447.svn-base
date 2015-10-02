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
 * A structure to specify the tranmission contingency and the party that bears the obligation.
 * 
 * <p>Classe Java pour ElectricityTransmissionContingency complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ElectricityTransmissionContingency">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contingency" type="{http://www.fpml.org/FpML-5/recordkeeping}ElectricityTransmissionContingencyType" minOccurs="0"/>
 *         &lt;element name="contingentParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElectricityTransmissionContingency", propOrder = {
    "contingency",
    "contingentParty"
})
public class ElectricityTransmissionContingency {

    protected ElectricityTransmissionContingencyType contingency;
    protected List<PartyReference> contingentParty;

    /**
     * Obtient la valeur de la propriété contingency.
     * 
     * @return
     *     possible object is
     *     {@link ElectricityTransmissionContingencyType }
     *     
     */
    public ElectricityTransmissionContingencyType getContingency() {
        return contingency;
    }

    /**
     * Définit la valeur de la propriété contingency.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricityTransmissionContingencyType }
     *     
     */
    public void setContingency(ElectricityTransmissionContingencyType value) {
        this.contingency = value;
    }

    /**
     * Gets the value of the contingentParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contingentParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContingentParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getContingentParty() {
        if (contingentParty == null) {
            contingentParty = new ArrayList<PartyReference>();
        }
        return this.contingentParty;
    }

}
