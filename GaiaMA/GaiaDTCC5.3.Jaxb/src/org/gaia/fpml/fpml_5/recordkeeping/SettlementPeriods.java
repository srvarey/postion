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
 * Specifies a set of Settlement Periods associated with an Electricity Transaction for delivery on an Applicable Day or for a series of Applicable Days.
 * 
 * <p>Classe Java pour SettlementPeriods complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SettlementPeriods">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duration" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementPeriodDurationEnum" minOccurs="0"/>
 *         &lt;element name="applicableDay" type="{http://www.fpml.org/FpML-5/recordkeeping}DayOfWeekEnum" maxOccurs="7" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.fpml.org/FpML-5/recordkeeping}OffsetPrevailingTime" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.fpml.org/FpML-5/recordkeeping}OffsetPrevailingTime" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="excludeHolidays" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityBusinessCalendar" minOccurs="0"/>
 *           &lt;element name="includeHolidays" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityBusinessCalendar" minOccurs="0"/>
 *         &lt;/choice>
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
@XmlType(name = "SettlementPeriods", propOrder = {
    "duration",
    "applicableDay",
    "startTime",
    "endTime",
    "excludeHolidays",
    "includeHolidays"
})
public class SettlementPeriods {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String duration;
    protected List<DayOfWeekEnum> applicableDay;
    protected OffsetPrevailingTime startTime;
    protected OffsetPrevailingTime endTime;
    protected CommodityBusinessCalendar excludeHolidays;
    protected CommodityBusinessCalendar includeHolidays;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété duration.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Définit la valeur de la propriété duration.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the applicableDay property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicableDay property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicableDay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DayOfWeekEnum }
     * 
     * 
     */
    public List<DayOfWeekEnum> getApplicableDay() {
        if (applicableDay == null) {
            applicableDay = new ArrayList<DayOfWeekEnum>();
        }
        return this.applicableDay;
    }

    /**
     * Obtient la valeur de la propriété startTime.
     * 
     * @return
     *     possible object is
     *     {@link OffsetPrevailingTime }
     *     
     */
    public OffsetPrevailingTime getStartTime() {
        return startTime;
    }

    /**
     * Définit la valeur de la propriété startTime.
     * 
     * @param value
     *     allowed object is
     *     {@link OffsetPrevailingTime }
     *     
     */
    public void setStartTime(OffsetPrevailingTime value) {
        this.startTime = value;
    }

    /**
     * Obtient la valeur de la propriété endTime.
     * 
     * @return
     *     possible object is
     *     {@link OffsetPrevailingTime }
     *     
     */
    public OffsetPrevailingTime getEndTime() {
        return endTime;
    }

    /**
     * Définit la valeur de la propriété endTime.
     * 
     * @param value
     *     allowed object is
     *     {@link OffsetPrevailingTime }
     *     
     */
    public void setEndTime(OffsetPrevailingTime value) {
        this.endTime = value;
    }

    /**
     * Obtient la valeur de la propriété excludeHolidays.
     * 
     * @return
     *     possible object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public CommodityBusinessCalendar getExcludeHolidays() {
        return excludeHolidays;
    }

    /**
     * Définit la valeur de la propriété excludeHolidays.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public void setExcludeHolidays(CommodityBusinessCalendar value) {
        this.excludeHolidays = value;
    }

    /**
     * Obtient la valeur de la propriété includeHolidays.
     * 
     * @return
     *     possible object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public CommodityBusinessCalendar getIncludeHolidays() {
        return includeHolidays;
    }

    /**
     * Définit la valeur de la propriété includeHolidays.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityBusinessCalendar }
     *     
     */
    public void setIncludeHolidays(CommodityBusinessCalendar value) {
        this.includeHolidays = value;
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
