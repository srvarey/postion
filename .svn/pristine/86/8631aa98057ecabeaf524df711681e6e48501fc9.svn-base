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
import javax.xml.bind.annotation.XmlType;


/**
 * A limited version of the CDS type used as an underlyer to CDS options in Transparency view, to avoid requiring product type etc.
 * 
 * <p>Classe Java pour LimitedCreditDefaultSwap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="LimitedCreditDefaultSwap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="generalTerms" type="{http://www.fpml.org/FpML-5/recordkeeping}GeneralTerms"/>
 *         &lt;element name="feeLeg" type="{http://www.fpml.org/FpML-5/recordkeeping}FeeLeg"/>
 *         &lt;element name="protectionTerms" type="{http://www.fpml.org/FpML-5/recordkeeping}ProtectionTerms" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitedCreditDefaultSwap", propOrder = {
    "generalTerms",
    "feeLeg",
    "protectionTerms"
})
public class LimitedCreditDefaultSwap {

    @XmlElement(required = true)
    protected GeneralTerms generalTerms;
    @XmlElement(required = true)
    protected FeeLeg feeLeg;
    @XmlElement(required = true)
    protected List<ProtectionTerms> protectionTerms;

    /**
     * Obtient la valeur de la propriété generalTerms.
     * 
     * @return
     *     possible object is
     *     {@link GeneralTerms }
     *     
     */
    public GeneralTerms getGeneralTerms() {
        return generalTerms;
    }

    /**
     * Définit la valeur de la propriété generalTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralTerms }
     *     
     */
    public void setGeneralTerms(GeneralTerms value) {
        this.generalTerms = value;
    }

    /**
     * Obtient la valeur de la propriété feeLeg.
     * 
     * @return
     *     possible object is
     *     {@link FeeLeg }
     *     
     */
    public FeeLeg getFeeLeg() {
        return feeLeg;
    }

    /**
     * Définit la valeur de la propriété feeLeg.
     * 
     * @param value
     *     allowed object is
     *     {@link FeeLeg }
     *     
     */
    public void setFeeLeg(FeeLeg value) {
        this.feeLeg = value;
    }

    /**
     * Gets the value of the protectionTerms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protectionTerms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtectionTerms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProtectionTerms }
     * 
     * 
     */
    public List<ProtectionTerms> getProtectionTerms() {
        if (protectionTerms == null) {
            protectionTerms = new ArrayList<ProtectionTerms>();
        }
        return this.protectionTerms;
    }

}
