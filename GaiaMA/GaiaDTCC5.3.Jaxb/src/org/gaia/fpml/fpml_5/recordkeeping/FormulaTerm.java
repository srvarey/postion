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
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining a term of the formula. Its value is the product of the its coefficient and the referenced partial derivatives.
 * 
 * <p>Classe Java pour FormulaTerm complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FormulaTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coefficient" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="partialDerivativeReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingParameterDerivativeReference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormulaTerm", propOrder = {
    "coefficient",
    "partialDerivativeReference"
})
public class FormulaTerm {

    protected BigDecimal coefficient;
    protected List<PricingParameterDerivativeReference> partialDerivativeReference;

    /**
     * Obtient la valeur de la propriété coefficient.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * Définit la valeur de la propriété coefficient.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCoefficient(BigDecimal value) {
        this.coefficient = value;
    }

    /**
     * Gets the value of the partialDerivativeReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partialDerivativeReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartialDerivativeReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricingParameterDerivativeReference }
     * 
     * 
     */
    public List<PricingParameterDerivativeReference> getPartialDerivativeReference() {
        if (partialDerivativeReference == null) {
            partialDerivativeReference = new ArrayList<PricingParameterDerivativeReference>();
        }
        return this.partialDerivativeReference;
    }

}
