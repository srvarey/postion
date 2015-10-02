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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Describes an FX touch condition.
 * 
 * <p>Classe Java pour FxTouch complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxTouch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="touchCondition" type="{http://www.fpml.org/FpML-5/recordkeeping}TouchConditionEnum" minOccurs="0"/>
 *         &lt;element name="quotedCurrencyPair" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotedCurrencyPair" minOccurs="0"/>
 *         &lt;element name="triggerRate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="spotRate" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="informationSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="observationStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *           &lt;element name="observationEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxTouch", propOrder = {
    "touchCondition",
    "quotedCurrencyPair",
    "triggerRate",
    "spotRate",
    "informationSource",
    "observationStartDate",
    "observationEndDate"
})
public class FxTouch {

    protected TouchConditionEnum touchCondition;
    protected QuotedCurrencyPair quotedCurrencyPair;
    protected BigDecimal triggerRate;
    protected BigDecimal spotRate;
    protected List<InformationSource> informationSource;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar observationStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar observationEndDate;

    /**
     * Obtient la valeur de la propriété touchCondition.
     * 
     * @return
     *     possible object is
     *     {@link TouchConditionEnum }
     *     
     */
    public TouchConditionEnum getTouchCondition() {
        return touchCondition;
    }

    /**
     * Définit la valeur de la propriété touchCondition.
     * 
     * @param value
     *     allowed object is
     *     {@link TouchConditionEnum }
     *     
     */
    public void setTouchCondition(TouchConditionEnum value) {
        this.touchCondition = value;
    }

    /**
     * Obtient la valeur de la propriété quotedCurrencyPair.
     * 
     * @return
     *     possible object is
     *     {@link QuotedCurrencyPair }
     *     
     */
    public QuotedCurrencyPair getQuotedCurrencyPair() {
        return quotedCurrencyPair;
    }

    /**
     * Définit la valeur de la propriété quotedCurrencyPair.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotedCurrencyPair }
     *     
     */
    public void setQuotedCurrencyPair(QuotedCurrencyPair value) {
        this.quotedCurrencyPair = value;
    }

    /**
     * Obtient la valeur de la propriété triggerRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTriggerRate() {
        return triggerRate;
    }

    /**
     * Définit la valeur de la propriété triggerRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTriggerRate(BigDecimal value) {
        this.triggerRate = value;
    }

    /**
     * Obtient la valeur de la propriété spotRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * Définit la valeur de la propriété spotRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpotRate(BigDecimal value) {
        this.spotRate = value;
    }

    /**
     * Gets the value of the informationSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informationSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformationSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformationSource }
     * 
     * 
     */
    public List<InformationSource> getInformationSource() {
        if (informationSource == null) {
            informationSource = new ArrayList<InformationSource>();
        }
        return this.informationSource;
    }

    /**
     * Obtient la valeur de la propriété observationStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getObservationStartDate() {
        return observationStartDate;
    }

    /**
     * Définit la valeur de la propriété observationStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setObservationStartDate(XMLGregorianCalendar value) {
        this.observationStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété observationEndDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getObservationEndDate() {
        return observationEndDate;
    }

    /**
     * Définit la valeur de la propriété observationEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setObservationEndDate(XMLGregorianCalendar value) {
        this.observationEndDate = value;
    }

}
