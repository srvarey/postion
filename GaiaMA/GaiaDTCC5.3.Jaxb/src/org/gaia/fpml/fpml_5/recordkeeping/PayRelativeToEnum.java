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
 * <p>Classe Java pour PayRelativeToEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PayRelativeToEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CalculationPeriodStartDate"/>
 *     &lt;enumeration value="CalculationPeriodEndDate"/>
 *     &lt;enumeration value="LastPricingDate"/>
 *     &lt;enumeration value="ResetDate"/>
 *     &lt;enumeration value="ValuationDate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PayRelativeToEnum")
@XmlEnum
public enum PayRelativeToEnum {


    /**
     * Payments will occur relative to the first day of each calculation period.
     * 
     */
    @XmlEnumValue("CalculationPeriodStartDate")
    CALCULATION_PERIOD_START_DATE("CalculationPeriodStartDate"),

    /**
     * Payments will occur relative to the last day of each calculation period.
     * 
     */
    @XmlEnumValue("CalculationPeriodEndDate")
    CALCULATION_PERIOD_END_DATE("CalculationPeriodEndDate"),

    /**
     * Payments will occur relative to the last Pricing Date of each Calculation Period.
     * 
     */
    @XmlEnumValue("LastPricingDate")
    LAST_PRICING_DATE("LastPricingDate"),

    /**
     * Payments will occur relative to the reset date.
     * 
     */
    @XmlEnumValue("ResetDate")
    RESET_DATE("ResetDate"),

    /**
     * Payments will occur relative to the valuation date.
     * 
     */
    @XmlEnumValue("ValuationDate")
    VALUATION_DATE("ValuationDate");
    private final String value;

    PayRelativeToEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PayRelativeToEnum fromValue(String v) {
        for (PayRelativeToEnum c: PayRelativeToEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
