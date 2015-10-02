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
 * A message type defining the ISDA defined Credit Event Notice. ISDA defines it as an irrevocable notice from a Notifying Party to the other party that describes a Credit Event that occurred. A Credit Event Notice must contain detail of the facts relevant to the determination that a Credit Event has occurred.
 * 
 * <p>Classe Java pour CreditEventNotification complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CreditEventNotification">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}CorrectableRequestMessage">
 *       &lt;sequence>
 *         &lt;element name="creditEventNotice" type="{http://www.fpml.org/FpML-5/recordkeeping}CreditEventNoticeDocument" minOccurs="0"/>
 *         &lt;element name="party" type="{http://www.fpml.org/FpML-5/recordkeeping}Party" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditEventNotification", propOrder = {
    "creditEventNotice",
    "party"
})
public class CreditEventNotification
    extends CorrectableRequestMessage
{

    protected CreditEventNoticeDocument creditEventNotice;
    protected List<Party> party;

    /**
     * Obtient la valeur de la propriété creditEventNotice.
     * 
     * @return
     *     possible object is
     *     {@link CreditEventNoticeDocument }
     *     
     */
    public CreditEventNoticeDocument getCreditEventNotice() {
        return creditEventNotice;
    }

    /**
     * Définit la valeur de la propriété creditEventNotice.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditEventNoticeDocument }
     *     
     */
    public void setCreditEventNotice(CreditEventNoticeDocument value) {
        this.creditEventNotice = value;
    }

    /**
     * Gets the value of the party property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the party property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Party }
     * 
     * 
     */
    public List<Party> getParty() {
        if (party == null) {
            party = new ArrayList<Party>();
        }
        return this.party;
    }

}
