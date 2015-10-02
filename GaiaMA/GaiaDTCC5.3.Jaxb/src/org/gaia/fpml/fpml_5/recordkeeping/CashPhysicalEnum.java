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
 * <p>Classe Java pour CashPhysicalEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CashPhysicalEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Cash"/>
 *     &lt;enumeration value="Physical"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CashPhysicalEnum")
@XmlEnum
public enum CashPhysicalEnum {


    /**
     * The intrinsic value of the option will be delivered by way of a cash settlement amount determined, (i) by reference to the differential between the strike price and the settlement price; or (ii) in accordance with a bilateral agreement between the parties
     * 
     */
    @XmlEnumValue("Cash")
    CASH("Cash"),

    /**
     * The securities underlying the transaction will be delivered by (i) in the case of a call, the seller to the buyer, or (ii) in the case of a put, the buyer to the seller versus a settlement amount equivalent to the strike price per share
     * 
     */
    @XmlEnumValue("Physical")
    PHYSICAL("Physical");
    private final String value;

    CashPhysicalEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CashPhysicalEnum fromValue(String v) {
        for (CashPhysicalEnum c: CashPhysicalEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
