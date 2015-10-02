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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Parties can perform multiple roles in a trade lifecycle. For example, the principal parties obligated to make payments from time to time during the term of the trade, but may include other parties involved in, or incidental to, the trade, such as parties acting in the role of novation transferor/transferee, broker, calculation agent, etc. In FpML roles are defined in multiple places within a document.
 * 
 * <p>Classe Java pour Party complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Party">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyId" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyId" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyName" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyName" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartyInformation.model" minOccurs="0"/>
 *         &lt;element name="contactInfo" type="{http://www.fpml.org/FpML-5/recordkeeping}ContactInformation" minOccurs="0"/>
 *         &lt;element name="businessUnit" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessUnit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="person" type="{http://www.fpml.org/FpML-5/recordkeeping}Person" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party", propOrder = {
    "partyId",
    "partyName",
    "classification",
    "creditRating",
    "country",
    "jurisdiction",
    "organizationType",
    "contactInfo",
    "businessUnit",
    "person"
})
public class Party {

    protected List<PartyId> partyId;
    protected PartyName partyName;
    protected List<IndustryClassification> classification;
    protected List<CreditRating> creditRating;
    protected CountryCode country;
    protected List<GoverningLaw> jurisdiction;
    protected OrganizationType organizationType;
    protected ContactInformation contactInfo;
    protected List<BusinessUnit> businessUnit;
    protected List<Person> person;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the partyId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyId }
     * 
     * 
     */
    public List<PartyId> getPartyId() {
        if (partyId == null) {
            partyId = new ArrayList<PartyId>();
        }
        return this.partyId;
    }

    /**
     * Obtient la valeur de la propriété partyName.
     * 
     * @return
     *     possible object is
     *     {@link PartyName }
     *     
     */
    public PartyName getPartyName() {
        return partyName;
    }

    /**
     * Définit la valeur de la propriété partyName.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyName }
     *     
     */
    public void setPartyName(PartyName value) {
        this.partyName = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndustryClassification }
     * 
     * 
     */
    public List<IndustryClassification> getClassification() {
        if (classification == null) {
            classification = new ArrayList<IndustryClassification>();
        }
        return this.classification;
    }

    /**
     * Gets the value of the creditRating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditRating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditRating }
     * 
     * 
     */
    public List<CreditRating> getCreditRating() {
        if (creditRating == null) {
            creditRating = new ArrayList<CreditRating>();
        }
        return this.creditRating;
    }

    /**
     * Obtient la valeur de la propriété country.
     * 
     * @return
     *     possible object is
     *     {@link CountryCode }
     *     
     */
    public CountryCode getCountry() {
        return country;
    }

    /**
     * Définit la valeur de la propriété country.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCode }
     *     
     */
    public void setCountry(CountryCode value) {
        this.country = value;
    }

    /**
     * Gets the value of the jurisdiction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jurisdiction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJurisdiction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoverningLaw }
     * 
     * 
     */
    public List<GoverningLaw> getJurisdiction() {
        if (jurisdiction == null) {
            jurisdiction = new ArrayList<GoverningLaw>();
        }
        return this.jurisdiction;
    }

    /**
     * Obtient la valeur de la propriété organizationType.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationType }
     *     
     */
    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    /**
     * Définit la valeur de la propriété organizationType.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationType }
     *     
     */
    public void setOrganizationType(OrganizationType value) {
        this.organizationType = value;
    }

    /**
     * Obtient la valeur de la propriété contactInfo.
     * 
     * @return
     *     possible object is
     *     {@link ContactInformation }
     *     
     */
    public ContactInformation getContactInfo() {
        return contactInfo;
    }

    /**
     * Définit la valeur de la propriété contactInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInformation }
     *     
     */
    public void setContactInfo(ContactInformation value) {
        this.contactInfo = value;
    }

    /**
     * Gets the value of the businessUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessUnit }
     * 
     * 
     */
    public List<BusinessUnit> getBusinessUnit() {
        if (businessUnit == null) {
            businessUnit = new ArrayList<BusinessUnit>();
        }
        return this.businessUnit;
    }

    /**
     * Gets the value of the person property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the person property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Person }
     * 
     * 
     */
    public List<Person> getPerson() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return this.person;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
