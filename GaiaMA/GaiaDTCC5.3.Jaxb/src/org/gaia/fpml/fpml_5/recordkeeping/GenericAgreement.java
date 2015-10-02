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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * An entity for defining a generic agreement executed between two parties for any purpose.
 * 
 * <p>Classe Java pour GenericAgreement complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GenericAgreement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}AgreementType" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.fpml.org/FpML-5/recordkeeping}AgreementVersion" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="amendmentDate" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="governingLaw" type="{http://www.fpml.org/FpML-5/recordkeeping}GoverningLaw" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericAgreement", propOrder = {
    "type",
    "version",
    "date",
    "amendmentDate",
    "governingLaw"
})
public class GenericAgreement {

    protected AgreementType type;
    protected AgreementVersion version;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> amendmentDate;
    protected GoverningLaw governingLaw;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link AgreementType }
     *     
     */
    public AgreementType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link AgreementType }
     *     
     */
    public void setType(AgreementType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link AgreementVersion }
     *     
     */
    public AgreementVersion getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link AgreementVersion }
     *     
     */
    public void setVersion(AgreementVersion value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the amendmentDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amendmentDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmendmentDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getAmendmentDate() {
        if (amendmentDate == null) {
            amendmentDate = new ArrayList<XMLGregorianCalendar>();
        }
        return this.amendmentDate;
    }

    /**
     * Obtient la valeur de la propriété governingLaw.
     * 
     * @return
     *     possible object is
     *     {@link GoverningLaw }
     *     
     */
    public GoverningLaw getGoverningLaw() {
        return governingLaw;
    }

    /**
     * Définit la valeur de la propriété governingLaw.
     * 
     * @param value
     *     allowed object is
     *     {@link GoverningLaw }
     *     
     */
    public void setGoverningLaw(GoverningLaw value) {
        this.governingLaw = value;
    }

}
