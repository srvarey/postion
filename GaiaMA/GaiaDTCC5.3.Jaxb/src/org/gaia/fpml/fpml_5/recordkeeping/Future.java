//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * An exchange traded future contract.
 * 
 * <p>Classe Java pour Future complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Future">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}ExchangeTraded">
 *       &lt;sequence>
 *         &lt;element name="multiplier" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="futureContractReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maturity" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Future", propOrder = {
    "multiplier",
    "futureContractReference",
    "maturity"
})
public class Future
    extends ExchangeTraded
{

    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger multiplier;
    protected String futureContractReference;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar maturity;

    /**
     * Obtient la valeur de la propriété multiplier.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMultiplier() {
        return multiplier;
    }

    /**
     * Définit la valeur de la propriété multiplier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMultiplier(BigInteger value) {
        this.multiplier = value;
    }

    /**
     * Obtient la valeur de la propriété futureContractReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureContractReference() {
        return futureContractReference;
    }

    /**
     * Définit la valeur de la propriété futureContractReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureContractReference(String value) {
        this.futureContractReference = value;
    }

    /**
     * Obtient la valeur de la propriété maturity.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaturity() {
        return maturity;
    }

    /**
     * Définit la valeur de la propriété maturity.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaturity(XMLGregorianCalendar value) {
        this.maturity = value;
    }

}
