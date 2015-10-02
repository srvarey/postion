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
 * Trigger point at which feature is effective.
 * 
 * <p>Classe Java pour Trigger complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Trigger">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;element name="levelPercentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;choice>
 *             &lt;element name="creditEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditEvents" minOccurs="0"/>
 *             &lt;element name="creditEventsReference" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditEventsReference" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/choice>
 *         &lt;element name="triggerType" type="{http://www.fpml.org/FpML-5/recordkeeping}TriggerTypeEnum" minOccurs="0"/>
 *         &lt;element name="triggerTimeType" type="{http://www.fpml.org/FpML-5/recordkeeping}TriggerTimeTypeEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trigger", propOrder = {
    "level",
    "levelPercentage",
    "creditEvents",
    "creditEventsReference",
    "triggerType",
    "triggerTimeType"
})
public class Trigger {

    protected BigDecimal level;
    protected BigDecimal levelPercentage;
    protected CreditEvents creditEvents;
    protected CreditEventsReference creditEventsReference;
    protected TriggerTypeEnum triggerType;
    protected TriggerTimeTypeEnum triggerTimeType;

    /**
     * Obtient la valeur de la propriété level.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLevel() {
        return level;
    }

    /**
     * Définit la valeur de la propriété level.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLevel(BigDecimal value) {
        this.level = value;
    }

    /**
     * Obtient la valeur de la propriété levelPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLevelPercentage() {
        return levelPercentage;
    }

    /**
     * Définit la valeur de la propriété levelPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLevelPercentage(BigDecimal value) {
        this.levelPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété creditEvents.
     * 
     * @return
     *     possible object is
     *     {@link CreditEvents }
     *     
     */
    public CreditEvents getCreditEvents() {
        return creditEvents;
    }

    /**
     * Définit la valeur de la propriété creditEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditEvents }
     *     
     */
    public void setCreditEvents(CreditEvents value) {
        this.creditEvents = value;
    }

    /**
     * Obtient la valeur de la propriété creditEventsReference.
     * 
     * @return
     *     possible object is
     *     {@link CreditEventsReference }
     *     
     */
    public CreditEventsReference getCreditEventsReference() {
        return creditEventsReference;
    }

    /**
     * Définit la valeur de la propriété creditEventsReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditEventsReference }
     *     
     */
    public void setCreditEventsReference(CreditEventsReference value) {
        this.creditEventsReference = value;
    }

    /**
     * Obtient la valeur de la propriété triggerType.
     * 
     * @return
     *     possible object is
     *     {@link TriggerTypeEnum }
     *     
     */
    public TriggerTypeEnum getTriggerType() {
        return triggerType;
    }

    /**
     * Définit la valeur de la propriété triggerType.
     * 
     * @param value
     *     allowed object is
     *     {@link TriggerTypeEnum }
     *     
     */
    public void setTriggerType(TriggerTypeEnum value) {
        this.triggerType = value;
    }

    /**
     * Obtient la valeur de la propriété triggerTimeType.
     * 
     * @return
     *     possible object is
     *     {@link TriggerTimeTypeEnum }
     *     
     */
    public TriggerTimeTypeEnum getTriggerTimeType() {
        return triggerTimeType;
    }

    /**
     * Définit la valeur de la propriété triggerTimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link TriggerTimeTypeEnum }
     *     
     */
    public void setTriggerTimeType(TriggerTimeTypeEnum value) {
        this.triggerTimeType = value;
    }

}
