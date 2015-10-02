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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Specifies the calculation method of the interest rate leg of the return swap. Includes the floating or fixed rate calculation definitions, along with the determination of the day count fraction.
 * 
 * <p>Classe Java pour InterestCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestCalculation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}InterestAccrualsMethod">
 *       &lt;sequence>
 *         &lt;element name="dayCountFraction" type="{http://www.fpml.org/FpML-5/recordkeeping}DayCountFraction" minOccurs="0"/>
 *         &lt;element name="compounding" type="{http://www.fpml.org/FpML-5/recordkeeping}Compounding" minOccurs="0"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="interpolationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterpolationMethod" minOccurs="0"/>
 *           &lt;element name="interpolationPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterpolationPeriodEnum" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestCalculation", propOrder = {
    "dayCountFraction",
    "compounding",
    "interpolationMethod",
    "interpolationPeriod"
})
public class InterestCalculation
    extends InterestAccrualsMethod
{

    protected DayCountFraction dayCountFraction;
    protected Compounding compounding;
    protected InterpolationMethod interpolationMethod;
    protected InterpolationPeriodEnum interpolationPeriod;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété dayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDayCountFraction() {
        return dayCountFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDayCountFraction(DayCountFraction value) {
        this.dayCountFraction = value;
    }

    /**
     * Obtient la valeur de la propriété compounding.
     * 
     * @return
     *     possible object is
     *     {@link Compounding }
     *     
     */
    public Compounding getCompounding() {
        return compounding;
    }

    /**
     * Définit la valeur de la propriété compounding.
     * 
     * @param value
     *     allowed object is
     *     {@link Compounding }
     *     
     */
    public void setCompounding(Compounding value) {
        this.compounding = value;
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
     * Obtient la valeur de la propriété interpolationPeriod.
     * 
     * @return
     *     possible object is
     *     {@link InterpolationPeriodEnum }
     *     
     */
    public InterpolationPeriodEnum getInterpolationPeriod() {
        return interpolationPeriod;
    }

    /**
     * Définit la valeur de la propriété interpolationPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolationPeriodEnum }
     *     
     */
    public void setInterpolationPeriod(InterpolationPeriodEnum value) {
        this.interpolationPeriod = value;
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

}
