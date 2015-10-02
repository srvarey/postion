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
 * A type for definining equity option simple strike or calendar spread strategy features.
 * 
 * <p>Classe Java pour StrategyFeature complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StrategyFeature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="strikeSpread" type="{http://www.fpml.org/FpML-5/recordkeeping}StrikeSpread" minOccurs="0"/>
 *         &lt;element name="calendarSpread" type="{http://www.fpml.org/FpML-5/recordkeeping}CalendarSpread" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrategyFeature", propOrder = {
    "strikeSpread",
    "calendarSpread"
})
public class StrategyFeature {

    protected StrikeSpread strikeSpread;
    protected CalendarSpread calendarSpread;

    /**
     * Obtient la valeur de la propriété strikeSpread.
     * 
     * @return
     *     possible object is
     *     {@link StrikeSpread }
     *     
     */
    public StrikeSpread getStrikeSpread() {
        return strikeSpread;
    }

    /**
     * Définit la valeur de la propriété strikeSpread.
     * 
     * @param value
     *     allowed object is
     *     {@link StrikeSpread }
     *     
     */
    public void setStrikeSpread(StrikeSpread value) {
        this.strikeSpread = value;
    }

    /**
     * Obtient la valeur de la propriété calendarSpread.
     * 
     * @return
     *     possible object is
     *     {@link CalendarSpread }
     *     
     */
    public CalendarSpread getCalendarSpread() {
        return calendarSpread;
    }

    /**
     * Définit la valeur de la propriété calendarSpread.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarSpread }
     *     
     */
    public void setCalendarSpread(CalendarSpread value) {
        this.calendarSpread = value;
    }

}
