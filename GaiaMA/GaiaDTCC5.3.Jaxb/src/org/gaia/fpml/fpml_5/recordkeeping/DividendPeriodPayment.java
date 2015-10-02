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
 * A time bounded dividend period, with fixed strike and a dividend payment date per period.
 * 
 * <p>Classe Java pour DividendPeriodPayment complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendPeriodPayment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DividendPeriod">
 *       &lt;sequence>
 *         &lt;element name="fixedStrike" type="{http://www.fpml.org/FpML-5/recordkeeping}PositiveDecimal" minOccurs="0"/>
 *         &lt;element name="paymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="valuationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendPeriodPayment", propOrder = {
    "fixedStrike",
    "paymentDate",
    "valuationDate"
})
public class DividendPeriodPayment
    extends DividendPeriod
{

    protected BigDecimal fixedStrike;
    protected AdjustableOrRelativeDate paymentDate;
    protected AdjustableOrRelativeDate valuationDate;

    /**
     * Obtient la valeur de la propriété fixedStrike.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedStrike() {
        return fixedStrike;
    }

    /**
     * Définit la valeur de la propriété fixedStrike.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedStrike(BigDecimal value) {
        this.fixedStrike = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Définit la valeur de la propriété paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setPaymentDate(AdjustableOrRelativeDate value) {
        this.paymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété valuationDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getValuationDate() {
        return valuationDate;
    }

    /**
     * Définit la valeur de la propriété valuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setValuationDate(AdjustableOrRelativeDate value) {
        this.valuationDate = value;
    }

}
