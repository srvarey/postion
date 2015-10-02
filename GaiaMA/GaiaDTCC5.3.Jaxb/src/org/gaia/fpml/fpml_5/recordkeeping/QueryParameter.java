//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type representing criteria for defining a query portfolio. The criteria are made up of a QueryParameterId, QueryParameterValue and QueryParameterOperator.
 * 
 * <p>Classe Java pour QueryParameter complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="QueryParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryParameterId" type="{http://www.fpml.org/FpML-5/recordkeeping}QueryParameterId" minOccurs="0"/>
 *         &lt;element name="queryParameterValue" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="queryParameterOperator" type="{http://www.fpml.org/FpML-5/recordkeeping}QueryParameterOperator" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryParameter", propOrder = {
    "queryParameterId",
    "queryParameterValue",
    "queryParameterOperator"
})
public class QueryParameter {

    protected QueryParameterId queryParameterId;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String queryParameterValue;
    protected QueryParameterOperator queryParameterOperator;

    /**
     * Obtient la valeur de la propriété queryParameterId.
     * 
     * @return
     *     possible object is
     *     {@link QueryParameterId }
     *     
     */
    public QueryParameterId getQueryParameterId() {
        return queryParameterId;
    }

    /**
     * Définit la valeur de la propriété queryParameterId.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryParameterId }
     *     
     */
    public void setQueryParameterId(QueryParameterId value) {
        this.queryParameterId = value;
    }

    /**
     * Obtient la valeur de la propriété queryParameterValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryParameterValue() {
        return queryParameterValue;
    }

    /**
     * Définit la valeur de la propriété queryParameterValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryParameterValue(String value) {
        this.queryParameterValue = value;
    }

    /**
     * Obtient la valeur de la propriété queryParameterOperator.
     * 
     * @return
     *     possible object is
     *     {@link QueryParameterOperator }
     *     
     */
    public QueryParameterOperator getQueryParameterOperator() {
        return queryParameterOperator;
    }

    /**
     * Définit la valeur de la propriété queryParameterOperator.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryParameterOperator }
     *     
     */
    public void setQueryParameterOperator(QueryParameterOperator value) {
        this.queryParameterOperator = value;
    }

}
