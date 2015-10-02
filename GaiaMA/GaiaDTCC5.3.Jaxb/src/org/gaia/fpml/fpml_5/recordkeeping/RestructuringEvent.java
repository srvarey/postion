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
 * <p>Classe Java pour RestructuringEvent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RestructuringEvent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}CreditEvent">
 *       &lt;sequence>
 *         &lt;element name="partialExerciseAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestructuringEvent", propOrder = {
    "partialExerciseAmount"
})
public class RestructuringEvent
    extends CreditEvent
{

    protected Money partialExerciseAmount;

    /**
     * Obtient la valeur de la propriété partialExerciseAmount.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getPartialExerciseAmount() {
        return partialExerciseAmount;
    }

    /**
     * Définit la valeur de la propriété partialExerciseAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setPartialExerciseAmount(Money value) {
        this.partialExerciseAmount = value;
    }

}
