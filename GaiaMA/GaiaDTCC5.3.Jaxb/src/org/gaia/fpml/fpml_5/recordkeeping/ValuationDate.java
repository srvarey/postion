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
 * <p>Classe Java pour ValuationDate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ValuationDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="singleValuationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}SingleValuationDate" minOccurs="0"/>
 *         &lt;element name="multipleValuationDates" type="{http://www.fpml.org/FpML-5/recordkeeping}MultipleValuationDates" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValuationDate", propOrder = {
    "singleValuationDate",
    "multipleValuationDates"
})
public class ValuationDate {

    protected SingleValuationDate singleValuationDate;
    protected MultipleValuationDates multipleValuationDates;

    /**
     * Obtient la valeur de la propriété singleValuationDate.
     * 
     * @return
     *     possible object is
     *     {@link SingleValuationDate }
     *     
     */
    public SingleValuationDate getSingleValuationDate() {
        return singleValuationDate;
    }

    /**
     * Définit la valeur de la propriété singleValuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleValuationDate }
     *     
     */
    public void setSingleValuationDate(SingleValuationDate value) {
        this.singleValuationDate = value;
    }

    /**
     * Obtient la valeur de la propriété multipleValuationDates.
     * 
     * @return
     *     possible object is
     *     {@link MultipleValuationDates }
     *     
     */
    public MultipleValuationDates getMultipleValuationDates() {
        return multipleValuationDates;
    }

    /**
     * Définit la valeur de la propriété multipleValuationDates.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleValuationDates }
     *     
     */
    public void setMultipleValuationDates(MultipleValuationDates value) {
        this.multipleValuationDates = value;
    }

}
