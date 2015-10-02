//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type defining the content model for a message that allows a service to send a notification message to a user of the service.
 * 
 * <p>Classe Java pour ServiceNotification complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ServiceNotification">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}NotificationMessage">
 *       &lt;sequence>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="status" type="{http://www.fpml.org/FpML-5/recordkeeping}ServiceStatus" minOccurs="0"/>
 *           &lt;element name="processingStatus" type="{http://www.fpml.org/FpML-5/recordkeeping}ServiceProcessingStatus" minOccurs="0"/>
 *           &lt;element name="advisory" type="{http://www.fpml.org/FpML-5/recordkeeping}ServiceAdvisory" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceNotification", propOrder = {
    "serviceName",
    "status",
    "processingStatus",
    "advisory"
})
public class ServiceNotification
    extends NotificationMessage
{

    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String serviceName;
    protected ServiceStatus status;
    protected ServiceProcessingStatus processingStatus;
    protected ServiceAdvisory advisory;

    /**
     * Obtient la valeur de la propriété serviceName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Définit la valeur de la propriété serviceName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setStatus(ServiceStatus value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propriété processingStatus.
     * 
     * @return
     *     possible object is
     *     {@link ServiceProcessingStatus }
     *     
     */
    public ServiceProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    /**
     * Définit la valeur de la propriété processingStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceProcessingStatus }
     *     
     */
    public void setProcessingStatus(ServiceProcessingStatus value) {
        this.processingStatus = value;
    }

    /**
     * Obtient la valeur de la propriété advisory.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAdvisory }
     *     
     */
    public ServiceAdvisory getAdvisory() {
        return advisory;
    }

    /**
     * Définit la valeur de la propriété advisory.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAdvisory }
     *     
     */
    public void setAdvisory(ServiceAdvisory value) {
        this.advisory = value;
    }

}
