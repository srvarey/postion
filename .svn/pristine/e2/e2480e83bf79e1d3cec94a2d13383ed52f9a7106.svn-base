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
 * <p>Classe Java pour CalculationAgentPartyEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CalculationAgentPartyEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ExercisingParty"/>
 *     &lt;enumeration value="NonExercisingParty"/>
 *     &lt;enumeration value="AsSpecifiedInMasterAgreement"/>
 *     &lt;enumeration value="AsSpecifiedInStandardTermsSupplement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CalculationAgentPartyEnum")
@XmlEnum
public enum CalculationAgentPartyEnum {


    /**
     * The party that gives notice of exercise. Per 2000 ISDA Definitions, Section 11.1. Parties, paragraph (d).
     * 
     */
    @XmlEnumValue("ExercisingParty")
    EXERCISING_PARTY("ExercisingParty"),

    /**
     * The party that is given notice of exercise. Per 2000 ISDA Definitions, Section 11.1. Parties, paragraph (e).
     * 
     */
    @XmlEnumValue("NonExercisingParty")
    NON_EXERCISING_PARTY("NonExercisingParty"),

    /**
     * The Calculation Agent is determined by reference to the relevant master agreement.
     * 
     */
    @XmlEnumValue("AsSpecifiedInMasterAgreement")
    AS_SPECIFIED_IN_MASTER_AGREEMENT("AsSpecifiedInMasterAgreement"),

    /**
     * The Calculation Agent is determined by reference to the relevant standard terms supplement.
     * 
     */
    @XmlEnumValue("AsSpecifiedInStandardTermsSupplement")
    AS_SPECIFIED_IN_STANDARD_TERMS_SUPPLEMENT("AsSpecifiedInStandardTermsSupplement");
    private final String value;

    CalculationAgentPartyEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CalculationAgentPartyEnum fromValue(String v) {
        for (CalculationAgentPartyEnum c: CalculationAgentPartyEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
