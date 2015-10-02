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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A structure describing a novation.
 * 
 * <p>Classe Java pour TradeNovationContent complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeNovationContent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}AbstractEvent">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}NewTrade.model"/>
 *           &lt;sequence>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}OldTrade.model"/>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FeeTrade.model" minOccurs="0"/>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}NewTrade.model" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FeeTrade.model"/>
 *             &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}NewTrade.model" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="transferor" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="transferorAccount" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="transferee" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="otherTransferee" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="transfereeAccount" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="otherTransfereeAccount" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="remainingParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="remainingPartyAccount" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="otherRemainingParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="otherRemainingPartyAccount" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="novationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="executionDateTime" type="{http://www.fpml.org/FpML-5/recordkeeping}ExecutionDateTime"/>
 *         &lt;element name="novationTradeDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="novatedAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" maxOccurs="2" minOccurs="0"/>
 *             &lt;element name="remainingAmount" type="{http://www.fpml.org/FpML-5/recordkeeping}Money" maxOccurs="2" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="novatedNumberOfOptions" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="remainingNumberOfOptions" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="novatedNumberOfUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *             &lt;element name="remainingNumberOfUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="fullFirstCalculationPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="firstPeriodStartDate" type="{http://www.fpml.org/FpML-5/recordkeeping}FirstPeriodStartDate" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="nonReliance" type="{http://www.fpml.org/FpML-5/recordkeeping}Empty" minOccurs="0"/>
 *         &lt;element name="creditDerivativesNotices" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditDerivativesNotices" minOccurs="0"/>
 *         &lt;element name="contractualDefinitions" type="{http://www.fpml.org/FpML-5/recordkeeping}ContractualDefinitions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractualTermsSupplement" type="{http://www.fpml.org/FpML-5/recordkeeping}ContractualTermsSupplement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="payment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeNovationContent", propOrder = {
    "rest"
})
public class TradeNovationContent
    extends AbstractEvent
{

    @XmlElementRefs({
        @XmlElementRef(name = "contractualTermsSupplement", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remainingNumberOfOptions", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remainingNumberOfUnits", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "transfereeAccount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "executionDateTime", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "otherRemainingPartyAccount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "novationTradeDate", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "novatedNumberOfUnits", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "transferor", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "nonReliance", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "firstPeriodStartDate", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "otherRemainingParty", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "novationDate", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "otherTransfereeAccount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "otherTransferee", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "novatedNumberOfOptions", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remainingAmount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remainingParty", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creditDerivativesNotices", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remainingPartyAccount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "novatedAmount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "newTradeIdentifier", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "transferorAccount", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "oldTrade", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "feeTradeIdentifier", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "transferee", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "feeTrade", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "oldTradeIdentifier", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "contractualDefinitions", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "fullFirstCalculationPeriod", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "newTrade", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "payment", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> rest;

    /**
     * Obtient le reste du modèle de contenu. 
     * 
     * <p>
     * Vous obtenez la propriété "catch-all" pour la raison suivante : 
     * Le nom de champ "NewTradeIdentifier" est utilisé par deux parties différentes d'un schéma. Reportez-vous à : 
     * ligne 421 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-business-events-5-3.xsd
     * ligne 421 sur file:/C:/transparence/logiciel/doc_fonctionnelle/DTCC/cross%20asset/GTR_FpMLTemplates/GTR%20FpMLTemplates%2005.18.2012/xmls/SDR/recordkeeping/fpml-business-events-5-3.xsd
     * <p>
     * Pour vous débarrasser de cette propriété, appliquez une personnalisation de propriété à l'une 
     * des deux déclarations suivantes afin de modifier leurs noms : 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ContractualTermsSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link AccountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link ExecutionDateTime }{@code >}
     * {@link JAXBElement }{@code <}{@link AccountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyReference }{@code >}
     * {@link JAXBElement }{@code <}{@link Empty }{@code >}
     * {@link JAXBElement }{@code <}{@link FirstPeriodStartDate }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyReference }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link AccountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyReference }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link Money }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyReference }{@code >}
     * {@link JAXBElement }{@code <}{@link CreditDerivativesNotices }{@code >}
     * {@link JAXBElement }{@code <}{@link AccountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link Money }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}
     * {@link JAXBElement }{@code <}{@link AccountReference }{@code >}
     * {@link JAXBElement }{@code <}{@link Trade }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyTradeIdentifier }{@code >}
     * {@link JAXBElement }{@code <}{@link Trade }{@code >}
     * {@link JAXBElement }{@code <}{@link PartyReference }{@code >}
     * {@link JAXBElement }{@code <}{@link ContractualDefinitions }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link Trade }{@code >}
     * {@link JAXBElement }{@code <}{@link Payment }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<?>>();
        }
        return this.rest;
    }

}
