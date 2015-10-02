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
 * A type that provides three alternative ways of identifying a party involved in the routing of a payment. The identification may use payment system identifiers only; actual name, address and other reference information; or a combination of both.
 * 
 * <p>Classe Java pour Routing complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Routing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}RoutingIdentification.model"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Routing", propOrder = {
    "routingIds",
    "routingExplicitDetails",
    "routingIdsAndExplicitDetails"
})
public class Routing {

    protected RoutingIds routingIds;
    protected RoutingExplicitDetails routingExplicitDetails;
    protected RoutingIdsAndExplicitDetails routingIdsAndExplicitDetails;

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

}
