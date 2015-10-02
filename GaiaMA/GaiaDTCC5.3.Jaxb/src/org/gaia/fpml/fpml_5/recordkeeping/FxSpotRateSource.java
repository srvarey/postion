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
 * A type defining the source and time for an fx rate.
 * 
 * <p>Classe Java pour FxSpotRateSource complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxSpotRateSource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="secondaryRateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationSource" minOccurs="0"/>
 *         &lt;element name="fixingTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxSpotRateSource", propOrder = {
    "primaryRateSource",
    "secondaryRateSource",
    "fixingTime"
})
public class FxSpotRateSource {

    protected InformationSource primaryRateSource;
    protected InformationSource secondaryRateSource;
    protected BusinessCenterTime fixingTime;

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

}
