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
 * <p>Classe Java pour FxBarrierTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FxBarrierTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Knockin"/>
 *     &lt;enumeration value="Knockout"/>
 *     &lt;enumeration value="ReverseKnockin"/>
 *     &lt;enumeration value="ReverseKnockout"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FxBarrierTypeEnum")
@XmlEnum
public enum FxBarrierTypeEnum {


    /**
     * Option exists once the barrier is hit. The trigger rate is out-of-the money in relation to the strike rate.
     * 
     */
    @XmlEnumValue("Knockin")
    KNOCKIN("Knockin"),

    /**
     * Option ceases to exist once the barrier is hit. The trigger rate is out-of the-money in relation to the strike rate.
     * 
     */
    @XmlEnumValue("Knockout")
    KNOCKOUT("Knockout"),

    /**
     * Option exists once the barrier is hit. The trigger rate is in-the money in relation to the strike rate.
     * 
     */
    @XmlEnumValue("ReverseKnockin")
    REVERSE_KNOCKIN("ReverseKnockin"),

    /**
     * Option ceases to exist once the barrier is hit. The trigger rate is in-the money in relation to the strike rate.
     * 
     */
    @XmlEnumValue("ReverseKnockout")
    REVERSE_KNOCKOUT("ReverseKnockout");
    private final String value;

    FxBarrierTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FxBarrierTypeEnum fromValue(String v) {
        for (FxBarrierTypeEnum c: FxBarrierTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
