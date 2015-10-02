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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A type to capture details relevant to the calculation of the floating price.
 * 
 * <p>Classe Java pour FloatingLegCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FloatingLegCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricingDates" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPricingDates"/>
 *         &lt;element name="averagingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}AveragingMethodEnum" minOccurs="0"/>
 *         &lt;element name="conversionFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="rounding" type="{http://www.fpml.org/FpML-5/recordkeeping}Rounding" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="spread" type="{http://www.fpml.org/FpML-5/recordkeeping}CommoditySpread" minOccurs="0"/>
 *           &lt;element name="spreadSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}CommoditySpreadSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="spreadPercentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="fx" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityFx" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatingLegCalculation", propOrder = {
    "pricingDates",
    "averagingMethod",
    "conversionFactor",
    "rounding",
    "spread",
    "spreadSchedule",
    "spreadPercentage",
    "fx"
})
public class FloatingLegCalculation {

    @XmlElement(required = true)
    protected CommodityPricingDates pricingDates;
    protected AveragingMethodEnum averagingMethod;
    protected BigDecimal conversionFactor;
    protected Rounding rounding;
    protected CommoditySpread spread;
    protected List<CommoditySpreadSchedule> spreadSchedule;
    protected BigDecimal spreadPercentage;
    protected CommodityFx fx;

    /**
     * Obtient la valeur de la propriété pricingDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPricingDates }
     *     
     */
    public CommodityPricingDates getPricingDates() {
        return pricingDates;
    }

    /**
     * Définit la valeur de la propriété pricingDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPricingDates }
     *     
     */
    public void setPricingDates(CommodityPricingDates value) {
        this.pricingDates = value;
    }

    /**
     * Obtient la valeur de la propriété averagingMethod.
     * 
     * @return
     *     possible object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public AveragingMethodEnum getAveragingMethod() {
        return averagingMethod;
    }

    /**
     * Définit la valeur de la propriété averagingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link AveragingMethodEnum }
     *     
     */
    public void setAveragingMethod(AveragingMethodEnum value) {
        this.averagingMethod = value;
    }

    /**
     * Obtient la valeur de la propriété conversionFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Définit la valeur de la propriété conversionFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConversionFactor(BigDecimal value) {
        this.conversionFactor = value;
    }

    /**
     * Obtient la valeur de la propriété rounding.
     * 
     * @return
     *     possible object is
     *     {@link Rounding }
     *     
     */
    public Rounding getRounding() {
        return rounding;
    }

    /**
     * Définit la valeur de la propriété rounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Rounding }
     *     
     */
    public void setRounding(Rounding value) {
        this.rounding = value;
    }

    /**
     * Obtient la valeur de la propriété spread.
     * 
     * @return
     *     possible object is
     *     {@link CommoditySpread }
     *     
     */
    public CommoditySpread getSpread() {
        return spread;
    }

    /**
     * Définit la valeur de la propriété spread.
     * 
     * @param value
     *     allowed object is
     *     {@link CommoditySpread }
     *     
     */
    public void setSpread(CommoditySpread value) {
        this.spread = value;
    }

    /**
     * Gets the value of the spreadSchedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spreadSchedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpreadSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommoditySpreadSchedule }
     * 
     * 
     */
    public List<CommoditySpreadSchedule> getSpreadSchedule() {
        if (spreadSchedule == null) {
            spreadSchedule = new ArrayList<CommoditySpreadSchedule>();
        }
        return this.spreadSchedule;
    }

    /**
     * Obtient la valeur de la propriété spreadPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpreadPercentage() {
        return spreadPercentage;
    }

    /**
     * Définit la valeur de la propriété spreadPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpreadPercentage(BigDecimal value) {
        this.spreadPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété fx.
     * 
     * @return
     *     possible object is
     *     {@link CommodityFx }
     *     
     */
    public CommodityFx getFx() {
        return fx;
    }

    /**
     * Définit la valeur de la propriété fx.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityFx }
     *     
     */
    public void setFx(CommodityFx value) {
        this.fx = value;
    }

}
