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
 * The substitution of a pricing input (e.g. curve) for another, used in generating prices and risks for valuation scenarios.
 * 
 * <p>Classe Java pour PricingInputReplacement complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PricingInputReplacement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="originalInputReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *         &lt;element name="replacementInputReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricingInputReplacement", propOrder = {
    "originalInputReference",
    "replacementInputReference"
})
public class PricingInputReplacement {

    protected PricingStructureReference originalInputReference;
    protected PricingStructureReference replacementInputReference;

    /**
     * Obtient la valeur de la propriété originalInputReference.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getOriginalInputReference() {
        return originalInputReference;
    }

    /**
     * Définit la valeur de la propriété originalInputReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setOriginalInputReference(PricingStructureReference value) {
        this.originalInputReference = value;
    }

    /**
     * Obtient la valeur de la propriété replacementInputReference.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getReplacementInputReference() {
        return replacementInputReference;
    }

    /**
     * Définit la valeur de la propriété replacementInputReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setReplacementInputReference(PricingStructureReference value) {
        this.replacementInputReference = value;
    }

}
