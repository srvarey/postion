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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Describes the characteristics for European exercise of FX products.
 * 
 * <p>Classe Java pour FxEuropeanExercise complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxEuropeanExercise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Exercise">
 *       &lt;sequence>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="expiryTime" type="{http://www.fpml.org/FpML-5/recordkeeping}BusinessCenterTime" minOccurs="0"/>
 *         &lt;element name="cutName" type="{http://www.fpml.org/FpML-5/recordkeeping}CutName" minOccurs="0"/>
 *         &lt;element name="valueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxEuropeanExercise", propOrder = {
    "expiryDate",
    "expiryTime",
    "cutName",
    "valueDate"
})
public class FxEuropeanExercise
    extends Exercise
{

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate;
    protected BusinessCenterTime expiryTime;
    protected CutName cutName;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar valueDate;

    /**
     * Obtient la valeur de la propriété expiryDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Définit la valeur de la propriété expiryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Obtient la valeur de la propriété expiryTime.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenterTime }
     *     
     */
    public BusinessCenterTime getExpiryTime() {
        return expiryTime;
    }

    /**
     * Définit la valeur de la propriété expiryTime.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenterTime }
     *     
     */
    public void setExpiryTime(BusinessCenterTime value) {
        this.expiryTime = value;
    }

    /**
     * Obtient la valeur de la propriété cutName.
     * 
     * @return
     *     possible object is
     *     {@link CutName }
     *     
     */
    public CutName getCutName() {
        return cutName;
    }

    /**
     * Définit la valeur de la propriété cutName.
     * 
     * @param value
     *     allowed object is
     *     {@link CutName }
     *     
     */
    public void setCutName(CutName value) {
        this.cutName = value;
    }

    /**
     * Obtient la valeur de la propriété valueDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValueDate() {
        return valueDate;
    }

    /**
     * Définit la valeur de la propriété valueDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValueDate(XMLGregorianCalendar value) {
        this.valueDate = value;
    }

}
