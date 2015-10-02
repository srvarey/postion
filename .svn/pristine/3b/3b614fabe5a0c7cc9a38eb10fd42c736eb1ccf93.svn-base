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
 * <p>Classe Java pour BullionTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="BullionTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Gold"/>
 *     &lt;enumeration value="Palladium"/>
 *     &lt;enumeration value="Platinum"/>
 *     &lt;enumeration value="Silver"/>
 *     &lt;enumeration value="RhodiumSponge"/>
 *     &lt;enumeration value="Iridium"/>
 *     &lt;enumeration value="Ruthenium"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BullionTypeEnum")
@XmlEnum
public enum BullionTypeEnum {


    /**
     * Gold. Quality as per the Good Delivery Rules issued by the London Bullion Market Association.
     * 
     */
    @XmlEnumValue("Gold")
    GOLD("Gold"),

    /**
     * Palladium. Quality as per the Good Delivery Rules issued by the London Platinum and Palladium Market.
     * 
     */
    @XmlEnumValue("Palladium")
    PALLADIUM("Palladium"),

    /**
     * Palladium. Quality as per the Good Delivery Rules issued by the London Platinum and Palladium Market.
     * 
     */
    @XmlEnumValue("Platinum")
    PLATINUM("Platinum"),

    /**
     * Silver. Quality as per the Good Delivery Rules issued by the London Bullion Market Association.
     * 
     */
    @XmlEnumValue("Silver")
    SILVER("Silver"),

    /**
     * Quality as per the Good Delivery Rules for Rhodium (Sponge).
     * 
     */
    @XmlEnumValue("RhodiumSponge")
    RHODIUM_SPONGE("RhodiumSponge"),

    /**
     * Quality as per the Good Delivery Rules for Iridium.
     * 
     */
    @XmlEnumValue("Iridium")
    IRIDIUM("Iridium"),

    /**
     * Quality as per the Good Delivery Rules for Ruthenium.
     * 
     */
    @XmlEnumValue("Ruthenium")
    RUTHENIUM("Ruthenium");
    private final String value;

    BullionTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BullionTypeEnum fromValue(String v) {
        for (BullionTypeEnum c: BullionTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
