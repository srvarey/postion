//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * CDS Basket Reference Information
 * 
 * <p>Classe Java pour BasketReferenceInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BasketReferenceInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BasketIdentifier.model" minOccurs="0"/>
 *         &lt;element name="referencePool" type="{http://www.fpml.org/FpML-5/recordkeeping}ReferencePool" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;sequence>
 *             &lt;element name="nthToDefault" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *             &lt;element name="mthToDefault" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="tranche" type="{http://www.fpml.org/FpML-5/recordkeeping}Tranche" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasketReferenceInformation", propOrder = {
    "basketName",
    "basketId",
    "referencePool",
    "nthToDefault",
    "mthToDefault",
    "tranche"
})
public class BasketReferenceInformation {

    protected BasketName basketName;
    protected List<BasketId> basketId;
    protected ReferencePool referencePool;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nthToDefault;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger mthToDefault;
    protected Tranche tranche;

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
     * Obtient la valeur de la propriété referencePool.
     * 
     * @return
     *     possible object is
     *     {@link ReferencePool }
     *     
     */
    public ReferencePool getReferencePool() {
        return referencePool;
    }

    /**
     * Définit la valeur de la propriété referencePool.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferencePool }
     *     
     */
    public void setReferencePool(ReferencePool value) {
        this.referencePool = value;
    }

    /**
     * Obtient la valeur de la propriété nthToDefault.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNthToDefault() {
        return nthToDefault;
    }

    /**
     * Définit la valeur de la propriété nthToDefault.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNthToDefault(BigInteger value) {
        this.nthToDefault = value;
    }

    /**
     * Obtient la valeur de la propriété mthToDefault.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMthToDefault() {
        return mthToDefault;
    }

    /**
     * Définit la valeur de la propriété mthToDefault.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMthToDefault(BigInteger value) {
        this.mthToDefault = value;
    }

    /**
     * Obtient la valeur de la propriété tranche.
     * 
     * @return
     *     possible object is
     *     {@link Tranche }
     *     
     */
    public Tranche getTranche() {
        return tranche;
    }

    /**
     * Définit la valeur de la propriété tranche.
     * 
     * @param value
     *     allowed object is
     *     {@link Tranche }
     *     
     */
    public void setTranche(Tranche value) {
        this.tranche = value;
    }

}
