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
 * <p>Classe Java pour TouchConditionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TouchConditionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Touch"/>
 *     &lt;enumeration value="Notouch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TouchConditionEnum")
@XmlEnum
public enum TouchConditionEnum {


    /**
     * The spot rate must have touched the predetermined trigger rate at any time over the life of the option for the payout to occur.
     * 
     */
    @XmlEnumValue("Touch")
    TOUCH("Touch"),

    /**
     * The spot rate has not touched the predetermined trigger rate at any time over the life of the option for the payout to occur.
     * 
     */
    @XmlEnumValue("Notouch")
    NOTOUCH("Notouch");
    private final String value;

    TouchConditionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TouchConditionEnum fromValue(String v) {
        for (TouchConditionEnum c: TouchConditionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
