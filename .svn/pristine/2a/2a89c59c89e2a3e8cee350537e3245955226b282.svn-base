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
 * <p>Classe Java pour FPVFinalPriceElectionFallbackEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FPVFinalPriceElectionFallbackEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="FPVClose"/>
 *     &lt;enumeration value="FPVHedgeExecution"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FPVFinalPriceElectionFallbackEnum")
@XmlEnum
public enum FPVFinalPriceElectionFallbackEnum {


    /**
     * In respect of the Early Final Valuation Date, the provisions for FPV Close shall apply.
     * 
     */
    @XmlEnumValue("FPVClose")
    FPV_CLOSE("FPVClose"),

    /**
     * In respect of the Early Final Valuation Date, the provisions for FPV Hedge Execution shall apply.
     * 
     */
    @XmlEnumValue("FPVHedgeExecution")
    FPV_HEDGE_EXECUTION("FPVHedgeExecution");
    private final String value;

    FPVFinalPriceElectionFallbackEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FPVFinalPriceElectionFallbackEnum fromValue(String v) {
        for (FPVFinalPriceElectionFallbackEnum c: FPVFinalPriceElectionFallbackEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
