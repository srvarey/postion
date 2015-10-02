//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A base class for all return leg types with an underlyer.
 * 
 * <p>Classe Java pour ReturnSwapLegUnderlyer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReturnSwapLegUnderlyer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DirectionalLeg">
 *       &lt;sequence>
 *         &lt;element name="strikeDate" type="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableOrRelativeDate" minOccurs="0"/>
 *         &lt;element name="underlyer" type="{http://www.fpml.org/FpML-5/recordkeeping}Underlyer"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSwapLegUnderlyer", propOrder = {
    "strikeDate",
    "underlyer"
})
@XmlSeeAlso({
    ReturnLeg.class
})
public abstract class ReturnSwapLegUnderlyer
    extends DirectionalLeg
{

    protected AdjustableOrRelativeDate strikeDate;
    @XmlElement(required = true)
    protected Underlyer underlyer;

    /**
     * Obtient la valeur de la propriété strikeDate.
     * 
     * @return
     *     possible object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public AdjustableOrRelativeDate getStrikeDate() {
        return strikeDate;
    }

    /**
     * Définit la valeur de la propriété strikeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustableOrRelativeDate }
     *     
     */
    public void setStrikeDate(AdjustableOrRelativeDate value) {
        this.strikeDate = value;
    }

    /**
     * Obtient la valeur de la propriété underlyer.
     * 
     * @return
     *     possible object is
     *     {@link Underlyer }
     *     
     */
    public Underlyer getUnderlyer() {
        return underlyer;
    }

    /**
     * Définit la valeur de la propriété underlyer.
     * 
     * @param value
     *     allowed object is
     *     {@link Underlyer }
     *     
     */
    public void setUnderlyer(Underlyer value) {
        this.underlyer = value;
    }

}
