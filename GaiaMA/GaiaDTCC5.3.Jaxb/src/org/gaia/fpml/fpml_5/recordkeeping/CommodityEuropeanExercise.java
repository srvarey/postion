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
 * A type for defining exercise procedures associated with a European style exercise of a commodity option.
 * 
 * <p>Classe Java pour CommodityEuropeanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityEuropeanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="expirationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="exerciseFrequency" type="{http://www.fpml.org/FpML-5/recordkeeping}Frequency" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="expirationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityEuropeanExercise", propOrder = {
    "expirationDate",
    "exerciseFrequency",
    "expirationTime"
})
public class CommodityEuropeanExercise
    extends Exercise
{

    protected List<AdjustableOrRelativeDate> expirationDate;
    protected Frequency exerciseFrequency;
    protected BusinessCenterTime expirationTime;

    /**
     * Gets the value of the expirationDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expirationDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpirationDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjustableOrRelativeDate }
     * 
     * 
     */
    public List<AdjustableOrRelativeDate> getExpirationDate() {
        if (expirationDate == null) {
            expirationDate = new ArrayList<AdjustableOrRelativeDate>();
        }
        return this.expirationDate;
    }

    /**
     * Obtient la valeur de la propriété exerciseFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Frequency }
     *     
     */
    public Frequency getExerciseFrequency() {
        return exerciseFrequency;
    }

    /**
     * Définit la valeur de la propriété exerciseFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Frequency }
     *     
     */
    public void setExerciseFrequency(Frequency value) {
        this.exerciseFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété expirationTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getExpirationTime() {
        return expirationTime;
    }

    /**
     * Définit la valeur de la propriété expirationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setExpirationTime(BusinessCenterTime value) {
        this.expirationTime = value;
    }

}
