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
 * A type defining the characteristics of the coal being traded in a physically settled gas transaction.
 * 
 * <p>Classe Java pour CoalProduct complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CoalProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalProductType"/>
 *           &lt;element name="coalProductSpecifications" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalProductSpecifications" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="source" type="{http://www.fpml.org/FpML-5/recordkeeping}CoalProductSource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CommodityUSCoalProduct.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoalProduct", propOrder = {
    "type",
    "coalProductSpecifications",
    "source",
    "btuQualityAdjustment",
    "so2QualityAdjustment"
})
public class CoalProduct {

    protected CoalProductType type;
    protected CoalProductSpecifications coalProductSpecifications;
    protected List<CoalProductSource> source;
    protected CoalQualityAdjustments btuQualityAdjustment;
    protected CoalQualityAdjustments so2QualityAdjustment;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link CoalProductType }
     *     
     */
    public CoalProductType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalProductType }
     *     
     */
    public void setType(CoalProductType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété coalProductSpecifications.
     * 
     * @return
     *     possible object is
     *     {@link CoalProductSpecifications }
     *     
     */
    public CoalProductSpecifications getCoalProductSpecifications() {
        return coalProductSpecifications;
    }

    /**
     * Définit la valeur de la propriété coalProductSpecifications.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalProductSpecifications }
     *     
     */
    public void setCoalProductSpecifications(CoalProductSpecifications value) {
        this.coalProductSpecifications = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the source property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoalProductSource }
     * 
     * 
     */
    public List<CoalProductSource> getSource() {
        if (source == null) {
            source = new ArrayList<CoalProductSource>();
        }
        return this.source;
    }

    /**
     * Obtient la valeur de la propriété btuQualityAdjustment.
     * 
     * @return
     *     possible object is
     *     {@link CoalQualityAdjustments }
     *     
     */
    public CoalQualityAdjustments getBtuQualityAdjustment() {
        return btuQualityAdjustment;
    }

    /**
     * Définit la valeur de la propriété btuQualityAdjustment.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalQualityAdjustments }
     *     
     */
    public void setBtuQualityAdjustment(CoalQualityAdjustments value) {
        this.btuQualityAdjustment = value;
    }

    /**
     * Obtient la valeur de la propriété so2QualityAdjustment.
     * 
     * @return
     *     possible object is
     *     {@link CoalQualityAdjustments }
     *     
     */
    public CoalQualityAdjustments getSo2QualityAdjustment() {
        return so2QualityAdjustment;
    }

    /**
     * Définit la valeur de la propriété so2QualityAdjustment.
     * 
     * @param value
     *     allowed object is
     *     {@link CoalQualityAdjustments }
     *     
     */
    public void setSo2QualityAdjustment(CoalQualityAdjustments value) {
        this.so2QualityAdjustment = value;
    }

}
