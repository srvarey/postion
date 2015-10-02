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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A type describing a mortgage asset.
 * 
 * <p>Classe Java pour Mortgage complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Mortgage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fpml.org/FpML-5/recordkeeping}UnderlyingAsset">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="insurer" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntity" minOccurs="0"/>
 *           &lt;element name="insurerReference" type="{http://www.fpml.org/FpML-5/recordkeeping}LegalEntityReference" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}FixedIncomeSecurityContent.model"/>
 *         &lt;group ref="{http://www.fpml.org/FpML-5/recordkeeping}BondCalculation.model"/>
 *         &lt;element name="originalPrincipalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pool" type="{http://www.fpml.org/FpML-5/recordkeeping}AssetPool" minOccurs="0"/>
 *         &lt;element name="sector" type="{http://www.fpml.org/FpML-5/recordkeeping}MortgageSector" minOccurs="0"/>
 *         &lt;element name="tranche" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mortgage", propOrder = {
    "insurer",
    "insurerReference",
    "issuerName",
    "issuerPartyReference",
    "seniority",
    "couponType",
    "couponRate",
    "maturity",
    "paymentFrequency",
    "dayCountFraction",
    "originalPrincipalAmount",
    "pool",
    "sector",
    "tranche"
})
public class Mortgage
    extends UnderlyingAsset
{

    protected LegalEntity insurer;
    protected LegalEntityReference insurerReference;
    protected String issuerName;
    protected PartyReference issuerPartyReference;
    protected CreditSeniority seniority;
    protected CouponType couponType;
    protected BigDecimal couponRate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar maturity;
    protected Period paymentFrequency;
    protected DayCountFraction dayCountFraction;
    protected BigDecimal originalPrincipalAmount;
    protected AssetPool pool;
    protected MortgageSector sector;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String tranche;

    /**
     * Obtient la valeur de la propriété insurer.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntity }
     *     
     */
    public LegalEntity getInsurer() {
        return insurer;
    }

    /**
     * Définit la valeur de la propriété insurer.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntity }
     *     
     */
    public void setInsurer(LegalEntity value) {
        this.insurer = value;
    }

    /**
     * Obtient la valeur de la propriété insurerReference.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntityReference }
     *     
     */
    public LegalEntityReference getInsurerReference() {
        return insurerReference;
    }

    /**
     * Définit la valeur de la propriété insurerReference.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntityReference }
     *     
     */
    public void setInsurerReference(LegalEntityReference value) {
        this.insurerReference = value;
    }

    /**
     * Obtient la valeur de la propriété issuerName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerName() {
        return issuerName;
    }

    /**
     * Définit la valeur de la propriété issuerName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerName(String value) {
        this.issuerName = value;
    }

    /**
     * Obtient la valeur de la propriété issuerPartyReference.
     * 
     * @return
     *     possible object is
     *     {@link PartyReference }
     *     
     */
    public PartyReference getIssuerPartyReference() {
        return issuerPartyReference;
    }

    /**
     * Définit la valeur de la propriété issuerPartyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyReference }
     *     
     */
    public void setIssuerPartyReference(PartyReference value) {
        this.issuerPartyReference = value;
    }

    /**
     * Obtient la valeur de la propriété seniority.
     * 
     * @return
     *     possible object is
     *     {@link CreditSeniority }
     *     
     */
    public CreditSeniority getSeniority() {
        return seniority;
    }

    /**
     * Définit la valeur de la propriété seniority.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditSeniority }
     *     
     */
    public void setSeniority(CreditSeniority value) {
        this.seniority = value;
    }

    /**
     * Obtient la valeur de la propriété couponType.
     * 
     * @return
     *     possible object is
     *     {@link CouponType }
     *     
     */
    public CouponType getCouponType() {
        return couponType;
    }

    /**
     * Définit la valeur de la propriété couponType.
     * 
     * @param value
     *     allowed object is
     *     {@link CouponType }
     *     
     */
    public void setCouponType(CouponType value) {
        this.couponType = value;
    }

    /**
     * Obtient la valeur de la propriété couponRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCouponRate() {
        return couponRate;
    }

    /**
     * Définit la valeur de la propriété couponRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCouponRate(BigDecimal value) {
        this.couponRate = value;
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

    /**
     * Obtient la valeur de la propriété paymentFrequency.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Définit la valeur de la propriété paymentFrequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setPaymentFrequency(Period value) {
        this.paymentFrequency = value;
    }

    /**
     * Obtient la valeur de la propriété dayCountFraction.
     * 
     * @return
     *     possible object is
     *     {@link DayCountFraction }
     *     
     */
    public DayCountFraction getDayCountFraction() {
        return dayCountFraction;
    }

    /**
     * Définit la valeur de la propriété dayCountFraction.
     * 
     * @param value
     *     allowed object is
     *     {@link DayCountFraction }
     *     
     */
    public void setDayCountFraction(DayCountFraction value) {
        this.dayCountFraction = value;
    }

    /**
     * Obtient la valeur de la propriété originalPrincipalAmount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOriginalPrincipalAmount() {
        return originalPrincipalAmount;
    }

    /**
     * Définit la valeur de la propriété originalPrincipalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOriginalPrincipalAmount(BigDecimal value) {
        this.originalPrincipalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété pool.
     * 
     * @return
     *     possible object is
     *     {@link AssetPool }
     *     
     */
    public AssetPool getPool() {
        return pool;
    }

    /**
     * Définit la valeur de la propriété pool.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetPool }
     *     
     */
    public void setPool(AssetPool value) {
        this.pool = value;
    }

    /**
     * Obtient la valeur de la propriété sector.
     * 
     * @return
     *     possible object is
     *     {@link MortgageSector }
     *     
     */
    public MortgageSector getSector() {
        return sector;
    }

    /**
     * Définit la valeur de la propriété sector.
     * 
     * @param value
     *     allowed object is
     *     {@link MortgageSector }
     *     
     */
    public void setSector(MortgageSector value) {
        this.sector = value;
    }

    /**
     * Obtient la valeur de la propriété tranche.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranche() {
        return tranche;
    }

    /**
     * Définit la valeur de la propriété tranche.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranche(String value) {
        this.tranche = value;
    }

}
