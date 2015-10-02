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
 * A type defining additional information that may be recorded against a trade.
 * 
 * <p>Classe Java pour TradeInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TradeInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}TradeInformation.model"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeInformation", propOrder = {
    "relatedParty",
    "reportingRole",
    "relatedBusinessUnit",
    "relatedPerson",
    "isAccountingHedge",
    "category",
    "executionDateTime",
    "timestamps",
    "intentToAllocate",
    "allocationStatus",
    "intentToClear",
    "clearingStatus",
    "collateralizationType",
    "reportingRegime",
    "endUserException",
    "endUserExceptionDeclaration",
    "nonStandardTerms",
    "offMarketPrice",
    "largeSizeTrade",
    "executionType",
    "executionVenueType",
    "verificationMethod",
    "confirmationMethod"
})
public class TradeInformation {

    protected List<RelatedParty> relatedParty;
    protected ReportingRole reportingRole;
    protected List<RelatedBusinessUnit> relatedBusinessUnit;
    protected List<RelatedPerson> relatedPerson;
    protected Boolean isAccountingHedge;
    protected List<TradeCategory> category;
    protected ExecutionDateTime executionDateTime;
    protected TradeProcessingTimestamps timestamps;
    protected Boolean intentToAllocate;
    protected AllocationReportingStatus allocationStatus;
    protected Boolean intentToClear;
    protected ClearingStatusValue clearingStatus;
    protected CollateralizationType collateralizationType;
    protected List<ReportingRegime> reportingRegime;
    protected Boolean endUserException;
    protected EndUserExceptionDeclaration endUserExceptionDeclaration;
    protected Boolean nonStandardTerms;
    protected Boolean offMarketPrice;
    protected Boolean largeSizeTrade;
    protected ExecutionType executionType;
    protected ExecutionVenueType executionVenueType;
    protected ConfirmationMethod verificationMethod;
    protected ConfirmationMethod confirmationMethod;

    /**
     * Gets the value of the relatedParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedParty }
     * 
     * 
     */
    public List<RelatedParty> getRelatedParty() {
        if (relatedParty == null) {
            relatedParty = new ArrayList<RelatedParty>();
        }
        return this.relatedParty;
    }

    /**
     * Obtient la valeur de la propriété reportingRole.
     * 
     * @return
     *     possible object is
     *     {@link ReportingRole }
     *     
     */
    public ReportingRole getReportingRole() {
        return reportingRole;
    }

    /**
     * Définit la valeur de la propriété reportingRole.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportingRole }
     *     
     */
    public void setReportingRole(ReportingRole value) {
        this.reportingRole = value;
    }

    /**
     * Gets the value of the relatedBusinessUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedBusinessUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedBusinessUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedBusinessUnit }
     * 
     * 
     */
    public List<RelatedBusinessUnit> getRelatedBusinessUnit() {
        if (relatedBusinessUnit == null) {
            relatedBusinessUnit = new ArrayList<RelatedBusinessUnit>();
        }
        return this.relatedBusinessUnit;
    }

    /**
     * Gets the value of the relatedPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedPerson }
     * 
     * 
     */
    public List<RelatedPerson> getRelatedPerson() {
        if (relatedPerson == null) {
            relatedPerson = new ArrayList<RelatedPerson>();
        }
        return this.relatedPerson;
    }

    /**
     * Obtient la valeur de la propriété isAccountingHedge.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAccountingHedge() {
        return isAccountingHedge;
    }

    /**
     * Définit la valeur de la propriété isAccountingHedge.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAccountingHedge(Boolean value) {
        this.isAccountingHedge = value;
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
     * Obtient la valeur de la propriété executionDateTime.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionDateTime }
     *     
     */
    public ExecutionDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    /**
     * Définit la valeur de la propriété executionDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionDateTime }
     *     
     */
    public void setExecutionDateTime(ExecutionDateTime value) {
        this.executionDateTime = value;
    }

    /**
     * Obtient la valeur de la propriété timestamps.
     * 
     * @return
     *     possible object is
     *     {@link TradeProcessingTimestamps }
     *     
     */
    public TradeProcessingTimestamps getTimestamps() {
        return timestamps;
    }

    /**
     * Définit la valeur de la propriété timestamps.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeProcessingTimestamps }
     *     
     */
    public void setTimestamps(TradeProcessingTimestamps value) {
        this.timestamps = value;
    }

    /**
     * Obtient la valeur de la propriété intentToAllocate.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntentToAllocate() {
        return intentToAllocate;
    }

    /**
     * Définit la valeur de la propriété intentToAllocate.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntentToAllocate(Boolean value) {
        this.intentToAllocate = value;
    }

    /**
     * Obtient la valeur de la propriété allocationStatus.
     * 
     * @return
     *     possible object is
     *     {@link AllocationReportingStatus }
     *     
     */
    public AllocationReportingStatus getAllocationStatus() {
        return allocationStatus;
    }

    /**
     * Définit la valeur de la propriété allocationStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link AllocationReportingStatus }
     *     
     */
    public void setAllocationStatus(AllocationReportingStatus value) {
        this.allocationStatus = value;
    }

    /**
     * Obtient la valeur de la propriété intentToClear.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntentToClear() {
        return intentToClear;
    }

    /**
     * Définit la valeur de la propriété intentToClear.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntentToClear(Boolean value) {
        this.intentToClear = value;
    }

    /**
     * Obtient la valeur de la propriété clearingStatus.
     * 
     * @return
     *     possible object is
     *     {@link ClearingStatusValue }
     *     
     */
    public ClearingStatusValue getClearingStatus() {
        return clearingStatus;
    }

    /**
     * Définit la valeur de la propriété clearingStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ClearingStatusValue }
     *     
     */
    public void setClearingStatus(ClearingStatusValue value) {
        this.clearingStatus = value;
    }

    /**
     * Obtient la valeur de la propriété collateralizationType.
     * 
     * @return
     *     possible object is
     *     {@link CollateralizationType }
     *     
     */
    public CollateralizationType getCollateralizationType() {
        return collateralizationType;
    }

    /**
     * Définit la valeur de la propriété collateralizationType.
     * 
     * @param value
     *     allowed object is
     *     {@link CollateralizationType }
     *     
     */
    public void setCollateralizationType(CollateralizationType value) {
        this.collateralizationType = value;
    }

    /**
     * Gets the value of the reportingRegime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportingRegime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportingRegime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportingRegime }
     * 
     * 
     */
    public List<ReportingRegime> getReportingRegime() {
        if (reportingRegime == null) {
            reportingRegime = new ArrayList<ReportingRegime>();
        }
        return this.reportingRegime;
    }

    /**
     * Obtient la valeur de la propriété endUserException.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEndUserException() {
        return endUserException;
    }

    /**
     * Définit la valeur de la propriété endUserException.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEndUserException(Boolean value) {
        this.endUserException = value;
    }

    /**
     * Obtient la valeur de la propriété endUserExceptionDeclaration.
     * 
     * @return
     *     possible object is
     *     {@link EndUserExceptionDeclaration }
     *     
     */
    public EndUserExceptionDeclaration getEndUserExceptionDeclaration() {
        return endUserExceptionDeclaration;
    }

    /**
     * Définit la valeur de la propriété endUserExceptionDeclaration.
     * 
     * @param value
     *     allowed object is
     *     {@link EndUserExceptionDeclaration }
     *     
     */
    public void setEndUserExceptionDeclaration(EndUserExceptionDeclaration value) {
        this.endUserExceptionDeclaration = value;
    }

    /**
     * Obtient la valeur de la propriété nonStandardTerms.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonStandardTerms() {
        return nonStandardTerms;
    }

    /**
     * Définit la valeur de la propriété nonStandardTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonStandardTerms(Boolean value) {
        this.nonStandardTerms = value;
    }

    /**
     * Obtient la valeur de la propriété offMarketPrice.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOffMarketPrice() {
        return offMarketPrice;
    }

    /**
     * Définit la valeur de la propriété offMarketPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOffMarketPrice(Boolean value) {
        this.offMarketPrice = value;
    }

    /**
     * Obtient la valeur de la propriété largeSizeTrade.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLargeSizeTrade() {
        return largeSizeTrade;
    }

    /**
     * Définit la valeur de la propriété largeSizeTrade.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLargeSizeTrade(Boolean value) {
        this.largeSizeTrade = value;
    }

    /**
     * Obtient la valeur de la propriété executionType.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionType }
     *     
     */
    public ExecutionType getExecutionType() {
        return executionType;
    }

    /**
     * Définit la valeur de la propriété executionType.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionType }
     *     
     */
    public void setExecutionType(ExecutionType value) {
        this.executionType = value;
    }

    /**
     * Obtient la valeur de la propriété executionVenueType.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionVenueType }
     *     
     */
    public ExecutionVenueType getExecutionVenueType() {
        return executionVenueType;
    }

    /**
     * Définit la valeur de la propriété executionVenueType.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionVenueType }
     *     
     */
    public void setExecutionVenueType(ExecutionVenueType value) {
        this.executionVenueType = value;
    }

    /**
     * Obtient la valeur de la propriété verificationMethod.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmationMethod }
     *     
     */
    public ConfirmationMethod getVerificationMethod() {
        return verificationMethod;
    }

    /**
     * Définit la valeur de la propriété verificationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmationMethod }
     *     
     */
    public void setVerificationMethod(ConfirmationMethod value) {
        this.verificationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété confirmationMethod.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmationMethod }
     *     
     */
    public ConfirmationMethod getConfirmationMethod() {
        return confirmationMethod;
    }

    /**
     * Définit la valeur de la propriété confirmationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmationMethod }
     *     
     */
    public void setConfirmationMethod(ConfirmationMethod value) {
        this.confirmationMethod = value;
    }

}
