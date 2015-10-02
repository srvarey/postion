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
 * The values of a yield curve, including possibly inputs and outputs (dfs, forwards, zero rates).
 * 
 * <p>Classe Java pour YieldCurveValuation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="YieldCurveValuation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PricingStructureValuation">
 *       &lt;sequence>
 *         &lt;element name="inputs" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotedAssetSet" minOccurs="0"/>
 *         &lt;element name="zeroCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}ZeroRateCurve" minOccurs="0"/>
 *         &lt;element name="forwardCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}ForwardRateCurve" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="discountFactorCurve" type="{http://www.fpml.org/FpML-5/recordkeeping}TermCurve" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "YieldCurveValuation", propOrder = {
    "inputs",
    "zeroCurve",
    "forwardCurve",
    "discountFactorCurve"
})
public class YieldCurveValuation
    extends PricingStructureValuation
{

    protected QuotedAssetSet inputs;
    protected ZeroRateCurve zeroCurve;
    protected List<ForwardRateCurve> forwardCurve;
    protected TermCurve discountFactorCurve;

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
     * Obtient la valeur de la propriété zeroCurve.
     * 
     * @return
     *     possible object is
     *     {@link ZeroRateCurve }
     *     
     */
    public ZeroRateCurve getZeroCurve() {
        return zeroCurve;
    }

    /**
     * Définit la valeur de la propriété zeroCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link ZeroRateCurve }
     *     
     */
    public void setZeroCurve(ZeroRateCurve value) {
        this.zeroCurve = value;
    }

    /**
     * Gets the value of the forwardCurve property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forwardCurve property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForwardCurve().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ForwardRateCurve }
     * 
     * 
     */
    public List<ForwardRateCurve> getForwardCurve() {
        if (forwardCurve == null) {
            forwardCurve = new ArrayList<ForwardRateCurve>();
        }
        return this.forwardCurve;
    }

    /**
     * Obtient la valeur de la propriété discountFactorCurve.
     * 
     * @return
     *     possible object is
     *     {@link TermCurve }
     *     
     */
    public TermCurve getDiscountFactorCurve() {
        return discountFactorCurve;
    }

    /**
     * Définit la valeur de la propriété discountFactorCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link TermCurve }
     *     
     */
    public void setDiscountFactorCurve(TermCurve value) {
        this.discountFactorCurve = value;
    }

}
