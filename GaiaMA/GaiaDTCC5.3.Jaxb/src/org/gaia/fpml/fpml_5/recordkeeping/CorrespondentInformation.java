//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type that describes the information to identify a correspondent bank that will make delivery of the funds on the paying bank's behalf in the country where the payment is to be made.
 * 
 * <p>Classe Java pour CorrespondentInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CorrespondentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}RoutingIdentification.model"/>
 *         &lt;element name="correspondentPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrespondentInformation", propOrder = {
    "routingIds",
    "routingExplicitDetails",
    "routingIdsAndExplicitDetails",
    "correspondentPartyReference"
})
public class CorrespondentInformation {

    protected RoutingIds routingIds;
    protected RoutingExplicitDetails routingExplicitDetails;
    protected RoutingIdsAndExplicitDetails routingIdsAndExplicitDetails;
    protected PartyReference correspondentPartyReference;

    /**
     * Obtient la valeur de la propriété routingIds.
     * 
     * @return
     *     possible object is
     *     {@link RoutingIds }
     *     
     */
    public RoutingIds getRoutingIds() {
        return routingIds;
    }

    /**
     * Définit la valeur de la propriété routingIds.
     * 
     * @param value
     *     allowed object is
     *     {@link RoutingIds }
     *     
     */
    public void setRoutingIds(RoutingIds value) {
        this.routingIds = value;
    }

    /**
     * Obtient la valeur de la propriété routingExplicitDetails.
     * 
     * @return
     *     possible object is
     *     {@link RoutingExplicitDetails }
     *     
     */
    public RoutingExplicitDetails getRoutingExplicitDetails() {
        return routingExplicitDetails;
    }

    /**
     * Définit la valeur de la propriété routingExplicitDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link RoutingExplicitDetails }
     *     
     */
    public void setRoutingExplicitDetails(RoutingExplicitDetails value) {
        this.routingExplicitDetails = value;
    }

    /**
     * Obtient la valeur de la propriété routingIdsAndExplicitDetails.
     * 
     * @return
     *     possible object is
     *     {@link RoutingIdsAndExplicitDetails }
     *     
     */
    public RoutingIdsAndExplicitDetails getRoutingIdsAndExplicitDetails() {
        return routingIdsAndExplicitDetails;
    }

    /**
     * Définit la valeur de la propriété routingIdsAndExplicitDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link RoutingIdsAndExplicitDetails }
     *     
     */
    public void setRoutingIdsAndExplicitDetails(RoutingIdsAndExplicitDetails value) {
        this.routingIdsAndExplicitDetails = value;
    }

    /**
     * Obtient la valeur de la propriété correspondentPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getCorrespondentPartyReference() {
        return correspondentPartyReference;
    }

    /**
     * Définit la valeur de la propriété correspondentPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setCorrespondentPartyReference(PartyReference value) {
        this.correspondentPartyReference = value;
    }

}
