//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the basic content for a message sent to inform another system that some exception has been detected.
 * 
 * <p>Classe Java pour Exception complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Exception">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Message">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://www.fpml.org/FpML-5/recordkeeping}ExceptionMessageHeader" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Validation.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CorrelationAndOptionalSequence.model" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Exception.model"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exception", propOrder = {
    "header",
    "validation",
    "parentCorrelationId",
    "correlationId",
    "sequenceNumber",
    "reason",
    "additionalData"
})
public class Exception
    extends Message
{

    protected ExceptionMessageHeader header;
    protected List<Validation> validation;
    protected CorrelationId parentCorrelationId;
    protected List<CorrelationId> correlationId;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceNumber;
    protected List<Reason> reason;
    protected AdditionalData additionalData;

    /**
     * Obtient la valeur de la propriété header.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionMessageHeader }
     *     
     */
    public ExceptionMessageHeader getHeader() {
        return header;
    }

    /**
     * Définit la valeur de la propriété header.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionMessageHeader }
     *     
     */
    public void setHeader(ExceptionMessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the validation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Validation }
     * 
     * 
     */
    public List<Validation> getValidation() {
        if (validation == null) {
            validation = new ArrayList<Validation>();
        }
        return this.validation;
    }

    /**
     * Obtient la valeur de la propriété parentCorrelationId.
     * 
     * @return
     *     possible object is
     *     {@link CorrelationId }
     *     
     */
    public CorrelationId getParentCorrelationId() {
        return parentCorrelationId;
    }

    /**
     * Définit la valeur de la propriété parentCorrelationId.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrelationId }
     *     
     */
    public void setParentCorrelationId(CorrelationId value) {
        this.parentCorrelationId = value;
    }

    /**
     * Gets the value of the correlationId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correlationId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorrelationId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorrelationId }
     * 
     * 
     */
    public List<CorrelationId> getCorrelationId() {
        if (correlationId == null) {
            correlationId = new ArrayList<CorrelationId>();
        }
        return this.correlationId;
    }

    /**
     * Obtient la valeur de la propriété sequenceNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Définit la valeur de la propriété sequenceNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequenceNumber(BigInteger value) {
        this.sequenceNumber = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reason }
     * 
     * 
     */
    public List<Reason> getReason() {
        if (reason == null) {
            reason = new ArrayList<Reason>();
        }
        return this.reason;
    }

    /**
     * Obtient la valeur de la propriété additionalData.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalData }
     *     
     */
    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    /**
     * Définit la valeur de la propriété additionalData.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalData }
     *     
     */
    public void setAdditionalData(AdditionalData value) {
        this.additionalData = value;
    }

}
