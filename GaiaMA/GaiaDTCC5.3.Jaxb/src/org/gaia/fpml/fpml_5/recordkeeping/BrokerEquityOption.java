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
 * A type for defining the broker equity options.
 * 
 * <p>Classe Java pour BrokerEquityOption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BrokerEquityOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}EquityDerivativeShortFormBase">
 *       &lt;sequence>
 *         &lt;element name="deltaCrossed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="brokerageFee" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *         &lt;element name="brokerNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrokerEquityOption", propOrder = {
    "deltaCrossed",
    "brokerageFee",
    "brokerNotes"
})
public class BrokerEquityOption
    extends EquityDerivativeShortFormBase
{

    protected Boolean deltaCrossed;
    protected Money brokerageFee;
    protected String brokerNotes;

    /**
     * Obtient la valeur de la propriété deltaCrossed.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeltaCrossed() {
        return deltaCrossed;
    }

    /**
     * Définit la valeur de la propriété deltaCrossed.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeltaCrossed(Boolean value) {
        this.deltaCrossed = value;
    }

    /**
     * Obtient la valeur de la propriété brokerageFee.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getBrokerageFee() {
        return brokerageFee;
    }

    /**
     * Définit la valeur de la propriété brokerageFee.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setBrokerageFee(Money value) {
        this.brokerageFee = value;
    }

    /**
     * Obtient la valeur de la propriété brokerNotes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerNotes() {
        return brokerNotes;
    }

    /**
     * Définit la valeur de la propriété brokerNotes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerNotes(String value) {
        this.brokerNotes = value;
    }

}
