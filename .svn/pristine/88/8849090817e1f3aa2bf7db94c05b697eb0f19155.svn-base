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
 * Determines the currency rate that the seller of the equity amounts will apply at each valuation date for converting the respective amounts into a currency that is different from the currency denomination of the underlyer.
 * 
 * <p>Classe Java pour Quanto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Quanto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fxRate" type="{http://www.fpml.org/FpML-5/recordkeeping}FxRate" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Quanto", propOrder = {
    "fxRate",
    "fxSpotRateSource"
})
public class Quanto {

    protected List<FxRate> fxRate;
    protected FxSpotRateSource fxSpotRateSource;

    /**
     * Gets the value of the fxRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFxRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxRate }
     * 
     * 
     */
    public List<FxRate> getFxRate() {
        if (fxRate == null) {
            fxRate = new ArrayList<FxRate>();
        }
        return this.fxRate;
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
