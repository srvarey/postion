//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Abstract base class of all time bounded dividend period types.
 * 
 * <p>Classe Java pour DividendPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unadjustedStartDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *         &lt;element name="unadjustedEndDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *         &lt;element name="dateAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *         &lt;element name="underlyerReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetReference" minOccurs="0"/>
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
@XmlType(name = "DividendPeriod", propOrder = {
    "unadjustedStartDate",
    "unadjustedEndDate",
    "dateAdjustments",
    "underlyerReference"
})
@XmlSeeAlso({
    DividendPeriodPayment.class,
    DividendPeriodDividend.class
})
public abstract class DividendPeriod {

    protected IdentifiedDate unadjustedStartDate;
    protected IdentifiedDate unadjustedEndDate;
    protected BusinessDayAdjustments dateAdjustments;
    protected AssetReference underlyerReference;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété unadjustedStartDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getUnadjustedStartDate() {
        return unadjustedStartDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setUnadjustedStartDate(IdentifiedDate value) {
        this.unadjustedStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété unadjustedEndDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getUnadjustedEndDate() {
        return unadjustedEndDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setUnadjustedEndDate(IdentifiedDate value) {
        this.unadjustedEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété dateAdjustments.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public BusinessDayAdjustments getDateAdjustments() {
        return dateAdjustments;
    }

    /**
     * Définit la valeur de la propriété dateAdjustments.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustments }
     *     
     */
    public void setDateAdjustments(BusinessDayAdjustments value) {
        this.dateAdjustments = value;
    }

    /**
     * Obtient la valeur de la propriété underlyerReference.
     * 
     * @return
     *     possible object is
     *     {@link AssetReference }
     *     
     */
    public AssetReference getUnderlyerReference() {
        return underlyerReference;
    }

    /**
     * Définit la valeur de la propriété underlyerReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetReference }
     *     
     */
    public void setUnderlyerReference(AssetReference value) {
        this.underlyerReference = value;
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
