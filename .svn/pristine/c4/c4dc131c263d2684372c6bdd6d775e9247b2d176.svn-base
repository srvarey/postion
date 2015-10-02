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
 * <p>Classe Java pour PositionStatusEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PositionStatusEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="Existing"/>
 *     &lt;enumeration value="Closed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PositionStatusEnum")
@XmlEnum
public enum PositionStatusEnum {


    /**
     * The position is open and has been newly added since the last position report.
     * 
     */
    @XmlEnumValue("New")
    NEW("New"),

    /**
     * The position is open and was present in the last position report.
     * 
     */
    @XmlEnumValue("Existing")
    EXISTING("Existing"),

    /**
     * The position is no longer open, for example because it has matured, was assigned, or was terminated.
     * 
     */
    @XmlEnumValue("Closed")
    CLOSED("Closed");
    private final String value;

    PositionStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PositionStatusEnum fromValue(String v) {
        for (PositionStatusEnum c: PositionStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
