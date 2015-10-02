//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Descibes the averaging period properties for an asian option.
 * 
 * <p>Classe Java pour FxAsianFeature complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxAsianFeature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="secondaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="fixingTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="observationSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}FxAverageRateObservationSchedule" minOccurs="0"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FxRateObservation.model" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="payoutFormula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="precision" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxAsianFeature", propOrder = {
    "primaryRateSource",
    "secondaryRateSource",
    "fixingTime",
    "observationSchedule",
    "rateObservation",
    "rateObservationQuoteBasis",
    "payoutFormula",
    "precision"
})
public class FxAsianFeature {

    protected InformationSource primaryRateSource;
    protected InformationSource secondaryRateSource;
    protected BusinessCenterTime fixingTime;
    protected FxAverageRateObservationSchedule observationSchedule;
    protected List<FxAverageRateObservation> rateObservation;
    protected StrikeQuoteBasisEnum rateObservationQuoteBasis;
    protected String payoutFormula;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger precision;

    /**
     * Obtient la valeur de la propriété primaryRateSource.
     * 
     * @return
     *     possible object is
     *     {@link InformationSource }
     *     
     */
    public InformationSource getPrimaryRateSource() {
        return primaryRateSource;
    }

    /**
     * Définit la valeur de la propriété primaryRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationSource }
     *     
     */
    public void setPrimaryRateSource(InformationSource value) {
        this.primaryRateSource = value;
    }

    /**
     * Obtient la valeur de la propriété secondaryRateSource.
     * 
     * @return
     *     possible object is
     *     {@link InformationSource }
     *     
     */
    public InformationSource getSecondaryRateSource() {
        return secondaryRateSource;
    }

    /**
     * Définit la valeur de la propriété secondaryRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationSource }
     *     
     */
    public void setSecondaryRateSource(InformationSource value) {
        this.secondaryRateSource = value;
    }

    /**
     * Obtient la valeur de la propriété fixingTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getFixingTime() {
        return fixingTime;
    }

    /**
     * Définit la valeur de la propriété fixingTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setFixingTime(BusinessCenterTime value) {
        this.fixingTime = value;
    }

    /**
     * Obtient la valeur de la propriété observationSchedule.
     * 
     * @return
     *     possible object is
     *     {@link FxAverageRateObservationSchedule }
     *     
     */
    public FxAverageRateObservationSchedule getObservationSchedule() {
        return observationSchedule;
    }

    /**
     * Définit la valeur de la propriété observationSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link FxAverageRateObservationSchedule }
     *     
     */
    public void setObservationSchedule(FxAverageRateObservationSchedule value) {
        this.observationSchedule = value;
    }

    /**
     * Gets the value of the rateObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rateObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRateObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxAverageRateObservation }
     * 
     * 
     */
    public List<FxAverageRateObservation> getRateObservation() {
        if (rateObservation == null) {
            rateObservation = new ArrayList<FxAverageRateObservation>();
        }
        return this.rateObservation;
    }

    /**
     * Obtient la valeur de la propriété rateObservationQuoteBasis.
     * 
     * @return
     *     possible object is
     *     {@link StrikeQuoteBasisEnum }
     *     
     */
    public StrikeQuoteBasisEnum getRateObservationQuoteBasis() {
        return rateObservationQuoteBasis;
    }

    /**
     * Définit la valeur de la propriété rateObservationQuoteBasis.
     * 
     * @param value
     *     allowed object is
     *     {@link StrikeQuoteBasisEnum }
     *     
     */
    public void setRateObservationQuoteBasis(StrikeQuoteBasisEnum value) {
        this.rateObservationQuoteBasis = value;
    }

    /**
     * Obtient la valeur de la propriété payoutFormula.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayoutFormula() {
        return payoutFormula;
    }

    /**
     * Définit la valeur de la propriété payoutFormula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayoutFormula(String value) {
        this.payoutFormula = value;
    }

    /**
     * Obtient la valeur de la propriété precision.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrecision() {
        return precision;
    }

    /**
     * Définit la valeur de la propriété precision.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrecision(BigInteger value) {
        this.precision = value;
    }

}
