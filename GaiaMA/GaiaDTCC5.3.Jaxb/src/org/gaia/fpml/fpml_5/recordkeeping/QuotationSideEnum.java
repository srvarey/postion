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
 * <p>Classe Java pour QuotationSideEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="QuotationSideEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Bid"/>
 *     &lt;enumeration value="Ask"/>
 *     &lt;enumeration value="Mid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QuotationSideEnum")
@XmlEnum
public enum QuotationSideEnum {


    /**
     * A value "bid" by a buyer for an asset, i.e. the value a buyer is willing to pay.
     * 
     */
    @XmlEnumValue("Bid")
    BID("Bid"),

    /**
     * A value "asked" by a seller for an asset, i.e. the value at which a seller is willing to sell.
     * 
     */
    @XmlEnumValue("Ask")
    ASK("Ask"),

    /**
     * A value midway between the bid and the ask value.
     * 
     */
    @XmlEnumValue("Mid")
    MID("Mid");
    private final String value;

    QuotationSideEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuotationSideEnum fromValue(String v) {
        for (QuotationSideEnum c: QuotationSideEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
