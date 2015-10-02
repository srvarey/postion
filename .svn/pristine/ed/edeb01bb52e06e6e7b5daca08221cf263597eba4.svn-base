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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ReferenceObligation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReferenceObligation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}bond" minOccurs="0"/>
 *           &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}convertibleBond" minOccurs="0"/>
 *           &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}mortgage" minOccurs="0"/>
 *           &lt;element ref="{http://www.fpml.org/FpML-5/recordkeeping}loan" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="primaryObligor" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntity" minOccurs="0"/>
 *           &lt;element name="primaryObligorReference" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntityReference" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="guarantor" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntity" minOccurs="0"/>
 *           &lt;element name="guarantorReference" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntityReference" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceObligation", propOrder = {
    "bond",
    "convertibleBond",
    "mortgage",
    "loan",
    "primaryObligor",
    "primaryObligorReference",
    "guarantorOrGuarantorReference"
})
public class ReferenceObligation {

    protected Bond bond;
    protected ConvertibleBond convertibleBond;
    protected Mortgage mortgage;
    protected Loan loan;
    protected LegalEntity primaryObligor;
    protected LegalEntityReference primaryObligorReference;
    @XmlElements({
        @XmlElement(name = "guarantor", type = LegalEntity.class),
        @XmlElement(name = "guarantorReference", type = LegalEntityReference.class)
    })
    protected List<Object> guarantorOrGuarantorReference;

    /**
     * Obtient la valeur de la propriété bond.
     * 
     * @return
     *     possible object is
     *     {@link Bond }
     *     
     */
    public Bond getBond() {
        return bond;
    }

    /**
     * Définit la valeur de la propriété bond.
     * 
     * @param value
     *     allowed object is
     *     {@link Bond }
     *     
     */
    public void setBond(Bond value) {
        this.bond = value;
    }

    /**
     * Obtient la valeur de la propriété convertibleBond.
     * 
     * @return
     *     possible object is
     *     {@link ConvertibleBond }
     *     
     */
    public ConvertibleBond getConvertibleBond() {
        return convertibleBond;
    }

    /**
     * Définit la valeur de la propriété convertibleBond.
     * 
     * @param value
     *     allowed object is
     *     {@link ConvertibleBond }
     *     
     */
    public void setConvertibleBond(ConvertibleBond value) {
        this.convertibleBond = value;
    }

    /**
     * Obtient la valeur de la propriété mortgage.
     * 
     * @return
     *     possible object is
     *     {@link Mortgage }
     *     
     */
    public Mortgage getMortgage() {
        return mortgage;
    }

    /**
     * Définit la valeur de la propriété mortgage.
     * 
     * @param value
     *     allowed object is
     *     {@link Mortgage }
     *     
     */
    public void setMortgage(Mortgage value) {
        this.mortgage = value;
    }

    /**
     * Obtient la valeur de la propriété loan.
     * 
     * @return
     *     possible object is
     *     {@link Loan }
     *     
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Définit la valeur de la propriété loan.
     * 
     * @param value
     *     allowed object is
     *     {@link Loan }
     *     
     */
    public void setLoan(Loan value) {
        this.loan = value;
    }

    /**
     * Obtient la valeur de la propriété primaryObligor.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntity }
     *     
     */
    public LegalEntity getPrimaryObligor() {
        return primaryObligor;
    }

    /**
     * Définit la valeur de la propriété primaryObligor.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntity }
     *     
     */
    public void setPrimaryObligor(LegalEntity value) {
        this.primaryObligor = value;
    }

    /**
     * Obtient la valeur de la propriété primaryObligorReference.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntityReference }
     *     
     */
    public LegalEntityReference getPrimaryObligorReference() {
        return primaryObligorReference;
    }

    /**
     * Définit la valeur de la propriété primaryObligorReference.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntityReference }
     *     
     */
    public void setPrimaryObligorReference(LegalEntityReference value) {
        this.primaryObligorReference = value;
    }

    /**
     * Gets the value of the guarantorOrGuarantorReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guarantorOrGuarantorReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuarantorOrGuarantorReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegalEntity }
     * {@link LegalEntityReference }
     * 
     * 
     */
    public List<Object> getGuarantorOrGuarantorReference() {
        if (guarantorOrGuarantorReference == null) {
            guarantorOrGuarantorReference = new ArrayList<Object>();
        }
        return this.guarantorOrGuarantorReference;
    }

}
