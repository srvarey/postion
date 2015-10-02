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
 * The specification of the oil product to be delivered.
 * 
 * <p>Classe Java pour OilProduct complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OilProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.fpml.org/FpML-5/recordkeeping}OilProductType"/>
 *         &lt;element name="grade" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityProductGrade"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OilProduct", propOrder = {
    "type",
    "grade"
})
public class OilProduct {

    @XmlElement(required = true)
    protected OilProductType type;
    @XmlElement(required = true)
    protected CommodityProductGrade grade;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link OilProductType }
     *     
     */
    public OilProductType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link OilProductType }
     *     
     */
    public void setType(OilProductType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété grade.
     * 
     * @return
     *     possible object is
     *     {@link CommodityProductGrade }
     *     
     */
    public CommodityProductGrade getGrade() {
        return grade;
    }

    /**
     * Définit la valeur de la propriété grade.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityProductGrade }
     *     
     */
    public void setGrade(CommodityProductGrade value) {
        this.grade = value;
    }

}
