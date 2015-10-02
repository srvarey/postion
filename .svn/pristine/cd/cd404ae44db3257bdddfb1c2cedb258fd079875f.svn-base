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
 * <p>Classe Java pour PremiumQuoteBasisEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PremiumQuoteBasisEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PercentageOfCallCurrencyAmount"/>
 *     &lt;enumeration value="PercentageOfPutCurrencyAmount"/>
 *     &lt;enumeration value="CallCurrencyPerPutCurrency"/>
 *     &lt;enumeration value="PutCurrencyPerCallCurrency"/>
 *     &lt;enumeration value="Explicit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PremiumQuoteBasisEnum")
@XmlEnum
public enum PremiumQuoteBasisEnum {


    /**
     * Premium is quoted as a percentage of the callCurrencyAmount.
     * 
     */
    @XmlEnumValue("PercentageOfCallCurrencyAmount")
    PERCENTAGE_OF_CALL_CURRENCY_AMOUNT("PercentageOfCallCurrencyAmount"),

    /**
     * Premium is quoted as a percentage of the putCurrencyAmount.
     * 
     */
    @XmlEnumValue("PercentageOfPutCurrencyAmount")
    PERCENTAGE_OF_PUT_CURRENCY_AMOUNT("PercentageOfPutCurrencyAmount"),

    /**
     * Premium is quoted in the call currency as a percentage of the put currency.
     * 
     */
    @XmlEnumValue("CallCurrencyPerPutCurrency")
    CALL_CURRENCY_PER_PUT_CURRENCY("CallCurrencyPerPutCurrency"),

    /**
     * Premium is quoted in the put currency as a percentage of the call currency.
     * 
     */
    @XmlEnumValue("PutCurrencyPerCallCurrency")
    PUT_CURRENCY_PER_CALL_CURRENCY("PutCurrencyPerCallCurrency"),

    /**
     * Premium is quoted as an explicit amount.
     * 
     */
    @XmlEnumValue("Explicit")
    EXPLICIT("Explicit");
    private final String value;

    PremiumQuoteBasisEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PremiumQuoteBasisEnum fromValue(String v) {
        for (PremiumQuoteBasisEnum c: PremiumQuoteBasisEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
