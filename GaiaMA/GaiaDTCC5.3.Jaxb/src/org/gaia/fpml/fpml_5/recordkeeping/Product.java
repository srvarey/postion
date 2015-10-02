//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import com.abc.extension_1_3.recordkeeping.ForwardSale;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The base type which all FpML products extend.
 * 
 * <p>Classe Java pour Product complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Product">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}Product.model"/>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "primaryAssetClass",
    "secondaryAssetClass",
    "productType",
    "productId",
    "embeddedOptionType"
})
@XmlSeeAlso({
    ForwardSale.class,
    FxSwap.class,
    CapFloor.class,
    TermDeposit.class,
    CreditDefaultSwap.class,
    StandardProduct.class,
    DividendSwapTransactionSupplement.class,
    BulletPayment.class,
    Swaption.class,
    GenericProduct.class,
    CommoditySwaption.class,
    FxSingleLeg.class,
    CommoditySwap.class,
    VarianceSwapTransactionSupplement.class,
    InstrumentTradeDetails.class,
    Fra.class,
    CommodityForward.class,
    Strategy.class,
    CommodityOption.class,
    Swap.class,
    NettedSwapBase.class,
    ReturnSwapBase.class,
    Option.class,
    EquityDerivativeBase.class
})
public abstract class Product {

    protected AssetClass primaryAssetClass;
    protected List<AssetClass> secondaryAssetClass;
    protected List<ProductType> productType;
    protected List<ProductId> productId;
    protected List<EmbeddedOptionType> embeddedOptionType;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété primaryAssetClass.
     * 
     * @return
     *     possible object is
     *     {@link AssetClass }
     *     
     */
    public AssetClass getPrimaryAssetClass() {
        return primaryAssetClass;
    }

    /**
     * Définit la valeur de la propriété primaryAssetClass.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetClass }
     *     
     */
    public void setPrimaryAssetClass(AssetClass value) {
        this.primaryAssetClass = value;
    }

    /**
     * Gets the value of the secondaryAssetClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the secondaryAssetClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondaryAssetClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetClass }
     * 
     * 
     */
    public List<AssetClass> getSecondaryAssetClass() {
        if (secondaryAssetClass == null) {
            secondaryAssetClass = new ArrayList<>();
        }
        return this.secondaryAssetClass;
    }

    /**
     * Gets the value of the productType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductType }
     * 
     * 
     */
    public List<ProductType> getProductType() {
        if (productType == null) {
            productType = new ArrayList<>();
        }
        return this.productType;
    }

    /**
     * Gets the value of the productId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductId }
     * 
     * 
     */
    public List<ProductId> getProductId() {
        if (productId == null) {
            productId = new ArrayList<>();
        }
        return this.productId;
    }

    /**
     * Gets the value of the embeddedOptionType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the embeddedOptionType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmbeddedOptionType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmbeddedOptionType }
     * 
     * 
     */
    public List<EmbeddedOptionType> getEmbeddedOptionType() {
        if (embeddedOptionType == null) {
            embeddedOptionType = new ArrayList<>();
        }
        return this.embeddedOptionType;
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
