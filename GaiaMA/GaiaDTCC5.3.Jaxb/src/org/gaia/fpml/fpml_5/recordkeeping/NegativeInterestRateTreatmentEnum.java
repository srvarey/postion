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
 * <p>Classe Java pour NegativeInterestRateTreatmentEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="NegativeInterestRateTreatmentEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NegativeInterestRateMethod"/>
 *     &lt;enumeration value="ZeroInterestRateMethod"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NegativeInterestRateTreatmentEnum")
@XmlEnum
public enum NegativeInterestRateTreatmentEnum {


    /**
     * Negative Interest Rate Method. Per 2000 ISDA Definitions, Section 6.4 Negative Interest Rates, paragraphs (b) and (c).
     * 
     */
    @XmlEnumValue("NegativeInterestRateMethod")
    NEGATIVE_INTEREST_RATE_METHOD("NegativeInterestRateMethod"),

    /**
     * Zero Interest Rate Method. Per 2000 ISDA Definitions, Section 6.4. Negative Interest Rates, paragraphs (d) and (e).
     * 
     */
    @XmlEnumValue("ZeroInterestRateMethod")
    ZERO_INTEREST_RATE_METHOD("ZeroInterestRateMethod");
    private final String value;

    NegativeInterestRateTreatmentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NegativeInterestRateTreatmentEnum fromValue(String v) {
        for (NegativeInterestRateTreatmentEnum c: NegativeInterestRateTreatmentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
