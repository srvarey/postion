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
 * <p>Classe Java pour NotionalAdjustmentEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="NotionalAdjustmentEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Execution"/>
 *     &lt;enumeration value="PortfolioRebalancing"/>
 *     &lt;enumeration value="Standard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NotionalAdjustmentEnum")
@XmlEnum
public enum NotionalAdjustmentEnum {


    /**
     * The adjustments to the number of units are governed by an execution clause.
     * 
     */
    @XmlEnumValue("Execution")
    EXECUTION("Execution"),

    /**
     * The adjustments to the number of units are governed by a portfolio rebalancing clause.
     * 
     */
    @XmlEnumValue("PortfolioRebalancing")
    PORTFOLIO_REBALANCING("PortfolioRebalancing"),

    /**
     * The adjustments to the number of units are not governed by any specific clause.
     * 
     */
    @XmlEnumValue("Standard")
    STANDARD("Standard");
    private final String value;

    NotionalAdjustmentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NotionalAdjustmentEnum fromValue(String v) {
        for (NotionalAdjustmentEnum c: NotionalAdjustmentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
