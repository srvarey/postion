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
 * <p>Classe Java pour CreditSupportAgreementIdentifier complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CreditSupportAgreementIdentifier">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.fpml.org/FpML-5/recordkeeping>Scheme">
 *       &lt;attribute name="creditSupportAgreementIdScheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditSupportAgreementIdentifier", propOrder = {
    "value"
})
public class CreditSupportAgreementIdentifier {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String value;
    @XmlAttribute(name = "creditSupportAgreementIdScheme")
    @XmlSchemaType(name = "anyURI")
    protected String creditSupportAgreementIdScheme;

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
     * Obtient la valeur de la propriété creditSupportAgreementIdScheme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditSupportAgreementIdScheme() {
        return creditSupportAgreementIdScheme;
    }

    /**
     * Définit la valeur de la propriété creditSupportAgreementIdScheme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditSupportAgreementIdScheme(String value) {
        this.creditSupportAgreementIdScheme = value;
    }

}
