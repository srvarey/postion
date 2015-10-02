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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type that represents information about a person connected with a trade or business process.
 * 
 * <p>Classe Java pour Person complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="honorific" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *           &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *           &lt;choice minOccurs="0">
 *             &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="initial" type="{http://www.fpml.org/FpML-5/recordkeeping}Initial" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/choice>
 *           &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *           &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="personId" type="{http://www.fpml.org/FpML-5/recordkeeping}PersonId" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="businessUnitReference" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessUnitReference" minOccurs="0"/>
 *         &lt;element name="contactInfo" type="{http://www.fpml.org/FpML-5/recordkeeping}ContactInformation" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.fpml.org/FpML-5/recordkeeping}CountryCode" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "honorific",
    "firstName",
    "middleName",
    "initial",
    "surname",
    "suffix",
    "personId",
    "businessUnitReference",
    "contactInfo",
    "country"
})
public class Person {

    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String honorific;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String firstName;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected List<String> middleName;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected List<String> initial;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String surname;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String suffix;
    protected List<PersonId> personId;
    protected BusinessUnitReference businessUnitReference;
    protected ContactInformation contactInfo;
    protected CountryCode country;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété honorific.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHonorific() {
        return honorific;
    }

    /**
     * Définit la valeur de la propriété honorific.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHonorific(String value) {
        this.honorific = value;
    }

    /**
     * Obtient la valeur de la propriété firstName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Définit la valeur de la propriété firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the middleName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiddleName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMiddleName() {
        if (middleName == null) {
            middleName = new ArrayList<String>();
        }
        return this.middleName;
    }

    /**
     * Gets the value of the initial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the initial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInitial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInitial() {
        if (initial == null) {
            initial = new ArrayList<String>();
        }
        return this.initial;
    }

    /**
     * Obtient la valeur de la propriété surname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Définit la valeur de la propriété surname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Obtient la valeur de la propriété suffix.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Définit la valeur de la propriété suffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the personId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonId }
     * 
     * 
     */
    public List<PersonId> getPersonId() {
        if (personId == null) {
            personId = new ArrayList<PersonId>();
        }
        return this.personId;
    }

    /**
     * Obtient la valeur de la propriété businessUnitReference.
     * 
     * @return
     *     possible object is
     *     {@link BusinessUnitReference }
     *     
     */
    public BusinessUnitReference getBusinessUnitReference() {
        return businessUnitReference;
    }

    /**
     * Définit la valeur de la propriété businessUnitReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessUnitReference }
     *     
     */
    public void setBusinessUnitReference(BusinessUnitReference value) {
        this.businessUnitReference = value;
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
