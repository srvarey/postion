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
 * The physical delivery conditions specific to an oil product delivered by pipeline.
 * 
 * <p>Classe Java pour OilPipelineDelivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OilPipelineDelivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pipelineName" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPipeline" minOccurs="0"/>
 *         &lt;element name="withdrawalPoint" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPoint" minOccurs="0"/>
 *         &lt;element name="entryPoint" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryPoint" minOccurs="0"/>
 *         &lt;element name="deliverableByBarge" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="risk" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityDeliveryRisk" minOccurs="0"/>
 *         &lt;element name="cycle" type="{http://www.fpml.org/FpML-5/recordkeeping}CommodityPipelineCycle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OilPipelineDelivery", propOrder = {
    "pipelineName",
    "withdrawalPoint",
    "entryPoint",
    "deliverableByBarge",
    "risk",
    "cycle"
})
public class OilPipelineDelivery {

    protected CommodityPipeline pipelineName;
    protected CommodityDeliveryPoint withdrawalPoint;
    protected CommodityDeliveryPoint entryPoint;
    protected Boolean deliverableByBarge;
    protected CommodityDeliveryRisk risk;
    protected List<CommodityPipelineCycle> cycle;

    /**
     * Obtient la valeur de la propriété pipelineName.
     * 
     * @return
     *     possible object is
     *     {@link CommodityPipeline }
     *     
     */
    public CommodityPipeline getPipelineName() {
        return pipelineName;
    }

    /**
     * Définit la valeur de la propriété pipelineName.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityPipeline }
     *     
     */
    public void setPipelineName(CommodityPipeline value) {
        this.pipelineName = value;
    }

    /**
     * Obtient la valeur de la propriété withdrawalPoint.
     * 
     * @return
     *     possible object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public CommodityDeliveryPoint getWithdrawalPoint() {
        return withdrawalPoint;
    }

    /**
     * Définit la valeur de la propriété withdrawalPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public void setWithdrawalPoint(CommodityDeliveryPoint value) {
        this.withdrawalPoint = value;
    }

    /**
     * Obtient la valeur de la propriété entryPoint.
     * 
     * @return
     *     possible object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public CommodityDeliveryPoint getEntryPoint() {
        return entryPoint;
    }

    /**
     * Définit la valeur de la propriété entryPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityDeliveryPoint }
     *     
     */
    public void setEntryPoint(CommodityDeliveryPoint value) {
        this.entryPoint = value;
    }

    /**
     * Obtient la valeur de la propriété deliverableByBarge.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeliverableByBarge() {
        return deliverableByBarge;
    }

    /**
     * Définit la valeur de la propriété deliverableByBarge.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeliverableByBarge(Boolean value) {
        this.deliverableByBarge = value;
    }

    /**
     * Obtient la valeur de la propriété risk.
     * 
     * @return
     *     possible object is
     *     {@link CommodityDeliveryRisk }
     *     
     */
    public CommodityDeliveryRisk getRisk() {
        return risk;
    }

    /**
     * Définit la valeur de la propriété risk.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityDeliveryRisk }
     *     
     */
    public void setRisk(CommodityDeliveryRisk value) {
        this.risk = value;
    }

    /**
     * Gets the value of the cycle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cycle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCycle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityPipelineCycle }
     * 
     * 
     */
    public List<CommodityPipelineCycle> getCycle() {
        if (cycle == null) {
            cycle = new ArrayList<CommodityPipelineCycle>();
        }
        return this.cycle;
    }

}
