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
 * A type describing the Stub Calculation Period.
 * 
 * <p>Classe Java pour StubCalculationPeriod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StubCalculationPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="initialStub" type="{http://www.fpml.org/FpML-5/recordkeeping}Stub" minOccurs="0"/>
 *           &lt;element name="finalStub" type="{http://www.fpml.org/FpML-5/recordkeeping}Stub" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StubCalculationPeriod", propOrder = {
    "initialStub",
    "finalStub"
})
public class StubCalculationPeriod {

    protected Stub initialStub;
    protected Stub finalStub;

    /**
     * Obtient la valeur de la propriété initialStub.
     * 
     * @return
     *     possible object is
     *     {@link Stub }
     *     
     */
    public Stub getInitialStub() {
        return initialStub;
    }

    /**
     * Définit la valeur de la propriété initialStub.
     * 
     * @param value
     *     allowed object is
     *     {@link Stub }
     *     
     */
    public void setInitialStub(Stub value) {
        this.initialStub = value;
    }

    /**
     * Obtient la valeur de la propriété finalStub.
     * 
     * @return
     *     possible object is
     *     {@link Stub }
     *     
     */
    public Stub getFinalStub() {
        return finalStub;
    }

    /**
     * Définit la valeur de la propriété finalStub.
     * 
     * @param value
     *     allowed object is
     *     {@link Stub }
     *     
     */
    public void setFinalStub(Stub value) {
        this.finalStub = value;
    }

}
