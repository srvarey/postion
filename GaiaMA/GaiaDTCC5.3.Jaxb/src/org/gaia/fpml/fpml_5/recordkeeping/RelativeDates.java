//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing a set of dates defined as relative to another set of dates.
 * 
 * <p>Classe Java pour RelativeDates complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RelativeDates">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset">
 *       &lt;sequence>
 *         &lt;element name="periodSkip" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="scheduleBounds" type="{http://www.fpml.org/FpML-5/recordkeeping}DateRange" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelativeDates", propOrder = {
    "periodSkip",
    "scheduleBounds"
})
public class RelativeDates
    extends RelativeDateOffset
{

    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger periodSkip;
    protected DateRange scheduleBounds;

    /**
     * Obtient la valeur de la propriété periodSkip.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPeriodSkip() {
        return periodSkip;
    }

    /**
     * Définit la valeur de la propriété periodSkip.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPeriodSkip(BigInteger value) {
        this.periodSkip = value;
    }

    /**
     * Obtient la valeur de la propriété scheduleBounds.
     * 
     * @return
     *     possible object is
     *     {@link DateRange }
     *     
     */
    public DateRange getScheduleBounds() {
        return scheduleBounds;
    }

    /**
     * Définit la valeur de la propriété scheduleBounds.
     * 
     * @param value
     *     allowed object is
     *     {@link DateRange }
     *     
     */
    public void setScheduleBounds(DateRange value) {
        this.scheduleBounds = value;
    }

}
