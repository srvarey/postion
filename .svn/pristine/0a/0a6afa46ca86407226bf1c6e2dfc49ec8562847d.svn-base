//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Describes the resource that contains the media representation of a business event (i.e used for stating the Publicly Available Information). For example, can describe a file or a URL that represents the event. This type is an extended version of a type defined by RIXML (www.rixml.org).
 * 
 * <p>Classe Java pour Resource complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Resource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceId" type="{http://www.fpml.org/FpML-5/recordkeeping}ResourceId" minOccurs="0"/>
 *         &lt;element name="resourceType" type="{http://www.fpml.org/FpML-5/recordkeeping}ResourceType" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.fpml.org/FpML-5/recordkeeping}Language" minOccurs="0"/>
 *         &lt;element name="sizeInBytes" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="length" type="{http://www.fpml.org/FpML-5/recordkeeping}ResourceLength" minOccurs="0"/>
 *         &lt;element name="mimeType" type="{http://www.fpml.org/FpML-5/recordkeeping}MimeType" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="string" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="hexadecimalBinary" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *           &lt;element name="base64Binary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *           &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
@XmlType(name = "Resource", propOrder = {
    "resourceId",
    "resourceType",
    "language",
    "sizeInBytes",
    "length",
    "mimeType",
    "name",
    "comments",
    "string",
    "hexadecimalBinary",
    "base64Binary",
    "url"
})
public class Resource {

    protected ResourceId resourceId;
    protected ResourceType resourceType;
    protected Language language;
    protected BigDecimal sizeInBytes;
    protected ResourceLength length;
    protected MimeType mimeType;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String name;
    protected String comments;
    protected String string;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] hexadecimalBinary;
    protected byte[] base64Binary;
    @XmlSchemaType(name = "anyURI")
    protected String url;

    /**
     * Obtient la valeur de la propriété resourceId.
     * 
     * @return
     *     possible object is
     *     {@link ResourceId }
     *     
     */
    public ResourceId getResourceId() {
        return resourceId;
    }

    /**
     * Définit la valeur de la propriété resourceId.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceId }
     *     
     */
    public void setResourceId(ResourceId value) {
        this.resourceId = value;
    }

    /**
     * Obtient la valeur de la propriété resourceType.
     * 
     * @return
     *     possible object is
     *     {@link ResourceType }
     *     
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Définit la valeur de la propriété resourceType.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceType }
     *     
     */
    public void setResourceType(ResourceType value) {
        this.resourceType = value;
    }

    /**
     * Obtient la valeur de la propriété language.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Définit la valeur de la propriété language.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguage(Language value) {
        this.language = value;
    }

    /**
     * Obtient la valeur de la propriété sizeInBytes.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * Définit la valeur de la propriété sizeInBytes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSizeInBytes(BigDecimal value) {
        this.sizeInBytes = value;
    }

    /**
     * Obtient la valeur de la propriété length.
     * 
     * @return
     *     possible object is
     *     {@link ResourceLength }
     *     
     */
    public ResourceLength getLength() {
        return length;
    }

    /**
     * Définit la valeur de la propriété length.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceLength }
     *     
     */
    public void setLength(ResourceLength value) {
        this.length = value;
    }

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
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété comments.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Définit la valeur de la propriété comments.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
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

}
