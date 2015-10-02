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
 * A type describing the underlyer features of a basket swap. Each of the basket constituents are described through an embedded component, the basketConstituentsType.
 * 
 * <p>Classe Java pour Basket complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Basket">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Asset">
 *       &lt;sequence>
 *         &lt;element name="openUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="basketConstituent" type="{http://www.fpml.org/FpML-5/recordkeeping}BasketConstituent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="basketDivisor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BasketIdentifier.model" minOccurs="0"/>
 *         &lt;element name="basketCurrency" type="{http://www.fpml.org/FpML-5/recordkeeping}Currency" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Basket", propOrder = {
    "openUnits",
    "basketConstituent",
    "basketDivisor",
    "basketName",
    "basketId",
    "basketCurrency"
})
public class Basket
    extends Asset
{

    protected BigDecimal openUnits;
    protected List<BasketConstituent> basketConstituent;
    protected BigDecimal basketDivisor;
    protected BasketName basketName;
    protected List<BasketId> basketId;
    protected Currency basketCurrency;

    /**
     * Obtient la valeur de la propriété openUnits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOpenUnits() {
        return openUnits;
    }

    /**
     * Définit la valeur de la propriété openUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOpenUnits(BigDecimal value) {
        this.openUnits = value;
    }

    /**
     * Gets the value of the basketConstituent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basketConstituent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasketConstituent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasketConstituent }
     * 
     * 
     */
    public List<BasketConstituent> getBasketConstituent() {
        if (basketConstituent == null) {
            basketConstituent = new ArrayList<BasketConstituent>();
        }
        return this.basketConstituent;
    }

    /**
     * Obtient la valeur de la propriété basketDivisor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBasketDivisor() {
        return basketDivisor;
    }

    /**
     * Définit la valeur de la propriété basketDivisor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBasketDivisor(BigDecimal value) {
        this.basketDivisor = value;
    }

    /**
     * Obtient la valeur de la propriété basketName.
     * 
     * @return
     *     possible object is
     *     {@link BasketName }
     *     
     */
    public BasketName getBasketName() {
        return basketName;
    }

    /**
     * Définit la valeur de la propriété basketName.
     * 
     * @param value
     *     allowed object is
     *     {@link BasketName }
     *     
     */
    public void setBasketName(BasketName value) {
        this.basketName = value;
    }

    /**
     * Gets the value of the basketId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basketId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasketId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasketId }
     * 
     * 
     */
    public List<BasketId> getBasketId() {
        if (basketId == null) {
            basketId = new ArrayList<BasketId>();
        }
        return this.basketId;
    }

    /**
     * Obtient la valeur de la propriété basketCurrency.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getBasketCurrency() {
        return basketCurrency;
    }

    /**
     * Définit la valeur de la propriété basketCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setBasketCurrency(Currency value) {
        this.basketCurrency = value;
    }

}
