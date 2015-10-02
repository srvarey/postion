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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * An entity for defining the master confirmation agreement executed between the parties.
 * 
 * <p>Classe Java pour MasterConfirmation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MasterConfirmation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="masterConfirmationType" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterConfirmationType" minOccurs="0"/>
 *         &lt;element name="masterConfirmationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="masterConfirmationAnnexDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="masterConfirmationAnnexType" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterConfirmationAnnexType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterConfirmation", propOrder = {
    "masterConfirmationType",
    "masterConfirmationDate",
    "masterConfirmationAnnexDate",
    "masterConfirmationAnnexType"
})
public class MasterConfirmation {

    protected MasterConfirmationType masterConfirmationType;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar masterConfirmationDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar masterConfirmationAnnexDate;
    protected MasterConfirmationAnnexType masterConfirmationAnnexType;

    /**
     * Obtient la valeur de la propriété masterConfirmationType.
     * 
     * @return
     *     possible object is
     *     {@link MasterConfirmationType }
     *     
     */
    public MasterConfirmationType getMasterConfirmationType() {
        return masterConfirmationType;
    }

    /**
     * Définit la valeur de la propriété masterConfirmationType.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterConfirmationType }
     *     
     */
    public void setMasterConfirmationType(MasterConfirmationType value) {
        this.masterConfirmationType = value;
    }

    /**
     * Obtient la valeur de la propriété masterConfirmationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMasterConfirmationDate() {
        return masterConfirmationDate;
    }

    /**
     * Définit la valeur de la propriété masterConfirmationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMasterConfirmationDate(XMLGregorianCalendar value) {
        this.masterConfirmationDate = value;
    }

    /**
     * Obtient la valeur de la propriété masterConfirmationAnnexDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMasterConfirmationAnnexDate() {
        return masterConfirmationAnnexDate;
    }

    /**
     * Définit la valeur de la propriété masterConfirmationAnnexDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMasterConfirmationAnnexDate(XMLGregorianCalendar value) {
        this.masterConfirmationAnnexDate = value;
    }

    /**
     * Obtient la valeur de la propriété masterConfirmationAnnexType.
     * 
     * @return
     *     possible object is
     *     {@link MasterConfirmationAnnexType }
     *     
     */
    public MasterConfirmationAnnexType getMasterConfirmationAnnexType() {
        return masterConfirmationAnnexType;
    }

    /**
     * Définit la valeur de la propriété masterConfirmationAnnexType.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterConfirmationAnnexType }
     *     
     */
    public void setMasterConfirmationAnnexType(MasterConfirmationAnnexType value) {
        this.masterConfirmationAnnexType = value;
    }

}
