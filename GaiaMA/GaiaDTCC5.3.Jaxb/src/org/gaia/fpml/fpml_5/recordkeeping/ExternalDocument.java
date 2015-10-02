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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A for holding information about documents external to the FpML.
 * 
 * <p>Classe Java pour ExternalDocument complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExternalDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mimeType" type="{http://www.fpml.org/FpML-5/recordkeeping}MimeType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="string" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="hexadecimalBinary" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *           &lt;element name="base64Binary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *           &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *           &lt;element name="attachmentReference" type="{http://www.fpml.org/FpML-5/recordkeeping}HTTPAttachmentReference" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalDocument", propOrder = {
    "mimeType",
    "string",
    "hexadecimalBinary",
    "base64Binary",
    "url",
    "attachmentReference"
})
public class ExternalDocument {

    protected MimeType mimeType;
    protected String string;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] hexadecimalBinary;
    protected byte[] base64Binary;
    @XmlSchemaType(name = "anyURI")
    protected String url;
    protected HTTPAttachmentReference attachmentReference;

    /**
     * Obtient la valeur de la propriété mimeType.
     * 
     * @return
     *     possible object is
     *     {@link MimeType }
     *     
     */
    public MimeType getMimeType() {
        return mimeType;
    }

    /**
     * Définit la valeur de la propriété mimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link MimeType }
     *     
     */
    public void setMimeType(MimeType value) {
        this.mimeType = value;
    }

    /**
     * Obtient la valeur de la propriété string.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString() {
        return string;
    }

    /**
     * Définit la valeur de la propriété string.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString(String value) {
        this.string = value;
    }

    /**
     * Obtient la valeur de la propriété hexadecimalBinary.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getHexadecimalBinary() {
        return hexadecimalBinary;
    }

    /**
     * Définit la valeur de la propriété hexadecimalBinary.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHexadecimalBinary(byte[] value) {
        this.hexadecimalBinary = value;
    }

    /**
     * Obtient la valeur de la propriété base64Binary.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBase64Binary() {
        return base64Binary;
    }

    /**
     * Définit la valeur de la propriété base64Binary.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBase64Binary(byte[] value) {
        this.base64Binary = value;
    }

    /**
     * Obtient la valeur de la propriété url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit la valeur de la propriété url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtient la valeur de la propriété attachmentReference.
     * 
     * @return
     *     possible object is
     *     {@link HTTPAttachmentReference }
     *     
     */
    public HTTPAttachmentReference getAttachmentReference() {
        return attachmentReference;
    }

    /**
     * Définit la valeur de la propriété attachmentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link HTTPAttachmentReference }
     *     
     */
    public void setAttachmentReference(HTTPAttachmentReference value) {
        this.attachmentReference = value;
    }

}
