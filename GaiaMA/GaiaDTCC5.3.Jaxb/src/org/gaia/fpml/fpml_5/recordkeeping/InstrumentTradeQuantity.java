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
 * A structure describing the amount of an instrument that was traded.
 * 
 * <p>Classe Java pour InstrumentTradeQuantity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InstrumentTradeQuantity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="nominal" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstrumentTradeQuantity", propOrder = {
    "number",
    "nominal"
})
public class InstrumentTradeQuantity {

    protected BigDecimal number;
    protected Money nominal;

    /**
     * Obtient la valeur de la propriété number.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumber() {
        return number;
    }

    /**
     * Définit la valeur de la propriété number.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumber(BigDecimal value) {
        this.number = value;
    }

    /**
     * Obtient la valeur de la propriété nominal.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getNominal() {
        return nominal;
    }

    /**
     * Définit la valeur de la propriété nominal.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setNominal(Money value) {
        this.nominal = value;
    }

}
