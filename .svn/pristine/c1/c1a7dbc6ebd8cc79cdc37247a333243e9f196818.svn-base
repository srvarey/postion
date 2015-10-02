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
 * <p>Classe Java pour QuotationStyleEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="QuotationStyleEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PointsUpFront"/>
 *     &lt;enumeration value="TradedSpread"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QuotationStyleEnum")
@XmlEnum
public enum QuotationStyleEnum {


    /**
     * When quotation style is "PointsUpFront", the initialPoints element of the feeLeg should be populated.
     * 
     */
    @XmlEnumValue("PointsUpFront")
    POINTS_UP_FRONT("PointsUpFront"),

    /**
     * When quotation style is "TradedSpread", the marketFixedRate element of the feeLeg should be populated.
     * 
     */
    @XmlEnumValue("TradedSpread")
    TRADED_SPREAD("TradedSpread");
    private final String value;

    QuotationStyleEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuotationStyleEnum fromValue(String v) {
        for (QuotationStyleEnum c: QuotationStyleEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
