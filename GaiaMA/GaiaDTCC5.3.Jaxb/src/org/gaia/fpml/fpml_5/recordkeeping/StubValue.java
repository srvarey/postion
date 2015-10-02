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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining how a stub calculation period amount is calculated. A single floating rate tenor different to that used for the regular part of the calculation periods schedule may be specified, or two floating rate tenors many be specified. If two floating rate tenors are specified then Linear Interpolation (in accordance with the 2000 ISDA Definitions, Section 8.3 Interpolation) is assumed to apply. Alternatively, an actual known stub rate or stub amount may be specified.
 * 
 * <p>Classe Java pour StubValue complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StubValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="floatingRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FloatingRate" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="stubRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="stubAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StubValue", propOrder = {
    "floatingRate",
    "stubRate",
    "stubAmount"
})
@XmlSeeAlso({
    Stub.class
})
public class StubValue {

    protected List<FloatingRate> floatingRate;
    protected BigDecimal stubRate;
    protected Money stubAmount;

    /**
     * Gets the value of the floatingRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the floatingRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFloatingRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FloatingRate }
     * 
     * 
     */
    public List<FloatingRate> getFloatingRate() {
        if (floatingRate == null) {
            floatingRate = new ArrayList<FloatingRate>();
        }
        return this.floatingRate;
    }

    /**
     * Obtient la valeur de la propriété stubRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStubRate() {
        return stubRate;
    }

    /**
     * Définit la valeur de la propriété stubRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStubRate(BigDecimal value) {
        this.stubRate = value;
    }

    /**
     * Obtient la valeur de la propriété stubAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getStubAmount() {
        return stubAmount;
    }

    /**
     * Définit la valeur de la propriété stubAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setStubAmount(Money value) {
        this.stubAmount = value;
    }

}
