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
 * <p>Classe Java pour AveragingInOutEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AveragingInOutEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="In"/>
 *     &lt;enumeration value="Out"/>
 *     &lt;enumeration value="Both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AveragingInOutEnum")
@XmlEnum
public enum AveragingInOutEnum {


    /**
     * The average price is used to derive the strike price. Also known as "Asian strike" style option.
     * 
     */
    @XmlEnumValue("In")
    IN("In"),

    /**
     * The average price is used to derive the expiration price. Also known as "Asian price" style option.
     * 
     */
    @XmlEnumValue("Out")
    OUT("Out"),

    /**
     * The average price is used to derive both the strike and the expiration price.
     * 
     */
    @XmlEnumValue("Both")
    BOTH("Both");
    private final String value;

    AveragingInOutEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AveragingInOutEnum fromValue(String v) {
        for (AveragingInOutEnum c: AveragingInOutEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
