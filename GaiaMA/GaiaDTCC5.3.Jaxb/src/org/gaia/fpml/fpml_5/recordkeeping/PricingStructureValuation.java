//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * An abstract pricing structure valuation base type. Used as a base for values of pricing structures such as yield curves and volatility matrices. Derived from the "Valuation" type.
 * 
 * <p>Classe Java pour PricingStructureValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PricingStructureValuation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Valuation">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PricingInputDates.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricingStructureValuation", propOrder = {
    "baseDate",
    "spotDate",
    "inputDataDate",
    "endDate",
    "buildDateTime"
})
@XmlSeeAlso({
    VolatilityMatrix.class,
    CreditCurveValuation.class,
    YieldCurveValuation.class,
    FxCurveValuation.class,
    DefaultProbabilityCurve.class
})
public class PricingStructureValuation
    extends Valuation
{

    protected IdentifiedDate baseDate;
    protected IdentifiedDate spotDate;
    protected IdentifiedDate inputDataDate;
    protected IdentifiedDate endDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar buildDateTime;

    /**
     * Obtient la valeur de la propriété baseDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getBaseDate() {
        return baseDate;
    }

    /**
     * Définit la valeur de la propriété baseDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setBaseDate(IdentifiedDate value) {
        this.baseDate = value;
    }

    /**
     * Obtient la valeur de la propriété spotDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getSpotDate() {
        return spotDate;
    }

    /**
     * Définit la valeur de la propriété spotDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setSpotDate(IdentifiedDate value) {
        this.spotDate = value;
    }

    /**
     * Obtient la valeur de la propriété inputDataDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getInputDataDate() {
        return inputDataDate;
    }

    /**
     * Définit la valeur de la propriété inputDataDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setInputDataDate(IdentifiedDate value) {
        this.inputDataDate = value;
    }

    /**
     * Obtient la valeur de la propriété endDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getEndDate() {
        return endDate;
    }

    /**
     * Définit la valeur de la propriété endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setEndDate(IdentifiedDate value) {
        this.endDate = value;
    }

    /**
     * Obtient la valeur de la propriété buildDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBuildDateTime() {
        return buildDateTime;
    }

    /**
     * Définit la valeur de la propriété buildDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBuildDateTime(XMLGregorianCalendar value) {
        this.buildDateTime = value;
    }

}
