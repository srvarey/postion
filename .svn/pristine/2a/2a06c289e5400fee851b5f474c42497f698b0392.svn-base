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
 * A curve consisting only of values over a term. This is a restricted form of One Dimensional Structure.
 * 
 * <p>Classe Java pour TermCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TermCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interpolationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}InterpolationMethod" minOccurs="0"/>
 *         &lt;element name="extrapolationPermitted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="point" type="{http://www.fpml.org/FpML-5/recordkeeping}TermPoint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TermCurve", propOrder = {
    "interpolationMethod",
    "extrapolationPermitted",
    "point"
})
public class TermCurve {

    protected InterpolationMethod interpolationMethod;
    protected Boolean extrapolationPermitted;
    protected List<TermPoint> point;

    /**
     * Obtient la valeur de la propriété interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link InterpolationMethod }
     *     
     */
    public InterpolationMethod getInterpolationMethod() {
        return interpolationMethod;
    }

    /**
     * Définit la valeur de la propriété interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolationMethod }
     *     
     */
    public void setInterpolationMethod(InterpolationMethod value) {
        this.interpolationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété extrapolationPermitted.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExtrapolationPermitted() {
        return extrapolationPermitted;
    }

    /**
     * Définit la valeur de la propriété extrapolationPermitted.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExtrapolationPermitted(Boolean value) {
        this.extrapolationPermitted = value;
    }

    /**
     * Gets the value of the point property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the point property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TermPoint }
     * 
     * 
     */
    public List<TermPoint> getPoint() {
        if (point == null) {
            point = new ArrayList<TermPoint>();
        }
        return this.point;
    }

}
