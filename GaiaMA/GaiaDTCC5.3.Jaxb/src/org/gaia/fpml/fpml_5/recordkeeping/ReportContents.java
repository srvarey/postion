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
 * A type used to describe the scope/contents of a report.
 * 
 * <p>Classe Java pour ReportContents complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReportContents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="accountReference" type="{http://www.fpml.org/FpML-5/recordkeeping}AccountReference" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.fpml.org/FpML-5/recordkeeping}TradeCategory" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="primaryAssetClass" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetClass" minOccurs="0"/>
 *           &lt;element name="secondaryAssetClass" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetClass" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="productType" type="{http://www.fpml.org/FpML-5/recordkeeping}ProductType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="queryPortfolio" type="{http://www.fpml.org/FpML-5/recordkeeping}QueryPortfolio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportContents", propOrder = {
    "partyReference",
    "accountReference",
    "category",
    "primaryAssetClass",
    "secondaryAssetClass",
    "productType",
    "queryPortfolio"
})
public class ReportContents {

    protected PartyReference partyReference;
    protected AccountReference accountReference;
    protected List<TradeCategory> category;
    protected AssetClass primaryAssetClass;
    protected List<AssetClass> secondaryAssetClass;
    protected List<ProductType> productType;
    protected QueryPortfolio queryPortfolio;

    /**
     * Obtient la valeur de la propriété partyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getPartyReference() {
        return partyReference;
    }

    /**
     * Définit la valeur de la propriété partyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setPartyReference(PartyReference value) {
        this.partyReference = value;
    }

    /**
     * Obtient la valeur de la propriété accountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getAccountReference() {
        return accountReference;
    }

    /**
     * Définit la valeur de la propriété accountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setAccountReference(AccountReference value) {
        this.accountReference = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TradeCategory }
     * 
     * 
     */
    public List<TradeCategory> getCategory() {
        if (category == null) {
            category = new ArrayList<TradeCategory>();
        }
        return this.category;
    }

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
            secondaryAssetClass = new ArrayList<AssetClass>();
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
            productType = new ArrayList<ProductType>();
        }
        return this.productType;
    }

    /**
     * Obtient la valeur de la propriété queryPortfolio.
     * 
     * @return
     *     possible object is
     *     {@link QueryPortfolio }
     *     
     */
    public QueryPortfolio getQueryPortfolio() {
        return queryPortfolio;
    }

    /**
     * Définit la valeur de la propriété queryPortfolio.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryPortfolio }
     *     
     */
    public void setQueryPortfolio(QueryPortfolio value) {
        this.queryPortfolio = value;
    }

}
