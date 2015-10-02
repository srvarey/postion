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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A definition of the mathematical derivative with respect to a specific pricing parameter.
 * 
 * <p>Classe Java pour PricingParameterDerivative complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PricingParameterDerivative">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="parameterReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetOrTermPointOrPricingStructureReference" minOccurs="0"/>
 *           &lt;element name="inputDateReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="calculationProcedure" type="{http://www.fpml.org/FpML-5/recordkeeping}DerivativeCalculationProcedure" minOccurs="0"/>
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
@XmlType(name = "PricingParameterDerivative", propOrder = {
    "description",
    "parameterReference",
    "inputDateReference",
    "calculationProcedure"
})
public class PricingParameterDerivative {

    protected String description;
    protected AssetOrTermPointOrPricingStructureReference parameterReference;
    protected List<ValuationReference> inputDateReference;
    protected DerivativeCalculationProcedure calculationProcedure;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété parameterReference.
     * 
     * @return
     *     possible object is
     *     {@link AssetOrTermPointOrPricingStructureReference }
     *     
     */
    public AssetOrTermPointOrPricingStructureReference getParameterReference() {
        return parameterReference;
    }

    /**
     * Définit la valeur de la propriété parameterReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetOrTermPointOrPricingStructureReference }
     *     
     */
    public void setParameterReference(AssetOrTermPointOrPricingStructureReference value) {
        this.parameterReference = value;
    }

    /**
     * Gets the value of the inputDateReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputDateReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputDateReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuationReference }
     * 
     * 
     */
    public List<ValuationReference> getInputDateReference() {
        if (inputDateReference == null) {
            inputDateReference = new ArrayList<ValuationReference>();
        }
        return this.inputDateReference;
    }

    /**
     * Obtient la valeur de la propriété calculationProcedure.
     * 
     * @return
     *     possible object is
     *     {@link DerivativeCalculationProcedure }
     *     
     */
    public DerivativeCalculationProcedure getCalculationProcedure() {
        return calculationProcedure;
    }

    /**
     * Définit la valeur de la propriété calculationProcedure.
     * 
     * @param value
     *     allowed object is
     *     {@link DerivativeCalculationProcedure }
     *     
     */
    public void setCalculationProcedure(DerivativeCalculationProcedure value) {
        this.calculationProcedure = value;
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
