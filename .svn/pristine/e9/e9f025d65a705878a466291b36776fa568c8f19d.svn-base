//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining an offset used in calculating a new date relative to a reference date. Currently, the only offsets defined are expected to be expressed as either calendar or business day offsets.
 * 
 * <p>Classe Java pour Offset complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Offset">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Period">
 *       &lt;sequence>
 *         &lt;element name="dayType" type="{http://www.fpml.org/FpML-5/recordkeeping}DayTypeEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Offset", propOrder = {
    "dayType"
})
@XmlSeeAlso({
    RelativeDateOffset.class,
    FxFixingDate.class,
    DateOffset.class
})
public class Offset
    extends Period
{

    protected DayTypeEnum dayType;

    /**
     * Obtient la valeur de la propriété dayType.
     * 
     * @return
     *     possible object is
     *     {@link DayTypeEnum }
     *     
     */
    public DayTypeEnum getDayType() {
        return dayType;
    }

    /**
     * Définit la valeur de la propriété dayType.
     * 
     * @param value
     *     allowed object is
     *     {@link DayTypeEnum }
     *     
     */
    public void setDayType(DayTypeEnum value) {
        this.dayType = value;
    }

}
