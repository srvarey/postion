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
 * <p>Classe Java pour CommodityBullionSettlementDisruptionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CommodityBullionSettlementDisruptionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Negotiation"/>
 *     &lt;enumeration value="Cancellation and Payment"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommodityBullionSettlementDisruptionEnum")
@XmlEnum
public enum CommodityBullionSettlementDisruptionEnum {


    /**
     * Negotiation will apply in the event of Bullion Settlement Disruption as per Section 10.5.(d) of the 2005 Commodity Definitions.
     * 
     */
    @XmlEnumValue("Negotiation")
    NEGOTIATION("Negotiation"),

    /**
     * Cancellation and Payment will apply in the event of Bullion Settlement Disruption as per Section 10.5.(d) of the 2005 Commodity Definitions.
     * 
     */
    @XmlEnumValue("Cancellation and Payment")
    CANCELLATION_AND_PAYMENT("Cancellation and Payment");
    private final String value;

    CommodityBullionSettlementDisruptionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommodityBullionSettlementDisruptionEnum fromValue(String v) {
        for (CommodityBullionSettlementDisruptionEnum c: CommodityBullionSettlementDisruptionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
