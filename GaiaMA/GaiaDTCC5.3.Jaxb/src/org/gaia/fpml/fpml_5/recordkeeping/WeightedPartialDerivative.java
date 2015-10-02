//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A partial derivative multiplied by a weighting factor.
 * 
 * <p>Classe Java pour WeightedPartialDerivative complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="WeightedPartialDerivative">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partialDerivativeReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingParameterDerivativeReference" minOccurs="0"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeightedPartialDerivative", propOrder = {
    "partialDerivativeReference",
    "weight"
})
public class WeightedPartialDerivative {

    protected PricingParameterDerivativeReference partialDerivativeReference;
    protected BigDecimal weight;

    /**
     * Obtient la valeur de la propriété partialDerivativeReference.
     * 
     * @return
     *     possible object is
     *     {@link PricingParameterDerivativeReference }
     *     
     */
    public PricingParameterDerivativeReference getPartialDerivativeReference() {
        return partialDerivativeReference;
    }

    /**
     * Définit la valeur de la propriété partialDerivativeReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingParameterDerivativeReference }
     *     
     */
    public void setPartialDerivativeReference(PricingParameterDerivativeReference value) {
        this.partialDerivativeReference = value;
    }

    /**
     * Obtient la valeur de la propriété weight.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Définit la valeur de la propriété weight.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

}
