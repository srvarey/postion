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
 * Knock In means option to exercise comes into existence. Knock Out means option to exercise goes out of existence.
 * 
 * <p>Classe Java pour Knock complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Knock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="knockIn" type="{http://www.fpml.org/FpML-5/recordkeeping}TriggerEvent" minOccurs="0"/>
 *         &lt;element name="knockOut" type="{http://www.fpml.org/FpML-5/recordkeeping}TriggerEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Knock", propOrder = {
    "knockIn",
    "knockOut"
})
public class Knock {

    protected TriggerEvent knockIn;
    protected TriggerEvent knockOut;

    /**
     * Obtient la valeur de la propriété knockIn.
     * 
     * @return
     *     possible object is
     *     {@link TriggerEvent }
     *     
     */
    public TriggerEvent getKnockIn() {
        return knockIn;
    }

    /**
     * Définit la valeur de la propriété knockIn.
     * 
     * @param value
     *     allowed object is
     *     {@link TriggerEvent }
     *     
     */
    public void setKnockIn(TriggerEvent value) {
        this.knockIn = value;
    }

    /**
     * Obtient la valeur de la propriété knockOut.
     * 
     * @return
     *     possible object is
     *     {@link TriggerEvent }
     *     
     */
    public TriggerEvent getKnockOut() {
        return knockOut;
    }

    /**
     * Définit la valeur de la propriété knockOut.
     * 
     * @param value
     *     allowed object is
     *     {@link TriggerEvent }
     *     
     */
    public void setKnockOut(TriggerEvent value) {
        this.knockOut = value;
    }

}
