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
 * A type for defining option features.
 * 
 * <p>Classe Java pour OptionFeature complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OptionFeature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionBaseFeature.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionFeature.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionFeature", propOrder = {
    "fxFeature",
    "strategyFeature",
    "asian",
    "barrier",
    "knock",
    "passThrough"
})
public class OptionFeature {

    protected FxFeature fxFeature;
    protected StrategyFeature strategyFeature;
    protected Asian asian;
    protected Barrier barrier;
    protected Knock knock;
    protected PassThrough passThrough;

    /**
     * Obtient la valeur de la propriété fxFeature.
     * 
     * @return
     *     possible object is
     *     {@link FxFeature }
     *     
     */
    public FxFeature getFxFeature() {
        return fxFeature;
    }

    /**
     * Définit la valeur de la propriété fxFeature.
     * 
     * @param value
     *     allowed object is
     *     {@link FxFeature }
     *     
     */
    public void setFxFeature(FxFeature value) {
        this.fxFeature = value;
    }

    /**
     * Obtient la valeur de la propriété strategyFeature.
     * 
     * @return
     *     possible object is
     *     {@link StrategyFeature }
     *     
     */
    public StrategyFeature getStrategyFeature() {
        return strategyFeature;
    }

    /**
     * Définit la valeur de la propriété strategyFeature.
     * 
     * @param value
     *     allowed object is
     *     {@link StrategyFeature }
     *     
     */
    public void setStrategyFeature(StrategyFeature value) {
        this.strategyFeature = value;
    }

    /**
     * Obtient la valeur de la propriété asian.
     * 
     * @return
     *     possible object is
     *     {@link Asian }
     *     
     */
    public Asian getAsian() {
        return asian;
    }

    /**
     * Définit la valeur de la propriété asian.
     * 
     * @param value
     *     allowed object is
     *     {@link Asian }
     *     
     */
    public void setAsian(Asian value) {
        this.asian = value;
    }

    /**
     * Obtient la valeur de la propriété barrier.
     * 
     * @return
     *     possible object is
     *     {@link Barrier }
     *     
     */
    public Barrier getBarrier() {
        return barrier;
    }

    /**
     * Définit la valeur de la propriété barrier.
     * 
     * @param value
     *     allowed object is
     *     {@link Barrier }
     *     
     */
    public void setBarrier(Barrier value) {
        this.barrier = value;
    }

    /**
     * Obtient la valeur de la propriété knock.
     * 
     * @return
     *     possible object is
     *     {@link Knock }
     *     
     */
    public Knock getKnock() {
        return knock;
    }

    /**
     * Définit la valeur de la propriété knock.
     * 
     * @param value
     *     allowed object is
     *     {@link Knock }
     *     
     */
    public void setKnock(Knock value) {
        this.knock = value;
    }

    /**
     * Obtient la valeur de la propriété passThrough.
     * 
     * @return
     *     possible object is
     *     {@link PassThrough }
     *     
     */
    public PassThrough getPassThrough() {
        return passThrough;
    }

    /**
     * Définit la valeur de la propriété passThrough.
     * 
     * @param value
     *     allowed object is
     *     {@link PassThrough }
     *     
     */
    public void setPassThrough(PassThrough value) {
        this.passThrough = value;
    }

}
