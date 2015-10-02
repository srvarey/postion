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
 * A type describing return swaps including return swaps (long form), total return swaps, and variance swaps.
 * 
 * <p>Classe Java pour ReturnSwap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReturnSwap">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapBase">
 *       &lt;sequence>
 *         &lt;element name="additionalPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapAdditionalPayment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="earlyTermination" type="{http://www.fpml.org/FpML-5/recordkeeping}ReturnSwapEarlyTermination" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extraordinaryEvents" type="{http://www.fpml.org/FpML-5/recordkeeping}ExtraordinaryEvents" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSwap", propOrder = {
    "additionalPayment",
    "earlyTermination",
    "extraordinaryEvents"
})
public class ReturnSwap
    extends ReturnSwapBase
{

    protected List<ReturnSwapAdditionalPayment> additionalPayment;
    protected List<ReturnSwapEarlyTermination> earlyTermination;
    protected ExtraordinaryEvents extraordinaryEvents;

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
     * {@link ReturnSwapAdditionalPayment }
     * 
     * 
     */
    public List<ReturnSwapAdditionalPayment> getAdditionalPayment() {
        if (additionalPayment == null) {
            additionalPayment = new ArrayList<ReturnSwapAdditionalPayment>();
        }
        return this.additionalPayment;
    }

    /**
     * Gets the value of the earlyTermination property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the earlyTermination property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEarlyTermination().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReturnSwapEarlyTermination }
     * 
     * 
     */
    public List<ReturnSwapEarlyTermination> getEarlyTermination() {
        if (earlyTermination == null) {
            earlyTermination = new ArrayList<ReturnSwapEarlyTermination>();
        }
        return this.earlyTermination;
    }

    /**
     * Obtient la valeur de la propriété extraordinaryEvents.
     * 
     * @return
     *     possible object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public ExtraordinaryEvents getExtraordinaryEvents() {
        return extraordinaryEvents;
    }

    /**
     * Définit la valeur de la propriété extraordinaryEvents.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtraordinaryEvents }
     *     
     */
    public void setExtraordinaryEvents(ExtraordinaryEvents value) {
        this.extraordinaryEvents = value;
    }

}
