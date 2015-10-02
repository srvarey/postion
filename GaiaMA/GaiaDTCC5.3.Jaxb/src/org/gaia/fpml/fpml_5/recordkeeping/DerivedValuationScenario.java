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
 * A valuation scenario that is derived from another valuation scenario.
 * 
 * <p>Classe Java pour DerivedValuationScenario complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DerivedValuationScenario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseValuationScenario" type="{http://www.fpml.org/FpML-5/recordkeeping}ValuationScenarioReference" minOccurs="0"/>
 *         &lt;element name="valuationDate" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedDate" minOccurs="0"/>
 *         &lt;element name="marketReference" type="{http://www.fpml.org/FpML-5/recordkeeping}MarketReference" minOccurs="0"/>
 *         &lt;element name="shift" type="{http://www.fpml.org/FpML-5/recordkeeping}PricingParameterShift" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "DerivedValuationScenario", propOrder = {
    "name",
    "baseValuationScenario",
    "valuationDate",
    "marketReference",
    "shift"
})
public class DerivedValuationScenario {

    protected String name;
    protected ValuationScenarioReference baseValuationScenario;
    protected IdentifiedDate valuationDate;
    protected MarketReference marketReference;
    protected List<PricingParameterShift> shift;
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
     * Obtient la valeur de la propriété baseValuationScenario.
     * 
     * @return
     *     possible object is
     *     {@link ValuationScenarioReference }
     *     
     */
    public ValuationScenarioReference getBaseValuationScenario() {
        return baseValuationScenario;
    }

    /**
     * Définit la valeur de la propriété baseValuationScenario.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuationScenarioReference }
     *     
     */
    public void setBaseValuationScenario(ValuationScenarioReference value) {
        this.baseValuationScenario = value;
    }

    /**
     * Obtient la valeur de la propriété valuationDate.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedDate }
     *     
     */
    public IdentifiedDate getValuationDate() {
        return valuationDate;
    }

    /**
     * Définit la valeur de la propriété valuationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedDate }
     *     
     */
    public void setValuationDate(IdentifiedDate value) {
        this.valuationDate = value;
    }

    /**
     * Obtient la valeur de la propriété marketReference.
     * 
     * @return
     *     possible object is
     *     {@link MarketReference }
     *     
     */
    public MarketReference getMarketReference() {
        return marketReference;
    }

    /**
     * Définit la valeur de la propriété marketReference.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketReference }
     *     
     */
    public void setMarketReference(MarketReference value) {
        this.marketReference = value;
    }

    /**
     * Gets the value of the shift property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shift property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShift().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricingParameterShift }
     * 
     * 
     */
    public List<PricingParameterShift> getShift() {
        if (shift == null) {
            shift = new ArrayList<PricingParameterShift>();
        }
        return this.shift;
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
