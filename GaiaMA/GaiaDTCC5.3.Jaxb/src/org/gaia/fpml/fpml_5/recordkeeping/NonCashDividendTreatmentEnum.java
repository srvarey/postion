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
 * <p>Classe Java pour NonCashDividendTreatmentEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="NonCashDividendTreatmentEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PotentialAdjustmentEvent"/>
 *     &lt;enumeration value="CashEquivalent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NonCashDividendTreatmentEnum")
@XmlEnum
public enum NonCashDividendTreatmentEnum {


    /**
     * The treatment of any non-cash dividend shall be determined in accordance with the Potential Adjustment Event provisions.
     * 
     */
    @XmlEnumValue("PotentialAdjustmentEvent")
    POTENTIAL_ADJUSTMENT_EVENT("PotentialAdjustmentEvent"),

    /**
     * Any non-cash dividend shall be treated as a Declared Cash Equivalent Dividend.
     * 
     */
    @XmlEnumValue("CashEquivalent")
    CASH_EQUIVALENT("CashEquivalent");
    private final String value;

    NonCashDividendTreatmentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NonCashDividendTreatmentEnum fromValue(String v) {
        for (NonCashDividendTreatmentEnum c: NonCashDividendTreatmentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}