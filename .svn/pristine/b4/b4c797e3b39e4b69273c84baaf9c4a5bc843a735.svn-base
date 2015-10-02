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
 * <p>Classe Java pour PutCallEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PutCallEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Put"/>
 *     &lt;enumeration value="Call"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PutCallEnum")
@XmlEnum
public enum PutCallEnum {


    /**
     * A put option gives the holder the right to sell the underlying asset by a certain date for a certain price.
     * 
     */
    @XmlEnumValue("Put")
    PUT("Put"),

    /**
     * A call option gives the holder the right to buy the underlying asset by a certain date for a certain price.
     * 
     */
    @XmlEnumValue("Call")
    CALL("Call");
    private final String value;

    PutCallEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PutCallEnum fromValue(String v) {
        for (PutCallEnum c: PutCallEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
