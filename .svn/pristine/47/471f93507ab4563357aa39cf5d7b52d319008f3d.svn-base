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
 * <p>Classe Java pour ResetRelativeToEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ResetRelativeToEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CalculationPeriodStartDate"/>
 *     &lt;enumeration value="CalculationPeriodEndDate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResetRelativeToEnum")
@XmlEnum
public enum ResetRelativeToEnum {


    /**
     * Resets will occur relative to the first day of each calculation period.
     * 
     */
    @XmlEnumValue("CalculationPeriodStartDate")
    CALCULATION_PERIOD_START_DATE("CalculationPeriodStartDate"),

    /**
     * Resets will occur relative to the last day of each calculation period.
     * 
     */
    @XmlEnumValue("CalculationPeriodEndDate")
    CALCULATION_PERIOD_END_DATE("CalculationPeriodEndDate");
    private final String value;

    ResetRelativeToEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResetRelativeToEnum fromValue(String v) {
        for (ResetRelativeToEnum c: ResetRelativeToEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
