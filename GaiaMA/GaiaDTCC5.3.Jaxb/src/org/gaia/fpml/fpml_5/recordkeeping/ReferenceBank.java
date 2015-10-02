//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type to describe an institution (party) identified by means of a coding scheme and an optional name.
 * 
 * <p>Classe Java pour ReferenceBank complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReferenceBank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="referenceBankId" type="{http://www.fpml.org/FpML-5/recordkeeping}ReferenceBankId" minOccurs="0"/>
 *         &lt;element name="referenceBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceBank", propOrder = {
    "referenceBankId",
    "referenceBankName"
})
public class ReferenceBank {

    protected ReferenceBankId referenceBankId;
    protected String referenceBankName;

    /**
     * Obtient la valeur de la propriété referenceBankId.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceBankId }
     *     
     */
    public ReferenceBankId getReferenceBankId() {
        return referenceBankId;
    }

    /**
     * Définit la valeur de la propriété referenceBankId.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceBankId }
     *     
     */
    public void setReferenceBankId(ReferenceBankId value) {
        this.referenceBankId = value;
    }

    /**
     * Obtient la valeur de la propriété referenceBankName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceBankName() {
        return referenceBankName;
    }

    /**
     * Définit la valeur de la propriété referenceBankName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceBankName(String value) {
        this.referenceBankName = value;
    }

}
