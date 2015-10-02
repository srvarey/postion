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
 * A type that is used for describing cash settlement of an option / non deliverable forward. It includes the currency to settle into together with the fixings required to calculate the currency amount.
 * 
 * <p>Classe Java pour FxCashSettlement complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxCashSettlement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settlementCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *         &lt;element name="fixing" type="{http://www.fpml.org/FpML-5/recordkeeping}FxFixing" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxCashSettlement", propOrder = {
    "settlementCurrency",
    "fixing"
})
public class FxCashSettlement {

    protected Currency settlementCurrency;
    protected List<FxFixing> fixing;

    /**
     * Obtient la valeur de la propriété settlementCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Définit la valeur de la propriété settlementCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setSettlementCurrency(Currency value) {
        this.settlementCurrency = value;
    }

    /**
     * Gets the value of the fixing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fixing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFixing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxFixing }
     * 
     * 
     */
    public List<FxFixing> getFixing() {
        if (fixing == null) {
            fixing = new ArrayList<FxFixing>();
        }
        return this.fixing;
    }

}
