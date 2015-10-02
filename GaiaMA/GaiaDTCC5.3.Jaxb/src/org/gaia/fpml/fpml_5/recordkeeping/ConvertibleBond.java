//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour ConvertibleBond complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ConvertibleBond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}Bond">
 *       &lt;sequence>
 *         &lt;element name="underlyingEquity" type="{http://www.fpml.org/FpML-5/recordkeeping}EquityAsset" minOccurs="0"/>
 *         &lt;element name="redemptionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConvertibleBond", propOrder = {
    "underlyingEquity",
    "redemptionDate"
})
public class ConvertibleBond
    extends Bond
{

    protected EquityAsset underlyingEquity;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar redemptionDate;

    /**
     * Obtient la valeur de la propriété underlyingEquity.
     * 
     * @return
     *     possible object is
     *     {@link EquityAsset }
     *     
     */
    public EquityAsset getUnderlyingEquity() {
        return underlyingEquity;
    }

    /**
     * Définit la valeur de la propriété underlyingEquity.
     * 
     * @param value
     *     allowed object is
     *     {@link EquityAsset }
     *     
     */
    public void setUnderlyingEquity(EquityAsset value) {
        this.underlyingEquity = value;
    }

    /**
     * Obtient la valeur de la propriété redemptionDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRedemptionDate() {
        return redemptionDate;
    }

    /**
     * Définit la valeur de la propriété redemptionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRedemptionDate(XMLGregorianCalendar value) {
        this.redemptionDate = value;
    }

}
