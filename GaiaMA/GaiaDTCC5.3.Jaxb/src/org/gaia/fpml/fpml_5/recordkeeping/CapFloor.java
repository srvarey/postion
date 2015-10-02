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
 * A type defining an interest rate cap, floor, or cap/floor strategy (e.g. collar) product.
 * 
 * <p>Classe Java pour CapFloor complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CapFloor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="capFloorStream" type="{http://www.fpml.org/FpML-5/recordkeeping}InterestRateStream" minOccurs="0"/>
 *         &lt;element name="premium" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="additionalPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="earlyTerminationProvision" type="{http://www.fpml.org/FpML-5/recordkeeping}EarlyTerminationProvision" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CapFloor", propOrder = {
    "capFloorStream",
    "premium",
    "additionalPayment",
    "earlyTerminationProvision"
})
public class CapFloor
    extends Product
{

    protected InterestRateStream capFloorStream;
    protected List<Payment> premium;
    protected List<Payment> additionalPayment;
    protected EarlyTerminationProvision earlyTerminationProvision;

    /**
     * Obtient la valeur de la propriété capFloorStream.
     * 
     * @return
     *     possible object is
     *     {@link InterestRateStream }
     *     
     */
    public InterestRateStream getCapFloorStream() {
        return capFloorStream;
    }

    /**
     * Définit la valeur de la propriété capFloorStream.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestRateStream }
     *     
     */
    public void setCapFloorStream(InterestRateStream value) {
        this.capFloorStream = value;
    }

    /**
     * Gets the value of the premium property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premium property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremium().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getPremium() {
        if (premium == null) {
            premium = new ArrayList<Payment>();
        }
        return this.premium;
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

}
