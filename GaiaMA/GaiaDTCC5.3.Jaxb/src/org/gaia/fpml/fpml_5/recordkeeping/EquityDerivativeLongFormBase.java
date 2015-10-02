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
 * type for defining the common features of equity derivatives.
 * 
 * <p>Classe Java pour EquityDerivativeLongFormBase complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityDerivativeLongFormBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}EquityDerivativeBase">
 *       &lt;sequence>
 *         &lt;element name="dividendConditions" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendConditions" minOccurs="0"/>
 *         &lt;element name="methodOfAdjustment" type="{http://www.fpml.org/FpML-5/recordkeeping}MethodOfAdjustmentEnum" minOccurs="0"/>
 *         &lt;element name="extraordinaryEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}ExtraordinaryEvents" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityDerivativeLongFormBase", propOrder = {
    "dividendConditions",
    "methodOfAdjustment",
    "extraordinaryEvents"
})
@XmlSeeAlso({
    EquityForward.class,
    EquityOption.class
})
public abstract class EquityDerivativeLongFormBase
    extends EquityDerivativeBase
{

    protected DividendConditions dividendConditions;
    protected MethodOfAdjustmentEnum methodOfAdjustment;
    protected ExtraordinaryEvents extraordinaryEvents;

    /**
     * Obtient la valeur de la propriété dividendConditions.
     * 
     * @return
     *     possible object is
     *     {@link DividendConditions }
     *     
     */
    public DividendConditions getDividendConditions() {
        return dividendConditions;
    }

    /**
     * Définit la valeur de la propriété dividendConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link DividendConditions }
     *     
     */
    public void setDividendConditions(DividendConditions value) {
        this.dividendConditions = value;
    }

    /**
     * Obtient la valeur de la propriété methodOfAdjustment.
     * 
     * @return
     *     possible object is
     *     {@link MethodOfAdjustmentEnum }
     *     
     */
    public MethodOfAdjustmentEnum getMethodOfAdjustment() {
        return methodOfAdjustment;
    }

    /**
     * Définit la valeur de la propriété methodOfAdjustment.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodOfAdjustmentEnum }
     *     
     */
    public void setMethodOfAdjustment(MethodOfAdjustmentEnum value) {
        this.methodOfAdjustment = value;
    }

    /**
     * Obtient la valeur de la propriété extraordinaryEvents.
     * 
     * @return
     *     possible object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public ExtraordinaryEvents getExtraordinaryEvents() {
        return extraordinaryEvents;
    }

    /**
     * Définit la valeur de la propriété extraordinaryEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public void setExtraordinaryEvents(ExtraordinaryEvents value) {
        this.extraordinaryEvents = value;
    }

}
