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
 * Period over which an average value is taken.
 * 
 * <p>Classe Java pour AveragingPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AveragingPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schedule" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="averagingDateTimes" type="{http://www.fpml.org/FpML-5/recordkeeping}DateTimeList" minOccurs="0"/>
 *           &lt;element name="averagingObservations" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingObservationList" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="marketDisruption" type="{http://www.fpml.org/FpML-5/recordkeeping}MarketDisruption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AveragingPeriod", propOrder = {
    "schedule",
    "averagingDateTimes",
    "averagingObservations",
    "marketDisruption"
})
public class AveragingPeriod {

    protected List<AveragingSchedule> schedule;
    protected DateTimeList averagingDateTimes;
    protected AveragingObservationList averagingObservations;
    protected MarketDisruption marketDisruption;

    /**
     * Gets the value of the schedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AveragingSchedule }
     * 
     * 
     */
    public List<AveragingSchedule> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<AveragingSchedule>();
        }
        return this.schedule;
    }

    /**
     * Obtient la valeur de la propriété averagingDateTimes.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeList }
     *     
     */
    public DateTimeList getAveragingDateTimes() {
        return averagingDateTimes;
    }

    /**
     * Définit la valeur de la propriété averagingDateTimes.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeList }
     *     
     */
    public void setAveragingDateTimes(DateTimeList value) {
        this.averagingDateTimes = value;
    }

    /**
     * Obtient la valeur de la propriété averagingObservations.
     * 
     * @return
     *     possible object is
     *     {@link AveragingObservationList }
     *     
     */
    public AveragingObservationList getAveragingObservations() {
        return averagingObservations;
    }

    /**
     * Définit la valeur de la propriété averagingObservations.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingObservationList }
     *     
     */
    public void setAveragingObservations(AveragingObservationList value) {
        this.averagingObservations = value;
    }

    /**
     * Obtient la valeur de la propriété marketDisruption.
     * 
     * @return
     *     possible object is
     *     {@link MarketDisruption }
     *     
     */
    public MarketDisruption getMarketDisruption() {
        return marketDisruption;
    }

    /**
     * Définit la valeur de la propriété marketDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketDisruption }
     *     
     */
    public void setMarketDisruption(MarketDisruption value) {
        this.marketDisruption = value;
    }

}
