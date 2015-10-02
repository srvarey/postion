//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A type used to record the details of a difference between two business objects/
 * 
 * <p>Classe Java pour TradeDifference complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeDifference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="differenceType" type="{http://www.fpml.org/FpML-5/recordkeeping}DifferenceTypeEnum" minOccurs="0"/>
 *         &lt;element name="differenceSeverity" type="{http://www.fpml.org/FpML-5/recordkeeping}DifferenceSeverityEnum" minOccurs="0"/>
 *         &lt;element name="element" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="missingElement" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extraElement" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeDifference", propOrder = {
    "differenceType",
    "differenceSeverity",
    "element",
    "basePath",
    "baseValue",
    "otherPath",
    "otherValue",
    "missingElement",
    "extraElement",
    "message"
})
public class TradeDifference {

    protected DifferenceTypeEnum differenceType;
    protected DifferenceSeverityEnum differenceSeverity;
    protected String element;
    protected String basePath;
    protected String baseValue;
    protected String otherPath;
    protected String otherValue;
    protected List<String> missingElement;
    protected List<String> extraElement;
    protected String message;

    /**
     * Obtient la valeur de la propriété differenceType.
     * 
     * @return
     *     possible object is
     *     {@link DifferenceTypeEnum }
     *     
     */
    public DifferenceTypeEnum getDifferenceType() {
        return differenceType;
    }

    /**
     * Définit la valeur de la propriété differenceType.
     * 
     * @param value
     *     allowed object is
     *     {@link DifferenceTypeEnum }
     *     
     */
    public void setDifferenceType(DifferenceTypeEnum value) {
        this.differenceType = value;
    }

    /**
     * Obtient la valeur de la propriété differenceSeverity.
     * 
     * @return
     *     possible object is
     *     {@link DifferenceSeverityEnum }
     *     
     */
    public DifferenceSeverityEnum getDifferenceSeverity() {
        return differenceSeverity;
    }

    /**
     * Définit la valeur de la propriété differenceSeverity.
     * 
     * @param value
     *     allowed object is
     *     {@link DifferenceSeverityEnum }
     *     
     */
    public void setDifferenceSeverity(DifferenceSeverityEnum value) {
        this.differenceSeverity = value;
    }

    /**
     * Obtient la valeur de la propriété element.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElement() {
        return element;
    }

    /**
     * Définit la valeur de la propriété element.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElement(String value) {
        this.element = value;
    }

    /**
     * Obtient la valeur de la propriété basePath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Définit la valeur de la propriété basePath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePath(String value) {
        this.basePath = value;
    }

    /**
     * Obtient la valeur de la propriété baseValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseValue() {
        return baseValue;
    }

    /**
     * Définit la valeur de la propriété baseValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseValue(String value) {
        this.baseValue = value;
    }

    /**
     * Obtient la valeur de la propriété otherPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPath() {
        return otherPath;
    }

    /**
     * Définit la valeur de la propriété otherPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPath(String value) {
        this.otherPath = value;
    }

    /**
     * Obtient la valeur de la propriété otherValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherValue() {
        return otherValue;
    }

    /**
     * Définit la valeur de la propriété otherValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherValue(String value) {
        this.otherValue = value;
    }

    /**
     * Gets the value of the missingElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the missingElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMissingElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMissingElement() {
        if (missingElement == null) {
            missingElement = new ArrayList<String>();
        }
        return this.missingElement;
    }

    /**
     * Gets the value of the extraElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExtraElement() {
        if (extraElement == null) {
            extraElement = new ArrayList<String>();
        }
        return this.extraElement;
    }

    /**
     * Obtient la valeur de la propriété message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit la valeur de la propriété message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
