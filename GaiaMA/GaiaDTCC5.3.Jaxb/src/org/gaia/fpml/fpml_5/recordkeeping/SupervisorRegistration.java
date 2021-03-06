//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Provides information about a regulator or other supervisory body that an organization is registered with.
 * 
 * <p>Classe Java pour SupervisorRegistration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SupervisorRegistration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}SupervisorRegistration.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupervisorRegistration", propOrder = {
    "supervisoryBody",
    "registrationNumber"
})
public class SupervisorRegistration {

    @XmlElement(required = true)
    protected SupervisoryBody supervisoryBody;
    protected RegulatorId registrationNumber;

    /**
     * Obtient la valeur de la propriété supervisoryBody.
     * 
     * @return
     *     possible object is
     *     {@link SupervisoryBody }
     *     
     */
    public SupervisoryBody getSupervisoryBody() {
        return supervisoryBody;
    }

    /**
     * Définit la valeur de la propriété supervisoryBody.
     * 
     * @param value
     *     allowed object is
     *     {@link SupervisoryBody }
     *     
     */
    public void setSupervisoryBody(SupervisoryBody value) {
        this.supervisoryBody = value;
    }

    /**
     * Obtient la valeur de la propriété registrationNumber.
     * 
     * @return
     *     possible object is
     *     {@link RegulatorId }
     *     
     */
    public RegulatorId getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Définit la valeur de la propriété registrationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link RegulatorId }
     *     
     */
    public void setRegistrationNumber(RegulatorId value) {
        this.registrationNumber = value;
    }

}
