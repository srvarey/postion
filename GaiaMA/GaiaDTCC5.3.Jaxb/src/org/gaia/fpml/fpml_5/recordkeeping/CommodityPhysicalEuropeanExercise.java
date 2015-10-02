//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * The parameters for defining the expiration date(s) and time(s) for a European style option.
 * 
 * <p>Classe Java pour CommodityPhysicalEuropeanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityPhysicalEuropeanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="expirationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *           &lt;element name="expirationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableRelativeOrPeriodicDates2" minOccurs="0"/>
 *           &lt;element name="relativeExpirationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityRelativeExpirationDates" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="expirationTime" type="{http://www.fpml.org/FpML-5/recordkeeping}PrevailingTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityPhysicalEuropeanExercise", propOrder = {
    "expirationDate",
    "expirationDates",
    "relativeExpirationDates",
    "expirationTime"
})
public class CommodityPhysicalEuropeanExercise
    extends Exercise
{

    protected AdjustableOrRelativeDate expirationDate;
    protected AdjustableRelativeOrPeriodicDates2 expirationDates;
    protected CommodityRelativeExpirationDates relativeExpirationDates;
    protected PrevailingTime expirationTime;

    /**
     * Obtient la valeur de la propriété expirationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Définit la valeur de la propriété expirationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setExpirationDate(AdjustableOrRelativeDate value) {
        this.expirationDate = value;
    }

    /**
     * Obtient la valeur de la propriété expirationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableRelativeOrPeriodicDates2 }
     *     
     */
    public AdjustableRelativeOrPeriodicDates2 getExpirationDates() {
        return expirationDates;
    }

    /**
     * Définit la valeur de la propriété expirationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableRelativeOrPeriodicDates2 }
     *     
     */
    public void setExpirationDates(AdjustableRelativeOrPeriodicDates2 value) {
        this.expirationDates = value;
    }

    /**
     * Obtient la valeur de la propriété relativeExpirationDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityRelativeExpirationDates }
     *     
     */
    public CommodityRelativeExpirationDates getRelativeExpirationDates() {
        return relativeExpirationDates;
    }

    /**
     * Définit la valeur de la propriété relativeExpirationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityRelativeExpirationDates }
     *     
     */
    public void setRelativeExpirationDates(CommodityRelativeExpirationDates value) {
        this.relativeExpirationDates = value;
    }

    /**
     * Obtient la valeur de la propriété expirationTime.
     * 
     * @return
     *     possible object is
     *     {@link PrevailingTime }
     *     
     */
    public PrevailingTime getExpirationTime() {
        return expirationTime;
    }

    /**
     * Définit la valeur de la propriété expirationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevailingTime }
     *     
     */
    public void setExpirationTime(PrevailingTime value) {
        this.expirationTime = value;
    }

}
