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

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

/**
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "scheduler")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scheduler.findAll", query = "SELECT s FROM Scheduler s"),
    @NamedQuery(name = "Scheduler.findBySchedulerId", query = "SELECT s FROM Scheduler s WHERE s.schedulerId = :schedulerId"),
    @NamedQuery(name = "Scheduler.findByFrequency", query = "SELECT s FROM Scheduler s WHERE s.frequency = :frequency"),
    @NamedQuery(name = "Scheduler.findByDaycount", query = "SELECT s FROM Scheduler s WHERE s.daycount = :daycount"),
    @NamedQuery(name = "Scheduler.findByAdjustment", query = "SELECT s FROM Scheduler s WHERE s.adjustment = :adjustment"),
    @NamedQuery(name = "Scheduler.findByRollDate", query = "SELECT s FROM Scheduler s WHERE s.rollDate = :rollDate"),
    @NamedQuery(name = "Scheduler.findByBusinessDaysRule", query = "SELECT s FROM Scheduler s WHERE s.businessDaysRule = :businessDaysRule"),
    @NamedQuery(name = "Scheduler.findByFirstDate", query = "SELECT s FROM Scheduler s WHERE s.firstDate = :firstDate"),
    @NamedQuery(name = "Scheduler.findByPenultimateDate", query = "SELECT s FROM Scheduler s WHERE s.penultimateDate = :penultimateDate"),
    @NamedQuery(name = "Scheduler.findByMaturity", query = "SELECT s FROM Scheduler s WHERE s.maturity = :maturity"),
    @NamedQuery(name = "Scheduler.findByCalendar", query = "SELECT s FROM Scheduler s WHERE s.calendar = :calendar"),
    @NamedQuery(name = "Scheduler.findByPaymentLag", query = "SELECT s FROM Scheduler s WHERE s.paymentLag = :paymentLag"),
    @NamedQuery(name = "Scheduler.findByIsPayInArrears", query = "SELECT s FROM Scheduler s WHERE s.isPayInArrears = :isPayInArrears"),
    @NamedQuery(name = "Scheduler.findByIsPayLagBusDays", query = "SELECT s FROM Scheduler s WHERE s.isPayLagBusDays = :isPayLagBusDays"),
    @NamedQuery(name = "Scheduler.findByResetLag", query = "SELECT s FROM Scheduler s WHERE s.resetLag = :resetLag"),
    @NamedQuery(name = "Scheduler.findByIsResetLagBusDays", query = "SELECT s FROM Scheduler s WHERE s.isResetLagBusDays = :isResetLagBusDays"),
    @NamedQuery(name = "Scheduler.findByIsResetInArrears", query = "SELECT s FROM Scheduler s WHERE s.isResetInArrears = :isResetInArrears"),
    @NamedQuery(name = "Scheduler.findByResetCalendar", query = "SELECT s FROM Scheduler s WHERE s.resetCalendar = :resetCalendar"),
    @NamedQuery(name = "Scheduler.findByIsCompound", query = "SELECT s FROM Scheduler s WHERE s.isCompound = :isCompound"),
    @NamedQuery(name = "Scheduler.findByCompoundMethod", query = "SELECT s FROM Scheduler s WHERE s.compoundMethod = :compoundMethod"),
    @NamedQuery(name = "Scheduler.findByCompoundFrequency", query = "SELECT s FROM Scheduler s WHERE s.compoundFrequency = :compoundFrequency")})
public class Scheduler implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduler_id")
    private Integer schedulerId;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "daycount")
    private String daycount;
    @Column(name = "adjustment")
    private String adjustment;
    @Column(name = "roll_date")
    private Short rollDate;
    @Column(name = "business_days_rule")
    private String businessDaysRule;
    @Column(name = "first_date")
    @Temporal(TemporalType.DATE)
    private Date firstDate;
    @Column(name = "penultimate_date")
    @Temporal(TemporalType.DATE)
    private Date penultimateDate;
    @Column(name = "maturity")
    @Temporal(TemporalType.DATE)
    private Date maturity;
    @Column(name = "calendar")
    private String calendar;
    @Column(name = "payment_lag")
    private Integer paymentLag = 0;
    @Column(name = "is_pay_in_arrears")
    private Boolean isPayInArrears = true;
    @Column(name = "is_pay_lag_bus_days")
    private Boolean isPayLagBusDays = true;
    @Column(name = "reset_lag")
    private Integer resetLag = 0;
    @Column(name = "is_reset_lag_bus_days")
    private Boolean isResetLagBusDays = true;
    @Column(name = "is_reset_in_arrears")
    private Boolean isResetInArrears = false;
    @Column(name = "reset_calendar")
    private String resetCalendar;
    @Column(name = "is_compound")
    private Boolean isCompound = false;
    @Column(name = "compound_method")
    private String compoundMethod;
    @Column(name = "compound_frequency")
    private String compoundFrequency;
    @Column(name = "averaging_frequency")
    private String averagingFrequency;
    @Column(name = "is_stub_at_end")
    private Boolean isStubAtEnd = false;
    @Column(name = "is_long_stub")
    private Boolean isLongStub = false;
    @Column(name = "compounding_date_rule")
    private String compoundDateRule;
    @Column(name = "averaging_date_rule")
    private String averagingDateRule;
    @OneToMany(mappedBy = "scheduler")
    @XStreamOmitField
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Product> products;

    public Scheduler() {
    }

    public Scheduler(Integer schedulerId) {
        this.schedulerId = schedulerId;
    }

    public Integer getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(Integer schedulerId) {
        this.schedulerId = schedulerId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDaycount() {
        return daycount;
    }

    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }

    public String getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    public Short getRollDate() {
        return rollDate;
    }

    public void setRollDate(Short rollDate) {
        this.rollDate = rollDate;
    }

    public String getBusinessDaysRule() {
        return businessDaysRule;
    }

    public void setBusinessDaysRule(String businessDaysRule) {
        this.businessDaysRule = businessDaysRule;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getPenultimateDate() {
        return penultimateDate;
    }

    public void setPenultimateDate(Date penultimateDate) {
        this.penultimateDate = penultimateDate;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public String getAveragingFrequency() {
        return averagingFrequency;
    }

    public void setAveragingFrequency(String averagingFrequency) {
        this.averagingFrequency = averagingFrequency;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public Integer getPaymentLag() {
        return paymentLag;
    }

    public void setPaymentLag(Integer paymentLag) {
        this.paymentLag = paymentLag;
    }

    public Boolean getIsPayInArrears() {
        return isPayInArrears;
    }

    public void setIsPayInArrears(Boolean isPayInArrears) {
        this.isPayInArrears = isPayInArrears;
    }

    public Boolean getIsPayLagBusDays() {
        return isPayLagBusDays;
    }

    public void setIsPayLagBusDays(Boolean isPayLagBusDays) {
        this.isPayLagBusDays = isPayLagBusDays;
    }

    public Integer getResetLag() {
        return resetLag;
    }

    public void setResetLag(Integer resetLag) {
        this.resetLag = resetLag;
    }

    public Boolean getIsResetLagBusDays() {
        return isResetLagBusDays;
    }

    public void setIsResetLagBusDays(Boolean isResetLagBusDays) {
        this.isResetLagBusDays = isResetLagBusDays;
    }

    public Boolean getIsResetInArrears() {
        return isResetInArrears;
    }

    public void setIsResetInArrears(Boolean isResetInArrears) {
        this.isResetInArrears = isResetInArrears;
    }

    public String getResetCalendar() {
        return resetCalendar;
    }

    public void setResetCalendar(String resetCalendar) {
        this.resetCalendar = resetCalendar;
    }

    public Boolean getIsCompound() {// dont change the name please : make cloning fail
        return isCompound;
    }

    public void setIsCompound(Boolean isCompound) {
        this.isCompound = isCompound;
    }

    public String getCompoundMethod() {
        return compoundMethod;
    }

    public void setCompoundMethod(String compoundMethod) {
        this.compoundMethod = compoundMethod;
    }

    public String getCompoundFrequency() {
        return compoundFrequency;
    }

    public void setCompoundFrequency(String compoundFrequency) {
        this.compoundFrequency = compoundFrequency;
    }

    public Boolean getIsStubAtEnd() {
        return isStubAtEnd;
    }

    public void setIsStubAtEnd(Boolean isStubAtEnd) {
        this.isStubAtEnd = isStubAtEnd;
    }

    public Boolean getIsLongStub() {
        return isLongStub;
    }

    public void setIsLongStub(Boolean isLongStub) {
        this.isLongStub = isLongStub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schedulerId != null ? schedulerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Scheduler)) {
            return false;
        }
        Scheduler other = (Scheduler) object;
        if ((this.schedulerId == null && other.schedulerId != null) || (this.schedulerId != null && !this.schedulerId.equals(other.schedulerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str="Scheduler " + schedulerId;
        if (frequency!=null){
            str+=StringUtils.SPACE+frequency;
        }
        return str;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @XmlTransient
    public Collection<Product> getProducts() {
        return products;
    }

    public String getCompoundDateRule() {
        return compoundDateRule;
    }

    public void setCompoundDateRule(String compoundDateRule) {
        this.compoundDateRule = compoundDateRule;
    }

    public String getAveragingDateRule() {
        return averagingDateRule;
    }

    public void setAveragingDateRule(String averagingDateRule) {
        this.averagingDateRule = averagingDateRule;
    }

    @Override
    public Scheduler clone() {
        Scheduler cloned = null;
        try {
            cloned = (Scheduler)super.clone();
            cloned.setSchedulerId(null);
            cloned.setProducts(new HashSet());
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }
}
