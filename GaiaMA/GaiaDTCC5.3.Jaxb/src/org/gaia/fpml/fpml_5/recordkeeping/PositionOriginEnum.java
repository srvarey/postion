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
 * <p>Classe Java pour PositionOriginEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PositionOriginEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Trade"/>
 *     &lt;enumeration value="Allocation"/>
 *     &lt;enumeration value="Novation"/>
 *     &lt;enumeration value="Netting"/>
 *     &lt;enumeration value="Exercise"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PositionOriginEnum")
@XmlEnum
public enum PositionOriginEnum {


    /**
     * The position originated directly from a trade.
     * 
     */
    @XmlEnumValue("Trade")
    TRADE("Trade"),

    /**
     * The position originated from an allocation of a block trade.
     * 
     */
    @XmlEnumValue("Allocation")
    ALLOCATION("Allocation"),

    /**
     * The position originated from a novation or post-trade transfer.
     * 
     */
    @XmlEnumValue("Novation")
    NOVATION("Novation"),

    /**
     * The position originated from netting or portfolio compression.
     * 
     */
    @XmlEnumValue("Netting")
    NETTING("Netting"),

    /**
     * The position originated from an exercise of a physically-settled option.
     * 
     */
    @XmlEnumValue("Exercise")
    EXERCISE("Exercise");
    private final String value;

    PositionOriginEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PositionOriginEnum fromValue(String v) {
        for (PositionOriginEnum c: PositionOriginEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
