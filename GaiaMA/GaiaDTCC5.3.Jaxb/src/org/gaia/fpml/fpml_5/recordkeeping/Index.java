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
 * A published index whose price depends on exchange traded constituents.
 * 
 * <p>Classe Java pour Index complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Index">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}ExchangeTradedCalculatedPrice">
 *       &lt;sequence>
 *         &lt;element name="futureId" type="{http://www.fpml.org/FpML-5/recordkeeping}FutureId" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Index", propOrder = {
    "futureId"
})
public class Index
    extends ExchangeTradedCalculatedPrice
{

    protected FutureId futureId;

    /**
     * Obtient la valeur de la propriété futureId.
     * 
     * @return
     *     possible object is
     *     {@link FutureId }
     *     
     */
    public FutureId getFutureId() {
        return futureId;
    }

    /**
     * Définit la valeur de la propriété futureId.
     * 
     * @param value
     *     allowed object is
     *     {@link FutureId }
     *     
     */
    public void setFutureId(FutureId value) {
        this.futureId = value;
    }

}
