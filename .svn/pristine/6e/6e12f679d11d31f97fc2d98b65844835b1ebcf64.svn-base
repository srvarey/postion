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
 * <p>Classe Java pour QuotationRateTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="QuotationRateTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Bid"/>
 *     &lt;enumeration value="Ask"/>
 *     &lt;enumeration value="Mid"/>
 *     &lt;enumeration value="ExercisingPartyPays"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QuotationRateTypeEnum")
@XmlEnum
public enum QuotationRateTypeEnum {


    /**
     * A bid rate.
     * 
     */
    @XmlEnumValue("Bid")
    BID("Bid"),

    /**
     * An ask rate.
     * 
     */
    @XmlEnumValue("Ask")
    ASK("Ask"),

    /**
     * A mid-market rate.
     * 
     */
    @XmlEnumValue("Mid")
    MID("Mid"),

    /**
     * If optional early termination is applicable to a swap transaction, the rate, which may be a bid or ask rate, which would result, if seller is in-the-money, in the higher absolute value of the cash settlement amount, or, is seller is out-of-the-money, in the lower absolute value of the cash settlement amount.
     * 
     */
    @XmlEnumValue("ExercisingPartyPays")
    EXERCISING_PARTY_PAYS("ExercisingPartyPays");
    private final String value;

    QuotationRateTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuotationRateTypeEnum fromValue(String v) {
        for (QuotationRateTypeEnum c: QuotationRateTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
