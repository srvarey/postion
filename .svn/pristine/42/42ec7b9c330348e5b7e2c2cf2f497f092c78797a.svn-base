//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the reset frequency. In the case of a weekly reset, also specifies the day of the week that the reset occurs. If the reset frequency is greater than the calculation period frequency the this implies that more or more reset dates is established for each calculation period and some form of rate averaginhg is applicable. The specific averaging method of calculation is specified in FloatingRateCalculation. In case the reset frequency is of value T (term), the period is defined by the swap\swapStream\calculationPerioDates\effectiveDate and the swap\swapStream\calculationPerioDates\terminationDate.
 * 
 * <p>Classe Java pour ResetFrequency complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ResetFrequency">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Frequency">
 *       &lt;sequence>
 *         &lt;element name="weeklyRollConvention" type="{http://www.fpml.org/FpML-5/recordkeeping}WeeklyRollConventionEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResetFrequency", propOrder = {
    "weeklyRollConvention"
})
public class ResetFrequency
    extends Frequency
{

    protected String weeklyRollConvention;

    /**
     * Obtient la valeur de la propriété weeklyRollConvention.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeeklyRollConvention() {
        return weeklyRollConvention;
    }

    /**
     * Définit la valeur de la propriété weeklyRollConvention.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeeklyRollConvention(String value) {
        this.weeklyRollConvention = value;
    }

}
