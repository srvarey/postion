//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * An abstract base class for all directional leg types with effective date, termination date, and underlyer, where a payer makes a stream of payments of greater than zero value to a receiver.
 * 
 * <p>Classe Java pour DirectionalLegUnderlyerValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DirectionalLegUnderlyerValuation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLegUnderlyer">
 *       &lt;sequence>
 *         &lt;element name="valuation" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityValuation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionalLegUnderlyerValuation", propOrder = {
    "valuation"
})
@XmlSeeAlso({
    CorrelationLeg.class,
    VarianceLeg.class
})
public abstract class DirectionalLegUnderlyerValuation
    extends DirectionalLegUnderlyer
{

    protected EquityValuation valuation;

    /**
     * Obtient la valeur de la propriété valuation.
     * 
     * @return
     *     possible object is
     *     {@link EquityValuation }
     *     
     */
    public EquityValuation getValuation() {
        return valuation;
    }

    /**
     * Définit la valeur de la propriété valuation.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityValuation }
     *     
     */
    public void setValuation(EquityValuation value) {
        this.valuation = value;
    }

}
