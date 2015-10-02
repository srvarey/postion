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
 * <p>Classe Java pour CommissionDenominationEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CommissionDenominationEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="BPS"/>
 *     &lt;enumeration value="Percentage"/>
 *     &lt;enumeration value="CentsPerShare"/>
 *     &lt;enumeration value="FixedAmount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommissionDenominationEnum")
@XmlEnum
public enum CommissionDenominationEnum {


    /**
     * The commission is expressed in basis points, in reference to the price referenced in the document.
     * 
     */
    BPS("BPS"),

    /**
     * The commission is expressed as a percentage of the gross price referenced in the document.
     * 
     */
    @XmlEnumValue("Percentage")
    PERCENTAGE("Percentage"),

    /**
     * The commission is expressed in cents per share.
     * 
     */
    @XmlEnumValue("CentsPerShare")
    CENTS_PER_SHARE("CentsPerShare"),

    /**
     * The commission is expressed as a absolute amount.
     * 
     */
    @XmlEnumValue("FixedAmount")
    FIXED_AMOUNT("FixedAmount");
    private final String value;

    CommissionDenominationEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommissionDenominationEnum fromValue(String v) {
        for (CommissionDenominationEnum c: CommissionDenominationEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
