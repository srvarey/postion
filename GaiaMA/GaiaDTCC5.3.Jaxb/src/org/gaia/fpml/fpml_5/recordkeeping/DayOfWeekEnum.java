//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DayOfWeekEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DayOfWeekEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="MON"/>
 *     &lt;enumeration value="TUE"/>
 *     &lt;enumeration value="WED"/>
 *     &lt;enumeration value="THU"/>
 *     &lt;enumeration value="FRI"/>
 *     &lt;enumeration value="SAT"/>
 *     &lt;enumeration value="SUN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DayOfWeekEnum")
@XmlEnum
public enum DayOfWeekEnum {


    /**
     * Monday
     * 
     */
    MON,

    /**
     * Tuesday
     * 
     */
    TUE,

    /**
     * Wednesday
     * 
     */
    WED,

    /**
     * Thursday
     * 
     */
    THU,

    /**
     * Friday
     * 
     */
    FRI,

    /**
     * Saturday
     * 
     */
    SAT,

    /**
     * Sunday
     * 
     */
    SUN;

    public String value() {
        return name();
    }

    public static DayOfWeekEnum fromValue(String v) {
        return valueOf(v);
    }

}
