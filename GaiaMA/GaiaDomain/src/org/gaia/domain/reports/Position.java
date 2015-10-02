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
package org.gaia.domain.reports;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.referentials.GaiaUser;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.IPriceable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId"),
    @NamedQuery(name = "Position.findByFlowType", query = "SELECT p FROM Position p WHERE p.flowType = :flowType"),
    @NamedQuery(name = "Position.findByIsCollateral", query = "SELECT p FROM Position p WHERE p.isCollateral = :isCollateral")})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Position implements Serializable, IPriceable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_id")
    private Integer positionId;
    @Column(name = "flow_type")
    private String flowType;
    @Column(name = "is_collateral")
    private Boolean isCollateral;
    @Column(name = "position_configuration_id")
    private Integer positionConfigurationId;
    @Transient
    private PositionHistory positionHistory; // for instrospection only
    @OneToMany(mappedBy = "position")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<PositionHistory> positionHistoryCollection;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "internal_counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AvailableValueList(LegalEntityRoleList = "InternalCounterparty")
    private LegalEntity internalCounterparty;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AvailableValueList(LegalEntityRoleList = "Counterparty,Issuer,Legal Entity,CCP")
    private LegalEntity counterparty;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trader_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private GaiaUser trader;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ccp_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AvailableValueList(LegalEntityRoleList = "CCP")
    private LegalEntity ccp;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "clearing_member_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AvailableValueList(LegalEntityRoleList = "Clearing Member")
    private LegalEntity clearingMember;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "market_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @AvailableValueList(LegalEntityRoleList = "Market")
    private LegalEntity market;
    @Column(name = "trade_type")
    private String tradeType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", insertable = false, updatable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Agreement agreement;
    @Column(name = "agreement_id")
    private Integer agreementId;

    public Position() {
    }

    public Position(String flowType, Boolean isCollateral, Product product, LegalEntity book, LegalEntity counterparty) {
        this.flowType = flowType;
        this.isCollateral = isCollateral;
        this.product = product;
        this.internalCounterparty = book;
        this.counterparty = counterparty;
    }

    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    public PositionHistory getPositionHistory(Date date) {
        if (positionHistoryCollection != null) {
            for (PositionHistory pf : positionHistoryCollection) {
                if (pf.getPositionDate().equals(date)) {
                    positionHistory = pf;
                    return pf;
                }
            }
        }
        return null;
    }

    public BigDecimal getQuantity() {
        if (positionHistoryCollection != null) {
            for (PositionHistory pf : positionHistoryCollection) {
                return pf.getQuantity();
            }
        }
        return null;
    }

    @Override
    public BigDecimal getQuantity(Date valueDate) {
        if (positionHistoryCollection != null) {
            for (PositionHistory positionHistory_ : positionHistoryCollection) {
                if (positionHistory_ != null) {
                    if (positionHistory_.getPositionDate().getTime()==valueDate.getTime()) {
                        return positionHistory_.getQuantity();
                    }
                }
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public Date getValueDate() {
        if (positionHistoryCollection != null) {
            for (PositionHistory pf : positionHistoryCollection) {
                return pf.getPositionDate();
            }
        }
        return null;
    }

    @Override
    public Integer getId() {
        return positionId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Boolean getIsCollateral() {
        return isCollateral;
    }

    public void setIsCollateral(Boolean isCollateral) {
        this.isCollateral = isCollateral;
    }

    @XmlTransient
    public Collection<PositionHistory> getPositionHistoryCollection() {
        return positionHistoryCollection;
    }

    public void setPositionHistoryCollection(Collection<PositionHistory> positionHistoryCollection) {
        this.positionHistoryCollection = positionHistoryCollection;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public LegalEntity getInternalCounterparty() {
        return internalCounterparty;
    }

    public void setInternalCounterparty(LegalEntity internalCounterparty) {
        this.internalCounterparty = internalCounterparty;
    }

    @Override
    public LegalEntity getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(LegalEntity counterparty) {
        this.counterparty = counterparty;
    }

    // do not remove : used by introspection
    public PositionHistory getPositionHistory() {
        return positionHistory;
    }

    public void setPositionHistory(PositionHistory positionHistory) {
        this.positionHistory = positionHistory;
    }

    public GaiaUser getTrader() {
        return trader;
    }

    public void setTrader(GaiaUser trader) {
        this.trader = trader;
    }

    public LegalEntity getCcp() {
        return ccp;
    }

    public void setCcp(LegalEntity ccp) {
        this.ccp = ccp;
    }

    public LegalEntity getClearingMember() {
        return clearingMember;
    }

    public void setClearingMember(LegalEntity clearingMember) {
        this.clearingMember = clearingMember;
    }

    public LegalEntity getMarket() {
        return market;
    }

    public void setMarket(LegalEntity market) {
        this.market = market;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getPositionConfigurationId() {
        return positionConfigurationId;
    }

    public void setPositionConfigurationId(Integer positionConfigurationId) {
        this.positionConfigurationId = positionConfigurationId;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Position of portfolio " + internalCounterparty.getShortName() + " on " + product.getShortName() + " id " + positionId;
    }

    @Override
    public Position clone() {
        Position myClone = null;
        try {
            myClone = (Position) super.clone();
            myClone.setPositionId(null);
            myClone.setPositionHistoryCollection(new HashSet());
            if (this.getPositionHistoryCollection()!=null){
                for (PositionHistory hist : this.getPositionHistoryCollection()){
                    myClone.getPositionHistoryCollection().add(hist.clone());
                }
            }
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }
}
