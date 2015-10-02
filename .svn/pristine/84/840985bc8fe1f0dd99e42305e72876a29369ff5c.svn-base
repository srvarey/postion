//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * A type describing the strike price.
 * 
 * <p>Classe Java pour Price complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Price">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commission" type="{http://www.fpml.org/FpML-5/recordkeeping}Commission" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="determinationMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}DeterminationMethod"/>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}EquityPrice.model" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="amountRelativeTo" type="{http://www.fpml.org/FpML-5/recordkeeping}AmountReference"/>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}EquityPrice.model"/>
 *         &lt;/choice>
 *         &lt;element name="cleanNetPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="quotationCharacteristics" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotationCharacteristics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Price", propOrder = {
    "content"
})
@XmlSeeAlso({
    ReturnLegValuationPrice.class
})
public class Price {

    @XmlElementRefs({
        @XmlElementRef(name = "determinationMethod", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "quotationCharacteristics", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "commission", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "grossPrice", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "amountRelativeTo", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "netPrice", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cleanNetPrice", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "fxConversion", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "accruedInterestPrice", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Obtient le reste du modèle de contenu. 
     * 
     * <p>
     * Vous obtenez la propriété "catch-all" pour la raison suivante : 
     * Le nom de champ "GrossPrice" est utilisé par deux parties différentes d'un schéma. Reportez-vous à : 
     * ligne 1371 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-asset-5-3.xsd
     * ligne 1371 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-asset-5-3.xsd
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
     * {@link JAXBElement }{@code <}{@link DeterminationMethod }{@code >}
     * {@link JAXBElement }{@code <}{@link QuotationCharacteristics }{@code >}
     * {@link JAXBElement }{@code <}{@link Commission }{@code >}
     * {@link JAXBElement }{@code <}{@link ActualPrice }{@code >}
     * {@link JAXBElement }{@code <}{@link AmountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link ActualPrice }{@code >}
     * {@link JAXBElement }{@code <}{@link FxConversion }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
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
