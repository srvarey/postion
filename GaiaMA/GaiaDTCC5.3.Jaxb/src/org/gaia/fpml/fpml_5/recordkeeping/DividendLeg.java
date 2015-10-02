//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Floating Payment Leg of a Dividend Swap.
 * 
 * <p>Classe Java pour DividendLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLegUnderlyer">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}DeclaredCashAndCashEquivalentDividendPercentage.model"/>
 *         &lt;element name="dividendPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}DividendPeriodPayment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specialDividends" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="materialDividend" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendLeg", propOrder = {
    "declaredCashDividendPercentage",
    "declaredCashEquivalentDividendPercentage",
    "dividendPeriod",
    "specialDividends",
    "materialDividend"
})
public class DividendLeg
    extends DirectionalLegUnderlyer
{

    protected BigDecimal declaredCashDividendPercentage;
    protected BigDecimal declaredCashEquivalentDividendPercentage;
    protected List<DividendPeriodPayment> dividendPeriod;
    protected Boolean specialDividends;
    protected Boolean materialDividend;

    /**
     * Obtient la valeur de la propriété declaredCashDividendPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclaredCashDividendPercentage() {
        return declaredCashDividendPercentage;
    }

    /**
     * Définit la valeur de la propriété declaredCashDividendPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclaredCashDividendPercentage(BigDecimal value) {
        this.declaredCashDividendPercentage = value;
    }

    /**
     * Obtient la valeur de la propriété declaredCashEquivalentDividendPercentage.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclaredCashEquivalentDividendPercentage() {
        return declaredCashEquivalentDividendPercentage;
    }

    /**
     * Définit la valeur de la propriété declaredCashEquivalentDividendPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclaredCashEquivalentDividendPercentage(BigDecimal value) {
        this.declaredCashEquivalentDividendPercentage = value;
    }

    /**
     * Gets the value of the dividendPeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dividendPeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDividendPeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DividendPeriodPayment }
     * 
     * 
     */
    public List<DividendPeriodPayment> getDividendPeriod() {
        if (dividendPeriod == null) {
            dividendPeriod = new ArrayList<DividendPeriodPayment>();
        }
        return this.dividendPeriod;
    }

    /**
     * Obtient la valeur de la propriété specialDividends.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSpecialDividends() {
        return specialDividends;
    }

    /**
     * Définit la valeur de la propriété specialDividends.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSpecialDividends(Boolean value) {
        this.specialDividends = value;
    }

    /**
     * Obtient la valeur de la propriété materialDividend.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMaterialDividend() {
        return materialDividend;
    }

    /**
     * Définit la valeur de la propriété materialDividend.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMaterialDividend(Boolean value) {
        this.materialDividend = value;
    }

}