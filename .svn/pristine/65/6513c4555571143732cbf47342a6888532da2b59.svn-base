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
 * <p>Classe Java pour IndexEventConsequenceEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="IndexEventConsequenceEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CalculationAgentAdjustment"/>
 *     &lt;enumeration value="NegotiatedCloseOut"/>
 *     &lt;enumeration value="CancellationAndPayment"/>
 *     &lt;enumeration value="RelatedExchange"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IndexEventConsequenceEnum")
@XmlEnum
public enum IndexEventConsequenceEnum {


    /**
     * Calculation Agent Adjustment
     * 
     */
    @XmlEnumValue("CalculationAgentAdjustment")
    CALCULATION_AGENT_ADJUSTMENT("CalculationAgentAdjustment"),

    /**
     * Negotiated Close Out
     * 
     */
    @XmlEnumValue("NegotiatedCloseOut")
    NEGOTIATED_CLOSE_OUT("NegotiatedCloseOut"),

    /**
     * Cancellation and Payment
     * 
     */
    @XmlEnumValue("CancellationAndPayment")
    CANCELLATION_AND_PAYMENT("CancellationAndPayment"),

    /**
     * Related Exchange Adjustment
     * 
     */
    @XmlEnumValue("RelatedExchange")
    RELATED_EXCHANGE("RelatedExchange");
    private final String value;

    IndexEventConsequenceEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IndexEventConsequenceEnum fromValue(String v) {
        for (IndexEventConsequenceEnum c: IndexEventConsequenceEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
