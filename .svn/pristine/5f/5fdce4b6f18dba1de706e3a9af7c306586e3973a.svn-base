//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FrequencyTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FrequencyTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Day"/>
 *     &lt;enumeration value="Business"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FrequencyTypeEnum")
@XmlEnum
public enum FrequencyTypeEnum {


    /**
     * Day is the unit of frequency.
     * 
     */
    @XmlEnumValue("Day")
    DAY("Day"),

    /**
     * TBD
     * 
     */
    @XmlEnumValue("Business")
    BUSINESS("Business");
    private final String value;

    FrequencyTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FrequencyTypeEnum fromValue(String v) {
        for (FrequencyTypeEnum c: FrequencyTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
