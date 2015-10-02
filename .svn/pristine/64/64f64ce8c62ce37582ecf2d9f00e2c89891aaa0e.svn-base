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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type defining an early termination provision for a swap. This early termination is at fair value, i.e. on termination the fair value of the product must be settled between the parties.
 * 
 * <p>Classe Java pour EarlyTerminationProvision complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EarlyTerminationProvision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}MandatoryEarlyTermination.model"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OptionalEarlyTermination.model" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EarlyTerminationProvision", propOrder = {
    "mandatoryEarlyTerminationDateTenor",
    "mandatoryEarlyTermination",
    "optionalEarlyTerminationParameters",
    "optionalEarlyTermination"
})
public class EarlyTerminationProvision {

    protected Period mandatoryEarlyTerminationDateTenor;
    protected MandatoryEarlyTermination mandatoryEarlyTermination;
    protected ExercisePeriod optionalEarlyTerminationParameters;
    protected OptionalEarlyTermination optionalEarlyTermination;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété mandatoryEarlyTerminationDateTenor.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getMandatoryEarlyTerminationDateTenor() {
        return mandatoryEarlyTerminationDateTenor;
    }

    /**
     * Définit la valeur de la propriété mandatoryEarlyTerminationDateTenor.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setMandatoryEarlyTerminationDateTenor(Period value) {
        this.mandatoryEarlyTerminationDateTenor = value;
    }

    /**
     * Obtient la valeur de la propriété mandatoryEarlyTermination.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryEarlyTermination }
     *     
     */
    public MandatoryEarlyTermination getMandatoryEarlyTermination() {
        return mandatoryEarlyTermination;
    }

    /**
     * Définit la valeur de la propriété mandatoryEarlyTermination.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryEarlyTermination }
     *     
     */
    public void setMandatoryEarlyTermination(MandatoryEarlyTermination value) {
        this.mandatoryEarlyTermination = value;
    }

    /**
     * Obtient la valeur de la propriété optionalEarlyTerminationParameters.
     * 
     * @return
     *     possible object is
     *     {@link ExercisePeriod }
     *     
     */
    public ExercisePeriod getOptionalEarlyTerminationParameters() {
        return optionalEarlyTerminationParameters;
    }

    /**
     * Définit la valeur de la propriété optionalEarlyTerminationParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link ExercisePeriod }
     *     
     */
    public void setOptionalEarlyTerminationParameters(ExercisePeriod value) {
        this.optionalEarlyTerminationParameters = value;
    }

    /**
     * Obtient la valeur de la propriété optionalEarlyTermination.
     * 
     * @return
     *     possible object is
     *     {@link OptionalEarlyTermination }
     *     
     */
    public OptionalEarlyTermination getOptionalEarlyTermination() {
        return optionalEarlyTermination;
    }

    /**
     * Définit la valeur de la propriété optionalEarlyTermination.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionalEarlyTermination }
     *     
     */
    public void setOptionalEarlyTermination(OptionalEarlyTermination value) {
        this.optionalEarlyTermination = value;
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
