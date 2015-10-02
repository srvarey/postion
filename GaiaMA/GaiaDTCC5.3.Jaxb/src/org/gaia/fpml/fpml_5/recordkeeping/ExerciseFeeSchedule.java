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
 * A type to define a fee or schedule of fees to be payable on the exercise of an option. This fee may be defined as an amount or a percentage of the notional exercised.
 * 
 * <p>Classe Java pour ExerciseFeeSchedule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExerciseFeeSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}PayerReceiver.model"/>
 *         &lt;element name="notionalReference" type="{http://www.fpml.org/FpML-5/recordkeeping}ScheduleReference" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="feeAmountSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}AmountSchedule" minOccurs="0"/>
 *           &lt;element name="feeRateSchedule" type="{http://www.fpml.org/FpML-5/recordkeeping}Schedule" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="feePaymentDate" type="{http://www.fpml.org/FpML-5/recordkeeping}RelativeDateOffset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExerciseFeeSchedule", propOrder = {
    "payerPartyReference",
    "payerAccountReference",
    "receiverPartyReference",
    "receiverAccountReference",
    "notionalReference",
    "feeAmountSchedule",
    "feeRateSchedule",
    "feePaymentDate"
})
public class ExerciseFeeSchedule {

    protected PartyReference payerPartyReference;
    protected AccountReference payerAccountReference;
    protected PartyReference receiverPartyReference;
    protected AccountReference receiverAccountReference;
    protected ScheduleReference notionalReference;
    protected AmountSchedule feeAmountSchedule;
    protected Schedule feeRateSchedule;
    protected RelativeDateOffset feePaymentDate;

    /**
     * Obtient la valeur de la propriété payerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getPayerPartyReference() {
        return payerPartyReference;
    }

    /**
     * Définit la valeur de la propriété payerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setPayerPartyReference(PartyReference value) {
        this.payerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété payerAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getPayerAccountReference() {
        return payerAccountReference;
    }

    /**
     * Définit la valeur de la propriété payerAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setPayerAccountReference(AccountReference value) {
        this.payerAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getReceiverPartyReference() {
        return receiverPartyReference;
    }

    /**
     * Définit la valeur de la propriété receiverPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setReceiverPartyReference(PartyReference value) {
        this.receiverPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété receiverAccountReference.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getReceiverAccountReference() {
        return receiverAccountReference;
    }

    /**
     * Définit la valeur de la propriété receiverAccountReference.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setReceiverAccountReference(AccountReference value) {
        this.receiverAccountReference = value;
    }

    /**
     * Obtient la valeur de la propriété notionalReference.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleReference }
     *     
     */
    public ScheduleReference getNotionalReference() {
        return notionalReference;
    }

    /**
     * Définit la valeur de la propriété notionalReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleReference }
     *     
     */
    public void setNotionalReference(ScheduleReference value) {
        this.notionalReference = value;
    }

    /**
     * Obtient la valeur de la propriété feeAmountSchedule.
     * 
     * @return
     *     possible object is
     *     {@link AmountSchedule }
     *     
     */
    public AmountSchedule getFeeAmountSchedule() {
        return feeAmountSchedule;
    }

    /**
     * Définit la valeur de la propriété feeAmountSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountSchedule }
     *     
     */
    public void setFeeAmountSchedule(AmountSchedule value) {
        this.feeAmountSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété feeRateSchedule.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getFeeRateSchedule() {
        return feeRateSchedule;
    }

    /**
     * Définit la valeur de la propriété feeRateSchedule.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setFeeRateSchedule(Schedule value) {
        this.feeRateSchedule = value;
    }

    /**
     * Obtient la valeur de la propriété feePaymentDate.
     * 
     * @return
     *     possible object is
     *     {@link RelativeDateOffset }
     *     
     */
    public RelativeDateOffset getFeePaymentDate() {
        return feePaymentDate;
    }

    /**
     * Définit la valeur de la propriété feePaymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeDateOffset }
     *     
     */
    public void setFeePaymentDate(RelativeDateOffset value) {
        this.feePaymentDate = value;
    }

}
