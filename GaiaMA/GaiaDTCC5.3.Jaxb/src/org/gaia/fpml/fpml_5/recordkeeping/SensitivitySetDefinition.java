//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
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
 * A sensitivity report definition, consisting of a collection of sensitivity definitions.
 * 
 * <p>Classe Java pour SensitivitySetDefinition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SensitivitySetDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensitivityCharacteristics" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotationCharacteristics" minOccurs="0"/>
 *         &lt;element name="valuationScenarioReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenarioReference" minOccurs="0"/>
 *         &lt;element name="pricingInputType" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingInputType" minOccurs="0"/>
 *         &lt;element name="pricingInputReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *         &lt;element name="scale" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="sensitivityDefinition" type="{http://www.fpml.org/FpML-5/recordkeeping}SensitivityDefinition" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "SensitivitySetDefinition", propOrder = {
    "name",
    "sensitivityCharacteristics",
    "valuationScenarioReference",
    "pricingInputType",
    "pricingInputReference",
    "scale",
    "sensitivityDefinition",
    "calculationProcedure"
})
public class SensitivitySetDefinition {

    protected String name;
    protected QuotationCharacteristics sensitivityCharacteristics;
    protected ValuationScenarioReference valuationScenarioReference;
    protected PricingInputType pricingInputType;
    protected PricingStructureReference pricingInputReference;
    protected BigDecimal scale;
    protected List<SensitivityDefinition> sensitivityDefinition;
    protected DerivativeCalculationProcedure calculationProcedure;
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
     * Obtient la valeur de la propriété sensitivityCharacteristics.
     * 
     * @return
     *     possible object is
     *     {@link QuotationCharacteristics }
     *     
     */
    public QuotationCharacteristics getSensitivityCharacteristics() {
        return sensitivityCharacteristics;
    }

    /**
     * Définit la valeur de la propriété sensitivityCharacteristics.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotationCharacteristics }
     *     
     */
    public void setSensitivityCharacteristics(QuotationCharacteristics value) {
        this.sensitivityCharacteristics = value;
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
     * Obtient la valeur de la propriété pricingInputType.
     * 
     * @return
     *     possible object is
     *     {@link PricingInputType }
     *     
     */
    public PricingInputType getPricingInputType() {
        return pricingInputType;
    }

    /**
     * Définit la valeur de la propriété pricingInputType.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingInputType }
     *     
     */
    public void setPricingInputType(PricingInputType value) {
        this.pricingInputType = value;
    }

    /**
     * Obtient la valeur de la propriété pricingInputReference.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getPricingInputReference() {
        return pricingInputReference;
    }

    /**
     * Définit la valeur de la propriété pricingInputReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setPricingInputReference(PricingStructureReference value) {
        this.pricingInputReference = value;
    }

    /**
     * Obtient la valeur de la propriété scale.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * Définit la valeur de la propriété scale.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setScale(BigDecimal value) {
        this.scale = value;
    }

    /**
     * Gets the value of the sensitivityDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensitivityDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensitivityDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SensitivityDefinition }
     * 
     * 
     */
    public List<SensitivityDefinition> getSensitivityDefinition() {
        if (sensitivityDefinition == null) {
            sensitivityDefinition = new ArrayList<SensitivityDefinition>();
        }
        return this.sensitivityDefinition;
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
