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
 * <p>Classe Java pour StrikeQuoteBasisEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StrikeQuoteBasisEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PutCurrencyPerCallCurrency"/>
 *     &lt;enumeration value="CallCurrencyPerPutCurrency"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StrikeQuoteBasisEnum")
@XmlEnum
public enum StrikeQuoteBasisEnum {


    /**
     * The strike price is an amount of putCurrency per one unit of callCurrency.
     * 
     */
    @XmlEnumValue("PutCurrencyPerCallCurrency")
    PUT_CURRENCY_PER_CALL_CURRENCY("PutCurrencyPerCallCurrency"),

    /**
     * The strike price is an amount of callCurrency per one unit of putCurrency.
     * 
     */
    @XmlEnumValue("CallCurrencyPerPutCurrency")
    CALL_CURRENCY_PER_PUT_CURRENCY("CallCurrencyPerPutCurrency");
    private final String value;

    StrikeQuoteBasisEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StrikeQuoteBasisEnum fromValue(String v) {
        for (StrikeQuoteBasisEnum c: StrikeQuoteBasisEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
