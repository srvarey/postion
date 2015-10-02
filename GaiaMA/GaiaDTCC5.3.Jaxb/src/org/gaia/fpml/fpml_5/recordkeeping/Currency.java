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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The code representation of a currency or fund. By default it is a valid currency code as defined by the ISO standard 4217 - Codes for representation of currencies and funds http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html.
 * 
 * <p>Classe Java pour Currency complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Currency">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.fpml.org/FpML-5/recordkeeping>Scheme">
 *       &lt;attribute name="currencyScheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.fpml.org/ext/iso4217-2001-08-15" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Currency", propOrder = {
    "value"
})
@XmlSeeAlso({
    IdentifiedCurrency.class
})
public class Currency {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String value;
    @XmlAttribute(name = "currencyScheme")
    @XmlSchemaType(name = "anyURI")
    protected String currencyScheme;

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
     * Obtient la valeur de la propriété currencyScheme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyScheme() {
        if (currencyScheme == null) {
            return "http://www.fpml.org/ext/iso4217-2001-08-15";
        } else {
            return currencyScheme;
        }
    }

    /**
     * Définit la valeur de la propriété currencyScheme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyScheme(String value) {
        this.currencyScheme = value;
    }

}
