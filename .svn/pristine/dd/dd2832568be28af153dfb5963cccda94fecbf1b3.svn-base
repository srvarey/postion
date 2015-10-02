//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * An type defining the notional amount or notional amount schedule associated with a swap stream. The notional schedule will be captured explicitly, specifying the dates that the notional changes and the outstanding notional amount that applies from that date. A parametric representation of the rules defining the notional step schedule can optionally be included.
 * 
 * <p>Classe Java pour Notional complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Notional">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="notionalStepSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}NonNegativeAmountSchedule"/>
 *         &lt;element name="notionalStepParameters" type="{http://www.fpml.org/FpML-5/recordkeeping}NotionalStepRule" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Notional", propOrder = {
    "notionalStepSchedule",
    "notionalStepParameters"
})
public class Notional {

    @XmlElement(required = true)
    protected NonNegativeAmountSchedule notionalStepSchedule;
    protected NotionalStepRule notionalStepParameters;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété notionalStepSchedule.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeAmountSchedule }
     *     
     */
    public NonNegativeAmountSchedule getNotionalStepSchedule() {
        return notionalStepSchedule;
    }

    /**
     * Définit la valeur de la propriété notionalStepSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeAmountSchedule }
     *     
     */
    public void setNotionalStepSchedule(NonNegativeAmountSchedule value) {
        this.notionalStepSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété notionalStepParameters.
     * 
     * @return
     *     possible object is
     *     {@link NotionalStepRule }
     *     
     */
    public NotionalStepRule getNotionalStepParameters() {
        return notionalStepParameters;
    }

    /**
     * Définit la valeur de la propriété notionalStepParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link NotionalStepRule }
     *     
     */
    public void setNotionalStepParameters(NotionalStepRule value) {
        this.notionalStepParameters = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
