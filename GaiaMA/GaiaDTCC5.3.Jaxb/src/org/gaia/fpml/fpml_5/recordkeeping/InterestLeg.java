//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the fixed income leg of the equity swap.
 * 
 * <p>Classe Java pour InterestLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLeg">
 *       &lt;sequence>
 *         &lt;element name="interestLegCalculationPeriodDates" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestLegCalculationPeriodDates" minOccurs="0"/>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapNotional" minOccurs="0"/>
 *         &lt;element name="interestAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}LegAmount" minOccurs="0"/>
 *         &lt;element name="interestCalculation" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestCalculation"/>
 *         &lt;element name="stubCalculationPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}StubCalculationPeriod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestLeg", propOrder = {
    "interestLegCalculationPeriodDates",
    "notional",
    "interestAmount",
    "interestCalculation",
    "stubCalculationPeriod"
})
public class InterestLeg
    extends DirectionalLeg
{

    protected InterestLegCalculationPeriodDates interestLegCalculationPeriodDates;
    protected ReturnSwapNotional notional;
    protected LegAmount interestAmount;
    @XmlElement(required = true)
    protected InterestCalculation interestCalculation;
    protected StubCalculationPeriod stubCalculationPeriod;

    /**
     * Obtient la valeur de la propriété interestLegCalculationPeriodDates.
     * 
     * @return
     *     possible object is
     *     {@link InterestLegCalculationPeriodDates }
     *     
     */
    public InterestLegCalculationPeriodDates getInterestLegCalculationPeriodDates() {
        return interestLegCalculationPeriodDates;
    }

    /**
     * Définit la valeur de la propriété interestLegCalculationPeriodDates.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestLegCalculationPeriodDates }
     *     
     */
    public void setInterestLegCalculationPeriodDates(InterestLegCalculationPeriodDates value) {
        this.interestLegCalculationPeriodDates = value;
    }

    /**
     * Obtient la valeur de la propriété notional.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSwapNotional }
     *     
     */
    public ReturnSwapNotional getNotional() {
        return notional;
    }

    /**
     * Définit la valeur de la propriété notional.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSwapNotional }
     *     
     */
    public void setNotional(ReturnSwapNotional value) {
        this.notional = value;
    }

    /**
     * Obtient la valeur de la propriété interestAmount.
     * 
     * @return
     *     possible object is
     *     {@link LegAmount }
     *     
     */
    public LegAmount getInterestAmount() {
        return interestAmount;
    }

    /**
     * Définit la valeur de la propriété interestAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link LegAmount }
     *     
     */
    public void setInterestAmount(LegAmount value) {
        this.interestAmount = value;
    }

    /**
     * Obtient la valeur de la propriété interestCalculation.
     * 
     * @return
     *     possible object is
     *     {@link InterestCalculation }
     *     
     */
    public InterestCalculation getInterestCalculation() {
        return interestCalculation;
    }

    /**
     * Définit la valeur de la propriété interestCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestCalculation }
     *     
     */
    public void setInterestCalculation(InterestCalculation value) {
        this.interestCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété stubCalculationPeriod.
     * 
     * @return
     *     possible object is
     *     {@link StubCalculationPeriod }
     *     
     */
    public StubCalculationPeriod getStubCalculationPeriod() {
        return stubCalculationPeriod;
    }

    /**
     * Définit la valeur de la propriété stubCalculationPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link StubCalculationPeriod }
     *     
     */
    public void setStubCalculationPeriod(StubCalculationPeriod value) {
        this.stubCalculationPeriod = value;
    }

}
