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
 * <p>Classe Java pour InterpolationPeriodEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="InterpolationPeriodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Initial"/>
 *     &lt;enumeration value="InitialAndFinal"/>
 *     &lt;enumeration value="Final"/>
 *     &lt;enumeration value="AnyPeriod"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InterpolationPeriodEnum")
@XmlEnum
public enum InterpolationPeriodEnum {


    /**
     * Interpolation is applicable to the initial period only.
     * 
     */
    @XmlEnumValue("Initial")
    INITIAL("Initial"),

    /**
     * Interpolation is applicable to the initial and final periods only.
     * 
     */
    @XmlEnumValue("InitialAndFinal")
    INITIAL_AND_FINAL("InitialAndFinal"),

    /**
     * Interpolation is applicable to the final period only.
     * 
     */
    @XmlEnumValue("Final")
    FINAL("Final"),

    /**
     * Interpolation is applicable to any non-standard period.
     * 
     */
    @XmlEnumValue("AnyPeriod")
    ANY_PERIOD("AnyPeriod");
    private final String value;

    InterpolationPeriodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterpolationPeriodEnum fromValue(String v) {
        for (InterpolationPeriodEnum c: InterpolationPeriodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
