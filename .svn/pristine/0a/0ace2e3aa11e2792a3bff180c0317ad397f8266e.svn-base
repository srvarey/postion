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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Some kind of numerical measure about an asset, eg. its NPV, together with characteristics of that measure.
 * 
 * <p>Classe Java pour BasicQuotation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BasicQuotation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Quotation.model"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicQuotation", propOrder = {
    "value",
    "measureType",
    "quoteUnits",
    "side",
    "currency",
    "currencyType",
    "timing",
    "businessCenter",
    "exchangeId",
    "informationSource",
    "pricingModel",
    "time",
    "valuationDate",
    "expiryTime",
    "cashflowType"
})
public class BasicQuotation {

    protected BigDecimal value;
    protected AssetMeasureType measureType;
    protected PriceQuoteUnits quoteUnits;
    protected QuotationSideEnum side;
    protected Currency currency;
    protected ReportingCurrencyType currencyType;
    protected QuoteTiming timing;
    protected BusinessCenter businessCenter;
    protected ExchangeId exchangeId;
    protected List<InformationSource> informationSource;
    protected PricingModel pricingModel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar valuationDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryTime;
    protected CashflowType cashflowType;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété measureType.
     * 
     * @return
     *     possible object is
     *     {@link AssetMeasureType }
     *     
     */
    public AssetMeasureType getMeasureType() {
        return measureType;
    }

    /**
     * Définit la valeur de la propriété measureType.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetMeasureType }
     *     
     */
    public void setMeasureType(AssetMeasureType value) {
        this.measureType = value;
    }

    /**
     * Obtient la valeur de la propriété quoteUnits.
     * 
     * @return
     *     possible object is
     *     {@link PriceQuoteUnits }
     *     
     */
    public PriceQuoteUnits getQuoteUnits() {
        return quoteUnits;
    }

    /**
     * Définit la valeur de la propriété quoteUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceQuoteUnits }
     *     
     */
    public void setQuoteUnits(PriceQuoteUnits value) {
        this.quoteUnits = value;
    }

    /**
     * Obtient la valeur de la propriété side.
     * 
     * @return
     *     possible object is
     *     {@link QuotationSideEnum }
     *     
     */
    public QuotationSideEnum getSide() {
        return side;
    }

    /**
     * Définit la valeur de la propriété side.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotationSideEnum }
     *     
     */
    public void setSide(QuotationSideEnum value) {
        this.side = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété currencyType.
     * 
     * @return
     *     possible object is
     *     {@link ReportingCurrencyType }
     *     
     */
    public ReportingCurrencyType getCurrencyType() {
        return currencyType;
    }

    /**
     * Définit la valeur de la propriété currencyType.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportingCurrencyType }
     *     
     */
    public void setCurrencyType(ReportingCurrencyType value) {
        this.currencyType = value;
    }

    /**
     * Obtient la valeur de la propriété timing.
     * 
     * @return
     *     possible object is
     *     {@link QuoteTiming }
     *     
     */
    public QuoteTiming getTiming() {
        return timing;
    }

    /**
     * Définit la valeur de la propriété timing.
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteTiming }
     *     
     */
    public void setTiming(QuoteTiming value) {
        this.timing = value;
    }

    /**
     * Obtient la valeur de la propriété businessCenter.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenter }
     *     
     */
    public BusinessCenter getBusinessCenter() {
        return businessCenter;
    }

    /**
     * Définit la valeur de la propriété businessCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenter }
     *     
     */
    public void setBusinessCenter(BusinessCenter value) {
        this.businessCenter = value;
    }

    /**
     * Obtient la valeur de la propriété exchangeId.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeId }
     *     
     */
    public ExchangeId getExchangeId() {
        return exchangeId;
    }

    /**
     * Définit la valeur de la propriété exchangeId.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeId }
     *     
     */
    public void setExchangeId(ExchangeId value) {
        this.exchangeId = value;
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
     * Obtient la valeur de la propriété pricingModel.
     * 
     * @return
     *     possible object is
     *     {@link PricingModel }
     *     
     */
    public PricingModel getPricingModel() {
        return pricingModel;
    }

    /**
     * Définit la valeur de la propriété pricingModel.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingModel }
     *     
     */
    public void setPricingModel(PricingModel value) {
        this.pricingModel = value;
    }

    /**
     * Obtient la valeur de la propriété time.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Définit la valeur de la propriété time.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Obtient la valeur de la propriété valuationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValuationDate() {
        return valuationDate;
    }

    /**
     * Définit la valeur de la propriété valuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValuationDate(XMLGregorianCalendar value) {
        this.valuationDate = value;
    }

    /**
     * Obtient la valeur de la propriété expiryTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryTime() {
        return expiryTime;
    }

    /**
     * Définit la valeur de la propriété expiryTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryTime(XMLGregorianCalendar value) {
        this.expiryTime = value;
    }

    /**
     * Obtient la valeur de la propriété cashflowType.
     * 
     * @return
     *     possible object is
     *     {@link CashflowType }
     *     
     */
    public CashflowType getCashflowType() {
        return cashflowType;
    }

    /**
     * Définit la valeur de la propriété cashflowType.
     * 
     * @param value
     *     allowed object is
     *     {@link CashflowType }
     *     
     */
    public void setCashflowType(CashflowType value) {
        this.cashflowType = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
