//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the components that are common for return type swaps, including short and long form return swaps representations.
 * 
 * <p>Classe Java pour ReturnSwapBase complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReturnSwapBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BuyerSeller.model" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}returnSwapLeg" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="principalExchangeFeatures" type="{http://www.fpml.org/FpML-5/recordkeeping}PrincipalExchangeFeatures" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSwapBase", propOrder = {
    "buyerPartyReference",
    "buyerAccountReference",
    "sellerPartyReference",
    "sellerAccountReference",
    "returnSwapLeg",
    "principalExchangeFeatures"
})
@XmlSeeAlso({
    ReturnSwap.class,
    EquitySwapTransactionSupplement.class
})
public abstract class ReturnSwapBase
    extends Product
{

    protected PartyReference buyerPartyReference;
    protected AccountReference buyerAccountReference;
    protected PartyReference sellerPartyReference;
    protected AccountReference sellerAccountReference;
    @XmlElementRef(name = "returnSwapLeg", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends DirectionalLeg>> returnSwapLeg;
    protected PrincipalExchangeFeatures principalExchangeFeatures;

    /**
     * Obtient la valeur de la propriété buyerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getBuyerPartyReference() {
        return buyerPartyReference;
    }

    /**
     * Définit la valeur de la propriété buyerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setBuyerPartyReference(PartyReference value) {
        this.buyerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété buyerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getBuyerAccountReference() {
        return buyerAccountReference;
    }

    /**
     * Définit la valeur de la propriété buyerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setBuyerAccountReference(AccountReference value) {
        this.buyerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getSellerPartyReference() {
        return sellerPartyReference;
    }

    /**
     * Définit la valeur de la propriété sellerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setSellerPartyReference(PartyReference value) {
        this.sellerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété sellerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getSellerAccountReference() {
        return sellerAccountReference;
    }

    /**
     * Définit la valeur de la propriété sellerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setSellerAccountReference(AccountReference value) {
        this.sellerAccountReference = value;
    }

    /**
     * Gets the value of the returnSwapLeg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnSwapLeg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnSwapLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link InterestLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link ReturnLeg }{@code >}
     * {@link JAXBElement }{@code <}{@link DirectionalLeg }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends DirectionalLeg>> getReturnSwapLeg() {
        if (returnSwapLeg == null) {
            returnSwapLeg = new ArrayList<JAXBElement<? extends DirectionalLeg>>();
        }
        return this.returnSwapLeg;
    }

    /**
     * Obtient la valeur de la propriété principalExchangeFeatures.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalExchangeFeatures }
     *     
     */
    public PrincipalExchangeFeatures getPrincipalExchangeFeatures() {
        return principalExchangeFeatures;
    }

    /**
     * Définit la valeur de la propriété principalExchangeFeatures.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalExchangeFeatures }
     *     
     */
    public void setPrincipalExchangeFeatures(PrincipalExchangeFeatures value) {
        this.principalExchangeFeatures = value;
    }

}
