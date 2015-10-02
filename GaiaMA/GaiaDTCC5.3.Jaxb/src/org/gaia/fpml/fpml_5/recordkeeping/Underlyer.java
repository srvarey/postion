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
 * A type describing the whole set of possible underlyers: single underlyers or multiple underlyers, each of these having either security or index components.
 * 
 * <p>Classe Java pour Underlyer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Underlyer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="singleUnderlyer" type="{http://www.fpml.org/FpML-5/recordkeeping}SingleUnderlyer" minOccurs="0"/>
 *         &lt;element name="basket" type="{http://www.fpml.org/FpML-5/recordkeeping}Basket" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Underlyer", propOrder = {
    "singleUnderlyer",
    "basket"
})
public class Underlyer {

    protected SingleUnderlyer singleUnderlyer;
    protected Basket basket;

    /**
     * Obtient la valeur de la propriété singleUnderlyer.
     * 
     * @return
     *     possible object is
     *     {@link SingleUnderlyer }
     *     
     */
    public SingleUnderlyer getSingleUnderlyer() {
        return singleUnderlyer;
    }

    /**
     * Définit la valeur de la propriété singleUnderlyer.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleUnderlyer }
     *     
     */
    public void setSingleUnderlyer(SingleUnderlyer value) {
        this.singleUnderlyer = value;
    }

    /**
     * Obtient la valeur de la propriété basket.
     * 
     * @return
     *     possible object is
     *     {@link Basket }
     *     
     */
    public Basket getBasket() {
        return basket;
    }

    /**
     * Définit la valeur de la propriété basket.
     * 
     * @param value
     *     allowed object is
     *     {@link Basket }
     *     
     */
    public void setBasket(Basket value) {
        this.basket = value;
    }

}
