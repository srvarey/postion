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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the features that may be present in an FX option.
 * 
 * <p>Classe Java pour FxOptionFeatures complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FxOptionFeatures">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="asian" type="{http://www.fpml.org/FpML-5/recordkeeping}FxAsianFeature"/>
 *           &lt;element name="barrier" type="{http://www.fpml.org/FpML-5/recordkeeping}FxBarrierFeature" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="barrier" type="{http://www.fpml.org/FpML-5/recordkeeping}FxBarrierFeature" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxOptionFeatures", propOrder = {
    "content"
})
public class FxOptionFeatures {

    @XmlElementRefs({
        @XmlElementRef(name = "barrier", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "asian", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Obtient le reste du modèle de contenu. 
     * 
     * <p>
     * Vous obtenez la propriété "catch-all" pour la raison suivante : 
     * Le nom de champ "Barrier" est utilisé par deux parties différentes d'un schéma. Reportez-vous à : 
     * ligne 550 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-fx-5-3.xsd
     * ligne 548 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-fx-5-3.xsd
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
     * {@link JAXBElement }{@code <}{@link FxBarrierFeature }{@code >}
     * {@link JAXBElement }{@code <}{@link FxAsianFeature }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
