//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PhysicalSettlementTerms complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PhysicalSettlementTerms">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}SettlementTerms">
 *       &lt;sequence>
 *         &lt;element name="physicalSettlementPeriod" type="{http://www.fpml.org/FpML-5/recordkeeping}PhysicalSettlementPeriod" minOccurs="0"/>
 *         &lt;element name="deliverableObligations" type="{http://www.fpml.org/FpML-5/recordkeeping}DeliverableObligations" minOccurs="0"/>
 *         &lt;element name="escrow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sixtyBusinessDaySettlementCap" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalSettlementTerms", propOrder = {
    "physicalSettlementPeriod",
    "deliverableObligations",
    "escrow",
    "sixtyBusinessDaySettlementCap"
})
public class PhysicalSettlementTerms
    extends SettlementTerms
{

    protected PhysicalSettlementPeriod physicalSettlementPeriod;
    protected DeliverableObligations deliverableObligations;
    protected Boolean escrow;
    protected Boolean sixtyBusinessDaySettlementCap;

    /**
     * Obtient la valeur de la propriété physicalSettlementPeriod.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalSettlementPeriod }
     *     
     */
    public PhysicalSettlementPeriod getPhysicalSettlementPeriod() {
        return physicalSettlementPeriod;
    }

    /**
     * Définit la valeur de la propriété physicalSettlementPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalSettlementPeriod }
     *     
     */
    public void setPhysicalSettlementPeriod(PhysicalSettlementPeriod value) {
        this.physicalSettlementPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété deliverableObligations.
     * 
     * @return
     *     possible object is
     *     {@link DeliverableObligations }
     *     
     */
    public DeliverableObligations getDeliverableObligations() {
        return deliverableObligations;
    }

    /**
     * Définit la valeur de la propriété deliverableObligations.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliverableObligations }
     *     
     */
    public void setDeliverableObligations(DeliverableObligations value) {
        this.deliverableObligations = value;
    }

    /**
     * Obtient la valeur de la propriété escrow.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEscrow() {
        return escrow;
    }

    /**
     * Définit la valeur de la propriété escrow.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEscrow(Boolean value) {
        this.escrow = value;
    }

    /**
     * Obtient la valeur de la propriété sixtyBusinessDaySettlementCap.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSixtyBusinessDaySettlementCap() {
        return sixtyBusinessDaySettlementCap;
    }

    /**
     * Définit la valeur de la propriété sixtyBusinessDaySettlementCap.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSixtyBusinessDaySettlementCap(Boolean value) {
        this.sixtyBusinessDaySettlementCap = value;
    }

}
