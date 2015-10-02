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
 * A type for defining the obligations of the counterparty subject to credit support requirements.
 * 
 * <p>Classe Java pour Collateral complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Collateral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="independentAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}IndependentAmount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Collateral", propOrder = {
    "independentAmount"
})
public class Collateral {

    protected IndependentAmount independentAmount;

    /**
     * Obtient la valeur de la propriété independentAmount.
     * 
     * @return
     *     possible object is
     *     {@link IndependentAmount }
     *     
     */
    public IndependentAmount getIndependentAmount() {
        return independentAmount;
    }

    /**
     * Définit la valeur de la propriété independentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link IndependentAmount }
     *     
     */
    public void setIndependentAmount(IndependentAmount value) {
        this.independentAmount = value;
    }

}
