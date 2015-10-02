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
import javax.xml.bind.annotation.XmlType;


/**
 * A list of dates (cash flows, resets, etc.) that are relevant for this structure, e.g. next cash flow, last reset, etc. Provides a way to list upcoming or recent servicing dates related to this trade stream in a way that is simpler and more flexible than the FpML "cashflows" structure.
 * 
 * <p>Classe Java pour ScheduledDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ScheduledDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scheduledDate" type="{http://www.fpml.org/FpML-5/recordkeeping}ScheduledDate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduledDates", propOrder = {
    "scheduledDate"
})
public class ScheduledDates {

    protected List<ScheduledDate> scheduledDate;

    /**
     * Gets the value of the scheduledDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduledDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduledDate }
     * 
     * 
     */
    public List<ScheduledDate> getScheduledDate() {
        if (scheduledDate == null) {
            scheduledDate = new ArrayList<ScheduledDate>();
        }
        return this.scheduledDate;
    }

}
