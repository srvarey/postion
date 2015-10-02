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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type describing a loan underlying asset.
 * 
 * <p>Classe Java pour Loan complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Loan">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}UnderlyingAsset">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="borrower" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntity" minOccurs="0"/>
 *           &lt;element name="borrowerReference" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntityReference" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="lien" type="{http://www.fpml.org/FpML-5/recordkeeping}Lien" minOccurs="0"/>
 *         &lt;element name="facilityType" type="{http://www.fpml.org/FpML-5/recordkeeping}FacilityType" minOccurs="0"/>
 *         &lt;element name="maturity" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="creditAgreementDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="tranche" type="{http://www.fpml.org/FpML-5/recordkeeping}UnderlyingAssetTranche" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Loan", propOrder = {
    "borrowerOrBorrowerReference",
    "lien",
    "facilityType",
    "maturity",
    "creditAgreementDate",
    "tranche"
})
public class Loan
    extends UnderlyingAsset
{

    @XmlElements({
        @XmlElement(name = "borrower", type = LegalEntity.class),
        @XmlElement(name = "borrowerReference", type = LegalEntityReference.class)
    })
    protected List<Object> borrowerOrBorrowerReference;
    protected Lien lien;
    protected FacilityType facilityType;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar maturity;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creditAgreementDate;
    protected UnderlyingAssetTranche tranche;

    /**
     * Gets the value of the borrowerOrBorrowerReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerOrBorrowerReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerOrBorrowerReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegalEntity }
     * {@link LegalEntityReference }
     * 
     * 
     */
    public List<Object> getBorrowerOrBorrowerReference() {
        if (borrowerOrBorrowerReference == null) {
            borrowerOrBorrowerReference = new ArrayList<Object>();
        }
        return this.borrowerOrBorrowerReference;
    }

    /**
     * Obtient la valeur de la propriété lien.
     * 
     * @return
     *     possible object is
     *     {@link Lien }
     *     
     */
    public Lien getLien() {
        return lien;
    }

    /**
     * Définit la valeur de la propriété lien.
     * 
     * @param value
     *     allowed object is
     *     {@link Lien }
     *     
     */
    public void setLien(Lien value) {
        this.lien = value;
    }

    /**
     * Obtient la valeur de la propriété facilityType.
     * 
     * @return
     *     possible object is
     *     {@link FacilityType }
     *     
     */
    public FacilityType getFacilityType() {
        return facilityType;
    }

    /**
     * Définit la valeur de la propriété facilityType.
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityType }
     *     
     */
    public void setFacilityType(FacilityType value) {
        this.facilityType = value;
    }

    /**
     * Obtient la valeur de la propriété maturity.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaturity() {
        return maturity;
    }

    /**
     * Définit la valeur de la propriété maturity.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaturity(XMLGregorianCalendar value) {
        this.maturity = value;
    }

    /**
     * Obtient la valeur de la propriété creditAgreementDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreditAgreementDate() {
        return creditAgreementDate;
    }

    /**
     * Définit la valeur de la propriété creditAgreementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreditAgreementDate(XMLGregorianCalendar value) {
        this.creditAgreementDate = value;
    }

    /**
     * Obtient la valeur de la propriété tranche.
     * 
     * @return
     *     possible object is
     *     {@link UnderlyingAssetTranche }
     *     
     */
    public UnderlyingAssetTranche getTranche() {
        return tranche;
    }

    /**
     * Définit la valeur de la propriété tranche.
     * 
     * @param value
     *     allowed object is
     *     {@link UnderlyingAssetTranche }
     *     
     */
    public void setTranche(UnderlyingAssetTranche value) {
        this.tranche = value;
    }

}
