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
 * <p>Classe Java pour RealisedVarianceMethodEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="RealisedVarianceMethodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Previous"/>
 *     &lt;enumeration value="Last"/>
 *     &lt;enumeration value="Both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RealisedVarianceMethodEnum")
@XmlEnum
public enum RealisedVarianceMethodEnum {


    /**
     * For a return on day T, the observed price on T-1 must be in range.
     * 
     */
    @XmlEnumValue("Previous")
    PREVIOUS("Previous"),

    /**
     * For a return on day T, the observed price on T must be in range.
     * 
     */
    @XmlEnumValue("Last")
    LAST("Last"),

    /**
     * For a return on day T, the observed prices on both T and T-1 must be in range
     * 
     */
    @XmlEnumValue("Both")
    BOTH("Both");
    private final String value;

    RealisedVarianceMethodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RealisedVarianceMethodEnum fromValue(String v) {
        for (RealisedVarianceMethodEnum c: RealisedVarianceMethodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
