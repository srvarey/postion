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
 * <p>Classe Java pour StandardSettlementStyleEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StandardSettlementStyleEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Standard"/>
 *     &lt;enumeration value="Net"/>
 *     &lt;enumeration value="StandardAndNet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StandardSettlementStyleEnum")
@XmlEnum
public enum StandardSettlementStyleEnum {


    /**
     * This trade will settle using standard pre-determined funds settlement instructions.
     * 
     */
    @XmlEnumValue("Standard")
    STANDARD("Standard"),

    /**
     * This trade is a candidate for settlement netting.
     * 
     */
    @XmlEnumValue("Net")
    NET("Net"),

    /**
     * This trade will settle using standard pre-determined funds settlement instructions and is a candidate for settlement netting.
     * 
     */
    @XmlEnumValue("StandardAndNet")
    STANDARD_AND_NET("StandardAndNet");
    private final String value;

    StandardSettlementStyleEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StandardSettlementStyleEnum fromValue(String v) {
        for (StandardSettlementStyleEnum c: StandardSettlementStyleEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
