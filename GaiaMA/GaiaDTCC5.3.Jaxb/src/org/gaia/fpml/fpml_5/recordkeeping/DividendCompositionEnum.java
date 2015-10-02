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
 * <p>Classe Java pour DividendCompositionEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DividendCompositionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="EquityAmountReceiverElection"/>
 *     &lt;enumeration value="CalculationAgentElection"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DividendCompositionEnum")
@XmlEnum
public enum DividendCompositionEnum {


    /**
     * The Equity Amount Receiver determines the composition of dividends (subject to conditions).
     * 
     */
    @XmlEnumValue("EquityAmountReceiverElection")
    EQUITY_AMOUNT_RECEIVER_ELECTION("EquityAmountReceiverElection"),

    /**
     * The Calculation Agent determines the composition of dividends (subject to conditions).
     * 
     */
    @XmlEnumValue("CalculationAgentElection")
    CALCULATION_AGENT_ELECTION("CalculationAgentElection");
    private final String value;

    DividendCompositionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DividendCompositionEnum fromValue(String v) {
        for (DividendCompositionEnum c: DividendCompositionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
