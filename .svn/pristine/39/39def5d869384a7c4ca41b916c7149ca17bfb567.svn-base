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
 * <p>Classe Java pour FlatRateEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FlatRateEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Fixed"/>
 *     &lt;enumeration value="Floating"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FlatRateEnum")
@XmlEnum
public enum FlatRateEnum {


    /**
     * The Flat Rate will be the New Worldwide Tanker Nominal Freight Scale for the Freight Index Route for the Trade Date for the transaction.
     * 
     */
    @XmlEnumValue("Fixed")
    FIXED("Fixed"),

    /**
     * The Flat Rate for each Pricing Date will be the New Worldwide Tanker Nominal Freight Scale for the Freight Index Route for the Pricing Date..
     * 
     */
    @XmlEnumValue("Floating")
    FLOATING("Floating");
    private final String value;

    FlatRateEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlatRateEnum fromValue(String v) {
        for (FlatRateEnum c: FlatRateEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
