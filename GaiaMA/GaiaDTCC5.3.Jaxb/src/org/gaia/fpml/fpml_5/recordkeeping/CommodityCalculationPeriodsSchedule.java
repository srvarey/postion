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
 * A parametric representation of the Calculation Periods for on Asian option or a leg of a swap. In case the calculation frequency is of value T (term), the period is defined by the commoditySwap\effectiveDate and the commoditySwap\terminationDate.
 * 
 * <p>Classe Java pour CommodityCalculationPeriodsSchedule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityCalculationPeriodsSchedule">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Frequency">
 *       &lt;sequence>
 *         &lt;element name="balanceOfFirstPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityCalculationPeriodsSchedule", propOrder = {
    "balanceOfFirstPeriod"
})
public class CommodityCalculationPeriodsSchedule
    extends Frequency
{

    protected Boolean balanceOfFirstPeriod;

    /**
     * Obtient la valeur de la propriété balanceOfFirstPeriod.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBalanceOfFirstPeriod() {
        return balanceOfFirstPeriod;
    }

    /**
     * Définit la valeur de la propriété balanceOfFirstPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBalanceOfFirstPeriod(Boolean value) {
        this.balanceOfFirstPeriod = value;
    }

}
