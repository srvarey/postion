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
 * The parameters for defining the expiration date(s) and time(s) for an American style option.
 * 
 * <p>Classe Java pour CommodityPhysicalAmericanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommodityPhysicalAmericanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="commencementDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDates" minOccurs="0"/>
 *             &lt;element name="expirationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDates" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="relativeCommencementDates" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityRelativeExpirationDates" minOccurs="0"/>
 *             &lt;element name="relativeExpirationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityRelativeExpirationDates" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="latestExerciseTime" type="{http://www.fpml.org/FpML-5/recordkeeping}PrevailingTime" minOccurs="0"/>
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
@XmlType(name = "CommodityPhysicalAmericanExercise", propOrder = {
    "commencementDates",
    "expirationDates",
    "relativeCommencementDates",
    "relativeExpirationDates",
    "latestExerciseTime",
    "expirationTime"
})
public class CommodityPhysicalAmericanExercise
    extends Exercise
{

    protected AdjustableOrRelativeDates commencementDates;
    protected AdjustableOrRelativeDates expirationDates;
    protected CommodityRelativeExpirationDates relativeCommencementDates;
    protected CommodityRelativeExpirationDates relativeExpirationDates;
    protected PrevailingTime latestExerciseTime;
    protected PrevailingTime expirationTime;

    /**
     * Obtient la valeur de la propriété commencementDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public AdjustableOrRelativeDates getCommencementDates() {
        return commencementDates;
    }

    /**
     * Définit la valeur de la propriété commencementDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public void setCommencementDates(AdjustableOrRelativeDates value) {
        this.commencementDates = value;
    }

    /**
     * Obtient la valeur de la propriété expirationDates.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public AdjustableOrRelativeDates getExpirationDates() {
        return expirationDates;
    }

    /**
     * Définit la valeur de la propriété expirationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDates }
     *     
     */
    public void setExpirationDates(AdjustableOrRelativeDates value) {
        this.expirationDates = value;
    }

    /**
     * Obtient la valeur de la propriété relativeCommencementDates.
     * 
     * @return
     *     possible object is
     *     {@link CommodityRelativeExpirationDates }
     *     
     */
    public CommodityRelativeExpirationDates getRelativeCommencementDates() {
        return relativeCommencementDates;
    }

    /**
     * Définit la valeur de la propriété relativeCommencementDates.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityRelativeExpirationDates }
     *     
     */
    public void setRelativeCommencementDates(CommodityRelativeExpirationDates value) {
        this.relativeCommencementDates = value;
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
     * Obtient la valeur de la propriété latestExerciseTime.
     * 
     * @return
     *     possible object is
     *     {@link PrevailingTime }
     *     
     */
    public PrevailingTime getLatestExerciseTime() {
        return latestExerciseTime;
    }

    /**
     * Définit la valeur de la propriété latestExerciseTime.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevailingTime }
     *     
     */
    public void setLatestExerciseTime(PrevailingTime value) {
        this.latestExerciseTime = value;
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
