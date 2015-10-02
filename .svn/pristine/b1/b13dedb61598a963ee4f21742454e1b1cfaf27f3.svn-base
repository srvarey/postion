//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining partial exercise. As defined in the 2000 ISDA Definitions, Section 12.3 Partial Exercise, the buyer of the option may exercise all or less than all the notional amount of the underlying swap but may not be less than the minimum notional amount (if specified) and must be an integral multiple of the integral multiple amount if specified.
 * 
 * <p>Classe Java pour PartialExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PartialExercise">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PartialExercise.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartialExercise", propOrder = {
    "notionalReference",
    "integralMultipleAmount",
    "minimumNotionalAmount",
    "minimumNumberOfOptions"
})
public class PartialExercise {

    protected List<NotionalReference> notionalReference;
    protected BigDecimal integralMultipleAmount;
    protected BigDecimal minimumNotionalAmount;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minimumNumberOfOptions;

    /**
     * Gets the value of the notionalReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notionalReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotionalReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotionalReference }
     * 
     * 
     */
    public List<NotionalReference> getNotionalReference() {
        if (notionalReference == null) {
            notionalReference = new ArrayList<NotionalReference>();
        }
        return this.notionalReference;
    }

    /**
     * Obtient la valeur de la propriété integralMultipleAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIntegralMultipleAmount() {
        return integralMultipleAmount;
    }

    /**
     * Définit la valeur de la propriété integralMultipleAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIntegralMultipleAmount(BigDecimal value) {
        this.integralMultipleAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minimumNotionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinimumNotionalAmount() {
        return minimumNotionalAmount;
    }

    /**
     * Définit la valeur de la propriété minimumNotionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinimumNotionalAmount(BigDecimal value) {
        this.minimumNotionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minimumNumberOfOptions.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinimumNumberOfOptions() {
        return minimumNumberOfOptions;
    }

    /**
     * Définit la valeur de la propriété minimumNumberOfOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinimumNumberOfOptions(BigInteger value) {
        this.minimumNumberOfOptions = value;
    }

}
