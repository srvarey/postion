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
 * An servicing date relevant for a trade structure, such as a payment or a reset.
 * 
 * <p>Classe Java pour ScheduledDate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ScheduledDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AdjustedAndOrUnadjustedDate.model"/>
 *         &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}ScheduledDateType" minOccurs="0"/>
 *         &lt;element name="assetReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AnyAssetReference" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AssociatedValue.model" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduledDate", propOrder = {
    "unadjustedDate",
    "adjustedDate",
    "type",
    "assetReference",
    "associatedValueModel"
})
public class ScheduledDate {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar unadjustedDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adjustedDate;
    protected ScheduledDateType type;
    protected AnyAssetReference assetReference;
    @XmlElements({
        @XmlElement(name = "associatedValue", type = AssetValuation.class),
        @XmlElement(name = "associatedValueReference", type = ValuationReference.class)
    })
    protected List<Object> associatedValueModel;

    /**
     * Obtient la valeur de la propriété unadjustedDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUnadjustedDate() {
        return unadjustedDate;
    }

    /**
     * Définit la valeur de la propriété unadjustedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUnadjustedDate(XMLGregorianCalendar value) {
        this.unadjustedDate = value;
    }

    /**
     * Obtient la valeur de la propriété adjustedDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdjustedDate() {
        return adjustedDate;
    }

    /**
     * Définit la valeur de la propriété adjustedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdjustedDate(XMLGregorianCalendar value) {
        this.adjustedDate = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link ScheduledDateType }
     *     
     */
    public ScheduledDateType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduledDateType }
     *     
     */
    public void setType(ScheduledDateType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété assetReference.
     * 
     * @return
     *     possible object is
     *     {@link AnyAssetReference }
     *     
     */
    public AnyAssetReference getAssetReference() {
        return assetReference;
    }

    /**
     * Définit la valeur de la propriété assetReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyAssetReference }
     *     
     */
    public void setAssetReference(AnyAssetReference value) {
        this.assetReference = value;
    }

    /**
     * Gets the value of the associatedValueModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedValueModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedValueModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetValuation }
     * {@link ValuationReference }
     * 
     * 
     */
    public List<Object> getAssociatedValueModel() {
        if (associatedValueModel == null) {
            associatedValueModel = new ArrayList<Object>();
        }
        return this.associatedValueModel;
    }

}
