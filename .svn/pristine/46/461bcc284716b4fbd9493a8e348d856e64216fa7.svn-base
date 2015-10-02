//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A set of credit curve values, which can include pricing inputs (which are typically credit spreads), default probabilities, and recovery rates.
 * 
 * <p>Classe Java pour CreditCurveValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CreditCurveValuation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureValuation">
 *       &lt;sequence>
 *         &lt;element name="inputs" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotedAssetSet" minOccurs="0"/>
 *         &lt;element name="defaultProbabilityCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}DefaultProbabilityCurve" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}RecoveryRate.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCurveValuation", propOrder = {
    "inputs",
    "defaultProbabilityCurve",
    "recoveryRate",
    "recoveryRateCurve"
})
public class CreditCurveValuation
    extends PricingStructureValuation
{

    protected QuotedAssetSet inputs;
    protected DefaultProbabilityCurve defaultProbabilityCurve;
    protected BigDecimal recoveryRate;
    protected TermCurve recoveryRateCurve;

    /**
     * Obtient la valeur de la propriété inputs.
     * 
     * @return
     *     possible object is
     *     {@link QuotedAssetSet }
     *     
     */
    public QuotedAssetSet getInputs() {
        return inputs;
    }

    /**
     * Définit la valeur de la propriété inputs.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotedAssetSet }
     *     
     */
    public void setInputs(QuotedAssetSet value) {
        this.inputs = value;
    }

    /**
     * Obtient la valeur de la propriété defaultProbabilityCurve.
     * 
     * @return
     *     possible object is
     *     {@link DefaultProbabilityCurve }
     *     
     */
    public DefaultProbabilityCurve getDefaultProbabilityCurve() {
        return defaultProbabilityCurve;
    }

    /**
     * Définit la valeur de la propriété defaultProbabilityCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultProbabilityCurve }
     *     
     */
    public void setDefaultProbabilityCurve(DefaultProbabilityCurve value) {
        this.defaultProbabilityCurve = value;
    }

    /**
     * Obtient la valeur de la propriété recoveryRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecoveryRate() {
        return recoveryRate;
    }

    /**
     * Définit la valeur de la propriété recoveryRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecoveryRate(BigDecimal value) {
        this.recoveryRate = value;
    }

    /**
     * Obtient la valeur de la propriété recoveryRateCurve.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getRecoveryRateCurve() {
        return recoveryRateCurve;
    }

    /**
     * Définit la valeur de la propriété recoveryRateCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setRecoveryRateCurve(TermCurve value) {
        this.recoveryRateCurve = value;
    }

}
