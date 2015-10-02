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
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing a financial formula, with its description and components.
 * 
 * <p>Classe Java pour Formula complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Formula">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="formulaDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="math" type="{http://www.fpml.org/FpML-5/recordkeeping}Math" minOccurs="0"/>
 *         &lt;element name="formulaComponent" type="{http://www.fpml.org/FpML-5/recordkeeping}FormulaComponent" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Formula", propOrder = {
    "formulaDescription",
    "math",
    "formulaComponent"
})
public class Formula {

    protected String formulaDescription;
    protected Math math;
    protected List<FormulaComponent> formulaComponent;

    /**
     * Obtient la valeur de la propriété formulaDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormulaDescription() {
        return formulaDescription;
    }

    /**
     * Définit la valeur de la propriété formulaDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormulaDescription(String value) {
        this.formulaDescription = value;
    }

    /**
     * Obtient la valeur de la propriété math.
     * 
     * @return
     *     possible object is
     *     {@link Math }
     *     
     */
    public Math getMath() {
        return math;
    }

    /**
     * Définit la valeur de la propriété math.
     * 
     * @param value
     *     allowed object is
     *     {@link Math }
     *     
     */
    public void setMath(Math value) {
        this.math = value;
    }

    /**
     * Gets the value of the formulaComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formulaComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormulaComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormulaComponent }
     * 
     * 
     */
    public List<FormulaComponent> getFormulaComponent() {
        if (formulaComponent == null) {
            formulaComponent = new ArrayList<FormulaComponent>();
        }
        return this.formulaComponent;
    }

}
