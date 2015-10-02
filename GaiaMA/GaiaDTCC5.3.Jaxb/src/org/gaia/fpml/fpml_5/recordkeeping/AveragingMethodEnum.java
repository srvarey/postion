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
 * <p>Classe Java pour AveragingMethodEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AveragingMethodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Unweighted"/>
 *     &lt;enumeration value="Weighted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AveragingMethodEnum")
@XmlEnum
public enum AveragingMethodEnum {


    /**
     * The arithmetic mean of the relevant rates for each reset date.
     * 
     */
    @XmlEnumValue("Unweighted")
    UNWEIGHTED("Unweighted"),

    /**
     * The arithmetic mean of the relevant rates in effect for each day in a calculation period calculated by multiplying each relevant rate by the number of days such relevant rate is in effect, determining the sum of such products and dividing such sum by the number of days in the calculation period.
     * 
     */
    @XmlEnumValue("Weighted")
    WEIGHTED("Weighted");
    private final String value;

    AveragingMethodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AveragingMethodEnum fromValue(String v) {
        for (AveragingMethodEnum c: AveragingMethodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
