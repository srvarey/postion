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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type that is different from AdjustableDate in two regards. First, date adjustments can be specified with either a dateAdjustments element or a reference to an existing dateAdjustments element. Second, it does not require the specification of date adjustments.
 * 
 * <p>Classe Java pour AdjustableDate2 complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdjustableDate2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unadjustedDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="dateAdjustments" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustments" minOccurs="0"/>
 *           &lt;element name="dateAdjustmentsReference" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayAdjustmentsReference" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="adjustedDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
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
@XmlType(name = "AdjustableDate2", propOrder = {
    "unadjustedDate",
    "dateAdjustments",
    "dateAdjustmentsReference",
    "adjustedDate"
})
public class AdjustableDate2 {

    @XmlElement(required = true)
    protected IdentifiedDate unadjustedDate;
    protected BusinessDayAdjustments dateAdjustments;
    protected BusinessDayAdjustmentsReference dateAdjustmentsReference;
    protected IdentifiedDate adjustedDate;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété unadjustedDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getUnadjustedDate() {
        return unadjustedDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setUnadjustedDate(IdentifiedDate value) {
        this.unadjustedDate = value;
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
     * Obtient la valeur de la propriété dateAdjustmentsReference.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayAdjustmentsReference }
     *     
     */
    public BusinessDayAdjustmentsReference getDateAdjustmentsReference() {
        return dateAdjustmentsReference;
    }

    /**
     * Définit la valeur de la propriété dateAdjustmentsReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayAdjustmentsReference }
     *     
     */
    public void setDateAdjustmentsReference(BusinessDayAdjustmentsReference value) {
        this.dateAdjustmentsReference = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getAdjustedDate() {
        return adjustedDate;
    }

    /**
     * Définit la valeur de la propriété adjustedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setAdjustedDate(IdentifiedDate value) {
        this.adjustedDate = value;
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
