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
 * <p>Classe Java pour TimeTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TimeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Close"/>
 *     &lt;enumeration value="Open"/>
 *     &lt;enumeration value="OSP"/>
 *     &lt;enumeration value="SpecificTime"/>
 *     &lt;enumeration value="XETRA"/>
 *     &lt;enumeration value="DerivativesClose"/>
 *     &lt;enumeration value="AsSpecifiedInMasterConfirmation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TimeTypeEnum")
@XmlEnum
public enum TimeTypeEnum {


    /**
     * The official closing time of the exchange on the valuation date.
     * 
     */
    @XmlEnumValue("Close")
    CLOSE("Close"),

    /**
     * The official opening time of the exchange on the valuation date.
     * 
     */
    @XmlEnumValue("Open")
    OPEN("Open"),

    /**
     * The time at which the official settlement price is determined.
     * 
     */
    OSP("OSP"),

    /**
     * The time specified in the element equityExpirationTime or valuationTime (as appropriate)
     * 
     */
    @XmlEnumValue("SpecificTime")
    SPECIFIC_TIME("SpecificTime"),

    /**
     * The time at which the official settlement price (following the auction by the exchange) is determined by the exchange.
     * 
     */
    XETRA("XETRA"),

    /**
     * The official closing time of the derivatives exchange on which a derivative contract is listed on that security underlyer.
     * 
     */
    @XmlEnumValue("DerivativesClose")
    DERIVATIVES_CLOSE("DerivativesClose"),

    /**
     * The time is determined as provided in the relevant Master Confirmation.
     * 
     */
    @XmlEnumValue("AsSpecifiedInMasterConfirmation")
    AS_SPECIFIED_IN_MASTER_CONFIRMATION("AsSpecifiedInMasterConfirmation");
    private final String value;

    TimeTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimeTypeEnum fromValue(String v) {
        for (TimeTypeEnum c: TimeTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
