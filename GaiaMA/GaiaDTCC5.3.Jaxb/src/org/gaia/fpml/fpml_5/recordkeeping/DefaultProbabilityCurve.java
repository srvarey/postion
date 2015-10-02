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
 * A set of default probabilities.
 * 
 * <p>Classe Java pour DefaultProbabilityCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DefaultProbabilityCurve">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureValuation">
 *       &lt;sequence>
 *         &lt;element name="baseYieldCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureReference" minOccurs="0"/>
 *         &lt;element name="defaultProbabilities" type="{http://www.fpml.org/FpML-5/recordkeeping}TermCurve" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefaultProbabilityCurve", propOrder = {
    "baseYieldCurve",
    "defaultProbabilities"
})
public class DefaultProbabilityCurve
    extends PricingStructureValuation
{

    protected PricingStructureReference baseYieldCurve;
    protected TermCurve defaultProbabilities;

    /**
     * Obtient la valeur de la propriété baseYieldCurve.
     * 
     * @return
     *     possible object is
     *     {@link PricingStructureReference }
     *     
     */
    public PricingStructureReference getBaseYieldCurve() {
        return baseYieldCurve;
    }

    /**
     * Définit la valeur de la propriété baseYieldCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingStructureReference }
     *     
     */
    public void setBaseYieldCurve(PricingStructureReference value) {
        this.baseYieldCurve = value;
    }

    /**
     * Obtient la valeur de la propriété defaultProbabilities.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getDefaultProbabilities() {
        return defaultProbabilities;
    }

    /**
     * Définit la valeur de la propriété defaultProbabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setDefaultProbabilities(TermCurve value) {
        this.defaultProbabilities = value;
    }

}
