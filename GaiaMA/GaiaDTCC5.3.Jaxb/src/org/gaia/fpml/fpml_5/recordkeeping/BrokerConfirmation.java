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
 * Identifies the market sector in which the trade has been arranged.
 * 
 * <p>Classe Java pour BrokerConfirmation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BrokerConfirmation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brokerConfirmationType" type="{http://www.fpml.org/FpML-5/recordkeeping}BrokerConfirmationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrokerConfirmation", propOrder = {
    "brokerConfirmationType"
})
public class BrokerConfirmation {

    protected BrokerConfirmationType brokerConfirmationType;

    /**
     * Obtient la valeur de la propriété brokerConfirmationType.
     * 
     * @return
     *     possible object is
     *     {@link BrokerConfirmationType }
     *     
     */
    public BrokerConfirmationType getBrokerConfirmationType() {
        return brokerConfirmationType;
    }

    /**
     * Définit la valeur de la propriété brokerConfirmationType.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerConfirmationType }
     *     
     */
    public void setBrokerConfirmationType(BrokerConfirmationType value) {
        this.brokerConfirmationType = value;
    }

}
