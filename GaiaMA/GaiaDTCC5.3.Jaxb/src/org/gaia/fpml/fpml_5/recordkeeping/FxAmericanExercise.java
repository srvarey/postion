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
 * Describes the characteristics for american exercise of FX products.
 * 
 * <p>Classe Java pour FxAmericanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxAmericanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}FxDigitalAmericanExercise">
 *       &lt;sequence>
 *         &lt;element name="multipleExercise" type="{http://www.fpml.org/FpML-5/recordkeeping}FxMultipleExercise" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxAmericanExercise", propOrder = {
    "multipleExercise"
})
public class FxAmericanExercise
    extends FxDigitalAmericanExercise
{

    protected FxMultipleExercise multipleExercise;

    /**
     * Obtient la valeur de la propriété multipleExercise.
     * 
     * @return
     *     possible object is
     *     {@link FxMultipleExercise }
     *     
     */
    public FxMultipleExercise getMultipleExercise() {
        return multipleExercise;
    }

    /**
     * Définit la valeur de la propriété multipleExercise.
     * 
     * @param value
     *     allowed object is
     *     {@link FxMultipleExercise }
     *     
     */
    public void setMultipleExercise(FxMultipleExercise value) {
        this.multipleExercise = value;
    }

}
