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
 * A type defining the source for a piece of information (e.g. a rate refix or an fx fixing).
 * 
 * <p>Classe Java pour InformationSource complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InformationSource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rateSource" type="{http://www.fpml.org/FpML-5/recordkeeping}InformationProvider" minOccurs="0"/>
 *         &lt;element name="rateSourcePage" type="{http://www.fpml.org/FpML-5/recordkeeping}RateSourcePage" minOccurs="0"/>
 *         &lt;element name="rateSourcePageHeading" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformationSource", propOrder = {
    "rateSource",
    "rateSourcePage",
    "rateSourcePageHeading"
})
public class InformationSource {

    protected InformationProvider rateSource;
    protected RateSourcePage rateSourcePage;
    protected String rateSourcePageHeading;

    /**
     * Obtient la valeur de la propriété rateSource.
     * 
     * @return
     *     possible object is
     *     {@link InformationProvider }
     *     
     */
    public InformationProvider getRateSource() {
        return rateSource;
    }

    /**
     * Définit la valeur de la propriété rateSource.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationProvider }
     *     
     */
    public void setRateSource(InformationProvider value) {
        this.rateSource = value;
    }

    /**
     * Obtient la valeur de la propriété rateSourcePage.
     * 
     * @return
     *     possible object is
     *     {@link RateSourcePage }
     *     
     */
    public RateSourcePage getRateSourcePage() {
        return rateSourcePage;
    }

    /**
     * Définit la valeur de la propriété rateSourcePage.
     * 
     * @param value
     *     allowed object is
     *     {@link RateSourcePage }
     *     
     */
    public void setRateSourcePage(RateSourcePage value) {
        this.rateSourcePage = value;
    }

    /**
     * Obtient la valeur de la propriété rateSourcePageHeading.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateSourcePageHeading() {
        return rateSourcePageHeading;
    }

    /**
     * Définit la valeur de la propriété rateSourcePageHeading.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateSourcePageHeading(String value) {
        this.rateSourcePageHeading = value;
    }

}
