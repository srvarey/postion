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
 * <p>Classe Java pour StubPeriodTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StubPeriodTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ShortInitial"/>
 *     &lt;enumeration value="ShortFinal"/>
 *     &lt;enumeration value="LongInitial"/>
 *     &lt;enumeration value="LongFinal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StubPeriodTypeEnum")
@XmlEnum
public enum StubPeriodTypeEnum {


    /**
     * If there is a non regular period remaining it is left shorter than the streams calculation period frequency and placed at the start of the stream
     * 
     */
    @XmlEnumValue("ShortInitial")
    SHORT_INITIAL("ShortInitial"),

    /**
     * If there is a non regular period remaining it is left shorter than the streams calculation period frequency and placed at the end of the stream
     * 
     */
    @XmlEnumValue("ShortFinal")
    SHORT_FINAL("ShortFinal"),

    /**
     * If there is a non regular period remaining it is placed at the start of the stream and combined with the adjacent calculation period to give a long first calculation period
     * 
     */
    @XmlEnumValue("LongInitial")
    LONG_INITIAL("LongInitial"),

    /**
     * If there is a non regular period remaining it is placed at the end of the stream and combined with the adjacent calculation period to give a long last calculation period
     * 
     */
    @XmlEnumValue("LongFinal")
    LONG_FINAL("LongFinal");
    private final String value;

    StubPeriodTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StubPeriodTypeEnum fromValue(String v) {
        for (StubPeriodTypeEnum c: StubPeriodTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
