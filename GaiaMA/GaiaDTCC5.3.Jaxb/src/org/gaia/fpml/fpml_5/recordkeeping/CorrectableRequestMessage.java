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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the content model for a request message that can be subsequently corrected or retracted.
 * 
 * <p>Classe Java pour CorrectableRequestMessage complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CorrectableRequestMessage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}RequestMessage">
 *       &lt;sequence>
 *         &lt;element name="isCorrection" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CorrelationAndSequence.model" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OnBehalfOf.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectableRequestMessage", propOrder = {
    "isCorrection",
    "parentCorrelationId",
    "correlationId",
    "sequenceNumber",
    "onBehalfOf"
})
@XmlSeeAlso({
    NonpublicExecutionReport.class,
    CreditEventNotification.class,
    RequestValuationReport.class
})
public class CorrectableRequestMessage
    extends RequestMessage
{

    protected Boolean isCorrection;
    protected CorrelationId parentCorrelationId;
    protected List<CorrelationId> correlationId;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceNumber;
    protected List<OnBehalfOf> onBehalfOf;

    /**
     * Obtient la valeur de la propriété isCorrection.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCorrection() {
        return isCorrection;
    }

    /**
     * Définit la valeur de la propriété isCorrection.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCorrection(Boolean value) {
        this.isCorrection = value;
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
     * Gets the value of the onBehalfOf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onBehalfOf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnBehalfOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OnBehalfOf }
     * 
     * 
     */
    public List<OnBehalfOf> getOnBehalfOf() {
        if (onBehalfOf == null) {
            onBehalfOf = new ArrayList<OnBehalfOf>();
        }
        return this.onBehalfOf;
    }

}
