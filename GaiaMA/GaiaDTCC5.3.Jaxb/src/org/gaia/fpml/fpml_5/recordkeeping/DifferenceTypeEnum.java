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
 * <p>Classe Java pour DifferenceTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DifferenceTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Value"/>
 *     &lt;enumeration value="Reference"/>
 *     &lt;enumeration value="Structure"/>
 *     &lt;enumeration value="Scheme"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DifferenceTypeEnum")
@XmlEnum
public enum DifferenceTypeEnum {

    @XmlEnumValue("Value")
    VALUE("Value"),
    @XmlEnumValue("Reference")
    REFERENCE("Reference"),
    @XmlEnumValue("Structure")
    STRUCTURE("Structure"),
    @XmlEnumValue("Scheme")
    SCHEME("Scheme");
    private final String value;

    DifferenceTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DifferenceTypeEnum fromValue(String v) {
        for (DifferenceTypeEnum c: DifferenceTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
