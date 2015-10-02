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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour RelatedBusinessUnit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RelatedBusinessUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessUnitReference" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessUnitReference"/>
 *         &lt;element name="role" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessUnitRole"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedBusinessUnit", propOrder = {
    "businessUnitReference",
    "role"
})
public class RelatedBusinessUnit {

    @XmlElement(required = true)
    protected BusinessUnitReference businessUnitReference;
    @XmlElement(required = true)
    protected BusinessUnitRole role;

    /**
     * Obtient la valeur de la propriété businessUnitReference.
     * 
     * @return
     *     possible object is
     *     {@link BusinessUnitReference }
     *     
     */
    public BusinessUnitReference getBusinessUnitReference() {
        return businessUnitReference;
    }

    /**
     * Définit la valeur de la propriété businessUnitReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessUnitReference }
     *     
     */
    public void setBusinessUnitReference(BusinessUnitReference value) {
        this.businessUnitReference = value;
    }

    /**
     * Obtient la valeur de la propriété role.
     * 
     * @return
     *     possible object is
     *     {@link BusinessUnitRole }
     *     
     */
    public BusinessUnitRole getRole() {
        return role;
    }

    /**
     * Définit la valeur de la propriété role.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessUnitRole }
     *     
     */
    public void setRole(BusinessUnitRole value) {
        this.role = value;
    }

}
