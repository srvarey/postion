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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type for defining a time with respect to a business day calendar location. For example, 11:00am London time.
 * 
 * <p>Classe Java pour BusinessCenterTime complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BusinessCenterTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hourMinuteTime" type="{http://www.fpml.org/FpML-5/recordkeeping}HourMinuteTime" minOccurs="0"/>
 *         &lt;element name="businessCenter" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessCenterTime", propOrder = {
    "hourMinuteTime",
    "businessCenter"
})
public class BusinessCenterTime {

    protected XMLGregorianCalendar hourMinuteTime;
    protected BusinessCenter businessCenter;

    /**
     * Obtient la valeur de la propriété hourMinuteTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHourMinuteTime() {
        return hourMinuteTime;
    }

    /**
     * Définit la valeur de la propriété hourMinuteTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHourMinuteTime(XMLGregorianCalendar value) {
        this.hourMinuteTime = value;
    }

    /**
     * Obtient la valeur de la propriété businessCenter.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenter }
     *     
     */
    public BusinessCenter getBusinessCenter() {
        return businessCenter;
    }

    /**
     * Définit la valeur de la propriété businessCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenter }
     *     
     */
    public void setBusinessCenter(BusinessCenter value) {
        this.businessCenter = value;
    }

}
