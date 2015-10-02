/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either flowVersion 3.0 of the License, or (at your
 * option) any later flowVersion.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.domain.trades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.referentials.GaiaUser;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.DomainUtils;
import org.gaia.domain.utils.NotAccessibleField;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "flow")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flow.findAll", query = "SELECT f FROM Flow f"),
    @NamedQuery(name = "Flow.findByFlowId", query = "SELECT f FROM Flow f WHERE f.flowId = :flowId"),
    @NamedQuery(name = "Flow.findByFlowType", query = "SELECT f FROM Flow f WHERE f.flowType = :flowType"),
    @NamedQuery(name = "Flow.findByValueDate", query = "SELECT f FROM Flow f WHERE f.valueDate = :valueDate"),
    @NamedQuery(name = "Flow.findBySettlementDate", query = "SELECT f FROM Flow f WHERE f.settlementDate = :settlementDate"),
    @NamedQuery(name = "Flow.findByProduct", query = "SELECT f FROM Flow f WHERE f.product = :product"),
    @NamedQuery(name = "Flow.findByStatus", query = "SELECT f FROM Flow f WHERE f.status = :status"),
    @NamedQuery(name = "Flow.findBySourceProductId", query = "SELECT f FROM Flow f WHERE f.sourceProductId = :sourceProductId"),
    @NamedQuery(name = "Flow.findByScheduleLineId", query = "SELECT f FROM Flow f WHERE f.scheduleLineId = :scheduleLineId"),
    @NamedQuery(name = "Flow.findByCreationDateTime", query = "SELECT f FROM Flow f WHERE f.creationDateTime = :creationDateTime"),
    @NamedQuery(name = "Flow.findByUpdateDateTime", query = "SELECT f FROM Flow f WHERE f.updateDateTime = :updateDateTime")})
public class Flow implements Serializable, Cloneable {

    public enum FlowType {

        CASH, ASSET
    };

    public enum FlowSubType {

        PRINCIPAL, INTEREST, EVENT, FEES
    };
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flow_id")
    private Integer flowId;
    @Column(name = "flow_type")
    @NotAccessibleField(level = 1)
    private String flowType;
    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    private Date valueDate;
    @Column(name = "settlement_date")
    @Temporal(TemporalType.DATE)
    private Date settlementDate;
    @Column(name = "trade_date")
    @Temporal(TemporalType.DATE)
    private Date tradeDate;
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Column(name = "quantity_type")
    private String quantityType;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "price_type")
    private String priceType;
    @AvailableValueList(isCurrency = true)
    @Column(name = "price_currency", length = 3)
    private String priceCurrency;
    @Column(name = "status")
    private String status;
    @Column(name = "source_product_id")
    private Integer sourceProductId;
    @Column(name = "schedule_line_id")
    private Integer scheduleLineId;
    @Column(name = "creation_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    @Column(name = "flow_version")
    private Integer flowVersion;
    @Column(name = "sub_type")
    private String subType;
    @Column(name = "is_collateral")
    private Boolean isCollateral = false;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trade_id")
    private Trade trade;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trader_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private GaiaUser trader;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "internal_counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity internalCounterparty;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "counterparty_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity counterparty;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ccp_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity ccp;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "clearing_member_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity clearingMember;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "market_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private LegalEntity market;
    @Column(name = "trade_type")
    private String tradeType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agreement_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Agreement agreement;

    public Flow() {
    }

    public Flow(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Integer getSourceProductId() {
        return sourceProductId;
    }

    public void setSourceProductId(Integer sourceProductId) {
        this.sourceProductId = sourceProductId;
    }

    public Integer getScheduleLineId() {
        return scheduleLineId;
    }

    public void setScheduleLineId(Integer scheduleLineId) {
        this.scheduleLineId = scheduleLineId;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Integer getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(Integer flowVersion) {
        this.flowVersion = flowVersion;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Boolean getIsCollateral() {
        return isCollateral;
    }

    public void setIsCollateral(Boolean isCollateral) {
        this.isCollateral = isCollateral;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public LegalEntity getInternalCounterparty() {
        return internalCounterparty;
    }

    public void setInternalCounterparty(LegalEntity internalCounterparty) {
        this.internalCounterparty = internalCounterparty;
    }

    public LegalEntity getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(LegalEntity counterparty) {
        this.counterparty = counterparty;
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
        hash += (flowId != null ? flowId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Flow)) {
            return false;
        }
        Flow other = (Flow) object;
        if ((this.flowId == null && other.flowId != null) || (this.flowId != null && !this.flowId.equals(other.flowId))) {
            return false;
        }
        return true;
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

    @Override
    public Flow clone() {
        Flow clonedFlow = null;
        try {
            clonedFlow = (Flow) super.clone();
            clonedFlow.setFlowId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }

        return clonedFlow;
    }

    @Override
    public String toString() {
        String ret = "Flow " + flowId;
        if (quantity != null) {

            ret += " of " + DomainUtils.decimalFormat.format(quantity);
        }
        if (product != null && product.getShortName() != null) {
            ret += StringUtils.SPACE + product.getShortName();
        }
        if (settlementDate != null) {
            ret += " on " + DomainUtils.dateFormat.format(settlementDate);
        }
        if (trade != null) {
            ret += " on trade " + trade.getTradeId();
        }
        return ret;
    }
}
