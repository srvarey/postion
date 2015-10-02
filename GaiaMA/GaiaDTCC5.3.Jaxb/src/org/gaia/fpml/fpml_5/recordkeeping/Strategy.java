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
import javax.xml.bind.annotation.XmlType;
import com.abc.extension_1_3.recordkeeping.ForwardSale;


/**
 * A type defining a group of products making up a single trade.
 * 
 * <p>Classe Java pour Strategy complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Strategy">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Product">
 *       &lt;sequence>
 *         &lt;element name="premiumProductReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ProductReference" minOccurs="0"/>
 *         &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}product" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Strategy", propOrder = {
    "premiumProductReference",
    "product"
})
public class Strategy
    extends Product
{

    protected ProductReference premiumProductReference;
    @XmlElementRef(name = "product", namespace = "http://www.fpml.org/FpML-5/recordkeeping", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends Product>> product;

    /**
     * Obtient la valeur de la propriété premiumProductReference.
     * 
     * @return
     *     possible object is
     *     {@link ProductReference }
     *     
     */
    public ProductReference getPremiumProductReference() {
        return premiumProductReference;
    }

    /**
     * Définit la valeur de la propriété premiumProductReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductReference }
     *     
     */
    public void setPremiumProductReference(ProductReference value) {
        this.premiumProductReference = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the product property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BrokerEquityOption }{@code >}
     * {@link JAXBElement }{@code <}{@link StandardProduct }{@code >}
     * {@link JAXBElement }{@code <}{@link TermDeposit }{@code >}
     * {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     * {@link JAXBElement }{@code <}{@link Fra }{@code >}
     * {@link JAXBElement }{@code <}{@link EquityForward }{@code >}
     * {@link JAXBElement }{@code <}{@link FxOption }{@code >}
     * {@link JAXBElement }{@code <}{@link CorrelationSwap }{@code >}
     * {@link JAXBElement }{@code <}{@link EquityOptionTransactionSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link Strategy }{@code >}
     * {@link JAXBElement }{@code <}{@link CommodityOption }{@code >}
     * {@link JAXBElement }{@code <}{@link VarianceSwapTransactionSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link CommoditySwap }{@code >}
     * {@link JAXBElement }{@code <}{@link EquityOption }{@code >}
     * {@link JAXBElement }{@code <}{@link CreditDefaultSwap }{@code >}
     * {@link JAXBElement }{@code <}{@link CapFloor }{@code >}
     * {@link JAXBElement }{@code <}{@link BulletPayment }{@code >}
     * {@link JAXBElement }{@code <}{@link CommodityForward }{@code >}
     * {@link JAXBElement }{@code <}{@link GenericProduct }{@code >}
     * {@link JAXBElement }{@code <}{@link InstrumentTradeDetails }{@code >}
     * {@link JAXBElement }{@code <}{@link DividendSwapTransactionSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link EquitySwapTransactionSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link ForwardSale }{@code >}
     * {@link JAXBElement }{@code <}{@link Product }{@code >}
     * {@link JAXBElement }{@code <}{@link Swap }{@code >}
     * {@link JAXBElement }{@code <}{@link FxDigitalOption }{@code >}
     * {@link JAXBElement }{@code <}{@link VarianceOptionTransactionSupplement }{@code >}
     * {@link JAXBElement }{@code <}{@link CreditDefaultSwapOption }{@code >}
     * {@link JAXBElement }{@code <}{@link FxSwap }{@code >}
     * {@link JAXBElement }{@code <}{@link ReturnSwap }{@code >}
     * {@link JAXBElement }{@code <}{@link VarianceSwap }{@code >}
     * {@link JAXBElement }{@code <}{@link CommoditySwaption }{@code >}
     * {@link JAXBElement }{@code <}{@link Swaption }{@code >}
     * {@link JAXBElement }{@code <}{@link BondOption }{@code >}
     * {@link JAXBElement }{@code <}{@link FxSingleLeg }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Product>> getProduct() {
        if (product == null) {
            product = new ArrayList<JAXBElement<? extends Product>>();
        }
        return this.product;
    }

}
