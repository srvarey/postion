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
 * A type defining swap streams and additional payments between the principal parties involved in the swap.
 * 
 * <p>Classe Java pour Swap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Swap">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="swapStream" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestRateStream" maxOccurs="unbounded"/>
 *         &lt;element name="earlyTerminationProvision" type="{http://www.fpml.org/FpML-5/recordkeeping}EarlyTerminationProvision" minOccurs="0"/>
 *         &lt;element name="cancelableProvision" type="{http://www.fpml.org/FpML-5/recordkeeping}CancelableProvision" minOccurs="0"/>
 *         &lt;element name="extendibleProvision" type="{http://www.fpml.org/FpML-5/recordkeeping}ExtendibleProvision" minOccurs="0"/>
 *         &lt;element name="additionalPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="additionalTerms" type="{http://www.fpml.org/FpML-5/recordkeeping}SwapAdditionalTerms" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Swap", propOrder = {
    "swapStream",
    "earlyTerminationProvision",
    "cancelableProvision",
    "extendibleProvision",
    "additionalPayment",
    "additionalTerms"
})
public class Swap
    extends Product
{

    @XmlElement(required = true)
    protected List<InterestRateStream> swapStream;
    protected EarlyTerminationProvision earlyTerminationProvision;
    protected CancelableProvision cancelableProvision;
    protected ExtendibleProvision extendibleProvision;
    protected List<Payment> additionalPayment;
    protected SwapAdditionalTerms additionalTerms;

    /**
     * Gets the value of the swapStream property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the swapStream property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSwapStream().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InterestRateStream }
     * 
     * 
     */
    public List<InterestRateStream> getSwapStream() {
        if (swapStream == null) {
            swapStream = new ArrayList<InterestRateStream>();
        }
        return this.swapStream;
    }

    /**
     * Obtient la valeur de la propriété earlyTerminationProvision.
     * 
     * @return
     *     possible object is
     *     {@link EarlyTerminationProvision }
     *     
     */
    public EarlyTerminationProvision getEarlyTerminationProvision() {
        return earlyTerminationProvision;
    }

    /**
     * Définit la valeur de la propriété earlyTerminationProvision.
     * 
     * @param value
     *     allowed object is
     *     {@link EarlyTerminationProvision }
     *     
     */
    public void setEarlyTerminationProvision(EarlyTerminationProvision value) {
        this.earlyTerminationProvision = value;
    }

    /**
     * Obtient la valeur de la propriété cancelableProvision.
     * 
     * @return
     *     possible object is
     *     {@link CancelableProvision }
     *     
     */
    public CancelableProvision getCancelableProvision() {
        return cancelableProvision;
    }

    /**
     * Définit la valeur de la propriété cancelableProvision.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelableProvision }
     *     
     */
    public void setCancelableProvision(CancelableProvision value) {
        this.cancelableProvision = value;
    }

    /**
     * Obtient la valeur de la propriété extendibleProvision.
     * 
     * @return
     *     possible object is
     *     {@link ExtendibleProvision }
     *     
     */
    public ExtendibleProvision getExtendibleProvision() {
        return extendibleProvision;
    }

    /**
     * Définit la valeur de la propriété extendibleProvision.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendibleProvision }
     *     
     */
    public void setExtendibleProvision(ExtendibleProvision value) {
        this.extendibleProvision = value;
    }

    /**
     * Gets the value of the additionalPayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalPayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getAdditionalPayment() {
        if (additionalPayment == null) {
            additionalPayment = new ArrayList<Payment>();
        }
        return this.additionalPayment;
    }

    /**
     * Obtient la valeur de la propriété additionalTerms.
     * 
     * @return
     *     possible object is
     *     {@link SwapAdditionalTerms }
     *     
     */
    public SwapAdditionalTerms getAdditionalTerms() {
        return additionalTerms;
    }

    /**
     * Définit la valeur de la propriété additionalTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link SwapAdditionalTerms }
     *     
     */
    public void setAdditionalTerms(SwapAdditionalTerms value) {
        this.additionalTerms = value;
    }

}
