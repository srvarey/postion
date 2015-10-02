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
 * <p>Classe Java pour ExerciseStyleEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ExerciseStyleEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="American"/>
 *     &lt;enumeration value="Bermuda"/>
 *     &lt;enumeration value="European"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ExerciseStyleEnum")
@XmlEnum
public enum ExerciseStyleEnum {


    /**
     * Option can be exercised on any date up to the expiry date.
     * 
     */
    @XmlEnumValue("American")
    AMERICAN("American"),

    /**
     * Option can be exercised on specified dates up to the expiry date.
     * 
     */
    @XmlEnumValue("Bermuda")
    BERMUDA("Bermuda"),

    /**
     * Option can only be exercised on the expiry date.
     * 
     */
    @XmlEnumValue("European")
    EUROPEAN("European");
    private final String value;

    ExerciseStyleEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExerciseStyleEnum fromValue(String v) {
        for (ExerciseStyleEnum c: ExerciseStyleEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
