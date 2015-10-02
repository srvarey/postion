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
 * <p>Classe Java pour InterestCalculationMethodEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="InterestCalculationMethodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ProRataShare"/>
 *     &lt;enumeration value="FacilityPosition"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InterestCalculationMethodEnum")
@XmlEnum
public enum InterestCalculationMethodEnum {


    /**
     * Agent bank is making an interest payment based on the lender pro-rata share.
     * 
     */
    @XmlEnumValue("ProRataShare")
    PRO_RATA_SHARE("ProRataShare"),

    /**
     * Agent bank is making an interest payment based on the lender position throughout the period.
     * 
     */
    @XmlEnumValue("FacilityPosition")
    FACILITY_POSITION("FacilityPosition");
    private final String value;

    InterestCalculationMethodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterestCalculationMethodEnum fromValue(String v) {
        for (InterestCalculationMethodEnum c: InterestCalculationMethodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
