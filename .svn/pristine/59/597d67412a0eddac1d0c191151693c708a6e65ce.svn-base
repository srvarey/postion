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
 * A type that models a complete instruction for settling a currency payment, including the settlement method to be used, the correspondent bank, any intermediary banks and the ultimate beneficary.
 * 
 * <p>Classe Java pour SettlementInstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SettlementInstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settlementMethod" type="{http://www.fpml.org/FpML-5/recordkeeping}SettlementMethod" minOccurs="0"/>
 *         &lt;element name="correspondentInformation" type="{http://www.fpml.org/FpML-5/recordkeeping}CorrespondentInformation" minOccurs="0"/>
 *         &lt;element name="intermediaryInformation" type="{http://www.fpml.org/FpML-5/recordkeeping}IntermediaryInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="beneficiaryBank" type="{http://www.fpml.org/FpML-5/recordkeeping}Beneficiary" minOccurs="0"/>
 *         &lt;element name="beneficiary" type="{http://www.fpml.org/FpML-5/recordkeeping}Beneficiary" minOccurs="0"/>
 *         &lt;element name="depositoryPartyReference" type="{http://www.fpml.org/FpML-5/recordkeeping}PartyReference" minOccurs="0"/>
 *         &lt;element name="splitSettlement" type="{http://www.fpml.org/FpML-5/recordkeeping}SplitSettlement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettlementInstruction", propOrder = {
    "settlementMethod",
    "correspondentInformation",
    "intermediaryInformation",
    "beneficiaryBank",
    "beneficiary",
    "depositoryPartyReference",
    "splitSettlement"
})
public class SettlementInstruction {

    protected SettlementMethod settlementMethod;
    protected CorrespondentInformation correspondentInformation;
    protected List<IntermediaryInformation> intermediaryInformation;
    protected Beneficiary beneficiaryBank;
    protected Beneficiary beneficiary;
    protected PartyReference depositoryPartyReference;
    protected List<SplitSettlement> splitSettlement;

    /**
     * Obtient la valeur de la propriété settlementMethod.
     * 
     * @return
     *     possible object is
     *     {@link SettlementMethod }
     *     
     */
    public SettlementMethod getSettlementMethod() {
        return settlementMethod;
    }

    /**
     * Définit la valeur de la propriété settlementMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementMethod }
     *     
     */
    public void setSettlementMethod(SettlementMethod value) {
        this.settlementMethod = value;
    }

    /**
     * Obtient la valeur de la propriété correspondentInformation.
     * 
     * @return
     *     possible object is
     *     {@link CorrespondentInformation }
     *     
     */
    public CorrespondentInformation getCorrespondentInformation() {
        return correspondentInformation;
    }

    /**
     * Définit la valeur de la propriété correspondentInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrespondentInformation }
     *     
     */
    public void setCorrespondentInformation(CorrespondentInformation value) {
        this.correspondentInformation = value;
    }

    /**
     * Gets the value of the intermediaryInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intermediaryInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIntermediaryInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IntermediaryInformation }
     * 
     * 
     */
    public List<IntermediaryInformation> getIntermediaryInformation() {
        if (intermediaryInformation == null) {
            intermediaryInformation = new ArrayList<IntermediaryInformation>();
        }
        return this.intermediaryInformation;
    }

    /**
     * Obtient la valeur de la propriété beneficiaryBank.
     * 
     * @return
     *     possible object is
     *     {@link Beneficiary }
     *     
     */
    public Beneficiary getBeneficiaryBank() {
        return beneficiaryBank;
    }

    /**
     * Définit la valeur de la propriété beneficiaryBank.
     * 
     * @param value
     *     allowed object is
     *     {@link Beneficiary }
     *     
     */
    public void setBeneficiaryBank(Beneficiary value) {
        this.beneficiaryBank = value;
    }

    /**
     * Obtient la valeur de la propriété beneficiary.
     * 
     * @return
     *     possible object is
     *     {@link Beneficiary }
     *     
     */
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    /**
     * Définit la valeur de la propriété beneficiary.
     * 
     * @param value
     *     allowed object is
     *     {@link Beneficiary }
     *     
     */
    public void setBeneficiary(Beneficiary value) {
        this.beneficiary = value;
    }

    /**
     * Obtient la valeur de la propriété depositoryPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getDepositoryPartyReference() {
        return depositoryPartyReference;
    }

    /**
     * Définit la valeur de la propriété depositoryPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setDepositoryPartyReference(PartyReference value) {
        this.depositoryPartyReference = value;
    }

    /**
     * Gets the value of the splitSettlement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the splitSettlement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSplitSettlement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SplitSettlement }
     * 
     * 
     */
    public List<SplitSettlement> getSplitSettlement() {
        if (splitSettlement == null) {
            splitSettlement = new ArrayList<SplitSettlement>();
        }
        return this.splitSettlement;
    }

}
