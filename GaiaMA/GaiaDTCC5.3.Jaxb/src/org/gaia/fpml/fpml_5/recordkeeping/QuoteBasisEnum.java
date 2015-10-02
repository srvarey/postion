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
 * <p>Classe Java pour QuoteBasisEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="QuoteBasisEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Currency1PerCurrency2"/>
 *     &lt;enumeration value="Currency2PerCurrency1"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QuoteBasisEnum")
@XmlEnum
public enum QuoteBasisEnum {


    /**
     * The amount of currency1 for one unit of currency2
     * 
     */
    @XmlEnumValue("Currency1PerCurrency2")
    CURRENCY_1_PER_CURRENCY_2("Currency1PerCurrency2"),

    /**
     * The amount of currency2 for one unit of currency1
     * 
     */
    @XmlEnumValue("Currency2PerCurrency1")
    CURRENCY_2_PER_CURRENCY_1("Currency2PerCurrency1");
    private final String value;

    QuoteBasisEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuoteBasisEnum fromValue(String v) {
        for (QuoteBasisEnum c: QuoteBasisEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
