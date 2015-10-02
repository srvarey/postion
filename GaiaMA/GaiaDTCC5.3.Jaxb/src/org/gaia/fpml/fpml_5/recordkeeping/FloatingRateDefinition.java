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
 * A type defining parameters associated with a floating rate reset. This type forms part of the cashflows representation of a stream.
 * 
 * <p>Classe Java pour FloatingRateDefinition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FloatingRateDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculatedRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="rateObservation" type="{http://www.fpml.org/FpML-5/recordkeeping}RateObservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="floatingRateMultiplier" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="spread" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="capRate" type="{http://www.fpml.org/FpML-5/recordkeeping}Strike" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="floorRate" type="{http://www.fpml.org/FpML-5/recordkeeping}Strike" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatingRateDefinition", propOrder = {
    "calculatedRate",
    "rateObservation",
    "floatingRateMultiplier",
    "spread",
    "capRate",
    "floorRate"
})
public class FloatingRateDefinition {

    protected BigDecimal calculatedRate;
    protected List<RateObservation> rateObservation;
    protected BigDecimal floatingRateMultiplier;
    protected BigDecimal spread;
    protected List<Strike> capRate;
    protected List<Strike> floorRate;

    /**
     * Obtient la valeur de la propriété calculatedRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCalculatedRate() {
        return calculatedRate;
    }

    /**
     * Définit la valeur de la propriété calculatedRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCalculatedRate(BigDecimal value) {
        this.calculatedRate = value;
    }

    /**
     * Gets the value of the rateObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rateObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRateObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RateObservation }
     * 
     * 
     */
    public List<RateObservation> getRateObservation() {
        if (rateObservation == null) {
            rateObservation = new ArrayList<RateObservation>();
        }
        return this.rateObservation;
    }

    /**
     * Obtient la valeur de la propriété floatingRateMultiplier.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFloatingRateMultiplier() {
        return floatingRateMultiplier;
    }

    /**
     * Définit la valeur de la propriété floatingRateMultiplier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFloatingRateMultiplier(BigDecimal value) {
        this.floatingRateMultiplier = value;
    }

    /**
     * Obtient la valeur de la propriété spread.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpread() {
        return spread;
    }

    /**
     * Définit la valeur de la propriété spread.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpread(BigDecimal value) {
        this.spread = value;
    }

    /**
     * Gets the value of the capRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the capRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCapRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Strike }
     * 
     * 
     */
    public List<Strike> getCapRate() {
        if (capRate == null) {
            capRate = new ArrayList<Strike>();
        }
        return this.capRate;
    }

    /**
     * Gets the value of the floorRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the floorRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFloorRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Strike }
     * 
     * 
     */
    public List<Strike> getFloorRate() {
        if (floorRate == null) {
            floorRate = new ArrayList<Strike>();
        }
        return this.floorRate;
    }

}
