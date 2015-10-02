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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Simple product representation providing key information about a variety of different products
 * 
 * <p>Classe Java pour StandardProduct complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StandardProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="notional" type="{http://www.fpml.org/FpML-5/recordkeeping}CashflowNotional"/>
 *         &lt;element name="quote" type="{http://www.fpml.org/FpML-5/recordkeeping}BasicQuotation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandardProduct", propOrder = {
    "notional",
    "quote"
})
public class StandardProduct
    extends Product
{

    @XmlElement(required = true)
    protected CashflowNotional notional;
    @XmlElement(required = true)
    protected List<BasicQuotation> quote;

    /**
     * Obtient la valeur de la propriété notional.
     * 
     * @return
     *     possible object is
     *     {@link CashflowNotional }
     *     
     */
    public CashflowNotional getNotional() {
        return notional;
    }

    /**
     * Définit la valeur de la propriété notional.
     * 
     * @param value
     *     allowed object is
     *     {@link CashflowNotional }
     *     
     */
    public void setNotional(CashflowNotional value) {
        this.notional = value;
    }

    /**
     * Gets the value of the quote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasicQuotation }
     * 
     * 
     */
    public List<BasicQuotation> getQuote() {
        if (quote == null) {
            quote = new ArrayList<BasicQuotation>();
        }
        return this.quote;
    }

}
