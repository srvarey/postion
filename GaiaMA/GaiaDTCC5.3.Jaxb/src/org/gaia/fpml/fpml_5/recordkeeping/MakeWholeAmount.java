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
 * A complex type to specify the amount to be paid by the buyer of the option if the option is exercised prior to the Early Call Date (Typically applicable to the convertible bond options).
 * 
 * <p>Classe Java pour MakeWholeAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MakeWholeAmount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}SwapCurveValuation">
 *       &lt;sequence>
 *         &lt;element name="interpolationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterpolationMethod" minOccurs="0"/>
 *         &lt;element name="earlyCallDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MakeWholeAmount", propOrder = {
    "interpolationMethod",
    "earlyCallDate"
})
public class MakeWholeAmount
    extends SwapCurveValuation
{

    protected InterpolationMethod interpolationMethod;
    protected IdentifiedDate earlyCallDate;

    /**
     * Obtient la valeur de la propriété interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link InterpolationMethod }
     *     
     */
    public InterpolationMethod getInterpolationMethod() {
        return interpolationMethod;
    }

    /**
     * Définit la valeur de la propriété interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolationMethod }
     *     
     */
    public void setInterpolationMethod(InterpolationMethod value) {
        this.interpolationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété earlyCallDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getEarlyCallDate() {
        return earlyCallDate;
    }

    /**
     * Définit la valeur de la propriété earlyCallDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setEarlyCallDate(IdentifiedDate value) {
        this.earlyCallDate = value;
    }

}
