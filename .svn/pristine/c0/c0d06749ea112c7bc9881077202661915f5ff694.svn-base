//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type describing a single cap or floor rate.
 * 
 * <p>Classe Java pour Strike complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Strike">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="strikeRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="buyer" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedPayerReceiver" minOccurs="0"/>
 *         &lt;element name="seller" type="{http://www.fpml.org/FpML-5/recordkeeping}IdentifiedPayerReceiver" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Strike", propOrder = {
    "strikeRate",
    "buyer",
    "seller"
})
public class Strike {

    protected BigDecimal strikeRate;
    protected IdentifiedPayerReceiver buyer;
    protected IdentifiedPayerReceiver seller;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété strikeRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStrikeRate() {
        return strikeRate;
    }

    /**
     * Définit la valeur de la propriété strikeRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStrikeRate(BigDecimal value) {
        this.strikeRate = value;
    }

    /**
     * Obtient la valeur de la propriété buyer.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedPayerReceiver }
     *     
     */
    public IdentifiedPayerReceiver getBuyer() {
        return buyer;
    }

    /**
     * Définit la valeur de la propriété buyer.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedPayerReceiver }
     *     
     */
    public void setBuyer(IdentifiedPayerReceiver value) {
        this.buyer = value;
    }

    /**
     * Obtient la valeur de la propriété seller.
     * 
     * @return
     *     possible object is
     *     {@link IdentifiedPayerReceiver }
     *     
     */
    public IdentifiedPayerReceiver getSeller() {
        return seller;
    }

    /**
     * Définit la valeur de la propriété seller.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifiedPayerReceiver }
     *     
     */
    public void setSeller(IdentifiedPayerReceiver value) {
        this.seller = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
