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
 * An entity for defining the definitions that govern the document and should include the year and type of definitions referenced, along with any relevant documentation (such as master agreement) and the date it was signed.
 * 
 * <p>Classe Java pour Documentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Documentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="masterAgreement" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterAgreement" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="masterConfirmation" type="{http://www.fpml.org/FpML-5/recordkeeping}MasterConfirmation" minOccurs="0"/>
 *           &lt;element name="brokerConfirmation" type="{http://www.fpml.org/FpML-5/recordkeeping}BrokerConfirmation" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="contractualDefinitions" type="{http://www.fpml.org/FpML-5/recordkeeping}ContractualDefinitions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractualTermsSupplement" type="{http://www.fpml.org/FpML-5/recordkeeping}ContractualTermsSupplement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractualMatrix" type="{http://www.fpml.org/FpML-5/recordkeeping}ContractualMatrix" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="creditSupportAgreement" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditSupportAgreement" minOccurs="0"/>
 *         &lt;element name="attachment" type="{http://www.fpml.org/FpML-5/recordkeeping}Resource" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documentation", propOrder = {
    "masterAgreement",
    "masterConfirmation",
    "brokerConfirmation",
    "contractualDefinitions",
    "contractualTermsSupplement",
    "contractualMatrix",
    "creditSupportAgreement",
    "attachment"
})
public class Documentation {

    protected MasterAgreement masterAgreement;
    protected MasterConfirmation masterConfirmation;
    protected BrokerConfirmation brokerConfirmation;
    protected List<ContractualDefinitions> contractualDefinitions;
    protected List<ContractualTermsSupplement> contractualTermsSupplement;
    protected List<ContractualMatrix> contractualMatrix;
    protected CreditSupportAgreement creditSupportAgreement;
    protected List<Resource> attachment;

    /**
     * Obtient la valeur de la propriété masterAgreement.
     * 
     * @return
     *     possible object is
     *     {@link MasterAgreement }
     *     
     */
    public MasterAgreement getMasterAgreement() {
        return masterAgreement;
    }

    /**
     * Définit la valeur de la propriété masterAgreement.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterAgreement }
     *     
     */
    public void setMasterAgreement(MasterAgreement value) {
        this.masterAgreement = value;
    }

    /**
     * Obtient la valeur de la propriété masterConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link MasterConfirmation }
     *     
     */
    public MasterConfirmation getMasterConfirmation() {
        return masterConfirmation;
    }

    /**
     * Définit la valeur de la propriété masterConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterConfirmation }
     *     
     */
    public void setMasterConfirmation(MasterConfirmation value) {
        this.masterConfirmation = value;
    }

    /**
     * Obtient la valeur de la propriété brokerConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link BrokerConfirmation }
     *     
     */
    public BrokerConfirmation getBrokerConfirmation() {
        return brokerConfirmation;
    }

    /**
     * Définit la valeur de la propriété brokerConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerConfirmation }
     *     
     */
    public void setBrokerConfirmation(BrokerConfirmation value) {
        this.brokerConfirmation = value;
    }

    /**
     * Gets the value of the contractualDefinitions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractualDefinitions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractualDefinitions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractualDefinitions }
     * 
     * 
     */
    public List<ContractualDefinitions> getContractualDefinitions() {
        if (contractualDefinitions == null) {
            contractualDefinitions = new ArrayList<ContractualDefinitions>();
        }
        return this.contractualDefinitions;
    }

    /**
     * Gets the value of the contractualTermsSupplement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractualTermsSupplement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractualTermsSupplement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractualTermsSupplement }
     * 
     * 
     */
    public List<ContractualTermsSupplement> getContractualTermsSupplement() {
        if (contractualTermsSupplement == null) {
            contractualTermsSupplement = new ArrayList<ContractualTermsSupplement>();
        }
        return this.contractualTermsSupplement;
    }

    /**
     * Gets the value of the contractualMatrix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractualMatrix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractualMatrix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractualMatrix }
     * 
     * 
     */
    public List<ContractualMatrix> getContractualMatrix() {
        if (contractualMatrix == null) {
            contractualMatrix = new ArrayList<ContractualMatrix>();
        }
        return this.contractualMatrix;
    }

    /**
     * Obtient la valeur de la propriété creditSupportAgreement.
     * 
     * @return
     *     possible object is
     *     {@link CreditSupportAgreement }
     *     
     */
    public CreditSupportAgreement getCreditSupportAgreement() {
        return creditSupportAgreement;
    }

    /**
     * Définit la valeur de la propriété creditSupportAgreement.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditSupportAgreement }
     *     
     */
    public void setCreditSupportAgreement(CreditSupportAgreement value) {
        this.creditSupportAgreement = value;
    }

    /**
     * Gets the value of the attachment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Resource }
     * 
     * 
     */
    public List<Resource> getAttachment() {
        if (attachment == null) {
            attachment = new ArrayList<Resource>();
        }
        return this.attachment;
    }

}
