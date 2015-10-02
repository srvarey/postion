//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A valuation of an valuable object - an asset or a pricing input. This is an abstract type, used as a base for values of pricing structures such as yield curves as well as asset values.
 * 
 * <p>Classe Java pour Valuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Valuation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objectReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AnyAssetReference" minOccurs="0"/>
 *         &lt;element name="valuationScenarioReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenarioReference" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="definitionRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Valuation", propOrder = {
    "objectReference",
    "valuationScenarioReference"
})
@XmlSeeAlso({
    BasicAssetValuation.class,
    AssetValuation.class,
    PricingStructureValuation.class
})
public class Valuation {

    protected AnyAssetReference objectReference;
    protected ValuationScenarioReference valuationScenarioReference;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "definitionRef")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object definitionRef;

    /**
     * Obtient la valeur de la propriété objectReference.
     * 
     * @return
     *     possible object is
     *     {@link AnyAssetReference }
     *     
     */
    public AnyAssetReference getObjectReference() {
        return objectReference;
    }

    /**
     * Définit la valeur de la propriété objectReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyAssetReference }
     *     
     */
    public void setObjectReference(AnyAssetReference value) {
        this.objectReference = value;
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

    /**
     * Obtient la valeur de la propriété definitionRef.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDefinitionRef() {
        return definitionRef;
    }

    /**
     * Définit la valeur de la propriété definitionRef.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDefinitionRef(Object value) {
        this.definitionRef = value;
    }

}
