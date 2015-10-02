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
 * A type describing the dividend payout ratio associated with an equity underlyer. In certain cases the actual ratio is not known on trade inception, and only general conditions are then specified.
 * 
 * <p>Classe Java pour DividendPayout complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DividendPayout">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="dividendPayoutRatio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;element name="dividendPayoutConditions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="dividendPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}PendingPayment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DividendPayout", propOrder = {
    "dividendPayoutRatio",
    "dividendPayoutConditions",
    "dividendPayment"
})
public class DividendPayout {

    protected BigDecimal dividendPayoutRatio;
    protected String dividendPayoutConditions;
    protected List<PendingPayment> dividendPayment;

    /**
     * Obtient la valeur de la propriété dividendPayoutRatio.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDividendPayoutRatio() {
        return dividendPayoutRatio;
    }

    /**
     * Définit la valeur de la propriété dividendPayoutRatio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDividendPayoutRatio(BigDecimal value) {
        this.dividendPayoutRatio = value;
    }

    /**
     * Obtient la valeur de la propriété dividendPayoutConditions.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDividendPayoutConditions() {
        return dividendPayoutConditions;
    }

    /**
     * Définit la valeur de la propriété dividendPayoutConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDividendPayoutConditions(String value) {
        this.dividendPayoutConditions = value;
    }

    /**
     * Gets the value of the dividendPayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dividendPayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDividendPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PendingPayment }
     * 
     * 
     */
    public List<PendingPayment> getDividendPayment() {
        if (dividendPayment == null) {
            dividendPayment = new ArrayList<PendingPayment>();
        }
        return this.dividendPayment;
    }

}
