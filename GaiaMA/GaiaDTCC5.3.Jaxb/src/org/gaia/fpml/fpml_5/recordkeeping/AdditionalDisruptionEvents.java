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
 * A type for defining ISDA 2002 Equity Derivative Additional Disruption Events.
 * 
 * <p>Classe Java pour AdditionalDisruptionEvents complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdditionalDisruptionEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changeInLaw" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="failureToDeliver" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="insolvencyFiling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hedgingDisruption" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lossOfStockBorrow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="maximumStockLoanRate" type="{http://www.fpml.org/FpML-5/recordkeeping}RestrictedPercentage" minOccurs="0"/>
 *         &lt;element name="increasedCostOfStockBorrow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="initialStockLoanRate" type="{http://www.fpml.org/FpML-5/recordkeeping}RestrictedPercentage" minOccurs="0"/>
 *         &lt;element name="increasedCostOfHedging" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="determiningPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="foreignOwnershipEvent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalDisruptionEvents", propOrder = {
    "changeInLaw",
    "failureToDeliver",
    "insolvencyFiling",
    "hedgingDisruption",
    "lossOfStockBorrow",
    "maximumStockLoanRate",
    "increasedCostOfStockBorrow",
    "initialStockLoanRate",
    "increasedCostOfHedging",
    "determiningPartyReference",
    "foreignOwnershipEvent"
})
public class AdditionalDisruptionEvents {

    protected Boolean changeInLaw;
    protected Boolean failureToDeliver;
    protected Boolean insolvencyFiling;
    protected Boolean hedgingDisruption;
    protected Boolean lossOfStockBorrow;
    protected BigDecimal maximumStockLoanRate;
    protected Boolean increasedCostOfStockBorrow;
    protected BigDecimal initialStockLoanRate;
    protected Boolean increasedCostOfHedging;
    protected PartyReference determiningPartyReference;
    protected Boolean foreignOwnershipEvent;

    /**
     * Obtient la valeur de la propriété changeInLaw.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangeInLaw() {
        return changeInLaw;
    }

    /**
     * Définit la valeur de la propriété changeInLaw.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangeInLaw(Boolean value) {
        this.changeInLaw = value;
    }

    /**
     * Obtient la valeur de la propriété failureToDeliver.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFailureToDeliver() {
        return failureToDeliver;
    }

    /**
     * Définit la valeur de la propriété failureToDeliver.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFailureToDeliver(Boolean value) {
        this.failureToDeliver = value;
    }

    /**
     * Obtient la valeur de la propriété insolvencyFiling.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInsolvencyFiling() {
        return insolvencyFiling;
    }

    /**
     * Définit la valeur de la propriété insolvencyFiling.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInsolvencyFiling(Boolean value) {
        this.insolvencyFiling = value;
    }

    /**
     * Obtient la valeur de la propriété hedgingDisruption.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHedgingDisruption() {
        return hedgingDisruption;
    }

    /**
     * Définit la valeur de la propriété hedgingDisruption.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHedgingDisruption(Boolean value) {
        this.hedgingDisruption = value;
    }

    /**
     * Obtient la valeur de la propriété lossOfStockBorrow.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLossOfStockBorrow() {
        return lossOfStockBorrow;
    }

    /**
     * Définit la valeur de la propriété lossOfStockBorrow.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLossOfStockBorrow(Boolean value) {
        this.lossOfStockBorrow = value;
    }

    /**
     * Obtient la valeur de la propriété maximumStockLoanRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumStockLoanRate() {
        return maximumStockLoanRate;
    }

    /**
     * Définit la valeur de la propriété maximumStockLoanRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumStockLoanRate(BigDecimal value) {
        this.maximumStockLoanRate = value;
    }

    /**
     * Obtient la valeur de la propriété increasedCostOfStockBorrow.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncreasedCostOfStockBorrow() {
        return increasedCostOfStockBorrow;
    }

    /**
     * Définit la valeur de la propriété increasedCostOfStockBorrow.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncreasedCostOfStockBorrow(Boolean value) {
        this.increasedCostOfStockBorrow = value;
    }

    /**
     * Obtient la valeur de la propriété initialStockLoanRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialStockLoanRate() {
        return initialStockLoanRate;
    }

    /**
     * Définit la valeur de la propriété initialStockLoanRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialStockLoanRate(BigDecimal value) {
        this.initialStockLoanRate = value;
    }

    /**
     * Obtient la valeur de la propriété increasedCostOfHedging.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncreasedCostOfHedging() {
        return increasedCostOfHedging;
    }

    /**
     * Définit la valeur de la propriété increasedCostOfHedging.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncreasedCostOfHedging(Boolean value) {
        this.increasedCostOfHedging = value;
    }

    /**
     * Obtient la valeur de la propriété determiningPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getDeterminingPartyReference() {
        return determiningPartyReference;
    }

    /**
     * Définit la valeur de la propriété determiningPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setDeterminingPartyReference(PartyReference value) {
        this.determiningPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété foreignOwnershipEvent.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isForeignOwnershipEvent() {
        return foreignOwnershipEvent;
    }

    /**
     * Définit la valeur de la propriété foreignOwnershipEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForeignOwnershipEvent(Boolean value) {
        this.foreignOwnershipEvent = value;
    }

}
