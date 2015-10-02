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
 * A type defining the ISDA calculation agent responsible for performing duties as defined in the applicable product definitions.
 * 
 * <p>Classe Java pour CalculationAgent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationAgent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="calculationAgentPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calculationAgentParty" type="{http://www.fpml.org/FpML-5/recordkeeping}CalculationAgentPartyEnum" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationAgent", propOrder = {
    "calculationAgentPartyReference",
    "calculationAgentParty"
})
public class CalculationAgent {

    protected List<PartyReference> calculationAgentPartyReference;
    protected CalculationAgentPartyEnum calculationAgentParty;

    /**
     * Gets the value of the calculationAgentPartyReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calculationAgentPartyReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalculationAgentPartyReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getCalculationAgentPartyReference() {
        if (calculationAgentPartyReference == null) {
            calculationAgentPartyReference = new ArrayList<PartyReference>();
        }
        return this.calculationAgentPartyReference;
    }

    /**
     * Obtient la valeur de la propriété calculationAgentParty.
     * 
     * @return
     *     possible object is
     *     {@link CalculationAgentPartyEnum }
     *     
     */
    public CalculationAgentPartyEnum getCalculationAgentParty() {
        return calculationAgentParty;
    }

    /**
     * Définit la valeur de la propriété calculationAgentParty.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationAgentPartyEnum }
     *     
     */
    public void setCalculationAgentParty(CalculationAgentPartyEnum value) {
        this.calculationAgentParty = value;
    }

}
