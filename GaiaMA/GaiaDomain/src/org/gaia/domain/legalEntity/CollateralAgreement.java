/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.domain.legalEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.NotAccessibleField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "collateral_agreement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "CollateralAgreement.findAll", query = "SELECT c FROM CollateralAgreement c")})
public class CollateralAgreement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "agreement_id")
    private Integer agreementId;
    @Column(name = "frequency")
    private String frequency;
    /**
     * //
     *
     * @Max(value=?)
     * @Min(value=?)//if you know range of your decimal fields consider using
     * these annotations to enforce field validation
     */
    @Column(name = "mta_1")
    private BigDecimal mta1;
    @Column(name = "mta_2")
    private BigDecimal mta2;
    @Column(name = "mta_currency")
    private String mtaCurrency;
    @Column(name = "collateral_type")
    private String collateralType;
    @Column(name = "delivery_currency")
    @AvailableValueList
    private String deliveryCurrency;
    @Column(name = "rounding_amount")
    private BigDecimal roundingAmount;
    @Column(name = "rounding_method")
    private String roundingMethod;
    @Column(name = "eligible_instrument_filter")
    private String eligibleInstrumentFilter;
    @OneToOne(optional = false)
    @JoinColumn(name = "agreement_id", referencedColumnName = "agreement_id", insertable = false, updatable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAccessibleField(level = 1)
    private Agreement agreement;

    public CollateralAgreement() {
    }

    public CollateralAgreement(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getMta1() {
        return mta1;
    }

    public void setMta1(BigDecimal mta1) {
        this.mta1 = mta1;
    }

    public BigDecimal getMta2() {
        return mta2;
    }

    public void setMta2(BigDecimal mta2) {
        this.mta2 = mta2;
    }

    public String getMtaCurrency() {
        return mtaCurrency;
    }

    public void setMtaCurrency(String mtaCurrency) {
        this.mtaCurrency = mtaCurrency;
    }

    public String getCollateralType() {
        return collateralType;
    }

    public void setCollateralType(String collateralType) {
        this.collateralType = collateralType;
    }

    public String getDeliveryCurrency() {
        return deliveryCurrency;
    }

    public void setDeliveryCurrency(String deliveryCurrency) {
        this.deliveryCurrency = deliveryCurrency;
    }

    public BigDecimal getRoundingAmount() {
        return roundingAmount;
    }

    public void setRoundingAmount(BigDecimal roundingAmount) {
        this.roundingAmount = roundingAmount;
    }

    public String getRoundingMethod() {
        return roundingMethod;
    }

    public void setRoundingMethod(String roundingMethod) {
        this.roundingMethod = roundingMethod;
    }

    public String getEligibleInstrumentFilter() {
        return eligibleInstrumentFilter;
    }

    public void setEligibleInstrumentFilter(String eligibleInstrumentFilter) {
        this.eligibleInstrumentFilter = eligibleInstrumentFilter;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agreementId != null ? agreementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CollateralAgreement)) {
            return false;
        }
        CollateralAgreement other = (CollateralAgreement) object;
        if ((this.agreementId == null && other.agreementId != null) || (this.agreementId != null && !this.agreementId.equals(other.agreementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.legalEntity.CollateralAgreement[ agreementId=" + agreementId + " ]";
    }
}
