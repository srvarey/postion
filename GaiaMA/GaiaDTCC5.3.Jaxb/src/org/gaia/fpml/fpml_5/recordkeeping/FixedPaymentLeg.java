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
 * Fixed Payment Leg of a Dividend Swap.
 * 
 * <p>Classe Java pour FixedPaymentLeg complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FixedPaymentLeg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLeg">
 *       &lt;sequence>
 *         &lt;element name="fixedPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}FixedPaymentAmount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedPaymentLeg", propOrder = {
    "fixedPayment"
})
public class FixedPaymentLeg
    extends DirectionalLeg
{

    protected List<FixedPaymentAmount> fixedPayment;

    /**
     * Gets the value of the fixedPayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fixedPayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFixedPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FixedPaymentAmount }
     * 
     * 
     */
    public List<FixedPaymentAmount> getFixedPayment() {
        if (fixedPayment == null) {
            fixedPayment = new ArrayList<FixedPaymentAmount>();
        }
        return this.fixedPayment;
    }

}
