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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour AdjustableRelativeOrPeriodicDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdjustableRelativeOrPeriodicDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="adjustableDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDates" minOccurs="0"/>
 *         &lt;element name="relativeDateSequence" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateSequence" minOccurs="0"/>
 *         &lt;element name="periodicDates" type="{http://www.fpml.org/FpML-5/recordkeeping}PeriodicDates" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdjustableRelativeOrPeriodicDates", propOrder = {
    "adjustableDates",
    "relativeDateSequence",
    "periodicDates"
})
public class AdjustableRelativeOrPeriodicDates {

    protected AdjustableDates adjustableDates;
    protected RelativeDateSequence relativeDateSequence;
    protected PeriodicDates periodicDates;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété adjustableDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableDates }
     *     
     */
    public AdjustableDates getAdjustableDates() {
        return adjustableDates;
    }

    /**
     * Définit la valeur de la propriété adjustableDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableDates }
     *     
     */
    public void setAdjustableDates(AdjustableDates value) {
        this.adjustableDates = value;
    }

    /**
     * Obtient la valeur de la propriété relativeDateSequence.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateSequence }
     *     
     */
    public RelativeDateSequence getRelativeDateSequence() {
        return relativeDateSequence;
    }

    /**
     * Définit la valeur de la propriété relativeDateSequence.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateSequence }
     *     
     */
    public void setRelativeDateSequence(RelativeDateSequence value) {
        this.relativeDateSequence = value;
    }

    /**
     * Obtient la valeur de la propriété periodicDates.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicDates }
     *     
     */
    public PeriodicDates getPeriodicDates() {
        return periodicDates;
    }

    /**
     * Définit la valeur de la propriété periodicDates.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicDates }
     *     
     */
    public void setPeriodicDates(PeriodicDates value) {
        this.periodicDates = value;
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
