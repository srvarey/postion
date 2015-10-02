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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A set of valuation inputs and results. This structure can be used for requesting valuations, or for reporting them. In general, the request fills in fewer elements.
 * 
 * <p>Classe Java pour ValuationSet complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ValuationSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valuationScenario" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valuationScenarioReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenarioReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="baseParty" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="quotationCharacteristics" type="{http://www.fpml.org/FpML-5/recordkeeping}QuotationCharacteristics" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sensitivitySetDefinition" type="{http://www.fpml.org/FpML-5/recordkeeping}SensitivitySetDefinition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="detail" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationSetDetail" minOccurs="0"/>
 *         &lt;element name="assetValuation" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetValuation" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ValuationSet", propOrder = {
    "name",
    "valuationScenario",
    "valuationScenarioReference",
    "baseParty",
    "quotationCharacteristics",
    "sensitivitySetDefinition",
    "detail",
    "assetValuation"
})
public class ValuationSet {

    protected String name;
    protected List<ValuationScenario> valuationScenario;
    protected List<ValuationScenarioReference> valuationScenarioReference;
    protected PartyReference baseParty;
    protected List<QuotationCharacteristics> quotationCharacteristics;
    protected List<SensitivitySetDefinition> sensitivitySetDefinition;
    protected ValuationSetDetail detail;
    protected List<AssetValuation> assetValuation;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the valuationScenario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valuationScenario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValuationScenario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuationScenario }
     * 
     * 
     */
    public List<ValuationScenario> getValuationScenario() {
        if (valuationScenario == null) {
            valuationScenario = new ArrayList<ValuationScenario>();
        }
        return this.valuationScenario;
    }

    /**
     * Gets the value of the valuationScenarioReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valuationScenarioReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValuationScenarioReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuationScenarioReference }
     * 
     * 
     */
    public List<ValuationScenarioReference> getValuationScenarioReference() {
        if (valuationScenarioReference == null) {
            valuationScenarioReference = new ArrayList<ValuationScenarioReference>();
        }
        return this.valuationScenarioReference;
    }

    /**
     * Obtient la valeur de la propriété baseParty.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getBaseParty() {
        return baseParty;
    }

    /**
     * Définit la valeur de la propriété baseParty.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setBaseParty(PartyReference value) {
        this.baseParty = value;
    }

    /**
     * Gets the value of the quotationCharacteristics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quotationCharacteristics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuotationCharacteristics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QuotationCharacteristics }
     * 
     * 
     */
    public List<QuotationCharacteristics> getQuotationCharacteristics() {
        if (quotationCharacteristics == null) {
            quotationCharacteristics = new ArrayList<QuotationCharacteristics>();
        }
        return this.quotationCharacteristics;
    }

    /**
     * Gets the value of the sensitivitySetDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensitivitySetDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensitivitySetDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SensitivitySetDefinition }
     * 
     * 
     */
    public List<SensitivitySetDefinition> getSensitivitySetDefinition() {
        if (sensitivitySetDefinition == null) {
            sensitivitySetDefinition = new ArrayList<SensitivitySetDefinition>();
        }
        return this.sensitivitySetDefinition;
    }

    /**
     * Obtient la valeur de la propriété detail.
     * 
     * @return
     *     possible object is
     *     {@link ValuationSetDetail }
     *     
     */
    public ValuationSetDetail getDetail() {
        return detail;
    }

    /**
     * Définit la valeur de la propriété detail.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationSetDetail }
     *     
     */
    public void setDetail(ValuationSetDetail value) {
        this.detail = value;
    }

    /**
     * Gets the value of the assetValuation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetValuation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetValuation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetValuation }
     * 
     * 
     */
    public List<AssetValuation> getAssetValuation() {
        if (assetValuation == null) {
            assetValuation = new ArrayList<AssetValuation>();
        }
        return this.assetValuation;
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
