/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.domain.reports;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.gaia.domain.utils.NotAccessibleField;

/**
 *
 * @author user
 */
@Entity
@Table(name = "position_historical_measure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionHistoricalMeasure.findAll", query = "SELECT p FROM PositionHistoricalMeasure p"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByPositionHistoricalMeasureId", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.positionHistoricalMeasureId = :positionHistoricalMeasureId"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByQuotationType", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.quotationType = :quotationType"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByMeasureName", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.measureName = :measureName"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByMeasureValue", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.measureValue = :measureValue"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByCurrency", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.currency = :currency"),
    @NamedQuery(name = "PositionHistoricalMeasure.findBySource", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.source = :source"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByProvider", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.provider = :provider"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByStatus", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.status = :status"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByCreationDate", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.creationDate = :creationDate"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByUpdateDate", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.updateDate = :updateDate"),
    @NamedQuery(name = "PositionHistoricalMeasure.findByComment", query = "SELECT p FROM PositionHistoricalMeasure p WHERE p.comment = :comment")})


public class PositionHistoricalMeasure implements Serializable, Cloneable {

    public enum PositionMeasure implements Serializable{ MARGIN_GLOBAL,MARGIN_SPAN,MARGIN_VARIATION,MARGIN_BROKERAGE}

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_historical_measure_id")
    private Integer positionHistoricalMeasureId;
    @Column(name = "quotation_type")
    private String quotationType;
    @Column(name = "measure_name")
    private String measureName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "measure_value")
    private BigDecimal measureValue;
    @Column(name = "currency")
    private String currency;
    @Column(name = "source")
    private String source;
    @Column(name = "provider")
    private String provider;
    @Column(name = "status")
    private String status;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    @ManyToOne
    @NotAccessibleField(level=1)
    private Position position;
    @Column(name = "position_date")
    @Temporal(TemporalType.DATE)
    private Date positionDate;

    public PositionHistoricalMeasure() {
    }

    public PositionHistoricalMeasure(Integer positionHistoricalMeasureId) {
        this.positionHistoricalMeasureId = positionHistoricalMeasureId;
    }

    public Integer getPositionHistoricalMeasureId() {
        return positionHistoricalMeasureId;
    }

    public void setPositionHistoricalMeasureId(Integer positionHistoricalMeasureId) {
        this.positionHistoricalMeasureId = positionHistoricalMeasureId;
    }

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public BigDecimal getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(BigDecimal measureValue) {
        this.measureValue = measureValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionHistoricalMeasureId != null ? positionHistoricalMeasureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PositionHistoricalMeasure)) {
            return false;
        }
        PositionHistoricalMeasure other = (PositionHistoricalMeasure) object;
        if ((this.positionHistoricalMeasureId == null && other.positionHistoricalMeasureId != null) || (this.positionHistoricalMeasureId != null && !this.positionHistoricalMeasureId.equals(other.positionHistoricalMeasureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PositionHistoricalMeasure[ positionHistoricalMeasureId=" + positionHistoricalMeasureId + " ]";
    }

    @Override
    public Object clone() {
            PositionHistoricalMeasure phm = null;
            try {
                    phm = (PositionHistoricalMeasure)super.clone();
                    phm.setPositionHistoricalMeasureId(null);
                    phm.setPosition(this.position);
            } catch(CloneNotSupportedException cnse) {
                    cnse.printStackTrace(System.err);
            }
            return phm;
    }
}
