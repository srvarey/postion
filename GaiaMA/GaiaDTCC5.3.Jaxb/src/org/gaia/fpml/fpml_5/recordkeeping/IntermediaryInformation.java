//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type that describes the information to identify an intermediary through which payment will be made by the correspondent bank to the ultimate beneficiary of the funds.
 * 
 * <p>Classe Java pour IntermediaryInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="IntermediaryInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}RoutingIdentification.model"/>
 *         &lt;element name="intermediarySequenceNumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="intermediaryPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntermediaryInformation", propOrder = {
    "routingIds",
    "routingExplicitDetails",
    "routingIdsAndExplicitDetails",
    "intermediarySequenceNumber",
    "intermediaryPartyReference"
})
public class IntermediaryInformation {

    protected RoutingIds routingIds;
    protected RoutingExplicitDetails routingExplicitDetails;
    protected RoutingIdsAndExplicitDetails routingIdsAndExplicitDetails;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger intermediarySequenceNumber;
    protected PartyReference intermediaryPartyReference;

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
     * Obtient la valeur de la propriété intermediarySequenceNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIntermediarySequenceNumber() {
        return intermediarySequenceNumber;
    }

    /**
     * Définit la valeur de la propriété intermediarySequenceNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIntermediarySequenceNumber(BigInteger value) {
        this.intermediarySequenceNumber = value;
    }

    /**
     * Obtient la valeur de la propriété intermediaryPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getIntermediaryPartyReference() {
        return intermediaryPartyReference;
    }

    /**
     * Définit la valeur de la propriété intermediaryPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setIntermediaryPartyReference(PartyReference value) {
        this.intermediaryPartyReference = value;
    }

}
