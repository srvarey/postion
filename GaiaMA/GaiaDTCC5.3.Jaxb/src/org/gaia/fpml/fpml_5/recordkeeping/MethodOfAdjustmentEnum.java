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
 * <p>Classe Java pour MethodOfAdjustmentEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="MethodOfAdjustmentEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CalculationAgent"/>
 *     &lt;enumeration value="OptionsExchange"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MethodOfAdjustmentEnum")
@XmlEnum
public enum MethodOfAdjustmentEnum {


    /**
     * The Calculation Agent has the right to adjust the terms of the trade following a corporate action.
     * 
     */
    @XmlEnumValue("CalculationAgent")
    CALCULATION_AGENT("CalculationAgent"),

    /**
     * The trade will be adjusted in accordance with any adjustment made by the exchange on which options on the underlying are listed.
     * 
     */
    @XmlEnumValue("OptionsExchange")
    OPTIONS_EXCHANGE("OptionsExchange");
    private final String value;

    MethodOfAdjustmentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MethodOfAdjustmentEnum fromValue(String v) {
        for (MethodOfAdjustmentEnum c: MethodOfAdjustmentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
