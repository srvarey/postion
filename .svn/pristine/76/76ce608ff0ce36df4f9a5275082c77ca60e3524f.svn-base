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
 * <p>Classe Java pour TriggerTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TriggerTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="EqualOrLess"/>
 *     &lt;enumeration value="EqualOrGreater"/>
 *     &lt;enumeration value="Equal"/>
 *     &lt;enumeration value="Less"/>
 *     &lt;enumeration value="Greater"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TriggerTypeEnum")
@XmlEnum
public enum TriggerTypeEnum {


    /**
     * The underlyer price must be equal to or less than the Trigger level.
     * 
     */
    @XmlEnumValue("EqualOrLess")
    EQUAL_OR_LESS("EqualOrLess"),

    /**
     * The underlyer price must be equal to or greater than the Trigger level.
     * 
     */
    @XmlEnumValue("EqualOrGreater")
    EQUAL_OR_GREATER("EqualOrGreater"),

    /**
     * The underlyer price must be equal to the Trigger level.
     * 
     */
    @XmlEnumValue("Equal")
    EQUAL("Equal"),

    /**
     * The underlyer price must be less than the Trigger level.
     * 
     */
    @XmlEnumValue("Less")
    LESS("Less"),

    /**
     * The underlyer price must be greater than the Trigger level.
     * 
     */
    @XmlEnumValue("Greater")
    GREATER("Greater");
    private final String value;

    TriggerTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TriggerTypeEnum fromValue(String v) {
        for (TriggerTypeEnum c: TriggerTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
