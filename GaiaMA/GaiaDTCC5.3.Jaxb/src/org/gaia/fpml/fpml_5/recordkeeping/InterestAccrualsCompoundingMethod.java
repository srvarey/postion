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
 * A type defining the way in which interests are accrued: the applicable rate (fixed or floating reference) and the compounding method.
 * 
 * <p>Classe Java pour InterestAccrualsCompoundingMethod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InterestAccrualsCompoundingMethod">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}InterestAccrualsMethod">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="compoundingMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}CompoundingMethodEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestAccrualsCompoundingMethod", propOrder = {
    "compoundingMethod"
})
public class InterestAccrualsCompoundingMethod
    extends InterestAccrualsMethod
{

    protected CompoundingMethodEnum compoundingMethod;

    /**
     * Obtient la valeur de la propriété compoundingMethod.
     * 
     * @return
     *     possible object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public CompoundingMethodEnum getCompoundingMethod() {
        return compoundingMethod;
    }

    /**
     * Définit la valeur de la propriété compoundingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundingMethodEnum }
     *     
     */
    public void setCompoundingMethod(CompoundingMethodEnum value) {
        this.compoundingMethod = value;
    }

}
