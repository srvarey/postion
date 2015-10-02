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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The abstract base type from which all FpML compliant messages and documents must be derived.
 * 
 * <p>Classe Java pour Document complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.fpml.org/FpML-5/recordkeeping}VersionAttributes.atts"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document")
@XmlSeeAlso({
    DataDocument.class,
    Message.class
})
public abstract class Document {

    @XmlAttribute(name = "fpmlVersion", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String fpmlVersion;
    @XmlAttribute(name = "expectedBuild")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger expectedBuild;
    @XmlAttribute(name = "actualBuild")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger actualBuild;

    /**
     * Obtient la valeur de la propriété fpmlVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFpmlVersion() {
        return fpmlVersion;
    }

    /**
     * Définit la valeur de la propriété fpmlVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFpmlVersion(String value) {
        this.fpmlVersion = value;
    }

    /**
     * Obtient la valeur de la propriété expectedBuild.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpectedBuild() {
        return expectedBuild;
    }

    /**
     * Définit la valeur de la propriété expectedBuild.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpectedBuild(BigInteger value) {
        this.expectedBuild = value;
    }

    /**
     * Obtient la valeur de la propriété actualBuild.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getActualBuild() {
        if (actualBuild == null) {
            return new BigInteger("4");
        } else {
            return actualBuild;
        }
    }

    /**
     * Définit la valeur de la propriété actualBuild.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setActualBuild(BigInteger value) {
        this.actualBuild = value;
    }

}
