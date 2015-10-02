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
 * <p>Classe Java pour ReturnTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ReturnTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Dividend"/>
 *     &lt;enumeration value="Price"/>
 *     &lt;enumeration value="Total"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReturnTypeEnum")
@XmlEnum
public enum ReturnTypeEnum {


    /**
     * Dividend return swap.
     * 
     */
    @XmlEnumValue("Dividend")
    DIVIDEND("Dividend"),

    /**
     * Price return swap.
     * 
     */
    @XmlEnumValue("Price")
    PRICE("Price"),

    /**
     * Total return swap.
     * 
     */
    @XmlEnumValue("Total")
    TOTAL("Total");
    private final String value;

    ReturnTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReturnTypeEnum fromValue(String v) {
        for (ReturnTypeEnum c: ReturnTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}