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
 * <p>Classe Java pour BusinessDayConventionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="BusinessDayConventionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="FOLLOWING"/>
 *     &lt;enumeration value="FRN"/>
 *     &lt;enumeration value="MODFOLLOWING"/>
 *     &lt;enumeration value="PRECEDING"/>
 *     &lt;enumeration value="MODPRECEDING"/>
 *     &lt;enumeration value="NEAREST"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="NotApplicable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BusinessDayConventionEnum")
@XmlEnum
public enum BusinessDayConventionEnum {


    /**
     * The non-business date will be adjusted to the first following day that is a business day
     * 
     */
    FOLLOWING("FOLLOWING"),

    /**
     * Per 2000 ISDA Definitions, Section 4.11. FRN Convention; Eurodollar Convention.
     * 
     */
    FRN("FRN"),

    /**
     * The non-business date will be adjusted to the first following day that is a business day unless that day falls in the next calendar month, in which case that date will be the first preceding day that is a business day.
     * 
     */
    MODFOLLOWING("MODFOLLOWING"),

    /**
     * The non-business day will be adjusted to the first preceding day that is a business day.
     * 
     */
    PRECEDING("PRECEDING"),

    /**
     * The non-business date will be adjusted to the first preceding day that is a business day unless that day falls in the previous calendar month, in which case that date will be the first following day that us a business day.
     * 
     */
    MODPRECEDING("MODPRECEDING"),

    /**
     * The non-business date will be adjusted to the nearest day that is a business day - i.e. if the non-business day falls on any day other than a Sunday or a Monday, it will be the first preceding day that is a business day, and will be the first following business day if it falls on a Sunday or a Monday.
     * 
     */
    NEAREST("NEAREST"),

    /**
     * The date will not be adjusted if it falls on a day that is not a business day.
     * 
     */
    NONE("NONE"),

    /**
     * The date adjustments conventions are defined elsewhere, so it is not required to specify them here.
     * 
     */
    @XmlEnumValue("NotApplicable")
    NOT_APPLICABLE("NotApplicable");
    private final String value;

    BusinessDayConventionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BusinessDayConventionEnum fromValue(String v) {
        for (BusinessDayConventionEnum c: BusinessDayConventionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
