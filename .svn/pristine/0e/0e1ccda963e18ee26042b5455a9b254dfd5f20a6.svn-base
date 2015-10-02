//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A set of characteristics describing a sensitivity.
 * 
 * <p>Classe Java pour SensitivityDefinition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SensitivityDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valuationScenarioReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenarioReference" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}ComputedDerivative.model"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}SensitivityDescription.model"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SensitivityDefinition", propOrder = {
    "name",
    "valuationScenarioReference",
    "partialDerivative",
    "formula",
    "term",
    "pricingCoordinateOrReferenceModel"
})
public class SensitivityDefinition {

    protected String name;
    protected ValuationScenarioReference valuationScenarioReference;
    protected List<PricingParameterDerivative> partialDerivative;
    protected DerivativeFormula formula;
    protected TimeDimension term;
    @XmlElements({
        @XmlElement(name = "coordinate", type = PricingDataPointCoordinate.class),
        @XmlElement(name = "coordinateReference", type = PricingDataPointCoordinateReference.class)
    })
    protected List<Object> pricingCoordinateOrReferenceModel;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété valuationScenarioReference.
     * 
     * @return
     *     possible object is
     *     {@link ValuationScenarioReference }
     *     
     */
    public ValuationScenarioReference getValuationScenarioReference() {
        return valuationScenarioReference;
    }

    /**
     * Définit la valeur de la propriété valuationScenarioReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationScenarioReference }
     *     
     */
    public void setValuationScenarioReference(ValuationScenarioReference value) {
        this.valuationScenarioReference = value;
    }

    /**
     * Gets the value of the partialDerivative property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partialDerivative property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartialDerivative().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricingParameterDerivative }
     * 
     * 
     */
    public List<PricingParameterDerivative> getPartialDerivative() {
        if (partialDerivative == null) {
            partialDerivative = new ArrayList<PricingParameterDerivative>();
        }
        return this.partialDerivative;
    }

    /**
     * Obtient la valeur de la propriété formula.
     * 
     * @return
     *     possible object is
     *     {@link DerivativeFormula }
     *     
     */
    public DerivativeFormula getFormula() {
        return formula;
    }

    /**
     * Définit la valeur de la propriété formula.
     * 
     * @param value
     *     allowed object is
     *     {@link DerivativeFormula }
     *     
     */
    public void setFormula(DerivativeFormula value) {
        this.formula = value;
    }

    /**
     * Obtient la valeur de la propriété term.
     * 
     * @return
     *     possible object is
     *     {@link TimeDimension }
     *     
     */
    public TimeDimension getTerm() {
        return term;
    }

    /**
     * Définit la valeur de la propriété term.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeDimension }
     *     
     */
    public void setTerm(TimeDimension value) {
        this.term = value;
    }

    /**
     * The input coordinates, or references to them (e.g. expiration, strike, tenor).Gets the value of the pricingCoordinateOrReferenceModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricingCoordinateOrReferenceModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricingCoordinateOrReferenceModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricingDataPointCoordinate }
     * {@link PricingDataPointCoordinateReference }
     * 
     * 
     */
    public List<Object> getPricingCoordinateOrReferenceModel() {
        if (pricingCoordinateOrReferenceModel == null) {
            pricingCoordinateOrReferenceModel = new ArrayList<Object>();
        }
        return this.pricingCoordinateOrReferenceModel;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
