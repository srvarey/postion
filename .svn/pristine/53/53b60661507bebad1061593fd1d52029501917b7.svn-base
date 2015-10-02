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
 * <p>Classe Java pour PremiumTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PremiumTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PrePaid"/>
 *     &lt;enumeration value="PostPaid"/>
 *     &lt;enumeration value="Variable"/>
 *     &lt;enumeration value="Fixed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PremiumTypeEnum")
@XmlEnum
public enum PremiumTypeEnum {


    /**
     * TODO
     * 
     */
    @XmlEnumValue("PrePaid")
    PRE_PAID("PrePaid"),

    /**
     * TODO
     * 
     */
    @XmlEnumValue("PostPaid")
    POST_PAID("PostPaid"),

    /**
     * TODO
     * 
     */
    @XmlEnumValue("Variable")
    VARIABLE("Variable"),

    /**
     * TODO
     * 
     */
    @XmlEnumValue("Fixed")
    FIXED("Fixed");
    private final String value;

    PremiumTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PremiumTypeEnum fromValue(String v) {
        for (PremiumTypeEnum c: PremiumTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
