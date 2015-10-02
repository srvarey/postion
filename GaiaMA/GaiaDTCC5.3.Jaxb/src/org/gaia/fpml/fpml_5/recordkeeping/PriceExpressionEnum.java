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
 * <p>Classe Java pour PriceExpressionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PriceExpressionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AbsoluteTerms"/>
 *     &lt;enumeration value="PercentageOfNotional"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PriceExpressionEnum")
@XmlEnum
public enum PriceExpressionEnum {


    /**
     * The price is expressed as an absolute amount.>
     * 
     */
    @XmlEnumValue("AbsoluteTerms")
    ABSOLUTE_TERMS("AbsoluteTerms"),

    /**
     * The price is expressed in percentage of the notional amount.
     * 
     */
    @XmlEnumValue("PercentageOfNotional")
    PERCENTAGE_OF_NOTIONAL("PercentageOfNotional");
    private final String value;

    PriceExpressionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PriceExpressionEnum fromValue(String v) {
        for (PriceExpressionEnum c: PriceExpressionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
