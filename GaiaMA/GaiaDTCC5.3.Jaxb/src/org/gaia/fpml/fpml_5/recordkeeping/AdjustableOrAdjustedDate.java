//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type for defining a date that shall be subject to adjustment if it would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the date.
 * 
 * <p>Classe Java pour AdjustableOrAdjustedDate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdjustableOrAdjustedDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}AdjustableDate.model"/>
 *         &lt;element name="adjustedDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdjustableOrAdjustedDate", propOrder = {
    "content"
})
public class AdjustableOrAdjustedDate {

    @XmlElementRefs({
        @XmlElementRef(name = "adjustedDate", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "dateAdjustments", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "unadjustedDate", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient le reste du modèle de contenu. 
     * 
     * <p>
     * Vous obtenez la propriété "catch-all" pour la raison suivante : 
     * Le nom de champ "AdjustedDate" est utilisé par deux parties différentes d'un schéma. Reportez-vous à : 
     * ligne 404 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-shared-5-3.xsd
     * ligne 3772 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-shared-5-3.xsd
     * <p>
     * Pour vous débarrasser de cette propriété, appliquez une personnalisation de propriété à l'une 
     * des deux déclarations suivantes afin de modifier leurs noms : 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BusinessDayAdjustments }{@code >}
     * {@link JAXBElement }{@code <}{@link IdentifiedDate }{@code >}
     * {@link JAXBElement }{@code <}{@link IdentifiedDate }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
