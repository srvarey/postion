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
 * <p>Classe Java pour LoanParticipation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="LoanParticipation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}PCDeliverableObligationCharac">
 *       &lt;sequence>
 *         &lt;element name="qualifyingParticipationSeller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanParticipation", propOrder = {
    "qualifyingParticipationSeller"
})
public class LoanParticipation
    extends PCDeliverableObligationCharac
{

    protected String qualifyingParticipationSeller;

    /**
     * Obtient la valeur de la propriété qualifyingParticipationSeller.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifyingParticipationSeller() {
        return qualifyingParticipationSeller;
    }

    /**
     * Définit la valeur de la propriété qualifyingParticipationSeller.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifyingParticipationSeller(String value) {
        this.qualifyingParticipationSeller = value;
    }

}