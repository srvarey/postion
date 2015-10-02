//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type that specifies the source for and timing of a fixing of an exchange rate. This is used in the agreement of non-deliverable forward trades as well as various types of FX OTC options that require observations against a particular rate.
 * 
 * <p>Classe Java pour FxFixing complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxFixing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quotedCurrencyPair" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotedCurrencyPair" minOccurs="0"/>
 *         &lt;element name="fixingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fxSpotRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}FxSpotRateSource" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxFixing", propOrder = {
    "quotedCurrencyPair",
    "fixingDate",
    "fxSpotRateSource"
})
public class FxFixing {

    protected QuotedCurrencyPair quotedCurrencyPair;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fixingDate;
    protected FxSpotRateSource fxSpotRateSource;

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
     * Obtient la valeur de la propriété fixingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFixingDate() {
        return fixingDate;
    }

    /**
     * Définit la valeur de la propriété fixingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFixingDate(XMLGregorianCalendar value) {
        this.fixingDate = value;
    }

    /**
     * Obtient la valeur de la propriété fxSpotRateSource.
     * 
     * @return
     *     possible object is
     *     {@link FxSpotRateSource }
     *     
     */
    public FxSpotRateSource getFxSpotRateSource() {
        return fxSpotRateSource;
    }

    /**
     * Définit la valeur de la propriété fxSpotRateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link FxSpotRateSource }
     *     
     */
    public void setFxSpotRateSource(FxSpotRateSource value) {
        this.fxSpotRateSource = value;
    }

}
