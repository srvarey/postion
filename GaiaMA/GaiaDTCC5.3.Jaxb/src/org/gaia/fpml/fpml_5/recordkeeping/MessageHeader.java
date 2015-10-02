//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type defining the content model for a generic message header that is refined by its derived classes.
 * 
 * <p>Classe Java pour MessageHeader complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MessageHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageId" type="{http://www.fpml.org/FpML-5/recordkeeping}MessageId" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeader", propOrder = {
    "messageId"
})
@XmlSeeAlso({
    ExceptionMessageHeader.class,
    NotificationMessageHeader.class,
    ResponseMessageHeader.class,
    RequestMessageHeader.class
})
public abstract class MessageHeader {

    protected MessageId messageId;

    /**
     * Obtient la valeur de la propriété messageId.
     * 
     * @return
     *     possible object is
     *     {@link MessageId }
     *     
     */
    public MessageId getMessageId() {
        return messageId;
    }

    /**
     * Définit la valeur de la propriété messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageId }
     *     
     */
    public void setMessageId(MessageId value) {
        this.messageId = value;
    }

}
