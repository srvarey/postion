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
package org.gaia.domain.trades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.DomainUtils;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "schedule_line")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleLine.findAll", query = "SELECT s FROM ScheduleLine s"),
    @NamedQuery(name = "ScheduleLine.findByScheduleLineId", query = "SELECT s FROM ScheduleLine s WHERE s.scheduleLineId = :scheduleLineId"),
    @NamedQuery(name = "ScheduleLine.findByStartDate", query = "SELECT s FROM ScheduleLine s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "ScheduleLine.findByEndDate", query = "SELECT s FROM ScheduleLine s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "ScheduleLine.findByResetDate", query = "SELECT s FROM ScheduleLine s WHERE s.resetDate = :resetDate"),
    @NamedQuery(name = "ScheduleLine.findByPaymentDate", query = "SELECT s FROM ScheduleLine s WHERE s.paymentDate = :paymentDate"),
    @NamedQuery(name = "ScheduleLine.findByNotional", query = "SELECT s FROM ScheduleLine s WHERE s.notional = :notional"),
    @NamedQuery(name = "ScheduleLine.findByFixing", query = "SELECT s FROM ScheduleLine s WHERE s.fixing = :fixing"),
    @NamedQuery(name = "ScheduleLine.findBySpread", query = "SELECT s FROM ScheduleLine s WHERE s.spread = :spread"),
    @NamedQuery(name = "ScheduleLine.findByPaymentAmount", query = "SELECT s FROM ScheduleLine s WHERE s.paymentAmount = :paymentAmount")})

public class ScheduleLine implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schedule_line_id", nullable = false)
    private Integer scheduleLineId;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "reset_date")
    @Temporal(TemporalType.DATE)
    private Date resetDate;
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(name = "notional", precision = 20, scale = 8)
    private BigDecimal notional;
    @Column(name = "fixing", precision = 16, scale = 10)
    private BigDecimal fixing;
    @Column(name = "spread", precision = 16, scale = 10)
    private BigDecimal spread;
    @Column(name = "payment_amount", precision = 20, scale = 8)
    private BigDecimal paymentAmount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "custom")
    private Boolean custom = false;
    @Column(name = "flow_id")
    private Integer flowId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "schedule_index")
    private Integer scheduleIndex;
    @Column(name = "is_fixed")
    private Boolean isFixed = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fixing_product_id")
    private Product fixingProduct;
    double period;

    @Transient
    public String type;

    public ScheduleLine() {
    }

    public ScheduleLine(Integer scheduleLineId) {
        this.scheduleLineId = scheduleLineId;
    }

    public ScheduleLine(Date resetDate, String currency, Date paymentDate, Product product, Product fixingProduct, Date startDate, Date endDate) {
        this.resetDate = resetDate;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.isFixed = false;
        this.fixing = BigDecimal.ZERO;
        this.spread = BigDecimal.ZERO;
        this.product = product;
        this.fixingProduct = fixingProduct;
        this.notional = BigDecimal.ONE;
        this.paymentAmount = BigDecimal.ZERO;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getScheduleLineId() {
        return scheduleLineId;
    }

    public void setScheduleLineId(Integer scheduleLineId) {
        this.scheduleLineId = scheduleLineId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getResetDate() {
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getNotional() {
        return notional;
    }

    public void setNotional(BigDecimal notional) {
        this.notional = notional;
    }

    public BigDecimal getFixing() {
        return fixing;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public void setFixing(BigDecimal fixing) {
        this.fixing = fixing;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Boolean isCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Boolean isFixed() {
        return isFixed;
    }

    public void setFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product _product) {
        this.product = _product;
    }

    public Integer getScheduleIndex() {
        return scheduleIndex;
    }

    public void setScheduleIndex(Integer scheduleIndex) {
        this.scheduleIndex = scheduleIndex;
    }

    public Product getFixingProduct() {
        return fixingProduct;
    }

    public void setFixingProduct(Product fixingProduct) {
        this.fixingProduct = fixingProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleLineId != null ? scheduleLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ScheduleLine)) {
            return false;
        }
        ScheduleLine other = (ScheduleLine) object;
        if ((this.scheduleLineId == null && other.scheduleLineId == null) || (this.scheduleLineId == null && other.scheduleLineId != null) || (this.scheduleLineId != null && !this.scheduleLineId.equals(other.scheduleLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String ret="ScheduleLine " + scheduleLineId;
        if (paymentAmount!=null){
            ret+=StringUtils.SPACE +DomainUtils.decimalFormat.format(paymentAmount);
        }
        ret+=StringUtils.SPACE +currency;
        if (paymentDate!=null){
            ret+=StringUtils.SPACE +DomainUtils.dateFormat.format(paymentDate);
        }
        return ret;
    }

    @Override
    public ScheduleLine clone() {
        ScheduleLine myClone = null;
        try {
            myClone = (ScheduleLine) super.clone();

        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }

}
