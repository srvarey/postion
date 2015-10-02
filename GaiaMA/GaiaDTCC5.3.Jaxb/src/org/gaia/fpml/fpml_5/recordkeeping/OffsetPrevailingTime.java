//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Allows the specification of a time that may be on a day prior or subsequent to the day in question. This type is intended for use with a day of the week (i.e. where no actual date is specified) as part of, for example, a period that runs from 23:00-07:00 on a series of days and where holidays on the actual days would affect the entire time period.
 * 
 * <p>Classe Java pour OffsetPrevailingTime complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OffsetPrevailingTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="time" type="{http://www.fpml.org/FpML-5/recordkeeping}PrevailingTime" minOccurs="0"/>
 *         &lt;element name="offset" type="{http://www.fpml.org/FpML-5/recordkeeping}Offset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OffsetPrevailingTime", propOrder = {
    "time",
    "offset"
})
public class OffsetPrevailingTime {

    protected PrevailingTime time;
    protected Offset offset;

    /**
     * Obtient la valeur de la propriété time.
     * 
     * @return
     *     possible object is
     *     {@link PrevailingTime }
     *     
     */
    public PrevailingTime getTime() {
        return time;
    }

    /**
     * Définit la valeur de la propriété time.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevailingTime }
     *     
     */
    public void setTime(PrevailingTime value) {
        this.time = value;
    }

    /**
     * Obtient la valeur de la propriété offset.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getOffset() {
        return offset;
    }

    /**
     * Définit la valeur de la propriété offset.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setOffset(Offset value) {
        this.offset = value;
    }

}
