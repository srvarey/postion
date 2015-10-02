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
 * <p>Classe Java pour DayTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DayTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Business"/>
 *     &lt;enumeration value="Calendar"/>
 *     &lt;enumeration value="CommodityBusiness"/>
 *     &lt;enumeration value="CurrencyBusiness"/>
 *     &lt;enumeration value="ExchangeBusiness"/>
 *     &lt;enumeration value="ScheduledTradingDay"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DayTypeEnum")
@XmlEnum
public enum DayTypeEnum {


    /**
     * When calculating the number of days between two dates the count includes only business days.
     * 
     */
    @XmlEnumValue("Business")
    BUSINESS("Business"),

    /**
     * When calculating the number of days between two dates the count includes all calendar days.
     * 
     */
    @XmlEnumValue("Calendar")
    CALENDAR("Calendar"),

    /**
     * When calculating the number of days between two dates the count includes only commodity business days.
     * 
     */
    @XmlEnumValue("CommodityBusiness")
    COMMODITY_BUSINESS("CommodityBusiness"),

    /**
     * When calculating the number of days between two dates the count includes only currency business days.
     * 
     */
    @XmlEnumValue("CurrencyBusiness")
    CURRENCY_BUSINESS("CurrencyBusiness"),

    /**
     * When calculating the number of days between two dates the count includes only stock exchange business days.
     * 
     */
    @XmlEnumValue("ExchangeBusiness")
    EXCHANGE_BUSINESS("ExchangeBusiness"),

    /**
     * When calculating the number of days between two dates the count includes only scheduled trading days.
     * 
     */
    @XmlEnumValue("ScheduledTradingDay")
    SCHEDULED_TRADING_DAY("ScheduledTradingDay");
    private final String value;

    DayTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DayTypeEnum fromValue(String v) {
        for (DayTypeEnum c: DayTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
