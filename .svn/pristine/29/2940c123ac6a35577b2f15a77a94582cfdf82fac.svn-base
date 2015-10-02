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
 * A matrix of volatilities with dimension 0-3.
 * 
 * <p>Classe Java pour VolatilityMatrix complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VolatilityMatrix">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureValuation">
 *       &lt;sequence>
 *         &lt;element name="dataPoints" type="{http://www.fpml.org/FpML-5/recordkeeping}MultiDimensionalPricingData" minOccurs="0"/>
 *         &lt;element name="adjustment" type="{http://www.fpml.org/FpML-5/recordkeeping}ParametricAdjustment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VolatilityMatrix", propOrder = {
    "dataPoints",
    "adjustment"
})
public class VolatilityMatrix
    extends PricingStructureValuation
{

    protected MultiDimensionalPricingData dataPoints;
    protected List<ParametricAdjustment> adjustment;

    /**
     * Obtient la valeur de la propriété dataPoints.
     * 
     * @return
     *     possible object is
     *     {@link MultiDimensionalPricingData }
     *     
     */
    public MultiDimensionalPricingData getDataPoints() {
        return dataPoints;
    }

    /**
     * Définit la valeur de la propriété dataPoints.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiDimensionalPricingData }
     *     
     */
    public void setDataPoints(MultiDimensionalPricingData value) {
        this.dataPoints = value;
    }

    /**
     * Gets the value of the adjustment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adjustment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdjustment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParametricAdjustment }
     * 
     * 
     */
    public List<ParametricAdjustment> getAdjustment() {
        if (adjustment == null) {
            adjustment = new ArrayList<ParametricAdjustment>();
        }
        return this.adjustment;
    }

}
