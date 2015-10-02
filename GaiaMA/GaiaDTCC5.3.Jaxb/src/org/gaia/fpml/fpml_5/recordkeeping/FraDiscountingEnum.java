//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FraDiscountingEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FraDiscountingEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ISDA"/>
 *     &lt;enumeration value="AFMA"/>
 *     &lt;enumeration value="NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FraDiscountingEnum")
@XmlEnum
public enum FraDiscountingEnum {


    /**
     * "FRA Discounting" per the ISDA Definitions will apply.
     * 
     */
    ISDA,

    /**
     * FRA discounting per the Australian Financial Markets Association (AFMA) OTC Financial Product Conventions will apply.
     * 
     */
    AFMA,

    /**
     * No discounting will apply.
     * 
     */
    NONE;

    public String value() {
        return name();
    }

    public static FraDiscountingEnum fromValue(String v) {
        return valueOf(v);
    }

}
