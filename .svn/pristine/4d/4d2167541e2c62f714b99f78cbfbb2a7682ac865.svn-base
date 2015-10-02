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
 * <p>Classe Java pour InterestShortfallCapEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="InterestShortfallCapEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Fixed"/>
 *     &lt;enumeration value="Variable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InterestShortfallCapEnum")
@XmlEnum
public enum InterestShortfallCapEnum {

    @XmlEnumValue("Fixed")
    FIXED("Fixed"),
    @XmlEnumValue("Variable")
    VARIABLE("Variable");
    private final String value;

    InterestShortfallCapEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterestShortfallCapEnum fromValue(String v) {
        for (InterestShortfallCapEnum c: InterestShortfallCapEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
