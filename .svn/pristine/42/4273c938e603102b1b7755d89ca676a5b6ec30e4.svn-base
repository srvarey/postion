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
 * A type that provides a combination of payment system identification codes with physical postal address details, for the purposes of identifying a party involved in the routing of a payment.
 * 
 * <p>Classe Java pour RoutingIdsAndExplicitDetails complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RoutingIdsAndExplicitDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="routingIds" type="{http://www.fpml.org/FpML-5/recordkeeping}RoutingIds" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}RoutingExplicitDetails.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoutingIdsAndExplicitDetails", propOrder = {
    "routingIds",
    "routingName",
    "routingAddress",
    "routingAccountNumber",
    "routingReferenceText"
})
public class RoutingIdsAndExplicitDetails {

    protected List<RoutingIds> routingIds;
    protected String routingName;
    protected Address routingAddress;
    protected String routingAccountNumber;
    protected List<String> routingReferenceText;

    /**
     * Gets the value of the routingIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routingIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoutingIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoutingIds }
     * 
     * 
     */
    public List<RoutingIds> getRoutingIds() {
        if (routingIds == null) {
            routingIds = new ArrayList<RoutingIds>();
        }
        return this.routingIds;
    }

    /**
     * Obtient la valeur de la propriété routingName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingName() {
        return routingName;
    }

    /**
     * Définit la valeur de la propriété routingName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingName(String value) {
        this.routingName = value;
    }

    /**
     * Obtient la valeur de la propriété routingAddress.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getRoutingAddress() {
        return routingAddress;
    }

    /**
     * Définit la valeur de la propriété routingAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setRoutingAddress(Address value) {
        this.routingAddress = value;
    }

    /**
     * Obtient la valeur de la propriété routingAccountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingAccountNumber() {
        return routingAccountNumber;
    }

    /**
     * Définit la valeur de la propriété routingAccountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingAccountNumber(String value) {
        this.routingAccountNumber = value;
    }

    /**
     * Gets the value of the routingReferenceText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routingReferenceText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoutingReferenceText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoutingReferenceText() {
        if (routingReferenceText == null) {
            routingReferenceText = new ArrayList<String>();
        }
        return this.routingReferenceText;
    }

}
