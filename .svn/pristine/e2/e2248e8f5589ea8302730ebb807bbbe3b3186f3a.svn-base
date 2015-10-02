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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.abc.extension_1_3.recordkeeping.ForwardSale;


/**
 * A type defining an FpML trade.
 * 
 * <p>Classe Java pour Trade complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Trade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeHeader" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeHeader" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}product" minOccurs="0"/>
 *         &lt;element name="otherPartyPayment" type="{http://www.fpml.org/FpML-5/recordkeeping}Payment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="brokerPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}CalculationAgent.model"/>
 *         &lt;element name="determiningParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="hedgingParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="collateral" type="{http://www.fpml.org/FpML-5/recordkeeping}Collateral" minOccurs="0"/>
 *         &lt;element name="documentation" type="{http://www.fpml.org/FpML-5/recordkeeping}Documentation" minOccurs="0"/>
 *         &lt;element name="governingLaw" type="{http://www.fpml.org/FpML-5/recordkeeping}GoverningLaw" minOccurs="0"/>
 *         &lt;element name="allocations" type="{http://www.fpml.org/FpML-5/recordkeeping}Allocations" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trade", propOrder = {
    "tradeHeader",
    "product",
    "otherPartyPayment",
    "brokerPartyReference",
    "calculationAgent",
    "calculationAgentBusinessCenter",
    "determiningParty",
    "hedgingParty",
    "collateral",
    "documentation",
    "governingLaw",
    "allocations"
})
public class Trade {

    protected TradeHeader tradeHeader;
    @XmlElementRef(name = "product", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends Product> product;
    protected List<Payment> otherPartyPayment;
    protected List<PartyReference> brokerPartyReference;
    protected CalculationAgent calculationAgent;
    protected BusinessCenter calculationAgentBusinessCenter;
    protected List<PartyReference> determiningParty;
    protected List<PartyReference> hedgingParty;
    protected Collateral collateral;
    protected Documentation documentation;
    protected GoverningLaw governingLaw;
    protected Allocations allocations;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété tradeHeader.
     * 
     * @return
     *     possible object is
     *     {@link TradeHeader }
     *     
     */
    public TradeHeader getTradeHeader() {
        return tradeHeader;
    }

    /**
     * Définit la valeur de la propriété tradeHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeHeader }
     *     
     */
    public void setTradeHeader(TradeHeader value) {
        this.tradeHeader = value;
    }

    /**
     * Obtient la valeur de la propriété product.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BrokerEquityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link StandardProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link TermDeposit }{@code >}
     *     {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link Fra }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityForward }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link CorrelationSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityOptionTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link Strategy }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceSwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommoditySwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditDefaultSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link CapFloor }{@code >}
     *     {@link JAXBElement }{@code <}{@link BulletPayment }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityForward }{@code >}
     *     {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstrumentTradeDetails }{@code >}
     *     {@link JAXBElement }{@code <}{@link DividendSwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquitySwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link ForwardSale }{@code >}
     *     {@link JAXBElement }{@code <}{@link Product }{@code >}
     *     {@link JAXBElement }{@code <}{@link Swap }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxDigitalOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceOptionTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditDefaultSwapOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link ReturnSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommoditySwaption }{@code >}
     *     {@link JAXBElement }{@code <}{@link Swaption }{@code >}
     *     {@link JAXBElement }{@code <}{@link BondOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxSingleLeg }{@code >}
     *     
     */
    public JAXBElement<? extends Product> getProduct() {
        return product;
    }

    /**
     * Définit la valeur de la propriété product.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BrokerEquityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link StandardProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link TermDeposit }{@code >}
     *     {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link Fra }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityForward }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link CorrelationSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityOptionTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link Strategy }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceSwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommoditySwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquityOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditDefaultSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link CapFloor }{@code >}
     *     {@link JAXBElement }{@code <}{@link BulletPayment }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommodityForward }{@code >}
     *     {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstrumentTradeDetails }{@code >}
     *     {@link JAXBElement }{@code <}{@link DividendSwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquitySwapTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link ForwardSale }{@code >}
     *     {@link JAXBElement }{@code <}{@link Product }{@code >}
     *     {@link JAXBElement }{@code <}{@link Swap }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxDigitalOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceOptionTransactionSupplement }{@code >}
     *     {@link JAXBElement }{@code <}{@link CreditDefaultSwapOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link ReturnSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link VarianceSwap }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommoditySwaption }{@code >}
     *     {@link JAXBElement }{@code <}{@link Swaption }{@code >}
     *     {@link JAXBElement }{@code <}{@link BondOption }{@code >}
     *     {@link JAXBElement }{@code <}{@link FxSingleLeg }{@code >}
     *     
     */
    public void setProduct(JAXBElement<? extends Product> value) {
        this.product = value;
    }

    /**
     * Gets the value of the otherPartyPayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherPartyPayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherPartyPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payment }
     * 
     * 
     */
    public List<Payment> getOtherPartyPayment() {
        if (otherPartyPayment == null) {
            otherPartyPayment = new ArrayList<Payment>();
        }
        return this.otherPartyPayment;
    }

    /**
     * Gets the value of the brokerPartyReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brokerPartyReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrokerPartyReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getBrokerPartyReference() {
        if (brokerPartyReference == null) {
            brokerPartyReference = new ArrayList<PartyReference>();
        }
        return this.brokerPartyReference;
    }

    /**
     * Obtient la valeur de la propriété calculationAgent.
     * 
     * @return
     *     possible object is
     *     {@link CalculationAgent }
     *     
     */
    public CalculationAgent getCalculationAgent() {
        return calculationAgent;
    }

    /**
     * Définit la valeur de la propriété calculationAgent.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationAgent }
     *     
     */
    public void setCalculationAgent(CalculationAgent value) {
        this.calculationAgent = value;
    }

    /**
     * Obtient la valeur de la propriété calculationAgentBusinessCenter.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCenter }
     *     
     */
    public BusinessCenter getCalculationAgentBusinessCenter() {
        return calculationAgentBusinessCenter;
    }

    /**
     * Définit la valeur de la propriété calculationAgentBusinessCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCenter }
     *     
     */
    public void setCalculationAgentBusinessCenter(BusinessCenter value) {
        this.calculationAgentBusinessCenter = value;
    }

    /**
     * Gets the value of the determiningParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the determiningParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeterminingParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getDeterminingParty() {
        if (determiningParty == null) {
            determiningParty = new ArrayList<PartyReference>();
        }
        return this.determiningParty;
    }

    /**
     * Gets the value of the hedgingParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hedgingParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHedgingParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyReference }
     * 
     * 
     */
    public List<PartyReference> getHedgingParty() {
        if (hedgingParty == null) {
            hedgingParty = new ArrayList<PartyReference>();
        }
        return this.hedgingParty;
    }

    /**
     * Obtient la valeur de la propriété collateral.
     * 
     * @return
     *     possible object is
     *     {@link Collateral }
     *     
     */
    public Collateral getCollateral() {
        return collateral;
    }

    /**
     * Définit la valeur de la propriété collateral.
     * 
     * @param value
     *     allowed object is
     *     {@link Collateral }
     *     
     */
    public void setCollateral(Collateral value) {
        this.collateral = value;
    }

    /**
     * Obtient la valeur de la propriété documentation.
     * 
     * @return
     *     possible object is
     *     {@link Documentation }
     *     
     */
    public Documentation getDocumentation() {
        return documentation;
    }

    /**
     * Définit la valeur de la propriété documentation.
     * 
     * @param value
     *     allowed object is
     *     {@link Documentation }
     *     
     */
    public void setDocumentation(Documentation value) {
        this.documentation = value;
    }

    /**
     * Obtient la valeur de la propriété governingLaw.
     * 
     * @return
     *     possible object is
     *     {@link GoverningLaw }
     *     
     */
    public GoverningLaw getGoverningLaw() {
        return governingLaw;
    }

    /**
     * Définit la valeur de la propriété governingLaw.
     * 
     * @param value
     *     allowed object is
     *     {@link GoverningLaw }
     *     
     */
    public void setGoverningLaw(GoverningLaw value) {
        this.governingLaw = value;
    }

    /**
     * Obtient la valeur de la propriété allocations.
     * 
     * @return
     *     possible object is
     *     {@link Allocations }
     *     
     */
    public Allocations getAllocations() {
        return allocations;
    }

    /**
     * Définit la valeur de la propriété allocations.
     * 
     * @param value
     *     allowed object is
     *     {@link Allocations }
     *     
     */
    public void setAllocations(Allocations value) {
        this.allocations = value;
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
