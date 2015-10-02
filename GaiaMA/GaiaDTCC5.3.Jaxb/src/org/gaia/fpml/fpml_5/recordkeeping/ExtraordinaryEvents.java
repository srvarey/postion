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
 * Where the underlying is shares, defines market events affecting the issuer of those shares that may require the terms of the transaction to be adjusted.
 * 
 * <p>Classe Java pour ExtraordinaryEvents complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExtraordinaryEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mergerEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityCorporateEvents" minOccurs="0"/>
 *         &lt;element name="tenderOffer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tenderOfferEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityCorporateEvents" minOccurs="0"/>
 *         &lt;element name="compositionOfCombinedConsideration" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="indexAdjustmentEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}IndexAdjustmentEvents" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="additionalDisruptionEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}AdditionalDisruptionEvents" minOccurs="0"/>
 *           &lt;element name="failureToDeliver" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="representations" type="{http://www.fpml.org/FpML-5/recordkeeping}Representations" minOccurs="0"/>
 *         &lt;element name="nationalisationOrInsolvency" type="{http://www.fpml.org/FpML-5/recordkeeping}NationalisationOrInsolvencyOrDelistingEventEnum" minOccurs="0"/>
 *         &lt;element name="delisting" type="{http://www.fpml.org/FpML-5/recordkeeping}NationalisationOrInsolvencyOrDelistingEventEnum" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}ExchangeIdentifier.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtraordinaryEvents", propOrder = {
    "mergerEvents",
    "tenderOffer",
    "tenderOfferEvents",
    "compositionOfCombinedConsideration",
    "indexAdjustmentEvents",
    "additionalDisruptionEvents",
    "failureToDeliver",
    "representations",
    "nationalisationOrInsolvency",
    "delisting",
    "relatedExchangeId",
    "optionsExchangeId",
    "specifiedExchangeId"
})
public class ExtraordinaryEvents {

    protected EquityCorporateEvents mergerEvents;
    protected Boolean tenderOffer;
    protected EquityCorporateEvents tenderOfferEvents;
    protected Boolean compositionOfCombinedConsideration;
    protected IndexAdjustmentEvents indexAdjustmentEvents;
    protected AdditionalDisruptionEvents additionalDisruptionEvents;
    protected Boolean failureToDeliver;
    protected Representations representations;
    protected NationalisationOrInsolvencyOrDelistingEventEnum nationalisationOrInsolvency;
    protected NationalisationOrInsolvencyOrDelistingEventEnum delisting;
    protected List<ExchangeId> relatedExchangeId;
    protected List<ExchangeId> optionsExchangeId;
    protected List<ExchangeId> specifiedExchangeId;

    /**
     * Obtient la valeur de la propriété mergerEvents.
     * 
     * @return
     *     possible object is
     *     {@link EquityCorporateEvents }
     *     
     */
    public EquityCorporateEvents getMergerEvents() {
        return mergerEvents;
    }

    /**
     * Définit la valeur de la propriété mergerEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityCorporateEvents }
     *     
     */
    public void setMergerEvents(EquityCorporateEvents value) {
        this.mergerEvents = value;
    }

    /**
     * Obtient la valeur de la propriété tenderOffer.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTenderOffer() {
        return tenderOffer;
    }

    /**
     * Définit la valeur de la propriété tenderOffer.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTenderOffer(Boolean value) {
        this.tenderOffer = value;
    }

    /**
     * Obtient la valeur de la propriété tenderOfferEvents.
     * 
     * @return
     *     possible object is
     *     {@link EquityCorporateEvents }
     *     
     */
    public EquityCorporateEvents getTenderOfferEvents() {
        return tenderOfferEvents;
    }

    /**
     * Définit la valeur de la propriété tenderOfferEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityCorporateEvents }
     *     
     */
    public void setTenderOfferEvents(EquityCorporateEvents value) {
        this.tenderOfferEvents = value;
    }

    /**
     * Obtient la valeur de la propriété compositionOfCombinedConsideration.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompositionOfCombinedConsideration() {
        return compositionOfCombinedConsideration;
    }

    /**
     * Définit la valeur de la propriété compositionOfCombinedConsideration.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompositionOfCombinedConsideration(Boolean value) {
        this.compositionOfCombinedConsideration = value;
    }

    /**
     * Obtient la valeur de la propriété indexAdjustmentEvents.
     * 
     * @return
     *     possible object is
     *     {@link IndexAdjustmentEvents }
     *     
     */
    public IndexAdjustmentEvents getIndexAdjustmentEvents() {
        return indexAdjustmentEvents;
    }

    /**
     * Définit la valeur de la propriété indexAdjustmentEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link IndexAdjustmentEvents }
     *     
     */
    public void setIndexAdjustmentEvents(IndexAdjustmentEvents value) {
        this.indexAdjustmentEvents = value;
    }

    /**
     * Obtient la valeur de la propriété additionalDisruptionEvents.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDisruptionEvents }
     *     
     */
    public AdditionalDisruptionEvents getAdditionalDisruptionEvents() {
        return additionalDisruptionEvents;
    }

    /**
     * Définit la valeur de la propriété additionalDisruptionEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDisruptionEvents }
     *     
     */
    public void setAdditionalDisruptionEvents(AdditionalDisruptionEvents value) {
        this.additionalDisruptionEvents = value;
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
     * Obtient la valeur de la propriété representations.
     * 
     * @return
     *     possible object is
     *     {@link Representations }
     *     
     */
    public Representations getRepresentations() {
        return representations;
    }

    /**
     * Définit la valeur de la propriété representations.
     * 
     * @param value
     *     allowed object is
     *     {@link Representations }
     *     
     */
    public void setRepresentations(Representations value) {
        this.representations = value;
    }

    /**
     * Obtient la valeur de la propriété nationalisationOrInsolvency.
     * 
     * @return
     *     possible object is
     *     {@link NationalisationOrInsolvencyOrDelistingEventEnum }
     *     
     */
    public NationalisationOrInsolvencyOrDelistingEventEnum getNationalisationOrInsolvency() {
        return nationalisationOrInsolvency;
    }

    /**
     * Définit la valeur de la propriété nationalisationOrInsolvency.
     * 
     * @param value
     *     allowed object is
     *     {@link NationalisationOrInsolvencyOrDelistingEventEnum }
     *     
     */
    public void setNationalisationOrInsolvency(NationalisationOrInsolvencyOrDelistingEventEnum value) {
        this.nationalisationOrInsolvency = value;
    }

    /**
     * Obtient la valeur de la propriété delisting.
     * 
     * @return
     *     possible object is
     *     {@link NationalisationOrInsolvencyOrDelistingEventEnum }
     *     
     */
    public NationalisationOrInsolvencyOrDelistingEventEnum getDelisting() {
        return delisting;
    }

    /**
     * Définit la valeur de la propriété delisting.
     * 
     * @param value
     *     allowed object is
     *     {@link NationalisationOrInsolvencyOrDelistingEventEnum }
     *     
     */
    public void setDelisting(NationalisationOrInsolvencyOrDelistingEventEnum value) {
        this.delisting = value;
    }

    /**
     * Gets the value of the relatedExchangeId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedExchangeId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedExchangeId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeId }
     * 
     * 
     */
    public List<ExchangeId> getRelatedExchangeId() {
        if (relatedExchangeId == null) {
            relatedExchangeId = new ArrayList<ExchangeId>();
        }
        return this.relatedExchangeId;
    }

    /**
     * Gets the value of the optionsExchangeId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionsExchangeId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionsExchangeId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeId }
     * 
     * 
     */
    public List<ExchangeId> getOptionsExchangeId() {
        if (optionsExchangeId == null) {
            optionsExchangeId = new ArrayList<ExchangeId>();
        }
        return this.optionsExchangeId;
    }

    /**
     * Gets the value of the specifiedExchangeId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specifiedExchangeId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecifiedExchangeId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeId }
     * 
     * 
     */
    public List<ExchangeId> getSpecifiedExchangeId() {
        if (specifiedExchangeId == null) {
            specifiedExchangeId = new ArrayList<ExchangeId>();
        }
        return this.specifiedExchangeId;
    }

}
