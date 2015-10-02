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
 * A type defining how the initial or final stub calculation period amounts is calculated. For example, the rate to be applied to the initial or final stub calculation period may be the linear interpolation of two different tenors for the floating rate index specified in the calculation period amount component, e.g. A two month stub period may used the linear interpolation of a one month and three month floating rate. The different rate tenors would be specified in this component. Note that a maximum of two rate tenors can be specified. If a stub period uses a single index tenor and this is the same as that specified in the calculation period amount component then the initial stub or final stub component, as the case may be, must not be included.
 * 
 * <p>Classe Java pour StubCalculationPeriodAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StubCalculationPeriodAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculationPeriodDatesReference" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationPeriodDatesReference" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="initialStub" type="{http://www.fpml.org/FpML-5/recordkeeping}StubValue" minOccurs="0"/>
 *           &lt;element name="finalStub" type="{http://www.fpml.org/FpML-5/recordkeeping}StubValue" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StubCalculationPeriodAmount", propOrder = {
    "calculationPeriodDatesReference",
    "initialStub",
    "finalStub"
})
public class StubCalculationPeriodAmount {

    protected CalculationPeriodDatesReference calculationPeriodDatesReference;
    protected StubValue initialStub;
    protected StubValue finalStub;

    /**
     * Obtient la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public CalculationPeriodDatesReference getCalculationPeriodDatesReference() {
        return calculationPeriodDatesReference;
    }

    /**
     * Définit la valeur de la propriété calculationPeriodDatesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPeriodDatesReference }
     *     
     */
    public void setCalculationPeriodDatesReference(CalculationPeriodDatesReference value) {
        this.calculationPeriodDatesReference = value;
    }

    /**
     * Obtient la valeur de la propriété initialStub.
     * 
     * @return
     *     possible object is
     *     {@link StubValue }
     *     
     */
    public StubValue getInitialStub() {
        return initialStub;
    }

    /**
     * Définit la valeur de la propriété initialStub.
     * 
     * @param value
     *     allowed object is
     *     {@link StubValue }
     *     
     */
    public void setInitialStub(StubValue value) {
        this.initialStub = value;
    }

    /**
     * Obtient la valeur de la propriété finalStub.
     * 
     * @return
     *     possible object is
     *     {@link StubValue }
     *     
     */
    public StubValue getFinalStub() {
        return finalStub;
    }

    /**
     * Définit la valeur de la propriété finalStub.
     * 
     * @param value
     *     allowed object is
     *     {@link StubValue }
     *     
     */
    public void setFinalStub(StubValue value) {
        this.finalStub = value;
    }

}
