//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Specifies the notional of return type swap. When used in the equity leg, the definition will typically combine the actual amount (using the notional component defined by the FpML industry group) and the determination method. When used in the interest leg, the definition will typically point to the definition of the equity leg.
 * 
 * <p>Classe Java pour ReturnSwapNotional complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReturnSwapNotional">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="relativeNotionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapNotionalAmountReference" minOccurs="0"/>
 *         &lt;element name="relativeDeterminationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}DeterminationMethodReference" minOccurs="0"/>
 *         &lt;element name="determinationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}DeterminationMethod" minOccurs="0"/>
 *         &lt;element name="notionalAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalAmount" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSwapNotional", propOrder = {
    "relativeNotionalAmount",
    "relativeDeterminationMethod",
    "determinationMethod",
    "notionalAmount"
})
public class ReturnSwapNotional {

    protected ReturnSwapNotionalAmountReference relativeNotionalAmount;
    protected DeterminationMethodReference relativeDeterminationMethod;
    protected DeterminationMethod determinationMethod;
    protected NotionalAmount notionalAmount;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété relativeNotionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSwapNotionalAmountReference }
     *     
     */
    public ReturnSwapNotionalAmountReference getRelativeNotionalAmount() {
        return relativeNotionalAmount;
    }

    /**
     * Définit la valeur de la propriété relativeNotionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSwapNotionalAmountReference }
     *     
     */
    public void setRelativeNotionalAmount(ReturnSwapNotionalAmountReference value) {
        this.relativeNotionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété relativeDeterminationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethodReference }
     *     
     */
    public DeterminationMethodReference getRelativeDeterminationMethod() {
        return relativeDeterminationMethod;
    }

    /**
     * Définit la valeur de la propriété relativeDeterminationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethodReference }
     *     
     */
    public void setRelativeDeterminationMethod(DeterminationMethodReference value) {
        this.relativeDeterminationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété determinationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DeterminationMethod }
     *     
     */
    public DeterminationMethod getDeterminationMethod() {
        return determinationMethod;
    }

    /**
     * Définit la valeur de la propriété determinationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeterminationMethod }
     *     
     */
    public void setDeterminationMethod(DeterminationMethod value) {
        this.determinationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété notionalAmount.
     * 
     * @return
     *     possible object is
     *     {@link NotionalAmount }
     *     
     */
    public NotionalAmount getNotionalAmount() {
        return notionalAmount;
    }

    /**
     * Définit la valeur de la propriété notionalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalAmount }
     *     
     */
    public void setNotionalAmount(NotionalAmount value) {
        this.notionalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
