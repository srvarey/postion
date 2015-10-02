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
 * An entity for defining the agreement executed between the parties and intended to govern all OTC derivatives transactions between those parties.
 * 
 * <p>Classe Java pour MasterAgreement complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MasterAgreement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="masterAgreementType" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterAgreementType" minOccurs="0"/>
 *         &lt;element name="masterAgreementVersion" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterAgreementVersion" minOccurs="0"/>
 *         &lt;element name="masterAgreementDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterAgreement", propOrder = {
    "masterAgreementType",
    "masterAgreementVersion",
    "masterAgreementDate"
})
public class MasterAgreement {

    protected MasterAgreementType masterAgreementType;
    protected MasterAgreementVersion masterAgreementVersion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar masterAgreementDate;

    /**
     * Obtient la valeur de la propriété masterAgreementType.
     * 
     * @return
     *     possible object is
     *     {@link MasterAgreementType }
     *     
     */
    public MasterAgreementType getMasterAgreementType() {
        return masterAgreementType;
    }

    /**
     * Définit la valeur de la propriété masterAgreementType.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterAgreementType }
     *     
     */
    public void setMasterAgreementType(MasterAgreementType value) {
        this.masterAgreementType = value;
    }

    /**
     * Obtient la valeur de la propriété masterAgreementVersion.
     * 
     * @return
     *     possible object is
     *     {@link MasterAgreementVersion }
     *     
     */
    public MasterAgreementVersion getMasterAgreementVersion() {
        return masterAgreementVersion;
    }

    /**
     * Définit la valeur de la propriété masterAgreementVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterAgreementVersion }
     *     
     */
    public void setMasterAgreementVersion(MasterAgreementVersion value) {
        this.masterAgreementVersion = value;
    }

    /**
     * Obtient la valeur de la propriété masterAgreementDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMasterAgreementDate() {
        return masterAgreementDate;
    }

    /**
     * Définit la valeur de la propriété masterAgreementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMasterAgreementDate(XMLGregorianCalendar value) {
        this.masterAgreementDate = value;
    }

}
