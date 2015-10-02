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
 * A type defining the components specifiying an Inflation Rate Calculation
 * 
 * <p>Classe Java pour InflationRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InflationRateCalculation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRateCalculation">
 *       &lt;sequence>
 *         &lt;element name="inflationLag" type="{http://www.fpml.org/FpML-5/recordkeeping}Offset" minOccurs="0"/>
 *         &lt;element name="indexSource" type="{http://www.fpml.org/FpML-5/recordkeeping}RateSourcePage" minOccurs="0"/>
 *         &lt;element name="mainPublication" type="{http://www.fpml.org/FpML-5/recordkeeping}MainPublication" minOccurs="0"/>
 *         &lt;element name="interpolationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterpolationMethod" minOccurs="0"/>
 *         &lt;element name="initialIndexLevel" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="fallbackBondApplicable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InflationRateCalculation", propOrder = {
    "inflationLag",
    "indexSource",
    "mainPublication",
    "interpolationMethod",
    "initialIndexLevel",
    "fallbackBondApplicable"
})
public class InflationRateCalculation
    extends FloatingRateCalculation
{

    protected Offset inflationLag;
    protected RateSourcePage indexSource;
    protected MainPublication mainPublication;
    protected InterpolationMethod interpolationMethod;
    protected BigDecimal initialIndexLevel;
    protected Boolean fallbackBondApplicable;

    /**
     * Obtient la valeur de la propriété inflationLag.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getInflationLag() {
        return inflationLag;
    }

    /**
     * Définit la valeur de la propriété inflationLag.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setInflationLag(Offset value) {
        this.inflationLag = value;
    }

    /**
     * Obtient la valeur de la propriété indexSource.
     * 
     * @return
     *     possible object is
     *     {@link RateSourcePage }
     *     
     */
    public RateSourcePage getIndexSource() {
        return indexSource;
    }

    /**
     * Définit la valeur de la propriété indexSource.
     * 
     * @param value
     *     allowed object is
     *     {@link RateSourcePage }
     *     
     */
    public void setIndexSource(RateSourcePage value) {
        this.indexSource = value;
    }

    /**
     * Obtient la valeur de la propriété mainPublication.
     * 
     * @return
     *     possible object is
     *     {@link MainPublication }
     *     
     */
    public MainPublication getMainPublication() {
        return mainPublication;
    }

    /**
     * Définit la valeur de la propriété mainPublication.
     * 
     * @param value
     *     allowed object is
     *     {@link MainPublication }
     *     
     */
    public void setMainPublication(MainPublication value) {
        this.mainPublication = value;
    }

    /**
     * Obtient la valeur de la propriété interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link InterpolationMethod }
     *     
     */
    public InterpolationMethod getInterpolationMethod() {
        return interpolationMethod;
    }

    /**
     * Définit la valeur de la propriété interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolationMethod }
     *     
     */
    public void setInterpolationMethod(InterpolationMethod value) {
        this.interpolationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété initialIndexLevel.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialIndexLevel() {
        return initialIndexLevel;
    }

    /**
     * Définit la valeur de la propriété initialIndexLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialIndexLevel(BigDecimal value) {
        this.initialIndexLevel = value;
    }

    /**
     * Obtient la valeur de la propriété fallbackBondApplicable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFallbackBondApplicable() {
        return fallbackBondApplicable;
    }

    /**
     * Définit la valeur de la propriété fallbackBondApplicable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFallbackBondApplicable(Boolean value) {
        this.fallbackBondApplicable = value;
    }

}
