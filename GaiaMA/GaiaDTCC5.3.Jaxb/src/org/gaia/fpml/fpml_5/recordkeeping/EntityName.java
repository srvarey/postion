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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The name of the reference entity. A free format string. FpML does not define usage rules for this element.
 * 
 * <p>Classe Java pour EntityName complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EntityName">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.fpml.org/FpML-5/recordkeeping>Scheme">
 *       &lt;attribute name="entityNameScheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.fpml.org/spec/2003/entity-name-RED-1-0" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityName", propOrder = {
    "value"
})
public class EntityName {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String value;
    @XmlAttribute(name = "entityNameScheme")
    @XmlSchemaType(name = "anyURI")
    protected String entityNameScheme;

    /**
     * The base class for all types which define coding schemes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété entityNameScheme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityNameScheme() {
        if (entityNameScheme == null) {
            return "http://www.fpml.org/spec/2003/entity-name-RED-1-0";
        } else {
            return entityNameScheme;
        }
    }

    /**
     * Définit la valeur de la propriété entityNameScheme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityNameScheme(String value) {
        this.entityNameScheme = value;
    }

}
