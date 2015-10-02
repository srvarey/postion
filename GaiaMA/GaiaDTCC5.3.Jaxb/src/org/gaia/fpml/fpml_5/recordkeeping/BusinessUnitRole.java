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
 * A type describing a role played by a unit in one or more transactions. Examples include roles such as Trader, Collateral, Confirmation, Settlement, etc. This can be extended to provide custom roles.
 * 
 * <p>Classe Java pour BusinessUnitRole complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BusinessUnitRole">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.fpml.org/FpML-5/recordkeeping>Scheme">
 *       &lt;attribute name="unitRoleScheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.fpml.org/coding-scheme/unit-role" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessUnitRole", propOrder = {
    "value"
})
public class BusinessUnitRole {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String value;
    @XmlAttribute(name = "unitRoleScheme")
    @XmlSchemaType(name = "anyURI")
    protected String unitRoleScheme;

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
     * Obtient la valeur de la propriété unitRoleScheme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitRoleScheme() {
        if (unitRoleScheme == null) {
            return "http://www.fpml.org/coding-scheme/unit-role";
        } else {
            return unitRoleScheme;
        }
    }

    /**
     * Définit la valeur de la propriété unitRoleScheme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitRoleScheme(String value) {
        this.unitRoleScheme = value;
    }

}
