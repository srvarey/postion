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
 * <p>Classe Java pour TriggerConditionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TriggerConditionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Above"/>
 *     &lt;enumeration value="Below"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TriggerConditionEnum")
@XmlEnum
public enum TriggerConditionEnum {


    /**
     * The spot rate must be greater than or equal to the trigger rate.
     * 
     */
    @XmlEnumValue("Above")
    ABOVE("Above"),

    /**
     * The spot rate must be less than or equal to the trigger rate.
     * 
     */
    @XmlEnumValue("Below")
    BELOW("Below");
    private final String value;

    TriggerConditionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TriggerConditionEnum fromValue(String v) {
        for (TriggerConditionEnum c: TriggerConditionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
