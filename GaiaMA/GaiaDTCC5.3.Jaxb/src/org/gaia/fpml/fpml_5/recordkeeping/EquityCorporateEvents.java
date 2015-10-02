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
 * A type for defining the merger events and their treatment.
 * 
 * <p>Classe Java pour EquityCorporateEvents complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EquityCorporateEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shareForShare" type="{http://www.fpml.org/FpML-5/recordkeeping}ShareExtraordinaryEventEnum" minOccurs="0"/>
 *         &lt;element name="shareForOther" type="{http://www.fpml.org/FpML-5/recordkeeping}ShareExtraordinaryEventEnum" minOccurs="0"/>
 *         &lt;element name="shareForCombined" type="{http://www.fpml.org/FpML-5/recordkeeping}ShareExtraordinaryEventEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquityCorporateEvents", propOrder = {
    "shareForShare",
    "shareForOther",
    "shareForCombined"
})
public class EquityCorporateEvents {

    protected ShareExtraordinaryEventEnum shareForShare;
    protected ShareExtraordinaryEventEnum shareForOther;
    protected ShareExtraordinaryEventEnum shareForCombined;

    /**
     * Obtient la valeur de la propriété shareForShare.
     * 
     * @return
     *     possible object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public ShareExtraordinaryEventEnum getShareForShare() {
        return shareForShare;
    }

    /**
     * Définit la valeur de la propriété shareForShare.
     * 
     * @param value
     *     allowed object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public void setShareForShare(ShareExtraordinaryEventEnum value) {
        this.shareForShare = value;
    }

    /**
     * Obtient la valeur de la propriété shareForOther.
     * 
     * @return
     *     possible object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public ShareExtraordinaryEventEnum getShareForOther() {
        return shareForOther;
    }

    /**
     * Définit la valeur de la propriété shareForOther.
     * 
     * @param value
     *     allowed object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public void setShareForOther(ShareExtraordinaryEventEnum value) {
        this.shareForOther = value;
    }

    /**
     * Obtient la valeur de la propriété shareForCombined.
     * 
     * @return
     *     possible object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public ShareExtraordinaryEventEnum getShareForCombined() {
        return shareForCombined;
    }

    /**
     * Définit la valeur de la propriété shareForCombined.
     * 
     * @param value
     *     allowed object is
     *     {@link ShareExtraordinaryEventEnum }
     *     
     */
    public void setShareForCombined(ShareExtraordinaryEventEnum value) {
        this.shareForCombined = value;
    }

}
