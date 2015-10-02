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
 * A type defining a range of contiguous business days by defining an unadjusted first date, an unadjusted last date and a business day convention and business centers for adjusting the first and last dates if they would otherwise fall on a non business day in the specified business centers. The days between the first and last date must also be good business days in the specified centers to be counted in the range.
 * 
 * <p>Classe Java pour BusinessDateRange complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BusinessDateRange">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}DateRange">
 *       &lt;sequence>
 *         &lt;element name="businessDayConvention" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessDayConventionEnum" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCentersOrReference.model" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessDateRange", propOrder = {
    "businessDayConvention",
    "businessCentersReference",
    "businessCenters"
})
public class BusinessDateRange
    extends DateRange
{

    protected BusinessDayConventionEnum businessDayConvention;
    protected BusinessCentersReference businessCentersReference;
    protected BusinessCenters businessCenters;

    /**
     * Obtient la valeur de la propriété businessDayConvention.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDayConventionEnum }
     *     
     */
    public BusinessDayConventionEnum getBusinessDayConvention() {
        return businessDayConvention;
    }

    /**
     * Définit la valeur de la propriété businessDayConvention.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDayConventionEnum }
     *     
     */
    public void setBusinessDayConvention(BusinessDayConventionEnum value) {
        this.businessDayConvention = value;
    }

    /**
     * Obtient la valeur de la propriété businessCentersReference.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCentersReference }
     *     
     */
    public BusinessCentersReference getBusinessCentersReference() {
        return businessCentersReference;
    }

    /**
     * Définit la valeur de la propriété businessCentersReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCentersReference }
     *     
     */
    public void setBusinessCentersReference(BusinessCentersReference value) {
        this.businessCentersReference = value;
    }

    /**
     * Obtient la valeur de la propriété businessCenters.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenters }
     *     
     */
    public BusinessCenters getBusinessCenters() {
        return businessCenters;
    }

    /**
     * Définit la valeur de la propriété businessCenters.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenters }
     *     
     */
    public void setBusinessCenters(BusinessCenters value) {
        this.businessCenters = value;
    }

}
