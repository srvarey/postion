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
 * Records supporting information justifying an end user exception under 17 CFR part 39.
 * 
 * <p>Classe Java pour EndUserExceptionDeclaration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EndUserExceptionDeclaration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditDocument" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditDocument" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="organizationCharacteristic" type="{http://www.fpml.org/FpML-5/recordkeeping}OrganizationCharacteristic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="transactionCharacteristic" type="{http://www.fpml.org/FpML-5/recordkeeping}TransactionCharacteristic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="supervisorRegistration" type="{http://www.fpml.org/FpML-5/recordkeeping}SupervisorRegistration" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndUserExceptionDeclaration", propOrder = {
    "creditDocument",
    "organizationCharacteristic",
    "transactionCharacteristic",
    "supervisorRegistration"
})
public class EndUserExceptionDeclaration {

    protected List<CreditDocument> creditDocument;
    protected List<OrganizationCharacteristic> organizationCharacteristic;
    protected List<TransactionCharacteristic> transactionCharacteristic;
    protected List<SupervisorRegistration> supervisorRegistration;

    /**
     * Gets the value of the creditDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditDocument }
     * 
     * 
     */
    public List<CreditDocument> getCreditDocument() {
        if (creditDocument == null) {
            creditDocument = new ArrayList<CreditDocument>();
        }
        return this.creditDocument;
    }

    /**
     * Gets the value of the organizationCharacteristic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationCharacteristic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationCharacteristic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganizationCharacteristic }
     * 
     * 
     */
    public List<OrganizationCharacteristic> getOrganizationCharacteristic() {
        if (organizationCharacteristic == null) {
            organizationCharacteristic = new ArrayList<OrganizationCharacteristic>();
        }
        return this.organizationCharacteristic;
    }

    /**
     * Gets the value of the transactionCharacteristic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionCharacteristic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionCharacteristic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionCharacteristic }
     * 
     * 
     */
    public List<TransactionCharacteristic> getTransactionCharacteristic() {
        if (transactionCharacteristic == null) {
            transactionCharacteristic = new ArrayList<TransactionCharacteristic>();
        }
        return this.transactionCharacteristic;
    }

    /**
     * Gets the value of the supervisorRegistration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supervisorRegistration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupervisorRegistration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupervisorRegistration }
     * 
     * 
     */
    public List<SupervisorRegistration> getSupervisorRegistration() {
        if (supervisorRegistration == null) {
            supervisorRegistration = new ArrayList<SupervisorRegistration>();
        }
        return this.supervisorRegistration;
    }

}
